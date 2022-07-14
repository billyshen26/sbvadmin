/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
    `name`      varchar(32)  DEFAULT NULL COMMENT '姓名',
    `phone`     char(11)     DEFAULT NULL COMMENT '手机号码',
    `email`     varchar(64)  DEFAULT NULL COMMENT '邮箱',
    `enabled`   tinyint(1)   DEFAULT '1' COMMENT '激活状态:1为启用，0位禁用',
    `username`  varchar(255) DEFAULT NULL COMMENT '用户名',
    `password`  varchar(255) DEFAULT NULL COMMENT '密码',
    `avatar`    varchar(255) DEFAULT NULL COMMENT '头像',
    `last_login_at` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
    `last_login_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后登录ip',
    `created_at` timestamp NULL DEFAULT NULL,
    `updated_at` timestamp NULL DEFAULT NULL,
    `mp_open_id` varchar(64) NOT NULL DEFAULT '' COMMENT '微信open_id',
    `union_id` varchar(64) NOT NULL DEFAULT '' COMMENT '微信union_id',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `user` */
insert into `user` (`id`, `name`, `phone`, `email`, `enabled`, `username`, `password`, `avatar`, `last_login_at`,
                   `last_login_ip`, `created_at`, `updated_at`, `mp_open_id`, `union_id`)
values (1, '超级管理员', '13912341234', 'likeboat@163.com',  1, 'root',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://img-home.csdnimg.cn/images/20201124032511.png', NULL,'',NULL,NULL,'',''),
       (2, '管理员', '13812341234', 'likeboat@126.com',  1, 'admin',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://img-home.csdnimg.cn/images/20201124032511.png', NULL,'',NULL,NULL,'',''),
       (3, '普通用户', '13712341234', 'likeboat@sina.com',  1, 'user',
        '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm',
        'https://img-home.csdnimg.cn/images/20201124032511.png', NULL,'',NULL,NULL,'','');