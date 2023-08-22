--
-- 增加微信号QQ号信息
--
ALTER TABLE sys_user ADD COLUMN wechat varchar(64) NOT NULL DEFAULT '' COMMENT '微信号' AFTER nickname;
ALTER TABLE sys_user ADD COLUMN qq varchar(20) NOT NULL DEFAULT '' COMMENT 'QQ号' AFTER nickname;