package com.pdf.entity;

public class Log {
	private int log_id;
	private String operate_desc;
	private String operate_time;
	private int user_id;
	private int file_id;
	private int log_state;
	private String nickname;
	public int getLog_state() {
		return log_state;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setLog_state(int log_state) {
		this.log_state = log_state;
	}
	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public String getOperate_desc() {
		return operate_desc;
	}
	public void setOperate_desc(String operate_desc) {
		this.operate_desc = operate_desc;
	}
	@Override
	public String toString() {
		return "Log [log_id=" + log_id + ", operate_desc=" + operate_desc
				+ ", operate_time=" + operate_time + ", user_id=" + user_id
				+ ", file_id=" + file_id + ", log_state=" + log_state
				+ ", nickname=" + nickname + "]";
	}
	public String getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	
}
