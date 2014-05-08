package com.huntech.huntoms.system.dto;

import java.io.Serializable;

import com.huntech.huntoms.system.domain.Resource;

public class ResourceDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6222977359327330839L;

	private Long id;

	private String name;

	private String icon;

	private String resparam;
	
	private String menuUrl;

	private long pId;

	private short open;

	private String parentName;
	
	private String fdesc;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}



	public String getResparam() {
		return resparam;
	}

	public void setResparam(String resparam) {
		this.resparam = resparam;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getFdesc() {
		return fdesc;
	}

	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}

	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

	public short getOpen() {
		return open;
	}

	public void setOpen(short open) {
		this.open = open;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public static ResourceDto resource2Dto(Resource resource) {
		ResourceDto node = new ResourceDto();

		node.setId(resource.getMenuId());
		node.setName(resource.getMenuName());
		node.setIcon(resource.getIcon());
		node.setMenuUrl(resource.getMenuUrl());
		node.setFdesc(resource.getFdesc());
		if (resource.getParent() != null) {
			node.setpId(resource.getParent().getMenuId());
			node.setParentName(resource.getParent().getMenuName());
		} 
		return node;
	}
	public static Resource Dto2Resource(Resource resource) {
		/*ResourceDto node = new ResourceDto();

		node.setId(resource.getId());
		node.setName(resource.getName());
		node.setIcon(resource.getIcon());
		node.setUserUrl(resource.getUrl());
		if (resource.getParent() != null) {
			node.setpId(resource.getParent().getId());
		} else {
			node.setpId(0);
		}
		return node;*/
		return null;
	}
}
