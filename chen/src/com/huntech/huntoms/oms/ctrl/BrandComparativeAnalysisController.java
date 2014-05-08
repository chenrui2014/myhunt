package com.huntech.huntoms.oms.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huntech.huntoms.oms.dto.BrachYearDto;
import com.huntech.huntoms.oms.dto.BranchDayDto;
import com.huntech.huntoms.oms.dto.BranchMonthDto;
import com.huntech.huntoms.oms.service.BranchCompareDayService;
import com.huntech.huntoms.oms.service.BranchCompareMonthService;
import com.huntech.huntoms.oms.service.BranchCompareYearService;

@Controller
@RequestMapping("/branch")
public class BrandComparativeAnalysisController {
	
	@Autowired
	private BranchCompareDayService branchCompareDayService;
	
	@Autowired
	private BranchCompareMonthService branchCompareMonthService;
	
	@Autowired
	private BranchCompareYearService branchCompareYearService;
	
	/**
	 * 进入电站年发电贡献统计分析
	 * @return
	 */
	@RequestMapping("/enterStaYearEContribute")
	public String enterStaYearEContribute(){
		return "oms/branch/staYearEContribute";
	}
	
	
	/**
	 * 显示电站年发电贡献统计分析
	 * @return
	 */
	@RequestMapping("/displayStaYearEContribute")
	@ResponseBody
	public Map displayStaYearEContribute(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear){
		Map jsonmap = new HashMap();
		
		List<BrachYearDto> list = branchCompareYearService.getStaYear(orgid,parayear);
		if(null!=list&&list.size()>0){
			jsonmap.put("table", list);
			Integer year = list.get(0).getYear();
			jsonmap.put("year", year);
			List orgName = new ArrayList();
			for(BrachYearDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsonmap.put("x", orgName);
			
			List ly = new ArrayList();
			//发电量
			Map e = new  HashMap();
			List eList = new ArrayList();
			for(BrachYearDto dto :list){
				eList.add(dto.getE());
			}
		
			e.put("name", "发电量");
			e.put("data", eList);
			ly.add(e);
			
			//上网电量
			
			Map onnet = new  HashMap();
			List onnetList = new ArrayList();
			for(BrachYearDto dto :list){
				onnetList.add(dto.getOnnete());
			}
			onnet.put("name", "上网电量");
			onnet.put("data", onnetList);
			ly.add(onnet);
			
			jsonmap.put("ly", ly);
			
			//装机容量
			List ry = new ArrayList();
			Map capacity = new  HashMap();
			List capacityList = new ArrayList();
			for(BrachYearDto dto :list){
				capacityList.add(dto.getCapacity());
			}
			capacity.put("name", "装机容量");
			capacity.put("data", capacityList);
			ry.add(capacity);
			jsonmap.put("ry", ry);
			}
		return jsonmap;
	}
	
	/**
	 * 进入电站月发电贡献统计分析
	 * @return
	 */
	@RequestMapping("/enterStaMonthEContribute")
	public String enterStaMonthEContribute(){
		return "oms/branch/staMonthEContribute";
	}
	
	
	/**
	 * 显示电站月发电贡献统计分析
	 * @return
	 */
	@RequestMapping("/displayStaMonthEContribute")
	@ResponseBody
	public Map displayStaMonthEContribute(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="1")Integer paramonth){
		
		Map jsonmap = new HashMap();
		List<BranchMonthDto> list = branchCompareMonthService.getStaMonth(orgid,parayear,paramonth);
		if(null!=list&&list.size()>0){
			jsonmap.put("table", list);
			Integer year = list.get(0).getYear();
			Integer month = list.get(0).getBmonth();
			jsonmap.put("year", year);
			jsonmap.put("month", month);
			List orgName = new ArrayList();
			for(BranchMonthDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsonmap.put("x", orgName);
			
			List ly = new ArrayList();
			//发电量
			Map e = new  HashMap();
			List eList = new ArrayList();
			for(BranchMonthDto dto :list){
				eList.add(dto.getE());
			}
		
			e.put("name", "发电量");
			e.put("data", eList);
			ly.add(e);
			
			//上网电量
			
			Map onnet = new  HashMap();
			List onnetList = new ArrayList();
			for(BranchMonthDto dto :list){
				onnetList.add(dto.getOnnete());
			}
			onnet.put("name", "上网电量");
			onnet.put("data", onnetList);
			ly.add(onnet);
			
			jsonmap.put("ly", ly);
			
			//装机容量
			List ry = new ArrayList();
			Map capacity = new  HashMap();
			List capacityList = new ArrayList();
			for(BranchMonthDto dto :list){
				capacityList.add(dto.getCapacity());
			}
			capacity.put("name", "装机容量");
			capacity.put("data", capacityList);
			ry.add(capacity);
			jsonmap.put("ry", ry);
			}
		return jsonmap;
	}
	
	
	
	/**
	 * 进入电站间年发电效率
	 * @return
	 */
	@RequestMapping("/enterStaYearEEffict")
	public String enterStaYearEffict(){
		return "oms/branch/staYearEEffict_";
	}
	
	
	/**
	 * 显示电站间年发电效率
	 * @return
	 */
	@RequestMapping("/displayStaYearEffict")
	@ResponseBody
	public Map displayStaYearEffict(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear){
		Map jsommap = new HashMap();
		List<BrachYearDto> list = branchCompareYearService.getStaYear(orgid,parayear);
		if(null!=list&&list.size()>0){
			jsommap.put("table", list);
			Integer year = list.get(0).getYear();
			jsommap.put("year", year);
			
			List orgName = new ArrayList();
			for(BrachYearDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsommap.put("x", orgName);
			
			List effict = new LinkedList();
			for(BrachYearDto dto :list){
				effict.add(dto.getEffict());
			}
			jsommap.put("y", effict);
		}
		return jsommap;
	}
	
	/**
	 * 进入电站间月发电效率
	 * @return
	 */
	@RequestMapping("/enterStaMonthEEffict")
	public String enterStaMonthEEffict(){
		return "oms/branch/staMonthEEffict";
	}
	
	
	/**
	 * 显示电站间月发电效率
	 * @return
	 */
	@RequestMapping("/displayStaMonthEffict")
	@ResponseBody
	public Map displayStaEffict(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="1")Integer paramonth){
		Map jsommap = new HashMap();
		List<BranchMonthDto> list = branchCompareMonthService.getStaMonth(orgid, parayear,paramonth);
		if(null!=list&&list.size()>0){
			jsommap.put("table", list);
			Integer year = list.get(0).getYear();
			Integer month = list.get(0).getBmonth();
			jsommap.put("year", year);
			jsommap.put("month", month);
			List orgName = new ArrayList();
			for(BranchMonthDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsommap.put("x", orgName);
			
			List effict = new LinkedList();
			for(BranchMonthDto dto :list){
				effict.add(dto.getEffict());
			}
			jsommap.put("y", effict);
		}
		return jsommap;
	}
	
	/**
	 * 进入电站间年单位兆瓦发电量对比
	 * @return
	 */
	@RequestMapping("/enterStaYearSinglemweE")
	public String enterStaYearSinglemweE(){
		return "oms/branch/staYearSinglemweE";
	}
	
	
	/**
	 * 显示电站间年单位兆瓦发电量对比
	 * @return
	 */
	@RequestMapping("/displayStaYearSinglemweE")
	@ResponseBody
	public Map displayStaYearSinglemweE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear){
		Map jsommap = new HashMap();
		List<BrachYearDto> list = branchCompareYearService.getStaYear(orgid,parayear);
		if(null!=list&&list.size()>0){
			jsommap.put("table", list);
			
			Integer year = list.get(0).getYear();
			jsommap.put("year", year);
			List orgName = new ArrayList();
			for(BrachYearDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsommap.put("x", orgName);
			
			List singlemwe = new LinkedList();
			for(BrachYearDto dto :list){
				singlemwe.add(dto.getSinglemwe());
			}
			jsommap.put("y", singlemwe);
		}
		
		return jsommap;
	}
	
	/**
	 * 进入电站间月单位兆瓦发电量对比
	 * @return
	 */
	@RequestMapping("/enterStaMonthSinglemweE")
	public String enterStaMonthSinglemweE(){
		return "oms/branch/staMonthSinglemweE";
	}
	
	
	/**
	 * 显示电站间月单位兆瓦发电量对比
	 * @return
	 */
	@RequestMapping("/displayStaMonthSinglemweE")
	@ResponseBody
	public Map displayStaMonthSinglemweE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="1")Integer paramonth){
		Map jsommap = new HashMap();
		List<BranchMonthDto> list = branchCompareMonthService.getStaMonth(orgid,parayear,paramonth);
		if(null!=list&&list.size()>0){
			jsommap.put("table", list);
			Integer year = list.get(0).getYear();
			Integer month = list.get(0).getBmonth();
			jsommap.put("year", year);
			jsommap.put("month", month);
			List orgName = new ArrayList();
			for(BranchMonthDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsommap.put("x", orgName);
			
			List singlemwe = new LinkedList();
			for(BranchMonthDto dto :list){
				singlemwe.add(dto.getSinglemwe());
			}
			jsommap.put("y", singlemwe);
		}
		return jsommap;
	}
	
	/**
	 * 进入电站间日单位兆瓦发电量对比
	 * @return
	 */
	@RequestMapping("/enterStaDaySinglemweE")
	public String enterStaDaySinglemweE(){
		return "oms/branch/staDaySinglemweE";
	}
	
	
	/**
	 * 显示电站间日单位兆瓦发电量对比
	 * @return
	 */
	@RequestMapping("/displayStaDaySinglemweE")
	@ResponseBody
	public Map displayStaDaySinglemweE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="1")Integer paramonth,@RequestParam(defaultValue="1")Integer paraday){
		Map jsommap = new HashMap();
		List<BranchDayDto> list = branchCompareDayService.getStaDay(orgid,parayear,paramonth,paraday);
		if(null!=list&&list.size()>0){
			jsommap.put("table", list);
			Integer year = list.get(0).getYear();
			Integer month = list.get(0).getMonth();
			Integer day = list.get(0).getDay();
			jsommap.put("year", year);
			jsommap.put("month", month);
			jsommap.put("day", day);
			List orgName = new ArrayList();
			for(BranchDayDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsommap.put("x", orgName);
			
			List singlemwe = new LinkedList();
			for(BranchDayDto dto :list){
				singlemwe.add(dto.getSinglemwe());
			}
			jsommap.put("y", singlemwe);
		}
		
		return jsommap;
	}
	
	/**
	 * 进入电站间年等效利用小时对比分析
	 * @return
	 */
	@RequestMapping("/enterStaYearEusehours")
	public String enterStaYearEusehours(){
		return "oms/branch/staYearEusehours";
	}
	
	
	/**
	 * 显示电站间年等效利用小时对比分析
	 * @return
	 */
	@RequestMapping("/displayStaYearEusehours")
	@ResponseBody
	public Map displayStaYearEusehours(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear){
		Map jsommap = new HashMap();
		List<BrachYearDto> list = branchCompareYearService.getStaYear(orgid,parayear);
		if(null!=list&&list.size()>0){
			jsommap.put("table", list);
			Integer year = list.get(0).getYear();
			jsommap.put("year", year);
			List orgName = new ArrayList();
			for(BrachYearDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsommap.put("x", orgName);
			
			List eusehours = new LinkedList();
			for(BrachYearDto dto :list){
				eusehours.add(dto.getEusehours());
			}
			jsommap.put("y", eusehours);
		}
		return jsommap;
	}
	
	
	/**
	 * 进入电站间月等效利用小时对比分析
	 * @return
	 */
	@RequestMapping("/enterStaMonthEusehours")
	public String enterStaMonthEusehours(){
		return "oms/branch/staMonthEusehours";
	}
	
	
	/**
	 * 显示电站间月等效利用小时对比分析
	 * @return
	 */
	@RequestMapping("/displayStaMonthEusehours")
	@ResponseBody
	public Map displayStaMonthEusehours(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="1")Integer paramonth){
		Map jsommap = new HashMap();
		List<BranchMonthDto> list = branchCompareMonthService.getStaMonth(orgid,parayear,paramonth);
		if(null!=list&&list.size()>0){
			jsommap.put("table", list);
			Integer year = list.get(0).getYear();
			Integer month = list.get(0).getBmonth();
			jsommap.put("year", year);
			jsommap.put("month", month);
			List orgName = new ArrayList();
			for(BranchMonthDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsommap.put("x", orgName);
			
			List eusehours = new LinkedList();
			for(BranchMonthDto dto :list){
				eusehours.add(dto.getEusehours());
			}
			jsommap.put("y", eusehours);
		}
		return jsommap;
	}
	
	
	
	/**
	 * 进入电站间日等效利用小时对比分析
	 * @return
	 */
	@RequestMapping("/enterStaDayEusehours")
	public String enterStaDayEusehours(){
		return "oms/branch/staDayEusehours";
	}
	
	
	/**
	 * 显示电站间日等效利用小时对比分析
	 * @return
	 */
	@RequestMapping("/displayStaDayEusehours")
	@ResponseBody
	public Map displayStaDayEusehours(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="1")Integer paramonth,@RequestParam(defaultValue="1")Integer paraday){
		Map jsommap = new HashMap();
		List<BranchDayDto> list = branchCompareDayService.getStaDay(orgid,parayear,paramonth,paraday);
		if(null!=list&&list.size()>0){
			jsommap.put("table", list);
			Integer year = list.get(0).getYear();
			Integer month = list.get(0).getMonth();
			Integer day = list.get(0).getDay();
			jsommap.put("year", year);
			jsommap.put("month", month);
			jsommap.put("day", day);
			List orgName = new ArrayList();
			for(BranchDayDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsommap.put("x", orgName);
			
			List eusehours = new LinkedList();
			for(BranchDayDto dto :list){
				eusehours.add(dto.getEusehours());
			}
			jsommap.put("y", eusehours);
		}
		return jsommap;
	}
	
	/**
	 * 进入电站间年发电计划执行分析
	 * @return
	 */
	@RequestMapping("/enterStaYearPlanE")
	public String enterStaYearPlanE(){
		return "oms/branch/staYearPlanE";
	}
	
	/**
	 * 显示电站间年发电计划执行分析
	 * @return
	 */
	@RequestMapping("/displayStaYearPlanE")
	@ResponseBody
	public Map displayStaYearPlanE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear){
		Map jsonmap = new HashMap();
		List<BrachYearDto> list = branchCompareYearService.getStaYear(orgid,parayear);
		if(null!=list&&list.size()>0){
			jsonmap.put("table", list);
			Integer year = list.get(0).getYear();
			jsonmap.put("year", year);
			List orgName = new ArrayList();
			for(BrachYearDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsonmap.put("x", orgName);
			
			List ly = new ArrayList();
			//发电量
			Map e = new  HashMap();
			List elist = new ArrayList();
			for(BrachYearDto dto :list){
				elist.add(dto.getE());
			}
		
			e.put("name", "发电量");
			e.put("data", elist);
			ly.add(e);
			
			//计划发电量
			Map plane = new  HashMap();
			List planlist = new ArrayList();
			for(BrachYearDto dto :list){
				planlist.add(dto.getPlanE());
			}
			plane.put("name", "计划发电量");
			plane.put("data", elist);
			ly.add(plane);
			
			jsonmap.put("ly", ly);
			
			List ry = new ArrayList();
			
			for(BrachYearDto dto :list){
				ry.add(dto.getPlanEffict());
			}
			jsonmap.put("ry", ry);
			
		}
		return jsonmap;
	}
	
	/**
	 * 进入电站间月发电计划执行分析
	 * @return
	 */
	@RequestMapping("/enterStaMonthPlanE")
	public String enterStaMonthPlanE(){
		return "oms/branch/staMonthPlanE";
	}
	
	/**
	 * 显示电站间月发电计划执行分析
	 * @return
	 */
	@RequestMapping("/displayStaMonthPlanE")
	@ResponseBody
	public Map displayStaMonthPlanE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="1")Integer paramonth){
		Map jsonmap = new HashMap();
		
		List<BranchMonthDto> list = branchCompareMonthService.getStaMonth(orgid,parayear,paramonth);
		if(null!=list&&list.size()>0){
			jsonmap.put("table", list);
			Integer year = list.get(0).getYear();
			Integer month = list.get(0).getBmonth();
			jsonmap.put("year", year);
			jsonmap.put("month", month);
			List orgName = new ArrayList();
			for(BranchMonthDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsonmap.put("x", orgName);
			
			List ly = new ArrayList();
			//发电量
			Map e = new  HashMap();
			List elist = new ArrayList();
			for(BranchMonthDto dto :list){
				elist.add(dto.getE());
			}
		
			e.put("name", "发电量");
			e.put("data", elist);
			ly.add(e);
			
			//计划发电量
			Map plane = new  HashMap();
			List planlist = new ArrayList();
			for(BranchMonthDto dto :list){
				planlist.add(dto.getPlanE());
			}
			plane.put("name", "计划发电量");
			plane.put("data", elist);
			ly.add(plane);
			
			jsonmap.put("ly", ly);
			
			List ry = new ArrayList();
			
			for(BranchMonthDto dto :list){
				ry.add(dto.getPlanEffict());
			}
			jsonmap.put("ry", ry);
		}
			
		return jsonmap;
	}
	
	/**
	 * 进入电站间年上网计划执行分析
	 * @return
	 */
	@RequestMapping("/enterStaYearPlanOnnetE")
	public String enterStaYearPlanOnnetE(){
		return "oms/branch/staYearPlanOnnetE";
	}
	
	
	/**
	 * 显示电站间年上网计划执行分析
	 * @return
	 */
	@RequestMapping("/displayStaYearPlanOnnetE")
	@ResponseBody
	public Map displayStaYearPlanOnnetE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear){
		Map jsonmap = new HashMap();
		List<BrachYearDto> list = branchCompareYearService.getStaYear(orgid,parayear);
		if(null!=list&&list.size()>0){
			jsonmap.put("table", list);
			Integer year = list.get(0).getYear();
			jsonmap.put("year", year);
			List orgName = new ArrayList();
			for(BrachYearDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsonmap.put("x", orgName);
			
			List ly = new ArrayList();
			//上网电量
			Map onnetE = new  HashMap();
			List elist = new ArrayList();
			for(BrachYearDto dto :list){
				elist.add(dto.getOnnete());
			}
		
			onnetE.put("name", "上网电量");
			onnetE.put("data", elist);
			ly.add(onnetE);
			
			//计划上网电量
			Map planOnnete = new  HashMap();
			List planOnnetlist = new ArrayList();
			for(BrachYearDto dto :list){
				planOnnetlist.add(dto.getPlanOne());
			}
			planOnnete.put("name", "计划上网电量");
			planOnnete.put("data", elist);
			ly.add(planOnnete);
			
			jsonmap.put("ly", ly);
			
			List ry = new ArrayList();
			
			for(BrachYearDto dto :list){
				ry.add(dto.getPlanOnnetEffict());
			}
			jsonmap.put("ry", ry);
			
		}
		
		return jsonmap;
	}
	
	
	/**
	 * 进入电站间月上网计划执行分析
	 * @return
	 */
	@RequestMapping("/enterStaMonthPlanOnnetE")
	public String enterStaMonthPlanOnnetE(){
		return "oms/branch/staMonthPlanOnnetE";
	}
	
	
	/**
	 * 显示电站间月上网计划执行分析
	 * @return
	 */
	@RequestMapping("/displayStaMonthPlanOnnetE")
	@ResponseBody
	public Map displayStaMonthPlanOnnetE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="1")Integer paramonth){
		Map jsonmap = new HashMap();
		List<BranchMonthDto> list = branchCompareMonthService.getStaMonth(orgid,parayear,paramonth);
		if(null!=list&&list.size()>0){
			jsonmap.put("table", list);
			Integer year = list.get(0).getYear();
			Integer month = list.get(0).getBmonth();
			jsonmap.put("year", year);
			jsonmap.put("month", month);
			List orgName = new ArrayList();
			for(BranchMonthDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsonmap.put("x", orgName);
			
			List ly = new ArrayList();
			//上网电量
			Map planE = new  HashMap();
			List planElist = new ArrayList();
			for(BranchMonthDto dto :list){
				planElist.add(dto.getPlanE());
			}
		
			planE.put("name", "上网电量");
			planE.put("data", planElist);
			ly.add(planE);
			
			//计划上网电量
			Map planOnnete = new  HashMap();
			List planOnnetlist = new ArrayList();
			for(BranchMonthDto dto :list){
				planOnnetlist.add(dto.getPlanE());
			}
			planOnnete.put("name", "计划发电量");
			planOnnete.put("data", planOnnetlist);
			ly.add(planOnnete);
			
			jsonmap.put("ly", ly);
			
			List ry = new ArrayList();
			
			for(BranchMonthDto dto :list){
				ry.add(dto.getPlanOnnetEffict());
			}
			jsonmap.put("ry", ry);
		}
		return jsonmap;
	}
	
	/**
	 * 进入电站间年厂用电量对比分析
	 * @return
	 */
	@RequestMapping("/enterStaYearfactoryE")
	public String enterStaYearfactoryE(){
		return "oms/branch/staYearfactoryE";
	}
	
	
	/**
	 * 显示电站间年厂用电量对比分析
	 * @return
	 */
	@RequestMapping("/displayStaYearfactoryE")
	@ResponseBody
	public Map displayStaYearfactoryE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear){
		Map jsonmap = new HashMap();
		List<BrachYearDto> list = branchCompareYearService.getStaYear(orgid,parayear);
		if(null!=list&&list.size()>0){
			jsonmap.put("table", list);
			Integer year = list.get(0).getYear();
			jsonmap.put("year", year);
			List orgName = new ArrayList();
			for(BrachYearDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsonmap.put("x", orgName);
			
			List ly = new ArrayList();
			//厂用电量
			Map factoryE = new  HashMap();
			List factoryList = new ArrayList();
			for(BrachYearDto dto :list){
				factoryList.add(dto.getFactorye());
			}
		
			factoryE.put("name", "厂用电量");
			factoryE.put("data", factoryList);
			ly.add(factoryE);
			jsonmap.put("ly", ly);
			
			//厂用电率
			List ry = new ArrayList();
			Map factoryEffict = new  HashMap();
			List factoryEffictList = new ArrayList();
			for(BrachYearDto dto :list){
				factoryEffictList.add(dto.getFactoryEffict());
			}
			factoryEffict.put("name", "厂用电率");
			factoryEffict.put("data", factoryEffictList);
			ry.add(factoryEffict);
			jsonmap.put("ry", ry);
			}
		return jsonmap;
	}
	
	/**
	 * 进入电站间年月用电量对比分析
	 * @return
	 */
	@RequestMapping("/enterStaMonthfactoryE")
	public String enterStaMonthfactoryE(){
		return "oms/branch/staMonthfactoryE";
	}
	
	
	/**
	 * 显示电站间月厂用电量对比分析
	 * @return
	 */
	@RequestMapping("/displayStaMonthfactoryE")
	@ResponseBody
	public Map displayStaMonthfactoryE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="1")Integer paramonth){
		Map jsonmap = new HashMap();
		List<BranchMonthDto> list = branchCompareMonthService.getStaMonth(orgid,paramonth,paramonth);
		if(null!=list&&list.size()>0){
			jsonmap.put("table", list);
			Integer year = list.get(0).getYear();
			Integer month = list.get(0).getBmonth();
			jsonmap.put("year", year);
			jsonmap.put("month", month);
			List orgName = new ArrayList();
			for(BranchMonthDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsonmap.put("x", orgName);
			
			List ly = new ArrayList();
			//厂用电量
			Map factoryE = new  HashMap();
			List factoryElist = new ArrayList();
			for(BranchMonthDto dto :list){
				factoryElist.add(dto.getFactorye());
			}
		
			factoryE.put("name", "厂用电量");
			factoryE.put("data", factoryElist);
			ly.add(factoryE);
			jsonmap.put("ly", ly);
			
			//综合厂用电率
			List ry = new ArrayList();
			Map sumFactoryEffict = new  HashMap();
			List psumFactoryEffictList = new ArrayList();
			for(BranchMonthDto dto :list){
				psumFactoryEffictList.add(dto.getFactoryEffict());
			}
			sumFactoryEffict.put("name", "厂用电率");
			sumFactoryEffict.put("data", psumFactoryEffictList);
			ry.add(sumFactoryEffict);
	
			jsonmap.put("ry", ry);
		}
		return jsonmap;
	}
	
	/**
	 * 进入电站间年综合厂用电量对比分析
	 * @return
	 */
	@RequestMapping("/enterStaYearSumfactoryE")
	public String enterStaYearSumfactoryE(){
		return "oms/branch/staYearSumfactoryE";
	}
	
	
	/**
	 * 显示电站间年综合厂用电量对比分析
	 * @return
	 */
	@RequestMapping("/displayStaYearSumfactoryE")
	@ResponseBody
	public Map displayStaYearSumfactoryE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear){
		Map jsonmap = new HashMap();
		List<BrachYearDto> list = branchCompareYearService.getStaYear(orgid,parayear);
		if(null!=list&&list.size()>0){
			jsonmap.put("table", list);
			Integer year = list.get(0).getYear();
			jsonmap.put("year", year);
			List orgName = new ArrayList();
			for(BrachYearDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsonmap.put("x", orgName);
			
			List ly = new ArrayList();
			//厂用电量
			Map factoryE = new  HashMap();
			List factoryList = new ArrayList();
			for(BrachYearDto dto :list){
				factoryList.add(dto.getSumFactorye());
			}
		
			factoryE.put("name", "综合厂用电量");
			factoryE.put("data", factoryList);
			ly.add(factoryE);
			jsonmap.put("ly", ly);
			
			//厂用电率
			List ry = new ArrayList();
			Map factoryEffict = new  HashMap();
			List factoryEffictList = new ArrayList();
			for(BrachYearDto dto :list){
				factoryEffictList.add(dto.getSumFactoryEffict());
			}
			factoryEffict.put("name", "综合厂用电率");
			factoryEffict.put("data", factoryEffictList);
			ry.add(factoryEffict);
			jsonmap.put("ry", ry);
			}
		return jsonmap;
	}
	
	/**
	 * 进入电站间年月综合用电量对比分析
	 * @return
	 */
	@RequestMapping("/enterStaMonthSumfactoryE")
	public String enterStaMonthSumfactoryE(){
		return "oms/branch/staMonthSumfactoryE";
	}
	
	
	/**
	 * 显示电站间年综合厂用电量对比分析
	 * @return
	 */
	@RequestMapping("/displayStaMonthSumfactoryE")
	@ResponseBody
	public Map displayStaMonthSumfactoryE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="1")Integer paramonth){
		Map jsonmap = new HashMap();
		List<BranchMonthDto> list = branchCompareMonthService.getStaMonth(orgid,parayear,paramonth);
		if(null!=list&&list.size()>0){
			jsonmap.put("table", list);
			Integer year = list.get(0).getYear();
			Integer month = list.get(0).getBmonth();
			jsonmap.put("year", year);
			jsonmap.put("month", month);
			List orgName = new ArrayList();
			for(BranchMonthDto dto :list){
				orgName.add(dto.getOrganName());
			}
			jsonmap.put("x", orgName);
			
			List ly = new ArrayList();
			//厂用电量
			Map factoryE = new  HashMap();
			List factoryElist = new ArrayList();
			for(BranchMonthDto dto :list){
				factoryElist.add(dto.getSumFactorye());
			}
		
			factoryE.put("name", "综合厂用电量");
			factoryE.put("data", factoryElist);
			ly.add(factoryE);
			jsonmap.put("ly", ly);
			
			//综合厂用电率
			List ry = new ArrayList();
			Map sumFactoryEffict = new  HashMap();
			List psumFactoryEffictList = new ArrayList();
			for(BranchMonthDto dto :list){
				psumFactoryEffictList.add(dto.getSumFactoryEffict());
			}
			sumFactoryEffict.put("name", "综合厂用电率");
			sumFactoryEffict.put("data", psumFactoryEffictList);
			ry.add(sumFactoryEffict);
	
			jsonmap.put("ry", ry);
		}
		return jsonmap;
	}

}
