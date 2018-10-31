use cppara;
drop table if exists charge_record;
create table charge_record
(
	id								int				        not null AUTO_INCREMENT,	/*ID*/
	serialnumber   		varchar(32)       not null,       					/*流水号*/
	charge_money  	  int					      not	null,        		  		/*充电金额 (分)*/ 
	pile_code	  			varchar(16)		  not null,									/*充电桩编号*/
	gun_id        		int               not null,        					/*充电枪*/
	
	start_date 		     	datetime            null,               	/*请求开始充电日期*/
	start_receive_time 	datetime  		  		null,           		  /*前置机推送充电开始时间*/
	start_flag    			tinyint	 					  null,      					  /*充电开始推送结果*/
	start_push        	tinyint    					null,     						/*推送标志 0:未推送 1:已推送*/
	start_push_time			datetime				  	null,                	/*推送充电开始时间*/	
	
	end_date   					datetime         		null,                 /*请求结束充电日期*/
	end_receive_time		datetime						null,                	/*前置机推送充电结束时间*/
  end_push   					tinyint    					null,     						/*推送标志 0:未推送 1:已推送*/ 	
 	end_push_time				datetime						null,                	/*推送充电结束时间*/
 	
	soc_push          	tinyint    					null,     						/*推送标志 0:未推送 1:已推送*/


	PRIMARY KEY (id)
);
create unique index uk_charge_record on charge_record(serialnumber);


alter table gun_state add column charge_dl double(12,2)  null comment '充电电量'
alter table gun_state add column soc 			  int  null				  comment '充电进度百分比SOC'
alter table gun_state add column remain_tm	int	 null			 	  comment '剩余时间-秒'

-- 合作伙伴配置表
drop table if EXISTS partner_config;
create table partner_config(
	id 		   					int  					not null AUTO_INCREMENT PRIMARY KEY,
	partner_id    		int 					not null UNIQUE,			-- 合作伙伴ID
	use_flag 					TINYINT				not null DEFAULT 0,		-- 使用标志
  username					VARCHAR(16)		not null,							-- 科林登录用户名
	password					VARCHAR(32)		not null,							-- 科林登录密码
  carowner_id   		int 					not null,							-- 默认车主id
	token   					VARCHAR(64)	  		null,							-- 回调登录token
	login_url					VARCHAR(128)	not null,							-- 回调登录url
	charge_start_url 	VARCHAR(128)	not null,							-- 回调推送充电开始地址
	charge_over_url  	VARCHAR(128)	not null 							-- 回调推送充电结束地址
	charge_dc_info_url VARCHAR(128)	not null 							-- 回调推送直流首次充电信息地址
)

use cpdata;
drop table if exists charge_record2018;
create table charge_record2018
(
	id								int				        not null AUTO_INCREMENT,	/*ID*/
	serialnumber   		varchar(32)       not null,       					/*流水号*/
	charge_money  	  int					      not	null,        		  		/*充电金额 (分)*/ 
	pile_code	  			  varchar(16)			  not null,								/*充电桩编号*/
	gun_id        		int               not null,        					/*充电枪*/
	
	start_date 		     	datetime            null,               	/*请求开始充电日期*/
	start_receive_time 	datetime  		  		null,           		  /*前置机推送充电开始时间*/
	start_flag    			tinyint	 					  null,      					  /*充电开始推送结果*/
	start_push        	tinyint    					null,     						/*推送标志 0:未推送 1:已推送*/
	start_push_time			datetime				  	null,                	/*推送充电开始时间*/	
	
	end_date   					datetime         		null,                 /*请求结束充电日期*/
	end_receive_time		datetime						null,                	/*前置机推送充电结束时间*/
  end_push   					tinyint    					null,     						/*推送标志 0:未推送 1:已推送*/ 	
 	end_push_time				datetime						null,                	/*推送充电结束时间*/
	soc_push          	tinyint    					null,     						/*推送标志 0:未推送 1:已推送*/

	PRIMARY KEY (id)
);
create unique index uk_charge_record2018 on charge_record2018(serialnumber);