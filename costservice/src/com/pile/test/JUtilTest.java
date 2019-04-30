package com.pile.test;

import static com.pile.utils.ConverterUtils.toByte;
import static com.pile.utils.ConverterUtils.toInt;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.swing.JInternalFrame.JDesktopIcon;

import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.protobuf.format.JsonFormat;
import com.pile.common.Constant;
import com.pile.customer.CalculateFeeExecutor;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.model.ChargeInfo;
import com.pile.model.MemberOrders;
import com.pile.model.OrderDiscountRecord;
import com.pile.netty.message.ChargeInfoBufOuterClass;
import com.pile.netty.message.ChargeInfoBufOuterClass.ChargeInfoBuf;
import com.pile.netty.server.NettyServer;
import com.pile.netty.server.NettyServerHandler;
import com.pile.service.ICalculateFeeService;
import com.pile.strategy.DiscountContext;
import com.pile.utils.DateUtils;
import com.pile.utils.JedisUtils;
import com.pile.utils.Log4jUtils;
import com.pile.utils.SerializeUtils;

import sun.nio.cs.ext.JISAutoDetect;

/**
 * @author dbr
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/config/applicationContext.xml"})
public class JUtilTest {

	@Value("${netty.port}")
	private int port;
	
	
	
	@Value("#{${appFlag}}")
	private Map<Integer, String> appFlagMap;
	
	@Value("#{${endcause}}")
	private Map<Integer, String> endCause;
	
	@Value("#{'${list}'.split(',')}")
	private List<String> list;
	
	private ICalculateFeeService calculateFeeService;
	
	@Autowired
	private JedisUtils jedisUtils;
	
	@Resource
	private DiscountContext discountContext;
	
	@Resource
	private CalculateFeeMapper calculateFeeMapper;
	
	@Autowired
	private TestTrans testTrans;
	

	
	@Test
	public void testTransactional() throws Exception{
		String orderSerialNumber = "2019032910250500000027802";
		String tableName = "chargedata.substperson_pay" + orderSerialNumber.substring(0, 4);
		int tradeMoney  = 900;
		Map<String, Object> paramMap = new HashMap<String, Object>();

		try {
			Double checkRatio = calculateFeeMapper.getCheckRatio(1, "20190329", 2);
			if (null != checkRatio && checkRatio != 0D) {
				int payMoney = (int) (tradeMoney * checkRatio);
				paramMap.put("serialNumber", orderSerialNumber);
				paramMap.put("tableName", tableName);
				paramMap.put("tradeMoney", tradeMoney);
				paramMap.put("payMoney", payMoney);
				paramMap.put("operatorId", 2);

				boolean f = calculateFeeMapper.insertPersonStationChargeDetail(paramMap);
				System.out.println(f);
			}
		} catch (Exception e) {
			Log4jUtils.getChargerecord().error("插入个人站充电明细表异常,参数：" + paramMap, e);
			e.printStackTrace();
		}
		
		
	}
	
	
//	@Test
	public void test() throws Exception{
//		System.out.println(list);
		
		
//		try {
//			int operatorId = 1;
//			String orderSerialNumber = "2018112115425400000001527";
//			/** 插入个人站记录表 */
//			String tradeDate = orderSerialNumber.substring(0,8);
//			int stationId = 22;
//			Double checkRatio = calculateFeeMapper.getCheckRatio(operatorId,tradeDate,stationId);	
//			System.out.println("checkRatio : "+ checkRatio);
//			if (checkRatio != null && checkRatio != 0D) {
//				Map<String,Object> paramMap = new HashMap<String,Object>();
//				String tableName = "chargedata.substperson_pay" + orderSerialNumber.substring(0, 4);
//				int tradeMoney = 1000;
//				int payMoney = (int) (tradeMoney * checkRatio);
//				paramMap.put("serialNumber", orderSerialNumber);
//				paramMap.put("tableName", tableName);
//				paramMap.put("tradeMoney", tradeMoney);
//				paramMap.put("payMoney", payMoney);
//				paramMap.put("operatorId", operatorId);
//				
//				System.out.println(calculateFeeMapper.insertPersonStationChargeDetail(paramMap));
//			}
//		} catch (Exception e) {
//			System.out.println("EEEEEEEEEEEEEEEEEEEEEE");
//			e.printStackTrace();
//		}

		
//		System.out.println(calculateFeeMapper.getCheckRatio(3));
//		System.out.println(calculateFeeMapper.insertPersonStationChargeDetail(paramMap));		
		
//		System.out.println(calculateFeeMapper.isPersonStation(5));
		
//		MemberOrders memberOrders = new MemberOrders();
//		memberOrders.setSerialnumber("2018102215425400000001527");
//		memberOrders.setEndpushFlag(Constant.NOPUSH);
//		memberOrders.setChargeState((byte) Constant.CALCULATED);
//		memberOrders.setTradeMoney(1200);
//		memberOrders.setDiscountMoney(300);
//		memberOrders.setRefundMoney(800);
//		memberOrders.setRefundPrincipal(640);
//		memberOrders.setRefundGive(160);
//		memberOrders.setLevel(5);
//		memberOrders.setLeveldesc("满300减50");
//		calculateFeeMapper.updateMemberOrder(memberOrders);
		
//		calculateFeeMapper.updateCouponUseFlag("123456", 0);
//		System.out.println(JedisUtils.rpop(Constant.BAK_COST_QUEUE));
		
//		OrderDiscountRecord record = new OrderDiscountRecord();
//		String orderSerialNumber = "2018121509370215456";
//		record.setSerialnumber(orderSerialNumber);
//		record.setPayableMoney(1500);
//		record.setDiscountMoney(300);
//		record.setPaymentMoney(1200);
//		record.setDiscountType(toByte(3));
//		record.setTableName(orderSerialNumber);
//		
//		StringBuffer discountDetail = new StringBuffer();
//		discountDetail.append("应付金额:").append(1500).append(",打折类型：").append(1);
//		discountDetail.append(",折扣百分比:").append(1).append(",折扣金额:").append(300);
//		discountDetail.append(",实际折扣金额:").append(300).append(",实际支付金额:").append(1200);
//			record.setDiscountDetail(discountDetail.toString());
//		/** 保存充电单折扣记录表 */
//		calculateFeeMapper.insertOrderDiscountRecord(record);
		
//		ChargeInfoBuf.Builder chargeInfo = ChargeInfoBufOuterClass.ChargeInfoBuf.newBuilder();
// 		for (int i = 0; i < 10000; i++) {
//			chargeInfo.setOrderSerialNumber(String.valueOf(i));
//			JedisUtils.lpush(Constant.COSTQUEUE, chargeInfo.build());
//		}
//		ExecutorService pool = Executors.newFixedThreadPool(3);
//		
//		ThreadTest t1 = new ThreadTest();
//		ThreadTest t2 = new ThreadTest();
//		ThreadTest t3 = new ThreadTest();
//		
//		pool.execute(t1);
//		pool.execute(t2);
//		pool.execute(t3);
		
//		pool.shutdown();
			
//		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("tableName", "ChargeData.member_account_change2018");
//		paramMap.put("memberId", 123);
//		paramMap.put("operatorId", 123);
//		paramMap.put("operateType", Constant.TRANSFERINCOME);
//		paramMap.put("serialNumber", "123");
//		paramMap.put("principalChange", 123);
//		paramMap.put("giveChange", 123);
//		paramMap.put("principalRemain", 456);
//		paramMap.put("giveRemain", 456);
		//63949	2648
//		boolean flag = calculateFeeMapper.delayCoupon("1", 10);
//		System.out.println(flag);
		
//		System.out.println(calculateFeeMapper.getMemberCurrentAccount(1, 1));
//		ChargeInfo chargeInfo = new ChargeInfo();
//		chargeInfo.setAppFlag(1);
//		chargeInfo.setOrderSerialNumber("11111");
//		/**小程序 app 将充电单存入队列*/
//		if (appFlagMap.containsKey(chargeInfo.getAppFlag())) {
//			jedisUtils.lpush(appFlagMap.get(chargeInfo.getAppFlag()), chargeInfo.getOrderSerialNumber());
//			System.out.println();
//		}
//		System.out.println(jedisUtils.rpop(appFlagMap.get(chargeInfo.getAppFlag())));
		
//		System.out.println(nettyServerHandler.acceptInboundMessage(""));;
//		boolean f = calculateFeeMapper.backUpOrderDiscountRecord("chargedata.order_discount_record201808", "20180601");
//		System.out.println(f);
		
//		System.out.println("customer Listener 启动！！！");
//		byte[] value = jedisUtils.brpopLpush(Constant.BAKCOSTQUEUE, Constant.COSTQUEUE, 20);
//		if (value != null) {
//			String chargeInfo = SerializeUtils.deserialize(value).toString();
//			System.out.println(chargeInfo.toString());
//		}
		
//		jedisUtils.rpop(Constant.COSTQUEUE);
//		 System.out.println(jedisUtils.lrange(Constant.COSTQUEUE, 0, -1).size());
	}
	
	
	
//	@Test
	public void mapperTest() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		OrderDiscountRecord record = new OrderDiscountRecord();
		
		record.setSerialnumber("1000001");
		record.setPayableMoney(100);
		record.setDiscountMoney(20);
		record.setPaymentMoney(80);
		record.setDiscountType(toByte(2));
		StringBuffer discountDetail = new StringBuffer();
		discountDetail.append("券码:").append(1).append(",券名:").append(1);
		discountDetail.append(",最低消费金额:").append(50).append(",优惠券金额:").append(20);
		record.setDiscountDetail(discountDetail.toString());
		//保存充电单折扣记录表
//		calculateFeeMapper.insertOrderDiscountRecord(record);	
//		System.out.println(calculateFeeMapper.getChargeDLBySerialNumber("1"));

		//更新会员优惠券表中使用标志和使用日期
//		calculateFeeMapper.updateMemberCoupon("1");
//		calculateFeeMapper.delayCoupon("1");
		
	}
	
//	@Test
	public void discountTest() throws InterruptedException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		//		@SuppressWarnings("resource")
//		Jedis jedis = new Jedis("127.0.0.1",6379);
//		jedis.auth("123456");
//		ChargeInfo charge = JSON.parseObject(jedis.lpop(Constant.ORDERLIST),ChargeInfo.class);
// 		System.out.println(charge.getDiscountMoney());
//		MinaServer.startServer();

		ChargeInfo chargeInfo = new ChargeInfo();
		chargeInfo.setOrderSerialNumber("1");
		chargeInfo.setMemberId(1);
		chargeInfo.setPileCode("KE000001");
  		chargeInfo.setPayableMoney(5000);

//  		System.out.println(calculateFeeMapper.listOperatorRule("1").size());;
  		discountContext.getDiscountInfo(chargeInfo, 0);
//		System.out.println(chargeInfo);
//		System.out.println(JedisUtils.hgetAll(Constant.DISCOUNTTYPE));
//		
//		MinaClient.calculateFeeClient(chargeInfo);
//		
//  		Map<String,String> d = new HashMap<String,String>();
//  		d.put("0", "discountByCouponStrategy");
//  		d.put("1", "discountByPercentStrategy");
//  		d.put("2", "discountByChargeFeeStrategy");
//  		
//  		JedisUtils.del("Diction:折扣策略_500");
//		JedisUtils.hset("Diction:折扣策略_500","0", "discountByCouponStrategy");
//		JedisUtils.hset("Diction:折扣策略_500","1", "discountByPercentStrategy");
//		JedisUtils.hset("Diction:折扣策略_500","2", "discountByChargeFeeStrategy");

//		System.out.println(JedisUtils.hgetAll("Diction:折扣策略_500"));
	}
	
	
	
	
}
