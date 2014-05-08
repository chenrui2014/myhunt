package com.huntech.huntoms.oms.ctrl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.applet.Main;


import com.huntech.huntoms.oms.dto.ViewStaAYDto;
import com.huntech.huntoms.oms.dto.ViewStaYDto;
import com.huntech.huntoms.oms.service.ViewStaYService;

@Controller
@RequestMapping("/station")
public class ViewStaYChartController {
	@Autowired
	private ViewStaYService viewStaYService;
	
	/**
	 * 电站年查询条件organ_id (organ_name) Year_
	 */
	@RequestMapping(value="/GetStaConditionDataY")
	@ResponseBody
	public Map staYEConditionData(){
		Map jsonMap=new HashMap();
		List<ViewStaYDto> list=viewStaYService.findAll();
		Set orgIdSet=new TreeSet();
		Set orgNameSet=new TreeSet();
		Set yearSet=new TreeSet();
		for(int i=0;i<list.size();i++){
			orgIdSet.add(list.get(i).getOrganID());
			orgNameSet.add(list.get(i).getOrganName());
			yearSet.add(list.get(i).getYear_());
		}
		jsonMap.put("orgId", orgIdSet);
		jsonMap.put("orgName", orgNameSet);
		jsonMap.put("Year_", yearSet);
		return jsonMap;
	}
	/**
	 * 电站间年查询条件organ_id (organ_name) Year_ Month_
	 */
	@RequestMapping(value="/GetBetweenStaConditionDataY")
	@ResponseBody
	public Map betweenStaYEConditionData(){
		Map jsonMap=new HashMap();
		List<ViewStaYDto> list=viewStaYService.findAll();
		Set orgIdSet=new TreeSet();
		Set orgNameSet=new TreeSet();
		Set yearSet=new TreeSet();
		Set monthSet=new TreeSet();
		for(int i=0;i<list.size();i++){
			orgIdSet.add(list.get(i).getOrganID());
			orgNameSet.add(list.get(i).getOrganName());
			yearSet.add(list.get(i).getYear_());
			monthSet.add(list.get(i).getMonth_());
		}
		jsonMap.put("orgId", orgIdSet);
		jsonMap.put("orgName", orgNameSet);
		jsonMap.put("Year_", yearSet);
		jsonMap.put("Month_", monthSet);
		return jsonMap;
	}
	/**
	 * 电站年发电量
	 */
	@RequestMapping(value ="/stationYE", method = RequestMethod.GET)
	public String stationAYearE() throws Exception {
		return "oms/station/StaMonthE";
	}
	@RequestMapping(value="/displaySMonthE")
	@ResponseBody
	public Map displaySYearE(@RequestParam("organ_id") int organ_id,@RequestParam("Year_") int Year_)throws Exception{
		Map jsonMap=new HashMap();
		List<ViewStaYDto> list =viewStaYService.findByOrgIDandYear(organ_id, Year_);

		jsonMap.put("list", list);
		
		if(list.size()==0) return null;
		
		String orgName = list.get(0).getOrganName();		
		jsonMap.put("orgName", orgName);
		
		String[] month = new String[list.size()];
		for(int i=0;i<list.size();i++){
			month[i]= list.get(i).getMonth_().toString()+"月";
		}
		jsonMap.put("month", month);
		
		List listsun = new LinkedList();
		//年实际发电量
		Map mape = new HashMap();
		mape.put("name", "实际发电量");
		String[] eArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			eArray[i]= String.valueOf(list.get(i).getE());
		}
		mape.put("data", eArray);
		mape.put("name", "实际发电量");
		listsun.add(mape);
		
		//理论发电量
		Map maptheorye = new HashMap();
		String[] theoryeArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			theoryeArray[i]= String.valueOf(list.get(i).getTheoryE());
		}
		maptheorye.put("data", theoryeArray);
		maptheorye.put("name", "理论发电量");
		listsun.add(maptheorye);
		
		//弃光电量
		Map maplosse = new HashMap();
		String[] maplosseArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			maplosseArray[i]= String.valueOf(list.get(i).getLossE());
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
			planOneArray[i]= String.valueOf(list.get(i).getPlanOnE());
		}
		mapplanOne.put("data", planOneArray);
		mapplanOne.put("name", "计划上网电量");
		listsun.add(mapplanOne);
		
		//onnete上网电量 
		Map maponnete = new HashMap();
		String[] onneteArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			onneteArray[i]= String.valueOf(list.get(i).getOnNetE());
		}
		maponnete.put("data", onneteArray);
		maponnete.put("name", "上网电量");
		listsun.add(maponnete);
		jsonMap.put("datasum", listsun);
		
		return jsonMap;
	}
	
	
	/**
	 * 电站间月单兆瓦发电量对比分析
	 * 
	 **/
	@RequestMapping(value ="/stationSigleMonth", method = RequestMethod.GET)
	public String stationMonthSingleE() throws Exception {
		return "oms/station/StaSingleM";
	}
	/**
	 *月间单兆瓦发电量
	 **/
	@RequestMapping(value = "/displayVSME")
	@ResponseBody
	public Map displayStaYSE(@RequestParam("Year_")int Year_,@RequestParam("Month_")int Month_) throws Exception {
		Map jsonMap = new HashMap();

		List xList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
		List yList=new LinkedList();
		
		//List<ViewStaYDto> list = viewStaYService.findAll();
		List<ViewStaYDto> list = viewStaYService.findByYearMonth(Year_, Month_);
		
		jsonMap.put("list", list);
		for (int i = 0; i < list.size(); i++) {
			xList.add(list.get(i).getOrganName());
			y1List.add(list.get(i).getCapacity());
			y2List.add(list.get(i).getE());
			
			yList.add(list.get(i).getSingleMWE());
		}

		
		jsonMap.put("x", xList);
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		
		jsonMap.put("y", yList);

		return jsonMap;
	}
	
	/**
	 * 月间等效利用小时
	 **/
	@RequestMapping(value ="/stationMonthEuseHours", method = RequestMethod.GET)
	public String stationMonthEuseHours() throws Exception {
		return "oms/station/StaMEuseHours";
	}
	@RequestMapping(value = "/displayVMH")
	@ResponseBody
	public Map displayStaMH(@RequestParam("Year_")int Year_,@RequestParam("Month_")int Month_) throws Exception {
		Map jsonMap = new HashMap();

		List xList = new LinkedList();
		List y1List = new LinkedList();
		List y2List = new LinkedList();
		List yList = new LinkedList();

		//List<ViewStaYDto> list = viewStaYService.findAll();
		List<ViewStaYDto> list = viewStaYService.findByYearMonth(Year_, Month_);
		
		jsonMap.put("list", list);
		for (int i = 0; i < list.size(); i++) {
			xList.add(list.get(i).getOrganName());
			y1List.add(list.get(i).getCapacity());
			y2List.add(list.get(i).getE());
			
			yList.add(list.get(i).getEuseHours());
		}

		jsonMap.put("x", xList);
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		
		jsonMap.put("y", yList);

		return jsonMap;
	}
	
	
	/**
	 * 电站间月发电效率
	 */
	@RequestMapping(value ="/stationMonthEfficiency", method = RequestMethod.GET)
	public String stationYEfficiency() throws Exception {
		return "oms/station/StaMEfficiency";
	}
	
	@RequestMapping(value = "/displayVMEff")
    @ResponseBody
	public Map displayStaMEff(@RequestParam("Year_")int Year_,@RequestParam("Month_")int Month_)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
		List yList=new LinkedList();
		
		List<ViewStaYDto> list = viewStaYService.findByYearMonth(Year_, Month_);
		
		jsonMap.put("list", list);
		for(int i =0;i<list.size();i++){
			xList.add(list.get(i).getOrganName());	

			y1List.add(list.get(i).getE());
			y2List.add(list.get(i).getTheoryE());
			
		}
		
		jsonMap.put("x", xList);		
		//jsonMap.put("y", yList);
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		
		
		return jsonMap;
	}
	
	/**
	 * 电站年发电效率
	 */
	@RequestMapping(value ="/stationYEffic", method = RequestMethod.GET)
	public String stationAYEffic() throws Exception {
		return "oms/station/StaYEffic";
	}
	
	@RequestMapping(value = "/displayYEffic")
    @ResponseBody
	public Map displayStaAYEffic(@RequestParam("organ_id")int organ_id,@RequestParam("Years_")int Years_)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
		List y3List=new LinkedList();
		
		List<ViewStaYDto> list =viewStaYService.findByOrgIDandYear(organ_id, Years_);//
		
		jsonMap.put("list", list);
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		
		BigDecimal disYear_=list.get(0).getYear_();
		jsonMap.put("disYear_", disYear_);
		
		for(int i =0;i<list.size();i++){
			xList.add(list.get(i).getMonth_()+"月");	
			y1List.add(list.get(i).getE());
			y2List.add(list.get(i).getTheoryE());
			y3List.add(list.get(i).getCapacity());
			
		}
		
		jsonMap.put("x", xList);		
		//jsonMap.put("y", yList);
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		jsonMap.put("y3", y3List);
		
		
		return jsonMap;
	}
	
	
	/**
	 * 电站年计划发电完成率
	 */
	@RequestMapping(value ="/stationPlanEY", method = RequestMethod.GET)
	public String stationPlanEAY() throws Exception {
		return "oms/station/StaPlanEY";
	}
	
	@RequestMapping(value = "/displayPlanEY")
    @ResponseBody
	public Map displayStaPlanEY(@RequestParam("organ_id")int organ_id,@RequestParam("Years_")int Years_)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
				
		List<ViewStaYDto> list =viewStaYService.findByOrgIDandYear(organ_id, Years_);//
		
		jsonMap.put("list", list);
		
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		BigDecimal Year_ = list.get(0).getYear_();
		jsonMap.put("Year_", Year_);
		
		for(int i =0;i<list.size();i++){
			xList.add(list.get(i).getMonth_()+"月");	
			y1List.add(list.get(i).getE());
			y2List.add(list.get(i).getPlanE());
		}
		
		jsonMap.put("x", xList);		
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		
		return jsonMap;
	}
	
	/**
	 * 电站年计划发电完成率
	 */
	@RequestMapping(value ="/stationPlanOnNetEY", method = RequestMethod.GET)
	public String stationPlanOnNetEY() throws Exception {
		return "oms/station/StaPlanOnNetEY";
	}
	
	@RequestMapping(value = "/displayPlanOnNetEY")
    @ResponseBody
	public Map displayStaPlanOnNetEY(@RequestParam("organ_id")int organ_id,@RequestParam("Years_")int Years_)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
				
		List<ViewStaYDto> list =viewStaYService.findByOrgIDandYear(organ_id, Years_);//
		
		jsonMap.put("list", list);
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		BigDecimal Year_ = list.get(0).getYear_();
		jsonMap.put("Year_", Year_);
		
		for(int i =0;i<list.size();i++){
			xList.add(list.get(i).getMonth_()+"月");	
			y1List.add(list.get(i).getOnNetE());
			y2List.add(list.get(i).getPlanOnE());
		}
		
		jsonMap.put("x", xList);		
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		
		System.out.println("*****************************");
		System.out.println(jsonMap);
		System.out.println("*****************************");
		
		return jsonMap;		
	}
	
	
	/**
	 * 电站年能耗执行分析
	 */
	@RequestMapping(value ="/stationEnCY", method = RequestMethod.GET)
	public String stationEnCY() throws Exception {
		return "oms/station/StaEnCY";
	}
	
	@RequestMapping(value = "/displayEnCY")
    @ResponseBody
    public Map displayStaEnCY(@RequestParam("organ_id")int organ_id,@RequestParam("Years_")int Years_)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
		List y3List=new LinkedList();
		
		List<ViewStaYDto> list =viewStaYService.findByOrgIDandYear(organ_id, Years_);//
		
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		BigDecimal Year_ = list.get(0).getYear_();
		jsonMap.put("Year_", Year_);
		
		jsonMap.put("list", list);
		for(int i =0;i<list.size();i++){
			xList.add(list.get(i).getMonth_()+"月");	
			y1List.add(list.get(i).getE());
			y2List.add(list.get(i).getFactoryE());
			y3List.add(list.get(i).getPurchaseNetE());
			
		}
		
		jsonMap.put("x", xList);		
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		jsonMap.put("y3", y3List);
		
		return jsonMap;		
	}
	
	/**
	 * 电站概览 获取月数据
	 *//*
	@RequestMapping(value ="/stationOverView", method = RequestMethod.GET)
	public String stationOverView() throws Exception {
		return "oms/station/StationOverView";
	}	
	@RequestMapping(value = "/displayStaOVYE")
    @ResponseBody
    public Map displayStaOVYE()throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List x1List=new LinkedList();
		List y1List1=new LinkedList();
		List y1List2=new LinkedList();
		List<ViewStaYDto> li=viewStaYService.findByOrgIDandYM(6, 2013, 4);
		for(int i=0;i<li.size();i++){
			x1List.add(li.get(i).getOrganName());
			y1List1.add(li.get(i).getE());
			y1List2.add(li.get(i).getOnNetE());
		}		
		jsonMap.put("x1", x1List);
		jsonMap.put("y11", y1List1);
		jsonMap.put("y12", y1List2);
		
		List x2List=new LinkedList();
		List y2List1=new LinkedList();
		List y2List2=new LinkedList();		
		List<ViewStaYDto> list =viewStaYService.findByOrgIDandYear(6, 2013);//		
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		BigDecimal Year_ = list.get(0).getYear_();
		jsonMap.put("Year_", Year_);		
		jsonMap.put("list", list);
		for(int i =0;i<list.size();i++){
			x2List.add(list.get(i).getMonth_()+"月");	
			y2List1.add(list.get(i).getE());
			y2List2.add(list.get(i).getOnNetE());
			
		}		
		jsonMap.put("x2", x2List);		
		jsonMap.put("y21", y2List1);
		jsonMap.put("y22", y2List2);	
		
		return jsonMap;		
	}*/
}
