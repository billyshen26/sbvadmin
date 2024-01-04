--
-- change_list_permission_to_menu
--
UPDATE sys_permission SET `title` = 'routes.demo.system.menu' ,`path` = 'menu' ,`component` = '/sbvadmin/menu/MenuIndex' ,`show_flag` = 0 ,`type` = 1 WHERE `id` = 11;
UPDATE sys_permission SET `title` = 'routes.demo.system.account' ,`path` = 'account' ,`component` = '/sbvadmin/user/UserIndex' ,`show_flag` = 0 ,`type` = 1 WHERE `id` = 6;
UPDATE sys_permission SET `title` = 'routes.demo.system.role' ,`path` = 'role' ,`component` = '/sbvadmin/role/RoleIndex' ,`show_flag` = 0 ,`type` = 1 WHERE `id` = 16;
UPDATE sys_permission SET `title` = 'routes.sbvadmin.Config.config' ,`path` = 'config' ,`component` = '/sbvadmin/config/ConfigIndex' ,`show_flag` = 0 ,`type` = 1 WHERE `id` = 27;
UPDATE sys_permission SET `path` = 'config' WHERE `id` = 26;
UPDATE sys_permission SET `title` = 'routes.sbvadmin.Dict.dict' ,`path` = 'dict' ,`component` = '/sbvadmin/dict/DictIndex' ,`show_flag` = 0 ,`type` = 1 WHERE `id` = 32;
UPDATE sys_permission SET `path` = 'dict' WHERE `id` = 31;
UPDATE sys_permission SET `title` = 'routes.sbvadmin.Dept.dept' ,`path` = 'dept' ,`component` = '/sbvadmin/dept/DeptIndex' ,`show_flag` = 0 ,`type` = 1 WHERE `id` = 37;
UPDATE sys_permission SET `path` = 'dept' WHERE `id` = 36;
UPDATE sys_permission SET `title` = 'routes.sbvadmin.Log.log' ,`path` = 'log' ,`component` = '/sbvadmin/log/LogIndex' ,`show_flag` = 0 ,`type` = 1 WHERE `id` = 21;
UPDATE sys_permission SET `path` = 'log' WHERE `id` = 20;