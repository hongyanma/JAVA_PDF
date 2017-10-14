package com.pdf.serviceImpl.andriod;

import java.util.List;

import com.pdf.dao.andriod.UserDao_andriod;
import com.pdf.entity.User;
import com.pdf.factory.DaoFactory;
import com.pdf.service.andriod.UserService_andriod;

public class UserServiceImpl_andriod implements UserService_andriod{

	private static UserServiceImpl_andriod instance = new UserServiceImpl_andriod();
	public static UserServiceImpl_andriod getInstance() {
		return instance;
	}
	private UserDao_andriod daoInstance = (UserDao_andriod) DaoFactory.getInstance().getBean(UserDao_andriod.class);
	

	@Override
	public User Login(User user) {
		System.out.println(user);
		return daoInstance.Login(user);
	}


	@Override
	public List<User> GetEditorList() {
		return daoInstance.GetEditorList();
	}

}
