INSERT INTO `iot_platform`.`user` (`id`, `password`) VALUES ('1', '123456');

INSERT INTO `iot_platform`.`thing_model` (`model_id`, `model_name`,`device_type`) VALUES ('1', '标准传感器模板' ,'SENSOR');
INSERT INTO `iot_platform`.`thing_model` (`model_id`, `model_name`,`device_type`) VALUES ('2', '标准执行器模板', 'EXECUTOR');

INSERT INTO `iot_platform`.`device` (`device_id`, `topic`, `type`, `user_id`, `register_time`, `state`, `is_online`, `thing_model`) VALUES ('1', 'test/1', 'SENSOR', '1', '2021-04-17 19:43:49', 'IN_USE', b'1', 1);
INSERT INTO `iot_platform`.`device` (`device_id`, `topic`, `type`, `user_id`, `register_time`, `state`, `is_online`, `thing_model`) VALUES ('2', 'test/2', 'EXECUTOR', '1', '2021-04-17 19:43:49', 'IN_USE', b'1', 2);

INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('water', '浇水', '10', '0', 'ml');
INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('sunshine_up', 'sunshine_up', '10', '1', '%');
INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('sunshine_down', 'sunshine_down', '10', '2', '%');

INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('1', '1', 'water');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('2', '1', 'sunshine_up');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('3', '1', 'sunshine_down');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('4', '2', 'water');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('5', '2', 'sunshine_up');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('6', '2', 'sunshine_down');




