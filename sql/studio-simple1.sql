/*
 Navicat Premium Data Transfer

 Source Server         : linux（centos7）
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 192.168.3.83:3306
 Source Schema         : studio

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 25/09/2022 15:48:26
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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, '0', '0', 'admin', '2022-03-17 09:22:45', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '负责人', 'manage', 2, '2', 0, '0', '0', 'admin', '2022-03-28 21:02:19', 'admin', '2022-06-10 14:52:39', '工作室负责人');
INSERT INTO `sys_role` VALUES (3, '成员', 'member', 3, '3', 1, '0', '0', 'admin', '2022-03-29 22:59:55', 'admin', '2022-04-12 15:27:53', '工作室成员');
INSERT INTO `sys_role` VALUES (4, '历届负责人', 'pastmanage', 4, '1', 0, '0', '0', 'admin', '2022-04-08 13:38:41', 'admin', '2022-06-10 14:52:59', '历届负责人');
INSERT INTO `sys_role` VALUES (5, '指导老师', 'teacher', 5, '1', 1, '0', '0', 'admin', '2022-06-02 12:05:11', 'admin', '2022-06-11 16:58:04', NULL);

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
INSERT INTO `sys_role_menu` VALUES (6, 1);
INSERT INTO `sys_role_menu` VALUES (6, 2);
INSERT INTO `sys_role_menu` VALUES (6, 3);
INSERT INTO `sys_role_menu` VALUES (6, 4);
INSERT INTO `sys_role_menu` VALUES (6, 5);
INSERT INTO `sys_role_menu` VALUES (6, 6);
INSERT INTO `sys_role_menu` VALUES (6, 7);
INSERT INTO `sys_role_menu` VALUES (6, 8);
INSERT INTO `sys_role_menu` VALUES (6, 9);
INSERT INTO `sys_role_menu` VALUES (6, 10);
INSERT INTO `sys_role_menu` VALUES (6, 11);
INSERT INTO `sys_role_menu` VALUES (6, 12);
INSERT INTO `sys_role_menu` VALUES (6, 13);
INSERT INTO `sys_role_menu` VALUES (6, 14);
INSERT INTO `sys_role_menu` VALUES (6, 19);
INSERT INTO `sys_role_menu` VALUES (6, 20);
INSERT INTO `sys_role_menu` VALUES (6, 21);
INSERT INTO `sys_role_menu` VALUES (6, 23);
INSERT INTO `sys_role_menu` VALUES (6, 24);
INSERT INTO `sys_role_menu` VALUES (6, 25);
INSERT INTO `sys_role_menu` VALUES (6, 26);
INSERT INTO `sys_role_menu` VALUES (6, 28);
INSERT INTO `sys_role_menu` VALUES (6, 29);
INSERT INTO `sys_role_menu` VALUES (6, 30);
INSERT INTO `sys_role_menu` VALUES (6, 31);
INSERT INTO `sys_role_menu` VALUES (6, 33);
INSERT INTO `sys_role_menu` VALUES (6, 34);
INSERT INTO `sys_role_menu` VALUES (6, 35);
INSERT INTO `sys_role_menu` VALUES (6, 36);
INSERT INTO `sys_role_menu` VALUES (6, 37);
INSERT INTO `sys_role_menu` VALUES (6, 38);
INSERT INTO `sys_role_menu` VALUES (6, 39);
INSERT INTO `sys_role_menu` VALUES (6, 40);
INSERT INTO `sys_role_menu` VALUES (6, 41);
INSERT INTO `sys_role_menu` VALUES (6, 42);
INSERT INTO `sys_role_menu` VALUES (6, 43);
INSERT INTO `sys_role_menu` VALUES (6, 44);
INSERT INTO `sys_role_menu` VALUES (6, 46);
INSERT INTO `sys_role_menu` VALUES (6, 47);
INSERT INTO `sys_role_menu` VALUES (6, 48);
INSERT INTO `sys_role_menu` VALUES (6, 49);
INSERT INTO `sys_role_menu` VALUES (6, 50);
INSERT INTO `sys_role_menu` VALUES (6, 51);
INSERT INTO `sys_role_menu` VALUES (6, 52);
INSERT INTO `sys_role_menu` VALUES (6, 53);
INSERT INTO `sys_role_menu` VALUES (6, 54);
INSERT INTO `sys_role_menu` VALUES (6, 55);
INSERT INTO `sys_role_menu` VALUES (6, 56);
INSERT INTO `sys_role_menu` VALUES (6, 57);
INSERT INTO `sys_role_menu` VALUES (6, 58);
INSERT INTO `sys_role_menu` VALUES (6, 59);
INSERT INTO `sys_role_menu` VALUES (6, 60);
INSERT INTO `sys_role_menu` VALUES (6, 61);
INSERT INTO `sys_role_menu` VALUES (6, 62);
INSERT INTO `sys_role_menu` VALUES (6, 63);
INSERT INTO `sys_role_menu` VALUES (6, 64);
INSERT INTO `sys_role_menu` VALUES (6, 65);
INSERT INTO `sys_role_menu` VALUES (6, 66);
INSERT INTO `sys_role_menu` VALUES (6, 67);
INSERT INTO `sys_role_menu` VALUES (6, 68);
INSERT INTO `sys_role_menu` VALUES (6, 69);
INSERT INTO `sys_role_menu` VALUES (6, 70);
INSERT INTO `sys_role_menu` VALUES (6, 71);
INSERT INTO `sys_role_menu` VALUES (6, 72);
INSERT INTO `sys_role_menu` VALUES (6, 73);
INSERT INTO `sys_role_menu` VALUES (6, 74);
INSERT INTO `sys_role_menu` VALUES (6, 75);
INSERT INTO `sys_role_menu` VALUES (6, 76);
INSERT INTO `sys_role_menu` VALUES (6, 77);
INSERT INTO `sys_role_menu` VALUES (6, 78);
INSERT INTO `sys_role_menu` VALUES (6, 79);
INSERT INTO `sys_role_menu` VALUES (6, 80);
INSERT INTO `sys_role_menu` VALUES (6, 81);
INSERT INTO `sys_role_menu` VALUES (6, 82);
INSERT INTO `sys_role_menu` VALUES (6, 83);
INSERT INTO `sys_role_menu` VALUES (6, 84);
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
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '123', '00', 'cl@163.com', '15888888888', '1', 'http://studio.codercl.xyz/studio/static/5d0b05ed-c3b7-4fa5-a87a-d6f6bb9df6d1.png', '{noop}123', '0', '0', '127.0.0.1', '2022-03-28 19:44:08', 'admin', '2022-03-17 09:22:45', '', '2022-08-22 07:39:00', '管理员', '', '热爱生活，热爱coding...1', 'http://studio.codercl.xyz/studio/static/06f7323b-12c2-469c-b721-1b1b0bf6293d.jpg', 2, 3);
INSERT INTO `sys_user` VALUES (29, '2020tongxue1', '2020tongxue1', '00', '', '', '0', '', '{bcrypt}$2a$10$tmU.D2KvbgFI/JAciQHvJOd5emZn1QZtdbVNpbP9u/P5mCjUjdF06', '0', '0', '', NULL, '', '2022-06-11 16:06:07', '', '2022-06-13 08:56:36', NULL, '路飞', '骷髅的旗帜,是信念的象征；每个人都有梦想,去实现吧!；我们是伙(同)伴啊!', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/9f295c18-c124-4b25-bfde-a385c4286d81.png', 1, 3);
INSERT INTO `sys_user` VALUES (30, '2020tongxue2', '2020tongxue2', '00', '', '', '0', '', '{bcrypt}$2a$10$69mpAoGpyBTTyteU60i66OzwYGOoeZns.YpLs8h9WghJyxbaff74O', '0', '0', '', NULL, '', '2022-06-11 16:06:18', '', '2022-06-13 08:56:18', NULL, '龙猫', '心存善意,定能途遇天使；大声地笑出来,就不那么害怕了；没问题的,别担心,一切都会好的', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/de5b8775-1a17-4761-aa7d-9cf78d36e21d.png', 6, 3);
INSERT INTO `sys_user` VALUES (31, '2021tongxue1', '2021tongxue1', '00', '', '', '0', '', '{bcrypt}$2a$10$PtSycSpaWNEvzhEd/motMOIPurDZtkpEV2K2d2x0.1kfFGRKhzhiu', '0', '0', '', NULL, '', '2022-06-11 16:06:26', '', '2022-06-13 08:55:56', NULL, '灰太狼', '立志要如山 ,行道要如水。不如山 ,不能坚定;不如水 ,不能曲达', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/97bf7c67-6c39-4330-827d-078cf1391822.png', 5, 2);
INSERT INTO `sys_user` VALUES (32, '2021tongxue2', '2021tongxue2', '00', '', '', '0', '', '{bcrypt}$2a$10$RMY/MJE9OCYTSNb37DcsWeIYInxGvNYrDsOW.epvCkigoQjFV2mmm', '0', '0', '', NULL, '', '2022-06-11 16:06:39', '', '2022-06-13 08:55:39', NULL, '柯南', '真相永远只有一个；当然是因为我喜欢你 比地球上任何一个人都喜欢你！人讲的话就像刀刃，要是用错了就会变成棘手的凶器。', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/7949d189-3f26-4efc-8997-776cb4c9ddc6.png', 4, 2);
INSERT INTO `sys_user` VALUES (33, '2022tongxue1', '2022tongxue1', '00', '', '', '0', '', '{bcrypt}$2a$10$zCQioXgzYA8hRz.IUwuqOO9.TLoZBVlbSUJH1iRL4KM4Kz9Nb3Ow2', '0', '0', '', NULL, '', '2022-06-11 16:06:52', '', '2022-06-13 08:55:16', NULL, '哪吒', '去他个鸟命!我命由我,不由天!是魔是仙,我自己决定!生活你全是泪,越是折腾越倒霉,垂死挣扎你累不累,不如瘫在床上睡', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/2db68433-a646-4cca-94d7-145ec9b121dd.png', 2, 1);
INSERT INTO `sys_user` VALUES (34, '2021tongxue3', '2021tongxue3', '00', '', '', '0', '', '{bcrypt}$2a$10$njo41Ttde4szVwxljIztLOHXJjEQ24Bl1zFE8VT.Nx54fy.zBltum', '0', '0', '', NULL, '', '2022-06-11 16:07:03', '', '2022-06-13 08:55:01', NULL, '喜羊羊', '这一次，换我来保护你们吧！有本事来抓我啊！', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/160cf775-4599-4aff-9310-cff551647003.png', 1, 2);
INSERT INTO `sys_user` VALUES (35, 'dijia', 'dijia', '00', '', '', '0', '', '{bcrypt}$2a$10$bCR/RZArgtNOm6qLxal65OkgvT5JoG4kkFC.eakMNYPUmpN5/Wcm2', '0', '0', '', NULL, '', '2022-06-11 16:26:37', '', '2022-06-13 08:54:05', NULL, '迪迦', '大古，我感觉我们正失去一些美好的东西，善良温柔体贴——这些属于我们人类美好的本质——好像正慢慢淡化!', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/a93b7517-1f38-47ab-abe9-72fd297beccf.png', 2, 4);
INSERT INTO `sys_user` VALUES (36, 'yiquanchaoren', 'yiquanchaoren', '00', '', '', '0', '', '{bcrypt}$2a$10$NHa5IjkH.wNnqA2bFfRco.3j2SGgPP3bWt5MCvQqnpvBPhGmkG2pC', '0', '0', '', NULL, '', '2022-06-11 16:28:18', '', '2022-06-13 08:54:23', NULL, '一拳超人', '世界上,没有一拳解决不了的事情。如果有,没有如果；只要你仍是因妥协成就的你,就永远战胜不了因兴趣铸就的我! ', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/beb50ee0-611d-4081-a65d-277c9d2d0b14.png', 2, 4);
INSERT INTO `sys_user` VALUES (37, 'xiaozhi', 'xiaozhi', '00', '', '', '0', '', '{bcrypt}$2a$10$VlORBlfCJIA4hYLcgd3aSOcWlwrkZVfmBEsyWeeqR6H/xtaC8ucdO', '0', '0', '', NULL, '', '2022-06-11 16:30:10', '', '2022-06-13 08:54:45', NULL, '小智', '我相信你', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/c7279fb3-7fb0-4ab4-9f1d-e0c51b503845.png', 2, 2);
INSERT INTO `sys_user` VALUES (38, 'yingmuhuadao', 'yingmuhuadao', '00', '', '', '0', '', '{bcrypt}$2a$10$7M5pOD/XqTfd1wdbD2eKnOf.3wuxCoxb21V3ofasPWlGQxWLyRUHq', '0', '0', '', NULL, '', '2022-06-11 16:35:08', '', '2022-06-13 08:53:39', NULL, '樱木花道', '本人是天才篮球手樱木花道,看着,看我英勇的姿势!外面明明是春天,而我的心却是寒冬。我的春天来了,我的春天终于来了!我最喜', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/816a00da-8c45-4e4c-a856-026b00242ca7.png', 1, 1);
INSERT INTO `sys_user` VALUES (39, 'labixiaoxin', 'labixiaoxin', '00', '', '', '0', '', '{bcrypt}$2a$10$Mf9TBwG9ZDWFa4rlKhYh0eRkb675yolZjW1p2V1i9BBu/WRi81tBC', '0', '0', '', NULL, '', '2022-06-11 16:36:46', '', '2022-06-13 08:53:23', NULL, '蜡笔小新', '大家好!我是野原新之助,大家都叫我小新,我今年五岁,喜欢的颜色是白色,喜欢的内裤图案是动感超人哦!', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/8a750268-4227-497b-b106-6b5133c3a713.png', 1, 1);
INSERT INTO `sys_user` VALUES (40, 'qianxun', 'qianxun', '00', '', '', '0', '', '{bcrypt}$2a$10$KdteBr0xtuyF56gPRaCUteDz0q9q02JP.U3uvER0q2CkseP6yXeJC', '0', '0', '', NULL, '', '2022-06-11 16:38:39', '', '2022-06-13 08:53:07', NULL, '千寻', '不管前方的路有多崎岖，只要方向正确，都比站在原地更接近幸福。曾经发生过的事情不可能忘记，只不过是想不起而已。', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/f0e590cf-0895-4776-a5cb-e038a54c0b71.png', 2, 1);
INSERT INTO `sys_user` VALUES (41, 'bailong', 'bailong', '00', '', '', '0', '', '{bcrypt}$2a$10$UJLZ5GrEeJVMw2BHa1fgaOEC1sTPxbUy0DZAabX5jI41E9T2QHrr2', '0', '0', '', NULL, '', '2022-06-11 16:40:43', '', '2022-06-13 08:52:53', NULL, '白龙', '我不知道将去何方,但我已在路上。我们会在原来的世界相遇吗,会的一定会,重要的人就算走到哪里都不会忘记。千万不可以丢失自我', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/d167b054-4158-474b-8e9f-91bba112b569.png', 5, 1);
INSERT INTO `sys_user` VALUES (42, 'mingren', 'mingren', '00', '', '', '0', '', '{bcrypt}$2a$10$GBWwxrzJwfitNZPwyladNuw3e4Fu6MVdctg/x7rtCAJjUaUWR2z.u', '0', '0', '', NULL, '', '2022-06-11 16:42:10', '', '2022-06-13 08:52:37', NULL, '鸣人', '我决定了,从今天起,我要选择一条不会让自己后悔的路。我要创造出属于自己的忍道!你别小看我!我不会临阵脱逃!', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/00a92046-6111-446f-bfeb-87da9ff87686.png', 3, 1);
INSERT INTO `sys_user` VALUES (43, 'sunwukong', 'sunwukong', '00', '', '', '0', '', '{bcrypt}$2a$10$NCZPA/amp3JHqZpOIN29QOWxRgDqCT9z4K5kJPJN..ZnSe3S.ACrG', '0', '0', '', NULL, '', '2022-06-11 16:44:07', '', '2022-06-13 08:52:03', NULL, '孙悟空', '我是在地球上成长的赛亚人!这就是那个魔人布欧么?好像不怎么强的样子。这家伙我一个人就够了!', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/39d94b94-87b0-4498-af7a-f8581b1d5cc2.png', 4, 2);
INSERT INTO `sys_user` VALUES (44, 'duolaameng', 'duolaameng', '00', '', '', '0', '', '{bcrypt}$2a$10$aEDy2e2eH5VUhfgMe5eA1ObHRbFAKqdXkXoQnfQDGA84lnxiBj54i', '0', '0', '', NULL, '', '2022-06-11 16:46:32', '', '2022-06-13 08:51:44', NULL, '哆啦a梦', '人的眼睛为什么长在前面？为的就是要你不断的前进，不要总是回想过去的事，应该以明天为目标。梦想是一个天真的词，实现梦想是一', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/afd2223d-e2b6-4af0-9614-7e7289744089.png', 5, 2);
INSERT INTO `sys_user` VALUES (45, 'tiejiaxiaobao', 'tiejiaxiaobao', '00', '', '', '0', '', '{bcrypt}$2a$10$dLTfV3nwqO6fHLH2/xb9O.5LbDvsFnoOrsGYRB/H.cIcVG4fpBOw2', '0', '0', '', NULL, '', '2022-06-11 16:48:31', '', '2022-06-13 08:51:27', NULL, '铁甲小宝', '是男人就该默默地工作, 嘿嘿 !绝对不能让你得到和平星。这就是友情的力量。', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/1b4e014a-6a4c-4c3c-a52f-201e770d4ea4.png', 1, 3);
INSERT INTO `sys_user` VALUES (46, 'yixiu', 'yixiu', '00', '', '', '0', '', '{bcrypt}$2a$10$JBAypKNClWhF0l84B0Nax.pahuiBfcra1ea8TBDm6gTLxOKgMW3eG', '0', '0', '', NULL, '', '2022-06-11 16:50:41', '', '2022-06-13 08:50:39', NULL, '一休', '明白了,母亲大人的意思是想提醒我不要自满对吧。不要着急,不要着急,休息,休息一会。我已经吃得饱饱的了,你就别客气了。', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/5e988da5-f631-4537-8814-094d8badc61e.png', 4, 3);
INSERT INTO `sys_user` VALUES (47, 'changlu', 'changlu', '00', '', '', '0', '', '{bcrypt}$2a$10$sEcpna7ypjEuTR4AxvqY7ua5J4ukFb48nHGmTCeYTkr23FEAbFI9.', '0', '0', '', NULL, '', '2022-06-11 16:52:46', '', '2022-06-13 08:51:04', NULL, '长路', '每个人都是独一无二的，把握好自己的节奏，跟着自己的心走。', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/c2d7f120-cb60-47cc-91da-8f55bd2d285c.png', 3, 4);

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
INSERT INTO `sys_user_role` VALUES (29, 3);
INSERT INTO `sys_user_role` VALUES (30, 3);
INSERT INTO `sys_user_role` VALUES (31, 3);
INSERT INTO `sys_user_role` VALUES (32, 2);
INSERT INTO `sys_user_role` VALUES (32, 3);
INSERT INTO `sys_user_role` VALUES (33, 3);
INSERT INTO `sys_user_role` VALUES (34, 3);
INSERT INTO `sys_user_role` VALUES (35, 2);
INSERT INTO `sys_user_role` VALUES (35, 3);
INSERT INTO `sys_user_role` VALUES (36, 3);
INSERT INTO `sys_user_role` VALUES (37, 3);
INSERT INTO `sys_user_role` VALUES (38, 3);
INSERT INTO `sys_user_role` VALUES (39, 3);
INSERT INTO `sys_user_role` VALUES (40, 3);
INSERT INTO `sys_user_role` VALUES (41, 2);
INSERT INTO `sys_user_role` VALUES (41, 3);
INSERT INTO `sys_user_role` VALUES (42, 3);
INSERT INTO `sys_user_role` VALUES (43, 3);
INSERT INTO `sys_user_role` VALUES (44, 3);
INSERT INTO `sys_user_role` VALUES (45, 3);
INSERT INTO `sys_user_role` VALUES (46, 2);
INSERT INTO `sys_user_role` VALUES (46, 3);
INSERT INTO `sys_user_role` VALUES (47, 3);
INSERT INTO `sys_user_role` VALUES (47, 5);
INSERT INTO `sys_user_role` VALUES (48, 3);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_ccie
-- ----------------------------
INSERT INTO `zf_ccie` VALUES (1, '证书1', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/2386faa8-98e0-42c1-a91b-f36f7a339982.png', '2022-05-31 00:00:00', '<p>加油</p>', 41);
INSERT INTO `zf_ccie` VALUES (2, '证书2', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/c99b593a-4ee4-4610-b813-76545542a7dd.png', '2022-06-09 00:00:00', '<p><br></p>', 41);
INSERT INTO `zf_ccie` VALUES (3, '证书3', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/1a0b4ec0-8bba-4734-975e-12b7391b556f.png', '2022-06-04 00:00:00', '<p>加油呀</p>', 41);

-- ----------------------------
-- Table structure for zf_grade
-- ----------------------------
DROP TABLE IF EXISTS `zf_grade`;
CREATE TABLE `zf_grade`  (
  `grade_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '年级主键id',
  `grade_num` int(20) NOT NULL COMMENT '年级',
  PRIMARY KEY (`grade_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_grade
-- ----------------------------
INSERT INTO `zf_grade` VALUES (1, 2022);
INSERT INTO `zf_grade` VALUES (2, 2021);
INSERT INTO `zf_grade` VALUES (3, 2020);
INSERT INTO `zf_grade` VALUES (4, 2019);
INSERT INTO `zf_grade` VALUES (5, 2018);
INSERT INTO `zf_grade` VALUES (6, 2017);

-- ----------------------------
-- Table structure for zf_major
-- ----------------------------
DROP TABLE IF EXISTS `zf_major`;
CREATE TABLE `zf_major`  (
  `major_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '专业主键id',
  `major_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业名称',
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_major
-- ----------------------------
INSERT INTO `zf_major` VALUES (1, '计算机科学');
INSERT INTO `zf_major` VALUES (2, '软件工程');
INSERT INTO `zf_major` VALUES (3, '计算机应用技术');
INSERT INTO `zf_major` VALUES (4, '物联网技术');
INSERT INTO `zf_major` VALUES (5, '虚拟现实');
INSERT INTO `zf_major` VALUES (6, '人工智能');

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_race
-- ----------------------------
INSERT INTO `zf_race` VALUES (1, '竞赛1', '41', '2022-06-09 00:00:00', '2022-06-29 00:00:00', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/956efb84-dae2-47d6-9056-1b05a544463a.png', 1, '<p>好好比赛加油</p>', '2022-06-11 10:09:15', '2022-06-13 08:58:06');
INSERT INTO `zf_race` VALUES (2, '竞赛2', '41', '2022-05-31 00:00:00', '2022-06-17 00:00:00', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/ee8eb365-7f91-41c4-ba28-edbe9ac64b90.png', 1, '<p>加油</p>', '2022-06-11 10:09:36', '2022-06-13 08:59:00');
INSERT INTO `zf_race` VALUES (3, '团体赛', '29,32,30,33,41', '2022-06-08 00:00:00', '2022-06-17 00:00:00', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/0ad449b0-079d-4789-bf28-87ed6a47dd48.png', 2, '<p>大家努力取得好成绩</p>', '2022-06-11 10:10:19', '2022-06-13 08:58:28');
INSERT INTO `zf_race` VALUES (4, '团队赛2', '29,30,40', '2022-06-01 00:00:00', '2022-06-16 00:00:00', 'http://127.0.0.1:8999/profile/e96f446f-58e2-4d19-92ff-e60fe15fca5a.png', 2, '<p>参赛记录</p>', '2022-06-11 10:19:09', NULL);
INSERT INTO `zf_race` VALUES (5, '竞赛1', '38', '2022-06-02 00:00:00', '2022-06-22 00:00:00', 'http://127.0.0.1:8999/profile/3c6f5115-256c-4273-9168-91e2412c91ed.png', 1, NULL, '2022-06-11 10:20:28', NULL);
INSERT INTO `zf_race` VALUES (6, '竞赛22', '38', '2022-06-10 00:00:00', '2022-06-25 00:00:00', 'http://127.0.0.1:8999/profile/e3914b5f-ae97-4cef-ac16-8f471e517a62.png', 1, NULL, '2022-06-11 10:20:48', NULL);
INSERT INTO `zf_race` VALUES (7, '竞赛111', '47', '2022-06-17 00:00:00', '2022-06-23 00:00:00', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/90c0a95a-18dd-44d7-b5f8-b52515d29ba0.png', 1, NULL, '2022-06-11 10:21:38', '2022-06-13 09:00:50');
INSERT INTO `zf_race` VALUES (8, '竞赛1111', '35', '2019-05-01 00:00:00', '2019-08-24 00:00:00', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/bee92646-b52b-4fe9-bf79-f7a4a919a77a.png', 1, NULL, '2022-06-11 10:22:31', '2022-06-13 08:59:39');
INSERT INTO `zf_race` VALUES (9, '竞赛111', '35', '2020-06-01 00:00:00', '2020-06-25 00:00:00', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/73fac460-1c4a-40a3-8ac1-ca6e45a20608.png', 1, '<p>加油</p>', '2022-06-12 10:18:12', '2022-06-13 08:59:59');
INSERT INTO `zf_race` VALUES (10, '竞赛3', '35', '2021-06-01 00:00:00', '2021-06-18 00:00:00', 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/4bef549f-ccf7-47a0-97a3-be46831782e7.png', 1, '<p>加油</p>', '2022-06-12 10:22:51', '2022-06-13 09:00:18');

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
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_resource
-- ----------------------------
INSERT INTO `zf_resource` VALUES (13, 'http://127.0.0.1:8999/profile/fad77e4b-edd3-4dd5-a1e8-ea6fd3d8a66e.jpg', 1, 'fad77e4b-edd3-4dd5-a1e8-ea6fd3d8a66e.jpg', '2022-06-11 10:19:09', 4);
INSERT INTO `zf_resource` VALUES (14, 'http://127.0.0.1:8999/profile/18f012ff-39c7-4d26-9332-184256a96e2c.jpg', 1, '18f012ff-39c7-4d26-9332-184256a96e2c.jpg', '2022-06-11 10:19:09', 4);
INSERT INTO `zf_resource` VALUES (15, 'http://127.0.0.1:8999/profile/5ec1ae9e-f3c9-4a4f-9f28-3eabd4fc8bb3.jpg', 1, '5ec1ae9e-f3c9-4a4f-9f28-3eabd4fc8bb3.jpg', '2022-06-11 10:19:09', 4);
INSERT INTO `zf_resource` VALUES (16, 'http://127.0.0.1:8999/profile/4f543f04-f65c-41cd-898b-85be6f672eda.jpg', 1, '4f543f04-f65c-41cd-898b-85be6f672eda.jpg', '2022-06-11 10:20:28', 5);
INSERT INTO `zf_resource` VALUES (17, 'http://127.0.0.1:8999/profile/5ebd433a-a19b-4a23-8431-7c43b6604f04.jpg', 1, '5ebd433a-a19b-4a23-8431-7c43b6604f04.jpg', '2022-06-11 10:20:28', 5);
INSERT INTO `zf_resource` VALUES (18, 'http://127.0.0.1:8999/profile/adcba92b-b0e7-4f23-a4d0-7ef1be1e7f2b.jpg', 1, 'adcba92b-b0e7-4f23-a4d0-7ef1be1e7f2b.jpg', '2022-06-11 10:20:28', 5);
INSERT INTO `zf_resource` VALUES (19, 'http://127.0.0.1:8999/profile/e88fdd2f-4d32-4a46-8ef3-d6d06931574f.jpg', 1, 'e88fdd2f-4d32-4a46-8ef3-d6d06931574f.jpg', '2022-06-11 10:20:48', 6);
INSERT INTO `zf_resource` VALUES (20, 'http://127.0.0.1:8999/profile/e52e3fa6-1783-4aa1-a379-1e707dadadbd.jpg', 1, 'e52e3fa6-1783-4aa1-a379-1e707dadadbd.jpg', '2022-06-11 10:20:48', 6);
INSERT INTO `zf_resource` VALUES (21, 'http://127.0.0.1:8999/profile/050685a5-ddf0-4308-9952-6b48472b9bdf.jpg', 1, '050685a5-ddf0-4308-9952-6b48472b9bdf.jpg', '2022-06-11 10:20:48', 6);
INSERT INTO `zf_resource` VALUES (37, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/7a4665d8-d973-4759-972a-d3e43a567fcb.jpg', 1, '7a4665d8-d973-4759-972a-d3e43a567fcb.jpg', '2022-06-13 08:58:06', 1);
INSERT INTO `zf_resource` VALUES (38, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/f9bb24f6-3182-403c-9bdc-aa0f3b331579.jpg', 1, 'f9bb24f6-3182-403c-9bdc-aa0f3b331579.jpg', '2022-06-13 08:58:06', 1);
INSERT INTO `zf_resource` VALUES (39, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/3737a231-1d5f-4d02-a576-aa6d533b8bca.jpg', 1, '3737a231-1d5f-4d02-a576-aa6d533b8bca.jpg', '2022-06-13 08:58:06', 1);
INSERT INTO `zf_resource` VALUES (40, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/3b4cb11a-3fc7-4b13-9dc6-b7be8f2dfa08.jpg', 1, '3b4cb11a-3fc7-4b13-9dc6-b7be8f2dfa08.jpg', '2022-06-13 08:58:28', 3);
INSERT INTO `zf_resource` VALUES (41, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/19e9e46d-026e-4aca-8ed8-6d6fb309f4bf.jpg', 1, '19e9e46d-026e-4aca-8ed8-6d6fb309f4bf.jpg', '2022-06-13 08:58:28', 3);
INSERT INTO `zf_resource` VALUES (42, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/9d61d437-e359-4f24-a0bb-79aa6a2ac776.jpg', 1, '9d61d437-e359-4f24-a0bb-79aa6a2ac776.jpg', '2022-06-13 08:58:28', 3);
INSERT INTO `zf_resource` VALUES (43, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/886ae690-1b00-4ce0-92dd-293ad682924a.jpg', 1, '886ae690-1b00-4ce0-92dd-293ad682924a.jpg', '2022-06-13 08:59:00', 2);
INSERT INTO `zf_resource` VALUES (44, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/158a5e96-92c8-4325-94b3-64fa98570b95.jpg', 1, '158a5e96-92c8-4325-94b3-64fa98570b95.jpg', '2022-06-13 08:59:00', 2);
INSERT INTO `zf_resource` VALUES (45, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/18f04e5d-f08f-43c4-9f42-e7107f770db7.jpg', 1, '18f04e5d-f08f-43c4-9f42-e7107f770db7.jpg', '2022-06-13 08:59:00', 2);
INSERT INTO `zf_resource` VALUES (46, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/85b4c064-1bb8-4bf8-90c8-aed9b25843e5.jpg', 1, '85b4c064-1bb8-4bf8-90c8-aed9b25843e5.jpg', '2022-06-13 08:59:39', 8);
INSERT INTO `zf_resource` VALUES (47, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/942ebbe6-da4b-4cc1-9859-3ef8c253ce0d.jpg', 1, '942ebbe6-da4b-4cc1-9859-3ef8c253ce0d.jpg', '2022-06-13 08:59:39', 8);
INSERT INTO `zf_resource` VALUES (48, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/1ac4faf6-3107-43c4-b105-1ed345977e45.jpg', 1, '1ac4faf6-3107-43c4-b105-1ed345977e45.jpg', '2022-06-13 08:59:39', 8);
INSERT INTO `zf_resource` VALUES (49, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/8bb637f4-ae5c-417e-8161-5ce9132426e5.jpg', 1, '8bb637f4-ae5c-417e-8161-5ce9132426e5.jpg', '2022-06-13 08:59:59', 9);
INSERT INTO `zf_resource` VALUES (50, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/d08e2bf4-f048-4e8d-9e03-db66e62ba837.jpg', 1, 'd08e2bf4-f048-4e8d-9e03-db66e62ba837.jpg', '2022-06-13 08:59:59', 9);
INSERT INTO `zf_resource` VALUES (51, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/5d815874-50f7-44df-b70c-c1514d04e4d4.jpg', 1, '5d815874-50f7-44df-b70c-c1514d04e4d4.jpg', '2022-06-13 08:59:59', 9);
INSERT INTO `zf_resource` VALUES (52, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/92fc3c4b-dc9c-4796-8a9f-db35e276942e.jpg', 1, '92fc3c4b-dc9c-4796-8a9f-db35e276942e.jpg', '2022-06-13 09:00:18', 10);
INSERT INTO `zf_resource` VALUES (53, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/362522dc-6273-477e-89ec-b9dba637e918.jpg', 1, '362522dc-6273-477e-89ec-b9dba637e918.jpg', '2022-06-13 09:00:18', 10);
INSERT INTO `zf_resource` VALUES (54, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/668af52b-98d2-4b1f-8926-ddf423153aa0.jpg', 1, '668af52b-98d2-4b1f-8926-ddf423153aa0.jpg', '2022-06-13 09:00:18', 10);
INSERT INTO `zf_resource` VALUES (55, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/67b829ef-ca83-437d-9afd-1f082eac01fb.jpg', 1, '67b829ef-ca83-437d-9afd-1f082eac01fb.jpg', '2022-06-13 09:00:50', 7);
INSERT INTO `zf_resource` VALUES (56, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/c0894ee4-0b9b-4fd5-99f9-8bbb0a17537c.jpg', 1, 'c0894ee4-0b9b-4fd5-99f9-8bbb0a17537c.jpg', '2022-06-13 09:00:50', 7);
INSERT INTO `zf_resource` VALUES (57, 'http://pictured-bedtest.oss-cn-beijing.aliyuncs.com/test/studio/958a7e5e-c6e9-4691-866b-16066bb2a53a.jpg', 1, '958a7e5e-c6e9-4691-866b-16066bb2a53a.jpg', '2022-06-13 09:00:50', 7);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of zf_think
-- ----------------------------
INSERT INTO `zf_think` VALUES (1, '大学历程', '<p>思考总结...</p>', '2022-06-12 10:27:01', NULL, 41);

SET FOREIGN_KEY_CHECKS = 1;
