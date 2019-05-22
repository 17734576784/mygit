/**   
* @Title: MainTest.java 
* @Package com.nb 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年3月11日 下午2:21:57 
* @version V1.0   
*/
package com.nb;

import static com.nb.utils.BytesUtils.getReserveCrc;
import static com.nb.utils.ConverterUtils.toInt;
import static com.nb.utils.ConverterUtils.toStr;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nb.exception.ResultBean;
import com.nb.httputil.HttpsClientUtil;
import com.nb.model.NbCommand;
import com.nb.model.fx.FXReport;
import com.nb.model.jd.Battery;
import com.nb.model.jd.DeviceAlarm;
import com.nb.model.jd.PeriodReport;
import com.nb.model.xt.SuntrontBattery;
import com.nb.model.xt.SuntrontMeter;
import com.nb.model.xt.SuntrontWaterMeter;
import com.nb.model.xt.SuntrontWaterMeterAlarm;
import com.nb.task.FxTelecomCallDataTask;
import com.nb.utils.BytesUtils;
import com.nb.utils.CommFunc;
import com.nb.utils.CommandEnum;
import com.nb.utils.Constant;
import com.nb.utils.ConverterUtils;
import com.nb.utils.DateUtils;
import com.nb.utils.JsonUtil;
import com.nb.utils.SerializeUtils;
import com.nb.utils.SuntrontProtocolUtil;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import io.lettuce.core.protocol.CommandEncoder;

/** 
* @ClassName: MainTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2019年3月11日 下午2:21:57 
*  
*/
public class MainTest {
	
	/**
     * 获取一个字节的bit数组
     *
     * @param value
     * @return
     */
    public static byte[] getByteArray(byte value) {
        byte[] byteArr = new byte[8]; //一个字节八位
        for (int i = 7; i > 0; i--) {
            byteArr[i] = (byte) (value & 1); //获取最低位
            value = (byte) (value >> 1); //每次右移一位
        }
        return byteArr;
    }

	/**
	 * @throws UnsupportedEncodingException  
	* @Title: main 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param args    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	public static void main(String[] args) throws UnsupportedEncodingException {
		JSONObject param = new JSONObject();
		String commandData = SuntrontProtocolUtil.sendVavleCommand(param.getIntValue("operate"));
		System.out.println(commandData);
		
		
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		DataOutputStream dos = new DataOutputStream(bos);
//		int vavleOperate = 1;
//		try {
//			dos.writeByte(0X68);
//			byte[] addr = new byte[Constant.SEVEN];
//			dos.write(addr);
//			dos.writeShort(0X500F);
//			dos.writeShort(0X0B00);
//			byte operate = 0;
//			/** 开阀操作 */
//			if (vavleOperate == Constant.ONE) {
//				operate = (byte) 0xAA;
//			} else if (vavleOperate == Constant.TWO) {
//				operate = (byte) 0x55;
//			}
//			dos.writeByte(operate);
//			dos.writeByte(Constant.ZERO);
//			byte[] nc = new byte[Constant.NINE];
//			dos.write(nc);
//			
//			dos.write(BytesUtils.hexStringToBytes(getReserveCrc(bos.toByteArray())));
//			dos.writeByte(0X16);
//			String result = BytesUtils.bytesToHex(bos.toByteArray());
//			System.out.println(result);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		Date date = new Date(1558281600362L);
//		System.out.println(BytesUtils.str2Bcd("34234")[0]);
//		Calendar c = Calendar.getInstance();
//		c.add(Calendar.DAY_OF_WEEK, Constant.ONE);
//		
//		System.out.println(c.getTime());
		
//		byte[] test = new byte[] {(byte) 139,1};
//		if ((test[0] & 0x80) == 0x80) { //fu
//			test[0] = (byte) (test[0] & 0x7F);
//		}
//		System.out.println(BytesUtils.getShort(test)); 
		
		JSONObject xt = new JSONObject();
		xt.put("at", 1557909036230L);
		xt.put("imei", "867726030828687");
		xt.put("type", 1);
		xt.put("ds_id", "3200_0_5505");
		xt.put("value", "68a107250419208000d0bdad0017051900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000089860437161890071548086076504044047800000000000000000018110000000000000000005416150519241946314435300808affcc90000eeeeeeeeeeB71416");
		xt.put("dev_id", 526298861);
//		
//		System.out.println(SuntrontProtocolUtil.parseDataMsg(xt));;
		
//		String data ="68a107250419208000d0bdad0017051900000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000089860437161890071548086076504044047800000000000000000018110000000000000000005416150519241946314435300808affcc90000eeeeeeeeee";
//		byte[] msg = BytesUtils.hexStringToBytes(data);
//		String result = BytesUtils.bytesToHex(msg);
//		System.out.println(data);
//		System.out.println(result);
//		System.out.println(data.equals(result));
//		
////		byte[] a = new byte[]{104, -95, 7, 37, 4, 25, 32, -128, 0, -48, -67, -83, 0, 21, 5, 25, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -119, -122, 4, 55, 22, 24, -112, 7, 21, 72, 8, 96, 118, 80, 64, 68, 4, 120, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 84, 22, 21, 5, 25, 36, 25, 70, 49, 68, 53, 48, 8, 8, -81, -4, -55, 0, 0, -18, -18, -18, -18, -18};
//		System.out.println(BytesUtils.getReserveCrc(msg));

//		int csq = BytesUtils.bcdToInt((byte)15);			
//		System.out.println(csq + " "+ BytesUtils.byteToHex((byte)15));
		
//		JSONObject data = new JSONObject();
//		data.put("msg", xt.toJSONString());
//		data.put("nonce", "3aVj1y9+");
//		data.put("signature", "iZI3Kut7Q7qGDqSq7A6tkw==");
		
		
		
//		String name ="杜宝瑞";
//		byte[] nameByte = name.getBytes();
//		
//		String encode = Base64.getEncoder().encodeToString(nameByte);
//		System.out.println(encode);
//		System.out.println(new String(Base64.getDecoder().decode(encode), "UTF-8"));

		
//		Date date = DateUtils.utcToLocal("20190506T162216Z", DateUtils.UTC_PATTERN);
//		System.out.println(DateUtils.formatTimesTampDate(date));
//		date = DateUtils.localToUTC("2019-05-07 00:22:16", DateUtils.TIMESTAMP_PATTERN);
//		System.out.println(DateUtils.formatDateByFormat(date,  DateUtils.UTC_PATTERN));

		
//		Map<String, String> dataMap = new HashMap<String, String>();
//		dataMap.put("dailyFlow", "12400");
//		dataMap.put("dailyReverseFlow", "2400");
//
//		SuntrontWaterMeter suntrontWaterMeter = JsonUtil.map2Bean(dataMap, SuntrontWaterMeter.class);
//		System.out.println(suntrontWaterMeter.getDailyFlow()+" "+ suntrontWaterMeter.getDailyReverseFlow());
//		
//		System.out.println(DateUtils.formatTimesTampDate(new Date(1557311171497L)));
//		
//		Calendar c = Calendar.getInstance();
//		c.setTime(DateUtils.parseTimesTampDate("20190507", DateUtils.DATE_PATTERN));
////		c.set(Calendar.MINUTE, 0);
//		
//		System.out.println(DateUtils.formatTimesTampDate(c.getTime()));
		
//		Map<String, String> dataMap = new HashMap<String, String>();
//		dataMap.put("highFlowAlarm", "0");
//		SuntrontWaterMeterAlarm suntrontwatermeteralarm = JsonUtil.map2Bean(dataMap, SuntrontWaterMeterAlarm.class);
//			
//		System.out.println(suntrontwatermeteralarm.getHighFlowAlarm());
//		XtBattery x = new XtBattery();
//		x.setEvnetTime("20180417T121245Z");
//		System.out.println(x.getEventDate() + "  " + x.getEventTime());
//		
//		
//		Date date = DateUtils.utcToLocal("20190428T012017Z", DateUtils.UTC_PATTERN);
//		System.out.println(DateUtils.formatDateByFormat(date, DateUtils.DATE_PATTERN)+" " + DateUtils.formatDateByFormat(date,DateUtils.TIME_PATTERN));
//		Calendar endDate = Calendar.getInstance();
//		endDate.add(Calendar.DAY_OF_MONTH, Constant.TASK_ENDDATE);
//		System.out.println(DateUtils.formatDateByFormat(endDate.getTime(), DateUtils.DATE_PATTERN));
//		System.out.println(FxTelecomCallDataTask.class.getName());
		
//		System.out.println(DateUtils.parseTimesTampDate("20190424", DateUtils.DATE_PATTERN));
		// TODO Auto-generated method stub
		
//		ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
//		        new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());   
		
		
//		Date ymd = DateUtils.parseDate(toStr(20190423), DateUtils.DATE_PATTERN);
//		Calendar cc = Calendar.getInstance();
//		cc.setTime(ymd);
//		cc.add(Calendar.DAY_OF_MONTH, -1);
//		
//		int date = toInt(DateUtils.parseDate(cc.getTime(), DateUtils.DATE_PATTERN));
//		
//		System.out.println(date);
//		Date date = new Date(1556153491636L);
//		System.out.println(date);
//		Map<String, Object> map = new HashMap<>(6);
//		System.out.println(map.size());
//		for (int i = 0; i < 100; i++) {
//			map.put("" + i, i);
//			i++;
//		}
//		System.out.println(map.size());

//		executorService.execute(command);
		
//		System.out.println(CommandEnum.getresultValue("SEND"));
//		Map<String, String> registerInfo = new HashMap<>();
//		registerInfo.put("deviceId", "232321");
//		
//		
//		String deviceId = registerInfo.get("deviceId");
//		System.out.println(deviceId != null && !deviceId.isEmpty());

//		ResultBean<JSONObject> result = new ResultBean<JSONObject>(Constant.ERROR, "配置信息错误");
//		System.out.println(result);
		
		
//		JSONObject deviceInfo = new JSONObject();
//		deviceInfo.put("rtuId", 11);
//		deviceInfo.put("mpId", 2);
//		
//		Map<String, String> registerInfo = new HashMap<>();
//		registerInfo = JsonUtil.jsonString2SimpleObj(deviceInfo, registerInfo.getClass());
//		
//		System.out.println("registerInfo : "+ registerInfo);
		
//		FXReport fx = new FXReport();
//		fx.setCurrentDateTime(DateUtils.formatTimesTampDate(new Date()));
//		
//		System.out.println(fx.getDate() + " " + fx.getTime());
//		List<Integer> list = new ArrayList<>();
//		list.add(1);
//		list.add(2);
//		list.add(3);
//		
//		PeriodReport r = new PeriodReport();
//		r.setFlows(list);
//		
//		System.out.println(r.getFlows());
//		Map<String,String> map = new HashMap<>();
//		map.put("readTime", "1555084800338");
//		map.put("cumulativeFlow", "102");
//		map.put("positiveCumulativeFlow", "2");
//		map.put("negativeCumulativeFlow", "3");
//		map.put("peakFlowRateTime", "4");
//		map.put("startTime", "1");
//		map.put("flows", "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
//
//		int index =0;
//			PeriodReport periodReport = null;
//			try {
//				periodReport = JsonUtil.map2Bean(map, PeriodReport.class);
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//				index ++;
//			}
//			System.out.println(periodReport.toString());
//		
//		System.out.println("index : " + index);
		
		
		
//		System.out.println(DateUtils.stampToDate(System.currentTimeMillis()));
		
//		DeviceAlarm b = JsonUtil.map2Bean(map, DeviceAlarm.class);
		
//		System.out.println(b.toString());
		Long s = null;
//		System.out.println( DateUtils.stampToDate(ConverterUtils.toLong(null)));
		
//		NbCommand command = new NbCommand();
//		command.setRtuId(1);
//		command.setCommandContent("dfdfdfsdf");
//		String jsonStr = JsonUtil.jsonObj2Sting(command);
//		System.out.println(jsonStr);
//		
//		command = JsonUtil.jsonString2SimpleObj(jsonStr, NbCommand.class);
//		System.out.println(command.getRtuId());
	}

}
