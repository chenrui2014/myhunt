package com.huntech.huntoms.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huntech.huntoms.common.uitl.orm.DynamicSpecifications;
import com.huntech.huntoms.common.uitl.orm.SearchFilter;
import com.huntech.huntoms.common.uitl.view.Pager;
import com.huntech.huntoms.system.domain.Resource;
import com.huntech.huntoms.system.dto.ResourceDto;
import com.huntech.huntoms.system.dto.tree.ZTree;
import com.huntech.huntoms.system.repository.ResourceDao;
import com.huntech.huntoms.system.service.ResourceService;

@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	protected ResourceDao resourceDao;

	public ResourceDto findById(Long id) {
		Resource r = resourceDao.findOne(id);
		return ResourceDto.resource2Dto(r);
	}

	public List<ZTree> findAll() {
		List<Resource> list = resourceDao.findAll();
		List<ZTree> nodes = new ArrayList<ZTree>();
		for(Resource r :list){
			if(r.getParent()!=null){
				nodes.add(ZTree.resource2Tree(r));
			}
		}
		return nodes;
	}

	public Page<Resource> findAll(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.ASC,
				"resourceId"));
		return resourceDao.findAll(pageable);
	}

	public Page<Resource> findAll(Specification<Resource> spec, int page,
			int size) {
		PageRequest pageRequest = buildPageRequest(page, size);
		return resourceDao.findAll(spec, pageRequest);
	}

	private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
		Sort sort = new Sort(Direction.ASC, "resourceId");

		return new PageRequest(pageNumber, pagzSize, sort);
	}



	public Resource getRoot() {
		return resourceDao.getRoot();
	}

	public Resource getParent(Long id) {
		return resourceDao.findOne(id).getParent();
	}

	public List<ZTree> getChildren(Long id) {
		List<Resource> list = resourceDao.findOne(id).getChildren();
		List<ZTree> nodes = new ArrayList<ZTree>();
		for(Resource r :list){
			if(r.getParent()!=null){
				nodes.add(ZTree.resource2Tree(r));
			}
		}
		return nodes;
	}
	@Transactional(readOnly = true)
	public List<ZTree> getTreeChildren(Long id) {
		List<Resource> children = resourceDao.getChildren(id);
		List<ZTree> nodes = new ArrayList<ZTree>();
		/*for(Resource r :children){
			if(r.getParent()!=null){
				nodes.add(ZTree.resource2Tree(r));
			}
		}*/
		for (Resource r : children) {
			readChild(r,nodes);
		}
		/*
		for(ZTree zt:nodes){
			System.out.println("zt name:" + zt.getName()+ "   id:"+ zt.getId()+"   id:"+ zt.getpId());
		}*/
		return nodes;
	}
	
	
	public void readChild(Resource resource, List<ZTree> nodes) {
		ZTree node = new ZTree();
		if (resource.getChildren().size()> 0) {// 有子节点继续便利
			node = ZTree.resource2Tree(resource);
			for (Resource r : resource.getChildren()) {
				if(resource!=null&&resource.getMenuName()!=null&&!"".endsWith(resource.getMenuName())){
					readChild(r, nodes);
				}
				
			}
		} else {
			if(resource!=null&&resource.getMenuName()!=null){
				node = ZTree.resource2Tree(resource);
			}
			
		}
		nodes.add(node);
	}
/*	public List<ExtTree> getTreeChildren(Long id) {
		List<Resource> children = resourceRepository.getChildren(id);
		List<ExtTree> nodes = new ArrayList<ExtTree>();

		for (Resource r : children) {
			ExtTree node = new ExtTree();
			node.setId(r.getResourceId());
			node.setText(r.getResourceName());
			node.setUrl(r.getUrl());
			node.setIconCls(r.getIcon());

			if (r.getChildren().size() > 0) {
				node.setLeaf(false);
			} else {
				node.setLeaf(true);
			}
			nodes.add(node);
		}

		return nodes;
	}
*/
	

/*	@Transactional(readOnly = true)
	public List<ExtCheckTree> getCheckTreeChildren(Long id,
			List<Long> resourceIds) {
		// 全部权限项
		List<Resource> children = resourceRepository.getChildren(id);
		List<ExtCheckTree> nodes = new ArrayList<ExtCheckTree>();
		boolean flag = false;
		for (Resource r : children) {
			ExtCheckTree node = new ExtCheckTree();
			node.setId(r.getResourceId());
			node.setText(r.getResourceName());
			node.setUrl(r.getUrl());
			node.setIconCls(r.getIcon());

			for (Long resourceId : resourceIds) {
				if (resourceId == r.getResourceId()) {
					flag = true;
					break;
				}
			}
			System.out.println("flag   :" + flag);
			node.setChecked(flag);

			if (r.getChildren().size() > 0) {
				node.setLeaf(false);
			} else {
				node.setLeaf(true);
			}
			nodes.add(node);
			flag = false;
		}

		return nodes;
	}*/

	@Transactional(readOnly = false)
	public Resource update(Resource resource) {
		return resourceDao.save(resource);
	}

	@Transactional(readOnly = false)
	public void deleteById(Long id) {
		resourceDao.delete(id);
	}

	@Transactional(readOnly = false)
	public Map<String,Object> delete(String ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		boolean flag = true;
		try {
			List<Long> idList = new ArrayList<Long>();
			if (ids != null) {
				for (String id : ids.split(",")) {

					if (!id.equals("")) {
						Long roleId = Long.valueOf(id.trim());
						Resource resource = resourceDao.findOne(roleId);
						if (resource.getChildren().size() > 0) {
							flag = false;
						} else {
							idList.add(roleId);
						}
					}
				}
			}
			if (flag) {
				resourceDao.delete(idList);
				map.put(""+flag, "删除成功");
			} else {
				map.put(flag+"", "选中数据有子记录，无法删除");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put(""+false, "出现异常");
		}
		return map;
	}

	@Override
	public Page<Resource> findAll(Map<String, Object> searchParams,
			Pager pager) {
		
		return null;
	}

	@Override
	public Resource insert(Resource resource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getResourceList(Map<String, Object> searchParams,
			Pager pager) {
		
		Specification<Resource> spec = buildSpecification(searchParams);
		Page<Resource> pages = resourceDao.findAll(spec, Pager.buildPageRequest(pager));
		Map<String, Object> map = new HashMap<String, Object>();
		List<ZTree> list = new ArrayList<ZTree>();
		for(Resource r : pages){
			list.add(ZTree.resource2Tree(r));
		}
		map.put("rows", list);
		map.put("total", pages.getTotalElements());
		return map;
	}
	private Specification<Resource> buildSpecification(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<Resource> spec = DynamicSpecifications.bySearchFilter(
				filters.values(), Resource.class);
		return spec;
	}
}
