package com.huntech.huntoms.system.ctrl;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huntech.huntoms.system.dto.tree.ZTree;

@Controller
@RequestMapping("/huntoms")
public class ReportController {
		
	@RequestMapping(value = "/report/{id}",method=RequestMethod.GET)
	public String toListPage(@PathVariable("id") long id,Model model,HttpSession session) {
		Map<Long,ZTree> map = (Map<Long, ZTree>) session.getAttribute("menuMap");
		String resparam = map.get(id).getResparam();
		model.addAttribute("resparam", resparam);
		return "report/showReport";
	}
	
}
