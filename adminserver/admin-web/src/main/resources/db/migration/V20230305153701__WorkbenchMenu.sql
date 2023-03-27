--
-- 新增菜单
--
INSERT INTO `sys_permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (13, 1, '/api/dashboard/workbench','GET', '工作台', 'routes.dashboard.workbench','工作台','workbench','/sbvadmin/dashboard/workbench/index', '','1','1','1','1',now(), now()),
       (14, 1, '/api/dashboard/analysis','GET', '分析页', 'routes.dashboard.analysis','分析页','analysis','/sbvadmin/dashboard/analysis/index', '','1','1','1','1',now(), now());
--
-- 默认将菜单分配给root
--
INSERT INTO `sys_role_permission` (`rid`, `pid`)
VALUES (1, 13),
       (1, 14);
