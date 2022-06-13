/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : studio

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 13/06/2022 15:31:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS `studio` default character set utf8mb4 collate utf8mb4_unicode_ci;

use `studio`;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '个人管理', 0, 1, 'own', NULL, NULL, 1, 0, 'M', '0', '0', '', 'user', 'admin', '2022-03-28 20:40:44', '', NULL, '个人管理目录');
INSERT INTO `sys_menu` VALUES (2, '工作室管理', 0, 2, 'team', '', NULL, 1, 0, 'M', '0', '0', NULL, 'guide', 'admin', '2022-03-28 20:43:19', '', NULL, '工作室管理目录');
INSERT INTO `sys_menu` VALUES (3, '信息录入', 1, 1, 'info', 'own/info/index', NULL, 1, 0, 'C', '0', '0', NULL, 'people', 'admin', '2022-03-28 20:44:31', '', NULL, '信息录入菜单');
INSERT INTO `sys_menu` VALUES (4, '获奖证书', 1, 2, 'ccie', 'own/ccie/index', NULL, 1, 0, 'C', '0', '0', '', 'system', 'admin', '2022-03-28 20:45:09', 'admin', '2022-04-12 11:04:11', '获奖证书菜单');
INSERT INTO `sys_menu` VALUES (5, '个人竞赛', 1, 3, 'race', 'own/race/index', NULL, 1, 0, 'C', '0', '0', '', 'skill', 'admin', '2022-03-28 20:45:57', 'admin', '2022-04-12 11:04:49', '个人竞赛菜单');
INSERT INTO `sys_menu` VALUES (6, '个人心得', 1, 4, 'think', 'own/think/index', NULL, 1, 0, 'C', '0', '0', '', 'form', 'admin', '2022-03-28 20:47:23', 'admin', '2022-04-12 11:05:08', '个人心得菜单');
INSERT INTO `sys_menu` VALUES (7, '专业管理', 2, 1, 'major', 'team/major/index', NULL, 1, 0, 'C', '0', '0', '', 'tool', 'admin', '2022-03-28 22:12:02', 'admin', '2022-04-12 11:05:30', '专业管理菜单');
INSERT INTO `sys_menu` VALUES (8, '年级管理', 2, 2, 'grage', 'team/grade/index', NULL, 1, 0, 'C', '0', '0', '', 'job', 'admin', '2022-03-29 22:56:23', 'admin', '2022-04-12 11:06:09', '年级管理菜单');
INSERT INTO `sys_menu` VALUES (9, '成员管理', 2, 5, 'member', 'team/member/index', NULL, 1, 0, 'C', '0', '0', '', 'peoples', 'admin', '2022-03-29 22:56:25', 'admin', '2022-04-12 11:07:52', '成员管理菜单');
INSERT INTO `sys_menu` VALUES (10, '证书管理', 2, 4, 'ccies', 'team/ccies/index', NULL, 1, 0, 'C', '0', '0', '', 'system', 'admin', '2022-03-29 22:56:27', 'admin', '2022-04-12 11:07:23', '证书管理菜单');
INSERT INTO `sys_menu` VALUES (11, '竞赛管理', 2, 3, 'races', 'team/races/index', NULL, 1, 0, 'C', '0', '0', '', 'skill', 'admin', '2022-03-29 22:56:30', 'admin', '2022-04-12 11:06:48', '竞赛管理菜单');
INSERT INTO `sys_menu` VALUES (12, '系统管理', 0, 3, 'system', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'system', 'admin', '2022-04-10 21:56:17', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (13, '角色管理', 12, 2, 'role', 'system/role/index', NULL, 1, 0, 'C', '0', '0', '', 'peoples', 'admin', '2022-04-10 21:57:23', 'admin', '2022-04-13 08:32:15', '角色管理菜单');
INSERT INTO `sys_menu` VALUES (14, '菜单管理', 12, 1, 'menu', 'system/menu/index', NULL, 1, 0, 'C', '0', '0', '', 'tree-table', 'admin', '2022-04-11 17:11:00', 'admin', '2022-04-13 08:32:19', '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (19, '获取专业年级菜单', 3, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:info:menu', '#', 'admin', '2022-04-11 21:25:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (20, '信息录入', 3, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:info:edit', '#', 'admin', '2022-04-11 23:10:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (21, '获取用户信息', 3, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:info:query', '#', 'admin', '2022-04-11 23:14:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (23, '获取详细信息', 4, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:ccie:query', '#', 'admin', '2022-04-12 09:46:47', 'admin', '2022-04-12 09:51:30', '');
INSERT INTO `sys_menu` VALUES (24, '新增获奖证书', 4, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:ccie:add', '#', 'admin', '2022-04-12 09:47:08', 'admin', '2022-04-12 09:51:35', '');
INSERT INTO `sys_menu` VALUES (25, '修改获奖证书', 4, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:ccie:edit', '#', 'admin', '2022-04-12 09:47:28', 'admin', '2022-04-12 09:51:40', '');
INSERT INTO `sys_menu` VALUES (26, '删除获奖名称', 4, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:ccie:remove', '#', 'admin', '2022-04-12 09:47:54', 'admin', '2022-04-12 09:51:45', '');
INSERT INTO `sys_menu` VALUES (28, '获取个人竞赛详细信息', 5, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:race:query', '#', 'admin', '2022-04-12 09:48:54', 'admin', '2022-04-12 09:52:31', '');
INSERT INTO `sys_menu` VALUES (29, '新增个人竞赛', 5, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:race:add', '#', 'admin', '2022-04-12 09:49:17', 'admin', '2022-04-12 09:52:40', '');
INSERT INTO `sys_menu` VALUES (30, '修改个人竞赛', 5, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:race:edit', '#', 'admin', '2022-04-12 09:49:42', 'admin', '2022-04-12 09:52:44', '');
INSERT INTO `sys_menu` VALUES (31, '删除个人竞赛', 5, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:race:remove', '#', 'admin', '2022-04-12 09:50:35', 'admin', '2022-04-12 09:52:50', '');
INSERT INTO `sys_menu` VALUES (33, '获取个人心得详细信息', 6, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:think:query', '#', 'admin', '2022-04-12 09:53:20', '', NULL, '');
INSERT INTO `sys_menu` VALUES (34, '新增个人心得', 6, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:think:add', '#', 'admin', '2022-04-12 09:53:37', '', NULL, '');
INSERT INTO `sys_menu` VALUES (35, '修改个人心得', 6, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:think:edit', '#', 'admin', '2022-04-12 09:54:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (36, '删除个人心得', 6, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:think:remove', '#', 'admin', '2022-04-12 09:54:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (37, '获取专业详细信息', 7, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:major:query', '#', 'admin', '2022-04-12 09:54:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (38, '新增专业', 7, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:major:add', '#', 'admin', '2022-04-12 09:55:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (39, '修改专业', 7, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:major:edit', '#', 'admin', '2022-04-12 09:55:35', 'admin', '2022-04-12 09:55:39', '');
INSERT INTO `sys_menu` VALUES (40, '删除专业', 7, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:major:remove', '#', 'admin', '2022-04-12 09:55:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (41, '获取年级详细信息', 8, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:grade:query', '#', 'admin', '2022-04-12 09:56:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (42, '新增年级', 8, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:grade:add', '#', 'admin', '2022-04-12 09:56:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (43, '修改年级', 8, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:grade:edit', '#', 'admin', '2022-04-12 09:57:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (44, '删除年级', 8, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:grade:remove', '#', 'admin', '2022-04-12 09:57:20', 'admin', '2022-04-12 09:57:31', '');
INSERT INTO `sys_menu` VALUES (46, '获取用户姓名与id', 11, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:race:memberoptions', '#', 'admin', '2022-04-12 09:59:16', 'admin', '2022-06-10 14:53:20', '');
INSERT INTO `sys_menu` VALUES (47, '导出竞赛记录', 11, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:race:export', '#', 'admin', '2022-04-12 09:59:37', 'admin', '2022-06-10 14:53:23', '');
INSERT INTO `sys_menu` VALUES (48, '证书导出', 10, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:ccie:export', '#', 'admin', '2022-04-12 10:00:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (49, '获取成员信息', 9, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:member:query', '#', 'admin', '2022-04-12 10:01:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (50, '修改成员信息', 9, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:member:edit', '#', 'admin', '2022-04-12 10:02:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (51, '注销账号', 9, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:member:cancelled', '#', 'admin', '2022-04-12 10:02:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (52, '激活账号', 9, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:member:active', '#', 'admin', '2022-04-12 10:02:35', '', NULL, '');
INSERT INTO `sys_menu` VALUES (53, '转让负责人', 9, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:member:transfer', '#', 'admin', '2022-04-12 10:03:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (54, '重置密码', 9, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:member:resetpwd', '#', 'admin', '2022-04-12 10:03:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (55, '删除账号', 9, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:member:remove', '#', 'admin', '2022-04-12 10:03:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (56, '导出用户信息', 9, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:member:export', '#', 'admin', '2022-04-12 10:04:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (57, '新建账号', 9, 9, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:member:add', '#', 'admin', '2022-04-12 10:04:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (58, '角色状态修改', 13, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:role:changeStatus', '#', 'admin', '2022-04-12 10:16:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (59, '查询角色详细', 13, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2022-04-12 10:16:22', '', NULL, '');
INSERT INTO `sys_menu` VALUES (60, '新增角色', 13, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2022-04-12 10:16:42', '', NULL, '');
INSERT INTO `sys_menu` VALUES (61, '修改角色', 13, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2022-04-12 10:16:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (62, '查询角色已授权用户列表', 13, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:role:authUser:allocatedList', '#', 'admin', '2022-04-12 10:17:23', 'admin', '2022-04-12 10:17:49', '');
INSERT INTO `sys_menu` VALUES (63, '取消用户授权角色', 13, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:role:authUser:cancel', '#', 'admin', '2022-04-12 10:17:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (64, '批量取消用户授权角色', 13, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:role:authUser:cancelAll', '#', 'admin', '2022-04-12 10:18:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (65, '查询角色未授权用户列表', 13, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:role:authUser:unallocatedList', '#', 'admin', '2022-04-12 10:18:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (66, '授权多个选择用户', 13, 9, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:role:authUser:selectAll', '#', 'admin', '2022-04-12 10:18:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (67, '查询菜单详细', 14, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2022-04-12 10:19:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (68, '添加菜单', 14, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2022-04-12 10:19:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (69, '修改菜单', 14, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2022-04-12 10:20:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (70, '删除菜单', 14, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2022-04-12 10:20:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (71, '根据角色id获取菜单', 14, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:menu:roleMenuTree', '#', 'admin', '2022-04-12 10:21:20', '', NULL, '');
INSERT INTO `sys_menu` VALUES (72, '查询菜单下拉树结构', 14, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:menu:treeselect', '#', 'admin', '2022-04-12 10:21:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (73, '列表查询', 4, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:ccie:list', '#', 'admin', '2022-04-12 11:04:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (74, '个人竞赛列表查询', 5, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:race:list', '#', 'admin', '2022-04-12 11:04:42', '', NULL, '');
INSERT INTO `sys_menu` VALUES (75, '个人心得列表查询', 6, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:think:list', '#', 'admin', '2022-04-12 11:05:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (76, '专业列表查询', 7, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:major:list', '#', 'admin', '2022-04-12 11:05:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (77, '年级列表查询', 8, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:grade:list', '#', 'admin', '2022-04-12 11:05:53', 'admin', '2022-04-12 11:06:03', '');
INSERT INTO `sys_menu` VALUES (78, '列表查询', 11, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:race:list', '#', 'admin', '2022-04-12 11:06:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (79, '证书列表查询', 10, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:ccie:list', '#', 'admin', '2022-04-12 11:07:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (80, '成员列表查询', 9, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:member:list', '#', 'admin', '2022-04-12 11:07:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (81, '列表查询', 13, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:role:list', '#', 'admin', '2022-04-12 11:08:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (82, '查询菜单列表', 14, 0, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:menu:list', '#', 'admin', '2022-04-12 11:08:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (83, '获取竞赛的参赛成员信息', 5, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'own:race:members', '#', 'admin', '2022-04-12 15:27:22', '', NULL, '');
INSERT INTO `sys_menu` VALUES (84, '备份数据', 9, 10, '', NULL, NULL, 1, 0, 'F', '0', '0', 'team:member:backup', '#', 'admin', '2022-06-03 09:38:03', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, '0', '0', 'admin', '2022-03-17 09:22:45', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '负责人', 'manage', 2, '2', 0, '0', '0', 'admin', '2022-03-28 21:02:19', 'admin', '2022-06-10 14:52:39', '工作室负责人');
INSERT INTO `sys_role` VALUES (3, '成员', 'member', 3, '3', 1, '0', '0', 'admin', '2022-03-29 22:59:55', 'admin', '2022-04-12 15:27:53', '工作室成员');
INSERT INTO `sys_role` VALUES (4, '历届负责人', 'pastmanage', 4, '1', 0, '0', '0', 'admin', '2022-04-08 13:38:41', 'admin', '2022-06-10 14:52:59', '历届负责人');
INSERT INTO `sys_role` VALUES (5, '指导老师', 'teacher', 5, '1', 1, '0', '0', 'admin', '2022-06-02 12:05:11', 'admin', '2022-06-12 20:47:46', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 7);
INSERT INTO `sys_role_menu` VALUES (2, 8);
INSERT INTO `sys_role_menu` VALUES (2, 9);
INSERT INTO `sys_role_menu` VALUES (2, 10);
INSERT INTO `sys_role_menu` VALUES (2, 11);
INSERT INTO `sys_role_menu` VALUES (2, 37);
INSERT INTO `sys_role_menu` VALUES (2, 38);
INSERT INTO `sys_role_menu` VALUES (2, 39);
INSERT INTO `sys_role_menu` VALUES (2, 40);
INSERT INTO `sys_role_menu` VALUES (2, 41);
INSERT INTO `sys_role_menu` VALUES (2, 42);
INSERT INTO `sys_role_menu` VALUES (2, 43);
INSERT INTO `sys_role_menu` VALUES (2, 44);
INSERT INTO `sys_role_menu` VALUES (2, 46);
INSERT INTO `sys_role_menu` VALUES (2, 47);
INSERT INTO `sys_role_menu` VALUES (2, 48);
INSERT INTO `sys_role_menu` VALUES (2, 49);
INSERT INTO `sys_role_menu` VALUES (2, 50);
INSERT INTO `sys_role_menu` VALUES (2, 51);
INSERT INTO `sys_role_menu` VALUES (2, 52);
INSERT INTO `sys_role_menu` VALUES (2, 53);
INSERT INTO `sys_role_menu` VALUES (2, 54);
INSERT INTO `sys_role_menu` VALUES (2, 55);
INSERT INTO `sys_role_menu` VALUES (2, 56);
INSERT INTO `sys_role_menu` VALUES (2, 57);
INSERT INTO `sys_role_menu` VALUES (2, 76);
INSERT INTO `sys_role_menu` VALUES (2, 77);
INSERT INTO `sys_role_menu` VALUES (2, 78);
INSERT INTO `sys_role_menu` VALUES (2, 79);
INSERT INTO `sys_role_menu` VALUES (2, 80);
INSERT INTO `sys_role_menu` VALUES (2, 84);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (3, 4);
INSERT INTO `sys_role_menu` VALUES (3, 5);
INSERT INTO `sys_role_menu` VALUES (3, 6);
INSERT INTO `sys_role_menu` VALUES (3, 19);
INSERT INTO `sys_role_menu` VALUES (3, 20);
INSERT INTO `sys_role_menu` VALUES (3, 21);
INSERT INTO `sys_role_menu` VALUES (3, 23);
INSERT INTO `sys_role_menu` VALUES (3, 24);
INSERT INTO `sys_role_menu` VALUES (3, 25);
INSERT INTO `sys_role_menu` VALUES (3, 26);
INSERT INTO `sys_role_menu` VALUES (3, 28);
INSERT INTO `sys_role_menu` VALUES (3, 29);
INSERT INTO `sys_role_menu` VALUES (3, 30);
INSERT INTO `sys_role_menu` VALUES (3, 31);
INSERT INTO `sys_role_menu` VALUES (3, 33);
INSERT INTO `sys_role_menu` VALUES (3, 34);
INSERT INTO `sys_role_menu` VALUES (3, 35);
INSERT INTO `sys_role_menu` VALUES (3, 36);
INSERT INTO `sys_role_menu` VALUES (3, 73);
INSERT INTO `sys_role_menu` VALUES (3, 74);
INSERT INTO `sys_role_menu` VALUES (3, 75);
INSERT INTO `sys_role_menu` VALUES (3, 83);
INSERT INTO `sys_role_menu` VALUES (4, 2);
INSERT INTO `sys_role_menu` VALUES (4, 7);
INSERT INTO `sys_role_menu` VALUES (4, 8);
INSERT INTO `sys_role_menu` VALUES (4, 9);
INSERT INTO `sys_role_menu` VALUES (4, 10);
INSERT INTO `sys_role_menu` VALUES (4, 11);
INSERT INTO `sys_role_menu` VALUES (4, 37);
INSERT INTO `sys_role_menu` VALUES (4, 41);
INSERT INTO `sys_role_menu` VALUES (4, 46);
INSERT INTO `sys_role_menu` VALUES (4, 49);
INSERT INTO `sys_role_menu` VALUES (4, 76);
INSERT INTO `sys_role_menu` VALUES (4, 77);
INSERT INTO `sys_role_menu` VALUES (4, 78);
INSERT INTO `sys_role_menu` VALUES (4, 79);
INSERT INTO `sys_role_menu` VALUES (4, 80);
INSERT INTO `sys_role_menu` VALUES (5, 2);
INSERT INTO `sys_role_menu` VALUES (5, 7);
INSERT INTO `sys_role_menu` VALUES (5, 8);
INSERT INTO `sys_role_menu` VALUES (5, 9);
INSERT INTO `sys_role_menu` VALUES (5, 10);
INSERT INTO `sys_role_menu` VALUES (5, 11);
INSERT INTO `sys_role_menu` VALUES (5, 37);
INSERT INTO `sys_role_menu` VALUES (5, 38);
INSERT INTO `sys_role_menu` VALUES (5, 39);
INSERT INTO `sys_role_menu` VALUES (5, 40);
INSERT INTO `sys_role_menu` VALUES (5, 41);
INSERT INTO `sys_role_menu` VALUES (5, 42);
INSERT INTO `sys_role_menu` VALUES (5, 43);
INSERT INTO `sys_role_menu` VALUES (5, 44);
INSERT INTO `sys_role_menu` VALUES (5, 46);
INSERT INTO `sys_role_menu` VALUES (5, 47);
INSERT INTO `sys_role_menu` VALUES (5, 48);
INSERT INTO `sys_role_menu` VALUES (5, 49);
INSERT INTO `sys_role_menu` VALUES (5, 50);
INSERT INTO `sys_role_menu` VALUES (5, 51);
INSERT INTO `sys_role_menu` VALUES (5, 52);
INSERT INTO `sys_role_menu` VALUES (5, 54);
INSERT INTO `sys_role_menu` VALUES (5, 55);
INSERT INTO `sys_role_menu` VALUES (5, 56);
INSERT INTO `sys_role_menu` VALUES (5, 57);
INSERT INTO `sys_role_menu` VALUES (5, 76);
INSERT INTO `sys_role_menu` VALUES (5, 77);
INSERT INTO `sys_role_menu` VALUES (5, 78);
INSERT INTO `sys_role_menu` VALUES (5, 79);
INSERT INTO `sys_role_menu` VALUES (5, 80);
INSERT INTO `sys_role_menu` VALUES (5, 84);
INSERT INTO `sys_role_menu` VALUES (7, 1);
INSERT INTO `sys_role_menu` VALUES (7, 2);
INSERT INTO `sys_role_menu` VALUES (7, 3);
INSERT INTO `sys_role_menu` VALUES (7, 7);
INSERT INTO `sys_role_menu` VALUES (7, 8);
INSERT INTO `sys_role_menu` VALUES (7, 9);
INSERT INTO `sys_role_menu` VALUES (7, 10);
INSERT INTO `sys_role_menu` VALUES (7, 11);
INSERT INTO `sys_role_menu` VALUES (7, 19);
INSERT INTO `sys_role_menu` VALUES (7, 20);
INSERT INTO `sys_role_menu` VALUES (7, 21);
INSERT INTO `sys_role_menu` VALUES (7, 37);
INSERT INTO `sys_role_menu` VALUES (7, 38);
INSERT INTO `sys_role_menu` VALUES (7, 39);
INSERT INTO `sys_role_menu` VALUES (7, 40);
INSERT INTO `sys_role_menu` VALUES (7, 41);
INSERT INTO `sys_role_menu` VALUES (7, 42);
INSERT INTO `sys_role_menu` VALUES (7, 43);
INSERT INTO `sys_role_menu` VALUES (7, 44);
INSERT INTO `sys_role_menu` VALUES (7, 46);
INSERT INTO `sys_role_menu` VALUES (7, 47);
INSERT INTO `sys_role_menu` VALUES (7, 48);
INSERT INTO `sys_role_menu` VALUES (7, 49);
INSERT INTO `sys_role_menu` VALUES (7, 50);
INSERT INTO `sys_role_menu` VALUES (7, 51);
INSERT INTO `sys_role_menu` VALUES (7, 52);
INSERT INTO `sys_role_menu` VALUES (7, 54);
INSERT INTO `sys_role_menu` VALUES (7, 55);
INSERT INTO `sys_role_menu` VALUES (7, 56);
INSERT INTO `sys_role_menu` VALUES (7, 57);
INSERT INTO `sys_role_menu` VALUES (7, 76);
INSERT INTO `sys_role_menu` VALUES (7, 77);
INSERT INTO `sys_role_menu` VALUES (7, 78);
INSERT INTO `sys_role_menu` VALUES (7, 79);
INSERT INTO `sys_role_menu` VALUES (7, 80);
INSERT INTO `sys_role_menu` VALUES (7, 84);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `real_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '真实姓名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '个人描述(用于官网展示)',
  `per_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '个人照片',
  `major_id` bigint(20) NULL DEFAULT NULL COMMENT '专业id',
  `grade_id` bigint(20) NULL DEFAULT NULL COMMENT '年级id',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '123', '00', 'cl@163.com', '15888888888', '1', 'http://127.0.0.1:8999/profile/21256e1a-7a49-46dc-810a-cd44b48ca743.png', '{noop}123', '0', '0', '127.0.0.1', '2022-03-28 19:44:08', 'admin', '2022-03-17 09:22:45', '', '2022-06-13 07:31:17', '管理员', '', '', '', 2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (18, 4);
INSERT INTO `sys_user_role` VALUES (19, 2);

-- ----------------------------
-- Table structure for zf_ccie
-- ----------------------------
DROP TABLE IF EXISTS `zf_ccie`;
CREATE TABLE `zf_ccie`  (
  `ccie_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '获奖证书主键id',
  `ccie_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '证书名称',
  `ccie_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '获奖证书图片',
  `ccci_get_time` datetime(0) NULL DEFAULT NULL COMMENT '获奖时间',
  `ccie_think` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '经验总结',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`ccie_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_ccie
-- ----------------------------

-- ----------------------------
-- Table structure for zf_grade
-- ----------------------------
DROP TABLE IF EXISTS `zf_grade`;
CREATE TABLE `zf_grade`  (
  `grade_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '年级主键id',
  `grade_num` int(20) NOT NULL COMMENT '年级',
  PRIMARY KEY (`grade_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_grade
-- ----------------------------

-- ----------------------------
-- Table structure for zf_major
-- ----------------------------
DROP TABLE IF EXISTS `zf_major`;
CREATE TABLE `zf_major`  (
  `major_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '专业主键id',
  `major_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业名称',
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_major
-- ----------------------------

-- ----------------------------
-- Table structure for zf_race
-- ----------------------------
DROP TABLE IF EXISTS `zf_race`;
CREATE TABLE `zf_race`  (
  `race_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '竞赛主键id',
  `race_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '竞赛名称',
  `race_members` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '竞赛成员（个人就填写一个id，存储参与成员的id，使用,分割）',
  `race_begin_time` datetime(0) NULL DEFAULT NULL COMMENT '竞赛开始时间',
  `race_end_time` datetime(0) NULL DEFAULT NULL COMMENT '竞赛结束时间',
  `race_ccie` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '获奖证书图片',
  `race_flag` tinyint(1) NULL DEFAULT 1 COMMENT '标识个人还是团队(1是个人,2是团队)',
  `race_summarize` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '经验总结',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`race_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_race
-- ----------------------------

-- ----------------------------
-- Table structure for zf_resource
-- ----------------------------
DROP TABLE IF EXISTS `zf_resource`;
CREATE TABLE `zf_resource`  (
  `res_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源主键id',
  `res_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '资源url地址',
  `res_flag` tinyint(1) NULL DEFAULT 1 COMMENT '资源标识(1表示竞赛团队)',
  `res_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '图片上传名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `table_id` bigint(20) NULL DEFAULT NULL COMMENT '关联表id(其他表主键id)',
  PRIMARY KEY (`res_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_resource
-- ----------------------------

-- ----------------------------
-- Table structure for zf_think
-- ----------------------------
DROP TABLE IF EXISTS `zf_think`;
CREATE TABLE `zf_think`  (
  `think_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '个人心得主键id',
  `think_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '思考标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '感悟思考',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户主键id',
  PRIMARY KEY (`think_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_think
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
