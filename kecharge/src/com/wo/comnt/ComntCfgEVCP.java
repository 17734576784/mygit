package com.wo.comnt;

import com.wo.comnt.ComntMsg.IMSG_READWRITESTREAM;
import com.wo.comnt.ComntVector.ByteVector;

public class ComntCfgEVCP extends ComntCfg{

	/**
	 * 汉佳APP发送充电指令--结构体
	 * */
//	public static class MSG_APP_CHARGE_HJ implements IMSG_READWRITESTREAM{
//		public byte[] pile_code = new byte[32]; // 桩体号
//		public byte[] wasteno = new byte[32]; // 流水号
//		public int gun; // 枪号 0：A枪 1：B枪
//		public int gun_state; // 枪连接状态
//		public int cmd; // 充电指令 1：充电 2：断电
//		public int cpFlag; // cp信号标志
//		public byte[] reserver1 = new byte[32]; // 备用字段1
//		public byte[] reserver2 = new byte[32]; // 备用字段2
//		public byte[] reserver3 = new byte[32]; // 备用字段3
//		
//		public void clean() {
//			gun = 0;
//			gun_state = 0;
//			cmd = 0;
//			cpFlag = 0;
//			for (int i = 0; i < 32; i++)
//				pile_code[i] = 0;
//			for (int i = 0; i < 32; i++)
//				wasteno[i] = 0;
//			for (int i = 0; i < 32; i++)
//				reserver1[i] = 0;
//			for (int i = 0; i < 32; i++)
//				reserver2[i] = 0;
//			for (int i = 0; i < 32; i++)
//				reserver3[i] = 0;
//		}
//		
//		public void clone(MSG_APP_CHARGE_HJ app_charge) {
//			gun = app_charge.gun;
//			gun_state = app_charge.gun_state;
//			cmd = app_charge.cmd;
//			for (int i = 0; i < 32; i++)
//				pile_code[i] = app_charge.pile_code[i];
//			for (int i = 0; i < 32; i++)
//				wasteno[i] = app_charge.wasteno[i];
//			for (int i = 0; i < 32; i++)
//				reserver1[i] = app_charge.reserver1[i];
//			for (int i = 0; i < 32; i++)
//				reserver2[i] = app_charge.reserver2[i];
//			for (int i = 0; i < 32; i++)
//				reserver3[i] = app_charge.reserver3[i];
//		}
//		
//		public MSG_APP_CHARGE_HJ clone() {
//			MSG_APP_CHARGE_HJ app_charge = new MSG_APP_CHARGE_HJ();
//			app_charge.clone(this);
//			return app_charge;
//		}
//		
//		public int fromDataStream(ByteVector byte_vect, int offset) {
//			int ret_len = 0;
//			
//			ComntStream.readStream(byte_vect, offset + ret_len, pile_code, 0, pile_code.length);
//			ret_len += ComntStream.getDataSize(pile_code[0]) * pile_code.length;
//			
//			ComntStream.readStream(byte_vect, offset + ret_len, wasteno, 0, wasteno.length);
//			ret_len += ComntStream.getDataSize(wasteno[0]) * wasteno.length;
//			
//			gun = ComntStream.readStream(byte_vect, offset + ret_len, gun);
//			ret_len += ComntStream.getDataSize(gun);
//			
//			gun_state = ComntStream.readStream(byte_vect, offset + ret_len, gun_state);
//			ret_len += ComntStream.getDataSize(gun_state);
//			
//			cmd = ComntStream.readStream(byte_vect, offset + ret_len, cmd);
//			ret_len += ComntStream.getDataSize(cmd);
//			
//			cpFlag = ComntStream.readStream(byte_vect, offset + ret_len, cpFlag);
//			ret_len += ComntStream.getDataSize(cpFlag);
//			
//			ComntStream.readStream(byte_vect, offset + ret_len, reserver1, 0, reserver1.length);
//			ret_len += ComntStream.getDataSize(reserver1[0]) * reserver1.length;
//			
//			ComntStream.readStream(byte_vect, offset + ret_len, reserver2, 0, reserver2.length);
//			ret_len += ComntStream.getDataSize(reserver2[0]) * reserver2.length;
//			
//			ComntStream.readStream(byte_vect, offset + ret_len, reserver3, 0, reserver3.length);
//			ret_len += ComntStream.getDataSize(reserver3[0]) * reserver3.length;
//			
//			return ret_len;
//		}
//
//		public int toDataStream(ByteVector byte_vect) {
//			int ret_len   = 0;
// 			ret_len += ComntStream.writeStream(byte_vect, pile_code, 0, pile_code.length);
// 			ret_len += ComntStream.writeStream(byte_vect, wasteno, 0, wasteno.length);
//			ret_len += ComntStream.writeStream(byte_vect, gun);
//			ret_len += ComntStream.writeStream(byte_vect, gun_state);
//			ret_len += ComntStream.writeStream(byte_vect, cmd);
//			ret_len += ComntStream.writeStream(byte_vect, cpFlag);
//			ret_len += ComntStream.writeStream(byte_vect, reserver1, 0, reserver1.length);
// 			ret_len += ComntStream.writeStream(byte_vect, reserver2, 0, reserver2.length);
//			ret_len += ComntStream.writeStream(byte_vect, reserver3, 0, reserver3.length);
//
//			return ret_len;
//		}
//		
//	}
	
	/**
	 * 汉佳充电请求应答
	 * */
//	public static class MSG_APP_CHARGE_HJ_RESULT implements IMSG_READWRITESTREAM{
//		public int retcode; // 返回应答结果 1：成功 2：失败
//		public byte[] retinfo = new byte[64]; // 描述信息
//
//		public void clean() {
//			retcode = 0;
//			for (int i = 0; i < 64; i++)
//				retinfo[i] = 0;
//		}
//
//		public void clone(MSG_APP_CHARGE_HJ_RESULT app_resp) {
//			retcode = app_resp.retcode;
//			for (int i = 0; i < 64; i++)
//				retinfo[i] = app_resp.retinfo[i];
//		}
//		
//		public MSG_APP_CHARGE_HJ_RESULT clone(){
//			MSG_APP_CHARGE_HJ_RESULT app_rest = new MSG_APP_CHARGE_HJ_RESULT();
//			app_rest.clone(this);
//			return app_rest;
//		}
//		
//		public int fromDataStream(ByteVector byte_vect, int offset) {
//			int ret_len = 0;
//			
//			retcode = ComntStream.readStream(byte_vect, offset + ret_len, retcode);
//			ret_len += ComntStream.getDataSize(retcode);			
//			
//			ComntStream.readStream(byte_vect, offset + ret_len, retinfo, 0, retinfo.length);
//			ret_len += ComntStream.getDataSize(retinfo[0]) * retinfo.length;						
//			
//			return ret_len;
//		}
//
//		public int toDataStream(ByteVector byte_vect) {
//			int ret_len   = 0;
//			ret_len += ComntStream.writeStream(byte_vect, retcode);
//			ret_len += ComntStream.writeStream(byte_vect, retinfo, 0, retinfo.length);
//			return ret_len;
//		}		
//	}
	
	/**
	 * 实充实退，APP发送充电指令--结构体
	 * */
	public static class APP_CHARGE_WITH_PAY implements IMSG_READWRITESTREAM{
		public int      app_type;					//应用类型  1:APP随充随退模式   2:微信小程序随充随退模式
		public int		owner_id;					//用户号
		public byte[]	pile_code = new byte[32];	//桩体号
		public int		gun;						//枪号	0：A枪 1：B枪
		public int		gun_state;					//枪连接状态
		public int		cmd;						//充电指令	1：充电 2：断电
		public byte[]   pay_wasteno = new byte[32]; //
		public int      pay_money;					//
		
		public void clean(){
			app_type    = 0;
			owner_id 	= 0;
			gun 		= 0;
			gun_state 	= 0;
			cmd 		= 0;
			pay_money   = 0;
			for(int i=0; i<32; i++) pile_code[i] = 0;
			for(int i=0; i<32; i++) pay_wasteno[i] = 0;
		}
		
		public void clone(APP_CHARGE_WITH_PAY app_charge){
			app_type    = app_charge.app_type;
			owner_id 	= app_charge.owner_id;
			gun 		= app_charge.gun;
			gun_state 	= app_charge.gun_state;
			cmd 		= app_charge.cmd;	
			pay_money   = app_charge.pay_money;
			for(int i=0; i<32; i++) pile_code[i] = app_charge.pile_code[i];
			for(int i=0; i<32; i++) pay_wasteno[i] = app_charge.pay_wasteno[i];
		}
		
		public APP_CHARGE_WITH_PAY clone(){
			APP_CHARGE_WITH_PAY app_charge = new APP_CHARGE_WITH_PAY();
			app_charge.clone(this);
			return app_charge;
		}
		
		public int fromDataStream(ByteVector byte_vect, int offset) {
			int ret_len = 0;
			
			app_type = ComntStream.readStream(byte_vect, offset + ret_len, app_type);
			ret_len += ComntStream.getDataSize(app_type);			
			
			owner_id = ComntStream.readStream(byte_vect, offset + ret_len, owner_id);
			ret_len += ComntStream.getDataSize(owner_id);			
			
			ComntStream.readStream(byte_vect, offset + ret_len, pile_code, 0, pile_code.length);
			ret_len += ComntStream.getDataSize(pile_code[0]) * pile_code.length;
			
			gun = ComntStream.readStream(byte_vect, offset + ret_len, gun);
			ret_len += ComntStream.getDataSize(gun);
			
			gun_state = ComntStream.readStream(byte_vect, offset + ret_len, gun_state);
			ret_len += ComntStream.getDataSize(gun_state);
			
			cmd = ComntStream.readStream(byte_vect, offset + ret_len, cmd);
			ret_len += ComntStream.getDataSize(cmd);
			
			ComntStream.readStream(byte_vect, offset + ret_len, pay_wasteno, 0, pay_wasteno.length);
			ret_len += ComntStream.getDataSize(pay_wasteno[0]) * pay_wasteno.length;
			
			pay_money = ComntStream.readStream(byte_vect, offset + ret_len, pay_money);
			ret_len += ComntStream.getDataSize(pay_money);
			
			return ret_len;
		}

		public int toDataStream(ByteVector byte_vect) {
			int ret_len   = 0;
			ret_len += ComntStream.writeStream(byte_vect, app_type);
			ret_len += ComntStream.writeStream(byte_vect, owner_id);
			ret_len += ComntStream.writeStream(byte_vect, pile_code, 0, pile_code.length);
			ret_len += ComntStream.writeStream(byte_vect, gun);
			ret_len += ComntStream.writeStream(byte_vect, gun_state);
			ret_len += ComntStream.writeStream(byte_vect, cmd);
			ret_len += ComntStream.writeStream(byte_vect, pay_wasteno, 0, pay_wasteno.length);
			ret_len += ComntStream.writeStream(byte_vect, pay_money);
			
			return ret_len;
		}
	}
	
	/**
	 * 后台立即应答APP--结构体
	 * */
	public static class APP_CHARGE_WITH_PAY_RESULT implements IMSG_READWRITESTREAM{
		public int 		retcode;				//返回应答结果  1：成功	2：失败
		public byte[] 	retinfo = new byte[64];	//描述信息	
		
		public void clean(){
			retcode = 0;
			for(int i=0; i<64; i++) retinfo[i] = 0;
		}
		
		public void clone(APP_CHARGE_WITH_PAY_RESULT app_charge_with_pay_result){
			retcode = app_charge_with_pay_result.retcode;
			for(int i=0; i<64; i++) retinfo[i] = app_charge_with_pay_result.retinfo[i];
		}
		
		public APP_CHARGE_WITH_PAY_RESULT clone(){
			APP_CHARGE_WITH_PAY_RESULT app_charge_with_pay_result = new APP_CHARGE_WITH_PAY_RESULT();
			app_charge_with_pay_result.clone(this);
			return app_charge_with_pay_result;
		}
		
		public int fromDataStream(ByteVector byte_vect, int offset) {
			int ret_len = 0;
			
			retcode = ComntStream.readStream(byte_vect, offset + ret_len, retcode);
			ret_len += ComntStream.getDataSize(retcode);			
			
			ComntStream.readStream(byte_vect, offset + ret_len, retinfo, 0, retinfo.length);
			ret_len += ComntStream.getDataSize(retinfo[0]) * retinfo.length;						
			
			return ret_len;
		}

		public int toDataStream(ByteVector byte_vect) {
			int ret_len   = 0;
			ret_len += ComntStream.writeStream(byte_vect, retcode);
			ret_len += ComntStream.writeStream(byte_vect, retinfo, 0, retinfo.length);
			return ret_len;
		}		
		
	}
	
	//------------------前置向web服务推送信息------------------------\\
	/**
	 * 中间规约ID-充电信息
	 * */
	public final static int PROT_I_ID_TRADE_CHARGE_INFO = 5050903;	
	public static class TRADE_CHARGE_INFO implements IMSG_READWRITESTREAM{
		public int m_gun; // 枪号 0：A枪，1：B枪
		public double m_ua; // A相电压
		public double m_ub; // B相电压
		public double m_uc; // C相电压
		public double m_ia; // A相电流
		public double m_ib; // B相电流
		public double m_ic; // C相电流
		public int m_time; // 当前充电时间 单位秒
		public double m_energy; // 充电电量
		public double m_amount; // 充电金额
		
		public void clean() {
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
		
		public void clone(TRADE_CHARGE_INFO trade_charge_info) {
			m_gun = trade_charge_info.m_gun;
			m_ua = trade_charge_info.m_ua;
			m_ub = trade_charge_info.m_ub;
			m_uc = trade_charge_info.m_uc;
			m_ia = trade_charge_info.m_ia;
			m_ib = trade_charge_info.m_ib;
			m_ic = trade_charge_info.m_ic;
			m_time = trade_charge_info.m_time;
			m_energy = trade_charge_info.m_energy;
			m_amount = trade_charge_info.m_amount;
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
		public byte[] m_serial = new byte[32]; // 流水号
		public int m_flag; // 成功失败标志 0：成功 1：失败

		public void clean() {
			for (int i = 0; i < 32; i++)
				m_serial[i] = 0;
			m_flag = 0;
		}

		public void clone(TRADE_CHARGE_BEGIN trade_charge_begin) {
			for (int i = 0; i < 32; i++)
				m_serial[i] = trade_charge_begin.m_serial[i];
			m_flag = trade_charge_begin.m_flag;
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
		public byte[] trade_no = new byte[16]; // 交易流水号
		public int area_code; // 地区代码
		public int media_type; // 媒介类型 0：卡 1：APP
		public byte[] card_no = new byte[32]; // 卡号
		public double begin_bd; // 开始交易电量行度
		public double[] begin_bd_fl = new double[4];// 开始交易费率电量行度 尖峰平谷
		public double end_bd; // 结束交易电量行度
		public double[] end_bd_fl = new double[4]; // 结束交易费率电量行度
		public double parking_price; // 停车费单价
		public int begin_date; // 交易开始日期
		public int begin_time; // 交易开始时间
		public int end_date; // 交易结束日期
		public int end_time; // 交易结束时间
		public double parking_fee; // 停车费
		public double balance1; // 交易前余额
		public double charge; // 充值金额
		public double balance2; // 交易后余额
		public int gun; // 枪号 0：A枪 1：B枪
	}
	
	// 中间规约ID-直流桩充电统计数据
	public static final int TRADE_CHARGE_INFO_DC =	5050933;
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
