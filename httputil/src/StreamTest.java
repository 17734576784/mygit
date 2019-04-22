import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**   
* @Title: StreamTest.java 
* @Package  
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2019��1��16�� ����3:38:58 
* @version V1.0   
*/

/**
 * @ClassName: StreamTest
 * @Description: TODO(������һ�仰��������������)
 * @author dbr
 * @date 2019��1��16�� ����3:38:58
 * 
 */
public class StreamTest {
	public static void main(String[] args) {
//		  int num = Runtime.getRuntime().availableProcessors();
//		     System.out.println(num);
//		     
//		 int size = 1000;
//	        List<String> uuisList = new ArrayList<>(size);
//	 
//	        System.out.println("��ʼ���� = " + new Date());
//	        //����500���uuid
//	        for (int i = 0; i< size; i++){
//	            uuisList.add(UUID.randomUUID().toString());
//	        }
//	        System.out.println("���ɽ��� = " + new Date());
//	 
//	        System.out.println("��ʼ��������");
//	        //long starttime = System.currentTimeMillis();//����
//	        long startTime = System.nanoTime();//���룬��Ϊ��ȷ
//	        uuisList.stream().sorted().count();//��������
//	        long endTime = System.nanoTime();//���룬��Ϊ��ȷ
//	        long distanceTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
//	        System.out.println("������������ ��ʱΪ " + distanceTime); 
	 
//	        System.out.println("��ʼ��������");
//	        //long starttime = System.currentTimeMillis();//����
//	        long startTime = System.nanoTime();//���룬��Ϊ��ȷ
//	 
//	        uuisList.parallelStream().sorted().count();//��������
//	 
//	        long endTime = System.nanoTime();//���룬��Ϊ��ȷ
//	        long  distanceTime = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
//	        System.out.println("������������ ��ʱΪ " + distanceTime);
		compareForAndStream();
		
	}
	
	public static void compareForAndStream() {
        //p1��ʾfor����,p2��ʾ����������
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
