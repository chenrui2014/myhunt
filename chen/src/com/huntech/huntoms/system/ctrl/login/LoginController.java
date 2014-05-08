package com.huntech.huntoms.system.ctrl.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.huntech.huntoms.system.domain.User;
import com.huntech.huntoms.system.dto.tree.ZTree;
import com.huntech.huntoms.system.service.ResourceService;
import com.huntech.huntoms.system.service.account.AccountService;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)
 * 
 * @author zxl
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private ResourceService resourceService;

	
	@RequestMapping(method = RequestMethod.POST)
	public String submit(@RequestParam String username,
			@RequestParam String password, HttpSession session,
			HttpServletRequest request, Model model) {
		User user = accountService.login(username, password);
		if(user!=null){
			List<ZTree> menus = resourceService.findAll();
			Map<Long,ZTree> map = new HashMap<Long,ZTree>();
			for(ZTree zt:menus){
				map.put(zt.getId(), zt);
			}
			session.setAttribute("menuMap",map);
			session.setAttribute("loginUser", user);
			return "frame/main";
		}else{
			model.addAttribute("errorMsg", "登陆失败");
			return "login";
		}
		
	}
	@RequestMapping( method = RequestMethod.GET)
	public String login(String userName, Model model) {
		return "/login";
	}
	@RequestMapping( value = "/fail" ,method = RequestMethod.POST)
	public String fail(String userName, Model model) {
		return "redirect:/login";
	}
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String logout(String userName, Model model) {
		return "/login";
	}
}
