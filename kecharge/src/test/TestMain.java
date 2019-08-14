/** TestMain.java  2016-7-9
 *  
 */
package test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.fastjson.JSONObject;
import com.wo.comnt.ComntCfgEVCP;
import com.wo.comnt.ComntMsgProc;
import com.wo.http.HttpUtil;
import com.wo.util.CommFunc;
import com.wo.util.Constant;
import com.wo.util.JDBCDao;
import com.wo.util.Log4jUtil;
import com.wo.util.WebConfig;


/**
 * @author Administrator
 * 
 */
public class TestMain {

	private static final int a = 0;
	public static void main(String[] args) throws IOException, ParseException {
		
		
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.add(Calendar.DAY_OF_MONTH, -1);
		
		Calendar endCalendar = Calendar.getInstance();

		System.out.println(a);
//		List<Integer> monthList = new ArrayList<Integer>();
//		
//		while () {
//			monthList.add(startYm);
//			startYm++;
//		}
		
		
		/**
		 * 获取访问令牌，该令牌在超过2小时不使用或重新获取令牌时失效
		 */
		String smsUrl = "http://192.168.19.9:8080/kecharge/login.json";
		 smsUrl = "http://192.168.19.9:8080/kecharge/listStationGPS.json";
//		 smsUrl = "http://192.168.19.9:8080/kecharge/getPileState.json";
//		 smsUrl = "http://192.168.19.9:8080/kecharge/getPileGps.json";
//		 smsUrl = "http://192.168.19.9:8080/kecharge/getPileRate.json";
//		 smsUrl = "http://192.168.19.9:8080/kecharge//chargemonitor/listChargeMonitor.json";

		 
//		 smsUrl = "http://192.168.19.9:8080/kecharge/chargeStart.json";
//		smsUrl = "http://192.168.19.9:19999/kecharge//chargeData.json";

//		 smsUrl = "http://192.168.19.9:19999/kecharge/chargeOver.json";
//		smsUrl = "http://139.199.61.63/kelin/pile/login.json";
		Map<String, String> param = new HashMap<String, String>();
		JSONObject json = new JSONObject();
		 json.put("userName", "xl-super");
		 json.put("passWord", "E10ADC3949BA59ABBE56E057F20F883E");
//		 json.put("pileNo", "ke10002");
//		 json.put("gunNo", "1");
//		 json.put("serialNumber", "9900028981");
//		 json.put("chargeMoney", "10.2000");

//		json.put("serialNumber", "2017071925");
//		json.put("chargeFlag ", "1");
		
//		json.put("pileNo", "KE0000000066");
//		json.put("gunNo", "1");
//		json.put("startDate", "2016-01-01 09:10:01");
//		json.put("endDate", "2016-02-01 15:04:02");
//		json.put("chargeMoney", "2.43");
//		json.put("totalElectricity", "12");
//		json.put("endCause", "充满");
		
		//		
		// json = CommFunc.errorInfo(FINALDef.OK, "");
		//		
		// json.put("chargTotalElectricity","5");
		// json.put("chargHighElectricity", "1");
		// json.put("chargFlatElectricity", "2");
		// json.put("chargLowElectricity", "1");
		// json.put("chargTipElectricity", "1");
		//		
		// json.put("AVoltage","220");
		// json.put("BVoltage","215");
		// json.put("CVoltage","214");
		//		
		// json.put("ACurrent","0.5");
		// json.put("BCurrent","0.8");
		// json.put("CCurrent","1.2");
		//
		param.put("queryJsonStr", json.toString());
		param.put("token", "00D95709C047FD4B8E136B23A564985D");
		//		
//		 smsUrl = "http://192.168.19.9:19999/kecharge/getPileState.json";
		// smsUrl = "http://192.168.19.9:199999/kecharge/listStationGPS.json";
//		 smsUrl = "http://139.199.61.63/kelin/pile/chargeStart.json";
//		 smsUrl = "http://139.199.61.63/kelin/pile/chargeOver.json";
//		 smsUrl = "http://192.168.19.9:19999/kecharge/getPileGps.json";
//		 smsUrl = "http://192.168.19.9:19999/kecharge/getPileChargeRcd.json";

//		String serialNumber = "909";
//		System.out.println(serialNumber.indexOf("909"));
//		 String response =HttpUtil.doPost(smsUrl, param);
//		System.out.println("response : "+ response);
	}
}
