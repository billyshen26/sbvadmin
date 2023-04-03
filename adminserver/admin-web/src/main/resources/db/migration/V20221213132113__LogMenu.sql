--
-- 新增菜单
--
INSERT INTO `sys_permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (20, 4, '/api/logs','ANY', '日志管理', 'routes.sbvadmin.Log.log','日志管理','/log','/sbvadmin/log/LogIndex.vue', '','1','1','1','7',now(), now()),
       (21, 20, '/api/logs','GET', '日志列表', '','日志列表','','', '','1','2','1','1',now(), now()),
       (22, 20, '/api/logs','POST', '新增日志', '','新增日志','','', '','1','2','1','1',now(), now()),
       (23, 20, '/api/logs/**','PUT', '修改日志', '','修改日志','','', '','1','2','1','1',now(), now()),
       (24, 20, '/api/logs/**','DELETE', '删除日志', '','删除日志','','', '','1','2','1','1',now(), now());


--
-- 默认将菜单分配给root
--
INSERT INTO `sys_role_permission` (`rid`, `pid`)
VALUES (2, 20),
       (2, 21),
       (2, 22),
       (2, 23),
       (2, 24),
       (1, 20),
       (1, 21),
       (1, 22),
       (1, 23),
       (1, 24);

