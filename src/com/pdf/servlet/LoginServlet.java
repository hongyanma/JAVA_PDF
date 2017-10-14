package com.pdf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pdf.entity.User;
import com.pdf.service.UserService;
import com.pdf.serviceImpl.UserServiceImpl;



@SuppressWarnings("serial")
public class LoginServlet extends BaseServlet{
	private static UserService userInstance = UserServiceImpl.getInstance();
	
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		User user = new User(); 
		user.setAccount(account);
		user.setPassword(password);
		user = userInstance.Login(user);
		System.out.println(user);
		JsonObject json =new JsonObject();
		if(user != null){
			System.out.println("--------userId----"+user.getUser_id());
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			json.addProperty("user_id", user.getUser_id());
			json.addProperty("result", "登陆成功");
			System.out.println("success");
			json.add("data",new JsonParser().parse(new Gson().toJson(user)).getAsJsonObject());
		//	int edit_id = userInstance.getUserEditId(user.getUser_id());                          /////去掉editid的传值
		//	json.addProperty("edit_id", edit_id);
		}else{
			json.addProperty("result", "用户名/密码错误或用户不存在");
			System.out.println("fail");
		}
		PrintWriter out =response.getWriter();
		out.println(json);
		out.flush();
		out.close();
		
	}
	@SuppressWarnings("rawtypes")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		System.out.println("Servlet-logout");
//		boolean flag = true;
		Enumeration em = request.getSession().getAttributeNames();
	    while(em.hasMoreElements()){
	    	request.getSession().removeAttribute(em.nextElement().toString());
	    }
	    response.sendRedirect("/pdf_2017-09-14/Admin/Login/login.jsp");
//	    PrintWriter out = response.getWriter();
//	    out.print(flag);
//	    out.flush();
//	    out.close();
	}

	
}
