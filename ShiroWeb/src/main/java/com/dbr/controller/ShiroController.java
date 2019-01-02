/**
 * 
 */
package com.dbr.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
/**   
 * @ClassName:  ShiroController   
 * @Description:TODO(������һ�仰��������������)   
 * @author: dbr 
 * @date:   2018��9��8�� ����8:50:23   
 *      
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dbr.service.ShiroService;

@Controller
public class ShiroController {
	@Autowired
	private ShiroService shiroService;

	@RequestMapping("/gologin.html")
	public String goLogin() {
		return "/login.jsp";
	}

	@RequestMapping("/login.html")
	public ModelAndView login(String username, String password) {
		try {
			shiroService.doLogin(username, password);
		} catch (Exception e) {
			return new ModelAndView("/error.jsp", "msg", e.getMessage());
		}
		return new ModelAndView("/index.jsp");
	}

	@RequestMapping("/logout.html")
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "/login.jsp";
    }
	
	 /**
     * ģ�ⲻͬ�������������ļ��ж��������Ȩ������
     */
    @RequestMapping("/do{act}.html")
    public ModelAndView get(@PathVariable String act) {
        //�򻯴��룬ʡ�����ݿ����
        //ͨ��ҳ������ʾ����Ϣ�鿴�����Ƿ�����
        return new ModelAndView("/page.jsp", "page", act);
    }
}
