/**   
* @Title: MinaServerHandler.java 
* @Package com.pile.mina 
* @Description: TODO(用一句话描述该文件做什么) 
* @author dbr
* @date 2018年4月19日 上午11:06:50 
* @version V1.0   
*/
package com.pile.mina;

import java.time.LocalDateTime;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.pile.common.Constant;
import com.pile.model.ChargeInfo;
import com.pile.utils.JedisUtils;
import com.pile.utils.JsonUtils;
import com.pile.utils.Log4jUtils;
/** 
* @ClassName: MinaServerHandler 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author dbr
* @date 2018年4月19日 上午11:06:50 
*  
*/
public class MinaServerHandler extends IoHandlerAdapter {


	@Autowired
	private JedisUtils jedisUtils;
	
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
		super.exceptionCaught(session, cause);
		Log4jUtils.getMinaserver().info("服务器端发送异常：" + cause.getMessage());

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
		super.inputClosed(session);
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
		Log4jUtils.getMinaserver().info("服务器端发送消息成功：" + message.toString());

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
		Log4jUtils.getMinaserver().info("与客户端创建连接关闭");
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
		Log4jUtils.getMinaserver().info("与客户端创建连接...");
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
		Log4jUtils.getMinaserver().info("与客户端创建连接打开");
	}

	/** (非 Javadoc) 
	* <p>Title: messageReceived</p> 
	* <p>Description: </p> 
	* @param session
	* @param message
	* @throws Exception 
	* @see org.apache.mina.core.service.IoHandlerAdapter#messageReceived(org.apache.mina.core.session.IoSession, java.lang.Object) 
	*/
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		super.messageReceived(session, message);
		try {
			System.out.println("message   : "+ message.toString());
			
			ChargeInfo order= JsonUtils.toBean(message.toString(), ChargeInfo.class);
			String serialNumber  = order.getSerialNumber();
			
	 		Log4jUtils.getMinaserver().info("服务器端接收的消息：" + serialNumber);
//			System.out.println("线程ID  ： " + Thread.currentThread().getId());
	 		jedisUtils.hset(Constant.ORDERMAP, serialNumber,JsonUtils.toJSONString(order));
			LocalDateTime date = LocalDateTime.now();
			session.write(date.toString() + " 确认流水号:  " + serialNumber);
			System.out.println("线程ID  ： " + Thread.currentThread().getId() + " 确认流水号 " + serialNumber);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

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
		super.sessionIdle(session, status);
		System.out.println(LocalDateTime.now().toLocalTime() +" server idle " + session.getIdleCount(status));
 	}

}
