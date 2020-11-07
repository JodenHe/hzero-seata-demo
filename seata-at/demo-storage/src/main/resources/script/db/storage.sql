create table `demo_storage` (
    `id` bigint not null auto_increment,
    `product_id` bigint comment '产品id',
    `total` int comment '总库存',
    `used` int comment '已用库存',
    `residue` int comment '剩余库存',
    `object_version_number` bigint(20) NOT NULL DEFAULT '1' COMMENT '行版本号，用来处理锁',
    `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` int(11) NOT NULL DEFAULT '-1',
    `last_updated_by` int(11) NOT NULL DEFAULT '-1',
    `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_update_login` bigint(20) DEFAULT NULL,
    primary key (`id`)
) engine=InnoDB auto_increment=2 default charset=utf8;

insert into `storage`(id, product_id, total, used, residue) VALUES (1, 1, 100, 0, 100);