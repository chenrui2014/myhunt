package com.huntech.huntoms.system.dto.tree;

import java.io.Serializable;

import com.huntech.huntoms.system.domain.Resource;

public class ZTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6222977359327330839L;

	private Long id;

	private String name;

	private String icon;

	private String userUrl;

	private long pId;

	private boolean open;

	private String parentName;
	
	private String resparam;
	
	private String fdesc;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public String getFdesc() {
		return fdesc;
	}

	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
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

	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getResparam() {
		return resparam;
	}

	public void setResparam(String resparam) {
		this.resparam = resparam;
	}

	public static ZTree resource2Tree(Resource resource) {
		ZTree node = new ZTree();

		node.setId(resource.getMenuId());
		node.setName(resource.getMenuName());
		node.setIcon(resource.getIcon());
		node.setResparam(resource.getResparam());
		node.setUserUrl(resource.getMenuUrl());
		if (resource.getParent() != null) {
			node.setpId(resource.getParent().getMenuId());
			node.setParentName(resource.getParent().getMenuName());
		} else {
			//node.setpId(0);
			node.setParentName("");
		}
		if (resource.getOpen() != null) {
			node.setOpen(1 == resource.getOpen() ? true : false);
		}
		
		return node;
	}

}
