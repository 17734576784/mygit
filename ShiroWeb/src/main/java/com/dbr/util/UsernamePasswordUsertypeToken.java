/**   
* @Title: UsernamePasswordUsertypeToken.java 
* @Package com.dbr.util 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author dbr
* @date 2018��12��17�� ����6:13:23 
* @version V1.0   
*/
package com.dbr.util;

/** 
* @ClassName: UsernamePasswordUsertypeToken 
* @Description: TODO(������һ�仰��������������) 
* @author dbr
* @date 2018��12��17�� ����6:13:23 
*  
*/
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
/**
 * �ο�org.apache.shiro.authcUsernamePasswordToken���������û����Ͳ���
 * @author caihz
 * @see org.apache.shiro.authcUsernamePasswordToken
 */
public class UsernamePasswordUsertypeToken implements HostAuthenticationToken, RememberMeAuthenticationToken {
	/**
     * �û���
     */
    private String username;
 
    /**
     * ����, in char[] format
     */
    private char[] password;
 
    /**
     * �Ƿ��ס��
     * Ĭ��ֵ��<code>false</code>
     */
    private boolean rememberMe = false;
 
    /**
     * �������ƻ�ip
     */
    private String host;
    
    /**
     * �û�����
     */
    private String usertype;
 
    public UsernamePasswordUsertypeToken() {
    }
 
    /**
     * ���췽��
     * @param username �û���
     * @param password ���루char[]��
     * @param rememberMe �Ƿ��ס��
     * @param host ������ip
     * @param usertype �û�����
     */
    public UsernamePasswordUsertypeToken(final String username, final char[] password,
                                 final boolean rememberMe, final String host, final String usertype) {
 
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
        this.host = host;
        this.usertype = usertype;
    }
    
    /**
     * ���췽��
     * @param username �û���
     * @param password ���루String��
     * @param rememberMe �Ƿ��ס��
     * @param host ������ip
     * @param usertype �û�����
     */
    public UsernamePasswordUsertypeToken(final String username, final String password,
                                 final boolean rememberMe, final String host, final String usertype) {
        this(username, password != null ? password.toCharArray() : null, rememberMe, host, usertype);
    }
 
	public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public char[] getPassword() {
        return password;
    }
    public void setPassword(char[] password) {
        this.password = password;
    }
 
    /**
     * Simply returns {@link #getUsername() getUsername()}.
     *
     * @return the {@link #getUsername() username}.
     * @see org.apache.shiro.authc.AuthenticationToken#getPrincipal()
     */
    public Object getPrincipal() {
        return getUsername();
    }
 
    /**
     * Returns the {@link #getPassword() password} char array.
     *
     * @return the {@link #getPassword() password} char array.
     * @see org.apache.shiro.authc.AuthenticationToken#getCredentials()
     */
    public Object getCredentials() {
        return getPassword();
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public boolean isRememberMe() {
        return rememberMe;
    }
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
    public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
 
	/**
	 * �������
	 * ���������Ϊ�գ����ó�0x00
	 */
    public void clear() {
        this.username = null;
        this.host = null;
        this.rememberMe = false;
        this.usertype = null;
 
        if (this.password != null) {
            for (int i = 0; i < password.length; i++) {
                this.password[i] = 0x00;
            }
            this.password = null;
        }
 
    }
 
    /**
     * ��дtoString����
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(username);
        sb.append(", usertype=").append(usertype);
        sb.append(", rememberMe=").append(rememberMe);
        if (host != null) {
            sb.append(" (").append(host).append(")");
        }
        return sb.toString();
    }
 
 
}
