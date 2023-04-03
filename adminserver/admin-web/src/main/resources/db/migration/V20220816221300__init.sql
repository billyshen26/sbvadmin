--
-- 表的结构 `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user`
(
    `id`            bigint UNSIGNED NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `nickname`      varchar(32) NOT NULL COMMENT '姓名',
    `phone`         char(11)    NOT NULL DEFAULT '' COMMENT '手机号码',
    `email`         varchar(64) NOT NULL DEFAULT '' COMMENT '邮箱',
    `activated`     tinyint(1) DEFAULT '1' COMMENT '激活状态:1为启用，0位禁用',
    `locked`        tinyint(1) DEFAULT '0' COMMENT '是否被锁:1为已锁，0位未锁',
    `username`      varchar(100) NOT NULL UNIQUE COMMENT '用户名',
    `password`      varchar(100) NOT NULL COMMENT '密码',
    `avatar`        varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
    `home_path`     varchar(100) NOT NULL DEFAULT '' COMMENT '入口页面',
    `last_login_at` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
    `last_login_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后登录ip',
    `mp_open_id`    varchar(64) NOT NULL DEFAULT '' COMMENT '微信open_id',
    `union_id`      varchar(64) NOT NULL DEFAULT '' COMMENT '微信union_id',
    `created_at`    datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updated_at`    datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='用户';

--
-- 转存表中的数据 `sys_user`
--
insert into `sys_user` (`id`, `nickname`, `phone`, `email`, `activated`, `locked`, `username`, `password`, `avatar`, `home_path`, `last_login_at`,
                    `last_login_ip`, `mp_open_id`, `union_id`, `created_at`, `updated_at`)
values (1, '超级管理员', '13912341234', 'likeboat@163.com', 1, 0, 'root',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://q1.qlogo.cn/g?b=qq&nk=190848757&s=640', '/dashboard/analysis',NULL, '', '', '', now(), now()),
       (2, '管理员', '13812341234', 'likeboat@126.com', 1, 0, 'admin',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://q1.qlogo.cn/g?b=qq&nk=190848757&s=640', '/dashboard/analysis',NULL, '', '', '', now(), now()),
       (3, '普通用户', '13712341234', 'likeboat@sina.com', 1, 0, 'user',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://q1.qlogo.cn/g?b=qq&nk=190848757&s=640', '/dashboard/analysis',NULL, '', '', '', now(), now());

--
-- 表的结构 `sys_role`
--
DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role`
(
    `id`          bigint UNSIGNED NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `name`        varchar(64) NOT NULL COMMENT '英文名称',
    `name_zh`     varchar(64) NOT NULL DEFAULT '' COMMENT '中文名称',
    `description` text COLLATE utf8mb4_unicode_ci COMMENT '描述',
    `status`      tinyint NOT NULL DEFAULT 1 COMMENT '状态:0-禁用;1-启用',
    `order_no`    decimal(10,2) NOT NULL DEFAULT 0.0 COMMENT '排序',
    `created_at`  datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updated_at`  datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='角色';
--
-- 转存表中的数据 `sys_role`
--

INSERT INTO `sys_role` (`id`, `name`, `name_zh`, `description`, `status`,`order_no`,`created_at`, `updated_at`)
VALUES (1, 'ROLE_root', '超级管理员', '超级管理员',1,1, now(), now()),
       (2, 'ROLE_admin', '管理员', '普通管理员',1,1, now(), now()),
       (3, 'ROLE_user', '普通用户', '普通用户',1,1, now(), now());

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`  int NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `uid` bigint DEFAULT NULL COMMENT '用户外键',
    `rid` bigint DEFAULT NULL COMMENT '角色外键',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='用户角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` (`id`, `uid`, `rid`)
VALUES (1, 1, 1),
       (2, 2, 2),
       (3, 3, 3),
       (4, 2, 3);

--
-- 表的结构 `sys_permission`
-- 该表同时承担菜单功能
--
DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission`
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
    `order_no`    decimal(10,2) NOT NULL DEFAULT 0.0 COMMENT '排序',
    `created_at`  datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updated_at`  datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='权限';
-- 预留1-999给sbvamin框架，方便框架升级

--
-- 转存表中的数据 `sys_permission`
-- 这里的设计很关键，request_method即指http请求的方法，这样设计可以既实现标准的Restful api设计规范，又能做到权限控制到按钮级别
--

INSERT INTO `sys_permission` (`id`, `pid`, `request_url`, `request_method`, `name`, `title`, `description`, `path`, `component`, `icon`, `show_flag`, `type`, `status`, `order_no`, `created_at`, `updated_at`)
VALUES (1, 0, '/api/dashboard','ANY', '首页', 'routes.dashboard.dashboard','首页目录','/dashboard','LAYOUT', 'bx:bx-home','1','0','1','1',now(), now()),
       (2, 1, '/api/dashboard/workbench','GET', '工作台', 'routes.dashboard.workbench','工作台','workbench','/sbvadmin/dashboard/workbench/index', '','1','1','1','1',now(), now()),
       (3, 1, '/api/dashboard/analysis','GET', '分析页', 'routes.dashboard.analysis','分析页','analysis','/sbvadmin/dashboard/analysis/index', '','1','1','1','1',now(), now()),
       (4, 0, '/api/system','ANY', '系统管理', 'routes.demo.system.moduleName','系统管理目录','/system','LAYOUT', 'ion:settings-outline','1','0','1','1',now(), now()),
       (5, 4, '/api/users','ANY', '账号管理', 'routes.demo.system.account','账号管理菜单','account','/sbvadmin/user/UserIndex', '','1','1','1','1',now(), now()),
       (6, 5, '/api/users','GET', '账号列表', '','账号列表','','', '','1','2','1','1',now(), now()),
       (7, 5, '/api/users','POST', '新增账号', '','新增账号','','', '','1','2','1','1',now(), now()),
       (8, 5, '/api/users/**','PUT', '修改账号', '','修改账号','','', '','1','2','1','1',now(), now()),
       (9, 5, '/api/users/**','DELETE', '删除账号', '','删除账号','','', '','1','2','1','1',now(), now()),
       (10, 4, '/api/permissions','ANY', '菜单管理', 'routes.demo.system.menu','菜单管理菜单','menu','/sbvadmin/menu/MenuIndex', '','1','1','1','1',now(), now()),
       (11, 10, '/api/permissions','GET', '菜单列表', '','菜单列表','','', '','1','2','1','1',now(), now()),
       (12, 10, '/api/permissions','POST', '新增菜单', '','新增菜单','','', '','1','2','1','1',now(), now()),
       (13, 10, '/api/permissions/**','PUT', '修改菜单', '','修改菜单','','', '','1','2','1','1',now(), now()),
       (14, 10, '/api/permissions/**','DELETE', '删除菜单', '','删除菜单','','', '','1','2','1','1',now(), now()),
       (15, 4, '/api/roles','ANY', '角色管理', 'routes.demo.system.role','角色管理菜单','role','/sbvadmin/role/RoleIndex', '','1','1','1','1',now(), now()),
       (16, 15, '/api/roles','GET', '角色列表', '','角色列表','','', '','1','2','1','1',now(), now()),
       (17, 15, '/api/roles','POST', '新增角色', '','新增角色','','', '','1','2','1','1',now(), now()),
       (18, 15, '/api/roles/**','PUT', '修改角色', '','修改角色','','', '','1','2','1','1',now(), now()),
       (19, 15, '/api/roles/**','DELETE', '删除角色', '','删除角色','','', '','1','2','1','1',now(), now());

--
-- 表的结构 `sys_role_permission`
--
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`
(
    `id`  int NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `rid` bigint DEFAULT NULL COMMENT '角色外键',
    `pid` bigint DEFAULT NULL COMMENT '权限外键',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='角色权限';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` (`rid`, `pid`)
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (2, 7),
       (2, 8),
       (2, 9),
       (2, 11),
       (2, 12),
       (2, 13),
       (2, 14),
       (2, 15),
       (2, 16),
       (2, 17),
       (2, 18),
       (2, 19),
       (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8),
       (1, 9),
       (1, 11),
       (1, 12),
       (1, 13),
       (1, 14),
       (1, 15),
       (1, 16),
       (1, 17),
       (1, 18),
       (1, 19);

--
-- 表的结构 `sys_log`
-- 记录操作日志和错误日志
--

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log`
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
    `address`       varchar(50)  NULL DEFAULT NULL COMMENT '请求地址',
    `version`       varchar(50)  NULL DEFAULT NULL COMMENT '版本号',
    `created_at`    datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updated_at`    datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='日志';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
    `id`          bigint UNSIGNED NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `symbol`      varchar(100) NOT NULL UNIQUE COMMENT '配置标识',
    `value`       text COLLATE utf8mb4_unicode_ci COMMENT  '配置值',
    `name`        varchar(100) NOT NULL COMMENT '配置名',
    `description` text COLLATE utf8mb4_unicode_ci COMMENT '描述',
    `order_no`    decimal(10,2) NOT NULL DEFAULT 0.0 COMMENT '排序',
    `created_at`  datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updated_at`  datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='配置';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` (`symbol`, `value`, `name`,`description`, `order_no`, `created_at`,`updated_at`)
VALUES ('common_title', 'SBVADMIN','平台名称','',0,now(),now());

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
    `id`            bigint UNSIGNED NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `pid`           bigint UNSIGNED NOT NULL COMMENT '父级ID',
    `type`          varchar(100) NOT NULL COMMENT '字典类型',
    `type_name`      varchar(100) NOT NULL COMMENT '字典类型名',
    `value`         text COLLATE utf8mb4_unicode_ci COMMENT  '字典值',
    `label`         varchar(100) NOT NULL COMMENT '字典名',
    `description`   text COLLATE utf8mb4_unicode_ci COMMENT '描述',
    `order_no`      decimal(10,2) NOT NULL DEFAULT 0.0 COMMENT '排序',
    `created_at`    datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updated_at`    datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` (`pid`, `type`, `type_name`, `value`, `label`,`description`, `order_no`, `created_at`,`updated_at`)
VALUES (0, 'sex','性别','1', '男','性别描述',0,now(),now()),
       (0, 'sex','性别','2', '女','性别描述',0,now(),now()),
       (0, 'education','学历','1', '大专','学历描述',0,now(),now()),
       (0, 'education','学历','2', '本科','学历描述',0,now(),now()),
       (0, 'education','学历','3', '硕士','学历描述',0,now(),now()),
       (0, 'education','学历','4', '博士','学历描述',0,now(),now());

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
    `id`            bigint UNSIGNED NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `pid`           bigint UNSIGNED NOT NULL COMMENT '上级机构ID，一级机构为0',
    `name`          varchar(50) DEFAULT NULL COMMENT '机构名称',
    `order_no`      decimal(10,2) NOT NULL DEFAULT 0.0 COMMENT '排序',
    `created_at`    datetime NULL DEFAULT NULL COMMENT '创建时间',
    `updated_at`    datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='机构';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_dept` (`id`, `pid`, `name`, `order_no`, `created_at`, `updated_at`)
VALUES (1, 0, '研发中心', 0, now(), now()),
       (2, 1, '软件部', 0, now(), now()),
       (3, 1, '硬件部', 0, now(), now()),
       (4, 0, '销售中心', 0, now(), now()),
       (5, 4, '华东大区', 0, now(), now()),
       (6, 4, '华南大区', 0, now(), now());


-- ----------------------------
-- Table structure for sys_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_dept`;
CREATE TABLE `sys_user_dept`
(
    `id`  int NOT NULL COMMENT 'ID' AUTO_INCREMENT,
    `uid` bigint DEFAULT NULL COMMENT '用户外键',
    `did` bigint DEFAULT NULL COMMENT '机构外键',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci comment='用户机构';

-- ----------------------------
-- Records of sys_user_dept
-- ----------------------------
INSERT INTO `sys_user_dept` (`id`, `uid`, `did`)
VALUES (1, 1, 1),
       (2, 1, 4),
       (3, 2, 2),
       (4, 3, 3);