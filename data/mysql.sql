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
    `email`       VARCHAR(32) COMMENT '邮箱',
    `phone`       VARCHAR(11) COMMENT '电话',
    `role_id`     VARCHAR(48)        NOT NULL DEFAULT 0 COMMENT '用户角色',
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
    `id`          VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `name`        VARCHAR(32) NOT NULL COMMENT '权限名',
    `sign`        VARCHAR(32) NOT NULL COMMENT '标识符',
    `is_default`  INT         NOT NULL DEFAULT 1 COMMENT '是否为默认',
    `create_user` VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '权限列表';

CREATE TABLE `router`
(
    `id`          VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `name`        VARCHAR(32) NOT NULL COMMENT '路由名',
    `value`       VARCHAR(48) NOT NULL COMMENT '路由值',
    `type`        INT         NOT NULL COMMENT '路由类型(0后端路由,1前端路由)',
    `create_user` VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user` VARCHAR(48) COMMENT '修改用户',
    `create_time` DATETIME    NOT NULL COMMENT '创建时间',
    `update_time` DATETIME COMMENT '修改时间'
) COMMENT '路由表';

CREATE TABLE `permission_router`
(
    `id`            VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `permission_id` VARCHAR(48) NOT NULL COMMENT '权限ID',
    `router_id`     VARCHAR(48) NOT NULL COMMENT '路由ID',
    `create_user`   VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user`   VARCHAR(48) COMMENT '修改用户',
    `create_time`   DATETIME    NOT NULL COMMENT '创建时间',
    `update_time`   DATETIME COMMENT '修改时间'
) COMMENT '权限-路由绑定表';

CREATE TABLE `problem_exam`
(
    `id`            VARCHAR(48) PRIMARY KEY COMMENT '主键',
    `exam_id`       VARCHAR(48) NOT NULL COMMENT '试卷ID',
    `permission_id` VARCHAR(48) NOT NULL COMMENT '题库ID',
    `create_user`   VARCHAR(48) NOT NULL COMMENT '创建用户',
    `update_user`   VARCHAR(48) COMMENT '修改用户',
    `create_time`   DATETIME    NOT NULL COMMENT '创建时间',
    `update_time`   DATETIME COMMENT '修改时间'
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

-- --------------------------
-- ---------测试数据----------
-- --------------------------

-- 权限表
INSERT INTO `permission`
VALUES ('11412c211a6042149610d2da6931ac57', '权限删除', 'sys:per:delete', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('125235b9c2fa400983a2adf55fc0218f', '权限查询', 'sys:per:select', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-29 16:04:13');
INSERT INTO `permission`
VALUES ('1370ab9948d64c86b8f454dbdf6148bb', '科目修改', 'op:sub:update', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('19e1065c601d402f979eeefae3ccf402', '题目修改', 'op:pro:update', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-29 12:35:05');
INSERT INTO `permission`
VALUES ('1dc27a1fec2940f58ca8791dcff1d311', '题目删除', 'op:pro:delete', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('1e1426fad12b4e09bc58d7fe2e5f4e19', '权限修改', 'sys:per:update', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('2e57771d96b6464f9e0ba22db63796e4', '个人信息查询', 'user:user:select', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('33272c92c164408e8ca33e6cdd2b72c9', '反馈信息新增', 'user:feed:insert', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ( '376612e5d41347d6871d7e2216cc678b', '反馈信息查看', 'sys:feed:select', 0, '0', '785ae2df8ce14b638770a781bcb2c313'
       , '2023-07-22 11:25:36'
       , '2023-07-29 09:59:05');
INSERT INTO `permission`
VALUES ('415d2c59d61449498505ac3b3270d940', '权限新增', 'sys:per:insert', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('4fc617c8d71a443da50b3603bc27414b', '试卷查询', 'op:exam:select', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('504d5f46f7984f20a749a18bc45fe87d', '试卷新增', 'op:exam:insert', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('51a998a73ab04996b1992e11e1f7f1a1', '角色新增', 'sys:role:insert', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('570d819be4fb45a69430fc2eb6e02f84', '试卷删除', 'op:exam:delete', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('7854230823434412be24b33b0d40844f', '个人信息修改', 'user:user:update', 0, '0', NULL, '2023-07-22 13:42:24',
        NULL);
INSERT INTO `permission`
VALUES ('7e64ee3b0ba449ee8a32d3197379542b', '个人信息注册', 'user:user:insert', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('7e916921afe946ab84a62c3fb8615689', '角色删除', 'sys:role:delete', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('83387680ef7a44ffb96dc4bfb9dd62c2', '用户删除', 'sys:user:delete', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('83d6633cfd90426b91898a457e831b9c', '题目新增', 'op:pro:insert', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('91d1e1e665fd4d7ba6fdc3178390e65d', '科目查询', 'op:sub:select', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('925bd3b2a75a4e4cbce2f91dbe722291', '角色查询', 'sys:role:select', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('97bfede98e794599a45b37bad4fc8112', '科目新增', 'op:sub:insert', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('9886ea88ed5f4d95b16c6edf9c88f037', '个人信息注销', 'user:user:delete', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('98b9b022a6b64ea38b2ae0a028ba5347', '试卷/题目信息查询', 'user:pro:select', 0, '0',
        NULL, '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('ba39d9fc58124e2588b5bcfd502dd0c8', '科目删除', 'op:sub:delete', 0, '0', NULL, '2023-07-30 08:16:29',
        NULL);
INSERT INTO `permission`
VALUES ('cbfe251729734bcb9f9486cd736f66b7', '题目查询', 'op:pro:select', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('d57428ef9c294cc4a98c6fcbff3fef9c', '角色修改', 'sys:role:update', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('d9b270a2d44344c4b4fc53511a212805', '反馈信息删除', 'sys:feed:delete', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-29 12:35:05');
INSERT INTO `permission`
VALUES ('ddb4596c59fb4f63a2a6c5fee80dd7e5', '用户修改', 'sys:user:update', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('e3338c77902e4a69963db05bf28ace27', '试卷修改', 'op:exam:update', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('e74cf92a484e42d4856cc541a232a588', '用户查询', 'sys:user:select', 0, '0', NULL, '2023-07-22 11:22:31',
        NULL);

-- 权限-角色关系表
INSERT INTO `permission_role`
VALUES ('027173e062174b4b82f8a8c685fdad92', '98b9b022a6b64ea38b2ae0a028ba5347', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('0ac079e9d64944ec81445e73aed170c9', '33272c92c164408e8ca33e6cdd2b72c9', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('0c23d1f4687f44ef840f901c73da4ccb', '415d2c59d61449498505ac3b3270d940', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('15cc4d4ece0341178916f024a51831e0', '2e57771d96b6464f9e0ba22db63796e4', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('15d7dd992e534e5f935b1b8a1b242b60', '91d1e1e665fd4d7ba6fdc3178390e65d', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('1efa159922ba48fb8425d429180599d0', '33272c92c164408e8ca33e6cdd2b72c9', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('21e8c5eb5d1848a9a233180739d0a562', '925bd3b2a75a4e4cbce2f91dbe722291', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('24a928917bbc4df3a4543d38f6e5cebc', '98b9b022a6b64ea38b2ae0a028ba5347', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('2fbd34d073c640ad8cff8aa9bdafa275', '4fc617c8d71a443da50b3603bc27414b', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('404f4fc5499e483b801b11d814780e4b', '376612e5d41347d6871d7e2216cc678b', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('4bd5959d760f4b0a98f85ad6a8de1589', '19e1065c601d402f979eeefae3ccf402', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('507bb24a39454569a2044e8a96f3b67b', '9886ea88ed5f4d95b16c6edf9c88f037', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('5372ce78011b4a4d8810eb8faa613bd9', 'e3338c77902e4a69963db05bf28ace27', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('5747a2c2eb03466796d7e1389d017d3b', '33272c92c164408e8ca33e6cdd2b72c9', 'ed394afe8c844205a6d7579f2b04b073',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('5b46f13fce8843748750315303173d1d', '925bd3b2a75a4e4cbce2f91dbe722291', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('5c03e354a3054c6da40c4f179a1fdc27', '19e1065c601d402f979eeefae3ccf402', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('622dc949c38d464f8b31c9b5474afff1', '7e64ee3b0ba449ee8a32d3197379542b', 'ed394afe8c844205a6d7579f2b04b073',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('6d21b5c256af468e9e23acd22b19cea2', '7854230823434412be24b33b0d40844f', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('6f140953e88c4c4c87d0e93a1f6e7d34', '4fc617c8d71a443da50b3603bc27414b', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('71dcbcb348db48edb24de17a6431cf51', '7e64ee3b0ba449ee8a32d3197379542b', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('734489dde0af4ac786815a939856bcd3', '7854230823434412be24b33b0d40844f', '0', '785ae2df8ce14b638770a781bcb2c313',
        NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('7392a96e7bd94867aef3e979dd02c240', '1370ab9948d64c86b8f454dbdf6148bb', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('7b811a411e0e49a7991ea31d042610c4', '91d1e1e665fd4d7ba6fdc3178390e65d', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('7d416de0f3f74b7cac417900fa44461c', '7854230823434412be24b33b0d40844f', 'ed394afe8c844205a6d7579f2b04b073',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('7f176c03891d45469c165f241363c42f', 'cbfe251729734bcb9f9486cd736f66b7', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('7fb83e2aebdf4f04a9f8dfdadf25e8b5', 'e74cf92a484e42d4856cc541a232a588', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('7fc8a2b98f834ea1bd92b72139cd7f11', '9886ea88ed5f4d95b16c6edf9c88f037', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('8189b86c8a9a4d30acca037c0484c4de', '48960c4727a14446802ded153dde30c4', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('85d6e5f488f14c30b547b71d2271d6ff', '9886ea88ed5f4d95b16c6edf9c88f037', '0', '785ae2df8ce14b638770a781bcb2c313',
        NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('8e286b516e0f4ac09808850b4207b8d5', 'e3338c77902e4a69963db05bf28ace27', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('8f8d3c38e9e74d4d81af5ff34972fe22', 'd57428ef9c294cc4a98c6fcbff3fef9c', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('8f9583cc00644ff19ca89e3cdf42b551', '97bfede98e794599a45b37bad4fc8112', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('92d0f731baba46aa82e6801ec4c69e06', '9886ea88ed5f4d95b16c6edf9c88f037', 'ed394afe8c844205a6d7579f2b04b073',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('93b309cc117b4bd583b8020c1d16ff74', '504d5f46f7984f20a749a18bc45fe87d', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('9809040c15884203aa5e239f4bc54336', '2e57771d96b6464f9e0ba22db63796e4', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('9888e5c3e5724a129a64f1b6fd2a4a9d', '7e916921afe946ab84a62c3fb8615689', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('a06228f0628b4a84a81a6d07bd8ca05c', '504d5f46f7984f20a749a18bc45fe87d', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('a0ff71a4652e4096a610fca44145e525', '7e64ee3b0ba449ee8a32d3197379542b', '0', '785ae2df8ce14b638770a781bcb2c313',
        NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('a957fa2313b84381be0924b284ba9099', '1e1426fad12b4e09bc58d7fe2e5f4e19', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('a9bd89817c164a71a1603cd13ff80fff', '11412c211a6042149610d2da6931ac57', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('abcc3bcfef804ff483ece78580826d2e', '48960c4727a14446802ded153dde30c4', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('abd2e07e954644b2aa808c715a113671', '1370ab9948d64c86b8f454dbdf6148bb', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('b2e2879de51c435d8f85570f6d1ae813', '1dc27a1fec2940f58ca8791dcff1d311', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('be93293ae82e4bd68d2c17e06adeca01', '2e57771d96b6464f9e0ba22db63796e4', 'ed394afe8c844205a6d7579f2b04b073',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('bf46f55ea2874e1780df249d4be04d50', '570d819be4fb45a69430fc2eb6e02f84', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('c41c55f5195b4ece9812768660a7ba58', '1dc27a1fec2940f58ca8791dcff1d311', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('c4ff5df9aa0b407d8ac748e4f8695eeb', '125235b9c2fa400983a2adf55fc0218f', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('c7cd53b727d7470a8c2c197bbd5ac98d', 'd9b270a2d44344c4b4fc53511a212805', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('d53d001587c041178b181b35fe1a42b7', '83d6633cfd90426b91898a457e831b9c', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('d5810999739a4c5e8ab3731f10b292dd', '7854230823434412be24b33b0d40844f', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('d935afc3fb4d4d708e67665942250fb0', '83d6633cfd90426b91898a457e831b9c', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('d9628df5581f4582af08fa7a84c46806', '51a998a73ab04996b1992e11e1f7f1a1', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('d9f7b20ea23245b0b3b2b9c31eea20c0', '97bfede98e794599a45b37bad4fc8112', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('da5c0c6cdd354428bc607a5a03b87e28', '570d819be4fb45a69430fc2eb6e02f84', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('dbb52ee541a647f5bb4479eea44bf5b4', '33272c92c164408e8ca33e6cdd2b72c9', '0', '785ae2df8ce14b638770a781bcb2c313',
        NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('deb11988037b4637a7542a9ca78ae6ab', '83387680ef7a44ffb96dc4bfb9dd62c2', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('e411b3374f764581b1e7cddbbebe3d05', '7e64ee3b0ba449ee8a32d3197379542b', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('e7d37b0a55c74442a96a42961faae743', '2e57771d96b6464f9e0ba22db63796e4', '0', '785ae2df8ce14b638770a781bcb2c313',
        NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('edaf71ce229442fa92147e3647f6a131', 'ba39d9fc58124e2588b5bcfd502dd0c8', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission_role`
VALUES ('ff8672cd1bbd493a9ffde6660ccc8e49', 'ddb4596c59fb4f63a2a6c5fee80dd7e5', '232b9005ab8a466495ca0b1f741e5adc', '0',
        NULL, '2023-07-30 08:16:29', NULL);

-- 角色表
INSERT INTO `role`
VALUES ('0', '普通用户', 'localUser', '785ae2df8ce14b638770a781bcb2c313', '2023-07-21 17:55:48', '2023-07-28 16:07:35');
INSERT INTO `role`
VALUES ('232b9005ab8a466495ca0b1f741e5adc', '超级管理员', '785ae2df8ce14b638770a781bcb2c313', '0', '2023-07-22 11:40:01',
        '2023-07-30 08:16:29');

-- 科目表
INSERT INTO `subject`
VALUES ('50235a3b1ca142c3ba01197c6892bd3f', '生物', '0', NULL, '2023-07-30 07:35:09', NULL);
INSERT INTO `subject`
VALUES ('701ddb01101147219a94d1181b660d5a', '英语', '0', '0', '2023-07-30 07:35:09', '2023-07-30 08:07:53');
INSERT INTO `subject`
VALUES ('9cef694aad514e2383ef9934e419fa90', '政治', '0', NULL, '2023-07-30 07:35:09', NULL);
INSERT INTO `subject`
VALUES ('a0345e31d1664059ab855b52ea0a531f', '历史', '0', NULL, '2023-07-30 07:35:09', NULL);
INSERT INTO `subject`
VALUES ('a24cfe6187d9442d8ccfffaf5e4681f1', '地理', '0', NULL, '2023-07-30 07:35:09', NULL);
INSERT INTO `subject`
VALUES ('aa0f7a5b363445c589d620d098f0d406', '化学', '0', NULL, '2023-07-30 07:35:09', NULL);
INSERT INTO `subject`
VALUES ('ca8732662ed5418abe256a188ce7fe8b', '数学', '0', NULL, '2023-07-30 07:35:09', NULL);
INSERT INTO `subject`
VALUES ('e3763f0aa4de4d3d8772eb5e1a7ec47c', '语文', '0', NULL, '2023-07-30 07:35:09', NULL);
INSERT INTO `subject`
VALUES ('f2b159d70546415b8befdc9d2a5126ae', '物理', '0', NULL, '2023-07-30 07:35:09', NULL);

-- 用户表
INSERT INTO `user`
VALUES ('0', 'root', '$2a$10$HEi62MXckXC75lDbBx2T0uF6h5H6.gTUg9a9b4UcNfDFmHoEDqLfi', '超级管理员', '0.jpg', 0,
        '2516649281@qq.com', '17571038682', '232b9005ab8a466495ca0b1f741e5adc', 0, 'localhost', NULL,
        '2023-07-22 13:24:02', NULL);
INSERT INTO `user`
VALUES ('6611ac92527141f0a58487173fdf63bd', 'user1', '$2a$10$NJnNA.vYlZj9ZuYZJ496dOJu5w0SjFWZ7Zxqzl1t1CYv0W440/nU2',
        '测试1', '0', 0, '2546378951@qq.com', '12547863594', '232b9005ab8a466495ca0b1f741e5adc', 0, 'localUser', NULL,
        '2023-07-22 13:24:02', NULL);
INSERT INTO `user`
VALUES ('675bd7b98de6455195b0938eb1ec73c9', 'user3', '$2a$10$sEaAZqlkUXmHJ4hlWDHxde6Hz8E1z7Mkqrbse3IBllHzhrSRuVYPu',
        '测试3', '0', 1, '2486915783@qq.com', '15479634587', '0', 0, 'localUser', NULL, '2023-07-28 08:02:56', NULL);
INSERT INTO `user`
VALUES ('7f76d86b777942649136f062e774d3f9', 'root1', '$2a$10$EWGButendp1R6sDd0Zwc4u/0aaoTFv7t.NxQhxdvMKxsOF.TsoraG',
        '超级管理员2', '0', 1, '2548936475@qq.com', '15784639458', '232b9005ab8a466495ca0b1f741e5adc', 0, 'localUser', NULL,
        '2023-07-29 11:26:37', NULL);
INSERT INTO `user`
VALUES ('d0651a7d27834111845b3865b4c932a5', 'user2', '$2a$10$HEi62MXckXC75lDbBx2T0uF6h5H6.gTUg9a9b4UcNfDFmHoEDqLfi',
        '测试2', '0', 1, '2546378125@qq.com', '15479643587', '0', 0, 'localUser', NULL, '2023-07-27 16:42:13', NULL);