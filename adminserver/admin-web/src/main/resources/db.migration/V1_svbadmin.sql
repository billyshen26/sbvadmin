--
-- 表的结构 `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`            bigint UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`          varchar(32)          DEFAULT NULL COMMENT '姓名',
    `phone`         char(11)             DEFAULT NULL COMMENT '手机号码',
    `email`         varchar(64)          DEFAULT NULL COMMENT '邮箱',
    `enabled`       tinyint(1) DEFAULT '1' COMMENT '激活状态:1为启用，0位禁用',
    `locked`        tinyint(1) DEFAULT '0' COMMENT '是否被锁:1为已锁，0位未锁',
    `username`      varchar(255)         DEFAULT NULL COMMENT '用户名',
    `password`      varchar(255)         DEFAULT NULL COMMENT '密码',
    `avatar`        varchar(255)         DEFAULT NULL COMMENT '头像',
    `last_login_at` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
    `last_login_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后登录ip',
    `mp_open_id`    varchar(64) NOT NULL DEFAULT '' COMMENT '微信open_id',
    `union_id`      varchar(64) NOT NULL DEFAULT '' COMMENT '微信union_id',
    `created_at`    datetime NULL DEFAULT NULL,
    `updated_at`    datetime NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 转存表中的数据 `user`
--
insert into `user` (`id`, `name`, `phone`, `email`, `enabled`, `locked`, `username`, `password`, `avatar`, `last_login_at`,
                    `last_login_ip`, `mp_open_id`, `union_id`, `created_at`, `updated_at`)
values (1, '超级管理员', '13912341234', 'likeboat@163.com', 1, 0, 'root',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://img-home.csdnimg.cn/images/20201124032511.png', NULL, '', '', '', now(), now()),
       (2, '管理员', '13812341234', 'likeboat@126.com', 1, 0, 'admin',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://img-home.csdnimg.cn/images/20201124032511.png', NULL, '', '', '', now(), now()),
       (3, '普通用户', '13712341234', 'likeboat@sina.com', 1, 0, 'user',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://img-home.csdnimg.cn/images/20201124032511.png', NULL, '', '', '', now(), now());

--
-- 表的结构 `role`
--
DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
(
    `id`          bigint UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`        varchar(64) NOT NULL COMMENT '英文名称',
    `alias`       varchar(64) NOT NULL DEFAULT '' COMMENT '中文名称',
    `description` text COLLATE utf8mb4_unicode_ci COMMENT '描述',
    `created_at`  datetime NULL DEFAULT NULL,
    `updated_at`  datetime NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`id`, `name`, `alias`, `description`, `created_at`, `updated_at`)
VALUES (1, 'ROLE_root', '超级管理员', '超级管理员', now(), now()),
       (2, 'ROLE_admin', '管理员', '普通管理员', now(), now()),
       (3, 'ROLE_user', '普通用户', '普通用户', now(), now());

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`  int NOT NULL AUTO_INCREMENT,
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
--
DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission`
(
    `id`          bigint UNSIGNED NOT NULL AUTO_INCREMENT,
    `pid`         bigint NOT NULL DEFAULT '0',
    `pattern`     varchar(128) NOT NULL COMMENT 'url路径',
    `method`      varchar(10) NOT NULL COMMENT '请求方式',
    `alias`       varchar(64) NOT NULL DEFAULT '' COMMENT '中文名称',
    `description` text COLLATE utf8mb4_unicode_ci COMMENT '描述',
    `created_at`  datetime NULL DEFAULT NULL,
    `updated_at`  datetime NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- 转存表中的数据 `permission`
-- 这里的设计很关键，method即指http请求的方法，这样设计可以既实现标准的Restful api设计规范，又能做到权限控制到按钮级别
--

INSERT INTO `permission` (`id`, `pid`, `pattern`, `method`, `alias`, `description`, `created_at`, `updated_at`)
VALUES (1, 0, '/**','ANY', '所有', '所有用户管理权限', now(), now()),
       (2, 1, '/users/**','ANY', '用户管理', '所有用户管理权限', now(), now()),
       (3, 2, '/users','GET', '用户列表', '获得用户列表', now(), now()),
       (4, 2, '/users','POST', '新增用户', '新增一个用户', now(), now()),
       (5, 2, '/users','PUT', '修改用户', '修改一个用户', now(), now()),
       (6, 2, '/users','DELETE', '删除用户', '删除一个用户', now(), now());

--
-- 表的结构 `role_permission`
--
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
    `id`  int NOT NULL AUTO_INCREMENT,
    `rid` bigint DEFAULT NULL COMMENT '角色外键',
    `pid` bigint DEFAULT NULL COMMENT '权限外键',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `role_permission` (`id`, `rid`, `pid`)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3);

--
-- 表的结构 `log`
-- 记录操作日志和错误日志
--

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log`
(
    `id`            bigint UNSIGNED NOT NULL AUTO_INCREMENT,
    `uid`           bigint UNSIGNED NOT NULL COMMENT '操作用户id',
    `username`      varchar(255)  DEFAULT NULL COMMENT '用户名',
    `level`         tinyint(1) DEFAULT '1' COMMENT '日志等级:1为行为日志,2为错误日志',
    `description`          varchar(255)  NULL DEFAULT NULL COMMENT '操作描述',
    `req_param`     text  NULL COMMENT '请求参数',
    `take_up_time`  int(64) NULL DEFAULT NULL COMMENT '耗时',
    `method`        varchar(255)  NULL DEFAULT NULL COMMENT '操作方法',
    `uri`           varchar(255)  NULL DEFAULT NULL COMMENT '请求url',
    `ip`            varchar(50)  NULL DEFAULT NULL COMMENT '请求IP',
    `version`       varchar(50)  NULL DEFAULT NULL COMMENT '版本号',
    `created_at`    datetime NULL DEFAULT NULL,
    `updated_at`    datetime NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;