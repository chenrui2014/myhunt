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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huntech.huntoms.oms.dto.ViewStaDDto;
import com.huntech.huntoms.oms.dto.ViewStaMDto;
import com.huntech.huntoms.oms.service.ViewStaDService;

@Controller
@RequestMapping("/station")
public class ViewStaDChartController {
	@Autowired
	private ViewStaDService viewStaDService;

	/**
	 * 电站日负荷曲线Daily load curve
	 */
	@RequestMapping(value = "/stationDPCurve", method = RequestMethod.GET)
	public String stationDPCurve() throws Exception {
		return "oms/station/StaDPCurve";
	}

	@RequestMapping(value = "/displaySDayPCurve")
	@ResponseBody
	public Map displaySDayPCurve(@RequestParam("organ_id")int organ_id,
								 @RequestParam("Years_")int Years_,
								 @RequestParam("Months_")int Months_,
								 @RequestParam("Days_")int Days_) throws Exception {
		Map jsonMap = new HashMap();
		
		List xList = new LinkedList();
		List y1List = new LinkedList();
		List y2List = new LinkedList();
		List y3List = new LinkedList();
		List y4List = new LinkedList();
		List y5List = new LinkedList();
		
		List<ViewStaDDto> list = viewStaDService.findByOrgIDYearMonthDay(organ_id, Years_, Months_, Days_);
		
		if (list.size() == 0)
			return null;

		jsonMap.put("list", list);
		String orgName = list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);

		BigDecimal Year_ = list.get(0).getYear_();
		BigDecimal Month_ = list.get(0).getMonth_();
		BigDecimal Day_ = list.get(0).getDay_();
		jsonMap.put("riqi", Year_+"年"+Month_+"月"+Day_+"日");
		
		for (int i = 0; i < list.size(); i++) {
			xList.add(list.get(i).getHour_()+"时");
			y1List.add(list.get(i).getGrdConnectP());
			y2List.add(list.get(i).getInvInput());
			y3List.add(list.get(i).getInvOutput());
			y4List.add(list.get(i).getTheoryP());
			y5List.add(list.get(i).getAvgIrradiance());
			
		}
			
		jsonMap.put("x",xList);
		jsonMap.put("y1", y1List);
		jsonMap.put("y2", y2List);
		jsonMap.put("y3", y3List);
		jsonMap.put("y4", y4List);
		jsonMap.put("y5", y5List);
		

		return jsonMap;
	}
}
