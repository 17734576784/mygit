/**   
* @Title: ManDecoratorB.java 
* @Package DecoratorModel 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2018��11��23�� ����11:20:15 
* @version V1.0   
*/
package DecoratorModel;

/** 
* @ClassName: ManDecoratorB 
* @Description: TODO(������һ�仰��������������) 
* @author dbr
* @date 2018��11��23�� ����11:20:15 
*  
*/
public class ManDecoratorB extends Decorator {
	 public void eat() {
	        super.eat();
	        System.out.println("===============");
	        System.out.println("ManDecoratorB��");
	    }
}
