drop table if exists `demo`;
create table `demo` (
    `id` bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine=Innodb default charset=utf8mb4 comment='测试';

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
) engine=Innodb default charset=utf8mb4 comment='电子书';

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


# 分类
drop table if exists `category`;
create table `category` (
    `id` bigint not null comment 'id',
    `parent` bigint not null default 0 comment '父id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '顺序',
    primary key (`id`)
) engine=Innodb default charset=utf8mb4 comment='分类';

insert into `category` (id, parent, name, sort) values (100, 000, '后端开发', 100);
insert into `category` (id, parent, name, sort) values (101, 100, 'Spring', 101);
insert into `category` (id, parent, name, sort) values (102, 100, 'Java', 102);
insert into `category` (id, parent, name, sort) values (200, 000, '前端开发', 200);
insert into `category` (id, parent, name, sort) values (201, 200, 'Vue', 201);
insert into `category` (id, parent, name, sort) values (202, 200, 'React', 202);
insert into `category` (id, parent, name, sort) values (203, 200, 'TypeScript', 203);
insert into `category` (id, parent, name, sort) values (103, 100, '后端开发', 103);

# 文档表
drop table if exists `doc`;
create table `doc` (
    `id` bigint not null comment 'id',
    `ebook_id` bigint not null default 0 comment '电子书id',
    `parent` bigint not null default 0 comment '父id',
    `name` varchar(50) not null comment '名称',
    `sort` int comment '顺序',
    `view_count` int default 0 comment '阅读数',
    `vote_count` int default 0 comment '点赞数',
    primary key (`id`)
) engine=Innodb default charset=utf8mb4 comment='文档';

insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (1, 1, 0, '文档1', 1, 0, 0);
insert into `doc` (id, ebook_id, parent, name, sort, view_count, vote_count) values (2, 2, 0, '文档2', 2, 0, 0);

# 文档内容
drop table if exists `content`;
create table `content` (
    `id` bigint not null comment '文档id',
    `content` mediumtext not null comment '内容',
    primary key (`id`)
) engine=Innodb default charset=utf8mb4 comment='文档内容';

# 用户表
drop table if exists `user`;
create table `user` (
    `id` bigint not null comment 'ID',
    `login_name` varchar(50) not null comment '登录名',
    `name` varchar(50) comment '昵称',
    `password` char(32) not null comment '密码',
    primary key (`id`),
    unique key `login_name_unique` (`login_name`)
) engine=Innodb default charset=utf8mb4 comment='用户';

# 电子书快照表
drop table if exists `ebook_snapshot`;
create table `ebook_snapshot` (
    `id` bigint auto_increment not null comment 'id',
    `ebook_id` bigint not null default 0 comment '电子书id',
    `date` date not null null comment '快照日期',
    `view_count` int not null default 0 comment '阅读数',
    `vote_count` int not null default 0 comment '点赞数',
    `view_increase` int not null default 0 comment '阅读增长',
    `vote_increase` int not null default 0 comment '点赞增长',
    primary key (`id`),
    unique key `ebook_id_date_unique` (`ebook_id`, `date`)
) engine=Innodb default charset=utf8mb4 comment='电子书快照表';

insert into ebook_snapshot(ebook_id, `date`, view_count, vote_count, view_increase, vote_increase)
    select t1.id, curdate(), 0, 0, 0, 0 from ebook t1 where not exists(select 1 from ebook_snapshot t2 where
    t1.id = t2.ebook_id and t2.`date` = curdate())
update ebook_snapshot t1, ebook t2
set t1.view_count = t2.view_count, t1.vote_count = t2.vote_count
where t1.`date` = curdate();
select t1.ebook_id, view_count, vote_count from ebook_snapshot t1 where t1.`date` = date_sub(curdate(), interval 1 day);

update ebook_snapshot t1, (select ebook_id, view_count, vote_count from ebook_snapshot
where `date` = date_sub(curdate(), interval 1 day)) t2
on t1.ebook_id = t2.ebook_id
set t1.view_increase = (t1.view_count - ifnull(t2.view_count, 0)),
    t1.vote_increase = (t1.vote_count - ifnull(t2.vote_count, 0))
where t1.`date` = curdate(),
