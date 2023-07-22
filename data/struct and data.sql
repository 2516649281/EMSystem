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

 Date: 22/07/2023 13:56:16
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

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `file_path`   varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '文件路径',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '反馈表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feedback
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名',
    `sign`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标识符',
    `create_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改用户',
    `create_time` datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time` datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限列表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission`
VALUES ('11412c211a6042149610d2da6931ac57', '权限删除', 'sys:per:delete', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('125235b9c2fa400983a2adf55fc0218f', '权限查询', 'sys:per:select', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('1370ab9948d64c86b8f454dbdf6148bb', '科目修改', 'op:sub:update', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('19e1065c601d402f979eeefae3ccf402', '题目修改', 'op:pro:update', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('1dc27a1fec2940f58ca8791dcff1d311', '题目删除', 'op:pro:delete', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('1e1426fad12b4e09bc58d7fe2e5f4e19', '权限修改', 'sys:per:update', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('2e57771d96b6464f9e0ba22db63796e4', '个人信息查询', 'user:user:select', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('33272c92c164408e8ca33e6cdd2b72c9', '反馈信息新增', 'user:feed:insert', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('376612e5d41347d6871d7e2216cc678b', '反馈信息查看', 'sys:feed:select', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('415d2c59d61449498505ac3b3270d940', '权限新增', 'sys:per:insert', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('48960c4727a14446802ded153dde30c4', '科目删除', 'op:sub:delete', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('4fc617c8d71a443da50b3603bc27414b', '试卷查询', 'op:exam:select', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('504d5f46f7984f20a749a18bc45fe87d', '试卷新增', 'op:exam:insert', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('51a998a73ab04996b1992e11e1f7f1a1', '角色新增', 'sys:role:insert', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('570d819be4fb45a69430fc2eb6e02f84', '试卷删除', 'op:exam:delete', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('7854230823434412be24b33b0d40844f', '个人信息修改', 'user:user:update', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 13:42:24', NULL);
INSERT INTO `permission`
VALUES ('7e64ee3b0ba449ee8a32d3197379542b', '个人信息注册', 'user:user:insert', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('7e916921afe946ab84a62c3fb8615689', '角色删除', 'sys:role:delete', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('83387680ef7a44ffb96dc4bfb9dd62c2', '用户删除', 'sys:user:delete', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('83d6633cfd90426b91898a457e831b9c', '题目新增', 'op:pro:insert', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('91d1e1e665fd4d7ba6fdc3178390e65d', '科目查询', 'op:sub:select', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('925bd3b2a75a4e4cbce2f91dbe722291', '角色查询', 'sys:role:select', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('97bfede98e794599a45b37bad4fc8112', '科目新增', 'op:sub:insert', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('9886ea88ed5f4d95b16c6edf9c88f037', '个人信息注销', 'user:user:delete', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('98b9b022a6b64ea38b2ae0a028ba5347', '试卷/题目信息查询', 'user:pro:select', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('cbfe251729734bcb9f9486cd736f66b7', '题目查询', 'op:pro:select', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('d57428ef9c294cc4a98c6fcbff3fef9c', '角色修改', 'sys:role:update', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('d9b270a2d44344c4b4fc53511a212805', '反馈信息删除', 'sys:feed:delete', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('ddb4596c59fb4f63a2a6c5fee80dd7e5', '用户修改', 'sys:user:update', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('e3338c77902e4a69963db05bf28ace27', '试卷修改', 'op:exam:update', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:25:36', NULL);
INSERT INTO `permission`
VALUES ('e74cf92a484e42d4856cc541a232a588', '用户查询', 'sys:user:select', '785ae2df8ce14b638770a781bcb2c313', NULL,
        '2023-07-22 11:22:31', NULL);

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
VALUES ('029b660a382c494eaa4ba4550f49c1b5', '4fc617c8d71a443da50b3603bc27414b', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('08ddbf32b7ba4c9b97b552bc0d6c7c3f', '125235b9c2fa400983a2adf55fc0218f', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('0d0a488af96a46ef83f0dc3b7b1938f3', 'e3338c77902e4a69963db05bf28ace27', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('1519d3bb60884584baeed135bd2c12b2', '11412c211a6042149610d2da6931ac57', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('219782eacc004460bd5626c1d1e90dfe', '83d6633cfd90426b91898a457e831b9c', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('220a126a8e504003b61685bd3269cc0a', '9886ea88ed5f4d95b16c6edf9c88f037', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('30045156bb3141988729367d90cf513c', '83387680ef7a44ffb96dc4bfb9dd62c2', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('42e0c8d9ce22456294ee861ce864dc20', 'd57428ef9c294cc4a98c6fcbff3fef9c', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('461e03ef65d2495ab0f1075495b2b454', '7e916921afe946ab84a62c3fb8615689', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('4a71d1060ea54c1f91374ac7b41308fe', '415d2c59d61449498505ac3b3270d940', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('4fef43addf59474bbfee2c21f77cb47c', '91d1e1e665fd4d7ba6fdc3178390e65d', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('50a6f876a87b47e5b342e7a0b6524a23', '925bd3b2a75a4e4cbce2f91dbe722291', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('54db3c9b6e074ea48027ea21a4fa90ac', '1e1426fad12b4e09bc58d7fe2e5f4e19', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('57a7820fdb8243cb8e89a8e876c1ea27', '2e57771d96b6464f9e0ba22db63796e4', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('5b303509fc184e04bdb8c48e4785f4e4', '7e64ee3b0ba449ee8a32d3197379542b', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('5f0e0aadb66e4c7891399a34bc8f8a15', 'd9b270a2d44344c4b4fc53511a212805', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('5fad3afc985a457d88d6cd6bac1e2959', '504d5f46f7984f20a749a18bc45fe87d', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('6a84fa1fd3a1425589a33ab7e65fdea1', '51a998a73ab04996b1992e11e1f7f1a1', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('711e0c61a8bb45e7a08f96fd69d7b16a', '97bfede98e794599a45b37bad4fc8112', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('74b40179561a47bcaee6ddad70fda943', '33272c92c164408e8ca33e6cdd2b72c9', '0', '785ae2df8ce14b638770a781bcb2c313',
        NULL, '2023-07-22 13:30:49', NULL);
INSERT INTO `permission_role`
VALUES ('7fb5123d451048b99186576b549eb40a', '1dc27a1fec2940f58ca8791dcff1d311', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('84844b6b270246e48a87fc2381d46e54', '7e64ee3b0ba449ee8a32d3197379542b', '0', '785ae2df8ce14b638770a781bcb2c313',
        NULL, '2023-07-22 13:30:49', NULL);
INSERT INTO `permission_role`
VALUES ('885d580c154747f9b36fb64c64012053', '2e57771d96b6464f9e0ba22db63796e4', '0', '785ae2df8ce14b638770a781bcb2c313',
        NULL, '2023-07-22 13:30:49', NULL);
INSERT INTO `permission_role`
VALUES ('8a8d3d83acbc445395e720cb382413ee', '7854230823434412be24b33b0d40844f', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 13:42:24', NULL);
INSERT INTO `permission_role`
VALUES ('969101db2e614ea0a6790405b64071c3', '33272c92c164408e8ca33e6cdd2b72c9', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('a4665158952845dfa1eb56841ed48a4a', '570d819be4fb45a69430fc2eb6e02f84', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('a6d778ebdaa24aa696c460e16506830d', 'ddb4596c59fb4f63a2a6c5fee80dd7e5', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('a79b4eca7f9742f9b6050c2279cabe8d', '98b9b022a6b64ea38b2ae0a028ba5347', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('ac1408f454ba43bcb69c39bbbab303cc', 'cbfe251729734bcb9f9486cd736f66b7', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('b828eda1e516412cb36de9266e235fb9', '9886ea88ed5f4d95b16c6edf9c88f037', '0', '785ae2df8ce14b638770a781bcb2c313',
        NULL, '2023-07-22 13:30:49', NULL);
INSERT INTO `permission_role`
VALUES ('b86c79426b7d45a2a91514c4de30af9b', '1370ab9948d64c86b8f454dbdf6148bb', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('b9fcc1d46c7f48a4b1195c1c2302ea5a', '376612e5d41347d6871d7e2216cc678b', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('c841019b56bb4a6481c30b6e2da9414f', '7854230823434412be24b33b0d40844f', '0', '785ae2df8ce14b638770a781bcb2c313',
        NULL, '2023-07-22 13:42:24', NULL);
INSERT INTO `permission_role`
VALUES ('d9aec76cb9c14683aa9cf1d141650631', '98b9b022a6b64ea38b2ae0a028ba5347', '0', '785ae2df8ce14b638770a781bcb2c313',
        NULL, '2023-07-22 13:42:24', NULL);
INSERT INTO `permission_role`
VALUES ('eaa1732da8b04a0aae073ad9bda136da', '19e1065c601d402f979eeefae3ccf402', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('ee2868eaba294159bddc54cb6887bbdc', '48960c4727a14446802ded153dde30c4', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);
INSERT INTO `permission_role`
VALUES ('f9306320aebd49d9b42072ad4dcf5ede', 'e74cf92a484e42d4856cc541a232a588', '232b9005ab8a466495ca0b1f741e5adc',
        '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 12:11:34', NULL);

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `type`        int                                                          NOT NULL DEFAULT 1 COMMENT '类型(0客观题,1主观题)',
    `subject`     varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '科目',
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

-- ----------------------------
-- Table structure for problem_exam
-- ----------------------------
DROP TABLE IF EXISTS `problem_exam`;
CREATE TABLE `problem_exam`
(
    `id`            varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `exam_id`       varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '试卷ID',
    `permission_id` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '题库ID',
    `create_user`   varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建用户',
    `update_user`   varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改用户',
    `create_time`   datetime                                                     NOT NULL COMMENT '创建时间',
    `update_time`   datetime                                                     NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '题库-试卷绑定表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem_exam
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
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
VALUES ('0', '普通用户', 'localUser', 'localUser', '2023-07-21 17:55:48', NULL);
INSERT INTO `role`
VALUES ('232b9005ab8a466495ca0b1f741e5adc', '超级管理员', '785ae2df8ce14b638770a781bcb2c313', NULL, '2023-07-22 11:40:01',
        NULL);

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
    `email`       varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '邮箱',
    `phone`       varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT NULL COMMENT '电话',
    `role`        varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '用户角色',
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
VALUES ('6611ac92527141f0a58487173fdf63bd', 'user1', '$2a$10$NJnNA.vYlZj9ZuYZJ496dOJu5w0SjFWZ7Zxqzl1t1CYv0W440/nU2',
        NULL, '0', 1, NULL, NULL, '0', 0, 'localUser', NULL, '2023-07-22 13:24:02', NULL);
INSERT INTO `user`
VALUES ('785ae2df8ce14b638770a781bcb2c313', 'root', '$2a$10$9uJpe8fy/IDWq.VpSVpo0uXhN4xWAjnzjbUQ3E2oaYua7Tw.xFwf2',
        NULL, '0', 1, NULL, NULL, '232b9005ab8a466495ca0b1f741e5adc', 0, 'localUser', NULL, '2023-07-21 17:55:48',
        NULL);

SET FOREIGN_KEY_CHECKS = 1;
