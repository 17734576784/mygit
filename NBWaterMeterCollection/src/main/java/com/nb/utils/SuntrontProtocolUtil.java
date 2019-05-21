/**   
* @Title: XtProtocolUtil.java 
* @Package com.nb.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年5月15日 下午3:38:34 
* @version V1.0   
*/
package com.nb.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.nb.logger.LogName;
import com.nb.logger.LoggerUtil;
import static com.nb.utils.DateUtils.*;
import static com.nb.utils.BytesUtils.*;

/**
 * @ClassName: XtProtocolUtil
 * @Description: 新天科技移动规约解析工具
 * @author dbr
 * @date 2019年5月15日 下午3:38:34
 * 
 */
public class SuntrontProtocolUtil {

	/** 
	* @Title: parseDataMsg 
	* @Description: 解析数据点信息 不同的数据存入对应的的redis队列中 
	* @param @param msgJson
	* @param @return
	* @param @throws Exception    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	public static JSONObject parseDataMsg(JSONObject msgJson) throws Exception {
		byte[] msg = BytesUtils.hexStringToBytes(msgJson.getString("value"));
		/** 验证接收到的消息，并返回数据部分 */
		byte[] data = validateMsg(msg);
		if (data == null || data.length == Constant.ZERO) {
			return null;
		}
		JSONObject dataJson = parseData(data, msgJson);
		return dataJson;
	}

	/** 
	* @Title: parseData 
	* @Description: 解析数据部分
	* @param @param data
	* @param @param msgJson
	* @param @return
	* @param @throws Exception    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	private static JSONObject parseData(byte[] data, JSONObject msgJson) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		JSONObject dataJson = new JSONObject();
		try {
			/** 日冻结时间日月年 */
			byte[] data0 = new byte[Constant.THREE];
			dis.read(data0);
			String reportTime = BytesUtils.bcdToString(data0);
			reportTime = formatNoCharDate(parseDate(reportTime, "ddMMyy"));

			/** data0的 0 点冻结表底数 */
			byte[] data1 = new byte[Constant.FOUR];
			dis.read(data1);
			double totalFlow = BytesUtils.getReserveInt(data1) * 1D / Constant.NUM_1000;

			/** 当天48 个点累计增量数据 */
			byte[] data2 = new byte[Constant.NUM_48 * Constant.TWO];
			dis.read(data2);

			/** 当前表底数 */
			byte[] data3 = new byte[Constant.FOUR];
			dis.read(data3);
			double currentTotalFlow = BytesUtils.getReserveInt(data3) * 1D / Constant.NUM_1000;

			/** 备用字节 */
			byte[] NC = new byte[Constant.FOUR];
			dis.read(NC);

			/** CCID 号 */
			byte[] data4 = new byte[Constant.TEN];
			dis.read(data4);

			/** IMEI 号 */
			byte[] data5 = new byte[Constant.EIGHT];
			dis.read(data5);

			/** 当前正向累计流量 */
			byte[] data6 = new byte[Constant.FOUR];
			dis.read(data6);
			double forwardTotalFlow = BytesUtils.getReserveInt(data6) * 1D / Constant.NUM_1000;

			/** 当前正向累计流量 */
			byte[] data7 = new byte[Constant.FOUR];
			dis.read(data7);
			double reverseTotalFlow = BytesUtils.getReserveInt(data7) * 1D / Constant.NUM_1000;

			/** 当天 48 个点增量（Data2）符号标识 */
			byte data9 = dis.readByte();
			/** data0的下一天 */
			byte data10 = dis.readByte();
			/** data10有效时，data10的0点数据 (data0的24点的数据) */
			byte[] data11 = new byte[Constant.FOUR];
			dis.read(data11);
			Double historyTotalFlow = 0D;
			int historyDate = -1;
			if (bcdToInt(data10) >= Constant.ONE && bcdToInt(data10) <= Constant.NUM_31) {
				historyTotalFlow = BytesUtils.getReserveInt(data11) * 1D / Constant.NUM_1000;
				historyDate = bcdToInt(data10);
			}

			/** 备用字节 */
			byte[] bak2 = new byte[Constant.SIX];
			dis.read(bak2);

			byte[] comm = new byte[Constant.FOUR * Constant.SIX];
			dis.read(comm);

			dataJson.put("reportTime", reportTime);
			dataJson.put("totalFlow", totalFlow);
			dataJson.put("currenttotalFlow", currentTotalFlow);
			dataJson.put("CCID", bytesToHex(data4));
			dataJson.put("IMEI", bytesToHex(data5));

			dataJson.put("forwardTotalFlow", forwardTotalFlow);
			dataJson.put("reverseTotalFlow", reverseTotalFlow);
			dataJson.put("historyDate", historyDate);
			dataJson.put("historyTotalFlow", historyTotalFlow);
			dataJson.putAll(parseCumulativeIncrementalData(data2, data9));
			dataJson.put("comm", parseCommData(comm));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataJson;
	}

	/** 
	* @Title: parseCumulativeIncrementalData 
	* @Description: 解析累计增量数据
	* @param @param data2
	* @param @param data9
	* @param @return
	* @param @throws Exception    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	private static JSONObject parseCumulativeIncrementalData(byte[] data2, byte data9) throws Exception {
		JSONObject crementDataJson = new JSONObject();
		ByteArrayInputStream bais = new ByteArrayInputStream(data2);
		DataInputStream dis = new DataInputStream(bais);
		List<Double> dataList = new LinkedList<>();
		for (int i = 0; i < data2.length / Constant.TWO; i++) {
			byte[] data = new byte[2];
			try {
				dis.read(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
			double symbol = 1.0;
			if (data9 == 0xAA && (data[0] & 0x80) == 0x80) { // fu
				data[0] = (byte) (data[0] & 0x7F);
				symbol = -1;
			}
			double result = symbol * BytesUtils.getShort(data) / Constant.NUM_1000;
			dataList.add(result);
		}
		crementDataJson.put("dataList", dataList);

		return crementDataJson;
	}

	/** 
	* @Title: parseCommData 
	* @Description: 解析用户数据域的公共部分
	* @param @param comm
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	public static JSONObject parseCommData(byte[] comm) {
		ByteArrayInputStream bais = new ByteArrayInputStream(comm);
		DataInputStream dis = new DataInputStream(bais);
		JSONObject commJson = new JSONObject();
		try {
			/** 终端时钟 分时日月年 */
			byte[] data0 = new byte[Constant.FIVE];
			dis.read(data0);
			String deviceTime = BytesUtils.bcdToString(data0);
			deviceTime = formatNoCharDate(parseDate(deviceTime, "mmHHddMMyy"));
			/** 电池电压 */
			double batteryVoltage = dis.readByte() * 1D / Constant.TEN;
			/** 信号质量 */
			int csq = BytesUtils.bcdToInt(dis.readByte());

			/** 版本号 */
			byte[] version = new byte[5];
			dis.read(version);

			/** ST0 */
			byte st0 = dis.readByte();
			/** ST1 */
			byte st1 = dis.readByte();
			/** 信号强度 */
			byte[] rsrp = new byte[Constant.TWO];
			dis.read(rsrp);
			/** 信噪比 */
			byte[] snr = new byte[Constant.TWO];
			dis.read(snr);
			/** 覆盖等级 */
			byte cc = dis.readByte();
			/** ST2 */
			byte st2 = dis.readByte();

			commJson.put("deviceTime", deviceTime);
			commJson.put("batteryVoltage", batteryVoltage);
			commJson.put("csq", csq);
			commJson.put("version", hexToAscii(bytesToHex(version)));
			commJson.put("st0", parsetSt0(st0));
			commJson.put("st1", parsetSt1(st1));
			commJson.put("rsrp", bytesToHex(rsrp));
			commJson.put("snr", bytesToHex(snr));
			commJson.put("cc", cc);
			commJson.put("st2", parsetSt2(st2));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return commJson;
	}

	/** 
	* @Title: parsetSt2 
	* @Description: 解析st2
	* @param @param st2
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	private static JSONObject parsetSt2(byte st2) {
		JSONObject json = new JSONObject();
		byte[] bytes = getByteArray(st2);
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		DataInputStream dis = new DataInputStream(bais);
		try {
			/** bit7 */
			json.put("bit7", dis.readByte());
			/** 反流告警 */
			json.put("reverseFlow", dis.readByte());
			/** 电子模块分离 */
			json.put("electronicModuleSeparation", dis.readByte());
			/** 管段空管 */
			json.put("emptyPipe", dis.readByte());
			/** 电池无电 */
			json.put("batteryWithoutElectricity", dis.readByte());
			/** 倾斜 */
			json.put("tiltAlarm", dis.readByte());
			/** 微漏 */
			json.put("microleakage", dis.readByte());
			/** 不通讯关阀状态 */
			json.put("noncommunicatingShutdownStatus", dis.readByte());

		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error("解析新天科技ST2异常，" + e.getMessage());
		}
		return json;
	}

	/** 
	* @Title: parsetSt1 
	* @Description: 解析st1 
	* @param @param st1
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	private static JSONObject parsetSt1(byte st1) {
		JSONObject json = new JSONObject();
		byte[] bytes = getByteArray(st1);
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		DataInputStream dis = new DataInputStream(bais);
		try {
			/** 漏气 */
			json.put("leakFlag", dis.readByte());
			/** 流量异常 */
			json.put("flowAnomaly", dis.readByte());
			/** 不采样关阀 */
			json.put("unsampledShutoff", dis.readByte());
			/** 直读模块异常 */
			json.put("directReadingAbnormal", dis.readByte());
			/** 校时状态 */
			json.put("timingStatus", dis.readByte());
			/** 强制开阀状态 */
			json.put("forcedOpeningstate", dis.readByte());
			/** 透支状态 */
			json.put("overdraft", dis.readByte());
			/** 余额不足状态 */
			json.put("insufficientBalance", dis.readByte());

		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error("解析新天科技ST1异常，" + e.getMessage());
		}
		return json;
	}

	/** 
	* @Title: parsetSt0 
	* @Description: 解析St0 
	* @param @param st0
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	private static JSONObject parsetSt0(byte st0) {
		JSONObject json = new JSONObject();
		byte[] bytes = getByteArray(st0);
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		DataInputStream dis = new DataInputStream(bais);
		try {
			/** 开窗标志 */
			json.put("windowFlag", dis.readByte());
			/** 终端启用标志 */
			json.put("terminalEnablationFlag", dis.readByte());
			/** 终端电池电压 */
			json.put("terminalBatteryVoltage", dis.readByte());
			/** 磁干扰 */
			json.put("magneticInterference", dis.readByte());
			/** 按键触发位 */
			json.put("keyTrigger", dis.readByte());
			/** 表计电池电压 */
			json.put("meterBatteryVoltage", dis.readByte());
			/** 阀门状态 */
			json.put("valveStatus", dis.readShort());

		} catch (Exception e) {
			e.printStackTrace();
			LoggerUtil.logger(LogName.ERROR).error("解析新天科技ST0异常，" + e.getMessage());
		}
		return json;
	}

	/** 
	* @Title: validateMsg 
	* @Description: T验证消息，并返回数据部分
	* @param @param msg
	* @param @return
	* @param @throws Exception    设定文件 
	* @return byte[]    返回类型 
	* @throws 
	*/
	private static byte[] validateMsg(byte[] msg) throws Exception {

		ByteArrayInputStream bais = new ByteArrayInputStream(msg);
		DataInputStream dis = new DataInputStream(bais);
		byte[] byteData = null;
		try {
			/** 起始字符 */
			byte start = dis.readByte();
			if (start != 0X68) {
				System.out.println("起始字符错误");
				return null;
			}
			/** 表类型 */
//			byte meterType = 
			dis.readByte();
			/** 地址域 */
			byte[] addr = new byte[Constant.SEVEN];
			dis.read(addr);
			/** 控制码 */
			byte[] control = new byte[Constant.TWO];
			dis.read(control);
			if (!bytesToHex(control).equals("D0BD") || !bytesToHex(control).equals("D00F")) {
				System.out.println("控制码错误");
				return null;
			}
			/** 数据长度 */
			byte[] len = new byte[Constant.TWO];
			dis.read(len);
			/** 数据 */
			byte[] datas = new byte[getReserveShort(len)];
			dis.read(datas);
			byteData = datas;
			/** 校验字节 */
			byte[] crc = new byte[Constant.TWO];
			dis.read(crc);
			/** 获取待验证数据，并计算CRC值 */
			byte[] crcData = new byte[Constant.TEN + Constant.THREE + getReserveShort(len)];
			System.arraycopy(msg, Constant.ZERO, crcData, Constant.ZERO, crcData.length);
			String calcCrc = getReserveCrc(crcData);
			/** 验证CRC与计算值 */
			if (!bytesToHex(crc).equals(calcCrc)) {
				System.out.println("CRC校验失败 " + bytesToHex(crc) + ":" + calcCrc);
				return null;
			}
			/** 结束字符 */
			byte end = dis.readByte();
			if (end != 0X16) {
				System.out.println("结束字符错误");
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteData;
	}

	/** 
	* @Title: parseCommandMsg 
	* @Description: 解析下行命令上报结果信息 
	* @param @param msgJson
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	public static JSONObject parseCommandMsg(JSONObject res) {
		System.out.println(res);
		byte[] msg = BytesUtils.hexStringToBytes(res.getString("value"));
		/** 验证接收到的消息，并返回数据部分 */
		byte[] data;
		try {
			data = validateMsg(msg);
			if (data == null || data.length == Constant.ZERO) {
				return null;
			}
			
			JSONObject dataJson = parseCommandData(data);
			return dataJson;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	/** 
	* @Title: parseCommandData 
	* @Description: 解析上报命令回复数据 
	* @param @param data
	* @param @return    设定文件 
	* @return JSONObject    返回类型 
	* @throws 
	*/
	public static JSONObject parseCommandData(byte[] data) {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		DataInputStream dis = new DataInputStream(bais);
		JSONObject dataJson = new JSONObject();

		/** 当前阀控状态 */
		byte vavleState;
		try {
			vavleState = dis.readByte();
			if (vavleState == 0xAA) {
				vavleState = Constant.TWO;
			} else if (vavleState == 0x55) {
				vavleState = Constant.FOUR;
			}
			dataJson.put("vavleState", vavleState);
			byte[] comm = new byte[Constant.FOUR * Constant.SIX];
			dis.read(comm);

			dataJson.put("comm", parseCommData(comm));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataJson;
	}

	/** 
	* @Title: sendVavleCommand 
	* @Description: 新天科技移动平台下发阀门控制命令
	* @param @param vavleOperate
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String sendVavleCommand(int vavleOperate) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		String dataFrame = "";
		try {
			/** 起始字符 */
			dos.writeByte(0X68);
			byte[] addr = new byte[Constant.SEVEN];
			dos.write(addr);
			dos.writeShort(0X500F);
			dos.writeShort(Constant.ELEVEN);
			byte operate = 0;
			/** 开阀操作 */
			if (vavleOperate == Constant.ONE) {
				operate = (byte) 0xAA;
			} else if (vavleOperate == Constant.TWO) {
				operate = (byte) 0x55;
			}
			dos.writeByte(operate);
			dos.writeByte(Constant.ZERO);
			byte[] nc = new byte[Constant.NINE];
			dos.write(nc);

			dos.write(BytesUtils.hexStringToBytes(getReserveCrc(bos.toByteArray())));
			dos.writeByte(0X16);
			dataFrame = BytesUtils.bytesToHex(bos.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataFrame;
	}
}
