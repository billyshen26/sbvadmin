--
-- 表的结构 `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`            bigint UNSIGNED NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `nickname`      varchar(32) NOT NULL COMMENT '姓名',
    `phone`         char(11)    NOT NULL DEFAULT '' COMMENT '手机号码',
    `email`         varchar(64) NOT NULL DEFAULT '' COMMENT '邮箱',
    `activated`     tinyint(1) DEFAULT '1' COMMENT '激活状态:1为启用，0位禁用',
    `locked`        tinyint(1) DEFAULT '0' COMMENT '是否被锁:1为已锁，0位未锁',
    `username`      varchar(255)     NOT NULL COMMENT '用户名',
    `password`      varchar(255)     NOT NULL COMMENT '密码',
    `avatar`        varchar(255)     NOT NULL DEFAULT '' COMMENT '头像',
    `last_login_at` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
    `last_login_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后登录ip',
    `mp_open_id`    varchar(64) NOT NULL DEFAULT '' COMMENT '微信open_id',
    `union_id`      varchar(64) NOT NULL DEFAULT '' COMMENT '微信union_id',
    `created_at`    datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updated_at`    datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 转存表中的数据 `user`
--
insert into `user` (`id`, `nickname`, `phone`, `email`, `activated`, `locked`, `username`, `password`, `avatar`, `last_login_at`,
                    `last_login_ip`, `mp_open_id`, `union_id`, `created_at`, `updated_at`)
values (1, '超级管理员', '13912341234', 'likeboat@163.com', 1, 0, 'root',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://q1.qlogo.cn/g?b=qq&nk=190848757&s=640', NULL, '', '', '', now(), now()),
       (2, '管理员', '13812341234', 'likeboat@126.com', 1, 0, 'admin',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://q1.qlogo.cn/g?b=qq&nk=190848757&s=640', NULL, '', '', '', now(), now()),
       (3, '普通用户', '13712341234', 'likeboat@sina.com', 1, 0, 'user',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://q1.qlogo.cn/g?b=qq&nk=190848757&s=640', NULL, '', '', '', now(), now());

--
-- 表的结构 `role`
--
DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
(
    `id`          bigint UNSIGNED NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `name`        varchar(64) NOT NULL COMMENT '英文名称',
    `name_zh`     varchar(64) NOT NULL DEFAULT '' COMMENT '中文名称',
    `description` text COLLATE utf8mb4_unicode_ci COMMENT '描述',
    `status`      tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-禁用;1-启用',
    `order_no`    tinyint NOT NULL DEFAULT 1 COMMENT '排序',
    `created_at`  datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updated_at`  datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`id`, `name`, `name_zh`, `description`, `status`,`order_no`,`created_at`, `updated_at`)
VALUES (1, 'ROLE_root', '超级管理员', '超级管理员',1,1, now(), now()),
       (2, 'ROLE_admin', '管理员', '普通管理员',1,1, now(), now()),
       (3, 'ROLE_user', '普通用户', '普通用户',1,1, now(), now());

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`  int NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `uid` bigint DEFAULT NULL COMMENT '用户外键',
    `rid` bigint DEFAULT NULL COMMENT '角色外键',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` (`id`, `uid`, `rid`)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3),
       (4, 2, 3);

--
-- 表的结构 `permission`
-- 该表同时承担菜单功能
--
DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission`
(
    `id`          bigint UNSIGNED NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `pid`         bigint NOT NULL DEFAULT '0',
    `request_url`     varchar(128) NOT NULL COMMENT '后端请求路径',
    `request_method`  varchar(10) NOT NULL COMMENT '后端请求方式',
    `name`        varchar(64) NOT NULL COMMENT '菜单名称',
    `title`       varchar(64) NOT NULL DEFAULT '' COMMENT '菜单名称i18n',
    `description` text COLLATE utf8mb4_unicode_ci COMMENT '描述',
    `path`        varchar(32) NOT NULL DEFAULT '' COMMENT '前端页面路径',
    `component`   varchar(128) NOT NULL DEFAULT '' COMMENT '前端页面组件',
    `icon`        varchar(32) NOT NULL DEFAULT '' COMMENT '图标',
    `show_flag`   tinyint NOT NULL DEFAULT 1 COMMENT '是否显示:0-隐藏;1-显示',
    `type`        tinyint NOT NULL DEFAULT 1 COMMENT '权限类型:0-目录;1-菜单;2-按钮',
    `status`      tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-禁用;1-启用',
    `order_no`    tinyint NOT NULL DEFAULT 1 COMMENT '排序',
    `created_at`  datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updated_at`  datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 转存表中的数据 `permission`
-- 这里的设计很关键，request_method即指http请求的方法，这样设计可以既实现标准的Restful api设计规范，又能做到权限控制到按钮级别
--

INSERT INTO `permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (1, 0, '/api/dashboard','GET', 'Dashboard', 'routes.dashboard.dashboard','Dashboard目录','/dashboard','LAYOUT', 'bx:bx-home','1','0','1','1',now(), now()),
       (2, 0, '/api/system','GET', '系统管理', 'routes.demo.system.moduleName','系统管理目录','/system','LAYOUT', 'ion:settings-outline','1','0','1','1',now(), now()),
       (3, 2, '/api/users','GET', '账号管理', 'routes.demo.system.account','账号管理菜单','account','/demo/system/account/index', '','1','1','1','1',now(), now()),
       (4, 3, '/api/users','POST', '新增账号', '','新增账号按钮','','', '','1','2','1','1',now(), now()),
       (5, 2, '/api/users','GET', '菜单管理', 'routes.demo.system.menu','菜单管理菜单','menu','/demo/system/menu/index', '','1','1','1','1',now(), now()),
       (6, 5, '/api/users','POST', '新增菜单', '','新增菜单按钮','','', '','1','2','1','1',now(), now()),
       (7, 2, '/api/users','GET', '角色管理', 'routes.demo.system.role','角色管理菜单','role','/demo/system/role/index', '','1','1','1','1',now(), now());


--
-- 表的结构 `role_permission`
--
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
    `id`  int NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `rid` bigint DEFAULT NULL COMMENT '角色外键',
    `pid` bigint DEFAULT NULL COMMENT '权限外键',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `role_permission` (`id`, `rid`, `pid`)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 1, 4),
       (5, 1, 5),
       (6, 1, 6),
       (7, 1, 7);
--
-- 表的结构 `log`
-- 记录操作日志和错误日志
--

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log`
(
    `id`            bigint UNSIGNED NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `uid`           bigint UNSIGNED NOT NULL COMMENT '操作用户id',
    `username`      varchar(255)  DEFAULT NULL COMMENT '用户名',
    `level`         tinyint DEFAULT '1' COMMENT '日志等级:1为行为日志,2为错误日志',
    `description`   varchar(255)  NULL DEFAULT NULL COMMENT '操作描述',
    `req_param`     text  NULL COMMENT '请求参数',
    `take_up_time`  int(64) NULL DEFAULT NULL COMMENT '耗时',
    `method`        varchar(255)  NULL DEFAULT NULL COMMENT '操作方法',
    `uri`           varchar(255)  NULL DEFAULT NULL COMMENT '请求url',
    `ip`            varchar(50)  NULL DEFAULT NULL COMMENT '请求IP',
    `version`       varchar(50)  NULL DEFAULT NULL COMMENT '版本号',
    `created_at`    datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updated_at`    datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='日志';