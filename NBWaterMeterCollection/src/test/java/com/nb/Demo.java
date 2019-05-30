package com.nb;


import com.nb.utils.JsonUtil;
import com.nb.utils.StringUtil;
import com.thrid.party.codec.ef.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] arg) throws Exception{
        Send();
//        Receive();
    }
    //服务器-表端
    public static void Send() throws Exception {
        //设置报端上报周期
        SendReceiveHelper helper = new SendReceiveHelperEF();//实例化操作类
        SIM_ModelEF sm = new SIM_ModelEF();//定义数据模型
        sm.setIMSI("00000867404030000497");//设置IMSI
        sm.setReportBaseTime ("2019-01-01 00:00:00");//上报起始基准时间
        sm.setReportIntervalHours (new BigDecimal("5"));//上报间隔时间  单位：小时
        String json = JsonUtil.GsonString(sm);//模型转JSON
        String ret = helper.ServerSendData(json,  0, 1, "SettingReportPeriod");//执行编码操作
        ReturnObject obj = JsonUtil.GsonToBean(ret,ReturnObject.class);//json反徐丽华
		System.out.println(obj.getCommand());
    }
    //表端->服务器
    public static void Receive() throws Exception {
        //表端异常主动上报
        Out<Integer> hasmore = new Out<>();//是否还有后续数据
        Out<Integer> mid = new Out<>();//消息ID
        SendReceiveHelper helper = new SendReceiveHelperEF();
        String json = helper.ServerReceiveData("681097040030404067080000A3013079B24D5A7BE2710966F8D704FA29C72C818DD2AE70210B7E65CBDB237B6A8899C4C754A6BF0F87F0E18B19E0118A74C1688B97DADD7515CF37E8F139C46C9C073B07FDC93929A73B3F7EE1AB9102B3983ED5E668E0353655CFA7C07374BB3823BD873796260C5A4928B5BE12C868F3B0707D58E5DD6E6517CEC6E343A406D1FA30FFD6B26405880EBA96F4C9149DE81B496C4F2CF2F84B4B83AEF59D02953A30B248B8392C378D163B4CAA26B25412DDFDEFB6B315311BFC6600C4A963398487833CACF49346086EBDFB00B948CB3DF8016A8D90E2DA4DACEB8B7DC15D6A6800740C10AE02D5E5E8E7854834917A9895A4D8477537C4B6465329323BB0E2E073B5A86F488985BBC5D40B060219C40145538C76A88E628E5AC3573D43A80CA4E6CAAC407951125B034660FBCE516613168BBB16"
                ,   hasmore,  mid
        );//解码  
        System.out.println(json);
        ReturnObject obj = JsonUtil.GsonToBean(json,ReturnObject.class);
        
		Map<String, String> serviceMap = new HashMap<>();
		serviceMap = JsonUtil.bean2Map(obj);
		String serviceId = "FxMoile" + obj.getServiceId();
		serviceMap.put("serviceId", serviceId);
		System.out.println("serviceMap : "+ serviceMap);
		
		System.out.println(JsonUtil.map2Bean(serviceMap, obj.getClass()));

        System.out.println("是否还有数据:"+hasmore.getValue());
        System.out.println("消息ID："+mid.getValue());
        System.out.println("ServiceId:"+obj.getServiceId());
    }

}
