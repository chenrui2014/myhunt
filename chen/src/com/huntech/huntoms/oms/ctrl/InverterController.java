package com.huntech.huntoms.oms.ctrl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huntech.huntoms.oms.dto.InverterBean;
import com.huntech.huntoms.oms.dto.ViewInvDDto;
import com.huntech.huntoms.oms.dto.ViewInvModelDto;
import com.huntech.huntoms.oms.service.InverterService;
import com.huntech.huntoms.oms.service.ViewInvDService;

@Controller
@RequestMapping("/device")
public class InverterController {
	//@Autowired
	//private ViewInvDService viewInvDService;
	
	/*
	 * 逆变器日发电量对比Daily load curve
	 */
	/*@RequestMapping(value = "/inverterDE", method = RequestMethod.GET)
	public String inverterDayE() throws Exception {
		return "oms/station/InverterDE";
	}

	@RequestMapping(value = "/displayInverterDE")
	@ResponseBody
	
	public Map displayInverterDE() throws Exception {
		Map jsonMap = new HashMap();
		
		////第一个
		List<ViewInvDDto> list1 = viewInvDService.findByOrgIDYMDInv(6, 2014, 3, 31, 1);
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
		List<ViewInvDDto> list2 = viewInvDService.findByOrgIDYMDInv(6, 2014, 3, 31, 2);		
		String invName2=list2.get(0).getDeviceName();
		List yList2=new LinkedList();		
		for(int i=0;i<list2.size();i++){
			yList2.add(list2.get(i).getE());
		}		
		jsonMap.put("invName2", invName2);System.out.println(invName2);			
		jsonMap.put("y2", yList2);System.out.println(yList2);
		//第三个
		List<ViewInvDDto> list3 = viewInvDService.findByOrgIDYMDInv(6, 2014, 3, 31, 3);		
		String invName3=list3.get(0).getDeviceName();
		List yList3=new LinkedList();		
		for(int i=0;i<list3.size();i++){
			yList3.add(list3.get(i).getE());
		}		
		jsonMap.put("invName3", invName3);System.out.println(invName3);			
		jsonMap.put("y3", yList3);System.out.println(yList3);
		//第四个
		List<ViewInvDDto> list4 = viewInvDService.findByOrgIDYMDInv(6, 2014, 3, 31, 4);		
		String invName4=list4.get(0).getDeviceName();
		List yList4=new LinkedList();		
		for(int i=0;i<list4.size();i++){
			yList4.add(list4.get(i).getE());
		}		
		jsonMap.put("invName4", invName4);System.out.println(invName4);			
		jsonMap.put("y4", yList4);System.out.println(yList4);
		//第五个
		List<ViewInvDDto> list5 = viewInvDService.findByOrgIDYMDInv(6, 2014, 3, 31, 5);		
		String invName5=list5.get(0).getDeviceName();
		List yList5=new LinkedList();		
		for(int i=0;i<list5.size();i++){
			yList5.add(list5.get(i).getE());
		}		
		jsonMap.put("invName5", invName5);System.out.println(invName5);			
		jsonMap.put("y5", yList5);System.out.println(yList5);
		
		return jsonMap;
	}*/
	@Autowired
	private InverterService inverterService;
	
	/*
	 * 同型号逆变器日发电量对比Daily load curve
	 */
	@RequestMapping(value = "/inverterModelE", method = RequestMethod.GET)
	public String inverterDayE() throws Exception {
		return "oms/device/InverterModelE";
	}
	@RequestMapping(value = "/displayInverterMondelE")
	@ResponseBody	
	public Map displayInverterDE() throws Exception{
		Map jsonMap = new HashMap();
		List<ViewInvModelDto> list = inverterService.findByOrgIDYearMonth(6, 2014, 3);
		String orgName=list.get(0).getOrganName();
		jsonMap.put("orgName", orgName);
		Set xList=new TreeSet();
		Set modelName=new TreeSet();
		
		for(int i=0;i<list.size();i++){
			xList.add(list.get(i).getDay_());
			modelName.add(list.get(i).getDeviceModel());	
		}
		
		jsonMap.put("x", xList);
		jsonMap.put("rowName", modelName);
		
		Iterator<Object> it = modelName.iterator();
		List list_ = new LinkedList();
		List dataSum = null;
		
		while(it.hasNext()){
			dataSum=new LinkedList();
			//da=new InverterBean();
			String str=(String) it.next();		
			Map map_ = new HashMap();
			map_.put("name", str);
			for(int i=0;i<list.size();i++){		
				if(list.get(i).getDeviceModel().equalsIgnoreCase(str)){
					dataSum.add(list.get(i).getEE());
					
				}/*else{
					break;
				}*/
			}	
			
			map_.put("data", dataSum);
			list_.add(map_);
		}
		
		
		System.out.println(list_);
		jsonMap.put("y", list_);
		//double[][] data=new double[xList.size()][modelName.size()];
		/*for(int i=0;i<list.size();i++){
			
		}*/
		return jsonMap;
	}
}
