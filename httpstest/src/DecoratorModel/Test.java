/**   
* @Title: Test.java 
* @Package DecoratorModel 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2018��11��23�� ����11:20:32 
* @version V1.0   
*/
package DecoratorModel;

/** 
* @ClassName: Test 
* @Description: TODO(������һ�仰��������������) 
* @author dbr
* @date 2018��11��23�� ����11:20:32 
*  
*/
public class Test {
	public static void main(String[] args) {
		Man man = new Man();
	    ManDecoratorA md1 = new ManDecoratorA();
	    ManDecoratorB md2 = new ManDecoratorB();
	    
	    md1.setPerson(man);
	    md2.setPerson(md1);
	    md2.eat();
	}
}
