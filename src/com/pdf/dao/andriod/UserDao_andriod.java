package com.pdf.dao.andriod;

import java.util.List;

import com.pdf.entity.User;

public interface UserDao_andriod {

	User Login(User user);

	List<User> GetEditorList();

}
