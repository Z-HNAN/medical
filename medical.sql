/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50559
Source Host           : 127.0.0.1:3306
Source Database       : medical

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2018-11-27 21:03:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_state` tinyint(4) DEFAULT NULL,
  `applier_id` bigint(20) DEFAULT NULL,
  `illness_type` tinyint(4) DEFAULT NULL,
  `serious_type_id` bigint(20) DEFAULT NULL,
  `hospital` varchar(50) DEFAULT NULL,
  `in_hospital_date` date DEFAULT NULL,
  `out_hospital_date` date DEFAULT NULL,
  `apply_money` decimal(18,4) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `bill_img` varchar(255) DEFAULT NULL,
  `com_dept_id` bigint(20) DEFAULT NULL,
  `auditor_id` bigint(20) DEFAULT NULL,
  `audit_date` date DEFAULT NULL,
  `note` varchar(20) DEFAULT NULL,
  `audit_money` decimal(18,4) DEFAULT NULL,
  `apply_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES ('1', '0', '1', '2', '9', '村东头医院', '2018-11-12', '2018-11-13', '200.0000', '去东头医院看了下，为何总被自己帅醒！去东头医院看了下，为何总被自己帅醒！', null, '2', '8', '2018-11-17', '就两块不给你', '198.0000', '2018-11-13');
INSERT INTO `bill` VALUES ('4', '1', '1', '2', '2', '村西头', '2018-11-12', '2018-11-13', '99.9900', '100块都不给我？', null, '2', '8', '2018-11-16', '还能不能凑0.01分！强迫症', '0.0000', '2018-11-15');
INSERT INTO `bill` VALUES ('5', '0', '1', '1', null, '村北头的', '2018-11-12', '2018-11-16', '0.0000', '你看我一分不花，就来测试一下', null, '2', '8', '2018-11-16', '反正我又不花钱！过！', '0.0000', '2018-11-14');
INSERT INTO `bill` VALUES ('6', '1', '1', '2', '1', '路边老中医', '2018-11-16', '2018-11-17', '998.0000', '我去老中医那里算了一挂，不知道能不能报销啊', null, '2', '8', '2018-11-27', '你又不是买手机，还998', null, '2018-11-17');
INSERT INTO `bill` VALUES ('7', '0', '1', '2', '2', '村北头小诊所', '2018-11-18', '2018-11-19', '111.1100', '治疗一下哥的帅气！', null, '2', '8', '2018-11-19', '啥也不说了 给你100块玩去吧', '100.0000', '2018-11-19');
INSERT INTO `bill` VALUES ('8', '0', '13', '2', '2', '村南头医院啊', '2018-11-19', '2018-11-20', '198.8800', '行行好，让我测试过一下吧', null, '5', '9', '2018-11-20', '过！全额给你过！', '198.8800', '2018-11-20');
INSERT INTO `bill` VALUES ('9', '0', '12', '1', null, '5873', '2018-11-27', '2020-11-27', '89.0000', '83', null, '5', '9', '2018-11-27', '80不能再多了', '80.0000', '2018-11-27');
INSERT INTO `bill` VALUES ('10', '0', '10', '0', null, '23454', '2018-02-02', '2018-02-03', '543.0000', '455435', null, '2', '8', '2018-11-27', '多拿点', '600.0000', '2018-11-27');
INSERT INTO `bill` VALUES ('11', '1', '10', '0', null, '23454', '2018-02-02', '2018-02-03', '543.0000', '455435', null, '2', '8', '2018-11-27', '停车了', null, '2018-11-27');
INSERT INTO `bill` VALUES ('12', '1', '10', '0', null, '23454', '2018-02-02', '2018-02-03', '543.0000', '455435', null, '2', '8', '2018-11-27', '依然停车', null, '2018-11-27');
INSERT INTO `bill` VALUES ('13', '0', '11', '2', '9', 'sjsjs', '2018-11-27', '2018-11-28', '2.0000', '', null, '2', '8', '2018-11-27', '多拿点', '200.0000', '2018-11-27');
INSERT INTO `bill` VALUES ('14', '0', '1', '2', '10', '校医院', '2018-11-26', '2018-11-27', '10.0000', '被车撞了，疼的一批，给点钱吧', null, '2', '8', '2018-11-27', '坚强点！给你精神支持', '0.0000', '2018-11-27');
INSERT INTO `bill` VALUES ('15', '1', '1', '1', null, 'sdadsadada', '2018-11-04', '2018-11-07', '1000.0000', '123', null, '2', '8', '2018-11-27', '', '0.0000', '2018-11-27');
INSERT INTO `bill` VALUES ('16', '0', '11', '2', '1', 'sdadsad', '2018-11-02', '2018-11-04', '10000.0000', '12312', null, '2', '8', '2018-11-27', '就剩100了', '100.0000', '2018-11-27');

-- ----------------------------
-- Table structure for com_dept
-- ----------------------------
DROP TABLE IF EXISTS `com_dept`;
CREATE TABLE `com_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `com_dept_name` varchar(20) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `parent_com_dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of com_dept
-- ----------------------------
INSERT INTO `com_dept` VALUES ('1', '下沙1区分公司', '0', null);
INSERT INTO `com_dept` VALUES ('2', '技术部', '1', '1');
INSERT INTO `com_dept` VALUES ('3', '人事部', '1', '1');
INSERT INTO `com_dept` VALUES ('4', '下城1区分公司', '0', null);
INSERT INTO `com_dept` VALUES ('5', '市场营销部', '1', '4');
INSERT INTO `com_dept` VALUES ('6', '后勤保障部', '1', '4');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `id_number` varchar(20) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `job_id` varchar(20) DEFAULT NULL,
  `bank_number` varchar(20) DEFAULT NULL,
  `com_dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '赵四', '0', '620000000000000000', '13777777777', 'XA0001', '888888888888', '2');
INSERT INTO `employee` VALUES ('10', 'yang', '0', '3654665', '32463454', '3434', '34665665', '2');
INSERT INTO `employee` VALUES ('11', '233', '0', '333', '533', '666', '433', '2');
INSERT INTO `employee` VALUES ('12', '123', '0', '555555', '99999', '33031', '66666', '5');
INSERT INTO `employee` VALUES ('13', '赵浩南', '0', '620000000000000000', '13777777777', 'XC00001', '6666666666666', '5');

-- ----------------------------
-- Table structure for logininfo
-- ----------------------------
DROP TABLE IF EXISTS `logininfo`;
CREATE TABLE `logininfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `user_type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logininfo
-- ----------------------------
INSERT INTO `logininfo` VALUES ('1', '8888', '8888', '0', '0');
INSERT INTO `logininfo` VALUES ('4', 'admin', 'root', '0', '3');
INSERT INTO `logininfo` VALUES ('5', 'medical', 'root', '0', '2');
INSERT INTO `logininfo` VALUES ('8', 'room1', 'root', '0', '1');
INSERT INTO `logininfo` VALUES ('9', 'room_xiacheng', 'root', '0', '1');
INSERT INTO `logininfo` VALUES ('10', 'yang', '123', '0', '0');
INSERT INTO `logininfo` VALUES ('11', 'tang', '123', '0', '0');
INSERT INTO `logininfo` VALUES ('12', 'zhou', '123', '0', '0');
INSERT INTO `logininfo` VALUES ('13', 'zhao', '123', '0', '0');

-- ----------------------------
-- Table structure for medical_room
-- ----------------------------
DROP TABLE IF EXISTS `medical_room`;
CREATE TABLE `medical_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal_id` bigint(20) DEFAULT NULL,
  `budget_amount` decimal(18,4) DEFAULT NULL,
  `use_amount` decimal(18,4) DEFAULT NULL,
  `com_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of medical_room
-- ----------------------------
INSERT INTO `medical_room` VALUES ('2', '8', '50000.0000', '1278.0000', '1');
INSERT INTO `medical_room` VALUES ('3', '9', '100000.0000', '278.8800', '4');

-- ----------------------------
-- Table structure for system_dictionary_item
-- ----------------------------
DROP TABLE IF EXISTS `system_dictionary_item`;
CREATE TABLE `system_dictionary_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `description` varchar(20) DEFAULT NULL,
  `sequence` int(11) DEFAULT NULL,
  `isdel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_dictionary_item
-- ----------------------------
INSERT INTO `system_dictionary_item` VALUES ('1', '重大疾病1', '这里是一个重大疾病啊', '1', '0');
INSERT INTO `system_dictionary_item` VALUES ('2', '重大疾病2', '这里又是一个测试的重大疾病', '2', '0');
INSERT INTO `system_dictionary_item` VALUES ('7', '测试删除4', '逻辑删除的疾病信息', '5', '1');
INSERT INTO `system_dictionary_item` VALUES ('8', '大病类型3', '用于测试删除的大病类型', '6', '1');
INSERT INTO `system_dictionary_item` VALUES ('9', '重大疾病3', '测试用用', '6', '0');
INSERT INTO `system_dictionary_item` VALUES ('10', '车祸', '被车撞了啊', '7', '0');
