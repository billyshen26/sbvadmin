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

