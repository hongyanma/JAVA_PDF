package com.pdf.entity;

public class Menu {
	private int menu_id;
	private int menu_state;
	private String menu_name;
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getMenu_state() {
		return menu_state;
	}
	public void setMenu_state(int menu_state) {
		this.menu_state = menu_state;
	}
	public String getMenu_name() {
		return menu_name;
	}
	@Override
	public String toString() {
		return "Menu [menu_id=" + menu_id + ", menu_state=" + menu_state + ", menu_name=" + menu_name + "]";
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	
}
