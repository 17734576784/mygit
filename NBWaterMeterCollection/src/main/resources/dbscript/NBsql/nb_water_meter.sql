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

Date: 2019-06-14 09:16:51
*/


-- ----------------------------
-- Table structure for nb_water_meter
-- ----------------------------
DROP TABLE [dbo].[nb_water_meter]
GO
CREATE TABLE [dbo].[nb_water_meter] (
[rtu_id] int NOT NULL ,
[mp_id] smallint NOT NULL ,
[appinfo_id] int NOT NULL ,
[device_model_id] int NOT NULL ,
[meter_number] varchar(64) NULL ,
[meter_caliber] int NULL ,
[imei_code] varchar(64) NULL ,
[imsi_code] varchar(64) NULL ,
[device_id] varchar(64) NULL ,
[low_voltage_threshold] decimal(4,2) NULL ,
[low_pressure_threshold] decimal(4,2) NULL ,
[high_pressure_threshold] decimal(4,2) NULL ,
[high_voltage_threshold] decimal(4,2) NULL ,
[large_flow_threshold] decimal(6,2) NULL ,
[large_flow_duration] int NULL ,
[long_time_water_use_threshold] int NULL ,
[small_flow_threshold] decimal(6,2) NULL ,
[small_flow_duration] int NULL ,
[online_delay_wait_time] int NULL ,
[report_base_time] datetime NULL ,
[report_interval_time] int NULL ,
[valve_status] tinyint NULL ,
[firmware_version] varchar(40) NULL 
)


GO

-- ----------------------------
-- Records of nb_water_meter
-- ----------------------------
INSERT INTO [dbo].[nb_water_meter] ([rtu_id], [mp_id], [appinfo_id], [device_model_id], [meter_number], [meter_caliber], [imei_code], [imsi_code], [device_id], [low_voltage_threshold], [low_pressure_threshold], [high_pressure_threshold], [high_voltage_threshold], [large_flow_threshold], [large_flow_duration], [long_time_water_use_threshold], [small_flow_threshold], [small_flow_duration], [online_delay_wait_time], [report_base_time], [report_interval_time], [valve_status], [firmware_version]) VALUES (N'1', N'2', N'1', N'11', N'1000001', N'232', N'869029032410846', N'869029032410846', N'52024a78-339e-44f2-b0e1-b2a9da1a2cfe', N'2.30', N'3.40', N'3.50', N'3.40', N'3.00', N'3', N'34', N'.02', N'90', N'4', N'2019-04-11 09:14:58.997', N'4', N'2', N'333')
GO
GO
INSERT INTO [dbo].[nb_water_meter] ([rtu_id], [mp_id], [appinfo_id], [device_model_id], [meter_number], [meter_caliber], [imei_code], [imsi_code], [device_id], [low_voltage_threshold], [low_pressure_threshold], [high_pressure_threshold], [high_voltage_threshold], [large_flow_threshold], [large_flow_duration], [long_time_water_use_threshold], [small_flow_threshold], [small_flow_duration], [online_delay_wait_time], [report_base_time], [report_interval_time], [valve_status], [firmware_version]) VALUES (N'1', N'3', N'1', N'11', N'1000002', N'22', N'867726033841935', N'867726033841935', N'92d70872-6bdc-4dd2-9297-cc386f97222c', null, null, null, null, null, null, null, null, null, null, null, null, N'4', N'23.09')
GO
GO
INSERT INTO [dbo].[nb_water_meter] ([rtu_id], [mp_id], [appinfo_id], [device_model_id], [meter_number], [meter_caliber], [imei_code], [imsi_code], [device_id], [low_voltage_threshold], [low_pressure_threshold], [high_pressure_threshold], [high_voltage_threshold], [large_flow_threshold], [large_flow_duration], [long_time_water_use_threshold], [small_flow_threshold], [small_flow_duration], [online_delay_wait_time], [report_base_time], [report_interval_time], [valve_status], [firmware_version]) VALUES (N'2', N'1', N'1', N'1', N'竟达水表', N'22', N'869029032404054', N'869029032404054', N'63c04ac0-a0ae-4e87-bd0c-9cad975202a3', null, null, null, null, null, null, null, null, null, null, null, null, N'2', null)
GO
GO
INSERT INTO [dbo].[nb_water_meter] ([rtu_id], [mp_id], [appinfo_id], [device_model_id], [meter_number], [meter_caliber], [imei_code], [imsi_code], [device_id], [low_voltage_threshold], [low_pressure_threshold], [high_pressure_threshold], [high_voltage_threshold], [large_flow_threshold], [large_flow_duration], [long_time_water_use_threshold], [small_flow_threshold], [small_flow_duration], [online_delay_wait_time], [report_base_time], [report_interval_time], [valve_status], [firmware_version]) VALUES (N'3', N'1', N'2', N'2', N'府星水表测试1', N'33', N'867404030206334', N'867404030206334', N'a0279c34-76b7-44db-81ce-80d361aa3257', null, null, null, null, null, null, null, null, null, null, null, null, N'12', N'12.09')
GO
GO
INSERT INTO [dbo].[nb_water_meter] ([rtu_id], [mp_id], [appinfo_id], [device_model_id], [meter_number], [meter_caliber], [imei_code], [imsi_code], [device_id], [low_voltage_threshold], [low_pressure_threshold], [high_pressure_threshold], [high_voltage_threshold], [large_flow_threshold], [large_flow_duration], [long_time_water_use_threshold], [small_flow_threshold], [small_flow_duration], [online_delay_wait_time], [report_base_time], [report_interval_time], [valve_status], [firmware_version]) VALUES (N'3', N'2', N'2', N'2', N'府星测试水表2', N'21', N'867726032982805', N'867726032982805', N'9459e7b6-d1a7-4474-aa9c-210db6c2dbdc', null, null, null, null, null, null, null, null, null, null, null, null, N'4', N'13.07')
GO
GO
INSERT INTO [dbo].[nb_water_meter] ([rtu_id], [mp_id], [appinfo_id], [device_model_id], [meter_number], [meter_caliber], [imei_code], [imsi_code], [device_id], [low_voltage_threshold], [low_pressure_threshold], [high_pressure_threshold], [high_voltage_threshold], [large_flow_threshold], [large_flow_duration], [long_time_water_use_threshold], [small_flow_threshold], [small_flow_duration], [online_delay_wait_time], [report_base_time], [report_interval_time], [valve_status], [firmware_version]) VALUES (N'4', N'1', N'3', N'3', N'新天科技测试水表', N'23', N'865118042558811', N'865118042558811', N'16d65bbd-0aea-4bdd-87d8-714662750f34', null, null, null, null, null, null, null, null, null, null, null, null, N'4', N'F1BC0')
GO
GO
INSERT INTO [dbo].[nb_water_meter] ([rtu_id], [mp_id], [appinfo_id], [device_model_id], [meter_number], [meter_caliber], [imei_code], [imsi_code], [device_id], [low_voltage_threshold], [low_pressure_threshold], [high_pressure_threshold], [high_voltage_threshold], [large_flow_threshold], [large_flow_duration], [long_time_water_use_threshold], [small_flow_threshold], [small_flow_duration], [online_delay_wait_time], [report_base_time], [report_interval_time], [valve_status], [firmware_version]) VALUES (N'5', N'1', N'7', N'6', N'300000000008', N'11', N'860765040439686', N'860765040439686', N'527450458', null, null, null, null, null, null, null, null, null, null, null, null, N'2', N'QE200')
GO
GO
INSERT INTO [dbo].[nb_water_meter] ([rtu_id], [mp_id], [appinfo_id], [device_model_id], [meter_number], [meter_caliber], [imei_code], [imsi_code], [device_id], [low_voltage_threshold], [low_pressure_threshold], [high_pressure_threshold], [high_voltage_threshold], [large_flow_threshold], [large_flow_duration], [long_time_water_use_threshold], [small_flow_threshold], [small_flow_duration], [online_delay_wait_time], [report_base_time], [report_interval_time], [valve_status], [firmware_version]) VALUES (N'6', N'1', N'8', N'7', N'府星移动', null, N'867726032932750', N'867726032932750', N'528255804', null, null, null, null, N'4.00', N'30', null, N'.00', N'0', null, N'2018-12-30 00:02:00.000', N'1', N'4', N'10.01')
GO
GO

-- ----------------------------
-- Indexes structure for table nb_water_meter
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table nb_water_meter
-- ----------------------------
ALTER TABLE [dbo].[nb_water_meter] ADD PRIMARY KEY ([rtu_id], [mp_id])
GO
