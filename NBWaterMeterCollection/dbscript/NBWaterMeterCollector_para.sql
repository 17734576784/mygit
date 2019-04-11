use ydpara;
go
-- *************** ����NBƽ̨Ӧ����Ϣ�� ***************
if (not exists (select name from sysobjects where name='nb_appinfo'))
	begin
		print '��������NBƽ̨Ӧ����Ϣ��'
		create table nb_appinfo
		(
			id			int IDENTITY(1,1) PRIMARY KEY,			/*NB���ұ��*/
			name		varchar(64)	    	not	null,					/*NB��������*/
			nb_type	tinyint   			  not null,  			  /*NBƽ̨���� 0: �ƶ� 1����ͨ 2������*/			
			app_id	varchar(64)     	unique not null,  /*Ӧ��ID*/
			secret  varchar(64) 	   	not	null,		 			/*Ӧ����Կ*/	

		)		
	end
ELSE
	BEGIN
		print '����NBƽ̨Ӧ����Ϣ���Ѿ�����'
	END
go

-- *************** NB�豸�ͺű� ***************
if (not exists (select name from sysobjects where name='nb_device_model'))
	begin
		print '����NB�豸�ͺű�'
		create table nb_device_model
		(
			id								int IDENTITY(1,1) PRIMARY KEY,		/*�豸���ͱ��*/
			appinfo_id				int					not null,							/*����NBƽ̨Ӧ�ñ��*/
			manufacturer_id		varchar(64)	not null,							/*����ID*/
			manufacturer_name	varchar(64)	not null, 						/*��������*/
			device_type 			varchar(64)	not null, 						/*�豸������Ϣ*/
			model 						varchar(64)	not null, 						/*�豸�ͺ�*/
			protocol_type 		varchar(64)	not null, 						/*�豸ʹ�õ�Э������*/
		)
	end
ELSE
	BEGIN
		print 'NB�豸�ͺű��Ѵ���'
	END
go


-- *************** NBˮ����Ϣ�� ***************
if (not exists (select name from sysobjects where name='nb_water_meter'))
	begin
		print '����NBˮ����Ϣ��'
		create table nb_water_meter
		(
			rtu_id										int				not null,				/*�ն˱��*/
			mp_id											smallint	not null,				/*��������*/
			appinfo_id								int				not null,				/*����NBƽ̨Ӧ�ñ��*/
			device_model_id 					int				not null,				/*�豸�ͺű��*/
			
			meter_number							varchar(64)		null,				/*ˮ����*/
			meter_caliber							int						null,				/*ˮ��ھ�*/
			imei_code									varchar(64)		null,				/*IMEI�루��ע��NBʱ�ã�*/
			imsi_code									varchar(64)		null, 			/*IMSI�루��ע��NBʱ�� �ƶ�ƽ̨��*/
			device_id									varchar(64)		unique null,/*�豸��� ��ע�����NBϵͳ�ϴ����£�*/
			
			low_voltage_threshold 		DECIMAL(4,2)	null,				/*��ص͵�ѹ�澯��ֵ*/			
			low_pressure_threshold 		DECIMAL(4,2)	null,				/*�͵�ѹ�澯��ֵ*/
			high_pressure_threshold 	DECIMAL(4,2)	null,				/*�ߵ�ѹ�澯��ֵ*/
						
			high_voltage_threshold 		DECIMAL(4,2)	null,				/*��ظߵ�ѹ�澯��ֵ*/
			large_flow_threshold 			DECIMAL(6,2)	null,				/*������������ֵ*/
			large_flow_duration  					int				null,				/*����������ʱ��*/	
			long_time_water_use_threshold	int				null,				/*��ʱ����ˮʱ�䷧ֵ*/
			
			small_flow_threshold			DECIMAL(6,2)	null,				/*С����������ֵ*/
			small_flow_duration 			int						null,				/*С��������ʱ��*/
			online_delay_wait_time		int						null,				/*ˮ��������ʱ�ȴ�ʱ��*/
			report_base_time					datetime			null,				/*�ϱ���ʼ��׼ʱ��*/
			report_interval_time				int					null,				/*�ϱ����ʱ�� ��λ��Сʱ*/
 
			valve_status							tinyint				null,				/*��������״̬ 1�����ڿ��� 2�����ſ���λ 3�����ڹط� 4�����Źص�λ 5���뿪 6������*/
			firmware_version					varchar(40)		null,				/*�̼��汾*/
			CONSTRAINT pk_nb_water_meter PRIMARY KEY (rtu_id,mp_id)				
		)				
	end
ELSE
	BEGIN
		print 'NBˮ����Ϣ���Ѿ�����'
	END
go



-- *************** ��ʱ�����***************
if (not exists (select name from sysobjects where name='schedule_job'))
	begin
		print '������ʱ�����'
		create table schedule_job
		(
			job_id					int	IDENTITY(1,1)	primary key	not null,				/*����ID*/
			job_name				VARCHAR(40) 				not null,				/*��������*/
			job_group				VARCHAR(40)					not null,				/*�������*/
			job_status	 		tinyint		default 1	not null,				/*����׮ 0������ 1������*/
			
			cron_expression	varchar(64)		not null,		/*��������ʱ����ʽ*/
			quartz_class		varchar(255)	null, 			/*��ʱ��������*/
			description			varchar(280)	null				/*������Ϣ*/		
		)		
	end
ELSE
	BEGIN
		print '������ʱ������Ѿ�����'
	END
go

--�澯�������			eve200808
--�����澯����
insert into eventclass(id,describe)values(20,	'NBˮ��澯')
--�����澯��������
insert into eventtype  values(20,	2001,	'�������澯')
insert into eventtype  values(20,	2002,	'С�����澯')
insert into eventtype  values(20,	2012,	'���ݱ��۸�')
insert into eventtype  values(20,	2003,	'�����澯')
insert into eventtype  values(20,	2004,	'�Ÿ��Ÿ澯')
insert into eventtype  values(20,	2005,	'��ص͵�ѹ�澯')
insert into eventtype  values(20,	2006,	'Զ��ģ�����澯')
insert into eventtype  values(20,	2007,	'�ڲ�����')
insert into eventtype  values(20,	2008,	'��ѹ�澯')
insert into eventtype  values(20,	2009,	'��ѹ�澯')
insert into eventtype  values(20,	2010,	'�����쳣')
insert into eventtype  values(20,	2011,	'�洢���쳣')