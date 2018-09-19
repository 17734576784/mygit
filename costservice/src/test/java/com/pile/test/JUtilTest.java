package com.pile.test;

import static com.pile.utils.ConverterUtils.toByte;

import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pile.CostserviceApplication;
import com.pile.common.Constant;
import com.pile.mapper.CalculateFeeMapper;
import com.pile.mapper.PileMapper;
import com.pile.model.ChargeInfo;
import com.pile.model.OrderDiscountRecord;
import com.pile.service.ICalculateFeeService;
import com.pile.strategy.DiscountContext;
import com.pile.utils.JedisUtils;

/**
 * @author dbr
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {CostserviceApplication.class})
public class JUtilTest{

 	@Resource
 	private DiscountContext discountContext;
	
	@Resource
	private CalculateFeeMapper calculateFeeMapper;
	
	@Autowired
	private ICalculateFeeService calculateFeeService;
	
	@Resource 
	private PileMapper pileMapper;
	
	@Resource
	private JedisUtils jedisUtils;
	
	@Test
	public void StrategyTest(){
		ChargeInfo chargeInfo = new ChargeInfo();
		chargeInfo.setSerialNumber("1");
		chargeInfo.setMemberId(1);
		chargeInfo.setPileCode("KE000001");
  		chargeInfo.setPayableMoney(5000);
  		jedisUtils.set("dbr", "dfdads");
		System.out.println(jedisUtils.get("dbr"));
		
		chargeInfo = discountContext.getDiscountInfo(chargeInfo, 0);
//  		System.out.println(pileMapper.getPileGps("KE0000000063"));
//  		jedisUtils.set("a", "dbr");
//  		System.out.println(jedisUtils.get("a").equals("dbr"));
//  		System.out.println(jedisUtils.hmget(Constant.ORDERMAP).size());

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
		chargeInfo.setSerialNumber("1");
		chargeInfo.setMemberId(1);
		chargeInfo.setPileCode("KE000001");
  		chargeInfo.setPayableMoney(5000);

//  		System.out.println(calculateFeeMapper.listOperatorRule("1").size());;
		calculateFeeService.calculateChargeFee(chargeInfo);
//		System.out.println(chargeInfo);
//		System.out.println(JedisUtils.hgetAll(Constant.DISCOUNTTYPE));
//		
//		MinaClient.calculateFeeClient(chargeInfo);
//		
//		JedisUtils.set("a", "222");
//		System.out.println(JedisUtils.get("a"));
	}
	
	
	
	
}
