package com.pdf.entity;

public class Role {
	private int role_id;
	private String role_name;
	private int state;
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getrole_name() {
		return role_name;
	}
	public void setNickname(String role_name) {
		this.role_name = role_name;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Role [role_id=" + role_id + ", role_name=" + role_name
				+ ", state=" + state + "]";
	}
	
}
