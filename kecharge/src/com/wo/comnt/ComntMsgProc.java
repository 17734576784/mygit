package com.wo.comnt;

import static com.wo.util.CommFunc.objToInt;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wo.comnt.ComntMsg.MSG_DATA_FN;
import com.wo.comnt.ComntMsg.MSG_SYSEVENT_ITEM;
import com.wo.mapper.ChargeRecordMapper;
import com.wo.mapper.YysCarownerOrderMapper;
import com.wo.model.ChargeRecord;
import com.wo.service.IChargeService;
import com.wo.util.CommFunc;
import com.wo.util.Constant;
import com.wo.util.JDBCDao;
import com.wo.util.Log4jUtil;
import com.wo.util.OnlineRtu;

import net.sf.json.JSONObject;

@Component
public class ComntMsgProc {
	public static final int MSG_MAX_TIMEOUT = 300; // 等待超时5min
	public static final int MSG_DEFAULT_CLIENT_WAIT_NUM = 610; // 客户端默认等待时间单位数量500ms * 610
	public static final int MAX_MSGBUF_NUM = 1024; // 最大消息缓存

	//消息状态
	public static final int MSGSTEP_WAITSEND 	= 0;		//等待发送
	public static final int MSGSTEP_RECVING	 	= 1;		//正在接收
	public static final int MSGSTEP_FINISH 	 	= 2;		//完成--数据接收完毕
	public static final int MSGSTEP_END	 	 	= 3;		//结束--删除消息

	@Autowired
	private JDBCDao jdbcDao;
	public void setJdbcDao(JDBCDao jdbcDao) {
		this.jdbcDao = jdbcDao;
	}
	
	@Autowired
	private IChargeService chargeService; 
	public void setChargeService(IChargeService chargeService) {
		this.chargeService = chargeService;
	}
	
	@Autowired
	private ChargeRecordMapper chargeRecordMapper;
	public void setChargeRecordMapper(ChargeRecordMapper chargeRecordMapper) {
		this.chargeRecordMapper = chargeRecordMapper;
	}

	@Autowired
	private YysCarownerOrderMapper yysCarownerOrderMapper;

	public static void setComntMsgProc(ComntMsgProc comntMsgProc) {
		ComntMsgProc.comntMsgProc = comntMsgProc;
	}

	private static ComntMsgProc comntMsgProc;
	
	@PostConstruct
	public void init(){
		comntMsgProc = this;
		comntMsgProc.jdbcDao = this.jdbcDao;
		comntMsgProc.chargeService = this.chargeService;
		comntMsgProc.chargeRecordMapper = this.chargeRecordMapper;
		comntMsgProc.yysCarownerOrderMapper = this.yysCarownerOrderMapper;
	}
		
	
	public void setMsg_list(LinkedList<ComntNetMsg> msgList) {
		msg_list = msgList;
	}

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
		OnlineRtu.setRtuState(rtustate_msg);
	}
	
	//判断是否为系统事项
	public static boolean isSelfSysEvent(ComntMsg.MSG_SERVER_STRUCT comnt_msg) {
		if ((comnt_msg.msg_head.msg_type != ComntDef.YD_WEBMSGTYPE_SYSEVENT) && (comnt_msg.msg_head.msg_type != ComntDef.EVCP_WEBMSGTYPE_APP_EVENT_PUSH)) return false;
		//if (comnt_msg.msg_head.uuid != -1) return false;
		
		return true;
	}
	
	//设置为系统事项
	public static void setSelfSysEvent(ComntMsg.MSG_SERVER_STRUCT comnt_msg) {
		if ((comnt_msg.msg_head.msg_type != ComntDef.YD_WEBMSGTYPE_SYSEVENT)  && (comnt_msg.msg_head.msg_type != ComntDef.EVCP_WEBMSGTYPE_APP_EVENT_PUSH)) return;
		
		ComntMsg.MSG_SYSEVENT sysevent_msg = new ComntMsg.MSG_SYSEVENT();
		sysevent_msg.fromDataStream(comnt_msg.msg_body.byte_vect, 0);		
		
		//发送报警事项
		sendAlramInfo(sysevent_msg);
	}
	/**
	 * 发送报警事项
	 * @param sysevent_msg  报警事项队列
	 * */
	public static void sendAlramInfo(ComntMsg.MSG_SYSEVENT sysevent_msg){
//		try {
//			
//			JDBCDao j_dao = comntMsgProc.jdbcDao;
//			SendAlarmInfoRequest sendAlarmInfoRequest = (SendAlarmInfoRequest)SpringUtil.getApplicationContext().getBean("sendAlarmInfoRequest");
//						
//	 		//解析推送报警记录中的数据
//			for(int i = 0; i < sysevent_msg.sysevent_vect.length; i++){
//				
//				MSG_SYSEVENT_ITEM  sysevent_item = sysevent_msg.sysevent_vect[i];
//				
//				String classNo = CommFunc.objToStr(sysevent_item.classno);
//				// 3: 终端事件  5: 电表事件  7: 电压事件 8: 电流事件
//				if(!(classNo.equals("3") || classNo.equals("5") || classNo.equals("7") || classNo.equals("8"))){
//					continue;
//				}
//				String typeNo = CommFunc.objToStr(sysevent_item.typeno);
//				
//				StringBuffer alarmDate = new StringBuffer(); 
//				alarmDate.append(CommFunc.FormatToYMD(sysevent_item.ymd,"day")).append(" ");
//				alarmDate.append(CommFunc.intToTime(sysevent_item.hmsms/1000,1));
//				
//				String alarmMess = queryAlarmInfoByClassNoAndTypeNo(classNo,typeNo);
//				String char_info = ComntFunc.byte2String(sysevent_item.char_info);
//
//				String pileNo = "";
//				String gunNo = "";
//				
//				int	member_id0 = CommFunc.objectToInt(sysevent_item.member_id0);;				// 终端Id
//				int	member_id1 = CommFunc.objectToInt(sysevent_item.member_id1);;				// 充电枪Id
//				
//				//通过rtuId获取充电桩编号
//				StringBuffer sql = new StringBuffer();
//				sql.append("select pile_code from cppara.pilepara where rtu_id = ").append(member_id0);
//				Map<String, Object> map = j_dao.queryOne(sql.toString());
//				if(map != null){
//					pileNo =  CommFunc.objToStr(map.get("pile_code"));
//				}
//				
//				//充电枪报警
//				if(member_id1 == 0 || member_id1 == 1){
//					gunNo = String.valueOf(member_id1).replace("0", "A").replace("1", "B");
//				}
//			 
//				JSONObject json = new JSONObject();
//				json.put("pileNumber", pileNo);
//				json.put("gunNumber",  gunNo);
//				json.put("alarmDate", alarmDate.toString());
//				json.put("alarmMess", alarmMess);
//				json.put("alarmValue", char_info);
//
//				
//				Log4jUtil.getLogger().logger.info("报警推送消息 ，发送内容" + json.toString());
//
//		  		//发送报警请求
//		  		if(sendAlarmInfoRequest.sendAlarmInfoRequest(json)){
//		  			updateEveSyncFlag(sysevent_item);
//		  		}
//		  		else{
//		  			//发送失败 重发一次 再失败的话 轮询处理
//		  			if(sendAlarmInfoRequest.sendAlarmInfoRequest(json)){
//		  	  			updateEveSyncFlag(sysevent_item);
//		  	  		}
//		  		}
//			}
//			
//		} catch (Exception e) {
//			Log4jUtil.getLogger().logger.error("报警推送消息发送失败,有异常信息!");
//		}
	}
	
	/**
	 * 根据事项大小类型 查询报警描述
	 * */
	public static String queryAlarmInfoByClassNoAndTypeNo(String classNo,String typeNo){
		JDBCDao j_dao = comntMsgProc.jdbcDao;
		if(classNo == null || classNo.isEmpty() || typeNo == null || typeNo.isEmpty()) return "";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT  describ from cppara.eventtype where classno = ").append(classNo);
		sql.append(" and typeno = ").append(typeNo);
		Map<String, Object> map = j_dao.queryOne(sql.toString());
		if(map == null) return "";
		return CommFunc.objToStr(map.get("describ"));
	}
	
	/***
	 * 更新事项表中同步标志字段
	 * @param wastno
	 * */
	public static void updateEveSyncFlag(MSG_SYSEVENT_ITEM msg_sysevent_item) {
		
		JDBCDao j_dao = comntMsgProc.jdbcDao;
		int ymd = msg_sysevent_item.ymd;
		int hmsms = msg_sysevent_item.hmsms;
		short typeno = msg_sysevent_item.typeno;
		int member_id0 = msg_sysevent_item.member_id0;
		int member_id1 = msg_sysevent_item.member_id1;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update ").append("cpdata.eve").append(ymd / 100);
		sql.append(" set sync_flag = 1 where ymd= ");
		sql.append(ymd).append(" and hmsms = ").append(hmsms);
		sql.append(" and typeno = ").append(typeno);
		sql.append(" and member_id0 = ").append(member_id0);
		sql.append(" and member_id1 = ").append(member_id1);
		
		j_dao.executeSql(sql.toString());
	}
	
	
	//判断是否为开始充电推送事项
	public static boolean isSelfAppChargingPushState(ComntMsg.MSG_SERVER_STRUCT comnt_msg){
		if (comnt_msg.msg_head.msg_type != ComntDef.EVCP_WEBMSGTYPE_APP_TRADE_PUSH) return false;
		return true;
	}
	
	//根据返回信息，推送开始充电消息
	public static void setSelfAppChargingPushState(ComntMsg.MSG_SERVER_STRUCT comnt_msg){
		if (comnt_msg.msg_head.msg_type != ComntDef.EVCP_WEBMSGTYPE_APP_TRADE_PUSH) {
			return;
		}
		
		ComntMsg.MSG_RESULT_DATA result_data = new ComntMsg.MSG_RESULT_DATA();
		result_data.fromDataStream(comnt_msg.msg_body.byte_vect, 0);
		
		MSG_DATA_FN[] fn_vect = result_data.fn_vect;
		int num = fn_vect.length;
		if (num == 0){
			return;
		}

		int prot_i_id = 0;
		ComntVector.ByteVector data_vect = null;
		
		for (int i = 0; i < num; i++) {
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
					ComntCfgEVCP.TRADE_CHARGE_END teade_charge_end = new ComntCfgEVCP.TRADE_CHARGE_END(); 
					teade_charge_end.fromDataStream(data_vect, 0);
					sendChargeOver(teade_charge_end);
					break;
					//充电结束
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
	
	public static void sendDCChargeInfo(ComntCfgEVCP.TRADE_CHARGE_INFO_DC trade_charge_info_dc){
		JSONObject json = new JSONObject();
		try {
	 		//解析推送直流首次充电信息数据
			String chargeSerialNumber = ComntFunc.byte2String(trade_charge_info_dc.m_serial);
			
			int remainSecond = objToInt(trade_charge_info_dc.m_remain_tm);
			int SOC = objToInt(trade_charge_info_dc.m_soc);
			
			String paySerialNumber = comntMsgProc.yysCarownerOrderMapper.getPaySerialNumber(chargeSerialNumber);
			if (null == paySerialNumber || paySerialNumber.isEmpty()) {
				return;
			}
			
			ChargeRecord chargeRecord = comntMsgProc.chargeRecordMapper.getChargeRecord(paySerialNumber);
			if (chargeRecord == null) {
				return;
			}
			
			if (chargeRecord.getSocPush() != null && chargeRecord.getSocPush() == Constant.PUSHED) {
				return;
			}
			
			//验证流水号合法性
			boolean checkWasteNo= CommFunc.checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				return;
			}
	  		 
			json.put("serialNumber", paySerialNumber);
			json.put("remainSecond", remainSecond);
			json.put("SOC", SOC);
	  	
			Log4jUtil.getChargeinfo().info("接收直流首次充电信息(SOC),接收内容："+json.toString());
	  			
			if (!comntMsgProc.chargeService.SendDCChargInfoRequest(json,Constant.RETRY)) {
				Log4jUtil.getError().error("发送直流首次充电信息(SOC)失败,"+json.toString());

			}
		} catch (Exception e) {
			Log4jUtil.getError().error("发送直流首次充电信息(SOC)失败,有异常信息!"+json.toString(), e);
			e.printStackTrace();
			return;
		}
	}
	
	public static void sendChargeStart(ComntCfgEVCP.TRADE_CHARGE_BEGIN teade_charge_begin){
		JSONObject json = new JSONObject();
		try {
	 		//解析推送充电记录中的数据
			String chargeSerialNumber = ComntFunc.byte2String(teade_charge_begin.m_serial);
			int result = CommFunc.objToInt(teade_charge_begin.m_flag);
			
			String paySerialNumber = comntMsgProc.yysCarownerOrderMapper.getPaySerialNumber(chargeSerialNumber);
			if (null == paySerialNumber || paySerialNumber.isEmpty()) {
				return;
			}
			
			//验证流水号合法性
			boolean checkWasteNo= CommFunc.checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				return;
			}
	  		 
	  		json.put("serialNumber",paySerialNumber);
	  		json.put("chargeFlag", 	(1-result));
	  	
			Log4jUtil.getChargeinfo().info("接收充电开始消息,接收内容："+json.toString());

			//更新接口充电记录中请求结束充电
			ChargeRecord chargeRecord = new ChargeRecord();
			chargeRecord.setSerialnumber(paySerialNumber);
  			chargeRecord.setStartReceiveTime(new Date());
  			chargeRecord.setStartPush((byte)0);
  			chargeRecord.setStartFlag((byte)(1-result));
  			
  			comntMsgProc.chargeRecordMapper.updateChargeRecord(chargeRecord);
 			
			if (!comntMsgProc.chargeService.SendChargStartRequest(json,Constant.RETRY)) {
				Log4jUtil.getError().error("充电开始推送消息发送失败,!"+json.toString());
			}
		} catch (Exception e) {
			Log4jUtil.getError().error("充电开始推送消息发送失败,有异常信息!"+json.toString(), e);
			e.printStackTrace();
			return;
		}
	}
	
	public static void sendChargeOver(ComntCfgEVCP.TRADE_CHARGE_END trade_charge_end){

		JSONObject json = new JSONObject();
		try {
	 		//解析推送充电记录中的数据
			String chargeSerialNumber = ComntFunc.byte2String(trade_charge_end.m_charge.trade_no);
			int ymd = CommFunc.objToInt(trade_charge_end.m_charge.end_date);
	  		String pileNo  = queryPileNoByWasteno(chargeSerialNumber,ymd);
			if (null == pileNo || pileNo.isEmpty()) {
				return;
			}
	  		int gunNo = trade_charge_end.m_charge.gun + 1;
	  		
	  		String paySerialNumber = comntMsgProc.yysCarownerOrderMapper.getPaySerialNumber(chargeSerialNumber);
			if (null == paySerialNumber || paySerialNumber.isEmpty()) {
				return;
			}
	  		//验证流水号合法性
			boolean checkWasteNo = CommFunc.checkWasteno(paySerialNumber);
			if (!checkWasteNo) {
				return;
			}
	  		
	  		double totalElectricity = CommFunc.objToDbl(trade_charge_end.m_charge.end_bd) - CommFunc.objToDbl(trade_charge_end.m_charge.begin_bd);
	  		double chargeMoney   = trade_charge_end.m_charge.balance1 + trade_charge_end.m_charge.charge - trade_charge_end.m_charge.balance2;
			double serviceMoney = trade_charge_end.m_charge.parking_fee;
	  		
	  		StringBuffer startDate = new StringBuffer(); 
	  		startDate.append(CommFunc.FormatToYMD(trade_charge_end.m_charge.begin_date,"day")).append(" ");
	  		startDate.append(CommFunc.intToTime(trade_charge_end.m_charge.begin_time,1));
	  		
	  		StringBuffer endDate = new StringBuffer();
	  		endDate.append(CommFunc.FormatToYMD(trade_charge_end.m_charge.end_date,"day")).append(" ");
	  		endDate.append(CommFunc.intToTime(trade_charge_end.m_charge.end_time,1));
			int m_cause = trade_charge_end.m_cause;
			
			String endCause = comntMsgProc.chargeRecordMapper.getEndCause(m_cause);
			if (null == endCause || endCause.equals("")) {
				endCause = "未知";
			}
	  		
			json.put("serialNumber", paySerialNumber);
			json.put("pileNo", pileNo);
			json.put("gunNo", gunNo);
			json.put("startDate", startDate.toString());
			json.put("endDate", endDate.toString());
	  		
			json.put("totalElectricity", CommFunc.round(totalElectricity, 2));
			json.put("chargeMoney", CommFunc.round(chargeMoney, 2));
			json.put("serviceMoney", CommFunc.round(serviceMoney, 2));
			json.put("endCause", endCause);
	  		
			Log4jUtil.getChargeinfo().info("接收充电结束消息,接收内容："+json.toString());

			//更新接口充电记录中请求结束充电
			ChargeRecord chargeRecord = new ChargeRecord();
			chargeRecord.setSerialnumber(paySerialNumber);
  			chargeRecord.setEndReceiveTime(new Date());
  			chargeRecord.setEndPush((byte)0);
  			comntMsgProc.chargeRecordMapper.updateChargeRecord(chargeRecord);
  			
  			if (!comntMsgProc.chargeService.SendChargOverRequest(json,Constant.RETRY)) {
  				Log4jUtil.getError().error("充电结束推送消息发送失败," + json);
			}  			
  			
		} catch (Exception e) {
			Log4jUtil.getError().error("充电结束推送消息发送失败,有异常信息!" + trade_charge_end, e);
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * 根据交易流水号查询充电桩编号
	 * */
	public static String queryPileNoByWasteno(String wasteno,int ymd){
		JDBCDao j_dao = comntMsgProc.jdbcDao;
		if (wasteno == null || wasteno.isEmpty())
			return "";
		
		StringBuffer sql = new StringBuffer();
		sql.append("select pile_code from cpdata.chargercd").append(ymd/100)
		.append(" where wasteno = '").append(wasteno).append("'");
		
		Map<String, Object> map = j_dao.queryOne(sql.toString());
		if (map == null)
			return "";
		
		return CommFunc.objToStr(map.get("pile_code"));
	}

	public static void waitMsg() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace(System.err);
		}
	}
	
	public static void sendReloadTableMsg(String user_name, byte[] reload_mask) {
		ComntMsg.MSG_CLIENT_STRUCT comnt_msg = new ComntMsg.MSG_CLIENT_STRUCT();
		int uuid = ComntFunc.getNewUUID();
		ComntMsg.makeReloadParaComntMsg(comnt_msg, uuid, user_name, reload_mask);
		addWaitSendMsg(comnt_msg.msg_head, comnt_msg.msg_body, 0, 0);
	}

}
