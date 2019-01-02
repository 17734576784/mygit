/**   
* @Title: ManDecoratorB.java 
* @Package DecoratorModel 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月23日 上午11:20:15 
* @version V1.0   
*/
package DecoratorModel;

/** 
* @ClassName: ManDecoratorB 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年11月23日 上午11:20:15 
*  
*/
public class ManDecoratorB extends Decorator {
	 public void eat() {
	        super.eat();
	        System.out.println("===============");
	        System.out.println("ManDecoratorB类");
	    }
}
