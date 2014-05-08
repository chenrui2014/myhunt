package com.huntech.huntoms.oms.ctrl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.ws.rs.DefaultValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huntech.huntoms.oms.dto.ViewStaAYDto;
//import com.huntech.huntoms.oms.dto.ViewStaYDto;
import com.huntech.huntoms.oms.service.ViewStaAYService;



@Controller
@RequestMapping("/station")

public class ViewStaAYChartController {
	
	@Autowired
	private ViewStaAYService viewStaAYService;
	
	/**
	 * 电站历年查询条件organ_id (organ_name)
	 * 
	 **/
	@RequestMapping(value="/GetStaConditionDataAY")
	@ResponseBody
	public Map staAYEConditionData(){
		Map jsonMap=new HashMap();
		List<ViewStaAYDto> list=viewStaAYService.findAll();
		Set orgNameSet=new TreeSet();
		for(int i=0;i<list.size();i++){
			orgNameSet.add(list.get(i).getOrganName());
		}
		jsonMap.put("orgName", orgNameSet);
		return jsonMap;
	}
	
	/**
	 * 电站间查询条件organ_id (organ_name)
	 * 
	 **/
	@RequestMapping(value="/GetBetweenStaConditionData")
	@ResponseBody
	public Map BetweenStaConditionData(){
		Map jsonMap=new HashMap();
		List<ViewStaAYDto> list=viewStaAYService.findAll();
		Set Year_=new TreeSet();
		for(int i=0;i<list.size();i++){
			Year_.add(list.get(i).getYear_());
		}
		jsonMap.put("Year_", Year_);
		return jsonMap;
	}
	
	/**
	 * 电站历年发电量
	 */
	@RequestMapping(value ="/stationAYE", method = RequestMethod.GET)
	public String stationAYearE() throws Exception {
		return "oms/station/StaYearE";
	}
	
	@RequestMapping(value="/displaySYearE")
	@ResponseBody
	public Map displaySYearE(@RequestParam("organ_id") String organ_id)throws Exception{
		Map jsonMap=new HashMap();
		String str = new String(organ_id.getBytes("ISO-8859-1"), "UTF-8");
		List<ViewStaAYDto> list =viewStaAYService.findByOrgID(str);
		//List<ViewStaAYDto> list =viewStaAYService.findAll();
		System.out.println(list);
		jsonMap.put("list", list);
		
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		String[] yearArray = new String[list.size()];
		for(int i=0;i<list.size();i++){
			yearArray[i]= String.valueOf(list.get(i).getYear_())+"年";
		}
		jsonMap.put("year", yearArray);
		
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
	 *电站间年单兆瓦发电量对比分析
	 */
	@RequestMapping(value ="/stationSigleYear", method = RequestMethod.GET)
	public String stationYearSingleE() throws Exception {
		return "oms/station/StaSingleY";
	}
	
	@RequestMapping(value = "/displayVSYE")
    @ResponseBody
    public Map displayStaAYSE(@RequestParam("Year_") int Year_)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List yList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
		//List<ViewStaAYDto> list =viewStaAYService.findAll();
		List<ViewStaAYDto> list =viewStaAYService.findByYear(Year_);
		
		System.out.println(list);
		jsonMap.put("list", list);
		
		for(int i =0;i<list.size();i++){
			xList.add(list.get(i).getOrganName());
			yList.add(list.get(i).getSingleMWE());
			y1List.add(list.get(i).getCapacity());
			y2List.add(list.get(i).getE());
		}
		
		jsonMap.put("x", xList);
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		jsonMap.put("y", yList);
		
		return jsonMap;
	}
	
	/**
	 * 电站间年等效利用小时
	 */
	@RequestMapping(value ="/stationYearEuseHours", method = RequestMethod.GET)
	public String stationYearEuseHours() throws Exception {
		return "oms/station/StaYEuseHours";
	}
	
	@RequestMapping(value = "/displayVYH")//ViewYearHour
    @ResponseBody
	public Map displayStaYH(@RequestParam("Year_") int Year_)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List yList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
		
		
		List<ViewStaAYDto> list =viewStaAYService.findByYear(Year_);//
		
		jsonMap.put("list", list);
		for(int i =0;i<list.size();i++){
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
	 * 电站间年发电效率
	 */
	@RequestMapping(value ="/stationYearEfficiency", method = RequestMethod.GET)
	public String stationYEfficiency() throws Exception {
		return "oms/station/StaYEfficiency";
	}
	
	@RequestMapping(value = "/displayVYEff")
    @ResponseBody
	public Map displayStaYEff(@RequestParam("Year_") int Year_)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
		List yList=new LinkedList();
		
		List<ViewStaAYDto> list =viewStaAYService.findByYear(Year_);//
		
		jsonMap.put("list", list);
		for(int i =0;i<list.size();i++){
			xList.add(list.get(i).getOrganName());	

			y1List.add(list.get(i).getE());
			y2List.add(list.get(i).getTheoryE());
			
		}
		
		jsonMap.put("x", xList);		
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		
		return jsonMap;
	}
	
	/**
	 * 电站历年发电效率
	 */
	@RequestMapping(value ="/stationAYEffic", method = RequestMethod.GET)
	public String stationAYEffic() throws Exception {
		return "oms/station/StaAYEffic";
	}
	
	@RequestMapping(value = "/displayAYEffic")
    @ResponseBody
	public Map displayStaAYEffic(@RequestParam("organ_id") String organ_id)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
		List y3List=new LinkedList();
		
		String str = new String(organ_id.getBytes("ISO-8859-1"), "UTF-8");
		List<ViewStaAYDto> list =viewStaAYService.findByOrgID(str);
				
		jsonMap.put("list", list);
		
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		
		for(int i =0;i<list.size();i++){
			xList.add(list.get(i).getYear_()+"年");	
			y1List.add(list.get(i).getE());
			y2List.add(list.get(i).getTheoryE());
			y3List.add(list.get(i).getCapacity());
			
		}
		
		jsonMap.put("x", xList);		
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		jsonMap.put("y3", y3List);
		
		return jsonMap;
	}
	
	
	/**
	 * 电站历年计划发电完成率
	 */
	@RequestMapping(value ="/stationPlanEAY", method = RequestMethod.GET)
	public String stationPlanEAY() throws Exception {
		return "oms/station/StaPlanEAY";
	}
	
	@RequestMapping(value = "/displayPlanEAY")
    @ResponseBody
	public Map displayStaPlanEAY(@RequestParam("organ_id") String organ_id)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
		
		String str = new String(organ_id.getBytes("ISO-8859-1"), "UTF-8");
		List<ViewStaAYDto> list =viewStaAYService.findByOrgID(str);//
		
		jsonMap.put("list", list);
		
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		
		for(int i =0;i<list.size();i++){
			xList.add(list.get(i).getYear_()+"年");	
			y1List.add(list.get(i).getE());
			y2List.add(list.get(i).getPlanE());
		}
		
		jsonMap.put("x", xList);		
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		
		return jsonMap;
	}
	
	
	/**
	 * 电站历年上网计划发电完成率
	 */
	@RequestMapping(value ="/stationPlanOnNetEAY", method = RequestMethod.GET)
	public String stationPlanOnNetEAY() throws Exception {
		return "oms/station/StaPlanOnNetEAY";
	}
	
	@RequestMapping(value = "/displayPlanOnNetEAY")
    @ResponseBody
	public Map displayStaPlanOnNetEAY(@RequestParam("organ_id") String organ_id)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
		String str = new String(organ_id.getBytes("ISO-8859-1"), "UTF-8");	
		List<ViewStaAYDto> list =viewStaAYService.findByOrgID(str);//
		
		jsonMap.put("list", list);
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		
		for(int i =0;i<list.size();i++){
			xList.add(list.get(i).getYear_()+"年");	
			y1List.add(list.get(i).getOnNetE());
			y2List.add(list.get(i).getPlanOnE());
		}
		
		jsonMap.put("x", xList);		
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		
		
		return jsonMap;
	}
	
	
	/**
	 * 电站历年能耗执行分析
	 */
	@RequestMapping(value ="/stationEnCAY", method = RequestMethod.GET)
	public String stationEnCAY() throws Exception {
		return "oms/station/StaEnCAY";
	}
	
	@RequestMapping(value = "/displayEnCAY")
    @ResponseBody
	public Map displayStaEncAY(@RequestParam("organ_id") String organ_id)throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List xList=new LinkedList();
		List y1List=new LinkedList();
		List y2List=new LinkedList();
		List y3List=new LinkedList();
		
		String str = new String(organ_id.getBytes("ISO-8859-1"), "UTF-8");
		List<ViewStaAYDto> list =viewStaAYService.findByOrgID(str);//
		
		jsonMap.put("list", list);
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		
		for(int i =0;i<list.size();i++){
			xList.add(list.get(i).getYear_()+"年");	
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
	
	
	
}
