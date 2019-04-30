package com.ke;

import static com.ke.utils.ConverterUtil.toByte;
import static com.ke.utils.ConverterUtil.toDouble;
import static com.ke.utils.ConverterUtil.toInt;
import static com.ke.utils.ConverterUtil.toStr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ke.common.CommFunc;
import com.ke.common.Constant;
import com.ke.logger.LogName;
import com.ke.logger.LoggerUtil;
import com.ke.mapper.ChargeMapper;
import com.ke.mapper.ChargeMonitorMapper;
import com.ke.mapper.MemberOrdersMapper;
import com.ke.mapper.PileMapper;
import com.ke.mapper.ShiroMapper;
import com.ke.mapper.SubstparaMapper;
import com.ke.model.ChargeMonitor;
import com.ke.model.LoginUser;
import com.ke.model.MemberOrders;
import com.ke.model.OperatorConfig;
import com.ke.model.Substpara;
import com.ke.service.IChargeMonitorService;
import com.ke.service.IChargeService;
import com.ke.service.IPileService;
import com.ke.service.IShiroService;
import com.ke.service.IStaionService;
import com.ke.service.ITaskService;
import com.ke.serviceimpl.ChargeServiceImpl;
import com.ke.utils.ConverterUtil;
import com.ke.utils.DateUtil;
import com.ke.utils.JedisUtil;
import com.ke.utils.SerializeUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class KechargeApplicationTests {

	@Resource
	private SubstparaMapper substparaMapper;
	
	@Resource
	private PileMapper pileMapper;
	
	@Resource
	private IPileService pileService;
	
	@Resource
	private ShiroMapper shiroMapper;
	
	@Resource
	private ChargeMonitorMapper chargeMonitorMapper; 
	
	@Resource
	private MemberOrdersMapper memberOrdersMapper;
	
	@Autowired
	private ChargeServiceImpl chargeServiceImpl;
	
	@Autowired
	private IStaionService staionService;
	@Resource
	private ChargeMapper chargeMapper;
	@Test
	public void ChargeTest() throws Exception{

		JSONObject json = new JSONObject();
		json.put("stationNo", "1000002");
//		json.put("startDate", "20190401");
//		json.put("endDate", "20190405");		
//		json.put("pileNo", "KE10002");

		
		System.out.println(pileService.listGunState(json.toJSONString()));
//		System.out.println(pileService.listGunState(json.toJSONString()).toJSONString());;
			
//		JSONObject rtnJson = new JSONObject(); 
//		String stationNo = "1000002";
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("stationNo", stationNo);
//		param.put("operatorId", 12);
//		boolean flag = substparaMapper.isStationOfOperator(param);
//		System.out.println("flag : "+ flag);
//		if (!flag) {
//			rtnJson = CommFunc.errorInfo(Constant.REQUEST_BAD, "充电站[" + stationNo + "]不属于该运营商");
//		}
//		System.out.println(rtnJson);
//		String paySerialNumber = "009900000001";

//		ChargeMonitor chargeMonitor = new ChargeMonitor();
//		chargeMonitor.setSerialnumber(paySerialNumber);
//		chargeMonitor.setStartPush((byte) 1);
//		chargeMonitor.setStartPushTime(new Date());
//		chargeMonitorMapper.updateChargeMonitor(chargeMonitor);
		
//		boolean rtuOnline = CommFunc.rtuOnlineFlag(50);
//		System.out.println(rtuOnline);
		
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("pileNo", "ke10002");
//		param.put("gunNo", 13);
//		Map<String, Object> pileInfo = this.chargeMapper.getPileInfo(param);
//		byte cpFlag = toByte(pileInfo.get("cpFlag"));
//		System.out.println("cpFlag  : "+ cpFlag);
//		MemberOrders memberOrders = new MemberOrders();
//		memberOrders.setMemberId(-1);
//		memberOrders.setOperatorId(toInt(pileInfo.get("operatorId")));
//		memberOrders.setSubstId(toInt(pileInfo.get("stationId")));
//		memberOrders.setPileId(toInt(pileInfo.get("pileId")));
//		memberOrders.setGunId(toInt(1));
//		memberOrders.setPileCode("ke10002");
//		
//		memberOrders.setSerialnumber("9999999999");
//		memberOrders.setTradeDate(new Date());
//		memberOrders.setPrechargeMoney(10000);
//		memberOrders.setPrepayType(Constant.BALANCE_CHARGING);
//		memberOrders.setAppFlag(Constant.CLIENTTYPE_INTERFACE);
//		memberOrders.setChargeState(Constant.CHARGINGSTATE_V2_STARTWAITING);
		
//		System.out.println(this.memberOrdersMapper.insertmemberOrders(memberOrders));
		
//		MemberOrders order = this.memberOrdersMapper.getmemberOrders("9999999999");
//		System.out.println(order.getSerialnumber());
		
//		String orderKey = Constant.ORDER + "2018091214151300000001017";
//		Map<String,String> orderMap = JedisUtil.hgetAll(orderKey);
//		System.out.println("orderMap : "+ orderMap);
//		String gunStateKey = Constant.GUNSTATE + orderMap.get("pileId") + "_" + orderMap.get("gunId");
//		System.out.println("gunStateKey : "+ gunStateKey);
//		Map<String,String> gunStateMap = JedisUtil.hgetAll(gunStateKey);
//		System.out.println(gunStateMap);
//		System.out.println(this.memberOrdersMapper.getMemberIdByPhone("13226359257"));
//		String key = Constant.OPERATORCONFIG_PREFIX + 5;
//		OperatorConfig operatorConfig = (OperatorConfig) SerializeUtil.deserialize(JedisUtil.get(key.getBytes()));
//		
//		System.out.println(operatorConfig.toString());
//		key = Constant.SERIALNUMBER_PREFIX;
//		Map<String, String> map = JedisUtil.hgetAll(key);
//		System.out.println(map);
//		System.out.println(map.containsKey("444"));

		
//		String paySerialNumber = "9900000001";
//		String pileNo = "KE0000001000";
//		String gunNo ="1";
//		// 查询最近180天内的数据
//		JSONObject param = new JSONObject();
//		param.put("serialNumber", paySerialNumber);
//		param.put("pileNo", pileNo);
//		param.put("gunNo", gunNo);
//		
//		System.out.println(this.chargeService.getPileChargeRcd(param.toJSONString()));
		
//		System.out.println(this.chargeServiceImpl.getOperatorToken(1));
		
	}
	
	private String getendCause(int endCause){
		Map<String,String> endCauseMap = JedisUtil.hgetAll(Constant.ENDCAUSE_DICTION);
		return endCauseMap.get(toStr(endCause));
	}
//	@Test
	public void testTask() throws Exception{
//		taskService.backUpChargeOrderTask();
//		taskService.backupChargeMonitorTask();
	}
	
//	@Test
	public void testChargeMonitor() throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		List<Integer> yearList = new ArrayList<>();
		yearList.add(2019);
		param.put("yearList", yearList);
		param.put("beginDate", "20190106");
		param.put("endDate", "20190112");
		param.put("serialnumber", "1");
		param.put("operatorId", 1);
		System.out.println(chargeMonitorMapper.listChargeMonitor(param).get(0).toString());
	}
	
	
//	@Test
	public void listChargeRecordMonitor(){
		List<Integer> yearList = new ArrayList<Integer>(2);
		yearList.add(2018);
		yearList.add(2019);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("yearList", yearList);
		param.put("beginDate", "20181101");
		param.put("endDate", "20190112");
		param.put("operatorId", 1);
 		
		List<ChargeMonitor> list = chargeMonitorMapper.listChargeMonitor(param);
		System.out.println(list);
	}
	
//	@Test
//	public void getAccessAuthority(){
//		System.out.println(shiroMapper.getAccessAuthority("xl-super"));
//	}
	
//	@Test
	public void isPileOfOPerator(){
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("pileNo", "ke10002");
		param.put("operatorId", 12);
		System.out.println(pileMapper.isPileOfOperator(param));
	}
	
//	@Test
	public void test(){
		byte[] key = (Constant.TOKEN_PREFIX + "3").getBytes();
		byte[] value = JedisUtil.get(key);
		LoginUser loginUser = (LoginUser) SerializeUtil.deserialize(value);
		System.out.println(loginUser);
	}

//	@Test
	public void getPileStatus() throws Exception{
		JSONObject json = new JSONObject();
		json.put("pileNo", "ke10002");
		System.out.println(pileService.getPileState(json.toJSONString()));
	}
	
//	@Test
	public void getPileGPS() throws Exception{
		JSONObject json = new JSONObject();
		json.put("pileNo", "ke10002");
		System.out.println(pileService.getPileGps(json.toJSONString()));
	}
	
//	@Test
	public void getPileRate() throws Exception{
		JSONObject json = new JSONObject();
		json.put("pileNo", "ke10001");
		System.out.println(pileService.getPileRate(json.toJSONString()));
	}
	
	
//	@Test
	public void listStationGPSTest() {
		System.out.println(Runtime.getRuntime().availableProcessors());

		int size = 50000000;
		List<Substpara> list = new ArrayList<>();
		System.out.println("开始生成：");
		for (int i = 0; i < size; i++) {
			Substpara s = new Substpara();
			s.setId(i);
			s.setSubstName(""+i);
			s.setLongitude(i * 1D);
			s.setLatitude(i*1D);
			list.add(s);
		}
		System.out.println("生成结束");
		System.out.println("开始串行处理");
		long start = System.nanoTime();
		List<JSONObject> count = list.stream()	
				.filter(substpara -> substpara!= null)
				.map(substpara -> getSubstpara(substpara)).collect(Collectors.toList());
				
		long end = System.nanoTime();
		long dis = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("串行结束 耗时：" + dis);
		System.out.println("开始并行");
		start = System.nanoTime();
		count = list.stream().parallel().filter(substpara -> substpara != null).map(substpara -> getSubstpara(substpara))
				.collect(Collectors.toList());
		end = System.nanoTime();
		dis = TimeUnit.NANOSECONDS.toMillis(end - start);
		System.out.println("bing行结束 耗时：" + dis);

	}

	private JSONObject getSubstpara(Substpara substpara) {
		JSONObject jsonItem = new JSONObject();
		jsonItem.put("stationNo", ConverterUtil.toStr(substpara.getId()));
		jsonItem.put("stationName", ConverterUtil.toStr(substpara.getSubstName()));
		jsonItem.put("lng", ConverterUtil.toStr(substpara.getLongitude()));
		jsonItem.put("lat", ConverterUtil.toStr(substpara.getLatitude()));
		return jsonItem;
	}

}
