--
-- 新增菜单
--
INSERT INTO `sys_permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (10, 2, '/api/dicts','GET', '字典管理', 'routes.sbvadmin.Dict.dict','字典管理','/dict','/sbvadmin/dict/DictIndex.vue', '','1','1','1','1',now(), now());
--
-- 默认将菜单分配给root
--
INSERT INTO `sys_role_permission` (`rid`, `pid`)
VALUES (1, 10);
