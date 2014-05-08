package com.huntech.huntoms.system.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.huntech.huntoms.common.uitl.JackJson;
import com.huntech.huntoms.common.uitl.view.Pager;
import com.huntech.huntoms.system.domain.Resource;
import com.huntech.huntoms.system.dto.tree.ZTree;
import com.huntech.huntoms.system.service.ResourceService;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	//获取全全部菜单
	@RequestMapping("/getAllMenu")
    @ResponseBody
    public Map<String,String> getAllMenu() throws Exception{  
		Map<String,String> map = new HashMap<String,String>();
		List<ZTree> nodes = resourceService.findAll();
		map.put("tree", JackJson.fromObjectToJson(nodes));
		return map;
    } 
	//获取菜单栏
	@RequestMapping("/getMenuBar")
    @ResponseBody
    public Map<String,String> getMenuBar(@RequestParam(defaultValue="0")long pid) throws Exception{  
		Map<String,String> map = new HashMap<String,String>();
		List<ZTree> nodes = resourceService.getChildren(pid);
		map.put("menuBar", JackJson.fromObjectToJson(nodes));
		return map;
    } 
	//获取子菜单
	@RequestMapping("/getChildMenu")
    @ResponseBody
    public Map<String,String> getChildMenu(@RequestParam(defaultValue="0")long pid) throws Exception{  
		Map<String,String> map = new HashMap<String,String>();
		List<ZTree> nodes = resourceService.getTreeChildren(pid);
		map.put("childMenu", JackJson.fromObjectToJson(nodes));
		return map;
    }
	//跳转到菜单管理列表页面
	@RequestMapping(value = "/toList",method=RequestMethod.GET)
	public String toListPage( Model model) {
		return "system/resource/resourceList";
	}
	//跳转到模版管理列表页面
	@RequestMapping(value = "resourceList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> findList(ServletRequest request,Pager pager, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//Long userId = getCurrentUserId();
		Map<String, Object> map = resourceService.getResourceList(searchParams,pager); 
		return map;
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String toCreate( Model model) {
		model.addAttribute("action", "create");
		return "system/resource/resourceForm";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String register(@Valid Resource resource, RedirectAttributes redirectAttributes) {
		if(resource.getParent()==null||resource.getParent().getMenuId()==null){
			resource.setParent(null);
		}
		resourceService.update(resource);
		//redirectAttributes.addFlashAttribute("username", user.getLoginName());
		return "system/resource/resourceList";
	}
	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		model.addAttribute("resource", resourceService.findById(id));
		model.addAttribute("action", "update");
		return "system/resource/resourceForm";
	}
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid Resource resource, RedirectAttributes redirectAttributes, BindingResult result) {
		if(resource.getParent()!=null&&resource.getParent().getMenuId()==0){
			resource.setParent(null);
		}
		resourceService.update(resource);
		redirectAttributes.addFlashAttribute("message", "更新菜单成功");
		return "system/resource/resourceList";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		resourceService.deleteById(id);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "system/resource/resourceList";
	}
}
