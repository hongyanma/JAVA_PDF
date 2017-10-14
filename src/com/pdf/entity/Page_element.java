package com.pdf.entity;

public class Page_element {
	private int page_element_id;
	private String page_element_name;
	private String page_element_state;
	public int getPage_element_id() {
		return page_element_id;
	}
	public void setPage_element_id(int page_element_id) {
		this.page_element_id = page_element_id;
	}
	public String getPage_element_name() {
		return page_element_name;
	}
	public void setPage_element_name(String page_element_name) {
		this.page_element_name = page_element_name;
	}
	public String getPage_element_state() {
		return page_element_state;
	}
	public void setPage_element_state(String page_element_state) {
		this.page_element_state = page_element_state;
	}
	@Override
	public String toString() {
		return "Page_element [page_element_id=" + page_element_id + ", page_element_name=" + page_element_name
				+ ", page_element_state=" + page_element_state + "]";
	}
	
}
