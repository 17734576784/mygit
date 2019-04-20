use yddata 
go

--   '-------Create table 居民分钟表底数据表(JMinBDDL200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JMinBDDL200808')
begin
create table JMinBDDL200808
(
	rtu_id			int			   			not null,
	mp_id				smallint   			not null,
	date				int							not null,
	time				int							not null,
	
	bd_zy				decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_fy				decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_zw				decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_fw				decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	dl_zy				decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_fy				decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_zw				decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_fw				decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/
	dl_source		tinyint					DEFAULT 0
)
grant select on  JMinBDDL200808 to public
create unique clustered index JMinBDDL200808index1
	on JMinBDDL200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 居民分钟瞬时量数据表(JMinSsl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JMinSsl200808')
begin
create table JMinSsl200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	va			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相*/
	vb			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相*/
	vc			decimal(14, 3) 	DEFAULT 0.0 ,	/*C相*/
	ia			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相*/
	ib			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相*/
	ic			decimal(14, 3) 	DEFAULT 0.0 ,	/*C相*/
	i0			decimal(14, 3) 	DEFAULT 0.0 ,	/*零序*/
	p				decimal(14, 3) 	DEFAULT 0.0 ,	/*总有功*/
	pa			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相有功*/
	pb			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相有功*/
	pc			decimal(14, 3) 	DEFAULT 0.0 ,	/*C相有功*/
	q				decimal(14, 3) 	DEFAULT 0.0 ,	/*总无功*/
	qa			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相无功*/
	qb			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相无功*/
	qc			decimal(14, 3) 	DEFAULT 0.0 ,	/*C相无功*/
	s				decimal(14, 3) 	DEFAULT 0.0 ,	/*视在*/
	cs			decimal(14, 3) 	DEFAULT 0.0 ,	/*总功率因数*/
	csa			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相功率因数*/
	csb			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相功率因数*/
	csc			decimal(14, 3) 	DEFAULT 0.0 	/*C相功率因数*/
)
grant select on  JMinSsl200808 to public
create unique clustered index JMinSsl200808index1
	on JMinSsl200808(rtu_id,mp_id,date,time)
end
go	
-- 标记结束

--   '-------Create table 居民日表底电量数据表(JDayBdDl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JDayBdDl200808')
begin
create table JDayBdDl200808
(
	rtu_id		int			   			not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
	
	dl_source	tinyint					DEFAULT 0
)
grant select on  JDayBdDl200808 to public
create unique clustered index JDayBdDl200808index1
	on JDayBdDl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 居民日最大需量功率数据表(JDayMaxXlP200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JDayMaxXlP200808')
begin
create table JDayMaxXlP200808
(
	rtu_id				int			   			not null,
	mp_id					smallint   			not null,
	date					int							not null,
	time					int							not null,
	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/		
	xl_zy_tm			int 						DEFAULT 0 ,		/*正向有功总需量发生时间*/
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/
	xl_zw_tm			int 						DEFAULT 0 ,		/*正向无功总需量发生时间*/
	ext_p_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最大有功功率*/
	ext_p_max_tm	int 						DEFAULT 0  	/*总最大有功功率发生时间*/
	)
grant select on  JDayMaxXlP200808 to public
create unique clustered index JDayMaxXlP200808index1
	on JDayMaxXlP200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 居民电表预付费日余额表(JDayYffMoney200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JDayYffMoney200808')
begin
create table JDayYffMoney200808
(
	rtu_id		int			   			not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,

	remain_value			decimal(14, 3) 	DEFAULT 0.0 ,	/*剩余金额*/
	credit_value			decimal(14, 3) 	DEFAULT 0.0 ,	/*赊欠金额*/
)
grant select on  JDayYffMoney200808 to public
create unique clustered index JDayYffMoney200808index1
	on JDayYffMoney200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 居民抄表日表底电量数据表(JMRBdDl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JMRBdDl200808')
begin
create table JMRBdDl200808
(
	rtu_id		int			   			not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
	
	dl_source	tinyint					DEFAULT 0

)
grant select on  JMRBdDl200808 to public
create unique clustered index JMRBdDl200808index1
	on JMRBdDl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 居民月表底电量数据表(JMonBdDl2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JMonBdDl2008')
begin
create table JMonBdDl2008
(
	rtu_id		int			   			not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
	
	dl_source	tinyint					DEFAULT 0

)
grant select on  JMonBdDl2008 to public
create unique clustered index JMonBdDl2008index1
	on JMonBdDl2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 居民月最大需量功率数据表(JMonMaxXlP2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JMonMaxXlP2008')
begin
create table JMonMaxXlP2008
(
	rtu_id				int			   			not null,
	mp_id					smallint   			not null,
	date					int							not null,
	time					int							not null,
	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/		
	xl_zy_tm			int 						DEFAULT 0 ,		/*正向有功总需量发生时间*/
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/
	xl_zw_tm			int 						DEFAULT 0 ,		/*正向无功总需量发生时间*/
	ext_p_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最大有功功率*/
	ext_p_max_tm	int 						DEFAULT 0 		/*总最大有功功率发生时间*/
	)
grant select on  JMonMaxXlP2008 to public
create unique clustered index JMonMaxXlP2008index1
	on JMonMaxXlP2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

----------------------居民应用结束--------------------------------------------


----------------------变电站应用开始--------------------------------------------
--   '-------Create table 变电站实时表底数据表(BRealBD200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BRealBD200808')
begin
create table BRealBD200808
(
	rtu_id			int			   		not null,
	mp_id				smallint   		not null,
	date				int						not null,
	time				int						not null,
	
	bd_zy			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖表底*/	
	
	bd_1xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 4) 	DEFAULT 0.0 	/*四相限无功表底*/
	
)
grant select on  BRealBD200808 to public
create unique clustered index BRealBD200808index1
	on BRealBD200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 变电站实时瞬时量数据表(BRealSsl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BRealSsl200808')
begin
create table BRealSsl200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	va			decimal(14, 4) 	DEFAULT 0.0 ,	/*	A相*/
	vb			decimal(14, 4) 	DEFAULT 0.0 ,	/*	B相*/
	vc			decimal(14, 4) 	DEFAULT 0.0 ,	/*	C相*/
	ia			decimal(14, 4) 	DEFAULT 0.0 ,	/*	A相*/
	ib			decimal(14, 4) 	DEFAULT 0.0 ,	/*	B相*/
	ic			decimal(14, 4) 	DEFAULT 0.0 ,	/*	C相*/
	i0			decimal(14, 4) 	DEFAULT 0.0 ,	/*	零序*/
	p				decimal(14, 4) 	DEFAULT 0.0 ,	/*	总有功*/
	pa			decimal(14, 4) 	DEFAULT 0.0 ,	/*	A相有功*/
	pb			decimal(14, 4) 	DEFAULT 0.0 ,	/*	B相有功*/
	pc			decimal(14, 4) 	DEFAULT 0.0 ,	/*	C相有功*/
	q				decimal(14, 4) 	DEFAULT 0.0 ,	/*	总无功*/
	qa			decimal(14, 4) 	DEFAULT 0.0 ,	/*	A相无功*/
	qb			decimal(14, 4) 	DEFAULT 0.0 ,	/*	B相无功*/
	qc			decimal(14, 4) 	DEFAULT 0.0 ,	/*	C相无功*/
	s				decimal(14, 4) 	DEFAULT 0.0 ,	/*	视在*/
	cs			decimal(14, 4) 	DEFAULT 0.0 ,	/*总功率因数*/
	csa			decimal(14, 4) 	DEFAULT 0.0 ,	/*A相功率因数*/
	csb			decimal(14, 4) 	DEFAULT 0.0 ,	/*B相功率因数*/
	csc			decimal(14, 4) 	DEFAULT 0.0 	/*C相功率因数*/
)
grant select on  BRealSsl200808 to public
create unique clustered index BRealSsl200808index1
	on BRealSsl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 变电站分钟表底电量数据表(BMinBdDl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinBdDl200808')
begin
create table BMinBdDl200808
(
	rtu_id			int			   		not null,
	mp_id				smallint   		not null,
	date				int						not null,
	time				int						not null,
	
	bd_zy			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	bd_1xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*四相限无功表底*/
	
	dl_zy			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
		
	dl_1xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 4) 	DEFAULT 0.0, 	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  BMinBdDl200808 to public
create unique clustered index BMinBdDl200808index1
	on BMinBdDl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束



--   '-------Create table 变电站分钟瞬时量数据表(BMinSsl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinSsl200808')
begin
create table BMinSsl200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	va			decimal(14, 4) 	DEFAULT 0.0 ,	/*	A相*/
	vb			decimal(14, 4) 	DEFAULT 0.0 ,	/*	B相*/
	vc			decimal(14, 4) 	DEFAULT 0.0 ,	/*	C相*/
	ia			decimal(14, 4) 	DEFAULT 0.0 ,	/*	A相*/
	ib			decimal(14, 4) 	DEFAULT 0.0 ,	/*	B相*/
	ic			decimal(14, 4) 	DEFAULT 0.0 ,	/*	C相*/
	i0			decimal(14, 4) 	DEFAULT 0.0 ,	/*	零序*/
	p				decimal(14, 4) 	DEFAULT 0.0 ,	/*	总有功*/
	pa			decimal(14, 4) 	DEFAULT 0.0 ,	/*	A相有功*/
	pb			decimal(14, 4) 	DEFAULT 0.0 ,	/*	B相有功*/
	pc			decimal(14, 4) 	DEFAULT 0.0 ,	/*	C相有功*/
	q				decimal(14, 4) 	DEFAULT 0.0 ,	/*	总无功*/
	qa			decimal(14, 4) 	DEFAULT 0.0 ,	/*	A相无功*/
	qb			decimal(14, 4) 	DEFAULT 0.0 ,	/*	B相无功*/
	qc			decimal(14, 4) 	DEFAULT 0.0 ,	/*	C相无功*/
	s				decimal(14, 4) 	DEFAULT 0.0 ,	/*	视在*/
	cs			decimal(14, 4) 	DEFAULT 0.0 ,	/*总功率因数*/
	csa			decimal(14, 4) 	DEFAULT 0.0 ,	/*A相功率因数*/
	csb			decimal(14, 4) 	DEFAULT 0.0 ,	/*B相功率因数*/
	csc			decimal(14, 4) 	DEFAULT 0.0 	/*C相功率因数*/
)
grant select on  BMinSsl200808 to public
create unique clustered index BMinSsl200808index1
	on BMinSsl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 变电站日表底电量数据表(BDayBdDl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BDayBdDl200808')
begin
create table BDayBdDl200808
(
	rtu_id		int			   			not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	bd_1xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*四相限无功表底*/
	
	dl_zy			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
		
	dl_1xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 4) 	DEFAULT 0.0, 	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  BDayBdDl200808 to public
create unique clustered index BDayBdDl200808index1
	on BDayBdDl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 变电站日需量数据表(BDayMaxXl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BDayMaxXl200808')
begin
create table BDayMaxXl200808
(
	rtu_id				int			   			not null,
	mp_id					smallint   			not null,
	date					int							not null,
	time					int							not null,

	xl_zy					decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷需量*/	
	xl_zy_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/	
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷需量*/
	xl_zw_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总需量*/	
	xl_fw_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷需量*/	
	xl_fw_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	
	
	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 ,		/*反向无功尖需量时间*/	
)
grant select on  BDayMaxXl200808 to public
create unique clustered index BDayMaxXl200808index1
	on BDayMaxXl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 变电站日电压合格率数据表(BDayVHg200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BDayVHg200808')
begin
create table BDayVHg200808
(
		rtu_id					int 		 not null,
		mp_id						smallint not null,
		date						int			 not null,
		time						int			 not null,

		hg_va_hh_tm			int		DEFAULT 0 ,								/*A相电压越上上限累计时间*/
		hg_va_ll_tm			int		DEFAULT 0 ,								/*A相电压越下下限累计时间*/
		hg_va_h_tm			int		DEFAULT 0 ,								/*A相电压越上限累计时间*/
		hg_va_l_tm			int		DEFAULT 0 ,								/*A相电压越下限累计时间*/
		hg_va_tm				int		DEFAULT 0 ,								/*A相电压合格率累计时间*/
		hg_vb_hh_tm			int		DEFAULT 0 ,								/*B相电压越上上限累计时间*/
		hg_vb_ll_tm			int		DEFAULT 0 ,								/*B相电压越下下限累计时间*/
		hg_vb_h_tm			int		DEFAULT 0 ,								/*B相电压越上限累计时间*/
		hg_vb_l_tm			int		DEFAULT 0 ,								/*B相电压越下限累计时间*/
		hg_vb_tm				int		DEFAULT 0 ,								/*B相电压合格率累计时间*/
		hg_vc_hh_tm			int		DEFAULT 0 ,								/*C相电压越上上限累计时间*/
		hg_vc_ll_tm			int		DEFAULT 0 ,								/*C相电压越下下限累计时间*/
		hg_vc_h_tm			int		DEFAULT 0 ,								/*C相电压越上限累计时间*/
		hg_vc_l_tm			int		DEFAULT 0 ,								/*C相电压越下限累计时间*/
		hg_vc_tm				int		DEFAULT 0 ,								/*C相电压合格率累计时间*/
		hg_va_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最大值*/
		hg_va_max_tm		int		DEFAULT 0 ,								/*A相电压最大值发生时间*/
		hg_va_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最小值*/
		hg_va_min_tm		int		DEFAULT 0 ,								/*A相电压最小值发生时间*/
		hg_vb_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最大值*/
		hg_vb_max_tm		int		DEFAULT 0 ,								/*B相电压最大值发生时间*/
		hg_vb_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最小值*/
		hg_vb_min_tm		int		DEFAULT 0 ,								/*B相电压最小值发生时间*/
		hg_vc_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最大值*/
		hg_vc_max_tm		int		DEFAULT 0 ,								/*C相电压最大值发生时间*/
		hg_vc_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最小值*/
		hg_vc_min_tm		int		DEFAULT 0 ,								/*C相电压最小值发生时间*/
		hg_va_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相平均电压*/
		hg_vb_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相平均电压*/
		hg_vc_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相平均电压*/
		
		hg_va_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越上限率*/
		hg_va_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越下限率*/
		hg_va_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压合格率*/
		hg_vb_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越上限率*/
		hg_vb_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越下限率*/
		hg_vb_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压合格率*/
		hg_vc_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越上限率*/
		hg_vc_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越下限率*/
		hg_vc_hgl 			decimal(14, 3) 	DEFAULT 0.0 		/*C相电压合格率*/			
)
grant select on  BDayVHg200808 to public
create unique clustered index BDayVHg200808index1
	on BDayVHg200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 变电站抄表日表底电量数据表(BMRBdDl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMRBdDl200808')
begin
create table BMRBdDl200808
(
	rtu_id		int			   			not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	bd_1xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*四相限无功表底*/
	
	dl_zy			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
		
	dl_1xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 4) 	DEFAULT 0.0, 	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
	
)
grant select on  BMRBdDl200808 to public
create unique clustered index BMRBdDl200808index1
	on BMRBdDl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 变电站月表底电量数据表(BMonBdDl2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMonBdDl2008')
begin
create table BMonBdDl2008
(
	rtu_id		int			   			not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	bd_1xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*四相限无功表底*/
	
	dl_zy			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
		
	dl_1xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 4) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 4) 	DEFAULT 0.0, 	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  BMonBdDl2008 to public
create unique clustered index BMonBdDl2008index1
	on BMonBdDl2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 变电站月需量数据表(BMonMaxXl2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMonMaxXl2008')
begin
create table BMonMaxXl2008
(
	rtu_id		int			   			not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,

	xl_zy					decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷需量*/
	xl_zy_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/	
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷需量*/	
	xl_zw_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总需量*/
	xl_fw_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷需量*/	
	xl_fw_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	
	
	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 ,		/*反向无功尖需量时间*/
)
grant select on  BMonMaxXl2008 to public
create unique clustered index BMonMaxXl2008index1
	on BMonMaxXl2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 变电站月电压合格率数据表(BMonVHg2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMonVHg2008')
begin
create table BMonVHg2008
(
		rtu_id					int 		 not null,
		mp_id						smallint not null,
		date						int			 not null,
		time						int			 not null,

		hg_va_hh_tm			int		DEFAULT 0 ,								/*A相电压越上上限累计时间*/
		hg_va_ll_tm			int		DEFAULT 0 ,								/*A相电压越下下限累计时间*/
		hg_va_h_tm			int		DEFAULT 0 ,								/*A相电压越上限累计时间*/
		hg_va_l_tm			int		DEFAULT 0 ,								/*A相电压越下限累计时间*/
		hg_va_tm				int		DEFAULT 0 ,								/*A相电压合格率累计时间*/
		hg_vb_hh_tm			int		DEFAULT 0 ,								/*B相电压越上上限累计时间*/
		hg_vb_ll_tm			int		DEFAULT 0 ,								/*B相电压越下下限累计时间*/
		hg_vb_h_tm			int		DEFAULT 0 ,								/*B相电压越上限累计时间*/
		hg_vb_l_tm			int		DEFAULT 0 ,								/*B相电压越下限累计时间*/
		hg_vb_tm				int		DEFAULT 0 ,								/*B相电压合格率累计时间*/
		hg_vc_hh_tm			int		DEFAULT 0 ,								/*C相电压越上上限累计时间*/
		hg_vc_ll_tm			int		DEFAULT 0 ,								/*C相电压越下下限累计时间*/
		hg_vc_h_tm			int		DEFAULT 0 ,								/*C相电压越上限累计时间*/
		hg_vc_l_tm			int		DEFAULT 0 ,								/*C相电压越下限累计时间*/
		hg_vc_tm				int		DEFAULT 0 ,								/*C相电压合格率累计时间*/
		hg_va_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最大值*/
		hg_va_max_tm		int		DEFAULT 0 ,								/*A相电压最大值发生时间*/
		hg_va_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最小值*/
		hg_va_min_tm		int		DEFAULT 0 ,								/*A相电压最小值发生时间*/
		hg_vb_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最大值*/
		hg_vb_max_tm		int		DEFAULT 0 ,								/*B相电压最大值发生时间*/
		hg_vb_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最小值*/
		hg_vb_min_tm		int		DEFAULT 0 ,								/*B相电压最小值发生时间*/
		hg_vc_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最大值*/
		hg_vc_max_tm		int		DEFAULT 0 ,								/*C相电压最大值发生时间*/
		hg_vc_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最小值*/
		hg_vc_min_tm		int		DEFAULT 0 ,								/*C相电压最小值发生时间*/
		hg_va_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相平均电压*/
		hg_vb_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相平均电压*/
		hg_vc_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相平均电压*/
		
		hg_va_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越上限率*/
		hg_va_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越下限率*/
		hg_va_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压合格率*/
		hg_vb_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越上限率*/
		hg_vb_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越下限率*/
		hg_vb_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压合格率*/
		hg_vc_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越上限率*/
		hg_vc_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越下限率*/
		hg_vc_hgl 			decimal(14, 3) 	DEFAULT 0.0 		/*C相电压合格率*/	
)
grant select on  BMonVHg2008 to public
create unique clustered index BMonVHg2008index1
	on BMonVHg2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--太钢IEC104接口用2015.12
--   '-------Create table 变电站分钟状态量数据表(BMinStat200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinStat200808')
begin
create table BMinStat200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	yx01    tinyint				      null,
	yx02    tinyint				      null,
	yx03    tinyint				      null,
	yx04    tinyint				      null,
	yx05    tinyint				      null,
	yx06    tinyint				      null,
	yx07    tinyint				      null,
	yx08    tinyint				      null,
	yx09    tinyint				      null,
	yx10    tinyint				      null,
	yx11    tinyint				      null,
	yx12    tinyint				      null,
	yx13    tinyint				      null,
	yx14    tinyint				      null,
	yx15    tinyint				      null,
	yx16    tinyint				      null,
	yx17    tinyint				      null,
	yx18    tinyint				      null,
	yx19    tinyint				      null,
	yx20    tinyint				      null,
)
grant select on  BMinStat200808 to public
create unique clustered index BMinStat200808index1
	on BMinStat200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

----------------------变电站应用结束--------------------------------------------

----------------------专变配变应用开始--------------------------------------------
--   '-------Create table 专变配变实时表底数据表(ZRealBD200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZRealBD200808')
begin
create table ZRealBD200808
(
	rtu_id			int			   		not null,
	mp_id				smallint   		not null,
	date				int						not null,
	time				int						not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖表底*/	
	
	bd_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 3) 	DEFAULT 0.0 	/*四相限无功表底*/
	
)
grant select on  ZRealBD200808 to public
create unique clustered index ZRealBD200808index1
	on ZRealBD200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变实时瞬时量数据表(ZRealSsl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZRealSsl200808')
begin
create table ZRealSsl200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	va			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相*/
	vb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相*/
	vc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相*/
	ia			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相*/
	ib			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相*/
	ic			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相*/
	i0			decimal(14, 3) 	DEFAULT 0.0 ,	/*	零序*/
	p				decimal(14, 3) 	DEFAULT 0.0 ,	/*	总有功*/
	pa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相有功*/
	pb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相有功*/
	pc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相有功*/
	q				decimal(14, 3) 	DEFAULT 0.0 ,	/*	总无功*/
	qa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相无功*/
	qb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相无功*/
	qc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相无功*/
	s				decimal(14, 3) 	DEFAULT 0.0 ,	/*	视在*/
	cs			decimal(14, 3) 	DEFAULT 0.0 ,	/*总功率因数*/
	csa			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相功率因数*/
	csb			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相功率因数*/
	csc			decimal(14, 3) 	DEFAULT 0.0 	/*C相功率因数*/
)
grant select on  ZRealSsl200808 to public
create unique clustered index ZRealSsl200808index1
	on ZRealSsl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	


--   '-------Create table 专变配变分钟表底电量数据表(ZMinBDDL200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinBDDL200808')
begin
create table ZMinBDDL200808
(
	rtu_id			int			   		not null,
	mp_id				smallint   		not null,
	date				int						not null,
	time				int						not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/		
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	

	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_source	tinyint					DEFAULT 0
)
grant select on  ZMinBDDL200808 to public
create unique clustered index ZMinBDDL200808index1
	on ZMinBDDL200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 专变配变分钟瞬时量数据表(ZMinSsl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinSsl200808')
begin
create table ZMinSsl200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	va			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相*/
	vb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相*/
	vc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相*/
	ia			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相*/
	ib			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相*/
	ic			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相*/
	i0			decimal(14, 3) 	DEFAULT 0.0 ,	/*	零序*/
	p				decimal(14, 3) 	DEFAULT 0.0 ,	/*	总有功*/
	pa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相有功*/
	pb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相有功*/
	pc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相有功*/
	q				decimal(14, 3) 	DEFAULT 0.0 ,	/*	总无功*/
	qa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相无功*/
	qb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相无功*/
	qc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相无功*/
	s				decimal(14, 3) 	DEFAULT 0.0 ,	/*	视在*/
	cs			decimal(14, 3) 	DEFAULT 0.0 ,	/*总功率因数*/
	csa			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相功率因数*/
	csb			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相功率因数*/
	csc			decimal(14, 3) 	DEFAULT 0.0 	/*C相功率因数*/
)
grant select on  ZMinSsl200808 to public
create unique clustered index ZMinSsl200808index1
	on ZMinSsl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	


--   '-------Create table 专变配变日表底电量数据表(ZDayBdDl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayBdDl200808')
begin
create table ZDayBdDl200808
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	bd_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*四相限无功表底*/
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
		
	dl_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 3) 	DEFAULT 0.0, 	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  ZDayBdDl200808 to public
create unique clustered index ZDayBdDl200808index1
	on ZDayBdDl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 专变配变日需量数据表(ZDayMaxXl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayMaxXl200808')
begin
create table ZDayMaxXl200808
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,

	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷需量*/	
	xl_zy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/		
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷需量*/	
	xl_zw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总需量*/	
	xl_fw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷需量*/
	xl_fw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	
	
	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 		/*反向无功尖需量时间*/
)
grant select on  ZDayMaxXl200808 to public
create unique clustered index ZDayMaxXl200808index1
	on ZDayMaxXl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变日电压合格率数据表(ZDayVHg200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayVHg200808')
begin
create table ZDayVHg200808
(
		rtu_id					int 		 not null,
		mp_id						smallint not null,
		date						int			 not null,
		time						int			 not null,

		hg_va_hh_tm			int		DEFAULT 0 ,								/*A相电压越上上限累计时间*/
		hg_va_ll_tm			int		DEFAULT 0 ,								/*A相电压越下下限累计时间*/
		hg_va_h_tm			int		DEFAULT 0 ,								/*A相电压越上限累计时间*/
		hg_va_l_tm			int		DEFAULT 0 ,								/*A相电压越下限累计时间*/
		hg_va_tm				int		DEFAULT 0 ,								/*A相电压合格率累计时间*/
		hg_vb_hh_tm			int		DEFAULT 0 ,								/*B相电压越上上限累计时间*/
		hg_vb_ll_tm			int		DEFAULT 0 ,								/*B相电压越下下限累计时间*/
		hg_vb_h_tm			int		DEFAULT 0 ,								/*B相电压越上限累计时间*/
		hg_vb_l_tm			int		DEFAULT 0 ,								/*B相电压越下限累计时间*/
		hg_vb_tm				int		DEFAULT 0 ,								/*B相电压合格率累计时间*/
		hg_vc_hh_tm			int		DEFAULT 0 ,								/*C相电压越上上限累计时间*/
		hg_vc_ll_tm			int		DEFAULT 0 ,								/*C相电压越下下限累计时间*/
		hg_vc_h_tm			int		DEFAULT 0 ,								/*C相电压越上限累计时间*/
		hg_vc_l_tm			int		DEFAULT 0 ,								/*C相电压越下限累计时间*/
		hg_vc_tm				int		DEFAULT 0 ,								/*C相电压合格率累计时间*/
		hg_va_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最大值*/
		hg_va_max_tm		int		DEFAULT 0 ,								/*A相电压最大值发生时间*/
		hg_va_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最小值*/
		hg_va_min_tm		int		DEFAULT 0 ,								/*A相电压最小值发生时间*/
		hg_vb_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最大值*/
		hg_vb_max_tm		int		DEFAULT 0 ,								/*B相电压最大值发生时间*/
		hg_vb_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最小值*/
		hg_vb_min_tm		int		DEFAULT 0 ,								/*B相电压最小值发生时间*/
		hg_vc_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最大值*/
		hg_vc_max_tm		int		DEFAULT 0 ,								/*C相电压最大值发生时间*/
		hg_vc_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最小值*/
		hg_vc_min_tm		int		DEFAULT 0 ,								/*C相电压最小值发生时间*/
		hg_va_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相平均电压*/
		hg_vb_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相平均电压*/
		hg_vc_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相平均电压*/
		
		hg_va_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越上限率*/
		hg_va_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越下限率*/
		hg_va_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压合格率*/
		hg_vb_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越上限率*/
		hg_vb_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越下限率*/
		hg_vb_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压合格率*/
		hg_vc_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越上限率*/
		hg_vc_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越下限率*/
		hg_vc_hgl 			decimal(14, 3) 	DEFAULT 0.0 		/*C相电压合格率*/	
)
grant select on  ZDayVHg200808 to public
create unique clustered index ZDayVHg200808index1
	on ZDayVHg200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变日电流越限数据表(ZDayILmt200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayILmt200808')
begin
create table ZDayILmt200808
(
		rtu_id					int  			not null,
		mp_id						smallint 	not null,
		date						int				not null,
		time						int				not null,

		lmt_ia_hh_tm		int				DEFAULT 0 ,			/*A相电流越上上限累计时间*/
		lmt_ia_h_tm			int				DEFAULT 0 ,			/*A相电流越上限累计时间*/
		lmt_ib_hh_tm		int				DEFAULT 0 ,			/*B相电流越上上限累计时间*/
		lmt_ib_h_tm			int				DEFAULT 0 ,			/*B相电流越上限累计时间*/
		lmt_ic_hh_tm		int				DEFAULT 0 ,			/*C相电流越上上限累计时间*/
		lmt_ic_h_tm			int				DEFAULT 0 ,			/*C相电流越上限累计时间*/
		lmt_i0_h_tm			int				DEFAULT 0 ,			/*零序电流越上限累计时间*/
		lmt_ia_max			decimal(14, 3)DEFAULT 0.0,/*A相电流最大值*/
		lmt_ia_max_tm		int				DEFAULT 0 ,			/*A相电流最大值发生时间*/
		lmt_ib_max			decimal(14, 3)DEFAULT 0.0,/*B相电流最大值*/
		lmt_ib_max_tm		int				DEFAULT 0 ,			/*B相电流最大值发生时间*/
		lmt_ic_max			decimal(14, 3)DEFAULT 0.0,/*C相电流最大值*/
		lmt_ic_max_tm		int				DEFAULT 0 ,			/*C相电流最大值发生时间*/
		lmt_i0_max			decimal(14, 3)DEFAULT 0.0,/*零序电流最大值*/
		lmt_i0_max_tm		int				DEFAULT 0 			/*零序电流最大值发生时间*/
)
grant select on  ZDayILmt200808 to public
create unique clustered index ZDayILmt200808index1
	on ZDayILmt200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 专变配变日断相统计数据表(ZDayDX200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayDX200808')
begin
create table ZDayDX200808
(
		rtu_id		int 			not null,
		mp_id			smallint	not null,
		date			int				not null,
		time			int				not null,
	
		dx_tms		int				DEFAULT 0 ,		/*总断相次数*/
		dx_a_tms	int				DEFAULT 0 ,		/*A相断相次数*/
		dx_b_tms	int				DEFAULT 0 ,		/*B相断相次数*/
		dx_c_tms	int				DEFAULT 0 ,		/*C相断相次数*/
		dx_tm			int				DEFAULT 0 ,		/*断相累计时间*/
		dx_a_tm		int				DEFAULT 0 ,		/*A断相累计时间*/
		dx_b_tm		int				DEFAULT 0 ,		/*B断相累计时间*/
		dx_c_tm		int				DEFAULT 0 ,		/*C断相累计时间*/
		dx_1_s_tm	int				DEFAULT 0 ,		/*最近一次断相起始时刻*/
		dx_a_s_tm	int				DEFAULT 0 ,		/*A相最近断相起始时刻*/
		dx_b_s_tm	int				DEFAULT 0 ,		/*B相最近断相起始时刻*/
		dx_c_s_tm	int				DEFAULT 0 ,		/*C相最近断相起始时刻*/
		dx_1_e_tm	int				DEFAULT 0 ,		/*最近一次断相结束时刻*/
		dx_a_e_tm	int				DEFAULT 0 ,		/*A相最近断相结束时刻*/
		dx_b_e_tm	int				DEFAULT 0 ,		/*B相最近断相结束时刻*/
		dx_c_e_tm	int				DEFAULT 0 ,		/*C相最近断相结束时刻*/

)
grant select on  ZDayDX200808 to public
create unique clustered index ZDayDX200808index1
	on ZDayDX200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变日功率极值数据表(ZDayPMax200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayPMax200808')
begin
create table ZDayPMax200808
(
		rtu_id				int	   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		ext_p_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最大有功功率*/	
		ext_p_max_tm	int		DEFAULT 0 ,							/*总最大有功功率发生时间*/
		ext_p_a				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相最大有功功率*/
		ext_p_a_tm		int		DEFAULT 0 ,							/*A相最大有功功率发生时间*/
		ext_p_b				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相最大有功功率*/
		ext_p_b_tm		int		DEFAULT 0 ,							/*B相最大有功功率发生时间*/
		ext_p_c				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相最大有功功率*/
		ext_p_c_tm		int		DEFAULT 0 ,							/*C相最大有功功率发生时间*/
		ext_p_min			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最有功小功率*/
		ext_p_min_tm	int		DEFAULT 0 ,							/*总最小有功功率发生时间*/
		ext_p_0_tm		int		DEFAULT 0 ,							/*总有功功率为零时间*/
		ext_p_a0_tm		int		DEFAULT 0 ,							/*A相有功功率为零时间*/
		ext_p_b0_tm		int		DEFAULT 0 ,							/*B相有功功率为零时间*/
		ext_p_c0_tm		int		DEFAULT 0								/*C相有功功率为零时间*/

)
grant select on  ZDayPMax200808 to public
create unique clustered index ZDayPMax200808index1
	on ZDayPMax200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 专变配变日电流不平衡数据表(ZDayIBal200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayIBal200808')
begin
create table ZDayIBal200808
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		bph_i_tm			int							DEFAULT 0 ,			/*电流不平衡度越限累计时间*/
		bph_v_tm			int							DEFAULT 0 ,			/*电压不平衡度越限累计时间*/ 
		bph_i_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*电流不平衡最大值*/
		bph_i_max_tm	int							DEFAULT 0 ,			/*电流不平衡最大值发生时间*/
		bph_v_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*电压不平衡最大值*/
		bph_v_max_tm	int							DEFAULT 0			  /*电压不平衡最大值发生时间*/
)
grant select on  ZDayIBal200808 to public
create unique clustered index ZDayIBal200808index1
	on ZDayIBal200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变日负载数据表(ZDayLoad200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayLoad200808')
begin
create table ZDayLoad200808
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		fz_max				decimal(14, 3) 	DEFAULT 0.0,	/*负载率最大值*/	
		fz_max_tm			int							DEFAULT 0 ,		/*负载率最大值发生时间*/
		fz_min				decimal(14, 3) 	DEFAULT 0.0,	/*负载率最小值*/
		fz_min_tm			int							DEFAULT 0 		/*负载率最小值发生时间*/
)
grant select on  ZDayLoad200808 to public
create unique clustered index ZDayLoad200808index1
	on ZDayLoad200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 专变配变日功率因数区段累计时间(ZDayCos200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayCos200808')
begin
create table ZDayCos200808
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		cs_tm_1				int							DEFAULT 0 ,		/*区段1累计时间（功率因数＜定值1）*/
		cs_tm_2				int							DEFAULT 0 ,		/*区段2累计时间（定值1≤功率因数＜定值2）*/
		cs_tm_3				int							DEFAULT 0 		/*区段3累计时间（功率因数≥定值2）*/
)
grant select on  ZDayCos200808 to public
create unique clustered index ZDayCos200808index1
	on ZDayCos200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变电表预付费日余额表(ZDayYffMoney200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayYffMoney200808')
begin
create table ZDayYffMoney200808
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,

	remain_value			decimal(14, 3) 	DEFAULT 0.0 ,	/*剩余金额*/
	credit_value			decimal(14, 3) 	DEFAULT 0.0 ,	/*赊欠金额*/
)
grant select on  ZDayYffMoney200808 to public
create unique clustered index ZDayYffMoney200808index1
	on ZDayYffMoney200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 专变配变抄表日表底电量数据表(ZMRBdDl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMRBdDl200808')
begin
create table ZMRBdDl200808
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	bd_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*四相限无功表底*/
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
		
	dl_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 3) 	DEFAULT 0.0, 	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  ZMRBdDl200808 to public
create unique clustered index ZMRBdDl200808index1
	on ZMRBdDl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变月表底电量数据表(ZMonBdDl2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonBdDl2008')
begin
create table ZMonBdDl2008
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	bd_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*四相限无功表底*/
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
		
	dl_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 3) 	DEFAULT 0.0, 	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  ZMonBdDl2008 to public
create unique clustered index ZMonBdDl2008index1
	on ZMonBdDl2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 专变配变月需量数据表(ZMonMaxXl2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonMaxXl2008')
begin
create table ZMonMaxXl2008
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,

	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷需量*/	
	xl_zy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/	
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷需量*/	
	xl_zw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总需量*/	
	xl_fw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷需量*/	
	xl_fw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	

	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 		/*反向无功尖需量时间*/
)
grant select on  ZMonMaxXl2008 to public
create unique clustered index ZMonMaxXl2008index1
	on ZMonMaxXl2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变月电压合格率数据表(ZMonVHg2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonVHg2008')
begin
create table ZMonVHg2008
(
		rtu_id					int   		not null,
		mp_id						smallint	not null,
		date						int				not null,
		time						int				not null,
	
		hg_va_hh_tm			int		DEFAULT 0 ,								/*A相电压越上上限累计时间*/
		hg_va_ll_tm			int		DEFAULT 0 ,								/*A相电压越下下限累计时间*/
		hg_va_h_tm			int		DEFAULT 0 ,								/*A相电压越上限累计时间*/
		hg_va_l_tm			int		DEFAULT 0 ,								/*A相电压越下限累计时间*/
		hg_va_tm				int		DEFAULT 0 ,								/*A相电压合格率累计时间*/
		hg_vb_hh_tm			int		DEFAULT 0 ,								/*B相电压越上上限累计时间*/
		hg_vb_ll_tm			int		DEFAULT 0 ,								/*B相电压越下下限累计时间*/
		hg_vb_h_tm			int		DEFAULT 0 ,								/*B相电压越上限累计时间*/
		hg_vb_l_tm			int		DEFAULT 0 ,								/*B相电压越下限累计时间*/
		hg_vb_tm				int		DEFAULT 0 ,								/*B相电压合格率累计时间*/
		hg_vc_hh_tm			int		DEFAULT 0 ,								/*C相电压越上上限累计时间*/
		hg_vc_ll_tm			int		DEFAULT 0 ,								/*C相电压越下下限累计时间*/
		hg_vc_h_tm			int		DEFAULT 0 ,								/*C相电压越上限累计时间*/
		hg_vc_l_tm			int		DEFAULT 0 ,								/*C相电压越下限累计时间*/
		hg_vc_tm				int		DEFAULT 0 ,								/*C相电压合格率累计时间*/
		hg_va_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最大值*/
		hg_va_max_tm		int		DEFAULT 0 ,								/*A相电压最大值发生时间*/
		hg_va_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最小值*/
		hg_va_min_tm		int		DEFAULT 0 ,								/*A相电压最小值发生时间*/
		hg_vb_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最大值*/
		hg_vb_max_tm		int		DEFAULT 0 ,								/*B相电压最大值发生时间*/
		hg_vb_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最小值*/
		hg_vb_min_tm		int		DEFAULT 0 ,								/*B相电压最小值发生时间*/
		hg_vc_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最大值*/
		hg_vc_max_tm		int		DEFAULT 0 ,								/*C相电压最大值发生时间*/
		hg_vc_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最小值*/
		hg_vc_min_tm		int		DEFAULT 0 ,								/*C相电压最小值发生时间*/
		hg_va_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相平均电压*/
		hg_vb_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相平均电压*/
		hg_vc_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相平均电压*/
		
		hg_va_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越上限率*/
		hg_va_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越下限率*/
		hg_va_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压合格率*/
		hg_vb_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越上限率*/
		hg_vb_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越下限率*/
		hg_vb_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压合格率*/
		hg_vc_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越上限率*/
		hg_vc_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越下限率*/
		hg_vc_hgl 			decimal(14, 3) 	DEFAULT 0.0 		/*C相电压合格率*/	
)
grant select on  ZMonVHg2008 to public
create unique clustered index ZMonVHg2008index1
	on ZMonVHg2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 专变配变月电流越限数据表(ZMonILmt2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonILmt2008')
begin
create table ZMonILmt2008
(
		rtu_id					int  			not null,
		mp_id						smallint 	not null,
		date						int				not null,
		time						int				not null,

		lmt_ia_hh_tm		int				DEFAULT 0 ,			/*A相电流越上上限累计时间*/
		lmt_ia_h_tm			int				DEFAULT 0 ,			/*A相电流越上限累计时间*/
		lmt_ib_hh_tm		int				DEFAULT 0 ,			/*B相电流越上上限累计时间*/
		lmt_ib_h_tm			int				DEFAULT 0 ,			/*B相电流越上限累计时间*/
		lmt_ic_hh_tm		int				DEFAULT 0 ,			/*C相电流越上上限累计时间*/
		lmt_ic_h_tm			int				DEFAULT 0 ,			/*C相电流越上限累计时间*/
		lmt_i0_h_tm			int				DEFAULT 0 ,			/*零序电流越上限累计时间*/
		lmt_ia_max			decimal(14, 3)DEFAULT 0.0,/*A相电流最大值*/
		lmt_ia_max_tm		int				DEFAULT 0 ,			/*A相电流最大值发生时间*/
		lmt_ib_max			decimal(14, 3)DEFAULT 0.0,/*B相电流最大值*/
		lmt_ib_max_tm		int				DEFAULT 0 ,			/*B相电流最大值发生时间*/
		lmt_ic_max			decimal(14, 3)DEFAULT 0.0,/*C相电流最大值*/
		lmt_ic_max_tm		int				DEFAULT 0 ,			/*C相电流最大值发生时间*/
		lmt_i0_max			decimal(14, 3)DEFAULT 0.0,/*零序电流最大值*/
		lmt_i0_max_tm		int				DEFAULT 0 			/*零序电流最大值发生时间*/
)
grant select on  ZMonILmt2008 to public
create unique clustered index ZMonILmt2008index1
	on ZMonILmt2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变月断相统计数据表(ZMonDx2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonDx2008')
begin
create table ZMonDx2008
(
		rtu_id		int 			not null,
		mp_id			smallint	not null,
		date			int				not null,
		time			int				not null,
	
		dx_tms		int				DEFAULT 0 ,		/*总断相次数*/
		dx_a_tms	int				DEFAULT 0 ,		/*A相断相次数*/
		dx_b_tms	int				DEFAULT 0 ,		/*B相断相次数*/
		dx_c_tms	int				DEFAULT 0 ,		/*C相断相次数*/
		dx_tm			int				DEFAULT 0 ,		/*断相累计时间*/
		dx_a_tm		int				DEFAULT 0 ,		/*A断相累计时间*/
		dx_b_tm		int				DEFAULT 0 ,		/*B断相累计时间*/
		dx_c_tm		int				DEFAULT 0 ,		/*C断相累计时间*/
		dx_1_s_tm	int				DEFAULT 0 ,		/*最近一次断相起始时刻*/
		dx_a_s_tm	int				DEFAULT 0 ,		/*A相最近断相起始时刻*/
		dx_b_s_tm	int				DEFAULT 0 ,		/*B相最近断相起始时刻*/
		dx_c_s_tm	int				DEFAULT 0 ,		/*C相最近断相起始时刻*/
		dx_1_e_tm	int				DEFAULT 0 ,		/*最近一次断相结束时刻*/
		dx_a_e_tm	int				DEFAULT 0 ,		/*A相最近断相结束时刻*/
		dx_b_e_tm	int				DEFAULT 0 ,		/*B相最近断相结束时刻*/
		dx_c_e_tm	int				DEFAULT 0 		/*C相最近断相结束时刻*/
)
grant select on  ZMonDx2008 to public
create unique clustered index ZMonDx2008index1
	on ZMonDx2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 专变配变月功率极值数据表(ZMonPMax2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonPMax2008')
begin
create table ZMonPMax2008
(
		rtu_id				int	   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		ext_p_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最大有功功率*/	
		ext_p_max_tm	int		DEFAULT 0 ,							/*总最大有功功率发生时间*/
		ext_p_a				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相最大有功功率*/
		ext_p_a_tm		int		DEFAULT 0 ,							/*A相最大有功功率发生时间*/
		ext_p_b				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相最大有功功率*/
		ext_p_b_tm		int		DEFAULT 0 ,							/*B相最大有功功率发生时间*/
		ext_p_c				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相最大有功功率*/
		ext_p_c_tm		int		DEFAULT 0 ,							/*C相最大有功功率发生时间*/
		ext_p_min			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最有功小功率*/
		ext_p_min_tm	int		DEFAULT 0 ,							/*总最小有功功率发生时间*/
		ext_p_0_tm		int		DEFAULT 0 ,							/*总有功功率为零时间*/
		ext_p_a0_tm		int		DEFAULT 0 ,							/*A相有功功率为零时间*/
		ext_p_b0_tm		int		DEFAULT 0 ,							/*B相有功功率为零时间*/
		ext_p_c0_tm		int		DEFAULT 0								/*C相有功功率为零时间*/
)
grant select on  ZMonPMax2008 to public
create unique clustered index ZMonPMax2008index1
	on ZMonPMax2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变月不平衡数据表(ZMonIBal2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonIBal2008')
begin
create table ZMonIBal2008
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		bph_i_tm			int							DEFAULT 0 ,			/*电流不平衡度越限累计时间*/
		bph_v_tm			int							DEFAULT 0 ,			/*电压不平衡度越限累计时间*/ 
		bph_i_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*电流不平衡最大值*/
		bph_i_max_tm	int							DEFAULT 0 ,			/*电流不平衡最大值发生时间*/
		bph_v_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*电压不平衡最大值*/
		bph_v_max_tm	int							DEFAULT 0			  /*电压不平衡最大值发生时间*/
)
grant select on  ZMonIBal2008 to public
create unique clustered index ZMonIBal2008index1
	on ZMonIBal2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变月负载数据表(ZMonLoad2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonLoad2008')
begin
create table ZMonLoad2008
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		fz_max				decimal(14, 3) 	DEFAULT 0.0,	/*负载率最大值*/	
		fz_max_tm			int							DEFAULT 0 ,		/*负载率最大值发生时间*/
		fz_min				decimal(14, 3) 	DEFAULT 0.0,	/*负载率最小值*/
		fz_min_tm			int							DEFAULT 0 		/*负载率最小值发生时间*/
)
grant select on  ZMonLoad2008 to public
create unique clustered index ZMonLoad2008index1
	on ZMonLoad2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变月功率因数区段累计时间(ZMonCos2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonCos2008')
begin
create table ZMonCos2008
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		cs_tm_1				int							DEFAULT 0 ,		/*区段1累计时间（功率因数＜定值1）*/
		cs_tm_2				int							DEFAULT 0 ,		/*区段2累计时间（定值1≤功率因数＜定值2）*/
		cs_tm_3				int							DEFAULT 0 		/*区段3累计时间（功率因数≥定值2）*/
)
grant select on  ZMonCos2008 to public
create unique clustered index ZMonCos2008index1
	on ZMonCos2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变谐波电流数据表(ZMonXbI2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonXbI2008')
begin
create table ZMonXbI2008
(
		rtu_id						int   					not null,
		mp_id							smallint   			not null,
		date							int							not null,
		time							int							not null,
		
		xb_ia_02					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电流最大值*/
		xb_ia_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电流最大值*/
		xb_ia_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电流最大值*/
		xb_ia_05			    decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电流最大值*/
		xb_ia_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电流最大值*/
		xb_ia_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电流最大值*/
		xb_ia_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电流最大值*/
		xb_ia_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电流最大值*/
		xb_ia_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电流最大值*/
		xb_ia_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电流最大值*/
		xb_ia_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电流最大值*/
		xb_ia_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电流最大值*/
		xb_ia_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电流最大值*/
		xb_ia_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电流最大值*/
		xb_ia_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电流最大值*/
		xb_ia_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电流最大值*/
		xb_ia_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电流最大值*/
		xb_ia_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电流最大值*/
		xb_ia_20					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电流最大值*/
		xb_ia_21					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电流最大值*/
		xb_ia							decimal(14, 3) 	DEFAULT 0.0 ,	/*A相总畸变电流最大值*/
		
		xb_ia_02_tm				int		DEFAULT 0 ,							/*A相2次谐波电流最大值发生时间*/
		xb_ia_03_tm				int		DEFAULT 0 ,							/*A相3次谐波电流最大值发生时间*/
		xb_ia_04_tm				int		DEFAULT 0 ,							/*A相4次谐波电流最大值发生时间*/
		xb_ia_05_tm				int		DEFAULT 0 ,							/*A相5次谐波电流最大值发生时间*/
		xb_ia_06_tm				int		DEFAULT 0 ,							/*A相6次谐波电流最大值发生时间*/
		xb_ia_07_tm				int		DEFAULT 0 ,							/*A相7次谐波电流最大值发生时间*/
		xb_ia_08_tm				int		DEFAULT 0 ,							/*A相8次谐波电流最大值发生时间*/
		xb_ia_09_tm				int		DEFAULT 0 ,							/*A相9次谐波电流最大值发生时间*/
		xb_ia_10_tm				int		DEFAULT 0 ,							/*A相10次谐波电流最大值发生时间*/
		xb_ia_11_tm				int		DEFAULT 0 ,							/*A相11次谐波电流最大值发生时间*/
		xb_ia_12_tm				int		DEFAULT 0 ,							/*A相12次谐波电流最大值发生时间*/
		xb_ia_13_tm				int		DEFAULT 0 ,							/*A相13次谐波电流最大值发生时间*/
		xb_ia_14_tm				int		DEFAULT 0 ,							/*A相14次谐波电流最大值发生时间*/
		xb_ia_15_tm				int		DEFAULT 0 ,							/*A相15次谐波电流最大值发生时间*/
		xb_ia_16_tm				int		DEFAULT 0 ,							/*A相16次谐波电流最大值发生时间*/
		xb_ia_17_tm				int		DEFAULT 0 ,							/*A相17次谐波电流最大值发生时间*/
		xb_ia_18_tm				int		DEFAULT 0 ,							/*A相18次谐波电流最大值发生时间*/
		xb_ia_19_tm				int		DEFAULT 0 ,							/*A相19次谐波电流最大值发生时间*/
		xb_ia_20_tm				int		DEFAULT 0 ,							/*A相20次谐波电流最大值发生时间*/
		xb_ia_21_tm				int		DEFAULT 0 ,							/*A相21次谐波电流最大值发生时间*/
		xb_ia_tm					int		DEFAULT 0 ,					 	  /*A相总畸变电流最大值发生时间*/
		
		
		xb_ib_02					decimal(14, 3) 	DEFAULT 0.0,	/*B相2次谐波电流最大值*/
		xb_ib_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电流最大值*/
		xb_ib_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电流最大值*/
		xb_ib_05					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电流最大值*/
		xb_ib_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电流最大值*/
		xb_ib_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电流最大值*/
		xb_ib_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电流最大值*/
		xb_ib_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电流最大值*/
		xb_ib_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电流最大值*/
		xb_ib_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电流最大值*/
		xb_ib_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电流最大值*/
		xb_ib_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电流最大值*/
		xb_ib_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电流最大值*/
		xb_ib_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电流最大值*/
		xb_ib_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电流最大值*/
		xb_ib_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电流最大值*/
		xb_ib_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电流最大值*/
		xb_ib_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电流最大值*/
		xb_ib_20					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电流最大值*/
		xb_ib_21					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电流最大值*/
		xb_ib							decimal(14, 3) 	DEFAULT 0.0 ,	/*B相总畸变电流最大值*/
		
		xb_ib_02_tm				int		DEFAULT 0 ,							/*B相2次谐波电流最大值发生时间*/
		xb_ib_03_tm				int		DEFAULT 0 ,							/*B相3次谐波电流最大值发生时间*/
		xb_ib_04_tm				int		DEFAULT 0 ,							/*B相4次谐波电流最大值发生时间*/
		xb_ib_05_tm				int		DEFAULT 0 ,							/*B相5次谐波电流最大值发生时间*/
		xb_ib_06_tm				int		DEFAULT 0 ,							/*B相6次谐波电流最大值发生时间*/
		xb_ib_07_tm				int		DEFAULT 0 ,							/*B相7次谐波电流最大值发生时间*/
		xb_ib_08_tm				int		DEFAULT 0 ,							/*B相8次谐波电流最大值发生时间*/
		xb_ib_09_tm				int		DEFAULT 0 ,							/*B相9次谐波电流最大值发生时间*/
		xb_ib_10_tm				int		DEFAULT 0 ,							/*B相10次谐波电流最大值发生时间*/
		xb_ib_11_tm				int		DEFAULT 0 ,							/*B相11次谐波电流最大值发生时间*/
		xb_ib_12_tm				int		DEFAULT 0 ,							/*B相12次谐波电流最大值发生时间*/
		xb_ib_13_tm				int		DEFAULT 0 ,							/*B相13次谐波电流最大值发生时间*/
		xb_ib_14_tm				int		DEFAULT 0 ,							/*B相14次谐波电流最大值发生时间*/
		xb_ib_15_tm				int		DEFAULT 0 ,							/*B相15次谐波电流最大值发生时间*/
		xb_ib_16_tm				int		DEFAULT 0 ,							/*B相16次谐波电流最大值发生时间*/
		xb_ib_17_tm				int		DEFAULT 0 ,							/*B相17次谐波电流最大值发生时间*/
		xb_ib_18_tm				int		DEFAULT 0 ,							/*B相18次谐波电流最大值发生时间*/
		xb_ib_19_tm				int		DEFAULT 0 ,							/*B相19次谐波电流最大值发生时间*/
		xb_ib_20_tm				int		DEFAULT 0 ,							/*B相20次谐波电流最大值发生时间*/
		xb_ib_21_tm				int		DEFAULT 0 ,							/*B相21次谐波电流最大值发生时间*/
		xb_ib_tm					int		DEFAULT 0 ,					 		/*B相总畸变电流最大值发生时间*/
		
		
		xb_ic_02					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相2次谐波电流最大值*/
		xb_ic_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电流最大值*/
		xb_ic_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电流最大值*/
		xb_ic_05					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电流最大值*/
		xb_ic_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电流最大值*/
		xb_ic_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电流最大值*/
		xb_ic_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电流最大值*/
		xb_ic_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电流最大值*/
		xb_ic_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电流最大值*/
		xb_ic_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电流最大值*/
		xb_ic_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电流最大值*/
		xb_ic_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电流最大值*/
		xb_ic_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电流最大值*/
		xb_ic_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电流最大值*/
		xb_ic_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电流最大值*/
		xb_ic_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电流最大值*/
		xb_ic_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电流最大值*/
		xb_ic_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电流最大值*/
		xb_ic_20					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电流最大值*/
		xb_ic_21					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相21次谐波电流最大值*/
		xb_ic							decimal(14, 3) 	DEFAULT 0.0 ,	/*C相总畸变电流最大值*/
		
		xb_ic_02_tm				int		DEFAULT 0 ,							/*C相2次谐波电流最大值发生时间*/
		xb_ic_03_tm				int		DEFAULT 0 ,							/*C相3次谐波电流最大值发生时间*/
		xb_ic_04_tm				int		DEFAULT 0 ,							/*C相4次谐波电流最大值发生时间*/
		xb_ic_05_tm				int		DEFAULT 0 ,							/*C相5次谐波电流最大值发生时间*/
		xb_ic_06_tm				int		DEFAULT 0 ,							/*C相6次谐波电流最大值发生时间*/
		xb_ic_07_tm				int		DEFAULT 0 ,							/*C相7次谐波电流最大值发生时间*/
		xb_ic_08_tm				int		DEFAULT 0 ,							/*C相8次谐波电流最大值发生时间*/
		xb_ic_09_tm				int		DEFAULT 0 ,							/*C相9次谐波电流最大值发生时间*/
		xb_ic_10_tm				int		DEFAULT 0 ,							/*C相10次谐波电流最大值发生时间*/
		xb_ic_11_tm				int		DEFAULT 0 ,							/*C相11次谐波电流最大值发生时间*/
		xb_ic_12_tm				int		DEFAULT 0 ,							/*C相12次谐波电流最大值发生时间*/
		xb_ic_13_tm				int		DEFAULT 0 ,							/*C相13次谐波电流最大值发生时间*/
		xb_ic_14_tm				int		DEFAULT 0 ,							/*C相14次谐波电流最大值发生时间*/
		xb_ic_15_tm				int		DEFAULT 0 ,							/*C相15次谐波电流最大值发生时间*/
		xb_ic_16_tm				int		DEFAULT 0 ,							/*C相16次谐波电流最大值发生时间*/
		xb_ic_17_tm				int		DEFAULT 0 ,							/*C相17次谐波电流最大值发生时间*/
		xb_ic_18_tm				int		DEFAULT 0 ,							/*C相18次谐波电流最大值发生时间*/
		xb_ic_19_tm				int		DEFAULT 0 ,							/*C相19次谐波电流最大值发生时间*/
		xb_ic_20_tm				int		DEFAULT 0 ,							/*C相20次谐波电流最大值发生时间*/
		xb_ic_21_tm				int		DEFAULT 0 ,							/*C相21次谐波电流最大值发生时间*/
		xb_ic_tm					int		DEFAULT 0 					 		/*C相总畸变电流最大值发生时间*/

)
grant select on  ZMonXbI2008 to public
create unique clustered index ZMonXbI2008index1
	on ZMonXbI2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变谐波电压数据表(ZMonXbU2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonXbU2008')
begin
create table ZMonXbU2008
(
		rtu_id					int   					not null,
		mp_id						smallint   			not null,
		date						int							not null,
		time						int							not null,
		
		xb_va_02				decimal(14, 3) 	DEFAULT 0.0,	/*A相2次谐波电压含有率最大值*/
		xb_va_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电压含有率最大值*/
		xb_va_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电压含有率最大值*/
		xb_va_05		    decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电压含有率最大值*/
		xb_va_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电压含有率最大值*/
		xb_va_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电压含有率最大值*/
		xb_va_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电压含有率最大值*/
		xb_va_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电压含有率最大值*/
		xb_va_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电压含有率最大值*/
		xb_va_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电压含有率最大值*/
		xb_va_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电压含有率最大值*/
		xb_va_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电压含有率最大值*/
		xb_va_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电压含有率最大值*/
		xb_va_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电压含有率最大值*/
		xb_va_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电压含有率最大值*/
		xb_va_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电压含有率最大值*/
		xb_va_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电压含有率最大值*/
		xb_va_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电压含有率最大值*/
		xb_va_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电压含有率最大值*/
		xb_va_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电压含有率最大值*/
		xb_va						decimal(14, 3) 	DEFAULT 0.0 ,	/*A相总畸变电流最大值*/
				
		xb_va_02_tm			int		DEFAULT 0 ,							/*A相2次谐波电压含有率最大值发生时间*/
		xb_va_03_tm			int		DEFAULT 0 ,							/*A相3次谐波电压含有率最大值发生时间*/
		xb_va_04_tm			int		DEFAULT 0 ,							/*A相4次谐波电压含有率最大值发生时间*/
		xb_va_05_tm			int		DEFAULT 0 ,							/*A相5次谐波电压含有率最大值发生时间*/
		xb_va_06_tm			int		DEFAULT 0 ,							/*A相6次谐波电压含有率最大值发生时间*/
		xb_va_07_tm			int		DEFAULT 0 ,							/*A相7次谐波电压含有率最大值发生时间*/
		xb_va_08_tm			int		DEFAULT 0 ,							/*A相8次谐波电压含有率最大值发生时间*/
		xb_va_09_tm			int		DEFAULT 0 ,							/*A相9次谐波电压含有率最大值发生时间*/
		xb_va_10_tm			int		DEFAULT 0 ,							/*A相10次谐波电压含有率最大值发生时间*/
		xb_va_11_tm			int		DEFAULT 0 ,							/*A相11次谐波电压含有率最大值发生时间*/
		xb_va_12_tm			int		DEFAULT 0 ,							/*A相12次谐波电压含有率最大值发生时间*/
		xb_va_13_tm			int		DEFAULT 0 ,							/*A相13次谐波电压含有率最大值发生时间*/
		xb_va_14_tm			int		DEFAULT 0 ,							/*A相14次谐波电压含有率最大值发生时间*/
		xb_va_15_tm			int		DEFAULT 0 ,							/*A相15次谐波电压含有率最大值发生时间*/
		xb_va_16_tm			int		DEFAULT 0 ,							/*A相16次谐波电压含有率最大值发生时间*/
		xb_va_17_tm			int		DEFAULT 0 ,							/*A相17次谐波电压含有率最大值发生时间*/
		xb_va_18_tm			int		DEFAULT 0 ,							/*A相18次谐波电压含有率最大值发生时间*/
		xb_va_19_tm			int		DEFAULT 0 ,							/*A相19次谐波电压含有率最大值发生时间*/
		xb_va_20_tm			int		DEFAULT 0 ,							/*A相20次谐波电压含有率最大值发生时间*/
		xb_va_21tm			int		DEFAULT 0 ,							/*A相21次谐波电压含有率最大值发生时间*/
		xb_va_tm				int		DEFAULT 0 ,					 		/*A相总畸变电流最大值发生时间*/
		
		xb_vb_02				decimal(14, 3) 	DEFAULT 0.0,	/*B相2次谐波电压含有率最大值*/
		xb_vb_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电压含有率最大值*/
		xb_vb_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电压含有率最大值*/
		xb_vb_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电压含有率最大值*/
		xb_vb_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电压含有率最大值*/
		xb_vb_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电压含有率最大值*/
		xb_vb_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电压含有率最大值*/
		xb_vb_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电压含有率最大值*/
		xb_vb_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电压含有率最大值*/
		xb_vb_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电压含有率最大值*/
		xb_vb_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电压含有率最大值*/
		xb_vb_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电压含有率最大值*/
		xb_vb_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电压含有率最大值*/
		xb_vb_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电压含有率最大值*/
		xb_vb_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电压含有率最大值*/
		xb_vb_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电压含有率最大值*/
		xb_vb_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电压含有率最大值*/
		xb_vb_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电压含有率最大值*/
		xb_vb_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电压含有率最大值*/
		xb_vb_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电压含有率最大值*/
		xb_vb						decimal(14, 3) 	DEFAULT 0.0 ,	/*B相总畸变电流最大值*/
		
		xb_vb_02_tm			int		DEFAULT 0 ,							/*B相2次谐波电压含有率最大值发生时间*/
		xb_vb_03_tm			int		DEFAULT 0 ,							/*B相3次谐波电压含有率最大值发生时间*/
		xb_vb_04_tm			int		DEFAULT 0 ,							/*B相4次谐波电压含有率最大值发生时间*/
		xb_vb_05_tm			int		DEFAULT 0 ,							/*B相5次谐波电压含有率最大值发生时间*/
		xb_vb_06_tm			int		DEFAULT 0 ,							/*B相6次谐波电压含有率最大值发生时间*/
		xb_vb_07_tm			int		DEFAULT 0 ,							/*B相7次谐波电压含有率最大值发生时间*/
		xb_vb_08_tm			int		DEFAULT 0 ,							/*B相8次谐波电压含有率最大值发生时间*/
		xb_vb_09_tm			int		DEFAULT 0 ,							/*B相9次谐波电压含有率最大值发生时间*/
		xb_vb_10_tm			int		DEFAULT 0 ,							/*B相10次谐波电压含有率最大值发生时间*/
		xb_vb_11_tm			int		DEFAULT 0 ,							/*B相11次谐波电压含有率最大值发生时间*/
		xb_vb_12_tm			int		DEFAULT 0 ,							/*B相12次谐波电压含有率最大值发生时间*/
		xb_vb_13_tm			int		DEFAULT 0 ,							/*B相13次谐波电压含有率最大值发生时间*/
		xb_vb_14_tm			int		DEFAULT 0 ,							/*B相14次谐波电压含有率最大值发生时间*/
		xb_vb_15_tm			int		DEFAULT 0 ,							/*B相15次谐波电压含有率最大值发生时间*/
		xb_vb_16_tm			int		DEFAULT 0 ,							/*B相16次谐波电压含有率最大值发生时间*/
		xb_vb_17_tm			int		DEFAULT 0 ,							/*B相17次谐波电压含有率最大值发生时间*/
		xb_vb_18_tm			int		DEFAULT 0 ,							/*B相18次谐波电压含有率最大值发生时间*/
		xb_vb_19_tm			int		DEFAULT 0 ,							/*B相19次谐波电压含有率最大值发生时间*/
		xb_vb_20_tm			int		DEFAULT 0 ,							/*B相20次谐波电压含有率最大值发生时间*/
		xb_vb_21_tm			int		DEFAULT 0 ,							/*B相21次谐波电压含有率最大值发生时间*/
		xb_vb_tm				int		DEFAULT 0 ,					 		/*B相总畸变电流最大值发生时间*/
		
		
		xb_vc_02				decimal(14, 3) 	DEFAULT 0.0	,	/*C相2次谐波电压含有率最大值*/
		xb_vc_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电压含有率最大值*/
		xb_vc_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电压含有率最大值*/
		xb_vc_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电压含有率最大值*/
		xb_vc_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电压含有率最大值*/
		xb_vc_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电压含有率最大值*/
		xb_vc_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电压含有率最大值*/
		xb_vc_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电压含有率最大值*/
		xb_vc_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电压含有率最大值*/
		xb_vc_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电压含有率最大值*/
		xb_vc_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电压含有率最大值*/
		xb_vc_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电压含有率最大值*/
		xb_vc_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电压含有率最大值*/
		xb_vc_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电压含有率最大值*/
		xb_vc_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电压含有率最大值*/
		xb_vc_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电压含有率最大值*/
		xb_vc_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电压含有率最大值*/
		xb_vc_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电压含有率最大值*/
		xb_vc_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电压含有率最大值*/
		xb_vc_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相21次谐波电压含有率最大值*/
		xb_vc						decimal(14, 3) 	DEFAULT 0.0 ,	/*C相总畸变电流最大值*/
		
		xb_vc_02_tm			int		DEFAULT 0 ,							/*C相2次谐波电压含有率最大值发生时间*/
		xb_vc_03_tm			int		DEFAULT 0 ,							/*C相3次谐波电压含有率最大值发生时间*/
		xb_vc_04_tm			int		DEFAULT 0 ,							/*C相4次谐波电压含有率最大值发生时间*/
		xb_vc_05_tm			int		DEFAULT 0 ,							/*C相5次谐波电压含有率最大值发生时间*/
		xb_vc_06_tm			int		DEFAULT 0 ,							/*C相6次谐波电压含有率最大值发生时间*/
		xb_vc_07_tm			int		DEFAULT 0 ,							/*C相7次谐波电压含有率最大值发生时间*/
		xb_vc_08_tm			int		DEFAULT 0 ,							/*C相8次谐波电压含有率最大值发生时间*/
		xb_vc_09_tm			int		DEFAULT 0 ,							/*C相9次谐波电压含有率最大值发生时间*/
		xb_vc_10_tm			int		DEFAULT 0 ,							/*C相10次谐波电压含有率最大值发生时间*/
		xb_vc_11_tm			int		DEFAULT 0 ,							/*C相11次谐波电压含有率最大值发生时间*/
		xb_vc_12_tm			int		DEFAULT 0 ,							/*C相12次谐波电压含有率最大值发生时间*/
		xb_vc_13_tm			int		DEFAULT 0 ,							/*C相13次谐波电压含有率最大值发生时间*/
		xb_vc_14_tm			int		DEFAULT 0 ,							/*C相14次谐波电压含有率最大值发生时间*/
		xb_vc_15_tm			int		DEFAULT 0 ,							/*C相15次谐波电压含有率最大值发生时间*/
		xb_vc_16_tm			int		DEFAULT 0 ,							/*C相16次谐波电压含有率最大值发生时间*/
		xb_vc_17_tm			int		DEFAULT 0 ,							/*C相17次谐波电压含有率最大值发生时间*/
		xb_vc_18_tm			int		DEFAULT 0 ,							/*C相18次谐波电压含有率最大值发生时间*/
		xb_vc_19_tm			int		DEFAULT 0 ,							/*C相19次谐波电压含有率最大值发生时间*/
		xb_vc_20_tm			int		DEFAULT 0 ,							/*C相20次谐波电压含有率最大值发生时间*/
		xb_vc_21_tm			int		DEFAULT 0 ,							/*C相21次谐波电压含有率最大值发生时间*/
		xb_vc_tm				int		DEFAULT 0 					 		/*C相总畸变电流最大值发生时间*/
)
grant select on  ZMonXbU2008 to public
create unique clustered index ZMonXbU2008index1
	on ZMonXbU2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变谐波越限数据表(ZMonXbLmt2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonXbLmt2008')
begin
create table ZMonXbLmt2008
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		
		xblmt_va			int		DEFAULT 0 ,			/*A相总畸变电压含有率越限累计时间*/
		xblmt_va_02		int		DEFAULT 0 ,			/*A相2次谐波电压含有率越限累计时间*/
		xblmt_va_03		int		DEFAULT 0 ,			/*A相3次谐波电压含有率越限累计时间*/
		xblmt_va_04		int		DEFAULT 0 ,			/*A相4次谐波电压含有率越限累计时间*/
		xblmt_va_05		int		DEFAULT 0 ,			/*A相5次谐波电压含有率越限累计时间*/
		xblmt_va_06		int		DEFAULT 0 ,			/*A相6次谐波电压含有率越限累计时间*/
		xblmt_va_07		int		DEFAULT 0 ,			/*A相7次谐波电压含有率越限累计时间*/
		xblmt_va_08		int		DEFAULT 0 ,			/*A相8次谐波电压含有率越限累计时间*/
		xblmt_va_09		int		DEFAULT 0 ,			/*A相9次谐波电压含有率越限累计时间*/
		xblmt_va_10		int		DEFAULT 0 ,			/*A相10次谐波电压含有率越限累计时间*/
		xblmt_va_11		int		DEFAULT 0 ,			/*A相11次谐波电压含有率越限累计时间*/
		xblmt_va_12		int		DEFAULT 0 ,			/*A相12次谐波电压含有率越限累计时间*/
		xblmt_va_13		int		DEFAULT 0 ,			/*A相13次谐波电压含有率越限累计时间*/
		xblmt_va_14		int		DEFAULT 0 ,			/*A相14次谐波电压含有率越限累计时间*/
		xblmt_va_15		int		DEFAULT 0 ,			/*A相15次谐波电压含有率越限累计时间*/
		xblmt_va_16		int		DEFAULT 0 ,			/*A相16次谐波电压含有率越限累计时间*/
		xblmt_va_17		int		DEFAULT 0 ,			/*A相17次谐波电压含有率越限累计时间*/
		xblmt_va_18		int		DEFAULT 0 ,			/*A相18次谐波电压含有率越限累计时间*/
		xblmt_va_19		int		DEFAULT 0 ,			/*A相19次谐波电压含有率越限累计时间*/
		xblmt_va_20		int		DEFAULT 0 ,			/*A相20次谐波电压含有率越限累计时间*/
		xblmt_va_21		int		DEFAULT 0 ,			/*A相21次谐波电压含有率越限累计时间*/
		xblmt_ia			int		DEFAULT 0 ,			/*A相总畸变电流越限累计时间*/
		xblmt_ia_02		int		DEFAULT 0 ,			/*A相2次谐波电流越限累计时间*/
		xblmt_ia_03		int		DEFAULT 0 ,			/*A相3次谐波电流越限累计时间*/
		xblmt_ia_04		int		DEFAULT 0 ,			/*A相4次谐波电流越限累计时间*/
		xblmt_ia_05		int		DEFAULT 0 ,			/*A相5次谐波电流越限累计时间*/
		xblmt_ia_06		int		DEFAULT 0 ,			/*A相6次谐波电流越限累计时间*/
		xblmt_ia_07		int		DEFAULT 0 ,			/*A相7次谐波电流越限累计时间*/
		xblmt_ia_08		int		DEFAULT 0 ,			/*A相8次谐波电流越限累计时间*/
		xblmt_ia_09		int		DEFAULT 0 ,			/*A相9次谐波电流越限累计时间*/
		xblmt_ia_10		int		DEFAULT 0 ,			/*A相10次谐波电流越限累计时间*/
		xblmt_ia_11		int		DEFAULT 0 ,			/*A相11次谐波电流越限累计时间*/
		xblmt_ia_12		int		DEFAULT 0 ,			/*A相12次谐波电流越限累计时间*/
		xblmt_ia_13		int		DEFAULT 0 ,			/*A相13次谐波电流越限累计时间*/
		xblmt_ia_14		int		DEFAULT 0 ,			/*A相14次谐波电流越限累计时间*/
		xblmt_ia_15		int		DEFAULT 0 ,			/*A相15次谐波电流越限累计时间*/
		xblmt_ia_16		int		DEFAULT 0 ,			/*A相16次谐波电流越限累计时间*/
		xblmt_ia_17		int		DEFAULT 0 ,			/*A相17次谐波电流越限累计时间*/
		xblmt_ia_18		int		DEFAULT 0 ,			/*A相18次谐波电流越限累计时间*/
		xblmt_ia_19		int		DEFAULT 0 ,			/*A相19次谐波电流越限累计时间*/
		xblmt_ia_20		int		DEFAULT 0 ,			/*A相20次谐波电流越限累计时间*/
		xblmt_ia_21		int		DEFAULT 0 ,			/*A相21次谐波电流越限累计时间*/
		xblmt_vb			int		DEFAULT 0 ,			/*B相总畸变电压含有率越限累计时间*/
		xblmt_vb_02		int		DEFAULT 0 ,			/*B相2次谐波电压含有率越限累计时间*/
		xblmt_vb_03		int		DEFAULT 0 ,			/*B相3次谐波电压含有率越限累计时间*/
		xblmt_vb_04		int		DEFAULT 0 ,			/*B相4次谐波电压含有率越限累计时间*/
		xblmt_vb_05		int		DEFAULT 0 ,			/*B相5次谐波电压含有率越限累计时间*/
		xblmt_vb_06		int		DEFAULT 0 ,			/*B相6次谐波电压含有率越限累计时间*/
		xblmt_vb_07		int		DEFAULT 0 ,			/*B相7次谐波电压含有率越限累计时间*/
		xblmt_vb_08		int		DEFAULT 0 ,			/*B相8次谐波电压含有率越限累计时间*/
		xblmt_vb_09		int		DEFAULT 0 ,			/*B相9次谐波电压含有率越限累计时间*/
		xblmt_vb_10		int		DEFAULT 0 ,			/*B相10次谐波电压含有率越限累计时间*/
		xblmt_vb_11		int		DEFAULT 0 ,			/*B相11次谐波电压含有率越限累计时间*/
		xblmt_vb_12		int		DEFAULT 0 ,			/*B相12次谐波电压含有率越限累计时间*/
		xblmt_vb_13		int		DEFAULT 0 ,			/*B相13次谐波电压含有率越限累计时间*/
		xblmt_vb_14		int		DEFAULT 0 ,			/*B相14次谐波电压含有率越限累计时间*/
		xblmt_vb_15		int		DEFAULT 0 ,			/*B相15次谐波电压含有率越限累计时间*/
		xblmt_vb_16		int		DEFAULT 0 ,			/*B相16次谐波电压含有率越限累计时间*/
		xblmt_vb_17		int		DEFAULT 0 ,			/*B相17次谐波电压含有率越限累计时间*/
		xblmt_vb_18		int		DEFAULT 0 ,			/*B相18次谐波电压含有率越限累计时间*/
		xblmt_vb_19		int		DEFAULT 0 ,			/*B相19次谐波电压含有率越限累计时间*/
		xblmt_vb_20		int		DEFAULT 0 ,			/*B相20次谐波电压含有率越限累计时间*/
		xblmt_vb_21		int		DEFAULT 0 ,			/*B相21次谐波电压含有率越限累计时间*/
		xblmt_ib			int		DEFAULT 0 ,			/*B相总畸变电流越限累计时间*/
		xblmt_ib_02		int		DEFAULT 0 ,			/*B相2次谐波电流越限累计时间*/
		xblmt_ib_03		int		DEFAULT 0 ,			/*B相3次谐波电流越限累计时间*/
		xblmt_ib_04		int		DEFAULT 0 ,			/*B相4次谐波电流越限累计时间*/
		xblmt_ib_05		int		DEFAULT 0 ,			/*B相5次谐波电流越限累计时间*/
		xblmt_ib_06		int		DEFAULT 0 ,			/*B相6次谐波电流越限累计时间*/
		xblmt_ib_07		int		DEFAULT 0 ,			/*B相7次谐波电流越限累计时间*/
		xblmt_ib_08		int		DEFAULT 0 ,			/*B相8次谐波电流越限累计时间*/
		xblmt_ib_09		int		DEFAULT 0 ,			/*B相9次谐波电流越限累计时间*/
		xblmt_ib_10		int		DEFAULT 0 ,			/*B相10次谐波电流越限累计时间*/
		xblmt_ib_11		int		DEFAULT 0 ,			/*B相11次谐波电流越限累计时间*/
		xblmt_ib_12		int		DEFAULT 0 ,			/*B相12次谐波电流越限累计时间*/
		xblmt_ib_13		int		DEFAULT 0 ,			/*B相13次谐波电流越限累计时间*/
		xblmt_ib_14		int		DEFAULT 0 ,			/*B相14次谐波电流越限累计时间*/
		xblmt_ib_15		int		DEFAULT 0 ,			/*B相15次谐波电流越限累计时间*/
		xblmt_ib_16		int		DEFAULT 0 ,			/*B相16次谐波电流越限累计时间*/
		xblmt_ib_17		int		DEFAULT 0 ,			/*B相17次谐波电流越限累计时间*/
		xblmt_ib_18		int		DEFAULT 0 ,			/*B相18次谐波电流越限累计时间*/
		xblmt_ib_19		int		DEFAULT 0 ,			/*B相19次谐波电流越限累计时间*/
		xblmt_ib_20		int		DEFAULT 0 ,			/*B相20次谐波电流越限累计时间*/
		xblmt_ib_21		int		DEFAULT 0 ,			/*B相21次谐波电流越限累计时间*/
		xblmt_vc			int		DEFAULT 0 ,			/*C相总畸变电压含有率越限累计时间*/
		xblmt_vc_02		int		DEFAULT 0 ,			/*C相2次谐波电压含有率越限累计时间*/
		xblmt_vc_03		int		DEFAULT 0 ,			/*C相3次谐波电压含有率越限累计时间*/
		xblmt_vc_04		int		DEFAULT 0 ,			/*C相4次谐波电压含有率越限累计时间*/
		xblmt_vc_05		int		DEFAULT 0 ,			/*C相5次谐波电压含有率越限累计时间*/
		xblmt_vc_06		int		DEFAULT 0 ,			/*C相6次谐波电压含有率越限累计时间*/
		xblmt_vc_07		int		DEFAULT 0 ,			/*C相7次谐波电压含有率越限累计时间*/
		xblmt_vc_08		int		DEFAULT 0 ,			/*C相8次谐波电压含有率越限累计时间*/
		xblmt_vc_09		int		DEFAULT 0 ,			/*C相9次谐波电压含有率越限累计时间*/
		xblmt_vc_10		int		DEFAULT 0 ,			/*C相10次谐波电压含有率越限累计时间*/
		xblmt_vc_11		int		DEFAULT 0 ,			/*C相11次谐波电压含有率越限累计时间*/
		xblmt_vc_12		int		DEFAULT 0 ,			/*C相12次谐波电压含有率越限累计时间*/
		xblmt_vc_13		int		DEFAULT 0 ,			/*C相13次谐波电压含有率越限累计时间*/
		xblmt_vc_14		int		DEFAULT 0 ,			/*C相14次谐波电压含有率越限累计时间*/
		xblmt_vc_15		int		DEFAULT 0 ,			/*C相15次谐波电压含有率越限累计时间*/
		xblmt_vc_16		int		DEFAULT 0 ,			/*C相16次谐波电压含有率越限累计时间*/
		xblmt_vc_17		int		DEFAULT 0 ,			/*C相17次谐波电压含有率越限累计时间*/
		xblmt_vc_18		int		DEFAULT 0 ,			/*C相18次谐波电压含有率越限累计时间*/
		xblmt_vc_19		int		DEFAULT 0 ,			/*C相19次谐波电压含有率越限累计时间*/
		xblmt_vc_20		int		DEFAULT 0 ,			/*C相20次谐波电压含有率越限累计时间*/
		xblmt_vc_21		int		DEFAULT 0 ,			/*C相21次谐波电压含有率越限累计时间*/
		xblmt_ic			int		DEFAULT 0 ,			/*C相总畸变电流越限累计时间*/
		xblmt_ic_02		int		DEFAULT 0 ,			/*C相2次谐波电流越限累计时间*/
		xblmt_ic_03		int		DEFAULT 0 ,			/*C相3次谐波电流越限累计时间*/
		xblmt_ic_04		int		DEFAULT 0 ,			/*C相4次谐波电流越限累计时间*/
		xblmt_ic_05		int		DEFAULT 0 ,			/*C相5次谐波电流越限累计时间*/
		xblmt_ic_06		int		DEFAULT 0 ,			/*C相6次谐波电流越限累计时间*/
		xblmt_ic_07		int		DEFAULT 0 ,			/*C相7次谐波电流越限累计时间*/
		xblmt_ic_08		int		DEFAULT 0 ,			/*C相8次谐波电流越限累计时间*/
		xblmt_ic_09		int		DEFAULT 0 ,			/*C相9次谐波电流越限累计时间*/
		xblmt_ic_10		int		DEFAULT 0 ,			/*C相10次谐波电流越限累计时间*/
		xblmt_ic_11		int		DEFAULT 0 ,			/*C相11次谐波电流越限累计时间*/
		xblmt_ic_12		int		DEFAULT 0 ,			/*C相12次谐波电流越限累计时间*/
		xblmt_ic_13		int		DEFAULT 0 ,			/*C相13次谐波电流越限累计时间*/
		xblmt_ic_14		int		DEFAULT 0 ,			/*C相14次谐波电流越限累计时间*/
		xblmt_ic_15		int		DEFAULT 0 ,			/*C相15次谐波电流越限累计时间*/
		xblmt_ic_16		int		DEFAULT 0 ,			/*C相16次谐波电流越限累计时间*/
		xblmt_ic_17		int		DEFAULT 0 ,			/*C相17次谐波电流越限累计时间*/
		xblmt_ic_18		int		DEFAULT 0 ,			/*C相18次谐波电流越限累计时间*/
		xblmt_ic_19		int		DEFAULT 0 ,			/*C相19次谐波电流越限累计时间*/
		xblmt_ic_20		int		DEFAULT 0 ,			/*C相20次谐波电流越限累计时间*/
		xblmt_ic_21		int		DEFAULT 0 			/*C相21次谐波电流越限累计时间*/
)
grant select on  ZMonXbLmt2008 to public
create unique clustered index ZMonXbLmt2008index1
	on ZMonXbLmt2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变总加组曲线数据表(ZGMinQx200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZGMinQx200808')
begin
create table ZGMinQx200808
(
	rtu_id		int   					not null,
	group_id	smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	p					decimal(14, 3) 	DEFAULT 0.0 ,	/*	有功功率*/
	q					decimal(14, 3) 	DEFAULT 0.0 ,	/*	无功功率*/
	ygdl			decimal(14, 3) 	DEFAULT 0.0 ,	/*	有功电量*/
	wgdl			decimal(14, 3) 	DEFAULT 0.0	  /*	无功电量*/
)
grant select on  ZGMinQx200808 to public
create unique clustered index ZGMinSsl200808index1
	on ZGMinQx200808(rtu_id,group_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 专变配变总加组日功率极值数据表(ZGDayPMax200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZGDayPMax200808')
begin
create table ZGDayPMax200808
(
		rtu_id				int			   			not null,
		group_id			smallint   			not null,
		date					int							not null,
		time					int							not null,
		
		ext_p_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最大有功功率*/	
		ext_p_max_tm	int		DEFAULT 0 ,							/*总最大有功功率发生时间*/
		ext_p_a				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相最大有功功率*/
		ext_p_a_tm		int		DEFAULT 0 ,							/*A相最大有功功率发生时间*/
		ext_p_b				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相最大有功功率*/
		ext_p_b_tm		int		DEFAULT 0 ,							/*B相最大有功功率发生时间*/
		ext_p_c				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相最大有功功率*/
		ext_p_c_tm		int		DEFAULT 0 ,							/*C相最大有功功率发生时间*/
		ext_p_min			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最有功小功率*/
		ext_p_min_tm	int		DEFAULT 0 ,							/*总最小有功功率发生时间*/
		ext_p_0_tm		int		DEFAULT 0 ,							/*总有功功率为零时间*/
		ext_p_a0_tm		int		DEFAULT 0 ,							/*A相有功功率为零时间*/
		ext_p_b0_tm		int		DEFAULT 0 ,							/*B相有功功率为零时间*/
		ext_p_c0_tm		int		DEFAULT 0								/*C相有功功率为零时间*/
)
grant select on  ZGDayPMax200808 to public
create unique clustered index ZGDayPMax200808index1
	on ZGDayPMax200808(rtu_id,group_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变总加组日电量数据表(ZGDayDl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZGDayDl200808')
begin
create table ZGDayDl200808
(
	rtu_id		int   					not null,
	group_id	smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/	
	
	dl_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  ZGDayDl200808 to public
create unique clustered index ZGDayDl200808index1
	on ZGDayDl200808(rtu_id,group_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变总加组月功率极值数据表(ZGMonMaxP2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZGMonMaxP2008')
begin
create table ZGMonMaxP2008
(
		rtu_id				int   					not null,
		group_id			smallint   			not null,
		date					int							not null,
		time					int							not null,
		ext_p_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最大有功功率*/	
		ext_p_max_tm	int		DEFAULT 0 ,							/*总最大有功功率发生时间*/
		ext_p_a				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相最大有功功率*/
		ext_p_a_tm		int		DEFAULT 0 ,							/*A相最大有功功率发生时间*/
		ext_p_b				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相最大有功功率*/
		ext_p_b_tm		int		DEFAULT 0 ,							/*B相最大有功功率发生时间*/
		ext_p_c				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相最大有功功率*/
		ext_p_c_tm		int		DEFAULT 0 ,							/*C相最大有功功率发生时间*/
		ext_p_min			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最有功小功率*/
		ext_p_min_tm	int		DEFAULT 0 ,							/*总最小有功功率发生时间*/
		ext_p_0_tm		int		DEFAULT 0 ,							/*总有功功率为零时间*/
		ext_p_a0_tm		int		DEFAULT 0 ,							/*A相有功功率为零时间*/
		ext_p_b0_tm		int		DEFAULT 0 ,							/*B相有功功率为零时间*/
		ext_p_c0_tm		int		DEFAULT 0								/*C相有功功率为零时间*/
)
grant select on  ZGMonMaxP2008 to public
create unique clustered index ZGMonMaxP2008index1
	on ZGMonMaxP2008(rtu_id,group_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变总加组月电量数据表(ZGMonDl2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZGMonDl2008')
begin
create table ZGMonDl2008
(
	rtu_id		int   					not null,
	group_id	smallint   			not null,
	date			int							not null,
	time			int							not null,
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/	
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  ZGMonDl2008 to public
create unique clustered index ZGMonDl2008index1
	on ZGMonDl2008(rtu_id,group_id,date,time)
end
go
-- 标记结束

----------------------专变配变应用结束--------------------------------------------


----------------------农排应用开始--------------------------------------------
--   '-------Create table 农排实时表底数据表(NRealBD200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NRealBD200808')
begin
create table NRealBD200808
(
	rtu_id			int			   		not null,
	mp_id				smallint   		not null,
	date				int						not null,
	time				int						not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖表底*/	
	
	bd_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 3) 	DEFAULT 0.0 	/*四相限无功表底*/
	
)
grant select on  NRealBD200808 to public
create unique clustered index NRealBD200808index1
	on NRealBD200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排实时瞬时量数据表(NRealSsl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NRealSsl200808')
begin
create table NRealSsl200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	va			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相*/
	vb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相*/
	vc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相*/
	ia			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相*/
	ib			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相*/
	ic			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相*/
	i0			decimal(14, 3) 	DEFAULT 0.0 ,	/*	零序*/
	p				decimal(14, 3) 	DEFAULT 0.0 ,	/*	总有功*/
	pa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相有功*/
	pb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相有功*/
	pc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相有功*/
	q				decimal(14, 3) 	DEFAULT 0.0 ,	/*	总无功*/
	qa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相无功*/
	qb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相无功*/
	qc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相无功*/
	s				decimal(14, 3) 	DEFAULT 0.0 ,	/*	视在*/
	cs			decimal(14, 3) 	DEFAULT 0.0 ,	/*总功率因数*/
	csa			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相功率因数*/
	csb			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相功率因数*/
	csc			decimal(14, 3) 	DEFAULT 0.0 	/*C相功率因数*/
)
grant select on  NRealSsl200808 to public
create unique clustered index NRealSsl200808index1
	on NRealSsl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	


--   '-------Create table 农排分钟表底电量数据表(NMinBDDL200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMinBDDL200808')
begin
create table NMinBDDL200808
(
	rtu_id			int			   		not null,
	mp_id				smallint   		not null,
	date				int						not null,
	time				int						not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/		
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	

	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_source	tinyint					DEFAULT 0
)
grant select on  NMinBDDL200808 to public
create unique clustered index NMinBDDL200808index1
	on NMinBDDL200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 农排分钟瞬时量数据表(NMinSsl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMinSsl200808')
begin
create table NMinSsl200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	va			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相*/
	vb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相*/
	vc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相*/
	ia			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相*/
	ib			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相*/
	ic			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相*/
	i0			decimal(14, 3) 	DEFAULT 0.0 ,	/*	零序*/
	p				decimal(14, 3) 	DEFAULT 0.0 ,	/*	总有功*/
	pa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相有功*/
	pb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相有功*/
	pc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相有功*/
	q				decimal(14, 3) 	DEFAULT 0.0 ,	/*	总无功*/
	qa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相无功*/
	qb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相无功*/
	qc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相无功*/
	s				decimal(14, 3) 	DEFAULT 0.0 ,	/*	视在*/
	cs			decimal(14, 3) 	DEFAULT 0.0 ,	/*总功率因数*/
	csa			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相功率因数*/
	csb			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相功率因数*/
	csc			decimal(14, 3) 	DEFAULT 0.0 	/*C相功率因数*/
)
grant select on  NMinSsl200808 to public
create unique clustered index NMinSsl200808index1
	on NMinSsl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	


--   '-------Create table 农排日表底电量数据表(NDayBdDl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NDayBdDl200808')
begin
create table NDayBdDl200808
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	bd_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*四相限无功表底*/
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
		
	dl_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 3) 	DEFAULT 0.0, 	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  NDayBdDl200808 to public
create unique clustered index NDayBdDl200808index1
	on NDayBdDl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 农排日需量数据表(NDayMaxXl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NDayMaxXl200808')
begin
create table NDayMaxXl200808
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,

	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷需量*/	
	xl_zy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/		
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷需量*/	
	xl_zw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总需量*/	
	xl_fw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷需量*/
	xl_fw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	
	
	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 		/*反向无功尖需量时间*/
)
grant select on  NDayMaxXl200808 to public
create unique clustered index NDayMaxXl200808index1
	on NDayMaxXl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排日电压合格率数据表(NDayVHg200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NDayVHg200808')
begin
create table NDayVHg200808
(
		rtu_id					int 		 not null,
		mp_id						smallint not null,
		date						int			 not null,
		time						int			 not null,

		hg_va_hh_tm			int		DEFAULT 0 ,								/*A相电压越上上限累计时间*/
		hg_va_ll_tm			int		DEFAULT 0 ,								/*A相电压越下下限累计时间*/
		hg_va_h_tm			int		DEFAULT 0 ,								/*A相电压越上限累计时间*/
		hg_va_l_tm			int		DEFAULT 0 ,								/*A相电压越下限累计时间*/
		hg_va_tm				int		DEFAULT 0 ,								/*A相电压合格率累计时间*/
		hg_vb_hh_tm			int		DEFAULT 0 ,								/*B相电压越上上限累计时间*/
		hg_vb_ll_tm			int		DEFAULT 0 ,								/*B相电压越下下限累计时间*/
		hg_vb_h_tm			int		DEFAULT 0 ,								/*B相电压越上限累计时间*/
		hg_vb_l_tm			int		DEFAULT 0 ,								/*B相电压越下限累计时间*/
		hg_vb_tm				int		DEFAULT 0 ,								/*B相电压合格率累计时间*/
		hg_vc_hh_tm			int		DEFAULT 0 ,								/*C相电压越上上限累计时间*/
		hg_vc_ll_tm			int		DEFAULT 0 ,								/*C相电压越下下限累计时间*/
		hg_vc_h_tm			int		DEFAULT 0 ,								/*C相电压越上限累计时间*/
		hg_vc_l_tm			int		DEFAULT 0 ,								/*C相电压越下限累计时间*/
		hg_vc_tm				int		DEFAULT 0 ,								/*C相电压合格率累计时间*/
		hg_va_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最大值*/
		hg_va_max_tm		int		DEFAULT 0 ,								/*A相电压最大值发生时间*/
		hg_va_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最小值*/
		hg_va_min_tm		int		DEFAULT 0 ,								/*A相电压最小值发生时间*/
		hg_vb_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最大值*/
		hg_vb_max_tm		int		DEFAULT 0 ,								/*B相电压最大值发生时间*/
		hg_vb_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最小值*/
		hg_vb_min_tm		int		DEFAULT 0 ,								/*B相电压最小值发生时间*/
		hg_vc_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最大值*/
		hg_vc_max_tm		int		DEFAULT 0 ,								/*C相电压最大值发生时间*/
		hg_vc_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最小值*/
		hg_vc_min_tm		int		DEFAULT 0 ,								/*C相电压最小值发生时间*/
		hg_va_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相平均电压*/
		hg_vb_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相平均电压*/
		hg_vc_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相平均电压*/
		
		hg_va_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越上限率*/
		hg_va_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越下限率*/
		hg_va_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压合格率*/
		hg_vb_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越上限率*/
		hg_vb_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越下限率*/
		hg_vb_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压合格率*/
		hg_vc_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越上限率*/
		hg_vc_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越下限率*/
		hg_vc_hgl 			decimal(14, 3) 	DEFAULT 0.0 		/*C相电压合格率*/	
)
grant select on  NDayVHg200808 to public
create unique clustered index NDayVHg200808index1
	on NDayVHg200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排日电流越限数据表(NDayILmt200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NDayILmt200808')
begin
create table NDayILmt200808
(
		rtu_id					int  			not null,
		mp_id						smallint 	not null,
		date						int				not null,
		time						int				not null,

		lmt_ia_hh_tm		int				DEFAULT 0 ,			/*A相电流越上上限累计时间*/
		lmt_ia_h_tm			int				DEFAULT 0 ,			/*A相电流越上限累计时间*/
		lmt_ib_hh_tm		int				DEFAULT 0 ,			/*B相电流越上上限累计时间*/
		lmt_ib_h_tm			int				DEFAULT 0 ,			/*B相电流越上限累计时间*/
		lmt_ic_hh_tm		int				DEFAULT 0 ,			/*C相电流越上上限累计时间*/
		lmt_ic_h_tm			int				DEFAULT 0 ,			/*C相电流越上限累计时间*/
		lmt_i0_h_tm			int				DEFAULT 0 ,			/*零序电流越上限累计时间*/
		lmt_ia_max			decimal(14, 3)DEFAULT 0.0,/*A相电流最大值*/
		lmt_ia_max_tm		int				DEFAULT 0 ,			/*A相电流最大值发生时间*/
		lmt_ib_max			decimal(14, 3)DEFAULT 0.0,/*B相电流最大值*/
		lmt_ib_max_tm		int				DEFAULT 0 ,			/*B相电流最大值发生时间*/
		lmt_ic_max			decimal(14, 3)DEFAULT 0.0,/*C相电流最大值*/
		lmt_ic_max_tm		int				DEFAULT 0 ,			/*C相电流最大值发生时间*/
		lmt_i0_max			decimal(14, 3)DEFAULT 0.0,/*零序电流最大值*/
		lmt_i0_max_tm		int				DEFAULT 0 			/*零序电流最大值发生时间*/
)
grant select on  NDayILmt200808 to public
create unique clustered index NDayILmt200808index1
	on NDayILmt200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 农排日断相统计数据表(NDayDX200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NDayDX200808')
begin
create table NDayDX200808
(
		rtu_id		int 			not null,
		mp_id			smallint	not null,
		date			int				not null,
		time			int				not null,
	
		dx_tms		int				DEFAULT 0 ,		/*总断相次数*/
		dx_a_tms	int				DEFAULT 0 ,		/*A相断相次数*/
		dx_b_tms	int				DEFAULT 0 ,		/*B相断相次数*/
		dx_c_tms	int				DEFAULT 0 ,		/*C相断相次数*/
		dx_tm			int				DEFAULT 0 ,		/*断相累计时间*/
		dx_a_tm		int				DEFAULT 0 ,		/*A断相累计时间*/
		dx_b_tm		int				DEFAULT 0 ,		/*B断相累计时间*/
		dx_c_tm		int				DEFAULT 0 ,		/*C断相累计时间*/
		dx_1_s_tm	int				DEFAULT 0 ,		/*最近一次断相起始时刻*/
		dx_a_s_tm	int				DEFAULT 0 ,		/*A相最近断相起始时刻*/
		dx_b_s_tm	int				DEFAULT 0 ,		/*B相最近断相起始时刻*/
		dx_c_s_tm	int				DEFAULT 0 ,		/*C相最近断相起始时刻*/
		dx_1_e_tm	int				DEFAULT 0 ,		/*最近一次断相结束时刻*/
		dx_a_e_tm	int				DEFAULT 0 ,		/*A相最近断相结束时刻*/
		dx_b_e_tm	int				DEFAULT 0 ,		/*B相最近断相结束时刻*/
		dx_c_e_tm	int				DEFAULT 0 ,		/*C相最近断相结束时刻*/

)
grant select on  NDayDX200808 to public
create unique clustered index NDayDX200808index1
	on NDayDX200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排日功率极值数据表(NDayPMax200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NDayPMax200808')
begin
create table NDayPMax200808
(
		rtu_id				int	   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		ext_p_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最大有功功率*/	
		ext_p_max_tm	int		DEFAULT 0 ,							/*总最大有功功率发生时间*/
		ext_p_a				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相最大有功功率*/
		ext_p_a_tm		int		DEFAULT 0 ,							/*A相最大有功功率发生时间*/
		ext_p_b				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相最大有功功率*/
		ext_p_b_tm		int		DEFAULT 0 ,							/*B相最大有功功率发生时间*/
		ext_p_c				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相最大有功功率*/
		ext_p_c_tm		int		DEFAULT 0 ,							/*C相最大有功功率发生时间*/
		ext_p_min			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最有功小功率*/
		ext_p_min_tm	int		DEFAULT 0 ,							/*总最小有功功率发生时间*/
		ext_p_0_tm		int		DEFAULT 0 ,							/*总有功功率为零时间*/
		ext_p_a0_tm		int		DEFAULT 0 ,							/*A相有功功率为零时间*/
		ext_p_b0_tm		int		DEFAULT 0 ,							/*B相有功功率为零时间*/
		ext_p_c0_tm		int		DEFAULT 0								/*C相有功功率为零时间*/

)
grant select on  NDayPMax200808 to public
create unique clustered index NDayPMax200808index1
	on NDayPMax200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 农排日电流不平衡数据表(NDayIBal200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NDayIBal200808')
begin
create table NDayIBal200808
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		bph_i_tm			int							DEFAULT 0 ,			/*电流不平衡度越限累计时间*/
		bph_v_tm			int							DEFAULT 0 ,			/*电压不平衡度越限累计时间*/ 
		bph_i_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*电流不平衡最大值*/
		bph_i_max_tm	int							DEFAULT 0 ,			/*电流不平衡最大值发生时间*/
		bph_v_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*电压不平衡最大值*/
		bph_v_max_tm	int							DEFAULT 0			  /*电压不平衡最大值发生时间*/
)
grant select on  NDayIBal200808 to public
create unique clustered index NDayIBal200808index1
	on NDayIBal200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排日负载数据表(NDayLoad200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NDayLoad200808')
begin
create table NDayLoad200808
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		fz_max				decimal(14, 3) 	DEFAULT 0.0,	/*负载率最大值*/	
		fz_max_tm			int							DEFAULT 0 ,		/*负载率最大值发生时间*/
		fz_min				decimal(14, 3) 	DEFAULT 0.0,	/*负载率最小值*/
		fz_min_tm			int							DEFAULT 0 		/*负载率最小值发生时间*/
)
grant select on  NDayLoad200808 to public
create unique clustered index NDayLoad200808index1
	on NDayLoad200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 农排日功率因数区段累计时间(NDayCos200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NDayCos200808')
begin
create table NDayCos200808
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		cs_tm_1				int							DEFAULT 0 ,		/*区段1累计时间（功率因数＜定值1）*/
		cs_tm_2				int							DEFAULT 0 ,		/*区段2累计时间（定值1≤功率因数＜定值2）*/
		cs_tm_3				int							DEFAULT 0 		/*区段3累计时间（功率因数≥定值2）*/
)
grant select on  NDayCos200808 to public
create unique clustered index NDayCos200808index1
	on NDayCos200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束



--   '-------Create table 农排抄表日表底电量数据表(NMRBdDl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMRBdDl200808')
begin
create table NMRBdDl200808
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	bd_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*四相限无功表底*/
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
		
	dl_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 3) 	DEFAULT 0.0, 	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  NMRBdDl200808 to public
create unique clustered index NMRBdDl200808index1
	on NMRBdDl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排月表底电量数据表(NMonBdDl2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonBdDl2008')
begin
create table NMonBdDl2008
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	bd_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总表底*/	
	bd_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰表底*/	
	bd_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平表底*/	
	bd_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷表底*/	
	bd_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖表底*/	
	
	bd_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总表底*/	
	bd_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰表底*/	
	bd_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平表底*/	
	bd_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷表底*/	
	bd_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖表底*/	
		
	bd_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总表底*/	
	bd_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰表底*/	
	bd_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平表底*/	
	bd_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷表底*/	
	bd_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖表底*/	
	
	bd_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总表底*/	
	bd_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰表底*/	
	bd_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平表底*/	
	bd_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷表底*/	
	bd_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖表底*/
	
	bd_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功表底*/	
	bd_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功表底*/	
	bd_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功表底*/	
	bd_4xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*四相限无功表底*/
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/
		
	dl_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 3) 	DEFAULT 0.0, 	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  NMonBdDl2008 to public
create unique clustered index NMonBdDl2008index1
	on NMonBdDl2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 农排月需量数据表(NMonMaxXl2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonMaxXl2008')
begin
create table NMonMaxXl2008
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,

	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷需量*/	
	xl_zy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/	
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷需量*/	
	xl_zw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总需量*/	
	xl_fw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷需量*/	
	xl_fw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	

	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 		/*反向无功尖需量时间*/
)
grant select on  NMonMaxXl2008 to public
create unique clustered index NMonMaxXl2008index1
	on NMonMaxXl2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排月电压合格率数据表(NMonVHg2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonVHg2008')
begin
create table NMonVHg2008
(
		rtu_id					int   		not null,
		mp_id						smallint	not null,
		date						int				not null,
		time						int				not null,
	
		hg_va_hh_tm			int		DEFAULT 0 ,								/*A相电压越上上限累计时间*/
		hg_va_ll_tm			int		DEFAULT 0 ,								/*A相电压越下下限累计时间*/
		hg_va_h_tm			int		DEFAULT 0 ,								/*A相电压越上限累计时间*/
		hg_va_l_tm			int		DEFAULT 0 ,								/*A相电压越下限累计时间*/
		hg_va_tm				int		DEFAULT 0 ,								/*A相电压合格率累计时间*/
		hg_vb_hh_tm			int		DEFAULT 0 ,								/*B相电压越上上限累计时间*/
		hg_vb_ll_tm			int		DEFAULT 0 ,								/*B相电压越下下限累计时间*/
		hg_vb_h_tm			int		DEFAULT 0 ,								/*B相电压越上限累计时间*/
		hg_vb_l_tm			int		DEFAULT 0 ,								/*B相电压越下限累计时间*/
		hg_vb_tm				int		DEFAULT 0 ,								/*B相电压合格率累计时间*/
		hg_vc_hh_tm			int		DEFAULT 0 ,								/*C相电压越上上限累计时间*/
		hg_vc_ll_tm			int		DEFAULT 0 ,								/*C相电压越下下限累计时间*/
		hg_vc_h_tm			int		DEFAULT 0 ,								/*C相电压越上限累计时间*/
		hg_vc_l_tm			int		DEFAULT 0 ,								/*C相电压越下限累计时间*/
		hg_vc_tm				int		DEFAULT 0 ,								/*C相电压合格率累计时间*/
		hg_va_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最大值*/
		hg_va_max_tm		int		DEFAULT 0 ,								/*A相电压最大值发生时间*/
		hg_va_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压最小值*/
		hg_va_min_tm		int		DEFAULT 0 ,								/*A相电压最小值发生时间*/
		hg_vb_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最大值*/
		hg_vb_max_tm		int		DEFAULT 0 ,								/*B相电压最大值发生时间*/
		hg_vb_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压最小值*/
		hg_vb_min_tm		int		DEFAULT 0 ,								/*B相电压最小值发生时间*/
		hg_vc_max				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最大值*/
		hg_vc_max_tm		int		DEFAULT 0 ,								/*C相电压最大值发生时间*/
		hg_vc_min				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压最小值*/
		hg_vc_min_tm		int		DEFAULT 0 ,								/*C相电压最小值发生时间*/
		hg_va_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*A相平均电压*/
		hg_vb_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*B相平均电压*/
		hg_vc_pj				decimal(14, 3) 	DEFAULT 0.0 ,		/*C相平均电压*/
		
		hg_va_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越上限率*/
		hg_va_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压越下限率*/
		hg_va_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压合格率*/
		hg_vb_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越上限率*/
		hg_vb_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压越下限率*/
		hg_vb_hgl 			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压合格率*/
		hg_vc_ysxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越上限率*/
		hg_vc_yxxl			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压越下限率*/
		hg_vc_hgl 			decimal(14, 3) 	DEFAULT 0.0 		/*C相电压合格率*/	
)
grant select on  NMonVHg2008 to public
create unique clustered index NMonVHg2008index1
	on NMonVHg2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 农排月电流越限数据表(NMonILmt2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonILmt2008')
begin
create table NMonILmt2008
(
		rtu_id					int  			not null,
		mp_id						smallint 	not null,
		date						int				not null,
		time						int				not null,

		lmt_ia_hh_tm		int				DEFAULT 0 ,			/*A相电流越上上限累计时间*/
		lmt_ia_h_tm			int				DEFAULT 0 ,			/*A相电流越上限累计时间*/
		lmt_ib_hh_tm		int				DEFAULT 0 ,			/*B相电流越上上限累计时间*/
		lmt_ib_h_tm			int				DEFAULT 0 ,			/*B相电流越上限累计时间*/
		lmt_ic_hh_tm		int				DEFAULT 0 ,			/*C相电流越上上限累计时间*/
		lmt_ic_h_tm			int				DEFAULT 0 ,			/*C相电流越上限累计时间*/
		lmt_i0_h_tm			int				DEFAULT 0 ,			/*零序电流越上限累计时间*/
		lmt_ia_max			decimal(14, 3)DEFAULT 0.0,/*A相电流最大值*/
		lmt_ia_max_tm		int				DEFAULT 0 ,			/*A相电流最大值发生时间*/
		lmt_ib_max			decimal(14, 3)DEFAULT 0.0,/*B相电流最大值*/
		lmt_ib_max_tm		int				DEFAULT 0 ,			/*B相电流最大值发生时间*/
		lmt_ic_max			decimal(14, 3)DEFAULT 0.0,/*C相电流最大值*/
		lmt_ic_max_tm		int				DEFAULT 0 ,			/*C相电流最大值发生时间*/
		lmt_i0_max			decimal(14, 3)DEFAULT 0.0,/*零序电流最大值*/
		lmt_i0_max_tm		int				DEFAULT 0 			/*零序电流最大值发生时间*/
)
grant select on  NMonILmt2008 to public
create unique clustered index NMonILmt2008index1
	on NMonILmt2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排月断相统计数据表(NMonDx2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonDx2008')
begin
create table NMonDx2008
(
		rtu_id		int 			not null,
		mp_id			smallint	not null,
		date			int				not null,
		time			int				not null,
	
		dx_tms		int				DEFAULT 0 ,		/*总断相次数*/
		dx_a_tms	int				DEFAULT 0 ,		/*A相断相次数*/
		dx_b_tms	int				DEFAULT 0 ,		/*B相断相次数*/
		dx_c_tms	int				DEFAULT 0 ,		/*C相断相次数*/
		dx_tm			int				DEFAULT 0 ,		/*断相累计时间*/
		dx_a_tm		int				DEFAULT 0 ,		/*A断相累计时间*/
		dx_b_tm		int				DEFAULT 0 ,		/*B断相累计时间*/
		dx_c_tm		int				DEFAULT 0 ,		/*C断相累计时间*/
		dx_1_s_tm	int				DEFAULT 0 ,		/*最近一次断相起始时刻*/
		dx_a_s_tm	int				DEFAULT 0 ,		/*A相最近断相起始时刻*/
		dx_b_s_tm	int				DEFAULT 0 ,		/*B相最近断相起始时刻*/
		dx_c_s_tm	int				DEFAULT 0 ,		/*C相最近断相起始时刻*/
		dx_1_e_tm	int				DEFAULT 0 ,		/*最近一次断相结束时刻*/
		dx_a_e_tm	int				DEFAULT 0 ,		/*A相最近断相结束时刻*/
		dx_b_e_tm	int				DEFAULT 0 ,		/*B相最近断相结束时刻*/
		dx_c_e_tm	int				DEFAULT 0 		/*C相最近断相结束时刻*/
)
grant select on  NMonDx2008 to public
create unique clustered index NMonDx2008index1
	on NMonDx2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 农排月功率极值数据表(NMonPMax2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonPMax2008')
begin
create table NMonPMax2008
(
		rtu_id				int	   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		ext_p_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最大有功功率*/	
		ext_p_max_tm	int		DEFAULT 0 ,							/*总最大有功功率发生时间*/
		ext_p_a				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相最大有功功率*/
		ext_p_a_tm		int		DEFAULT 0 ,							/*A相最大有功功率发生时间*/
		ext_p_b				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相最大有功功率*/
		ext_p_b_tm		int		DEFAULT 0 ,							/*B相最大有功功率发生时间*/
		ext_p_c				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相最大有功功率*/
		ext_p_c_tm		int		DEFAULT 0 ,							/*C相最大有功功率发生时间*/
		ext_p_min			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最有功小功率*/
		ext_p_min_tm	int		DEFAULT 0 ,							/*总最小有功功率发生时间*/
		ext_p_0_tm		int		DEFAULT 0 ,							/*总有功功率为零时间*/
		ext_p_a0_tm		int		DEFAULT 0 ,							/*A相有功功率为零时间*/
		ext_p_b0_tm		int		DEFAULT 0 ,							/*B相有功功率为零时间*/
		ext_p_c0_tm		int		DEFAULT 0								/*C相有功功率为零时间*/
)
grant select on  NMonPMax2008 to public
create unique clustered index NMonPMax2008index1
	on NMonPMax2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排月不平衡数据表(NMonIBal2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonIBal2008')
begin
create table NMonIBal2008
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		bph_i_tm			int							DEFAULT 0 ,			/*电流不平衡度越限累计时间*/
		bph_v_tm			int							DEFAULT 0 ,			/*电压不平衡度越限累计时间*/ 
		bph_i_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*电流不平衡最大值*/
		bph_i_max_tm	int							DEFAULT 0 ,			/*电流不平衡最大值发生时间*/
		bph_v_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*电压不平衡最大值*/
		bph_v_max_tm	int							DEFAULT 0			  /*电压不平衡最大值发生时间*/
)
grant select on  NMonIBal2008 to public
create unique clustered index NMonIBal2008index1
	on NMonIBal2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排月负载数据表(NMonLoad2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonLoad2008')
begin
create table NMonLoad2008
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		fz_max				decimal(14, 3) 	DEFAULT 0.0,	/*负载率最大值*/	
		fz_max_tm			int							DEFAULT 0 ,		/*负载率最大值发生时间*/
		fz_min				decimal(14, 3) 	DEFAULT 0.0,	/*负载率最小值*/
		fz_min_tm			int							DEFAULT 0 		/*负载率最小值发生时间*/
)
grant select on  NMonLoad2008 to public
create unique clustered index NMonLoad2008index1
	on NMonLoad2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排月功率因数区段累计时间(NMonCos2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonCos2008')
begin
create table NMonCos2008
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		cs_tm_1				int							DEFAULT 0 ,		/*区段1累计时间（功率因数＜定值1）*/
		cs_tm_2				int							DEFAULT 0 ,		/*区段2累计时间（定值1≤功率因数＜定值2）*/
		cs_tm_3				int							DEFAULT 0 		/*区段3累计时间（功率因数≥定值2）*/
)
grant select on  NMonCos2008 to public
create unique clustered index NMonCos2008index1
	on NMonCos2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排谐波电流数据表(NMonXbI2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonXbI2008')
begin
create table NMonXbI2008
(
		rtu_id						int   					not null,
		mp_id							smallint   			not null,
		date							int							not null,
		time							int							not null,
		
		xb_ia_02					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电流最大值*/
		xb_ia_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电流最大值*/
		xb_ia_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电流最大值*/
		xb_ia_05			    decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电流最大值*/
		xb_ia_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电流最大值*/
		xb_ia_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电流最大值*/
		xb_ia_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电流最大值*/
		xb_ia_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电流最大值*/
		xb_ia_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电流最大值*/
		xb_ia_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电流最大值*/
		xb_ia_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电流最大值*/
		xb_ia_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电流最大值*/
		xb_ia_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电流最大值*/
		xb_ia_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电流最大值*/
		xb_ia_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电流最大值*/
		xb_ia_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电流最大值*/
		xb_ia_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电流最大值*/
		xb_ia_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电流最大值*/
		xb_ia							decimal(14, 3) 	DEFAULT 0.0 ,	/*A相总畸变电流最大值*/
		
		xb_ia_02_tm				int		DEFAULT 0 ,							/*A相2次谐波电流最大值发生时间*/
		xb_ia_03_tm				int		DEFAULT 0 ,							/*A相3次谐波电流最大值发生时间*/
		xb_ia_04_tm				int		DEFAULT 0 ,							/*A相4次谐波电流最大值发生时间*/
		xb_ia_05_tm				int		DEFAULT 0 ,							/*A相5次谐波电流最大值发生时间*/
		xb_ia_06_tm				int		DEFAULT 0 ,							/*A相6次谐波电流最大值发生时间*/
		xb_ia_07_tm				int		DEFAULT 0 ,							/*A相7次谐波电流最大值发生时间*/
		xb_ia_08_tm				int		DEFAULT 0 ,							/*A相8次谐波电流最大值发生时间*/
		xb_ia_09_tm				int		DEFAULT 0 ,							/*A相9次谐波电流最大值发生时间*/
		xb_ia_10_tm				int		DEFAULT 0 ,							/*A相10次谐波电流最大值发生时间*/
		xb_ia_11_tm				int		DEFAULT 0 ,							/*A相11次谐波电流最大值发生时间*/
		xb_ia_12_tm				int		DEFAULT 0 ,							/*A相12次谐波电流最大值发生时间*/
		xb_ia_13_tm				int		DEFAULT 0 ,							/*A相13次谐波电流最大值发生时间*/
		xb_ia_14_tm				int		DEFAULT 0 ,							/*A相14次谐波电流最大值发生时间*/
		xb_ia_15_tm				int		DEFAULT 0 ,							/*A相15次谐波电流最大值发生时间*/
		xb_ia_16_tm				int		DEFAULT 0 ,							/*A相16次谐波电流最大值发生时间*/
		xb_ia_17_tm				int		DEFAULT 0 ,							/*A相17次谐波电流最大值发生时间*/
		xb_ia_18_tm				int		DEFAULT 0 ,							/*A相18次谐波电流最大值发生时间*/
		xb_ia_19_tm				int		DEFAULT 0 ,							/*A相19次谐波电流最大值发生时间*/
		xb_ia_tm					int		DEFAULT 0 ,					 	  /*A相总畸变电流最大值发生时间*/
		
		
		xb_ib_02					decimal(14, 3) 	DEFAULT 0.0,	/*B相2次谐波电流最大值*/
		xb_ib_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电流最大值*/
		xb_ib_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电流最大值*/
		xb_ib_05					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电流最大值*/
		xb_ib_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电流最大值*/
		xb_ib_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电流最大值*/
		xb_ib_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电流最大值*/
		xb_ib_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电流最大值*/
		xb_ib_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电流最大值*/
		xb_ib_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电流最大值*/
		xb_ib_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电流最大值*/
		xb_ib_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电流最大值*/
		xb_ib_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电流最大值*/
		xb_ib_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电流最大值*/
		xb_ib_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电流最大值*/
		xb_ib_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电流最大值*/
		xb_ib_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电流最大值*/
		xb_ib_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电流最大值*/
		xb_ib							decimal(14, 3) 	DEFAULT 0.0 ,	/*B相总畸变电流最大值*/
		
		xb_ib_02_tm				int		DEFAULT 0 ,							/*B相2次谐波电流最大值发生时间*/
		xb_ib_03_tm				int		DEFAULT 0 ,							/*B相3次谐波电流最大值发生时间*/
		xb_ib_04_tm				int		DEFAULT 0 ,							/*B相4次谐波电流最大值发生时间*/
		xb_ib_05_tm				int		DEFAULT 0 ,							/*B相5次谐波电流最大值发生时间*/
		xb_ib_06_tm				int		DEFAULT 0 ,							/*B相6次谐波电流最大值发生时间*/
		xb_ib_07_tm				int		DEFAULT 0 ,							/*B相7次谐波电流最大值发生时间*/
		xb_ib_08_tm				int		DEFAULT 0 ,							/*B相8次谐波电流最大值发生时间*/
		xb_ib_09_tm				int		DEFAULT 0 ,							/*B相9次谐波电流最大值发生时间*/
		xb_ib_10_tm				int		DEFAULT 0 ,							/*B相10次谐波电流最大值发生时间*/
		xb_ib_11_tm				int		DEFAULT 0 ,							/*B相11次谐波电流最大值发生时间*/
		xb_ib_12_tm				int		DEFAULT 0 ,							/*B相12次谐波电流最大值发生时间*/
		xb_ib_13_tm				int		DEFAULT 0 ,							/*B相13次谐波电流最大值发生时间*/
		xb_ib_14_tm				int		DEFAULT 0 ,							/*B相14次谐波电流最大值发生时间*/
		xb_ib_15_tm				int		DEFAULT 0 ,							/*B相15次谐波电流最大值发生时间*/
		xb_ib_16_tm				int		DEFAULT 0 ,							/*B相16次谐波电流最大值发生时间*/
		xb_ib_17_tm				int		DEFAULT 0 ,							/*B相17次谐波电流最大值发生时间*/
		xb_ib_18_tm				int		DEFAULT 0 ,							/*B相18次谐波电流最大值发生时间*/
		xb_ib_19_tm				int		DEFAULT 0 ,							/*B相19次谐波电流最大值发生时间*/
		xb_ib_tm					int		DEFAULT 0 ,					 		/*B相总畸变电流最大值发生时间*/
		
		
		xb_ic_02					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相2次谐波电流最大值*/
		xb_ic_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电流最大值*/
		xb_ic_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电流最大值*/
		xb_ic_05					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电流最大值*/
		xb_ic_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电流最大值*/
		xb_ic_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电流最大值*/
		xb_ic_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电流最大值*/
		xb_ic_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电流最大值*/
		xb_ic_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电流最大值*/
		xb_ic_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电流最大值*/
		xb_ic_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电流最大值*/
		xb_ic_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电流最大值*/
		xb_ic_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电流最大值*/
		xb_ic_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电流最大值*/
		xb_ic_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电流最大值*/
		xb_ic_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电流最大值*/
		xb_ic_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电流最大值*/
		xb_ic_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电流最大值*/
		xb_ic							decimal(14, 3) 	DEFAULT 0.0 ,	/*C相总畸变电流最大值*/
		
		xb_ic_02_tm				int		DEFAULT 0 ,							/*C相2次谐波电流最大值发生时间*/
		xb_ic_03_tm				int		DEFAULT 0 ,							/*C相3次谐波电流最大值发生时间*/
		xb_ic_04_tm				int		DEFAULT 0 ,							/*C相4次谐波电流最大值发生时间*/
		xb_ic_05_tm				int		DEFAULT 0 ,							/*C相5次谐波电流最大值发生时间*/
		xb_ic_06_tm				int		DEFAULT 0 ,							/*C相6次谐波电流最大值发生时间*/
		xb_ic_07_tm				int		DEFAULT 0 ,							/*C相7次谐波电流最大值发生时间*/
		xb_ic_08_tm				int		DEFAULT 0 ,							/*C相8次谐波电流最大值发生时间*/
		xb_ic_09_tm				int		DEFAULT 0 ,							/*C相9次谐波电流最大值发生时间*/
		xb_ic_10_tm				int		DEFAULT 0 ,							/*C相10次谐波电流最大值发生时间*/
		xb_ic_11_tm				int		DEFAULT 0 ,							/*C相11次谐波电流最大值发生时间*/
		xb_ic_12_tm				int		DEFAULT 0 ,							/*C相12次谐波电流最大值发生时间*/
		xb_ic_13_tm				int		DEFAULT 0 ,							/*C相13次谐波电流最大值发生时间*/
		xb_ic_14_tm				int		DEFAULT 0 ,							/*C相14次谐波电流最大值发生时间*/
		xb_ic_15_tm				int		DEFAULT 0 ,							/*C相15次谐波电流最大值发生时间*/
		xb_ic_16_tm				int		DEFAULT 0 ,							/*C相16次谐波电流最大值发生时间*/
		xb_ic_17_tm				int		DEFAULT 0 ,							/*C相17次谐波电流最大值发生时间*/
		xb_ic_18_tm				int		DEFAULT 0 ,							/*C相18次谐波电流最大值发生时间*/
		xb_ic_19_tm				int		DEFAULT 0 ,							/*C相19次谐波电流最大值发生时间*/
		xb_ic_tm					int		DEFAULT 0 					 		/*C相总畸变电流最大值发生时间*/

)
grant select on  NMonXbI2008 to public
create unique clustered index NMonXbI2008index1
	on NMonXbI2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排谐波电压数据表(NMonXbU2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonXbU2008')
begin
create table NMonXbU2008
(
		rtu_id					int   					not null,
		mp_id						smallint   			not null,
		date						int							not null,
		time						int							not null,
		
		xb_va_02				decimal(14, 3) 	DEFAULT 0.0,	/*A相2次谐波电压含有率最大值*/
		xb_va_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电压含有率最大值*/
		xb_va_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电压含有率最大值*/
		xb_va_05		    decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电压含有率最大值*/
		xb_va_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电压含有率最大值*/
		xb_va_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电压含有率最大值*/
		xb_va_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电压含有率最大值*/
		xb_va_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电压含有率最大值*/
		xb_va_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电压含有率最大值*/
		xb_va_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电压含有率最大值*/
		xb_va_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电压含有率最大值*/
		xb_va_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电压含有率最大值*/
		xb_va_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电压含有率最大值*/
		xb_va_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电压含有率最大值*/
		xb_va_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电压含有率最大值*/
		xb_va_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电压含有率最大值*/
		xb_va_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电压含有率最大值*/
		xb_va_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电压含有率最大值*/
		xb_va						decimal(14, 3) 	DEFAULT 0.0 ,	/*A相总畸变电流最大值*/
				
		xb_va_02_tm			int		DEFAULT 0 ,							/*A相2次谐波电压含有率最大值发生时间*/
		xb_va_03_tm			int		DEFAULT 0 ,							/*A相3次谐波电压含有率最大值发生时间*/
		xb_va_04_tm			int		DEFAULT 0 ,							/*A相4次谐波电压含有率最大值发生时间*/
		xb_va_05_tm			int		DEFAULT 0 ,							/*A相5次谐波电压含有率最大值发生时间*/
		xb_va_06_tm			int		DEFAULT 0 ,							/*A相6次谐波电压含有率最大值发生时间*/
		xb_va_07_tm			int		DEFAULT 0 ,							/*A相7次谐波电压含有率最大值发生时间*/
		xb_va_08_tm			int		DEFAULT 0 ,							/*A相8次谐波电压含有率最大值发生时间*/
		xb_va_09_tm			int		DEFAULT 0 ,							/*A相9次谐波电压含有率最大值发生时间*/
		xb_va_10_tm			int		DEFAULT 0 ,							/*A相10次谐波电压含有率最大值发生时间*/
		xb_va_11_tm			int		DEFAULT 0 ,							/*A相11次谐波电压含有率最大值发生时间*/
		xb_va_12_tm			int		DEFAULT 0 ,							/*A相12次谐波电压含有率最大值发生时间*/
		xb_va_13_tm			int		DEFAULT 0 ,							/*A相13次谐波电压含有率最大值发生时间*/
		xb_va_14_tm			int		DEFAULT 0 ,							/*A相14次谐波电压含有率最大值发生时间*/
		xb_va_15_tm			int		DEFAULT 0 ,							/*A相15次谐波电压含有率最大值发生时间*/
		xb_va_16_tm			int		DEFAULT 0 ,							/*A相16次谐波电压含有率最大值发生时间*/
		xb_va_17_tm			int		DEFAULT 0 ,							/*A相17次谐波电压含有率最大值发生时间*/
		xb_va_18_tm			int		DEFAULT 0 ,							/*A相18次谐波电压含有率最大值发生时间*/
		xb_va_19_tm			int		DEFAULT 0 ,							/*A相19次谐波电压含有率最大值发生时间*/
		xb_va_tm				int		DEFAULT 0 ,					 		/*A相总畸变电流最大值发生时间*/
		
		xb_vb_02				decimal(14, 3) 	DEFAULT 0.0,	/*B相2次谐波电压含有率最大值*/
		xb_vb_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电压含有率最大值*/
		xb_vb_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电压含有率最大值*/
		xb_vb_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电压含有率最大值*/
		xb_vb_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电压含有率最大值*/
		xb_vb_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电压含有率最大值*/
		xb_vb_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电压含有率最大值*/
		xb_vb_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电压含有率最大值*/
		xb_vb_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电压含有率最大值*/
		xb_vb_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电压含有率最大值*/
		xb_vb_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电压含有率最大值*/
		xb_vb_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电压含有率最大值*/
		xb_vb_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电压含有率最大值*/
		xb_vb_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电压含有率最大值*/
		xb_vb_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电压含有率最大值*/
		xb_vb_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电压含有率最大值*/
		xb_vb_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电压含有率最大值*/
		xb_vb_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电压含有率最大值*/
		xb_vb						decimal(14, 3) 	DEFAULT 0.0 ,	/*B相总畸变电流最大值*/
		
		xb_vb_02_tm			int		DEFAULT 0 ,							/*B相2次谐波电压含有率最大值发生时间*/
		xb_vb_03_tm			int		DEFAULT 0 ,							/*B相3次谐波电压含有率最大值发生时间*/
		xb_vb_04_tm			int		DEFAULT 0 ,							/*B相4次谐波电压含有率最大值发生时间*/
		xb_vb_05_tm			int		DEFAULT 0 ,							/*B相5次谐波电压含有率最大值发生时间*/
		xb_vb_06_tm			int		DEFAULT 0 ,							/*B相6次谐波电压含有率最大值发生时间*/
		xb_vb_07_tm			int		DEFAULT 0 ,							/*B相7次谐波电压含有率最大值发生时间*/
		xb_vb_08_tm			int		DEFAULT 0 ,							/*B相8次谐波电压含有率最大值发生时间*/
		xb_vb_09_tm			int		DEFAULT 0 ,							/*B相9次谐波电压含有率最大值发生时间*/
		xb_vb_10_tm			int		DEFAULT 0 ,							/*B相10次谐波电压含有率最大值发生时间*/
		xb_vb_11_tm			int		DEFAULT 0 ,							/*B相11次谐波电压含有率最大值发生时间*/
		xb_vb_12_tm			int		DEFAULT 0 ,							/*B相12次谐波电压含有率最大值发生时间*/
		xb_vb_13_tm			int		DEFAULT 0 ,							/*B相13次谐波电压含有率最大值发生时间*/
		xb_vb_14_tm			int		DEFAULT 0 ,							/*B相14次谐波电压含有率最大值发生时间*/
		xb_vb_15_tm			int		DEFAULT 0 ,							/*B相15次谐波电压含有率最大值发生时间*/
		xb_vb_16_tm			int		DEFAULT 0 ,							/*B相16次谐波电压含有率最大值发生时间*/
		xb_vb_17_tm			int		DEFAULT 0 ,							/*B相17次谐波电压含有率最大值发生时间*/
		xb_vb_18_tm			int		DEFAULT 0 ,							/*B相18次谐波电压含有率最大值发生时间*/
		xb_vb_19_tm			int		DEFAULT 0 ,							/*B相19次谐波电压含有率最大值发生时间*/
		xb_vb_tm				int		DEFAULT 0 ,					 		/*B相总畸变电流最大值发生时间*/
		
		
		xb_vc_02				decimal(14, 3) 	DEFAULT 0.0	,	/*C相2次谐波电压含有率最大值*/
		xb_vc_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电压含有率最大值*/
		xb_vc_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电压含有率最大值*/
		xb_vc_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电压含有率最大值*/
		xb_vc_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电压含有率最大值*/
		xb_vc_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电压含有率最大值*/
		xb_vc_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电压含有率最大值*/
		xb_vc_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电压含有率最大值*/
		xb_vc_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电压含有率最大值*/
		xb_vc_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电压含有率最大值*/
		xb_vc_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电压含有率最大值*/
		xb_vc_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电压含有率最大值*/
		xb_vc_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电压含有率最大值*/
		xb_vc_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电压含有率最大值*/
		xb_vc_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电压含有率最大值*/
		xb_vc_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电压含有率最大值*/
		xb_vc_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电压含有率最大值*/
		xb_vc_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电压含有率最大值*/
		xb_vc						decimal(14, 3) 	DEFAULT 0.0 ,	/*C相总畸变电流最大值*/
		
		xb_vc_02_tm			int		DEFAULT 0 ,							/*C相2次谐波电压含有率最大值发生时间*/
		xb_vc_03_tm			int		DEFAULT 0 ,							/*C相3次谐波电压含有率最大值发生时间*/
		xb_vc_04_tm			int		DEFAULT 0 ,							/*C相4次谐波电压含有率最大值发生时间*/
		xb_vc_05_tm			int		DEFAULT 0 ,							/*C相5次谐波电压含有率最大值发生时间*/
		xb_vc_06_tm			int		DEFAULT 0 ,							/*C相6次谐波电压含有率最大值发生时间*/
		xb_vc_07_tm			int		DEFAULT 0 ,							/*C相7次谐波电压含有率最大值发生时间*/
		xb_vc_08_tm			int		DEFAULT 0 ,							/*C相8次谐波电压含有率最大值发生时间*/
		xb_vc_09_tm			int		DEFAULT 0 ,							/*C相9次谐波电压含有率最大值发生时间*/
		xb_vc_10_tm			int		DEFAULT 0 ,							/*C相10次谐波电压含有率最大值发生时间*/
		xb_vc_11_tm			int		DEFAULT 0 ,							/*C相11次谐波电压含有率最大值发生时间*/
		xb_vc_12_tm			int		DEFAULT 0 ,							/*C相12次谐波电压含有率最大值发生时间*/
		xb_vc_13_tm			int		DEFAULT 0 ,							/*C相13次谐波电压含有率最大值发生时间*/
		xb_vc_14_tm			int		DEFAULT 0 ,							/*C相14次谐波电压含有率最大值发生时间*/
		xb_vc_15_tm			int		DEFAULT 0 ,							/*C相15次谐波电压含有率最大值发生时间*/
		xb_vc_16_tm			int		DEFAULT 0 ,							/*C相16次谐波电压含有率最大值发生时间*/
		xb_vc_17_tm			int		DEFAULT 0 ,							/*C相17次谐波电压含有率最大值发生时间*/
		xb_vc_18_tm			int		DEFAULT 0 ,							/*C相18次谐波电压含有率最大值发生时间*/
		xb_vc_19_tm			int		DEFAULT 0 ,							/*C相19次谐波电压含有率最大值发生时间*/
		xb_vc_tm				int		DEFAULT 0 					 		/*C相总畸变电流最大值发生时间*/
)
grant select on  NMonXbU2008 to public
create unique clustered index NMonXbU2008index1
	on NMonXbU2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排谐波越限数据表(NMonXbLmt2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NMonXbLmt2008')
begin
create table NMonXbLmt2008
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		
		xblmt_va			int		DEFAULT 0 ,			/*A相总畸变电压含有率越限累计时间*/
		xblmt_va_02		int		DEFAULT 0 ,			/*A相2次谐波电压含有率越限累计时间*/
		xblmt_va_03		int		DEFAULT 0 ,			/*A相3次谐波电压含有率越限累计时间*/
		xblmt_va_04		int		DEFAULT 0 ,			/*A相4次谐波电压含有率越限累计时间*/
		xblmt_va_05		int		DEFAULT 0 ,			/*A相5次谐波电压含有率越限累计时间*/
		xblmt_va_06		int		DEFAULT 0 ,			/*A相6次谐波电压含有率越限累计时间*/
		xblmt_va_07		int		DEFAULT 0 ,			/*A相7次谐波电压含有率越限累计时间*/
		xblmt_va_08		int		DEFAULT 0 ,			/*A相8次谐波电压含有率越限累计时间*/
		xblmt_va_09		int		DEFAULT 0 ,			/*A相9次谐波电压含有率越限累计时间*/
		xblmt_va_10		int		DEFAULT 0 ,			/*A相10次谐波电压含有率越限累计时间*/
		xblmt_va_11		int		DEFAULT 0 ,			/*A相11次谐波电压含有率越限累计时间*/
		xblmt_va_12		int		DEFAULT 0 ,			/*A相12次谐波电压含有率越限累计时间*/
		xblmt_va_13		int		DEFAULT 0 ,			/*A相13次谐波电压含有率越限累计时间*/
		xblmt_va_14		int		DEFAULT 0 ,			/*A相14次谐波电压含有率越限累计时间*/
		xblmt_va_15		int		DEFAULT 0 ,			/*A相15次谐波电压含有率越限累计时间*/
		xblmt_va_16		int		DEFAULT 0 ,			/*A相16次谐波电压含有率越限累计时间*/
		xblmt_va_17		int		DEFAULT 0 ,			/*A相17次谐波电压含有率越限累计时间*/
		xblmt_va_18		int		DEFAULT 0 ,			/*A相18次谐波电压含有率越限累计时间*/
		xblmt_va_19		int		DEFAULT 0 ,			/*A相19次谐波电压含有率越限累计时间*/
		xblmt_ia			int		DEFAULT 0 ,			/*A相总畸变电流越限累计时间*/
		xblmt_ia_02		int		DEFAULT 0 ,			/*A相2次谐波电流越限累计时间*/
		xblmt_ia_03		int		DEFAULT 0 ,			/*A相3次谐波电流越限累计时间*/
		xblmt_ia_04		int		DEFAULT 0 ,			/*A相4次谐波电流越限累计时间*/
		xblmt_ia_05		int		DEFAULT 0 ,			/*A相5次谐波电流越限累计时间*/
		xblmt_ia_06		int		DEFAULT 0 ,			/*A相6次谐波电流越限累计时间*/
		xblmt_ia_07		int		DEFAULT 0 ,			/*A相7次谐波电流越限累计时间*/
		xblmt_ia_08		int		DEFAULT 0 ,			/*A相8次谐波电流越限累计时间*/
		xblmt_ia_09		int		DEFAULT 0 ,			/*A相9次谐波电流越限累计时间*/
		xblmt_ia_10		int		DEFAULT 0 ,			/*A相10次谐波电流越限累计时间*/
		xblmt_ia_11		int		DEFAULT 0 ,			/*A相11次谐波电流越限累计时间*/
		xblmt_ia_12		int		DEFAULT 0 ,			/*A相12次谐波电流越限累计时间*/
		xblmt_ia_13		int		DEFAULT 0 ,			/*A相13次谐波电流越限累计时间*/
		xblmt_ia_14		int		DEFAULT 0 ,			/*A相14次谐波电流越限累计时间*/
		xblmt_ia_15		int		DEFAULT 0 ,			/*A相15次谐波电流越限累计时间*/
		xblmt_ia_16		int		DEFAULT 0 ,			/*A相16次谐波电流越限累计时间*/
		xblmt_ia_17		int		DEFAULT 0 ,			/*A相17次谐波电流越限累计时间*/
		xblmt_ia_18		int		DEFAULT 0 ,			/*A相18次谐波电流越限累计时间*/
		xblmt_ia_19		int		DEFAULT 0 ,			/*A相19次谐波电流越限累计时间*/
		xblmt_vb			int		DEFAULT 0 ,			/*B相总畸变电压含有率越限累计时间*/
		xblmt_vb_02		int		DEFAULT 0 ,			/*B相2次谐波电压含有率越限累计时间*/
		xblmt_vb_03		int		DEFAULT 0 ,			/*B相3次谐波电压含有率越限累计时间*/
		xblmt_vb_04		int		DEFAULT 0 ,			/*B相4次谐波电压含有率越限累计时间*/
		xblmt_vb_05		int		DEFAULT 0 ,			/*B相5次谐波电压含有率越限累计时间*/
		xblmt_vb_06		int		DEFAULT 0 ,			/*B相6次谐波电压含有率越限累计时间*/
		xblmt_vb_07		int		DEFAULT 0 ,			/*B相7次谐波电压含有率越限累计时间*/
		xblmt_vb_08		int		DEFAULT 0 ,			/*B相8次谐波电压含有率越限累计时间*/
		xblmt_vb_09		int		DEFAULT 0 ,			/*B相9次谐波电压含有率越限累计时间*/
		xblmt_vb_10		int		DEFAULT 0 ,			/*B相10次谐波电压含有率越限累计时间*/
		xblmt_vb_11		int		DEFAULT 0 ,			/*B相11次谐波电压含有率越限累计时间*/
		xblmt_vb_12		int		DEFAULT 0 ,			/*B相12次谐波电压含有率越限累计时间*/
		xblmt_vb_13		int		DEFAULT 0 ,			/*B相13次谐波电压含有率越限累计时间*/
		xblmt_vb_14		int		DEFAULT 0 ,			/*B相14次谐波电压含有率越限累计时间*/
		xblmt_vb_15		int		DEFAULT 0 ,			/*B相15次谐波电压含有率越限累计时间*/
		xblmt_vb_16		int		DEFAULT 0 ,			/*B相16次谐波电压含有率越限累计时间*/
		xblmt_vb_17		int		DEFAULT 0 ,			/*B相17次谐波电压含有率越限累计时间*/
		xblmt_vb_18		int		DEFAULT 0 ,			/*B相18次谐波电压含有率越限累计时间*/
		xblmt_vb_19		int		DEFAULT 0 ,			/*B相19次谐波电压含有率越限累计时间*/
		xblmt_ib			int		DEFAULT 0 ,			/*B相总畸变电流越限累计时间*/
		xblmt_ib_02		int		DEFAULT 0 ,			/*B相2次谐波电流越限累计时间*/
		xblmt_ib_03		int		DEFAULT 0 ,			/*B相3次谐波电流越限累计时间*/
		xblmt_ib_04		int		DEFAULT 0 ,			/*B相4次谐波电流越限累计时间*/
		xblmt_ib_05		int		DEFAULT 0 ,			/*B相5次谐波电流越限累计时间*/
		xblmt_ib_06		int		DEFAULT 0 ,			/*B相6次谐波电流越限累计时间*/
		xblmt_ib_07		int		DEFAULT 0 ,			/*B相7次谐波电流越限累计时间*/
		xblmt_ib_08		int		DEFAULT 0 ,			/*B相8次谐波电流越限累计时间*/
		xblmt_ib_09		int		DEFAULT 0 ,			/*B相9次谐波电流越限累计时间*/
		xblmt_ib_10		int		DEFAULT 0 ,			/*B相10次谐波电流越限累计时间*/
		xblmt_ib_11		int		DEFAULT 0 ,			/*B相11次谐波电流越限累计时间*/
		xblmt_ib_12		int		DEFAULT 0 ,			/*B相12次谐波电流越限累计时间*/
		xblmt_ib_13		int		DEFAULT 0 ,			/*B相13次谐波电流越限累计时间*/
		xblmt_ib_14		int		DEFAULT 0 ,			/*B相14次谐波电流越限累计时间*/
		xblmt_ib_15		int		DEFAULT 0 ,			/*B相15次谐波电流越限累计时间*/
		xblmt_ib_16		int		DEFAULT 0 ,			/*B相16次谐波电流越限累计时间*/
		xblmt_ib_17		int		DEFAULT 0 ,			/*B相17次谐波电流越限累计时间*/
		xblmt_ib_18		int		DEFAULT 0 ,			/*B相18次谐波电流越限累计时间*/
		xblmt_ib_19		int		DEFAULT 0 ,			/*B相19次谐波电流越限累计时间*/
		xblmt_vc			int		DEFAULT 0 ,			/*C相总畸变电压含有率越限累计时间*/
		xblmt_vc_02		int		DEFAULT 0 ,			/*C相2次谐波电压含有率越限累计时间*/
		xblmt_vc_03		int		DEFAULT 0 ,			/*C相3次谐波电压含有率越限累计时间*/
		xblmt_vc_04		int		DEFAULT 0 ,			/*C相4次谐波电压含有率越限累计时间*/
		xblmt_vc_05		int		DEFAULT 0 ,			/*C相5次谐波电压含有率越限累计时间*/
		xblmt_vc_06		int		DEFAULT 0 ,			/*C相6次谐波电压含有率越限累计时间*/
		xblmt_vc_07		int		DEFAULT 0 ,			/*C相7次谐波电压含有率越限累计时间*/
		xblmt_vc_08		int		DEFAULT 0 ,			/*C相8次谐波电压含有率越限累计时间*/
		xblmt_vc_09		int		DEFAULT 0 ,			/*C相9次谐波电压含有率越限累计时间*/
		xblmt_vc_10		int		DEFAULT 0 ,			/*C相10次谐波电压含有率越限累计时间*/
		xblmt_vc_11		int		DEFAULT 0 ,			/*C相11次谐波电压含有率越限累计时间*/
		xblmt_vc_12		int		DEFAULT 0 ,			/*C相12次谐波电压含有率越限累计时间*/
		xblmt_vc_13		int		DEFAULT 0 ,			/*C相13次谐波电压含有率越限累计时间*/
		xblmt_vc_14		int		DEFAULT 0 ,			/*C相14次谐波电压含有率越限累计时间*/
		xblmt_vc_15		int		DEFAULT 0 ,			/*C相15次谐波电压含有率越限累计时间*/
		xblmt_vc_16		int		DEFAULT 0 ,			/*C相16次谐波电压含有率越限累计时间*/
		xblmt_vc_17		int		DEFAULT 0 ,			/*C相17次谐波电压含有率越限累计时间*/
		xblmt_vc_18		int		DEFAULT 0 ,			/*C相18次谐波电压含有率越限累计时间*/
		xblmt_vc_19		int		DEFAULT 0 ,			/*C相19次谐波电压含有率越限累计时间*/
		xblmt_ic			int		DEFAULT 0 ,			/*C相总畸变电流越限累计时间*/
		xblmt_ic_02		int		DEFAULT 0 ,			/*C相2次谐波电流越限累计时间*/
		xblmt_ic_03		int		DEFAULT 0 ,			/*C相3次谐波电流越限累计时间*/
		xblmt_ic_04		int		DEFAULT 0 ,			/*C相4次谐波电流越限累计时间*/
		xblmt_ic_05		int		DEFAULT 0 ,			/*C相5次谐波电流越限累计时间*/
		xblmt_ic_06		int		DEFAULT 0 ,			/*C相6次谐波电流越限累计时间*/
		xblmt_ic_07		int		DEFAULT 0 ,			/*C相7次谐波电流越限累计时间*/
		xblmt_ic_08		int		DEFAULT 0 ,			/*C相8次谐波电流越限累计时间*/
		xblmt_ic_09		int		DEFAULT 0 ,			/*C相9次谐波电流越限累计时间*/
		xblmt_ic_10		int		DEFAULT 0 ,			/*C相10次谐波电流越限累计时间*/
		xblmt_ic_11		int		DEFAULT 0 ,			/*C相11次谐波电流越限累计时间*/
		xblmt_ic_12		int		DEFAULT 0 ,			/*C相12次谐波电流越限累计时间*/
		xblmt_ic_13		int		DEFAULT 0 ,			/*C相13次谐波电流越限累计时间*/
		xblmt_ic_14		int		DEFAULT 0 ,			/*C相14次谐波电流越限累计时间*/
		xblmt_ic_15		int		DEFAULT 0 ,			/*C相15次谐波电流越限累计时间*/
		xblmt_ic_16		int		DEFAULT 0 ,			/*C相16次谐波电流越限累计时间*/
		xblmt_ic_17		int		DEFAULT 0 ,			/*C相17次谐波电流越限累计时间*/
		xblmt_ic_18		int		DEFAULT 0 ,			/*C相18次谐波电流越限累计时间*/
		xblmt_ic_19		int		DEFAULT 0 			/*C相19次谐波电流越限累计时间*/
)
grant select on  NMonXbLmt2008 to public
create unique clustered index NMonXbLmt2008index1
	on NMonXbLmt2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排总加组曲线数据表(NGMinQx200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NGMinQx200808')
begin
create table NGMinQx200808
(
	rtu_id		int   					not null,
	group_id	smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	p					decimal(14, 3) 	DEFAULT 0.0 ,	/*	有功功率*/
	q					decimal(14, 3) 	DEFAULT 0.0 ,	/*	无功功率*/
	ygdl			decimal(14, 3) 	DEFAULT 0.0 ,	/*	有功电量*/
	wgdl			decimal(14, 3) 	DEFAULT 0.0	  /*	无功电量*/
)
grant select on  NGMinQx200808 to public
create unique clustered index ZGMinSsl200808index1
	on NGMinQx200808(rtu_id,group_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 农排总加组日功率极值数据表(NGDayPMax200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NGDayPMax200808')
begin
create table NGDayPMax200808
(
		rtu_id				int			   			not null,
		group_id			smallint   			not null,
		date					int							not null,
		time					int							not null,
		
		ext_p_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最大有功功率*/	
		ext_p_max_tm	int		DEFAULT 0 ,							/*总最大有功功率发生时间*/
		ext_p_a				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相最大有功功率*/
		ext_p_a_tm		int		DEFAULT 0 ,							/*A相最大有功功率发生时间*/
		ext_p_b				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相最大有功功率*/
		ext_p_b_tm		int		DEFAULT 0 ,							/*B相最大有功功率发生时间*/
		ext_p_c				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相最大有功功率*/
		ext_p_c_tm		int		DEFAULT 0 ,							/*C相最大有功功率发生时间*/
		ext_p_min			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最有功小功率*/
		ext_p_min_tm	int		DEFAULT 0 ,							/*总最小有功功率发生时间*/
		ext_p_0_tm		int		DEFAULT 0 ,							/*总有功功率为零时间*/
		ext_p_a0_tm		int		DEFAULT 0 ,							/*A相有功功率为零时间*/
		ext_p_b0_tm		int		DEFAULT 0 ,							/*B相有功功率为零时间*/
		ext_p_c0_tm		int		DEFAULT 0								/*C相有功功率为零时间*/
)
grant select on  NGDayPMax200808 to public
create unique clustered index NGDayPMax200808index1
	on NGDayPMax200808(rtu_id,group_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排总加组日电量数据表(NGDayDl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NGDayDl200808')
begin
create table NGDayDl200808
(
	rtu_id		int   					not null,
	group_id	smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/	
	
	dl_1xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*一相限无功电量*/	
	dl_2xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*二相限无功电量*/	
	dl_3xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*三相限无功电量*/	
	dl_4xx		decimal(14, 3) 	DEFAULT 0.0 ,	/*四相限无功电量*/
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  NGDayDl200808 to public
create unique clustered index NGDayDl200808index1
	on NGDayDl200808(rtu_id,group_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排总加组月功率极值数据表(NGMonMaxP2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NGMonMaxP2008')
begin
create table NGMonMaxP2008
(
		rtu_id				int   					not null,
		group_id			smallint   			not null,
		date					int							not null,
		time					int							not null,
		ext_p_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最大有功功率*/	
		ext_p_max_tm	int		DEFAULT 0 ,							/*总最大有功功率发生时间*/
		ext_p_a				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相最大有功功率*/
		ext_p_a_tm		int		DEFAULT 0 ,							/*A相最大有功功率发生时间*/
		ext_p_b				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相最大有功功率*/
		ext_p_b_tm		int		DEFAULT 0 ,							/*B相最大有功功率发生时间*/
		ext_p_c				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相最大有功功率*/
		ext_p_c_tm		int		DEFAULT 0 ,							/*C相最大有功功率发生时间*/
		ext_p_min			decimal(14, 3) 	DEFAULT 0.0 ,	/*总最有功小功率*/
		ext_p_min_tm	int		DEFAULT 0 ,							/*总最小有功功率发生时间*/
		ext_p_0_tm		int		DEFAULT 0 ,							/*总有功功率为零时间*/
		ext_p_a0_tm		int		DEFAULT 0 ,							/*A相有功功率为零时间*/
		ext_p_b0_tm		int		DEFAULT 0 ,							/*B相有功功率为零时间*/
		ext_p_c0_tm		int		DEFAULT 0								/*C相有功功率为零时间*/
)
grant select on  NGMonMaxP2008 to public
create unique clustered index NGMonMaxP2008index1
	on NGMonMaxP2008(rtu_id,group_id,date,time)
end
go
-- 标记结束

--   '-------Create table 农排总加组月电量数据表(NGMonDl2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NGMonDl2008')
begin
create table NGMonDl2008
(
	rtu_id		int   					not null,
	group_id	smallint   			not null,
	date			int							not null,
	time			int							not null,
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_zy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰电量*/	
	dl_zy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平电量*/	
	dl_zy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷电量*/	
	dl_zy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖电量*/	
	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_fy_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰电量*/	
	dl_fy_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平电量*/	
	dl_fy_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷电量*/	
	dl_fy_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖电量*/	
		
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/	
	dl_zw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰电量*/	
	dl_zw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平电量*/	
	dl_zw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷电量*/	
	dl_zw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖电量*/	
	
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总电量*/	
	dl_fw_fl1	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰电量*/	
	dl_fw_fl2	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平电量*/	
	dl_fw_fl3	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷电量*/	
	dl_fw_fl4	decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖电量*/	
	
	dl_source	tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  NGMonDl2008 to public
create unique clustered index NGMonDl2008index1
	on NGMonDl2008(rtu_id,group_id,date,time)
end
go
-- 标记结束


--	'-------Create table 农排用电记录表(npyd_record2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'npyd_record2008')
begin
create table npyd_record2008
(
	rtu_id				int						  		not null,
	mp_id					smallint  					not null,	
	card_no				varchar(32)					not	null,			/*卡号*/
	
	area_id				int											null,
	farmer_id			smallint								null,
	farmer_no			varchar(64)							null,

	begin_date		int									not	null,			/*开始日期*/
	begin_time		int									not	null,			/*开始时间*/	
	end_date			int									not	null,			/*结束日期*/
	end_time			int									not	null,			/*结束时间*/		
	fee						decimal(12,6)						null,			/*费率*/
	use_money			decimal(12,2)						null,			/*本次用电金额*/	
	remain_money  decimal(12,2)						null,			/*剩余金额*/	
	use_dl				decimal(12,2)						null,			/*本次用电电量*/	
	zero_dl				decimal(12,2)						null,			/*本次过零电量*/	
	farmer_state	tinyint									null,			/*用电状态 0 正常 1 系统停电 2 无脉冲自动拉闸 3人为锁表*/
)
grant select on npyd_record2008 to public
create unique clustered index npyd_record2008index1
	on npyd_record2008(rtu_id,mp_id,begin_date,begin_time)
create index npyd_record2008index2
	on npyd_record2008(card_no,begin_date,begin_time)
end
go
-- 标记结束


--hzhw 20130311
--	'-------Create table 农排挂起记录表(npgq_record2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'npgq_record2008')
begin
create table npgq_record2008
(
	rtu_id				int						  		not null,
	mp_id					smallint  					not null,	
	card_no				varchar(32)					not	null,			/*卡号*/
	
	begin_date		int									not	null,			/*开始日期*/
	begin_time		int									not	null,			/*开始时间*/	
	gq_date			  int									not	null,			/*挂起日期*/
	gq_time			  int									not	null,			/*挂起时间*/		
	fee						decimal(12,6)						null,			/*费率*/
	use_money			decimal(12,2)						null,			/*本次用电金额*/	
	remain_money  decimal(12,2)						null,			/*剩余金额*/	
	
	use_dl				decimal(12,2)						null,			/*本次用电电量*/	
	zero_dl				decimal(12,2)						null,			/*本次过零电量*/	
	farmer_state	tinyint									null,			/*用电状态 0 正常 1 系统停电 2 无脉冲自动拉闸 3人为锁表*/
)
grant select on npgq_record2008 to public
create unique clustered index npgq_record2008index1
	on npgq_record2008(rtu_id,mp_id,begin_date,begin_time)
create index npgq_record2008index2
	on npgq_record2008(card_no,begin_date,begin_time)
end
go
-- 标记结束

----------------------农排应用结束--------------------------------------------


----------------------系统事项开始------------------------------------------------

--   '-------Create table 历史事项表(eve200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'eve200808')
begin
create table eve200808
(
    classno    			smallint      	null,
    typeno  				smallint      	not null,
    ymd        			int           	not null,
    hmsms    	 			int           	not null,
    voltgrade	  		tinyint		 			null,
    output        	tinyint       	null,
    member_id0  		int      				not null,
    member_id1  		int      				not null,
    member_id2  		int      				not null,
    double_value0  	decimal(14, 3)	null,
    double_value1  	decimal(14, 3)	null,
    double_value2  	decimal(14, 3)	null,
    double_value3  	decimal(14, 3)	null,
    state_value0  	tinyint       	null,
    state_value1  	tinyint       	null,
    state_value2  	tinyint       	null,
    state_value3  	tinyint       	null,
    group_name  		varchar(16)     null,    
    member_name0  	varchar(16)     null,
    member_name1  	varchar(16)     null,
    member_name2  	varchar(16)     null,
    char_info  		varchar(255)    	null
)
grant select on eve200808 to public
create  clustered index  eve200808ind
	on eve200808(ymd,hmsms,typeno,member_id0,member_id1)  
end
go
-- 标记结束

--   '-------Create table 历史事项备份表(eve_kld200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'eve_kld200808')
begin
create table eve_kld200808
(
    classno    			smallint      	null,
    typeno  				smallint      	not null,
    ymd        			int           	not null,
    hmsms    	 			int           	not null,
    voltgrade	  		tinyint		 			null,
    output        	tinyint       	null,
    member_id0  		int      				not null,
    member_id1  		int      				not null,
    member_id2  		int      				not null,
    double_value0  	decimal(14, 3)	null,
    double_value1  	decimal(14, 3)	null,
    double_value2  	decimal(14, 3)	null,
    double_value3  	decimal(14, 3)	null,
    state_value0  	tinyint       	null,
    state_value1  	tinyint       	null,
    state_value2  	tinyint       	null,
    state_value3  	tinyint       	null,    
    group_name  		varchar(16)     null,
    member_name0  	varchar(16)    	null,
    member_name1  	varchar(16)     null,
    member_name2  	varchar(16)     null,    
    char_info  		varchar(255)    null
)
grant select on eve_kld200808 to public
create  clustered index  eve_kld200808ind
	on eve_kld200808(ymd,hmsms,typeno,member_id0,member_id1)  
end
go
-- 标记结束

--   '-------Create table 历史事项处理表(eveproc200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'eveproc200808')
begin
create table eveproc200808
(
    classno       smallint       null,
    typeno      smallint       not null,
    ymd           int            not null,
    hmsms         int            not null,
    member_id0    int          not null,
    member_id1    int          not null,
    member_id2    int          not null,
  procmanid    smallint    null,
  procymd       int            null,
    prochmsms     int            null,
    procinfo     varchar(255)    null
)
grant select on eveproc200808 to public
create  clustered index  eveproc200808ind
 on eveproc200808(ymd,hmsms,typeno,member_id0,member_id1)  
end
go
-- 标记结束

----------------------缴费结算事项开始------------------------------------------------

--   '-------Create table 缴费结算事项表(payjs200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'payjs200808')
begin
create table payjs200808
(
		ymd        			int           	not null,
    hmsms    	 			int           	not null,
    app_type				tinyint					not null, 
    ok_flag					tinyint					not null,
    double_value0  	decimal(14, 3)	null,
    double_value1  	decimal(14, 3)	null,
    state_value0  	tinyint       	null,
    state_value1  	tinyint       	null,
    member_name0  	varchar(16)     null,
    member_name1  	varchar(16)     null,
    char_info  			varchar(128)    null
)
grant select on payjs200808 to public
create  clustered index  payjs200808ind
	on payjs200808(ymd,hmsms,app_type,ok_flag)  
end
go
-- 标记结束
----------------------系统事项结束------------------------------------------------


--hzhw20110609
----------------------预付费开始------------------------------------------------

--	'-------Create table 终端预付费记录表(ZYff2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZYff2008')
begin
create table ZYff2008
(
	rtu_id				int						  		not null,
	zjg_id				smallint  					not null,
	
	cons_id				smallint						not null,			/*客户编号*/
	cons_desc			varchar(64)							null,			/*客户名称*/
	
	op_man				varchar(64)					not null,			/*操作员*/	
	op_type				tinyint							not null,			/*本次操作类型 0 初始态, 1 开户, 2 缴费, 3 补卡, 4 补写卡, 5 冲正, 6 换表, 7 换费率 10 销户  */	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/
	
	pay_type			tinyint									null,			/*缴费方式*/
	wasteno				varchar(32)							null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/	
	
	pay_money			decimal(12,2)						null,			/*缴费金额*/	
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)						null,			/*总金额*/
	
	buy_dl				decimal(12,2)						null,			/*购电量*/
	pay_bmc				decimal(12,2)						null,			/*表码差*/
	
	shutdown_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 表底计费时为:断电止码 */
	
	jsbd_zyz			decimal(12,2)						null,			/*测量点1-结算总表底*/
	jsbd_zyj			decimal(12,2)						null,			/*测量点1-结算尖表底*/	
	jsbd_zyf			decimal(12,2)						null,			/*测量点1-结算峰表底*/	
	jsbd_zyp			decimal(12,2)						null,			/*测量点1-结算平表底*/	
	jsbd_zyg			decimal(12,2)						null,			/*测量点1-结算谷表底*/

	jsbd1_zyz			decimal(12,2)						null,			/*测量点2-结算总表底*/
	jsbd1_zyj			decimal(12,2)						null,			/*测量点2-结算尖表底*/	
	jsbd1_zyf			decimal(12,2)						null,			/*测量点2-结算峰表底*/	
	jsbd1_zyp			decimal(12,2)						null,			/*测量点2-结算平表底*/	
	jsbd1_zyg			decimal(12,2)						null,			/*测量点2-结算谷表底*/

	jsbd2_zyz			decimal(12,2)						null,			/*测量点3-结算总表底*/
	jsbd2_zyj			decimal(12,2)						null,			/*测量点3-结算尖表底*/	
	jsbd2_zyf			decimal(12,2)						null,			/*测量点3-结算峰表底*/	
	jsbd2_zyp			decimal(12,2)						null,			/*测量点3-结算平表底*/	
	jsbd2_zyg			decimal(12,2)						null,			/*测量点3-结算谷表底*/
	-- 20130108
	jsbd_zwz			decimal(12,2)						null,			/*测量点1-结算无功总表底*/
	jsbd1_zwz			decimal(12,2)						null,			/*测量点2-结算无功总表底*/
	jsbd2_zwz			decimal(12,2)						null,			/*测量点3-结算无功总表底*/
	--20130108
	
	jsbd_ymd			int											null,			/*结算时间*/

	-- 20130108
  calc_bdymd		int										 	null,			/*算费表底时间-YYYYMMDD*/              		                  
	calc_zyz			decimal(12,2)		  			null,			/*算费时总表底*/
	calc_zyj			decimal(12,2)		  			null,			/*算费时尖表底*/
	calc_zyf			decimal(12,2)		  			null,			/*算费时峰表底*/
	calc_zyp			decimal(12,2)		  			null,			/*算费时平表底*/
	calc_zyg			decimal(12,2)		  			null,			/*算费时谷表底*/
	              		                  
	calc1_zyz			decimal(12,2)		  			null,			/*动力关联1-算费时总表底*/
	calc1_zyj			decimal(12,2)		  			null,			/*动力关联1-算费时尖表底*/
	calc1_zyf			decimal(12,2)		  			null,			/*动力关联1-算费时峰表底*/
	calc1_zyp			decimal(12,2)		  			null,			/*动力关联1-算费时平表底*/
	calc1_zyg			decimal(12,2)		  			null,			/*动力关联1-算费时谷表底*/

	calc2_zyz			decimal(12,2)		  			null,			/*动力关联2-算费时总表底*/
	calc2_zyj			decimal(12,2)		  			null,			/*动力关联2-算费时尖表底*/
	calc2_zyf			decimal(12,2)		  			null,			/*动力关联2-算费时峰表底*/
	calc2_zyp			decimal(12,2)		  			null,			/*动力关联2-算费时平表底*/
	calc2_zyg			decimal(12,2)		  			null,			/*动力关联2-算费时谷表底*/

	calc_zwz			decimal(12,2)						null,			/*测量点1-算费无功总表底*/
	calc1_zwz			decimal(12,2)						null,			/*测量点2-算费无功总表底*/
	calc2_zwz			decimal(12,2)						null,			/*测量点3-算费无功总表底*/

	real_powrate	decimal(12,2)						null,			/*实际功率因数*/
	ele_money		  decimal(12,2)						null,			/*电度电费*/
	jbf_money		  decimal(12,2)						null,			/*基本费电费*/
	powrate_money	decimal(12,2)						null,			/*力调电费*/
	other_money		decimal(12,2)						null,			/*其它电费*/
	
	now_remain		decimal(12,2)		  			null,			/*当前剩余*/
	total_gdz			decimal(12,2)		  			null,			/*累计购电值*/
	
	total_yzbdl		decimal(12,2)						null,			/*有功追补累计用电量*/
	total_wzbdl		decimal(12,2)						null,			/*无功追补累计用电量*/
	total_ydl			decimal(12,2)						null,			/*有功累计用电量*/
	total_wdl			decimal(12,2)						null,			/*无功累计用电量*/

	zbele_money	  decimal(12,2)						null,			/*追补电度电费*/
	zbjbf_money	  decimal(12,2)						null,			/*追补基本费电费*/
	zbother_money	decimal(12,2)						null,			/*追补其它电费*/
	--20130108

	alarm1				decimal(12,2)						null,			/*报警值1*/
	alarm2				decimal(12,2)						null,			/*报警值2*/

	lsttw_money		decimal(12,6)						null,			/*上次调尾金额*/
	nowtw_money		decimal(12,6)						null,			/*本次调尾金额*/

	buy_times			int											null,			/*购电次数*/
	
	cur_feeproj		smallint								null,			/*测量点1-当前费率号*/
	cur_feeproj1	smallint								null,			/*测量点2-当前费率号*/
	cur_feeproj2	smallint								null,			/*测量点3-当前费率号*/

	op_result			tinyint									null,			/*操作结果 0:未成功 1:写卡成功 2:远程设置成功 3:主站操作成功 4:暂时不处理 5:正在操作 6:失败*/	

	visible_flag	tinyint									null,			/*是否显示*/	
	
	sg186_ysdw		varchar(20)							null,			/*186应收单位标识*/	
	up186_flag		tinyint									null,			/*186上传标志*/
	checkpay_flag	tinyint									null			/*186上传成功标志*/
)
grant select on ZYff2008 to public
create clustered index ZYff2008index1
	on ZYff2008(rtu_id,zjg_id,op_date,op_time)	
end
go
-- 标记结束


--	'-------Create table 终端预付费记录备份表(syszyfrcd2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'syszyfrcd2008')
begin
create table syszyfrcd2008
(
	rtu_id				int						  		not null,
	zjg_id				smallint  					not null,
	
	cons_id				smallint						not null,			/*客户编号*/
	cons_desc			varchar(64)							null,			/*客户名称*/
	
	op_man				varchar(64)					not null,			/*操作员*/	
	op_type				tinyint							not null,			/*本次操作类型 0 初始态, 1 开户, 2 缴费, 3 补卡, 4 补写卡, 5 冲正, 6 换表, 7 换费率 10 销户  */	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/
	
	pay_type			tinyint									null,			/*缴费方式*/
	wasteno				varchar(32)							null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/	
	
	pay_money			decimal(12,2)						null,			/*缴费金额*/	
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)						null,			/*总金额*/
	
	buy_dl				decimal(12,2)						null,			/*购电量*/
	pay_bmc				decimal(12,2)						null,			/*表码差*/
	
	shutdown_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 表底计费时为:断电止码 */
	
	jsbd_zyz			decimal(12,2)						null,			/*测量点1-结算总表底*/
	jsbd_zyj			decimal(12,2)						null,			/*测量点1-结算尖表底*/	
	jsbd_zyf			decimal(12,2)						null,			/*测量点1-结算峰表底*/	
	jsbd_zyp			decimal(12,2)						null,			/*测量点1-结算平表底*/	
	jsbd_zyg			decimal(12,2)						null,			/*测量点1-结算谷表底*/

	jsbd1_zyz			decimal(12,2)						null,			/*测量点2-结算总表底*/
	jsbd1_zyj			decimal(12,2)						null,			/*测量点2-结算尖表底*/	
	jsbd1_zyf			decimal(12,2)						null,			/*测量点2-结算峰表底*/	
	jsbd1_zyp			decimal(12,2)						null,			/*测量点2-结算平表底*/	
	jsbd1_zyg			decimal(12,2)						null,			/*测量点2-结算谷表底*/

	jsbd2_zyz			decimal(12,2)						null,			/*测量点3-结算总表底*/
	jsbd2_zyj			decimal(12,2)						null,			/*测量点3-结算尖表底*/	
	jsbd2_zyf			decimal(12,2)						null,			/*测量点3-结算峰表底*/	
	jsbd2_zyp			decimal(12,2)						null,			/*测量点3-结算平表底*/	
	jsbd2_zyg			decimal(12,2)						null,			/*测量点3-结算谷表底*/
	-- 20130108
	jsbd_zwz			decimal(12,2)						null,			/*测量点1-结算无功总表底*/
	jsbd1_zwz			decimal(12,2)						null,			/*测量点2-结算无功总表底*/
	jsbd2_zwz			decimal(12,2)						null,			/*测量点3-结算无功总表底*/
	--20130108
	
	jsbd_ymd			int											null,			/*结算时间*/

	-- 20130108
  calc_bdymd		int										 	null,			/*算费表底时间-YYYYMMDD*/              		                  
	calc_zyz			decimal(12,2)		  			null,			/*算费时总表底*/
	calc_zyj			decimal(12,2)		  			null,			/*算费时尖表底*/
	calc_zyf			decimal(12,2)		  			null,			/*算费时峰表底*/
	calc_zyp			decimal(12,2)		  			null,			/*算费时平表底*/
	calc_zyg			decimal(12,2)		  			null,			/*算费时谷表底*/
	              		                  
	calc1_zyz			decimal(12,2)		  			null,			/*动力关联1-算费时总表底*/
	calc1_zyj			decimal(12,2)		  			null,			/*动力关联1-算费时尖表底*/
	calc1_zyf			decimal(12,2)		  			null,			/*动力关联1-算费时峰表底*/
	calc1_zyp			decimal(12,2)		  			null,			/*动力关联1-算费时平表底*/
	calc1_zyg			decimal(12,2)		  			null,			/*动力关联1-算费时谷表底*/

	calc2_zyz			decimal(12,2)		  			null,			/*动力关联2-算费时总表底*/
	calc2_zyj			decimal(12,2)		  			null,			/*动力关联2-算费时尖表底*/
	calc2_zyf			decimal(12,2)		  			null,			/*动力关联2-算费时峰表底*/
	calc2_zyp			decimal(12,2)		  			null,			/*动力关联2-算费时平表底*/
	calc2_zyg			decimal(12,2)		  			null,			/*动力关联2-算费时谷表底*/

	calc_zwz			decimal(12,2)						null,			/*测量点1-算费无功总表底*/
	calc1_zwz			decimal(12,2)						null,			/*测量点2-算费无功总表底*/
	calc2_zwz			decimal(12,2)						null,			/*测量点3-算费无功总表底*/

	real_powrate	decimal(12,2)						null,			/*实际功率因数*/
	ele_money		  decimal(12,2)						null,			/*电度电费*/
	jbf_money		  decimal(12,2)						null,			/*基本费电费*/
	powrate_money	decimal(12,2)						null,			/*力调电费*/
	other_money		decimal(12,2)						null,			/*其它电费*/
	
	now_remain		decimal(12,2)		  			null,			/*当前剩余*/
	total_gdz			decimal(12,2)		  			null,			/*累计购电值*/
	
	total_yzbdl		decimal(12,2)						null,			/*有功追补累计用电量*/
	total_wzbdl		decimal(12,2)						null,			/*无功追补累计用电量*/
	total_ydl			decimal(12,2)						null,			/*有功累计用电量*/
	total_wdl			decimal(12,2)						null,			/*无功累计用电量*/

	zbele_money	  decimal(12,2)						null,			/*追补电度电费*/
	zbjbf_money	  decimal(12,2)						null,			/*追补基本费电费*/
	zbother_money	decimal(12,2)						null,			/*追补其它电费*/
	--20130108

	alarm1				decimal(12,2)						null,			/*报警值1*/
	alarm2				decimal(12,2)						null,			/*报警值2*/

	lsttw_money		decimal(12,6)						null,			/*上次调尾金额*/
	nowtw_money		decimal(12,6)						null,			/*本次调尾金额*/

	buy_times			int											null,			/*购电次数*/
	
	cur_feeproj		smallint								null,			/*测量点1-当前费率号*/
	cur_feeproj1	smallint								null,			/*测量点2-当前费率号*/
	cur_feeproj2	smallint								null,			/*测量点3-当前费率号*/

	op_result			tinyint									null,			/*操作结果 0:未成功 1:写卡成功 2:远程设置成功 3:主站操作成功 4:暂时不处理 5:正在操作 6:失败*/	

	visible_flag	tinyint									null,			/*是否显示*/	
	
	sg186_ysdw		varchar(20)							null,			/*186应收单位标识*/	
	up186_flag		tinyint									null,			/*186上传标志*/
	checkpay_flag	tinyint									null			/*186上传成功标志*/
)
grant select on syszyfrcd2008 to public
create clustered index syszyfrcd2008index1
	on syszyfrcd2008(rtu_id,zjg_id,op_date,op_time)	
end
go
-- 标记结束


--	'-------Create table 负荷控制记录表(ZFk2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZFk2008')
begin
create table ZFk2008
(
	rtu_id				int						  		not null,
	zjg_id				smallint  					not null,
	cons_id				smallint						not null,			/*客户编号*/
		
	op_date				int									not null,
	op_time				int									not null,
	
	op_man				char(64)								null,			/*操作员*/	
	op_type				tinyint									null,			/*控制类型*/
	
	shuttime			int											null,			/*断电时间长度*/	

	op_result			tinyint				default 0 null,			/*操作结果*/
	
	reserve1		  int											null,
	reserve2		  int											null		
)
grant select on ZFk2008 to public
create unique clustered index ZFk2008index1
	on ZFk2008(rtu_id, zjg_id, op_date, op_time)
end
go
-- 标记结束


--	'-------Create table (syszcontrol2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'syszcontrol2008')
begin
create table syszcontrol2008
(
	rtu_id				int						  		not null,
	zjg_id				smallint  					not null,
	cons_id				smallint						not null,			/*客户编号*/

	op_date				int									not null,
	op_time				int									not null,

	op_man				char(64)								null,			/*操作员*/	
	op_type				tinyint									null,			/*控制类型*/

	shuttime			int											null,			/*断电时间长度*/	

	op_result			tinyint				default 0 null,			/*操作结果*/

	reserve1		  int											null,
	reserve2		  int											null		
)
grant select on syszcontrol2008 to public
create unique clustered index syszcontrol2008index1
	on syszcontrol2008(rtu_id,zjg_id, op_date, op_time)
end
go
-- 标记结束



--	'-------Create table 费率更改记录表(ZFeeRateChg2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZFeeRateChg2008')
begin
create table ZFeeRateChg2008
(
	rtu_id				int						  		not null,
	zjg_id				smallint  					not null,
	cons_id				smallint						not null,					/*客户编号*/

	op_date				int									not null,
	op_time				int									not null,
	
	op_man				char(64)								null,					/*操作员*/	

	/*旧费率*/
	old_id				smallint        				null,					/*编号*/
	old_describe	varchar(64)     				null,					/*名称*/
	
	/*add*/
	old_fee_type	tinyint									null,					/*费率*/ /*1单费率 2复费率 3混合费率 4阶梯*/
	
	old_areafee_f	tinyint				default 0 null,					/*时区电价标志 20141006*/
	old_rate_ext	decimal(12,6)			  		null,					/*附加电价*/

	/*单费率*/
	old_rated_z		decimal(12,6)			  		null,					/*总费率*/
	old_rated2_z	decimal(12,6)			  		null,					/*总费率 枯*/
	old_rated3_z	decimal(12,6)			  		null,					/*总费率 丰*/

	/*复费率*/
	old_ratef_j		decimal(12,6)   				null,					/*尖费率*/
	old_ratef_f		decimal(12,6)			  		null,					/*峰费率*/	
	old_ratef_p		decimal(12,6)  					null,					/*平费率*/
	old_ratef_g		decimal(12,6)   				null,					/*谷费率*/

	old_ratef2_j	decimal(12,6)   				null,					/*尖费率*/
	old_ratef2_f	decimal(12,6)			  		null,					/*峰费率*/	
	old_ratef2_p	decimal(12,6)  					null,					/*平费率*/
	old_ratef2_g	decimal(12,6)   				null,					/*谷费率*/
	
	old_ratef3_j	decimal(12,6)   				null,					/*尖费率*/
	old_ratef3_f	decimal(12,6)			  		null,					/*峰费率*/	
	old_ratef3_p	decimal(12,6)  					null,					/*平费率*/
	old_ratef3_g	decimal(12,6)   				null,					/*谷费率*/	
	
	/*混合费率*/
	old_rateh_1		decimal(12,6)   				null,					/*混合费率1*/
	old_rateh_2		decimal(12,6)			  		null,					/*混合费率2*/	
	old_rateh_3		decimal(12,6)  					null,					/*混合费率3*/
	old_rateh_4		decimal(12,6)   				null,					/*混合费率4*/	
	
	old_rateh_bl1	tinyint									null,					/*混合比例1*/	
	old_rateh_bl2	tinyint									null,					/*混合比例2*/		
	old_rateh_bl3	tinyint									null,					/*混合比例3*/		
	old_rateh_bl4	tinyint									null,					/*混合比例4*/		
	
	/*阶梯电价*/
	/*阶梯电价*/
	old_ratej_type 			tinyint									null,					/*阶梯电价类型 	0 年度方案, 1月度方案	2月度峰谷/阶梯混合*/
	old_ratej_num 			tinyint									null,					/*阶梯电价数*/
	old_meterfee_type		tinyint									null,					/*电表费率类型  1单费率 2复费率*/
	old_meterfee_r			decimal(12,6)   				null,					/*电表执行电价*/
	
	old_ratej_r1				decimal(12,6)   				null,					/*阶梯费率1*/
	old_ratej_r2				decimal(12,6)			  		null,					/*阶梯费率2*/	
	old_ratej_r3				decimal(12,6)  					null,					/*阶梯费率3*/
	old_ratej_r4				decimal(12,6)   				null,					/*阶梯费率4*/

	old_ratej_td1				decimal(12,6)						null,					/*阶梯梯度值1*/
	old_ratej_td2				decimal(12,6)						null,					/*阶梯梯度值2*/
	old_ratej_td3				decimal(12,6)						null,					/*阶梯梯度值3*/

/*混合阶梯电价*/
	old_ratehj_type 		tinyint									null,					/*阶梯电价类型*/ 	/*0 年度方案, 1月度方案*/
	old_ratehj_num			tinyint									null,					/*阶梯电价数*/
	
	old_meterfeehj_type	tinyint									null,					/*电表费率类型  0单费率 1复费率 20120704*/
	old_meterfeehj_r		decimal(12,6)   				null,					/*电表执行电价*/
	
	old_ratehj_hr1_r1		decimal(12,6)   				null,					/*第1比例电价-阶梯费率1*/
	old_ratehj_hr1_r2		decimal(12,6)						null,					/*第1比例电价-阶梯费率2*/
	old_ratehj_hr1_r3		decimal(12,6)  					null,					/*第1比例电价-阶梯费率3*/
	old_ratehj_hr1_r4		decimal(12,6)   				null,					/*第1比例电价-阶梯费率4*/
		
	old_ratehj_hr1_td1	decimal(12,6)						null,					/*第1比例电价-阶梯梯度值1*/
	old_ratehj_hr1_td2	decimal(12,6)						null,					/*第1比例电价-阶梯梯度值2*/
	old_ratehj_hr1_td3	decimal(12,6)						null,					/*第1比例电价-阶梯梯度值3*/
	
	old_ratehj_hr2			decimal(12,6)   				null,					/*第2比例电价*/
	old_ratehj_hr3			decimal(12,6)   				null,					/*第3比例电价*/
	
	old_ratehj_bl1			tinyint									null,					/*混合比例1*/	
	old_ratehj_bl2			tinyint									null,					/*混合比例2*/	
	old_ratehj_bl3			tinyint									null,					/*混合比例3*/	
	
	/*新费率*/
	new_id							smallint        				null,					/*编号*/
	new_describe				varchar(64)     				null,					/*名称*/
	
	/*add*/
	new_fee_type				tinyint									null,					/*费率*/ /*1单费率 2复费率 3混合费率 4阶梯*/

	new_areafee_f				tinyint				default 0 null,					/*时区电价标志 20141006*/
	new_rate_ext				decimal(12,6)				  	null,					/*附加电价*/
	
	/*单费率*/
	new_rated_z					decimal(12,6)				  	null,					/*总费率*/
	new_rated2_z				decimal(12,6)				  	null,					/*总费率*/
	new_rated3_z				decimal(12,6)			  		null,					/*总费率*/
	
	/*复费率*/
	new_ratef_j					decimal(12,6)		   			null,					/*尖费率*/
	new_ratef_f					decimal(12,6)				  	null,					/*峰费率*/	
	new_ratef_p					decimal(12,6)	  				null,					/*平费率*/
	new_ratef_g					decimal(12,6)		   			null,					/*谷费率*/
	
	new_ratef2_j				decimal(12,6)   				null,					/*尖费率*/
	new_ratef2_f				decimal(12,6)				  	null,					/*峰费率*/	
	new_ratef2_p				decimal(12,6)  					null,					/*平费率*/
	new_ratef2_g				decimal(12,6)   				null,					/*谷费率*/
	
	new_ratef3_j				decimal(12,6)		   			null,					/*尖费率*/
	new_ratef3_f				decimal(12,6)				  	null,					/*峰费率*/	
	new_ratef3_p				decimal(12,6)  					null,					/*平费率*/
	new_ratef3_g				decimal(12,6)   				null,					/*谷费率*/
	
	/*混合费率*/
	new_rateh_1					decimal(12,6)   				null,					/*混合费率1*/
	new_rateh_2					decimal(12,6)			  		null,					/*混合费率2*/	
	new_rateh_3					decimal(12,6)  					null,					/*混合费率3*/
	new_rateh_4					decimal(12,6)   				null,					/*混合费率4*/	
	
	new_rateh_bl1				tinyint									null,					/*混合比例1*/	
	new_rateh_bl2				tinyint									null,					/*混合比例2*/		
	new_rateh_bl3				tinyint									null,					/*混合比例3*/		
	new_rateh_bl4				tinyint									null,					/*混合比例4*/		
	
	/*阶梯电价*/
	new_ratej_type 			tinyint									null,					/*阶梯电价类型 	0 年度方案, 1月度方案	2月度峰谷/阶梯混合*/
	new_ratej_num 			tinyint									null,					/*阶梯电价数*/	
	new_meterfee_type		tinyint									null,					/*电表费率类型  1单费率 2复费率*/
	new_meterfee_r			decimal(12,6)   				null,					/*电表执行电价*/
		
	new_ratej_r1				decimal(12,6)   				null,					/*阶梯费率1*/
	new_ratej_r2				decimal(12,6)			  		null,					/*阶梯费率2*/	
	new_ratej_r3				decimal(12,6)  					null,					/*阶梯费率3*/
	new_ratej_r4				decimal(12,6)   				null,					/*阶梯费率4*/

	new_ratej_td1				decimal(12,6)						null,					/*阶梯梯度值1*/
	new_ratej_td2				decimal(12,6)						null,					/*阶梯梯度值2*/
	new_ratej_td3				decimal(12,6)						null,					/*阶梯梯度值3*/

	/*混合阶梯电价*/
	new_ratehj_type 		tinyint									null,					/*阶梯电价类型*/ 	/*0 年度方案, 1月度方案*/
	new_ratehj_num			tinyint									null,					/*阶梯电价数*/
	
	new_meterfeehj_type	tinyint									null,					/*电表费率类型  0单费率 1复费率 20120704*/
	new_meterfeehj_r		decimal(12,6)   				null,					/*电表执行电价*/
	
	new_ratehj_hr1_r1		decimal(12,6)   				null,					/*第1比例电价-阶梯费率1*/
	new_ratehj_hr1_r2		decimal(12,6)						null,					/*第1比例电价-阶梯费率2*/
	new_ratehj_hr1_r3		decimal(12,6)  					null,					/*第1比例电价-阶梯费率3*/
	new_ratehj_hr1_r4		decimal(12,6)   				null,					/*第1比例电价-阶梯费率4*/
		
	new_ratehj_hr1_td1	decimal(12,6)						null,					/*第1比例电价-阶梯梯度值1*/
	new_ratehj_hr1_td2	decimal(12,6)						null,					/*第1比例电价-阶梯梯度值2*/
	new_ratehj_hr1_td3	decimal(12,6)						null,					/*第1比例电价-阶梯梯度值3*/
	
	new_ratehj_hr2			decimal(12,6)   				null,					/*第2比例电价*/
	new_ratehj_hr3			decimal(12,6)   				null,					/*第3比例电价*/
	
	new_ratehj_bl1			tinyint									null,					/*混合比例1*/	
	new_ratehj_bl2			tinyint									null,					/*混合比例2*/	
	new_ratehj_bl3			tinyint									null,					/*混合比例3*/
	
	reserve1		  			int											null,
	reserve2		  			int											null		
)
grant select on ZFeeRateChg2008 to public
create unique clustered index ZFeeRateChg2008index1
	on ZFeeRateChg2008(rtu_id, zjg_id, op_date, op_time)
end
go
-- 标记结束

--	'-------Create table 附加费更改记录表(ZPayAddChg2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZPayAddChg2008')
begin
create table ZPayAddChg2008
(
	rtu_id				int						  		not null,
	zjg_id				smallint  					not null,
	cons_id				smallint						not null,					/*客户编号*/

	op_date				int									not null,
	op_time				int									not null,
	
	op_man				char(64)								null,					/*操作员*/	

	old_payadd1		decimal(12,6)   				null,					/*旧附加费1*/
	old_payadd2		decimal(12,6)   				null,					/*旧附加费2*/
	old_payadd3		decimal(12,6)   				null,					/*旧附加费3*/
			
	new_payadd1		decimal(12,6)   				null,					/*新附加费*/
	new_payadd2		decimal(12,6)   				null,					/*新附加费*/
	new_payadd3		decimal(12,6)   				null,					/*新附加费*/		
	
	reserve1		  int											null,
	reserve2		  int											null		
)
grant select on ZPayAddChg2008 to public
create unique clustered index ZPayAddChg2008index1
	on ZPayAddChg2008(rtu_id, zjg_id, op_date, op_time)
end
go
-- 标记结束

--	'-------Create table 测量点预付费记录表(JYff2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JYff2008')
begin
create table JYff2008
(
	rtu_id				int						  		not null,
	mp_id					smallint  					not null,	
	
	res_id				smallint						not null,			/*客户编号*/
	res_desc			char(64)								null,			/*客户名称*/

	op_man				char(64)						not null,			/*操作员*/	
	op_type				tinyint							not null,			/*本次操作类型 0 初始态, 1 开户, 2 缴费, 3 补卡, 4 补写卡, 5 冲正, 6 换表, 7 换费率 10 销户 */	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/

	pay_type			tinyint									null,			/*缴费方式*/

	wasteno				varchar(32)							null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/	

	pay_money			decimal(12,2)						null,			/*缴费金额*/	
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)						null,			/*总金额*/

	--add 201406
	buy_dl				decimal(12,2)						null,			/*购电量*/
	pay_bmc				decimal(12,2)						null,			/*表码差*//*又名写卡电量*/
	--end
	
	shutdown_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额*/
	--20130108
	shutdown_val2	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 本地模拟电表 */	
	--20130108

	jsbd_zyz			decimal(12,2)						null,			/*测量点1-结算总表底*/
	jsbd_zyj			decimal(12,2)						null,			/*测量点1-结算尖表底*/	
	jsbd_zyf			decimal(12,2)						null,			/*测量点1-结算峰表底*/	
	jsbd_zyp			decimal(12,2)						null,			/*测量点1-结算平表底*/	
	jsbd_zyg			decimal(12,2)						null,			/*测量点1-结算谷表底*/

	jsbd1_zyz			decimal(12,2)						null,			/*测量点2-结算总表底*/
	jsbd1_zyj			decimal(12,2)						null,			/*测量点2-结算尖表底*/	
	jsbd1_zyf			decimal(12,2)						null,			/*测量点2-结算峰表底*/	
	jsbd1_zyp			decimal(12,2)						null,			/*测量点2-结算平表底*/	
	jsbd1_zyg			decimal(12,2)						null,			/*测量点2-结算谷表底*/

	jsbd2_zyz			decimal(12,2)						null,			/*测量点3-结算总表底*/
	jsbd2_zyj			decimal(12,2)						null,			/*测量点3-结算尖表底*/	
	jsbd2_zyf			decimal(12,2)						null,			/*测量点3-结算峰表底*/	
	jsbd2_zyp			decimal(12,2)						null,			/*测量点3-结算平表底*/	
	jsbd2_zyg			decimal(12,2)						null,			/*测量点3-结算谷表底*/

	jsbd_ymd			int											null,			/*结算时间*/

	--20130108
  calc_bdymd		int										 	null,			/*算费表底时间-YYYYMMDD*/              		                  
	calc_zyz			decimal(12,2)		  			null,			/*算费时总表底*/
	calc_zyj			decimal(12,2)		  			null,			/*算费时尖表底*/
	calc_zyf			decimal(12,2)		  			null,			/*算费时峰表底*/
	calc_zyp			decimal(12,2)		  			null,			/*算费时平表底*/
	calc_zyg			decimal(12,2)		  			null,			/*算费时谷表底*/

	calc1_zyz			decimal(12,2)		  			null,			/*动力关联1-算费时总表底*/
	calc1_zyj			decimal(12,2)		  			null,			/*动力关联1-算费时尖表底*/
	calc1_zyf			decimal(12,2)		  			null,			/*动力关联1-算费时峰表底*/
	calc1_zyp			decimal(12,2)		  			null,			/*动力关联1-算费时平表底*/
	calc1_zyg			decimal(12,2)		  			null,			/*动力关联1-算费时谷表底*/

	calc2_zyz			decimal(12,2)		  			null,			/*动力关联2-算费时总表底*/
	calc2_zyj			decimal(12,2)		  			null,			/*动力关联2-算费时尖表底*/
	calc2_zyf			decimal(12,2)		  			null,			/*动力关联2-算费时峰表底*/
	calc2_zyp			decimal(12,2)		  			null,			/*动力关联2-算费时平表底*/
	calc2_zyg			decimal(12,2)		  			null,			/*动力关联2-算费时谷表底*/

	now_remain		decimal(12,2)						null,			/*当前剩余*/
	now_remain2		decimal(12,2)						null,			/*当前剩余 本地模拟电表*/

	total_gdz			decimal(12,2)						null,			/*累计购电值*/

	jt_total_zbdl	decimal(12,2)						null,			/*阶梯追补累计用电量*/
	jt_total_dl		decimal(12,2)						null,			/*阶梯累计用电量*/
	--20130108

	alarm1				decimal(12,2)						null,			/*报警值1*/		
	alarm2				decimal(12,2)						null,			/*报警值2*/			

	buy_times			int											null,			/*购电次数*/		

	cur_feeproj		smallint								null,			/*当前费率号*/

	op_result			tinyint									null,			/*操作结果 0:未成功 1:写卡成功 2:远程设置成功 3:主站操作成功 4:暂时不处理 5:正在操作 6:失败*/

	visible_flag	tinyint									null,			/*是否显示*/	

	sg186_ysdw		varchar(20)							null,			/*186应收单位标识*/	
	up186_flag		tinyint									null,			/*186上传成功标志*/
	checkpay_flag	tinyint									null			/*对账成功标志*/
)
grant select on JYff2008 to public
create clustered index JYff2008index1
	on JYff2008(rtu_id,mp_id,op_date,op_time,op_type)	
create index JYff2008index2 
	on JYff2008(rtu_id,res_id,op_date,op_time,op_type)  
end
go
-- 标记结束


--	'-------Create table 测量点预付费记录表备份(sysjyfrcd2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'sysjyfrcd2008')
begin
create table sysjyfrcd2008
(
	rtu_id				int						  		not null,
	mp_id					smallint  					not null,	
	
	res_id				smallint						not null,			/*客户编号*/
	res_desc			char(64)								null,			/*客户名称*/

	op_man				char(64)						not null,			/*操作员*/	
	op_type				tinyint							not null,			/*本次操作类型 0 初始态, 1 开户, 2 缴费, 3 补卡, 4 补写卡, 5 冲正, 6 换表, 7 换费率 10 销户 */	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/

	pay_type			tinyint									null,			/*缴费方式*/

	wasteno				varchar(32)							null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/	

	pay_money			decimal(12,2)						null,			/*缴费金额*/	
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)						null,			/*总金额*/

	--add 201406
	buy_dl				decimal(12,2)						null,			/*购电量*/
	pay_bmc				decimal(12,2)						null,			/*表码差*//*又名写卡电量*/
	--end

	shutdown_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额*/
	--20130108
	shutdown_val2	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 本地模拟电表 */	
	--20130108

	jsbd_zyz			decimal(12,2)						null,			/*测量点1-结算总表底*/
	jsbd_zyj			decimal(12,2)						null,			/*测量点1-结算尖表底*/	
	jsbd_zyf			decimal(12,2)						null,			/*测量点1-结算峰表底*/	
	jsbd_zyp			decimal(12,2)						null,			/*测量点1-结算平表底*/	
	jsbd_zyg			decimal(12,2)						null,			/*测量点1-结算谷表底*/

	jsbd1_zyz			decimal(12,2)						null,			/*测量点2-结算总表底*/
	jsbd1_zyj			decimal(12,2)						null,			/*测量点2-结算尖表底*/	
	jsbd1_zyf			decimal(12,2)						null,			/*测量点2-结算峰表底*/	
	jsbd1_zyp			decimal(12,2)						null,			/*测量点2-结算平表底*/	
	jsbd1_zyg			decimal(12,2)						null,			/*测量点2-结算谷表底*/

	jsbd2_zyz			decimal(12,2)						null,			/*测量点3-结算总表底*/
	jsbd2_zyj			decimal(12,2)						null,			/*测量点3-结算尖表底*/	
	jsbd2_zyf			decimal(12,2)						null,			/*测量点3-结算峰表底*/	
	jsbd2_zyp			decimal(12,2)						null,			/*测量点3-结算平表底*/	
	jsbd2_zyg			decimal(12,2)						null,			/*测量点3-结算谷表底*/

	jsbd_ymd			int											null,			/*结算时间*/

	--20130108
  calc_bdymd		int										 	null,			/*算费表底时间-YYYYMMDD*/              		                  
	calc_zyz			decimal(12,2)		  			null,			/*算费时总表底*/
	calc_zyj			decimal(12,2)		  			null,			/*算费时尖表底*/
	calc_zyf			decimal(12,2)		  			null,			/*算费时峰表底*/
	calc_zyp			decimal(12,2)		  			null,			/*算费时平表底*/
	calc_zyg			decimal(12,2)		  			null,			/*算费时谷表底*/

	calc1_zyz			decimal(12,2)		  			null,			/*动力关联1-算费时总表底*/
	calc1_zyj			decimal(12,2)		  			null,			/*动力关联1-算费时尖表底*/
	calc1_zyf			decimal(12,2)		  			null,			/*动力关联1-算费时峰表底*/
	calc1_zyp			decimal(12,2)		  			null,			/*动力关联1-算费时平表底*/
	calc1_zyg			decimal(12,2)		  			null,			/*动力关联1-算费时谷表底*/

	calc2_zyz			decimal(12,2)		  			null,			/*动力关联2-算费时总表底*/
	calc2_zyj			decimal(12,2)		  			null,			/*动力关联2-算费时尖表底*/
	calc2_zyf			decimal(12,2)		  			null,			/*动力关联2-算费时峰表底*/
	calc2_zyp			decimal(12,2)		  			null,			/*动力关联2-算费时平表底*/
	calc2_zyg			decimal(12,2)		  			null,			/*动力关联2-算费时谷表底*/

	now_remain		decimal(12,2)						null,			/*当前剩余*/
	now_remain2		decimal(12,2)						null,			/*当前剩余 本地模拟电表*/

	total_gdz			decimal(12,2)						null,			/*累计购电值*/

	jt_total_zbdl	decimal(12,2)						null,			/*阶梯追补累计用电量*/
	jt_total_dl		decimal(12,2)						null,			/*阶梯累计用电量*/
	--20130108

	alarm1				decimal(12,2)						null,			/*报警值1*/		
	alarm2				decimal(12,2)						null,			/*报警值2*/			

	buy_times			int											null,			/*购电次数*/		

	cur_feeproj		smallint								null,			/*当前费率号*/

	op_result			tinyint									null,			/*操作结果 0:未成功 1:写卡成功 2:远程设置成功 3:主站操作成功 4:暂时不处理 5:正在操作 6:失败*/

	visible_flag	tinyint									null,			/*是否显示*/	

	sg186_ysdw		varchar(20)							null,			/*186应收单位标识*/	
	up186_flag		tinyint									null,			/*186上传成功标志*/
	checkpay_flag	tinyint									null			/*对账成功标志*/
)
grant select on sysjyfrcd2008 to public
create clustered index sysjyfrcd2008index1
	on sysjyfrcd2008(rtu_id,mp_id,op_date,op_time,op_type)	
create index sysjyfrcd2008index2 
	on sysjyfrcd2008(rtu_id,res_id,op_date,op_time,op_type)  
end
go
-- 标记结束


--	'-------Create table 负荷控制记录表(JFk2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JFk2008')
begin
create table JFk2008
(
	rtu_id				int						  		not null,
	mp_id					smallint  					not null,
	res_id				smallint						not null,			/*客户编号*/
		
	op_date				int									not null,
	op_time				int									not null,
	
	op_man				char(64)								null,			/*操作员*/	
	op_type				tinyint									null,			/*控制类型*/
	
	shuttime			int											null,			/*断电时间长度*/	

	op_result			tinyint				default 0 null,			/*操作结果*/
	
	reserve1		  int											null,
	reserve2		  int											null
)
grant select on JFk2008 to public
create unique clustered index JFk2008index1
	on JFk2008(rtu_id, mp_id, op_date, op_time)
create index JFk2008index2 
	on JFk2008(rtu_id,res_id,op_date,op_time)  
end
go
-- 标记结束



--	'-------Create table (sysjcontrol2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'sysjcontrol2008')
begin
create table sysjcontrol2008
(
	rtu_id				int						  		not null,
	mp_id					smallint  					not null,
	res_id				smallint						not null,			/*客户编号*/
		
	op_date				int									not null,
	op_time				int									not null,
	
	op_man				char(64)								null,			/*操作员*/	
	op_type				tinyint									null,			/*控制类型*/
	
	shuttime			int											null,			/*断电时间长度*/	

	op_result			tinyint				default 0 null,			/*操作结果*/
	
	reserve1		  int											null,
	reserve2		  int											null
)
grant select on sysjcontrol2008 to public
create unique clustered index sysjcontrol2008index1
	on sysjcontrol2008(rtu_id,mp_id, op_date, op_time)
create index sysjcontrolindex2 
	on sysjcontrol2008(rtu_id,res_id,op_date,op_time)  	
end
go
-- 标记结束



--	'-------Create table 费率更改记录表(JFeeRateChg2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JFeeRateChg2008')
begin
create table JFeeRateChg2008
(
	rtu_id						int						  		not null,
	mp_id							smallint  					not null,
	res_id						smallint						not null,					/*客户编号*/

	op_date						int									not null,
	op_time						int									not null,
	
	op_man						char(64)								null,					/*操作员*/	

	/*旧费率*/
	old_id						smallint        				null,					/*编号*/
	old_describe			varchar(64)     				null,					/*名称*/
	
	/*add*/
	old_fee_type			tinyint									null,					/*费率*/ /*1单费率 2复费率 3混合费率 4阶梯*/

	old_areafee_f			tinyint			default 0 	null,					/*时区电价标志 20141006*/
	old_rate_ext			decimal(12,6)	  				null,					/*附加电价*/
	
	/*单费率*/
	old_rated_z				decimal(12,6)			  		null,					/*总费率*/
	old_rated2_z			decimal(12,6)			  		null,					/*总费率 枯*/
	old_rated3_z			decimal(12,6)			  		null,					/*总费率 丰*/

	/*复费率*/
	old_ratef_j				decimal(12,6)   				null,					/*尖费率*/
	old_ratef_f				decimal(12,6)			  		null,					/*峰费率*/
	old_ratef_p				decimal(12,6)  					null,					/*平费率*/
	old_ratef_g				decimal(12,6)   				null,					/*谷费率*/

	old_ratef2_j			decimal(12,6)   				null,					/*尖费率*/
	old_ratef2_f			decimal(12,6)			  		null,					/*峰费率*/
	old_ratef2_p			decimal(12,6)  					null,					/*平费率*/
	old_ratef2_g			decimal(12,6)   				null,					/*谷费率*/
	
	old_ratef3_j			decimal(12,6)   				null,					/*尖费率*/
	old_ratef3_f			decimal(12,6)			  		null,					/*峰费率*/
	old_ratef3_p			decimal(12,6)  					null,					/*平费率*/
	old_ratef3_g			decimal(12,6)   				null,					/*谷费率*/
	
	/*混合费率*/
	old_rateh_1				decimal(12,6)   				null,					/*混合费率1*/
	old_rateh_2				decimal(12,6)			  		null,					/*混合费率2*/	
	old_rateh_3				decimal(12,6)  					null,					/*混合费率3*/
	old_rateh_4				decimal(12,6)   				null,					/*混合费率4*/	
	
	old_rateh_bl1			tinyint									null,					/*混合比例1*/	
	old_rateh_bl2			tinyint									null,					/*混合比例2*/		
	old_rateh_bl3			tinyint									null,					/*混合比例3*/		
	old_rateh_bl4			tinyint									null,					/*混合比例4*/		
	
	/*阶梯电价*/
	old_ratej_type 		tinyint									null,					/*阶梯电价类型 	0 年度方案, 1月度方案	2月度峰谷/阶梯混合*/
	old_ratej_num 		tinyint									null,					/*阶梯电价数*/
	old_meterfee_type	tinyint									null,					/*电表费率类型  1单费率 2复费率*/
	old_meterfee_r		decimal(12,6)   				null,					/*电表执行电价*/
		
	old_ratej_r1			decimal(12,6)   				null,					/*阶梯费率1*/
	old_ratej_r2			decimal(12,6)			  		null,					/*阶梯费率2*/	
	old_ratej_r3			decimal(12,6)  					null,					/*阶梯费率3*/
	old_ratej_r4			decimal(12,6)   				null,					/*阶梯费率4*/
		
	old_ratej_td1			decimal(12,6)						null,					/*阶梯梯度值1*/
	old_ratej_td2			decimal(12,6)						null,					/*阶梯梯度值2*/
	old_ratej_td3			decimal(12,6)						null,					/*阶梯梯度值3*/

	/*混合阶梯电价*/
	old_ratehj_type 		tinyint								null,					/*阶梯电价类型*/ 	/*0 年度方案, 1月度方案*/
	old_ratehj_num			tinyint								null,					/*阶梯电价数*/
	
	old_meterfeehj_type	tinyint								null,					/*电表费率类型  0单费率 1复费率 20120704*/
	old_meterfeehj_r		decimal(12,6)   			null,					/*电表执行电价*/
	
	old_ratehj_hr1_r1		decimal(12,6)   			null,					/*第1比例电价-阶梯费率1*/
	old_ratehj_hr1_r2		decimal(12,6)					null,					/*第1比例电价-阶梯费率2*/
	old_ratehj_hr1_r3		decimal(12,6)  				null,					/*第1比例电价-阶梯费率3*/
	old_ratehj_hr1_r4		decimal(12,6)   			null,					/*第1比例电价-阶梯费率4*/
		
	old_ratehj_hr1_td1	decimal(12,6)					null,					/*第1比例电价-阶梯梯度值1*/
	old_ratehj_hr1_td2	decimal(12,6)					null,					/*第1比例电价-阶梯梯度值2*/
	old_ratehj_hr1_td3	decimal(12,6)					null,					/*第1比例电价-阶梯梯度值3*/
	
	old_ratehj_hr2			decimal(12,6)   			null,					/*第2比例电价*/
	old_ratehj_hr3			decimal(12,6)   			null,					/*第3比例电价*/
	
	old_ratehj_bl1			tinyint								null,					/*混合比例1*/	
	old_ratehj_bl2			tinyint								null,					/*混合比例2*/	
	old_ratehj_bl3			tinyint								null,					/*混合比例3*/	

	/*新费率*/
	new_id							smallint        			null,					/*编号*/
	new_describe				varchar(64)     			null,					/*名称*/
	
	/*add*/
	new_fee_type				tinyint								null,					/*费率*/ /*1单费率 2复费率 3混合费率 4阶梯*/
	
	new_areafee_f				tinyint			default 0 null,					/*时区电价标志 20141006*/
	new_rate_ext				decimal(12,6)			  	null,					/*附加电价*/
	
	/*单费率*/
	new_rated_z					decimal(12,6)			  	null,					/*总费率*/
	new_rated2_z				decimal(12,6)			  	null,					/*总费率*/
	new_rated3_z				decimal(12,6)			  	null,					/*总费率*/
	
	/*复费率*/
	new_ratef_j					decimal(12,6)   			null,					/*尖费率*/
	new_ratef_f					decimal(12,6)			  	null,					/*峰费率*/	
	new_ratef_p					decimal(12,6)  				null,					/*平费率*/
	new_ratef_g					decimal(12,6)   			null,					/*谷费率*/
	
	new_ratef2_j				decimal(12,6)   			null,					/*尖费率*/
	new_ratef2_f				decimal(12,6)			  	null,					/*峰费率*/	
	new_ratef2_p				decimal(12,6)  				null,					/*平费率*/
	new_ratef2_g				decimal(12,6)   			null,					/*谷费率*/
	
	new_ratef3_j				decimal(12,6)   			null,					/*尖费率*/
	new_ratef3_f				decimal(12,6)			  	null,					/*峰费率*/	
	new_ratef3_p				decimal(12,6)  				null,					/*平费率*/
	new_ratef3_g				decimal(12,6)   			null,					/*谷费率*/

	/*混合费率*/
	new_rateh_1					decimal(12,6)   			null,					/*混合费率1*/
	new_rateh_2					decimal(12,6)			  	null,					/*混合费率2*/	
	new_rateh_3					decimal(12,6)  				null,					/*混合费率3*/
	new_rateh_4					decimal(12,6)   			null,					/*混合费率4*/	
	              			
	new_rateh_bl1				tinyint								null,					/*混合比例1*/	
	new_rateh_bl2				tinyint								null,					/*混合比例2*/		
	new_rateh_bl3				tinyint								null,					/*混合比例3*/		
	new_rateh_bl4				tinyint								null,					/*混合比例4*/		
	
	/*阶梯电价*/
	new_ratej_type 			tinyint								null,					/*阶梯电价类型 	0 年度方案, 1月度方案	2月度峰谷/阶梯混合*/
	new_ratej_num 			tinyint								null,					/*阶梯电价数*/	
	new_meterfee_type		tinyint								null,					/*电表费率类型  1单费率 2复费率*/
	new_meterfee_r			decimal(12,6)   			null,					/*电表执行电价*/
	                                        	
	new_ratej_r1				decimal(12,6)   			null,					/*阶梯费率1*/
	new_ratej_r2				decimal(12,6)			  	null,					/*阶梯费率2*/	
	new_ratej_r3				decimal(12,6)  				null,					/*阶梯费率3*/
	new_ratej_r4				decimal(12,6)   			null,					/*阶梯费率4*/

	new_ratej_td1				decimal(12,6)					null,					/*阶梯梯度值1*/
	new_ratej_td2				decimal(12,6)					null,					/*阶梯梯度值2*/
	new_ratej_td3				decimal(12,6)					null,					/*阶梯梯度值3*/

	/*混合阶梯电价*/
	new_ratehj_type 		tinyint								null,					/*阶梯电价类型*/ 	/*0 年度方案, 1月度方案*/
	new_ratehj_num			tinyint								null,					/*阶梯电价数*/
	
	new_meterfeehj_type	tinyint								null,					/*电表费率类型  0单费率 1复费率 20120704*/
	new_meterfeehj_r		decimal(12,6)   			null,					/*电表执行电价*/
	
	new_ratehj_hr1_r1		decimal(12,6)   			null,					/*第1比例电价-阶梯费率1*/
	new_ratehj_hr1_r2		decimal(12,6)					null,					/*第1比例电价-阶梯费率2*/
	new_ratehj_hr1_r3		decimal(12,6)  				null,					/*第1比例电价-阶梯费率3*/
	new_ratehj_hr1_r4		decimal(12,6)   			null,					/*第1比例电价-阶梯费率4*/
		
	new_ratehj_hr1_td1	decimal(12,6)					null,					/*第1比例电价-阶梯梯度值1*/
	new_ratehj_hr1_td2	decimal(12,6)					null,					/*第1比例电价-阶梯梯度值2*/
	new_ratehj_hr1_td3	decimal(12,6)					null,					/*第1比例电价-阶梯梯度值3*/
	
	new_ratehj_hr2			decimal(12,6)   			null,					/*第2比例电价*/
	new_ratehj_hr3			decimal(12,6)   			null,					/*第3比例电价*/
	
	new_ratehj_bl1			tinyint								null,					/*混合比例1*/	
	new_ratehj_bl2			tinyint								null,					/*混合比例2*/	
	new_ratehj_bl3			tinyint								null,					/*混合比例3*/
	reserve1		 				int										null,
	reserve2		 				int										null		
)
grant select on JFeeRateChg2008 to public
create unique clustered index JFeeRateChg2008index1
	on JFeeRateChg2008(rtu_id, mp_id, op_date, op_time)
end
go
-- 标记结束

----------------------预付费结束------------------------------------------------



----------------------综合应用开始------------------------------------------------

--   '-------Create table 操作日志记录(operlog2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'operlog2008')
begin
create table operlog2008
(
	oper_id				smallint   			not null,	/*操作员编号*/
	date					int							not null,	/*日期*/	
	time					int							not null,	/*时间*/
	log_index			int							not null, /*index flag*/	
	opertype			tinyint							null,	/*操作类型 0 登录 1 退出*/
--ipAddr				varchar(32)					null,	/*ip地址*/
	oper_info  		varchar(256)    		null,	/*操作内容*/
	reserve1			int								  null,
	reserve2			decimal(12,3)   		null,
	reserve3			varchar(40)      		null
)
grant select on  operlog2008 to public
create unique clustered index operlog2008index1
	on operlog2008(oper_id,date,time,log_index)
end
go
-- 标记结束


--   '-------Create table 预付费操作日志记录(yffoperlog2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'yffoperlog2008')
begin
create table yffoperlog2008
(
	oper_id				smallint   			not null,	/*操作员编号*/
	date					int							not null,	/*日期*/	
	time					int							not null,	/*时间*/	
	log_index			int							not null, /*index flag*/	
	opertype			tinyint							null,	/*操作类型 0 登录 1 退出*/
--ipAddr				varchar(32)					null,	/*ip地址*/
	oper_info  		varchar(128)    		null,	/*操作内容*/
	reserve1			int								  null,
	reserve2			decimal(12,3)   		null,
	reserve3			varchar(40)      		null
)
grant select on  yffoperlog2008 to public
create unique clustered index yffoperlog2008index1
	on yffoperlog2008(oper_id,date,time,log_index)
end
go
-- 标记结束


--   '-------Create table 计算任务执行记录(accounttask2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'accounttask2008')
begin
create table accounttask2008
(
	account_id		smallint   			not null,	/*计算任务编号*/
	date					int							not null,	/*日期*/	
	time					int							not null,	/*时间*/	
	acctype				tinyint							null,	/*执行类型 0 自动 1 数据存储调用 2 手动*/
	start_time		int									null,	/*执行起始时间*/	
	end_time			int									null,	/*执行结束时间*/	
	exeresult			tinyint							null,	/*执行结果 0 成功 1 失败*/
)
grant select on  accounttask2008 to public
create unique clustered index accounttask2008index1
	on accounttask2008(account_id,date,time)
end
go
-- 标记结束






--   '-------Create table 电量汇总日数据表(dltotalday2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'dltotalday2008')
begin
create table dltotalday2008
(
	dltotal_id		smallint   			not null,
	date					int							not null,
	time					int							not null,
	
	dl_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总电量*/	
	dl_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总电量*/	
	dl_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总电量*/		
	dl_fw			decimal(14, 3) 	DEFAULT 0.0 	/*反向无功总电量*/	
)
grant select on  dltotalday2008 to public
create unique clustered index dltotalday2008index1
	on dltotalday2008(dltotal_id,date,time)
end
go
-- 标记结束


--   '-------Create table 线损及站内平衡日数据表(linebalanceday2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'linebalanceday2008')
begin
create table linebalanceday2008
(
	linebalance_id	smallint		 		not null,		/*编号*/	
	type						tinyint	 	 					null,		/*类型*/
	date						int							not null,
	time						int							not null,
	
	dl_zy_z			decimal(14, 3) 	DEFAULT 0.0 ,	/*总表正向有功总电量*/	
	dl_fy_z			decimal(14, 3) 	DEFAULT 0.0 ,	/*总表反向有功总电量*/	
	dl_zw_z			decimal(14, 3) 	DEFAULT 0.0 ,	/*总表正向无功总电量*/		
	dl_fw_z			decimal(14, 3) 	DEFAULT 0.0 ,	/*总表反向无功总电量*/	
	dl_zy_f			decimal(14, 3) 	DEFAULT 0.0 ,	/*分表正向有功总电量*/	
	dl_fy_f			decimal(14, 3) 	DEFAULT 0.0 ,	/*分表反向有功总电量*/	
	dl_zw_f			decimal(14, 3) 	DEFAULT 0.0 ,	/*分表正向无功总电量*/		
	dl_fw_f			decimal(14, 3) 	DEFAULT 0.0 ,	/*分表反向无功总电量*/	
	per_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功损耗比例*/	
	per_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功损耗比例*/	
	per_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功损耗比例*/		
	per_fw			decimal(14, 3) 	DEFAULT 0.0 	/*反向无功损耗比例*/		
)
grant select on  linebalanceday2008 to public
create unique clustered index linebalanceday2008index1
	on linebalanceday2008(linebalance_id,type,date,time)
end
go
-- 标记结束


--   '-------Create table 终端上线率日数据表(rtuonlineday2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'rtuonlineday2008')
begin
create table rtuonlineday2008
(
	obj_id				smallint   			not null,			/*对象编号*/	
	obj_type			tinyint					not null,			/*对象类型*/	
	date					int							not null,
	time					int							not null,
	
	rtu_count			int							null,					/*终端总数*/	
	online_count	int							null,					/*上线总数*/
	online_per		decimal(14, 3) 	DEFAULT 0.0   /*上线率*/	
)
grant select on  rtuonlineday2008 to public
create unique clustered index rtuonlineday2008index1
	on rtuonlineday2008(obj_id,obj_type,date,time)
end
go
-- 标记结束



--   '-------Create table 电表采集成功率日数据表(metercjday2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'metercjday2008')
begin
create table metercjday2008
(
	obj_id				smallint   			not null,			/*对象编号*/	
	obj_type			tinyint					not null,			/*对象类型*/	
	date					int							not null,
	time					int							not null,
	
	meter_count		int							null,					/*电表总数*/	
	success_count	int							null,					/*成功总数*/
	success_per		decimal(14, 3) 	DEFAULT 0.0   /*成功率*/	
)
grant select on  metercjday2008 to public
create unique clustered index metercjday2008index1
	on metercjday2008(obj_id,obj_type,date,time)
end
go
-- 标记结束



--   '-------Create table 线路负荷日数据表(lineloadday2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'lineloadday2008')
begin
create table lineloadday2008
(
	linebalance_id		smallint   			not null,				/*线损编号*/	
	date							int							not null,
	time							int							not null,
	
	load_z						decimal(14, 3) 	DEFAULT 0.0 ,		/*总表功率*/	
	load_f						decimal(14, 3) 	DEFAULT 0.0 ,		/*分表功率*/
	load_per					decimal(14, 3) 	DEFAULT 0.0   	/*负荷率*/
)
grant select on  lineloadday2008 to public
create unique clustered index lineloadday2008index1
	on lineloadday2008(linebalance_id,date,time)
end
go
-- 标记结束





--   '-------Create table SG186导库操作记录(sg186exp2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'sg186exp2008')
begin
create table sg186exp2008
(
	rtu_id				int   					not null,	/*终端编号*/
	mp_id					smallint				not null, /*测点编号*/
	exp_date			int							not	null,	/*导库操作日期 年月日*/
	exp_time			int							not	null, /*导库操作时间 时分秒*/
	d_date				int							 		null, /*数据日期*/
	d_time				int							 		null, /*数据时间*/
	g_date				int									null, /*导入SG186写入数据日期*/
	g_time				int									null, /*导入SG186写入数据时间*/
	data_item			int							not null, /*数据标识 有功表底 无功表底*/
	datavalue			decimal(14, 3)			null,	/*数据值*/
	cons_no				varchar(16)					null, /*户号*/
	mp_no					varchar(32)					null, /*计量点号*/
	made_no				varchar(32)					null, /*电表出厂编号*/
	org_no				varchar(32)					null, /*供电单位编号*/
	coll_obj_id		decimal(16)					null, /*采集对象标识*/
	auto_flag			tinyint					 		null, /*自动导库标志 0:自动	1:手动*/
	exp_flag			tinyint					 		null, /*导出成功标志 0:失败 1:成功*/
	opername			varchar(32)					null, /*操作员名称*/
	autotask_id		smallint						null, /*SG186表底数据导入参数编号 界面暂时隐藏*/
	exp_id				smallint						null, /*导出数据项方案编号 界面暂时隐藏*/
	reserve1			int								  null,
	reserve2			decimal(12,3)   		null,
	reserve3			varchar(40)      		null
)
grant select on  sg186exp2008 to public
create unique clustered index sg186exp2008index1
	on sg186exp2008(rtu_id,mp_id,exp_date,exp_time,data_item)
end
go
-- 标记结束


--   '-------Create table 短信转发记录表(gsmzf200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'gsmzf200808')
begin
create table gsmzf200808
(
	d_date				int									not null,
	d_time				int									not null,
	d_uuid				int									not null,	
	
	send_date			int											null,			/*最后发送日期*/
	send_time			int											null,			/*最后发送时间*/
	
  classno    		smallint      					null,
  typeno  			smallint      					null,	
	rtu_id				int											null,			/*终端ID*/
	
	procname			varchar(32)							null,			/*源进程名称*/
	srcaddr				varchar(16)							null,			/*已发送短信的源地址*/
	destaddr			varchar(16)							null,			/*已发送短信的目的地址*/
	content				varchar(255)						null,			/*已发送短信内容*/

	priority			tinyint									null,			/*优先级*/
	need_conf			tinyint									null,			/*需要确认标志*/

	retry_inter		smallint								null,			/*重试间隔*/
	retry_times		smallint								null,			/*重试次数*/
	
	result				tinyint									null,			/*发送结果 0:初始 1:已发送 2:成功 3:失败*/
	resp_stat			int                     null			/*短信回执结果*/
)
grant select on  gsmzf200808 to public
create unique clustered index gsmzf200808index1	on gsmzf200808(d_uuid)
create index gsmzf200808index2 on gsmzf200808(d_date,d_time)
end
go
-- 标记结束


--SG186银电接口
--   '-------Create table 居民算费系统缴费数据表(JYffMisPay2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JYffMisPay2008')
begin
create table JYffMisPay2008
(
	rtu_id			int			   			not null,
	mp_id				smallint   			not null,

	date				int							not null,
	time				int							not null,

	rever_flag	tinyint							null,				/*被冲正标志*/
	check_flag	tinyint					  	null,				/*对账标志*/
	error_no		int									null,				/*错误号*/

	ccbh				varchar(40)  	  		null,				/*出厂编号*/

	dsdwbh		  varchar(20)			not null,				/*代收单位编号*/
	czybh		  	varchar(20)			not null,				/*操作员编号*/
	jylsh		  	varchar(40)			not null,				/*交易流水号*/
	yhbh		  	varchar(20)			not null,				/*用户编号*/
	jylb				tinyint					not null,				/*交易类别*/
	jffs		  	varchar(8)			not null,				/*缴费方式*/
	jsfs		  	varchar(8)			not null,				/*结算方式*/
	jfje				decimal(14, 3)	not null,				/*缴费金额*/
	yhzwrq			int							not null,				/*银行账务日期*/
	dyfp				tinyint					not null,				/*是否打印发票*/
	jfbs				int							not null,				/*缴费笔数*/
	remark		  varchar(120)				null,				/*备注*/
	
	ysbs1				varchar(20)					null,				/*应收标识1*/
	ysbs2				varchar(20)					null,				/*应收标识2*/
	ysbs3				varchar(20)					null,				/*应收标识3*/
	ysbs4				varchar(20)					null,				/*应收标识4*/
	ysbs5				varchar(20)					null,				/*应收标识5*/
	ysbs6				varchar(20)					null,				/*应收标识6*/
	ysbs7				varchar(20)					null,				/*应收标识7*/
	ysbs8				varchar(20)					null,				/*应收标识8*/
	ysbs9				varchar(20)					null,				/*应收标识9*/
	ysbs10			varchar(20)					null,				/*应收标识10*/
	
	reserve1		varchar(20)					null,			
	reserve2		varchar(20)					null,				
	reserve3		varchar(20)					null				
)
grant select on  JYffMisPay2008 to public
create unique clustered index JYffMisPay2008index1
	on JYffMisPay2008(rtu_id,mp_id,date,time)
create unique index JYffMisPay2008index2 
	on JYffMisPay2008(jylsh)
end
go
-- 标记结束

--   '-------Create table 居民算费系统冲正数据表(JYffMisRever2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JYffMisRever2008')
begin
create table JYffMisRever2008
(
	rtu_id			int			   			not null,
	mp_id				smallint   			not null,

	date				int							not null,
	time				int							not null,
	
	error_no		int									null,				/*错误号*/	
	
	ccbh				varchar(40)  	  		null,				/*出厂编号*/
	
	dsdwbh		  varchar(20)			not null,				/*代收单位编号*/
	czybh		  	varchar(20)			not null,				/*操作员编号*/
	czjylsh  		varchar(40)			not null,				/*冲正交易流水号*/
	yjylsh			varchar(40)			not null,				/*原交易流水号*/
	yhzwrq			int							not	null,				/*银行账务日期*/

	reserve1		varchar(20)					null,			
	reserve2		varchar(20)					null,				
	reserve3		varchar(20)					null				
)
grant select on  JYffMisRever2008 to public
create unique clustered index JYffMisRever2008index1
	on JYffMisRever2008(rtu_id,mp_id,date,time)
create unique index JYffMisRever2008index2 
	on JYffMisRever2008(czjylsh)
end
go
-- 标记结束

--   '-------Create table 专变算费系统缴费数据表(ZYffMisPay2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZYffMisPay2008')
begin
create table ZYffMisPay2008
(
	rtu_id			int			   			not null,
	zjg_id			smallint   			not null,

	date				int							not null,
	time				int							not null,

	rever_flag	tinyint							null,				/*被冲正标志*/	
	check_flag	tinyint						  null,				/*对账标志*/
	error_no		int									null,				/*错误号*/	
	
	ccbh				varchar(40)  	  		null,				/*出厂编号*/
	
	dsdwbh		  varchar(20)			not null,				/*代收单位编号*/
	czybh		  	varchar(20)			not null,				/*操作员编号*/
	jylsh		  	varchar(40)			not null,				/*交易流水号*/
	yhbh		  	varchar(20)			not null,				/*用户编号*/
	jylb				tinyint					not null,				/*交易类别*/
	jffs		  	varchar(8)			not null,				/*缴费方式*/
	jsfs		  	varchar(8)			not null,				/*结算方式*/
	jfje				decimal(14, 3)	not null,				/*缴费金额*/
	yhzwrq			int							not null,				/*银行账务日期*/
	dyfp				tinyint					not null,				/*是否打印发票*/
	jfbs				int							not null,				/*缴费笔数*/
	remark		  varchar(120)				null,				/*备注*/
	
	ysbs1				varchar(20)					null,				/*应收标识1*/
	ysbs2				varchar(20)					null,				/*应收标识2*/
	ysbs3				varchar(20)					null,				/*应收标识3*/
	ysbs4				varchar(20)					null,				/*应收标识4*/
	ysbs5				varchar(20)					null,				/*应收标识5*/
	ysbs6				varchar(20)					null,				/*应收标识6*/
	ysbs7				varchar(20)					null,				/*应收标识7*/
	ysbs8				varchar(20)					null,				/*应收标识8*/
	ysbs9				varchar(20)					null,				/*应收标识9*/
	ysbs10			varchar(20)					null,				/*应收标识10*/
	
	reserve1		varchar(20)					null,			
	reserve2		varchar(20)					null,				
	reserve3		varchar(20)					null				
)
grant select on  ZYffMisPay2008 to public
create unique clustered index ZYffMisPay2008index1
	on ZYffMisPay2008(rtu_id,zjg_id,date,time)
create unique index ZYffMisPay2008index2 
	on ZYffMisPay2008(jylsh)
end
go
-- 标记结束

--   '-------Create table 专变算费系统冲正数据表(ZYffMisRever2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZYffMisRever2008')
begin
create table ZYffMisRever2008
(
	rtu_id			int			   			not null,
	zjg_id			smallint   			not null,
		
	date				int							not null,
	time				int							not null,
	
	error_no		int									null,				/*错误号*/	
	
	ccbh				varchar(40)  	  		null,				/*出厂编号*/
	
	dsdwbh		  varchar(20)			not null,				/*代收单位编号*/
	czybh		  	varchar(20)			not null,				/*操作员编号*/
	czjylsh  		varchar(40)			not null,				/*冲正交易流水号*/
	yjylsh			varchar(40)			not null,				/*原交易流水号*/
	yhzwrq			int							not	null,				/*银行账务日期*/

	reserve1		varchar(20)					null,			
	reserve2		varchar(20)					null,				
	reserve3		varchar(20)					null				
)
grant select on  ZYffMisRever2008 to public
create unique clustered index ZYffMisRever2008index1
	on ZYffMisRever2008(rtu_id,zjg_id,date,time)
create unique index ZYffMisRever2008index2 
	on ZYffMisRever2008(czjylsh)
end
go
-- 标记结束


--   '-------Create table 居民算费系统缴费数据表[新](JYffMisPayNew2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JYffMisPayNew2008')
begin
create table JYffMisPayNew2008
(
	rtu_id			int			   			not null,
	mp_id				smallint   			not null,

	date				int							not null,
	time				int							not null,

	rever_flag	tinyint							null,				/*被冲正标志*/
	check_flag	tinyint					  	null,				/*对账标志*/
	
	error_no		int									null,				/*错误号*/
	error_msg		varchar(60)  	  		null,				/*错误信息*/
	
	ccbh				varchar(40)  	  		null,				/*出厂编号*/

	dsdwbh		  varchar(20)			not null,				/*代收单位编号*/
	czybh		  	varchar(20)			not null,				/*操作员编号*/
	jylsh		  	varchar(40)			not null,				/*交易流水号*/
	dzpc				varchar(20)			not null,				/*对账批次*/
	
	yhbh		  	varchar(20)			not null,				/*用户编号*/
	jylb				tinyint					not null,				/*交易类别*/
	jffs		  	varchar(16)			not null,				/*缴费方式*/
	jsfs		  	varchar(16)			not null,				/*结算方式*/

	bcsj_zje		decimal(14, 3)	not null,				/*本次实缴总金额*/
	bcsj_qfje		decimal(14, 3)	not null,				/*本次实缴欠费金额*/
	bcsj_ysje		decimal(14, 3)	not null,				/*本次实缴预收金额*/

	yhzwrq			int							not null,				/*银行账务日期*/
	dyfp				tinyint					not null,				/*是否打印发票*/
	jfbs				int							not null,				/*缴费笔数*/
	
	reserve		  varchar(80)					null,				/*预留字段*/
	remark		  varchar(120)				null,				/*备注*/
	
	jfmx1				varchar(120)				null,				/*缴费明细1*/
	jfmx2				varchar(120)				null,				/*缴费明细2*/
	jfmx3				varchar(120)				null,				/*缴费明细3*/
	jfmx4				varchar(120)				null,				/*缴费明细4*/
	jfmx5				varchar(120)				null,				/*缴费明细5*/
	jfmx6				varchar(120)				null,				/*缴费明细6*/
	jfmx7				varchar(120)				null,				/*缴费明细7*/
	jfmx8				varchar(120)				null,				/*缴费明细8*/
	jfmx9				varchar(120)				null,				/*缴费明细9*/
	jfmx10			varchar(120)				null,				/*缴费明细10*/
	
	reserve1		varchar(20)					null,			
	reserve2		varchar(20)					null,				
	reserve3		varchar(20)					null				
)
grant select on  JYffMisPayNew2008 to public
create unique clustered index JYffMisPayNew2008index1
	on JYffMisPayNew2008(rtu_id,mp_id,date,time)
create unique index JYffMisPayNew2008index2 
	on JYffMisPayNew2008(jylsh)
end
go
-- 标记结束

--   '-------Create table 居民算费系统冲正数据表[新](JYffMisReverNew2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JYffMisReverNew2008')
begin
create table JYffMisReverNew2008
(
	rtu_id			int			   			not null,
	mp_id				smallint   			not null,

	date				int							not null,
	time				int							not null,
	
	error_no		int									null,				/*错误号*/	
	error_msg		varchar(60)  	  		null,				/*错误信息*/
	
	ccbh				varchar(40)  	  		null,				/*出厂编号*/
	
	dsdwbh		  varchar(20)			not null,				/*代收单位编号*/
	czybh		  	varchar(20)			not null,				/*操作员编号*/
	czjylsh  		varchar(40)			not null,				/*冲正交易流水号*/
	dzpc				varchar(20)			not null,				/*对账批次*/
	yjylsh			varchar(40)			not null,				/*原交易流水号*/
	yhzwrq			int							not	null,				/*银行账务日期*/

	reserve1		varchar(20)					null,			
	reserve2		varchar(20)					null,				
	reserve3		varchar(20)					null				
)
grant select on  JYffMisReverNew2008 to public
create unique clustered index JYffMisReverNew2008index1
	on JYffMisReverNew2008(rtu_id,mp_id,date,time)
create unique index JYffMisReverNew2008index2 
	on JYffMisReverNew2008(czjylsh)
end
go
-- 标记结束

--   '-------Create table 专变算费系统缴费数据表[新](ZYffMisPayNew2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZYffMisPayNew2008')
begin
create table ZYffMisPayNew2008
(
	rtu_id			int			   			not null,
	zjg_id			smallint   			not null,

	date				int							not null,
	time				int							not null,

	rever_flag	tinyint							null,				/*被冲正标志*/	
	check_flag	tinyint						  null,				/*对账标志*/
	
	error_no		int									null,				/*错误号*/	
	error_msg		varchar(60)  	  		null,				/*错误信息*/
	
	ccbh				varchar(40)  	  		null,				/*出厂编号*/
	
	dsdwbh		  varchar(20)			not null,				/*代收单位编号*/
	czybh		  	varchar(20)			not null,				/*操作员编号*/
	jylsh		  	varchar(40)			not null,				/*交易流水号*/
	dzpc				varchar(20)			not null,				/*对账批次*/
	
	yhbh		  	varchar(20)			not null,				/*用户编号*/
	jylb				tinyint					not null,				/*交易类别*/
	jffs		  	varchar(16)			not null,				/*缴费方式*/
	jsfs		  	varchar(16)			not null,				/*结算方式*/
	
	bcsj_zje		decimal(14, 3)	not null,				/*本次实缴总金额*/
	bcsj_qfje		decimal(14, 3)	not null,				/*本次实缴欠费金额*/
	bcsj_ysje		decimal(14, 3)	not null,				/*本次实缴预收金额*/

	yhzwrq			int							not null,				/*银行账务日期*/
	dyfp				tinyint					not null,				/*是否打印发票*/
	jfbs				int							not null,				/*缴费笔数*/
	
	reserve		  varchar(80)					null,				/*预留字段*/
	remark		  varchar(120)				null,				/*备注*/
	
	jfmx1				varchar(120)				null,				/*缴费明细1*/
	jfmx2				varchar(120)				null,				/*缴费明细2*/
	jfmx3				varchar(120)				null,				/*缴费明细3*/
	jfmx4				varchar(120)				null,				/*缴费明细4*/
	jfmx5				varchar(120)				null,				/*缴费明细5*/
	jfmx6				varchar(120)				null,				/*缴费明细6*/
	jfmx7				varchar(120)				null,				/*缴费明细7*/
	jfmx8				varchar(120)				null,				/*缴费明细8*/
	jfmx9				varchar(120)				null,				/*缴费明细9*/
	jfmx10			varchar(120)				null,				/*缴费明细10*/
	
	reserve1		varchar(20)					null,			
	reserve2		varchar(20)					null,				
	reserve3		varchar(20)					null				
)
grant select on  ZYffMisPayNew2008 to public
create unique clustered index ZYffMisPayNew2008index1
	on ZYffMisPayNew2008(rtu_id,zjg_id,date,time)
create unique index ZYffMisPayNew2008index2 
	on ZYffMisPayNew2008(jylsh)
end
go
-- 标记结束

--   '-------Create table 专变算费系统冲正数据表[新](ZYffMisReverNew2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZYffMisReverNew2008')
begin
create table ZYffMisReverNew2008
(
	rtu_id			int			   			not null,
	zjg_id			smallint   			not null,
		
	date				int							not null,
	time				int							not null,
	
	error_no		int									null,				/*错误号*/	
	error_msg		varchar(60)  	  		null,				/*错误信息*/
	
	ccbh				varchar(40)  	  		null,				/*出厂编号*/
	
	dsdwbh		  varchar(20)			not null,				/*代收单位编号*/
	czybh		  	varchar(20)			not null,				/*操作员编号*/
	czjylsh  		varchar(40)			not null,				/*冲正交易流水号*/
	dzpc				varchar(20)			not null,				/*对账批次*/
	yjylsh			varchar(40)			not null,				/*原交易流水号*/
	yhzwrq			int							not	null,				/*银行账务日期*/

	reserve1		varchar(20)					null,			
	reserve2		varchar(20)					null,				
	reserve3		varchar(20)					null				
)
grant select on  ZYffMisReverNew2008 to public
create unique clustered index ZYffMisReverNew2008index1
	on ZYffMisReverNew2008(rtu_id,zjg_id,date,time)
create unique index ZYffMisReverNew2008index2 
	on ZYffMisReverNew2008(czjylsh)
end
go
-- 标记结束

--   '-------Create table 测量点日余额数据表(MpDayRemain200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'MpDayRemain200808')
begin
create table MpDayRemain200808
(
	rtu_id				int       					not null,			/*终端编号*/	
	mp_id					smallint 						not null,			/*测量点编号*/
	ymd						int									not null,			/*数据日期*/
	
	cus_state			tinyint									null,			/*用户状态 0 初始态, 1 正常态, 10 销户态*/	
	op_type				tinyint									null,			/*本次操作类型 0 初始态, 1 开户, 2 缴费, 3 补卡, 4 补写卡, 5 冲正, 6 换表, 7 换费率 10 销户 */	
	
	shutdown_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 */	
	shutdown_val2	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 本地模拟电表 */	
	
	jsbd_ymd			int											null,			/*结算时间*/
	buy_times			int											null,			/*购电次数*/

	calc_mdhmi		int											null,			/*算费时间-MMDDHHMI*/
	calc_bdymd		int											null,			/*算费表底时间-YYYYMMDD*/	
	
	now_remain		decimal(12,2)						null,			/*当前剩余*/
	now_remain2		decimal(12,2)						null,			/*当前剩余 本地模拟电表*/
	
	cs_al1_state	tinyint									null,			/*报警1状态  0:正常状态 1:报警状态*/
	cs_al2_state	tinyint									null,			/*报警2状态  0:正常状态 1:报警状态*/
	cs_fhz_state	tinyint									null,			/*分合闸状态 0:分闸状态 1:合闸状态*/

	al1_mdhmi			int											null,			/*报警1状态改变时间-MMDDHHMI*/
	al2_mdhmi			int											null,			/*报警2状态改变时间-MMDDHHMI*/	
	fhz_mdhmi			int											null,			/*分合闸状态改变时间-MMDDHHMI*/

	fz_zyz				decimal(12,2)						null,			/*分闸时总表底*/
	fz1_zyz				decimal(12,2)						null,			/*动力关联1-分闸时总表底*/
	fz2_zyz				decimal(12,2)						null,			/*动力关联2-分闸时总表底*/
	

	total_gdz			decimal(12,2)						null,			/*累计购电值*/

	jt_total_zbdl		decimal(12,2)					null,			/*阶梯追补累计用电量*/
	jt_total_dl			decimal(12,2)					null,			/*阶梯累计用电量*/
	jt_reset_ymd		int										null,			/*阶梯上次自动切换日期*/
	jt_reset_mdhmi	int										null,			/*阶梯切换执行时间*/

	fxdf_iall_money		decimal(12,2)				null, 		/*发行电费当月缴费总金额*/
	fxdf_iall_money2	decimal(12,2)				null, 		/*发行电费当月缴费总金额2*/
	fxdf_remain				decimal(12,2)				null,			/*发行电费后剩余金额  		fxdf_after_remain*/
	fxdf_remain2			decimal(12,2)				null,			/*发行电费后剩余金额  		fxdf_after_remain 本地模拟电表*/

	fxdf_ym					int										null,			/*发行电费数据日期YYYYMM*/
	fxdf_data_ymd		int										null,			/*发行电费数据日期-YYYYMMDD*/
	fxdf_calc_mdhmi	int										null,			/*发行电费算费时间-MMDDHHMI*/

	js_bc_ymd			int											null, 	  	/*结算补差日期YYYYMMDD*/
)
grant select on  MpDayRemain200808 to public
create unique clustered index MpDayRemain200808index1
	on MpDayRemain200808(rtu_id,mp_id,ymd)
end
go
-- 标记结束

--发行电费

--   '-------Create table 测量点发行电费余额数据表(MpFxMoney2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'MpFxMoney2008')
begin
create table MpFxMoney2008
(
	rtu_id						int							  not null,
	mp_id							smallint				  not null,					/*zjg_id mp_id*/
	fxdf_ym						int								not null, 				/*发行电费年月*/
--data_type					tinyint						not null, 				/*数据类型 0 发行电费 1 阶梯切换*/
		
	cons_no						varchar(64)			  not null,					/*客户编号 居民户号*/
  
  fxdf_mdhmi				int										null,					/*发行电费算费时间-MMDDHHMI*/
  fxdf_lastym				int										null,					/*上次电费年月*/

	iall_money				decimal(12,2)		  		null,					/*本月缴费总金额*/
	iall_money2				decimal(12,2)		  		null,					/*本月缴费总金额2*/

	last_remain				decimal(12,2)		  		null,					/*上次剩余*/
	last_remain2			decimal(12,2)		  		null,					/*上次剩余2*/
	
	now_remain				decimal(12,2)		  		null,					/*当前剩余*/
	now_remain2				decimal(12,2)		  		null,					/*当前剩余2*/

	jt_total_zbdl			decimal(12,2)		  		null,					/*阶梯追补累计用电量*/
	jt_total_dl				decimal(12,2)		  		null,					/*阶梯累计用电量*/

	df_money					decimal(12,2)		  not null,					/*电费金额*/
	df_money2					decimal(12,2)		  not null,					/*电费金额2*/
  	                                    
	shutdown_val			decimal(12,2)		  		null,					/*断电值 金额计费时为:断电金额 */	
	shutdown_val2			decimal(12,2)		  		null,					/*断电值 金额计费时为:断电金额2*/	

 	jsbd_ymd					int										null,					/*上次结算时间*/
	jsbd_zyz					decimal(12,2)		  		null,					/*上次结算总表底*/
	jsbd_zyj					decimal(12,2)		  		null,					/*上次结算尖表底*/
	jsbd_zyf					decimal(12,2)		  		null,					/*上次结算峰表底*/
	jsbd_zyp					decimal(12,2)		  		null,					/*上次结算平表底*/
	jsbd_zyg					decimal(12,2)		  		null,					/*上次结算谷表底*/
                		                  
	jsbd1_zyz					decimal(12,2)		  		null,					/*动力关联1-上次结算总表底*/
	jsbd1_zyj					decimal(12,2)		  		null,					/*动力关联1-上次结算尖表底*/
	jsbd1_zyf					decimal(12,2)		  		null,					/*动力关联1-上次结算峰表底*/
	jsbd1_zyp					decimal(12,2)		  		null,					/*动力关联1-上次结算平表底*/
	jsbd1_zyg					decimal(12,2)		  		null,					/*动力关联1-上次结算谷表底*/
                		                  
	jsbd2_zyz					decimal(12,2)		  		null,					/*动力关联2-上次结算总表底*/
	jsbd2_zyj					decimal(12,2)		  		null,					/*动力关联2-上次结算尖表底*/
	jsbd2_zyf					decimal(12,2)		  		null,					/*动力关联2-上次结算峰表底*/
	jsbd2_zyp					decimal(12,2)		  		null,					/*动力关联2-上次结算平表底*/
	jsbd2_zyg					decimal(12,2)		  		null,					/*动力关联2-上次结算谷表底*/
  
  calc_bdymd				int										null,					/*算费表底时间-YYYYMMDD*/              		                  
	calc_zyz					decimal(12,2)		  		null,					/*算费时总表底*/
	calc_zyj					decimal(12,2)		  		null,					/*算费时尖表底*/
	calc_zyf					decimal(12,2)		  		null,					/*算费时峰表底*/
	calc_zyp					decimal(12,2)		  		null,					/*算费时平表底*/
	calc_zyg					decimal(12,2)		  		null,					/*算费时谷表底*/
	              		                  
	calc1_zyz					decimal(12,2)		  		null,					/*动力关联1-算费时总表底*/
	calc1_zyj					decimal(12,2)		  		null,					/*动力关联1-算费时尖表底*/
	calc1_zyf					decimal(12,2)		  		null,					/*动力关联1-算费时峰表底*/
	calc1_zyp					decimal(12,2)		  		null,					/*动力关联1-算费时平表底*/
	calc1_zyg					decimal(12,2)		  		null,					/*动力关联1-算费时谷表底*/
	
	calc2_zyz					decimal(12,2)		  		null,					/*动力关联2-算费时总表底*/
	calc2_zyj					decimal(12,2)		  		null,					/*动力关联2-算费时尖表底*/
	calc2_zyf					decimal(12,2)		  		null,					/*动力关联2-算费时峰表底*/
	calc2_zyp					decimal(12,2)		  		null,					/*动力关联2-算费时平表底*/
	calc2_zyg					decimal(12,2)		  		null,					/*动力关联2-算费时谷表底*/		

	update_count			int										null,					/*更新次数*/

  all_monthf				tinyint								null,					/*整月标志*/
	bc_flag						tinyint					  		null, 				/*结算补差标记*/
	bc_date						int							  		null,					/*结算补差日期*/
	bc_time						int							  		null,					/*结算补差时间*/
	op_man						varchar(64)			  		null,					/*操作员*/	
)
grant select on  MpFxMoney2008 to public
create unique clustered index MpFxMoney2008index1
	on MpFxMoney2008(rtu_id,mp_id,fxdf_ym)
end
go
-- 标记结束


--   '-------Create table 测量点居民阶梯清零记录(MpJtReset2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'MpJtReset2008')
begin
create table MpJtReset2008
(
	rtu_id						int							  not null,
	mp_id							smallint				  not null,					/*zjg_id mp_id*/
	date							int								not null, 				/*发行电费年月*/
		
	cons_no						varchar(64)			  not null,					/*客户编号 居民户号*/
  
  jtreset_mdhmi			int										null,					/*清零执行时间-MMDDHHMI*/
  jtreset_lastymd		int										null,					/*上次清零日期*/

	jt_total_zbdl			decimal(12,2)		  		null,					/*阶梯追补累计用电量*/
	jt_total_dl				decimal(12,2)		  		null,					/*阶梯累计用电量*/
			                                    
	shutdown_val			decimal(12,2)		  		null,					/*断电值 金额计费时为:断电金额 */	
	shutdown_val2			decimal(12,2)		  		null,					/*断电值 金额计费时为:断电金额2*/	

 	jsbd_ymd					int										null,					/*上次结算时间*/
	jsbd_zyz					decimal(12,2)		  		null,					/*上次结算总表底*/
	jsbd_zyj					decimal(12,2)		  		null,					/*上次结算尖表底*/
	jsbd_zyf					decimal(12,2)		  		null,					/*上次结算峰表底*/
	jsbd_zyp					decimal(12,2)		  		null,					/*上次结算平表底*/
	jsbd_zyg					decimal(12,2)		  		null,					/*上次结算谷表底*/
                		                  
	jsbd1_zyz					decimal(12,2)		  		null,					/*动力关联1-上次结算总表底*/
	jsbd1_zyj					decimal(12,2)		  		null,					/*动力关联1-上次结算尖表底*/
	jsbd1_zyf					decimal(12,2)		  		null,					/*动力关联1-上次结算峰表底*/
	jsbd1_zyp					decimal(12,2)		  		null,					/*动力关联1-上次结算平表底*/
	jsbd1_zyg					decimal(12,2)		  		null,					/*动力关联1-上次结算谷表底*/
                		                  
	jsbd2_zyz					decimal(12,2)		  		null,					/*动力关联2-上次结算总表底*/
	jsbd2_zyj					decimal(12,2)		  		null,					/*动力关联2-上次结算尖表底*/
	jsbd2_zyf					decimal(12,2)		  		null,					/*动力关联2-上次结算峰表底*/
	jsbd2_zyp					decimal(12,2)		  		null,					/*动力关联2-上次结算平表底*/
	jsbd2_zyg					decimal(12,2)		  		null,					/*动力关联2-上次结算谷表底*/
  
  calc_bdymd				int										null,					/*算费表底时间-YYYYMMDD*/              		                  
	calc_zyz					decimal(12,2)		  		null,					/*算费时总表底*/
	calc_zyj					decimal(12,2)		  		null,					/*算费时尖表底*/
	calc_zyf					decimal(12,2)		  		null,					/*算费时峰表底*/
	calc_zyp					decimal(12,2)		  		null,					/*算费时平表底*/
	calc_zyg					decimal(12,2)		  		null,					/*算费时谷表底*/
	              		                  
	calc1_zyz					decimal(12,2)		  		null,					/*动力关联1-算费时总表底*/
	calc1_zyj					decimal(12,2)		  		null,					/*动力关联1-算费时尖表底*/
	calc1_zyf					decimal(12,2)		  		null,					/*动力关联1-算费时峰表底*/
	calc1_zyp					decimal(12,2)		  		null,					/*动力关联1-算费时平表底*/
	calc1_zyg					decimal(12,2)		  		null,					/*动力关联1-算费时谷表底*/
	
	calc2_zyz					decimal(12,2)		  		null,					/*动力关联2-算费时总表底*/
	calc2_zyj					decimal(12,2)		  		null,					/*动力关联2-算费时尖表底*/
	calc2_zyf					decimal(12,2)		  		null,					/*动力关联2-算费时峰表底*/
	calc2_zyp					decimal(12,2)		  		null,					/*动力关联2-算费时平表底*/
	calc2_zyg					decimal(12,2)		  		null,					/*动力关联2-算费时谷表底*/		

	op_man						varchar(64)			  		null,					/*操作员*/	
)                                     
grant select on  MpJtReset2008 to public
create unique clustered index MpJtReset2008index1
	on MpJtReset2008(rtu_id,mp_id,date)
end
go
-- 标记结束

--   '-------Create table 总加组发行电费余额数据表(ZjgFxMoney2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZjgFxMoney2008')
begin
create table ZjgFxMoney2008
(
	rtu_id						int							  not null,
	zjg_id						smallint				  not null,					/*zjg_id mp_id*/
	fxdf_ym						int								not null, 				/*发行电费年月*/
--data_type					tinyint						not null, 				/*数据类型 0 发行电费 1 阶梯切换*/

	bus_no						varchar(64)			  not null,					/*客户编号 居民户号*/
  
  fxdf_mdhmi				int										null,					/*发行电费算费时间-MMDDHHMI*/
  fxdf_lastym				int										null,					/*上次电费年月*/

	iall_money				decimal(12,2)		  		null,					/*本月缴费总金额*/

	last_remain				decimal(12,2)		  		null,					/*上次剩余*/
	now_remain				decimal(12,2)		  		null,					/*当前剩余*/

	df_money					decimal(12,2)		  not null,					/*电费金额*/

	real_powrate			decimal(12,2)		  		null,					/*实际功率因数*/
	ele_money					decimal(12,2)		  		null,					/*电度电费*/
	jbf_money					decimal(12,2)		  		null,					/*基本费电费*/
	powrate_money			decimal(12,2)		  		null,					/*力调电费*/
	other_money				decimal(12,2)					null,					/*其它电费*/

	total_yzbdl				decimal(12,2)		  		null,					/*有功追补累计用电量*/
	total_wzbdl				decimal(12,2)		  		null,					/*无功追补累计用电量*/
	total_ydl					decimal(12,2)		  		null,					/*有功累计用电量*/
	total_wdl					decimal(12,2)		  		null,					/*无功累计用电量*/

 	zbele_money	  		decimal(12,2)					null,					/*追补电度电费*/
	zbjbf_money	  		decimal(12,2)					null,					/*追补基本费电费*/
	zbother_money			decimal(12,2)					null,					/*追补其它电费*/

	shutdown_val			decimal(12,2)		  		null,					/*断电值 金额计费时为:断电金额 */	

 	jsbd_ymd					int										null,					/*上次结算时间*/
	jsbd_zyz					decimal(12,2)		  		null,					/*上次结算总表底*/
	jsbd_zyj					decimal(12,2)		  		null,					/*上次结算尖表底*/
	jsbd_zyf					decimal(12,2)		  		null,					/*上次结算峰表底*/
	jsbd_zyp					decimal(12,2)		  		null,					/*上次结算平表底*/
	jsbd_zyg					decimal(12,2)		  		null,					/*上次结算谷表底*/
                		                  
	jsbd1_zyz					decimal(12,2)		  		null,					/*-上次结算总表底*/
	jsbd1_zyj					decimal(12,2)		  		null,					/*-上次结算尖表底*/
	jsbd1_zyf					decimal(12,2)		  		null,					/*-上次结算峰表底*/
	jsbd1_zyp					decimal(12,2)		  		null,					/*-上次结算平表底*/
	jsbd1_zyg					decimal(12,2)		  		null,					/*-上次结算谷表底*/
                		                  
	jsbd2_zyz					decimal(12,2)		  		null,					/*-上次结算总表底*/
	jsbd2_zyj					decimal(12,2)		  		null,					/*-上次结算尖表底*/
	jsbd2_zyf					decimal(12,2)		  		null,					/*-上次结算峰表底*/
	jsbd2_zyp					decimal(12,2)		  		null,					/*-上次结算平表底*/
	jsbd2_zyg					decimal(12,2)		  		null,					/*-上次结算谷表底*/

	jsbd_zwz					decimal(12,2)		  		null,					/*上次结算无功总表底*/
	jsbd1_zwz					decimal(12,2)		  		null,					/*上次结算无功总表底*/
	jsbd2_zwz					decimal(12,2)		  		null,					/*上次结算无功总表底*/
  
  calc_bdymd				int										null,					/*算费表底时间-YYYYMMDD*/              		                  
	calc_zyz					decimal(12,2)		  		null,					/*算费时总表底*/
	calc_zyj					decimal(12,2)		  		null,					/*算费时尖表底*/
	calc_zyf					decimal(12,2)		  		null,					/*算费时峰表底*/
	calc_zyp					decimal(12,2)		  		null,					/*算费时平表底*/
	calc_zyg					decimal(12,2)		  		null,					/*算费时谷表底*/
	              		                  
	calc1_zyz					decimal(12,2)		  		null,					/*-算费时总表底*/
	calc1_zyj					decimal(12,2)		  		null,					/*-算费时尖表底*/
	calc1_zyf					decimal(12,2)		  		null,					/*-算费时峰表底*/
	calc1_zyp					decimal(12,2)		  		null,					/*-算费时平表底*/
	calc1_zyg					decimal(12,2)		  		null,					/*-算费时谷表底*/
	
	calc2_zyz					decimal(12,2)		  		null,					/*-算费时总表底*/
	calc2_zyj					decimal(12,2)		  		null,					/*-算费时尖表底*/
	calc2_zyf					decimal(12,2)		  		null,					/*-算费时峰表底*/
	calc2_zyp					decimal(12,2)		  		null,					/*-算费时平表底*/
	calc2_zyg					decimal(12,2)		  		null,					/*-算费时谷表底*/		

	calc_zwz					decimal(12,2)		  		null,					/*算费无功总表底*/
	calc1_zwz					decimal(12,2)		  		null,					/*算费无功总表底*/
	calc2_zwz					decimal(12,2)		  		null,					/*算费无功总表底*/
	
	update_count			int										null,					/*更新次数*/

  all_monthf				tinyint								null,					/*整月标志*/
	bc_flag						tinyint					  		null, 				/*结算补差标记*/
	bc_date						int							  		null,					/*结算补差日期*/
	bc_time						int							  		null,					/*结算补差时间*/
	op_man						varchar(64)			  		null,					/*操作员*/	
)
grant select on  ZjgFxMoney2008 to public
create unique clustered index ZjgFxMoney2008index1
	on ZjgFxMoney2008(rtu_id,zjg_id,fxdf_ym)
end
go
-- 标记结束

-- 结算记录表

--	'-------Create table 居民结算记录表(ZJsRecord2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JJsRecord2008')
begin
create table JJsRecord2008
(
	rtu_id				int						  		not null,
	mp_id				  smallint  					not null,
	fxdf_ym				int									not null, 		/*发行电费年月*/
  js_times			int									not	null, 		/*结算次数*/

	op_date				int									not null,			/*本次操作日期*/
	op_time				int									not null,			/*本次操作时间*/
		
	res_id				smallint						not null,			/*客户编号*/
	res_desc			varchar(64)							null,			/*客户名称*/
	
	op_man				varchar(64)					not null,			/*操作员*/
	
	pay_type			tinyint									null,			/*缴费方式*/
	wasteno				varchar(32)							null,			/*流水号*/

	pay_money			decimal(12,2)						null,			/*缴费金额*/
	othjs_money		decimal(12,2)						null,			/*实际结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/
	all_money			decimal(12,2)						null,			/*总金额*/

	buy_times			int											null,			/*购电次数*/

--上面为缴费记录 下面新添加用于结算补差
	misjs_money	  decimal(12,2)						null,			/*mis应结算金额*/
	totjs_money   decimal(12,2)						null,			/*累计结算金额*/

	lastala_val1  decimal(12,2)						null,			/*上次报警值1*/
	lastala_val2  decimal(12,2)						null,			/*上次报警值2*/
	lastshut_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 表底计费时为:断电止码 */
	
	newala_val1   decimal(12,2)						null,			/*报警值1*/
	newala_val2   decimal(12,2)						null,			/*报警值2*/
	newshut_val   decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 表底计费时为:断电止码 */

	reserve1   		decimal(12,2)						null,		  /*扩展字段1*/
	reserve2   		decimal(12,2)  					null,		  /*扩展字段2*/
	reserve3   		varchar(32)  						null,		  /*扩展字段3*/
	reserve4   		varchar(32)  						null,		  /*扩展字段4*/

)
grant select on JJsRecord2008 to public
create unique clustered index JJsRecord2008index1
	on JJsRecord2008(rtu_id,mp_id,fxdf_ym,js_times)	
end
go
-- 标记结束

--	'-------Create table 总加组结算记录表(ZJsRecord2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZJsRecord2008')
begin
create table ZJsRecord2008
(
	rtu_id				int						  		not null,
	zjg_id				smallint  					not null,
	fxdf_ym				int									not null, 		/*发行电费年月*/
  js_times			int									not	null, 		/*结算次数*/

	op_date				int									not null,			/*本次操作日期*/
	op_time				int									not null,			/*本次操作时间*/
		
	cons_id				smallint						not null,			/*客户编号*/
	cons_desc			varchar(64)							null,			/*客户名称*/
	
	op_man				varchar(64)					not null,			/*操作员*/
	
	pay_type			tinyint									null,			/*缴费方式*/
	wasteno				varchar(32)							null,			/*流水号*/

	pay_money			decimal(12,2)						null,			/*缴费金额*/
	othjs_money		decimal(12,2)						null,			/*实际结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/
	all_money			decimal(12,2)						null,			/*总金额*/

	buy_dl				decimal(12,2)						null,			/*购电量*/
	pay_bmc				decimal(12,2)						null,			/*表码差*/

	buy_times			int											null,			/*购电次数*/

--上面为缴费记录 下面新添加用于结算补差
	misjs_money	  decimal(12,2)						null,			/*mis应结算金额*/
	totjs_money   decimal(12,2)						null,			/*累计结算金额*/

	misjs_bmc			decimal(12,2)						null,			/*mis应结算表码差*/
	totjs_bmc			decimal(12,2)						null,			/*累计结算表码差*/


  cur_bd				decimal(12,2)						null,			/*当前表底*/

	lastala_val1  decimal(12,2)						null,			/*上次报警值1*/
	lastala_val2  decimal(12,2)						null,			/*上次报警值2*/
	lastshut_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 表底计费时为:断电止码 */
	
	newala_val1   decimal(12,2)						null,			/*报警值1*/
	newala_val2   decimal(12,2)						null,			/*报警值2*/
	newshut_val   decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 表底计费时为:断电止码 */

	reserve1   		decimal(12,2)						null,		  /*扩展字段1*/
	reserve2   		decimal(12,2)  					null,		  /*扩展字段2*/
	reserve3   		varchar(32)  						null,		  /*扩展字段3*/
	reserve4   		varchar(32)  						null,		  /*扩展字段4*/

)
grant select on ZJsRecord2008 to public
create unique clustered index ZJsRecord2008index1
	on ZJsRecord2008(rtu_id,zjg_id,fxdf_ym,js_times)	
end
go
-- 标记结束


--	'-------Create table 农排预付费记录表(NYff2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'NYff2008')
begin
create table NYff2008
(
	area_id				int									not	null,			/*所属片区*/
	farmer_id			smallint	    			not null,			/*农排用户id*/
	
	farmer_desc		varchar(64)							null,			/*客户名称*/  				/*冗余存储*/
	farmer_no			varchar(64)							null,			/*农排户号sg186编号*/ /*冗余存储*/
	card_no				varchar(32)							null,			/*卡号*/							/*冗余存储*/
	area_code			varchar(32)							null,			/*区域号*/						/*冗余存储*/

	op_man				varchar(64)					not null,			/*操作员*/	
	op_type				tinyint							not null,			/*本次操作类型 0 初始态, 开户, 缴费, 补卡, 补写卡, 冲正, 销户 定义与原来一致 */	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/

	pay_type			tinyint									null,			/*缴费方式*/

	wasteno				varchar(32)							null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/

	pay_money			decimal(12,2)						null,			/*缴费金额*/
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/
	all_money			decimal(12,2)						null,			/*总金额*/

	ls_remain			decimal(12,2)						null,			/*上次剩余金额 last remain*/	
	cur_remain		decimal(12,2)						null,			/*当前剩余金额 current remain*/

	total_gdz			decimal(12,2)						null,			/*累计购电金额*/
	buy_times			int											null,			/*购电次数*/
	totbuy_times	int											null,			/*累计购电次数*/

	alarm1				decimal(12,2)						null,			/*报警值1*/		/*备用*/
	alarm2				decimal(12,2)						null,			/*报警值2*/		/*备用*/

	op_result			tinyint									null,			/*操作结果 0:未成功 1:写卡成功 2:远程设置成功 3:主站操作成功 4:暂时不处理 5:正在操作 6:失败*/
	visible_flag	tinyint									null,			/*是否显示*/	

	sg186_ysdw		varchar(20)							null,			/*186应收单位标识*/	
	up186_flag		tinyint									null,			/*186上传成功标志*/
	checkpay_flag	tinyint									null			/*对账成功标志*/
)
grant select on NYff2008 to public
create clustered index NYff2008index1
	on NYff2008(area_id,farmer_id,op_date,op_time,op_type)
		
create index NYff2008index2 
	on NYff2008(area_id,farmer_no,op_date,op_time,op_type)  
end
go
-- 标记结束


--	'-------Create table 农排预付费记录表备份(sysnyfrcd2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'sysnyfrcd2008')
begin
create table sysnyfrcd2008
(
	area_id				int									not	null,			/*所属片区*/
	farmer_id			smallint	    			not null,			/*农排用户id*/
	
	farmer_desc		varchar(64)							null,			/*客户名称*/  				 /*冗余存储*/
	farmer_no			varchar(64)							null,			/*农排户号sg186编号*/  /*冗余存储*/
	card_no				varchar(32)							null,			/*卡号*/							 /*冗余存储*/
	area_code			varchar(32)							null,			/*区域号*/						 /*冗余存储*/

	op_man				varchar(64)					not null,			/*操作员*/	
	op_type				tinyint							not null,			/*本次操作类型 0 初始态, 开户, 缴费, 补卡, 补写卡, 冲正, 销户 定义与原来一致 */	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/

	pay_type			tinyint									null,			/*缴费方式*/

	wasteno				varchar(32)							null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/

	pay_money			decimal(12,2)						null,			/*缴费金额*/	
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)						null,			/*总金额*/

	ls_remain			decimal(12,2)						null,			/*上次剩余金额 last remain*/	
	cur_remain		decimal(12,2)						null,			/*当前剩余金额 current remain*/

	total_gdz			decimal(12,2)						null,			/*累计购电金额*/
	buy_times			int											null,			/*购电次数*/
	totbuy_times	int											null,			/*累计购电次数*/

	alarm1				decimal(12,2)						null,			/*报警值1*/		/*备用*/
	alarm2				decimal(12,2)						null,			/*报警值2*/		/*备用*/

	op_result			tinyint									null,			/*操作结果 0:未成功 1:写卡成功 2:远程设置成功 3:主站操作成功 4:暂时不处理 5:正在操作 6:失败*/
	visible_flag	tinyint									null,			/*是否显示*/	

	sg186_ysdw		varchar(20)							null,			/*186应收单位标识*/	
	up186_flag		tinyint									null,			/*186上传成功标志*/
	checkpay_flag	tinyint									null			/*对账成功标志*/
)
grant select on sysnyfrcd2008 to public
create clustered index sysnyfrcd2008index1
	on sysnyfrcd2008(area_id,farmer_id,op_date,op_time,op_type)	
create index sysnyfrcd2008index2 
	on sysnyfrcd2008(area_id,farmer_no,op_date,op_time,op_type)
end
go
-- 标记结束


--a.       SB-2100A、D：累积流量、瞬时流量、累积热量、瞬时热量、差压（频率）、密度、压力、温度、热焓、当前时间及来停电记录。
--b.       SB-2100H：累积流量、瞬时流量、累积热（差）量、瞬时热（差）量、差压、频率、密度、压力、入口温度、出口温度、当前时间。
----------------------蒸汽应用开始--------------------------------------------

--   '-------Create table 蒸汽实时瞬时量数据表(SRealllrl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'SRealllrl200808')
begin
create table SRealllrl200808
(
	rtu_id			int			   		not null,
	mp_id				smallint   		not null,
	date				int						not null,
	time				int						not null,
	
	ll_total		decimal(14, 3) 	DEFAULT 0.0 ,	/*累积流量*/	
	ll_ss				decimal(14, 3) 	DEFAULT 0.0 ,	/*瞬时流量*/	
	rl_total		decimal(14, 3) 	DEFAULT 0.0 ,	/*累积热量*/	
	rl_ss				decimal(14, 3) 	DEFAULT 0.0 ,	/*瞬时热量*/
	dif_gauge 	decimal(14, 3) 	DEFAULT 0.0 ,	/*差压*/
	hz					decimal(14, 3) 	DEFAULT 0 	,	/*频率*/	
	
	density			decimal(14, 3) 	DEFAULT 0.0 ,	/*密度*/	
	pressure		decimal(14, 3) 	DEFAULT 0.0 ,	/*压力*/	
	temper 			decimal(14, 3) 	DEFAULT 0.0 ,	/*温度*/	
	enthalpy		decimal(14, 3) 	DEFAULT 0.0 ,	/*热焓*/	
	noele_times	int 						DEFAULT 0 	,	/*停电次数*/	
	noele_time	int 						DEFAULT 0 	,	/*停电累计时间*/	
	
	reserve1		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段1*/	
	reserve2		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段2*/	
	reserve3		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段3*/	
	reserve4		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段4*/
	source			tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  SRealllrl200808 to public
create unique clustered index SRealllrl200808index1
	on SRealllrl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 蒸汽分钟表底电量数据表(SMinllrl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'SMinllrl200808')
begin
create table SMinllrl200808
(
	rtu_id			int			   		not null,
	mp_id				smallint   		not null,
	date				int						not null,
	time				int						not null,
	
	ll_total		decimal(14, 3) 	DEFAULT 0.0 ,	/*累积流量*/	
	ll_ss				decimal(14, 3) 	DEFAULT 0.0 ,	/*瞬时流量*/	
	rl_total		decimal(14, 3) 	DEFAULT 0.0 ,	/*累积热量*/	
	rl_ss				decimal(14, 3) 	DEFAULT 0.0 ,	/*瞬时热量*/
	dif_gauge 	decimal(14, 3) 	DEFAULT 0.0 ,	/*差压*/	
	hz					decimal(14, 3) 	DEFAULT 0 	,	/*频率*/	
	
	density			decimal(14, 3) 	DEFAULT 0.0 ,	/*密度*/	
	pressure		decimal(14, 3) 	DEFAULT 0.0 ,	/*压力*/	
	temper 			decimal(14, 3) 	DEFAULT 0.0 ,	/*温度*/	
	enthalpy		decimal(14, 3) 	DEFAULT 0.0 ,	/*热焓*/	
	noele_times	int 						DEFAULT 0 	,	/*停电次数*/	
	noele_time	int 						DEFAULT 0 	,	/*停电累计时间*/	
	
	ll					decimal(14, 3) 	DEFAULT 0.0 ,	/*流量*/	
	rl					decimal(14, 3) 	DEFAULT 0.0 ,	/*热量*/	
	
	reserve1		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段1*/	
	reserve2		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段2*/	
	reserve3		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段3*/	
	reserve4		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段4*/
	
	source			tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  SMinllrl200808 to public
create unique clustered index SMinllrl200808index1
	on SMinllrl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束




--   '-------Create table 蒸汽日表底电量数据表(SDayllrl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'SDayllrl200808')
begin
create table SDayllrl200808
(
	rtu_id			int			   		not null,
	mp_id				smallint   		not null,
	date				int						not null,
	time				int						not null,
	
	ll_total		decimal(14, 3) 	DEFAULT 0.0 ,	/*累积流量*/	
	ll_ss				decimal(14, 3) 	DEFAULT 0.0 ,	/*瞬时流量*/	
	rl_total		decimal(14, 3) 	DEFAULT 0.0 ,	/*累积热量*/	
	rl_ss				decimal(14, 3) 	DEFAULT 0.0 ,	/*瞬时热量*/
	dif_gauge 	decimal(14, 3) 	DEFAULT 0.0 ,	/*差压*/	
	hz					decimal(14, 3) 	DEFAULT 0 	,	/*频率*/	
	
	density			decimal(14, 3) 	DEFAULT 0.0 ,	/*密度*/	
	pressure		decimal(14, 3) 	DEFAULT 0.0 ,	/*压力*/	
	temper 			decimal(14, 3) 	DEFAULT 0.0 ,	/*温度*/	
	enthalpy		decimal(14, 3) 	DEFAULT 0.0 ,	/*热焓*/	
	noele_times	int 						DEFAULT 0 	,	/*停电次数*/	
	noele_time	int 						DEFAULT 0 	,	/*停电累计时间*/	
	
	ll					decimal(14, 3) 	DEFAULT 0.0 ,	/*流量*/	
	rl					decimal(14, 3) 	DEFAULT 0.0 ,	/*热量*/	
	
	reserve1		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段1*/	
	reserve2		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段2*/	
	reserve3		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段3*/	
	reserve4		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段4*/
	
	source			tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  SDayllrl200808 to public
create unique clustered index SDayllrl200808index1
	on SDayllrl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束



--   '-------Create table 蒸汽月表底电量数据表(SMonllrl2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'SMonllrl2008')
begin
create table SMonllrl2008
(
	rtu_id			int			   		not null,
	mp_id				smallint   		not null,
	date				int						not null,
	time				int						not null,
	
	ll_total		decimal(14, 3) 	DEFAULT 0.0 ,	/*累积流量*/	
	ll_ss				decimal(14, 3) 	DEFAULT 0.0 ,	/*瞬时流量*/	
	rl_total		decimal(14, 3) 	DEFAULT 0.0 ,	/*累积热量*/	
	rl_ss				decimal(14, 3) 	DEFAULT 0.0 ,	/*瞬时热量*/
	dif_gauge 	decimal(14, 3) 	DEFAULT 0.0 ,	/*差压*/	
	hz					decimal(14, 3) 	DEFAULT 0 	,	/*频率*/	
	
	density			decimal(14, 3) 	DEFAULT 0.0 ,	/*密度*/	
	pressure		decimal(14, 3) 	DEFAULT 0.0 ,	/*压力*/	
	temper 			decimal(14, 3) 	DEFAULT 0.0 ,	/*温度*/	
	enthalpy		decimal(14, 3) 	DEFAULT 0.0 ,	/*热焓*/	
	noele_times	int 						DEFAULT 0 	,	/*停电次数*/	
	noele_time	int 						DEFAULT 0 	,	/*停电累计时间*/	
	
	ll					decimal(14, 3) 	DEFAULT 0.0 ,	/*流量*/	
	rl					decimal(14, 3) 	DEFAULT 0.0 ,	/*热量*/	
	
	reserve1		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段1*/	
	reserve2		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段2*/	
	reserve3		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段3*/	
	reserve4		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段4*/
	
	source			tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  SMonllrl2008 to public
create unique clustered index SMonllrl2008index1
	on SMonllrl2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束



----------------------蒸汽应用结束--------------------------------------------



--   '-------Create table 电容器日数据表(ZDayCap200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayCap200808')
begin
create table ZDayCap200808
(
	rtu_id			int			   		not null,
	mp_id				smallint   		not null,
	date				int						not null,
	time				int						not null,
	
	day_wgdl		decimal(14, 3) 	DEFAULT 0.0 ,	/*日补偿无功电能量*/	
	mon_wgdl		decimal(14, 3) 	DEFAULT 0.0 ,	/*月补偿无功电能量*/	
	
	total_tm1		int 						DEFAULT 0 	,	/*第1组电容器累计投入时间*/	
	total_tm2		int 						DEFAULT 0 	,	/*第2组电容器累计投入时间*/	
	total_tm3		int 						DEFAULT 0 	,	/*第3组电容器累计投入时间*/	
	total_tm4		int 						DEFAULT 0 	,	/*第4组电容器累计投入时间*/	
	total_tm5		int 						DEFAULT 0 	,	/*第5组电容器累计投入时间*/	
	total_tm6		int 						DEFAULT 0 	,	/*第6组电容器累计投入时间*/	
	total_tm7		int 						DEFAULT 0 	,	/*第7组电容器累计投入时间*/	
	total_tm8		int 						DEFAULT 0 	,	/*第8组电容器累计投入时间*/	
	total_tm9		int 						DEFAULT 0 	,	/*第9组电容器累计投入时间*/	
	total_tm10	int 						DEFAULT 0 	,	/*第10组电容器累计投入时间*/	
	total_tm11	int 						DEFAULT 0 	,	/*第11组电容器累计投入时间*/	
	total_tm12	int 						DEFAULT 0 	,	/*第12组电容器累计投入时间*/	
	total_tm13	int 						DEFAULT 0 	,	/*第13组电容器累计投入时间*/	
	total_tm14	int 						DEFAULT 0 	,	/*第14组电容器累计投入时间*/	
	total_tm15	int 						DEFAULT 0 	,	/*第15组电容器累计投入时间*/	
	total_tm16	int 						DEFAULT 0 	,	/*第16组电容器累计投入时间*/	
	total_tm17	int 						DEFAULT 0 	,	/*第17组电容器累计投入时间*/	
	total_tm18	int 						DEFAULT 0 	,	/*第18组电容器累计投入时间*/	
	total_tm19	int 						DEFAULT 0 	,	/*第19组电容器累计投入时间*/	
	total_tm20	int 						DEFAULT 0 	,	/*第20组电容器累计投入时间*/	
	total_tm21	int 						DEFAULT 0 	,	/*第21组电容器累计投入时间*/	
	total_tm22	int 						DEFAULT 0 	,	/*第22组电容器累计投入时间*/	
	total_tm23	int 						DEFAULT 0 	,	/*第23组电容器累计投入时间*/	
	total_tm24	int 						DEFAULT 0 	,	/*第24组电容器累计投入时间*/	
	total_tm25	int 						DEFAULT 0 	,	/*第25组电容器累计投入时间*/	
	total_tm26	int 						DEFAULT 0 	,	/*第26组电容器累计投入时间*/	
	total_tm27	int 						DEFAULT 0 	,	/*第27组电容器累计投入时间*/	
	total_tm28	int 						DEFAULT 0 	,	/*第28组电容器累计投入时间*/	
	total_cnt1	int 						DEFAULT 0 	,	/*第1组电容器累计投入次数*/	
	total_cnt2	int 						DEFAULT 0 	,	/*第2组电容器累计投入次数*/	
	total_cnt3	int 						DEFAULT 0 	,	/*第3组电容器累计投入次数*/	
	total_cnt4	int 						DEFAULT 0 	,	/*第4组电容器累计投入次数*/	
	total_cnt5	int 						DEFAULT 0 	,	/*第5组电容器累计投入次数*/	
	total_cnt6	int 						DEFAULT 0 	,	/*第6组电容器累计投入次数*/	
	total_cnt7	int 						DEFAULT 0 	,	/*第7组电容器累计投入次数*/	
	total_cnt8	int 						DEFAULT 0 	,	/*第8组电容器累计投入次数*/	
	total_cnt9	int 						DEFAULT 0 	,	/*第9组电容器累计投入次数*/	
	total_cnt10	int 						DEFAULT 0 	,	/*第10组电容器累计投入次数*/	
	total_cnt11	int 						DEFAULT 0 	,	/*第11组电容器累计投入次数*/	
	total_cnt12	int 						DEFAULT 0 	,	/*第12组电容器累计投入次数*/	
	total_cnt13	int 						DEFAULT 0 	,	/*第13组电容器累计投入次数*/	
	total_cnt14	int 						DEFAULT 0 	,	/*第14组电容器累计投入次数*/	
	total_cnt15	int 						DEFAULT 0 	,	/*第15组电容器累计投入次数*/	
	total_cnt16	int 						DEFAULT 0 	,	/*第16组电容器累计投入次数*/	
	total_cnt17	int 						DEFAULT 0 	,	/*第17组电容器累计投入次数*/	
	total_cnt18	int 						DEFAULT 0 	,	/*第18组电容器累计投入次数*/	
	total_cnt19	int 						DEFAULT 0 	,	/*第19组电容器累计投入次数*/	
	total_cnt20	int 						DEFAULT 0 	,	/*第20组电容器累计投入次数*/	
	total_cnt21	int 						DEFAULT 0 	,	/*第21组电容器累计投入次数*/	
	total_cnt22	int 						DEFAULT 0 	,	/*第22组电容器累计投入次数*/	
	total_cnt23	int 						DEFAULT 0 	,	/*第23组电容器累计投入次数*/	
	total_cnt24	int 						DEFAULT 0 	,	/*第24组电容器累计投入次数*/	
	total_cnt25	int 						DEFAULT 0 	,	/*第25组电容器累计投入次数*/	
	total_cnt26	int 						DEFAULT 0 	,	/*第26组电容器累计投入次数*/	
	total_cnt27	int 						DEFAULT 0 	,	/*第27组电容器累计投入次数*/	
	total_cnt28	int 						DEFAULT 0 	,	/*第28组电容器累计投入次数*/	
)
grant select on  ZDayCap200808 to public
create unique clustered index ZDayCap200808index1
	on ZDayCap200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--zxp 20130808
--   '-------Create table 居民电表购用电数据表(JDayGYD200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JDayGYD200808')
begin
create table JDayGYD200808
(
	rtu_id		int			   			not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	gdcs			int									null,							/*购电次数*/
	syje			decimal(14, 3)	DEFAULT 0.0 ,					/*剩余金额*/
	ljgdje		decimal(14, 3)  DEFAULT 0.0 ,					/*累计购电金额*/
	sydl			decimal(14, 3)	DEFAULT 0.0 ,					/*剩余电量*/
	tzdl			decimal(14, 3)	DEFAULT 0.0 ,					/*透支电量*/
	ljgdl			decimal(14, 3)	DEFAULT 0.0 ,					/*累计购电量*/
	sqmxdl		decimal(14, 3)	DEFAULT 0.0 ,					/*赊欠门限电量*/
	bjdl			decimal(14, 3)	DEFAULT 0.0 ,					/*报警电量*/
	gzdl			decimal(14, 3)	DEFAULT 0.0 					/*故障电量*/
)
grant select on  JDayGYD200808 to public
create unique clustered index JDayGYD200808index1
	on JDayGYD200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 居民电表运行状态字数据表(JMeterState) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JMeterState')
begin
create table JMeterState
(
	rtu_id		int			   			not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,
	
	run_bw1		int									null,							/*电表运行状态字变位1*/
	run_bw2		int									null,							/*电表运行状态字变位2*/
	run_bw3		int									null,							/*电表运行状态字变位3*/
	run_bw4		int									null,							/*电表运行状态字变位4*/
	run_bw5		int									null,							/*电表运行状态字变位5*/
	run_bw6		int									null,							/*电表运行状态字变位6*/
	run_bw7		int									null,							/*电表运行状态字变位7*/
	run_st1		int									null,							/*电表运行状态字1*/
	run_st2		int									null,							/*电表运行状态字2*/
	run_st3		int									null,							/*电表运行状态字3*/
	run_st4		int									null,							/*电表运行状态字4*/
	run_st5		int									null,							/*电表运行状态字5*/
	run_st6		int									null,							/*电表运行状态字6*/
	run_st7		int									null,							/*电表运行状态字7*/
	
	rpt_bw1		int									null,							/*主动上报状态字变位1*/
	rpt_bw2		int									null,							/*主动上报状态字变位2*/
	rpt_bw3		int									null,							/*主动上报状态字变位3*/
	rpt_bw4		int									null,							/*主动上报状态字变位4*/
	rpt_bw5		int									null,							/*主动上报状态字变位5*/
	rpt_st1		int									null,							/*主动上报状态字1*/
	rpt_st2		int									null,							/*主动上报状态字2*/
	rpt_st3		int									null,							/*主动上报状态字3*/
	rpt_st4		int									null,							/*主动上报状态字4*/
	rpt_st5		int									null,							/*主动上报状态字5*/
	
	card_bw		int									null,							/*插卡状态字变位*/
	card_st		int									null,							/*插卡状态字*/
		
	reserve1_bw		int							null,							/*备用状态字1变位*/
	reserve1_st		int							null,							/*备用状态字1*/

	reserve2_bw		int							null,							/*备用状态字2变位*/
	reserve2_st		int							null,							/*备用状态字2*/	
)
grant select on  JMeterState to public
create unique clustered index JMeterStateindex1
	on JMeterState(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--	'-------Create table 抄表卡抄表记录表(JRM2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JRM2008')
begin
create table JRM2008
(
	rtu_id				int						  		not null,
	mp_id					smallint  					not null,	
	op_date				int									not null,			/*本次操作日期*/

	res_id				smallint						not null,			/*客户编号*/
	res_desc			char(64)						null,				  /*客户名称*/
	cons_no				varchar(64)					null,					/*居民户号*/
	op_man				char(64)						not null,			/*操作员*/	
	
	writecard_no	varchar(32)							null,			/*信息输出*/	-- 20130604	
	cacl_type			tinyint				default 0 null,			/*算费类型 0:无 1:金额计费 2:表底计费 */

	pay_val				decimal(12,2)						null,			/*购电量/金额	*/	
	buy_times			int											null,			/*购电次数*/		
	limit_val			decimal(12,4)   				null,			/*囤积限值*/	
	alarm_val			decimal(12,4)   				null,			/*报警值*/	
	ztlimit_val		decimal(12,4)   				null,			/*透支限值*/	
	pt						decimal(12,4)   				null,			/*pt*/	
	ct						decimal(12,4)   				null,			/*ct*/
	meter_id     	varchar(16)     				null,   	/*ESAM表号*/
	rm_date				int										  null,			/*抄表时间*/
	cur_fei				decimal(12,4)   				null,			/*当前费率电价*/
	cur_jtfei			decimal(12,4)   				null,			/*当前阶梯电价*/
	errin_num			int										  null,			/*非法插卡次数*/
	break_num			int										  null,			/*跳闸次数*/

	dbstate1			int										  null,			/*状态字1*/
	dbstate2			int										  null,			/*状态字2*/
	
	bd_zzyz			decimal(12,2)						null,				/*组合正向有功总表底*/
	bd_zyz			decimal(12,2)						null,				/*正向有功总表底*/
	bd_zyj			decimal(12,2)						null,				/*正向有功尖表底*/	
	bd_zyf			decimal(12,2)						null,				/*正向有功峰表底*/	
	bd_zyp			decimal(12,2)						null,				/*正向有功平表底*/	
	bd_zyg			decimal(12,2)						null,				/*正向有功谷表底*/
	
	bd_fyz			decimal(12,2)						null,				/*反向有功总表底*/
	bd_fyj			decimal(12,2)						null,				/*反向有功尖表底*/	
	bd_fyf			decimal(12,2)						null,				/*反向有功峰表底*/	
	bd_fyp			decimal(12,2)						null,				/*反向有功平表底*/	
	bd_fyg			decimal(12,2)						null,				/*反向有功谷表底*/
	
	remain_val	decimal(12,2)						null,				/*剩余电量/金额*/	
	tz_val			decimal(12,2)						null,				/*透支电量/金额*/	
	totbuy_val	decimal(12,2)						null,				/*累计购电电量/金额*/	
	lsmonzyz		decimal(12,2)						null,				/*上月正向有功总电量*/
	lssmonzyz		decimal(12,2)						null,				/*上上月正向有功总电量*/

)
grant select on JRM2008 to public
	
create unique clustered index JRM2008index1
	on JRM2008(rtu_id,mp_id,op_date)	
create index JRM2008index2 
	on JRM2008(rtu_id,res_id,op_date)  
end
go
-- 标记结束


--   '-------Create table 线损及站内平衡分钟数据表(linebalancemin2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'linebalancemin2008')
begin
create table linebalancemin2008
(
	linebalance_id	smallint		 		not null,		/*编号*/	
	type						tinyint	 	 					null,		/*类型*/
	date						int							not null,
	time						int							not null,
	
	dl_zy_z			decimal(14, 3) 	DEFAULT 0.0 ,	/*总表正向有功总电量*/	
	dl_fy_z			decimal(14, 3) 	DEFAULT 0.0 ,	/*总表反向有功总电量*/	
	dl_zw_z			decimal(14, 3) 	DEFAULT 0.0 ,	/*总表正向无功总电量*/		
	dl_fw_z			decimal(14, 3) 	DEFAULT 0.0 ,	/*总表反向无功总电量*/	
	dl_zy_f			decimal(14, 3) 	DEFAULT 0.0 ,	/*分表正向有功总电量*/	
	dl_fy_f			decimal(14, 3) 	DEFAULT 0.0 ,	/*分表反向有功总电量*/	
	dl_zw_f			decimal(14, 3) 	DEFAULT 0.0 ,	/*分表正向无功总电量*/		
	dl_fw_f			decimal(14, 3) 	DEFAULT 0.0 ,	/*分表反向无功总电量*/	
	per_zy			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功损耗比例*/	
	per_fy			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功损耗比例*/	
	per_zw			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功损耗比例*/		
	per_fw			decimal(14, 3) 	DEFAULT 0.0 	/*反向无功损耗比例*/		
)
grant select on  linebalancemin2008 to public
create unique clustered index linebalancemin2008index1
	on linebalancemin2008(linebalance_id,type,date,time)
end
go
-- 标记结束


--任丘华油需量2015.08
--   '-------Create table 变电站分钟需量数据表(BMinXl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinXl200808')
begin
create table BMinXl200808
(
	rtu_id				int			   			not null,
	mp_id					smallint   			not null,
	date					int							not null,
	time					int							not null,

	xl_zy					decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功谷需量*/	
	xl_zy_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
			
	xl_fy					decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
		
	xl_zw					decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功谷需量*/
	xl_zw_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_fw					decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功总需量*/	
	xl_fw_fl1			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功谷需量*/	
	xl_fw_fl4			decimal(14, 4) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	
)
grant select on  BMinXl200808 to public
create unique clustered index BMinXl200808index1
	on BMinXl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 变电站月需量数据表(BMonCalcXl2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMonCalcXl2008')
begin
create table BMonCalcXl2008
(
	rtu_id		int			   			not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,

	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷需量*/
	xl_zy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/	
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷需量*/	
	xl_zw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总需量*/
	xl_fw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷需量*/	
	xl_fw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	
	
	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 ,		/*反向无功尖需量时间*/
)
grant select on  BMonCalcXl2008 to public
create unique clustered index BMonCalcXl2008index1
	on BMonCalcXl2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束



--太钢谐波 20151215
--   '-------Create table 专变配变谐波数据表(ZMinXb200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinXb200808')
begin
create table ZMinXb200808
(
		rtu_id						int   					not null,
		mp_id							smallint   			not null,
		date							int							not null,
		time							int							not null,
		
		xb_va_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电压*/
		xb_va_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电压*/
		xb_va_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电压*/
		xb_va_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电压*/
		xb_va_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电压*/
		xb_va_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电压*/
		xb_va_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电压*/
		xb_va_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电压*/
		xb_va_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电压*/
		xb_va_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电压*/
		xb_va_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电压*/
		xb_va_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电压*/
		xb_va_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电压*/
		xb_va_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电压*/
		xb_va_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电压*/
		xb_va_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电压*/
		xb_va_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电压*/
		xb_va_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电压*/
		xb_va_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电压*/
		xb_va_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电压*/
				
		
		xb_vb_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相2次谐波电压*/
		xb_vb_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电压*/
		xb_vb_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电压*/
		xb_vb_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电压*/
		xb_vb_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电压*/
		xb_vb_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电压*/
		xb_vb_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电压*/
		xb_vb_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电压*/
		xb_vb_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电压*/
		xb_vb_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电压*/
		xb_vb_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电压*/
		xb_vb_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电压*/
		xb_vb_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电压*/
		xb_vb_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电压*/
		xb_vb_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电压*/
		xb_vb_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电压*/
		xb_vb_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电压*/
		xb_vb_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电压*/
		xb_vb_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电压*/
		xb_vb_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电压*/
		
		
		xb_vc_02				decimal(14, 3) 	DEFAULT 0.0	,	/*C相2次谐波电压*/
		xb_vc_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电压*/
		xb_vc_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电压*/
		xb_vc_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电压*/
		xb_vc_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电压*/
		xb_vc_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电压*/
		xb_vc_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电压*/
		xb_vc_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电压*/
		xb_vc_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电压*/
		xb_vc_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电压*/
		xb_vc_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电压*/
		xb_vc_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电压*/
		xb_vc_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电压*/
		xb_vc_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电压*/
		xb_vc_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电压*/
		xb_vc_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电压*/
		xb_vc_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电压*/
		xb_vc_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电压*/
		xb_vc_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电压*/
		xb_vc_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相21次谐波电压*/
	
		
		xb_ia_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电流*/
		xb_ia_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电流*/
		xb_ia_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电流*/
		xb_ia_05			  decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电流*/
		xb_ia_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电流*/
		xb_ia_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电流*/
		xb_ia_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电流*/
		xb_ia_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电流*/
		xb_ia_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电流*/
		xb_ia_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电流*/
		xb_ia_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电流*/
		xb_ia_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电流*/
		xb_ia_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电流*/
		xb_ia_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电流*/
		xb_ia_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电流*/
		xb_ia_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电流*/
		xb_ia_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电流*/
		xb_ia_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电流*/
		xb_ia_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电流*/
		xb_ia_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电流*/
		
		
		xb_ib_02				decimal(14, 3) 	DEFAULT 0.0,	/*B相2次谐波电流*/
		xb_ib_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电流*/
		xb_ib_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电流*/
		xb_ib_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电流*/
		xb_ib_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电流*/
		xb_ib_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电流*/
		xb_ib_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电流*/
		xb_ib_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电流*/
		xb_ib_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电流*/
		xb_ib_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电流*/
		xb_ib_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电流*/
		xb_ib_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电流*/
		xb_ib_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电流*/
		xb_ib_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电流*/
		xb_ib_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电流*/
		xb_ib_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电流*/
		xb_ib_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电流*/
		xb_ib_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电流*/
		xb_ib_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电流*/
		xb_ib_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电流*/
				
		xb_ic_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相2次谐波电流*/
		xb_ic_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电流*/
		xb_ic_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电流*/
		xb_ic_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电流*/
		xb_ic_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电流*/
		xb_ic_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电流*/
		xb_ic_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电流*/
		xb_ic_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电流*/
		xb_ic_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电流*/
		xb_ic_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电流*/
		xb_ic_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电流*/
		xb_ic_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电流*/
		xb_ic_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电流*/
		xb_ic_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电流*/
		xb_ic_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电流*/
		xb_ic_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电流*/
		xb_ic_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电流*/
		xb_ic_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电流*/
		xb_ic_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电流*/
		xb_ic_21				decimal(14, 3) 	DEFAULT 0.0 	/*C相21次谐波电流*/
		
)
grant select on  ZMinXb200808 to public
create unique clustered index ZMinXb200808index1
	on ZMinXb200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束
--   '-------Create table 专变配变谐波含有率数据表(ZMinXRate200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinXRate200808')
begin
create table ZMinXRate200808
(
		rtu_id					int   					not null,
		mp_id						smallint   			not null,
		date						int							not null,
		time						int							not null,
		
		xbr_va						decimal(14, 3) 	DEFAULT 0.0 ,	/*A相总谐波电压含有率*/
		xbr_vb						decimal(14, 3) 	DEFAULT 0.0 ,	/*B相总谐波电压含有率*/
		xbr_vc						decimal(14, 3) 	DEFAULT 0.0 ,	/*C相总谐波电压含有率*/
		
		xbr_va_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电压含有率*/
		xbr_va_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电压含有率*/
		xbr_va_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电压含有率*/
		xbr_va_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电压含有率*/
		xbr_va_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电压含有率*/
		xbr_va_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电压含有率*/
		xbr_va_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电压含有率*/
		xbr_va_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电压含有率*/
		xbr_va_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电压含有率*/
		xbr_va_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电压含有率*/
		xbr_va_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电压含有率*/
		xbr_va_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电压含有率*/
		xbr_va_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电压含有率*/
		xbr_va_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电压含有率*/
		xbr_va_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电压含有率*/
		xbr_va_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电压含有率*/
		xbr_va_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电压含有率*/
		xbr_va_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电压含有率*/
		xbr_va_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电压含有率*/
		xbr_va_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电压含有率*/
		
		xbr_vb_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相2次谐波电压含有率*/
		xbr_vb_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电压含有率*/
		xbr_vb_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电压含有率*/
		xbr_vb_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电压含有率*/
		xbr_vb_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电压含有率*/
		xbr_vb_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电压含有率*/
		xbr_vb_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电压含有率*/
		xbr_vb_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电压含有率*/
		xbr_vb_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电压含有率*/
		xbr_vb_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电压含有率*/
		xbr_vb_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电压含有率*/
		xbr_vb_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电压含有率*/
		xbr_vb_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电压含有率*/
		xbr_vb_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电压含有率*/
		xbr_vb_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电压含有率*/
		xbr_vb_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电压含有率*/
		xbr_vb_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电压含有率*/
		xbr_vb_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电压含有率*/
		xbr_vb_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电压含有率*/
		xbr_vb_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电压含有率*/
	
		xbr_vc_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相2次谐波电压含有率*/
		xbr_vc_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电压含有率*/
		xbr_vc_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电压含有率*/
		xbr_vc_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电压含有率*/
		xbr_vc_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电压含有率*/
		xbr_vc_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电压含有率*/
		xbr_vc_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电压含有率*/
		xbr_vc_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电压含有率*/
		xbr_vc_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电压含有率*/
		xbr_vc_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电压含有率*/
		xbr_vc_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电压含有率*/
		xbr_vc_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电压含有率*/
		xbr_vc_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电压含有率*/
		xbr_vc_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电压含有率*/
		xbr_vc_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电压含有率*/
		xbr_vc_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电压含有率*/
		xbr_vc_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电压含有率*/
		xbr_vc_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电压含有率*/
		xbr_vc_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电压含有率*/
		xbr_vc_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相21次谐波电压含有率*/
			
		xbr_ia_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电流含有率*/
		xbr_ia_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电流含有率*/
		xbr_ia_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电流含有率*/
		xbr_ia_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电流含有率*/
		xbr_ia_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电流含有率*/
		xbr_ia_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电流含有率*/
		xbr_ia_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电流含有率*/
		xbr_ia_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电流含有率*/
		xbr_ia_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电流含有率*/
		xbr_ia_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电流含有率*/
		xbr_ia_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电流含有率*/
		xbr_ia_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电流含有率*/
		xbr_ia_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电流含有率*/
		xbr_ia_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电流含有率*/
		xbr_ia_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电流含有率*/
		xbr_ia_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电流含有率*/
		xbr_ia_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电流含有率*/
		xbr_ia_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电流含有率*/
		xbr_ia_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电流含有率*/
		xbr_ia_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电流含有率*/
				
		
		xbr_ib_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相2次谐波电流含有率*/
		xbr_ib_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电流含有率*/
		xbr_ib_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电流含有率*/
		xbr_ib_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电流含有率*/
		xbr_ib_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电流含有率*/
		xbr_ib_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电流含有率*/
		xbr_ib_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电流含有率*/
		xbr_ib_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电流含有率*/
		xbr_ib_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电流含有率*/
		xbr_ib_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电流含有率*/
		xbr_ib_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电流含有率*/
		xbr_ib_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电流含有率*/
		xbr_ib_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电流含有率*/
		xbr_ib_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电流含有率*/
		xbr_ib_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电流含有率*/
		xbr_ib_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电流含有率*/
		xbr_ib_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电流含有率*/
		xbr_ib_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电流含有率*/
		xbr_ib_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电流含有率*/
		xbr_ib_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电流含有率*/
		
		
		xbr_ic_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相2次谐波电流含有率*/
		xbr_ic_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电流含有率*/
		xbr_ic_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电流含有率*/
		xbr_ic_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电流含有率*/
		xbr_ic_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电流含有率*/
		xbr_ic_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电流含有率*/
		xbr_ic_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电流含有率*/
		xbr_ic_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电流含有率*/
		xbr_ic_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电流含有率*/
		xbr_ic_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电流含有率*/
		xbr_ic_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电流含有率*/
		xbr_ic_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电流含有率*/
		xbr_ic_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电流含有率*/
		xbr_ic_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电流含有率*/
		xbr_ic_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电流含有率*/
		xbr_ic_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电流含有率*/
		xbr_ic_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电流含有率*/
		xbr_ic_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电流含有率*/
		xbr_ic_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电流含有率*/
		xbr_ic_21				decimal(14, 3) 	DEFAULT 0.0 	/*C相21次谐波电流含有率*/
)
grant select on  ZMinXRate200808 to public
create unique clustered index ZMinXRate200808index1
	on ZMinXRate200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 专变配变分钟扩展数据表(ZMinExt200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinExt200808')
begin
create table ZMinExt200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	freq		decimal(14, 3) 	DEFAULT 0.0 ,	/*	电网频率*/
	
	vab			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前线电压Uab*/
	vbc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前线电压Ubc*/
	vca			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前线电压Uca*/
		
	s				decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总视在功率*/
	sa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前A相视在功率*/
	sb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前B相视在功率*/
	sc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前C相视在功率*/
			
	cs1			decimal(14, 3) 	DEFAULT 0.0 ,	/*	超前功率因数*/
	cs2			decimal(14, 3) 	DEFAULT 0.0 ,	/*	滞后功率因数*/
	
	jva			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波A相电压*/
	jvb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波B相电压*/
	jvc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波C相电压*/
	jia			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波A相电流*/
	jib			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波B相电流*/
	jic			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波C相电流*/	
	jp			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总基波有功功率*/
	jpa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前A相基波有功功率*/
	jpb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前B相基波有功功率*/
	jpc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前C相基波有功功率*/
	
	
	xva			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波A相电压占有量*/
	xvb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波B相电压占有量*/
	xvc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波C相电压占有量*/
	xia			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波A相电流占有量*/
	xib			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波B相电流占有量*/
	xic			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波C相电流占有量*/	
	xp			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波有功功率*/
	xpa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前A相谐波有功功率*/
	xpb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前B相谐波有功功率*/
	xpc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前C相谐波有功功率*/
	
	xvar		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波A相电压总畸变率*/
	xvbr		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波B相电压总畸变率*/
	xvcr		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波C相电压总畸变率*/
	xiar		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波A相电流总畸变率*/
	xibr		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波B相电流总畸变率*/
	xicr		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波C相电流总畸变率*/
	
	q1			decimal(14, 3) 	DEFAULT 0.0 ,	/*	一象限无功功率*/
	q2			decimal(14, 3) 	DEFAULT 0.0 ,	/*	二象限无功功率*/
	q3			decimal(14, 3) 	DEFAULT 0.0 ,	/*	三象限无功功率*/
	q4			decimal(14, 3) 	DEFAULT 0.0 ,	/*	四象限无功功率*/
		
	i2			decimal(14, 3) 	DEFAULT 0.0 ,	/*	负序电流*/
)
grant select on  ZMinExt200808 to public
create unique clustered index ZMinExt200808index1
	on ZMinExt200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 专变配变分钟电压电流分量数据表(ZMinUIWeight200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinUIWeight200808')
begin
create table ZMinUIWeight200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	zxva		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相正序电压分量*/
	fxva		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相负序电压分量*/
	lxva		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相零序电压分量*/
	zxvb		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相正序电压分量*/
	fxvb		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相负序电压分量*/
	lxvb		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相零序电压分量*/
	zxvc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相正序电压分量*/
	fxvc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相负序电压分量*/
	lxvc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相零序电压分量*/
	
	zxia		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相正序电流分量*/
	fxia		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相负序电流分量*/
	lxia		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相零序电流分量*/
	zxib		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相正序电流分量*/
	fxib		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相负序电流分量*/
	lxib		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相零序电流分量*/
	zxic		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相正序电流分量*/
	fxic		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相负序电流分量*/
	lxic		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相零序电流分量*/
)
grant select on  ZMinUIWeight200808 to public
create unique clustered index ZMinUIWeight200808index1
	on ZMinUIWeight200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	


--   '-------Create table 专变配变分钟电压电流不平衡度数据表(ZMinUIImbalance200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinUIImbalance200808')
begin
create table ZMinUIImbalance200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	v				decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相电压不平衡度*/
	v0			decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相零序电压不平衡度*/	
	v2			decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相负序电压不平衡度*/
	
	i				decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相电流不平衡度*/
	i0			decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相零序电流不平衡度*/	
	i2			decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相负序电流不平衡度*/

	va			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相电压*/
	vb			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相电压*/
	vc			decimal(14, 3) 	DEFAULT 0.0 ,	/*C相电压*/
	ia			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相电流*/
	ib			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相电流*/
	ic			decimal(14, 3) 	DEFAULT 0.0 ,	/*C相电流*/

	vab			decimal(14, 3) 	DEFAULT 0.0 ,	/*线电压Uab*/
	vbc			decimal(14, 3) 	DEFAULT 0.0 ,	/*线电压Ubc*/
	vca			decimal(14, 3) 	DEFAULT 0.0 ,	/*线电压Uca*/


	v_max				decimal(14, 3) 	DEFAULT 0.0 ,	/*电压不平衡最大值*/
	v_max_tm	int							DEFAULT 0 ,		  /*电压不平衡最大值发生时间*/
	i_max				decimal(14, 3) 	DEFAULT 0.0 ,	/*电流不平衡最大值*/
	i_max_tm	int							DEFAULT 0 ,		  /*电流不平衡最大值发生时间*/		
			
	v0_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*零序电压不平衡最大值*/
	v0_max_tm	int							DEFAULT 0 ,			/*零序电压不平衡最大值发生时间*/
	i0_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*零序电流不平衡最大值*/
	i0_max_tm	int							DEFAULT 0 ,			/*零序电流不平衡最大值发生时间*/		
	
	v2_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*负序电压不平衡最大值*/
	v2_max_tm	int							DEFAULT 0 ,			/*负序电压不平衡最大值发生时间*/
	i2_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*负序电流不平衡最大值*/
	i2_max_tm	int							DEFAULT 0 ,			/*负序电流不平衡最大值发生时间*/
)
grant select on  ZMinUIImbalance200808 to public
create unique clustered index ZMinUIImbalance200808index1
	on ZMinUIImbalance200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	


--   '-------Create table 专变配变分钟电压偏差合格率数据表(ZMinVPC200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinVPC200808')
begin
create table ZMinVPC200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	vapc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相电压偏差*/
	vbpc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相电压偏差*/
	vcpc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相电压偏差*/
	
	vahg		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相电压偏差合格率*/
	vbhg		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相电压偏差合格率*/
	vchg		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相电压偏差合格率*/
)
grant select on  ZMinVPC200808 to public
create unique clustered index ZMinVPC200808index1
	on ZMinVPC200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	




--   '-------Create table 专变配变高次谐波数据表(ZMinHXb200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinHXb200808')
begin
create table ZMinHXb200808
(
		rtu_id						int   					not null,
		mp_id							smallint   			not null,
		date							int							not null,
		time							int							not null,
		
		xb_va_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相22次谐波电压*/
		xb_va_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相23次谐波电压*/
		xb_va_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相24次谐波电压*/
		xb_va_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相25次谐波电压*/
		xb_va_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相26次谐波电压*/
		xb_va_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相27次谐波电压*/
		xb_va_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相28次谐波电压*/
		xb_va_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相29次谐波电压*/
		xb_va_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相30次谐波电压*/
		xb_va_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相31次谐波电压*/
		xb_va_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相32次谐波电压*/
		xb_va_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相33次谐波电压*/
		xb_va_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相34次谐波电压*/
		xb_va_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相35次谐波电压*/
		xb_va_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相36次谐波电压*/
		xb_va_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相37次谐波电压*/
		xb_va_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相38次谐波电压*/
		xb_va_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相39次谐波电压*/
		xb_va_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相40次谐波电压*/
		xb_va_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相41次谐波电压*/
		xb_va_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相42次谐波电压*/
		xb_va_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相43次谐波电压*/
		xb_va_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相44次谐波电压*/
		xb_va_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相45次谐波电压*/
		xb_va_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相46次谐波电压*/
		xb_va_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相47次谐波电压*/
		xb_va_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相48次谐波电压*/
		xb_va_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相49次谐波电压*/
		xb_va_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相50次谐波电压*/
				
		
		xb_vb_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相22次谐波电压*/
		xb_vb_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相23次谐波电压*/
		xb_vb_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相24次谐波电压*/
		xb_vb_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相25次谐波电压*/
		xb_vb_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相26次谐波电压*/
		xb_vb_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相27次谐波电压*/
		xb_vb_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相28次谐波电压*/
		xb_vb_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相29次谐波电压*/
		xb_vb_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相30次谐波电压*/
		xb_vb_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相31次谐波电压*/
		xb_vb_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相32次谐波电压*/
		xb_vb_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相33次谐波电压*/
		xb_vb_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相34次谐波电压*/
		xb_vb_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相35次谐波电压*/
		xb_vb_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相36次谐波电压*/
		xb_vb_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相37次谐波电压*/
		xb_vb_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相38次谐波电压*/
		xb_vb_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相39次谐波电压*/
		xb_vb_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相40次谐波电压*/
		xb_vb_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相41次谐波电压*/
		xb_vb_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相42次谐波电压*/
		xb_vb_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相43次谐波电压*/
		xb_vb_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相44次谐波电压*/
		xb_vb_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相45次谐波电压*/
		xb_vb_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相46次谐波电压*/
		xb_vb_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相47次谐波电压*/
		xb_vb_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相48次谐波电压*/
		xb_vb_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相49次谐波电压*/
		xb_vb_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相50次谐波电压*/
		
		
		xb_vc_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相22次谐波电压*/
		xb_vc_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相23次谐波电压*/
		xb_vc_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相24次谐波电压*/
		xb_vc_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相25次谐波电压*/
		xb_vc_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相26次谐波电压*/
		xb_vc_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相27次谐波电压*/
		xb_vc_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相28次谐波电压*/
		xb_vc_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相29次谐波电压*/
		xb_vc_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相30次谐波电压*/
		xb_vc_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相31次谐波电压*/
		xb_vc_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相32次谐波电压*/
		xb_vc_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相33次谐波电压*/
		xb_vc_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相34次谐波电压*/
		xb_vc_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相35次谐波电压*/
		xb_vc_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相36次谐波电压*/
		xb_vc_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相37次谐波电压*/
		xb_vc_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相38次谐波电压*/
		xb_vc_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相39次谐波电压*/
		xb_vc_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相40次谐波电压*/
		xb_vc_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相41次谐波电压*/
		xb_vc_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相42次谐波电压*/
		xb_vc_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相43次谐波电压*/
		xb_vc_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相44次谐波电压*/
		xb_vc_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相45次谐波电压*/
		xb_vc_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相46次谐波电压*/
		xb_vc_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相47次谐波电压*/
		xb_vc_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相48次谐波电压*/
		xb_vc_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相49次谐波电压*/
		xb_vc_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相50次谐波电压*/
	
		
		xb_ia_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相22次谐波电流*/
		xb_ia_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相23次谐波电流*/
		xb_ia_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相24次谐波电流*/
		xb_ia_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相25次谐波电流*/
		xb_ia_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相26次谐波电流*/
		xb_ia_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相27次谐波电流*/
		xb_ia_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相28次谐波电流*/
		xb_ia_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相29次谐波电流*/
		xb_ia_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相30次谐波电流*/
		xb_ia_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相31次谐波电流*/
		xb_ia_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相32次谐波电流*/
		xb_ia_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相33次谐波电流*/
		xb_ia_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相34次谐波电流*/
		xb_ia_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相35次谐波电流*/
		xb_ia_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相36次谐波电流*/
		xb_ia_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相37次谐波电流*/
		xb_ia_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相38次谐波电流*/
		xb_ia_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相39次谐波电流*/
		xb_ia_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相40次谐波电流*/
		xb_ia_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相41次谐波电流*/
		xb_ia_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相42次谐波电流*/
		xb_ia_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相43次谐波电流*/
		xb_ia_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相44次谐波电流*/
		xb_ia_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相45次谐波电流*/
		xb_ia_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相46次谐波电流*/
		xb_ia_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相47次谐波电流*/
		xb_ia_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相48次谐波电流*/
		xb_ia_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相49次谐波电流*/
		xb_ia_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相50次谐波电流*/
				
		
		xb_ib_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相22次谐波电流*/
		xb_ib_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相23次谐波电流*/
		xb_ib_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相24次谐波电流*/
		xb_ib_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相25次谐波电流*/
		xb_ib_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相26次谐波电流*/
		xb_ib_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相27次谐波电流*/
		xb_ib_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相28次谐波电流*/
		xb_ib_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相29次谐波电流*/
		xb_ib_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相30次谐波电流*/
		xb_ib_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相31次谐波电流*/
		xb_ib_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相32次谐波电流*/
		xb_ib_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相33次谐波电流*/
		xb_ib_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相34次谐波电流*/
		xb_ib_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相35次谐波电流*/
		xb_ib_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相36次谐波电流*/
		xb_ib_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相37次谐波电流*/
		xb_ib_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相38次谐波电流*/
		xb_ib_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相39次谐波电流*/
		xb_ib_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相40次谐波电流*/
		xb_ib_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相41次谐波电流*/
		xb_ib_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相42次谐波电流*/
		xb_ib_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相43次谐波电流*/
		xb_ib_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相44次谐波电流*/
		xb_ib_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相45次谐波电流*/
		xb_ib_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相46次谐波电流*/
		xb_ib_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相47次谐波电流*/
		xb_ib_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相48次谐波电流*/
		xb_ib_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相49次谐波电流*/
		xb_ib_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相50次谐波电流*/
		
		
		xb_ic_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相22次谐波电流*/
		xb_ic_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相23次谐波电流*/
		xb_ic_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相24次谐波电流*/
		xb_ic_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相25次谐波电流*/
		xb_ic_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相26次谐波电流*/
		xb_ic_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相27次谐波电流*/
		xb_ic_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相28次谐波电流*/
		xb_ic_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相29次谐波电流*/
		xb_ic_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相30次谐波电流*/
		xb_ic_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相31次谐波电流*/
		xb_ic_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相32次谐波电流*/
		xb_ic_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相33次谐波电流*/
		xb_ic_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相34次谐波电流*/
		xb_ic_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相35次谐波电流*/
		xb_ic_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相36次谐波电流*/
		xb_ic_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相37次谐波电流*/
		xb_ic_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相38次谐波电流*/
		xb_ic_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相39次谐波电流*/
		xb_ic_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相40次谐波电流*/
		xb_ic_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相41次谐波电流*/
		xb_ic_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相42次谐波电流*/
		xb_ic_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相43次谐波电流*/
		xb_ic_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相44次谐波电流*/
		xb_ic_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相45次谐波电流*/
		xb_ic_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相46次谐波电流*/
		xb_ic_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相47次谐波电流*/
		xb_ic_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相48次谐波电流*/
		xb_ic_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相49次谐波电流*/
		xb_ic_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相50次谐波电流*/
		
)
grant select on  ZMinHXb200808 to public
create unique clustered index ZMinHXb200808index1
	on ZMinHXb200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束
--   '-------Create table 专变配变高次谐波含有率数据表(ZMinHXRate200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinHXRate200808')
begin
create table ZMinHXRate200808
(
		rtu_id					int   					not null,
		mp_id						smallint   			not null,
		date						int							not null,
		time						int							not null,
				
		xbr_va_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相22次谐波电压含有率*/
		xbr_va_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相23次谐波电压含有率*/
		xbr_va_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相24次谐波电压含有率*/
		xbr_va_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相25次谐波电压含有率*/
		xbr_va_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相26次谐波电压含有率*/
		xbr_va_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相27次谐波电压含有率*/
		xbr_va_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相28次谐波电压含有率*/
		xbr_va_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相29次谐波电压含有率*/
		xbr_va_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相30次谐波电压含有率*/
		xbr_va_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相31次谐波电压含有率*/
		xbr_va_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相32次谐波电压含有率*/
		xbr_va_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相33次谐波电压含有率*/
		xbr_va_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相34次谐波电压含有率*/
		xbr_va_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相35次谐波电压含有率*/
		xbr_va_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相36次谐波电压含有率*/
		xbr_va_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相37次谐波电压含有率*/
		xbr_va_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相38次谐波电压含有率*/
		xbr_va_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相39次谐波电压含有率*/
		xbr_va_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相40次谐波电压含有率*/
		xbr_va_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相41次谐波电压含有率*/
		xbr_va_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相42次谐波电压含有率*/
		xbr_va_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相43次谐波电压含有率*/
		xbr_va_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相44次谐波电压含有率*/
		xbr_va_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相45次谐波电压含有率*/
		xbr_va_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相46次谐波电压含有率*/
		xbr_va_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相47次谐波电压含有率*/
		xbr_va_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相48次谐波电压含有率*/
		xbr_va_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相49次谐波电压含有率*/
		xbr_va_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相50次谐波电压含有率*/
		
		xbr_vb_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相22次谐波电压含有率*/
		xbr_vb_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相23次谐波电压含有率*/
		xbr_vb_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相24次谐波电压含有率*/
		xbr_vb_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相25次谐波电压含有率*/
		xbr_vb_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相26次谐波电压含有率*/
		xbr_vb_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相27次谐波电压含有率*/
		xbr_vb_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相28次谐波电压含有率*/
		xbr_vb_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相29次谐波电压含有率*/
		xbr_vb_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相30次谐波电压含有率*/
		xbr_vb_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相31次谐波电压含有率*/
		xbr_vb_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相32次谐波电压含有率*/
		xbr_vb_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相33次谐波电压含有率*/
		xbr_vb_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相34次谐波电压含有率*/
		xbr_vb_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相35次谐波电压含有率*/
		xbr_vb_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相36次谐波电压含有率*/
		xbr_vb_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相37次谐波电压含有率*/
		xbr_vb_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相38次谐波电压含有率*/
		xbr_vb_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相39次谐波电压含有率*/
		xbr_vb_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相40次谐波电压含有率*/
		xbr_vb_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相41次谐波电压含有率*/
		xbr_vb_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相42次谐波电压含有率*/
		xbr_vb_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相43次谐波电压含有率*/
		xbr_vb_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相44次谐波电压含有率*/
		xbr_vb_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相45次谐波电压含有率*/
		xbr_vb_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相46次谐波电压含有率*/
		xbr_vb_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相47次谐波电压含有率*/
		xbr_vb_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相48次谐波电压含有率*/
		xbr_vb_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相49次谐波电压含有率*/
		xbr_vb_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相50次谐波电压含有率*/
	
		xbr_vc_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相22次谐波电压含有率*/
		xbr_vc_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相23次谐波电压含有率*/
		xbr_vc_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相24次谐波电压含有率*/
		xbr_vc_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相25次谐波电压含有率*/
		xbr_vc_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相26次谐波电压含有率*/
		xbr_vc_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相27次谐波电压含有率*/
		xbr_vc_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相28次谐波电压含有率*/
		xbr_vc_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相29次谐波电压含有率*/
		xbr_vc_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相30次谐波电压含有率*/
		xbr_vc_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相31次谐波电压含有率*/
		xbr_vc_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相32次谐波电压含有率*/
		xbr_vc_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相33次谐波电压含有率*/
		xbr_vc_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相34次谐波电压含有率*/
		xbr_vc_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相35次谐波电压含有率*/
		xbr_vc_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相36次谐波电压含有率*/
		xbr_vc_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相37次谐波电压含有率*/
		xbr_vc_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相38次谐波电压含有率*/
		xbr_vc_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相39次谐波电压含有率*/
		xbr_vc_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相40次谐波电压含有率*/
		xbr_vc_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相41次谐波电压含有率*/
		xbr_vc_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相42次谐波电压含有率*/
		xbr_vc_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相43次谐波电压含有率*/
		xbr_vc_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相44次谐波电压含有率*/
		xbr_vc_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相45次谐波电压含有率*/
		xbr_vc_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相46次谐波电压含有率*/
		xbr_vc_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相47次谐波电压含有率*/
		xbr_vc_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相48次谐波电压含有率*/
		xbr_vc_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相49次谐波电压含有率*/
		xbr_vc_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相50次谐波电压含有率*/
			
		xbr_ia_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相22次谐波电流含有率*/
		xbr_ia_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相23次谐波电流含有率*/
		xbr_ia_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相24次谐波电流含有率*/
		xbr_ia_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相25次谐波电流含有率*/
		xbr_ia_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相26次谐波电流含有率*/
		xbr_ia_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相27次谐波电流含有率*/
		xbr_ia_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相28次谐波电流含有率*/
		xbr_ia_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相29次谐波电流含有率*/
		xbr_ia_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相30次谐波电流含有率*/
		xbr_ia_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相31次谐波电流含有率*/
		xbr_ia_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相32次谐波电流含有率*/
		xbr_ia_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相33次谐波电流含有率*/
		xbr_ia_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相34次谐波电流含有率*/
		xbr_ia_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相35次谐波电流含有率*/
		xbr_ia_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相36次谐波电流含有率*/
		xbr_ia_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相37次谐波电流含有率*/
		xbr_ia_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相38次谐波电流含有率*/
		xbr_ia_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相39次谐波电流含有率*/
		xbr_ia_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相40次谐波电流含有率*/
		xbr_ia_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相41次谐波电流含有率*/
		xbr_ia_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相42次谐波电流含有率*/
		xbr_ia_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相43次谐波电流含有率*/
		xbr_ia_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相44次谐波电流含有率*/
		xbr_ia_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相45次谐波电流含有率*/
		xbr_ia_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相46次谐波电流含有率*/
		xbr_ia_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相47次谐波电流含有率*/
		xbr_ia_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相48次谐波电流含有率*/
		xbr_ia_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相49次谐波电流含有率*/
		xbr_ia_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相50次谐波电流含有率*/
				
		
		xbr_ib_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相22次谐波电流含有率*/
		xbr_ib_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相23次谐波电流含有率*/
		xbr_ib_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相24次谐波电流含有率*/
		xbr_ib_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相25次谐波电流含有率*/
		xbr_ib_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相26次谐波电流含有率*/
		xbr_ib_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相27次谐波电流含有率*/
		xbr_ib_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相28次谐波电流含有率*/
		xbr_ib_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相29次谐波电流含有率*/
		xbr_ib_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相30次谐波电流含有率*/
		xbr_ib_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相31次谐波电流含有率*/
		xbr_ib_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相32次谐波电流含有率*/
		xbr_ib_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相33次谐波电流含有率*/
		xbr_ib_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相34次谐波电流含有率*/
		xbr_ib_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相35次谐波电流含有率*/
		xbr_ib_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相36次谐波电流含有率*/
		xbr_ib_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相37次谐波电流含有率*/
		xbr_ib_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相38次谐波电流含有率*/
		xbr_ib_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相39次谐波电流含有率*/
		xbr_ib_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相40次谐波电流含有率*/
		xbr_ib_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相41次谐波电流含有率*/
		xbr_ib_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相42次谐波电流含有率*/
		xbr_ib_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相43次谐波电流含有率*/
		xbr_ib_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相44次谐波电流含有率*/
		xbr_ib_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相45次谐波电流含有率*/
		xbr_ib_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相46次谐波电流含有率*/
		xbr_ib_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相47次谐波电流含有率*/
		xbr_ib_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相48次谐波电流含有率*/
		xbr_ib_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相49次谐波电流含有率*/
		xbr_ib_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相50次谐波电流含有率*/
		
		
		xbr_ic_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相22次谐波电流含有率*/
		xbr_ic_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相23次谐波电流含有率*/
		xbr_ic_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相24次谐波电流含有率*/
		xbr_ic_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相25次谐波电流含有率*/
		xbr_ic_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相26次谐波电流含有率*/
		xbr_ic_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相27次谐波电流含有率*/
		xbr_ic_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相28次谐波电流含有率*/
		xbr_ic_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相29次谐波电流含有率*/
		xbr_ic_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相30次谐波电流含有率*/
		xbr_ic_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相31次谐波电流含有率*/
		xbr_ic_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相32次谐波电流含有率*/
		xbr_ic_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相33次谐波电流含有率*/
		xbr_ic_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相34次谐波电流含有率*/
		xbr_ic_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相35次谐波电流含有率*/
		xbr_ic_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相36次谐波电流含有率*/
		xbr_ic_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相37次谐波电流含有率*/
		xbr_ic_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相38次谐波电流含有率*/
		xbr_ic_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相39次谐波电流含有率*/
		xbr_ic_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相40次谐波电流含有率*/
		xbr_ic_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相41次谐波电流含有率*/
		xbr_ic_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相42次谐波电流含有率*/
		xbr_ic_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相43次谐波电流含有率*/
		xbr_ic_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相44次谐波电流含有率*/
		xbr_ic_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相45次谐波电流含有率*/
		xbr_ic_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相46次谐波电流含有率*/
		xbr_ic_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相47次谐波电流含有率*/
		xbr_ic_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相48次谐波电流含有率*/
		xbr_ic_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相49次谐波电流含有率*/
		xbr_ic_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相50次谐波电流含有率*/
)
grant select on  ZMinHXRate200808 to public
create unique clustered index ZMinHXRate200808index1
	on ZMinHXRate200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 专变配变日电压电流负序零序不平衡数据表(ZDayUIFLBal200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayUIFLBal200808')
begin
create table ZDayUIFLBal200808
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,

		v_max				decimal(14, 3) 	DEFAULT 0.0 ,	/*电压不平衡度最大值*/
		v_max_tm		int							DEFAULT 0 ,		/*电压不平衡度最大值发生时间*/
		v_tm				int							DEFAULT 0 ,		/*电压不平衡度超限日累计时间*/		
		i_max				decimal(14, 3) 	DEFAULT 0.0 ,	/*电流不平衡度最大值*/
		i_max_tm		int							DEFAULT 0 ,		/*电流不平衡度最大值发生时间*/		
		i_tm				int							DEFAULT 0 ,		/*电流不平衡度超限日累计时间*/
				

		v0_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*零序电压不平衡度最大值*/
		v0_max_tm		int							DEFAULT 0 ,		/*零序电压不平衡度最大值发生时间*/
		v0_tm				int							DEFAULT 0 ,		/*零序电压不平衡度超限日累计时间*/		
		i0_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*零序电流不平衡度最大值*/
		i0_max_tm		int							DEFAULT 0 ,		/*零序电流不平衡度最大值发生时间*/		
		i0_tm				int							DEFAULT 0 ,		/*零序电流不平衡度超限日累计时间*/
		
		v2_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*负序电压不平衡度最大值*/
		v2_max_tm		int							DEFAULT 0 ,		/*负序电压不平衡度最大值发生时间*/
		v2_tm				int							DEFAULT 0 ,		/*负序电压不平衡度超限日累计时间*/		
		i2_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*负序电流不平衡度最大值*/
		i2_max_tm		int							DEFAULT 0 ,		/*负序电流不平衡度最大值发生时间*/		
		i2_tm				int							DEFAULT 0 ,		/*负序电流不平衡度超限日累计时间*/
)
grant select on  ZDayUIFLBal200808 to public
create unique clustered index ZDayUIFLBal200808index1
	on ZDayUIFLBal200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变日电压偏差合格率数据表(ZDayVPC200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayVPC200808')
begin
create table ZDayVPC200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	

	vapc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压偏差最大值*/
	vapc_max_tm		int							DEFAULT 0 ,			/*A相电压偏差最大值发生时间*/
	vahg_tm				int							DEFAULT 0 ,			/*A相电压偏差合格时间*/	
	vahgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压偏差合格率*/

	vbpc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压偏差最大值*/
	vbpc_max_tm		int							DEFAULT 0 ,			/*B相电压偏差最大值发生时间*/
	vbhg_tm				int							DEFAULT 0 ,			/*B相电压偏差合格时间*/	
	vbhgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压偏差合格率*/

	vcpc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压偏差最大值*/
	vcpc_max_tm		int							DEFAULT 0 ,			/*C相电压偏差最大值发生时间*/
	vchg_tm				int							DEFAULT 0 ,			/*C相电压偏差合格时间*/	
	vchgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压偏差合格率*/
)
grant select on  ZDayVPC200808 to public
create unique clustered index ZDayVPC200808index1
	on ZDayVPC200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 专变配变月电压偏差合格率数据表(ZMonVPC2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonVPC2008')
begin
create table ZMonVPC2008
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	

	vapc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压偏差最大值*/
	vapc_max_tm		int							DEFAULT 0 ,			/*A相电压偏差最大值发生时间*/
	vahg_tm				int							DEFAULT 0 ,			/*A相电压偏差合格时间*/	
	vahgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压偏差合格率*/

	vbpc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压偏差最大值*/
	vbpc_max_tm		int							DEFAULT 0 ,			/*B相电压偏差最大值发生时间*/
	vbhg_tm				int							DEFAULT 0 ,			/*B相电压偏差合格时间*/	
	vbhgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压偏差合格率*/

	vcpc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压偏差最大值*/
	vcpc_max_tm		int							DEFAULT 0 ,			/*C相电压偏差最大值发生时间*/
	vchg_tm				int							DEFAULT 0 ,			/*C相电压偏差合格时间*/	
	vchgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压偏差合格率*/
)
grant select on  ZMonVPC2008 to public
create unique clustered index ZMonVPC2008index1
	on ZMonVPC2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 专变配变分钟需量数据表(ZMinMaxXl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinMaxXl200808')
begin
create table ZMinMaxXl200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷需量*/	
	xl_zy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/	
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷需量*/
	xl_zw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总需量*/	
	xl_fw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷需量*/	
	xl_fw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	
	
	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 ,		/*反向无功尖需量时间*/	
)
grant select on  ZMinMaxXl200808 to public
create unique clustered index ZMinMaxXl200808index1
	on ZMinMaxXl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 专变配变分钟电压波动率数据表(ZMinVFLUT200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinVFLUT200808')
begin
create table ZMinVFLUT200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	vaflut	decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相电压波动*/
	vbflut	decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相电压波动*/
	vcflut	decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相电压波动*/
	
	vaflic	decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相电压突变/闪变*/
	vbflic	decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相电压突变/闪变*/
	vcflic	decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相电压突变/闪变*/
)
grant select on  ZMinVFLUT200808 to public
create unique clustered index ZMinVFLUT200808index1
	on ZMinVFLUT200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	



--   '-------Create table 专变配变谐波电流数据表(ZMonXbI2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonXbI2008')
begin
create table ZMonXbI2008
(
		rtu_id						int   					not null,
		mp_id							smallint   			not null,
		date							int							not null,
		time							int							not null,
		
		xb_ia_02					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电流最大值*/
		xb_ia_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电流最大值*/
		xb_ia_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电流最大值*/
		xb_ia_05			    decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电流最大值*/
		xb_ia_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电流最大值*/
		xb_ia_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电流最大值*/
		xb_ia_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电流最大值*/
		xb_ia_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电流最大值*/
		xb_ia_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电流最大值*/
		xb_ia_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电流最大值*/
		xb_ia_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电流最大值*/
		xb_ia_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电流最大值*/
		xb_ia_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电流最大值*/
		xb_ia_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电流最大值*/
		xb_ia_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电流最大值*/
		xb_ia_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电流最大值*/
		xb_ia_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电流最大值*/
		xb_ia_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电流最大值*/
		xb_ia_20					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电流最大值*/
		xb_ia_21					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电流最大值*/
		xb_ia							decimal(14, 3) 	DEFAULT 0.0 ,	/*A相总畸变电流最大值*/
		
		xb_ia_02_tm				int		DEFAULT 0 ,							/*A相2次谐波电流最大值发生时间*/
		xb_ia_03_tm				int		DEFAULT 0 ,							/*A相3次谐波电流最大值发生时间*/
		xb_ia_04_tm				int		DEFAULT 0 ,							/*A相4次谐波电流最大值发生时间*/
		xb_ia_05_tm				int		DEFAULT 0 ,							/*A相5次谐波电流最大值发生时间*/
		xb_ia_06_tm				int		DEFAULT 0 ,							/*A相6次谐波电流最大值发生时间*/
		xb_ia_07_tm				int		DEFAULT 0 ,							/*A相7次谐波电流最大值发生时间*/
		xb_ia_08_tm				int		DEFAULT 0 ,							/*A相8次谐波电流最大值发生时间*/
		xb_ia_09_tm				int		DEFAULT 0 ,							/*A相9次谐波电流最大值发生时间*/
		xb_ia_10_tm				int		DEFAULT 0 ,							/*A相10次谐波电流最大值发生时间*/
		xb_ia_11_tm				int		DEFAULT 0 ,							/*A相11次谐波电流最大值发生时间*/
		xb_ia_12_tm				int		DEFAULT 0 ,							/*A相12次谐波电流最大值发生时间*/
		xb_ia_13_tm				int		DEFAULT 0 ,							/*A相13次谐波电流最大值发生时间*/
		xb_ia_14_tm				int		DEFAULT 0 ,							/*A相14次谐波电流最大值发生时间*/
		xb_ia_15_tm				int		DEFAULT 0 ,							/*A相15次谐波电流最大值发生时间*/
		xb_ia_16_tm				int		DEFAULT 0 ,							/*A相16次谐波电流最大值发生时间*/
		xb_ia_17_tm				int		DEFAULT 0 ,							/*A相17次谐波电流最大值发生时间*/
		xb_ia_18_tm				int		DEFAULT 0 ,							/*A相18次谐波电流最大值发生时间*/
		xb_ia_19_tm				int		DEFAULT 0 ,							/*A相19次谐波电流最大值发生时间*/
		xb_ia_20_tm				int		DEFAULT 0 ,							/*A相20次谐波电流最大值发生时间*/
		xb_ia_21_tm				int		DEFAULT 0 ,							/*A相21次谐波电流最大值发生时间*/
		xb_ia_tm					int		DEFAULT 0 ,					 	  /*A相总畸变电流最大值发生时间*/
		
		
		xb_ib_02					decimal(14, 3) 	DEFAULT 0.0,	/*B相2次谐波电流最大值*/
		xb_ib_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电流最大值*/
		xb_ib_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电流最大值*/
		xb_ib_05					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电流最大值*/
		xb_ib_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电流最大值*/
		xb_ib_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电流最大值*/
		xb_ib_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电流最大值*/
		xb_ib_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电流最大值*/
		xb_ib_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电流最大值*/
		xb_ib_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电流最大值*/
		xb_ib_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电流最大值*/
		xb_ib_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电流最大值*/
		xb_ib_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电流最大值*/
		xb_ib_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电流最大值*/
		xb_ib_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电流最大值*/
		xb_ib_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电流最大值*/
		xb_ib_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电流最大值*/
		xb_ib_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电流最大值*/
		xb_ib_20					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电流最大值*/
		xb_ib_21					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电流最大值*/
		xb_ib							decimal(14, 3) 	DEFAULT 0.0 ,	/*B相总畸变电流最大值*/
		
		xb_ib_02_tm				int		DEFAULT 0 ,							/*B相2次谐波电流最大值发生时间*/
		xb_ib_03_tm				int		DEFAULT 0 ,							/*B相3次谐波电流最大值发生时间*/
		xb_ib_04_tm				int		DEFAULT 0 ,							/*B相4次谐波电流最大值发生时间*/
		xb_ib_05_tm				int		DEFAULT 0 ,							/*B相5次谐波电流最大值发生时间*/
		xb_ib_06_tm				int		DEFAULT 0 ,							/*B相6次谐波电流最大值发生时间*/
		xb_ib_07_tm				int		DEFAULT 0 ,							/*B相7次谐波电流最大值发生时间*/
		xb_ib_08_tm				int		DEFAULT 0 ,							/*B相8次谐波电流最大值发生时间*/
		xb_ib_09_tm				int		DEFAULT 0 ,							/*B相9次谐波电流最大值发生时间*/
		xb_ib_10_tm				int		DEFAULT 0 ,							/*B相10次谐波电流最大值发生时间*/
		xb_ib_11_tm				int		DEFAULT 0 ,							/*B相11次谐波电流最大值发生时间*/
		xb_ib_12_tm				int		DEFAULT 0 ,							/*B相12次谐波电流最大值发生时间*/
		xb_ib_13_tm				int		DEFAULT 0 ,							/*B相13次谐波电流最大值发生时间*/
		xb_ib_14_tm				int		DEFAULT 0 ,							/*B相14次谐波电流最大值发生时间*/
		xb_ib_15_tm				int		DEFAULT 0 ,							/*B相15次谐波电流最大值发生时间*/
		xb_ib_16_tm				int		DEFAULT 0 ,							/*B相16次谐波电流最大值发生时间*/
		xb_ib_17_tm				int		DEFAULT 0 ,							/*B相17次谐波电流最大值发生时间*/
		xb_ib_18_tm				int		DEFAULT 0 ,							/*B相18次谐波电流最大值发生时间*/
		xb_ib_19_tm				int		DEFAULT 0 ,							/*B相19次谐波电流最大值发生时间*/
		xb_ib_20_tm				int		DEFAULT 0 ,							/*B相20次谐波电流最大值发生时间*/
		xb_ib_21_tm				int		DEFAULT 0 ,							/*B相21次谐波电流最大值发生时间*/
		xb_ib_tm					int		DEFAULT 0 ,					 		/*B相总畸变电流最大值发生时间*/
		
		
		xb_ic_02					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相2次谐波电流最大值*/
		xb_ic_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电流最大值*/
		xb_ic_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电流最大值*/
		xb_ic_05					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电流最大值*/
		xb_ic_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电流最大值*/
		xb_ic_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电流最大值*/
		xb_ic_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电流最大值*/
		xb_ic_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电流最大值*/
		xb_ic_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电流最大值*/
		xb_ic_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电流最大值*/
		xb_ic_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电流最大值*/
		xb_ic_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电流最大值*/
		xb_ic_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电流最大值*/
		xb_ic_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电流最大值*/
		xb_ic_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电流最大值*/
		xb_ic_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电流最大值*/
		xb_ic_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电流最大值*/
		xb_ic_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电流最大值*/
		xb_ic_20					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电流最大值*/
		xb_ic_21					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相21次谐波电流最大值*/
		xb_ic							decimal(14, 3) 	DEFAULT 0.0 ,	/*C相总畸变电流最大值*/
		
		xb_ic_02_tm				int		DEFAULT 0 ,							/*C相2次谐波电流最大值发生时间*/
		xb_ic_03_tm				int		DEFAULT 0 ,							/*C相3次谐波电流最大值发生时间*/
		xb_ic_04_tm				int		DEFAULT 0 ,							/*C相4次谐波电流最大值发生时间*/
		xb_ic_05_tm				int		DEFAULT 0 ,							/*C相5次谐波电流最大值发生时间*/
		xb_ic_06_tm				int		DEFAULT 0 ,							/*C相6次谐波电流最大值发生时间*/
		xb_ic_07_tm				int		DEFAULT 0 ,							/*C相7次谐波电流最大值发生时间*/
		xb_ic_08_tm				int		DEFAULT 0 ,							/*C相8次谐波电流最大值发生时间*/
		xb_ic_09_tm				int		DEFAULT 0 ,							/*C相9次谐波电流最大值发生时间*/
		xb_ic_10_tm				int		DEFAULT 0 ,							/*C相10次谐波电流最大值发生时间*/
		xb_ic_11_tm				int		DEFAULT 0 ,							/*C相11次谐波电流最大值发生时间*/
		xb_ic_12_tm				int		DEFAULT 0 ,							/*C相12次谐波电流最大值发生时间*/
		xb_ic_13_tm				int		DEFAULT 0 ,							/*C相13次谐波电流最大值发生时间*/
		xb_ic_14_tm				int		DEFAULT 0 ,							/*C相14次谐波电流最大值发生时间*/
		xb_ic_15_tm				int		DEFAULT 0 ,							/*C相15次谐波电流最大值发生时间*/
		xb_ic_16_tm				int		DEFAULT 0 ,							/*C相16次谐波电流最大值发生时间*/
		xb_ic_17_tm				int		DEFAULT 0 ,							/*C相17次谐波电流最大值发生时间*/
		xb_ic_18_tm				int		DEFAULT 0 ,							/*C相18次谐波电流最大值发生时间*/
		xb_ic_19_tm				int		DEFAULT 0 ,							/*C相19次谐波电流最大值发生时间*/
		xb_ic_20_tm				int		DEFAULT 0 ,							/*C相20次谐波电流最大值发生时间*/
		xb_ic_21_tm				int		DEFAULT 0 ,							/*C相21次谐波电流最大值发生时间*/
		xb_ic_tm					int		DEFAULT 0 					 		/*C相总畸变电流最大值发生时间*/

)
grant select on  ZMonXbI2008 to public
create unique clustered index ZMonXbI2008index1
	on ZMonXbI2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变谐波电压数据表(ZMonXbU2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonXbU2008')
begin
create table ZMonXbU2008
(
		rtu_id					int   					not null,
		mp_id						smallint   			not null,
		date						int							not null,
		time						int							not null,
		
		xb_va_02				decimal(14, 3) 	DEFAULT 0.0,	/*A相2次谐波电压含有率最大值*/
		xb_va_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电压含有率最大值*/
		xb_va_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电压含有率最大值*/
		xb_va_05		    decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电压含有率最大值*/
		xb_va_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电压含有率最大值*/
		xb_va_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电压含有率最大值*/
		xb_va_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电压含有率最大值*/
		xb_va_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电压含有率最大值*/
		xb_va_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电压含有率最大值*/
		xb_va_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电压含有率最大值*/
		xb_va_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电压含有率最大值*/
		xb_va_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电压含有率最大值*/
		xb_va_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电压含有率最大值*/
		xb_va_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电压含有率最大值*/
		xb_va_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电压含有率最大值*/
		xb_va_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电压含有率最大值*/
		xb_va_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电压含有率最大值*/
		xb_va_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电压含有率最大值*/
		xb_va_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电压含有率最大值*/
		xb_va_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电压含有率最大值*/
		xb_va						decimal(14, 3) 	DEFAULT 0.0 ,	/*A相总畸变电流最大值*/
				
		xb_va_02_tm			int		DEFAULT 0 ,							/*A相2次谐波电压含有率最大值发生时间*/
		xb_va_03_tm			int		DEFAULT 0 ,							/*A相3次谐波电压含有率最大值发生时间*/
		xb_va_04_tm			int		DEFAULT 0 ,							/*A相4次谐波电压含有率最大值发生时间*/
		xb_va_05_tm			int		DEFAULT 0 ,							/*A相5次谐波电压含有率最大值发生时间*/
		xb_va_06_tm			int		DEFAULT 0 ,							/*A相6次谐波电压含有率最大值发生时间*/
		xb_va_07_tm			int		DEFAULT 0 ,							/*A相7次谐波电压含有率最大值发生时间*/
		xb_va_08_tm			int		DEFAULT 0 ,							/*A相8次谐波电压含有率最大值发生时间*/
		xb_va_09_tm			int		DEFAULT 0 ,							/*A相9次谐波电压含有率最大值发生时间*/
		xb_va_10_tm			int		DEFAULT 0 ,							/*A相10次谐波电压含有率最大值发生时间*/
		xb_va_11_tm			int		DEFAULT 0 ,							/*A相11次谐波电压含有率最大值发生时间*/
		xb_va_12_tm			int		DEFAULT 0 ,							/*A相12次谐波电压含有率最大值发生时间*/
		xb_va_13_tm			int		DEFAULT 0 ,							/*A相13次谐波电压含有率最大值发生时间*/
		xb_va_14_tm			int		DEFAULT 0 ,							/*A相14次谐波电压含有率最大值发生时间*/
		xb_va_15_tm			int		DEFAULT 0 ,							/*A相15次谐波电压含有率最大值发生时间*/
		xb_va_16_tm			int		DEFAULT 0 ,							/*A相16次谐波电压含有率最大值发生时间*/
		xb_va_17_tm			int		DEFAULT 0 ,							/*A相17次谐波电压含有率最大值发生时间*/
		xb_va_18_tm			int		DEFAULT 0 ,							/*A相18次谐波电压含有率最大值发生时间*/
		xb_va_19_tm			int		DEFAULT 0 ,							/*A相19次谐波电压含有率最大值发生时间*/
		xb_va_20_tm			int		DEFAULT 0 ,							/*A相20次谐波电压含有率最大值发生时间*/
		xb_va_21tm			int		DEFAULT 0 ,							/*A相21次谐波电压含有率最大值发生时间*/
		xb_va_tm				int		DEFAULT 0 ,					 		/*A相总畸变电流最大值发生时间*/
		
		xb_vb_02				decimal(14, 3) 	DEFAULT 0.0,	/*B相2次谐波电压含有率最大值*/
		xb_vb_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电压含有率最大值*/
		xb_vb_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电压含有率最大值*/
		xb_vb_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电压含有率最大值*/
		xb_vb_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电压含有率最大值*/
		xb_vb_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电压含有率最大值*/
		xb_vb_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电压含有率最大值*/
		xb_vb_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电压含有率最大值*/
		xb_vb_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电压含有率最大值*/
		xb_vb_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电压含有率最大值*/
		xb_vb_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电压含有率最大值*/
		xb_vb_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电压含有率最大值*/
		xb_vb_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电压含有率最大值*/
		xb_vb_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电压含有率最大值*/
		xb_vb_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电压含有率最大值*/
		xb_vb_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电压含有率最大值*/
		xb_vb_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电压含有率最大值*/
		xb_vb_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电压含有率最大值*/
		xb_vb_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电压含有率最大值*/
		xb_vb_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电压含有率最大值*/
		xb_vb						decimal(14, 3) 	DEFAULT 0.0 ,	/*B相总畸变电流最大值*/
		
		xb_vb_02_tm			int		DEFAULT 0 ,							/*B相2次谐波电压含有率最大值发生时间*/
		xb_vb_03_tm			int		DEFAULT 0 ,							/*B相3次谐波电压含有率最大值发生时间*/
		xb_vb_04_tm			int		DEFAULT 0 ,							/*B相4次谐波电压含有率最大值发生时间*/
		xb_vb_05_tm			int		DEFAULT 0 ,							/*B相5次谐波电压含有率最大值发生时间*/
		xb_vb_06_tm			int		DEFAULT 0 ,							/*B相6次谐波电压含有率最大值发生时间*/
		xb_vb_07_tm			int		DEFAULT 0 ,							/*B相7次谐波电压含有率最大值发生时间*/
		xb_vb_08_tm			int		DEFAULT 0 ,							/*B相8次谐波电压含有率最大值发生时间*/
		xb_vb_09_tm			int		DEFAULT 0 ,							/*B相9次谐波电压含有率最大值发生时间*/
		xb_vb_10_tm			int		DEFAULT 0 ,							/*B相10次谐波电压含有率最大值发生时间*/
		xb_vb_11_tm			int		DEFAULT 0 ,							/*B相11次谐波电压含有率最大值发生时间*/
		xb_vb_12_tm			int		DEFAULT 0 ,							/*B相12次谐波电压含有率最大值发生时间*/
		xb_vb_13_tm			int		DEFAULT 0 ,							/*B相13次谐波电压含有率最大值发生时间*/
		xb_vb_14_tm			int		DEFAULT 0 ,							/*B相14次谐波电压含有率最大值发生时间*/
		xb_vb_15_tm			int		DEFAULT 0 ,							/*B相15次谐波电压含有率最大值发生时间*/
		xb_vb_16_tm			int		DEFAULT 0 ,							/*B相16次谐波电压含有率最大值发生时间*/
		xb_vb_17_tm			int		DEFAULT 0 ,							/*B相17次谐波电压含有率最大值发生时间*/
		xb_vb_18_tm			int		DEFAULT 0 ,							/*B相18次谐波电压含有率最大值发生时间*/
		xb_vb_19_tm			int		DEFAULT 0 ,							/*B相19次谐波电压含有率最大值发生时间*/
		xb_vb_20_tm			int		DEFAULT 0 ,							/*B相20次谐波电压含有率最大值发生时间*/
		xb_vb_21_tm			int		DEFAULT 0 ,							/*B相21次谐波电压含有率最大值发生时间*/
		xb_vb_tm				int		DEFAULT 0 ,					 		/*B相总畸变电流最大值发生时间*/
		
		
		xb_vc_02				decimal(14, 3) 	DEFAULT 0.0	,	/*C相2次谐波电压含有率最大值*/
		xb_vc_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电压含有率最大值*/
		xb_vc_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电压含有率最大值*/
		xb_vc_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电压含有率最大值*/
		xb_vc_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电压含有率最大值*/
		xb_vc_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电压含有率最大值*/
		xb_vc_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电压含有率最大值*/
		xb_vc_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电压含有率最大值*/
		xb_vc_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电压含有率最大值*/
		xb_vc_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电压含有率最大值*/
		xb_vc_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电压含有率最大值*/
		xb_vc_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电压含有率最大值*/
		xb_vc_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电压含有率最大值*/
		xb_vc_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电压含有率最大值*/
		xb_vc_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电压含有率最大值*/
		xb_vc_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电压含有率最大值*/
		xb_vc_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电压含有率最大值*/
		xb_vc_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电压含有率最大值*/
		xb_vc_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电压含有率最大值*/
		xb_vc_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相21次谐波电压含有率最大值*/
		xb_vc						decimal(14, 3) 	DEFAULT 0.0 ,	/*C相总畸变电流最大值*/
		
		xb_vc_02_tm			int		DEFAULT 0 ,							/*C相2次谐波电压含有率最大值发生时间*/
		xb_vc_03_tm			int		DEFAULT 0 ,							/*C相3次谐波电压含有率最大值发生时间*/
		xb_vc_04_tm			int		DEFAULT 0 ,							/*C相4次谐波电压含有率最大值发生时间*/
		xb_vc_05_tm			int		DEFAULT 0 ,							/*C相5次谐波电压含有率最大值发生时间*/
		xb_vc_06_tm			int		DEFAULT 0 ,							/*C相6次谐波电压含有率最大值发生时间*/
		xb_vc_07_tm			int		DEFAULT 0 ,							/*C相7次谐波电压含有率最大值发生时间*/
		xb_vc_08_tm			int		DEFAULT 0 ,							/*C相8次谐波电压含有率最大值发生时间*/
		xb_vc_09_tm			int		DEFAULT 0 ,							/*C相9次谐波电压含有率最大值发生时间*/
		xb_vc_10_tm			int		DEFAULT 0 ,							/*C相10次谐波电压含有率最大值发生时间*/
		xb_vc_11_tm			int		DEFAULT 0 ,							/*C相11次谐波电压含有率最大值发生时间*/
		xb_vc_12_tm			int		DEFAULT 0 ,							/*C相12次谐波电压含有率最大值发生时间*/
		xb_vc_13_tm			int		DEFAULT 0 ,							/*C相13次谐波电压含有率最大值发生时间*/
		xb_vc_14_tm			int		DEFAULT 0 ,							/*C相14次谐波电压含有率最大值发生时间*/
		xb_vc_15_tm			int		DEFAULT 0 ,							/*C相15次谐波电压含有率最大值发生时间*/
		xb_vc_16_tm			int		DEFAULT 0 ,							/*C相16次谐波电压含有率最大值发生时间*/
		xb_vc_17_tm			int		DEFAULT 0 ,							/*C相17次谐波电压含有率最大值发生时间*/
		xb_vc_18_tm			int		DEFAULT 0 ,							/*C相18次谐波电压含有率最大值发生时间*/
		xb_vc_19_tm			int		DEFAULT 0 ,							/*C相19次谐波电压含有率最大值发生时间*/
		xb_vc_20_tm			int		DEFAULT 0 ,							/*C相20次谐波电压含有率最大值发生时间*/
		xb_vc_21_tm			int		DEFAULT 0 ,							/*C相21次谐波电压含有率最大值发生时间*/
		xb_vc_tm				int		DEFAULT 0 					 		/*C相总畸变电流最大值发生时间*/
)
grant select on  ZMonXbU2008 to public
create unique clustered index ZMonXbU2008index1
	on ZMonXbU2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 专变配变谐波越限数据表(ZMonXbLmt2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonXbLmt2008')
begin
create table ZMonXbLmt2008
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		
		xblmt_va			int		DEFAULT 0 ,			/*A相总畸变电压含有率越限累计时间*/
		xblmt_va_02		int		DEFAULT 0 ,			/*A相2次谐波电压含有率越限累计时间*/
		xblmt_va_03		int		DEFAULT 0 ,			/*A相3次谐波电压含有率越限累计时间*/
		xblmt_va_04		int		DEFAULT 0 ,			/*A相4次谐波电压含有率越限累计时间*/
		xblmt_va_05		int		DEFAULT 0 ,			/*A相5次谐波电压含有率越限累计时间*/
		xblmt_va_06		int		DEFAULT 0 ,			/*A相6次谐波电压含有率越限累计时间*/
		xblmt_va_07		int		DEFAULT 0 ,			/*A相7次谐波电压含有率越限累计时间*/
		xblmt_va_08		int		DEFAULT 0 ,			/*A相8次谐波电压含有率越限累计时间*/
		xblmt_va_09		int		DEFAULT 0 ,			/*A相9次谐波电压含有率越限累计时间*/
		xblmt_va_10		int		DEFAULT 0 ,			/*A相10次谐波电压含有率越限累计时间*/
		xblmt_va_11		int		DEFAULT 0 ,			/*A相11次谐波电压含有率越限累计时间*/
		xblmt_va_12		int		DEFAULT 0 ,			/*A相12次谐波电压含有率越限累计时间*/
		xblmt_va_13		int		DEFAULT 0 ,			/*A相13次谐波电压含有率越限累计时间*/
		xblmt_va_14		int		DEFAULT 0 ,			/*A相14次谐波电压含有率越限累计时间*/
		xblmt_va_15		int		DEFAULT 0 ,			/*A相15次谐波电压含有率越限累计时间*/
		xblmt_va_16		int		DEFAULT 0 ,			/*A相16次谐波电压含有率越限累计时间*/
		xblmt_va_17		int		DEFAULT 0 ,			/*A相17次谐波电压含有率越限累计时间*/
		xblmt_va_18		int		DEFAULT 0 ,			/*A相18次谐波电压含有率越限累计时间*/
		xblmt_va_19		int		DEFAULT 0 ,			/*A相19次谐波电压含有率越限累计时间*/
		xblmt_va_20		int		DEFAULT 0 ,			/*A相20次谐波电压含有率越限累计时间*/
		xblmt_va_21		int		DEFAULT 0 ,			/*A相21次谐波电压含有率越限累计时间*/
		xblmt_ia			int		DEFAULT 0 ,			/*A相总畸变电流越限累计时间*/
		xblmt_ia_02		int		DEFAULT 0 ,			/*A相2次谐波电流越限累计时间*/
		xblmt_ia_03		int		DEFAULT 0 ,			/*A相3次谐波电流越限累计时间*/
		xblmt_ia_04		int		DEFAULT 0 ,			/*A相4次谐波电流越限累计时间*/
		xblmt_ia_05		int		DEFAULT 0 ,			/*A相5次谐波电流越限累计时间*/
		xblmt_ia_06		int		DEFAULT 0 ,			/*A相6次谐波电流越限累计时间*/
		xblmt_ia_07		int		DEFAULT 0 ,			/*A相7次谐波电流越限累计时间*/
		xblmt_ia_08		int		DEFAULT 0 ,			/*A相8次谐波电流越限累计时间*/
		xblmt_ia_09		int		DEFAULT 0 ,			/*A相9次谐波电流越限累计时间*/
		xblmt_ia_10		int		DEFAULT 0 ,			/*A相10次谐波电流越限累计时间*/
		xblmt_ia_11		int		DEFAULT 0 ,			/*A相11次谐波电流越限累计时间*/
		xblmt_ia_12		int		DEFAULT 0 ,			/*A相12次谐波电流越限累计时间*/
		xblmt_ia_13		int		DEFAULT 0 ,			/*A相13次谐波电流越限累计时间*/
		xblmt_ia_14		int		DEFAULT 0 ,			/*A相14次谐波电流越限累计时间*/
		xblmt_ia_15		int		DEFAULT 0 ,			/*A相15次谐波电流越限累计时间*/
		xblmt_ia_16		int		DEFAULT 0 ,			/*A相16次谐波电流越限累计时间*/
		xblmt_ia_17		int		DEFAULT 0 ,			/*A相17次谐波电流越限累计时间*/
		xblmt_ia_18		int		DEFAULT 0 ,			/*A相18次谐波电流越限累计时间*/
		xblmt_ia_19		int		DEFAULT 0 ,			/*A相19次谐波电流越限累计时间*/
		xblmt_ia_20		int		DEFAULT 0 ,			/*A相20次谐波电流越限累计时间*/
		xblmt_ia_21		int		DEFAULT 0 ,			/*A相21次谐波电流越限累计时间*/
		xblmt_vb			int		DEFAULT 0 ,			/*B相总畸变电压含有率越限累计时间*/
		xblmt_vb_02		int		DEFAULT 0 ,			/*B相2次谐波电压含有率越限累计时间*/
		xblmt_vb_03		int		DEFAULT 0 ,			/*B相3次谐波电压含有率越限累计时间*/
		xblmt_vb_04		int		DEFAULT 0 ,			/*B相4次谐波电压含有率越限累计时间*/
		xblmt_vb_05		int		DEFAULT 0 ,			/*B相5次谐波电压含有率越限累计时间*/
		xblmt_vb_06		int		DEFAULT 0 ,			/*B相6次谐波电压含有率越限累计时间*/
		xblmt_vb_07		int		DEFAULT 0 ,			/*B相7次谐波电压含有率越限累计时间*/
		xblmt_vb_08		int		DEFAULT 0 ,			/*B相8次谐波电压含有率越限累计时间*/
		xblmt_vb_09		int		DEFAULT 0 ,			/*B相9次谐波电压含有率越限累计时间*/
		xblmt_vb_10		int		DEFAULT 0 ,			/*B相10次谐波电压含有率越限累计时间*/
		xblmt_vb_11		int		DEFAULT 0 ,			/*B相11次谐波电压含有率越限累计时间*/
		xblmt_vb_12		int		DEFAULT 0 ,			/*B相12次谐波电压含有率越限累计时间*/
		xblmt_vb_13		int		DEFAULT 0 ,			/*B相13次谐波电压含有率越限累计时间*/
		xblmt_vb_14		int		DEFAULT 0 ,			/*B相14次谐波电压含有率越限累计时间*/
		xblmt_vb_15		int		DEFAULT 0 ,			/*B相15次谐波电压含有率越限累计时间*/
		xblmt_vb_16		int		DEFAULT 0 ,			/*B相16次谐波电压含有率越限累计时间*/
		xblmt_vb_17		int		DEFAULT 0 ,			/*B相17次谐波电压含有率越限累计时间*/
		xblmt_vb_18		int		DEFAULT 0 ,			/*B相18次谐波电压含有率越限累计时间*/
		xblmt_vb_19		int		DEFAULT 0 ,			/*B相19次谐波电压含有率越限累计时间*/
		xblmt_vb_20		int		DEFAULT 0 ,			/*B相20次谐波电压含有率越限累计时间*/
		xblmt_vb_21		int		DEFAULT 0 ,			/*B相21次谐波电压含有率越限累计时间*/
		xblmt_ib			int		DEFAULT 0 ,			/*B相总畸变电流越限累计时间*/
		xblmt_ib_02		int		DEFAULT 0 ,			/*B相2次谐波电流越限累计时间*/
		xblmt_ib_03		int		DEFAULT 0 ,			/*B相3次谐波电流越限累计时间*/
		xblmt_ib_04		int		DEFAULT 0 ,			/*B相4次谐波电流越限累计时间*/
		xblmt_ib_05		int		DEFAULT 0 ,			/*B相5次谐波电流越限累计时间*/
		xblmt_ib_06		int		DEFAULT 0 ,			/*B相6次谐波电流越限累计时间*/
		xblmt_ib_07		int		DEFAULT 0 ,			/*B相7次谐波电流越限累计时间*/
		xblmt_ib_08		int		DEFAULT 0 ,			/*B相8次谐波电流越限累计时间*/
		xblmt_ib_09		int		DEFAULT 0 ,			/*B相9次谐波电流越限累计时间*/
		xblmt_ib_10		int		DEFAULT 0 ,			/*B相10次谐波电流越限累计时间*/
		xblmt_ib_11		int		DEFAULT 0 ,			/*B相11次谐波电流越限累计时间*/
		xblmt_ib_12		int		DEFAULT 0 ,			/*B相12次谐波电流越限累计时间*/
		xblmt_ib_13		int		DEFAULT 0 ,			/*B相13次谐波电流越限累计时间*/
		xblmt_ib_14		int		DEFAULT 0 ,			/*B相14次谐波电流越限累计时间*/
		xblmt_ib_15		int		DEFAULT 0 ,			/*B相15次谐波电流越限累计时间*/
		xblmt_ib_16		int		DEFAULT 0 ,			/*B相16次谐波电流越限累计时间*/
		xblmt_ib_17		int		DEFAULT 0 ,			/*B相17次谐波电流越限累计时间*/
		xblmt_ib_18		int		DEFAULT 0 ,			/*B相18次谐波电流越限累计时间*/
		xblmt_ib_19		int		DEFAULT 0 ,			/*B相19次谐波电流越限累计时间*/
		xblmt_ib_20		int		DEFAULT 0 ,			/*B相20次谐波电流越限累计时间*/
		xblmt_ib_21		int		DEFAULT 0 ,			/*B相21次谐波电流越限累计时间*/
		xblmt_vc			int		DEFAULT 0 ,			/*C相总畸变电压含有率越限累计时间*/
		xblmt_vc_02		int		DEFAULT 0 ,			/*C相2次谐波电压含有率越限累计时间*/
		xblmt_vc_03		int		DEFAULT 0 ,			/*C相3次谐波电压含有率越限累计时间*/
		xblmt_vc_04		int		DEFAULT 0 ,			/*C相4次谐波电压含有率越限累计时间*/
		xblmt_vc_05		int		DEFAULT 0 ,			/*C相5次谐波电压含有率越限累计时间*/
		xblmt_vc_06		int		DEFAULT 0 ,			/*C相6次谐波电压含有率越限累计时间*/
		xblmt_vc_07		int		DEFAULT 0 ,			/*C相7次谐波电压含有率越限累计时间*/
		xblmt_vc_08		int		DEFAULT 0 ,			/*C相8次谐波电压含有率越限累计时间*/
		xblmt_vc_09		int		DEFAULT 0 ,			/*C相9次谐波电压含有率越限累计时间*/
		xblmt_vc_10		int		DEFAULT 0 ,			/*C相10次谐波电压含有率越限累计时间*/
		xblmt_vc_11		int		DEFAULT 0 ,			/*C相11次谐波电压含有率越限累计时间*/
		xblmt_vc_12		int		DEFAULT 0 ,			/*C相12次谐波电压含有率越限累计时间*/
		xblmt_vc_13		int		DEFAULT 0 ,			/*C相13次谐波电压含有率越限累计时间*/
		xblmt_vc_14		int		DEFAULT 0 ,			/*C相14次谐波电压含有率越限累计时间*/
		xblmt_vc_15		int		DEFAULT 0 ,			/*C相15次谐波电压含有率越限累计时间*/
		xblmt_vc_16		int		DEFAULT 0 ,			/*C相16次谐波电压含有率越限累计时间*/
		xblmt_vc_17		int		DEFAULT 0 ,			/*C相17次谐波电压含有率越限累计时间*/
		xblmt_vc_18		int		DEFAULT 0 ,			/*C相18次谐波电压含有率越限累计时间*/
		xblmt_vc_19		int		DEFAULT 0 ,			/*C相19次谐波电压含有率越限累计时间*/
		xblmt_vc_20		int		DEFAULT 0 ,			/*C相20次谐波电压含有率越限累计时间*/
		xblmt_vc_21		int		DEFAULT 0 ,			/*C相21次谐波电压含有率越限累计时间*/
		xblmt_ic			int		DEFAULT 0 ,			/*C相总畸变电流越限累计时间*/
		xblmt_ic_02		int		DEFAULT 0 ,			/*C相2次谐波电流越限累计时间*/
		xblmt_ic_03		int		DEFAULT 0 ,			/*C相3次谐波电流越限累计时间*/
		xblmt_ic_04		int		DEFAULT 0 ,			/*C相4次谐波电流越限累计时间*/
		xblmt_ic_05		int		DEFAULT 0 ,			/*C相5次谐波电流越限累计时间*/
		xblmt_ic_06		int		DEFAULT 0 ,			/*C相6次谐波电流越限累计时间*/
		xblmt_ic_07		int		DEFAULT 0 ,			/*C相7次谐波电流越限累计时间*/
		xblmt_ic_08		int		DEFAULT 0 ,			/*C相8次谐波电流越限累计时间*/
		xblmt_ic_09		int		DEFAULT 0 ,			/*C相9次谐波电流越限累计时间*/
		xblmt_ic_10		int		DEFAULT 0 ,			/*C相10次谐波电流越限累计时间*/
		xblmt_ic_11		int		DEFAULT 0 ,			/*C相11次谐波电流越限累计时间*/
		xblmt_ic_12		int		DEFAULT 0 ,			/*C相12次谐波电流越限累计时间*/
		xblmt_ic_13		int		DEFAULT 0 ,			/*C相13次谐波电流越限累计时间*/
		xblmt_ic_14		int		DEFAULT 0 ,			/*C相14次谐波电流越限累计时间*/
		xblmt_ic_15		int		DEFAULT 0 ,			/*C相15次谐波电流越限累计时间*/
		xblmt_ic_16		int		DEFAULT 0 ,			/*C相16次谐波电流越限累计时间*/
		xblmt_ic_17		int		DEFAULT 0 ,			/*C相17次谐波电流越限累计时间*/
		xblmt_ic_18		int		DEFAULT 0 ,			/*C相18次谐波电流越限累计时间*/
		xblmt_ic_19		int		DEFAULT 0 ,			/*C相19次谐波电流越限累计时间*/
		xblmt_ic_20		int		DEFAULT 0 ,			/*C相20次谐波电流越限累计时间*/
		xblmt_ic_21		int		DEFAULT 0 			/*C相21次谐波电流越限累计时间*/
)
grant select on  ZMonXbLmt2008 to public
create unique clustered index ZMonXbLmt2008index1
	on ZMonXbLmt2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--太钢谐波 20151215
--   '-------Create table 变电站谐波数据表(BMinXb200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinXb200808')
begin
create table BMinXb200808
(
		rtu_id						int   					not null,
		mp_id							smallint   			not null,
		date							int							not null,
		time							int							not null,
		
		xb_va_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电压*/
		xb_va_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电压*/
		xb_va_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电压*/
		xb_va_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电压*/
		xb_va_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电压*/
		xb_va_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电压*/
		xb_va_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电压*/
		xb_va_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电压*/
		xb_va_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电压*/
		xb_va_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电压*/
		xb_va_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电压*/
		xb_va_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电压*/
		xb_va_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电压*/
		xb_va_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电压*/
		xb_va_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电压*/
		xb_va_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电压*/
		xb_va_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电压*/
		xb_va_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电压*/
		xb_va_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电压*/
		xb_va_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电压*/
				
		
		xb_vb_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相2次谐波电压*/
		xb_vb_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电压*/
		xb_vb_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电压*/
		xb_vb_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电压*/
		xb_vb_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电压*/
		xb_vb_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电压*/
		xb_vb_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电压*/
		xb_vb_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电压*/
		xb_vb_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电压*/
		xb_vb_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电压*/
		xb_vb_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电压*/
		xb_vb_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电压*/
		xb_vb_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电压*/
		xb_vb_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电压*/
		xb_vb_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电压*/
		xb_vb_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电压*/
		xb_vb_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电压*/
		xb_vb_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电压*/
		xb_vb_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电压*/
		xb_vb_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电压*/
		
		
		xb_vc_02				decimal(14, 3) 	DEFAULT 0.0	,	/*C相2次谐波电压*/
		xb_vc_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电压*/
		xb_vc_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电压*/
		xb_vc_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电压*/
		xb_vc_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电压*/
		xb_vc_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电压*/
		xb_vc_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电压*/
		xb_vc_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电压*/
		xb_vc_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电压*/
		xb_vc_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电压*/
		xb_vc_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电压*/
		xb_vc_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电压*/
		xb_vc_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电压*/
		xb_vc_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电压*/
		xb_vc_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电压*/
		xb_vc_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电压*/
		xb_vc_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电压*/
		xb_vc_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电压*/
		xb_vc_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电压*/
		xb_vc_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相21次谐波电压*/
	
		
		xb_ia_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电流*/
		xb_ia_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电流*/
		xb_ia_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电流*/
		xb_ia_05			  decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电流*/
		xb_ia_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电流*/
		xb_ia_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电流*/
		xb_ia_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电流*/
		xb_ia_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电流*/
		xb_ia_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电流*/
		xb_ia_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电流*/
		xb_ia_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电流*/
		xb_ia_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电流*/
		xb_ia_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电流*/
		xb_ia_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电流*/
		xb_ia_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电流*/
		xb_ia_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电流*/
		xb_ia_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电流*/
		xb_ia_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电流*/
		xb_ia_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电流*/
		xb_ia_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电流*/
		
		
		xb_ib_02				decimal(14, 3) 	DEFAULT 0.0,	/*B相2次谐波电流*/
		xb_ib_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电流*/
		xb_ib_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电流*/
		xb_ib_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电流*/
		xb_ib_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电流*/
		xb_ib_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电流*/
		xb_ib_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电流*/
		xb_ib_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电流*/
		xb_ib_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电流*/
		xb_ib_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电流*/
		xb_ib_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电流*/
		xb_ib_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电流*/
		xb_ib_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电流*/
		xb_ib_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电流*/
		xb_ib_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电流*/
		xb_ib_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电流*/
		xb_ib_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电流*/
		xb_ib_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电流*/
		xb_ib_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电流*/
		xb_ib_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电流*/
				
		xb_ic_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相2次谐波电流*/
		xb_ic_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电流*/
		xb_ic_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电流*/
		xb_ic_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电流*/
		xb_ic_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电流*/
		xb_ic_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电流*/
		xb_ic_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电流*/
		xb_ic_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电流*/
		xb_ic_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电流*/
		xb_ic_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电流*/
		xb_ic_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电流*/
		xb_ic_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电流*/
		xb_ic_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电流*/
		xb_ic_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电流*/
		xb_ic_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电流*/
		xb_ic_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电流*/
		xb_ic_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电流*/
		xb_ic_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电流*/
		xb_ic_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电流*/
		xb_ic_21				decimal(14, 3) 	DEFAULT 0.0 	/*C相21次谐波电流*/
		
)
grant select on  BMinXb200808 to public
create unique clustered index BMinXb200808index1
	on BMinXb200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束
--   '-------Create table 变电站谐波含有率数据表(BMinXRate200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinXRate200808')
begin
create table BMinXRate200808
(
		rtu_id					int   					not null,
		mp_id						smallint   			not null,
		date						int							not null,
		time						int							not null,
		
		xbr_va						decimal(14, 3) 	DEFAULT 0.0 ,	/*A相总谐波电压含有率*/
		xbr_vb						decimal(14, 3) 	DEFAULT 0.0 ,	/*B相总谐波电压含有率*/
		xbr_vc						decimal(14, 3) 	DEFAULT 0.0 ,	/*C相总谐波电压含有率*/
		
		xbr_va_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电压含有率*/
		xbr_va_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电压含有率*/
		xbr_va_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电压含有率*/
		xbr_va_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电压含有率*/
		xbr_va_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电压含有率*/
		xbr_va_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电压含有率*/
		xbr_va_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电压含有率*/
		xbr_va_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电压含有率*/
		xbr_va_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电压含有率*/
		xbr_va_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电压含有率*/
		xbr_va_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电压含有率*/
		xbr_va_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电压含有率*/
		xbr_va_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电压含有率*/
		xbr_va_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电压含有率*/
		xbr_va_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电压含有率*/
		xbr_va_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电压含有率*/
		xbr_va_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电压含有率*/
		xbr_va_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电压含有率*/
		xbr_va_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电压含有率*/
		xbr_va_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电压含有率*/
		
		xbr_vb_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相2次谐波电压含有率*/
		xbr_vb_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电压含有率*/
		xbr_vb_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电压含有率*/
		xbr_vb_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电压含有率*/
		xbr_vb_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电压含有率*/
		xbr_vb_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电压含有率*/
		xbr_vb_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电压含有率*/
		xbr_vb_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电压含有率*/
		xbr_vb_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电压含有率*/
		xbr_vb_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电压含有率*/
		xbr_vb_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电压含有率*/
		xbr_vb_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电压含有率*/
		xbr_vb_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电压含有率*/
		xbr_vb_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电压含有率*/
		xbr_vb_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电压含有率*/
		xbr_vb_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电压含有率*/
		xbr_vb_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电压含有率*/
		xbr_vb_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电压含有率*/
		xbr_vb_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电压含有率*/
		xbr_vb_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电压含有率*/
	
		xbr_vc_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相2次谐波电压含有率*/
		xbr_vc_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电压含有率*/
		xbr_vc_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电压含有率*/
		xbr_vc_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电压含有率*/
		xbr_vc_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电压含有率*/
		xbr_vc_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电压含有率*/
		xbr_vc_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电压含有率*/
		xbr_vc_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电压含有率*/
		xbr_vc_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电压含有率*/
		xbr_vc_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电压含有率*/
		xbr_vc_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电压含有率*/
		xbr_vc_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电压含有率*/
		xbr_vc_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电压含有率*/
		xbr_vc_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电压含有率*/
		xbr_vc_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电压含有率*/
		xbr_vc_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电压含有率*/
		xbr_vc_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电压含有率*/
		xbr_vc_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电压含有率*/
		xbr_vc_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电压含有率*/
		xbr_vc_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相21次谐波电压含有率*/
			
		xbr_ia_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电流含有率*/
		xbr_ia_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电流含有率*/
		xbr_ia_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电流含有率*/
		xbr_ia_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电流含有率*/
		xbr_ia_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电流含有率*/
		xbr_ia_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电流含有率*/
		xbr_ia_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电流含有率*/
		xbr_ia_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电流含有率*/
		xbr_ia_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电流含有率*/
		xbr_ia_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电流含有率*/
		xbr_ia_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电流含有率*/
		xbr_ia_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电流含有率*/
		xbr_ia_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电流含有率*/
		xbr_ia_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电流含有率*/
		xbr_ia_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电流含有率*/
		xbr_ia_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电流含有率*/
		xbr_ia_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电流含有率*/
		xbr_ia_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电流含有率*/
		xbr_ia_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电流含有率*/
		xbr_ia_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电流含有率*/
				
		
		xbr_ib_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相2次谐波电流含有率*/
		xbr_ib_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电流含有率*/
		xbr_ib_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电流含有率*/
		xbr_ib_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电流含有率*/
		xbr_ib_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电流含有率*/
		xbr_ib_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电流含有率*/
		xbr_ib_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电流含有率*/
		xbr_ib_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电流含有率*/
		xbr_ib_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电流含有率*/
		xbr_ib_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电流含有率*/
		xbr_ib_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电流含有率*/
		xbr_ib_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电流含有率*/
		xbr_ib_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电流含有率*/
		xbr_ib_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电流含有率*/
		xbr_ib_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电流含有率*/
		xbr_ib_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电流含有率*/
		xbr_ib_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电流含有率*/
		xbr_ib_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电流含有率*/
		xbr_ib_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电流含有率*/
		xbr_ib_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电流含有率*/
		
		
		xbr_ic_02				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相2次谐波电流含有率*/
		xbr_ic_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电流含有率*/
		xbr_ic_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电流含有率*/
		xbr_ic_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电流含有率*/
		xbr_ic_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电流含有率*/
		xbr_ic_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电流含有率*/
		xbr_ic_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电流含有率*/
		xbr_ic_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电流含有率*/
		xbr_ic_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电流含有率*/
		xbr_ic_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电流含有率*/
		xbr_ic_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电流含有率*/
		xbr_ic_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电流含有率*/
		xbr_ic_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电流含有率*/
		xbr_ic_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电流含有率*/
		xbr_ic_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电流含有率*/
		xbr_ic_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电流含有率*/
		xbr_ic_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电流含有率*/
		xbr_ic_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电流含有率*/
		xbr_ic_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电流含有率*/
		xbr_ic_21				decimal(14, 3) 	DEFAULT 0.0 	/*C相21次谐波电流含有率*/
)
grant select on  BMinXRate200808 to public
create unique clustered index BMinXRate200808index1
	on BMinXRate200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 变电站分钟扩展数据表(BMinExt200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinExt200808')
begin
create table BMinExt200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	freq		decimal(14, 3) 	DEFAULT 0.0 ,	/*	电网频率*/
	
	vab			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前线电压Uab*/
	vbc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前线电压Ubc*/
	vca			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前线电压Uca*/
		
	s				decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总视在功率*/
	sa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前A相视在功率*/
	sb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前B相视在功率*/
	sc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前C相视在功率*/
			
	cs1			decimal(14, 3) 	DEFAULT 0.0 ,	/*	超前功率因数*/
	cs2			decimal(14, 3) 	DEFAULT 0.0 ,	/*	滞后功率因数*/
	
	jva			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波A相电压*/
	jvb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波B相电压*/
	jvc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波C相电压*/
	jia			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波A相电流*/
	jib			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波B相电流*/
	jic			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前基波C相电流*/	
	jp			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总基波有功功率*/
	jpa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前A相基波有功功率*/
	jpb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前B相基波有功功率*/
	jpc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前C相基波有功功率*/
	
	
	xva			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波A相电压占有量*/
	xvb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波B相电压占有量*/
	xvc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波C相电压占有量*/
	xia			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波A相电流占有量*/
	xib			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波B相电流占有量*/
	xic			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波C相电流占有量*/	
	xp			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前总谐波有功功率*/
	xpa			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前A相谐波有功功率*/
	xpb			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前B相谐波有功功率*/
	xpc			decimal(14, 3) 	DEFAULT 0.0 ,	/*	当前C相谐波有功功率*/
	
	xvar		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波A相电压总畸变率*/
	xvbr		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波B相电压总畸变率*/
	xvcr		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波C相电压总畸变率*/
	xiar		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波A相电流总畸变率*/
	xibr		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波B相电流总畸变率*/
	xicr		decimal(14, 3) 	DEFAULT 0.0 ,	/*	谐波C相电流总畸变率*/
	
	q1			decimal(14, 3) 	DEFAULT 0.0 ,	/*	一象限无功功率*/
	q2			decimal(14, 3) 	DEFAULT 0.0 ,	/*	二象限无功功率*/
	q3			decimal(14, 3) 	DEFAULT 0.0 ,	/*	三象限无功功率*/
	q4			decimal(14, 3) 	DEFAULT 0.0 ,	/*	四象限无功功率*/
	
	i2			decimal(14, 3) 	DEFAULT 0.0 ,	/*	负序电流*/
)
grant select on  BMinExt200808 to public
create unique clustered index BMinExt200808index1
	on BMinExt200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 变电站分钟电压电流分量数据表(BMinUIWeight200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinUIWeight200808')
begin
create table BMinUIWeight200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	zxva		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相正序电压分量*/
	fxva		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相负序电压分量*/
	lxva		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相零序电压分量*/
	zxvb		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相正序电压分量*/
	fxvb		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相负序电压分量*/
	lxvb		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相零序电压分量*/
	zxvc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相正序电压分量*/
	fxvc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相负序电压分量*/
	lxvc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相零序电压分量*/
	
	zxia		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相正序电流分量*/
	fxia		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相负序电流分量*/
	lxia		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相零序电流分量*/
	zxib		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相正序电流分量*/
	fxib		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相负序电流分量*/
	lxib		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相零序电流分量*/
	zxic		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相正序电流分量*/
	fxic		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相负序电流分量*/
	lxic		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相零序电流分量*/
)
grant select on  BMinUIWeight200808 to public
create unique clustered index BMinUIWeight200808index1
	on BMinUIWeight200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	


--   '-------Create table 变电站分钟电压电流不平衡度数据表(BMinUIImbalance200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinUIImbalance200808')
begin
create table BMinUIImbalance200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	v				decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相电压不平衡度*/
	v0			decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相零序电压不平衡度*/	
	v2			decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相负序电压不平衡度*/
	
	i				decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相电流不平衡度*/
	i0			decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相零序电流不平衡度*/	
	i2			decimal(14, 3) 	DEFAULT 0.0 ,	/*	三相负序电流不平衡度*/

	va			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相电压*/
	vb			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相电压*/
	vc			decimal(14, 3) 	DEFAULT 0.0 ,	/*C相电压*/
	ia			decimal(14, 3) 	DEFAULT 0.0 ,	/*A相电流*/
	ib			decimal(14, 3) 	DEFAULT 0.0 ,	/*B相电流*/
	ic			decimal(14, 3) 	DEFAULT 0.0 ,	/*C相电流*/

	vab			decimal(14, 3) 	DEFAULT 0.0 ,	/*线电压Uab*/
	vbc			decimal(14, 3) 	DEFAULT 0.0 ,	/*线电压Ubc*/
	vca			decimal(14, 3) 	DEFAULT 0.0 ,	/*线电压Uca*/


	v_max				decimal(14, 3) 	DEFAULT 0.0 ,	/*电压不平衡最大值*/
	v_max_tm	int							DEFAULT 0 ,		  /*电压不平衡最大值发生时间*/
	i_max				decimal(14, 3) 	DEFAULT 0.0 ,	/*电流不平衡最大值*/
	i_max_tm	int							DEFAULT 0 ,		  /*电流不平衡最大值发生时间*/		
			
	v0_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*零序电压不平衡最大值*/
	v0_max_tm	int							DEFAULT 0 ,			/*零序电压不平衡最大值发生时间*/
	i0_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*零序电流不平衡最大值*/
	i0_max_tm	int							DEFAULT 0 ,			/*零序电流不平衡最大值发生时间*/		
	
	v2_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*负序电压不平衡最大值*/
	v2_max_tm	int							DEFAULT 0 ,			/*负序电压不平衡最大值发生时间*/
	i2_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*负序电流不平衡最大值*/
	i2_max_tm	int							DEFAULT 0 ,			/*负序电流不平衡最大值发生时间*/
)
grant select on  BMinUIImbalance200808 to public
create unique clustered index BMinUIImbalance200808index1
	on BMinUIImbalance200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	


--   '-------Create table 变电站分钟电压偏差合格率数据表(BMinVPC200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinVPC200808')
begin
create table BMinVPC200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	vapc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相电压偏差*/
	vbpc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相电压偏差*/
	vcpc		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相电压偏差*/
	
	vahg		decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相电压偏差合格率*/
	vbhg		decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相电压偏差合格率*/
	vchg		decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相电压偏差合格率*/
)
grant select on  BMinVPC200808 to public
create unique clustered index BMinVPC200808index1
	on BMinVPC200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	




--   '-------Create table 变电站高次谐波数据表(BMinHXb200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinHXb200808')
begin
create table BMinHXb200808
(
		rtu_id						int   					not null,
		mp_id							smallint   			not null,
		date							int							not null,
		time							int							not null,
		
		xb_va_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相22次谐波电压*/
		xb_va_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相23次谐波电压*/
		xb_va_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相24次谐波电压*/
		xb_va_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相25次谐波电压*/
		xb_va_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相26次谐波电压*/
		xb_va_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相27次谐波电压*/
		xb_va_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相28次谐波电压*/
		xb_va_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相29次谐波电压*/
		xb_va_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相30次谐波电压*/
		xb_va_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相31次谐波电压*/
		xb_va_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相32次谐波电压*/
		xb_va_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相33次谐波电压*/
		xb_va_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相34次谐波电压*/
		xb_va_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相35次谐波电压*/
		xb_va_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相36次谐波电压*/
		xb_va_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相37次谐波电压*/
		xb_va_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相38次谐波电压*/
		xb_va_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相39次谐波电压*/
		xb_va_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相40次谐波电压*/
		xb_va_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相41次谐波电压*/
		xb_va_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相42次谐波电压*/
		xb_va_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相43次谐波电压*/
		xb_va_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相44次谐波电压*/
		xb_va_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相45次谐波电压*/
		xb_va_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相46次谐波电压*/
		xb_va_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相47次谐波电压*/
		xb_va_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相48次谐波电压*/
		xb_va_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相49次谐波电压*/
		xb_va_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相50次谐波电压*/
				
		
		xb_vb_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相22次谐波电压*/
		xb_vb_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相23次谐波电压*/
		xb_vb_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相24次谐波电压*/
		xb_vb_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相25次谐波电压*/
		xb_vb_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相26次谐波电压*/
		xb_vb_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相27次谐波电压*/
		xb_vb_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相28次谐波电压*/
		xb_vb_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相29次谐波电压*/
		xb_vb_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相30次谐波电压*/
		xb_vb_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相31次谐波电压*/
		xb_vb_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相32次谐波电压*/
		xb_vb_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相33次谐波电压*/
		xb_vb_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相34次谐波电压*/
		xb_vb_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相35次谐波电压*/
		xb_vb_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相36次谐波电压*/
		xb_vb_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相37次谐波电压*/
		xb_vb_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相38次谐波电压*/
		xb_vb_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相39次谐波电压*/
		xb_vb_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相40次谐波电压*/
		xb_vb_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相41次谐波电压*/
		xb_vb_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相42次谐波电压*/
		xb_vb_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相43次谐波电压*/
		xb_vb_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相44次谐波电压*/
		xb_vb_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相45次谐波电压*/
		xb_vb_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相46次谐波电压*/
		xb_vb_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相47次谐波电压*/
		xb_vb_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相48次谐波电压*/
		xb_vb_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相49次谐波电压*/
		xb_vb_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相50次谐波电压*/
		
		
		xb_vc_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相22次谐波电压*/
		xb_vc_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相23次谐波电压*/
		xb_vc_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相24次谐波电压*/
		xb_vc_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相25次谐波电压*/
		xb_vc_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相26次谐波电压*/
		xb_vc_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相27次谐波电压*/
		xb_vc_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相28次谐波电压*/
		xb_vc_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相29次谐波电压*/
		xb_vc_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相30次谐波电压*/
		xb_vc_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相31次谐波电压*/
		xb_vc_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相32次谐波电压*/
		xb_vc_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相33次谐波电压*/
		xb_vc_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相34次谐波电压*/
		xb_vc_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相35次谐波电压*/
		xb_vc_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相36次谐波电压*/
		xb_vc_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相37次谐波电压*/
		xb_vc_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相38次谐波电压*/
		xb_vc_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相39次谐波电压*/
		xb_vc_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相40次谐波电压*/
		xb_vc_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相41次谐波电压*/
		xb_vc_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相42次谐波电压*/
		xb_vc_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相43次谐波电压*/
		xb_vc_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相44次谐波电压*/
		xb_vc_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相45次谐波电压*/
		xb_vc_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相46次谐波电压*/
		xb_vc_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相47次谐波电压*/
		xb_vc_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相48次谐波电压*/
		xb_vc_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相49次谐波电压*/
		xb_vc_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相50次谐波电压*/
	
		
		xb_ia_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相22次谐波电流*/
		xb_ia_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相23次谐波电流*/
		xb_ia_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相24次谐波电流*/
		xb_ia_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相25次谐波电流*/
		xb_ia_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相26次谐波电流*/
		xb_ia_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相27次谐波电流*/
		xb_ia_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相28次谐波电流*/
		xb_ia_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相29次谐波电流*/
		xb_ia_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相30次谐波电流*/
		xb_ia_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相31次谐波电流*/
		xb_ia_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相32次谐波电流*/
		xb_ia_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相33次谐波电流*/
		xb_ia_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相34次谐波电流*/
		xb_ia_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相35次谐波电流*/
		xb_ia_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相36次谐波电流*/
		xb_ia_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相37次谐波电流*/
		xb_ia_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相38次谐波电流*/
		xb_ia_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相39次谐波电流*/
		xb_ia_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相40次谐波电流*/
		xb_ia_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相41次谐波电流*/
		xb_ia_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相42次谐波电流*/
		xb_ia_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相43次谐波电流*/
		xb_ia_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相44次谐波电流*/
		xb_ia_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相45次谐波电流*/
		xb_ia_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相46次谐波电流*/
		xb_ia_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相47次谐波电流*/
		xb_ia_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相48次谐波电流*/
		xb_ia_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相49次谐波电流*/
		xb_ia_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相50次谐波电流*/
				
		
		xb_ib_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相22次谐波电流*/
		xb_ib_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相23次谐波电流*/
		xb_ib_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相24次谐波电流*/
		xb_ib_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相25次谐波电流*/
		xb_ib_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相26次谐波电流*/
		xb_ib_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相27次谐波电流*/
		xb_ib_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相28次谐波电流*/
		xb_ib_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相29次谐波电流*/
		xb_ib_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相30次谐波电流*/
		xb_ib_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相31次谐波电流*/
		xb_ib_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相32次谐波电流*/
		xb_ib_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相33次谐波电流*/
		xb_ib_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相34次谐波电流*/
		xb_ib_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相35次谐波电流*/
		xb_ib_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相36次谐波电流*/
		xb_ib_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相37次谐波电流*/
		xb_ib_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相38次谐波电流*/
		xb_ib_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相39次谐波电流*/
		xb_ib_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相40次谐波电流*/
		xb_ib_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相41次谐波电流*/
		xb_ib_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相42次谐波电流*/
		xb_ib_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相43次谐波电流*/
		xb_ib_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相44次谐波电流*/
		xb_ib_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相45次谐波电流*/
		xb_ib_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相46次谐波电流*/
		xb_ib_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相47次谐波电流*/
		xb_ib_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相48次谐波电流*/
		xb_ib_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相49次谐波电流*/
		xb_ib_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相50次谐波电流*/
		
		
		xb_ic_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相22次谐波电流*/
		xb_ic_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相23次谐波电流*/
		xb_ic_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相24次谐波电流*/
		xb_ic_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相25次谐波电流*/
		xb_ic_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相26次谐波电流*/
		xb_ic_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相27次谐波电流*/
		xb_ic_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相28次谐波电流*/
		xb_ic_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相29次谐波电流*/
		xb_ic_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相30次谐波电流*/
		xb_ic_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相31次谐波电流*/
		xb_ic_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相32次谐波电流*/
		xb_ic_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相33次谐波电流*/
		xb_ic_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相34次谐波电流*/
		xb_ic_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相35次谐波电流*/
		xb_ic_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相36次谐波电流*/
		xb_ic_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相37次谐波电流*/
		xb_ic_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相38次谐波电流*/
		xb_ic_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相39次谐波电流*/
		xb_ic_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相40次谐波电流*/
		xb_ic_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相41次谐波电流*/
		xb_ic_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相42次谐波电流*/
		xb_ic_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相43次谐波电流*/
		xb_ic_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相44次谐波电流*/
		xb_ic_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相45次谐波电流*/
		xb_ic_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相46次谐波电流*/
		xb_ic_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相47次谐波电流*/
		xb_ic_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相48次谐波电流*/
		xb_ic_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相49次谐波电流*/
		xb_ic_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相50次谐波电流*/
		
)
grant select on  BMinHXb200808 to public
create unique clustered index BMinHXb200808index1
	on BMinHXb200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束
--   '-------Create table 变电站高次谐波含有率数据表(BMinHXRate200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinHXRate200808')
begin
create table BMinHXRate200808
(
		rtu_id					int   					not null,
		mp_id						smallint   			not null,
		date						int							not null,
		time						int							not null,
				
		xbr_va_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相22次谐波电压含有率*/
		xbr_va_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相23次谐波电压含有率*/
		xbr_va_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相24次谐波电压含有率*/
		xbr_va_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相25次谐波电压含有率*/
		xbr_va_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相26次谐波电压含有率*/
		xbr_va_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相27次谐波电压含有率*/
		xbr_va_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相28次谐波电压含有率*/
		xbr_va_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相29次谐波电压含有率*/
		xbr_va_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相30次谐波电压含有率*/
		xbr_va_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相31次谐波电压含有率*/
		xbr_va_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相32次谐波电压含有率*/
		xbr_va_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相33次谐波电压含有率*/
		xbr_va_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相34次谐波电压含有率*/
		xbr_va_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相35次谐波电压含有率*/
		xbr_va_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相36次谐波电压含有率*/
		xbr_va_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相37次谐波电压含有率*/
		xbr_va_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相38次谐波电压含有率*/
		xbr_va_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相39次谐波电压含有率*/
		xbr_va_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相40次谐波电压含有率*/
		xbr_va_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相41次谐波电压含有率*/
		xbr_va_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相42次谐波电压含有率*/
		xbr_va_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相43次谐波电压含有率*/
		xbr_va_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相44次谐波电压含有率*/
		xbr_va_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相45次谐波电压含有率*/
		xbr_va_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相46次谐波电压含有率*/
		xbr_va_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相47次谐波电压含有率*/
		xbr_va_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相48次谐波电压含有率*/
		xbr_va_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相49次谐波电压含有率*/
		xbr_va_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相50次谐波电压含有率*/
		
		xbr_vb_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相22次谐波电压含有率*/
		xbr_vb_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相23次谐波电压含有率*/
		xbr_vb_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相24次谐波电压含有率*/
		xbr_vb_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相25次谐波电压含有率*/
		xbr_vb_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相26次谐波电压含有率*/
		xbr_vb_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相27次谐波电压含有率*/
		xbr_vb_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相28次谐波电压含有率*/
		xbr_vb_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相29次谐波电压含有率*/
		xbr_vb_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相30次谐波电压含有率*/
		xbr_vb_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相31次谐波电压含有率*/
		xbr_vb_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相32次谐波电压含有率*/
		xbr_vb_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相33次谐波电压含有率*/
		xbr_vb_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相34次谐波电压含有率*/
		xbr_vb_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相35次谐波电压含有率*/
		xbr_vb_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相36次谐波电压含有率*/
		xbr_vb_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相37次谐波电压含有率*/
		xbr_vb_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相38次谐波电压含有率*/
		xbr_vb_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相39次谐波电压含有率*/
		xbr_vb_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相40次谐波电压含有率*/
		xbr_vb_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相41次谐波电压含有率*/
		xbr_vb_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相42次谐波电压含有率*/
		xbr_vb_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相43次谐波电压含有率*/
		xbr_vb_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相44次谐波电压含有率*/
		xbr_vb_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相45次谐波电压含有率*/
		xbr_vb_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相46次谐波电压含有率*/
		xbr_vb_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相47次谐波电压含有率*/
		xbr_vb_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相48次谐波电压含有率*/
		xbr_vb_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相49次谐波电压含有率*/
		xbr_vb_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相50次谐波电压含有率*/
	
		xbr_vc_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相22次谐波电压含有率*/
		xbr_vc_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相23次谐波电压含有率*/
		xbr_vc_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相24次谐波电压含有率*/
		xbr_vc_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相25次谐波电压含有率*/
		xbr_vc_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相26次谐波电压含有率*/
		xbr_vc_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相27次谐波电压含有率*/
		xbr_vc_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相28次谐波电压含有率*/
		xbr_vc_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相29次谐波电压含有率*/
		xbr_vc_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相30次谐波电压含有率*/
		xbr_vc_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相31次谐波电压含有率*/
		xbr_vc_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相32次谐波电压含有率*/
		xbr_vc_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相33次谐波电压含有率*/
		xbr_vc_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相34次谐波电压含有率*/
		xbr_vc_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相35次谐波电压含有率*/
		xbr_vc_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相36次谐波电压含有率*/
		xbr_vc_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相37次谐波电压含有率*/
		xbr_vc_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相38次谐波电压含有率*/
		xbr_vc_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相39次谐波电压含有率*/
		xbr_vc_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相40次谐波电压含有率*/
		xbr_vc_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相41次谐波电压含有率*/
		xbr_vc_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相42次谐波电压含有率*/
		xbr_vc_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相43次谐波电压含有率*/
		xbr_vc_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相44次谐波电压含有率*/
		xbr_vc_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相45次谐波电压含有率*/
		xbr_vc_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相46次谐波电压含有率*/
		xbr_vc_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相47次谐波电压含有率*/
		xbr_vc_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相48次谐波电压含有率*/
		xbr_vc_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相49次谐波电压含有率*/
		xbr_vc_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相50次谐波电压含有率*/
			
		xbr_ia_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相22次谐波电流含有率*/
		xbr_ia_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相23次谐波电流含有率*/
		xbr_ia_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相24次谐波电流含有率*/
		xbr_ia_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相25次谐波电流含有率*/
		xbr_ia_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相26次谐波电流含有率*/
		xbr_ia_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相27次谐波电流含有率*/
		xbr_ia_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相28次谐波电流含有率*/
		xbr_ia_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相29次谐波电流含有率*/
		xbr_ia_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相30次谐波电流含有率*/
		xbr_ia_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相31次谐波电流含有率*/
		xbr_ia_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相32次谐波电流含有率*/
		xbr_ia_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相33次谐波电流含有率*/
		xbr_ia_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相34次谐波电流含有率*/
		xbr_ia_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相35次谐波电流含有率*/
		xbr_ia_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相36次谐波电流含有率*/
		xbr_ia_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相37次谐波电流含有率*/
		xbr_ia_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相38次谐波电流含有率*/
		xbr_ia_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相39次谐波电流含有率*/
		xbr_ia_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相40次谐波电流含有率*/
		xbr_ia_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相41次谐波电流含有率*/
		xbr_ia_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相42次谐波电流含有率*/
		xbr_ia_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相43次谐波电流含有率*/
		xbr_ia_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相44次谐波电流含有率*/
		xbr_ia_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相45次谐波电流含有率*/
		xbr_ia_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相46次谐波电流含有率*/
		xbr_ia_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相47次谐波电流含有率*/
		xbr_ia_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相48次谐波电流含有率*/
		xbr_ia_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相49次谐波电流含有率*/
		xbr_ia_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相50次谐波电流含有率*/
				
		
		xbr_ib_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相22次谐波电流含有率*/
		xbr_ib_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相23次谐波电流含有率*/
		xbr_ib_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相24次谐波电流含有率*/
		xbr_ib_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相25次谐波电流含有率*/
		xbr_ib_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相26次谐波电流含有率*/
		xbr_ib_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相27次谐波电流含有率*/
		xbr_ib_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相28次谐波电流含有率*/
		xbr_ib_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相29次谐波电流含有率*/
		xbr_ib_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相30次谐波电流含有率*/
		xbr_ib_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相31次谐波电流含有率*/
		xbr_ib_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相32次谐波电流含有率*/
		xbr_ib_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相33次谐波电流含有率*/
		xbr_ib_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相34次谐波电流含有率*/
		xbr_ib_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相35次谐波电流含有率*/
		xbr_ib_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相36次谐波电流含有率*/
		xbr_ib_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相37次谐波电流含有率*/
		xbr_ib_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相38次谐波电流含有率*/
		xbr_ib_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相39次谐波电流含有率*/
		xbr_ib_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相40次谐波电流含有率*/
		xbr_ib_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相41次谐波电流含有率*/
		xbr_ib_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相42次谐波电流含有率*/
		xbr_ib_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相43次谐波电流含有率*/
		xbr_ib_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相44次谐波电流含有率*/
		xbr_ib_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相45次谐波电流含有率*/
		xbr_ib_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相46次谐波电流含有率*/
		xbr_ib_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相47次谐波电流含有率*/
		xbr_ib_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相48次谐波电流含有率*/
		xbr_ib_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相49次谐波电流含有率*/
		xbr_ib_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相50次谐波电流含有率*/
		
		
		xbr_ic_22				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相22次谐波电流含有率*/
		xbr_ic_23				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相23次谐波电流含有率*/
		xbr_ic_24				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相24次谐波电流含有率*/
		xbr_ic_25				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相25次谐波电流含有率*/
		xbr_ic_26				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相26次谐波电流含有率*/
		xbr_ic_27				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相27次谐波电流含有率*/
		xbr_ic_28				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相28次谐波电流含有率*/
		xbr_ic_29				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相29次谐波电流含有率*/
		xbr_ic_30				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相30次谐波电流含有率*/
		xbr_ic_31				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相31次谐波电流含有率*/
		xbr_ic_32				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相32次谐波电流含有率*/
		xbr_ic_33				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相33次谐波电流含有率*/
		xbr_ic_34				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相34次谐波电流含有率*/
		xbr_ic_35				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相35次谐波电流含有率*/
		xbr_ic_36				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相36次谐波电流含有率*/
		xbr_ic_37				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相37次谐波电流含有率*/
		xbr_ic_38				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相38次谐波电流含有率*/
		xbr_ic_39				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相39次谐波电流含有率*/
		xbr_ic_40				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相40次谐波电流含有率*/
		xbr_ic_41				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相41次谐波电流含有率*/
		xbr_ic_42				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相42次谐波电流含有率*/
		xbr_ic_43				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相43次谐波电流含有率*/
		xbr_ic_44				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相44次谐波电流含有率*/
		xbr_ic_45				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相45次谐波电流含有率*/
		xbr_ic_46				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相46次谐波电流含有率*/
		xbr_ic_47				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相47次谐波电流含有率*/
		xbr_ic_48				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相48次谐波电流含有率*/
		xbr_ic_49				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相49次谐波电流含有率*/
		xbr_ic_50				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相50次谐波电流含有率*/
)
grant select on  BMinHXRate200808 to public
create unique clustered index BMinHXRate200808index1
	on BMinHXRate200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 变电站日电压电流负序零序不平衡数据表(BDayUIFLBal200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BDayUIFLBal200808')
begin
create table BDayUIFLBal200808
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,

		v_max				decimal(14, 3) 	DEFAULT 0.0 ,	/*电压不平衡度最大值*/
		v_max_tm		int							DEFAULT 0 ,		/*电压不平衡度最大值发生时间*/
		v_tm				int							DEFAULT 0 ,		/*电压不平衡度超限日累计时间*/		
		i_max				decimal(14, 3) 	DEFAULT 0.0 ,	/*电流不平衡度最大值*/
		i_max_tm		int							DEFAULT 0 ,		/*电流不平衡度最大值发生时间*/		
		i_tm				int							DEFAULT 0 ,		/*电流不平衡度超限日累计时间*/
				

		v0_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*零序电压不平衡度最大值*/
		v0_max_tm		int							DEFAULT 0 ,		/*零序电压不平衡度最大值发生时间*/
		v0_tm				int							DEFAULT 0 ,		/*零序电压不平衡度超限日累计时间*/		
		i0_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*零序电流不平衡度最大值*/
		i0_max_tm		int							DEFAULT 0 ,		/*零序电流不平衡度最大值发生时间*/		
		i0_tm				int							DEFAULT 0 ,		/*零序电流不平衡度超限日累计时间*/
		
		v2_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*负序电压不平衡度最大值*/
		v2_max_tm		int							DEFAULT 0 ,		/*负序电压不平衡度最大值发生时间*/
		v2_tm				int							DEFAULT 0 ,		/*负序电压不平衡度超限日累计时间*/		
		i2_max			decimal(14, 3) 	DEFAULT 0.0 ,	/*负序电流不平衡度最大值*/
		i2_max_tm		int							DEFAULT 0 ,		/*负序电流不平衡度最大值发生时间*/		
		i2_tm				int							DEFAULT 0 ,		/*负序电流不平衡度超限日累计时间*/
)
grant select on  BDayUIFLBal200808 to public
create unique clustered index BDayUIFLBal200808index1
	on BDayUIFLBal200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 变电站日电压偏差合格率数据表(BDayVPC200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BDayVPC200808')
begin
create table BDayVPC200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	

	vapc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压偏差最大值*/
	vapc_max_tm		int							DEFAULT 0 ,			/*A相电压偏差最大值发生时间*/
	vahg_tm				int							DEFAULT 0 ,			/*A相电压偏差合格时间*/	
	vahgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压偏差合格率*/

	vbpc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压偏差最大值*/
	vbpc_max_tm		int							DEFAULT 0 ,			/*B相电压偏差最大值发生时间*/
	vbhg_tm				int							DEFAULT 0 ,			/*B相电压偏差合格时间*/	
	vbhgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压偏差合格率*/

	vcpc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压偏差最大值*/
	vcpc_max_tm		int							DEFAULT 0 ,			/*C相电压偏差最大值发生时间*/
	vchg_tm				int							DEFAULT 0 ,			/*C相电压偏差合格时间*/	
	vchgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压偏差合格率*/
)
grant select on  BDayVPC200808 to public
create unique clustered index BDayVPC200808index1
	on BDayVPC200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 变电站月电压偏差合格率数据表(BMonVPC2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMonVPC2008')
begin
create table BMonVPC2008
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	

	vapc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压偏差最大值*/
	vapc_max_tm		int							DEFAULT 0 ,			/*A相电压偏差最大值发生时间*/
	vahg_tm				int							DEFAULT 0 ,			/*A相电压偏差合格时间*/	
	vahgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*A相电压偏差合格率*/

	vbpc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压偏差最大值*/
	vbpc_max_tm		int							DEFAULT 0 ,			/*B相电压偏差最大值发生时间*/
	vbhg_tm				int							DEFAULT 0 ,			/*B相电压偏差合格时间*/	
	vbhgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*B相电压偏差合格率*/

	vcpc_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压偏差最大值*/
	vcpc_max_tm		int							DEFAULT 0 ,			/*C相电压偏差最大值发生时间*/
	vchg_tm				int							DEFAULT 0 ,			/*C相电压偏差合格时间*/	
	vchgr					decimal(14, 3) 	DEFAULT 0.0 ,		/*C相电压偏差合格率*/
)
grant select on  BMonVPC2008 to public
create unique clustered index BMonVPC2008index1
	on BMonVPC2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 变电站分钟需量数据表(BMinMaxXl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinMaxXl200808')
begin
create table BMinMaxXl200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷需量*/	
	xl_zy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/	
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷需量*/
	xl_zw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总需量*/	
	xl_fw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷需量*/	
	xl_fw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	
	
	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 ,		/*反向无功尖需量时间*/	
)
grant select on  BMinMaxXl200808 to public
create unique clustered index BMinMaxXl200808index1
	on BMinMaxXl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 变电站分钟电压波动率数据表(BMinVFLUT200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinVFLUT200808')
begin
create table BMinVFLUT200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	vaflut	decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相电压波动*/
	vbflut	decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相电压波动*/
	vcflut	decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相电压波动*/
	
	vaflic	decimal(14, 3) 	DEFAULT 0.0 ,	/*	A相电压突变/闪变*/
	vbflic	decimal(14, 3) 	DEFAULT 0.0 ,	/*	B相电压突变/闪变*/
	vcflic	decimal(14, 3) 	DEFAULT 0.0 ,	/*	C相电压突变/闪变*/
)
grant select on  BMinVFLUT200808 to public
create unique clustered index BMinVFLUT200808index1
	on BMinVFLUT200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	



--   '-------Create table 变电站谐波电流数据表(BMonXbI2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMonXbI2008')
begin
create table BMonXbI2008
(
		rtu_id						int   					not null,
		mp_id							smallint   			not null,
		date							int							not null,
		time							int							not null,
		
		xb_ia_02					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相2次谐波电流最大值*/
		xb_ia_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电流最大值*/
		xb_ia_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电流最大值*/
		xb_ia_05			    decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电流最大值*/
		xb_ia_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电流最大值*/
		xb_ia_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电流最大值*/
		xb_ia_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电流最大值*/
		xb_ia_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电流最大值*/
		xb_ia_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电流最大值*/
		xb_ia_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电流最大值*/
		xb_ia_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电流最大值*/
		xb_ia_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电流最大值*/
		xb_ia_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电流最大值*/
		xb_ia_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电流最大值*/
		xb_ia_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电流最大值*/
		xb_ia_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电流最大值*/
		xb_ia_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电流最大值*/
		xb_ia_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电流最大值*/
		xb_ia_20					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电流最大值*/
		xb_ia_21					decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电流最大值*/
		xb_ia							decimal(14, 3) 	DEFAULT 0.0 ,	/*A相总畸变电流最大值*/
		
		xb_ia_02_tm				int		DEFAULT 0 ,							/*A相2次谐波电流最大值发生时间*/
		xb_ia_03_tm				int		DEFAULT 0 ,							/*A相3次谐波电流最大值发生时间*/
		xb_ia_04_tm				int		DEFAULT 0 ,							/*A相4次谐波电流最大值发生时间*/
		xb_ia_05_tm				int		DEFAULT 0 ,							/*A相5次谐波电流最大值发生时间*/
		xb_ia_06_tm				int		DEFAULT 0 ,							/*A相6次谐波电流最大值发生时间*/
		xb_ia_07_tm				int		DEFAULT 0 ,							/*A相7次谐波电流最大值发生时间*/
		xb_ia_08_tm				int		DEFAULT 0 ,							/*A相8次谐波电流最大值发生时间*/
		xb_ia_09_tm				int		DEFAULT 0 ,							/*A相9次谐波电流最大值发生时间*/
		xb_ia_10_tm				int		DEFAULT 0 ,							/*A相10次谐波电流最大值发生时间*/
		xb_ia_11_tm				int		DEFAULT 0 ,							/*A相11次谐波电流最大值发生时间*/
		xb_ia_12_tm				int		DEFAULT 0 ,							/*A相12次谐波电流最大值发生时间*/
		xb_ia_13_tm				int		DEFAULT 0 ,							/*A相13次谐波电流最大值发生时间*/
		xb_ia_14_tm				int		DEFAULT 0 ,							/*A相14次谐波电流最大值发生时间*/
		xb_ia_15_tm				int		DEFAULT 0 ,							/*A相15次谐波电流最大值发生时间*/
		xb_ia_16_tm				int		DEFAULT 0 ,							/*A相16次谐波电流最大值发生时间*/
		xb_ia_17_tm				int		DEFAULT 0 ,							/*A相17次谐波电流最大值发生时间*/
		xb_ia_18_tm				int		DEFAULT 0 ,							/*A相18次谐波电流最大值发生时间*/
		xb_ia_19_tm				int		DEFAULT 0 ,							/*A相19次谐波电流最大值发生时间*/
		xb_ia_20_tm				int		DEFAULT 0 ,							/*A相20次谐波电流最大值发生时间*/
		xb_ia_21_tm				int		DEFAULT 0 ,							/*A相21次谐波电流最大值发生时间*/
		xb_ia_tm					int		DEFAULT 0 ,					 	  /*A相总畸变电流最大值发生时间*/
		
		
		xb_ib_02					decimal(14, 3) 	DEFAULT 0.0,	/*B相2次谐波电流最大值*/
		xb_ib_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电流最大值*/
		xb_ib_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电流最大值*/
		xb_ib_05					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电流最大值*/
		xb_ib_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电流最大值*/
		xb_ib_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电流最大值*/
		xb_ib_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电流最大值*/
		xb_ib_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电流最大值*/
		xb_ib_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电流最大值*/
		xb_ib_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电流最大值*/
		xb_ib_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电流最大值*/
		xb_ib_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电流最大值*/
		xb_ib_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电流最大值*/
		xb_ib_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电流最大值*/
		xb_ib_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电流最大值*/
		xb_ib_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电流最大值*/
		xb_ib_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电流最大值*/
		xb_ib_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电流最大值*/
		xb_ib_20					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电流最大值*/
		xb_ib_21					decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电流最大值*/
		xb_ib							decimal(14, 3) 	DEFAULT 0.0 ,	/*B相总畸变电流最大值*/
		
		xb_ib_02_tm				int		DEFAULT 0 ,							/*B相2次谐波电流最大值发生时间*/
		xb_ib_03_tm				int		DEFAULT 0 ,							/*B相3次谐波电流最大值发生时间*/
		xb_ib_04_tm				int		DEFAULT 0 ,							/*B相4次谐波电流最大值发生时间*/
		xb_ib_05_tm				int		DEFAULT 0 ,							/*B相5次谐波电流最大值发生时间*/
		xb_ib_06_tm				int		DEFAULT 0 ,							/*B相6次谐波电流最大值发生时间*/
		xb_ib_07_tm				int		DEFAULT 0 ,							/*B相7次谐波电流最大值发生时间*/
		xb_ib_08_tm				int		DEFAULT 0 ,							/*B相8次谐波电流最大值发生时间*/
		xb_ib_09_tm				int		DEFAULT 0 ,							/*B相9次谐波电流最大值发生时间*/
		xb_ib_10_tm				int		DEFAULT 0 ,							/*B相10次谐波电流最大值发生时间*/
		xb_ib_11_tm				int		DEFAULT 0 ,							/*B相11次谐波电流最大值发生时间*/
		xb_ib_12_tm				int		DEFAULT 0 ,							/*B相12次谐波电流最大值发生时间*/
		xb_ib_13_tm				int		DEFAULT 0 ,							/*B相13次谐波电流最大值发生时间*/
		xb_ib_14_tm				int		DEFAULT 0 ,							/*B相14次谐波电流最大值发生时间*/
		xb_ib_15_tm				int		DEFAULT 0 ,							/*B相15次谐波电流最大值发生时间*/
		xb_ib_16_tm				int		DEFAULT 0 ,							/*B相16次谐波电流最大值发生时间*/
		xb_ib_17_tm				int		DEFAULT 0 ,							/*B相17次谐波电流最大值发生时间*/
		xb_ib_18_tm				int		DEFAULT 0 ,							/*B相18次谐波电流最大值发生时间*/
		xb_ib_19_tm				int		DEFAULT 0 ,							/*B相19次谐波电流最大值发生时间*/
		xb_ib_20_tm				int		DEFAULT 0 ,							/*B相20次谐波电流最大值发生时间*/
		xb_ib_21_tm				int		DEFAULT 0 ,							/*B相21次谐波电流最大值发生时间*/
		xb_ib_tm					int		DEFAULT 0 ,					 		/*B相总畸变电流最大值发生时间*/
		
		
		xb_ic_02					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相2次谐波电流最大值*/
		xb_ic_03					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电流最大值*/
		xb_ic_04					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电流最大值*/
		xb_ic_05					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电流最大值*/
		xb_ic_06					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电流最大值*/
		xb_ic_07					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电流最大值*/
		xb_ic_08					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电流最大值*/
		xb_ic_09					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电流最大值*/
		xb_ic_10					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电流最大值*/
		xb_ic_11					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电流最大值*/
		xb_ic_12					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电流最大值*/
		xb_ic_13					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电流最大值*/
		xb_ic_14					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电流最大值*/
		xb_ic_15					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电流最大值*/
		xb_ic_16					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电流最大值*/
		xb_ic_17					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电流最大值*/
		xb_ic_18					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电流最大值*/
		xb_ic_19					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电流最大值*/
		xb_ic_20					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电流最大值*/
		xb_ic_21					decimal(14, 3) 	DEFAULT 0.0 ,	/*C相21次谐波电流最大值*/
		xb_ic							decimal(14, 3) 	DEFAULT 0.0 ,	/*C相总畸变电流最大值*/
		
		xb_ic_02_tm				int		DEFAULT 0 ,							/*C相2次谐波电流最大值发生时间*/
		xb_ic_03_tm				int		DEFAULT 0 ,							/*C相3次谐波电流最大值发生时间*/
		xb_ic_04_tm				int		DEFAULT 0 ,							/*C相4次谐波电流最大值发生时间*/
		xb_ic_05_tm				int		DEFAULT 0 ,							/*C相5次谐波电流最大值发生时间*/
		xb_ic_06_tm				int		DEFAULT 0 ,							/*C相6次谐波电流最大值发生时间*/
		xb_ic_07_tm				int		DEFAULT 0 ,							/*C相7次谐波电流最大值发生时间*/
		xb_ic_08_tm				int		DEFAULT 0 ,							/*C相8次谐波电流最大值发生时间*/
		xb_ic_09_tm				int		DEFAULT 0 ,							/*C相9次谐波电流最大值发生时间*/
		xb_ic_10_tm				int		DEFAULT 0 ,							/*C相10次谐波电流最大值发生时间*/
		xb_ic_11_tm				int		DEFAULT 0 ,							/*C相11次谐波电流最大值发生时间*/
		xb_ic_12_tm				int		DEFAULT 0 ,							/*C相12次谐波电流最大值发生时间*/
		xb_ic_13_tm				int		DEFAULT 0 ,							/*C相13次谐波电流最大值发生时间*/
		xb_ic_14_tm				int		DEFAULT 0 ,							/*C相14次谐波电流最大值发生时间*/
		xb_ic_15_tm				int		DEFAULT 0 ,							/*C相15次谐波电流最大值发生时间*/
		xb_ic_16_tm				int		DEFAULT 0 ,							/*C相16次谐波电流最大值发生时间*/
		xb_ic_17_tm				int		DEFAULT 0 ,							/*C相17次谐波电流最大值发生时间*/
		xb_ic_18_tm				int		DEFAULT 0 ,							/*C相18次谐波电流最大值发生时间*/
		xb_ic_19_tm				int		DEFAULT 0 ,							/*C相19次谐波电流最大值发生时间*/
		xb_ic_20_tm				int		DEFAULT 0 ,							/*C相20次谐波电流最大值发生时间*/
		xb_ic_21_tm				int		DEFAULT 0 ,							/*C相21次谐波电流最大值发生时间*/
		xb_ic_tm					int		DEFAULT 0 					 		/*C相总畸变电流最大值发生时间*/

)
grant select on  BMonXbI2008 to public
create unique clustered index BMonXbI2008index1
	on BMonXbI2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 变电站谐波电压数据表(BMonXbU2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMonXbU2008')
begin
create table BMonXbU2008
(
		rtu_id					int   					not null,
		mp_id						smallint   			not null,
		date						int							not null,
		time						int							not null,
		
		xb_va_02				decimal(14, 3) 	DEFAULT 0.0,	/*A相2次谐波电压含有率最大值*/
		xb_va_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相3次谐波电压含有率最大值*/
		xb_va_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相4次谐波电压含有率最大值*/
		xb_va_05		    decimal(14, 3) 	DEFAULT 0.0 ,	/*A相5次谐波电压含有率最大值*/
		xb_va_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相6次谐波电压含有率最大值*/
		xb_va_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相7次谐波电压含有率最大值*/
		xb_va_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相8次谐波电压含有率最大值*/
		xb_va_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相9次谐波电压含有率最大值*/
		xb_va_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相10次谐波电压含有率最大值*/
		xb_va_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相11次谐波电压含有率最大值*/
		xb_va_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相12次谐波电压含有率最大值*/
		xb_va_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相13次谐波电压含有率最大值*/
		xb_va_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相14次谐波电压含有率最大值*/
		xb_va_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相15次谐波电压含有率最大值*/
		xb_va_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相16次谐波电压含有率最大值*/
		xb_va_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相17次谐波电压含有率最大值*/
		xb_va_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相18次谐波电压含有率最大值*/
		xb_va_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相19次谐波电压含有率最大值*/
		xb_va_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相20次谐波电压含有率最大值*/
		xb_va_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*A相21次谐波电压含有率最大值*/
		xb_va						decimal(14, 3) 	DEFAULT 0.0 ,	/*A相总畸变电流最大值*/
				
		xb_va_02_tm			int		DEFAULT 0 ,							/*A相2次谐波电压含有率最大值发生时间*/
		xb_va_03_tm			int		DEFAULT 0 ,							/*A相3次谐波电压含有率最大值发生时间*/
		xb_va_04_tm			int		DEFAULT 0 ,							/*A相4次谐波电压含有率最大值发生时间*/
		xb_va_05_tm			int		DEFAULT 0 ,							/*A相5次谐波电压含有率最大值发生时间*/
		xb_va_06_tm			int		DEFAULT 0 ,							/*A相6次谐波电压含有率最大值发生时间*/
		xb_va_07_tm			int		DEFAULT 0 ,							/*A相7次谐波电压含有率最大值发生时间*/
		xb_va_08_tm			int		DEFAULT 0 ,							/*A相8次谐波电压含有率最大值发生时间*/
		xb_va_09_tm			int		DEFAULT 0 ,							/*A相9次谐波电压含有率最大值发生时间*/
		xb_va_10_tm			int		DEFAULT 0 ,							/*A相10次谐波电压含有率最大值发生时间*/
		xb_va_11_tm			int		DEFAULT 0 ,							/*A相11次谐波电压含有率最大值发生时间*/
		xb_va_12_tm			int		DEFAULT 0 ,							/*A相12次谐波电压含有率最大值发生时间*/
		xb_va_13_tm			int		DEFAULT 0 ,							/*A相13次谐波电压含有率最大值发生时间*/
		xb_va_14_tm			int		DEFAULT 0 ,							/*A相14次谐波电压含有率最大值发生时间*/
		xb_va_15_tm			int		DEFAULT 0 ,							/*A相15次谐波电压含有率最大值发生时间*/
		xb_va_16_tm			int		DEFAULT 0 ,							/*A相16次谐波电压含有率最大值发生时间*/
		xb_va_17_tm			int		DEFAULT 0 ,							/*A相17次谐波电压含有率最大值发生时间*/
		xb_va_18_tm			int		DEFAULT 0 ,							/*A相18次谐波电压含有率最大值发生时间*/
		xb_va_19_tm			int		DEFAULT 0 ,							/*A相19次谐波电压含有率最大值发生时间*/
		xb_va_20_tm			int		DEFAULT 0 ,							/*A相20次谐波电压含有率最大值发生时间*/
		xb_va_21tm			int		DEFAULT 0 ,							/*A相21次谐波电压含有率最大值发生时间*/
		xb_va_tm				int		DEFAULT 0 ,					 		/*A相总畸变电流最大值发生时间*/
		
		xb_vb_02				decimal(14, 3) 	DEFAULT 0.0,	/*B相2次谐波电压含有率最大值*/
		xb_vb_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相3次谐波电压含有率最大值*/
		xb_vb_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相4次谐波电压含有率最大值*/
		xb_vb_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相5次谐波电压含有率最大值*/
		xb_vb_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相6次谐波电压含有率最大值*/
		xb_vb_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相7次谐波电压含有率最大值*/
		xb_vb_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相8次谐波电压含有率最大值*/
		xb_vb_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相9次谐波电压含有率最大值*/
		xb_vb_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相10次谐波电压含有率最大值*/
		xb_vb_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相11次谐波电压含有率最大值*/
		xb_vb_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相12次谐波电压含有率最大值*/
		xb_vb_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相13次谐波电压含有率最大值*/
		xb_vb_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相14次谐波电压含有率最大值*/
		xb_vb_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相15次谐波电压含有率最大值*/
		xb_vb_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相16次谐波电压含有率最大值*/
		xb_vb_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相17次谐波电压含有率最大值*/
		xb_vb_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相18次谐波电压含有率最大值*/
		xb_vb_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相19次谐波电压含有率最大值*/
		xb_vb_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相20次谐波电压含有率最大值*/
		xb_vb_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*B相21次谐波电压含有率最大值*/
		xb_vb						decimal(14, 3) 	DEFAULT 0.0 ,	/*B相总畸变电流最大值*/
		
		xb_vb_02_tm			int		DEFAULT 0 ,							/*B相2次谐波电压含有率最大值发生时间*/
		xb_vb_03_tm			int		DEFAULT 0 ,							/*B相3次谐波电压含有率最大值发生时间*/
		xb_vb_04_tm			int		DEFAULT 0 ,							/*B相4次谐波电压含有率最大值发生时间*/
		xb_vb_05_tm			int		DEFAULT 0 ,							/*B相5次谐波电压含有率最大值发生时间*/
		xb_vb_06_tm			int		DEFAULT 0 ,							/*B相6次谐波电压含有率最大值发生时间*/
		xb_vb_07_tm			int		DEFAULT 0 ,							/*B相7次谐波电压含有率最大值发生时间*/
		xb_vb_08_tm			int		DEFAULT 0 ,							/*B相8次谐波电压含有率最大值发生时间*/
		xb_vb_09_tm			int		DEFAULT 0 ,							/*B相9次谐波电压含有率最大值发生时间*/
		xb_vb_10_tm			int		DEFAULT 0 ,							/*B相10次谐波电压含有率最大值发生时间*/
		xb_vb_11_tm			int		DEFAULT 0 ,							/*B相11次谐波电压含有率最大值发生时间*/
		xb_vb_12_tm			int		DEFAULT 0 ,							/*B相12次谐波电压含有率最大值发生时间*/
		xb_vb_13_tm			int		DEFAULT 0 ,							/*B相13次谐波电压含有率最大值发生时间*/
		xb_vb_14_tm			int		DEFAULT 0 ,							/*B相14次谐波电压含有率最大值发生时间*/
		xb_vb_15_tm			int		DEFAULT 0 ,							/*B相15次谐波电压含有率最大值发生时间*/
		xb_vb_16_tm			int		DEFAULT 0 ,							/*B相16次谐波电压含有率最大值发生时间*/
		xb_vb_17_tm			int		DEFAULT 0 ,							/*B相17次谐波电压含有率最大值发生时间*/
		xb_vb_18_tm			int		DEFAULT 0 ,							/*B相18次谐波电压含有率最大值发生时间*/
		xb_vb_19_tm			int		DEFAULT 0 ,							/*B相19次谐波电压含有率最大值发生时间*/
		xb_vb_20_tm			int		DEFAULT 0 ,							/*B相20次谐波电压含有率最大值发生时间*/
		xb_vb_21_tm			int		DEFAULT 0 ,							/*B相21次谐波电压含有率最大值发生时间*/
		xb_vb_tm				int		DEFAULT 0 ,					 		/*B相总畸变电流最大值发生时间*/
		
		
		xb_vc_02				decimal(14, 3) 	DEFAULT 0.0	,	/*C相2次谐波电压含有率最大值*/
		xb_vc_03				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相3次谐波电压含有率最大值*/
		xb_vc_04				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相4次谐波电压含有率最大值*/
		xb_vc_05				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相5次谐波电压含有率最大值*/
		xb_vc_06				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相6次谐波电压含有率最大值*/
		xb_vc_07				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相7次谐波电压含有率最大值*/
		xb_vc_08				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相8次谐波电压含有率最大值*/
		xb_vc_09				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相9次谐波电压含有率最大值*/
		xb_vc_10				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相10次谐波电压含有率最大值*/
		xb_vc_11				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相11次谐波电压含有率最大值*/
		xb_vc_12				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相12次谐波电压含有率最大值*/
		xb_vc_13				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相13次谐波电压含有率最大值*/
		xb_vc_14				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相14次谐波电压含有率最大值*/
		xb_vc_15				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相15次谐波电压含有率最大值*/
		xb_vc_16				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相16次谐波电压含有率最大值*/
		xb_vc_17				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相17次谐波电压含有率最大值*/
		xb_vc_18				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相18次谐波电压含有率最大值*/
		xb_vc_19				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相19次谐波电压含有率最大值*/
		xb_vc_20				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相20次谐波电压含有率最大值*/
		xb_vc_21				decimal(14, 3) 	DEFAULT 0.0 ,	/*C相21次谐波电压含有率最大值*/
		xb_vc						decimal(14, 3) 	DEFAULT 0.0 ,	/*C相总畸变电流最大值*/
		
		xb_vc_02_tm			int		DEFAULT 0 ,							/*C相2次谐波电压含有率最大值发生时间*/
		xb_vc_03_tm			int		DEFAULT 0 ,							/*C相3次谐波电压含有率最大值发生时间*/
		xb_vc_04_tm			int		DEFAULT 0 ,							/*C相4次谐波电压含有率最大值发生时间*/
		xb_vc_05_tm			int		DEFAULT 0 ,							/*C相5次谐波电压含有率最大值发生时间*/
		xb_vc_06_tm			int		DEFAULT 0 ,							/*C相6次谐波电压含有率最大值发生时间*/
		xb_vc_07_tm			int		DEFAULT 0 ,							/*C相7次谐波电压含有率最大值发生时间*/
		xb_vc_08_tm			int		DEFAULT 0 ,							/*C相8次谐波电压含有率最大值发生时间*/
		xb_vc_09_tm			int		DEFAULT 0 ,							/*C相9次谐波电压含有率最大值发生时间*/
		xb_vc_10_tm			int		DEFAULT 0 ,							/*C相10次谐波电压含有率最大值发生时间*/
		xb_vc_11_tm			int		DEFAULT 0 ,							/*C相11次谐波电压含有率最大值发生时间*/
		xb_vc_12_tm			int		DEFAULT 0 ,							/*C相12次谐波电压含有率最大值发生时间*/
		xb_vc_13_tm			int		DEFAULT 0 ,							/*C相13次谐波电压含有率最大值发生时间*/
		xb_vc_14_tm			int		DEFAULT 0 ,							/*C相14次谐波电压含有率最大值发生时间*/
		xb_vc_15_tm			int		DEFAULT 0 ,							/*C相15次谐波电压含有率最大值发生时间*/
		xb_vc_16_tm			int		DEFAULT 0 ,							/*C相16次谐波电压含有率最大值发生时间*/
		xb_vc_17_tm			int		DEFAULT 0 ,							/*C相17次谐波电压含有率最大值发生时间*/
		xb_vc_18_tm			int		DEFAULT 0 ,							/*C相18次谐波电压含有率最大值发生时间*/
		xb_vc_19_tm			int		DEFAULT 0 ,							/*C相19次谐波电压含有率最大值发生时间*/
		xb_vc_20_tm			int		DEFAULT 0 ,							/*C相20次谐波电压含有率最大值发生时间*/
		xb_vc_21_tm			int		DEFAULT 0 ,							/*C相21次谐波电压含有率最大值发生时间*/
		xb_vc_tm				int		DEFAULT 0 					 		/*C相总畸变电流最大值发生时间*/
)
grant select on  BMonXbU2008 to public
create unique clustered index BMonXbU2008index1
	on BMonXbU2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 变电站谐波越限数据表(BMonXbLmt2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMonXbLmt2008')
begin
create table BMonXbLmt2008
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		
		xblmt_va			int		DEFAULT 0 ,			/*A相总畸变电压含有率越限累计时间*/
		xblmt_va_02		int		DEFAULT 0 ,			/*A相2次谐波电压含有率越限累计时间*/
		xblmt_va_03		int		DEFAULT 0 ,			/*A相3次谐波电压含有率越限累计时间*/
		xblmt_va_04		int		DEFAULT 0 ,			/*A相4次谐波电压含有率越限累计时间*/
		xblmt_va_05		int		DEFAULT 0 ,			/*A相5次谐波电压含有率越限累计时间*/
		xblmt_va_06		int		DEFAULT 0 ,			/*A相6次谐波电压含有率越限累计时间*/
		xblmt_va_07		int		DEFAULT 0 ,			/*A相7次谐波电压含有率越限累计时间*/
		xblmt_va_08		int		DEFAULT 0 ,			/*A相8次谐波电压含有率越限累计时间*/
		xblmt_va_09		int		DEFAULT 0 ,			/*A相9次谐波电压含有率越限累计时间*/
		xblmt_va_10		int		DEFAULT 0 ,			/*A相10次谐波电压含有率越限累计时间*/
		xblmt_va_11		int		DEFAULT 0 ,			/*A相11次谐波电压含有率越限累计时间*/
		xblmt_va_12		int		DEFAULT 0 ,			/*A相12次谐波电压含有率越限累计时间*/
		xblmt_va_13		int		DEFAULT 0 ,			/*A相13次谐波电压含有率越限累计时间*/
		xblmt_va_14		int		DEFAULT 0 ,			/*A相14次谐波电压含有率越限累计时间*/
		xblmt_va_15		int		DEFAULT 0 ,			/*A相15次谐波电压含有率越限累计时间*/
		xblmt_va_16		int		DEFAULT 0 ,			/*A相16次谐波电压含有率越限累计时间*/
		xblmt_va_17		int		DEFAULT 0 ,			/*A相17次谐波电压含有率越限累计时间*/
		xblmt_va_18		int		DEFAULT 0 ,			/*A相18次谐波电压含有率越限累计时间*/
		xblmt_va_19		int		DEFAULT 0 ,			/*A相19次谐波电压含有率越限累计时间*/
		xblmt_va_20		int		DEFAULT 0 ,			/*A相20次谐波电压含有率越限累计时间*/
		xblmt_va_21		int		DEFAULT 0 ,			/*A相21次谐波电压含有率越限累计时间*/
		xblmt_ia			int		DEFAULT 0 ,			/*A相总畸变电流越限累计时间*/
		xblmt_ia_02		int		DEFAULT 0 ,			/*A相2次谐波电流越限累计时间*/
		xblmt_ia_03		int		DEFAULT 0 ,			/*A相3次谐波电流越限累计时间*/
		xblmt_ia_04		int		DEFAULT 0 ,			/*A相4次谐波电流越限累计时间*/
		xblmt_ia_05		int		DEFAULT 0 ,			/*A相5次谐波电流越限累计时间*/
		xblmt_ia_06		int		DEFAULT 0 ,			/*A相6次谐波电流越限累计时间*/
		xblmt_ia_07		int		DEFAULT 0 ,			/*A相7次谐波电流越限累计时间*/
		xblmt_ia_08		int		DEFAULT 0 ,			/*A相8次谐波电流越限累计时间*/
		xblmt_ia_09		int		DEFAULT 0 ,			/*A相9次谐波电流越限累计时间*/
		xblmt_ia_10		int		DEFAULT 0 ,			/*A相10次谐波电流越限累计时间*/
		xblmt_ia_11		int		DEFAULT 0 ,			/*A相11次谐波电流越限累计时间*/
		xblmt_ia_12		int		DEFAULT 0 ,			/*A相12次谐波电流越限累计时间*/
		xblmt_ia_13		int		DEFAULT 0 ,			/*A相13次谐波电流越限累计时间*/
		xblmt_ia_14		int		DEFAULT 0 ,			/*A相14次谐波电流越限累计时间*/
		xblmt_ia_15		int		DEFAULT 0 ,			/*A相15次谐波电流越限累计时间*/
		xblmt_ia_16		int		DEFAULT 0 ,			/*A相16次谐波电流越限累计时间*/
		xblmt_ia_17		int		DEFAULT 0 ,			/*A相17次谐波电流越限累计时间*/
		xblmt_ia_18		int		DEFAULT 0 ,			/*A相18次谐波电流越限累计时间*/
		xblmt_ia_19		int		DEFAULT 0 ,			/*A相19次谐波电流越限累计时间*/
		xblmt_ia_20		int		DEFAULT 0 ,			/*A相20次谐波电流越限累计时间*/
		xblmt_ia_21		int		DEFAULT 0 ,			/*A相21次谐波电流越限累计时间*/
		xblmt_vb			int		DEFAULT 0 ,			/*B相总畸变电压含有率越限累计时间*/
		xblmt_vb_02		int		DEFAULT 0 ,			/*B相2次谐波电压含有率越限累计时间*/
		xblmt_vb_03		int		DEFAULT 0 ,			/*B相3次谐波电压含有率越限累计时间*/
		xblmt_vb_04		int		DEFAULT 0 ,			/*B相4次谐波电压含有率越限累计时间*/
		xblmt_vb_05		int		DEFAULT 0 ,			/*B相5次谐波电压含有率越限累计时间*/
		xblmt_vb_06		int		DEFAULT 0 ,			/*B相6次谐波电压含有率越限累计时间*/
		xblmt_vb_07		int		DEFAULT 0 ,			/*B相7次谐波电压含有率越限累计时间*/
		xblmt_vb_08		int		DEFAULT 0 ,			/*B相8次谐波电压含有率越限累计时间*/
		xblmt_vb_09		int		DEFAULT 0 ,			/*B相9次谐波电压含有率越限累计时间*/
		xblmt_vb_10		int		DEFAULT 0 ,			/*B相10次谐波电压含有率越限累计时间*/
		xblmt_vb_11		int		DEFAULT 0 ,			/*B相11次谐波电压含有率越限累计时间*/
		xblmt_vb_12		int		DEFAULT 0 ,			/*B相12次谐波电压含有率越限累计时间*/
		xblmt_vb_13		int		DEFAULT 0 ,			/*B相13次谐波电压含有率越限累计时间*/
		xblmt_vb_14		int		DEFAULT 0 ,			/*B相14次谐波电压含有率越限累计时间*/
		xblmt_vb_15		int		DEFAULT 0 ,			/*B相15次谐波电压含有率越限累计时间*/
		xblmt_vb_16		int		DEFAULT 0 ,			/*B相16次谐波电压含有率越限累计时间*/
		xblmt_vb_17		int		DEFAULT 0 ,			/*B相17次谐波电压含有率越限累计时间*/
		xblmt_vb_18		int		DEFAULT 0 ,			/*B相18次谐波电压含有率越限累计时间*/
		xblmt_vb_19		int		DEFAULT 0 ,			/*B相19次谐波电压含有率越限累计时间*/
		xblmt_vb_20		int		DEFAULT 0 ,			/*B相20次谐波电压含有率越限累计时间*/
		xblmt_vb_21		int		DEFAULT 0 ,			/*B相21次谐波电压含有率越限累计时间*/
		xblmt_ib			int		DEFAULT 0 ,			/*B相总畸变电流越限累计时间*/
		xblmt_ib_02		int		DEFAULT 0 ,			/*B相2次谐波电流越限累计时间*/
		xblmt_ib_03		int		DEFAULT 0 ,			/*B相3次谐波电流越限累计时间*/
		xblmt_ib_04		int		DEFAULT 0 ,			/*B相4次谐波电流越限累计时间*/
		xblmt_ib_05		int		DEFAULT 0 ,			/*B相5次谐波电流越限累计时间*/
		xblmt_ib_06		int		DEFAULT 0 ,			/*B相6次谐波电流越限累计时间*/
		xblmt_ib_07		int		DEFAULT 0 ,			/*B相7次谐波电流越限累计时间*/
		xblmt_ib_08		int		DEFAULT 0 ,			/*B相8次谐波电流越限累计时间*/
		xblmt_ib_09		int		DEFAULT 0 ,			/*B相9次谐波电流越限累计时间*/
		xblmt_ib_10		int		DEFAULT 0 ,			/*B相10次谐波电流越限累计时间*/
		xblmt_ib_11		int		DEFAULT 0 ,			/*B相11次谐波电流越限累计时间*/
		xblmt_ib_12		int		DEFAULT 0 ,			/*B相12次谐波电流越限累计时间*/
		xblmt_ib_13		int		DEFAULT 0 ,			/*B相13次谐波电流越限累计时间*/
		xblmt_ib_14		int		DEFAULT 0 ,			/*B相14次谐波电流越限累计时间*/
		xblmt_ib_15		int		DEFAULT 0 ,			/*B相15次谐波电流越限累计时间*/
		xblmt_ib_16		int		DEFAULT 0 ,			/*B相16次谐波电流越限累计时间*/
		xblmt_ib_17		int		DEFAULT 0 ,			/*B相17次谐波电流越限累计时间*/
		xblmt_ib_18		int		DEFAULT 0 ,			/*B相18次谐波电流越限累计时间*/
		xblmt_ib_19		int		DEFAULT 0 ,			/*B相19次谐波电流越限累计时间*/
		xblmt_ib_20		int		DEFAULT 0 ,			/*B相20次谐波电流越限累计时间*/
		xblmt_ib_21		int		DEFAULT 0 ,			/*B相21次谐波电流越限累计时间*/
		xblmt_vc			int		DEFAULT 0 ,			/*C相总畸变电压含有率越限累计时间*/
		xblmt_vc_02		int		DEFAULT 0 ,			/*C相2次谐波电压含有率越限累计时间*/
		xblmt_vc_03		int		DEFAULT 0 ,			/*C相3次谐波电压含有率越限累计时间*/
		xblmt_vc_04		int		DEFAULT 0 ,			/*C相4次谐波电压含有率越限累计时间*/
		xblmt_vc_05		int		DEFAULT 0 ,			/*C相5次谐波电压含有率越限累计时间*/
		xblmt_vc_06		int		DEFAULT 0 ,			/*C相6次谐波电压含有率越限累计时间*/
		xblmt_vc_07		int		DEFAULT 0 ,			/*C相7次谐波电压含有率越限累计时间*/
		xblmt_vc_08		int		DEFAULT 0 ,			/*C相8次谐波电压含有率越限累计时间*/
		xblmt_vc_09		int		DEFAULT 0 ,			/*C相9次谐波电压含有率越限累计时间*/
		xblmt_vc_10		int		DEFAULT 0 ,			/*C相10次谐波电压含有率越限累计时间*/
		xblmt_vc_11		int		DEFAULT 0 ,			/*C相11次谐波电压含有率越限累计时间*/
		xblmt_vc_12		int		DEFAULT 0 ,			/*C相12次谐波电压含有率越限累计时间*/
		xblmt_vc_13		int		DEFAULT 0 ,			/*C相13次谐波电压含有率越限累计时间*/
		xblmt_vc_14		int		DEFAULT 0 ,			/*C相14次谐波电压含有率越限累计时间*/
		xblmt_vc_15		int		DEFAULT 0 ,			/*C相15次谐波电压含有率越限累计时间*/
		xblmt_vc_16		int		DEFAULT 0 ,			/*C相16次谐波电压含有率越限累计时间*/
		xblmt_vc_17		int		DEFAULT 0 ,			/*C相17次谐波电压含有率越限累计时间*/
		xblmt_vc_18		int		DEFAULT 0 ,			/*C相18次谐波电压含有率越限累计时间*/
		xblmt_vc_19		int		DEFAULT 0 ,			/*C相19次谐波电压含有率越限累计时间*/
		xblmt_vc_20		int		DEFAULT 0 ,			/*C相20次谐波电压含有率越限累计时间*/
		xblmt_vc_21		int		DEFAULT 0 ,			/*C相21次谐波电压含有率越限累计时间*/
		xblmt_ic			int		DEFAULT 0 ,			/*C相总畸变电流越限累计时间*/
		xblmt_ic_02		int		DEFAULT 0 ,			/*C相2次谐波电流越限累计时间*/
		xblmt_ic_03		int		DEFAULT 0 ,			/*C相3次谐波电流越限累计时间*/
		xblmt_ic_04		int		DEFAULT 0 ,			/*C相4次谐波电流越限累计时间*/
		xblmt_ic_05		int		DEFAULT 0 ,			/*C相5次谐波电流越限累计时间*/
		xblmt_ic_06		int		DEFAULT 0 ,			/*C相6次谐波电流越限累计时间*/
		xblmt_ic_07		int		DEFAULT 0 ,			/*C相7次谐波电流越限累计时间*/
		xblmt_ic_08		int		DEFAULT 0 ,			/*C相8次谐波电流越限累计时间*/
		xblmt_ic_09		int		DEFAULT 0 ,			/*C相9次谐波电流越限累计时间*/
		xblmt_ic_10		int		DEFAULT 0 ,			/*C相10次谐波电流越限累计时间*/
		xblmt_ic_11		int		DEFAULT 0 ,			/*C相11次谐波电流越限累计时间*/
		xblmt_ic_12		int		DEFAULT 0 ,			/*C相12次谐波电流越限累计时间*/
		xblmt_ic_13		int		DEFAULT 0 ,			/*C相13次谐波电流越限累计时间*/
		xblmt_ic_14		int		DEFAULT 0 ,			/*C相14次谐波电流越限累计时间*/
		xblmt_ic_15		int		DEFAULT 0 ,			/*C相15次谐波电流越限累计时间*/
		xblmt_ic_16		int		DEFAULT 0 ,			/*C相16次谐波电流越限累计时间*/
		xblmt_ic_17		int		DEFAULT 0 ,			/*C相17次谐波电流越限累计时间*/
		xblmt_ic_18		int		DEFAULT 0 ,			/*C相18次谐波电流越限累计时间*/
		xblmt_ic_19		int		DEFAULT 0 ,			/*C相19次谐波电流越限累计时间*/
		xblmt_ic_20		int		DEFAULT 0 ,			/*C相20次谐波电流越限累计时间*/
		xblmt_ic_21		int		DEFAULT 0 			/*C相21次谐波电流越限累计时间*/
)
grant select on  BMonXbLmt2008 to public
create unique clustered index BMonXbLmt2008index1
	on BMonXbLmt2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 变电站日电流不平衡数据表(BDayIBal200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BDayIBal200808')
begin
create table BDayIBal200808
(
		rtu_id				int   					not null,
		mp_id					smallint   			not null,
		date					int							not null,
		time					int							not null,
		bph_i_tm			int							DEFAULT 0 ,			/*电流不平衡度越限累计时间*/
		bph_v_tm			int							DEFAULT 0 ,			/*电压不平衡度越限累计时间*/ 
		bph_i_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*电流不平衡最大值*/
		bph_i_max_tm	int							DEFAULT 0 ,			/*电流不平衡最大值发生时间*/
		bph_v_max			decimal(14, 3) 	DEFAULT 0.0 ,		/*电压不平衡最大值*/
		bph_v_max_tm	int							DEFAULT 0			  /*电压不平衡最大值发生时间*/
)
grant select on  BDayIBal200808 to public
create unique clustered index BDayIBal200808index1
	on BDayIBal200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--   '-------Create table 变电站日电流越限数据表(BDayILmt200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BDayILmt200808')
begin
create table BDayILmt200808
(
		rtu_id					int  			not null,
		mp_id						smallint 	not null,
		date						int				not null,
		time						int				not null,

		lmt_ia_hh_tm		int				DEFAULT 0 ,			/*A相电流越上上限累计时间*/
		lmt_ia_h_tm			int				DEFAULT 0 ,			/*A相电流越上限累计时间*/
		lmt_ib_hh_tm		int				DEFAULT 0 ,			/*B相电流越上上限累计时间*/
		lmt_ib_h_tm			int				DEFAULT 0 ,			/*B相电流越上限累计时间*/
		lmt_ic_hh_tm		int				DEFAULT 0 ,			/*C相电流越上上限累计时间*/
		lmt_ic_h_tm			int				DEFAULT 0 ,			/*C相电流越上限累计时间*/
		lmt_i0_h_tm			int				DEFAULT 0 ,			/*零序电流越上限累计时间*/
		lmt_ia_max			decimal(14, 3)DEFAULT 0.0,/*A相电流最大值*/
		lmt_ia_max_tm		int				DEFAULT 0 ,			/*A相电流最大值发生时间*/
		lmt_ib_max			decimal(14, 3)DEFAULT 0.0,/*B相电流最大值*/
		lmt_ib_max_tm		int				DEFAULT 0 ,			/*B相电流最大值发生时间*/
		lmt_ic_max			decimal(14, 3)DEFAULT 0.0,/*C相电流最大值*/
		lmt_ic_max_tm		int				DEFAULT 0 ,			/*C相电流最大值发生时间*/
		lmt_i0_max			decimal(14, 3)DEFAULT 0.0,/*零序电流最大值*/
		lmt_i0_max_tm		int				DEFAULT 0 			/*零序电流最大值发生时间*/
)
grant select on  BDayILmt200808 to public
create unique clustered index BDayILmt200808index1
	on BDayILmt200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--注：巴基斯坦原来在专变应用，现转为低压应用
--   '-------Create table 巴基斯坦居民分钟需量数据表(JMinMaxXl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JMinMaxXl200808')
begin
create table JMinMaxXl200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷需量*/	
	xl_zy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/	
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷需量*/
	xl_zw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总需量*/	
	xl_fw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷需量*/	
	xl_fw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	
	
	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 ,		/*反向无功尖需量时间*/	
)
grant select on  JMinMaxXl200808 to public
create unique clustered index JMinMaxXl200808index1
	on JMinMaxXl200808(rtu_id,mp_id,date,time)
end
go

-- 标记结束

--   '-------Create table 居民日需量数据表(JDayMaxXl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JDayMaxXl200808')
begin
create table JDayMaxXl200808
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,

	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷需量*/	
	xl_zy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/		
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷需量*/	
	xl_zw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总需量*/	
	xl_fw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷需量*/
	xl_fw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	
	
	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 		/*反向无功尖需量时间*/
)
grant select on  JDayMaxXl200808 to public
create unique clustered index JDayMaxXl200808index1
	on JDayMaxXl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

-- 标记开始
if not exists (select * from sysobjects where name = 'JMonMaxXl2008')
begin
create table JMonMaxXl2008
(
	rtu_id		int   					not null,
	mp_id			smallint   			not null,
	date			int							not null,
	time			int							not null,

	xl_zy					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功总需量*/	
	xl_zy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功峰需量*/	
	xl_zy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功平需量*/	
	xl_zy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功谷需量*/	
	xl_zy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向有功尖需量*/	
	
	xl_zy_tm			int							DEFAULT 0 ,		/*正向有功总需量时间*/	
	xl_zy_fl1_tm	int							DEFAULT 0 ,		/*正向有功峰需量时间*/	
	xl_zy_fl2_tm	int							DEFAULT 0 ,		/*正向有功平需量时间*/	
	xl_zy_fl3_tm	int							DEFAULT 0 ,		/*正向有功谷需量时间*/	
	xl_zy_fl4_tm	int							DEFAULT 0 ,		/*正向有功尖需量时间*/	
		
	xl_fy					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功总需量*/	
	xl_fy_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功峰需量*/	
	xl_fy_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功平需量*/	
	xl_fy_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功谷需量*/	
	xl_fy_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向有功尖需量*/	
	
	xl_fy_tm			int							DEFAULT 0 ,		/*反向有功总需量时间*/	
	xl_fy_fl1_tm	int							DEFAULT 0 ,		/*反向有功峰需量时间*/	
	xl_fy_fl2_tm	int							DEFAULT 0 ,		/*反向有功平需量时间*/	
	xl_fy_fl3_tm	int							DEFAULT 0 ,		/*反向有功谷需量时间*/	
	xl_fy_fl4_tm	int							DEFAULT 0 ,		/*反向有功尖需量时间*/	
			
			
	xl_zw					decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功总需量*/	
	xl_zw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功峰需量*/	
	xl_zw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功平需量*/	
	xl_zw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功谷需量*/	
	xl_zw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*正向无功尖需量*/	
	
	xl_zw_tm			int							DEFAULT 0 ,		/*正向无功总需量时间*/	
	xl_zw_fl1_tm	int							DEFAULT 0 ,		/*正向无功峰需量时间*/	
	xl_zw_fl2_tm	int							DEFAULT 0 ,		/*正向无功平需量时间*/	
	xl_zw_fl3_tm	int							DEFAULT 0 ,		/*正向无功谷需量时间*/	
	xl_zw_fl4_tm	int							DEFAULT 0 ,		/*正向无功尖需量时间*/	
	
	xl_fw					decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功总需量*/	
	xl_fw_fl1			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功峰需量*/	
	xl_fw_fl2			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功平需量*/	
	xl_fw_fl3			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功谷需量*/	
	xl_fw_fl4			decimal(14, 3) 	DEFAULT 0.0 ,	/*反向无功尖需量*/	

	xl_fw_tm			int							DEFAULT 0 ,		/*反向无功总需量时间*/	
	xl_fw_fl1_tm	int							DEFAULT 0 ,		/*反向无功峰需量时间*/	
	xl_fw_fl2_tm	int							DEFAULT 0 ,		/*反向无功平需量时间*/	
	xl_fw_fl3_tm	int							DEFAULT 0 ,		/*反向无功谷需量时间*/
	xl_fw_fl4_tm	int							DEFAULT 0 		/*反向无功尖需量时间*/
)
grant select on  JMonMaxXl2008 to public
create unique clustered index JMonMaxXl2008index1
	on JMonMaxXl2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--终端事项数据表
--   '-------Create table 巴基斯坦终端事项数据表(EventExt200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'EventExt200808')
begin
create table EventExt200808
(
	rtu_id			int   					not null,
	mp_id				smallint   			not null,
	
	date				int							not null,
	time				int							not null,
	
	prot_type		tinyint					not null,		/*规约类型*/
	
	event_cls		int							not null,		/*事项大类*/
	event_type	int							not null,		/*事项小类*/
	
	sdate				int							null,				/*事项起始日期*/
	stime				int							null,				/*事项起始时间*/
	edate				int							null,				/*事项结束日期*/
	etime				int							null,				/*事项结束时间*/
	
	event_data	varchar(255)		null				/*事项数据-原始报文*/
)
grant select on  EventExt200808 to public
create unique clustered index EventExt200808index1
	on EventExt200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--电表编程记录表
--   '-------Create table 巴基斯坦电表编程记录数据表(ProgrammingReport2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ProgrammingReport2008')
begin
create table ProgrammingReport2008
(
	rtu_id			int   					not null,
	mp_id				smallint   			not null,
	
	date				int							not null,
	time				int							not null,
	
	cons_id			varchar(64)					null,		/*Consumer Identification客户编号*/
	meter_id		varchar(32)					null,		/*Meter Serial No.电表序列号*/
	meter_type	varchar(32)					null,		/*Meter Type电表类型*/
	cur_date		int									null,		/*Current Date抄表日期*/
	cur_time		int									null,		/*Current Time抄表时间*/
	
	pt_ratio		decimal(14, 3) 			DEFAULT 0.0 ,	/*PT Ratio 电压互感器变比*/	
	ct_ratio		decimal(14, 3) 			DEFAULT 0.0 ,	/*CT Ratio电流互感器变比*/	
	reset_date		int 							DEFAULT 0 ,		/*Date of Reset 重置日期 DDhh*/
	md_inter			int 							DEFAULT 0 ,		/*Intergration Period 需量滑差周期*/
	season_num		int 							DEFAULT 0 ,		/*Number of Seasons 季节数*/
	tariff_num		int 							DEFAULT 0 ,		/*Tariff Number 费率数*/
	
	daytable_1		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_2		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_3		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_4		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_5		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_6		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_7		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_8		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_9		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_10		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_11		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_12		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_13		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	daytable_14		int 							DEFAULT 0 ,		/*Season Setting 时区表MMdd*/
	
)
grant select on  ProgrammingReport2008 to public
create unique clustered index ProgrammingReport2008index1
	on ProgrammingReport2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--电表账单数据表
--   '-------Create table 巴基斯坦电表账单数据表(BillingReport2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BillingReport2008')
begin
create table BillingReport2008
(
	rtu_id			int   					not null,
	mp_id				smallint   			not null,
	
	date				int							not null,
	time				int							not null,
	
	cons_id			varchar(64)					null,		/*Consumer Identification客户编号*/
	meter_id		varchar(32)					null,		/*Meter Serial No.电表序列号*/
	meter_type	varchar(32)					null,		/*Meter Type电表类型*/
	cur_date		int									null,		/*Current Date抄表日期*/
	cur_time		int									null,		/*Current Time抄表时间*/
	cur_season	int									null,		/*Current Season季节*/
	
	sreading_tot	decimal(14, 3) 		DEFAULT 0.0 ,	/*Start Reading Total kWh		起始(上2月)总电能示值*/	
	sreading_t1		decimal(14, 3) 		DEFAULT 0.0 ,	/*Start Reading kWh Rate T1	起始电能示值费率1*/	
	sreading_t2		decimal(14, 3) 		DEFAULT 0.0 ,	/*Start Reading kWh Rate T1	起始电能示值费率2*/	
	sreading_t3		decimal(14, 3) 		DEFAULT 0.0 ,	/*Start Reading kWh Rate T1	起始电能示值费率3*/	
	sreading_t4		decimal(14, 3) 		DEFAULT 0.0 ,	/*Start Reading kWh Rate T1	起始电能示值费率4*/	
	
	ereading_tot	decimal(14, 3) 		DEFAULT 0.0 ,	/*End Reading Total kWh		结束(上1月)总电能示值*/	
	ereading_t1		decimal(14, 3) 		DEFAULT 0.0 ,	/*End Reading kWh Rate T1	结束电能示值费率1*/	
	ereading_t2		decimal(14, 3) 		DEFAULT 0.0 ,	/*End Reading kWh Rate T1	结束电能示值费率2*/	
	ereading_t3		decimal(14, 3) 		DEFAULT 0.0 ,	/*End Reading kWh Rate T1	结束电能示值费率3*/	
	ereading_t4		decimal(14, 3) 		DEFAULT 0.0 ,	/*End Reading kWh Rate T1	结束电能示值费率4*/	
	
	kwh_tot				decimal(14, 3) 		DEFAULT 0.0 ,	/*Consumption kWh Total	上1月总用电量*/	
	kwh_t1				decimal(14, 3) 		DEFAULT 0.0 ,	/*Consumption kWh T1	上1月费率1用电量*/
	kwh_t2				decimal(14, 3) 		DEFAULT 0.0 ,	/*Consumption kWh T2	上1月费率2用电量*/
	kwh_t3				decimal(14, 3) 		DEFAULT 0.0 ,	/*Consumption kWh T3	上1月费率3用电量*/
	kwh_t4				decimal(14, 3) 		DEFAULT 0.0 ,	/*Consumption kWh T4	上1月费率4用电量*/
	
	kvarh_tot			decimal(14, 3) 		DEFAULT 0.0 ,	/*Consumption kvarh Total	上1月总用电量*/	
	kvarh_t1			decimal(14, 3) 		DEFAULT 0.0 ,	/*Consumption kvarh T1	上1月费率1用电量*/
	kvarh_t2			decimal(14, 3) 		DEFAULT 0.0 ,	/*Consumption kvarh T2	上1月费率2用电量*/
	kvarh_t3			decimal(14, 3) 		DEFAULT 0.0 ,	/*Consumption kvarh T3	上1月费率3用电量*/
	kvarh_t4			decimal(14, 3) 		DEFAULT 0.0 ,	/*Consumption kvarh T4	上1月费率4用电量*/
	
	--Maximum Demand
	md_tot				decimal(14, 3) 		DEFAULT 0.0 ,	/*LastMonth Max kW Total	上1月最大需量*/	
	md_tot_tm			int 							DEFAULT 0 ,		/*LastMonth Max kW Total Date and Time	上1月最大需量发生时间*/
	
	md_t1					decimal(14, 3) 		DEFAULT 0.0 ,	/*LastMonth Max kW T1	上1月最大需量T1*/	
	md_t1_tm			int 							DEFAULT 0 ,		/*LastMonth Max kW T1 Date and Time	上1月最大需量T1发生时间*/
	md_t2					decimal(14, 3) 		DEFAULT 0.0 ,	/*LastMonth Max kW T2 上1月最大需量T2*/	
	md_t2_tm			int 							DEFAULT 0 ,		/*LastMonth Max kW T2 Date and Time	上1月最大需量T2发生时间*/
	md_t3					decimal(14, 3) 		DEFAULT 0.0 ,	/*LastMonth Max kW T3	上1月最大需量T1*/	
	md_t3_tm			int 							DEFAULT 0 ,		/*LastMonth Max kW T3 Date and Time	上1月最大需量T3发生时间*/
	md_t4					decimal(14, 3) 		DEFAULT 0.0 ,	/*LastMonth Max kW T4	上1月最大需量T1*/	
	md_t4_tm			int 							DEFAULT 0 ,		/*LastMonth Max kW T4 Date and Time	上1月最大需量T4发生时间*/
	
	--Cumulative Demand
	cumdemand_tot	decimal(14, 3) 		DEFAULT 0.0 ,	/*Current Cum kW	当前累计最大需量总*/	
	cumdemand_t1	decimal(14, 3) 		DEFAULT 0.0 ,	/*Current Cum kW T1	当前累计最大需量T1*/	
	cumdemand_t2	decimal(14, 3) 		DEFAULT 0.0 ,	/*Current Cum kW T2	当前累计最大需量T2*/	
	cumdemand_t3	decimal(14, 3) 		DEFAULT 0.0 ,	/*Current Cum kW T3	当前累计最大需量T3*/	
	cumdemand_t4	decimal(14, 3) 		DEFAULT 0.0 ,	/*Current Cum kW T4	当前累计最大需量T4*/
	
	reset_num			int 							DEFAULT 0 ,		/*Nos. of Reset 重置次数*/
	lastreset_dt	int 							DEFAULT 0 ,		/*Date of Last Reset 上次重置日期*/
	
)
grant select on  BillingReport2008 to public
create unique clustered index BillingReport2008index1
	on BillingReport2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束


--电表安全数据表
--   '-------Create table 巴基斯坦电表安全数据表(SecurityReport2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'SecurityReport2008')
begin
create table SecurityReport2008
(
	rtu_id				int   					not null,
	mp_id					smallint   			not null,
	
	date					int							not null,
	time					int							not null,
	
	cons_id				varchar(64)					null,		/*Consumer Identification客户编号*/
	meter_id			varchar(32)					null,		/*Meter Serial No.电表序列号*/
	meter_type		varchar(32)					null,		/*Meter Type电表类型*/
	cur_date			int									null,		/*Current Date抄表日期*/
	cur_time			int									null,		/*Current Time抄表时间*/
	cur_season		int									null,		/*Current Season季节*/
	
	IR_date				int									null,		/*Last IR Comm. Date and Time 上次红外通讯日期和时间YYMMDD*/
	IR_time				int									null,		/*hhmmss*/
	IR_num				int						DEFAULT 0 ,		/*Nos. of Reset 重置次数*/		/*No. IR Comm. 红外通讯次数*/
	
	md_date				int									null,		/*Last Demand Reset Date and Time 需量上一次重置的日期和时间YYMMDD*/
	md_time				int									null,		/*hhmm*/
	md_num				int						DEFAULT 0 ,		/*No. Demand Reset 需量重置次数*/
	
	prog_date			int									null,		/*Last Programming Date and Time 上次编辑日期和时间YYMMDD*/
	prog_time			int									null,		/*hhmmss*/
	
	outage_sdate	int									null,		/*Last Power Outage Date and Time 上次停电开始日期和时间 YYMMDD*/
	outage_stime	int									null,		/*hhmmss*/
	outage_edate	int									null,		/*Last Power Outage Date and Time 上次停电结束日期和时间 YYMMDD*/
	outage_etime	int									null,		/*hhmmss*/
	outage_num		int						DEFAULT 0 ,		/*No. of Power Outages 停电次数*/	
)
grant select on  SecurityReport2008 to public
create unique clustered index SecurityReport2008index1
	on SecurityReport2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--四川明星用电支撑系统
--   '-------Create table 工单信息表(workform_support2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'workform_support2008')
begin
create table workform_support2008
(
	id					int							not null,
	formno			varchar(16)			not null,	/*表单编号(可根据公司要求生成)*/
	formstate		tinyint					null default(0),/*表单状态 0=初始下发 1=负责人分配 2=执行人员回执负责人审核 3=审核通过值班人回执 4=结束*/
	problemtype tinyint					null,		/*问题类型*/
	
	sendman			varchar(16)			not null,	/*发起人(存储为用户账号)*/
	sendmantel	varchar(16)					null,	/*发起人电话号码*/
	sendymd			int							not null, /*发起日期*/
	sendhms			int									null, /*发起时间*/
	problemdesc	varchar(1024)				null,	/*问题描述*/
	
	fzman				varchar(16)					null, /*负责人(存储为用户账号)*/
	fzmantel		varchar(16)					null, /*负责人电话*/
	orderymd		int									null, /*下发分配日期*/
	orderhms		int									null, /*下发分配时间*/
	planymd			int									null,	/*计划完成日期*/
	orderremark	varchar(1024)				null, /*负责人意见*/
	
	operman			varchar(16)					null, /*执行人(存储为用户账号)*/
	opermantel	varchar(16)					null,	/*执行人电话号码*/
	operymd			int									null, /*完成日期*/
	operhms			int									null, /*完成时间*/
	operremark	varchar(1024)				null, /*完成情况*/
	
	reserve1		varchar(16)					null, /*备用字段1*/
	reserve2		varchar(16)					null  /*备用字段2*/	
	
	CONSTRAINT workform_support2008_index1 PRIMARY KEY (id)	
)
grant select on  workform_support2008 to public
end
go
-- 标记结束

--四川明星用电支撑系统
--   '-------Create table 工单附件表(attach_file2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'attach_file2008')
begin
create table attach_file2008
(
	id				int							not null,	/*id*/	 
	formno		varchar(16)			not null,	/*工单编号(与workform_support中formno一致)*/

	filename 	varchar(64)			not	null,	/*文件名*/
	filetext			image   		not	null,	/*文件内容*/
	submitman varchar(16)			null	/*附件提交人*/
	
	CONSTRAINT attach_file2008_index1 PRIMARY KEY (id)					
)
grant select on attach_file2008 to public
end
go
-- 标记结束


--正泰龙终端图片
--   '-------Create table 终端图片信息表(rtuimg2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'rtuimg2008')
begin
create table rtuimg2008
(
	rtu_id	int			   				not null,
	date	int							not null,
	time	int							not null,
	fid 	tinyint,
	ftype	tinyint,
	fcmd	tinyint,
	fsize	int,
	ftime	int,
	fdata	image
	)
grant select on  rtuimg2008 to public
create unique clustered index rtuimg2008index1
	on rtuimg2008(rtu_id,date,time)
end
go
-- 标记结束


--正泰龙变压器防烧预警
--   '-------Create table 终端变压器防烧预警信息表(Fsyj200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'Fsyj200808')
begin
create table Fsyj200808
(
	rtu_id				int			   			not null,
	mp_id					smallint 				not null,
	date					int							not null,
	time					int							not null,
	temp_env			decimal(14, 3)	DEFAULT 0.0,/*环境温度*/
	temp_oil			decimal(14, 3)	DEFAULT 0.0,/*顶油温度*/
	temp_winding	decimal(14, 3)	DEFAULT 0.0,/*绕组温度*/
	min_remain		int							DEFAULT 0,/*剩余安全运行时间-分钟*/

	)
grant select on  Fsyj200808 to public
create unique clustered index Fsyj200808index1
	on Fsyj200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束



----------------------流量计应用开始 由星光糖业移植过来 用于四表集抄201610-------
--   '-------Create table 流量计实时流量数据表(JFRealFlow200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JFRealFlow200808')
begin
create table JFRealFlow200808
(
	rtu_id			int			   			not null,
	mp_id				smallint   			not null,
	date				int							not null,
	time				int							not null,
	
	--正向	
	ljll_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/	


	--反向
	ljll_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/	
	
	
	ll_ljll_zx	decimal(14, 3) 		DEFAULT 0.0 ,	/*正向累积流量*/	
	ll_ljll_fx	decimal(14, 3) 		DEFAULT 0.0 ,	/*反向累积流量*/	
	rl_ljrl_zx	decimal(14, 3) 		DEFAULT 0.0 ,	/*正向累积热量*/	
	rl_ljrl_fx	decimal(14, 3) 		DEFAULT 0.0 ,	/*反向累积热量*/	
	
	source			tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  JFRealFlow200808 to public
create unique clustered index JFRealFlow200808index1
	on JFRealFlow200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 流量计分钟流量数据表(JFMinFlow200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JFMinFlow200808')
begin
create table JFMinFlow200808
(
	rtu_id			int			   			not null,
	mp_id				smallint   			not null,
	date				int							not null,
	time				int							not null,
	
	--正向
	ljll_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/	


	--反向
	ljll_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/	
	
	
	ll_ljll_zx	decimal(14, 3) 		DEFAULT 0.0 ,	/*正向累积流量*/	
	ll_ljll_fx	decimal(14, 3) 		DEFAULT 0.0 ,	/*反向累积流量*/	
	rl_ljrl_zx	decimal(14, 3) 		DEFAULT 0.0 ,	/*正向累积热量*/	
	rl_ljrl_fx	decimal(14, 3) 		DEFAULT 0.0 ,	/*反向累积热量*/	
	
	source			tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  JFMinFlow200808 to public
create unique clustered index JFMinFlow200808index1
	on JFMinFlow200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 流量计日流量数据表(JFDayFlow200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JFDayFlow200808')
begin
create table JFDayFlow200808
(
	rtu_id			int			   			not null,
	mp_id				smallint   			not null,
	date				int							not null,
	time				int							not null,
	
	--正向
	ljll_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/	


	--反向
	ljll_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/	
	
	
	ll_ljll_zx	decimal(14, 3) 		DEFAULT 0.0 ,	/*正向累积流量*/	
	ll_ljll_fx	decimal(14, 3) 		DEFAULT 0.0 ,	/*反向累积流量*/	
	rl_ljrl_zx	decimal(14, 3) 		DEFAULT 0.0 ,	/*正向累积热量*/	
	rl_ljrl_fx	decimal(14, 3) 		DEFAULT 0.0 ,	/*反向累积热量*/	
	
	ljsj_run		int							DEFAULT 0,			/*累积工作时间*/
	now_time		int							DEFAULT 0,			/*实时时间*/
	st					int							DEFAULT 0,			/*状态ST*/

	source			tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  JFDayFlow200808 to public
create unique clustered index JFDayFlow200808index1
	on JFDayFlow200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 流量计月流量数据表(JFMonFlow2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JFMonFlow2008')
begin
create table JFMonFlow2008
(
	rtu_id			int			   			not null,
	mp_id				smallint   			not null,
	date				int							not null,
	time				int							not null,
	
	--正向
	ljll_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/	


	--反向
	ljll_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/	
	
	
	ll_ljll_zx	decimal(14, 3) 		DEFAULT 0.0 ,	/*正向累积流量*/	
	ll_ljll_fx	decimal(14, 3) 		DEFAULT 0.0 ,	/*反向累积流量*/	
	rl_ljrl_zx	decimal(14, 3) 		DEFAULT 0.0 ,	/*正向累积热量*/	
	rl_ljrl_fx	decimal(14, 3) 		DEFAULT 0.0 ,	/*反向累积热量*/	
	
	source			tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  JFMonFlow2008 to public
create unique clustered index JFMonFlow2008index1
	on JFMonFlow2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束

--   '-------Create table 流量计实时瞬时量数据表(JFRealSsl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JFRealSsl200808')
begin
create table JFRealSsl200808
(
	rtu_id			int			   			not null,
	mp_id				smallint   			not null,
	date				int							not null,
	time				int							not null,
	
	--正向
	ssll_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*瞬时流量*/	
	ssrl_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*瞬时热量*/	
	ljll_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/
	--反向
	ssll_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*瞬时流量*/	
	ssrl_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*瞬时热量*/	
	ljll_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/		
	
	wd1				decimal(14, 3) 	DEFAULT 0.0 ,	/*温度1*/
	wd2				decimal(14, 3) 	DEFAULT 0.0 ,	/*温度2*/
	sd1				decimal(14, 3) 	DEFAULT 0.0 ,	/*湿度1*/
	sd2				decimal(14, 3) 	DEFAULT 0.0 ,	/*湿度2*/
	yl1				decimal(14, 3) 	DEFAULT 0.0 ,	/*压力1*/
	yl2				decimal(14, 3) 	DEFAULT 0.0 ,	/*压力2*/
	pl				decimal(14, 3) 	DEFAULT 0.0 ,	/*频率*/
	md				decimal(14, 3) 	DEFAULT 0.0 ,	/*密度*/
	ls				decimal(14, 3) 	DEFAULT 0.0 ,	/*流速*/
	rh				decimal(14, 3) 	DEFAULT 0.0 ,	/*热焓*/		
	cy 				decimal(14, 3) 	DEFAULT 0.0 ,	/*差压*/
	rgl 			decimal(14, 3) 	DEFAULT 0.0 ,	/*热功率*/
--	dl 				decimal(14, 3) 	DEFAULT 0.0 ,	/*电流*/
--	dy 				decimal(14, 3) 	DEFAULT 0.0 ,	/*电压*/
	
	td_cnt		int 						DEFAULT 0 	,	/*停电次数*/	
	td_tot		int 						DEFAULT 0 	,	/*停电累计时间*/	
	
	reserve1		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段1*/	
	reserve2		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段2*/	
	reserve3		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段3*/	
	reserve4		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段4*/
	
	source			tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  JFRealSsl200808 to public
create unique clustered index JFRealSsl200808index1
	on JFRealSsl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 流量计分钟瞬时量数据表(JFMinSsl200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'JFMinSsl200808')
begin
create table JFMinSsl200808
(
	rtu_id			int			   			not null,
	mp_id				smallint   			not null,
	date				int							not null,
	time				int							not null,
	
	--正向
	ssll_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*瞬时流量*/	
	ssrl_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*瞬时热量*/	
	ljll_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_zx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/
	--反向
	ssll_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*瞬时流量*/	
	ssrl_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*瞬时热量*/	
	ljll_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积流量示值*/	
	ljrl_fx			decimal(14, 3) 		DEFAULT 0.0 ,	/*累积热量示值*/	
	
	wd1				decimal(14, 3) 	DEFAULT 0.0 ,	/*温度1*/
	wd2				decimal(14, 3) 	DEFAULT 0.0 ,	/*温度2*/
	sd1				decimal(14, 3) 	DEFAULT 0.0 ,	/*湿度1*/
	sd2				decimal(14, 3) 	DEFAULT 0.0 ,	/*湿度2*/
	yl1				decimal(14, 3) 	DEFAULT 0.0 ,	/*压力1*/
	yl2				decimal(14, 3) 	DEFAULT 0.0 ,	/*压力2*/
	pl				decimal(14, 3) 	DEFAULT 0.0 ,	/*频率*/
	md				decimal(14, 3) 	DEFAULT 0.0 ,	/*密度*/
	ls				decimal(14, 3) 	DEFAULT 0.0 ,	/*流速*/
	rh				decimal(14, 3) 	DEFAULT 0.0 ,	/*热焓*/		
	cy 				decimal(14, 3) 	DEFAULT 0.0 ,	/*差压*/
	rgl 			decimal(14, 3) 	DEFAULT 0.0 ,	/*热功率*/
--	dl 				decimal(14, 3) 	DEFAULT 0.0 ,	/*电流*/
--	dy 				decimal(14, 3) 	DEFAULT 0.0 ,	/*电压*/
	
	td_cnt		int 						DEFAULT 0 	,	/*停电次数*/	
	td_tot		int 						DEFAULT 0 	,	/*停电累计时间*/	
	
	reserve1		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段1*/	
	reserve2		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段2*/	
	reserve3		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段3*/	
	reserve4		decimal(14, 3) 	DEFAULT 0.0 ,	/*扩展字段4*/
	
	source			tinyint					DEFAULT 0			/*数据来源*/
)
grant select on  JFMinSsl200808 to public
create unique clustered index JFMinSsl200808index1
	on JFMinSsl200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束


----------------------流量计应用结束--------------------------------------------





--太钢组合有功20170106
--   '-------Create table 专变配变实时组合电能数据表(ZRealZHBD200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZRealZHBD200808')
begin
create table ZRealZHBD200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	bd_zhyg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功总电能*/
	bd_zhyg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率1电能*/
	bd_zhyg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率2电能*/
	bd_zhyg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率3电能*/
	bd_zhyg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率4电能*/
	
	bd_zhwg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功总电能*/
	bd_zhwg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率1电能*/
	bd_zhwg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率2电能*/
	bd_zhwg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率3电能*/
	bd_zhwg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率4电能*/
)
grant select on  ZRealZHBD200808 to public
create unique clustered index ZRealZHBD200808index1
	on ZRealZHBD200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 专变配变分钟组合电能数据表(ZMinZHBD200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMinZHBD200808')
begin
create table ZMinZHBD200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	bd_zhyg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功总电能*/
	bd_zhyg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率1电能*/
	bd_zhyg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率2电能*/
	bd_zhyg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率3电能*/
	bd_zhyg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率4电能*/
	
	bd_zhwg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功总电能*/
	bd_zhwg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率1电能*/
	bd_zhwg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率2电能*/
	bd_zhwg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率3电能*/
	bd_zhwg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率4电能*/
)
grant select on  ZMinZHBD200808 to public
create unique clustered index ZMinZHBD200808index1
	on ZMinZHBD200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 专变配变日组合电能数据表(ZDayZHBD200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZDayZHBD200808')
begin
create table ZDayZHBD200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	bd_zhyg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功总电能*/
	bd_zhyg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率1电能*/
	bd_zhyg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率2电能*/
	bd_zhyg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率3电能*/
	bd_zhyg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率4电能*/
	
	bd_zhwg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功总电能*/
	bd_zhwg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率1电能*/
	bd_zhwg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率2电能*/
	bd_zhwg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率3电能*/
	bd_zhwg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率4电能*/
)
grant select on  ZDayZHBD200808 to public
create unique clustered index ZDayZHBD200808index1
	on ZDayZHBD200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 专变配变月组合电能数据表(ZMonZHBD2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'ZMonZHBD2008')
begin
create table ZMonZHBD2008
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	bd_zhyg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功总电能*/
	bd_zhyg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率1电能*/
	bd_zhyg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率2电能*/
	bd_zhyg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率3电能*/
	bd_zhyg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率4电能*/
	
	bd_zhwg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功总电能*/
	bd_zhwg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率1电能*/
	bd_zhwg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率2电能*/
	bd_zhwg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率3电能*/
	bd_zhwg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率4电能*/
)
grant select on  ZMonZHBD2008 to public
create unique clustered index ZMonZHBD2008index1
	on ZMonZHBD2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 变电站实时组合电能数据表BRealZHBD200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BRealZHBD200808')
begin
create table BRealZHBD200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	bd_zhyg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功总电能*/
	bd_zhyg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率1电能*/
	bd_zhyg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率2电能*/
	bd_zhyg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率3电能*/
	bd_zhyg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率4电能*/
	
	bd_zhwg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功总电能*/
	bd_zhwg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率1电能*/
	bd_zhwg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率2电能*/
	bd_zhwg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率3电能*/
	bd_zhwg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率4电能*/
)
grant select on  BRealZHBD200808 to public
create unique clustered index BRealZHBD200808index1
	on BRealZHBD200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 变电站分钟组合电能数据表BMinZHBD200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMinZHBD200808')
begin
create table BMinZHBD200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	bd_zhyg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功总电能*/
	bd_zhyg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率1电能*/
	bd_zhyg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率2电能*/
	bd_zhyg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率3电能*/
	bd_zhyg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率4电能*/
	
	bd_zhwg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功总电能*/
	bd_zhwg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率1电能*/
	bd_zhwg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率2电能*/
	bd_zhwg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率3电能*/
	bd_zhwg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率4电能*/
)
grant select on  BMinZHBD200808 to public
create unique clustered index BMinZHBD200808index1
	on BMinZHBD200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 变电站日组合电能数据表(BDayZHBD200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BDayZHBD200808')
begin
create table BDayZHBD200808
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	bd_zhyg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功总电能*/
	bd_zhyg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率1电能*/
	bd_zhyg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率2电能*/
	bd_zhyg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率3电能*/
	bd_zhyg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率4电能*/
	
	bd_zhwg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功总电能*/
	bd_zhwg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率1电能*/
	bd_zhwg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率2电能*/
	bd_zhwg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率3电能*/
	bd_zhwg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率4电能*/
)
grant select on  BDayZHBD200808 to public
create unique clustered index BDayZHBD200808index1
	on BDayZHBD200808(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 变电站月组合电能数据表(BMonZHBD2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'BMonZHBD2008')
begin
create table BMonZHBD2008
(
	rtu_id	int			   			not null,
	mp_id		smallint   			not null,
	date		int							not null,
	time		int							not null,
	
	bd_zhyg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功总电能*/
	bd_zhyg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率1电能*/
	bd_zhyg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率2电能*/
	bd_zhyg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率3电能*/
	bd_zhyg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合有功费率4电能*/
	
	bd_zhwg				decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功总电能*/
	bd_zhwg_fl1		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率1电能*/
	bd_zhwg_fl2		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率2电能*/
	bd_zhwg_fl3		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率3电能*/
	bd_zhwg_fl4		decimal(14, 3) 	DEFAULT 0.0 ,	/*	组合无功费率4电能*/
)
grant select on  BMonZHBD2008 to public
create unique clustered index BMonZHBD2008index1
	on BMonZHBD2008(rtu_id,mp_id,date,time)
end
go
-- 标记结束	

--   '-------Create table 采集状态表数据表(cjstate2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'cjstate2008')
begin
create table cjstate2008
(
--	cons_id			int			   			not null,
	line_fzman_id		smallint				not	null,			/*线路负责人*/
	ymd							int							not null,
	app_type				tinyint					not null,			/*应用类型  0 变电站 1 专变 2 公变   3 低压集抄   128 所有应用 */	
	paratotnum			int							 		null,     /*电表总数*/	
	totnum					int							 		null,
	realnum					int									null,
	rate						decimal(14, 6) 	DEFAULT 0.0 ,	/*采集成功率*/	
)
grant select on  cjstate2008 to public
create unique clustered index cjstate2008index1
	on cjstate2008(line_fzman_id, ymd, app_type)
end
go
-- 标记结束

--   '-------Create table 终端时钟数据表(rtutime2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'rtutime2008')
begin
create table rtutime2008
(
	rtu_id					int			   			not null,		/*终端编号*/
	oper_ym					int							not null,		/*操作年月*/
	rtu_time				int									null,		/*终端时钟*/
	sys_time				int									null,		/*系统时钟*/
)
grant select on  rtutime2008 to public
create unique clustered index rtutime2008index1
	on rtutime2008(rtu_id, oper_ym)
end
go
-- 标记结束

--	'-------Create table 流量计预付费记录表(FmYff2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'FmYff2008')
begin
create table FmYff2008
(
	rtu_id				int						  		not null,
	mp_id					smallint  					not null,	
	
--res_id				smallint						not null,			/*客户编号*/
	glo_resid				int										null,		/*居民用户编号*/
	res_desc			char(64)								null,			/*客户名称*/

	op_man				char(64)						not null,			/*操作员*/	
	op_type				tinyint							not null,			/*本次操作类型 0 初始态, 1 开户, 2 缴费, 3 补卡, 4 补写卡, 5 冲正, 6 换表, 7 换费率 10 销户 */	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/

	pay_type			tinyint									null,			/*缴费方式*/

	wasteno				varchar(32)							null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/	

	pay_money			decimal(12,2)						null,			/*缴费金额*/	
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)						null,			/*总金额*/

	--add 201406
	buy_dl				decimal(12,2)						null,			/*购电量*/
	pay_bmc				decimal(12,2)						null,			/*表码差*//*又名写卡电量*/
	--end
	
	shutdown_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额*/
	
	jsbd_zyz			decimal(12,2)						null,			/*测量点1-结算总表底*/
	jsbd_ymd			int											null,			/*结算时间*/
	
  calc_bdymd		int										 	null,			/*算费表底时间-YYYYMMDD*/              		                  
	calc_zyz			decimal(12,2)		  			null,			/*算费时总表底*/

	now_remain		decimal(12,2)						null,			/*当前剩余*/

	total_gdz			decimal(12,2)						null,			/*累计购电值*/

	jt_total_zbdl	decimal(12,2)						null,			/*阶梯追补累计用电量*/
	jt_total_dl		decimal(12,2)						null,			/*阶梯累计用电量*/

	alarm1				decimal(12,2)						null,			/*报警值1*/		
	alarm2				decimal(12,2)						null,			/*报警值2*/			

	buy_times			int											null,			/*购电次数*/		

	cur_feeproj		smallint								null,			/*当前费率号*/

	op_result			tinyint									null,			/*操作结果 0:未成功 1:写卡成功 2:远程设置成功 3:主站操作成功 4:暂时不处理 5:正在操作 6:失败*/

	visible_flag	tinyint									null,			/*是否显示*/	

	sg186_ysdw		varchar(20)							null,			/*186应收单位标识*/	
	up186_flag		tinyint									null,			/*186上传成功标志*/
	checkpay_flag	tinyint									null			/*对账成功标志*/
)
grant select on FmYff2008 to public
create clustered index FmYff2008index1
	on FmYff2008(rtu_id,mp_id,op_date,op_time,op_type)	
create index FmYff2008index2 
	on FmYff2008(rtu_id,glo_resid,op_date,op_time,op_type)  
end
go
-- 标记结束


--	'-------Create table 流量计预付费记录表备份(sysfmyfrcd2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'sysfmyfrcd2008')
begin
create table sysfmyfrcd2008
(
	rtu_id				int						  		not null,
	mp_id					smallint  					not null,	
	
--res_id				smallint						not null,			/*客户编号*/
	glo_resid				int										null,		/*居民用户编号*/
	res_desc			char(64)								null,			/*客户名称*/

	op_man				char(64)						not null,			/*操作员*/	
	op_type				tinyint							not null,			/*本次操作类型 0 初始态, 1 开户, 2 缴费, 3 补卡, 4 补写卡, 5 冲正, 6 换表, 7 换费率 10 销户 */	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/

	pay_type			tinyint									null,			/*缴费方式*/

	wasteno				varchar(32)							null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/	

	pay_money			decimal(12,2)						null,			/*缴费金额*/	
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)						null,			/*总金额*/

	--add 201406
	buy_dl				decimal(12,2)						null,			/*购电量*/
	pay_bmc				decimal(12,2)						null,			/*表码差*//*又名写卡电量*/
	--end
	
	shutdown_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额*/
	
	jsbd_zyz			decimal(12,2)						null,			/*测量点1-结算总表底*/
	jsbd_ymd			int											null,			/*结算时间*/
	
  calc_bdymd		int										 	null,			/*算费表底时间-YYYYMMDD*/              		                  
	calc_zyz			decimal(12,2)		  			null,			/*算费时总表底*/

	now_remain		decimal(12,2)						null,			/*当前剩余*/

	total_gdz			decimal(12,2)						null,			/*累计购电值*/

	jt_total_zbdl	decimal(12,2)						null,			/*阶梯追补累计用电量*/
	jt_total_dl		decimal(12,2)						null,			/*阶梯累计用电量*/

	alarm1				decimal(12,2)						null,			/*报警值1*/		
	alarm2				decimal(12,2)						null,			/*报警值2*/			

	buy_times			int											null,			/*购电次数*/		

	cur_feeproj		smallint								null,			/*当前费率号*/

	op_result			tinyint									null,			/*操作结果 0:未成功 1:写卡成功 2:远程设置成功 3:主站操作成功 4:暂时不处理 5:正在操作 6:失败*/

	visible_flag	tinyint									null,			/*是否显示*/	

	sg186_ysdw		varchar(20)							null,			/*186应收单位标识*/	
	up186_flag		tinyint									null,			/*186上传成功标志*/
	checkpay_flag	tinyint									null			/*对账成功标志*/
)
grant select on sysfmyfrcd2008 to public
create clustered index sysfmyfrcd2008index1
	on sysfmyfrcd2008(rtu_id,mp_id,op_date,op_time,op_type)	
create index sysfmyfrcd2008index2 
	on sysfmyfrcd2008(rtu_id,glo_resid,op_date,op_time,op_type)  
end
go
-- 标记结束


--	'-------Create table 流量计负荷控制记录表(FmFk2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'FmFk2008')
begin
create table FmFk2008
(
	rtu_id				int						  		not null,
	mp_id					smallint  					not null,
	glo_resid			int											null,		/*居民用户编号*/
		
	op_date				int									not null,
	op_time				int									not null,
	
	op_man				char(64)								null,			/*操作员*/	
	op_type				tinyint									null,			/*控制类型*/
	
	shuttime			int											null,			/*断电时间长度*/	

	op_result			tinyint				default 0 null,			/*操作结果*/
	
	reserve1		  int											null,
	reserve2		  int											null
)
grant select on FmFk2008 to public
create unique clustered index FmFk2008index1
	on FmFk2008(rtu_id, mp_id, op_date, op_time)
create index FmFk2008index2 
	on FmFk2008(rtu_id,glo_resid,op_date,op_time)  
end
go
-- 标记结束



--	'-------Create table (sysfmcontrol2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'sysfmcontrol2008')
begin
create table sysfmcontrol2008
(
	rtu_id				int						  		not null,
	mp_id					smallint  					not null,
	glo_resid			int											null,		/*居民用户编号*/
		
	op_date				int									not null,
	op_time				int									not null,
	
	op_man				char(64)								null,			/*操作员*/	
	op_type				tinyint									null,			/*控制类型*/
	
	shuttime			int											null,			/*断电时间长度*/	

	op_result			tinyint				default 0 null,			/*操作结果*/
	
	reserve1		  int											null,
	reserve2		  int											null
)
grant select on sysfmcontrol2008 to public
create unique clustered index sysfmcontrol2008index1
	on sysfmcontrol2008(rtu_id,mp_id, op_date, op_time)
create index sysjcontrolindex2 
	on sysfmcontrol2008(rtu_id,glo_resid,op_date,op_time)  	
end
go
-- 标记结束


--   '-------Create table 流量计发行电费余额数据表(FmFxMoney2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'FmFxMoney2008')
begin
create table FmFxMoney2008
(
	rtu_id						int							  not null,
	mp_id							smallint				  not null,					/*zjg_id mp_id*/
	fxdf_ym						int								not null, 				/*发行电费年月*/
--data_type					tinyint						not null, 				/*数据类型 0 发行电费 1 阶梯切换*/
		
	cons_no						varchar(64)			  not null,					/*客户编号 居民户号*/
  
  fxdf_mdhmi				int										null,					/*发行电费算费时间-MMDDHHMI*/
  fxdf_lastym				int										null,					/*上次电费年月*/

	iall_money				decimal(12,2)		  		null,					/*本月缴费总金额*/
	last_remain				decimal(12,2)		  		null,					/*上次剩余*/
	
	now_remain				decimal(12,2)		  		null,					/*当前剩余*/
	
	jt_total_zbdl			decimal(12,2)		  		null,					/*阶梯追补累计用电量*/
	jt_total_dl				decimal(12,2)		  		null,					/*阶梯累计用电量*/

	df_money					decimal(12,2)		  not null,					/*电费金额*/
	  	                                    
	shutdown_val			decimal(12,2)		  		null,					/*断电值 金额计费时为:断电金额 */	
	
 	jsbd_ymd					int										null,					/*上次结算时间*/
	jsbd_zyz					decimal(12,2)		  		null,					/*上次结算总表底*/
	 
  calc_bdymd				int										null,					/*算费表底时间-YYYYMMDD*/              		                  
	calc_zyz					decimal(12,2)		  		null,					/*算费时总表底*/
	
	update_count			int										null,					/*更新次数*/

  all_monthf				tinyint								null,					/*整月标志*/
	bc_flag						tinyint					  		null, 				/*结算补差标记*/
	bc_date						int							  		null,					/*结算补差日期*/
	bc_time						int							  		null,					/*结算补差时间*/
	op_man						varchar(64)			  		null,					/*操作员*/	
)
grant select on  FmFxMoney2008 to public
create unique clustered index FmFxMoney2008index1
	on FmFxMoney2008(rtu_id,mp_id,fxdf_ym)
end
go
-- 标记结束


--   '-------Create table 流量计阶梯清零记录(FmJtReset2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'FmJtReset2008')
begin
create table FmJtReset2008
(
	rtu_id						int							  not null,
	mp_id							smallint				  not null,					/*zjg_id mp_id*/
	date							int								not null, 				/*发行电费年月*/
		
	cons_no						varchar(64)			  not null,					/*客户编号 居民户号*/
  
  jtreset_mdhmi			int										null,					/*清零执行时间-MMDDHHMI*/
  jtreset_lastymd		int										null,					/*上次清零日期*/

	jt_total_zbdl			decimal(12,2)		  		null,					/*阶梯追补累计用电量*/
	jt_total_dl				decimal(12,2)		  		null,					/*阶梯累计用电量*/
			                                    
	shutdown_val			decimal(12,2)		  		null,					/*断电值 金额计费时为:断电金额 */	
	
	jsbd_ymd					int										null,					/*上次结算时间*/
	jsbd_zyz					decimal(12,2)		  		null,					/*上次结算总表底*/

  calc_bdymd				int										null,					/*算费表底时间-YYYYMMDD*/              		                  
	calc_zyz					decimal(12,2)		  		null,					/*算费时总表底*/

	op_man						varchar(64)			  		null,					/*操作员*/	
)                                     
grant select on  FmJtReset2008 to public
create unique clustered index FmJtReset2008index1
	on FmJtReset2008(rtu_id,mp_id,date)
end
go
-- 标记结束


--	'-------Create table 费率更改记录表(FmFeeRateChg2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'FmFeeRateChg2008')
begin
create table FmFeeRateChg2008
(
	rtu_id						int						  		not null,
	mp_id							smallint  					not null,
	glo_resid					int									not null,					/*客户编号*/

	op_date						int									not null,
	op_time						int									not null,
	
	op_man						char(64)								null,					/*操作员*/	

	/*旧费率*/
	old_id						smallint        				null,					/*编号*/
	old_describe			varchar(64)     				null,					/*名称*/
	old_meter_type		tinyint									null,					
	/*add*/
	old_fee_type			tinyint									null,					/*费率*/ /*1单费率 2复费率 3混合费率 4阶梯*/
	
	/*单费率*/
	old_rated_z				decimal(12,6)			  		null,					/*总费率*/
	
	/*阶梯电价*/
	old_ratej_type 		tinyint									null,					/*阶梯电价类型 	0 年度方案, 1月度方案	2月度峰谷/阶梯混合*/
	old_ratej_num 		tinyint									null,					/*阶梯电价数*/
			
	old_ratej_r1			decimal(12,6)   				null,					/*阶梯费率1*/
	old_ratej_r2			decimal(12,6)			  		null,					/*阶梯费率2*/	
	old_ratej_r3			decimal(12,6)  					null,					/*阶梯费率3*/
	old_ratej_r4			decimal(12,6)   				null,					/*阶梯费率4*/
		
	old_ratej_td1			decimal(12,6)						null,					/*阶梯梯度值1*/
	old_ratej_td2			decimal(12,6)						null,					/*阶梯梯度值2*/
	old_ratej_td3			decimal(12,6)						null,					/*阶梯梯度值3*/
	
	/*新费率*/
	new_id							smallint        			null,					/*编号*/
	new_describe				varchar(64)     			null,					/*名称*/
	new_meter_type			tinyint								null,
	new_fee_type				tinyint								null,					/*费率*/ /*1单费率 2复费率 3混合费率 4阶梯*/
	
	/*单费率*/
	new_rated_z					decimal(12,6)			  	null,					/*总费率*/
	
	/*阶梯电价*/
	new_ratej_type 			tinyint								null,					/*阶梯电价类型 	0 年度方案, 1月度方案	2月度峰谷/阶梯混合*/
	new_ratej_num 			tinyint								null,					/*阶梯电价数*/	
	                                        	
	new_ratej_r1				decimal(12,6)   			null,					/*阶梯费率1*/
	new_ratej_r2				decimal(12,6)			  	null,					/*阶梯费率2*/	
	new_ratej_r3				decimal(12,6)  				null,					/*阶梯费率3*/
	new_ratej_r4				decimal(12,6)   			null,					/*阶梯费率4*/

	new_ratej_td1				decimal(12,6)					null,					/*阶梯梯度值1*/
	new_ratej_td2				decimal(12,6)					null,					/*阶梯梯度值2*/
	new_ratej_td3				decimal(12,6)					null,					/*阶梯梯度值3*/
	
	reserve1		 				int										null,
	reserve2		 				int										null		
)
grant select on FmFeeRateChg2008 to public
create unique clustered index FmFeeRateChg2008index1
	on FmFeeRateChg2008(rtu_id, mp_id, op_date, op_time)
end
go
-- 标记结束

--	'-------Create table 多表集抄接口缴费记录表(order_rcd2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'order_rcd2008')
begin
create table order_rcd2008
(
	wasteno						varchar(32)					not	null,			/*流水号*/	

	create_ymd				int       	        not null,     /*创建日期*/
	create_hms				int       	        not null,     /*创建时间*/
	
	userid						int									not null,			/*用户id*
	glo_resno					varchar(32)					not	null,			/*接口编号户号*/
	cons_desc					varchar(128)						null,			/*用户名称*/

	org_no						varchar(32)							null,			/*营业区域代码*/

	charge_id					varchar(16)							null,			/*收费人员/POS机编号*/
	charge_name				varchar(64)							null,			/*收费员名称*/

	pay_money					int									not	null,			/*缴费金额单位为分*/
	pay_flag					tinyint									null,			/*缴费冲正标志 2缴费,  5冲正*/

	success_flag			tinyint            			 null default 0,    /*支付成功标志， 1 未支付  2 已支付*/

	--更新信息
	source_type				tinyint              		 null,    /*充值来源 1 支付宝 2 微信 3 银联 4 内部缴费 9 转账*/
	trade_no					varchar(32)              null,    /*订单号（微信订单号transaction_id、支付宝交易号trade_no）*/
  pay_ymd           int       	         		 null,    /*完成日期 微信gmt_create或支付宝提供 time_end*/
  pay_hms           int       	         		 null,    /*完成时间 微信或支付宝提供*/
  buyer_info				varchar(64)              null,    /*客户信息 微信openid 支付宝 buyer_email*/

  --发票相关 暂时不用
	printinvoice_f				tinyint            	not null default 1,    /*打印发票标志 1 未打印 2 已打印*/
	print_ymd						int											 null, 		/*打印日期*/
	print_hms						int											 null, 		/*打印时间*/
	print_man						varchar(32)           	 null,    /*打印操作员*/
	voice_no						varchar(16)							 null, 		/*发票编号*/

	reconciliation		tinyint              not null ,    /*对账标志 1 未对账 2 对账单中含有本记录 3 本记录数据库中有，excel中无 */
																											 /* 4 本记录数据库中无，execl中有，根据excel新生成记录*/
	reconciliymd			int											 null,		/*对账日期*/
	
	settlement				tinyint							 not null,		/*结算标志 1 未结算 2 已结算*/
	settleymd					int											 null,		/*结算日期*/
   
	reserve1					int											null,			/*扩展字段*/
	reserve2					int											null,			/*扩展字段*/
)
grant select on order_rcd2008 to public
create clustered index order_rcd2008index1	on order_rcd2008(wasteno)  
create index order_rcd2008index2 		on order_rcd2008(glo_resno, create_ymd, create_hms)  
create index order_rcd2008index3 		on order_rcd2008(userid) 

end
go

--	'-------Create table 多表集抄接口缴费记录表(pubpay_order2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'pubpay_order2008')
begin
create table pubpay_order2008
(
	rtu_id						int									not null,			/*终端编号*/
	mp_id							smallint						not null,			/*测点编号*/

	wasteno						varchar(32)					not	null,			/*流水号*/	
	meter_type				tinyint 						not null,			/*表计类型 见数据字典2090*/

	userid						int									not null,			/*用户id*/

	glo_resno					varchar(32)					not	null,			/*接口编号户号*/
	cons_desc					varchar(128)						null,			/*用户名称*/

	org_no						varchar(32)							null,			/*营业区域代码*/

	create_ymd				int       	        not null,     /*创建日期*/
	create_hms				int       	        not null,     /*创建时间*/

	charge_id					varchar(16)							null,			/*收费人员/POS机编号*/
	charge_name				varchar(64)							null,			/*收费员名称*/
	
	pay_money					int									not	null,			/*缴费金额单位为分*/
	pay_flag					tinyint									null,			/*缴费冲正标志 2缴费,  5冲正*/

	success_flag			tinyint            			 null default 0,    /*支付成功标志， 1 未支付  2 已支付*/

	--更新信息
--	source_type				tinyint              		 null,    /*充值来源 1 支付宝 2 微信 3 银联 4 内部缴费 9 转账*/
--	trade_no					varchar(32)              null,    /*订单号（微信订单号transaction_id、支付宝交易号trade_no）*/
--  pay_ymd           int       	         		 null,    /*完成日期 微信gmt_create或支付宝提供 time_end*/
--  pay_hms           int       	         		 null,    /*完成时间 微信或支付宝提供*/
--  buyer_info				varchar(64)              null,    /*客户信息 微信openid 支付宝 buyer_email*/

  --发票相关 暂时不用
--	ptinvoice_f					tinyint            	not null default 1,    /*打印发票标志 1 未打印 2 已打印*/
--	pt_ymd						int											 null, 		/*打印日期*/
--	pt_hms						int											 null, 		/*打印时间*/
--	pt_man						varchar(32)           	 null,    /*打印操作员*/
--	pvoice_no					varchar(16)							 null, 		/*发票编号*/

--	reconciliation		tinyint              not null ,    /*对账标志 1 未对账 2 对账单中含有本记录 3 本记录数据库中有，excel中无 */
																											 /* 4 本记录数据库中无，execl中有，根据excel新生成记录*/
--	reconciliymd			int											 null,		/*对账日期*/
	
--	settlement				tinyint							 not null,		/*结算标志 1 未结算 2 已结算*/
--	settleymd					int											 null,		/*结算日期*/
  
--  charge_source			varchar(16)							null,			/*缴费信息来源*/
   
	yffop_ymd					int											null,			/*预付费缴费日期*/	
	yffop_hms					int											null,			/*预付费缴费时间*/

	nowstate					int											null,			/*当前状态*/	-- 
	errtimes					int											null,			/*失败次数*/
	payinfo						varchar(64)							null,			/*缴费信息*/	--失败信息

	reserve1					int											null,			/*扩展字段*/
	reserve2					int											null,			/*扩展字段*/
)
grant select on pubpay_order2008 to public
create clustered index pubpay_order2008index1	on pubpay_order2008(wasteno, meter_type)	
create index pubpay_order2008index2 					on pubpay_order2008(rtu_id, mp_id, create_ymd, create_hms)  
create index pubpay_order2008index3 					on pubpay_order2008(userid)   

end
go
-- 标记结束

--	'-------Create table 物业费记录表(PropYff2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'PropYff2008')
begin
create table PropYff2008
(
	glo_resid			int									not	null,			/*居民用户编号*/
	glo_resno			varchar(32)							null,			/*居民用户户号*/
	res_desc			char(64)								null,			/*客户名称*/

	op_man				char(64)						not null,			/*操作员*/	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/

	pay_type			tinyint									null,			/*缴费方式*/

	wasteno				varchar(32)					not null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/	

	pay_money			decimal(12,2)				not null,			/*缴费金额*/	
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)				not null,			/*总金额*/

	buymons				int									not null, 		/**/
	property_ym		int									not null,			/*物业费缴费终止年月*/
	buy_times			int									not null,			/*购电次数*/		

	cur_feeproj		smallint								null,			/*当前费率号*/

	visible_flag	tinyint									null,			/*是否显示*/	
)
grant select on PropYff2008 to public
create clustered index PropYff2008index1
	on PropYff2008(glo_resid,op_date,op_time)	
end
go
-- 标记结束


--	'-------Create table 物业费记录表(sysfmPpyfrcd2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'sysfmPpyfrcd2008')
begin
create table sysfmPpyfrcd2008
(
	glo_resid			int									not	null,			/*居民用户编号*/
	glo_resno			varchar(32)							null,			/*居民用户户号*/
	res_desc			char(64)								null,			/*客户名称*/

	op_man				char(64)						not null,			/*操作员*/	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/

	pay_type			tinyint									null,			/*缴费方式*/

	wasteno				varchar(32)					not null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/	

	pay_money			decimal(12,2)				not null,			/*缴费金额*/	
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)				not null,			/*总金额*/

	buymons				int									not null, 		/**/
	property_ym		int									not null,			/*物业费缴费终止年月*/
	buy_times			int									not null,			/*购电次数*/		

	cur_feeproj		smallint								null,			/*当前费率号*/

	visible_flag	tinyint									null,			/*是否显示*/	
)
grant select on sysfmPpyfrcd2008 to public
create clustered index sysfmPpyfrcd2008index1
	on sysfmPpyfrcd2008(glo_resid,op_date,op_time)	

end
go
-- 标记结束


--	'-------Create table app消息推送记录表(appsendmessage2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'appsendmessage200808')
begin
create table appsendmessage200808
(
	glo_resid			int									not null,			/*居民用户编号*/
	meter_type		tinyint 						not null,			/*表计类型 见数据字典2090*/

	glo_resno			varchar(32)							null,			/*居民用户户号*/

	messtype			tinyint							not null,			/*消息类型关联  0 单个消息， 1 广播消息*/
	create_ymd		int       	        not null,     /*创建日期*/
	create_hms		int       	        not null,     /*创建时间*/
	content				varchar(512)        not null,     /*消息内容*/

	nowstate			tinyint							not null			/*当前状态 0初始态, 1 已推送*/
)
grant select on appsendmessage200808 to public
create clustered index appsendmessage200808index1
	on appsendmessage200808(glo_resid,create_ymd,create_hms)	

end
go
-- 标记结束



--	'-------Create table 全局专变预付费记录表(Gyff2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'Gyff2008')
begin
create table Gyff2008
(
	glospecid			int									not null,					/*全局ID*/

	op_man				varchar(64)					not null,			/*操作员*/	
	op_type				tinyint							not null,			/*本次操作类型 0 初始态, 1 开户, 2 缴费, 3 补卡, 4 补写卡, 5 冲正, 6 换表, 7 换费率 10 销户  */	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/
	
	pay_type			tinyint									null,			/*缴费方式*/
	wasteno				varchar(32)							null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/	
	
	pay_money			decimal(12,2)						null,			/*缴费金额*/	
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)						null,			/*总金额*/

	shutdown_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 表底计费时为:断电止码 */
	jsbd_ymd			int											null,			/*结算时间*/

  calc_bdymd		int										 	null,			/*算费表底时间-YYYYMMDD*/              		                  

	real_powrate	decimal(12,2)						null,			/*实际功率因数*/
	ele_money		  decimal(12,2)						null,			/*电度电费*/
	jbf_money		  decimal(12,2)						null,			/*基本费电费*/
	powrate_money	decimal(12,2)						null,			/*力调电费*/
	other_money		decimal(12,2)						null,			/*其它电费*/
	
	now_remain		decimal(12,2)		  			null,			/*当前剩余*/
	total_gdz			decimal(12,2)		  			null,			/*累计购电值*/
	
	total_yzbdl		decimal(12,2)						null,			/*有功追补累计用电量*/
	total_wzbdl		decimal(12,2)						null,			/*无功追补累计用电量*/
	total_ydl			decimal(12,2)						null,			/*有功累计用电量*/
	total_wdl			decimal(12,2)						null,			/*无功累计用电量*/

	zbele_money	  decimal(12,2)						null,			/*追补电度电费*/
	zbjbf_money	  decimal(12,2)						null,			/*追补基本费电费*/
	zbother_money	decimal(12,2)						null,			/*追补其它电费*/
	buy_times			int											null,			/*购电次数*/
	op_result			tinyint									null,			/*操作结果 0:未成功 1:写卡成功 2:远程设置成功 3:主站操作成功 4:暂时不处理 5:正在操作 6:失败*/	

	visible_flag	tinyint									null,			/*是否显示*/	
	
	sg186_ysdw		varchar(20)							null,			/*186应收单位标识*/	
	up186_flag		tinyint									null,			/*186上传标志*/
	checkpay_flag	tinyint									null			/*186上传成功标志*/
)
grant select on Gyff2008 to public
create clustered index Gyff2008index1
	on Gyff2008(glospecid,op_date,op_time)	
end
go
-- 标记结束


--	'-------Create table 全局专变预付费记录备份表(sysgyfrcd2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'sysgyfrcd2008')
begin
create table sysgyfrcd2008
(
	glospecid			int									not null,					/*全局ID*/

	op_man				varchar(64)					not null,			/*操作员*/	
	op_type				tinyint							not null,			/*本次操作类型 0 初始态, 1 开户, 2 缴费, 3 补卡, 4 补写卡, 5 冲正, 6 换表, 7 换费率 10 销户  */	
	op_date				int									not null,			/*本次操作日期*/	
	op_time				int									not null,			/*本次操作时间*/
	
	pay_type			tinyint									null,			/*缴费方式*/
	wasteno				varchar(32)							null,			/*流水号*/	
	rewasteno			varchar(32)							null,			/*被冲正流水号*/	
	
	pay_money			decimal(12,2)						null,			/*缴费金额*/	
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)						null,			/*总金额*/
	
	shutdown_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 表底计费时为:断电止码 */
	jsbd_ymd			int											null,			/*结算时间*/

  calc_bdymd		int										 	null,			/*算费表底时间-YYYYMMDD*/              		                  

	real_powrate	decimal(12,2)						null,			/*实际功率因数*/
	ele_money		  decimal(12,2)						null,			/*电度电费*/
	jbf_money		  decimal(12,2)						null,			/*基本费电费*/
	powrate_money	decimal(12,2)						null,			/*力调电费*/
	other_money		decimal(12,2)						null,			/*其它电费*/
	
	now_remain		decimal(12,2)		  			null,			/*当前剩余*/
	total_gdz			decimal(12,2)		  			null,			/*累计购电值*/
	
	total_yzbdl		decimal(12,2)						null,			/*有功追补累计用电量*/
	total_wzbdl		decimal(12,2)						null,			/*无功追补累计用电量*/
	total_ydl			decimal(12,2)						null,			/*有功累计用电量*/
	total_wdl			decimal(12,2)						null,			/*无功累计用电量*/

	zbele_money	  decimal(12,2)						null,			/*追补电度电费*/
	zbjbf_money	  decimal(12,2)						null,			/*追补基本费电费*/
	zbother_money	decimal(12,2)						null,			/*追补其它电费*/

	buy_times			int											null,			/*购电次数*/

	op_result			tinyint									null,			/*操作结果 0:未成功 1:写卡成功 2:远程设置成功 3:主站操作成功 4:暂时不处理 5:正在操作 6:失败*/	

	visible_flag	tinyint									null,			/*是否显示*/	
	
	sg186_ysdw		varchar(20)							null,			/*186应收单位标识*/	
	up186_flag		tinyint									null,			/*186上传标志*/
	checkpay_flag	tinyint									null			/*186上传成功标志*/
)
grant select on sysgyfrcd2008 to public
create clustered index sysgyfrcd2008index1
	on sysgyfrcd2008(glospecid,op_date,op_time)
end
go
-- 标记结束

--	'-------Create table 全局专变负荷控制记录表(GFk2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'GFk2008')
begin
create table GFk2008
(
	glospecid			int									not null,					/*全局ID*/
	ctrl_id				smallint	    			not null,				/*轮次序号0-7*/
	rtu_id				int						  		not null,
	poll_id				smallint  					not null,

	op_date				int									not null,
	op_time				int									not null,
	
	op_man				char(64)								null,			/*操作员*/	
	op_type				tinyint									null,			/*控制类型*/
	
	shuttime			int											null,			/*断电时间长度*/	

	op_result			tinyint				default 0 null,			/*操作结果*/
	
	reserve1		  int											null,
	reserve2		  int											null		
)
grant select on GFk2008 to public
create index GFk2008index1
	on GFk2008(rtu_id, poll_id, op_date, op_time)
create index GFk2008index2
	on GFk2008(glospecid, ctrl_id, op_date, op_time)	
end
go
-- 标记结束


--	'-------Create table (sysgcontrol2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'sysgcontrol2008')
begin
create table sysgcontrol2008
(
	glospecid			int									not null,					/*全局ID*/
	ctrl_id				smallint	    			not null,				/*轮次序号0-7*/
	rtu_id				int						  		not null,
	poll_id				smallint  					not null,
	
	op_date				int									not null,
	op_time				int									not null,

	op_man				char(64)								null,			/*操作员*/	
	op_type				tinyint									null,			/*控制类型*/

	shuttime			int											null,			/*断电时间长度*/	

	op_result			tinyint				default 0 null,			/*操作结果*/

	reserve1		  int											null,
	reserve2		  int											null		
)
grant select on sysgcontrol2008 to public
create index sysgcontrol2008index1
	on sysgcontrol2008(rtu_id, poll_id, op_date, op_time)
create index sysgcontrol2008index2
	on sysgcontrol2008(glospecid, ctrl_id, op_date, op_time)	
end	
go
-- 标记结束

--   '-------Create table 全局专变发行电费余额数据表(GspecFxMoney2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'GspecFxMoney2008')
begin
create table GspecFxMoney2008
(
	glospecid			int									not null,					/*全局ID*/
	fxdf_ym						int								not null, 				/*发行电费年月*/
	bus_no						varchar(64)			  not null,					/*客户编号 居民户号*/
  
  fxdf_mdhmi				int										null,					/*发行电费算费时间-MMDDHHMI*/
  fxdf_lastym				int										null,					/*上次电费年月*/

	iall_money				decimal(12,2)		  		null,					/*本月缴费总金额*/

	last_remain				decimal(12,2)		  		null,					/*上次剩余*/
	now_remain				decimal(12,2)		  		null,					/*当前剩余*/

	df_money					decimal(12,2)		  not null,					/*电费金额*/

	real_powrate			decimal(12,2)		  		null,					/*实际功率因数*/
	ele_money					decimal(12,2)		  		null,					/*电度电费*/
	jbf_money					decimal(12,2)		  		null,					/*基本费电费*/
	powrate_money			decimal(12,2)		  		null,					/*力调电费*/
	other_money				decimal(12,2)					null,					/*其它电费*/

	total_yzbdl				decimal(12,2)		  		null,					/*有功追补累计用电量*/
	total_wzbdl				decimal(12,2)		  		null,					/*无功追补累计用电量*/
	total_ydl					decimal(12,2)		  		null,					/*有功累计用电量*/
	total_wdl					decimal(12,2)		  		null,					/*无功累计用电量*/

 	zbele_money	  		decimal(12,2)					null,					/*追补电度电费*/
	zbjbf_money	  		decimal(12,2)					null,					/*追补基本费电费*/
	zbother_money			decimal(12,2)					null,					/*追补其它电费*/

	shutdown_val			decimal(12,2)		  		null,					/*断电值 金额计费时为:断电金额 */	

 	jsbd_ymd					int										null,					/*上次结算时间*/
  calc_bdymd				int										null,					/*算费表底时间-YYYYMMDD*/              		                  
	update_count			int										null,					/*更新次数*/
  all_monthf				tinyint								null,					/*整月标志*/
	bc_flag						tinyint					  		null, 				/*结算补差标记*/
	bc_date						int							  		null,					/*结算补差日期*/
	bc_time						int							  		null,					/*结算补差时间*/
	op_man						varchar(64)			  		null,					/*操作员*/	
)
grant select on  GspecFxMoney2008 to public
create unique clustered index GFxMoney2008index1
	on GspecFxMoney2008(glospecid,fxdf_ym)
end
go
-- 标记结束

--   '-------Create table 全局专变发行电费余额数据表(GspecItemFx2008) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'GspecItemFx2008')
begin
create table GspecItemFx2008
(
	glospecid			int									not null,			/*全局ID*/
	item_id				smallint	    			not null,			/*总加组编号0-7*/
	fxdf_ym				int									not null, 		/*发行电费年月*/

	cus_state			tinyint									null,			/*用户状态 0 初始态, 1 正常态, 10 销户态*/	

	jsbd_zyz			decimal(12,2)						null,			/*测量点1-结算总表底*/
	jsbd_zyj			decimal(12,2)						null,			/*测量点1-结算尖表底*/
	jsbd_zyf			decimal(12,2)						null,			/*测量点1-结算峰表底*/
	jsbd_zyp			decimal(12,2)						null,			/*测量点1-结算平表底*/
	jsbd_zyg			decimal(12,2)						null,			/*测量点1-结算谷表底*/

	jsbd_zwz			decimal(12,2)						null,			/*测量点1-结算无功总表底*/

	jsbd_ymd			int											null,			/*结算时间*/

	calc_mdhmi		int											null,			/*算费时间-MMDDHHMI*/
	calc_bdymd		int											null,			/*算费表底时间-YYYYMMDD*/	

	calc_zyz			decimal(12,2)						null,			/*测量点1-算费时总表底*/
	calc_zyj			decimal(12,2)						null,			/*测量点1-算费时尖表底*/
	calc_zyf			decimal(12,2)						null,			/*测量点1-算费时峰表底*/
	calc_zyp			decimal(12,2)						null,			/*测量点1-算费时平表底*/
	calc_zyg			decimal(12,2)						null,			/*测量点1-算费时谷表底*/
	calc_zwz			decimal(12,2)						null,			/*测量点1-算费无功总表底*/

	fz_zyz				decimal(12,2)						null,			/*测量点1-分闸时总表底*/

	total_yzbdl		decimal(12,2)						null,			/*有功追补累计用电量*/
	total_wzbdl		decimal(12,2)						null,			/*无功追补累计用电量*/
	total_ydl			decimal(12,2)						null,			/*有功累计用电量*/
	total_wdl			decimal(12,2)						null,			/*无功累计用电量*/
		
)
grant select on  GspecItemFx2008 to public
create unique clustered index GspecItemFx2008index1
	on GspecItemFx2008(glospecid,item_id, fxdf_ym)
end
go
-- 标记结束



--	'-------Create table 测量点预付费状态表(mppay_state200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'mppay_state200808')
begin
create table mppay_state200808
(
	rtu_id				int       					not null,			/*终端编号*/	
	mp_id					smallint 						not null,			/*测量点编号*/
	ymd						int									not null,			

	cus_state			tinyint									null,			/*用户状态 0 初始态, 1 正常态, 10 销户态*/	

	op_type				tinyint									null,			/*本次操作类型 0 初始态, 1 开户, 2 缴费, 3 补卡, 4 补写卡, 5 冲正, 6 换表, 7 换费率 10 销户 */	
	op_date				int											null,			/*本次操作日期*/	
	op_time				int											null,			/*本次操作时间*/	
	
	pay_money			decimal(12,2)						null,			/*缴费金额*/
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/
	all_money			decimal(12,2)						null,			/*总金额*/
	
	--add 201406
	buy_dl				decimal(12,2)						null,			/*购电量*/
	pay_bmc				decimal(12,2)						null,			/*表码差*//*又名写卡电量*/
	--end
	
	shutdown_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 */	
	shutdown_val2	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 本地模拟电表 */	
	
	jsbd_zyz			decimal(12,2)						null,			/*结算总表底*/
	jsbd_zyj			decimal(12,2)						null,			/*结算尖表底*/
	jsbd_zyf			decimal(12,2)						null,			/*结算峰表底*/
	jsbd_zyp			decimal(12,2)						null,			/*结算平表底*/
	jsbd_zyg			decimal(12,2)						null,			/*结算谷表底*/

	jsbd1_zyz			decimal(12,2)						null,			/*动力关联1-结算总表底*/
	jsbd1_zyj			decimal(12,2)						null,			/*动力关联1-结算尖表底*/
	jsbd1_zyf			decimal(12,2)						null,			/*动力关联1-结算峰表底*/
	jsbd1_zyp			decimal(12,2)						null,			/*动力关联1-结算平表底*/
	jsbd1_zyg			decimal(12,2)						null,			/*动力关联1-结算谷表底*/

	jsbd2_zyz			decimal(12,2)						null,			/*动力关联2-结算总表底*/
	jsbd2_zyj			decimal(12,2)						null,			/*动力关联2-结算尖表底*/
	jsbd2_zyf			decimal(12,2)						null,			/*动力关联2-结算峰表底*/
	jsbd2_zyp			decimal(12,2)						null,			/*动力关联2-结算平表底*/
	jsbd2_zyg			decimal(12,2)						null,			/*动力关联2-结算谷表底*/

	jsbd_ymd			int											null,			/*结算时间*/
	buy_times			int											null,			/*购电次数*/

	calc_mdhmi		int											null,			/*算费时间-MMDDHHMI*/
	calc_bdymd		int											null,			/*算费表底时间-YYYYMMDD*/	
	
	calc_zyz			decimal(12,2)						null,			/*算费时总表底*/
	calc_zyj			decimal(12,2)						null,			/*算费时尖表底*/
	calc_zyf			decimal(12,2)						null,			/*算费时峰表底*/
	calc_zyp			decimal(12,2)						null,			/*算费时平表底*/
	calc_zyg			decimal(12,2)						null,			/*算费时谷表底*/
	
	calc1_zyz			decimal(12,2)						null,			/*动力关联1-算费时总表底*/
	calc1_zyj			decimal(12,2)						null,			/*动力关联1-算费时尖表底*/
	calc1_zyf			decimal(12,2)						null,			/*动力关联1-算费时峰表底*/
	calc1_zyp			decimal(12,2)						null,			/*动力关联1-算费时平表底*/
	calc1_zyg			decimal(12,2)						null,			/*动力关联1-算费时谷表底*/
	
	calc2_zyz			decimal(12,2)						null,			/*动力关联2-算费时总表底*/
	calc2_zyj			decimal(12,2)						null,			/*动力关联2-算费时尖表底*/
	calc2_zyf			decimal(12,2)						null,			/*动力关联2-算费时峰表底*/
	calc2_zyp			decimal(12,2)						null,			/*动力关联2-算费时平表底*/
	calc2_zyg			decimal(12,2)						null,			/*动力关联2-算费时谷表底*/		
	
	now_remain		decimal(12,2)						null,			/*当前剩余*/
	now_remain2		decimal(12,2)						null,			/*当前剩余 本地模拟电表*/

	bj_bd					decimal(12,2)						null,			/*报警门限*/
	tz_bd					decimal(12,2)						null,			/*跳闸门限*/

	cs_al1_state	tinyint									null,			/*报警1状态  0:正常状态 1:报警状态*/
	cs_al2_state	tinyint									null,			/*报警2状态  0:正常状态 1:报警状态*/
	cs_fhz_state	tinyint									null,			/*分合闸状态 0:分闸状态 1:合闸状态*/

	al1_mdhmi			int											null,			/*报警1状态改变时间-MMDDHHMI*/
	al2_mdhmi			int											null,			/*报警2状态改变时间-MMDDHHMI*/	
	fhz_mdhmi			int											null,			/*分合闸状态改变时间-MMDDHHMI*/

	fz_zyz				decimal(12,2)						null,			/*分闸时总表底*/
	fz1_zyz				decimal(12,2)						null,			/*动力关联1-分闸时总表底*/
	fz2_zyz				decimal(12,2)						null,			/*动力关联2-分闸时总表底*/

	yc_flag1			int											null,			/*异常标志1, 参数错误 */
	yc_flag2			int											null,			/*异常标志2(按位标志), 数据错误 0位:分闸后表字继续走 1位:表底飞走 2位:表底倒转 3位:长时间无数据 4位:长时间不缴费*/
	yc_flag3			int											null,			/*异常标志3, 备用 */

	hb_date				int											null,			/*上次换表日期*/
	hb_time				int											null,			/*上次换表时间*/	
	
	kh_date				int											null,			/*开户日期-YYYYMMDD*/
	xh_date				int											null,			/*销户日期-YYYYMMDD*/

	total_gdz			decimal(12,2)						null,			/*累计购电值*/

	jt_total_zbdl		decimal(12,2)					null,			/*阶梯追补累计用电量*/
	jt_total_dl			decimal(12,2)					null,			/*阶梯累计用电量*/
	jt_reset_ymd		int										null,			/*阶梯上次自动切换日期*/
	jt_reset_mdhmi	int										null,			/*阶梯切换执行时间*/

	fxdf_iall_money		decimal(12,2)				null, 		/*发行电费当月缴费总金额*/
	fxdf_iall_money2	decimal(12,2)				null, 		/*发行电费当月缴费总金额2*/
	fxdf_remain				decimal(12,2)				null,			/*发行电费后剩余金额  		fxdf_after_remain*/
	fxdf_remain2			decimal(12,2)				null,			/*发行电费后剩余金额  		fxdf_after_remain 本地模拟电表*/

	fxdf_ym					int										null,			/*发行电费数据日期YYYYMM*/
	fxdf_data_ymd		int										null,			/*发行电费数据日期-YYYYMMDD*/
	fxdf_calc_mdhmi	int										null,			/*发行电费算费时间-MMDDHHMI*/

	js_bc_ymd			int											null, 	  	/*结算补差日期YYYYMMDD*/
	
	reserve1			int								   		null,	
	reserve2			int							   			null,	
	reserve3			decimal(12,2)			   		null,	
	reserve4			decimal(12,2)			  	 	null,	
)
grant select on mppay_state200808 to public
create clustered index mppay_state200808index1
	on mppay_state200808(rtu_id,mp_id,ymd)	
end
go
-- 标记结束


--	'-------Create table 测量点预付费报警状态表(mppay_almstate200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'mppay_almstate200808')
begin
create table mppay_almstate200808
(
	rtu_id				int       					not null,			/*终端编号*/	
	mp_id					smallint 						not null,			/*测量点编号*/
	ymd						int									not null,			

	qr_al1_1_state 		tinyint							null,			/*报警1-1确认状态(短信方式) 低6位(重试次数0~63) 高2位(0:初始态 1:成功 2:失败)*/
	qr_al1_2_state 		tinyint							null,			/*报警1-2确认状态(声音方式)*/
	qr_al1_3_state 		tinyint							null,			/*报警1-3确认状态(备用方式)*/	

	qr_al2_1_state 		tinyint							null,			/*报警2-1确认状态(短信方式) 低6位(重试次数0~63) 高2位(0:初始态 1:成功 2:失败)*/
	qr_al2_2_state 		tinyint							null,			/*报警2-2确认状态(声音方式)*/
	qr_al2_3_state 		tinyint							null,			/*报警2-3确认状态(备用方式)*/	

	qr_fhz_state			tinyint							null,			/*分合闸确认状态*/
	qr_fhz_rf1_state	tinyint							null,			/*分合闸确认状态(动力1)*/
	qr_fhz_rf2_state	tinyint							null,			/*分合闸确认状态(动力2)*/
	
	qr_fz_times				tinyint							null,			/*分闸次数*/
	qr_fz_rf1_times		tinyint							null,			/*分闸次数(动力1)*/
	qr_fz_rf2_times		tinyint							null,			/*分闸次数(动力2)*/
	
	qr_al1_1_mdhmi		int									null,			/*报警1-1确认状态(短信方式) 发送时间*/
	qr_al1_2_mdhmi		int									null,			/*报警1-2确认状态(声音方式) 发送时间*/
	qr_al1_3_mdhmi		int									null,			/*报警1-3确认状态(备用方式) 发送时间*/

	qr_al2_1_mdhmi		int									null,			/*报警2-1确认状态(短信方式) 发送时间*/
	qr_al2_2_mdhmi		int									null,			/*报警2-2确认状态(声音方式) 发送时间*/
	qr_al2_3_mdhmi		int									null,			/*报警2-3确认状态(备用方式) 发送时间*/

	qr_fhz_mdhmi			int									null,			/*分合闸确认状态 发送时间*/
	qr_fhz_rf1_mdhmi	int									null,			/*分合闸确认状态 发送时间(动力1)*/
	qr_fhz_rf2_mdhmi	int									null,			/*分合闸确认状态 发送时间(动力2)*/
			
	cg_fhz_mdhmi			int									null,			/*成功分合闸时间-MMDDHHMI*/
	cg_fhz_rf1_mdhmi	int									null,			/*成功分合闸时间-MMDDHHMI(动力1)*/
	cg_fhz_rf2_mdhmi	int									null,			/*成功分合闸时间-MMDDHHMI(动力2)*/
		
	qr_al1_1_uuid			int									null,			/*报警1-1确认状态(短信方式) UUID*/
	qr_al2_1_uuid			int									null,			/*报警2-1确认状态(短信方式) UUID*/

	out_info					varchar(64)					null,			/*信息输出*/
)
grant select on mppay_almstate200808 to public
create clustered index mppay_almstate200808index1
	on mppay_almstate200808(rtu_id,mp_id,ymd)	
end
go
-- 标记结束

--	'-------Create table 总加组预付费状态表(zjgpay_state200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'zjgpay_state200808')
begin
create table zjgpay_state200808
(
	rtu_id				int       					not null,			/*终端编号*/	
	zjg_id				smallint						not null,			/*总加组号*/
	ymd						int									not null,			
	
	cus_state			tinyint									null,			/*用户状态 0 初始态, 1 正常态, 10 销户态*/	

	op_type				tinyint									null,			/*本次操作类型 0 初始态, 1 开户, 2 缴费, 3 补卡, 4 补写卡, 5 冲正, 6 换表, 7 换费率 10 销户 */	
	op_date				int											null,			/*本次操作日期*/
	op_time				int											null,			/*本次操作时间*/
	
	pay_money			decimal(12,2)						null,			/*缴费金额*/
	othjs_money		decimal(12,2)						null,			/*结算金额(其它系统)*/
	zb_money			decimal(12,2)						null,			/*追补金额*/	
	all_money			decimal(12,2)						null,			/*总金额*/
	
	buy_dl				decimal(12,2)						null,			/*购电量*/
	pay_bmc				decimal(12,2)						null,			/*表码差*/

	alarm_val1		decimal(12,2)						null,			/*报警值1*/		
	alarm_val2		decimal(12,2)						null,			/*报警值2*/		
		
	shutdown_val	decimal(12,2)						null,			/*断电值 金额计费时为:断电金额 表底计费时为:断电止码 */
--shutdown_val2	decimal(12,2)						null,			/*断电值2 金额计费时为:断电金额 表底计费时为:断电止码 */
	
	jsbd_zyz			decimal(12,2)						null,			/*测量点1-结算总表底*/
	jsbd_zyj			decimal(12,2)						null,			/*测量点1-结算尖表底*/
	jsbd_zyf			decimal(12,2)						null,			/*测量点1-结算峰表底*/
	jsbd_zyp			decimal(12,2)						null,			/*测量点1-结算平表底*/
	jsbd_zyg			decimal(12,2)						null,			/*测量点1-结算谷表底*/

	jsbd1_zyz			decimal(12,2)						null,			/*测量点2-结算总表底*/
	jsbd1_zyj			decimal(12,2)						null,			/*测量点2-结算尖表底*/
	jsbd1_zyf			decimal(12,2)						null,			/*测量点2-结算峰表底*/
	jsbd1_zyp			decimal(12,2)						null,			/*测量点2-结算平表底*/
	jsbd1_zyg			decimal(12,2)						null,			/*测量点2-结算谷表底*/

	jsbd2_zyz			decimal(12,2)						null,			/*测量点3-结算总表底*/
	jsbd2_zyj			decimal(12,2)						null,			/*测量点3-结算尖表底*/
	jsbd2_zyf			decimal(12,2)						null,			/*测量点3-结算峰表底*/
	jsbd2_zyp			decimal(12,2)						null,			/*测量点3-结算平表底*/
	jsbd2_zyg			decimal(12,2)						null,			/*测量点3-结算谷表底*/

	--20120723
	jsbd_zwz			decimal(12,2)						null,			/*测量点1-结算无功总表底*/
	jsbd1_zwz			decimal(12,2)						null,			/*测量点2-结算无功总表底*/
	jsbd2_zwz			decimal(12,2)						null,			/*测量点3-结算无功总表底*/
	--end

	jsbd_ymd			int											null,			/*结算时间*/

	lsttw_money		decimal(12,6)						null,			/*上次调尾金额*/
	nowtw_money		decimal(12,6)						null,			/*本次调尾金额*/

	buy_times			int											null,			/*购电次数*/

	calc_mdhmi		int											null,			/*算费时间-MMDDHHMI*/
	calc_bdymd		int											null,			/*算费表底时间-YYYYMMDD*/	
	
	calc_zyz			decimal(12,2)						null,			/*测量点1-算费时总表底*/
	calc_zyj			decimal(12,2)						null,			/*测量点1-算费时尖表底*/
	calc_zyf			decimal(12,2)						null,			/*测量点1-算费时峰表底*/
	calc_zyp			decimal(12,2)						null,			/*测量点1-算费时平表底*/
	calc_zyg			decimal(12,2)						null,			/*测量点1-算费时谷表底*/

	calc1_zyz			decimal(12,2)						null,			/*测量点2-算费时总表底*/
	calc1_zyj			decimal(12,2)						null,			/*测量点2-算费时尖表底*/
	calc1_zyf			decimal(12,2)						null,			/*测量点2-算费时峰表底*/
	calc1_zyp			decimal(12,2)						null,			/*测量点2-算费时平表底*/
	calc1_zyg			decimal(12,2)						null,			/*测量点2-算费时谷表底*/

	calc2_zyz			decimal(12,2)						null,			/*测量点3-算费时总表底*/
	calc2_zyj			decimal(12,2)						null,			/*测量点3-算费时尖表底*/
	calc2_zyf			decimal(12,2)						null,			/*测量点3-算费时峰表底*/
	calc2_zyp			decimal(12,2)						null,			/*测量点3-算费时平表底*/
	calc2_zyg			decimal(12,2)						null,			/*测量点3-算费时谷表底*/		

	--20120723
	calc_zwz			decimal(12,2)						null,			/*测量点1-算费无功总表底*/
	calc1_zwz			decimal(12,2)						null,			/*测量点2-算费无功总表底*/
	calc2_zwz			decimal(12,2)						null,			/*测量点3-算费无功总表底*/

	real_powrate	decimal(12,2)						null,			/*实际功率因数*/
	ele_money		  decimal(12,2)						null,			/*电度电费*/
	jbf_money		  decimal(12,2)						null,			/*基本费电费*/
	powrate_money	decimal(12,2)						null,			/*力调电费*/
	other_money		decimal(12,2)						null,			/*其它电费*/
	--end

	now_remain		decimal(12,2)						null,			/*当前剩余*/

	bj_bd					decimal(12,2)						null,			/*报警门限*/
	tz_bd					decimal(12,2)						null,			/*跳闸门限*/

	cs_al1_state	tinyint									null,			/*报警1状态  0:正常状态 1:报警状态*/
	cs_al2_state	tinyint									null,			/*报警2状态  0:正常状态 1:报警状态*/
	cs_fhz_state	tinyint									null,			/*分合闸状态 0:分闸状态 1:合闸状态*/

	al1_mdhmi			int											null,			/*报警1状态改变时间-MMDDHHMI*/
	al2_mdhmi			int											null,			/*报警2状态改变时间-MMDDHHMI*/	
	fhz_mdhmi			int											null,			/*分合闸状态改变时间-MMDDHHMI*/

	fz_zyz				decimal(12,2)						null,			/*测量点1-分闸时总表底*/
	fz1_zyz				decimal(12,2)						null,			/*测量点2-分闸时总表底*/
	fz2_zyz				decimal(12,2)						null,			/*测量点3-分闸时总表底*/		

	yc_flag1			int											null,			/*异常标志1, 参数错误 */
	yc_flag2			int											null,			/*异常标志2(按位标志), 数据错误 0位:分闸后表字继续走 1位:表底飞走 2位:表底倒转 3位:长时间无数据 4位:长时间不缴费*/
	yc_flag3			int											null,			/*异常标志3, 备用 */

	hb_date				int											null,			/*上次换表日期*/
	hb_time				int											null,			/*上次换表时间*/	

	kh_date				int											null,			/*开户日期-YYYYMMDD*/
	xh_date				int											null,			/*销户日期-YYYYMMDD*/

	total_gdz			decimal(12,2)						null,			/*累计购电值*/

	--20120723
	total_yzbdl		decimal(12,2)						null,			/*有功追补累计用电量*/
	total_wzbdl		decimal(12,2)						null,			/*无功追补累计用电量*/
	total_ydl			decimal(12,2)						null,			/*有功累计用电量*/
	total_wdl			decimal(12,2)						null,			/*无功累计用电量*/

	zbele_money	  decimal(12,2)						null,			/*追补电度电费*/
	zbjbf_money	  decimal(12,2)						null,			/*追补基本费电费*/
	zbother_money	decimal(12,2)						null,			/*追补其它电费*/
	--end

	fxdf_iall_money		decimal(12,2)				null, 		/*发行电费当月缴费总金额*/
	fxdf_remain				decimal(12,2)				null,			/*发行电费后剩余金额  		fxdf_after_remain*/

	fxdf_ym						int									null,			/*发行电费数据日期YYYYMM 哪月发行*/
	fxdf_data_ymd			int									null,			/*发行电费数据日期-YYYYMMDD 取表底时间*/
	fxdf_calc_mdhmi		int									null,			/*发行电费算费时间-MMDDHHMI 算费时间*/

	js_bc_ymd					int									null, 	  /*结算补差日期YYYYMMDD*/

	reserve1					int								  null,
)
grant select on zjgpay_state200808 to public
create clustered index zjgpay_state200808index1
	on zjgpay_state200808(rtu_id,zjg_id,ymd)	
end
go
-- 标记结束

--	'-------Create table 总加组预付费报警状态表(zjgpay_almstate200808) on yddata-------'
-- 标记开始
if not exists (select * from sysobjects where name = 'zjgpay_almstate200808')
begin
create table zjgpay_almstate200808
(
	rtu_id				int       					not null,			/*终端编号*/	
	zjg_id				smallint						not null,			/*总加组号*/
	ymd						int									not null,			
	
	qr_al1_1_state tinyint								null,			/*报警1-1确认状态(短信方式) 低6位(重试次数0~63) 高2位(0:初始态 1:成功 2:失败)*/
	qr_al1_2_state tinyint								null,			/*报警1-2确认状态(声音方式)*/
	qr_al1_3_state tinyint								null,			/*报警1-3确认状态(备用方式)*/	

	qr_al2_1_state tinyint								null,			/*报警2-1确认状态(短信方式) 低6位(重试次数0~63) 高2位(0:初始态 1:成功 2:失败)*/
	qr_al2_2_state tinyint								null,			/*报警2-2确认状态(声音方式)*/
	qr_al2_3_state tinyint								null,			/*报警2-3确认状态(备用方式)*/	

	qr_fhz_state		tinyint								null,			/*分合闸确认状态*/
	qr_fz_times		tinyint									null,			/*分闸次数*/
	
	qr_al1_1_mdhmi	int										null,			/*报警1-1确认状态(短信方式) 发送时间*/
	qr_al1_2_mdhmi	int										null,			/*报警1-2确认状态(声音方式) 发送时间*/
	qr_al1_3_mdhmi	int										null,			/*报警1-3确认状态(备用方式) 发送时间*/

	qr_al2_1_mdhmi	int										null,			/*报警2-1确认状态(短信方式) 发送时间*/
	qr_al2_2_mdhmi	int										null,			/*报警2-2确认状态(声音方式) 发送时间*/
	qr_al2_3_mdhmi	int										null,			/*报警2-3确认状态(备用方式) 发送时间*/

	qr_fhz_mdhmi		int										null,			/*分合闸确认状态 发送时间*/
	cg_fhz_mdhmi		int										null,			/*成功分合闸时间-MMDDHHMI*/
	
	qr_al1_1_uuid		int										null,			/*报警1-1确认状态(短信方式) UUID*/
	qr_al2_1_uuid		int										null,			/*报警2-1确认状态(短信方式) UUID*/

	out_info			varchar(64)							null,			/*信息输出*/
)
grant select on zjgpay_almstate200808 to public
create clustered index zjgpay_almstate200808index1
	on zjgpay_almstate200808(rtu_id,zjg_id,ymd)	
end
go
-- 标记结束

--文档最后【标记结束】后必须换行