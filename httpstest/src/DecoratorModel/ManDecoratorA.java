/**   
* @Title: ManDecoratorA.java 
* @Package DecoratorModel 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2018��11��23�� ����11:17:07 
* @version V1.0   
*/
package DecoratorModel;

/** 
* @ClassName: ManDecoratorA 
* @Description: TODO(������һ�仰��������������) 
* @author dbr
* @date 2018��11��23�� ����11:17:07 
*  
*/
public class ManDecoratorA extends Decorator {
	public void eat() {
		super.eat();
		reEat();
		System.out.println("ManDecoratorA��");
	}

	public void reEat() {
		System.out.println("�ٳ�һ�ٷ�");
	}
}
