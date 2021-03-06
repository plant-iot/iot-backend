INSERT INTO `iot_platform`.`user` (`id`, `password`) VALUES ('1', '123456');
INSERT INTO `iot_platform`.`user` (`id`, `password`) VALUES ('2', '123456');

INSERT INTO `iot_platform`.`thing_model` (`model_id`, `model_name`,`device_type`) VALUES ('1', '标准传感器模板' ,'SENSOR');
INSERT INTO `iot_platform`.`thing_model` (`model_id`, `model_name`,`device_type`) VALUES ('2', '标准执行器模板', 'EXECUTOR');
INSERT INTO `iot_platform`.`thing_model` (`model_id`, `model_name`,`device_type`) VALUES ('3', '光照调节执行器模板', 'EXECUTOR');

INSERT INTO `iot_platform`.`device` (`device_id`, `topic`, `type`, `user_id`, `register_time`, `state`, `is_online`, `thing_model`, `device_name`) VALUES ('1', 'test/1', 'SENSOR', '1', '2021-04-17 19:43:49', 'IN_USE', b'1', 1, '植物传感器');
INSERT INTO `iot_platform`.`device` (`device_id`, `topic`, `type`, `user_id`, `register_time`, `state`, `is_online`, `thing_model`, `device_name`) VALUES ('2', 'test/2', 'EXECUTOR', '1', '2021-04-17 19:43:49', 'IN_USE', b'1', 2, '植物照料机器人');

INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('1', 'ON', '2021-04-17 19:45:49', '1');
INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('2', 'OFF', '2021-04-17 19:43:49', '2');
INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('3', 'OFF', '2021-04-17 19:44:49', '1');
INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('4', 'ON', '2021-04-17 19:44:49', '2');
INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('5', 'ON', '2021-04-17 20:45:49', '1');
INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('6', 'OFF', '2021-04-17 20:43:49', '2');
INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('7', 'OFF', '2021-04-17 20:44:49', '1');
INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('8', 'ON', '2021-04-17 20:44:49', '2');

INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('WATER', '浇水', '10', '0', 'ml');
INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('SUNSHINE_UP', '调高光照', '10', '1', '%');
INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('SUNSHINE_DOWN', '调低光照', '10', '2', '%');
INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('APPLY_FERTILIZER', '施肥', '10', '3', 'g');

INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('1', '1', 'WATER');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('2', '1', 'SUNSHINE_UP');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('3', '1', 'SUNSHINE_DOWN');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('4', '1', 'APPLY_FERTILIZER');

INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('5', '2', 'WATER');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('6', '2', 'SUNSHINE_UP');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('7', '2', 'SUNSHINE_DOWN');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('8', '2', 'APPLY_FERTILIZER');

INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('9', '3', 'SUNSHINE_UP');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('10', '3', 'SUNSHINE_DOWN');

INSERT INTO `iot_platform`.`user_thing_model_relation` (`id`, `thing_model`, `user_id`) VALUES ('1', '3', '1');

INSERT INTO `iot_platform`.`device_group` (`id`, `group_name`, `user_id`) VALUES ('1', 'group1', '1');
INSERT INTO `iot_platform`.`device_group_relation` (`id`, `device_id`, `group_id`) VALUES ('1', '1', '1');
INSERT INTO `iot_platform`.`device_group_relation` (`id`, `device_id`, `group_id`) VALUES ('2', '2', '1');

INSERT INTO `iot_platform`.`device_group` (`id`, `group_name`, `user_id`) VALUES ('2', 'group2', '1');
INSERT INTO `iot_platform`.`device_group_relation` (`id`, `device_id`, `group_id`) VALUES ('3', '1', '2');

INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('1', '2021-04-17 09:55:49', 'TEMPERATURE', '20', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('2', '2021-04-17 09:55:49', 'HUMIDITY', '50', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('3', '2021-04-17 09:55:49', 'LIGHT_INTENSITY', '50', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('4', '2021-04-17 09:55:49', 'CO2', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('5', '2021-04-17 09:55:49', 'N_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('6', '2021-04-17 09:55:49', 'P_CONTENT', '4', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('7', '2021-04-17 09:55:49', 'K_CONTENT', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('8', '2021-04-17 09:55:49', 'MG_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('9', '2021-04-17 10:55:49', 'TEMPERATURE', '23', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('10', '2021-04-17 10:55:49', 'HUMIDITY', '50', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('11', '2021-04-17 10:55:49', 'LIGHT_INTENSITY', '45', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('12', '2021-04-17 10:55:49', 'CO2', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('13', '2021-04-17 10:55:49', 'N_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('14', '2021-04-17 10:55:49', 'P_CONTENT', '6', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('15', '2021-04-17 10:55:49', 'K_CONTENT', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('16', '2021-04-17 10:55:49', 'MG_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('17', '2021-04-17 11:55:49', 'TEMPERATURE', '19', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('18', '2021-04-17 11:55:49', 'HUMIDITY', '50', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('19', '2021-04-17 11:55:49', 'LIGHT_INTENSITY', '45', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('20', '2021-04-17 11:55:49', 'CO2', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('21', '2021-04-17 11:55:49', 'N_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('22', '2021-04-17 11:55:49', 'P_CONTENT', '4', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('23', '2021-04-17 11:55:49', 'K_CONTENT', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('24', '2021-04-17 11:55:49', 'MG_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('25', '2021-04-17 12:55:49', 'TEMPERATURE', '19', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('26', '2021-04-17 12:55:49', 'HUMIDITY', '50', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('27', '2021-04-17 12:55:49', 'LIGHT_INTENSITY', '52', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('28', '2021-04-17 12:55:49', 'CO2', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('29', '2021-04-17 12:55:49', 'N_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('30', '2021-04-17 12:55:49', 'P_CONTENT', '4', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('31', '2021-04-17 12:55:49', 'K_CONTENT', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('32', '2021-04-17 12:55:49', 'MG_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('33', '2021-04-17 13:55:49', 'TEMPERATURE', '20', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('34', '2021-04-17 13:55:49', 'HUMIDITY', '45', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('35', '2021-04-17 13:55:49', 'LIGHT_INTENSITY', '45', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('36', '2021-04-17 13:55:49', 'CO2', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('37', '2021-04-17 13:55:49', 'N_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('38', '2021-04-17 13:55:49', 'P_CONTENT', '4', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('39', '2021-04-17 13:55:49', 'K_CONTENT', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('40', '2021-04-17 13:55:49', 'MG_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('41', '2021-04-17 14:55:49', 'TEMPERATURE', '20', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('42', '2021-04-17 14:55:49', 'HUMIDITY', '45', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('43', '2021-04-17 14:55:49', 'LIGHT_INTENSITY', '45', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('44', '2021-04-17 14:55:49', 'CO2', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('45', '2021-04-17 14:55:49', 'N_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('46', '2021-04-17 14:55:49', 'P_CONTENT', '4', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('47', '2021-04-17 14:55:49', 'K_CONTENT', '2', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('48', '2021-04-17 14:55:49', 'MG_CONTENT', '5', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('49', '2021-04-17 14:55:50','HEIGHT', '24046', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('50', '2021-04-17 14:55:50','HEIGHT', '24047', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('51', '2021-04-17 14:55:50','HEIGHT', '24048', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('52', '2021-04-17 14:55:50','HEIGHT', '24049', '1');
INSERT INTO `iot_platform`.`data` (`id`, `time`, `type`, `value`, `device_id`) VALUES ('53', '2021-04-17 14:55:50','HEIGHT', '24050', '1');


INSERT INTO `iot_platform`.`rule` (`id`, `state`, `threshold_data`, `type`, `user_id`) VALUES ('1', 'ENABLED', '30', 'TEMPERATURE_RULE', '1');
INSERT INTO `iot_platform`.`rule` (`id`, `state`, `threshold_data`, `type`, `user_id`) VALUES ('2', 'ENABLED', '40', 'HUMIDITY_RULE', '1');
