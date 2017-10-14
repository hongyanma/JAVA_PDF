package com.pdf.service.andriod;

import java.util.List;

import com.pdf.entity.User;

public interface UserService_andriod {

	User Login(User user);

	List<User> GetEditorList();

}
