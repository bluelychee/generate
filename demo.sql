/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.75
Source Server Version : 50628
Source Host           : 192.168.1.75:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2018-06-25 16:43:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_ID` varchar(32) NOT NULL,
  `role_Name` varchar(20) NOT NULL,
  `status` int(10) NOT NULL,
  PRIMARY KEY (`role_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='status = 0:角色禁用    1:启用';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL COMMENT '用户ID',
  `org_id` varchar(32) DEFAULT NULL COMMENT '单位ID',
  `user_name` varchar(40) DEFAULT NULL COMMENT '用户名',
  `password_md5` varchar(100) DEFAULT NULL COMMENT '登录密码-MD5加密',
  `mobile` varchar(30) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '电子邮箱',
  `sex` int(1) DEFAULT NULL COMMENT '性别 0-未选择 1-男 2-女',
  `dept` varchar(255) DEFAULT NULL COMMENT '部门',
  `input_acc` varchar(32) DEFAULT NULL COMMENT '录入人',
  `input_time` datetime DEFAULT NULL COMMENT '录入时间',
  `update_acc` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` int(1) DEFAULT '0' COMMENT '用户状态 1:启用,0:禁用',
  `is_del` int(1) DEFAULT '0' COMMENT '是否删除 1:删除,0:未删除',
  PRIMARY KEY (`id`),
  KEY `user_name_INDEX` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('013570e5c3d8433889322169a93e30ca', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, '2018-05-15 09:04:31', '1', '0');
INSERT INTO `sys_user` VALUES ('01f49d71a76f4b059bea07038f220bbe', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('0603074443894055b37ec4ef8a340736', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('07bf127bbeab4769ae187998f09c893b', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('09383466a00b48088bee6f3e18ec95c0', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('0d93eb14b47a46bf9f0dcde67ed2d366', 'test', 'admin1222', null, '15868495022', '165465@qq.com', '1', null, 'admin', '2018-05-14 17:39:41', null, '2018-05-14 17:48:06', '0', '0');
INSERT INTO `sys_user` VALUES ('101d087e2232475ca19b3bcdee4be51a', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('15b1aae108404a6d93ed1566ca7bc526', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('1653c5e5a9b446dc9089b5fcd145e053', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('1965540b58aa4167b1b22b916654ebbf', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('1b7b5a3d55534004b87beddc4348f48d', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('1cb963fec5d34eabbe109c4f63260ca5', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('2095d85fc5634419a2a3516a06e8d6ca', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('219552df7d5945a6a04cbf1a92bcfd2a', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('2230923b32354f47b528a4add7c6e7b2', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('24b6034e7379408aa51db807c8a7415a', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('24b64f9e08314679bcebebaa4c16472b', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('25baca498ce04592999f0832c398df50', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('27a11443222d4c97bb355e28861fe1dc', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('28f7cfc9652949688c5e9c7a10278203', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('2a2e97ba1ba941a784ea4b7604bd6908', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('32fbcf321fc64fc78aa3233ab3e63e5e', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('34b099d17e0144cfb4a4e1df4c6c04e2', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('35672f9b2d6c4f3ab89e7197e871d401', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('3b2a7463436448df9047f2ee010a9864', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('3c1fc286d1424d59a06a8f134633f5de', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('3d1f0c0c2c90496eacfc140967042502', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('498992b2b00144b3a736cab4e638d7b2', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('4c3e8a8b6d8043559c6f5fdc08d90585', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('50f890784bee4882aba0bf9bce4c6bc0', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('51a94f1dd1b4456f8fb1768a5876490a', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('522f5e91d2ca4ab5a7d5506901dd4e1b', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('52fb6bbc82344a169a86666bd6515e04', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('5360290973c64775ba1ebf7de861ba17', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('54e84054d58b4584ae0ea4d1c64a4b78', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('5582ca0b9893493dae22af671dd1f721', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('5695d06a95394da292814a25cd07847b', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('5aaa80220bb74d93a86da9bdd5e99917', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('5ea00210c03448e0bfee4265072a078a', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('5eb7b21499a94a79968710948994dad8', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('61261a178813448080c9c176a66cfb2e', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('669d93378c094582a9f141446ab6d49c', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('673602665b53454e956102c3230cfcd4', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('6b62b62f7fbd4f89b9448544fb54cd5d', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('6b73f4b2c01341e19299c265adfa3690', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('6c60a4cbd5ee4f7c85828d25d4d80273', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('7b506a93e3f2481498f873809a493b7a', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('7efe88128ec7437b807ee34471546278', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('83a6c7d6298c4e3a9c9966934fd13ad8', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('85b4f89a7fd5457f8a576b414301b983', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('8b34413e8c5e4239a799059a04524440', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('8b57d2bca08641c2b384a708c6fcefbb', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('8c999a508d7e4a718e805de74e62886a', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('93def05369954fd59de91ce969ebf7b9', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('94a8072626e3435e9cb0d366e6d85049', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('96b4f7dab91a4259ac3f065584379b0f', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('9e297a5db1c04249939ba3e7ecb29640', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('9f28821a8c0f4c838c5b5535677a2daa', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('a286c316bd2b4086986a24bdc45c952a', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('a5dc828af2b941a9a4c8afd32b13a943', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('a72290849f7c492ea23a8df05e93c2aa', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('b3391088d63b4c49b8aea29763d41241', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('b4a90b73ecd54fa7a3819efb7cd21e7a', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('b56ae055008b48888db3d93e6eced84a', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('bbbc1a0a70054ebc9ffa4bdb3379ee42', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('bdf52a639fb245179aff0f70967f9d36', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('be11366f80934c49a39839bf375d3c30', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('bf0f66adea6f4dd5bee29770f98ce635', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('bfcd6b96e4104545a304271b20cbfbab', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('c06b8b4ddad94cfa81859e94d5e7bd88', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('c3f7678e76184d25b8f954ff53b8aa6c', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('c49b943d9f8d46fcbf9a9e176fce76c3', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('c684bacec6db40d4bbb668898f1c1b5e', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('c7e7f92c53c042359499885aa94b7dc5', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('cb2941eb0f854e91a93beb281b66a15c', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('cd0503485cb94d92a9a91f95e0d4f43f', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('ceaeaf3c073f4ff4916945f23d40544f', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('cf5647dd4b654139abca7ae6b3fea4b6', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('d0219783ee5e4e2da8877294beec2780', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('d1899933050f4dd4813c37c54fa6c890', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('d2bd07ab2bac4e03addfad888d56a691', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('d581a710a1554a0ead42c92b4e62d4a2', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('d62eca86de56434eba86c1405928bae1', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('d76ebf8996264855909ac4e637ef8573', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('d864fd8f1f084517a8f86252e6d41882', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('da66144b281f495698b65f6f2d7c9883', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('db01fd9d73744aa19e958aab74eb9640', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('ddc12da4e626439eb645ae6fd837ddc7', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('e25dc1d778ab4eb9b78aa403f0f618f9', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('e3f44c75906e4d83bd0d965d9ec93070', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('e6a9376931694a4b8c736f3b572b7483', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('eaeb5846623c4ec6b82bb1168cca3035', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('ec86707679014a9496e2037fe9bcbb62', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('eea8766492134b82b82bf1159eff70a6', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('f035db082c904b5097101944be80a61f', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('f34e88f387e2498089f2be8de7610a20', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('f7599a86c6ac47dd8712ba625f228af7', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('f9e9af3bc3f64a179139b83cbc2e4bc9', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('fb869804cdd34e0db0f886ac8157d576', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('fcb083ca04bb4339bf76cdaa11ca33f9', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
INSERT INTO `sys_user` VALUES ('fe2744ccd31349b9bcefcb954337c058', 'test', null, null, null, null, null, null, 'test1', '2018-05-14 17:44:10', null, null, null, '0');
