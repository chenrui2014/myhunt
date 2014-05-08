package com.huntech.huntoms.system.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.huntech.huntoms.system.domain.User;

public interface UserDao extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
	
	User findByLoginNameAndPassword(String loginName , String password);
    
	User findByLoginName(String loginName);
	
	User findByUserCode(String userCode);
	//List<User> findAll();
}
