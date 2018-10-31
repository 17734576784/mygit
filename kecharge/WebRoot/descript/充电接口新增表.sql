use cppara;
drop table if exists charge_record;
create table charge_record
(
	id								int				        not null AUTO_INCREMENT,	/*ID*/
	serialnumber   		varchar(32)       not null,       					/*��ˮ��*/
	charge_money  	  int					      not	null,        		  		/*����� (��)*/ 
	pile_code	  			varchar(16)		  not null,									/*���׮���*/
	gun_id        		int               not null,        					/*���ǹ*/
	
	start_date 		     	datetime            null,               	/*����ʼ�������*/
	start_receive_time 	datetime  		  		null,           		  /*ǰ�û����ͳ�翪ʼʱ��*/
	start_flag    			tinyint	 					  null,      					  /*��翪ʼ���ͽ��*/
	start_push        	tinyint    					null,     						/*���ͱ�־ 0:δ���� 1:������*/
	start_push_time			datetime				  	null,                	/*���ͳ�翪ʼʱ��*/	
	
	end_date   					datetime         		null,                 /*��������������*/
	end_receive_time		datetime						null,                	/*ǰ�û����ͳ�����ʱ��*/
  end_push   					tinyint    					null,     						/*���ͱ�־ 0:δ���� 1:������*/ 	
 	end_push_time				datetime						null,                	/*���ͳ�����ʱ��*/
 	
	soc_push          	tinyint    					null,     						/*���ͱ�־ 0:δ���� 1:������*/


	PRIMARY KEY (id)
);
create unique index uk_charge_record on charge_record(serialnumber);


alter table gun_state add column charge_dl double(12,2)  null comment '������'
alter table gun_state add column soc 			  int  null				  comment '�����Ȱٷֱ�SOC'
alter table gun_state add column remain_tm	int	 null			 	  comment 'ʣ��ʱ��-��'

-- ����������ñ�
drop table if EXISTS partner_config;
create table partner_config(
	id 		   					int  					not null AUTO_INCREMENT PRIMARY KEY,
	partner_id    		int 					not null UNIQUE,			-- �������ID
	use_flag 					TINYINT				not null DEFAULT 0,		-- ʹ�ñ�־
  username					VARCHAR(16)		not null,							-- ���ֵ�¼�û���
	password					VARCHAR(32)		not null,							-- ���ֵ�¼����
  carowner_id   		int 					not null,							-- Ĭ�ϳ���id
	token   					VARCHAR(64)	  		null,							-- �ص���¼token
	login_url					VARCHAR(128)	not null,							-- �ص���¼url
	charge_start_url 	VARCHAR(128)	not null,							-- �ص����ͳ�翪ʼ��ַ
	charge_over_url  	VARCHAR(128)	not null 							-- �ص����ͳ�������ַ
	charge_dc_info_url VARCHAR(128)	not null 							-- �ص�����ֱ���״γ����Ϣ��ַ
)

use cpdata;
drop table if exists charge_record2018;
create table charge_record2018
(
	id								int				        not null AUTO_INCREMENT,	/*ID*/
	serialnumber   		varchar(32)       not null,       					/*��ˮ��*/
	charge_money  	  int					      not	null,        		  		/*����� (��)*/ 
	pile_code	  			  varchar(16)			  not null,								/*���׮���*/
	gun_id        		int               not null,        					/*���ǹ*/
	
	start_date 		     	datetime            null,               	/*����ʼ�������*/
	start_receive_time 	datetime  		  		null,           		  /*ǰ�û����ͳ�翪ʼʱ��*/
	start_flag    			tinyint	 					  null,      					  /*��翪ʼ���ͽ��*/
	start_push        	tinyint    					null,     						/*���ͱ�־ 0:δ���� 1:������*/
	start_push_time			datetime				  	null,                	/*���ͳ�翪ʼʱ��*/	
	
	end_date   					datetime         		null,                 /*��������������*/
	end_receive_time		datetime						null,                	/*ǰ�û����ͳ�����ʱ��*/
  end_push   					tinyint    					null,     						/*���ͱ�־ 0:δ���� 1:������*/ 	
 	end_push_time				datetime						null,                	/*���ͳ�����ʱ��*/
	soc_push          	tinyint    					null,     						/*���ͱ�־ 0:δ���� 1:������*/

	PRIMARY KEY (id)
);
create unique index uk_charge_record2018 on charge_record2018(serialnumber);