create table `demo_account` (
    `id` bigint not null auto_increment,
    `user_id` bigint comment '用户id',
    `total` decimal(10, 0) comment '总额度',
    `used` decimal(10, 0) comment '已用额度',
    `residue` decimal(10, 0) default 0 comment '剩余可用额度',
    `object_version_number` bigint(20) NOT NULL DEFAULT '1' COMMENT '行版本号，用来处理锁',
    `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` int(11) NOT NULL DEFAULT '-1',
    `last_updated_by` int(11) NOT NULL DEFAULT '-1',
    `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_update_login` bigint(20) DEFAULT NULL,
    primary key (`id`)
) engine=InnoDB auto_increment=2 DEFAULT CHARSET=utf8;

insert into `demo_account` (id, user_id, total, used, residue) VALUES (1, 1, 1000, 0, 1000);