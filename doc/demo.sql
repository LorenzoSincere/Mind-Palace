drop table if exists `demo`;
create table `demo` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine=Innodb default  charset=utf8mb4 comment='测试';

insert into `demo` (id, name) value (1, '测试');


drop table if exists `ebook`;
create table `ebook` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    `category1_id` bigint comment '分类1',
    `category2_id` bigint comment '分类2',
    `description` varchar(200) comment '描述',
    `cover` varchar(200) comment '封面',
    `doc_count` int comment '文档数',
    `view_count` int comment '阅读数',
    `vote_count` int comment '点赞数',
    primary key (`id`)
) engine=Innodb default  charset=utf8mb4 comment='电子书';

insert into `ebook` (id, name, description) values (1, 'Spring实战', '畅销书');
insert into `ebook` (id, name, description) values (2, 'Spring实战2', '畅销书');
insert into `ebook` (id, name, description) values (3, 'Spring实战3', '畅销书');
insert into `ebook` (id, name, description) values (4, 'Spring实战4', '畅销书');
insert into `ebook` (id, name, description) values (5, 'Spring实战5', '畅销书');
insert into `ebook` (id, name, description) values (6, 'Spring实战6', '畅销书');
insert into `ebook` (id, name, description) values (7, 'Spring实战7', '畅销书');
insert into `ebook` (id, name, description) values (8, 'Spring实战8', '畅销书');
insert into `ebook` (id, name, description) values (9, 'Spring实战9', '畅销书');
insert into `ebook` (id, name, description) values (10, 'Spring实战10', '畅销书');
insert into `ebook` (id, name, description) values (11, 'Spring实战11', '畅销书');
insert into `ebook` (id, name, description) values (12, 'Spring实战12', '畅销书');
