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

Date: 2019-06-14 09:16:57
*/


-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE [dbo].[schedule_job]
GO
CREATE TABLE [dbo].[schedule_job] (
[job_id] int NOT NULL IDENTITY(1,1) ,
[job_name] varchar(128) NOT NULL ,
[job_group] varchar(40) NOT NULL ,
[job_status] tinyint NOT NULL DEFAULT ((1)) ,
[cron_expression] varchar(64) NOT NULL ,
[quartz_class] varchar(255) NULL ,
[description] varchar(280) NULL ,
[model_id] int NULL ,
[appinfo_id] int NULL ,
[command_id] int NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[schedule_job]', RESEED, 6)
GO

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
SET IDENTITY_INSERT [dbo].[schedule_job] ON
GO
INSERT INTO [dbo].[schedule_job] ([job_id], [job_name], [job_group], [job_status], [cron_expression], [quartz_class], [description], [model_id], [appinfo_id], [command_id]) VALUES (N'2', N'府星电信平台补招任务', N'task', N'1', N'0 48 9 * * ?', N'com.nb.task.FxTelecomCallDataTask', null, N'2', N'2', N'4')
GO
GO
INSERT INTO [dbo].[schedule_job] ([job_id], [job_name], [job_group], [job_status], [cron_expression], [quartz_class], [description], [model_id], [appinfo_id], [command_id]) VALUES (N'5', N'竞达电信平台补招任务', N'task', N'1', N'0 45 9 * * ?', N'com.nb.task.JdTelecomCallDataTask', null, N'1', N'1', N'6')
GO
GO
INSERT INTO [dbo].[schedule_job] ([job_id], [job_name], [job_group], [job_status], [cron_expression], [quartz_class], [description], [model_id], [appinfo_id], [command_id]) VALUES (N'6', N'新天电信平台补招任务', N'task', N'0', N'0 50 11 * * ?', N'com.nb.task.XtTelecomCallDataTask', null, N'3', N'3', N'7')
GO
GO
SET IDENTITY_INSERT [dbo].[schedule_job] OFF
GO

-- ----------------------------
-- Indexes structure for table schedule_job
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table schedule_job
-- ----------------------------
ALTER TABLE [dbo].[schedule_job] ADD PRIMARY KEY ([job_id])
GO
