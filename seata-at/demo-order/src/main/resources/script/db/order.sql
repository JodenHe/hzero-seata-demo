create table `demo_order` (
    `id` bigint not null auto_increment,
    `user_id` bigint comment '用户id',
    `product_id` bigint comment '产品id',
    `count` int comment '数量',
    `money` decimal(11, 2) comment '金额',
    `status` int(1) default null comment '订单状态: 0: 创建中；1: 已完结',
    `object_version_number` bigint(20) NOT NULL DEFAULT '1' COMMENT '行版本号，用来处理锁',
    `creation_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` int(11) NOT NULL DEFAULT '-1',
    `last_updated_by` int(11) NOT NULL DEFAULT '-1',
    `last_update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_update_login` bigint(20) DEFAULT NULL,
    primary key (`id`)
) engine=InnoDB default charset=utf8;

