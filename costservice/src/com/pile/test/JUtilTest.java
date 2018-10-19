package com.pile.test;

import static com.pile.utils.ConverterUtils.toByte;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.swing.JInternalFrame.JDesktopIcon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pile.common.Constant;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.model.ChargeInfo;
import com.pile.model.OrderDiscountRecord;
import com.pile.netty.server.NettyServer;
import com.pile.netty.server.NettyServerHandler;
import com.pile.service.ICalculateFeeService;
import com.pile.strategy.DiscountContext;
import com.pile.utils.JedisUtils;
import com.pile.utils.SerializeUtils;


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
	
	private ICalculateFeeService calculateFeeService;
	
	@Autowired
	private JedisUtils jedisUtils;
	
	@Resource
	private DiscountContext discountContext;
	
	@Resource
	private CalculateFeeMapper calculateFeeMapper;
	
	@Autowired
	private NettyServer nettyServer;
	
	@Autowired
	private NettyServerHandler nettyServerHandler;
	
	
	@Test
	public void test() throws Exception{
		
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
		boolean flag = calculateFeeMapper.delayCoupon("1", 10);
		System.out.println(flag);
		
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
