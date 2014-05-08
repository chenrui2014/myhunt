package com.huntech.huntoms.oms.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//import com.huntech.huntoms.oms.domain.ViewStaAY;

import com.huntech.huntoms.oms.dto.ViewStaAYDto;
import com.huntech.huntoms.oms.repository.ViewStaAYJdbc;
import com.huntech.huntoms.oms.service.ViewStaAYService;

@Service
public class ViewStaAYServiceImpl implements ViewStaAYService{

	@Autowired
	private ViewStaAYJdbc viewStaAYJdbc;
	
	@Override
	public List<ViewStaAYDto> findAll() {
		/*List<Organization> domainList = organDao.findAll();
		Iterator<Organization> ite = domainList.iterator();
		List<OrganDto> dtoList = new ArrayList<OrganDto>();
		while (ite.hasNext()) {
			dtoList.add(OrganDto.domainToDto(ite.next()));  //List<Map<String, Object>> list
		}
		return dtoList;*/
		//ViewStaAYDto dto=new ViewStaAYDto();
		
		/*List<Map<String,Object>> list= viewStaAYJdbc.viewStaAYJdbc();//有错
		Iterator<Map<String, Object>> it=list.iterator();
		
		List<ViewStaAYDto> dtoList=new ArrayList<ViewStaAYDto>();	
		while(it.hasNext()){
			
			dtoList.add((ViewStaAYDto) ViewStaAYDto.DaoToDto((List<Map<String, Object>>) it.next()));
		}		
		return dtoList;*/
		
		//List<Map<String,Object>>  list =  viewStaAYJdbc.viewStaAYJdbc();
		List<Map<String,Object>>  list =  viewStaAYJdbc.selectStationAllYearE();
		List<ViewStaAYDto>  dtoList = ViewStaAYDto.DaoToDto(list);
		return dtoList;
	}
	
	
	public List<ViewStaAYDto> findByYear(int Year_){
		List<Map<String,Object>>  list =  viewStaAYJdbc.selectStationYearEByYear(Year_);
		List<ViewStaAYDto>  dtoList = ViewStaAYDto.DaoToDto(list);
		return dtoList;
	}
	
	public List<ViewStaAYDto> findByOrgID(String orgName){
		List<Map<String,Object>>  list =  viewStaAYJdbc.selectStationYearEByID(orgName);
		List<ViewStaAYDto>  dtoList = ViewStaAYDto.DaoToDto(list);
		return dtoList;
		
	}
	public List<ViewStaAYDto> findByOrgIDAndYear_(int orgID,int Year_){
		List<Map<String,Object>>  list =  viewStaAYJdbc.selectStationYearEByIDandYear_(orgID, Year_);
		List<ViewStaAYDto>  dtoList = ViewStaAYDto.DaoToDto(list);
		return dtoList;
	}
}
