package com.pdf.entity;

public class Page {
	private int page_id;
	private String page_name;
	private int page_state;
	public int getPage_id() {
		return page_id;
	}
	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}
	public String getPage_name() {
		return page_name;
	}
	public void setPage_name(String page_name) {
		this.page_name = page_name;
	}
	public int getPage_state() {
		return page_state;
	}
	public void setPage_state(int page_state) {
		this.page_state = page_state;
	}
	@Override
	public String toString() {
		return "Page [page_id=" + page_id + ", page_name=" + page_name + ", page_state=" + page_state + "]";
	}
	
}
