INSERT INTO `iot_platform`.`user` (`id`, `password`) VALUES ('1', '123456');

INSERT INTO `iot_platform`.`thing_model` (`model_id`, `model_name`,`device_type`) VALUES ('1', '标准传感器模板' ,'SENSOR');
INSERT INTO `iot_platform`.`thing_model` (`model_id`, `model_name`,`device_type`) VALUES ('2', '标准执行器模板', 'EXECUTOR');

INSERT INTO `iot_platform`.`device` (`device_id`, `topic`, `type`, `user_id`, `register_time`, `state`, `is_online`, `thing_model`, `device_name`) VALUES ('1', 'test/1', 'SENSOR', '1', '2021-04-17 19:43:49', 'IN_USE', b'1', 1, 'device1');
INSERT INTO `iot_platform`.`device` (`device_id`, `topic`, `type`, `user_id`, `register_time`, `state`, `is_online`, `thing_model`, `device_name`) VALUES ('2', 'test/2', 'EXECUTOR', '1', '2021-04-17 19:43:49', 'IN_USE', b'1', 2, 'device2');

INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('1', 'ON', '2021-04-17 19:45:49', '1');
INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('2', 'OFF', '2021-04-17 19:43:49', '2');
INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('3', 'OFF', '2021-04-17 19:44:49', '1');
INSERT INTO `iot_platform`.`device_on_off_record` (`id`, `action`, `time`, `device_id`) VALUES ('4', 'ON', '2021-04-17 19:44:49', '2');

INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('WATER', '浇水', '10', '0', 'ml');
INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('SUNSHINE_UP', '调高光照', '10', '1', '%');
INSERT INTO `iot_platform`.`thing_model_service` (`service_name`, `description`, `quantity`, `type`, `unit`) VALUES ('SUNSHINE_DOWN', '调低光照', '10', '2', '%');

INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('1', '1', 'WATER');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('2', '1', 'SUNSHINE_UP');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('3', '1', 'SUNSHINE_DOWN');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('4', '2', 'WATER');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('5', '2', 'SUNSHINE_UP');
INSERT INTO `iot_platform`.`thing_model_record` (`record_id`, `thing_model`, `service_name`) VALUES ('6', '2', 'SUNSHINE_DOWN');

INSERT INTO `iot_platform`.`device_group` (`id`, `group_name`, `user_id`) VALUES ('1', 'group1', '1');
INSERT INTO `iot_platform`.`device_group_relation` (`id`, `device_id`, `group_id`) VALUES ('1', '1', '1');
INSERT INTO `iot_platform`.`device_group_relation` (`id`, `device_id`, `group_id`) VALUES ('2', '2', '1');

INSERT INTO `iot_platform`.`device_group` (`id`, `group_name`, `user_id`) VALUES ('2', 'group2', '1');
INSERT INTO `iot_platform`.`device_group_relation` (`id`, `device_id`, `group_id`) VALUES ('3', '1', '2');



