package com.pdf.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class File {
	private int file_id;
	private String order_name;
	private int user_id;
	private String order_id;
	private int order_state;
	private String file_path;
	//修改
	private int locked;
	private String modify_time;
	private int edit_time;
	
	private int edition;
	private int schedule;
	//基本上一次录入不修改
	private String order_time;
	private String end_time;
	private String delivery_time;
	private String client_name;
	private String remark;
	private String explain;
	private String nickname; 
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modify_time) {
		//将日期最后的.0去掉
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		try {
			Date date;
			date = sdf.parse(modify_time);
			String str = sdf.format(date);
			this.modify_time =str;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getEdit_time() {
		return edit_time;
	}
	public void setEdit_time(int edit_time) {
		this.edit_time = edit_time;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
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
	
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	public int getSchedule() {
		return schedule;
	}
	public void setSchedule(int schedule) {
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
	
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	@Override
	public String toString() {
		return "File [file_id=" + file_id + ", order_name=" + order_name + ", user_id=" + user_id + ", order_id="
				+ order_id + ", order_state=" + order_state + ", file_path=" + file_path + ", locked=" + locked
				+ ", modify_time=" + modify_time + ", edit_time=" + edit_time + ", edition=" + edition + ", schedule="
				+ schedule + ", order_time=" + order_time + ", end_time=" + end_time + ", delivery_time="
				+ delivery_time + ", client_name=" + client_name + ", remark=" + remark + ", explain=" + explain
				+ ", nickname=" + nickname + "]";
	}


	public void totalSet(String name, String value) {
		switch(name){
		case "order_name":
			this.order_name = (String) value;
			break;
		case "client_name":
			this.client_name = (String) value;
			break;
		case "order_time":
			this.order_time = (String) value;
			break;
		case "end_time":
			this.end_time = (String) value;
			break;
		case "delivery_time":
			this.delivery_time = (String) value;
			break;
		case "explain" :
			this.explain = (String) value;
			break;
		case "remark" :
			this.remark = (String) value;
			break;
		default :
			break;
		}
	}
	
}
