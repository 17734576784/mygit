package com.ke.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
/**   
 * @ClassName:  ShiroController   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: dbr 
 * @date:   2018年9月8日 上午8:50:23   
 *      
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.ke.service.IShiroService;


@Controller
public class ShiroController {
	@Autowired
	private IShiroService shiroService;

	@RequestMapping("/gologin.html")
	public String goLogin() {
		return "login";
	}
	
	@ResponseBody
	@RequestMapping("/error.json")
	public JSONObject error() {
		JSONObject json = new JSONObject();
		json.put("status", -1);
		json.put("error", "请求错误！");
		return json;

	}

	@RequestMapping("/login.html")
	public ModelAndView login1(String username, String password) {
		try {
			shiroService.doLogin(username, password);
		} catch (Exception e) {
			return new ModelAndView("error", "msg", e.getMessage());
		}
		return new ModelAndView("index");
	}
	
	@ResponseBody
	@RequestMapping("/login.json")
	public JSONObject login(@RequestBody String username, @RequestBody String password) {
		JSONObject json = new JSONObject();
		try {
			json = shiroService.doLogin(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	@RequestMapping("/logout.html")
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "login";
    }
	
	 /**
     * 模拟不同的请求，在配置文件中对请求进行权限拦截
     */
    @RequestMapping("/do{act}.html")
    public ModelAndView get(@PathVariable String act) {
        //简化代码，省略数据库操作
        //通过页面上显示的信息查看请求是否被拦截
        return new ModelAndView("page", "page", act);
    }
}
