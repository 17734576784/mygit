package test; 

import static com.wo.util.CommFunc.objToDbl;
import static com.wo.util.CommFunc.objToInt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONObject;

import org.aspectj.weaver.ArrayAnnotationValue;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.wo.comnt.ComntCfgEVCP;
import com.wo.comnt.ComntMsgProc;
import com.wo.mapper.ChargeMapper;
import com.wo.mapper.ChargeRecordMapper;
import com.wo.mapper.InterceptorMapper;
import com.wo.mapper.PartnerConfigMapper;
import com.wo.mapper.PartnerParaMapper;
import com.wo.mapper.PileMapper;
import com.wo.mapper.StationMapper;
import com.wo.model.ChargeRecord;
import com.wo.model.PartnerConfig;
import com.wo.model.PartnerPara;
import com.wo.model.Station;
import com.wo.service.IChargeService;
import com.wo.service.IPileService;
import com.wo.service.IStaionService;
import com.wo.util.CommFunc;
import com.wo.util.JDBCDao;


 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/applicationContext.xml","classpath:config/spring-mvc.xml"})
public class UtilTest {
	@Resource
	private  PartnerParaMapper partnerParaMapper;
	@Resource
	private InterceptorMapper interceptorMapper;
	
	@Resource 
	private StationMapper stationMapper;
	
	@Resource
	private ChargeMapper chargeMapper;
	
	@Resource
	private PileMapper pileMapper;
	
	@Autowired
	private IChargeService chargeService;
	
	@Autowired
	private IStaionService staionService;
	
	@Autowired
	private IPileService pileService;
	
	@Autowired
	private ChargeRecordMapper chargeRecordMapper;
	
	@Resource
	private PartnerConfigMapper partnerConfigMapper;
	
	
	@Test
	public void test() throws Exception{
		
		JSONObject json = new JSONObject();
		json.put("stationNo", "00000111");
		json.put("startDate", "20180110");
		json.put("endDate", "20181010");
		System.out.println(staionService.listChargeOrders(json.toString()));
		
//		ComntCfgEVCP.TRADE_CHARGE_END t = new ComntCfgEVCP.TRADE_CHARGE_END();
//		ComntMsgProc.updateChargeRecordSyncFlag(t);
//		
		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("serialNumber", "019820110001");

//		JSONObject json = new JSONObject();
//		json.put("pileNo", "KE8888888888");
//		System.out.println(pileService.getPileRate(json.toString()));
		
//		map.put("useFlag", 0);
//		List<Map<String,Object>> partnerConfigList = partnerConfigMapper.listPartnerConfigArchive(map);
//		System.out.println(partnerConfigList.size());

		
		//将开始请求充电记录存入接口充电记录
		ChargeRecord chargeRecord = new ChargeRecord();
		chargeRecord.setSerialnumber("112");
		chargeRecord.setPileCode("XW0000000065");
		chargeRecord.setGunId(objToInt("1"));
		chargeRecord.setStartDate(new Date());
		chargeRecord.setChargeMoney((int)(objToDbl("8.9") * 100));
//		chargeRecordMapper.insertChargeRecord(chargeRecord);
		
		
		//更新接口充电记录中请求结束充电
//		chargeRecord.setStartReceiveTime(new Date());
//		chargeRecord.setStartPush((byte)0);
//		chargeRecord.setStartFlag((byte)(1-0));
//		chargeRecordMapper.updateChargeRecord(chargeRecord);
		
		
//		chargeRecord.setStartPush((byte)1);
//		chargeRecord.setStratPushTime(new Date());
//		chargeRecordMapper.updateChargeRecord(chargeRecord);

//		chargeRecord.setEndDate(new Date());
//			chargeRecordMapper.updateChargeRecord(chargeRecord);
		
//		chargeRecord.setEndReceiveTime(new Date());
//			chargeRecord.setEndPush((byte)0);
//			chargeRecordMapper.updateChargeRecord(chargeRecord);
//			
//			chargeRecord.setSerialnumber(serialNumber);
//			chargeRecord.setEndPush((byte)1);
//			chargeRecord.setEndPushTime(new Date());
//	  			chargeRecordMapper.updateChargeRecord(chargeRecord);
		
//		Integer partnderId = chargeMapper.getPartnerIdBySerialNumber("11");
//		System.out.println("partnderId  : "+ partnderId);
		
//		System.out.println(chargeRecordMapper.listChargeRecord().size());
			
//  		json.put("pileNo", 	"KE00000006664");
//  		json.put("gunNo", 	1);
//  		json.put("startDate", 	"2017-09-11 12:20:30");  		
//  		json.put("endDate",   	"2017-09-11 14:20:30");
//  		
//  		json.put("totalElectricity",CommFunc.round(2.5, 2));
//  		json.put("chargeMoney",CommFunc.round(4.0, 2));
//  		json.put("endCause","");
//  		
//  		System.out.println(this.pileService.getPileState(json.toString()));
  		//		json.put(key, value)
//		PartnerPara partnerPara = this.partnerParaMapper.getPartnerPara("");
//		if (partnerPara != null) {
//			System.out.println(partnerPara.toString());
//		} else {
//			System.out.println("null");
//		}
		 
//		Map<String,Object> param = new HashMap<String, Object>();
//		param.put("pileNo", "WO00000000011");
//		param.put("partnerId", 1);
//		List<Map<String,Object>> list = this.pileMapper.getPileState("WO0000000001");
//		for (Map<String,Object> map : list) {
//			System.out.println(map.toString());
//		}
		
		
		
		Map<String,Object> param = new HashMap<String, Object>();
//		JSONObject param = new JSONObject();
		
	 
//		param.put("nowTableName", "cpdata.chargercd201709");
//		param.put("lastTableName", "cpdata.chargercd201708");
//		param.put("partnerId", 2);
//		int gunState = CommFunc.objToInt(this.pileMapper.getRtuByPile("KE0000000066");
//		System.out.println(this.chargeMapper.listPileChargeRcd(param).size());

//		int gunState = CommFunc.objToInt(this.chargeMapper.getGunState(param));
//		System.out.println("gunState  :  "+ gunState);
//		System.out.println(this.chargeMapper.checkWasteno("332312"));
//		System.out.println(this.chargeMapper.queryRtuParaByPileNo("WO0000000001"));
//		List<Station> list = this.stationMapper.listStationGPS(1);
//		for (Station s : list) {
//			System.out.println(s.toString());
//		}
//		System.out.println(chargeService.getChargeRealData(json.toString()));
		
	}
	
}
