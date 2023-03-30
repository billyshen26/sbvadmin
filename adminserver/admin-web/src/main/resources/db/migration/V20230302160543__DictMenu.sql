--
-- 新增菜单
--
INSERT INTO `sys_permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (31, 4, '/api/dicts','ANY', '字典管理', 'routes.sbvadmin.Dict.dict','字典管理','/dict','/sbvadmin/dict/DictIndex.vue', '','1','1','1','1',now(), now()),
       (32, 31, '/api/dicts','GET', '字典列表', '','字典列表','','', '','1','2','1','1',now(), now()),
       (33, 31, '/api/dicts','POST', '新增字典', '','新增字典','','', '','1','2','1','1',now(), now()),
       (34, 31, '/api/dicts/**','PUT', '修改字典', '','修改字典','','', '','1','2','1','1',now(), now()),
       (35, 31, '/api/dicts/**','DELETE', '删除字典', '','删除字典','','', '','1','2','1','1',now(), now());

--
-- 默认将菜单分配给root
--
INSERT INTO `sys_role_permission` (`rid`, `pid`)
VALUES (2, 31),
       (2, 32),
       (2, 33),
       (2, 34),
       (2, 35),
       (1, 31),
       (1, 32),
       (1, 33),
       (1, 34),
       (1, 35);
