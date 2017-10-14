package com.pdf.entity;

public class User {
	private int user_id;
	private String account;  //手机号用作账号
	private String password;
	private int sex;
	//0表示男 1表示女
	private String mail;
	private String nickname;
	private String role_name;
	private int user_state;
	private int role_id;
	private String color;
	private int edit_id;
	
	
	
	
	public int getEdit_id() {
		return edit_id;
	}

	public void setEdit_id(int edit_id) {
		this.edit_id = edit_id;
	}

	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getUser_state() {
		return user_state;
	}
	public void setUser_state(int user_state) {
		this.user_state = user_state;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", account=" + account
				+ ", password=" + password + ", sex=" + sex + ", mail=" + mail
				+ ", nickname=" + nickname + ", role_name=" + role_name
				+ ", user_state=" + user_state + ", role_id=" + role_id
				+ ", color=" + color + ", edit_id=" + edit_id + "]";
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
}
