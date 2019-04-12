use yddata;
GO

--   '-------Create table NBˮ�������ݽṹ��(nb_alarm_200808) on yddata-------'
-- ��ǿ�ʼ
if not exists (select * from sysobjects where name = 'nb_daily_data_200808')
begin
create table nb_daily_data_200808
(
    rtu_id				int				not null,				/*�ն˱��*/
		mp_id					smallint	not null,				/*��������*/
		ymd						int				not null,				/*��������*/
		hms						int				not null,				/*����ʱ��*/
		report_type		tinyint				null,				/*�ϱ���ʽ 0�������ϱ� 1�������ϱ� 2������*/

		battery_voltage 		DECIMAL(4,2) 	null,			/*��ǰ��ص�ѹ*/
		total_flow 					DECIMAL(10,3) null,			/*��ǰ�ۼ�����*/
		month_total_flow 		DECIMAL(10,3) null,			/*�����ۼ�ˮ��*/
		daily_positive_flow DECIMAL(7,3) 	null,			/*�ս����ۻ�����*/
		daily_negative_flow DECIMAL(7,3) 	null,			/*�սḺ�ۻ�����*/
		hydraulic_pressure  DECIMAL(7,3) 	null,			/*��ǰˮѹ*/
		daily_max_velocity  DECIMAL(7,3) 	null,			/*�����������*/
		total_online_success int					null,			/*�ۼ����߳ɹ�����*/
		total_online_failure int					null,			/*�ۼ�����ʧ�ܴ���*/
		valve_status				tinyint				null,			/*��������״̬ 1�����ڿ��� 2�����ſ���λ 3�����ڹط� 4�����Źص�λ 5���뿪 6������*/
		CONSTRAINT pk_nb_daily_data_200808 PRIMARY KEY (rtu_id,mp_id,ymd,hms)				

)
 create unique index  uk_nb_daily_data_200808
	on nb_daily_data_200808(rtu_id,mp_id,ymd,hms,report_type)  
	create index  idx_nb_daily_data_200808
	on nb_daily_data_200808(rtu_id,mp_id,ymd)  
end
go
-- ��ǽ���

--   '-------Create table NBˮ�������ݽṹ��(nb_alarm_200808) on yddata-------'
-- ��ǿ�ʼ
if not exists (select * from sysobjects where name = 'nb_instantaneous_200808')
begin
create table nb_instantaneous_200808
(
    rtu_id				int				not null,				/*�ն˱��*/
		mp_id					smallint	not null,				/*��������*/
		ymd						int				not null,				/*��������*/
		hms						int				not null,				/*����ʱ��*/

		total_flow 					DECIMAL(10,3) null,			/*��ǰ�ۼ�����*/
		daily_positive_flow DECIMAL(7,3) 	null,			/*�ս����ۻ�����*/
		daily_negative_flow DECIMAL(7,3) 	null,			/*�սḺ�ۻ�����*/
		hydraulic_pressure  DECIMAL(7,3) 	null,			/*��ǰˮѹ*/
		daily_max_velocity  DECIMAL(7,3) 	null,			/*�����������*/
		total_online_success int					null,			/*�ۼ����߳ɹ�����*/
		total_online_failure int					null,			/*�ۼ�����ʧ�ܴ���*/
		valve_status				tinyint				null,			/*��������״̬ 1�����ڿ��� 2�����ſ���λ 3�����ڹط� 4�����Źص�λ 5���뿪 6������*/
		
		CONSTRAINT pk_nb_instantaneous_200808 PRIMARY KEY (rtu_id,mp_id,ymd,hms)				

)
 create unique index  uk_nb_instantaneous_200808
	on nb_instantaneous_200808(rtu_id,mp_id,ymd,hms)  
end
go
-- ��ǽ���

--   '-------Create table NBˮ�������ݽṹ��(nb_alarm_200808) on yddata-------'
-- ��ǿ�ʼ
if not exists (select * from sysobjects where name = 'nb_battery_200808')
begin
create table nb_battery_200808
(
    rtu_id				int				not null,				/*�ն˱��*/
		mp_id					smallint	not null,				/*��������*/
		ymd						int				not null,				/*��������*/
		hms						int				not null,				/*����ʱ��*/
		battery_voltage 		DECIMAL(4,2) 	null,			/*��ǰ��ص�ѹ*/
	
		
		CONSTRAINT pk_nb_battery_200808 PRIMARY KEY (rtu_id,mp_id,ymd,hms)				

)
 create unique index  uk_nb_battery_200808
	on nb_battery_200808(rtu_id,mp_id,ymd,hms)  
end
go
-- ��ǽ���


--   '-------Create table NB��������ṹ��(nb_alarm_200808) on yddata-------'
-- ��ǿ�ʼ
if not exists (select * from sysobjects where name = 'nb_command_200808')
begin
create table nb_command_200808
(
    rtu_id				int				not null,				/*�ն˱��*/
		mp_id					smallint	not null,				/*��������*/
		send_time			datetime 	not null DEFAULT GETDATE(),		/*���������*/

		command_class		tinyint			 not null,								/*������� 0���������� 1:��������*/
		command_type		tinyint			 not null,								/*�������� �·�����  �������� */
		command_id		 	varchar(64)  		 null,								/*����ID*/
		execute_result 	tinyint      		 null,    						/*ִ�н��*/
		report_time    	datetime     		 null,    						/*�ϱ�ʱ��*/
		command_content varchar(255) not null,								/*��������*/
		operator_id 		int 				 not null,								/*����Ա���*/
	  
	  CONSTRAINT pk_nb_command_200808 PRIMARY KEY (rtu_id,mp_id,send_time,command_type)					
)
 create unique index  uk_nb_command_200808 on nb_command_200808(command_id)  
end
go
-- ��ǽ���