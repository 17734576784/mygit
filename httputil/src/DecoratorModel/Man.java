/**   
* @Title: Man.java 
* @Package DecoratorModel 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2018��11��23�� ����11:12:38 
* @version V1.0   
*/
package DecoratorModel;

/** 
* @ClassName: Man 
* @Description: TODO(������һ�仰��������������) 
* @author dbr
* @date 2018��11��23�� ����11:12:38 
*  
*/
public class Man implements Person {
	
	/** (�� Javadoc) 
	* <p>Title: eat</p> 
	* <p>Description: </p>  
	* @see DecoratorModel.Person#eat() 
	*/
	@Override
	public void eat() {
		// TODO Auto-generated method stub
        System.out.println("�����ڳ�");
	}

}
