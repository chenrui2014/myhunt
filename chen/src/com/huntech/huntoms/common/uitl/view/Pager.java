package com.huntech.huntoms.common.uitl.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

/**flexigrid的一些参数*/
public class Pager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1763838566374639719L;
	/** 当前第几页 */
	private Integer page;
	/** 每页大小 */
	private Integer pagesize;
	/** 排序的列名 */
	private String sortname;
	/** 升序或者降序; desc;asc */
	private String sortorder;


	/**返回给页面需要用的*/
	public Integer getPage() {
		return page;
	}

	/**
	 * @return 分页起始值
	 */
	public Integer getLessThanNum() {
		return page*pagesize;
		    
	}
	/**
	 * @return 分页结束值
	 */
	public Integer getMoreThanNum() {
		return pagesize*(page-1) ;
	}
	/**
	 * @param page
	 *            返回给页面需要用的
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**每页大小*/
	public Integer getPagesize() {
		return pagesize;
	}

	/**每页大小*/
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}

	/**排序的列名*/
	public String getSortname() {
		return sortname;
	}

	/**排序的列名*/
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	/**升序或者降序; desc;asc*/
	public String getSortorder() {
		return sortorder;
	}

	/**升序或者降序; desc;asc*/
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}
	public static PageRequest buildPageRequest1(Pager pager) {
		if (pager != null) {

			List<Order> orders = new ArrayList<Order>();

			Direction dir = null;
			if ("DESC".equals(pager.getSortorder())) {
				dir = Direction.DESC;
			} else {
				dir = Direction.ASC;
			}

			Order order = new Order(dir, pager.getSortname());
			orders.add(order);

			Sort sort = new Sort(orders);

			PageRequest pr = new PageRequest(pager.getPage()-1, pager.getPagesize(), sort);

			return pr;
		}

		return null;
	}

	public static PageRequest buildPageRequest(Pager pager) {
		if (pager != null) {
			PageRequest pr = new PageRequest(pager.getPage()-1, pager.getPagesize());

			return pr;
		}

		return null;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE, true, true);
	}
}
