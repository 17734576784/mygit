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

Date: 2019-06-14 09:16:23
*/


-- ----------------------------
-- Table structure for nb_appinfo
-- ----------------------------
DROP TABLE [dbo].[nb_appinfo]
GO
CREATE TABLE [dbo].[nb_appinfo] (
[id] int NOT NULL IDENTITY(1,1) ,
[name] varchar(64) NOT NULL ,
[nb_type] tinyint NOT NULL ,
[app_id] varchar(64) NOT NULL ,
[secret] varchar(64) NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[nb_appinfo]', RESEED, 8)
GO

-- ----------------------------
-- Records of nb_appinfo
-- ----------------------------
SET IDENTITY_INSERT [dbo].[nb_appinfo] ON
GO
INSERT INTO [dbo].[nb_appinfo] ([id], [name], [nb_type], [app_id], [secret]) VALUES (N'1', N'竟达电信', N'2', N'kFIBMLfUCggAtt9dwgSFeYTP9Ssa', N'PyVL5CMym0X_3XmSsfAuza2rc_Ma')
GO
GO
INSERT INTO [dbo].[nb_appinfo] ([id], [name], [nb_type], [app_id], [secret]) VALUES (N'2', N'府星电信', N'2', N'595yVAxsUL1HnK84TnylSC6xaY8a', N'FD9Rz9ayh80D1KM6146VXxQwU54a')
GO
GO
INSERT INTO [dbo].[nb_appinfo] ([id], [name], [nb_type], [app_id], [secret]) VALUES (N'3', N'新天科技电信', N'2', N'EABIuKc6GwOVoG4UgEkxDiLsAzca', N'cU0qd1fOLKvXgTqKMzcfu5h_vt0a')
GO
GO
INSERT INTO [dbo].[nb_appinfo] ([id], [name], [nb_type], [app_id], [secret]) VALUES (N'7', N'新天移动', N'0', N'CP203GSQK9BkTJmuwV2C3S7wAJY=', N'CP203GSQK9BkTJmuwV2C3S7wAJY=')
GO
GO
INSERT INTO [dbo].[nb_appinfo] ([id], [name], [nb_type], [app_id], [secret]) VALUES (N'8', N'府星移动', N'0', N'PBLKgW7hn3a3IT3p5VjLGt0ougU=', N'PBLKgW7hn3a3IT3p5VjLGt0ougU=')
GO
GO
SET IDENTITY_INSERT [dbo].[nb_appinfo] OFF
GO

-- ----------------------------
-- Indexes structure for table nb_appinfo
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table nb_appinfo
-- ----------------------------
ALTER TABLE [dbo].[nb_appinfo] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Uniques structure for table nb_appinfo
-- ----------------------------
ALTER TABLE [dbo].[nb_appinfo] ADD UNIQUE ([app_id] ASC)
GO
