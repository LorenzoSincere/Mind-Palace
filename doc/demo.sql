drop table if exists `demo`;
create table `demo` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine=Innodb default  charset=utf8mb4 comment='测试';

insert into `demo` (id, name) value (1, '测试');
