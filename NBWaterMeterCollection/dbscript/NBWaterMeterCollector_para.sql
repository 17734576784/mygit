use ydpara;
go
-- *************** 厂家NB平台应用信息表 ***************
if (not exists (select name from sysobjects where name='nb_appinfo'))
	begin
		print '创建厂家NB平台应用信息表'
		create table nb_appinfo
		(
			id			int IDENTITY(1,1) PRIMARY KEY,			/*NB厂家编号*/
			name		varchar(64)	    	not	null,					/*NB厂家名称*/
			nb_type	tinyint   			  not null,  			  /*NB平台类型 0: 移动 1：联通 2：电信*/			
			app_id	varchar(64)     	unique not null,  /*应用ID*/
			secret  varchar(64) 	   	not	null,		 			/*应用秘钥*/	

		)		
	end
ELSE
	BEGIN
		print '厂家NB平台应用信息表已经存在'
	END
go

-- *************** NB设备型号表 ***************
if (not exists (select name from sysobjects where name='nb_device_model'))
	begin
		print '创建NB设备型号表'
		create table nb_device_model
		(
			id								int IDENTITY(1,1) PRIMARY KEY,		/*设备类型编号*/
			appinfo_id				int					not null,							/*厂家NB平台应用编号*/
			manufacturer_id		varchar(64)	not null,							/*厂商ID*/
			manufacturer_name	varchar(64)	not null, 						/*厂家名称*/
			device_type 			varchar(64)	not null, 						/*设备类型信息*/
			model 						varchar(64)	not null, 						/*设备型号*/
			protocol_type 		varchar(64)	not null, 						/*设备使用的协议类型*/
		)
	end
ELSE
	BEGIN
		print 'NB设备型号表已存在'
	END
go


-- *************** NB水表信息表 ***************
if (not exists (select name from sysobjects where name='nb_water_meter'))
	begin
		print '创建NB水表信息表'
		create table nb_water_meter
		(
			rtu_id										int				not null,				/*终端编号*/
			mp_id											smallint	not null,				/*测量点编号*/
			appinfo_id								int				not null,				/*厂家NB平台应用编号*/
			device_model_id 					int				not null,				/*设备型号编号*/
			
			meter_number							varchar(64)		null,				/*水表表号*/
			meter_caliber							int						null,				/*水表口径*/
			imei_code									varchar(64)		null,				/*IMEI码（仅注册NB时用）*/
			imsi_code									varchar(64)		null, 			/*IMSI码（仅注册NB时用 移动平台）*/
			device_id									varchar(64)		unique null,/*设备编号 （注册后由NB系统上传更新）*/
			
			low_voltage_threshold 		DECIMAL(4,2)	null,				/*电池低电压告警阀值*/			
			low_pressure_threshold 		DECIMAL(4,2)	null,				/*低电压告警阀值*/
			high_pressure_threshold 	DECIMAL(4,2)	null,				/*高电压告警阀值*/
						
			high_voltage_threshold 		DECIMAL(4,2)	null,				/*电池高电压告警阀值*/
			large_flow_threshold 			DECIMAL(6,2)	null,				/*大流量报警阀值*/
			large_flow_duration  					int				null,				/*大流量持续时间*/	
			long_time_water_use_threshold	int				null,				/*长时间用水时间阀值*/
			
			small_flow_threshold			DECIMAL(6,2)	null,				/*小流量报警阀值*/
			small_flow_duration 			int						null,				/*小流量持续时间*/
			online_delay_wait_time		int						null,				/*水表在线延时等待时间*/
			report_base_time					datetime			null,				/*上报起始基准时间*/
			report_interval_time				int					null,				/*上报间隔时间 单位：小时*/
 
			valve_status							tinyint				null,				/*阀门最新状态 1：正在开阀 2：阀门开到位 3：正在关阀 4：阀门关到位 5：半开 6：其他*/
			firmware_version					varchar(40)		null,				/*固件版本*/
			CONSTRAINT pk_nb_water_meter PRIMARY KEY (rtu_id,mp_id)				
		)				
	end
ELSE
	BEGIN
		print 'NB水表信息表已经存在'
	END
go



-- *************** 定时任务表***************
if (not exists (select name from sysobjects where name='schedule_job'))
	begin
		print '创建定时任务表'
		create table schedule_job
		(
			job_id					int	IDENTITY(1,1)	primary key	not null,				/*任务ID*/
			job_name				VARCHAR(40) 				not null,				/*任务名称*/
			job_group				VARCHAR(40)					not null,				/*任务分组*/
			job_status	 		tinyint		default 1	not null,				/*任务桩 0：禁用 1：启用*/
			
			cron_expression	varchar(64)		not null,		/*任务运行时间表达式*/
			quartz_class		varchar(255)	null, 			/*定时任务处理类*/
			description			varchar(280)	null				/*描述信息*/		
		)		
	end
ELSE
	BEGIN
		print '创建定时任务表已经存在'
	END
go

--告警事项表复用			eve200808
--新增告警类型
insert into eventclass(id,describe)values(20,	'NB水表告警')
--新增告警事项类型
insert into eventtype  values(20,	2001,	'大流量告警')
insert into eventtype  values(20,	2002,	'小流量告警')
insert into eventtype  values(20,	2012,	'数据被篡改')
insert into eventtype  values(20,	2003,	'反流告警')
insert into eventtype  values(20,	2004,	'磁干扰告警')
insert into eventtype  values(20,	2005,	'电池低电压告警')
insert into eventtype  values(20,	2006,	'远传模块分离告警')
insert into eventtype  values(20,	2007,	'内部错误')
insert into eventtype  values(20,	2008,	'低压告警')
insert into eventtype  values(20,	2009,	'高压告警')
insert into eventtype  values(20,	2010,	'阀门异常')
insert into eventtype  values(20,	2011,	'存储器异常')