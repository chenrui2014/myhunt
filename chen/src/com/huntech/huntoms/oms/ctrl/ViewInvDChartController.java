package com.huntech.huntoms.oms.ctrl;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.huntech.huntoms.oms.dto.ViewInvDDto;
import com.huntech.huntoms.oms.dto.ViewStaDDto;
import com.huntech.huntoms.oms.service.ViewInvDService;

@Controller
@RequestMapping("/device")
public class ViewInvDChartController {
	@Autowired
	private ViewInvDService viewInvDService;

	/**
	 * 逆变器日发电量对比Daily load curve
	 */
	@RequestMapping(value = "/inverterDayE", method = RequestMethod.GET)
	public String inverterDayE() throws Exception {
		return "oms/device/InverterDayE";
	}

	@RequestMapping(value = "/displayInverterDayE")
	@ResponseBody
	public Map displayInverterDayE(@RequestParam("organ_id")int organ_id,
								   @RequestParam("Years_")int Years_,
								   @RequestParam("Months_")int Months_,
								   @RequestParam("Days_") int Days_,
								   @RequestParam("invNum")int invNum) throws Exception {
		Map jsonMap = new HashMap();
		
		////第一个
		List<ViewInvDDto> list1 = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum);
		String orgName=list1.get(0).getOrganName();
		String invName1=list1.get(0).getDeviceName();
		List xList=new LinkedList();
		List yList1=new LinkedList();		
		for(int i=0;i<list1.size();i++){
			xList.add(list1.get(i).getHour_());
			yList1.add(list1.get(i).getE());
		}		
		jsonMap.put("orgName", orgName);System.out.println(orgName);	
		jsonMap.put("invName1", invName1);System.out.println(invName1);
		jsonMap.put("x", xList);System.out.println(xList);		
		jsonMap.put("y1", yList1);System.out.println(yList1);
		
		//第二个
		List<ViewInvDDto> list2 = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum+1);		
		String invName2=list2.get(0).getDeviceName();
		List yList2=new LinkedList();		
		for(int i=0;i<list2.size();i++){
			yList2.add(list2.get(i).getE());
		}		
		jsonMap.put("invName2", invName2);System.out.println(invName2);			
		jsonMap.put("y2", yList2);System.out.println(yList2);
		//第三个
		List<ViewInvDDto> list3 = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum+2);		
		String invName3=list3.get(0).getDeviceName();
		List yList3=new LinkedList();		
		for(int i=0;i<list3.size();i++){
			yList3.add(list3.get(i).getE());
		}		
		jsonMap.put("invName3", invName3);System.out.println(invName3);			
		jsonMap.put("y3", yList3);System.out.println(yList3);
		//第四个
		List<ViewInvDDto> list4 = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum+3);		
		String invName4=list4.get(0).getDeviceName();
		List yList4=new LinkedList();		
		for(int i=0;i<list4.size();i++){
			yList4.add(list4.get(i).getE());
		}		
		jsonMap.put("invName4", invName4);System.out.println(invName4);			
		jsonMap.put("y4", yList4);System.out.println(yList4);
		//第五个
		List<ViewInvDDto> list5 = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum+4);		
		String invName5=list5.get(0).getDeviceName();
		List yList5=new LinkedList();		
		for(int i=0;i<list5.size();i++){
			yList5.add(list5.get(i).getE());
		}		
		jsonMap.put("invName5", invName5);System.out.println(invName5);			
		jsonMap.put("y5", yList5);System.out.println(yList5);
		
		return jsonMap;
	}
	/**
	 * 逆变器辐照度Daily load curve
	 */
	@RequestMapping(value = "/inverterDayIrr", method = RequestMethod.GET)
	public String inverterDayIrr() throws Exception {
		return "oms/device/InverterDayIrr";
	}

	@RequestMapping(value = "/displayInverterDayIrr")
	@ResponseBody
	public Map displayInverterDayIrr(@RequestParam("organ_id")int organ_id,
			   @RequestParam("Years_")int Years_,
			   @RequestParam("Months_")int Months_,
			   @RequestParam("Days_") int Days_,
			   @RequestParam("invNum")int invNum) throws Exception {
		Map jsonMap = new HashMap();
		
		List hourList = new ArrayList();
		List pList=new LinkedList();
		List iList=new LinkedList();
		
		int min_ = 1;
		int max_ = 5;
		/*List<ViewInvDDto> list = viewInvDService.findByOrgIDYMDAsc(6, 2014, 3,
				31, min_, max_);*/
		List<ViewInvDDto> list = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum);
		
		jsonMap.put("list", list);
		if(list!=null&&list.size()>0){
			ViewInvDDto dto=list.get(0);
			jsonMap.put("orgName", dto.getOrganName());
			jsonMap.put("invName", dto.getDeviceName());
			jsonMap.put("year_", dto.getYear_()+"年");
			jsonMap.put("month_", dto.getMonth_()+"月");
			jsonMap.put("day_", dto.getDay_()+"日");
			
			for(ViewInvDDto hour_:list){
				hourList.add(hour_.getHour_());
			}
			jsonMap.put("hour_", hourList);
			
			for(ViewInvDDto hour_:list){
				pList.add(hour_.getAvg_P());	
				
			}
			jsonMap.put("p", pList);			
			for(ViewInvDDto hour_:list){
				iList.add(hour_.getAvgIrradiance());	
				
			}
			jsonMap.put("irr", iList);
		}
		return jsonMap;
	}
	/**
	 * 逆变器温度Daily load curve
	 */
	@RequestMapping(value = "/inverterDayTemp", method = RequestMethod.GET)
	public String inverterDayTemp() throws Exception {
		return "oms/device/InverterDayTemp";
	}

	@RequestMapping(value = "/displayInverterDayTemp")
	@ResponseBody
	public Map displayInverterDayTemp(@RequestParam("organ_id")int organ_id,
			   @RequestParam("Years_")int Years_,
			   @RequestParam("Months_")int Months_,
			   @RequestParam("Days_") int Days_,
			   @RequestParam("invNum")int invNum) throws Exception {
		Map jsonMap = new HashMap();
		
		List hourList = new ArrayList();
		List pList=new LinkedList();
		List tList=new LinkedList();
		
		int min_ = 1;
		int max_ = 5;
		/*List<ViewInvDDto> list = viewInvDService.findByOrgIDYMDAsc(6, 2014, 3,
				31, min_, max_);*/
		List<ViewInvDDto> list = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum);
		jsonMap.put("list", list);		
		if(list!=null&&list.size()>0){
			ViewInvDDto dto=list.get(0);
			jsonMap.put("orgName", dto.getOrganName());
			jsonMap.put("invName", dto.getDeviceName());
			jsonMap.put("year_", dto.getYear_()+"年");
			jsonMap.put("month_", dto.getMonth_()+"月");
			jsonMap.put("day_", dto.getDay_()+"日");
			
			for(ViewInvDDto hour_:list){
				hourList.add(hour_.getHour_());
			}
			jsonMap.put("hour_", hourList);
			
			for(ViewInvDDto hour_:list){
				pList.add(hour_.getAvg_P());	
				
			}
			jsonMap.put("p", pList);			
			for(ViewInvDDto hour_:list){
				tList.add(hour_.getTemp());	
				
			}
			jsonMap.put("t", tList);
		}
		return jsonMap;
	}
	/**
	 * 逆变器转换效率Daily load curve
	 */
	@RequestMapping(value = "/inverterConvertEff", method = RequestMethod.GET)
	public String inverterConvertEff() throws Exception {
		return "oms/device/InverterConvertEff";
	}

	@RequestMapping(value = "/displayInverterConvertEff")
	@ResponseBody
	public Map displayInverterConvertEff(@RequestParam("organ_id")int organ_id,
			   @RequestParam("Years_")int Years_,
			   @RequestParam("Months_")int Months_,
			   @RequestParam("Days_") int Days_,
			   @RequestParam("invNum")int invNum) throws Exception {
		Map jsonMap = new HashMap();
		
		////第一个
		List<ViewInvDDto> list1 = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum);
		String orgName=list1.get(0).getOrganName();
		String invName1=list1.get(0).getDeviceName();
		List xList=new LinkedList();
		List yList1=new LinkedList();		
		for(int i=0;i<list1.size();i++){
			xList.add(list1.get(i).getHour_());
			yList1.add(list1.get(i).getAvgTransferEffect());
		}		
		jsonMap.put("orgName", orgName);System.out.println(orgName);	
		jsonMap.put("invName1", invName1);System.out.println(invName1);
		jsonMap.put("x", xList);System.out.println(xList);		
		jsonMap.put("y1", yList1);System.out.println(yList1);
		
		//第二个
		List<ViewInvDDto> list2 = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum+1);		
		String invName2=list2.get(0).getDeviceName();
		List yList2=new LinkedList();		
		for(int i=0;i<list2.size();i++){
			yList2.add(list2.get(i).getAvgTransferEffect());
		}		
		jsonMap.put("invName2", invName2);System.out.println(invName2);			
		jsonMap.put("y2", yList2);System.out.println(yList2);
		//第三个
		List<ViewInvDDto> list3 = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum+2);		
		String invName3=list3.get(0).getDeviceName();
		List yList3=new LinkedList();		
		for(int i=0;i<list3.size();i++){
			yList3.add(list3.get(i).getAvgTransferEffect());
		}		
		jsonMap.put("invName3", invName3);System.out.println(invName3);			
		jsonMap.put("y3", yList3);System.out.println(yList3);
		//第四个
		List<ViewInvDDto> list4 = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum+3);		
		String invName4=list4.get(0).getDeviceName();
		List yList4=new LinkedList();		
		for(int i=0;i<list4.size();i++){
			yList4.add(list4.get(i).getAvgTransferEffect());
		}		
		jsonMap.put("invName4", invName4);System.out.println(invName4);			
		jsonMap.put("y4", yList4);System.out.println(yList4);
		//第五个
		List<ViewInvDDto> list5 = viewInvDService.findByOrgIDYMDInv(organ_id, Years_, Months_, Days_, invNum+4);		
		String invName5=list5.get(0).getDeviceName();
		List yList5=new LinkedList();		
		for(int i=0;i<list5.size();i++){
			yList5.add(list5.get(i).getAvgTransferEffect());
		}		
		jsonMap.put("invName5", invName5);System.out.println(invName5);			
		jsonMap.put("y5", yList5);System.out.println(yList5);
		
		return jsonMap;
	}
}
