/*
Navicat MySQL Data Transfer

Source Server         : 192.168.19.4_3306
Source Server Version : 50720
Source Host           : 192.168.19.4:3306
Source Database       : chargepara

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-08-12 14:19:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `charge_monitor`
-- ----------------------------
DROP TABLE IF EXISTS `charge_monitor`;
CREATE TABLE `charge_monitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serialnumber` varchar(32) NOT NULL,
  `charge_money` int(11) NOT NULL,
  `pile_code` varchar(16) NOT NULL,
  `gun_id` int(11) NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `start_receive_time` datetime DEFAULT NULL,
  `start_flag` tinyint(4) DEFAULT NULL,
  `start_push` tinyint(4) DEFAULT NULL,
  `start_push_time` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `end_receive_time` datetime DEFAULT NULL,
  `end_push` tinyint(4) DEFAULT NULL,
  `end_push_time` datetime DEFAULT NULL,
  `soc_push` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_charge_monitor` (`serialnumber`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of charge_monitor
-- ----------------------------
INSERT INTO `charge_monitor` VALUES ('1', '008900000001', '0', 'KE0000000063', '1', '2019-08-08 16:10:22', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('2', '008900000002', '0', 'KE0000000063', '1', '2019-08-08 16:11:08', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('3', '008900000003', '0', 'KE0000000063', '1', '2019-08-08 16:12:10', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('4', '008900000004', '0', 'KE0000000063', '1', '2019-08-08 16:14:21', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('5', '008900000005', '0', 'KE0000000063', '1', '2019-08-08 16:14:30', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('6', '008900000006', '0', 'KE0000000063', '1', '2019-08-08 16:19:16', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('7', '008900000007', '0', 'KE0000000063', '1', '2019-08-08 16:19:50', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('8', '008900000008', '0', 'KE0000000063', '1', '2019-08-08 16:20:01', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('9', '008900000009', '0', 'KE0000000063', '1', '2019-08-08 16:20:07', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('10', '008900000019', '0', 'KE0000000063', '1', '2019-08-08 16:20:11', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('11', '008900000011', '0', 'KE0000000063', '1', '2019-08-08 16:25:37', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('12', '008900000012', '0', 'KE0000000063', '1', '2019-08-08 16:27:49', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('13', '008900000014', '0', 'KE0000000063', '1', '2019-08-08 16:28:30', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('14', '008900000016', '0', 'KE0000000063', '1', '2019-08-08 17:10:32', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('15', '008900000017', '0', 'KE0000000063', '1', '2019-08-08 17:13:16', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('16', '008900000018', '0', 'KE0000000063', '1', '2019-08-08 17:14:32', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('17', '008900000020', '0', 'KE0000000063', '1', '2019-08-08 17:17:53', null, null, null, null, '2019-08-08 19:27:34', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('18', '008900000022', '0', 'KE0000000063', '1', '2019-08-08 17:41:21', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('19', '008900000023', '0', 'KE0000000063', '1', '2019-08-08 17:43:41', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('20', '008900000024', '0', 'KE0000000063', '1', '2019-08-08 17:44:18', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('21', '008900000025', '0', 'KE0000000063', '1', '2019-08-08 17:50:16', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('22', '008900000028', '0', 'KE0000000063', '1', '2019-08-08 17:58:05', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('23', '008900000029', '0', 'KE0000000063', '1', '2019-08-08 18:00:08', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('24', '008900000030', '0', 'KE0000000063', '1', '2019-08-08 18:01:06', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('25', '008900000034', '0', 'KE0000000063', '1', '2019-08-08 18:12:38', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('26', '008900000035', '0', 'KE0000000063', '1', '2019-08-08 18:14:20', null, null, null, null, '2019-08-08 19:50:29', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('27', '008900000038', '0', 'KE0000000063', '0', '2019-08-08 19:52:32', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('28', '008900000039', '0', 'KE0000000063', '0', '2019-08-08 19:53:56', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('29', '008900000040', '0', 'KE0000000063', '0', '2019-08-08 19:54:22', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('30', '008900000041', '0', 'KE0000000063', '0', '2019-08-08 19:55:23', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('31', '008900000042', '0', 'KE0000000063', '1', '2019-08-08 19:59:01', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('32', '890000000045', '0', 'KE0000000063', '1', '2019-08-08 20:01:59', null, null, null, null, '2019-08-08 20:03:57', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('33', '008900000046', '0', 'KE0000000063', '1', '2019-08-09 08:51:03', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('34', '008900000047', '0', 'KE0000000063', '1', '2019-08-09 08:51:08', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('35', '008900000049', '0', 'KE0000000063', '1', '2019-08-09 09:03:33', null, null, null, null, '2019-08-09 09:09:00', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('36', '008900000050', '0', 'KE0000000063', '1', '2019-08-09 09:13:52', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('37', '008900000051', '0', 'KE0000000063', '1', '2019-08-09 09:15:07', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('38', '008900000052', '0', 'KE0000000063', '1', '2019-08-09 09:22:39', null, null, null, null, '2019-08-09 09:27:09', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('39', '008900000053', '0', 'KE0000000063', '1', '2019-08-09 09:31:04', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('40', '008900000054', '0', 'KE0000000063', '1', '2019-08-09 09:32:12', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('41', '008900000055', '0', 'KE0000000063', '1', '2019-08-09 09:37:13', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('42', '008900000068', '0', 'KE0000000063', '1', '2019-08-09 10:54:12', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('43', '890000000069', '0', 'KE0000000063', '1', '2019-08-09 19:05:29', null, null, null, null, '2019-08-09 19:08:36', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('44', '008900000070', '0', 'KE0000000063', '1', '2019-08-09 19:17:48', null, null, null, null, '2019-08-09 19:34:28', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('45', '008900000071', '0', 'KE0000000063', '1', '2019-08-09 19:45:03', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('46', '008900000072', '0', 'KE0000000063', '1', '2019-08-09 19:55:05', null, null, null, null, '2019-08-09 20:01:08', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('47', '008900000073', '0', 'KE0000000063', '1', '2019-08-09 20:01:59', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('48', '008900000074', '0', 'KE0000000063', '1', '2019-08-09 20:03:05', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('49', '008900000075', '0', 'KE0000000063', '1', '2019-08-09 20:04:03', null, null, null, null, '2019-08-09 20:09:15', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('50', '008900000076', '0', 'KE0000000063', '1', '2019-08-09 20:11:44', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('51', '008900000077', '0', 'KE0000000063', '1', '2019-08-09 20:12:48', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('52', '008900000078', '0', 'KE0000000063', '1', '2019-08-09 20:13:33', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('53', '008900000079', '0', 'KE0000000063', '1', '2019-08-09 20:13:42', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('54', '008900000080', '0', 'KE0000000063', '1', '2019-08-09 20:14:21', null, null, null, null, '2019-08-09 20:15:21', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('55', '008900000081', '0', 'KE0000000063', '1', '2019-08-09 20:35:00', null, null, null, null, '2019-08-09 20:37:19', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('56', '008900000082', '0', 'KE0000000063', '1', '2019-08-09 20:40:27', null, null, null, null, '2019-08-09 20:40:46', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('57', '008900000083', '0', 'KE0000000063', '1', '2019-08-09 20:43:57', null, null, null, null, '2019-08-09 20:44:50', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('58', '008900000084', '0', 'KE0000000063', '1', '2019-08-10 08:49:50', null, null, null, null, '2019-08-10 09:23:49', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('59', '008900000085', '0', 'KE0000000063', '1', '2019-08-10 09:30:31', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('60', '008900000086', '0', 'KE0000000063', '2', '2019-08-10 09:34:59', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('61', '008900000087', '0', 'KE0000000063', '1', '2019-08-10 10:01:20', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('62', '008900000088', '0', 'KE0000000063', '1', '2019-08-10 10:05:06', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('63', '008900000089', '0', 'KE0000000063', '1', '2019-08-10 10:38:00', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('64', '008900000090', '0', 'KE0000000063', '1', '2019-08-10 11:00:12', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('65', '008900000094', '0', 'KE0000000062', '1', '2019-08-10 14:32:09', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('66', '008900000095', '0', 'KE0000000062', '1', '2019-08-10 14:34:01', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('67', '008900000096', '0', 'KE0000000062', '1', '2019-08-10 14:34:45', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('68', '008900000097', '0', 'KE0000000062', '1', '2019-08-10 14:36:11', '2019-08-10 14:38:00', '1', '1', '2019-08-10 14:38:16', null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('69', '008900000098', '0', 'KE0000000062', '1', '2019-08-10 14:47:16', '2019-08-10 14:48:38', '1', '1', '2019-08-10 14:48:39', null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('70', '008900000099', '0', 'KE0000000062', '1', '2019-08-10 14:50:27', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('71', '008900000100', '0', 'KE0000000062', '1', '2019-08-10 14:52:24', '2019-08-10 14:53:17', '1', '1', '2019-08-10 14:53:18', '2019-08-10 14:53:44', '2019-08-10 14:56:32', '1', '2019-08-10 14:56:32', null);
INSERT INTO `charge_monitor` VALUES ('72', '008900000101', '0', 'KE0000000062', '1', '2019-08-10 15:04:55', '2019-08-10 15:07:50', '1', '1', '2019-08-10 15:07:50', null, '2019-08-10 15:07:47', '1', '2019-08-10 15:07:47', null);
INSERT INTO `charge_monitor` VALUES ('73', '008900000102', '0', 'KE0000000062', '1', '2019-08-10 15:11:10', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('74', '008900000103', '0', 'KE0000000062', '1', '2019-08-10 15:14:11', '2019-08-10 15:14:32', '1', '1', '2019-08-10 15:14:33', '2019-08-10 15:18:13', '2019-08-10 15:18:27', '1', '2019-08-10 15:18:28', null);
INSERT INTO `charge_monitor` VALUES ('75', '008900000104', '0', 'KE0000000062', '1', '2019-08-10 15:35:36', '2019-08-10 15:36:03', '1', '1', '2019-08-10 15:36:03', '2019-08-10 15:56:53', '2019-08-10 15:57:04', '1', '2019-08-10 15:57:04', null);
INSERT INTO `charge_monitor` VALUES ('76', '008900000115', '0', 'KE0000000062', '1', '2019-08-10 15:49:42', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('77', '008900000105', '0', 'KE0000000062', '1', '2019-08-10 16:12:14', null, null, null, null, null, null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('78', '008900000106', '0', 'KE0000000062', '1', '2019-08-10 16:13:28', '2019-08-10 16:14:36', '1', '1', '2019-08-10 16:14:37', '2019-08-10 16:59:20', '2019-08-10 17:01:13', '1', '2019-08-10 17:01:13', null);
INSERT INTO `charge_monitor` VALUES ('79', '008900000110', '0', 'KE0000000062', '1', '2019-08-10 17:30:55', '2019-08-10 17:31:00', '1', '1', '2019-08-10 17:31:00', '2019-08-10 17:31:25', '2019-08-10 17:31:51', '1', '2019-08-10 17:31:51', null);
INSERT INTO `charge_monitor` VALUES ('80', '008900000111', '0', 'KE0000000062', '1', '2019-08-10 17:35:07', '2019-08-10 17:35:18', '1', '1', '2019-08-10 17:35:18', '2019-08-10 17:41:34', '2019-08-10 17:41:52', '1', '2019-08-10 17:41:52', null);
INSERT INTO `charge_monitor` VALUES ('81', '008900000112', '0', 'KE0000000062', '1', '2019-08-10 17:52:06', '2019-08-10 17:52:23', '1', '1', '2019-08-10 17:52:23', '2019-08-10 17:52:35', '2019-08-10 17:57:03', '1', '2019-08-10 17:57:03', null);
INSERT INTO `charge_monitor` VALUES ('82', '008900000113', '0', 'KE0000000062', '1', '2019-08-10 18:01:19', '2019-08-10 18:01:25', '1', '1', '2019-08-10 18:01:25', '2019-08-10 18:01:53', null, null, null, null);
INSERT INTO `charge_monitor` VALUES ('83', '008900000114', '0', 'KE0000000062', '1', '2019-08-10 18:06:07', '2019-08-10 18:06:18', '1', '1', '2019-08-10 18:06:18', '2019-08-10 18:06:26', '2019-08-10 18:07:55', '1', '2019-08-10 18:07:55', null);
INSERT INTO `charge_monitor` VALUES ('84', '008900000116', '0', 'KE0000000062', '1', '2019-08-10 18:45:57', '2019-08-10 18:46:06', '1', '1', '2019-08-10 18:46:06', '2019-08-10 18:47:11', '2019-08-10 18:47:28', '1', '2019-08-10 18:47:28', null);
INSERT INTO `charge_monitor` VALUES ('85', '008900000122', '0', 'KE0000000063', '1', '2019-08-12 11:52:05', '2019-08-12 11:52:51', '1', '1', '2019-08-12 11:52:51', '2019-08-12 13:57:08', '2019-08-12 13:57:23', '1', '2019-08-12 13:57:23', null);
INSERT INTO `charge_monitor` VALUES ('86', '008900000123', '0', 'KE0000000063', '1', '2019-08-12 14:01:37', '2019-08-12 14:01:50', '1', '1', '2019-08-12 14:01:50', '2019-08-12 14:02:02', '2019-08-12 14:02:08', '1', '2019-08-12 14:02:08', null);
INSERT INTO `charge_monitor` VALUES ('87', '008900000124', '0', 'KE0000000063', '1', '2019-08-12 14:02:21', '2019-08-12 14:02:29', '1', '1', '2019-08-12 14:02:30', null, '2019-08-12 14:12:41', '1', '2019-08-12 14:12:41', null);

-- ----------------------------
-- Table structure for `interface_premission`
-- ----------------------------
DROP TABLE IF EXISTS `interface_premission`;
CREATE TABLE `interface_premission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(16) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of interface_premission
-- ----------------------------

-- ----------------------------
-- Table structure for `interface_role`
-- ----------------------------
DROP TABLE IF EXISTS `interface_role`;
CREATE TABLE `interface_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of interface_role
-- ----------------------------
INSERT INTO `interface_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for `interface_role_url`
-- ----------------------------
DROP TABLE IF EXISTS `interface_role_url`;
CREATE TABLE `interface_role_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of interface_role_url
-- ----------------------------
INSERT INTO `interface_role_url` VALUES ('1', '/getPileState.json', '1');
INSERT INTO `interface_role_url` VALUES ('2', '/listStationGPS.json', '1');
INSERT INTO `interface_role_url` VALUES ('3', '/getPileGps.json', '1');
INSERT INTO `interface_role_url` VALUES ('4', '/getPileRate.json', '1');
INSERT INTO `interface_role_url` VALUES ('5', '/chargeStart.json', '1');
INSERT INTO `interface_role_url` VALUES ('6', '/chargeOver.json', '1');
INSERT INTO `interface_role_url` VALUES ('7', '/chargeData.json', '1');
INSERT INTO `interface_role_url` VALUES ('8', '/getPileChargeRcd.json', '1');
INSERT INTO `interface_role_url` VALUES ('9', '/chargeRealData.json', '1');
INSERT INTO `interface_role_url` VALUES ('10', '/listChargeOrders.json', '1');
INSERT INTO `interface_role_url` VALUES ('11', '/listPileInfo.json', '1');
INSERT INTO `interface_role_url` VALUES ('12', '/listGunInfo.json', '1');
INSERT INTO `interface_role_url` VALUES ('13', '/listGunState.json', '1');
INSERT INTO `interface_role_url` VALUES ('14', '//chargeControl.json', '1');
INSERT INTO `interface_role_url` VALUES ('15', '/getPileRecord.json', '1');
