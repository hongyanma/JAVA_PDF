package com.pdf.andriod.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pdf.entity.User;
import com.pdf.service.andriod.UserService_andriod;
import com.pdf.serviceImpl.andriod.UserServiceImpl_andriod;
import com.pdf.servlet.BaseServlet;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static UserService_andriod userInstance = UserServiceImpl_andriod.getInstance();
	//µÇÂ¼
	public void login(HttpServletRequest request,HttpServletResponse response)throws IOException{
    	String account = request.getParameter("account");
    	String password = request.getParameter("password");
    	System.out.println(account);
    	System.out.println(password);
    	User user = new User();
    	user.setAccount(account);
    	user.setPassword(password);
    	JsonObject json = new JsonObject();
    	PrintWriter out = response.getWriter();
    	User result = userInstance.Login(user);
    	System.out.println(result);
    	if(result!=null){
    		json.add("data",new JsonParser().parse(new Gson().toJson(result)).getAsJsonObject());
    		json.addProperty("flag", true);
    	}else{
    		json.addProperty("flag", false);
    		json.addProperty("message", "1");
    	}
    	System.out.println("login"+result);
    	out.println(json);
    	out.flush();
    	out.close();
    	
    }
	
}
