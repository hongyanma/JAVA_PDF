package com.pdf.service;

import java.util.List;

import com.pdf.entity.User;

public interface UserService {
	/**
	 * 
	 * 函数功能:登陆
	 * @author Searlas
	 * 时间2017年7月8日下午4:15:27
	 * @param user
	 * @return
	 */
	User Login(User user);
	/**
	 * 
	 * 函数功能:增加用户
	 * @author Searlas
	 * 时间2017年7月9日下午4:01:31
	 * @param user
	 * @return
	 */
	int AddUser(User user);
	/**
	 * 
	 * 函数功能:删除用户
	 * @author Searlas
	 * 时间2017年7月10日下午1:47:19
	 * @param user_id
	 * @return
	 */
	boolean DeleteUser(String user_id);
	/**
	 * 
	 * 函数功能:批量删除用户
	 * @author Searlas
	 * 时间2017年7月10日下午2:24:04
	 * @param user_list
	 * @return
	 */
	boolean DeleteUserList(String user_list);
	/**
	 * 
	 * 函数功能:获取编辑员数量
	 * @author Searlas
	 * 时间2017年7月11日下午7:31:06
	 * @return
	 */
	int getEditorNum();
	/**
	 * 
	 * 函数功能:获取编辑员列表
	 * @author Searlas
	 * 时间2017年7月11日下午7:49:11
	 * @return
	 */
	List<User> GetEditorList();
	/**
	 * 
	 * 函数功能:编辑用户信息
	 * @author Searlas
	 * 时间2017年7月12日下午1:16:10
	 * @param user
	 * @return
	 */
	boolean EditUser(User user);
	List<User> getUserList(String page, String user_name);
	int getUserNumber(String user_name);
	/**
	 * 
	 * 函数功能:根据id搜索用户
	 * @author Searlas
	 * 时间2017年7月20日下午1:12:15
	 * @param user_id
	 * @return
	 */
	User SearchUserById(int user_id);
	User UserDetail(int user_id);
	
}
