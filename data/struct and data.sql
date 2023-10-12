/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8.0
 Source Server Type    : MySQL
 Source Server Version : 80020 (8.0.20)
 Source Host           : localhost:3306
 Source Schema         : ems

 Target Server Type    : MySQL
 Target Server Version : 80020 (8.0.20)
 File Encoding         : 65001

 Date: 12/10/2023 22:15:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `file_path`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '存放路径',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '试卷表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam
-- ----------------------------
INSERT INTO `exam`
VALUES ('344b72bded14456ea2cc0ba34c87c0a9', 'D:/Test/exam_344b72bded14456ea2cc0ba34c87c0a9.txt', '0', '0',
        '2023-08-10 17:59:21', '2023-10-09 19:19:53');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `file_path`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件路径',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '反馈表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback`
VALUES ('0588aec8fd3a4be695820eda70840634', 'D:/Test/feedBack_0588aec8fd3a4be695820eda70840634.txt',
        '2023-08-11 08:44:34');
INSERT INTO `feedback`
VALUES ('080338b70d2d4378b6c8e6698330b471', 'D:/Test/feedBack_080338b70d2d4378b6c8e6698330b471.txt',
        '2023-08-11 14:39:47');
INSERT INTO `feedback`
VALUES ('26516e8e893244579280c6cc413c0941', 'D:/Test/feedBack_26516e8e893244579280c6cc413c0941.txt',
        '2023-08-11 08:44:34');
INSERT INTO `feedback`
VALUES ('5eca57f143a642a8a9946b141759a928', 'D:/Test/feedBack_5eca57f143a642a8a9946b141759a928.txt',
        '2023-08-11 08:44:34');

-- ----------------------------
-- Table structure for gradle
-- ----------------------------
DROP TABLE IF EXISTS `gradle`;
CREATE TABLE `gradle`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '年级名',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '年级表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gradle
-- ----------------------------
INSERT INTO `gradle`
VALUES ('0', '高一', '0', NULL, '2023-08-03 14:33:31', NULL);
INSERT INTO `gradle`
VALUES ('ca72d06f3030497b835c196e957a46f9', '高二', '0', NULL, '2023-08-09 09:51:55', NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名',
    `sign`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标识符',
    `is_default`  int                                                          NOT NULL DEFAULT 1 COMMENT '是否为默认',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限列表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission`
VALUES ('09345f3798a44c69bf41e92fba428e8c', '删除试卷历史', 'user:ue:delete', 1, '0', NULL, '2023-09-06 22:10:33',
        NULL);
INSERT INTO `permission`
VALUES ('0b8aa4205d1b48999eda0a87c69213c0', '新增试卷历史', 'user:ue:insert', 1, '0', NULL, '2023-09-06 22:10:33',
        NULL);
INSERT INTO `permission`
VALUES ('0cfc6494d2284252a89fd97f443abc29', '查看试卷历史', 'user:ue:select', 1, '0', NULL, '2023-09-06 22:10:33',
        NULL);
INSERT INTO `permission`
VALUES ('11412c211a6042149610d2da6931ac57', '权限删除', 'sys:per:delete', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-08-02 08:20:33');
INSERT INTO `permission`
VALUES ('125235b9c2fa400983a2adf55fc0218f', '权限查询', 'sys:per:select', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('1370ab9948d64c86b8f454dbdf6148bb', '科目修改', 'op:sub:update', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('19e1065c601d402f979eeefae3ccf402', '题目修改', 'op:pro:update', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('1dc27a1fec2940f58ca8791dcff1d311', '题目删除', 'op:pro:delete', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('1e1426fad12b4e09bc58d7fe2e5f4e19', '权限修改', 'sys:per:update', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('24cbeb47067c4133b0b079a9dc8712c1', '年级新增', 'op:gradle:insert', 1, '0', NULL, '2023-08-06 14:58:25', NULL);
INSERT INTO `permission`
VALUES ('27483ef7113949d983de2e2823984242', '年级修改', 'op:gradle:update', 1, '0', NULL, '2023-08-06 14:58:25', NULL);
INSERT INTO `permission`
VALUES ('288caddc8fe64a27a71d32d6091deaae', '删除做题历史', 'user:up:delete', 1, '0', '0', '2023-09-06 22:10:33',
        '2023-09-06 22:10:33');
INSERT INTO `permission`
VALUES ('2c00b44556a64df1aee4220c6820e53e', '路由添加', 'sys:router:insert', 0, '0', '0', '2023-07-30 15:37:15',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('2e57771d96b6464f9e0ba22db63796e4', '个人信息查询', 'user:user:select', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('31a54479774c4065938411f81d668c32', '年级查询', 'op:gradle:select', 1, '0', NULL, '2023-08-06 14:58:25', NULL);
INSERT INTO `permission`
VALUES ('33272c92c164408e8ca33e6cdd2b72c9', '反馈信息新增', 'user:feed:insert', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('376612e5d41347d6871d7e2216cc678b', '反馈信息查看', 'sys:feed:select', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('40a16c64701849009852f96cde5f3a3c', '路由查询', 'sys:router:select', 0, '0', '0', '2023-07-30 15:37:15',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('40f72874f100407d85cbe4482abe2bb5', '新增做题历史', 'user:up:insert', 1, '0', '0', '2023-09-06 22:10:33',
        '2023-09-06 22:10:33');
INSERT INTO `permission`
VALUES ('415d2c59d61449498505ac3b3270d940', '权限新增', 'sys:per:insert', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('4fc617c8d71a443da50b3603bc27414b', '试卷查询', 'op:exam:select', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('504d5f46f7984f20a749a18bc45fe87d', '试卷新增', 'op:exam:insert', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('51a998a73ab04996b1992e11e1f7f1a1', '角色新增', 'sys:role:insert', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('548f84361dff4ed3b71fd2898a535de5', '修改试卷历史', 'user:ue:update', 1, '0', NULL, '2023-09-06 22:10:33',
        NULL);
INSERT INTO `permission`
VALUES ('570d819be4fb45a69430fc2eb6e02f84', '试卷删除', 'op:exam:delete', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('7854230823434412be24b33b0d40844f', '个人信息修改', 'user:user:update', 0, '0', '0', '2023-07-22 13:42:24',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('7e64ee3b0ba449ee8a32d3197379542b', '个人信息注册', 'user:user:insert', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-31 10:10:13');
INSERT INTO `permission`
VALUES ('7e916921afe946ab84a62c3fb8615689', '角色删除', 'sys:role:delete', 0, '0', NULL, '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('807773f3c4e84e7289f73986d0bbbac2', '路由删除', 'sys:router:delete', 0, '0', '0', '2023-07-30 15:37:15',
        '2023-08-01 15:37:13');
INSERT INTO `permission`
VALUES ('83387680ef7a44ffb96dc4bfb9dd62c2', '用户删除', 'sys:user:delete', 0, '0', NULL, '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('83d6633cfd90426b91898a457e831b9c', '题目新增', 'op:pro:insert', 0, '0', NULL, '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('91d1e1e665fd4d7ba6fdc3178390e65d', '科目查询', 'op:sub:select', 0, '0', NULL, '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('925bd3b2a75a4e4cbce2f91dbe722291', '角色查询', 'sys:role:select', 0, '0', NULL, '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('97bfede98e794599a45b37bad4fc8112', '科目新增', 'op:sub:insert', 0, '0', NULL, '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('9886ea88ed5f4d95b16c6edf9c88f037', '个人信息注销', 'user:user:delete', 0, '0', NULL, '2023-07-22 11:25:36',
        NULL);
INSERT INTO `permission`
VALUES ('a68925f349a8462ebc597b9d9533791f', '修改做题历史', 'user:up:update', 1, '0', NULL, '2023-09-06 22:10:33',
        NULL);
INSERT INTO `permission`
VALUES ('abcb78a67ec74e86bf57a1f2dcaaea20', '年级删除', 'op:gradle:delete', 1, '0', NULL, '2023-08-06 14:58:25', NULL);
INSERT INTO `permission`
VALUES ('ba39d9fc58124e2588b5bcfd502dd0c8', '科目删除', 'op:sub:delete', 0, '0', NULL, '2023-07-30 08:16:29', NULL);
INSERT INTO `permission`
VALUES ('c1be6dc2b1854fc488682e00bf5e3508', '查看做题历史', 'user:up:select', 1, '0', NULL, '2023-09-06 22:10:33',
        NULL);
INSERT INTO `permission`
VALUES ('cbfe251729734bcb9f9486cd736f66b7', '题目查询', 'op:pro:select', 0, '0', NULL, '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('d57428ef9c294cc4a98c6fcbff3fef9c', '角色修改', 'sys:role:update', 0, '0', NULL, '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('d9b270a2d44344c4b4fc53511a212805', '反馈信息删除', 'sys:feed:delete', 0, '0', '0', '2023-07-22 11:25:36',
        '2023-07-29 12:35:05');
INSERT INTO `permission`
VALUES ('ddb4596c59fb4f63a2a6c5fee80dd7e5', '用户修改', 'sys:user:update', 0, '0', NULL, '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('e3338c77902e4a69963db05bf28ace27', '试卷修改', 'op:exam:update', 0, '0', NULL, '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('e74cf92a484e42d4856cc541a232a588', '用户查询', 'sys:user:select', 0, '0', NULL, '2023-07-22 11:22:31', NULL);
INSERT INTO `permission`
VALUES ('f147715d5e334efc97caf4e49bd28228', '路由修改', 'sys:router:update', 0, '0', NULL, '2023-07-30 15:37:15', NULL);

-- ----------------------------
-- Table structure for permission_role
-- ----------------------------
DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role`
(
    `id`            varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `permission_id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限ID',
    `role_id`       varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色ID',
    `create_user`   varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user`   varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改用户',
    `create_time`   datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time`   datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限-角色绑定表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_role
-- ----------------------------
INSERT INTO `permission_role`
VALUES ('07d330b193ce4d70aa346ad260168271', '1dc27a1fec2940f58ca8791dcff1d311', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('0a8d9396ac9941d09d80dc21dafafcc1', '2c00b44556a64df1aee4220c6820e53e', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('0bf2c9cb080441ddbb5fc1dcdf7e6512', '97bfede98e794599a45b37bad4fc8112', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('0cbd96119dd1443cb644e9a81877ddd6', 'd9b270a2d44344c4b4fc53511a212805', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('0d4f94944f124094b66dacb5bd5b89e9', '33272c92c164408e8ca33e6cdd2b72c9', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('0e646c417c5c4522b8da89958ef209aa', '7e916921afe946ab84a62c3fb8615689', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('0eaaea3548fc4baea7cd7fa94ea28eb7', '83d6633cfd90426b91898a457e831b9c', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('10c4378eda114b25adf589eb2034c000', '9886ea88ed5f4d95b16c6edf9c88f037', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('110e8bb334f646e28d9c4f8804d9ae69', '83387680ef7a44ffb96dc4bfb9dd62c2', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('18e07a47170348e4bc45284131d72487', 'd9b270a2d44344c4b4fc53511a212805', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('19a5a319d5f942c28120692fd14d4270', 'd9b270a2d44344c4b4fc53511a212805', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('1a680a1c55594993a80c9f3f7367ebdf', '504d5f46f7984f20a749a18bc45fe87d', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('1b57e1ceaf4144afbc706ed5bd061d73', 'ba39d9fc58124e2588b5bcfd502dd0c8', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('1b7725ef25f5475da6323d0bf67300ac', '11412c211a6042149610d2da6931ac57', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('1d0fe9061a9a4ccbaae6af51d60ba454', '27483ef7113949d983de2e2823984242', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('1efa159922ba48fb8425d429180599d0', '33272c92c164408e8ca33e6cdd2b72c9', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('1fedb56ba1c949009e6f4755b214bd11', '2e57771d96b6464f9e0ba22db63796e4', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('21371feab6df4f22be27b5b0a8522ff9', '376612e5d41347d6871d7e2216cc678b', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('21e8c5eb5d1848a9a233180739d0a562', '925bd3b2a75a4e4cbce2f91dbe722291', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('2394dd0ef67a4dbda0631572c8ae0ae2', '91d1e1e665fd4d7ba6fdc3178390e65d', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('23c46e52ad2b42feaeac49f90a8eec93', '83d6633cfd90426b91898a457e831b9c', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('23e181bacfb1450a9d1e23368a1d7954', '9886ea88ed5f4d95b16c6edf9c88f037', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('2a445947cf3f4f3e9521d1d13219645a', '415d2c59d61449498505ac3b3270d940', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('2dc2fc7422f840bba33956d9076cc05a', '925bd3b2a75a4e4cbce2f91dbe722291', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('3114c8e2cbee4e01880b502f3f932774', 'e3338c77902e4a69963db05bf28ace27', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('3204f21fc9ba4fc785c31f393aae53b0', '2e57771d96b6464f9e0ba22db63796e4', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('32151b7bf1304c7389ae62ec179e84c2', '570d819be4fb45a69430fc2eb6e02f84', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('37f046c80794404bb3d69e4af8c3fd6e', '7e64ee3b0ba449ee8a32d3197379542b', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('3b75935264f747c485882b386a9ac13f', '807773f3c4e84e7289f73986d0bbbac2', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('3f0c5fb76a5b4f779114ddcbfb370144', '27483ef7113949d983de2e2823984242', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('3fb0ddba59e34fd79aaa9b705aaee3e9', '09345f3798a44c69bf41e92fba428e8c', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('40f91ac947fb4ab39bf0cf92786adb30', '83387680ef7a44ffb96dc4bfb9dd62c2', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('438b9efc5aee4d83b1dc560539cd5eb6', '125235b9c2fa400983a2adf55fc0218f', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('470c7befe5654b2b9206beed48d73c3a', 'e74cf92a484e42d4856cc541a232a588', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('5040c7b85d8346879e6f75c0a5194170', '1dc27a1fec2940f58ca8791dcff1d311', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('5179f209d229454dbb8c55ce4c0df963', '7854230823434412be24b33b0d40844f', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('5372ce78011b4a4d8810eb8faa613bd9', 'e3338c77902e4a69963db05bf28ace27', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('5518588303904655af6d8a1dc5fbf4f2', '504d5f46f7984f20a749a18bc45fe87d', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('55c3beaef4c849afb0963247446ee3cf', '0b8aa4205d1b48999eda0a87c69213c0', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('5747a2c2eb03466796d7e1389d017d3b', '33272c92c164408e8ca33e6cdd2b72c9', 'ed394afe8c844205a6d7579f2b04b073',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('59cf7c3d68c34cf9bf542d2e88ea6540', '376612e5d41347d6871d7e2216cc678b', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('5a472fb5f1d44ccd843f353ffc53a87a', '91d1e1e665fd4d7ba6fdc3178390e65d', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('5a8f18b87d9b4c95935613e454455ab3', '570d819be4fb45a69430fc2eb6e02f84', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('5b3cb74fd68446c6bf33079ae26858d3', 'ddb4596c59fb4f63a2a6c5fee80dd7e5', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('5c03e354a3054c6da40c4f179a1fdc27', '19e1065c601d402f979eeefae3ccf402', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('5c6b26141e46419fbe4091e87b538023', '1370ab9948d64c86b8f454dbdf6148bb', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('5cdb9d286d564d129a19fa1f8bb87aa4', '7854230823434412be24b33b0d40844f', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('5cdfdf1f31e84c82b0a9fa94e6a4eaee', '40a16c64701849009852f96cde5f3a3c', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('5d0aeeafc69a4943a839207a476078cd', '4fc617c8d71a443da50b3603bc27414b', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('622dc949c38d464f8b31c9b5474afff1', '7e64ee3b0ba449ee8a32d3197379542b', 'ed394afe8c844205a6d7579f2b04b073',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('6735aa68454b42ee80a84d215e7ed3e6', '415d2c59d61449498505ac3b3270d940', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('67e3304e8e1646ca81dfd51053ce17c0', '2e57771d96b6464f9e0ba22db63796e4', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('6964e063db3443ed9c86273d99657b14', '27483ef7113949d983de2e2823984242', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('6f140953e88c4c4c87d0e93a1f6e7d34', '4fc617c8d71a443da50b3603bc27414b', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('6fe4b14805994fe7bcdb540590797dd5', 'abcb78a67ec74e86bf57a1f2dcaaea20', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('71d9d1eea2ab4847b06d9ae5297b820d', '2c00b44556a64df1aee4220c6820e53e', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('71dcbcb348db48edb24de17a6431cf51', '7e64ee3b0ba449ee8a32d3197379542b', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('741a9ae18b904195b62abad1ff29ee9c', '19e1065c601d402f979eeefae3ccf402', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('742996aaadaf45f7a70818e7d43449cc', '1dc27a1fec2940f58ca8791dcff1d311', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('7445f75a76de44e3ae877d3eb9cef4cc', 'e3338c77902e4a69963db05bf28ace27', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('77fb724b221a42bdbc98296b83cc08be', '33272c92c164408e8ca33e6cdd2b72c9', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('786e4ade35fa43a8b8c323980944011e', '33272c92c164408e8ca33e6cdd2b72c9', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('7b811a411e0e49a7991ea31d042610c4', '91d1e1e665fd4d7ba6fdc3178390e65d', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('7c8a58f8fe88439888b519cf480102b7', '19e1065c601d402f979eeefae3ccf402', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('7d3ebb323b2b476a90dcb77d7cd48a6b', '288caddc8fe64a27a71d32d6091deaae', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('7d416de0f3f74b7cac417900fa44461c', '7854230823434412be24b33b0d40844f', 'ed394afe8c844205a6d7579f2b04b073',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('7d6af51f74044ff5b327cc728bb967b6', '9886ea88ed5f4d95b16c6edf9c88f037', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('7fc8a2b98f834ea1bd92b72139cd7f11', '9886ea88ed5f4d95b16c6edf9c88f037', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('84b4063986f1406f81fb547c8de37849', '125235b9c2fa400983a2adf55fc0218f', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('857df360a7d54e258a051e718a8ba059', '51a998a73ab04996b1992e11e1f7f1a1', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('8674a1cf514e4a63a86123ed5989c63a', '24cbeb47067c4133b0b079a9dc8712c1', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('87c15d81a0674981803172fda7480745', '40a16c64701849009852f96cde5f3a3c', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('889454ae15d9495ea1edfd09c398a4ed', '7e64ee3b0ba449ee8a32d3197379542b', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('89189e7124ad4db9a9f3ba3124f3e8b8', 'cbfe251729734bcb9f9486cd736f66b7', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('89d8db1bc95047a0ab6f3deaf094c5b7', 'cbfe251729734bcb9f9486cd736f66b7', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('8a36f95314544763b07baa48795367fe', '1370ab9948d64c86b8f454dbdf6148bb', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('8b0bcdbaf29a4038ae64b3830d8a50b1', '0cfc6494d2284252a89fd97f443abc29', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('8d5271705cc14335a7220a5751b43824', '24cbeb47067c4133b0b079a9dc8712c1', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('8ded7f97c98d46a69673b500cb9145ac', '7e64ee3b0ba449ee8a32d3197379542b', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('8e512c314d8640a1ad541c755974485e', '925bd3b2a75a4e4cbce2f91dbe722291', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('92d0f731baba46aa82e6801ec4c69e06', '9886ea88ed5f4d95b16c6edf9c88f037', 'ed394afe8c844205a6d7579f2b04b073',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('9475b65f7aa1453a90109ca9877bd93e', '4fc617c8d71a443da50b3603bc27414b', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('95ceec752c1d4d38b3ebbb861d426685', '31a54479774c4065938411f81d668c32', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('97a71bc0b5d9400f8bb71ca90b8c8a72', '11412c211a6042149610d2da6931ac57', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('9809040c15884203aa5e239f4bc54336', '2e57771d96b6464f9e0ba22db63796e4', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('9ed6bdd966db45cab91393368bde4ef4', 'd57428ef9c294cc4a98c6fcbff3fef9c', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('9fcc691d51a541a1a355544249aba2a5', 'e74cf92a484e42d4856cc541a232a588', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('9fd0f06f3aa942858882821581df1a93', '51a998a73ab04996b1992e11e1f7f1a1', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('a06228f0628b4a84a81a6d07bd8ca05c', '504d5f46f7984f20a749a18bc45fe87d', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('a15698ab37904f90b5bc3f331ca40d10', '9886ea88ed5f4d95b16c6edf9c88f037', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('a244c63ff62144d0910eb663b93601e3', '91d1e1e665fd4d7ba6fdc3178390e65d', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('abd2e07e954644b2aa808c715a113671', '1370ab9948d64c86b8f454dbdf6148bb', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('abe519e3f5434fb09c98ad54704955a8', '33272c92c164408e8ca33e6cdd2b72c9', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('ac0fff336104444fb7ff2f37fb52bf40', '376612e5d41347d6871d7e2216cc678b', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('ad6e9cde7e0a465e941bc4db3ed9cbba', '83d6633cfd90426b91898a457e831b9c', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('aeaa54e735054d99a3719e5fc33c3004', 'a68925f349a8462ebc597b9d9533791f', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('afd34c9905bb4e648a991e2c59066c2a', '24cbeb47067c4133b0b079a9dc8712c1', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('b27317cb4cfd410ebef1f4cc905a6410', 'ba39d9fc58124e2588b5bcfd502dd0c8', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('b4c4691bcb98434ab1b4cb37e57a8b1e', 'e3338c77902e4a69963db05bf28ace27', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('ba70379f9c174323a0bbe56f45c47195', '1e1426fad12b4e09bc58d7fe2e5f4e19', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('babf4171ea584d7198324ffabf3e99ba', 'ba39d9fc58124e2588b5bcfd502dd0c8', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('be93293ae82e4bd68d2c17e06adeca01', '2e57771d96b6464f9e0ba22db63796e4', 'ed394afe8c844205a6d7579f2b04b073',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('c1a4b3f81a1a4b82a655b487898fc235', '31a54479774c4065938411f81d668c32', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('c41c55f5195b4ece9812768660a7ba58', '1dc27a1fec2940f58ca8791dcff1d311', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('c7d9a5bedf5c4e6c805b3640d4cdaa05', '97bfede98e794599a45b37bad4fc8112', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('c9174387976848b096aaae7d1913db12', '31a54479774c4065938411f81d668c32', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('ca511043495b4938828a06b190ba00c0', '7854230823434412be24b33b0d40844f', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('cd9eaab64b90444fa4031f34eaff21b9', '1e1426fad12b4e09bc58d7fe2e5f4e19', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('d08df134754c4c91ae834cffd978cae4', '7e916921afe946ab84a62c3fb8615689', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('d2bed565504145e792769c3b109164f8', 'd57428ef9c294cc4a98c6fcbff3fef9c', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('d53d001587c041178b181b35fe1a42b7', '83d6633cfd90426b91898a457e831b9c', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('d5810999739a4c5e8ab3731f10b292dd', '7854230823434412be24b33b0d40844f', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('d85d74e50b614cfda5cb9a5f9f4f6102', '570d819be4fb45a69430fc2eb6e02f84', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('d8625ed8f2f642e68b9a50a912b20b51', '4fc617c8d71a443da50b3603bc27414b', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('d9db9c41c5d14af0a655e82d7779b1df', 'ddb4596c59fb4f63a2a6c5fee80dd7e5', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('d9f7b20ea23245b0b3b2b9c31eea20c0', '97bfede98e794599a45b37bad4fc8112', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('da4d2ca3826a48bb9da0046911444e4a', 'f147715d5e334efc97caf4e49bd28228', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('da5c0c6cdd354428bc607a5a03b87e28', '570d819be4fb45a69430fc2eb6e02f84', '98876a5b3f164eecbd0a0d32b8ac5b07',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-28 16:07:35', NULL);
INSERT INTO `permission_role`
VALUES ('dac60ce224f943d7b2503ce0276eaa76', 'c1be6dc2b1854fc488682e00bf5e3508', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('dbc49a5249af4a818a60f598dbd561d8', 'abcb78a67ec74e86bf57a1f2dcaaea20', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('dbdb9ff84b204576862fd48d59209e4a', '1370ab9948d64c86b8f454dbdf6148bb', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('dda1ff2209db4575897a0300d7c8c3b7', 'cbfe251729734bcb9f9486cd736f66b7', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('e0f0ca8d8e744fa99bcb8598f876b6b2', '97bfede98e794599a45b37bad4fc8112', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('e174176b746c4cd3869dce4b9c48f4b4', '125235b9c2fa400983a2adf55fc0218f', '44cf370197e6446c8a1cc5cadf4048c2', '0',
        NULL, '2023-07-30 12:59:37', NULL);
INSERT INTO `permission_role`
VALUES ('e2d26d2a7efc46389a444f98903ea5ea', '19e1065c601d402f979eeefae3ccf402', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('e4da166b00224386ac9a41bcfd18da2d', '7854230823434412be24b33b0d40844f', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('e5d08386b9b64bc4bd7cb164777508ac', '4fc617c8d71a443da50b3603bc27414b', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('e615a22ae6fb4042983db86d1a4ee512', 'f147715d5e334efc97caf4e49bd28228', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('e67c4df2d3d2408ca87a1fadf72e5f11', 'abcb78a67ec74e86bf57a1f2dcaaea20', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('e750263bb77945269979e1474ab670c8', '31a54479774c4065938411f81d668c32', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('e8e6a67200304febb9513c0c4ea858c8', '504d5f46f7984f20a749a18bc45fe87d', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('eb29fcdd3ef5499c996397b6d4836515', 'cbfe251729734bcb9f9486cd736f66b7', 'a98606c89d624d26a93b377da61b230d', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('ecf2f8da2d4f4bd9bb8f36d21f6bd78a', '2e57771d96b6464f9e0ba22db63796e4', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 19:05:51', NULL);
INSERT INTO `permission_role`
VALUES ('f05e1feb059946a0b1f7af2edc74bc0b', '548f84361dff4ed3b71fd2898a535de5', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);
INSERT INTO `permission_role`
VALUES ('f06b595468dd43d68365eb1122a23f69', '807773f3c4e84e7289f73986d0bbbac2', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('fb2c5c965d1b41dd93e7e5e034742752', '7e64ee3b0ba449ee8a32d3197379542b', '1', '0', NULL, '2023-08-09 08:46:49',
        NULL);
INSERT INTO `permission_role`
VALUES ('fc1fe0a8194645eda30a8cd8124f6501', '91d1e1e665fd4d7ba6fdc3178390e65d', '5b319d59f1624b8cb16665a2798b7c44', '0',
        NULL, '2023-08-07 18:40:09', NULL);
INSERT INTO `permission_role`
VALUES ('ff6d9d8a092f4a22987166bba625d912', '40f72874f100407d85cbe4482abe2bb5', '0', '0', NULL, '2023-09-09 19:52:08',
        NULL);

-- ----------------------------
-- Table structure for permission_router
-- ----------------------------
DROP TABLE IF EXISTS `permission_router`;
CREATE TABLE `permission_router`
(
    `id`            varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `permission_id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限ID',
    `router_id`     varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路由ID',
    `create_user`   varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user`   varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改用户',
    `create_time`   datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time`   datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限-路由绑定表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission_router
-- ----------------------------
INSERT INTO `permission_router`
VALUES ('00a293f7928f4d21a2ba160f28c6e242', '91d1e1e665fd4d7ba6fdc3178390e65d', 'de9be7dcf36d4287a989a47de9c58457', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('04e9344a921f40e88e2edead750d4b80', '1370ab9948d64c86b8f454dbdf6148bb', '09544f753e33449fb482f462592a5240', '0',
        NULL, '2023-08-02 08:20:33', NULL);
INSERT INTO `permission_router`
VALUES ('0a0b0bc0545445a1b5ba3f38e2ad4e3c', 'ddb4596c59fb4f63a2a6c5fee80dd7e5', 'e787354f9cbb4fd79f4088138115685d', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('0a498268b5604d6b9cbde7d7d6c20904', 'a68925f349a8462ebc597b9d9533791f', '41b36e7dc2bd408f8c92bfa5d4dac25b', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('0dc4786a96514168942c376283b05fdb', '504d5f46f7984f20a749a18bc45fe87d', '89d55789ce7c48e2abf78f86016b56fa', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('0fe4131427e4454ab81cc0b4a9fabfff', '2e57771d96b6464f9e0ba22db63796e4', '916b4451f3e44f57aa64abcd7e8ace86', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('1bd33634f2814a2ca2485cb85b31374d', '1dc27a1fec2940f58ca8791dcff1d311', 'b2c4cd8070a14284aa18cbe16962d0bc', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('2250271587e0425889582fda2684fd17', '09345f3798a44c69bf41e92fba428e8c', '65fabcf2df2445fa9cce2743e7d87157', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('228e537eab2e4a0b91964e3371787e3c', '925bd3b2a75a4e4cbce2f91dbe722291', 'd28d759796cd4229aacdda66d66ea7f7', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('287b5d56b93b47f29ffe9f5c7ae3a4e6', '0cfc6494d2284252a89fd97f443abc29', '3071172895bc40d38611830b8edd1d0a', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('2af94235bbdf4bd3b175dfbe9a000fd6', '31a54479774c4065938411f81d668c32', '9ea26b0f3c7146948baa42f12686538b', '0',
        NULL, '2023-08-09 09:51:55', NULL);
INSERT INTO `permission_router`
VALUES ('31389f7eb9ba4302bde9fa68379966bc', 'e3338c77902e4a69963db05bf28ace27', 'aed943b5595349e7b8f4dcba012c7b35', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('33c0c7413d6e4afebefe47a732fb8299', '2c00b44556a64df1aee4220c6820e53e', '998889d0c2d242baa98a6120b2904602', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('36e9514cef5a4710b1dab8c93c3a99b5', '11412c211a6042149610d2da6931ac57', '0d2a241cf1504428a98a54423ea1b04d', '0',
        NULL, '2023-08-07 18:30:03', NULL);
INSERT INTO `permission_router`
VALUES ('3bfee35e643841c8841e2d6afe8dfd87', '288caddc8fe64a27a71d32d6091deaae', '48367bfd9555472fb375d8993bf9bdda', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('3c779ecc4b944cf3aab43fa425ba5940', '570d819be4fb45a69430fc2eb6e02f84', '6a00c1e03788469c9337533fa817038e', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('3d132cac60fd485ca91726b6ce489e20', '40a16c64701849009852f96cde5f3a3c', 'd2c4ace8b498477fa400f30334e1b057', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('42e186ad665e4900926da74a8625ea62', '09345f3798a44c69bf41e92fba428e8c', '0b8755a703e64dc183077a4cd9dde5d7', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('45979a3168324ef884d1182b571a7c36', '40a16c64701849009852f96cde5f3a3c', '7f5ce299838f4725a2b3af1b6d712559', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('51d3321ac8234a9db959cc5d38835c04', 'c1be6dc2b1854fc488682e00bf5e3508', '58894351787343eeaeebb7a3442d6671', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('5b05502d598840fe9832aea6243c3ec6', '376612e5d41347d6871d7e2216cc678b', '84c951559a0442938bb16438f92d1799', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('5ec1fe7145294b4c9d177124ef8ac74b', '40a16c64701849009852f96cde5f3a3c', '9a55a82134754efcbcf2eab36a43e14f', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('6483d797bdf1431397f74909ff8e40d3', 'cbfe251729734bcb9f9486cd736f66b7', 'fb5a731347354e7391597639b693c693', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('6b1e8c2a4eae41faa546ed7493628620', '7854230823434412be24b33b0d40844f', 'e787354f9cbb4fd79f4088138115685d', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('6bf7121ea2ca41cba6858293936fa8d1', '0cfc6494d2284252a89fd97f443abc29', '7dc7ea4946f64187b299e4b64678bfa1', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('6e9246e31a42473d8db969482ac43c18', '925bd3b2a75a4e4cbce2f91dbe722291', '7920bde0c63f43f3a550f10b2173452f', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('774404bb416b41d6bd802ecae7ddc681', '40f72874f100407d85cbe4482abe2bb5', 'a217b2c6fc97419daeabb16b6b33568f', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('78ce74ab54b74509a9580251f5d32643', '24cbeb47067c4133b0b079a9dc8712c1', '6dd622f76d6b43da87d7ece1ac677f59', '0',
        NULL, '2023-08-09 09:51:55', NULL);
INSERT INTO `permission_router`
VALUES ('7946a95a8f2b4847bf864ef415920f6a', '27483ef7113949d983de2e2823984242', '1092ceafbd7d40a8ab1c1a50bd2c9ae4', '0',
        NULL, '2023-08-09 10:09:48', NULL);
INSERT INTO `permission_router`
VALUES ('7bcafabb5d264066a8f72c9bc9332cc5', '97bfede98e794599a45b37bad4fc8112', 'da11a3221ee7434f8501c2215f2d0143', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('7f923371fa364faa855bd6fa36f3f636', 'd57428ef9c294cc4a98c6fcbff3fef9c', 'e8237c6a8d5740a2b2748158b065eb85', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('8a4e422df1404aa9a4f1bf946856b9a9', '51a998a73ab04996b1992e11e1f7f1a1', '78237bb89a7f4f5cb6787e7a8d5305dd', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('8b56e897999e4fd7aa10e8fd207ebe83', 'ba39d9fc58124e2588b5bcfd502dd0c8', '9ba49971cbd14de9a99cf6e446e15193', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('8b9d3db5b4854158a97b5e0e00170391', '31a54479774c4065938411f81d668c32', '6f3f05edf289417cae1056300ff0294e', '0',
        NULL, '2023-08-07 10:03:57', NULL);
INSERT INTO `permission_router`
VALUES ('8d0038443d064711b23f2d9cb6bdd64c', '83d6633cfd90426b91898a457e831b9c', 'cfd0d97a9b2a4bd1945cc3bd42ac14fc', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('8f4d599ae9cc4394a66eff74c705e638', '0cfc6494d2284252a89fd97f443abc29', '56c224d3d14944279264143d92549fc9', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('9306e916015e433f8b808bd1921646d6', '40f72874f100407d85cbe4482abe2bb5', '0e709563fe2b433fa63fad63207149ce', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('93d41487c50547848107a3e2e0e91f06', '125235b9c2fa400983a2adf55fc0218f', '9e0790f2efc440cfaf73436c698e9a3d', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('9506f3c64d894865a60a3498e01a41f8', '807773f3c4e84e7289f73986d0bbbac2', '79f82fa5e5e14586b2400440ce5832b5', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('99c81e3dc0d74b36b8bd920ed005ec5b', '91d1e1e665fd4d7ba6fdc3178390e65d', 'cd3846346c6740c999fffebd28a607ed', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('a3009bb661164a4697b72aee58cef3ce', '27483ef7113949d983de2e2823984242', '102bdad4b5be46129c88fd6f7bddff34', '0',
        NULL, '2023-08-09 10:09:48', NULL);
INSERT INTO `permission_router`
VALUES ('b7c2484ddc614568af8ed64bc06917e4', '925bd3b2a75a4e4cbce2f91dbe722291', 'bb7af490737243b2b14b4775b5c7d0da', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('b85d545a1e0b44339d3426b6e7530ec1', '33272c92c164408e8ca33e6cdd2b72c9', 'e1166e54b0324ac39f9c1f1c44ce0a9d', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('bc16ab66a931441da01393bede0cca70', '4fc617c8d71a443da50b3603bc27414b', '59288881aead4d049b7b07618a666c31', '0',
        NULL, '2023-08-02 09:55:13', NULL);
INSERT INTO `permission_router`
VALUES ('bc9bfa4911a74de983f012df3511919b', 'f147715d5e334efc97caf4e49bd28228', '33bc7f8bb8384dce82c3b4bc811da0a9', '0',
        NULL, '2023-08-02 09:55:13', NULL);
INSERT INTO `permission_router`
VALUES ('be5081d3a4d549f69383b6d9d2413d1e', 'e74cf92a484e42d4856cc541a232a588', '986c1b55ef72426d91ed5d0520b484f0', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('bf272852ef254452be7227cba19a2f66', '0b8aa4205d1b48999eda0a87c69213c0', 'a6c5e3991e2a46489142a3fa7ecd3ab3', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('c473b6bfb17c467c8f206ceda0546b73', '4fc617c8d71a443da50b3603bc27414b', '3b258069f92f4319bc7bad9ab44fe9f8', '0',
        NULL, '2023-08-02 10:49:16', NULL);
INSERT INTO `permission_router`
VALUES ('c7c587edd35146f8af0088e5365321a5', '415d2c59d61449498505ac3b3270d940', '039fbed6e73c43a6bcab32ba327e0855', '0',
        NULL, '2023-08-02 08:20:33', NULL);
INSERT INTO `permission_router`
VALUES ('ca387dd2149c46658c3e01135ddda4f5', '548f84361dff4ed3b71fd2898a535de5', '421d47e35cae4fc69c598d42f0ef813d', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('cbcac044838246e28abb6850101aeeac', 'c1be6dc2b1854fc488682e00bf5e3508', '4cd499bfd5e74452856eab5472feb985', '0',
        NULL, '2023-09-06 22:10:33', NULL);
INSERT INTO `permission_router`
VALUES ('d2fb18c1d8544026999e14f56a24b02f', '1e1426fad12b4e09bc58d7fe2e5f4e19', '0659bef327b54372a3ce35d74376b68a', '0',
        NULL, '2023-08-02 08:20:33', NULL);
INSERT INTO `permission_router`
VALUES ('e1700ca74dcc4636a759a9206480abd4', '4fc617c8d71a443da50b3603bc27414b', 'e545fef7049743e6b2956e64eef28bba', '0',
        NULL, '2023-08-10 17:59:21', NULL);
INSERT INTO `permission_router`
VALUES ('e264a1fffaf84459ae6a7228ee2e434f', 'cbfe251729734bcb9f9486cd736f66b7', 'c2893ff29ca04a53b99ec7bd23915939', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('e48d8c09e44540fdaefa23ad8994aa36', 'e74cf92a484e42d4856cc541a232a588', 'c9f98155146f4650b6003657cbb2ff43', '0',
        NULL, '2023-08-02 12:24:49', NULL);
INSERT INTO `permission_router`
VALUES ('e57eb50a5c47450f9354adde16a555f2', '7854230823434412be24b33b0d40844f', '0ae33c6a65174f4ea20c72e13003770b', '0',
        NULL, '2023-08-02 08:20:33', NULL);
INSERT INTO `permission_router`
VALUES ('e6fc4adc17314d8ba52cd021f8259e8d', 'd9b270a2d44344c4b4fc53511a212805', 'a3373a376a7d492daa97ebe020bb4c7d', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('e9debb8fa73d4e87beac42a61a6da89b', '125235b9c2fa400983a2adf55fc0218f', 'e26c18ae57b64ef6a16f75ff5aa02c22', '0',
        NULL, '2023-08-02 11:09:10', NULL);
INSERT INTO `permission_router`
VALUES ('e9f88419d02449318d4bd34e001c00f7', '2e57771d96b6464f9e0ba22db63796e4', 'c9f98155146f4650b6003657cbb2ff43', '0',
        NULL, '2023-08-02 12:24:49', NULL);
INSERT INTO `permission_router`
VALUES ('ebd825e3c77b44d5adf138179e39d430', '7e916921afe946ab84a62c3fb8615689', '48b839edba7f4abbbc384c9f658d24d1', '0',
        NULL, '2023-08-02 09:55:13', NULL);
INSERT INTO `permission_router`
VALUES ('ee81515bca1047b8967eae3e73f138fe', '376612e5d41347d6871d7e2216cc678b', '9561b3068f3b4fceb70329d1d52b7a77', '0',
        NULL, '2023-08-02 12:33:18', NULL);
INSERT INTO `permission_router`
VALUES ('f40b98792a4a465a817eebc4bd3a9ba9', '19e1065c601d402f979eeefae3ccf402', 'f7ae8ceae88e4351ab051ca0174567c2', '0',
        NULL, '2023-08-02 11:09:10', NULL);

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `type`        int                                                          NOT NULL DEFAULT 1 COMMENT '类型(0客观题,1主观题)',
    `subject_id`  varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '科目',
    `gradle_id`   varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '年级',
    `file_path`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '存放路径',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '题库'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem`
VALUES ('5bd5685320b04960b53813dab375993a', 0, '701ddb01101147219a94d1181b660d5a', 'ca72d06f3030497b835c196e957a46f9',
        'D:/Test/problem_5bd5685320b04960b53813dab375993a.txt', '0', NULL, '2023-09-11 14:43:20', NULL);
INSERT INTO `problem`
VALUES ('85b41aadb73240749dbff862ee260db5', 0, '701ddb01101147219a94d1181b660d5a', 'ca72d06f3030497b835c196e957a46f9',
        'D:/Test/problem_85b41aadb73240749dbff862ee260db5.txt', '0', NULL, '2023-09-11 14:43:20', NULL);
INSERT INTO `problem`
VALUES ('9fd770e027eb4b37afd2eaf7009e810b', 0, 'f2b159d70546415b8befdc9d2a5126ae', '0',
        'D:/Test/problem_9fd770e027eb4b37afd2eaf7009e810b.txt', '0', '0', '2023-08-09 18:13:36', '2023-09-10 10:54:58');
INSERT INTO `problem`
VALUES ('bbd4a93b5a504888801a446c7192bba6', 1, 'ca8732662ed5418abe256a188ce7fe8b', 'ca72d06f3030497b835c196e957a46f9',
        'D:/Test/problem_bbd4a93b5a504888801a446c7192bba6.txt', '0', '0', '2023-09-11 14:43:20', '2023-09-12 20:02:08');
INSERT INTO `problem`
VALUES ('fec2d685ae9f495a9be9ac247c4f1db2', 0, 'ca8732662ed5418abe256a188ce7fe8b', 'ca72d06f3030497b835c196e957a46f9',
        'D:/Test/problem_fec2d685ae9f495a9be9ac247c4f1db2.txt', '0', '0', '2023-08-05 10:23:24', '2023-08-11 14:39:47');

-- ----------------------------
-- Table structure for problem_exam
-- ----------------------------
DROP TABLE IF EXISTS `problem_exam`;
CREATE TABLE `problem_exam`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `exam_id`     varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '试卷ID',
    `problem_id`  varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题库ID',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '题库-试卷绑定表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_exam
-- ----------------------------
INSERT INTO `problem_exam`
VALUES ('03359491bdb1425dbd4698f374f5799a', '344b72bded14456ea2cc0ba34c87c0a9', 'fec2d685ae9f495a9be9ac247c4f1db2', '0',
        NULL, '2023-10-09 19:19:53', NULL);
INSERT INTO `problem_exam`
VALUES ('1ce1f22bd2f94e99b430ae84700b08e0', '344b72bded14456ea2cc0ba34c87c0a9', '9fd770e027eb4b37afd2eaf7009e810b', '0',
        NULL, '2023-10-09 19:19:53', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
    `is_default`  int                                                          NULL DEFAULT 1 COMMENT '是否默认(0默认,1自定义)',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role`
VALUES ('0', '普通用户', 0, 'localUser', '0', '2023-07-21 17:55:48', '2023-09-09 19:52:08');
INSERT INTO `role`
VALUES ('1', '超级管理员', 0, '0', '0', '2023-08-07 19:05:51', '2023-08-09 08:46:49');
INSERT INTO `role`
VALUES ('5b319d59f1624b8cb16665a2798b7c44', '教师', 1, '0', '0', '2023-08-03 11:13:54', '2023-08-07 18:40:09');

-- ----------------------------
-- Table structure for router
-- ----------------------------
DROP TABLE IF EXISTS `router`;
CREATE TABLE `router`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路由名',
    `value`       varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '路由值',
    `method`      varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL     DEFAULT 'GET' COMMENT '请求方式',
    `type`        int                                                          NOT NULL COMMENT '路由类型(0后端路由,1前端路由)',
    `is_default`  int                                                          NOT NULL DEFAULT 1 COMMENT '是否默认',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '路由表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of router
-- ----------------------------
INSERT INTO `router`
VALUES ('039fbed6e73c43a6bcab32ba327e0855', '权限新增', '/per', 'POST', 0, 0, '0', '0', '2023-07-30 16:48:04',
        '2023-08-02 08:20:33');
INSERT INTO `router`
VALUES ('0659bef327b54372a3ce35d74376b68a', '权限修改', '/per', 'PUT', 0, 0, '0', '0', '2023-07-30 16:48:04',
        '2023-08-02 08:20:33');
INSERT INTO `router`
VALUES ('09544f753e33449fb482f462592a5240', '科目修改', '/subject', 'PUT', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 08:20:33');
INSERT INTO `router`
VALUES ('0ae33c6a65174f4ea20c72e13003770b', '用户上传头像', '/user/upload', 'POST', 0, 0, '0', '0',
        '2023-07-30 16:11:59', '2023-08-02 08:20:33');
INSERT INTO `router`
VALUES ('0b8755a703e64dc183077a4cd9dde5d7', '根据试卷解绑历史试卷记录', '/ue/pro', 'DELETE', 0, 1, '0', '0',
        '2023-09-06 21:48:58', '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('0d2a241cf1504428a98a54423ea1b04d', '权限删除', '/per', 'DELETE', 0, 0, '0', '0', '2023-07-30 16:48:04',
        '2023-08-07 18:30:03');
INSERT INTO `router`
VALUES ('0e709563fe2b433fa63fad63207149ce', '查询所有历史做题记录', '/up', 'GET', 0, 1, '0', '0', '2023-09-06 22:10:33',
        '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('102bdad4b5be46129c88fd6f7bddff34', '年级修改', '/gradle', 'PUT', 0, 1, '0', '0', '2023-08-06 14:58:25',
        '2023-08-09 10:09:48');
INSERT INTO `router`
VALUES ('1092ceafbd7d40a8ab1c1a50bd2c9ae4', '年级删除', '/gradle', 'DELETE', 0, 1, '0', '0', '2023-08-06 14:58:25',
        '2023-08-09 10:09:48');
INSERT INTO `router`
VALUES ('3071172895bc40d38611830b8edd1d0a', '查询所有历史试卷记录', '/ue', 'GET', 0, 1, '0', '0', '2023-09-06 21:43:47',
        '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('33bc7f8bb8384dce82c3b4bc811da0a9', '路由修改', '/router', 'PUT', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 09:55:13');
INSERT INTO `router`
VALUES ('3b258069f92f4319bc7bad9ab44fe9f8', '分类查询试卷', '/exam/select', 'GET', 0, 0, '0', '0',
        '2023-07-30 16:52:48', '2023-08-02 10:49:16');
INSERT INTO `router`
VALUES ('41b36e7dc2bd408f8c92bfa5d4dac25b', '修改历史做题记录', '/up', 'PUT', 0, 1, '0', '0', '2023-09-06 22:10:33',
        '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('421d47e35cae4fc69c598d42f0ef813d', '修改历史试卷记录', '/ue', 'PUT', 0, 1, '0', '0', '2023-09-06 21:43:47',
        '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('48367bfd9555472fb375d8993bf9bdda', '解绑历史做题记录', '/up', 'DELETE', 0, 1, '0', '0', '2023-09-06 22:10:33',
        '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('48b839edba7f4abbbc384c9f658d24d1', '角色删除', '/role', 'DELETE', 0, 0, '0', '0', '2023-07-30 16:27:18',
        '2023-08-02 09:55:13');
INSERT INTO `router`
VALUES ('4cd499bfd5e74452856eab5472feb985', '分类查询历史做题记录', '/up/select', 'GET', 0, 1, '0', '0',
        '2023-09-06 22:10:33', '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('56c224d3d14944279264143d92549fc9', '分类查询历史试卷记录', '/ue/select', 'GET', 0, 1, '0', '0',
        '2023-09-06 21:48:58', '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('58894351787343eeaeebb7a3442d6671', '查询单个历史做题记录', '/up/one', 'GET', 0, 1, '0', '0',
        '2023-09-06 22:10:33', '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('59288881aead4d049b7b07618a666c31', '查询所有试卷', '/exam', 'GET', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 09:55:13');
INSERT INTO `router`
VALUES ('65fabcf2df2445fa9cce2743e7d87157', '批量解绑历史试卷记录', '/ue', 'DELETE', 0, 1, '0', '0',
        '2023-09-06 21:43:47', '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('6a00c1e03788469c9337533fa817038e', '试卷删除', '/exam', 'DELETE', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('6dd622f76d6b43da87d7ece1ac677f59', '年级添加', '/gradle', 'POST', 0, 1, '0', '0', '2023-08-06 14:58:25',
        '2023-08-09 09:51:55');
INSERT INTO `router`
VALUES ('6f3f05edf289417cae1056300ff0294e', '查询所有年级', '/gradle', 'GET', 0, 1, '0', '0', '2023-08-07 09:59:32',
        '2023-08-07 10:03:57');
INSERT INTO `router`
VALUES ('78237bb89a7f4f5cb6787e7a8d5305dd', '角色新增', '/role', 'POST', 0, 0, '0', '0', '2023-07-30 16:27:18',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('7920bde0c63f43f3a550f10b2173452f', '查询一条角色', '/role/one', 'GET', 0, 0, '0', '0', '2023-07-30 16:48:04',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('79f82fa5e5e14586b2400440ce5832b5', '路由删除', '/router', 'DELETE', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('7dc7ea4946f64187b299e4b64678bfa1', '查询单个历史试卷记录', '/ue/one', 'GET', 0, 1, '0', '0',
        '2023-09-06 21:48:58', '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('7f5ce299838f4725a2b3af1b6d712559', '查询所有路由', '/router', 'GET', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('84c951559a0442938bb16438f92d1799', '查询所有反馈', '/feed', 'GET', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('89d55789ce7c48e2abf78f86016b56fa', '试卷新增', '/exam', 'POST', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('916b4451f3e44f57aa64abcd7e8ace86', '用户查看头像', '/user/avatar', 'GET', 0, 0, '0', '0',
        '2023-07-30 16:48:04', '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('9561b3068f3b4fceb70329d1d52b7a77', '分类查询反馈', '/feed/select', 'GET', 0, 0, '0', '0',
        '2023-07-30 16:52:48', '2023-08-02 12:33:18');
INSERT INTO `router`
VALUES ('986c1b55ef72426d91ed5d0520b484f0', '查询所有用户', '/user', 'GET', 0, 0, '0', '0', '2023-07-30 15:56:47',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('998889d0c2d242baa98a6120b2904602', '路由新增', '/router', 'POST', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('9a55a82134754efcbcf2eab36a43e14f', '分类查询路由', '/router/select', 'GET', 0, 0, '0', '0',
        '2023-07-30 16:52:48', '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('9ba49971cbd14de9a99cf6e446e15193', '科目删除', '/subject', 'DELETE', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('9e0790f2efc440cfaf73436c698e9a3d', '查询所有权限', '/per', 'GET', 0, 0, '0', '0', '2023-07-30 16:48:04',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('9ea26b0f3c7146948baa42f12686538b', '分类查询年级', '/gradle/select', 'GET', 0, 1, '0', '0',
        '2023-08-06 14:58:25', '2023-08-09 09:51:55');
INSERT INTO `router`
VALUES ('a217b2c6fc97419daeabb16b6b33568f', '绑定历史做题记录', '/up', 'POST', 0, 1, '0', '0', '2023-09-06 22:10:33',
        '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('a3373a376a7d492daa97ebe020bb4c7d', '反馈删除', '/feed', 'DELETE', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('a6c5e3991e2a46489142a3fa7ecd3ab3', '绑定历史试卷记录', '/ue', 'POST', 0, 1, '0', '0', '2023-09-06 21:43:47',
        '2023-09-06 22:10:33');
INSERT INTO `router`
VALUES ('aed943b5595349e7b8f4dcba012c7b35', '试卷修改', '/exam', 'PUT', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('b2c4cd8070a14284aa18cbe16962d0bc', '题库删除', '/pro', 'DELETE', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('b67cbc917fb7411589b4d325d3440769', '用户删除', '/user', 'DELETE', 0, 0, '0', NULL, '2023-07-30 16:11:59',
        NULL);
INSERT INTO `router`
VALUES ('bb7af490737243b2b14b4775b5c7d0da', '分类查询角色', '/role/select', 'GET', 0, 0, '0', '0',
        '2023-07-30 16:11:59', '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('c2893ff29ca04a53b99ec7bd23915939', '分类查询题库', '/pro/select', 'GET', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('c9f98155146f4650b6003657cbb2ff43', '分类查询用户', '/user/select', 'GET', 0, 0, '0', '0',
        '2023-07-30 16:11:59', '2023-08-02 12:24:49');
INSERT INTO `router`
VALUES ('cd3846346c6740c999fffebd28a607ed', '分类查询科目', '/subject/select', 'GET', 0, 0, '0', '0',
        '2023-07-30 16:52:48', '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('cfd0d97a9b2a4bd1945cc3bd42ac14fc', '题库新增', '/pro', 'POST', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('d28d759796cd4229aacdda66d66ea7f7', '查询所有角色', '/role', 'GET', 0, 0, '0', '0', '2023-07-30 16:27:18',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('d2c4ace8b498477fa400f30334e1b057', '查询一条路由', '/router/one', 'GET', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('da11a3221ee7434f8501c2215f2d0143', '科目新增', '/subject', 'POST', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('de9be7dcf36d4287a989a47de9c58457', '查询所有科目', '/subject', 'GET', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('e1166e54b0324ac39f9c1f1c44ce0a9d', '反馈新增', '/feed', 'POST', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('e26c18ae57b64ef6a16f75ff5aa02c22', '分类查询权限', '/per/select', 'GET', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('e545fef7049743e6b2956e64eef28bba', '查询一条试卷信息', '/exam/one', 'GET', 0, 1, '0', '0',
        '2023-08-10 17:59:21', '2023-08-10 17:59:21');
INSERT INTO `router`
VALUES ('e787354f9cbb4fd79f4088138115685d', '用户修改', '/user', 'PUT', 0, 0, '0', '0', '2023-07-30 16:11:59',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('e8237c6a8d5740a2b2748158b065eb85', '角色修改', '/role', 'PUT', 0, 0, '0', '0', '2023-07-30 16:27:18',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('f7ae8ceae88e4351ab051ca0174567c2', '题库修改', '/pro', 'PUT', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');
INSERT INTO `router`
VALUES ('fb5a731347354e7391597639b693c693', '查询所有题库', '/pro', 'GET', 0, 0, '0', '0', '2023-07-30 16:52:48',
        '2023-08-02 11:09:10');

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '科目名',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '科目表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of subject
-- ----------------------------
INSERT INTO `subject`
VALUES ('0', '语文', '0', NULL, '2023-07-30 07:35:09', NULL);
INSERT INTO `subject`
VALUES ('4e0cc8b3617d45b185dd9275c5cb7bb2', '生物', '0', NULL, '2023-08-03 14:33:31', NULL);
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
VALUES ('f2b159d70546415b8befdc9d2a5126ae', '物理', '0', NULL, '2023-07-30 07:35:09', NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
    `password`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
    `nick_name`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '昵称',
    `avatar`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '头像',
    `sex`         int                                                          NOT NULL DEFAULT 1 COMMENT '性别(0女,1男)',
    `email`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '邮箱',
    `phone`       varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '电话',
    `role_id`     varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '用户角色',
    `status`      int                                                          NOT NULL DEFAULT 0 COMMENT '状态(0正常,1失效)',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL     DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name` (`name` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES ('0', 'root', '$2a$10$HEi62MXckXC75lDbBx2T0uF6h5H6.gTUg9a9b4UcNfDFmHoEDqLfi', '超级管理员', '0.png', 0,
        '2516649281@qq.com', '17571038682', '1', 0, 'localhost', NULL, '2023-07-22 13:24:02', NULL);
INSERT INTO `user`
VALUES ('6611ac92527141f0a58487173fdf63bd', 'user1', '$2a$10$NJnNA.vYlZj9ZuYZJ496dOJu5w0SjFWZ7Zxqzl1t1CYv0W440/nU2',
        '测试1', '6611ac92527141f0a58487173fdf63bd.png', 1, '2546378951@qq.com', '12547863594',
        '5b319d59f1624b8cb16665a2798b7c44', 0, 'localUser', '0', '2023-07-22 13:24:02', '2023-10-09 19:19:53');
INSERT INTO `user`
VALUES ('7f76d86b777942649136f062e774d3f9', 'root1', '$2a$10$EWGButendp1R6sDd0Zwc4u/0aaoTFv7t.NxQhxdvMKxsOF.TsoraG',
        '超级管理员2', '0', 1, '2548936475@qq.com', '15784639458', '1', 0, 'localUser', '0', '2023-07-29 11:26:37',
        '2023-10-09 19:19:53');
INSERT INTO `user`
VALUES ('d0651a7d27834111845b3865b4c932a5', 'user2', '$2a$10$j9mwQeKv3g3TgyMsErgV6OYFDrIh1MhlMMxTvta//DndekMfOML6G',
        '测试2', 'd0651a7d27834111845b3865b4c932a5.jpg', 0, '2398756432@qq.com', '15479643587', '0', 0, 'localUser',
        'd0651a7d27834111845b3865b4c932a5', '2023-07-27 16:42:13', '2023-10-09 19:19:53');

-- ----------------------------
-- Table structure for user_exam
-- ----------------------------
DROP TABLE IF EXISTS `user_exam`;
CREATE TABLE `user_exam`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `user_id`     varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
    `exam_id`     varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '试卷ID',
    `score`       float                                                        NOT NULL COMMENT '得分',
    `time`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '时间',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户-试卷关系表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_exam
-- ----------------------------

-- ----------------------------
-- Table structure for user_problem
-- ----------------------------
DROP TABLE IF EXISTS `user_problem`;
CREATE TABLE `user_problem`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `user_id`     varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
    `problem_id`  varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题目ID',
    `status`      int                                                          NOT NULL COMMENT '状态(0正确,1错误)',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户-题目关系表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_problem
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
