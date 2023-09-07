--
-- 手动更新所有菜单缓存的权限点
--
INSERT INTO `sys_permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (50, 10, '/api/permissions/refreshPermissions','POST', '手动更新菜单缓存', '','手动更新菜单缓存','','', '','1','2','1','1',now(), now()),
       (51, 10, '/api/permissions/refreshAllCache','POST', '手动更新所有缓存', '','手动更新所有缓存','','', '','1','2','1','1',now(), now());

--
-- 默认将菜单分配给root
--
INSERT INTO `sys_role_permission` (`rid`, `pid`)
VALUES (2, 50),
       (1, 50),
       (2, 51),
       (1, 51);