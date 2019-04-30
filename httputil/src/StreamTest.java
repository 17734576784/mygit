import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**   
* @Title: StreamTest.java 
* @Package  
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2019年1月16日 下午3:38:58 
* @version V1.0   
*/

/**
 * @ClassName: StreamTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2019年1月16日 下午3:38:58
 * 
 */
public class StreamTest {
	public static void main(String[] args) {
		  System.out.println(utcToLocal("20190428T012017Z")); 
	}
	
	/**
     * local时间转换成UTC时间
     * @param localTime
     * @return
     */
    public static Date localToUTC(String localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate= null;
        try {
            localDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long localTimeInMillis=localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(java.util.Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(java.util.Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate=new Date(calendar.getTimeInMillis());
        return utcDate;
    }
    
	/**
     * utc时间转成local时间
     * @param utcTime
     * @return
     */
    public static Date utcToLocal(String utcTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date utcDate = null;
        try {
            utcDate = sdf.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getDefault());
        Date locatlDate = null;
        String localTime = sdf.format(utcDate.getTime());
        try {
            locatlDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return locatlDate;
    }
    
    
    
	public static void compareForAndStream() {
        //p1表示for性能,p2表示流处理性能
        long p1 = 0, p2 = 0;
        int n = 500000;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int j = 0; j < 100; j ++) {
            for(int i = 0; i < n; i ++) {
                arr.add(i);
            }
            Integer sum = 0;
            long t1 = System.nanoTime();
            for(Integer v : arr) {
                sum = sum + v;
            }
            long t2 = System.nanoTime();
//            arr.stream().reduce(0, (a, b) -> a + b);
            arr.stream().parallel().reduce(0, (a, b) -> a + b);
            long t3 = System.nanoTime();
            p1 += (t2 - t1);
            p2 += (t3 - t2);
            arr.clear();
        }
        System.out.println(p1 / 100 / 1000);
        System.out.println(p2 / 100 / 1000);

    }

}
