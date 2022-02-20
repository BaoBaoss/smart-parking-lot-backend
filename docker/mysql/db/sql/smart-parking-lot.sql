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

 Date: 18/02/2022 17:26:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
-- Table structure for gateway_device
-- ----------------------------
DROP TABLE IF EXISTS `gateway_device`;
CREATE TABLE `gateway_device`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mac` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物理地址',
  `online_count` int(11) NOT NULL DEFAULT 0 COMMENT '在线数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of gateway_device
-- ----------------------------

-- ----------------------------
-- Table structure for ibeacon_device
-- ----------------------------
DROP TABLE IF EXISTS `ibeacon_device`;
CREATE TABLE `ibeacon_device`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mac` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物理地址',
  `uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid',
  `major` int(11) NOT NULL DEFAULT 0 COMMENT '主要标识',
  `minor` int(11) NOT NULL DEFAULT 0 COMMENT '次要标识',
  `x` int(11) NOT NULL COMMENT 'x坐标',
  `y` int(11) NOT NULL COMMENT 'y坐标',
  `rssi` int(11) NULL DEFAULT NULL COMMENT 'rssi信号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ibeacon_device
-- ----------------------------

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
  `status` int(11) NOT NULL COMMENT '账号启用状态：0->禁用；1->启用',
  `avatar` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `gender` int(11) NULL DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `account_balance` int(11) NOT NULL DEFAULT 0 COMMENT '账户余额',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `card_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL COMMENT '菜单id',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父菜单id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `order` int(11) NULL DEFAULT 1 COMMENT '显示顺序',
  `route_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件地址',
  `type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单类型：M->目录；C->菜单；A->按钮',
  `visible` int(1) NOT NULL DEFAULT 1 COMMENT '是否显示：1->显示；0->隐藏',
  `perms` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `menu_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, 0, '系统管理', 1, 'system', NULL, 'M', 1, NULL, 'system', '2021-11-29 17:00:40', '2022-01-27 14:46:35');
INSERT INTO `menu` VALUES (100, 1, '用户管理', 1, 'user', 'system/user/index', 'C', 1, 'system:user:list', 'user', '2021-12-12 06:58:09', '2022-02-09 02:39:01');
INSERT INTO `menu` VALUES (101, 1, '角色管理', 2, 'role', 'system/role/index', 'C', 1, 'system:role:list', 'peoples', '2021-12-12 06:58:39', '2022-02-06 13:06:20');
INSERT INTO `menu` VALUES (1001, 100, '用户新增', 2, NULL, NULL, 'A', 1, 'system:user:add', '#', '2022-02-07 01:27:46', '2022-02-07 01:27:46');
INSERT INTO `menu` VALUES (1002, 100, '用户修改', 3, NULL, NULL, 'A', 1, 'system:user:edit', '#', '2022-02-07 01:28:22', '2022-02-07 01:28:22');
INSERT INTO `menu` VALUES (1003, 100, '用户删除', 4, NULL, NULL, 'A', 1, 'system:user:remove', '#', '2022-02-07 01:28:55', '2022-02-07 01:28:55');
INSERT INTO `menu` VALUES (1005, 101, '角色新增', 1, NULL, NULL, 'A', 1, 'system:role:add', '#', '2022-02-13 08:31:23', '2022-02-13 08:31:23');
INSERT INTO `menu` VALUES (1006, 101, '角色修改', 1, NULL, NULL, 'A', 1, 'system:role:edit', '#', '2022-02-13 08:31:45', '2022-02-13 08:31:45');
INSERT INTO `menu` VALUES (1007, 101, '角色删除', 1, NULL, NULL, 'A', 1, 'system:role:remove', '#', '2022-02-13 08:32:04', '2022-02-13 08:32:04');

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
  `parking_lot_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `carport_count` int(11) NOT NULL COMMENT '车位总数',
  `parking_count` int(11) NOT NULL COMMENT '停车数',
  `price_standard` float NOT NULL DEFAULT 0 COMMENT '收费标准  元/小时',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of parking_lot
-- ----------------------------

-- ----------------------------
-- Table structure for parking_space
-- ----------------------------
DROP TABLE IF EXISTS `parking_space`;
CREATE TABLE `parking_space`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parking_space_id` int(11) NOT NULL COMMENT '车位号',
  `x` int(11) NOT NULL COMMENT 'x坐标',
  `y` int(11) NOT NULL COMMENT 'y坐标',
  `available` int(11) NOT NULL DEFAULT 1 COMMENT '车位是否可用：0->不可用；1->可用',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of parking_space
-- ----------------------------

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
INSERT INTO `role` VALUES (2, '普通用户', 2, 1, '2022-01-20 02:37:15', '2022-02-17 07:33:22');
INSERT INTO `role` VALUES (4, '测试角色2', 2, 1, '2022-02-14 08:42:58', '2022-02-16 14:26:26');
INSERT INTO `role` VALUES (7, '测试角色', 1, 1, '2022-02-17 07:13:12', '2022-02-17 07:13:12');

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
INSERT INTO `role_menu` VALUES (2, 1001);
INSERT INTO `role_menu` VALUES (2, 1002);
INSERT INTO `role_menu` VALUES (2, 1003);
INSERT INTO `role_menu` VALUES (2, 1005);
INSERT INTO `role_menu` VALUES (2, 1006);
INSERT INTO `role_menu` VALUES (2, 1007);
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
INSERT INTO `user_role` VALUES (2, 1);
INSERT INTO `user_role` VALUES (2, 2);
INSERT INTO `user_role` VALUES (3, 2);
INSERT INTO `user_role` VALUES (4, 4);
INSERT INTO `user_role` VALUES (12, 2);

SET FOREIGN_KEY_CHECKS = 1;
