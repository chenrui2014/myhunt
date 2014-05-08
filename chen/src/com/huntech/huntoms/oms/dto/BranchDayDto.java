package com.huntech.huntoms.oms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BranchDayDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double e;	//实际发电量

	private Double onnete; //上网电量
	
	private String organName; //组织名称
	
	private Integer day; // 天
	
	private Integer year;
	
	private Integer month;
	
	private Double singlemwe; //单兆瓦发电量 

	private Double eusehours; //等效利用小时
	
	private Double   losse ; //弃光电量
	
	public Double getE() {
		return e;
	}

	public void setE(Double e) {
		this.e = e;
	}

	public Double getOnnete() {
		return onnete;
	}

	public void setOnnete(Double onnete) {
		this.onnete = onnete;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	
	public Double getSinglemwe() {
		return singlemwe;
	}

	public void setSinglemwe(Double singlemwe) {
		this.singlemwe = singlemwe;
	}

	
	public Double getEusehours() {
		return eusehours;
	}

	public void setEusehours(Double eusehours) {
		this.eusehours = eusehours;
	}

	public Double getLosse() {
		return losse;
	}

	public void setLosse(Double losse) {
		this.losse = losse;
	}

	public static List<BranchDayDto> transferBranchDayDto(List<Map<String,Object>> list){
		
			List<BranchDayDto> listB = new ArrayList<BranchDayDto>();
			if(null!=list&&list.size()>0){
				BranchDayDto dto =null;
				List<Map<String,Object>> listE = null;
				Map<String,Object> map = null;
				for(int i =0;i<list.size();i++){
					dto = new BranchDayDto();
					map = (Map<String,Object>)list.get(i);
					BigDecimal e = (BigDecimal)map.get("e");
					if(e!=null){
						dto.setE(e.doubleValue());
					}else{
						dto.setE(0.00);
					}
					BigDecimal onnete = (BigDecimal)map.get("onnete");
					if(null!=onnete){
						dto.setOnnete(onnete.doubleValue());
					}else{
						dto.setOnnete(0.00);
					}
					BigDecimal year = (BigDecimal)map.get("year");
					if(null!=year){
						dto.setYear(year.intValue());
					}else{
						dto.setYear(0);
					}
					BigDecimal month = (BigDecimal)map.get("month");
					if(month!=null){
						dto.setMonth(month.intValue());
					}else{
						dto.setMonth(0);
					}
					BigDecimal day = (BigDecimal)map.get("day");
					if(day!=null){
						dto.setDay(day.intValue());	
					}else{
						dto.setDay(0);
					}
					dto.setOrganName((String)map.get("organName"));
					BigDecimal singlemwe = (BigDecimal)map.get("singlemwe");
					if(null!=singlemwe){
						dto.setSinglemwe(singlemwe.doubleValue());
					}else{
						dto.setSinglemwe(0.00);
					}
					BigDecimal eusehours = (BigDecimal)map.get("eusehours");
					if(null!=eusehours){
						dto.setEusehours(eusehours.doubleValue());
					}else{
						dto.setEusehours(0.00);
					}
					BigDecimal losse = (BigDecimal)map.get("losse");
					if(null!=losse){
						dto.setLosse(losse.doubleValue());
					}else{
						dto.setLosse(0.00);
					}
					
					listB.add(dto);
				}
			}
		return listB;
	}	
}
