package com.wo.comnt;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ComntTime {
	public static class TM {
		public short year  = 1900;
		public short month = 0;
		public short mday  = 1;
		public short wday  = 0;
		public short hour  = 0;
		public short minute= 0;
		public short secend= 0;
		public short msec  = 0;
	}

	public static int cTime()	{
        Date date = new Date();
        Calendar tmpCalen = new GregorianCalendar();
        tmpCalen.setTime(date);
        double retL = (double)(date.getTime() / (1000*1.0));
        return (int)retL;
	}
	
	public static ComntTime.TM localCTime(int ctime)	{
        Calendar tmpCalendar = new GregorianCalendar();
        tmpCalendar.setTime(new Date(((long)(ctime))*1000));
        
        ComntTime.TM ret_tm = new ComntTime.TM();
        
        ret_tm.year   = (short)(tmpCalendar.get(Calendar.YEAR) - 1900);
        ret_tm.month  = (short)tmpCalendar.get(Calendar.MONTH);
        ret_tm.mday   = (short)tmpCalendar.get(Calendar.DATE);
        ret_tm.wday   = (short)(tmpCalendar.get(Calendar.DAY_OF_WEEK) - 1);
        ret_tm.hour   = (short)tmpCalendar.get(Calendar.HOUR_OF_DAY);
        ret_tm.minute = (short)tmpCalendar.get(Calendar.MINUTE);
        ret_tm.secend = (short)tmpCalendar.get(Calendar.SECOND);
        ret_tm.msec   = (short)tmpCalendar.get(Calendar.MILLISECOND);        
        
        return ret_tm;
	}
	
	public static int makeTime(ComntTime.TM tm) {
        Calendar tmpCalendar = new GregorianCalendar();		
        tmpCalendar.set(tm.year + 1900, tm.month, tm.mday, tm.hour, tm.minute, tm.secend);
        
        Date date = tmpCalendar.getTime();
        if (date == null) return 0;
        
        double retL = (double)(date.getTime() / (1000*1.0));
        return (int)retL;        
	}
	
	public static int yearToLong(int short_year) {
		int cur_time = cTime();
		ComntTime.TM tm = localCTime(cur_time);
		
		return ((int)tm.year + 1900) / 100 * 100 + short_year;
	}
	
	public static int yearToShort(int long_year) {
		return long_year % 100;
	}
	
	public static String getFormatTime(int second){		
		return String.format("%2.2f", second * 1.0 / (60 * 60.0));			
	}
	
	public static String getFormatYMDHMS(TM tm){
		String str = (tm.year+1900) + "";
		str +=  "/" + String.format("%02d",(tm.month+1));
		str +=  "/" + String.format("%02d",(tm.mday));
		str +=  " " + String.format("%02d",(tm.hour));
		str +=  ":" + String.format("%02d",(tm.minute));
		str +=  ":" + String.format("%02d",(tm.secend));		
		return str;			
	}
	
	public static String getFormatYMDHMS2(TM tm){
		String str = (tm.year+1900) + "";
		str +=  "年" + String.format("%02d",(tm.month+1));
		str +=  "月" + String.format("%02d",(tm.mday));
		str +=  "日" + String.format("%02d",(tm.hour));
		str +=  "时" + String.format("%02d",(tm.minute));
		str +=  "分" + String.format("%02d",(tm.secend)) + "秒";		
		return str;			
	}

}
