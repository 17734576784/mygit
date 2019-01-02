/**   
* @Title: Decorator.java 
* @Package DecoratorModel 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月23日 上午11:13:34 
* @version V1.0   
*/
package DecoratorModel;

/**
 * @ClassName: Decorator
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author dbr
 * @date 2018年11月23日 上午11:13:34
 * 
 */
public abstract class Decorator implements Person {
	protected Person person;

	public void setPerson(Person person) {
		this.person = person;
	}

	public void eat() {
		person.eat();
	}
}
