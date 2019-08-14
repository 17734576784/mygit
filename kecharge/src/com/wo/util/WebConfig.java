package com.wo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.wo.mapper.PartnerConfigMapper;
import com.wo.model.PartnerConfig;
import com.wo.service.ILoginService;

/**
 * @author dbr
 *
 */
@Component
public class WebConfig {

	public static String realBasepath;
	public static long cacheTimeOut; // 缓存过期时间 默认两个小时
	public static Map<Integer,PartnerConfig> partnerUrl = new HashMap<Integer, PartnerConfig>();
	
	public static String webservice_ip = "127.0.0.1";
	public static int webservice_port = 17206;
	public static long session_timeout;

	@Autowired
	private ILoginService loginService;

	@Resource
	private PartnerConfigMapper partnerConfigMapper;
	
	private static WebConfig webConfig;

	@PostConstruct
	public void init() {
		webConfig = this;
		webConfig.loginService = this.loginService;
		webConfig.partnerConfigMapper = this.partnerConfigMapper;

	}

	static{
		realBasepath = new File(new File(WebConfig.class.getResource("/").getPath()).getParent()).getParent();
		try {
			realBasepath = URLDecoder.decode(realBasepath,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析config.xml，初始化系统配置信息
	 * 
	 * **/
	public static void initial() {
		// 获取根节点
		Element root = getRoot();
		
		cacheTimeOut = getSessionTimeout(root);
		initComntPara(root);
//		initPrefix(root);
		initPartnerConfig();
	}
	
	/**
	 * 初始化流水号前缀
	 * @param root   配置文件
	 * @return
	 * **/
	public static List<String> listPrefix() {
		List<String> prefixList = new ArrayList<String>();
		try {
			Element root = getRoot();
			NodeList prefixNodeList = root.getElementsByTagName("prefix");
			for (int i = 0; i < prefixNodeList.getLength(); i++) {
				Node prefixNode = prefixNodeList.item(i);
				NamedNodeMap prefixNodeMap = prefixNode.getAttributes();
				String prefix = prefixNodeMap.getNamedItem("value").getTextContent();
				prefixList.add(prefix);
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return prefixList;
	}
	
	
	/**
	 * 加载合作伙伴配置
	 * 
	 * @param root
	 *            配置文件
	 * @return
	 * 
	 * **/
	public static void initPartnerConfig() {

		try {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("useFlag", 1);
			List<PartnerConfig> partnerConfigList = webConfig.partnerConfigMapper.listPartnerConfig(param);
			for (PartnerConfig partnerConfig : partnerConfigList) {
				if (null == partnerConfig) {
					continue;
				}
				partnerUrl.put(partnerConfig.getPartnerId(), partnerConfig);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新指定合作伙伴配置
	 * 
	 * @param root
	 *            配置文件
	 * @return
	 * 
	 * **/
	public static void initPartnerConfig(int partnerId) {

		try {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("useFlag", 1);
			param.put("partnerId", partnerId);
			List<PartnerConfig> partnerConfigList = webConfig.partnerConfigMapper.listPartnerConfig(param);
			if (null == partnerConfigList || partnerConfigList.isEmpty()) {
				if(partnerUrl.containsKey(partnerId)){
					partnerUrl.remove(partnerId);
				}
				return;
			}
			
			for (PartnerConfig partnerConfig : partnerConfigList) {

				if (null == partnerConfig) {
					continue;
				}

				partnerUrl.put(partnerConfig.getPartnerId(), partnerConfig);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean initLoginToken(int partnderId) {
		boolean flag = false;
		try {
			PartnerConfig partnerConfig = partnerUrl.get(partnderId);
			if (null == partnerConfig) {
				Log4jUtil.getInfo().info("不存在合作伙伴信信息，重新加载:partnderId=" + partnderId);
				return flag;
			}
			JSONObject urlJson = new JSONObject();
			String loginUrl = partnerConfig.getLoginUrl();
			if (null == loginUrl || loginUrl.isEmpty()) {
				return flag;
			}
			
			urlJson.put("loginUrl", partnerConfig.getLoginUrl());
			urlJson.put("userName", partnerConfig.getUsername());
			urlJson.put("passWord", partnerConfig.getPassword());
			
			String rtnToken = webConfig.loginService.sendLoginTokenRequest(urlJson);
			if (!rtnToken.isEmpty()) {
				partnerConfig.setToken(rtnToken);
				partnerUrl.put(partnderId, partnerConfig);
				flag = true;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/**
	 * 初始化与前置通讯参数
	 * @param root 配置文件
	 * 
	 * **/
	public static void initComntPara(Element root) {
		// 获取根节点

		NodeList nl_webservice_addr = root.getElementsByTagName("web_service_addr");
		Node node = nl_webservice_addr.item(0);
		NamedNodeMap node_map = node.getAttributes();
		Node attr_ip = node_map.getNamedItem("ip");
		Node attr_port = node_map.getNamedItem("port");

		if (attr_ip != null) {
			webservice_ip = attr_ip.getNodeValue();
		}

		if (attr_port != null) {
			webservice_port = CommFunc.objToInt(attr_port.getNodeValue());
		}

		session_timeout = getSessionTimeout(root);
	}
	
	/**
	 * 获取缓存过期时间
	 * @param root 配置文件
	 * @return
	 * 
	 * **/
	private static long getSessionTimeout(Element root) {

		long timeout = 2;
		if (root == null) {
			return timeout;
		}
		NodeList ra = root.getElementsByTagName("cacheTimeOut");
		Node station = ra.item(0);
		NamedNodeMap node_map = station.getAttributes();
		Node timeoutNode = node_map.getNamedItem("timeout");
		try {
			timeout = Long.parseLong(timeoutNode.getNodeValue());
			if (timeout <= 0) {
				timeout = 2;
			}
		} catch (NumberFormatException e) {
			timeout = 2;
		}

		timeout = timeout * 1000 * 60 * 60;

		return timeout;
	}
	
	/**
	 * 解析配置文件config.xml
	 */
	public static Element getRoot() {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Element root = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			String file_path = realBasepath + File.separator + "WEB-INF" + File.separator + "config.xml";
			file_path = URLDecoder.decode(file_path, "UTF-8");
			InputStream is = new FileInputStream(file_path);
			Document doc = builder.parse(is);
			root = doc.getDocumentElement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return root;
	}
}
