/**   
* @Title: ComntCfgEVCP.java 
* @Package com.keicpms.communite 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhp
* @date 2018年12月13日 下午5:01:30 
* @version V1.0   
*/
package com.ke.comnt;

import com.ke.comnt.ComntMsg.IMSG_READWRITESTREAM;
import com.ke.comnt.ComntVector.ByteVector;

/** 
* @ClassName: ComntCfgEVCP 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhp
* @date 2018年12月13日 下午5:01:30 
*  
*/
public class ComntCfgEVCP{
	
	//-------------------三版充断电结构体------------------------------\\
	/** 
	* @ClassName: APP_CHARGE_RESULT 
	* @Description: 返回信息
	* @author zhp
	* @date 2018年12月14日 下午5:33:35 
	*  
	*/
	public static class APP_CHARGE_RESULT implements IMSG_READWRITESTREAM{
		public int memberId;
		public int pileId;
		public int gunId;
		public byte[] pileCode = new byte[32];
		public byte[] wasteno = new byte[32];
		public int retcode;
		
		public void clean(){
			memberId = 0;
			pileId = 0;
			gunId = 0;
			for(int i=0; i<32; i++) pileCode[i] = 0;
			for(int i=0; i<32; i++) wasteno[i] = 0;
			retcode = 0;
		}
		
		public void clone(APP_CHARGE_RESULT app_resp){
			memberId = app_resp.memberId;
			pileId = app_resp.pileId;
			gunId = app_resp.gunId;
			for(int i=0; i<64; i++) pileCode[i] = app_resp.pileCode[i];
			for(int i=0; i<64; i++) wasteno[i] = app_resp.wasteno[i];
			retcode = app_resp.retcode;
		}
		
		public APP_CHARGE_RESULT clone(){
			APP_CHARGE_RESULT app_rest = new APP_CHARGE_RESULT();
			app_rest.clone(this);
			return app_rest;
		}
		
		@Override
		public int fromDataStream(ByteVector byte_vect, int offset) {
			int ret_len = 0;
			
			memberId = ComntStream.readStream(byte_vect, offset + ret_len, memberId);
			ret_len += ComntStream.getDataSize(memberId);			
			
			pileId = ComntStream.readStream(byte_vect, offset + ret_len, pileId);
			ret_len += ComntStream.getDataSize(pileId);			
			
			gunId = ComntStream.readStream(byte_vect, offset + ret_len, gunId);
			ret_len += ComntStream.getDataSize(gunId);			
			
			ComntStream.readStream(byte_vect, offset + ret_len, pileCode, 0, pileCode.length);
			ret_len += ComntStream.getDataSize(pileCode[0]) * pileCode.length;						
			
			ComntStream.readStream(byte_vect, offset + ret_len, wasteno, 0, wasteno.length);
			ret_len += ComntStream.getDataSize(wasteno[0]) * wasteno.length;						
			
			retcode = ComntStream.readStream(byte_vect, offset + ret_len, retcode);
			ret_len += ComntStream.getDataSize(retcode);
			
			return ret_len;
		}
		
		@Override
		public int toDataStream(ByteVector byte_vect) {
			int ret_len   = 0;
			ret_len += ComntStream.writeStream(byte_vect, memberId);
			ret_len += ComntStream.writeStream(byte_vect, pileId);
			ret_len += ComntStream.writeStream(byte_vect, gunId);
			ret_len += ComntStream.writeStream(byte_vect, pileCode, 0, pileCode.length);
			ret_len += ComntStream.writeStream(byte_vect, wasteno, 0, wasteno.length);
			ret_len += ComntStream.writeStream(byte_vect, retcode);
			return ret_len;
		}

	}
	
	/**
	 * APP发送充电指令--结构体,最原始版本
	 * */
	public static class APP_CHARGE implements IMSG_READWRITESTREAM{
		public int appFlag; // 应用类型 1:APP 2:微信小程序 rcd130
		public int memberId; // 会员id
		public int pileId; // 充电桩id
		public int gunId; // 枪号 1:枪1 ....
		public int cmd; // 充电指令 1：充电 2：断电
		public int payType; // 1:金额 2:电量
		public int payMoney; // 充值金额-分
		
		public double payAmount; // 充电电量-kWh
		public byte[] pileCode = new byte[32]; // 桩体号
		public byte[] orderWasteno = new byte[32]; // 订单流水号
		
		public void clean() {
			appFlag = 0;
			memberId = 0;
			pileId = 0;
			gunId = 0;
			cmd = 0;
			payType = 0;
			payMoney = 0;
			payAmount = 0.0;
			for (int i = 0; i < 32; i++)
				pileCode[i] = 0;
			for (int i = 0; i < 32; i++)
				orderWasteno[i] = 0;
		}
		
		public void clone(APP_CHARGE app_charge) {
			appFlag = app_charge.appFlag;
			memberId = app_charge.memberId;
			pileId = app_charge.pileId;
			gunId = app_charge.gunId;
			cmd = app_charge.cmd;
			payType = app_charge.payType;
			payMoney = app_charge.payMoney;
			payAmount = app_charge.payAmount;

			for (int i = 0; i < 32; i++)
				pileCode[i] = app_charge.pileCode[i];
			for (int i = 0; i < 32; i++)
				orderWasteno[i] = app_charge.orderWasteno[i];
		}
		
		public APP_CHARGE clone() {
			APP_CHARGE app_charge = new APP_CHARGE();
			app_charge.clone(this);
			return app_charge;
		}
		
		public int fromDataStream(ByteVector byte_vect, int offset) {
			int ret_len = 0;

			appFlag = ComntStream.readStream(byte_vect, offset + ret_len, appFlag);
			ret_len += ComntStream.getDataSize(appFlag);

			memberId = ComntStream.readStream(byte_vect, offset + ret_len, memberId);
			ret_len += ComntStream.getDataSize(memberId);

			pileId = ComntStream.readStream(byte_vect, offset + ret_len, pileId);
			ret_len += ComntStream.getDataSize(pileId);

			gunId = ComntStream.readStream(byte_vect, offset + ret_len, gunId);
			ret_len += ComntStream.getDataSize(gunId);

			cmd = ComntStream.readStream(byte_vect, offset + ret_len, cmd);
			ret_len += ComntStream.getDataSize(cmd);

			payType = ComntStream.readStream(byte_vect, offset + ret_len, payType);
			ret_len += ComntStream.getDataSize(payType);

			payMoney = ComntStream.readStream(byte_vect, offset + ret_len, payMoney);
			ret_len += ComntStream.getDataSize(payMoney);

			payAmount = ComntStream.readStream(byte_vect, offset + ret_len, payAmount);
			ret_len += ComntStream.getDataSize(payAmount);

			ComntStream.readStream(byte_vect, offset + ret_len, pileCode, 0, pileCode.length);
			ret_len += ComntStream.getDataSize(pileCode[0]) * pileCode.length;

			ComntStream.readStream(byte_vect, offset + ret_len, orderWasteno, 0, orderWasteno.length);
			ret_len += ComntStream.getDataSize(orderWasteno[0]) * orderWasteno.length;

			return ret_len;
		}

		public int toDataStream(ByteVector byte_vect) {
			int ret_len = 0;
			ret_len += ComntStream.writeStream(byte_vect, appFlag);
			ret_len += ComntStream.writeStream(byte_vect, memberId);
			ret_len += ComntStream.writeStream(byte_vect, pileId);
			ret_len += ComntStream.writeStream(byte_vect, gunId);
			ret_len += ComntStream.writeStream(byte_vect, cmd);
			ret_len += ComntStream.writeStream(byte_vect, payType);
			ret_len += ComntStream.writeStream(byte_vect, payMoney);
			ret_len += ComntStream.writeStream(byte_vect, payAmount);

			ret_len += ComntStream.writeStream(byte_vect, pileCode, 0, pileCode.length);
			ret_len += ComntStream.writeStream(byte_vect, orderWasteno, 0, orderWasteno.length);
			return ret_len;
		}
	}
	
	//------------------前置向web服务推送信息------------------------\\
	/**
	 * 中间规约ID-充电信息
	 * */
	public final static int PROT_I_ID_TRADE_CHARGE_INFO = 5050903;	
	public static class TRADE_CHARGE_INFO implements IMSG_READWRITESTREAM{
		public int		m_gun;		//枪号   0：A枪，1：B枪
		public double	m_ua;		//A相电压
		public double	m_ub;		//B相电压
		public double	m_uc;		//C相电压
		public double	m_ia;		//A相电流
		public double	m_ib;		//B相电流
		public double	m_ic;		//C相电流
		public int		m_time;		//当前充电时间	单位秒
		public double	m_energy;	//充电电量
		public double	m_amount;	//充电金额
		
		public void clean(){
			m_gun = 0;
			m_ua = 0;
			m_ub = 0;
			m_uc = 0;
			m_ia = 0;
			m_ib = 0;
			m_ic = 0;
			m_time = 0;
			m_energy = 0;
			m_amount = 0;
		}
		
		public void clone(TRADE_CHARGE_INFO trade_charge_info){ 
			m_gun 	 =  trade_charge_info.m_gun;
			m_ua 	 =  trade_charge_info.m_ua;
			m_ub 	 =  trade_charge_info.m_ub;
			m_uc 	 =  trade_charge_info.m_uc;
			m_ia 	 =  trade_charge_info.m_ia;
			m_ib 	 =  trade_charge_info.m_ib;
			m_ic 	 =  trade_charge_info.m_ic;
			m_time 	 =  trade_charge_info.m_time;
			m_energy =  trade_charge_info.m_energy;
			m_amount =  trade_charge_info.m_amount;
		}
		
		public TRADE_CHARGE_INFO clone(){
			TRADE_CHARGE_INFO trade_charge_info = new TRADE_CHARGE_INFO();
			trade_charge_info.clone(this);
			return trade_charge_info;
		}
		
		public int fromDataStream(ByteVector byte_vect, int offset) {
			int ret_len = 0;
			
			m_gun = ComntStream.readStream(byte_vect, offset + ret_len, m_gun);
			ret_len += ComntStream.getDataSize(m_gun);
			
			m_ua = ComntStream.readStream(byte_vect, offset + ret_len, m_ua);
			ret_len += ComntStream.getDataSize(m_ua);
			
			m_ub = ComntStream.readStream(byte_vect, offset + ret_len, m_ub);
			ret_len += ComntStream.getDataSize(m_ub);
			
			m_uc = ComntStream.readStream(byte_vect, offset + ret_len, m_uc);
			ret_len += ComntStream.getDataSize(m_uc);
			
			m_ia = ComntStream.readStream(byte_vect, offset + ret_len, m_ia);
			ret_len += ComntStream.getDataSize(m_ia);
			
			m_ib = ComntStream.readStream(byte_vect, offset + ret_len, m_ib);
			ret_len += ComntStream.getDataSize(m_ib);
			
			m_ic = ComntStream.readStream(byte_vect, offset + ret_len, m_ic);
			ret_len += ComntStream.getDataSize(m_ic);
			
			m_time = ComntStream.readStream(byte_vect, offset + ret_len, m_time);
			ret_len += ComntStream.getDataSize(m_time);
			
			m_energy = ComntStream.readStream(byte_vect, offset + ret_len, m_energy);
			ret_len += ComntStream.getDataSize(m_energy);
			
			m_amount = ComntStream.readStream(byte_vect, offset + ret_len, m_amount);
			ret_len += ComntStream.getDataSize(m_amount);
			
			return ret_len;
		}
		
		public int toDataStream(ByteVector byte_vect) {
			int ret_len   = 0;
			
			ret_len += ComntStream.writeStream(byte_vect, m_gun);
			ret_len += ComntStream.writeStream(byte_vect, m_ua);
			ret_len += ComntStream.writeStream(byte_vect, m_ub);
			ret_len += ComntStream.writeStream(byte_vect, m_uc);
			ret_len += ComntStream.writeStream(byte_vect, m_ia);
			ret_len += ComntStream.writeStream(byte_vect, m_ib);
			ret_len += ComntStream.writeStream(byte_vect, m_ic);
			ret_len += ComntStream.writeStream(byte_vect, m_time);
			ret_len += ComntStream.writeStream(byte_vect, m_energy);
			ret_len += ComntStream.writeStream(byte_vect, m_amount);

			return ret_len;
		}
		
	}
	
	/**
	 * 中间规约ID-充电开始
	 * */
	public final static int PROT_I_ID_TRADE_CHARGE_BEGIN = 5050904;
	public static class TRADE_CHARGE_BEGIN implements IMSG_READWRITESTREAM{
		public byte[] m_serial = new byte[32];	//流水号
		public int 	  m_flag;					//成功失败标志	0：成功 1：失败	
		
		public void clean(){
			for(int i=0; i<32; i++) m_serial[i] = 0;
			m_flag 	= 0;
		}
		
		public void clone(TRADE_CHARGE_BEGIN trade_charge_begin){
			for(int i=0; i<32; i++) m_serial[i] = trade_charge_begin.m_serial[i];
			m_flag  = trade_charge_begin.m_flag;
		}
		
		public TRADE_CHARGE_BEGIN clone(){
			TRADE_CHARGE_BEGIN trade_charge_begin = new TRADE_CHARGE_BEGIN();
			trade_charge_begin.clone(this);
			return trade_charge_begin;
		}
		
		public int fromDataStream(ByteVector byte_vect, int offset) {
			int ret_len = 0;
			
			ComntStream.readStream(byte_vect, offset + ret_len, m_serial, 0, m_serial.length);
			ret_len += ComntStream.getDataSize(m_serial[0]) * m_serial.length;
			
			m_flag = ComntStream.readStream(byte_vect, offset + ret_len, m_flag);
			ret_len += ComntStream.getDataSize(m_flag);			
		
			return ret_len;
		}
		
		public int toDataStream(ByteVector byte_vect) {
			int ret_len   = 0;
			ret_len += ComntStream.writeStream(byte_vect, m_serial, 0, m_serial.length);
			ret_len += ComntStream.writeStream(byte_vect, m_flag);
			return ret_len;
		}
	}
	
	/**
	 * 中间规约ID-充电结束
	 * */
	public final static int PROT_I_ID_TRADE_CHARGE_END = 5050905;
	public static final int TRADE_CHARGE_INFO_DC = 5050933;
	public static class TRADE_CHARGE_END implements IMSG_READWRITESTREAM{
		public int			m_cause;  						//结束原因  0：正常充满，1：余额不足，2：停电，3：急停，4：过压，5：欠压，6：过流，7：485召表不通，8：充电枪断开，9：人为结束
		public CHARGEINFO 	m_charge = new CHARGEINFO(); 	//充电记录
		
		public void clean(){
			m_cause = 0;
//			m_charge = null;
		}
		
		public void clone(TRADE_CHARGE_END trade_charge_end){
			m_cause  = trade_charge_end.m_cause;
			m_charge = trade_charge_end.m_charge;
		}
		
		public TRADE_CHARGE_END clone(){
			TRADE_CHARGE_END trade_charge_end = new TRADE_CHARGE_END();
			trade_charge_end.clone(this);
			return trade_charge_end;
		}
		
		public int fromDataStream(ByteVector byte_vect, int offset) {
			int ret_len = 0;
			
			m_cause = ComntStream.readStream(byte_vect, offset + ret_len, m_cause);
			ret_len += ComntStream.getDataSize(m_cause);
			
			if(m_charge != null){
				
				ComntStream.readStream(byte_vect, offset + ret_len, m_charge.trade_no, 0, m_charge.trade_no.length);
				ret_len += ComntStream.getDataSize(m_charge.trade_no[0]) * m_charge.trade_no.length;
				
				m_charge.area_code = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.area_code);
				ret_len += ComntStream.getDataSize(m_charge.area_code);
			
				m_charge.media_type = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.media_type);
				ret_len += ComntStream.getDataSize(m_charge.media_type);
				
				ComntStream.readStream(byte_vect, offset + ret_len, m_charge.card_no, 0, m_charge.card_no.length);
				ret_len += ComntStream.getDataSize(m_charge.card_no[0]) * m_charge.card_no.length;
				
				m_charge.begin_bd = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.begin_bd);
				ret_len += ComntStream.getDataSize(m_charge.begin_bd);
			
				ComntStream.readStream(byte_vect, offset + ret_len, m_charge.begin_bd_fl, 0, m_charge.begin_bd_fl.length);
				ret_len += ComntStream.getDataSize(m_charge.begin_bd_fl[0]) * m_charge.begin_bd_fl.length;
				
				m_charge.end_bd = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.end_bd);
				ret_len += ComntStream.getDataSize(m_charge.end_bd);
				
				ComntStream.readStream(byte_vect, offset + ret_len, m_charge.end_bd_fl, 0, m_charge.end_bd_fl.length);
				ret_len += ComntStream.getDataSize(m_charge.end_bd_fl[0]) * m_charge.end_bd_fl.length;
				
				m_charge.parking_price = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.parking_price);
				ret_len += ComntStream.getDataSize(m_charge.parking_price);
				
				m_charge.begin_date = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.begin_date);
				ret_len += ComntStream.getDataSize(m_charge.begin_date);
				
				m_charge.begin_time = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.begin_time);
				ret_len += ComntStream.getDataSize(m_charge.begin_time);
				
				m_charge.end_date = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.end_date);
				ret_len += ComntStream.getDataSize(m_charge.end_date);
				
				m_charge.end_time = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.end_time);
				ret_len += ComntStream.getDataSize(m_charge.end_time);
				
				m_charge.parking_fee = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.parking_fee);
				ret_len += ComntStream.getDataSize(m_charge.parking_fee);
				
				m_charge.balance1 = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.balance1);
				ret_len += ComntStream.getDataSize(m_charge.balance1);
				
				m_charge.charge = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.charge);
				ret_len += ComntStream.getDataSize(m_charge.charge);
				
				m_charge.balance2 = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.balance2);
				ret_len += ComntStream.getDataSize(m_charge.balance2);
			
				m_charge.gun = ComntStream.readStream(byte_vect, offset + ret_len, m_charge.gun);
				ret_len += ComntStream.getDataSize(m_charge.gun);
			}
			
			return ret_len;
		}	
		
		public int toDataStream(ByteVector byte_vect) {
			int ret_len   = 0;
			ret_len += ComntStream.writeStream(byte_vect, m_cause);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.trade_no, 0, m_charge.trade_no.length);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.area_code);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.media_type);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.card_no, 0, m_charge.card_no.length);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.begin_bd);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.begin_bd_fl, 0, m_charge.begin_bd_fl.length);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.end_bd);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.end_bd_fl, 0, m_charge.end_bd_fl.length);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.parking_price);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.begin_date);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.begin_time);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.end_date);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.end_time);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.parking_fee);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.balance1);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.charge);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.balance2);
			ret_len += ComntStream.writeStream(byte_vect, m_charge.gun);
			return ret_len;
		}
	}
	
	//充电记录--结构体
	public static class CHARGEINFO{
		public byte[]	trade_no = new byte[16];	//充电流水号
		public int		area_code;					//地区代码
		public int		media_type;					//媒介类型	0：卡	1：APP
		public byte[]	card_no  = new byte[16];	//卡号
		public double	begin_bd;					//开始交易电量行度
		public double[]	begin_bd_fl = new double[4];//开始交易费率电量行度
		public double	end_bd;						//结束交易电量行度
		public double[]	end_bd_fl = new double[4];	//结束交易费率电量行度
		public double	parking_price;				//停车费单价
		public int		begin_date;					//交易开始日期
		public int		begin_time;					//交易开始时间
		public int		end_date;					//交易结束日期
		public int		end_time;					//交易结束时间
		public double	parking_fee;				//停车费
		public double	balance1;					//交易前余额
		public double	charge;						//充值金额
		public double	balance2;					//交易后余额
		public int		gun;						//枪号 0：A枪	1：B枪
	}
	
	// 中间规约ID-直流桩充电统计数据
	public static class TRADE_CHARGE_INFO_DC implements IMSG_READWRITESTREAM{

		public int m_gun; // 枪号 0：A枪，1：B枪
		public int m_soc; // 充电进度百分比SOC -(1-100)%
		public int m_remain_tm; // 剩余时间-秒
		public byte[] m_serial = new byte[32]; // 流水号
		public byte[] m_reserve = new byte[32]; // 扩展

		public void clean() {
			for (int i = 0; i < 32; i++) {
				m_serial[i] = 0;
				m_reserve[i] = 0;
			}
			m_gun = 0;
			m_soc = 0;
			m_remain_tm = 0;
		}
		
		public void clone(TRADE_CHARGE_INFO_DC trade_charge_info_dc){
			for (int i = 0; i < 32; i++) {
				m_serial[i] = trade_charge_info_dc.m_serial[i];
				m_reserve[i] = trade_charge_info_dc.m_reserve[i];
			}
			m_gun = trade_charge_info_dc.m_gun;
			m_soc = trade_charge_info_dc.m_soc;
			m_remain_tm = trade_charge_info_dc.m_remain_tm;
		}
		
		public TRADE_CHARGE_INFO_DC clone(){
			TRADE_CHARGE_INFO_DC trade_charge_info_dc = new TRADE_CHARGE_INFO_DC();
			trade_charge_info_dc.clone(this);
			return trade_charge_info_dc;
		}
		
		
		
		/* (non-Javadoc)
		 * @see com.wo.comnt.ComntMsg.IMSG_READWRITESTREAM#fromDataStream(com.wo.comnt.ComntVector.ByteVector, int)
		 */
		public int fromDataStream(ByteVector byteVect, int offset) {
			int ret_len = 0;
			m_gun = ComntStream.readStream(byteVect, offset + ret_len, m_gun);
			ret_len += ComntStream.getDataSize(m_gun);			

			m_soc = ComntStream.readStream(byteVect, offset + ret_len, m_soc);
			ret_len += ComntStream.getDataSize(m_soc);	
			
			m_remain_tm= ComntStream.readStream(byteVect, offset + ret_len, m_remain_tm);
			ret_len += ComntStream.getDataSize(m_remain_tm);	
			
			ComntStream.readStream(byteVect, offset + ret_len, m_serial, 0, m_serial.length);
			ret_len += ComntStream.getDataSize(m_serial[0]) * m_serial.length;
			
			ComntStream.readStream(byteVect, offset + ret_len, m_reserve, 0, m_reserve.length);
			ret_len += ComntStream.getDataSize(m_reserve[0]) * m_reserve.length;
 			
			return ret_len;
		}

		/* (non-Javadoc)
		 * @see com.wo.comnt.ComntMsg.IMSG_READWRITESTREAM#toDataStream(com.wo.comnt.ComntVector.ByteVector)
		 */
		public int toDataStream(ByteVector byteVect) {
 			int ret_len = 0;
			ret_len += ComntStream.writeStream(byteVect, m_gun);
			ret_len += ComntStream.writeStream(byteVect, m_soc);
			ret_len += ComntStream.writeStream(byteVect, m_remain_tm);
			ret_len += ComntStream.writeStream(byteVect, m_serial, 0, m_serial.length);
			ret_len += ComntStream.writeStream(byteVect, m_reserve, 0, m_reserve.length);
			
			return ret_len;
		}
	}
}
