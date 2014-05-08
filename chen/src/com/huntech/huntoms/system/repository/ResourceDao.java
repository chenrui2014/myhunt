package com.huntech.huntoms.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.huntech.huntoms.system.domain.Resource;

public interface ResourceDao extends JpaRepository<Resource, Long>, JpaSpecificationExecutor<Resource> {
	
	@Query("SELECT r FROM Resource r WHERE parent is null")
	public Resource getRoot(); 
	
	@Modifying 
	@Query("delete from Resource r where r.menuId in ?1") 
	void delete(List<Long> ids);
	
	//@Query("Select r From Resource r   Start With r.menuId = ?1 Connect By Prior r.menuId=r.parent.menuId")
	@Query("SELECT r FROM Resource r WHERE parent.menuId = ?1 order by r.menuId asc")
	public List<Resource> getChildren(Long id);
}
