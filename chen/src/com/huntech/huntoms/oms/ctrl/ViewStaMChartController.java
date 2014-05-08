package com.huntech.huntoms.oms.ctrl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huntech.huntoms.oms.dto.ViewStaMDto;
import com.huntech.huntoms.oms.dto.ViewStaYDto;
import com.huntech.huntoms.oms.service.ViewStaMService;

@Controller
@RequestMapping("/station")
public class ViewStaMChartController {

	@Autowired
	private ViewStaMService viewStaMService;

	/**
	 * 电站月查询条件organ_id (organ_name) Year_ Month_
	 */
	@RequestMapping(value="/GetStaConditionDataM")
	@ResponseBody
	public Map staMEConditionData(){
		Map jsonMap=new HashMap();
		List<ViewStaMDto> list=viewStaMService.findAll();
		Set orgIdSet=new TreeSet();
		Set orgNameSet=new TreeSet();
		Set yearSet=new TreeSet();
		Set monthSet=new TreeSet();
		for(int i=0;i<list.size();i++){
			orgIdSet.add(list.get(i).getOrganID());
			orgNameSet.add(list.get(i).getOrganName());
			if(list.get(i).getYear_()!=null){
				yearSet.add(list.get(i).getYear_());
			}
			if(list.get(i).getMonth_()!=null){
				monthSet.add(list.get(i).getMonth_());
			}
			//monthSet.add(list.get(i).getMonth_());
		}
		jsonMap.put("orgId", orgIdSet);
		jsonMap.put("orgName", orgNameSet);
		jsonMap.put("Year_", yearSet);
		jsonMap.put("Month_", monthSet);
		return jsonMap;
	}
	/**
	 * 电站月发电量
	 */
	@RequestMapping(value = "/stationME", method = RequestMethod.GET)
	public String stationAYearE() throws Exception {
		return "oms/station/StaDayE";
	}

	@RequestMapping(value = "/displaySDayE")
	@ResponseBody
	public Map displaySMonthE(@RequestParam("organ_id")int organ_id,
							  @RequestParam("Year_")int Year_,
				              @RequestParam("Month_")int Month_) throws Exception {
		Map jsonMap = new HashMap();
		List<ViewStaMDto> list = viewStaMService.findByOrgIDYearMonth(organ_id, Year_,Month_);
		if (list.size() == 0)
			return null;
		jsonMap.put("list", list);
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);

		String[] day = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			day[i] = list.get(i).getDay_().toString() + "日";
		}
		jsonMap.put("day", day);

		List listsun = new LinkedList();
		// 年实际发电量
		Map mape = new HashMap();
		mape.put("name", "实际发电量");
		String[] eArray = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			eArray[i] = String.valueOf(list.get(i).getE());
		}
		mape.put("data", eArray);
		mape.put("name", "实际发电量");
		listsun.add(mape);

		// onnete上网电量
		Map maponnete = new HashMap();
		String[] onneteArray = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			onneteArray[i] = String.valueOf(list.get(i).getNetE());
		}
		maponnete.put("data", onneteArray);
		maponnete.put("name", "上网电量");
		listsun.add(maponnete);

		// 弃光电量
		Map maplosse = new HashMap();
		String[] maplosseArray = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			maplosseArray[i] = String.valueOf(list.get(i).getLossE());
		}
		maplosse.put("data", maplosseArray);
		maplosse.put("name", "弃光电量");
		listsun.add(maplosse);

		jsonMap.put("datasum", listsun);
		
		return jsonMap;
	}

	/**
	 * 日间单兆瓦发电量
	 **/
	@RequestMapping(value = "/stationSigleDay", method = RequestMethod.GET)
	public String stationDaySingleE() throws Exception {
		return "oms/station/StaSingleD";
	}

	@RequestMapping(value = "/displayVSDE")
	@ResponseBody
	public Map displayStaMSE(@RequestParam("Year_")int Year_,
						     @RequestParam("Month_")int Month_,
						     @RequestParam("Day_")int Day_) throws Exception {
		Map jsonMap = new HashMap();

		List xList = new LinkedList();
		List y1List = new LinkedList();
		List y2List = new LinkedList();
		List yList = new LinkedList();

		List<ViewStaMDto> list = viewStaMService.findByYearMonthDay(Year_, Month_, Day_);//
		
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
	 * 日间等效利用小时
	 **/
	@RequestMapping(value = "/stationDayEuseHours", method = RequestMethod.GET)
	public String stationDayEuseHours() throws Exception {
		return "oms/station/StaDEuseHours";
	}

	@RequestMapping(value = "/displayVDH")
	@ResponseBody
	public Map displayStaMH(@RequestParam("Year_")int Year_,
		     @RequestParam("Month_")int Month_,
		     @RequestParam("Day_")int Day_) throws Exception {
		Map jsonMap = new HashMap();

		List xList = new LinkedList();
		List y1List = new LinkedList();
		List y2List = new LinkedList();
		List yList = new LinkedList();

		// List<ViewStaMDto> list = viewStaMService.findAll();
		List<ViewStaMDto> list = viewStaMService.findByYearMonthDay(Year_, Month_, Day_);
		
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

}
