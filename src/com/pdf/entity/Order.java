package com.pdf.entity;

public class Order {
	private String order_id;
	private String client_name;
	private String order_time;
	private String delivery_time;
	private String entering_id;
	private int file_id;
	private int order_state;
	private String edition;
	private String schedule;
	private String explain;
	private String remark;
	private String ordertime;//接单时间
	private String deliverytime; //交货时间
	private int userid; //用户id
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getOrder_time() {
		return order_time;
	}
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}
	public String getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(String delivery_time) {
		this.delivery_time = delivery_time;
	}
	public String getEntering_id() {
		return entering_id;
	}
	public void setEntering_id(String entering_id) {
		this.entering_id = entering_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public int getOrder_state() {
		return order_state;
	}
	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getDeliverytime() {
		return deliverytime;
	}
	public void setDeliverytime(String deliverytime) {
		this.deliverytime = deliverytime;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", client_name=" + client_name + ", order_time=" + order_time
				+ ", delivery_time=" + delivery_time + ", entering_id=" + entering_id + ", file_id=" + file_id
				+ ", order_state=" + order_state + ", edition=" + edition + ", schedule=" + schedule + ", explain="
				+ explain + ", remark=" + remark + "]";
	}
	
}
