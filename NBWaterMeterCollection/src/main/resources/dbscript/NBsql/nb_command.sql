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

Date: 2019-06-14 09:16:31
*/


-- ----------------------------
-- Table structure for nb_command
-- ----------------------------
DROP TABLE [dbo].[nb_command]
GO
CREATE TABLE [dbo].[nb_command] (
[model_id] int NOT NULL ,
[appinfo_id] int NOT NULL ,
[command_id] int NOT NULL ,
[command_name] varchar(64) NOT NULL ,
[service_type] int NOT NULL DEFAULT ((0)) ,
[service_id] varchar(64) NOT NULL ,
[method] varchar(64) NOT NULL ,
[res_id] varchar(64) NULL ,
[param] varchar(256) NULL ,
[control_code] varchar(64) NULL ,
[use_flag] tinyint NOT NULL DEFAULT ((1)) 
)


GO

-- ----------------------------
-- Records of nb_command
-- ----------------------------
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'1', N'竞达电信终端注册', N'0', N'DeviceInfo', N'Regist', null, N'{"value":1}', null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'2', N'竞达电信终端取消注册', N'0', N'DeviceInfo', N'Unregist', null, N'{"value":1}', null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'3', N'竞达电信查询阀门状态', N'0', N'Valve', N'Query', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'4', N'竞达电信开阀', N'0', N'Valve', N'Open', null, N'{"value":1}', null, N'1')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'5', N'竞达电信关阀', N'0', N'Valve', N'Close', null, N'{"value":2}', null, N'1')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'6', N'竞达电信数据上报', N'1', N'PeriodReport', N'PeriodReport', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'7', N'竞达电信电池服务数据', N'1', N'Battery', N'Battery', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'8', N'竞达电信通信服务数据', N'1', N'Connectivity', N'Connectivity', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'9', N'竞达电信告警服务数据', N'1', N'DeviceAlarm', N'DeviceAlarm', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'10', N'竞达电信设备信息服务数据', N'1', N'DeviceInfo', N'DeviceInfo', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'11', N'竞达电信设备参数服务数据', N'1', N'DeviceParaSetting', N'DeviceParaSetting', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'12', N'竞达电信设备状态服务数据', N'1', N'DeviceState', N'DeviceState', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'13', N'竞达电信实时数据服务数据', N'1', N'RealtimeReport', N'RealtimeReport', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'1', N'1', N'14', N'竞达电信阀门服务数据', N'1', N'Valve', N'Valve', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'2', N'2', N'1', N'府星电信设置表端上报时间周期', N'0', N'SettingReportPeriod', N'SET_REPORT_PERIOD', null, N'{"AFN":19 ,"IMSI":"xxx" ,"CNT":"xxx", "DIR":"xxx","ReportBaseTime ":"xxx"," ReportIntervalHours ":"xxx"}', null, N'1')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'2', N'2', N'2', N'设置电信表端日期时间府星', N'0', N'SettingDateTime', N'SET_DATE_TIME', null, N'{"AFN":20, "CNT":"xxx", "DIR":"xxx","IMSI":"xxx","CurrentDateTime":"xxx"}', null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'2', N'2', N'3', N'府星电信阀门控制命令', N'0', N'SettingValveState', N'SET_VALVE_STATE', null, N'{"AFN":27, "DIR":"xxx","IMSI":"xxx","CNT":"xxx"," ValveOperate ":"xxx"}', null, N'1')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'2', N'2', N'4', N'府星电信开户前数据上报', N'1', N'ReportBeforeOpenAccount', N'ReportBeforeOpenAccount', null, N'', null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'2', N'2', N'5', N'府星电信	异常数据上报内容', N'1', N'ReportException', N'ReportException', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'3', N'3', N'1', N'新天设置上报频率电信', N'0', N'SuntrontWaterMeter', N'SET_REPORT_FREQ_VAL', null, N'{"freqVal ":22}', null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'3', N'3', N'2', N'新天电信获取设备配置信息', N'0', N'SuntrontWaterMeter', N'GET_CONFIG', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'3', N'3', N'3', N'新天电信设置大流量报警门限', N'0', N'SuntrontWaterMeterAlarm    ', N'SET_HIGHFLOW_ALARM', null, N'{"highFlow":33}', null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'3', N'3', N'4', N'新天电信阀门命令', N'0', N'SuntrontValve', N'SET_Control_Valve', null, N'{"valveStatus":2}', null, N'1')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'3', N'3', N'5', N'新天电信电池服务数据', N'1', N'SuntrontBattery', N'SuntrontBattery', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'3', N'3', N'6', N'新天电信累计流量数据', N'1', N'SuntrontMeter', N'SuntrontMeter', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'3', N'3', N'7', N'新天电信水表数据', N'1', N'SuntrontWaterMeter', N'SuntrontWaterMeter', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'3', N'3', N'8', N'新天电信告警数据', N'1', N'SuntrontWaterMeterAlarm', N'SuntrontWaterMeterAlarm', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'3', N'3', N'9', N'新天电信阀门数据', N'1', N'SuntrontValve', N'SuntrontValve', null, null, null, N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'6', N'7', N'1', N'新天移动平台', N'1', N'3200', N'0', N'5505', null, N'D0BD', N'0')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'6', N'7', N'2', N'新提移动阀门控制', N'0', N'3200', N'0', N'5505', N'{"operate":1}', N'500F', N'1')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'7', N'8', N'1', N'府星移动阀门控制', N'0', N'3200', N'0', N'5750', N'{"ValveOperate":1}', N'1B', N'1')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'7', N'8', N'2', N'府星移动设置上报周期', N'0', N'3200', N'0', N'5750', N'{"ReportBaseTime":"2019-05-150 08:48:01","ReportIntervalHours":5}', N'13', N'1')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'7', N'8', N'3', N'府星移动设置表端日期时间', N'0', N'3200', N'0', N'5750', N'{"CurrentDateTime":"2019-05-150 08:47:07"}', N'14', N'1')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'7', N'8', N'4', N'府星移动设置表端流量告警参数', N'0', N'3200', N'0', N'5750', N'{"ContinuousFlowAlarmThreshold":5.0,"LowFlowAlarmThreshold":5,"LowFlowContinuousMonitorTime":5,"LargeFlowAlarmThreshold":2.9,"ContinuousFlowContinuousMonitorTime":5,"LargeFlowContinuousMonitorTime":5}', N'21', N'1')
GO
GO
INSERT INTO [dbo].[nb_command] ([model_id], [appinfo_id], [command_id], [command_name], [service_type], [service_id], [method], [res_id], [param], [control_code], [use_flag]) VALUES (N'7', N'8', N'5', N'府星移动设置表端压力告警参数', N'0', N'3200', N'0', N'5750', N'{"LowPressureAlarmThreshold":5,"HighPressureAlarmThreshold":2.9}', N'22', N'1')
GO
GO

-- ----------------------------
-- Indexes structure for table nb_command
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table nb_command
-- ----------------------------
ALTER TABLE [dbo].[nb_command] ADD PRIMARY KEY ([appinfo_id], [model_id], [command_id])
GO
