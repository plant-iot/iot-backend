package com.nju.iot;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * @author: xiang
 * TODO
 * @date: 2021/4/17
 * @description:
 */
public class MysqlConfig extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}