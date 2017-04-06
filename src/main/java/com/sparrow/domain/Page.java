package com.sparrow.domain;

/**
 * page 实体类
 * @author:贤名
 * @createDate:2017-03-28
 *
 */

public class Page {
	private int current=1;//当前页码
	private int rowCount=20;//每页显示多少条
	private long total;//总条数
	Object rows;//集合

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public Page() {

	}
}
