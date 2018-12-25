/**   
* @Title: LoginUser.java 
* @Package com.ke.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年12月21日 下午1:31:02 
* @version V1.0   
*/
package com.ke.model;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: LoginUser
 * @Description: 当前登录用户
 * @author dbr
 * @date 2018年12月21日 下午1:31:02
 * 
 */
public class LoginUser implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private String loginName;
	private String describe;
	private Integer partnerId;
	private List<String> permList;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public Integer getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	public List<String> getPermList() {
		return permList;
	}

	public void setPermList(List<String> permList) {
		this.permList = permList;
	}

}
