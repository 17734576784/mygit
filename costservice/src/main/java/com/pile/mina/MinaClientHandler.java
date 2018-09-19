/**   
* @Title: MinaClientHander.java 
* @Package com.pile.mina 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年4月19日 上午10:41:24 
* @version V1.0   
*/
package com.pile.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/** 
* @ClassName: MinaClientHander 
* @Description: 客户端处理类 
* @author dbr
* @date 2018年4月19日 上午10:41:24 
*  
*/
public class MinaClientHandler extends IoHandlerAdapter{

	/** (非 Javadoc) 
	* <p>Title: exceptionCaught</p> 
	* <p>Description: </p> 
	* @param session
	* @param cause
	* @throws Exception 
	* @see org.apache.mina.core.service.IoHandlerAdapter#exceptionCaught(org.apache.mina.core.session.IoSession, java.lang.Throwable) 
	*/
	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
	}

	/** (非 Javadoc) 
	* <p>Title: inputClosed</p> 
	* <p>Description: </p> 
	* @param session
	* @throws Exception 
	* @see org.apache.mina.core.service.IoHandlerAdapter#inputClosed(org.apache.mina.core.session.IoSession) 
	*/
	@Override
	public void inputClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.inputClosed(session);
	}

	/** (非 Javadoc) 
	* <p>Title: messageReceived</p> 
	* <p>Description: </p> 
	* @param session
	* @param message
	* @throws Exception 
	* @see org.apache.mina.core.service.IoHandlerAdapter#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object) 
	*/
	@SuppressWarnings("deprecation")
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageReceived(session, message);
		System.out.println("client 接收消息 :" + message.toString());
		session.close();
	}

	/** (非 Javadoc) 
	* <p>Title: messageSent</p> 
	* <p>Description: </p> 
	* @param session
	* @param message
	* @throws Exception 
	* @see org.apache.mina.core.service.IoHandlerAdapter#messageSent(org.apache.mina.core.session.IoSession, java.lang.Object) 
	*/
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
		System.out.println(" client 发送消息： "+message.toString());

	}

	/** (非 Javadoc) 
	* <p>Title: sessionClosed</p> 
	* <p>Description: </p> 
	* @param session
	* @throws Exception 
	* @see org.apache.mina.core.service.IoHandlerAdapter#sessionClosed(org.apache.mina.core.session.IoSession) 
	*/
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
		System.out.println(" client 关闭连接 ");

	}

	/** (非 Javadoc) 
	* <p>Title: sessionCreated</p> 
	* <p>Description: </p> 
	* @param session
	* @throws Exception 
	* @see org.apache.mina.core.service.IoHandlerAdapter#sessionCreated(org.apache.mina.core.session.IoSession) 
	*/
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionCreated(session);
		System.out.println(" client 创建连接 ");

	}

	/** (非 Javadoc) 
	* <p>Title: sessionIdle</p> 
	* <p>Description: </p> 
	* @param session
	* @param status
	* @throws Exception 
	* @see org.apache.mina.core.service.IoHandlerAdapter#sessionIdle(org.apache.mina.core.session.IoSession, org.apache.mina.core.session.IdleStatus) 
	*/
	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.sessionIdle(session, status);
		System.out.println(" client idle " + session.getIdleCount(status));
	}

	/** (非 Javadoc) 
	* <p>Title: sessionOpened</p> 
	* <p>Description: </p> 
	* @param session
	* @throws Exception 
	* @see org.apache.mina.core.service.IoHandlerAdapter#sessionOpened(org.apache.mina.core.session.IoSession) 
	*/
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
		System.out.println("client 打开连接");
	}
	

}
