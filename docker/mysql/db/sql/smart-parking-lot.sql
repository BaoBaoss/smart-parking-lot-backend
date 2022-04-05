/*
 Navicat Premium Data Transfer

 Source Server         : ParkingLot
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 192.168.6.6:3306
 Source Schema         : smart-parking-lot

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 05/04/2022 17:07:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for beacon_coordinate
-- ----------------------------
DROP TABLE IF EXISTS `beacon_coordinate`;
CREATE TABLE `beacon_coordinate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `coordinate_id` int(11) NOT NULL COMMENT '坐标id',
  `beacon_id` int(11) NOT NULL COMMENT '信标id',
  `rssi` double(11, 2) NOT NULL COMMENT 'rssi值',
  PRIMARY KEY (`id`, `coordinate_id`, `beacon_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of beacon_coordinate
-- ----------------------------
INSERT INTO `beacon_coordinate` VALUES (1, 1, 5, -49.71);
INSERT INTO `beacon_coordinate` VALUES (2, 1, 1, -58.33);
INSERT INTO `beacon_coordinate` VALUES (3, 1, 4, -68.52);
INSERT INTO `beacon_coordinate` VALUES (4, 1, 2, -72.78);
INSERT INTO `beacon_coordinate` VALUES (5, 1, 3, -65.78);
INSERT INTO `beacon_coordinate` VALUES (6, 2, 5, -64.42);
INSERT INTO `beacon_coordinate` VALUES (7, 2, 1, -56.79);
INSERT INTO `beacon_coordinate` VALUES (8, 2, 4, -72.80);
INSERT INTO `beacon_coordinate` VALUES (9, 2, 2, -67.57);
INSERT INTO `beacon_coordinate` VALUES (10, 2, 3, -59.89);
INSERT INTO `beacon_coordinate` VALUES (11, 3, 5, -57.84);
INSERT INTO `beacon_coordinate` VALUES (12, 3, 1, -58.34);
INSERT INTO `beacon_coordinate` VALUES (13, 3, 4, -67.38);
INSERT INTO `beacon_coordinate` VALUES (14, 3, 2, -67.98);
INSERT INTO `beacon_coordinate` VALUES (15, 3, 3, -63.17);
INSERT INTO `beacon_coordinate` VALUES (16, 4, 5, -59.88);
INSERT INTO `beacon_coordinate` VALUES (17, 4, 1, -58.31);
INSERT INTO `beacon_coordinate` VALUES (18, 4, 4, -66.23);
INSERT INTO `beacon_coordinate` VALUES (19, 4, 2, -71.96);
INSERT INTO `beacon_coordinate` VALUES (20, 4, 3, -56.29);
INSERT INTO `beacon_coordinate` VALUES (21, 5, 5, -62.19);
INSERT INTO `beacon_coordinate` VALUES (22, 5, 1, -63.29);
INSERT INTO `beacon_coordinate` VALUES (23, 5, 4, -59.15);
INSERT INTO `beacon_coordinate` VALUES (24, 5, 2, -70.72);
INSERT INTO `beacon_coordinate` VALUES (25, 5, 3, -64.21);
INSERT INTO `beacon_coordinate` VALUES (26, 6, 5, -77.43);
INSERT INTO `beacon_coordinate` VALUES (27, 6, 1, -63.05);
INSERT INTO `beacon_coordinate` VALUES (28, 6, 4, -63.77);
INSERT INTO `beacon_coordinate` VALUES (29, 6, 2, -61.40);
INSERT INTO `beacon_coordinate` VALUES (30, 6, 3, -61.82);
INSERT INTO `beacon_coordinate` VALUES (31, 7, 5, -68.85);
INSERT INTO `beacon_coordinate` VALUES (32, 7, 1, -69.66);
INSERT INTO `beacon_coordinate` VALUES (33, 7, 4, -60.53);
INSERT INTO `beacon_coordinate` VALUES (34, 7, 2, -67.58);
INSERT INTO `beacon_coordinate` VALUES (35, 7, 3, -57.85);
INSERT INTO `beacon_coordinate` VALUES (36, 8, 5, -62.04);
INSERT INTO `beacon_coordinate` VALUES (37, 8, 1, -72.12);
INSERT INTO `beacon_coordinate` VALUES (38, 8, 4, -59.99);
INSERT INTO `beacon_coordinate` VALUES (39, 8, 2, -69.55);
INSERT INTO `beacon_coordinate` VALUES (40, 8, 3, -63.17);
INSERT INTO `beacon_coordinate` VALUES (41, 9, 5, -66.64);
INSERT INTO `beacon_coordinate` VALUES (42, 9, 1, -68.99);
INSERT INTO `beacon_coordinate` VALUES (43, 9, 4, -56.62);
INSERT INTO `beacon_coordinate` VALUES (44, 9, 2, -75.86);
INSERT INTO `beacon_coordinate` VALUES (45, 9, 3, -68.30);
INSERT INTO `beacon_coordinate` VALUES (46, 10, 5, -84.79);
INSERT INTO `beacon_coordinate` VALUES (47, 10, 1, -57.90);
INSERT INTO `beacon_coordinate` VALUES (48, 10, 4, -66.87);
INSERT INTO `beacon_coordinate` VALUES (49, 10, 2, -50.92);
INSERT INTO `beacon_coordinate` VALUES (50, 10, 3, -58.42);
INSERT INTO `beacon_coordinate` VALUES (51, 11, 5, -70.70);
INSERT INTO `beacon_coordinate` VALUES (52, 11, 1, -50.84);
INSERT INTO `beacon_coordinate` VALUES (53, 11, 4, -60.63);
INSERT INTO `beacon_coordinate` VALUES (54, 11, 2, -68.11);
INSERT INTO `beacon_coordinate` VALUES (55, 11, 3, -63.09);
INSERT INTO `beacon_coordinate` VALUES (56, 12, 5, -67.94);
INSERT INTO `beacon_coordinate` VALUES (57, 12, 1, -63.19);
INSERT INTO `beacon_coordinate` VALUES (58, 12, 4, -63.30);
INSERT INTO `beacon_coordinate` VALUES (59, 12, 2, -77.44);
INSERT INTO `beacon_coordinate` VALUES (60, 12, 3, -63.68);
INSERT INTO `beacon_coordinate` VALUES (61, 13, 5, -66.33);
INSERT INTO `beacon_coordinate` VALUES (62, 13, 1, -64.39);
INSERT INTO `beacon_coordinate` VALUES (63, 13, 4, -53.25);
INSERT INTO `beacon_coordinate` VALUES (64, 13, 2, -75.22);
INSERT INTO `beacon_coordinate` VALUES (65, 13, 3, -71.11);
INSERT INTO `beacon_coordinate` VALUES (66, 14, 5, -73.54);
INSERT INTO `beacon_coordinate` VALUES (67, 14, 1, -56.24);
INSERT INTO `beacon_coordinate` VALUES (68, 14, 4, -67.74);
INSERT INTO `beacon_coordinate` VALUES (69, 14, 2, -59.84);
INSERT INTO `beacon_coordinate` VALUES (70, 14, 3, -63.86);
INSERT INTO `beacon_coordinate` VALUES (71, 15, 5, -67.08);
INSERT INTO `beacon_coordinate` VALUES (72, 15, 1, -51.89);
INSERT INTO `beacon_coordinate` VALUES (73, 15, 4, -62.95);
INSERT INTO `beacon_coordinate` VALUES (74, 15, 2, -65.32);
INSERT INTO `beacon_coordinate` VALUES (75, 15, 3, -60.39);
INSERT INTO `beacon_coordinate` VALUES (76, 16, 5, -67.87);
INSERT INTO `beacon_coordinate` VALUES (77, 16, 1, -52.27);
INSERT INTO `beacon_coordinate` VALUES (78, 16, 4, -61.46);
INSERT INTO `beacon_coordinate` VALUES (79, 16, 2, -75.60);
INSERT INTO `beacon_coordinate` VALUES (80, 16, 3, -60.15);
INSERT INTO `beacon_coordinate` VALUES (81, 17, 5, -71.83);
INSERT INTO `beacon_coordinate` VALUES (82, 17, 1, -60.67);
INSERT INTO `beacon_coordinate` VALUES (83, 17, 4, -60.08);
INSERT INTO `beacon_coordinate` VALUES (84, 17, 2, -71.15);
INSERT INTO `beacon_coordinate` VALUES (85, 17, 3, -62.66);
INSERT INTO `beacon_coordinate` VALUES (86, 18, 5, -73.51);
INSERT INTO `beacon_coordinate` VALUES (87, 18, 1, -46.63);
INSERT INTO `beacon_coordinate` VALUES (88, 18, 4, -63.41);
INSERT INTO `beacon_coordinate` VALUES (89, 18, 2, -68.46);
INSERT INTO `beacon_coordinate` VALUES (90, 18, 3, -61.57);
INSERT INTO `beacon_coordinate` VALUES (91, 19, 5, -74.53);
INSERT INTO `beacon_coordinate` VALUES (92, 19, 1, -59.21);
INSERT INTO `beacon_coordinate` VALUES (93, 19, 4, -69.53);
INSERT INTO `beacon_coordinate` VALUES (94, 19, 2, -66.17);
INSERT INTO `beacon_coordinate` VALUES (95, 19, 3, -63.10);
INSERT INTO `beacon_coordinate` VALUES (96, 20, 5, -65.06);
INSERT INTO `beacon_coordinate` VALUES (97, 20, 1, -47.51);
INSERT INTO `beacon_coordinate` VALUES (98, 20, 4, -73.09);
INSERT INTO `beacon_coordinate` VALUES (99, 20, 2, -66.98);
INSERT INTO `beacon_coordinate` VALUES (100, 20, 3, -60.38);

-- ----------------------------
-- Table structure for beacon_device
-- ----------------------------
DROP TABLE IF EXISTS `beacon_device`;
CREATE TABLE `beacon_device`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mac` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物理地址',
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'uuid',
  `major` int(11) NULL DEFAULT NULL COMMENT '主要标识',
  `minor` int(11) NULL DEFAULT NULL COMMENT '次要标识',
  `x` int(11) NOT NULL COMMENT 'x坐标',
  `y` int(11) NOT NULL COMMENT 'y坐标',
  `parking_lot_id` int(11) NOT NULL COMMENT '停车场id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of beacon_device
-- ----------------------------
INSERT INTO `beacon_device` VALUES (1, '60:77:71:C3:2B:CB', 'fda5693-a4e2-4fb1-afcf-c6eb7647825', 10, 7, 0, 0, 2, '2022-03-05 11:06:59', '2022-03-28 13:18:19');
INSERT INTO `beacon_device` VALUES (2, '60:77:71:C3:2E:E7', 'fda5693-a4e2-4fb1-afcf-c6eb7647825', 10, 7, 0, 5, 2, '2022-03-05 11:07:28', '2022-03-31 06:17:17');
INSERT INTO `beacon_device` VALUES (3, '60:77:71:C3:27:72', 'fda5693-a4e2-4fb1-afcf-c6eb7647825', 10, 7, 2, 2, 2, '2022-03-05 11:08:05', '2022-03-28 13:18:19');
INSERT INTO `beacon_device` VALUES (4, '60:77:71:C3:2E:ED', 'fda5693-a4e2-4fb1-afcf-c6eb7647825', 10, 7, 4, 0, 2, '2022-03-08 14:20:48', '2022-03-31 06:17:19');
INSERT INTO `beacon_device` VALUES (5, '60:77:71:C3:28:25', 'fda5693-a4e2-4fb1-afcf-c6eb7647825', 10, 7, 4, 5, 2, '2022-03-08 14:22:04', '2022-03-31 06:17:24');

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `car_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车牌号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `purchase_date` date NULL DEFAULT NULL COMMENT '购入日期',
  `purchsae_price` int(11) NULL DEFAULT NULL COMMENT '购入价格',
  `color` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '颜色',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of car
-- ----------------------------

-- ----------------------------
-- Table structure for coordinate
-- ----------------------------
DROP TABLE IF EXISTS `coordinate`;
CREATE TABLE `coordinate`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `x` int(11) NOT NULL COMMENT 'x坐标',
  `y` int(11) NOT NULL COMMENT 'y坐标',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coordinate
-- ----------------------------
INSERT INTO `coordinate` VALUES (1, 0, 0, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (2, 1, 0, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (3, 0, 1, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (4, 1, 1, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (5, 0, 2, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (6, 2, 0, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (7, 2, 1, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (8, 1, 2, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (9, 0, 3, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (10, 3, 0, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (11, 2, 2, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (12, 1, 3, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (13, 0, 4, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (14, 3, 1, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (15, 3, 2, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (16, 2, 3, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (17, 1, 4, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (18, 3, 3, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (19, 2, 4, '2022-03-31 06:22:25', '2022-03-31 06:22:25');
INSERT INTO `coordinate` VALUES (20, 3, 4, '2022-03-31 06:22:25', '2022-03-31 06:22:25');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '账号启用状态：0->禁用；1->启用',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` int(11) NULL DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `account_balance` int(11) NOT NULL DEFAULT 0 COMMENT '账户余额',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, '张三', '$2a$10$3QULEJ7SVRS.rOCVxH8.audE1a3i06PLI7yzqXhofipTx/oxcm.4u', NULL, NULL, 1, NULL, NULL, NULL, 0, '2022-04-02 13:47:16', '2022-04-02 13:47:16');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父菜单id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `order` int(11) NULL DEFAULT 1 COMMENT '显示顺序',
  `route_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件地址',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型：M->目录；C->菜单；A->按钮',
  `visible` int(1) NOT NULL DEFAULT 1 COMMENT '是否显示：1->显示；0->隐藏',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '菜单状态：1->正常；0->停用',
  `perms` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `menu_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1026 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '系统管理', 1, 'system', NULL, 'M', 1, 1, NULL, 'system', '2021-11-29 17:00:40', '2022-01-27 14:46:35');
INSERT INTO `menu` VALUES (100, 1, '用户管理', 1, 'user', 'system/user/index', 'C', 1, 1, 'system:user:list', 'user', '2021-12-12 06:58:09', '2022-02-09 02:39:01');
INSERT INTO `menu` VALUES (101, 1, '角色管理', 2, 'role', 'system/role/index', 'C', 1, 1, 'system:role:list', 'peoples', '2021-12-12 06:58:39', '2022-02-24 09:22:30');
INSERT INTO `menu` VALUES (102, 1, '菜单管理', 3, 'menu', 'system/menu/index', 'C', 1, 1, 'system:menu:list', 'tree-table', '2022-02-22 06:35:43', '2022-02-22 07:18:56');
INSERT INTO `menu` VALUES (1001, 100, '用户新增', 1, NULL, NULL, 'A', 1, 1, 'system:user:add', '#', '2022-02-07 01:27:46', '2022-02-22 15:12:06');
INSERT INTO `menu` VALUES (1002, 100, '用户修改', 2, NULL, NULL, 'A', 1, 1, 'system:user:edit', '#', '2022-02-07 01:28:22', '2022-02-22 15:12:09');
INSERT INTO `menu` VALUES (1003, 100, '用户删除', 3, NULL, NULL, 'A', 1, 1, 'system:user:remove', '#', '2022-02-07 01:28:55', '2022-02-22 15:12:11');
INSERT INTO `menu` VALUES (1005, 101, '角色新增', 1, NULL, NULL, 'A', 1, 1, 'system:role:add', '#', '2022-02-13 08:31:23', '2022-02-13 08:31:23');
INSERT INTO `menu` VALUES (1006, 101, '角色修改', 2, NULL, NULL, 'A', 1, 1, 'system:role:edit', '#', '2022-02-13 08:31:45', '2022-02-22 15:11:59');
INSERT INTO `menu` VALUES (1007, 101, '角色删除', 3, NULL, NULL, 'A', 1, 1, 'system:role:remove', '#', '2022-02-13 08:32:04', '2022-02-22 15:12:03');
INSERT INTO `menu` VALUES (1008, 102, '菜单新增', 1, NULL, NULL, 'A', 1, 1, 'system:menu:add', '#', '2022-02-22 15:11:31', '2022-02-22 15:11:53');
INSERT INTO `menu` VALUES (1009, 102, '菜单修改', 2, NULL, NULL, 'A', 1, 1, 'system:menu:edit', '#', '2022-02-24 03:37:06', '2022-02-24 09:26:53');
INSERT INTO `menu` VALUES (1010, 102, '菜单删除', 3, NULL, NULL, 'A', 1, 1, 'system:menu:remove', '#', '2022-02-24 08:31:41', '2022-02-24 09:26:57');
INSERT INTO `menu` VALUES (1011, 0, 'app管理', 2, 'app', NULL, 'M', 1, 1, NULL, 'client', '2022-04-03 03:05:49', '2022-04-03 03:05:49');
INSERT INTO `menu` VALUES (1012, 1011, '停车场管理', 1, 'parking', 'app/parking/index', 'C', 1, 1, 'app:parking:list', 'dict', '2022-04-03 03:08:02', '2022-04-04 03:19:24');
INSERT INTO `menu` VALUES (1013, 1012, '停车场新增', 1, NULL, NULL, 'A', 1, 1, 'app:parking:add', '#', '2022-04-03 08:59:41', '2022-04-03 08:59:41');
INSERT INTO `menu` VALUES (1014, 1012, '停车场修改', 2, NULL, NULL, 'A', 1, 1, 'app:parking:edit', '#', '2022-04-03 09:00:10', '2022-04-03 09:00:10');
INSERT INTO `menu` VALUES (1015, 1012, '停车场删除', 3, NULL, NULL, 'A', 1, 1, 'app:parking:remove', '#', '2022-04-03 09:00:33', '2022-04-03 09:00:33');
INSERT INTO `menu` VALUES (1018, 1012, '车位新增', 4, NULL, NULL, 'A', 1, 1, 'app:space:add', '#', '2022-04-04 03:15:09', '2022-04-04 03:55:20');
INSERT INTO `menu` VALUES (1019, 1012, '车位修改', 5, NULL, NULL, 'A', 1, 1, 'app:space:edit', '#', '2022-04-04 03:15:29', '2022-04-04 03:55:22');
INSERT INTO `menu` VALUES (1020, 1012, '车位删除', 6, NULL, NULL, 'A', 1, 1, 'app:space:remove', '#', '2022-04-04 03:15:46', '2022-04-04 03:55:23');
INSERT INTO `menu` VALUES (1021, 1012, '车位列表', 7, NULL, NULL, 'A', 1, 1, 'app:space:list', '#', '2022-04-04 03:56:06', '2022-04-04 03:56:06');
INSERT INTO `menu` VALUES (1022, 1012, '信标新增', 8, NULL, NULL, 'A', 1, 1, 'app:beacon:add', '#', '2022-04-04 13:47:34', '2022-04-04 13:47:34');
INSERT INTO `menu` VALUES (1023, 1012, '信标修改', 9, NULL, NULL, 'A', 1, 1, 'app:beacon:edit', '#', '2022-04-04 13:47:53', '2022-04-04 13:47:53');
INSERT INTO `menu` VALUES (1024, 1012, '信标删除', 10, NULL, NULL, 'A', 1, 1, 'app:beacon:remove', '#', '2022-04-04 13:48:11', '2022-04-04 13:48:11');
INSERT INTO `menu` VALUES (1025, 1012, '信标列表', 11, NULL, NULL, 'A', 1, 1, 'app:beacon:list', '#', '2022-04-04 13:48:25', '2022-04-04 13:48:25');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `notice_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告编号',
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `release_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `level` int(11) NULL DEFAULT NULL COMMENT '公告级别：0->未知；1->一般；2->重要；3->非常重要',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for parking_lot
-- ----------------------------
DROP TABLE IF EXISTS `parking_lot`;
CREATE TABLE `parking_lot`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '停车场名称',
  `longitude` decimal(12, 6) NOT NULL COMMENT '经度',
  `latitude` decimal(12, 6) NOT NULL COMMENT '纬度',
  `price_standard` float NOT NULL DEFAULT 0 COMMENT '收费标准  元/小时',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of parking_lot
-- ----------------------------
INSERT INTO `parking_lot` VALUES (1, '清河北苑停车场', 118.716515, 31.975403, 30, '2022-03-26 14:29:39', '2022-03-27 05:53:36');
INSERT INTO `parking_lot` VALUES (2, '中海大厦停车场', 118.752403, 32.045404, 15, '2022-03-27 05:55:35', '2022-03-27 05:55:35');

-- ----------------------------
-- Table structure for parking_space
-- ----------------------------
DROP TABLE IF EXISTS `parking_space`;
CREATE TABLE `parking_space`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parking_lot_id` int(11) NOT NULL COMMENT '停车场id',
  `car_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `x` int(11) NOT NULL COMMENT 'x坐标',
  `y` int(11) NOT NULL COMMENT 'y坐标',
  `available` int(11) NOT NULL DEFAULT 1 COMMENT '车位是否可用：0->不可用；1->可用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of parking_space
-- ----------------------------
INSERT INTO `parking_space` VALUES (1, 2, NULL, 0, 1, 1, '2022-03-31 06:25:30', '2022-03-31 06:25:30');
INSERT INTO `parking_space` VALUES (2, 2, '粤A68688', 0, 3, 0, '2022-03-31 06:25:41', '2022-04-04 08:05:06');
INSERT INTO `parking_space` VALUES (3, 2, NULL, 1, 4, 1, '2022-03-31 06:25:53', '2022-03-31 06:25:53');
INSERT INTO `parking_space` VALUES (4, 2, NULL, 3, 1, 1, '2022-03-31 06:26:08', '2022-03-31 06:26:08');
INSERT INTO `parking_space` VALUES (5, 2, '京B88888', 3, 3, 0, '2022-03-31 06:26:38', '2022-04-04 08:05:21');
INSERT INTO `parking_space` VALUES (13, 1, NULL, 0, 1, 1, '2022-04-04 15:57:39', '2022-04-04 15:57:51');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `order` int(11) NOT NULL DEFAULT 1 COMMENT '显示顺序',
  `status` int(1) NOT NULL DEFAULT 1 COMMENT '是否启用：1->启用；0->停用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name_unique`(`name`) USING BTREE COMMENT '角色名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', 1, 1, '2022-01-20 02:37:05', '2022-02-13 08:35:12');
INSERT INTO `role` VALUES (2, '普通用户', 2, 1, '2022-01-20 02:37:15', '2022-02-24 09:25:51');
INSERT INTO `role` VALUES (4, '测试角色2', 2, 1, '2022-02-14 08:42:58', '2022-02-16 14:26:26');
INSERT INTO `role` VALUES (7, '测试角色', 1, 1, '2022-02-17 07:13:12', '2022-02-24 09:03:09');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (2, 1);
INSERT INTO `role_menu` VALUES (2, 101);
INSERT INTO `role_menu` VALUES (2, 102);
INSERT INTO `role_menu` VALUES (2, 109);
INSERT INTO `role_menu` VALUES (2, 1001);
INSERT INTO `role_menu` VALUES (2, 1002);
INSERT INTO `role_menu` VALUES (2, 1003);
INSERT INTO `role_menu` VALUES (2, 1005);
INSERT INTO `role_menu` VALUES (2, 1006);
INSERT INTO `role_menu` VALUES (2, 1007);
INSERT INTO `role_menu` VALUES (2, 1008);
INSERT INTO `role_menu` VALUES (2, 1010);
INSERT INTO `role_menu` VALUES (4, 1);
INSERT INTO `role_menu` VALUES (4, 100);
INSERT INTO `role_menu` VALUES (4, 101);
INSERT INTO `role_menu` VALUES (4, 1000);
INSERT INTO `role_menu` VALUES (4, 1002);
INSERT INTO `role_menu` VALUES (4, 1003);
INSERT INTO `role_menu` VALUES (4, 1004);
INSERT INTO `role_menu` VALUES (4, 1005);
INSERT INTO `role_menu` VALUES (4, 1006);
INSERT INTO `role_menu` VALUES (7, 1);
INSERT INTO `role_menu` VALUES (7, 101);
INSERT INTO `role_menu` VALUES (7, 1005);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `status` int(1) NULL DEFAULT NULL COMMENT '账号启用状态：0->禁用；1->启用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_unique`(`username`) USING BTREE COMMENT '用户名唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$zSRKh/qw5PLE5HZ9GzrN7O3v26aaOl1dAQFaniN/7Wx6.fxSU.gse', NULL, '13955781457', '1928160048@qq.com', '测兔儿', '系统管理员', 1, '2021-12-17 07:55:59', '2022-01-24 13:02:31');
INSERT INTO `user` VALUES (2, 'cetuer', '$2a$10$.DliFpaBb6uDls0NbBQCeO1Ez.usP9JFI3cWKZaDGBCIG.bJqrRQu', 'http://192.168.6.6:8888/parking/2022-02-12/91e220b9-4070-4f04-8a2a-c37455ad65de.jpg', '13955745974', '192186854@qq.com', 'Cetuer', '系统管理员', 1, '2022-01-24 13:04:00', '2022-02-17 08:20:03');
INSERT INTO `user` VALUES (3, 'admin1', '$2a$10$zSRKh/qw5PLE5HZ9GzrN7O3v26aaOl1dAQFaniN/7Wx6.fxSU.gse', NULL, '13955781457', '1928160048@qq.com', '测兔儿', '系统管理员', 1, '2021-12-17 07:55:59', '2022-01-24 13:02:31');
INSERT INTO `user` VALUES (4, 'admin2', '$2a$10$wCF./6t4WDCKh5UZ4/KaoOR0WPOxOQNe2p9csH1zOouk.cYde1Hl.', NULL, '13955781457', '1928160048@qq.com', '测兔儿15', '系统管理员', 0, '2021-12-17 07:55:59', '2022-02-17 07:07:55');
INSERT INTO `user` VALUES (9, 'admin7', '$2a$10$zSRKh/qw5PLE5HZ9GzrN7O3v26aaOl1dAQFaniN/7Wx6.fxSU.gse', NULL, '13955781457', '1928160048@qq.com', '测兔儿', '系统管理员', 1, '2021-12-17 07:55:59', '2022-01-24 13:02:31');
INSERT INTO `user` VALUES (12, 'admin10', '$2a$10$zSRKh/qw5PLE5HZ9GzrN7O3v26aaOl1dAQFaniN/7Wx6.fxSU.gse', NULL, '13955781457', '1928160048@qq.com', '测兔儿', '系统管理员', 1, '2021-12-17 07:55:59', '2022-01-24 13:02:31');
INSERT INTO `user` VALUES (15, 'zhangsan', '$2a$10$PQSlgVDACTGuMsCP9DeBlOM6JQ0Syx9zRXEeaaEs8bH/HShKkME3S', NULL, NULL, NULL, '张三', NULL, 1, '2022-02-09 09:01:31', '2022-02-09 09:01:31');
INSERT INTO `user` VALUES (16, 'sss', '$2a$10$QdYgBHJCM64Q8.ntx9E6uOo7jOLHctabVIXv5qhIGW5g.va1Tpmi2', NULL, '13955557849', '123@qq.com', '测试', 'wu', 0, '2022-02-09 09:03:20', '2022-02-09 13:42:26');
INSERT INTO `user` VALUES (19, '45646454186445', '$2a$10$Aw08pbl.eFziKoV7KtkU4.gQ34hDaOEcIGkLDqOfKC5Gi.6y5stWu', NULL, NULL, NULL, '545651', NULL, 1, '2022-02-09 09:20:35', '2022-02-09 09:20:35');
INSERT INTO `user` VALUES (21, '新增测试用户', '$2a$10$qyjnD1sk6JMUT18wIzuUbOp2cgGYSkVTHbYetwYokxgtMEFZyGaeS', NULL, '13955778478', '19@qq.com', 'hhh', NULL, 0, '2022-02-17 07:06:16', '2022-02-17 07:06:16');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1);
INSERT INTO `user_role` VALUES (2, 2);
INSERT INTO `user_role` VALUES (3, 2);
INSERT INTO `user_role` VALUES (4, 4);
INSERT INTO `user_role` VALUES (12, 2);

SET FOREIGN_KEY_CHECKS = 1;
