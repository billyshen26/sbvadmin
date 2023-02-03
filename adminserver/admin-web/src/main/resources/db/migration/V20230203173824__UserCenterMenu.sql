--
-- 新增菜单
--
INSERT INTO `permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (9, 2, '/api/getUserInfo','GET', '个人中心', 'layout.header.dropdownItemUserCenter','个人中心','/user-center','/sbvadmin/account/index', '','0','1','1','1',now(), now());
--
-- 默认将菜单分配给root
--
INSERT INTO `role_permission` (`rid`, `pid`)
VALUES (1, 9);
