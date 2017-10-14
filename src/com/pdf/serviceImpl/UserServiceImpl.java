package com.pdf.serviceImpl;



import java.util.List;

import com.pdf.dao.UserDao;
import com.pdf.entity.User;
import com.pdf.factory.DaoFactory;
import com.pdf.service.UserService;

public class UserServiceImpl implements UserService {
	private static UserServiceImpl instance = new UserServiceImpl();
	public static UserServiceImpl getInstance() {
		return instance;
	}
	private UserDao daoInstance = (UserDao) DaoFactory.getInstance().getBean(UserDao.class);
	
	
	@Override
	public User Login(User user) {
		return daoInstance.Login(user);
	}

	@Override
	public int AddUser(User user) {
		return daoInstance.AddUser(user);
	}


	@Override
	public boolean DeleteUser(String user_id) {
		return daoInstance.DeleteUser(user_id);
	}

	@Override
	public boolean DeleteUserList(String user_list) {
		return daoInstance.DeleteUserList(user_list);
	}

	@Override
	public int getEditorNum() {
		return daoInstance.getEditorNum();
	}

	@Override
	public List<User> GetEditorList() {
		return daoInstance.GetEditorList();
	}

	@Override
	public boolean EditUser(User user) {
		return daoInstance.EditUser(user);
	}
	
	@Override
	public List<User> getUserList(String page, String user_name) {
		List<User> file=null;
		file =daoInstance.getUserList(page,user_name);
		return file;
	}
	
	@Override
	public int getUserNumber(String user_name) {
		int number = daoInstance.getUserNumber(user_name);
		return number;
	}


	@Override
	public User SearchUserById(int user_id) {
		return daoInstance.SearchUserById(user_id);
	}

	@Override
	public User UserDetail(int user_id) {
		return daoInstance.UserDetail(user_id);
	}

	
}
