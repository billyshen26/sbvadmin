--
-- 新增菜单
--
INSERT INTO `sys_permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (26, 4, '/api/configs','ANY', '配置管理', 'routes.sbvadmin.Config.config','配置管理','/config','/sbvadmin/config/ConfigIndex.vue', '','1','1','1','1',now(), now()),
       (27, 26, '/api/configs','GET', '配置列表', '','配置列表','','', '','1','2','1','1',now(), now()),
       (28, 26, '/api/configs','POST', '新增配置', '','新增配置','','', '','1','2','1','1',now(), now()),
       (29, 26, '/api/configs/**','PUT', '修改配置', '','修改配置','','', '','1','2','1','1',now(), now()),
       (30, 26, '/api/configs/**','DELETE', '删除配置', '','删除配置','','', '','1','2','1','1',now(), now());

--
-- 默认将菜单分配给root
--
INSERT INTO `sys_role_permission` (`rid`, `pid`)
VALUES (2, 26),
       (2, 27),
       (2, 28),
       (2, 29),
       (2, 30),
       (1, 26),
       (1, 27),
       (1, 28),
       (1, 29),
       (1, 30);