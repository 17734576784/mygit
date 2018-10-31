package com.wo.comnt;

public class ComntMsg {
	public static final int MSG_USERNAME_LEN	= 64;
	
	public static class MSG_CLIENT_HEAD {
		public int  msg_type      = 0;
		public int  uuid		  = 0;
		public int  data_len      = 0;
		public int  onlysend_flag = 0;
		public int  wait_count	  = 0;
		public byte user_name[]   = new byte[MSG_USERNAME_LEN];

		public static int size() {
			return MSG_USERNAME_LEN + 4 * 5;
		}

		public void clean() {
			msg_type 	  = 0;
			uuid	 	  = 0;
			data_len 	  = 0;
			onlysend_flag = 0;
			wait_count	  = 0;
			ComntFunc.arraySet(user_name, (byte)0);
		}

		public void clone(MSG_CLIENT_HEAD msg_head) {
			msg_type 	  = msg_head.msg_type;
			uuid	 	  = msg_head.uuid;
			data_len 	  = msg_head.data_len;
			onlysend_flag = msg_head.onlysend_flag;
			wait_count	  = msg_head.wait_count;			
			ComntFunc.arrayCopy(user_name, msg_head.user_name);
		}

		public MSG_CLIENT_HEAD clone() {
			MSG_CLIENT_HEAD ret_msg = new MSG_CLIENT_HEAD();
			ret_msg.clone(this);

			return ret_msg;
		}

		public void makeMsgHead(int msg_type, int uuid, int data_len, int onlysend_flag, 
								int wait_count, String user_name) {
			this.msg_type 		= msg_type;
			this.uuid     		= uuid;
			this.data_len 		= data_len;
			this.onlysend_flag	= onlysend_flag;
			this.wait_count	    = wait_count;
			ComntFunc.string2Byte(user_name, this.user_name);
		}

		public int toDataStream(ComntVector.ByteVector byte_vect) {
			int ret_len = 0;
			ret_len += ComntStream.writeStream(byte_vect, msg_type);
			ret_len += ComntStream.writeStream(byte_vect, uuid);
			ret_len += ComntStream.writeStream(byte_vect, data_len);
			ret_len += ComntStream.writeStream(byte_vect, onlysend_flag);
			ret_len += ComntStream.writeStream(byte_vect, wait_count);
			ret_len += ComntStream.writeStream(byte_vect, user_name, 0, user_name.length);
			return ret_len;
		}

		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset) {
			int ret_len = 0;
			msg_type = ComntStream.readStream(byte_vect, offset + ret_len, msg_type);
			ret_len += ComntStream.getDataSize(msg_type);

			uuid = ComntStream.readStream(byte_vect, offset + ret_len, uuid);
			ret_len += ComntStream.getDataSize(uuid);

			data_len = ComntStream.readStream(byte_vect, offset + ret_len, data_len);
			ret_len += ComntStream.getDataSize(data_len);

			onlysend_flag = ComntStream.readStream(byte_vect, offset + ret_len, onlysend_flag);
			ret_len += ComntStream.getDataSize(onlysend_flag);

			wait_count = ComntStream.readStream(byte_vect, offset + ret_len, wait_count);
			ret_len += ComntStream.getDataSize(wait_count);

			ComntStream.readStream(byte_vect, offset + ret_len, user_name, 0, user_name.length);
			ret_len += ComntStream.getDataSize((byte)1) * user_name.length;
			
			return ret_len;
		}
	}

	public static class MSG_SERVER_HEAD {
		public int  msg_type      = 0;
		public int  uuid		  = 0;
		public int  data_len      = 0;

		public static int size() {
			return 4 * 3;
		}

		public void clean() {
			msg_type 	  = 0;
			uuid	 	  = 0;
			data_len 	  = 0;
		}

		public void clone(MSG_SERVER_HEAD msg_head) {
			msg_type 	  = msg_head.msg_type;
			uuid	 	  = msg_head.uuid;
			data_len 	  = msg_head.data_len;
		}

		public MSG_SERVER_HEAD clone() {
			MSG_SERVER_HEAD ret_msg = new MSG_SERVER_HEAD();
			ret_msg.clone(this);
			
			return ret_msg;
		}
		
		public void makeMsgHead(int msg_type, int uuid, int data_len) {
			this.msg_type 		= msg_type;
			this.uuid     		= uuid;
			this.data_len 		= data_len;
		}

		public int toDataStream(ComntVector.ByteVector byte_vect) {
			int ret_len = 0;
			ret_len += ComntStream.writeStream(byte_vect, msg_type);
			ret_len += ComntStream.writeStream(byte_vect, uuid);
			ret_len += ComntStream.writeStream(byte_vect, data_len);
			return ret_len;
		}

		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset) {
			int ret_len = 0;
			msg_type = ComntStream.readStream(byte_vect, offset + ret_len, msg_type);
			ret_len += ComntStream.getDataSize(msg_type);

			uuid = ComntStream.readStream(byte_vect, offset + ret_len, uuid);
			ret_len += ComntStream.getDataSize(uuid);

			data_len = ComntStream.readStream(byte_vect, offset + ret_len, data_len);
			ret_len += ComntStream.getDataSize(data_len);
			
			return ret_len;
		}
	}
	
	public static class MSG_BODY {
		public ComntVector.ByteVector byte_vect = new ComntVector.ByteVector();
		
		public void clean() {
			byte_vect.clear();
		}
		
		public void clone(MSG_BODY msg_body) {
			byte_vect.clone(msg_body.byte_vect);
		}

		public MSG_BODY clone() {
			MSG_BODY ret_msg = new MSG_BODY();
			ret_msg.clone(this);

			return ret_msg;
		}
		
		public void makeMsgBody(ComntVector.ByteVector byte_vect) {
			this.byte_vect.clone(byte_vect);
		}
	}
	
	public static class MSG_CLIENT_STRUCT {
		public MSG_CLIENT_HEAD msg_head = new MSG_CLIENT_HEAD();
		public MSG_BODY        msg_body = new MSG_BODY();

		public void clean() {
			msg_head.clean();
			msg_body.clean();
		}
		
		public void clone(MSG_CLIENT_STRUCT msg_struct) {
			msg_head.clone(msg_struct.msg_head);
			msg_body.clone(msg_struct.msg_body);
		}
		
		public MSG_CLIENT_STRUCT clone() {
			MSG_CLIENT_STRUCT ret_msg = new MSG_CLIENT_STRUCT();
			ret_msg.clone(this);
			
			return ret_msg;
		}
		
		public void makeClientMsgStruct(int msg_type, int uuid, int onlysend_flag, 
								        int wait_count, String user_name, ComntVector.ByteVector byte_vect) {
			
			msg_head.makeMsgHead(msg_type, uuid, byte_vect.size(), onlysend_flag, wait_count, user_name);			
			msg_body.byte_vect.clone(byte_vect);			
		}
	}
	
	public static class MSG_SERVER_STRUCT {
		public MSG_SERVER_HEAD msg_head = new MSG_SERVER_HEAD();
		public MSG_BODY        msg_body = new MSG_BODY();

		public void clean() {
			msg_head.clean();
			msg_body.clean();
		}
		
		public void clone(MSG_SERVER_STRUCT msg_struct) {
			msg_head.clone(msg_struct.msg_head);
			msg_body.clone(msg_struct.msg_body);
		}
		
		public MSG_SERVER_STRUCT clone() {
			MSG_SERVER_STRUCT ret_msg = new MSG_SERVER_STRUCT();
			ret_msg.clone(this);
			
			return ret_msg;
		}
		
		public void makeServerMsgStruct(int msg_type, int uuid, ComntVector.ByteVector byte_vect) {
			msg_head.msg_type = msg_type;
			msg_head.uuid     = uuid;
			msg_head.data_len = byte_vect.size();
			
			msg_body.byte_vect.clone(byte_vect);
		}
	}
	
	public static interface IMSG_READWRITESTREAM {
		public int toDataStream(ComntVector.ByteVector byte_vect);
		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset);
	 
	}
	
	public static class MSG_TASK implements IMSG_READWRITESTREAM {
		public byte		taskapptype		= 0;
		public byte		taskassigntype	= 0;

		public int		rtuid			= -1;
		public byte		prottype		= 0;

		public byte		retrynum		= 0;
//		public short	waittimeout		= 0;

//		public int		task_uuid		= 0;

		public byte		bakresult_flag	= 0;		//返回任务执行状态
		public byte		bakdata_dict	= 0;		//返回数据方向
		public byte		bakcode_flag	= 0;		//返回通讯报文标志
		public byte		bakcodejx_flag  = 0;		//返回通讯报文解析标志

		public ComntVector.ByteVector data_vect = new ComntVector.ByteVector();
		
		public void clean() {
			taskapptype		= 0;
			taskassigntype	= 0;

			rtuid			= -1;
			prottype		= 0;

			retrynum		= 0;
//			waittimeout		= 0;

//			task_uuid		= 0;

			bakresult_flag	= 0;		//返回任务执行状态
			bakdata_dict	= 0;		//返回数据方向
			bakcode_flag	= 0;		//返回通讯报文标志
			bakcodejx_flag  = 0;		//返回通讯报文解析标志
			
			data_vect.clear();
		}
		
		public void clone(MSG_TASK msg) {
			taskapptype		= msg.taskapptype;
			taskassigntype	= msg.taskassigntype;

			rtuid			= msg.rtuid;
			prottype		= msg.prottype;

			retrynum		= msg.retrynum;
//			waittimeout		= msg.waittimeout;

//			task_uuid		= msg.task_uuid;

			bakresult_flag	= msg.bakresult_flag;		//返回任务执行状态
			bakdata_dict	= msg.bakdata_dict;			//返回数据方向
			bakcode_flag	= msg.bakcode_flag;			//返回通讯报文标志
			bakcodejx_flag  = msg.bakcodejx_flag;		//返回通讯报文解析标志

			data_vect.clone(msg.data_vect);
		}
		
		public MSG_TASK clone() {
			MSG_TASK ret_msg = new MSG_TASK();
			ret_msg.clone(this);
			
			return ret_msg;
		}
		
		public void makeMsgTask(byte  apptype,     
								byte  assigntype,
								int   rtuid,
								byte  prottype,
								byte  retrynum,
								byte  bakresult_flag, 
								byte  bakdata_dict,  
								byte  bakcode_flag, 
								byte  bakcodejx_flag) {

			this.taskapptype	= apptype;
			this.taskassigntype	= assigntype;

			this.rtuid			= rtuid;
			this.prottype		= prottype;
			this.retrynum		= retrynum;

			this.bakresult_flag	= bakresult_flag;		//返回任务执行状态
			this.bakdata_dict	= bakdata_dict;			//返回数据方向
			this.bakcode_flag	= bakcode_flag;			//返回通讯报文标志
			this.bakcodejx_flag = bakcodejx_flag;		//返回通讯报文解析标志			
		}
		
		public int toDataStream(ComntVector.ByteVector byte_vect) {
			int ret_len   = 0;
			int vect_size = 0;

			ret_len += ComntStream.writeStream(byte_vect, taskapptype);
			ret_len += ComntStream.writeStream(byte_vect, taskassigntype);

			ret_len += ComntStream.writeStream(byte_vect, rtuid);
			ret_len += ComntStream.writeStream(byte_vect, prottype);

			ret_len += ComntStream.writeStream(byte_vect, retrynum);
//			ret_len += ComntStream.writeStream(byte_vect, waittimeout);

//			ret_len += ComntStream.writeStream(byte_vect, task_uuid);

			ret_len += ComntStream.writeStream(byte_vect, bakresult_flag);
			ret_len += ComntStream.writeStream(byte_vect, bakdata_dict);
			ret_len += ComntStream.writeStream(byte_vect, bakcode_flag);
			ret_len += ComntStream.writeStream(byte_vect, bakcodejx_flag);			
			
			vect_size = data_vect.size();
			ret_len += ComntStream.writeStream(byte_vect, vect_size);
			ret_len += ComntStream.writeStream(byte_vect, data_vect.getaddr(), 0, vect_size); 
			
			return ret_len;
		}
		
		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset) {
			int ret_len = 0;
			int vect_size = 0;
			
			taskapptype = ComntStream.readStream(byte_vect, offset + ret_len, taskapptype); 
			ret_len += ComntStream.getDataSize(taskapptype);
			
			taskassigntype = ComntStream.readStream(byte_vect, offset + ret_len, taskassigntype); 
			ret_len += ComntStream.getDataSize(taskassigntype);
			
			rtuid = ComntStream.readStream(byte_vect, offset + ret_len, rtuid);
			ret_len += ComntStream.getDataSize(rtuid);
			
			prottype = ComntStream.readStream(byte_vect, offset + ret_len, prottype);
			ret_len += ComntStream.getDataSize(prottype);
			
			retrynum = ComntStream.readStream(byte_vect, offset + ret_len, retrynum);
			ret_len += ComntStream.getDataSize(retrynum);					
			
//			waittimeout = ComntStream.readStream(byte_vect, offset + ret_len, waittimeout);
//			ret_len += ComntStream.getDataSize(waittimeout);					

//			task_uuid = ComntStream.readStream(byte_vect, offset + ret_len, task_uuid);
//			ret_len += ComntStream.getDataSize(task_uuid);

			bakresult_flag = ComntStream.readStream(byte_vect, offset + ret_len, bakresult_flag);
			ret_len += ComntStream.getDataSize(bakresult_flag);

			bakdata_dict = ComntStream.readStream(byte_vect, offset + ret_len, bakdata_dict);
			ret_len += ComntStream.getDataSize(bakdata_dict);

			bakcode_flag = ComntStream.readStream(byte_vect, offset + ret_len, bakcode_flag);
			ret_len += ComntStream.getDataSize(bakcode_flag);

			bakcodejx_flag = ComntStream.readStream(byte_vect, offset + ret_len, bakcodejx_flag);
			ret_len += ComntStream.getDataSize(bakcodejx_flag);

			vect_size = ComntStream.readStream(byte_vect, offset + ret_len, vect_size);
			ret_len += ComntStream.getDataSize(vect_size);
			
			data_vect.resize(vect_size);
			ComntStream.readStream(byte_vect, offset + ret_len, data_vect.getaddr(), 0, vect_size);
			ret_len += ComntStream.getDataSize((byte)1) * vect_size;
			
			return ret_len;
		}		
	}	
	
	public static void makeTaskComntMsg(MSG_CLIENT_STRUCT comnt_msg, 
										MSG_TASK task_msg, 
										int uuid, 
										int onlysend_flag, 
										int wait_count, 
										String user_name) {		
		task_msg.toDataStream(comnt_msg.msg_body.byte_vect);
		
		comnt_msg.msg_head.makeMsgHead(ComntDef.YD_WEBMSGTYPE_TASK, uuid, comnt_msg.msg_body.byte_vect.size(), 
									   onlysend_flag, wait_count, user_name);		
	}

	public static void makeTaskComntMsg(MSG_CLIENT_STRUCT comnt_msg, 
										int 	uuid, 
										int 	onlysend_flag, 
										int 	wait_count, 
										String 	user_name,
										byte  	apptype,     
										byte 	assigntype,     
										int 	rtuid,          
										byte 	prottype,     
										byte 	retrynum,
										byte	bakresult_flag, 
										byte 	bakdata_dict,  
										byte 	bakcode_flag, 
										byte  	bakcodejx_flag,
										ComntVector.ByteVector byte_vect) {
		MSG_TASK task_msg = new MSG_TASK();
		task_msg.makeMsgTask(apptype, assigntype, rtuid, prottype, retrynum, bakresult_flag, bakdata_dict, bakcode_flag, bakcodejx_flag);		
		task_msg.data_vect.clone(byte_vect);
		
		makeTaskComntMsg(comnt_msg, task_msg, uuid, onlysend_flag, wait_count, user_name);
	}	
	
	public static class MSG_TASKRESULT implements IMSG_READWRITESTREAM  {
//		public byte		taskapptype		= 0;
		public int		rtuid			= -1;
//		public byte		prottype		= 0;
		public byte		taskresult		= 0;
		
		public byte		errcode			= 0;
		public byte		errproc			= 0;
		public byte   	errhost[]		= new byte[16]; 
		
		public int		user_data1		= 0;
		public int		user_data2		= 0;
		
		{
			for (int i = 0; i < 16; i++) errhost[i] = 0;
		}
		
		public void clean() {
//			taskapptype		= 0;
			rtuid			= -1;
//			prottype		= 0;
			taskresult		= 0;
			
			errcode			= 0;
			errproc			= 0;
			for (int i = 0; i < 16; i++) errhost[i] = 0;
			
			user_data1      = 0;
			user_data2      = 0;
		}
		
		public void clone(MSG_TASKRESULT task_result) {
//			taskapptype		= task_result.taskapptype;
			rtuid			= task_result.rtuid;
//			prottype		= task_result.prottype;
			taskresult		= task_result.taskresult;
			
			errcode			= task_result.errcode;
			errproc			= task_result.errproc;
			for (int i = 0; i < 16; i++) errhost[i] = task_result.errhost[i];
			
			user_data1		= task_result.user_data1;
			user_data2		= task_result.user_data2;
		}

		public MSG_TASKRESULT clone() {
			MSG_TASKRESULT ret_msg = new MSG_TASKRESULT();
			ret_msg.clone(this);

			return ret_msg;
		}

		public void makeMsgTaskResult(int rtuid, byte taskresult, 
									  byte errcode, byte errproc, String errhost, int user_data1, int user_data2) {
//			this.taskapptype	= apptype;
			this.rtuid			= rtuid;
//			this.prottype		= prottype;
			this.taskresult		= taskresult;
			
			this.errcode		= errcode;
			this.errproc		= errproc;
			
			byte t_b[] = ComntFunc.string2Byte(errhost);
			
			for (int i = 0; i < 15; i++) {
				if (t_b != null && i < t_b.length) this.errhost[i] = t_b[i];
				else this.errhost[i] = 0;
			}
			this.errhost[15] = 0;
			
			this.user_data1     = user_data1;
			this.user_data2     = user_data1;
		}

		public int toDataStream(ComntVector.ByteVector byte_vect) {
			int ret_len   = 0;

//			ret_len += ComntStream.writeStream(byte_vect, taskapptype);
			ret_len += ComntStream.writeStream(byte_vect, rtuid);
//			ret_len += ComntStream.writeStream(byte_vect, prottype);
			ret_len += ComntStream.writeStream(byte_vect, taskresult);
			ret_len += ComntStream.writeStream(byte_vect, errcode); 
			ret_len += ComntStream.writeStream(byte_vect, errproc);
			
			ret_len += ComntStream.writeStream(byte_vect, errhost, 0, errhost.length);
			
			ret_len += ComntStream.writeStream(byte_vect, user_data1); 
			ret_len += ComntStream.writeStream(byte_vect, user_data2); 
			
			return ret_len;
		}
		
		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset) {
			int ret_len = 0;

//			taskapptype = ComntStream.readStream(byte_vect, offset + ret_len, taskapptype); 
//			ret_len += ComntStream.getDataSize(taskapptype);

			rtuid = ComntStream.readStream(byte_vect, offset + ret_len, rtuid);
			ret_len += ComntStream.getDataSize(rtuid);

//			prottype = ComntStream.readStream(byte_vect, offset + ret_len, prottype);
//			ret_len += ComntStream.getDataSize(prottype);

			taskresult = ComntStream.readStream(byte_vect, offset + ret_len, taskresult);
			ret_len += ComntStream.getDataSize(taskresult);

			errcode = ComntStream.readStream(byte_vect, offset + ret_len, errcode);
			ret_len += ComntStream.getDataSize(errcode);

			errproc = ComntStream.readStream(byte_vect, offset + ret_len, errproc);
			ret_len += ComntStream.getDataSize(errproc);			
			
			ComntStream.readStream(byte_vect, offset + ret_len, errhost, 0, errhost.length);
			ret_len += ComntStream.getDataSize(errhost[0]) * errhost.length;						
			
			user_data1 = ComntStream.readStream(byte_vect, offset + ret_len, user_data1);
			ret_len += ComntStream.getDataSize(user_data1);

			user_data2 = ComntStream.readStream(byte_vect, offset + ret_len, user_data2);
			ret_len += ComntStream.getDataSize(user_data2);

			return ret_len;
		}
	}

	public static class MSG_DATA_FN implements IMSG_READWRITESTREAM  {
		public int		prot_i_id		= 0;
		public int		point_id		= 0;

		public ComntVector.ByteVector data_vect = new ComntVector.ByteVector();
		
		public void clean() {
			prot_i_id		= 0;		//规约项所属对象类型
			point_id		= 0;

			data_vect.clear();
		}
		
		public void clone(MSG_DATA_FN msg) {
			prot_i_id = msg.prot_i_id;
			point_id  = msg.point_id;
			
			data_vect.clone(msg.data_vect);			
		}
		
		public MSG_DATA_FN clone() {
			MSG_DATA_FN ret_msg = new MSG_DATA_FN();
			ret_msg.clone(this);
			
			return ret_msg;
		}
		
		public int toDataStream(ComntVector.ByteVector byte_vect) {
			int ret_len   = 0;
			int vect_size = 0;
			
			ret_len += ComntStream.writeStream(byte_vect, prot_i_id);
			ret_len += ComntStream.writeStream(byte_vect, point_id);
		
			vect_size = data_vect.size();
			ret_len += ComntStream.writeStream(byte_vect, vect_size);
			ret_len += ComntStream.writeStream(byte_vect, data_vect.getaddr(), 0, vect_size); 
			
			return ret_len;
		}
		
		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset) {
			int ret_len = 0;
			int vect_size = 0;
			
			prot_i_id = ComntStream.readStream(byte_vect, offset + ret_len, prot_i_id); 
			ret_len += ComntStream.getDataSize(prot_i_id);
			
			point_id = ComntStream.readStream(byte_vect, offset + ret_len, point_id); 
			ret_len += ComntStream.getDataSize(point_id);
			
			vect_size = ComntStream.readStream(byte_vect, offset + ret_len, vect_size);
			ret_len += ComntStream.getDataSize(vect_size);
			
			data_vect.resize(vect_size);
			ComntStream.readStream(byte_vect, offset + ret_len, data_vect.getaddr(), 0, vect_size);
			ret_len += ComntStream.getDataSize((byte)1) * vect_size;
			
			return ret_len;
		}				
	}
	
	public static class MSG_RESULT_DATA implements IMSG_READWRITESTREAM  {
		public int		afn			= 0;
		public int 		datatime	= 0;			//数据时间

		public byte		dataclass	= 0;			//数据分类

		public int		rtuid		= 0;	
		public byte		prottype	= 0;

//		public int		task_uuid	= 0;

		public MSG_DATA_FN[] fn_vect = null;
		
		public void clean() {
			afn			= 0;
			datatime	= 0;
			dataclass	= 0;
			rtuid		= 0;
			prottype	= 0;
			fn_vect		= null;
		}
		
		public void clone(MSG_RESULT_DATA msg) {
			afn			= msg.afn;
			datatime	= msg.datatime;			//数据时间

			dataclass	= msg.dataclass;		//数据分类

			rtuid		= msg.rtuid;
			prottype	= msg.prottype;

//			task_uuid	= msg.task_uuid;
			
			if (msg.fn_vect == null) {
				msg.fn_vect = null;
			}
			else {
				if (fn_vect == null || fn_vect.length != msg.fn_vect.length)  fn_vect = new MSG_DATA_FN [msg.fn_vect.length];				
				for (int i = 0; i < fn_vect.length; i++)  fn_vect[i] = msg.fn_vect[i].clone();				
			}						
		}
		
		public MSG_RESULT_DATA clone() {
			MSG_RESULT_DATA ret_msg = new MSG_RESULT_DATA();
			ret_msg.clone(this);
			
			return ret_msg;
		}
		
		public int toDataStream(ComntVector.ByteVector byte_vect) {
			int ret_len   = 0;
			int i = 0, vect_size = 0;
			
			ret_len += ComntStream.writeStream(byte_vect, afn);
			ret_len += ComntStream.writeStream(byte_vect, datatime);
			ret_len += ComntStream.writeStream(byte_vect, dataclass);
			ret_len += ComntStream.writeStream(byte_vect, rtuid);
			ret_len += ComntStream.writeStream(byte_vect, prottype);
//			ret_len += ComntStream.writeStream(byte_vect, task_uuid);
		
			vect_size = fn_vect.length;
			ret_len += ComntStream.writeStream(byte_vect, vect_size);
			
			for (i = 0; i < vect_size; i++) {
				ret_len += fn_vect[i].toDataStream(byte_vect);
			}
			
			return ret_len;
		}		
		
		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset) {
			int ret_len = 0;
			int i = 0, vect_size = 0;
			
			afn = ComntStream.readStream(byte_vect, offset + ret_len, afn); 
			ret_len += ComntStream.getDataSize(afn);
			
			datatime = ComntStream.readStream(byte_vect, offset + ret_len, datatime); 
			ret_len += ComntStream.getDataSize(datatime);
			
			dataclass = ComntStream.readStream(byte_vect, offset + ret_len, dataclass); 
			ret_len += ComntStream.getDataSize(dataclass);
			
			rtuid = ComntStream.readStream(byte_vect, offset + ret_len, rtuid); 
			ret_len += ComntStream.getDataSize(rtuid);
			
			prottype = ComntStream.readStream(byte_vect, offset + ret_len, prottype); 
			ret_len += ComntStream.getDataSize(prottype);
			
//			task_uuid = ComntStream.readStream(byte_vect, offset + ret_len, task_uuid); 
//			ret_len += ComntStream.getDataSize(task_uuid);
			
			vect_size = ComntStream.readStream(byte_vect, offset + ret_len, vect_size);
			ret_len += ComntStream.getDataSize(vect_size);
			
			fn_vect = new MSG_DATA_FN[vect_size];
			for (i = 0; i < vect_size; i++) {
				fn_vect[i] = new MSG_DATA_FN();
				ret_len += fn_vect[i].fromDataStream(byte_vect, offset + ret_len);
			}
			
			return ret_len;
		}						
	}
	
	
	public static class MSG_RTUSTATE_ITEM {
		public int		rtu_id			= -1;
		public byte		comm_state		= 0;	//RTU通讯状态
//		public short    err_cnt			= 0;	//RTU通讯错误计数
		public short    frame_ok_cnt	= 0;	//正确侦记数
		public short    frame_err_cnt	= 0;	//错误侦记数
		public int		last_time		= 0;	//RTU最后通讯时间
		public int		ip_addr			= 0;	//当前IP地址
		public byte		run_state		= 0;	//当前运行状态
		public byte		autotask_num	= 0;	//自动任务数量
		public byte		mantask_num		= 0;	//手工任务数量
		
		/////sjh-20110519
		public short    rx_cnt			= 0;	//接收数据量
		public short    tx_cnt			= 0;	//发送数据量
		/////
		
		public void clean() {
			rtu_id			= -1;
			comm_state		= 0;		//RTU通讯状态
//			err_cnt			= 0;		//RTU通讯错误计数
			frame_ok_cnt	= 0;		//正确侦记数
			frame_err_cnt	= 0;		//错误侦记数		
			last_time		= 0;		//RTU最后通讯时间
			ip_addr			= 0;		//当前IP地址
			run_state		= 0;		//当前运行状态
			autotask_num	= 0;		//自动任务数量
			mantask_num		= 0;		//手工任务数量
			rx_cnt			= 0;		//接收数据量
			tx_cnt			= 0;		//发送数据量
		}
		
		public void clone(MSG_RTUSTATE_ITEM msg) {
			rtu_id			= msg.rtu_id;
			comm_state		= msg.comm_state;	//RTU通讯状态
//			err_cnt			= msg.err_cnt;		//RTU通讯错误计数
			frame_ok_cnt	= msg.frame_ok_cnt;	 //正确侦记数
			frame_err_cnt	= msg.frame_err_cnt; //错误侦记数				
			last_time		= msg.last_time;	//RTU最后通讯时间
			ip_addr			= msg.ip_addr;		//当前IP地址
			run_state		= msg.run_state;	//当前运行状态
			autotask_num	= msg.autotask_num;	//自动任务数量
			mantask_num		= msg.mantask_num;	//手工任务数量
			rx_cnt			= msg.rx_cnt;		//接收数据量
			tx_cnt			= msg.tx_cnt;		//发送数据量
		}
		
		public MSG_RTUSTATE_ITEM clone() {
			MSG_RTUSTATE_ITEM ret_msg = new MSG_RTUSTATE_ITEM();
			ret_msg.clone(this);
			
			return ret_msg;
		}
		
		public int toDataStream(ComntVector.ByteVector byte_vect) {
			int ret_len   = 0;
			
			ret_len += ComntStream.writeStream(byte_vect, rtu_id);
			ret_len += ComntStream.writeStream(byte_vect, comm_state);
//			ret_len += ComntStream.writeStream(byte_vect, err_cnt);
			ret_len += ComntStream.writeStream(byte_vect, frame_ok_cnt);
			ret_len += ComntStream.writeStream(byte_vect, frame_err_cnt);			
			ret_len += ComntStream.writeStream(byte_vect, last_time);
			ret_len += ComntStream.writeStream(byte_vect, ip_addr);
			ret_len += ComntStream.writeStream(byte_vect, run_state);
			ret_len += ComntStream.writeStream(byte_vect, autotask_num);
			ret_len += ComntStream.writeStream(byte_vect, rx_cnt);
			ret_len += ComntStream.writeStream(byte_vect, tx_cnt);			
			
			return ret_len;
		}
		
		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset) {
			int ret_len = 0;

			rtu_id = ComntStream.readStream(byte_vect, offset + ret_len, rtu_id); 
			ret_len += ComntStream.getDataSize(rtu_id);
			
			comm_state = ComntStream.readStream(byte_vect, offset + ret_len, comm_state); 
			ret_len += ComntStream.getDataSize(comm_state);
			
//			err_cnt = ComntStream.readStream(byte_vect, offset + ret_len, err_cnt);
//			ret_len += ComntStream.getDataSize(err_cnt);
	
			frame_ok_cnt = ComntStream.readStream(byte_vect, offset + ret_len, frame_ok_cnt);
			ret_len += ComntStream.getDataSize(frame_ok_cnt);			
			
			frame_err_cnt = ComntStream.readStream(byte_vect, offset + ret_len, frame_err_cnt);
			ret_len += ComntStream.getDataSize(frame_err_cnt);			
			
			last_time = ComntStream.readStream(byte_vect, offset + ret_len, last_time); 
			ret_len += ComntStream.getDataSize(last_time);
			
			ip_addr = ComntStream.readStream(byte_vect, offset + ret_len, ip_addr); 
			ret_len += ComntStream.getDataSize(ip_addr);
			
			run_state = ComntStream.readStream(byte_vect, offset + ret_len, run_state); 
			ret_len += ComntStream.getDataSize(run_state);
			
			autotask_num = ComntStream.readStream(byte_vect, offset + ret_len, autotask_num); 
			ret_len += ComntStream.getDataSize(autotask_num);

			mantask_num = ComntStream.readStream(byte_vect, offset + ret_len, mantask_num); 
			ret_len += ComntStream.getDataSize(mantask_num);			
			
			rx_cnt = ComntStream.readStream(byte_vect, offset + ret_len, rx_cnt); 
			ret_len += ComntStream.getDataSize(rx_cnt);
			
			tx_cnt = ComntStream.readStream(byte_vect, offset + ret_len, tx_cnt); 
			ret_len += ComntStream.getDataSize(tx_cnt);
			
			return ret_len;
		}				
	}
	
	public static class MSG_RTUSTATE implements IMSG_READWRITESTREAM  {
		public MSG_RTUSTATE_ITEM[] rtuitem_vect = null;
		
		public void clean() {
			rtuitem_vect = null;
		}
		
		public void clone(MSG_RTUSTATE msg) {
			if (msg.rtuitem_vect == null) {
				msg.rtuitem_vect = null;
			}
			else {
				if (rtuitem_vect == null || rtuitem_vect.length != msg.rtuitem_vect.length)  rtuitem_vect = new MSG_RTUSTATE_ITEM [msg.rtuitem_vect.length];				
				for (int i = 0; i < rtuitem_vect.length; i++)  rtuitem_vect[i] = msg.rtuitem_vect[i].clone();				
			}						
		}
		
		public MSG_RTUSTATE clone() {
			MSG_RTUSTATE ret_msg = new MSG_RTUSTATE();
			ret_msg.clone(this);
			
			return ret_msg;
		}
		
		public int toDataStream(ComntVector.ByteVector byte_vect) {
			int ret_len   = 0;
			int i = 0, vect_size = 0;
			
			vect_size = rtuitem_vect.length;
			ret_len += ComntStream.writeStream(byte_vect, vect_size);
			
			for (i = 0; i < vect_size; i++) {
				ret_len += rtuitem_vect[i].toDataStream(byte_vect);
			}
			
			return ret_len;
		}		
		
		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset) {
			int ret_len = 0;
			int i = 0, vect_size = 0;

			vect_size = ComntStream.readStream(byte_vect, offset + ret_len, vect_size); 
			ret_len += ComntStream.getDataSize(vect_size);	
			
			rtuitem_vect = new MSG_RTUSTATE_ITEM[vect_size];
			for (i = 0; i < vect_size; i++) {
				rtuitem_vect[i] = new MSG_RTUSTATE_ITEM();
				ret_len += rtuitem_vect[i].fromDataStream(byte_vect, offset + ret_len);
			}
			
			return ret_len;
		}						
	}
	
	public static class MSG_SYSEVENT_ITEM {
		public short	classno			= 0;				//0 事件分类号
		public short	typeno			= 0;				//1 事件类型
		public int		ymd				= 0;				//2 年月日
		public int		hmsms			= 0;				//3 时分秒毫秒
		public byte		voltgrade		= 0;				//4 电压等级
		public byte		output			= 0;				//5 输出级
		public int		member_id0		= 0;				//6 事件对象ID0
		public int		member_id1		= 0;				//
		public int		member_id2		= 0;				//
		public double	double_value0	= 0;				//9 当前浮点值
		public double	double_value1	= 0;
		public double	double_value2	= 0;
		public double	double_value3	= 0;
		public byte		state_value0	= 0;				//13 当前状态值(越限事项代表越限级别)
		public byte		state_value1	= 0;
		public byte		state_value2	= 0;
		public byte		state_value3	= 0;
		public byte		group_name[]  	= new byte [16];	//17 事件对象组名
		public byte		member_name0[]	= new byte [16];	//18 事件对象名1
		public byte		member_name1[]	= new byte [16];	//19 事件对象名2
		public byte		member_name2[]	= new byte [16];	//20 事件对象名3
		public byte		char_info[]   	= new byte [128];	//21 文字描述信息
		//public byte		sound_info[]    = new byte [128];	//22 语音描述信息
		
		{
			ComntFunc.arraySet(group_name, 		0, group_name.length, 	(byte)0);
			ComntFunc.arraySet(member_name0, 	0, member_name0.length, (byte)0);
			ComntFunc.arraySet(member_name1, 	0, member_name1.length, (byte)0);
			ComntFunc.arraySet(member_name2, 	0, member_name2.length, (byte)0);
			ComntFunc.arraySet(char_info, 		0, char_info.length, 	(byte)0);
			//ComntFunc.arraySet(sound_info, 		0, sound_info.length, 	(byte)0);
		}		
		
		public void clean() {
			classno			= 0;				//0 事件分类号
			typeno			= 0;				//1 事件类型
			ymd				= 0;				//2 年月日
			hmsms			= 0;				//3 时分秒毫秒
			voltgrade		= 0;				//4 电压等级
			output			= 0;				//5 输出级
			member_id0		= 0;				//6 事件对象ID0
			member_id1		= 0;				//
			member_id2		= 0;				//
			double_value0	= 0;				//9 当前浮点值
			double_value1	= 0;
			double_value2	= 0;
			double_value3	= 0;
			state_value0	= 0;				//13 当前状态值(越限事项代表越限级别)
			state_value1	= 0;
			state_value2	= 0;
			state_value3	= 0;
			
			ComntFunc.arraySet(group_name, 		0, group_name.length, 	(byte)0);
			ComntFunc.arraySet(member_name0, 	0, member_name0.length, (byte)0);
			ComntFunc.arraySet(member_name1, 	0, member_name1.length, (byte)0);
			ComntFunc.arraySet(member_name2, 	0, member_name2.length, (byte)0);
			ComntFunc.arraySet(char_info, 		0, char_info.length, 	(byte)0);
			//ComntFunc.arraySet(sound_info, 		0, sound_info.length, 	(byte)0);			
		}
		
		public void clone(MSG_SYSEVENT_ITEM msg) {
			classno			= msg.classno;			//0 事件分类号
			typeno			= msg.typeno;			//1 事件类型
			ymd				= msg.ymd;				//2 年月日
			hmsms			= msg.hmsms;			//3 时分秒毫秒
			voltgrade		= msg.voltgrade;		//4 电压等级
			output			= msg.output;			//5 输出级
			member_id0		= msg.member_id0;		//6 事件对象ID0
			member_id1		= msg.member_id1;		//
			member_id2		= msg.member_id2;		//
			double_value0	= msg.double_value0;	//9 当前浮点值
			double_value1	= msg.double_value1;
			double_value2	= msg.double_value2;
			double_value3	= msg.double_value3;
			state_value0	= msg.state_value0;		//13 当前状态值(越限事项代表越限级别)
			state_value1	= msg.state_value1;
			state_value2	= msg.state_value2;
			state_value3	= msg.state_value3;
			
			ComntFunc.arrayCopy(group_name, 	msg.group_name);
			ComntFunc.arrayCopy(member_name0, 	msg.member_name0);
			ComntFunc.arrayCopy(member_name1, 	msg.member_name1);
			ComntFunc.arrayCopy(member_name2, 	msg.member_name2);
			ComntFunc.arrayCopy(char_info, 		msg.char_info);
			//ComntFunc.arrayCopy(sound_info, 	msg.sound_info);				
		}
		
		public MSG_SYSEVENT_ITEM clone() {
			MSG_SYSEVENT_ITEM ret_msg = new MSG_SYSEVENT_ITEM();
			ret_msg.clone(this);
			
			return ret_msg;
		}
		
		public int toDataStream(ComntVector.ByteVector byte_vect) {
			int ret_len   = 0;
			
			ret_len += ComntStream.writeStream(byte_vect, classno);				//0 事件分类号
			ret_len += ComntStream.writeStream(byte_vect, typeno);				//1 事件类型
			ret_len += ComntStream.writeStream(byte_vect, ymd);					//2 年月日
			ret_len += ComntStream.writeStream(byte_vect, hmsms);				//3 时分秒毫秒
			ret_len += ComntStream.writeStream(byte_vect, voltgrade);			//4 电压等级
			ret_len += ComntStream.writeStream(byte_vect, output);				//5 输出级
			ret_len += ComntStream.writeStream(byte_vect, member_id0);			//6 事件对象ID0
			ret_len += ComntStream.writeStream(byte_vect, member_id1);			//
			ret_len += ComntStream.writeStream(byte_vect, member_id2);			//
			ret_len += ComntStream.writeStream(byte_vect, double_value0);		//9 当前浮点值
			ret_len += ComntStream.writeStream(byte_vect, double_value1);
			ret_len += ComntStream.writeStream(byte_vect, double_value2);
			ret_len += ComntStream.writeStream(byte_vect, double_value3);
			ret_len += ComntStream.writeStream(byte_vect, state_value0);		//13 当前状态值(越限事项代表越限级别)
			ret_len += ComntStream.writeStream(byte_vect, state_value1);
			ret_len += ComntStream.writeStream(byte_vect, state_value2);
			ret_len += ComntStream.writeStream(byte_vect, state_value3);
			
			ret_len += ComntStream.writeStream(byte_vect, group_name,	0, group_name.length);		//17 事件对象组名
			ret_len += ComntStream.writeStream(byte_vect, member_name0, 0, member_name0.length);	//18 事件对象名1
			ret_len += ComntStream.writeStream(byte_vect, member_name1, 0, member_name1.length);	//19 事件对象名2
			ret_len += ComntStream.writeStream(byte_vect, member_name2, 0, member_name2.length);	//20 事件对象名3
			ret_len += ComntStream.writeStream(byte_vect, char_info,	0, char_info.length);		//21 文字描述信息
			//ret_len += ComntStream.writeStream(byte_vect, sound_info,	0, sound_info.length);		//22 语音描述信息 			
			
			return ret_len;
		}
		
		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset) {
			int ret_len = 0;

			classno			= ComntStream.readStream(byte_vect, offset + ret_len,	classno);			//0 事件分类号
			ret_len += ComntStream.getDataSize(classno);

			typeno			= ComntStream.readStream(byte_vect, offset + ret_len,	typeno);			//1 事件类型
			ret_len += ComntStream.getDataSize(typeno);

			ymd				= ComntStream.readStream(byte_vect, offset + ret_len,	ymd);				//2 年月日
			ret_len += ComntStream.getDataSize(ymd);

			hmsms			= ComntStream.readStream(byte_vect, offset + ret_len,	hmsms);				//3 时分秒毫秒
			ret_len += ComntStream.getDataSize(hmsms);

			voltgrade		= ComntStream.readStream(byte_vect, offset + ret_len,	voltgrade);			//4 电压等级
			ret_len += ComntStream.getDataSize(voltgrade);

			output			= ComntStream.readStream(byte_vect, offset + ret_len,	output);			//5 输出级
			ret_len += ComntStream.getDataSize(output);

			member_id0		= ComntStream.readStream(byte_vect, offset + ret_len,	member_id0);		//6 事件对象ID0
			ret_len += ComntStream.getDataSize(member_id0);

			member_id1		= ComntStream.readStream(byte_vect, offset + ret_len,	member_id1);		//
			ret_len += ComntStream.getDataSize(member_id1);

			member_id2		= ComntStream.readStream(byte_vect, offset + ret_len,	member_id2);		//
			ret_len += ComntStream.getDataSize(member_id2);

			double_value0	= ComntStream.readStream(byte_vect, offset + ret_len,	double_value0);		//9 当前浮点值
			ret_len += ComntStream.getDataSize(double_value0);

			double_value1	= ComntStream.readStream(byte_vect, offset + ret_len,	double_value1);
			ret_len += ComntStream.getDataSize(double_value1);

			double_value2	= ComntStream.readStream(byte_vect, offset + ret_len,	double_value2);
			ret_len += ComntStream.getDataSize(double_value2);

			double_value3	= ComntStream.readStream(byte_vect, offset + ret_len,	double_value3);
			ret_len += ComntStream.getDataSize(double_value3);

			state_value0	= ComntStream.readStream(byte_vect, offset + ret_len,	state_value0);		//13 当前状态值(越限事项代表越限级别)
			ret_len += ComntStream.getDataSize(state_value0);

			state_value1	= ComntStream.readStream(byte_vect, offset + ret_len,	state_value1);
			ret_len += ComntStream.getDataSize(state_value1);

			state_value2	= ComntStream.readStream(byte_vect, offset + ret_len,	state_value2);
			ret_len += ComntStream.getDataSize(state_value2);

			state_value3	= ComntStream.readStream(byte_vect, offset + ret_len,	state_value3);
			ret_len += ComntStream.getDataSize(state_value3);			
				
			ComntStream.readStream(byte_vect, offset + ret_len, group_name,		0, group_name.length);		//17 事件对象组名
			ret_len += ComntStream.getDataSize((byte)1) * group_name.length;	

			ComntStream.readStream(byte_vect, offset + ret_len, member_name0,	0, member_name0.length);	//18 事件对象名1
			ret_len += ComntStream.getDataSize((byte)1) * member_name0.length;	

			ComntStream.readStream(byte_vect, offset + ret_len, member_name1,	0, member_name1.length);	//19 事件对象名2
			ret_len += ComntStream.getDataSize((byte)1) * member_name1.length;	

			ComntStream.readStream(byte_vect, offset + ret_len, member_name2,	0, member_name2.length);	//20 事件对象名3
			ret_len += ComntStream.getDataSize((byte)1) * member_name2.length;	

			ComntStream.readStream(byte_vect, offset + ret_len, char_info,		0, char_info.length);		//21 文字描述信息
			ret_len += ComntStream.getDataSize((byte)1) * char_info.length;	

			//ComntStream.readStream(byte_vect, offset + ret_len, sound_info,		0, sound_info.length);		//22 语音描述信息
			//ret_len += ComntStream.getDataSize((byte)1) * sound_info.length;	
			
			return ret_len;
		}				
	}
	
	public static class MSG_SYSEVENT implements IMSG_READWRITESTREAM  {
		public MSG_SYSEVENT_ITEM[] sysevent_vect = null;
		
		public void clean() {
			sysevent_vect = null;
		}
		
		public void clone(MSG_SYSEVENT msg) {
			if (msg.sysevent_vect == null) {
				msg.sysevent_vect = null;
			}
			else {
				if (sysevent_vect == null || sysevent_vect.length != msg.sysevent_vect.length)  sysevent_vect = new MSG_SYSEVENT_ITEM [msg.sysevent_vect.length];				
				for (int i = 0; i < sysevent_vect.length; i++)  sysevent_vect[i] = msg.sysevent_vect[i].clone();				
			}						
		}
		
		public MSG_SYSEVENT clone() {
			MSG_SYSEVENT ret_msg = new MSG_SYSEVENT();
			ret_msg.clone(this);
			
			return ret_msg;
		}
		
		public int toDataStream(ComntVector.ByteVector byte_vect) {
			int ret_len   = 0;
			int i = 0, vect_size = 0;
			
			vect_size = sysevent_vect.length;
			ret_len += ComntStream.writeStream(byte_vect, vect_size);
			
			for (i = 0; i < vect_size; i++) {
				ret_len += sysevent_vect[i].toDataStream(byte_vect);
			}
			
			return ret_len;
		}		
		
		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset) {
			int ret_len = 0;
			int i = 0, vect_size = 0;

			vect_size = ComntStream.readStream(byte_vect, offset + ret_len, vect_size); 
			ret_len += ComntStream.getDataSize(vect_size);	
			
			sysevent_vect = new MSG_SYSEVENT_ITEM[vect_size];
			for (i = 0; i < vect_size; i++) {
				sysevent_vect[i] = new MSG_SYSEVENT_ITEM();
				ret_len += sysevent_vect[i].fromDataStream(byte_vect, offset + ret_len);
			}
			
			return ret_len;
		}						
	}
	
	public static class MSG_RELOADPARA implements IMSG_READWRITESTREAM  {
		public byte[]     reload_mask = new byte[ComntDef.YD_32_STRLEN];
		
		public void clean() {
			for (int i = 0; i < ComntDef.YD_32_STRLEN; i++) reload_mask[i] = 0;
		}
		
		public void clone(MSG_RELOADPARA msg) {
			for (int i = 0; i < ComntDef.YD_32_STRLEN; i++) {
				reload_mask[i] = msg.reload_mask[i];
			}
		}
		
		public MSG_RELOADPARA clone() {
			MSG_RELOADPARA ret_msg = new MSG_RELOADPARA();
			ret_msg.clone(this);
			
			return ret_msg;
		}
		
		public void makeCancelManTaskMsg(byte[] reload_code) {
			for (int i = 0; i < ComntDef.YD_32_STRLEN; i++) {
				this.reload_mask[i] = reload_code[i];
			}
		}
		
		public int toDataStream(ComntVector.ByteVector byte_vect) {
			int ret_len   = 0;
			
			ret_len += ComntStream.writeStream(byte_vect, reload_mask, 0, reload_mask.length);
			
			return ret_len;
		}

		public int fromDataStream(ComntVector.ByteVector byte_vect, int offset) {
			int ret_len = 0;

			ComntStream.readStream(byte_vect, offset + ret_len, reload_mask, 0, reload_mask.length);
			ret_len += ComntStream.getDataSize(reload_mask[0]) * reload_mask.length;

			return ret_len;
		}
	}

	public static void makeTaskResultComntMsg(MSG_SERVER_STRUCT comnt_msg, MSG_TASKRESULT taskresult_msg, int uuid) {
		comnt_msg.msg_head.msg_type = ComntDef.YD_WEBMSGTYPE_TASKRESULT;
		comnt_msg.msg_head.uuid     = uuid;
		
		taskresult_msg.toDataStream(comnt_msg.msg_body.byte_vect);
		
		comnt_msg.msg_head.data_len = comnt_msg.msg_body.byte_vect.size();		
	}

	public static void makeTaskResultComntMsg(MSG_SERVER_STRUCT comnt_msg, int uuid, int rtuid, byte taskresult, 
											  byte errcode, byte errproc, String errhost, int user_data1, int user_data2) {
		MSG_TASKRESULT taskresult_msg = new MSG_TASKRESULT();
		taskresult_msg.makeMsgTaskResult(rtuid, taskresult, errcode, errproc, errhost, user_data1, user_data2);
		
		makeTaskResultComntMsg(comnt_msg, taskresult_msg, uuid);
	}
	
	public static void makeTaskResultComntMsg(MSG_SERVER_STRUCT comnt_msg, MSG_CLIENT_STRUCT src_comnt_msg, byte taskresult, byte errcode, byte errproc, String errhost) {		
		MSG_TASK task_msg = new MSG_TASK();
		task_msg.fromDataStream(src_comnt_msg.msg_body.byte_vect, 0);
		
		MSG_TASKRESULT taskresult_msg = new MSG_TASKRESULT(); 
		
		if (src_comnt_msg.msg_head.msg_type == ComntDef.YD_MSGTYPE_TASK) {		
			taskresult_msg.makeMsgTaskResult(task_msg.rtuid, taskresult, errcode, errproc, errhost, 0, 0);
		}
		else {
			taskresult_msg.makeMsgTaskResult((int)-1, taskresult, errcode, errproc, errhost, 0, 0);			
		}
		
		makeTaskResultComntMsg(comnt_msg, taskresult_msg, src_comnt_msg.msg_head.uuid);
	}

	public static void makeReloadParaComntMsg(MSG_CLIENT_STRUCT comnt_msg, MSG_RELOADPARA reload_msg, int uuid, String user_name) {		
		reload_msg.toDataStream(comnt_msg.msg_body.byte_vect);
		comnt_msg.msg_head.makeMsgHead(ComntDef.YD_WEBMSGTYPE_RELOADPARA, uuid, comnt_msg.msg_body.byte_vect.size(), 1, 0, user_name);
	}

	public static void makeReloadParaComntMsg(MSG_CLIENT_STRUCT comnt_msg, int uuid, String user_name, byte[] reload_mask) {
		MSG_RELOADPARA reload_msg = new MSG_RELOADPARA();
		reload_msg.makeCancelManTaskMsg(reload_mask);
		
		makeReloadParaComntMsg(comnt_msg, reload_msg, uuid, user_name);
	}


}
