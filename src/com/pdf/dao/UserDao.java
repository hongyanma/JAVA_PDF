package com.pdf.dao;

import java.util.List;

import com.pdf.entity.User;

public interface UserDao {
	/**
	 * 
	 * 函数功能:
	 * @author Searlas
	 * 时间2017年7月8日下午4:30:28
	 * @param user
	 * @return
	 */
	User Login(User user);
	/**
	 * 
	 * 函数功能:增加用户
	 * @author Searlas
	 * 时间2017年7月9日下午4:02:11
	 * @param user
	 * @return
	 */
	int AddUser(User user);
	/**
	 * 
	 * 函数功能:删除用户
	 * @author Searlas
	 * 时间2017年7月10日下午2:24:59
	 * @param user_id
	 * @return
	 */
	boolean DeleteUser(String user_id);
	/**
	 * 
	 * 函数功能:批量删除用户
	 * @author Searlas
	 * 时间2017年7月10日下午2:40:13
	 * @param user_list
	 * @return
	 */
	boolean DeleteUserList(String user_list);
	/**
	 * 
	 * 函数功能:获取编辑员数量
	 * @author Searlas
	 * 时间2017年7月11日下午7:32:57
	 * @return
	 */
	int getEditorNum();
	/**
	 * 
	 * 函数功能:获取编辑员列表
	 * @author Searlas
	 * 时间2017年7月11日下午7:49:44
	 * @return
	 */
	List<User> GetEditorList();
	/**
	 * 
	 * 函数功能:编辑用户信息
	 * @author Searlas
	 * 时间2017年7月12日下午1:16:57
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
	 * 时间2017年7月20日下午1:13:23
	 * @param user_id
	 * @return
	 */
	User SearchUserById(int user_id);
	User UserDetail(int user_id);

}
