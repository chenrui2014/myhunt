package com.huntech.huntoms.system.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.huntech.huntoms.common.uitl.view.Pager;
import com.huntech.huntoms.system.domain.User;
import com.huntech.huntoms.system.service.account.AccountService;

@Controller
@RequestMapping("/user")
public class AccountController {

	@Autowired
	private AccountService accountService;

	// 跳转到用户管理列表页面
	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public String toListPage(Model model) {
		return "system/user/accountList";
	}

	@RequestMapping(value = "accountList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findList(ServletRequest request, Pager pager) {
		
		Map<String, Object> searchParams = new HashMap<String, Object>();
		Page<User> page = accountService.findUserList(searchParams, pager);
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("rows", page.getContent());
		map.put("total", page.getTotalElements());
		return map;
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String toCreate(Model model) {
		model.addAttribute("action", "create");
		return "system/user/accountForm";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String register(@Valid User user,
			RedirectAttributes redirectAttributes) {
		accountService.updateUser(user);
		// redirectAttributes.addFlashAttribute("username",
		// user.getLoginName());
		return "system/user/accountList";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("userID") Long id, Model model) {
		model.addAttribute("user", accountService.getUser(id));
		model.addAttribute("action", "update");
		return "system/user/accountForm";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("preloadUser") User user,
			RedirectAttributes redirectAttributes, BindingResult result) {
		accountService.updateUser(user);
		redirectAttributes.addFlashAttribute("message",
				"更新用户" + user.getLoginName() + "成功");
		return "system/user/accountList";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id,
			RedirectAttributes redirectAttributes) {
		// User user = accountService.getUser(id);
		accountService.deleteUser(id);
		redirectAttributes.addFlashAttribute("message", "删除用户成功");
		return "system/user/accountList";
	}

	@RequestMapping(value = "checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("loginName") String loginName) {
		if (accountService.findUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

	@RequestMapping(value = "checkUserCode")
	@ResponseBody
	public String checkUserCode(@RequestParam("userCode") String userCode) {
		if (accountService.findUserByUserCode(userCode) == null) {
			return "true";
		}
		return "false";
	}

	/**
	 * 使用@ModelAttribute, 实现Struts2
	 * Preparable二次部分绑定的效果,先根据form的id从数据库查出User对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此本方法在该方法中执行.
	 */
	@ModelAttribute("preloadUser")
	public User getUser(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return accountService.getUser(id);
		}
		return null;
	}
}
