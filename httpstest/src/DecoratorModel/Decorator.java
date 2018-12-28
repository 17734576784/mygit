/**   
* @Title: Decorator.java 
* @Package DecoratorModel 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2018��11��23�� ����11:13:34 
* @version V1.0   
*/
package DecoratorModel;

/**
 * @ClassName: Decorator
 * @Description: TODO(������һ�仰��������������)
 * @author dbr
 * @date 2018��11��23�� ����11:13:34
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
