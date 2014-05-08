package com.huntech.huntoms.oms.ctrl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huntech.huntoms.oms.dto.ViewStaAYDto;
import com.huntech.huntoms.oms.dto.ViewStaMDto;
import com.huntech.huntoms.oms.dto.ViewStaYDto;
import com.huntech.huntoms.oms.service.ViewStaAYService;
import com.huntech.huntoms.oms.service.ViewStaMService;
import com.huntech.huntoms.oms.service.ViewStaYService;

@Controller
@RequestMapping("/station")
public class StationController {
	@Autowired
	private ViewStaAYService viewStaAYService;
	@Autowired
	private ViewStaYService viewStaYService;
	@Autowired
	private ViewStaMService viewStaMService;
	
	/**
	 * 电站概览
	 */
	@RequestMapping(value ="/stationOverView", method = RequestMethod.GET)
	public String stationOverView() throws Exception {
		return "oms/station/StationOverView";
	}	
	
	/**
	 * 获取电站概览电站概览年数据
	 */
	@RequestMapping(value="/displayStaOVAYE")
	@ResponseBody
	public Map displayStaOVAYE()throws Exception{
		Map jsonMap=new HashMap();
		List thisYE=new LinkedList();
		List thisYOnE=new LinkedList();
		List lastYE=new LinkedList();
		List lastYOnE=new LinkedList();
		
		//今年年电量
		List<ViewStaAYDto> list=viewStaAYService.findByOrgIDAndYear_(6, 2014);
		for(int i=0;i<list.size();i++){
			thisYE.add(list.get(i).getE());
			thisYOnE.add(list.get(i).getOnNetE());
		}	
		//去年年电量
		List<ViewStaAYDto> llist=viewStaAYService.findByOrgIDAndYear_(6, 2013);
		for(int i=0;i<llist.size();i++){
			lastYE.add(llist.get(i).getE());
			lastYOnE.add(llist.get(i).getOnNetE());
		}
		
		jsonMap.put("thisYE", thisYE);
		jsonMap.put("thisYOnE", thisYOnE);
		jsonMap.put("lastYE", lastYE);
		jsonMap.put("lastYOnE", lastYOnE);
		
		return jsonMap;
	}
	
	/**
	 * 获取电站概览电站概览月数据
	 */
	@RequestMapping(value = "/displayStaOVYE")
    @ResponseBody
    public Map displayStaOVYE()throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List x1List=new LinkedList();
		List y1List1=new LinkedList();
		List y1List2=new LinkedList();
		
		List theoryEList=new LinkedList();
		List planEList=new LinkedList();
		List planOnEList=new LinkedList();
		
		//当月数据
		List<ViewStaYDto> li=viewStaYService.findByOrgIDandYM(6, 2013, 4);
		
		BigDecimal install=li.get(0).getCapacity();
		BigDecimal planInstall=li.get(0).getPlanInstall();
		
		jsonMap.put("install", install);
		jsonMap.put("planInstall", planInstall);
		
		for(int i=0;i<li.size();i++){
			
			x1List.add(li.get(i).getOrganName());
			y1List1.add(li.get(i).getE());
			y1List2.add(li.get(i).getOnNetE());
			theoryEList.add(li.get(i).getTheoryE());
			planEList.add(li.get(i).getPlanE());
			planOnEList.add(li.get(i).getPlanOnE());
		}		
		jsonMap.put("x1", x1List);
		jsonMap.put("y11", y1List1);
		jsonMap.put("y12", y1List2);
		
		jsonMap.put("theoryE", theoryEList);
		jsonMap.put("planE", planEList);
		jsonMap.put("planOnE", planOnEList);
		//一年的数据
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
	}
	
	/**
	 * 获取电站概览电站概览日数据
	 */
	@RequestMapping(value = "/displayStaOVME")
    @ResponseBody
    public Map displayStaOVME()throws Exception{
		Map  jsonMap  =  new HashMap();
		
		List staP=new LinkedList();//当日平均输出功率
		//List<ViewStaMDto> list=viewStaMService.findByOrgIDYMD(6, 2014, 3, 31);
		
		List yesDEList=new LinkedList();
		List yesOnEList=new LinkedList();
		
		List<ViewStaMDto> list=viewStaMService.findByOrgIDYMD(6, 2014, 3, 31);
		
		for(int i=0;i<list.size();i++){
			yesDEList.add(list.get(i).getE());
			yesOnEList.add(list.get(i).getNetE());	
			staP.add(list.get(i).getAvg_p());
		}
		jsonMap.put("staP", staP);
		
		jsonMap.put("yesDE", yesDEList);
		jsonMap.put("yesOnE", yesOnEList);
		return jsonMap;
	}
}
