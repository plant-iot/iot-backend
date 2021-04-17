INSERT INTO `iot_platform`.`user` (`id`, `password`) VALUES ('1', '123456');

INSERT INTO `iot_platform`.`device` (`device_id`, `topic`, `type`, `user_id`, `register_time`, `state`, `is_online`) VALUES ('1', 'test/1', 'SENSOR', '1', '2021-04-17 19:43:49', 'IN_USE', b'0');
INSERT INTO `iot_platform`.`device` (`device_id`, `topic`, `type`, `user_id`, `register_time`, `state`, `is_online`) VALUES ('2', 'test/2', 'EXECUTOR', '1', '2021-04-17 19:43:49', 'IN_USE', b'0');
