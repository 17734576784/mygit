use yddata;
GO

--   '-------Create table NB水表日数据结构表(nb_alarm_200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'nb_daily_data_200808')
begin
create table nb_daily_data_200808
(
    rtu_id				int				not null,				/*终端编号*/
		mp_id					smallint	not null,				/*测量点编号*/
		ymd						int				not null,				/*数据日期*/
		hms						int				not null,				/*数据时间*/
		report_type		tinyint				null,				/*上报方式 0：正常上报 1：按键上报 2：补招*/

		battery_voltage 		DECIMAL(4,2) 	null,			/*当前电池电压*/
		total_flow 					DECIMAL(10,3) null,			/*当前累计流量*/
		month_total_flow 		DECIMAL(10,3) null,			/*当月累计水量*/
		daily_positive_flow DECIMAL(7,3) 	null,			/*日结正累积流量*/
		daily_negative_flow DECIMAL(7,3) 	null,			/*日结负累积流量*/
		hydraulic_pressure  DECIMAL(7,3) 	null,			/*当前水压*/
		daily_max_velocity  DECIMAL(7,3) 	null,			/*当日最大流速*/
		total_online_success int					null,			/*累计上线成功次数*/
		total_online_failure int					null,			/*累计上线失败次数*/
		valve_status				tinyint				null,			/*阀门最新状态 1：正在开阀 2：阀门开到位 3：正在关阀 4：阀门关到位 5：半开 6：其他*/
		CONSTRAINT pk_nb_daily_data_200808 PRIMARY KEY (rtu_id,mp_id,ymd,hms)				

)
 create unique index  uk_nb_daily_data_200808
	on nb_daily_data_200808(rtu_id,mp_id,ymd,hms,report_type)  
	create index  idx_nb_daily_data_200808
	on nb_daily_data_200808(rtu_id,mp_id,ymd)  
end
go
-- 标记结束

--   '-------Create table NB水表日数据结构表(nb_alarm_200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'nb_instantaneous_200808')
begin
create table nb_instantaneous_200808
(
    rtu_id				int				not null,				/*终端编号*/
		mp_id					smallint	not null,				/*测量点编号*/
		ymd						int				not null,				/*数据日期*/
		hms						int				not null,				/*数据时间*/

		total_flow 					DECIMAL(10,3) null,			/*当前累计流量*/
		daily_positive_flow DECIMAL(7,3) 	null,			/*日结正累积流量*/
		daily_negative_flow DECIMAL(7,3) 	null,			/*日结负累积流量*/
		hydraulic_pressure  DECIMAL(7,3) 	null,			/*当前水压*/
		daily_max_velocity  DECIMAL(7,3) 	null,			/*当日最大流速*/
		total_online_success int					null,			/*累计上线成功次数*/
		total_online_failure int					null,			/*累计上线失败次数*/
		valve_status				tinyint				null,			/*阀门最新状态 1：正在开阀 2：阀门开到位 3：正在关阀 4：阀门关到位 5：半开 6：其他*/
		
		CONSTRAINT pk_nb_instantaneous_200808 PRIMARY KEY (rtu_id,mp_id,ymd,hms)				

)
 create unique index  uk_nb_instantaneous_200808
	on nb_instantaneous_200808(rtu_id,mp_id,ymd,hms)  
end
go
-- 标记结束

--   '-------Create table NB水表日数据结构表(nb_alarm_200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'nb_battery_200808')
begin
create table nb_battery_200808
(
    rtu_id				int				not null,				/*终端编号*/
		mp_id					smallint	not null,				/*测量点编号*/
		ymd						int				not null,				/*数据日期*/
		hms						int				not null,				/*数据时间*/
		battery_voltage 		DECIMAL(4,2) 	null,			/*当前电池电压*/
	
		
		CONSTRAINT pk_nb_battery_200808 PRIMARY KEY (rtu_id,mp_id,ymd,hms)				

)
 create unique index  uk_nb_battery_200808
	on nb_battery_200808(rtu_id,mp_id,ymd,hms)  
end
go
-- 标记结束


--   '-------Create table NB任务命令结构表(nb_alarm_200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'nb_command_200808')
begin
create table nb_command_200808
(
    rtu_id				int				not null,				/*终端编号*/
		mp_id					smallint	not null,				/*测量点编号*/
		send_time			datetime 	not null DEFAULT GETDATE(),		/*命令发送日期*/

		command_class		tinyint			 not null,								/*命令类别 0：批量命令 1:单个命令*/
		command_type		tinyint			 not null,								/*命令类型 下发参数  补招数据 */
		command_id		 	varchar(64)  		 null,								/*命令ID*/
		execute_result 	tinyint      		 null,    						/*执行结果*/
		report_time    	datetime     		 null,    						/*上报时间*/
		command_content varchar(255) not null,								/*命令内容*/
		operator_id 		int 				 not null,								/*操作员编号*/
	  
	  CONSTRAINT pk_nb_command_200808 PRIMARY KEY (rtu_id,mp_id,send_time,command_type)					
)
 create unique index  uk_nb_command_200808 on nb_command_200808(command_id)  
end
go
-- 标记结束