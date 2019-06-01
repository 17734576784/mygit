package com.nb;


import com.nb.utils.JsonUtil;
import com.nb.utils.StringUtil;
import com.thrid.party.codec.ef.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Demo {
    public static void main(String[] arg) throws Exception{
//        Send();
        Receive();
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
		String json = helper.ServerReceiveData(
				"681050279332607267080000A30130B076DDC3CB357A7934008D108D7512E2F8B3985ECB5B470350974EFF9D0F0A54D4F69D3737B4D9FE28F4814B9B8395A0593929B8EE9AF2F12424D45D5B52FB4755E03AED826F8997B0FF9962092C6AE6E61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE61F9149A0C3EDBC8F943853C3256BDAE94716",
				hasmore, mid);// 解码
        System.out.println(json);
        ReturnObject obj = JsonUtil.GsonToBean(json,ReturnObject.class);
    }

}
