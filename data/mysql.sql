CREATE DATABASE ems;

USE ems;

CREATE TABLE `user`
(
    `id`          VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `name`        VARCHAR(32) UNIQUE NOT NULL COMMENT '用户名',
    `password`    VARCHAR(64)        NOT NULL COMMENT '密码',
    `nick_name`   varchar(32) COMMENT '昵称',
    `avatar`      VARCHAR(64)        NOT NULL DEFAULT '0' COMMENT '头像',
    `sex`         INT                NOT NULL DEFAULT 1 COMMENT '性别(0女,1男)',
    `email`       VARCHAR(16) COMMENT '邮箱',
    `phone`       VARCHAR(11) COMMENT '电话',
    `role`        VARCHAR(48)        NOT NULL DEFAULT 0 COMMENT '用户角色',
    `status`      INT                         DEFAULT 0 NOT NULL COMMENT '状态(0正常,1失效)',
    `create_user` VARCHAR(48)        NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME           NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '用户表';

CREATE TABLE `role`
(
    `id`          VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `name`        VARCHAR(32) NOT NULL COMMENT '角色名',
    `create_user` VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '角色表';

CREATE TABLE `permission_role`
(
    `id`            VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `permission_id` VARCHAR(48) NOT NULL COMMENT '权限ID',
    `role_id`       VARCHAR(48) NOT NULL COMMENT '角色ID',
    `create_user`   VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user`   VARCHAR(48) COMMENT '修改用户',
    `create_time`   DATETIME    NOT NULL COMMENT '创建时间',
    `update_time`   DATETIME COMMENT '修改时间'
) COMMENT '权限-角色绑定表';

CREATE TABLE `problem`
(
    `id`          VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `type`        INT         NOT NULL DEFAULT 1 COMMENT '类型(0客观题,1主观题)',
    `subject`     VARCHAR(48) NOT NULL COMMENT '科目',
    `file_path`   VARCHAR(64) NOT NULL COMMENT '存放路径',
    `create_user` VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '题库';

CREATE TABLE `permission`
(
    `id`          VARCHAR(8) PRIMARY KEY COMMENT '主键',
    `name`        VARCHAR(32) NOT NULL COMMENT '权限名',
    `sign`        VARCHAR(32) NOT NULL COMMENT '标识符',
    `create_user` VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '权限列表';

CREATE TABLE `problem_exam`
(
    `id`          VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `exam_id`     VARCHAR(48) NOT NULL COMMENT '试卷ID',
    `permission`  VARCHAR(48) NOT NULL COMMENT '题库ID',
    `create_user` VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) comment '题库-试卷绑定表';

CREATE TABLE `subject`
(
    `id`          VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `name`        VARCHAR(32) NOT NULL COMMENT '科目名',
    `create_user` VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '科目表';

CREATE TABLE `exam`
(
    `id`          VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `file_path`   VARCHAR(64) NOT NULL COMMENT '存放路径',
    `create_user` VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '试卷表';

CREATE TABLE `feedback`
(
    `id`          VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `file_path`   VARCHAR(48) NOT NULL COMMENT '文件路径',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间'
) COMMENT '反馈表';