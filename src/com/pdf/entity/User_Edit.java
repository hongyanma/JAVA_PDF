package com.pdf.entity;

public class User_Edit {
    private int user_id;
    private int edit_id;
    private String nickname;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getEdit_id() {
		return edit_id;
	}
	public void setEdit_id(int edit_id) {
		this.edit_id = edit_id;
	}
	@Override
	public String toString() {
		return "User_Edit [user_id=" + user_id + ", edit_id=" + edit_id + ", nickname=" + nickname + "]";
	}
  
}
