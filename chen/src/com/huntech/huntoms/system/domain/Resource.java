package com.huntech.huntoms.system.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "S_Menu")
public class Resource implements Serializable {

	private static final long serialVersionUID = -5962641970095112770L;

	@Id
	@Column(name = "Menu_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESOURCE")
	@SequenceGenerator(name = "SEQ_RESOURCE", sequenceName = "SEQ_RESOURCE", allocationSize = 1)
	private Long menuId;

	@Column(name = "Menu_Name", length = 60)
	private String menuName;

	@Column(name = "RESPARARM", length = 400)
	private String resparam;
	
	@Column(name = "Menu_Url", length = 400)
	private String menuUrl;

	@Column(name = "ICON", length = 400)
	private String icon;

	@Column(name="OPEN")
	private Short open;
	
	@Column(name = "FDESC", length = 400)
	private String fdesc;
	
	@ManyToOne
	@JoinColumn(name = "parentID")
	private Resource parent;

   // @JoinColumn(name="PID")
	@OneToMany(mappedBy = "parent", cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	private List<Resource> children = new ArrayList<Resource>();



	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Short getOpen() {
		return open;
	}

	public void setOpen(Short open) {
		this.open = open;
	}

	public String getFdesc() {
		return fdesc;
	}

	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Resource [menuId=" + menuId + ", menuName=" + menuName
				+ ", menuUrl=" + menuUrl + ", icon=" + icon + ", open=" + open
				+ ", fdesc=" + fdesc + ", parent=" + parent + ", children="
				+ children + "]";
	}

	public String getResparam() {
		return resparam;
	}

	public void setResparam(String resparam) {
		this.resparam = resparam;
	}

	
	

}
