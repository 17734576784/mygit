package com.wo.comnt;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ComntParaBase {

	/**
	 * 这里不需要该类，但为保持底层不变，只定义，不使用
	 * */
	public static class RTU_PARA {
		public int   rtu_id       = 0;
		public byte  prot_type    = 0;
		public short rtu_model	  = 0;
		public short timeout      = 0;
		public short def_timeout  = 0;
		public short min_timeout  = 0;
		public short max_timeout  = 0;
	}
	
	public static class OpDetailInfo {
		public final static int TYPE_TASK = 0;	//任务执行信息
		public final static int TYPE_RX	  = 1;	//接收的报文
		public final static int TYPE_TX	  = 2;	//发送的报文
		public final static int TYPE_RXJX = 3;	//接收报文的解析
		public final static int TYPE_TXJX = 4;	//发送报文的解析
		public final static int TYPE_DB   = 5;	//数据库信息
		public final static int TYPE_USER = 6;	//用户信息
		public final static int TYPE_OTHER= 7;	//其他信息
				
		public static class ITEM {
			public int    type = 0;
			public int    time = 0;
			public int    data_len = 0;
			public String data = null;
		}
		
		public ArrayList<ITEM> item_list = new ArrayList<ITEM>();
		
		public void addTaskInfo(String str) {
			ITEM item = new ITEM();
			item.type = TYPE_TASK;
			item.time = ComntTime.cTime();
			item.data_len = 0;
			item.data = str;
			item_list.add(item);
		}
		
		public void addRxInfo(int data_time, byte[] datas) {
			ITEM item = new ITEM();
			item.type = TYPE_RX;
			item.time = data_time;
			item.data_len = datas.length;
			item.data = ComntFunc.printProtalData(datas);
			item_list.add(item);
		}
		
		public void addTxInfo(int data_time, byte[] datas) {
			ITEM item = new ITEM();
			item.type = TYPE_TX;
			item.time = data_time;
			item.data_len = datas.length;
			item.data = ComntFunc.printProtalData(datas);
			item_list.add(item);
		}
		
		public void addRxJxInfo(int data_time, String str) {
			ITEM item = new ITEM();
			item.type = TYPE_RXJX;
			item.time = data_time;
			item.data_len = 0;
			item.data = str;
			item_list.add(item);
		}		
		
		public void addTxJxInfo(int data_time, String str) {
			ITEM item = new ITEM();
			item.type = TYPE_TXJX;
			item.time = data_time;
			item.data_len = 0;
			item.data = str;
			item_list.add(item);
		}
		
		public void addDbInfo(String str) {
			ITEM item = new ITEM();
			item.type = TYPE_DB;
			item.time = ComntTime.cTime();
			item.data_len = 0;
			item.data = str;
			item_list.add(item);
		}
		
		public void addInfo(String str) {
			ITEM item = new ITEM();
			item.type = TYPE_USER;
			item.time = ComntTime.cTime();
			item.data_len = 0;
			item.data = str;
			item_list.add(item);
		}
		
		public void addOtherInfo(String str) {
			ITEM item = new ITEM();
			item.type = TYPE_OTHER;
			item.time = ComntTime.cTime();
			item.data_len = 0;
			item.data = str;
			item_list.add(item);
		}
		
		public String toJsonString() {
			JSONArray j_obj_array = new JSONArray();			
			JSONObject j_obj = null;

			ITEM item = null;
			
			for (int i = 0; i < item_list.size(); i++) {
				item = item_list.get(i);
				
				j_obj = new JSONObject();
				
				j_obj.put("TYPE",    String.valueOf(item.type));
				
				ComntTime.TM data_time = ComntTime.localCTime(item.time);				
				String str_time = String.format("%02d/%02d %02d:%02d:%02d", 
												(int)data_time.month + 1, (int)data_time.mday, 
												(int)data_time.hour, (int)data_time.minute, (int)data_time.secend);

				j_obj.put("TIME",    str_time);
				j_obj.put("DATALEN", String.valueOf(item.data_len));
				j_obj.put("DATA",    item.data);

				j_obj_array.add(j_obj);				
			}

			j_obj = new JSONObject();
			j_obj.put("OPDETAIL", j_obj_array);

			return j_obj.toString();
		}
	}
	
	public static int getRtuTimeOut(RTU_PARA rtu_para, int retry_num) {
		int tmp_val = 120;
		if (rtu_para == null) return  (retry_num + 1) * tmp_val;

		tmp_val = rtu_para.timeout;
		if (tmp_val == 0) tmp_val = rtu_para.def_timeout; 
		if (tmp_val < rtu_para.min_timeout) tmp_val = rtu_para.min_timeout;
		if (tmp_val > rtu_para.max_timeout) tmp_val = rtu_para.max_timeout;
		
		return (retry_num + 1) * tmp_val + 6;
	}
	
	/**
	 * 此处不做界面展示，暂时保留
	 * */
	public static String makeErrCode(ComntMsg.MSG_TASKRESULT task_result) {
		String code_str = ComntDef.getErrCodeString((int)task_result.errcode);
		String proc_str = ComntDef.getErrProcString((int)task_result.errproc);
		String host_str = ComntFunc.byte2String(task_result.errhost);
		
		return "" + "  描述:[" + code_str + "]  进程:[" + proc_str + "]  节点:[" + host_str + "]";
	}
	
	public static boolean get1StepTaskResult(String user_name, 
											 int userData1, 
											 int userData2, 
											 RTU_PARA rtu_para,
											 byte task_apptype, 
											 byte task_assigntype, 
											 ComntVector.ByteVector task_data_vect,  
											 byte[] task_result, 
											 ArrayList<ComntCfgEVCP.APP_CHARGE_WITH_PAY_RESULT> ret_data_vect, 
											 OpDetailInfo detail_info,
											 StringBuffer opDetail) {

		return get1StepTaskResult_Retry(user_name, 
										userData1, 
										userData2, 
										rtu_para, 
										task_apptype, 
										task_assigntype, 
										task_data_vect, 
										0,
										task_result, 
										ret_data_vect, 
										detail_info,
										opDetail);
	}

	public static boolean get1StepTaskResult_Retry(String user_name, 
												   int userData1, 
												   int userData2, 
												   RTU_PARA rtu_para,
												   byte task_apptype, 
												   byte task_assigntype, 
												   ComntVector.ByteVector task_data_vect, 
												   int retry_times,  
												   byte[] task_result, 
												   ArrayList<ComntCfgEVCP.APP_CHARGE_WITH_PAY_RESULT> ret_data_vect, 
												   OpDetailInfo detail_info,
												   StringBuffer opDetail) 
	{

		task_result[0] = (byte)ComntDef.YD_TASKRESULT_NULL;

		int uuid     = ComntFunc.getNewUUID();
		int time_out = ComntParaBase.getRtuTimeOut(rtu_para, 0);

		ComntMsg.MSG_CLIENT_STRUCT comnt_msg = new ComntMsg.MSG_CLIENT_STRUCT();
		ComntMsg.makeTaskComntMsg(comnt_msg,								//要发送的消息 
								 uuid, 										//消息的UUID
								 ComntDef.FALSE, 							//是否不接受任务结果
								 time_out, 									//等待超时
								 user_name, 								//操作的用户名称,用于记录日志
								 (byte)task_apptype, 						//任务的应用类型-设置参数的任务
								 (byte)task_assigntype, 					//将被分配到前置的手工任务区
								 rtu_para.rtu_id, 							//终端ID
								 (byte)rtu_para.prot_type, 					//终端的规约类型
					 			 (byte)retry_times, 						//重试次数,0-不重试
								 (byte)ComntDef.TRUE, 						//是否返回任务结果
								 (byte)ComntDef.YD_TASK_BACKDATA_SRC, 		//返回数据方向,返回给发送端即WEB客户端
								 (byte)ComntDef.TRUE, 						//是否返回规约报文
								 (byte)ComntDef.TRUE, 						//规约报文是否解析
								 task_data_vect);							//任务结构转化成的字节流

		int i_user_data1 = userData1;	//rtu_id
		int i_user_data2 = userData2;	//rtuPara.prot_type
		
		//将任务放入发送队列
		boolean add_val = ComntMsgProc.addWaitSendMsg(comnt_msg.msg_head, comnt_msg.msg_body, i_user_data1, i_user_data2);
		if (!add_val) {
			detail_info.addTaskInfo("ERR:放入发送队列发生错误");
			opDetail.setLength(0);
			opDetail.append("放入发送队列发生错误");
			return false;
		}

		ArrayList<ComntMsg.MSG_SERVER_STRUCT> retmsg_vect = new ArrayList<ComntMsg.MSG_SERVER_STRUCT>();
		int msg_step = ComntMsgProc.MSGSTEP_END;

		//等待返回结果
		int ferr_count = 0;
		do {
			ComntMsgProc.waitMsg();			
			msg_step = ComntMsgProc.getRecvMsg(comnt_msg.msg_head.uuid, 
					   true, 		//是否一次接受所有返回数据
					   retmsg_vect);

			if (++ferr_count >= 1200) break;
		} while (msg_step < ComntMsgProc.MSGSTEP_FINISH);

		if (++ferr_count >= 1200) {					//1h 改为十分钟 7200 ->1200
			detail_info.addTaskInfo("ERR:等待超时");			
			opDetail.setLength(0);
			opDetail.append("等待超时");
			return false;
		}

		int tmp_i = 0;
		ComntMsg.MSG_SERVER_STRUCT ret_msg = null;

		//判断任务结果
		for (tmp_i = 0; tmp_i < retmsg_vect.size(); tmp_i++) {
			ret_msg = retmsg_vect.get(tmp_i);
			if (ret_msg.msg_head.msg_type == ComntDef.YD_WEBMSGTYPE_TASKRESULT) break;
		}
		
		if (tmp_i >= retmsg_vect.size()) {	//无任务结果 退出
			detail_info.addTaskInfo("ERR:无任务执行结果");
			opDetail.setLength(0);
			opDetail.append("无任务执行结果");
			return false;
		}
		
		ComntMsg.MSG_TASKRESULT result_msg = new ComntMsg.MSG_TASKRESULT();
		result_msg.fromDataStream(ret_msg.msg_body.byte_vect, 0);
		
		/*task_result[0] = result_msg.taskresult;
		
		//任务执行失败
		if (result_msg.taskresult != ComntDef.YD_TASKRESULT_SUCCEED) {		//执行失败 退出
			detail_info.addTaskInfo("ERR:任务执行失败." + makeErrCode(result_msg));			
			return false;
		}*/
		
		///
		byte sec_flag = ComntDef.YD_TASKRESULT_SUCCEED;
		//任务执行失败
		if (result_msg.taskresult != ComntDef.YD_TASKRESULT_SUCCEED) {		//执行失败 退出
			sec_flag = ComntDef.YD_TASKRESULT_FAILED;
		}
		
		if (result_msg.taskresult == ComntDef.YD_TASKRESULT_SUCCEED) {
			if(result_msg.errcode == 0) {		//执行成功
				sec_flag  = ComntDef.YD_TASKRESULT_SUCCEED;
			}
			else {
				sec_flag  = ComntDef.YD_TASKRESULT_FAILED;
			}
		}
		
		task_result[0] = (sec_flag == ComntDef.YD_TASKRESULT_SUCCEED) ? (byte)ComntDef.YD_TASKRESULT_SUCCEED : (byte)ComntDef.YD_TASKRESULT_FAILED;
		
		if(sec_flag == ComntDef.YD_TASKRESULT_FAILED){
			String err_str = "ERR:任务执行失败." + makeErrCode(result_msg);
			System.out.println(err_str);
			detail_info.addTaskInfo(err_str);
			opDetail.setLength(0);
			opDetail.append(ComntDef.getErrCodeString(result_msg.errcode) + "[" + result_msg.errcode +"]");
			return false;
		}
		
		return true;
	}
	
}
