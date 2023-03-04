--
-- 新增菜单
--
INSERT INTO `sys_permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (12, 2, '/api/depts','GET', '机构管理', 'routes.Dept.dept','机构管理','/dept','/sbvadmin/dept/DeptIndex.vue', '','1','1','1','1',now(), now());
--
-- 默认将菜单分配给root
--
INSERT INTO `sys_role_permission` (`rid`, `pid`)
VALUES (1, 12);
