--
-- 增加数据权限 add_data_permission_feature
--
ALTER TABLE sys_config ADD COLUMN did bigint NOT NULL DEFAULT 1 COMMENT '机构外键' AFTER order_no;
ALTER TABLE sys_dict ADD COLUMN did bigint NOT NULL DEFAULT 1 COMMENT '机构外键' AFTER order_no;
ALTER TABLE sys_log ADD COLUMN did bigint NOT NULL DEFAULT 1 COMMENT '机构外键' AFTER version;
ALTER TABLE sys_role ADD COLUMN did bigint NOT NULL DEFAULT 1 COMMENT '机构外键' AFTER order_no;
DELETE FROM sys_role_permission WHERE rid = 2 and pid = 4;
DELETE FROM sys_role_permission WHERE rid = 2 and pid = 12;
DELETE FROM sys_role_permission WHERE rid = 2 and pid = 13;
DELETE FROM sys_role_permission WHERE rid = 2 and pid = 14;
DELETE FROM sys_role_permission WHERE rid = 2 and pid = 42;
DELETE FROM sys_role_permission WHERE rid = 2 and pid = 38;
DELETE FROM sys_role_permission WHERE rid = 2 and pid = 39;
DELETE FROM sys_role_permission WHERE rid = 2 and pid = 40;
DELETE FROM sys_role_permission WHERE rid = 2 and pid = 47;
UPDATE sys_user_dept SET `uid` = 2 ,`did` = 1 WHERE `id` = 2;
UPDATE sys_user_dept SET `uid` = 3 ,`did` = 1 WHERE `id` = 3;
DELETE FROM sys_user_dept WHERE id = 4;
ALTER TABLE sys_user_dept AUTO_INCREMENT=4;
UPDATE sys_dept SET `name` = '默认机构' WHERE `id` = 1;
DELETE FROM sys_dept WHERE id = 2;
DELETE FROM sys_dept WHERE id = 3;
DELETE FROM sys_dept WHERE id = 4;
DELETE FROM sys_dept WHERE id = 5;
DELETE FROM sys_dept WHERE id = 6;
ALTER TABLE sys_dept AUTO_INCREMENT=2;