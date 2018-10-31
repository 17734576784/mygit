package com.wo.comnt;

import java.util.ArrayList;

import com.wo.util.CommFunc;

public class ComntProc implements Runnable{
	ComntCfg     comnt_cfg     = null;
	ComntSocket  comnt_socket  = null;
	ComntMsgProc comnt_msgproc = null;

	int last_connect_ctime   = 0;
	int sock_err_count		 = 0;
	
	public ComntProc(){
		comnt_cfg     = new ComntCfg();
		comnt_socket  = new ComntSocket();
		comnt_msgproc = new ComntMsgProc();
		comnt_cfg.loadComntCfg();
	}

	public static final int SOCKET_CONNECT_INTER	= 5;	//socket重新连接间隔5s
	
	private boolean connectSocket() {
		if (comnt_socket == null) return false;
		
		int cur_ctime = ComntTime.cTime();
		if (!comnt_socket.isSocketValid()) {			
			if (Math.abs(cur_ctime - last_connect_ctime) < SOCKET_CONNECT_INTER) return false;			
			boolean cnn_val = comnt_socket.connect(comnt_cfg.webservice_ip, comnt_cfg.webservice_port);
			
//			System.out.println("通讯线程:SOCKET 连接" + "IP:" + comnt_cfg.webservice_ip  + "/" + comnt_cfg.webservice_port + (cnn_val ? "成功" : "失败") + ".");
			
			last_connect_ctime = cur_ctime;
			
			if (cnn_val == false) {
				sock_err_count++;
				return false;			
			}
		}
		
		sock_err_count = 0;
		last_connect_ctime = cur_ctime;
		
		return true;
	}
	
	public static final int SOCKET_DATA_MAXLEN		= (1024 * 1024 * 1024);	//读写的SOCKET最大数据长度 1M
	
	private int readProc() {
		if (comnt_socket.getErrFlag()) return -1;
		
		int sel_val = comnt_socket.select(10);		//10ms
		if (sel_val < 0) {							//socket err
			comnt_socket.setErrFlag(true);
			return -1;
		}
		else if (sel_val == 0) {					//no data
			return 0;
		}

		ComntVector.ByteVector byte_vect = new ComntVector.ByteVector();
		
		byte_vect.resize(ComntMsg.MSG_SERVER_HEAD.size());
		int read_val = comnt_socket.read(byte_vect.getaddr(), 0, byte_vect.size(), 1000);
		
		if (read_val != byte_vect.size()) {
			comnt_socket.setErrFlag(true);
			return -1;
		}
		
		ComntMsg.MSG_SERVER_STRUCT comnt_msg = new ComntMsg.MSG_SERVER_STRUCT();
		comnt_msg.msg_head.fromDataStream(byte_vect, 0);
		
		//消息为空或太长则抛弃
		if (comnt_msg.msg_head.data_len <= 0 || comnt_msg.msg_head.data_len >= SOCKET_DATA_MAXLEN) return 1;		
		
		sel_val = comnt_socket.select(1000);		//1000ms
		if (sel_val <= 0) {							//socket err
			comnt_socket.setErrFlag(true);
			return -1;
		}

		byte_vect.resize(comnt_msg.msg_head.data_len);

		read_val = comnt_socket.read(byte_vect.getaddr(), 0, byte_vect.size(), 1000);

		if (read_val != byte_vect.size()) {
			comnt_socket.setErrFlag(true);
			return -1;
		}

		comnt_msg.msg_body.byte_vect.push_back(byte_vect);

		//每过5s服务器发送终端状态		
		if (ComntMsgProc.isSelfRtuState(comnt_msg)) {
			ComntMsgProc.setSelfRtuState(comnt_msg);
		}
		//推送报警信息
		else if (ComntMsgProc.isSelfSysEvent(comnt_msg)) {
			ComntMsgProc.setSelfSysEvent(comnt_msg);
		}
		//向用户推送充电状态
		else if(ComntMsgProc.isSelfAppChargingPushState(comnt_msg)){
//			MsgPush.addQueue(comnt_msg);
			ComntMsgProc.setSelfAppChargingPushState(comnt_msg);
		}
		//其他
		else {
			ComntMsgProc.addRecvMsg(comnt_msg);			
		}

		return 1;
	}
	
	private int writeProc() {
		if (comnt_socket.getErrFlag()) return -1;

		ArrayList<ComntMsg.MSG_CLIENT_STRUCT> msg_vect = ComntMsgProc.getWaitSendMsg(10);	//每次发送10个任务
		if (msg_vect == null || msg_vect.size() <= 0) return 0;
		
		ComntMsg.MSG_CLIENT_STRUCT msg = null;
		
		int write_val = 0;
		
		ComntVector.ByteVector byte_vect = new ComntVector.ByteVector();		
		
		for (int i = 0; i < msg_vect.size(); i++) {
			msg = msg_vect.get(i);
			
			byte_vect.clear();
			msg.msg_head.toDataStream(byte_vect);
			
			write_val = comnt_socket.write(byte_vect.getaddr(), 0, byte_vect.size());
			if (write_val != byte_vect.size()) {
				comnt_socket.setErrFlag(true);				
				return -1;
			}
			
			byte_vect.clear();
			byte_vect.push_back(msg.msg_body.byte_vect);
			
			write_val = comnt_socket.write(byte_vect.getaddr(), 0, byte_vect.size());
			if (write_val != byte_vect.size()) {
				comnt_socket.setErrFlag(true);				
				return -1;
			}
			
			//设置结束标记
			ComntMsgProc.setMsgRecvingFlag(msg.msg_head.uuid);
			
//			if (msg.msg_head.msg_type == ComntDef.YD_WEBMSGTYPE_CANCELMANTASK) {
//				ComntMsg.MSG_CANCELMANTASK cancel_msg = new ComntMsg.MSG_CANCELMANTASK();
//				cancel_msg.fromDataStream(msg.msg_body.byte_vect, 0);
//				ComntMsgProc.setMsgCancelRecv(cancel_msg.uuid);
//			}
//			else {
//				ComntMsgProc.setMsgRecvingFlag(msg.msg_head.uuid);
//			}
		}

		return 1;
	}
	
	private void checkSocket() {
		if (comnt_socket.getErrFlag()) {
			System.out.println(CommFunc.nowDateStr() +" "+ CommFunc.nowTimeStr() + "Socket Error");
			comnt_socket.setErrFlag(false);
			comnt_socket.close();
		}
	}
	
	private boolean isSocketValid() {
		if (comnt_socket == null || !comnt_socket.isSocketValid()) return false;
		else return true;
	}
	
	private void threadProc() 	{	
		if (!connectSocket()) return;

		int read_val  = 0;

		for (int i = 0; i < 10; i++) {
			read_val = readProc();			
			if (read_val <= 0) break;
		}		

		writeProc();

		checkSocket();		
	}
  
	private static boolean glo_live_flag = true;
	private static boolean glo_exit_flag = false;
	
	public static final int CHECK_MSG_INTER	= 5;	//检查消息间隔5s
	
	public static final int MAX_SOCKERR_NUM	= 4;	//socket错误清理消息
	
	////////////////////////////////////////////////
	public void run() {
		int last_time = ComntTime.cTime();
		int cur_time = last_time;

		System.out.println("通讯线程:正常启动.");

		while(glo_live_flag){
			try {
				threadProc();  //调试临时注释
				
				cur_time = ComntTime.cTime();
				if (Math.abs(cur_time - last_time) >= CHECK_MSG_INTER) {	//5s检查消息队列
					ComntMsgProc.checkMsg();
					last_time = cur_time;
				}
				
				if (sock_err_count > MAX_SOCKERR_NUM) {						//连续打开socket错误,清楚所有未完成任务
					ComntMsgProc.endAllRunningTask(ComntDef.YD_TASKERR_COMNT_TIMEOUT);
					sock_err_count = 0;
				}
				
				if (isSocketValid()) {
					//Thread.sleep(1);
				}
				else {
					Thread.sleep(500);
				}
				
			}catch(InterruptedException e){
				e.printStackTrace(System.err);
			}
		}
		
		comnt_socket.close();
		
		System.out.println("通讯线程:正常退出.");
		
		glo_exit_flag = true;		
	}

	////////////////////////////////////////////////
	private static Thread	   	glo_comnt_thread = null;
	private static ComntProc 	glo_comnt_proc   = null;

	public static void startComntProc() {
		glo_comnt_proc   = new ComntProc();
		glo_comnt_thread = new Thread(glo_comnt_proc);

		glo_comnt_thread.start();
	}

	public static void stopComntProc() {
		glo_live_flag = false;

		for (int i = 0; i < 10; i++) {	//5s
			if (glo_exit_flag) break;
			ComntFunc.wait(500);
		}
	}	
	
	public static ComntMsgProc getMsgProc() {
		if (glo_comnt_proc == null) return null;
		else return glo_comnt_proc.comnt_msgproc;
	}
	
	public static ComntCfg getComntCfg() {
		if (glo_comnt_proc == null) return null;
		else return glo_comnt_proc.comnt_cfg;
	}
}
