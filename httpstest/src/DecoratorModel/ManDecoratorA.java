/**   
* @Title: ManDecoratorA.java 
* @Package DecoratorModel 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月23日 上午11:17:07 
* @version V1.0   
*/
package DecoratorModel;

/** 
* @ClassName: ManDecoratorA 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年11月23日 上午11:17:07 
*  
*/
public class ManDecoratorA extends Decorator {
	public void eat() {
		super.eat();
		reEat();
		System.out.println("ManDecoratorA类");
	}

	public void reEat() {
		System.out.println("再吃一顿饭");
	}
}
