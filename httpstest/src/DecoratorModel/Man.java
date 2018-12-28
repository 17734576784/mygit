/**   
* @Title: Man.java 
* @Package DecoratorModel 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年11月23日 上午11:12:38 
* @version V1.0   
*/
package DecoratorModel;

/** 
* @ClassName: Man 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年11月23日 上午11:12:38 
*  
*/
public class Man implements Person {
	
	/** (非 Javadoc) 
	* <p>Title: eat</p> 
	* <p>Description: </p>  
	* @see DecoratorModel.Person#eat() 
	*/
	@Override
	public void eat() {
		// TODO Auto-generated method stub
        System.out.println("男人在吃");
	}

}
