package com.huntech.huntoms.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.huntech.huntoms.common.uitl.view.Pager;
import com.huntech.huntoms.system.domain.Resource;
import com.huntech.huntoms.system.dto.ResourceDto;
import com.huntech.huntoms.system.dto.tree.ZTree;



public interface ResourceService {
	/**
	 * 
	 * @param id
	 * @return 通过ID 获取对象
	 */
	public ResourceDto findById(Long id);

	/**
	 * 
	 * @return 获取所有的菜单对象
	 */
	public List<ZTree> findAll();
	
	/**
	 * 获取菜单列表用于列表展示
	 * @param searchParams
	 * @param pager
	 * @return
	 */
	public Map<String, Object> getResourceList(Map<String, Object> searchParams,Pager pager) ;
	/**
	 * 
	 * @param searchParams
	 * @param pager
	 * @return 根据参数 获取对象并处理分页
	 */
	public Page<Resource> findAll(Map<String, Object> searchParams,
			Pager pager);

	/**
	 * 
	 * @param spec
	 * @param page
	 * @param size
	 * @return 根据参数 获取对象并处理分页
	 */
	public Page<Resource> findAll(Specification<Resource> spec, int page,
			int size);

	/**
	 * 
	 * @return 获取资源菜单的根节点
	 */
	public Resource getRoot();

	/**
	 * 
	 * @param id
	 * @return 获取此id对象的父节点对象
	 */
	public Resource getParent(Long id);

	/**
	 * 
	 * @param id
	 * @return 获取此id对象的子节点元素的列表
	 */
	public List<ZTree> getChildren(Long id);

	/**
	 * 
	 * @param resource
	 * @return 保存
	 */
	public Resource insert(Resource resource);

	/**
	 * 
	 * @param resource
	 * @return 更新
	 */
	public Resource update(Resource resource);

	/**
	 * 
	 * @param id
	 *            删除
	 */
	public void deleteById(Long id);

	public List<ZTree> getTreeChildren(Long id) ;
}
