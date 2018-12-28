/**   
* @Title: Test.java 
* @Package DecoratorModel 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月23日 上午11:20:32 
* @version V1.0   
*/
package DecoratorModel;

/** 
* @ClassName: Test 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年11月23日 上午11:20:32 
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
