--
-- Structure for add_oa_open_id_in_user
--
ALTER TABLE sys_user ADD COLUMN oa_open_id varchar(64) NOT NULL DEFAULT '' COMMENT '公众号open_id' AFTER mp_open_id;