/**   
* @Title: ComntMsgProc.java 
* @Package com.ke.comnt 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhp
* @date 2018年12月13日 下午5:03:58 
* @version V1.0   
*/
package com.ke.comnt;


import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ke.common.CommFunc;
import com.ke.common.Constant;
import com.ke.comnt.ComntCfgEVCP.TRADE_CHARGE_BEGIN;
import com.ke.comnt.ComntCfgEVCP.TRADE_CHARGE_END;
import com.ke.comnt.ComntCfgEVCP.TRADE_CHARGE_INFO_DC;
import com.ke.comnt.ComntMsg.MSG_DATA_FN;
import com.ke.logger.LogName;
import com.ke.logger.LoggerUtil;
import com.ke.mapper.ChargeMonitorMapper;
import com.ke.mapper.MemberOrdersMapper;
import com.ke.model.ChargeMonitor;
import com.ke.model.MemberOrders;
import com.ke.service.IChargeService;
import com.ke.utils.BytesUtil;
import com.ke.utils.ConverterUtil;
import com.ke.utils.DateUtil;
import com.ke.utils.JedisUtil;
 

/** 
* @ClassName: ComntMsgProc 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author zhp
* @date 2018年12月13日 下午5:03:58 
*  
*/
@Component
public class ComntMsgProc {
	
	private static ComntMsgProc comntMsgProc;
	
	@Resource
	private MemberOrdersMapper memberOrdersMapper;
	
	@Resource
	private ChargeMonitorMapper chargeMonitorMapper;
	
	@Autowired
	private IChargeService chargeService;

	@PostConstruct
	public void init(){
		comntMsgProc = this;
		comntMsgProc.memberOrdersMapper = this.memberOrdersMapper;
		comntMsgProc.chargeService = this.chargeService;
		comntMsgProc.chargeMonitorMapper = this.chargeMonitorMapper;
	}
	
	public static final int MSG_MAX_TIMEOUT		= 300;			//等待超时5min
	public static final int MSG_DEFAULT_CLIENT_WAIT_NUM	= 610;	//客户端默认等待时间单位数量500ms * 610
	
	public static final int MAX_MSGBUF_NUM 		= 1024;		//最大消息缓存
	
	//消息状态
	public static final int MSGSTEP_WAITSEND 	= 0;		//等待发送
	public static final int MSGSTEP_RECVING	 	= 1;		//正在接收
	public static final int MSGSTEP_FINISH 	 	= 2;		//完成--数据接收完毕
	public static final int MSGSTEP_END	 	 	= 3;		//结束--删除消息

	public static class ComntNetMsg {
		public int     time_cnt      = 0;
		public int     cur_step      = 0;
		public int     user_id1		 = 0;
		public int 	   user_id2		 = 0;
		
		public boolean cancel_flag	 = false;

		public ComntMsg.MSG_CLIENT_STRUCT 				msg_send 	 = null;
		public ArrayList<ComntMsg.MSG_SERVER_STRUCT> 	msg_recvvect = null;
	}

	private LinkedList<ComntNetMsg> msg_list = new LinkedList<ComntNetMsg>();

	public LinkedList<ComntNetMsg> getMsgList() {
		return msg_list;
	}

	private boolean isListFull() {
		if (msg_list.size() >= MAX_MSGBUF_NUM) return true;
		else return false;
	}

	private int findNetMsg(int uuid) {
		ComntNetMsg h_msg = null;
		
		for (int i = 0; i < msg_list.size(); i++) {
			h_msg = msg_list.get(i);
			if (h_msg.msg_send.msg_head.uuid == uuid) return i;
		}
		
		return -1;
	}

	private ComntNetMsg findNoFinishedNetMsg(String user_name, int user_id1, int user_id2) {
		ComntNetMsg h_msg = null;
		
		String str;
		
		for (int i = 0; i < msg_list.size(); i++) {
			h_msg = msg_list.get(i);
			if (h_msg.cur_step >= MSGSTEP_FINISH) continue;
			
			if (user_id1 != h_msg.user_id1 || user_id2 != h_msg.user_id2) continue;			
			str = ComntFunc.byte2String(h_msg.msg_send.msg_head.user_name);
						
			if (user_name.compareToIgnoreCase(str) == 0) return h_msg;
		}
		
		return null;
	}
	
	private ComntNetMsg findNoEndNetMsg(String user_name, int user_id1, int user_id2) {
		ComntNetMsg h_msg = null;
		
		String str;
		
		for (int i = 0; i < msg_list.size(); i++) {
			h_msg = msg_list.get(i);
			if (h_msg.cur_step >= MSGSTEP_END) continue;
			
			if (user_id1 != h_msg.user_id1 || user_id2 != h_msg.user_id2) continue;			
			str = ComntFunc.byte2String(h_msg.msg_send.msg_head.user_name);
						
			if (user_name.compareToIgnoreCase(str) == 0) return h_msg;
		}
		
		return null;
	}	
	
	private static boolean isMsgRecvFinish(int msg_type) {
		if ((msg_type == ComntDef.YD_WEBMSGTYPE_TASKRESULT) ||
			(msg_type == ComntDef.YD_WEBMSGTYPE_RTUSTATE)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private static boolean isASendMsg(int msg_type) {
		if ((msg_type == ComntDef.YD_WEBMSGTYPE_TASK) ||
			(msg_type == ComntDef.YD_WEBMSGTYPE_QUERYRTUSTATE) ||
			(msg_type == ComntDef.YD_WEBMSGTYPE_RELOADPARA)) {
			return true;
		}
		else return false;		
	}
	
	private static boolean isARecvMsg(int msg_type) {
		if ((msg_type == ComntDef.YD_WEBMSGTYPE_TASKRESULT) ||
			(msg_type == ComntDef.YD_WEBMSGTYPE_DATA) ||
			(msg_type == ComntDef.YD_WEBMSGTYPE_RAWDATA) ||
			(msg_type == ComntDef.YD_WEBMSGTYPE_RTUSTATE)) {
				return true;
			}
			else return false;
	}
	
	private static boolean isAOnlySendMsg(int msg_type) {
		if (msg_type == ComntDef.YD_WEBMSGTYPE_RELOADPARA) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean endAlreadyInBufferMsg(int msg_type, byte []user_name, int user_id1, int user_id2) {
		if (msg_type != ComntDef.YD_WEBMSGTYPE_TASK) return true;
		
		String str = ComntFunc.byte2String(user_name);
		
		for (int i = 0; i< 10; i++) { //容错
	//		ComntNetMsg h_msg = findNoFinishedNetMsg(str, user_id1, user_id2);
			ComntNetMsg h_msg = findNoEndNetMsg(str, user_id1, user_id2);		
			
			if (h_msg == null) return true;
			else if (h_msg.cur_step < MSGSTEP_FINISH) return false;
			else if (h_msg.cur_step == MSGSTEP_FINISH) {
				h_msg.cur_step = MSGSTEP_END;	//结束消息
			}
			//return true;
		}
		
		return true;
	}
	
	public synchronized static boolean addWaitSendMsg(ComntMsg.MSG_CLIENT_HEAD msg_head, ComntMsg.MSG_BODY msg_body, int user_id1, int user_id2) {
		if (msg_head.data_len != msg_body.byte_vect.size()) return false;		
		if (!isASendMsg(msg_head.msg_type)) 				return false;

		if (isAOnlySendMsg(msg_head.msg_type)) msg_head.onlysend_flag = 1;		

		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) 		return false;
		if (msgproc.isListFull()) 	return false;
		if (!msgproc.endAlreadyInBufferMsg(msg_head.msg_type, msg_head.user_name, user_id1, user_id2)) return false;
		
		LinkedList<ComntNetMsg> msg_list = msgproc.getMsgList();

		ComntNetMsg net_msg = new ComntNetMsg();

		net_msg.time_cnt  	  = ComntTime.cTime();
		net_msg.cur_step 	  = MSGSTEP_WAITSEND;

		net_msg.user_id1      = user_id1;
		net_msg.user_id2      = user_id2;
		
		net_msg.msg_send      = new ComntMsg.MSG_CLIENT_STRUCT();
		net_msg.msg_send.msg_head.clone(msg_head);
		net_msg.msg_send.msg_body.clone(msg_body);

		net_msg.msg_recvvect  = new ArrayList<ComntMsg.MSG_SERVER_STRUCT>();

		msg_list.add(net_msg);
		
		return true;
	}
	
	public synchronized static ArrayList<ComntMsg.MSG_CLIENT_STRUCT> getWaitSendMsg(int max_num) {
		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) return null;

		LinkedList<ComntNetMsg> 				msg_list = msgproc.getMsgList();
		ArrayList<ComntMsg.MSG_CLIENT_STRUCT> 	ret_vect = new ArrayList<ComntMsg.MSG_CLIENT_STRUCT>();

		int i = 0, msg_num = 0;
		ComntNetMsg net_msg = null;

		//任务取消,高优先级
		ComntMsg.MSG_CLIENT_STRUCT comnt_msg = null; 

		for (i = 0; i < msg_list.size(); i++) {
			net_msg = msg_list.get(i);
			if (net_msg.cur_step != MSGSTEP_RECVING) continue;
			if (net_msg.msg_send.msg_head.onlysend_flag != 0 || !net_msg.cancel_flag) continue;

			//生成任务取消消息
			comnt_msg = new ComntMsg.MSG_CLIENT_STRUCT();
			//ComntMsg.makeCancelManComntMsg(comnt_msg, 0, ComntFunc.byte2String(net_msg.msg_send.msg_head.user_name), net_msg.msg_send.msg_head.uuid);

			ret_vect.add(comnt_msg);

			msg_num++;
			if (msg_num >= max_num) break;
		}

		if (msg_num >= max_num) return ret_vect;

		//再发送任务数据
		for (i = 0; i < msg_list.size(); i++) {
			net_msg = msg_list.get(i);
			if (net_msg.cur_step != MSGSTEP_WAITSEND) continue;
			if (net_msg.cancel_flag) continue;
			
			ret_vect.add(net_msg.msg_send);
			
			msg_num++;
			if (msg_num >= max_num) break;
		}
		
		if (ret_vect.size() <= 0) return null;
		else return ret_vect;
	}
	
	public synchronized static boolean setMsgRecvingFlag(int uuid) {
		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) 	return false;

		int msg_idx = msgproc.findNetMsg(uuid);
		if (msg_idx < 0) 		return false;

		LinkedList<ComntNetMsg> msg_list 	= msgproc.getMsgList();		
		ComntNetMsg 			net_msg 	= msg_list.get(msg_idx);

		if (net_msg.msg_send.msg_head.onlysend_flag != 0) {
			net_msg.cur_step = MSGSTEP_END;
		}
		else if (net_msg.cur_step < MSGSTEP_RECVING) {//防止复写cancel
			net_msg.cur_step = MSGSTEP_RECVING;
		}
		
		return true;
	}
	
	public synchronized static boolean setMsgCancelRecv(int uuid) {
		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) return false;

		int msg_idx = msgproc.findNetMsg(uuid);
		if (msg_idx < 0) 	 return false;

		LinkedList<ComntNetMsg> msg_list = msgproc.getMsgList();		
		ComntNetMsg 			net_msg  = msg_list.get(msg_idx);

		if (!net_msg.cancel_flag) return false;

		//组织任务失败结果
		ComntMsg.MSG_SERVER_STRUCT comnt_msg = new ComntMsg.MSG_SERVER_STRUCT();
		ComntMsg.makeTaskResultComntMsg(comnt_msg, net_msg.msg_send, (byte)ComntDef.YD_TASKRESULT_FAILED, (byte)ComntDef.YD_TASKERR_CANCEL, (byte)ComntDef.YD_TASKPROC_JWEBSERVICE, ComntDef.YD_TASKPROC_JWEBSERVICE_NAME);

		net_msg.msg_recvvect.add(comnt_msg);

		net_msg.cur_step = MSGSTEP_FINISH;

		return true;
	}
	
	public synchronized static boolean addRecvMsg(ComntMsg.MSG_SERVER_STRUCT msg_struct) {
		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) return false;

		if (!isARecvMsg(msg_struct.msg_head.msg_type)) return false;

		int msg_idx = msgproc.findNetMsg(msg_struct.msg_head.uuid);
		if (msg_idx < 0) return false;

		LinkedList<ComntNetMsg> msg_list = msgproc.getMsgList();

		ComntNetMsg net_msg = msg_list.get(msg_idx);
		if (net_msg.cur_step <= MSGSTEP_WAITSEND || net_msg.cur_step >= MSGSTEP_FINISH) return false;
		if (net_msg.msg_send.msg_head.onlysend_flag != 0 || net_msg.cancel_flag) return false;		
		
		net_msg.time_cnt = ComntTime.cTime();
		net_msg.msg_recvvect.add(msg_struct.clone());

		boolean end_flag = isMsgRecvFinish(msg_struct.msg_head.msg_type);

		if (end_flag) {
			net_msg.cur_step = MSGSTEP_FINISH;
		}

		return true;
	}
	
	public static int getRecvMsgI(int uuid, boolean finish_flag, ArrayList<ComntMsg.MSG_SERVER_STRUCT> ret_vect) {
		ret_vect.clear();
		
		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) return MSGSTEP_END;
		
		int msg_idx = msgproc.findNetMsg(uuid);
		if (msg_idx < 0) return MSGSTEP_END;

		LinkedList<ComntNetMsg> msg_list = msgproc.getMsgList();

		ComntNetMsg net_msg = msg_list.get(msg_idx);
		int ret_step = net_msg.cur_step;

		if ((net_msg.cur_step != MSGSTEP_RECVING) && (net_msg.cur_step != MSGSTEP_FINISH)) return ret_step;		
		if (finish_flag && (net_msg.cur_step != MSGSTEP_FINISH)) return ret_step;

		int i = 0;
		ComntMsg.MSG_SERVER_STRUCT t_comnt_msg1, t_comnt_msg2;

		if (!finish_flag && (net_msg.cur_step == MSGSTEP_RECVING)) {
			for (i = 0; i < net_msg.msg_recvvect.size(); i++) {
				t_comnt_msg1 = net_msg.msg_recvvect.get(i);
				if ((t_comnt_msg1.msg_head.msg_type == ComntDef.YD_WEBMSGTYPE_DATA) ||
					(t_comnt_msg1.msg_head.msg_type == ComntDef.YD_WEBMSGTYPE_TASKRESULT)) break;
			}
			
			if (i >= net_msg.msg_recvvect.size()) return ret_step;
		}
		
		for (i = 0; i < net_msg.msg_recvvect.size(); i++) {
			t_comnt_msg1 = net_msg.msg_recvvect.get(i);
			t_comnt_msg2 = t_comnt_msg1.clone();

			ret_vect.add(t_comnt_msg2);
		}

		net_msg.msg_recvvect.clear();
		
		if (net_msg.cur_step == MSGSTEP_FINISH) {
			net_msg.cur_step = MSGSTEP_END;
		}

		return ret_step;
	}
	
	public synchronized static int getRecvMsg(int uuid, boolean finish_flag, ArrayList<ComntMsg.MSG_SERVER_STRUCT> ret_vect) {
		return getRecvMsgI(uuid, finish_flag, ret_vect);
	}
	
	public synchronized static int getRecvMsg(String user_name, int user_id1, int user_id2, 
											  boolean finish_flag, ArrayList<ComntMsg.MSG_SERVER_STRUCT> ret_vect) {
		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) 	return MSGSTEP_END;
			
		ComntNetMsg 			net_msg  = msgproc.findNoEndNetMsg(user_name, user_id1, user_id2);
		if (net_msg == null) 	return MSGSTEP_END;
		
		return getRecvMsgI(net_msg.msg_send.msg_head.uuid, finish_flag, ret_vect);
	}	
	
	public synchronized static int getRecvMsgTimeOut(int uuid) {
		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) return -100;
		
		int msg_idx = msgproc.findNetMsg(uuid);
		if (msg_idx < 0) return -100;
					
		LinkedList<ComntNetMsg> msg_list = msgproc.getMsgList();
		
		ComntNetMsg net_msg = msg_list.get(msg_idx);		
		if (net_msg.cur_step == MSGSTEP_END) return -100;
		
		int max_timeout = 0;
		max_timeout = Math.max(net_msg.msg_send.msg_head.wait_count, MSG_MAX_TIMEOUT);
		
		int cur_time = ComntTime.cTime();
		
		return max_timeout - Math.abs(cur_time - net_msg.time_cnt);		
	}
	
	private boolean cancelMsg(ComntNetMsg net_msg) {
		if (net_msg == null) return false;
		if (net_msg.msg_send.msg_head.onlysend_flag != 0 || net_msg.cancel_flag) 	return false;
		if (net_msg.cur_step > MSGSTEP_RECVING) 			return false;
		
		net_msg.cancel_flag = true;
		
		if (net_msg.cur_step == MSGSTEP_WAITSEND) {
			//组织任务失败结果
			ComntMsg.MSG_SERVER_STRUCT comnt_msg = new ComntMsg.MSG_SERVER_STRUCT();
			ComntMsg.makeTaskResultComntMsg(comnt_msg, net_msg.msg_send, (byte)ComntDef.YD_TASKRESULT_FAILED, (byte)ComntDef.YD_TASKERR_CANCEL, (byte)ComntDef.YD_TASKPROC_JWEBSERVICE, ComntDef.YD_TASKPROC_JWEBSERVICE_NAME);
			net_msg.msg_recvvect.add(comnt_msg);
			
			net_msg.cur_step = MSGSTEP_FINISH;
		}
		
		return true;
	}
	
	public synchronized static boolean cancelMsg(int uuid) {
		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) 	return false;
		
		int msg_idx = msgproc.findNetMsg(uuid);
		if (msg_idx < 0) 		return false;
		
		LinkedList<ComntNetMsg> msg_list = msgproc.getMsgList();		
		ComntNetMsg 			net_msg  = msg_list.get(msg_idx);
		
		return msgproc.cancelMsg(net_msg);
	}
	
	public synchronized static boolean cancelMsg(String user_name, int user_id1, int user_id2) {
		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) 	return false;
			
		ComntNetMsg 			net_msg  = msgproc.findNoFinishedNetMsg(user_name, user_id1, user_id2);
		if (net_msg == null) 	return false;
		
		return msgproc.cancelMsg(net_msg);		
	}
	
	public synchronized static void endAllRunningTask(int err_code) {
		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) return;
		
		LinkedList<ComntNetMsg> msg_list = msgproc.getMsgList();
		
		int i = 0;
		ComntNetMsg 				net_msg 	= null;		
		ComntMsg.MSG_SERVER_STRUCT 	comnt_msg 	= null;
		
		for (i = 0; i < msg_list.size(); i++) {
			net_msg = msg_list.get(i);
			if (net_msg.cur_step >= MSGSTEP_FINISH)  continue;

			net_msg.cur_step = MSGSTEP_FINISH;
			if (net_msg.cancel_flag || net_msg.msg_send.msg_head.onlysend_flag != 0) continue;

			//组织任务失败结果
			comnt_msg = new ComntMsg.MSG_SERVER_STRUCT();
			ComntMsg.makeTaskResultComntMsg(comnt_msg, net_msg.msg_send, (byte)ComntDef.YD_TASKRESULT_FAILED, (byte)err_code, (byte)ComntDef.YD_TASKPROC_JWEBSERVICE, ComntDef.YD_TASKPROC_JWEBSERVICE_NAME);

			net_msg.msg_recvvect.add(comnt_msg);
		}
	}
	
	public synchronized static void checkMsg() {
		ComntMsgProc msgproc = ComntProc.getMsgProc();
		if (msgproc == null) return;
		
		LinkedList<ComntNetMsg> msg_list = msgproc.getMsgList();
		
		int cur_time = ComntTime.cTime();
		
		int i = 0;
		ComntNetMsg net_msg = null;

		int max_timeout = 0;
		
		for (i = msg_list.size() - 1; i >= 0; i--) {
			net_msg = msg_list.get(i);
			if (net_msg.cur_step == MSGSTEP_END) {
				msg_list.remove(i);
				continue;
			}

			max_timeout = Math.max(net_msg.msg_send.msg_head.wait_count, MSG_MAX_TIMEOUT);
			
			if (Math.abs(net_msg.time_cnt - cur_time) >= max_timeout) {
				if ((net_msg.cur_step <= MSGSTEP_RECVING) && (!net_msg.cancel_flag) && 
					(net_msg.msg_send.msg_head.onlysend_flag != 0)) {
					net_msg.cancel_flag = true;
					net_msg.time_cnt    = cur_time;
				}
				else {
					msg_list.remove(i);
				}
				continue;
			}
		}
	}
	
	//判断是否为终端状态事项
	public static boolean isSelfRtuState(ComntMsg.MSG_SERVER_STRUCT comnt_msg) {
		if (comnt_msg.msg_head.msg_type != ComntDef.YD_WEBMSGTYPE_RTUSTATE) return false;
		if (comnt_msg.msg_head.uuid != -1) return false;
		
		return true;
	}
	
	//设置为终端状态事项
	public static void setSelfRtuState(ComntMsg.MSG_SERVER_STRUCT comnt_msg) {
		if (comnt_msg.msg_head.msg_type != ComntDef.YD_WEBMSGTYPE_RTUSTATE) return;
		
		ComntMsg.MSG_RTUSTATE rtustate_msg = new ComntMsg.MSG_RTUSTATE();
		rtustate_msg.fromDataStream(comnt_msg.msg_body.byte_vect, 0);
		
		//OnlineRtu.setRtuState(rtustate_msg);
	}
	
	//判断是否为系统事项
	public static boolean isSelfSysEvent(ComntMsg.MSG_SERVER_STRUCT comnt_msg) {
		if (comnt_msg.msg_head.msg_type != ComntDef.YD_WEBMSGTYPE_SYSEVENT) return false;
		//if (comnt_msg.msg_head.uuid != -1) return false;
		
		return true;
	}
	
	//设置为系统事项
	public static void setSelfSysEvent(ComntMsg.MSG_SERVER_STRUCT comnt_msg) {
		if (comnt_msg.msg_head.msg_type != ComntDef.YD_WEBMSGTYPE_SYSEVENT) return;
		
		ComntMsg.MSG_SYSEVENT sysevent_msg = new ComntMsg.MSG_SYSEVENT();
		sysevent_msg.fromDataStream(comnt_msg.msg_body.byte_vect, 0);		
		
		//等待处理
		//RealSysEvent.addSysEvent(sysevent_msg);
	}
	
	//判断是否为开始充电推送事项
	public static boolean isSelfAppChargingPushState(ComntMsg.MSG_SERVER_STRUCT comnt_msg){
		if (comnt_msg.msg_head.msg_type != ComntDef.EVCP_WEBMSGTYPE_APP_TRADE_PUSH) return false;
		return true;
	}
	
	//根据返回信息，推送开始充电消息
	public static void setSelfAppChargingPushState(ComntMsg.MSG_SERVER_STRUCT comnt_msg){
		if (comnt_msg.msg_head.msg_type != ComntDef.EVCP_WEBMSGTYPE_APP_TRADE_PUSH) return;
		
		ComntMsg.MSG_RESULT_DATA result_data = new ComntMsg.MSG_RESULT_DATA();
		result_data.fromDataStream(comnt_msg.msg_body.byte_vect, 0);
		
		MSG_DATA_FN[] fn_vect = result_data.fn_vect;
		int num = fn_vect.length;
		if(num == 0)	return;
		int prot_i_id = 0;
		ComntVector.ByteVector data_vect = null;
		
		for(int i=0; i<num; i++){
			prot_i_id = fn_vect[i].prot_i_id;
			data_vect = fn_vect[i].data_vect;
			
			switch(prot_i_id){
				//充电开始
				case ComntCfgEVCP.PROT_I_ID_TRADE_CHARGE_BEGIN:
					ComntCfgEVCP.TRADE_CHARGE_BEGIN teade_charge_begin = new ComntCfgEVCP.TRADE_CHARGE_BEGIN();
					teade_charge_begin.fromDataStream(data_vect, 0);
					sendChargeStart(teade_charge_begin);
					break;
				//充电结束
				case ComntCfgEVCP.PROT_I_ID_TRADE_CHARGE_END:
					ComntCfgEVCP.TRADE_CHARGE_END treade_charge_end = new ComntCfgEVCP.TRADE_CHARGE_END(); 
					treade_charge_end.fromDataStream(data_vect, 0);
					sendChargeOver(treade_charge_end);
					break;
				case ComntCfgEVCP.TRADE_CHARGE_INFO_DC:
					ComntCfgEVCP.TRADE_CHARGE_INFO_DC trade_charge_info_dc = new ComntCfgEVCP.TRADE_CHARGE_INFO_DC(); 
					trade_charge_info_dc.fromDataStream(data_vect, 0);
					sendDCChargeInfo(trade_charge_info_dc);
					break;
				default:
					break;
			}	
		}
	}
	
	/** 
	* @Title: sendDCChargeInfo 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param trade_charge_info_dc    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private static void sendDCChargeInfo(TRADE_CHARGE_INFO_DC trade_charge_info_dc) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			// 解析推送直流首次充电信息数据
			String chargeSerialNumber = ComntFunc.byte2String(trade_charge_info_dc.m_serial);

			int remainSecond = ConverterUtil.toInt(trade_charge_info_dc.m_remain_tm);
			int SOC = ConverterUtil.toInt(trade_charge_info_dc.m_soc);

			String paySerialNumber = comntMsgProc.memberOrdersMapper.getPaySerialNumber(chargeSerialNumber);
			if (null == paySerialNumber) {
				return;
			}
			ChargeMonitor chargeMonitor = comntMsgProc.chargeMonitorMapper.getChargeMonitor(paySerialNumber);
			if (chargeMonitor.getSocPush() != null && chargeMonitor.getSocPush() == Constant.PUSHED) {
				return;
			}
 			
			// 验证流水号合法性
			boolean checkWasteNo = CommFunc.checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				return;
			}

			json.put("serialNumber", paySerialNumber);
			json.put("remainSecond", remainSecond);
			json.put("SOC", SOC);

			LoggerUtil.logger(LogName.CHARGE).info("接收直流首次充电信息(SOC),接收内容：" + json.toString());

			if (!comntMsgProc.chargeService.SendDCChargeInfoRequest(json, Constant.RETRY)) {
				LoggerUtil.logger(LogName.ERROR).error("发送直流首次充电信息(SOC)失败," + json.toString());

			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("发送直流首次充电信息(SOC)失败,有异常信息!" + json.toString(), e);
			e.printStackTrace();
			return;
		}
	}

	/** 
	* @Title: sendChargeOver 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param treade_charge_end    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private static void sendChargeOver(TRADE_CHARGE_END treade_charge_end) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			// 解析推送充电记录中的数据
			String chargeSerialNumber = BytesUtil.getString(treade_charge_end.m_charge.trade_no);
			String paySerialNumber = comntMsgProc.memberOrdersMapper.getPaySerialNumber(chargeSerialNumber);
			if (null == paySerialNumber) {
				return;
			}
			MemberOrders order = comntMsgProc.memberOrdersMapper.getmemberOrders(paySerialNumber);
			String pileNo = order.getPileCode();
			if (null == pileNo || pileNo.isEmpty()) {
				return;
			}
			int gunNo = treade_charge_end.m_charge.gun + 1;

			// 验证流水号合法性
			boolean checkWasteNo = CommFunc.checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				return;
			}

			double totalElectricity = ConverterUtil.toDouble(treade_charge_end.m_charge.end_bd)
					- ConverterUtil.toDouble(treade_charge_end.m_charge.begin_bd);
			double chargeMoney = treade_charge_end.m_charge.balance1 + treade_charge_end.m_charge.charge
					- treade_charge_end.m_charge.balance2;
			double serviceMoney = treade_charge_end.m_charge.parking_fee;

			StringBuffer startDate = new StringBuffer();
			startDate.append(DateUtil.formatToYMD(treade_charge_end.m_charge.begin_date, "day")).append(" ");
			startDate.append(DateUtil.intToTime(treade_charge_end.m_charge.begin_time, 1));

			StringBuffer endDate = new StringBuffer();
			endDate.append(DateUtil.formatToYMD(treade_charge_end.m_charge.end_date, "day")).append(" ");
			endDate.append(DateUtil.intToTime(treade_charge_end.m_charge.end_time, 1));
			int m_cause = treade_charge_end.m_cause;

			Map<String, String> endCauseMap = JedisUtil.hgetAll(Constant.ENDCAUSE_DICTION);
			String endCause = endCauseMap.get(m_cause);
			if (null == endCause || endCause.equals("")) {
				endCause = "未知";
			}

			json.put("serialNumber", paySerialNumber);
			json.put("pileNo", pileNo);
			json.put("gunNo", gunNo);
			json.put("startDate", startDate.toString());
			json.put("endDate", endDate.toString());

			json.put("totalElectricity", ConverterUtil.roundTosString(totalElectricity, 2));
			json.put("chargeMoney", ConverterUtil.roundTosString(chargeMoney, 2));
			json.put("serviceMoney", ConverterUtil.roundTosString(serviceMoney, 2));
			json.put("endCause", endCause);

			LoggerUtil.logger(LogName.CHARGE).info("接收充电结束消息,接收内容:{}", json.toString());

			// 更新接口充电记录中请求结束充电
			ChargeMonitor chargeMonitor = new ChargeMonitor();
			chargeMonitor.setSerialnumber(paySerialNumber);
			chargeMonitor.setEndReceiveTime(new Date());
			chargeMonitor.setEndPush((byte) 0);
			comntMsgProc.chargeMonitorMapper.updateChargeMonitor(chargeMonitor);

			if (!comntMsgProc.chargeService.SendChargeOverRequest(json, Constant.RETRY)) {
				LoggerUtil.logger(LogName.ERROR).error("充电结束推送消息发送失败," + json);
			}

		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("充电结束推送消息发送失败,有异常信息!" + treade_charge_end, e);
			e.printStackTrace();
			return;
		}
	}
	

	/** 
	* @Title: sendChargeStart 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param teade_charge_begin    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private static void sendChargeStart(TRADE_CHARGE_BEGIN teade_charge_begin) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			// 解析推送充电记录中的数据
			String chargeSerialNumber = ComntFunc.byte2String(teade_charge_begin.m_serial);
			int result = ConverterUtil.toInt(teade_charge_begin.m_flag);

			String paySerialNumber = comntMsgProc.memberOrdersMapper.getPaySerialNumber(chargeSerialNumber);
			if (null == paySerialNumber) {
				return;
			}

			// 验证流水号合法性
			boolean checkWasteNo = CommFunc.checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				return;
			}

			json.put("serialNumber", paySerialNumber);
			json.put("chargeFlag", (1 - result));

			LoggerUtil.logger(LogName.CHARGE).info("接收充电开始消息,接收内容：" + json.toString());

			// 更新接口充电记录中请求结束充电
			ChargeMonitor chargeMonitor = new ChargeMonitor();
			chargeMonitor.setSerialnumber(paySerialNumber);
			chargeMonitor.setStartReceiveTime(new Date());
			chargeMonitor.setStartPush((byte) 0);
			chargeMonitor.setStartFlag((byte) (1 - result));

			comntMsgProc.chargeMonitorMapper.updateChargeMonitor(chargeMonitor);

			if (!comntMsgProc.chargeService.SendChargeStartRequest(json, Constant.RETRY)) {
				LoggerUtil.logger(LogName.ERROR).error("充电开始推送消息发送失败,!" + json.toString());
			}
		} catch (Exception e) {
			LoggerUtil.logger(LogName.ERROR).error("充电开始推送消息发送失败,有异常信息!" + json.toString(), e);
			e.printStackTrace();
			return;
		}
	}

	public static void waitMsg() {
		try {
			Thread.sleep(500);
		}catch(InterruptedException e){
			e.printStackTrace(System.err);
		}
	}
	
	
	 
}
