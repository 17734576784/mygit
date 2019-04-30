use chargepara;

-- 创建运营商用户角色关联表
CREATE TABLE interface_user_role
(
	user_id int not null,
	role_id     int not NULL,
	PRIMARY key(user_id,role_id)
);

INSERT INTO `interface_user_role` VALUES (26, 1);
INSERT INTO `interface_user_role` VALUES (30, 1);
INSERT INTO `interface_user_role` VALUES (34, 1);
 
-- 创建接口角色表
create TABLE interface_role
(
 id int not NULL PRIMARY key auto_increment,
 role_id int not null 
);

INSERT INTO `interface_role` VALUES (1, 1);


-- 创建接口权限表
CREATE TABLE interface_premission
(
 id int not NULL PRIMARY key auto_increment,
 permission VARCHAR(16) not null,
 role_id int NOT NULL
);


-- 创建角色资源关系表
CREATE TABLE interface_role_url
(
 id int not NULL PRIMARY key auto_increment,
 url VARCHAR(64) not null,
 role_id int NOT NULL
);

INSERT INTO `interface_role_url` VALUES (1, '/getPileState.json', 1);
INSERT INTO `interface_role_url` VALUES (2, '/listStationGPS.json', 1);
INSERT INTO `interface_role_url` VALUES (3, '/getPileGps.json', 1);
INSERT INTO `interface_role_url` VALUES (4, '/getPileRate.json', 1);
INSERT INTO `interface_role_url` VALUES (5, '/chargeStart.json', 1);
INSERT INTO `interface_role_url` VALUES (6, '/chargeOver.json', 1);
INSERT INTO `interface_role_url` VALUES (7, '/chargeData.json', 1);
INSERT INTO `interface_role_url` VALUES (8, '/getPileChargeRcd.json', 1);
INSERT INTO `interface_role_url` VALUES (9, '/chargeRealData.json', 1);
INSERT INTO `interface_role_url` VALUES (10, '/listChargeOrders.json', 1);
INSERT INTO `interface_role_url` VALUES (11, '/listPileInfo.json', 1);
INSERT INTO `interface_role_url` VALUES (12, '/listGunInfo.json', 1);
INSERT INTO `interface_role_url` VALUES (13, '/listGunState.json', 1);
