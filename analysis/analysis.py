import statsmodels.api as sm
import pandas as pd
import numpy as np
import pymysql
import os

path = os.getcwd() + '/analysis/'

# 文件路径
conn = pymysql.connect(host='localhost', port=3306, user='root', password='root', database='iot_platform',
                       charset='utf8')
sql = 'select * from data'
cursor = conn.cursor()
cursor.execute(sql)
conn.commit()
data = cursor.fetchall()
temperature = []
humidity = []
light = []
height = []
invalid_temperature = 0  # 缺失温度
invalid_humidity = 0  # 缺失湿度
invalid_light = 0  # 缺失光照
invalid_height = 0  # 缺失高度
temperature_frequency = {}  # 温度频率统计
humidity_frequency = {}  # 湿度频率统计
light_frequency = {}  # 光照频率统计
height_frequency = {}  # 高度频率统计
temperature_descriptive_statistics = {'最小值': 1e9, '最大值': -1e9, '平均值': 0, '标准差': 0}
humidity_descriptive_statistics = {'最小值': 1e9, '最大值': -1e9, '平均值': 0, '标准差': 0}
light_descriptive_statistics = {'最小值': 1e9, '最大值': -1e9, '平均值': 0, '标准差': 0}
height_descriptive_statistics = {'最小值': 1e9, '最大值': -1e9, '平均值': 0, '标准差': 0}

for i in data:
    if i[2] == 'TEMPERATURE':  # 温度传感器
        if i[3] > 100:
            invalid_temperature += 1
            continue
        if i[3] in temperature_frequency:
            temperature_frequency[str(i[3])]['频率'] += 1
        else:
            temperature_frequency[str(i[3])] = {'频率': 1, '百分比': 0, '有效百分比': 0, '累积百分比': 0}
        temperature.append(i[3])
        temperature_descriptive_statistics['最小值'] = min(temperature_descriptive_statistics['最小值'], i[3])
        temperature_descriptive_statistics['最大值'] = max(temperature_descriptive_statistics['最大值'], i[3])
    elif i[2] == 'HUMIDITY':  # 湿度传感器
        if i[3] > 100:
            invalid_humidity += 1
            continue
        if i[3] in humidity_frequency:
            humidity_frequency[str(i[3])]['频率'] += 1
        else:
            humidity_frequency[str(i[3])] = {'频率': 1, '百分比': 0, '有效百分比': 0, '累积百分比': 0}
        humidity.append(i[3])
        humidity_descriptive_statistics['最小值'] = min(humidity_descriptive_statistics['最小值'], i[3])
        humidity_descriptive_statistics['最大值'] = max(humidity_descriptive_statistics['最大值'], i[3])
    elif i[2] == 'LIGHT_INTENSITY':  # 光照传感器
        if i[3] > 100:
            invalid_light += 1
            continue
        if i[3] in light_frequency:
            light_frequency[str(i[3])]['频率'] += 1
        else:
            light_frequency[str(i[3])] = {'频率': 1, '百分比': 0, '有效百分比': 0, '累积百分比': 0}
        light.append(i[3])
        light_descriptive_statistics['最小值'] = min(light_descriptive_statistics['最小值'], i[3])
        light_descriptive_statistics['最大值'] = max(light_descriptive_statistics['最大值'], i[3])
    elif i[2] == 'HEIGHT':  # 高度传感器
        if i[3] in height_frequency:
            height_frequency[str(i[3])]['频率'] += 1
        else:
            height_frequency[str(i[3])] = {'频率': 1, '百分比': 0, '有效百分比': 0, '累积百分比': 0}
        height.append(i[3])
        height_descriptive_statistics['最小值'] = min(height_descriptive_statistics['最小值'], i[3])
        height_descriptive_statistics['最大值'] = max(height_descriptive_statistics['最大值'], i[3])

if temperature:
    temperature_descriptive_statistics['平均值'] = np.mean(temperature)
    temperature_descriptive_statistics['标准差'] = np.std(temperature)
if humidity:
    humidity_descriptive_statistics['平均值'] = np.mean(humidity)
    humidity_descriptive_statistics['标准差'] = np.std(humidity)
if light:
    light_descriptive_statistics['平均值'] = np.mean(light)
    light_descriptive_statistics['标准差'] = np.std(light)
if height:
    height_descriptive_statistics['平均值'] = np.mean(height)
    height_descriptive_statistics['标准差'] = np.std(height)
accumulative_percentage = 0
for key in temperature_frequency.keys():
    temperature_frequency[key]['百分比'] = temperature_frequency[key]['频率'] / len(temperature) * 100
    accumulative_percentage += temperature_frequency[key]['百分比']
    temperature_frequency[key]['有效百分比'] = temperature_frequency[key]['频率'] / (
            len(temperature) + invalid_temperature) * 100
    temperature_frequency[key]['累积百分比'] = accumulative_percentage
accumulative_percentage = 0
for key in humidity_frequency.keys():
    humidity_frequency[key]['百分比'] = humidity_frequency[key]['频率'] / len(humidity)
    accumulative_percentage += humidity_frequency[key]['百分比']
    humidity_frequency[key]['有效百分比'] = humidity_frequency[key]['频率'] / (len(humidity) + invalid_humidity)
    humidity_frequency[key]['累积百分比'] = accumulative_percentage
accumulative_percentage = 0
for key in light_frequency.keys():
    light_frequency[key]['百分比'] = light_frequency[key]['频率'] / len(light)
    accumulative_percentage += light_frequency[key]['百分比']
    light_frequency[key]['有效百分比'] = light_frequency[key]['频率'] / (len(light) + invalid_light)
    light_frequency[key]['累积百分比'] = accumulative_percentage
accumulative_percentage = 0
for key in height_frequency.keys():
    height_frequency[key]['百分比'] = height_frequency[key]['频率'] / len(height)
    accumulative_percentage += height_frequency[key]['百分比']
    height_frequency[key]['有效百分比'] = height_frequency[key]['频率'] / (len(height) + invalid_height)
    height_frequency[key]['累积百分比'] = accumulative_percentage
f = open(path + 'data_analysis_template.html', 'r', encoding='UTF-8')
out = f.read()
f.close()
out = out.replace('{temperature_valid}', str(len(temperature)))
out = out.replace('{humidity_valid}', str(len(humidity)))
out = out.replace('{light_valid}', str(len(humidity)))
out = out.replace('{height_valid}', str(len(height)))
out = out.replace('{temperature_invalid}', str(invalid_temperature))
out = out.replace('{humidity_invalid}', str(invalid_humidity))
out = out.replace('{light_invalid}', str(invalid_humidity))
out = out.replace('{height_invalid}', str(invalid_height))
out = out.replace('{temperature0}', str(len(temperature)))
out = out.replace('{temperature1}', '{:.2f}'.format(temperature_descriptive_statistics['最小值']))
out = out.replace('{temperature2}', '{:.2f}'.format(temperature_descriptive_statistics['最大值']))
out = out.replace('{temperature3}', '{:.2f}'.format(temperature_descriptive_statistics['平均值']))
out = out.replace('{temperature4}', '{:.2f}'.format(temperature_descriptive_statistics['标准差']))
out = out.replace('{humidity0}', str(len(humidity)))
out = out.replace('{humidity1}', '{:.2f}'.format(humidity_descriptive_statistics['最小值']))
out = out.replace('{humidity2}', '{:.2f}'.format(humidity_descriptive_statistics['最大值']))
out = out.replace('{humidity3}', '{:.2f}'.format(humidity_descriptive_statistics['平均值']))
out = out.replace('{humidity4}', '{:.2f}'.format(humidity_descriptive_statistics['标准差']))
out = out.replace('{light0}', str(len(light)))
out = out.replace('{light1}', '{:.2f}'.format(light_descriptive_statistics['最小值']))
out = out.replace('{light2}', '{:.2f}'.format(light_descriptive_statistics['最大值']))
out = out.replace('{light3}', '{:.2f}'.format(light_descriptive_statistics['平均值']))
out = out.replace('{light4}', '{:.2f}'.format(light_descriptive_statistics['标准差']))
out = out.replace('{height0}', str(len(light)))
out = out.replace('{height1}', '{:.2f}'.format(height_descriptive_statistics['最小值']))
out = out.replace('{height2}', '{:.2f}'.format(height_descriptive_statistics['最大值']))
out = out.replace('{height3}', '{:.2f}'.format(height_descriptive_statistics['平均值']))
out = out.replace('{height4}', '{:.2f}'.format(height_descriptive_statistics['标准差']))
out = out.replace('{valid_}', str(min(min(len(temperature), len(height)), min(len(humidity), len(light)))))

row = ''
for key in temperature_frequency.keys():
    row += '<tr><td>{}</td><td>{}</td><td>{}</td><td>{}</td><td>{}</td></tr>'.format(key,
                                                                                     '{:.2f}'.format(
                                                                                         temperature_frequency[key][
                                                                                             '频率']),
                                                                                     '{:.2f}'.format(
                                                                                         temperature_frequency[key][
                                                                                             '百分比']),
                                                                                     '{:.2f}'.format(
                                                                                         temperature_frequency[key][
                                                                                             '有效百分比']),
                                                                                     '{:.2f}'.format(
                                                                                         temperature_frequency[key][
                                                                                             '累积百分比']))
out = out.replace('{temperature_frequency}', row)
row = ''
for key in humidity_frequency.keys():
    row += '<tr><td>{}</td><td>{}</td><td>{}</td><td>{}</td><td>{}</td></tr>'.format(key, '{:.2f}'.format(
        humidity_frequency[key]['频率']),
                                                                                     '{:.2f}'.format(
                                                                                         humidity_frequency[key][
                                                                                             '百分比']),
                                                                                     '{:.2f}'.format(
                                                                                         humidity_frequency[key][
                                                                                             '有效百分比']),
                                                                                     '{:.2f}'.format(
                                                                                         humidity_frequency[key][
                                                                                             '累积百分比']))
out = out.replace('{humidity_frequency}', row)
row = ''
for key in light_frequency.keys():
    row += '<tr><td>{}</td><td>{}</td><td>{}</td><td>{}</td><td>{}</td></tr>'.format(key, '{:.2f}'.format(
        light_frequency[key]['频率']),
                                                                                     '{:.2f}'.format(
                                                                                         light_frequency[key]['百分比']),
                                                                                     '{:.2f}'.format(
                                                                                         light_frequency[key]['有效百分比']),
                                                                                     '{:.2f}'.format(
                                                                                         light_frequency[key]['累积百分比']))
out = out.replace('{light_frequency}', row)
row = ''
for key in height_frequency.keys():
    row += '<tr><td>{}</td><td>{}</td><td>{}</td><td>{}</td><td>{}</td></tr>'.format(key, '{:.2f}'.format(
        height_frequency[key]['频率']),
                                                                                     '{:.2f}'.format(
                                                                                         height_frequency[key]['百分比']),
                                                                                     '{:.2f}'.format(
                                                                                         height_frequency[key][
                                                                                             '有效百分比']),
                                                                                     '{:.2f}'.format(
                                                                                         height_frequency[key][
                                                                                             '累积百分比']))
out = out.replace('{height_frequency}', row)

length = min(min(len(temperature), len(height)), min(len(humidity), len(light)))
if length > 0:
    data = {'高度': height[:length], '温度': temperature[:length], '湿度': humidity[:length], '光照': light[:length]}
    data = pd.DataFrame(data)
    # print(data)

    x = sm.add_constant(data.iloc[:, 1:])  # 生成自变量
    y = data['高度']  # 生成因变量
    model = sm.OLS(y, x)  # 生成模型
    result = model.fit()  # 模型拟合
    result = str(result.summary()).split(
        '==============================================================================')
    result = result[2]
    result = result.replace('\n', '<br>')
    result = result.replace(' ', '&nbsp')
    out = out.replace('{regression}', result)
else:
    out = out.replace('{regression}', '不含高度数据')

# f = open(path + 'data_analysis.html', 'w', encoding="utf-8")
# f.write(out)
# f.close()
print(out.replace('\n', ''))
