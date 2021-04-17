INSERT INTO `iot_platform`.`user` (`id`, `password`) VALUES ('1', '123456');

INSERT INTO `iot_platform`.`device` (`device_id`, `topic`, `type`, `user_id`, `register_time`, `state`, `is_online`) VALUES ('1', 'test/1', 'SENSOR', '1', '2021-04-17 19:43:49', 'IN_USE', b'1');
INSERT INTO `iot_platform`.`device` (`device_id`, `topic`, `type`, `user_id`, `register_time`, `state`, `is_online`) VALUES ('2', 'test/2', 'EXECUTOR', '1', '2021-04-17 19:43:49', 'IN_USE', b'1');

INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('water', '浇水', '10', '0', 'ml');
INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('sunshine_up', 'sunshine_up', '10', '1', '%');
INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('sunshine_down', 'sunshine_down', '10', '2', '%');



