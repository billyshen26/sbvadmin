INSERT INTO `permission` (`pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (2, '/api/logs','GET', '日志管理', 'routes.Log.log','日志管理','/log','/sbvadmin/log/LogIndex.vue', '','1','1','1','1',now(), now());
