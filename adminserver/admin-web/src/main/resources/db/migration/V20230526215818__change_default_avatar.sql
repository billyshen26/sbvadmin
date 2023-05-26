--
--  change_default_avatar
--
UPDATE sys_permission SET `order_no` = 10000 WHERE `id` = 4;
UPDATE sys_user SET `avatar` = 'avatar.png',`password` = '$2a$10$i/YP9e6IOPUymeu2LEIgSOxXU.IkpuTV5ZXe9DjfR1dlEID0dF32.' WHERE `id` = 1;
UPDATE sys_user SET `avatar` = 'avatar.png' WHERE `id` = 2;
UPDATE sys_user SET `avatar` = 'avatar.png' WHERE `id` = 3;
INSERT INTO `sys_config` (`symbol`, `value`, `name`,`description`, `order_no`, `created_at`,`updated_at`)
VALUES ('host_ip', '127.0.0.1','平台IP','',0,now(),now());