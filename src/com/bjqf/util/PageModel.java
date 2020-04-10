package com.bjqf.util;

import java.util.List;

public class PageModel {
	private int pageNo;//当前页码
	private int pageSize;//定义每一页显示的数据条数
	private List dataList;//每一页的数据集合
	private int count;//表中的总数据
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPage() {
		/*
		 * 获取总页数
		 */
		return count%pageSize == 0 ? count/pageSize : count/pageSize + 1;
	}
	/**
	 * 获取上一页方法
	 * @return
	 */
	public int getPrePage() {
		if(this.pageNo <= 1){
			return 1;
		}else{
			return this.pageNo - 1;
		}
	}
	/**
	 * 获取下一页方法
	 * @return
	 */
	public int getNextPage() {
		if(this.pageNo >= this.getTotalPage()){
			return this.getTotalPage();
		}else{
			return this.pageNo + 1;
		}
	}
}
