--
-- 新增菜单
--
INSERT INTO `sys_permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (36, 4, '/api/depts','ANY', '机构管理', 'routes.sbvadmin.Dept.dept','机构管理','/dept','/sbvadmin/dept/DeptIndex.vue', '','1','1','1','4',now(), now()),
       (37, 36, '/api/depts/getDeptsAsTree','GET', '机构列表', '','机构列表','','', '','1','2','1','1',now(), now()),
       (38, 36, '/api/depts','POST', '新增机构', '','新增机构','','', '','1','2','1','1',now(), now()),
       (39, 36, '/api/depts/**','PUT', '修改机构', '','修改机构','','', '','1','2','1','1',now(), now()),
       (40, 36, '/api/depts/**','DELETE', '删除机构', '','删除机构','','', '','1','2','1','1',now(), now());

--
-- 默认将菜单分配给root
--
INSERT INTO `sys_role_permission` (`rid`, `pid`)
VALUES (2, 36),
       (2, 37),
       (2, 38),
       (2, 39),
       (2, 40),
       (1, 36),
       (1, 37),
       (1, 38),
       (1, 39),
       (1, 40);
--
-- 补充获取详情的权限点
--
INSERT INTO `sys_permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (41, 5, '/api/users/**','GET', '用户详情', '','用户详情','','', '','1','2','1','1',now(), now()),
       (42, 10, '/api/permissions/**','GET', '菜单详情', '','菜单详情','','', '','1','2','1','1',now(), now()),
       (43, 15, '/api/roles/**','GET', '角色详情', '','角色详情','','', '','1','2','1','1',now(), now()),
       (44, 20, '/api/logs/**','GET', '日志详情', '','日志详情','','', '','1','2','1','1',now(), now()),
       (45, 26, '/api/configs/**','GET', '配置详情', '','配置详情','','', '','1','2','1','1',now(), now()),
       (46, 31, '/api/dicts/**','GET', '字典详情', '','字典详情','','', '','1','2','1','1',now(), now()),
       (47, 36, '/api/depts/**','GET', '机构详情', '','机构详情','','', '','1','2','1','1',now(), now()),
       (48, 25, '/api/upload','POST', '上传文件', '','上传文件','','', '','1','2','1','1',now(), now());

--
-- 默认将菜单分配给root
--
INSERT INTO `sys_role_permission` (`rid`, `pid`)
VALUES (2, 41),
       (2, 42),
       (2, 43),
       (2, 44),
       (2, 45),
       (2, 46),
       (2, 47),
       (2, 48),
       (1, 41),
       (1, 42),
       (1, 43),
       (1, 44),
       (1, 45),
       (1, 46),
       (1, 47),
       (1, 48);