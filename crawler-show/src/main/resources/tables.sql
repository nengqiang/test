create table t_csdn_extract_result
(
    id               bigint auto_increment comment '自增主键'
        primary key,
    task_id          bigint                                not null comment '此次爬取任务id',
    plat_form        varchar(32) default ''                null comment '平台',
    keyword          varchar(32)                           not null comment '爬取关键字',
    title            varchar(128)                          not null comment '爬取的问题',
    author           varchar(64)                           not null comment '回答这个问题的作者的昵称',
    readers          int         default 0                 null comment '阅读数',
    link             varchar(128)                          not null comment '作者观点链接',
    public_time      date                                  null comment '发布时间',
    overview         longtext                              not null comment '作者对问题的观点',
    create_date      timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    last_update_date timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment 'CSDN关键词观点抓取结果';

create index IDX_KEYWORD
    on t_csdn_extract_result (keyword);

create index IDX_TASKID
    on t_csdn_extract_result (task_id);


create table t_zhihu_extract_result
(
    id               bigint auto_increment comment '自增主键'
        primary key,
    task_id          bigint                              not null comment '此次爬取任务id',
    keyword          varchar(32)                         not null comment '爬取关键字',
    question         varchar(128)                        not null comment '爬取的问题',
    author           varchar(64)                         not null comment '回答这个问题的作者的昵称',
    agree            int                                 not null comment '作者观点的被赞同数',
    comments         int       default 0                 not null comment '作者观点的被评论数',
    link             varchar(128)                        not null comment '作者观点链接',
    public_time      date                                null comment '发布时间',
    edit_time        date                                null comment '编辑时间',
    overview         text                                not null comment '作者对问题的观点',
    create_date      timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    last_update_date timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间'
)
    comment '知乎观点抓取结果';

create index IDX_KEYWORD
    on t_zhihu_extract_result (keyword);

create index IDX_TASKID
    on t_zhihu_extract_result (task_id);

