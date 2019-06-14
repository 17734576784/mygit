/*
Navicat SQL Server Data Transfer

Source Server         : 130
Source Server Version : 105000
Source Host           : 192.168.1.130:1433
Source Database       : ydpara
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 105000
File Encoding         : 65001

Date: 2019-06-14 09:16:44
*/


-- ----------------------------
-- Table structure for nb_device_model
-- ----------------------------
DROP TABLE [dbo].[nb_device_model]
GO
CREATE TABLE [dbo].[nb_device_model] (
[id] int NOT NULL IDENTITY(1,1) ,
[appinfo_id] int NOT NULL ,
[manufacturer_id] varchar(64) NOT NULL ,
[manufacturer_name] varchar(64) NOT NULL ,
[device_type] varchar(64) NOT NULL ,
[model] varchar(64) NOT NULL ,
[protocol_type] varchar(64) NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[nb_device_model]', RESEED, 7)
GO

-- ----------------------------
-- Records of nb_device_model
-- ----------------------------
SET IDENTITY_INSERT [dbo].[nb_device_model] ON
GO
INSERT INTO [dbo].[nb_device_model] ([id], [appinfo_id], [manufacturer_id], [manufacturer_name], [device_type], [model], [protocol_type]) VALUES (N'1', N'1', N'Jingda', N'Jingda', N'WaterMeter', N'CoapV0200', N'CoAP')
GO
GO
INSERT INTO [dbo].[nb_device_model] ([id], [appinfo_id], [manufacturer_id], [manufacturer_name], [device_type], [model], [protocol_type]) VALUES (N'2', N'2', N'FuXingYiBiao', N'FuXingYiBiao', N'WaterMeter', N'FXYB-NB-001', N'CoAP')
GO
GO
INSERT INTO [dbo].[nb_device_model] ([id], [appinfo_id], [manufacturer_id], [manufacturer_name], [device_type], [model], [protocol_type]) VALUES (N'3', N'3', N'SUNTRONT', N'SUNTRONT', N'METER', N'CloudNBDevice', N'CoAP')
GO
GO
INSERT INTO [dbo].[nb_device_model] ([id], [appinfo_id], [manufacturer_id], [manufacturer_name], [device_type], [model], [protocol_type]) VALUES (N'6', N'7', N'3200_0_5505', N'3200_0_5505', N'2', N'2', N'LWM2M')
GO
GO
INSERT INTO [dbo].[nb_device_model] ([id], [appinfo_id], [manufacturer_id], [manufacturer_name], [device_type], [model], [protocol_type]) VALUES (N'7', N'8', N'3200_0_5750', N'3200_0_5750', N'2', N'2', N'LWM2M')
GO
GO
SET IDENTITY_INSERT [dbo].[nb_device_model] OFF
GO

-- ----------------------------
-- Indexes structure for table nb_device_model
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table nb_device_model
-- ----------------------------
ALTER TABLE [dbo].[nb_device_model] ADD PRIMARY KEY ([id])
GO
