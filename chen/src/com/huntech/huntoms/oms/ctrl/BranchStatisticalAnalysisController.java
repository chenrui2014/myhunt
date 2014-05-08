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
import com.huntech.huntoms.oms.service.BrachYearService;
import com.huntech.huntoms.oms.service.BranchDayService;
import com.huntech.huntoms.oms.service.BranchMonthService;
 


@Controller
@RequestMapping("/branch")
public class BranchStatisticalAnalysisController {
	
	@Autowired
	private BrachYearService brachYearService;
	
	@Autowired 
	private BranchMonthService   branchMonthService;
	
	@Autowired
	private BranchDayService branchDayService;
	
	
	@RequestMapping("/displayIndex")
	public String displayIndex(){
		return "oms/branch/branchYearE";
	}
	
	@RequestMapping(value = "/displayBranchYearE")
    @ResponseBody
	public Map displayBranchYearE(@RequestParam(defaultValue="2")Integer orgid)throws Exception{
		Map  jsonMap  =  new HashMap();
		List<BrachYearDto> list =brachYearService.getBranchYearE(orgid);
		jsonMap.put("table", list);
		if(list.size()==0) return null;
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		String[] yearArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			yearArray[i]= String.valueOf(list.get(i).getYear());
		}
		jsonMap.put("year", yearArray);
		
		List listsun = new LinkedList();
		//年实际发电量
		Map mape = new HashMap();
		mape.put("name", "年实际发电量");
		String[] eArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			eArray[i]= String.valueOf(list.get(i).getE());
		}
		mape.put("data", eArray);
		mape.put("name", "年实际发电量");
		listsun.add(mape);
		
		//onnete上网电量 
		Map maponnete = new HashMap();
		String[] onneteArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			onneteArray[i]= String.valueOf(list.get(i).getPlanE());
		}
		maponnete.put("data", onneteArray);
		maponnete.put("name", "上网电量");
		listsun.add(maponnete);
		jsonMap.put("datasum", listsun);
		
		//理论发电量
		Map maptheorye = new HashMap();
		String[] theoryeArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			theoryeArray[i]= String.valueOf(list.get(i).getTheorye());
		}
		maptheorye.put("data", theoryeArray);
		maptheorye.put("name", "理论发电量");
		listsun.add(maptheorye);
		
		//弃光电量
		Map maplosse = new HashMap();
		String[] maplosseArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			maplosseArray[i]= String.valueOf(list.get(i).getLosse());
		}
		maplosse.put("data", maplosseArray);
		maplosse.put("name", "弃光电量");
		listsun.add(maplosse);
		
		//计划发电量
		Map mapPlanE = new HashMap();
		String[] planEArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			planEArray[i]= String.valueOf(list.get(i).getPlanE());
		}
		mapPlanE.put("data", planEArray);
		mapPlanE.put("name", "计划发电量");
		listsun.add(mapPlanE);
		
		// 计划上网电量
		Map mapplanOne = new HashMap();
		String[] planOneArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			planOneArray[i]= String.valueOf(list.get(i).getPlanOne());
		}
		mapplanOne.put("data", planOneArray);
		mapplanOne.put("name", "计划上网电量");
		listsun.add(mapplanOne);
		

		return jsonMap;
	}
	
	@RequestMapping("/displayBranchM")
	public String displayBranchM(){
		return "oms/branch/branchMonthE";
	}
	
	@RequestMapping(value = "/displayBranchMonthE")
    @ResponseBody
	public Map displayBranchMonthE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="0")Integer paramonth) throws Exception{
		Map jsonMap = new HashMap();
		List<BranchMonthDto> list =branchMonthService.getBranchMonthE(orgid,parayear,paramonth);
		if(null!=list&&list.size()>0){
			jsonMap.put("table", list);
			String orgName = list.get(0).getOrganName();
			
			jsonMap.put("orgName", orgName);
			String[] month = new String[list.size()];
			for(int i=0;i<list.size();i++){
				month[i]= list.get(i).getBmonth().toString();
			}
			jsonMap.put("month", month);
			
			List listsun = new LinkedList();
			//年实际发电量
			Map mape = new HashMap();
			mape.put("name", "年实际发电量");
			Double[] eArray = new Double[list.size()];
			for(int i=0;i<list.size();i++){
				eArray[i]= list.get(i).getE();
			}
			mape.put("data", eArray);
			mape.put("name", "年实际发电量");
			listsun.add(mape);
			
			//理论发电量
			Map maptheorye = new HashMap();
			Double[] theoryeArray = new Double[list.size()];
			for(int i=0;i<list.size();i++){
				theoryeArray[i]= list.get(i).getTheorye();
			}
			maptheorye.put("data", theoryeArray);
			maptheorye.put("name", "理论发电量");
			listsun.add(maptheorye);
			
			//弃光电量
			Map maplosse = new HashMap();
			Double[] maplosseArray = new Double[list.size()];
			for(int i=0;i<list.size();i++){
				maplosseArray[i]= list.get(i).getLosse();
			}
			maplosse.put("data", maplosseArray);
			maplosse.put("name", "弃光电量");
			listsun.add(maplosse);
			
			//计划发电量
			Map mapPlanE = new HashMap();
			Double[] planEArray = new Double[list.size()];
			for(int i=0;i<list.size();i++){
				planEArray[i]= list.get(i).getPlanE();
			}
			mapPlanE.put("data", planEArray);
			mapPlanE.put("name", "计划发电量");
			listsun.add(mapPlanE);
			
			// 计划上网电量
			Map mapplanOne = new HashMap();
			Double[] planOneArray = new Double[list.size()];
			for(int i=0;i<list.size();i++){
				planOneArray[i]= list.get(i).getPlanOne();
			}
			mapplanOne.put("data", planOneArray);
			mapplanOne.put("name", "计划上网电量");
			listsun.add(mapplanOne);
			
			//onnete上网电量 
			Map maponnete = new HashMap();
			Double[] onneteArray = new Double[list.size()];
			for(int i=0;i<list.size();i++){
				onneteArray[i]= list.get(i).getPlanE();
			}
			maponnete.put("data", onneteArray);
			maponnete.put("name", "上网电量");
			listsun.add(maponnete);
			jsonMap.put("datasum", listsun);
		}
		return jsonMap;

	}
	
	
	/**
	 * 进入区域月电量统计分析
	 * @return
	 */
	@RequestMapping("/displayBranchDayIndex")
	public String displayBranchDay(){
		return "oms/branch/branchDayE";
	}
	
	/**
	 * 区域月电量统计分析
	 * @return
	 * @throws Exception
	 */
	
		@RequestMapping(value = "/displayBranchDayE")
	    @ResponseBody
	 public Map displayBranchDayE(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="0")Integer paramonth) throws Exception{
			Map jsonMap  = new HashMap();
			List<BranchDayDto> branchDayE = branchDayService.getBranchDayE(orgid,parayear,paramonth);
			if(branchDayE!=null&&branchDayE.size()>0){
				jsonMap.put("table", branchDayE);
				BranchDayDto dto = branchDayE.get(0);
				jsonMap.put("orgName", dto.getOrganName());
				jsonMap.put("year", dto.getYear());
				jsonMap.put("month", dto.getMonth());
				List dayList = new ArrayList();
				for(BranchDayDto day :branchDayE){
					dayList.add(day.getDay());
				}
				jsonMap.put("day", dayList);
				
				List list = new LinkedList();
				//实际发电量
				Map e = new HashMap();
				List elist = new  ArrayList();
				for(BranchDayDto day :branchDayE){
					elist.add(day.getE());
				}
				e.put("name", "实际发电量");
				e.put("data", elist);
				list.add(e);
				//jsonMap.put("e", e);
				
				//上网电量
				Map onnet = new HashMap();
				List onnetList = new  ArrayList();
				for(BranchDayDto day :branchDayE){
					onnetList.add(day.getOnnete());
				}
				onnet.put("name", "上网电量");
				onnet.put("data", onnetList);
				list.add(onnet);
				//jsonMap.put("onnet", onnet);
				jsonMap.put("list", list);
			}
			return jsonMap;
		}
		
		/**
		 * 区域历年发电效率统计分析
		 * @return
		 */
		@RequestMapping("/enterBranchYearEffic")
		public String enterBranchYearEffic(){
			return "oms/branch/branchYearEffic";
		}
		
		
		/**
		 * 区域历年发电相率统计分析
		 * @return
		 * @throws Exception
		 */
		@RequestMapping(value = "/displayBranchYearEffic")
	    @ResponseBody
		public	Map displayBranchYearEffic(@RequestParam(defaultValue="2")Integer orgid) throws Exception{
			Map jsonMap = new HashMap();
			List<BrachYearDto> list =brachYearService.getBranchYearE(orgid);
			if(null!=list&&list.size()>0){
				jsonMap.put("table", list);
				String orgName = list.get(0).getOrganName();
				jsonMap.put("orgName", orgName);
				
				//左y轴
				List singlemwe =  new LinkedList();
				for(BrachYearDto dto :list){
					singlemwe.add(dto.getSinglemwe());
				}
				jsonMap.put("ly", singlemwe);
				//右y轴
				List effic =  new LinkedList();
				for(BrachYearDto dto :list){
					effic.add(dto.getEffict());
				}
				jsonMap.put("ry", effic);
				
				List year =  new LinkedList();
				for(BrachYearDto dto :list){
					year.add(dto.getYear());
				}
				jsonMap.put("x", year);
			}
			return jsonMap;
		}
		
		/**
		 * 进入区域年发电效率统计分析
		 * @return
		 */
		@RequestMapping("/displayBranchMonthEfficIndex")
		public String enterBranchMonthEffic(){
			return "oms/branch/branchMonthEffic";
		}
		
		/**
		 * 区域年发电效率统计分析
		 * @return
		 */
		@RequestMapping("/displayBranchMonthEffic")
		@ResponseBody
		public Map displayBranchMonthEffic(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="0")Integer paramonth){
			Map jsonMap = new HashMap();
			List<BranchMonthDto> branchMonthE = branchMonthService.getBranchMonthE(orgid,parayear,paramonth);
			if(null!=branchMonthE&&branchMonthE.size()>0){
				jsonMap.put("table", branchMonthE);
				String orgName = branchMonthE.get(0).getOrganName();
				Integer year = branchMonthE.get(0).getYear();
				jsonMap.put("orgName", orgName);
				jsonMap.put("year", year);
				//左y轴
				List singlemwe =  new LinkedList();
				for(BranchMonthDto dto :branchMonthE){
					singlemwe.add(dto.getSinglemwe());
				}
				jsonMap.put("ly", singlemwe);
				
				
				//右y轴
				List effic =  new LinkedList();
				for(BranchMonthDto dto :branchMonthE){
					effic.add(dto.getEffict());
				}
				jsonMap.put("ry", effic);
				
				List month =  new LinkedList();
				for(BranchMonthDto dto :branchMonthE){
					month.add(dto.getBmonth());
				}
				jsonMap.put("x", month);
			}
			return jsonMap;
		}
		
		/**
		 * 进入区域年历年发电计划执行分析
		 * @return
		 */
		@RequestMapping("/enterBranchYearPlanEffic")
		public String enterBranchYearPlanEffic(){
			return "oms/branch/branchYearPlanEffict";
		}
		
		/**
		 * 显示区域年历年发电计划执行分析
		 * @return
		 */
		@RequestMapping("/dispayBranchYearPlanEffic")
		@ResponseBody
		public Map dispayBranchYearPlanEffic(@RequestParam(defaultValue="2")Integer orgid){
			Map jsonMap = new HashMap();
			List<BrachYearDto> list =brachYearService.getBranchYearE(orgid);
			if(null!=list&&list.size()>0){
				jsonMap.put("table", list);
				String orgName = list.get(0).getOrganName();
				jsonMap.put("orgName", orgName);
				String[] yearArray = new String[list.size()];
				for(int i=0;i<list.size();i++){
					yearArray[i]= String.valueOf(list.get(i).getYear());
				}
				jsonMap.put("x", yearArray);
				
				//右y轴
				List planEffic =  new LinkedList();
				for(BrachYearDto dto :list){
					planEffic.add(dto.getPlanEffict());
				}
				jsonMap.put("ry", planEffic);
				
				//左y轴
				//计划发电量
				List ly = new LinkedList();
				Map plane = new HashMap();
				List pe = new LinkedList();
				for(BrachYearDto dto :list){
					pe.add(dto.getPlanE());
				}
				plane.put("name", "计划发电量");
				plane.put("data", pe);
				ly.add(plane);
				
				//实际发电量
				Map e = new HashMap();
				List elist = new  ArrayList();
				for(BrachYearDto year :list){
					elist.add(year.getE());
				}
				e.put("name", "实际发电量");
				e.put("data", elist);
				ly.add(e);
				jsonMap.put("ly",ly);
			}
			
			return jsonMap;
		}
		
		/**
		 * 进入区域年发电计划执行分析
		 * @return
		 */
		@RequestMapping("/enterBranchMonthPlanEffic")
		public String enterBranchMonthPlanEffic(){
			return "oms/branch/branchMonthPlanEffict";
		}
		
		/**
		 * 显示区域年发电计划执行分析
		 * @return
		 */
		@RequestMapping("/dispayBranchMonthPlanEffic")
		@ResponseBody
		public Map dispayBranchMonthPlanEffic(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="0")Integer paramonth){
			Map jsonMap = new HashMap();
			List<BranchMonthDto> list =branchMonthService.getBranchMonthE(orgid,parayear,paramonth);
			if(null!=list&&list.size()>0){
				jsonMap.put("table", list);
				String orgName = list.get(0).getOrganName();
				jsonMap.put("orgName", orgName);
				String[] monthArray = new String[list.size()];
				for(int i=0;i<list.size();i++){
					monthArray[i]= String.valueOf(list.get(i).getBmonth());
				}
				jsonMap.put("x", monthArray);
				
				//右y轴
				List planEffic =  new LinkedList();
				for(BranchMonthDto dto :list){
					planEffic.add(dto.getPlanEffict());
				}
				jsonMap.put("ry", planEffic);
				
				//左y轴
				//计划发电量
				List ly = new LinkedList();
				Map plane = new HashMap();
				List pe = new LinkedList();
				for(BranchMonthDto dto :list){
					pe.add(dto.getPlanE());
				}
				plane.put("name", "计划发电量");
				plane.put("data", pe);
				ly.add(plane);
				
				//实际发电量
				Map e = new HashMap();
				List elist = new  ArrayList();
				for(BranchMonthDto dto :list){
					elist.add(dto.getE());
				}
				e.put("name", "实际发电量");
				e.put("data", elist);
				ly.add(e);
				jsonMap.put("ly",ly);
			}
			return jsonMap;
			}
		
		/**
		 * 进入区域历年上网计划执行分析
		 * @return
		 */
		@RequestMapping("/enterBranchYearPlanOnnetEffic")
		public String enterBranchYearPlanOnnetEffic(){
			return "oms/branch/branchYearPlanOnnetEffic";
		}
		
		/**
		 * 显示区域历年上网计划执行分析
		 * @return
		 */
		@RequestMapping("/dispayBranchYearPlanOnnetEffic")
		@ResponseBody
		public Map dispayBranchYearPlanOnnetEffic(@RequestParam(defaultValue="2")Integer orgid){
			Map jsonMap = new HashMap();
			List<BrachYearDto> list =brachYearService.getBranchYearE(orgid);
			if(null!=list&&list.size()>0){
				jsonMap.put("table", list);
				String orgName = list.get(0).getOrganName();
				jsonMap.put("orgName", orgName);
				String[] yearArray = new String[list.size()];
				for(int i=0;i<list.size();i++){
					yearArray[i]= String.valueOf(list.get(i).getYear());
				}
				jsonMap.put("x", yearArray);
				
				//右y轴
				List planOnnetEffic =  new LinkedList();
				for(BrachYearDto dto :list){
					planOnnetEffic.add(dto.getPlanOnnetEffict());
				}
				jsonMap.put("ry", planOnnetEffic);
				
				//左y轴
				//计划上网发电量
				List ly = new LinkedList();
				Map planeOnnet = new HashMap();
				List poe = new LinkedList();
				for(BrachYearDto dto :list){
					poe.add(dto.getPlanOne());
				}
				planeOnnet.put("name", "计划上网电量");
				planeOnnet.put("data", poe);
				ly.add(planeOnnet);
				
				//实际上网电量
				Map onnetE = new HashMap();
				List eOnnetlist = new  ArrayList();
				for(BrachYearDto dto :list){
					eOnnetlist.add(dto.getPlanOne());
				}
				onnetE.put("name", "实际上网量");
				onnetE.put("data", eOnnetlist);
				ly.add(onnetE);
				jsonMap.put("ly",ly);
			}
			return jsonMap;
		}
		
		/**
		 * 进入区域年上网计划执行分析
		 * @return
		 */
		@RequestMapping("/enterBranchMonthPlanOnnetEffic")
		public String enterBranchMonthPlanOnnetEffic(){
			return "oms/branch/branchMonthPlanOnnetEffic";
		}
		
		/**
		 * 显示区域年上网计划执行分析
		 * @return
		 */
		@RequestMapping("/dispayBranchMonthPlanOnnetEffic")
		@ResponseBody
		public Map dispayBranchMonthPlanOnnetEffic(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="0")Integer paramonth){
			Map jsonMap = new HashMap();
			List<BranchMonthDto> list =branchMonthService.getBranchMonthE(orgid,parayear,paramonth);
			if(null!=list&&list.size()>0){
				jsonMap.put("table", list);
				String orgName = list.get(0).getOrganName();
				jsonMap.put("orgName", orgName);
				String[] monthArray = new String[list.size()];
				for(int i=0;i<list.size();i++){
					monthArray[i]= String.valueOf(list.get(i).getBmonth());
				}
				jsonMap.put("x", monthArray);
				
				//右y轴
				List planOnnetEffic =  new LinkedList();
				for(BranchMonthDto dto :list){
					planOnnetEffic.add(dto.getPlanOne());
				}
				jsonMap.put("ry", planOnnetEffic);
				
				//左y轴
				//计划上网电量
				List ly = new LinkedList();
				Map planeOnnet = new HashMap();
				List poe = new LinkedList();
				for(BranchMonthDto dto :list){
					poe.add(dto.getPlanOne());
				}
				planeOnnet.put("name", "计划上网电量");
				planeOnnet.put("data", poe);
				ly.add(planeOnnet);
				
				//实际上网电量
				Map onnetE = new HashMap();
				List eOnnetlist = new  ArrayList();
				for(BranchMonthDto dto :list){
					eOnnetlist.add(dto.getOnnete());
				}
				onnetE.put("name", "实际上网电量");
				onnetE.put("data", eOnnetlist);
				ly.add(onnetE);
				jsonMap.put("ly",ly);
			}
			return jsonMap;
			}
		
		/**
		 * 进入区域历年能耗指标分析
		 * @return
		 */
		@RequestMapping("/enterBranchYearEnergyConsumption")
		public String enterBranchYearEnergyConsumption(){
			return "oms/branch/branchYearEnergyConsumption";
		}                      
		
		/**
		 * 显示区域历年能耗指标分析
		 * @return
		 */
		@RequestMapping("/displayBranchYearEnergyConsumption")
		@ResponseBody
		public Map displayBranchYearEnergyConsumption(@RequestParam(defaultValue="2")Integer orgid){
			Map jsonMap = new HashMap();
			List<BrachYearDto> list =brachYearService.getBranchYearE(orgid);
			if(null!=list&&list.size()>0){
				jsonMap.put("table", list);
				String orgName = list.get(0).getOrganName();
				jsonMap.put("orgName", orgName);
				String[] yearArray = new String[list.size()];
				for(int i=0;i<list.size();i++){
					yearArray[i]= String.valueOf(list.get(i).getYear());
				}
				jsonMap.put("x", yearArray);
				
				//右y轴
				List ry =  new LinkedList();
				List factoryEffict =  new LinkedList();
				for(BrachYearDto dto :list){
					factoryEffict.add(dto.getFactoryEffict());
				}
				Map factoryEffictMap = new HashMap();
				factoryEffictMap.put("name", "厂用电率");
				factoryEffictMap.put("data", factoryEffict);
				ry.add(factoryEffictMap);
				
				Map sumfactoryEffictMap = new HashMap();
				List sumFactoryEffict =  new LinkedList();
				for(BrachYearDto dto :list){
					sumFactoryEffict.add(dto.getSumFactoryEffict());
				}
				sumfactoryEffictMap.put("name", "综合厂用电率");
				sumfactoryEffictMap.put("data", sumFactoryEffict);
				ry.add(sumfactoryEffictMap);
				
				jsonMap.put("ry", ry);
				
				//左y轴
				//厂用电量
				List ly = new LinkedList();
				Map facatoryE = new HashMap();
				List factoryE = new LinkedList();
				for(BrachYearDto dto :list){
					factoryE.add(dto.getFactorye());
				}
				facatoryE.put("name", "厂用电量");
				facatoryE.put("data", factoryE);
				ly.add(facatoryE);
				
				//综合厂用电量
				Map sumFacatoryE = new HashMap();
				List sumFacatoryElist = new  ArrayList();
				for(BrachYearDto dto :list){
					sumFacatoryElist.add(dto.getSumFactorye());
				}
				sumFacatoryE.put("name", "综合厂用电量");
				sumFacatoryE.put("data", sumFacatoryElist);
				ly.add(sumFacatoryE);
				jsonMap.put("ly", ly);
			}
			
			return jsonMap;
		}
		
		/**
		 * 进入区域年能耗指标分析
		 * @return
		 */
		@RequestMapping("/enterBranchMonthEnergyConsumption")
		public String enterBranchMonthEnergyConsumption(){
			return "oms/branch/branchMonthEnergyConsumption";
		}
		
		/**
		 * 显示区域年能耗指标分析
		 * @return
		 */
		@RequestMapping("/displayBranchMonthEnergyConsumption")
		@ResponseBody
		public Map displayBranchMonthEnergyConsumption(@RequestParam(defaultValue="2")Integer orgid,@RequestParam(defaultValue="2014")Integer parayear,@RequestParam(defaultValue="0")Integer paramonth){
			Map jsonMap = new HashMap();
			List<BranchMonthDto> list =branchMonthService.getBranchMonthE(orgid,parayear,paramonth);
			if(null!=list&&list.size()>0){
				jsonMap.put("table", list);
				String orgName = list.get(0).getOrganName();
				jsonMap.put("orgName", orgName);
				String[] monthArray = new String[list.size()];
				for(int i=0;i<list.size();i++){
					monthArray[i]= String.valueOf(list.get(i).getBmonth());
				}
				jsonMap.put("x", monthArray);
				
				//右y轴
				List ry =  new LinkedList();
				List factoryEffict =  new LinkedList();
				for(BranchMonthDto dto :list){
					factoryEffict.add(dto.getFactoryEffict());
				}
				Map factoryEffictMap = new HashMap();
				factoryEffictMap.put("name", "厂用电率");
				factoryEffictMap.put("data", factoryEffict);
				ry.add(factoryEffictMap);
				
				
				Map sumfactoryEffictMap = new HashMap();
				List sumFactoryEffict =  new LinkedList();
				for(BranchMonthDto dto :list){
					sumFactoryEffict.add(dto.getSumFactoryEffict());
				}
				sumfactoryEffictMap.put("name", "综合厂用电率");
				sumfactoryEffictMap.put("data", sumFactoryEffict);
				ry.add(sumfactoryEffictMap);
				jsonMap.put("ry", ry);
				
				//左y轴
				//厂用电量
				List ly = new LinkedList();
				Map facatoryE = new HashMap();
				List factoryE = new LinkedList();
				for(BranchMonthDto dto :list){
					factoryE.add(dto.getFactorye());
				}
				facatoryE.put("name", "厂用电量");
				facatoryE.put("data", factoryE);
				ly.add(facatoryE);
				
				//综合厂用电量
				Map sumFacatoryE = new HashMap();
				List sumFacatoryElist = new  ArrayList();
				for(BranchMonthDto dto :list){
					sumFacatoryElist.add(dto.getSumFactorye());
				}
				sumFacatoryE.put("name", "综合厂用电量");
				sumFacatoryE.put("data", sumFacatoryElist);
				ly.add(sumFacatoryE);
				jsonMap.put("ly", ly);
			}
			return jsonMap;
		}
}
