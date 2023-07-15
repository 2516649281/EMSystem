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
    `auth`        VARCHAR(64) COMMENT '权限',
    `create_user` VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '角色表';

CREATE TABLE `problem`
(
    `id`          VARCHAR(8) PRIMARY KEY COMMENT '主键',
    `main`        VARCHAR(64) NOT NULL COMMENT '题干',
    `options`     VARCHAR(64) COMMENT '选项',
    `type`        INT         NOT NULL DEFAULT 1 COMMENT '类型(0客观题,1主观题)',
    `answer`      VARCHAR(64) NOT NULL COMMENT '答案',
    `parse`       VARCHAR(64) NOT NULL COMMENT '解析',
    `score`       FLOAT       NOT NULL COMMENT '得分',
    `subject`     VARCHAR(48) NOT NULL COMMENT '科目',
    `create_user` VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '题库';

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
    `name`        VARCHAR(32)  NOT NULL COMMENT '试卷名',
    `time`        VARCHAR(32)  NOT NULL COMMENT '考试时间',
    `score`       FLOAT        NOT NULL COMMENT '总分',
    `pass`        FLOAT        NOT NULL COMMENT '合格分数',
    `problems`    VARCHAR(128) NOT NULL COMMENT '题库',
    `create_user` VARCHAR(48)  NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME     NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '试卷表';

CREATE TABLE `feedback`
(
    `id`          VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `message`     VARCHAR(256) NOT NULL COMMENT '反馈消息',
    `create_time` DATETIME     NOT NULL COMMENT '创建时间'
) COMMENT '反馈表';

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