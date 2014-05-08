package com.huntech.huntoms.system.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeController {

	
	@RequestMapping(value = "/homePage",method=RequestMethod.GET)
	public String toMainPage(Model model) {
		return "common/homePage";
	}
}
