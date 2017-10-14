package com.pdf.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pdf.entity.User;
import com.pdf.service.UserService;
import com.pdf.serviceImpl.UserServiceImpl;

@SuppressWarnings("serial")
public class UserEdit extends HttpServlet{		
	private String wrongMessage = null;
	
	private static UserService instance = UserServiceImpl.getInstance();
	
	@Override
	public void init() throws ServletException {
		wrongMessage = this.getInitParameter("WRONG_MESSAGE");                //获得错误类型
		
		if( wrongMessage == null || wrongMessage.trim().isEmpty() ){
			wrongMessage = "服务器错误";
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
    	res.setCharacterEncoding("utf-8");
    	req.setCharacterEncoding("utf-8");
    	super.service(req, res);
    }
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		this.doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("getUserInfo");
		String user_id = req.getParameter("user_id");  //不可修改只用于区别
		int user_id1 = Integer.valueOf(user_id);
		String sex = req.getParameter("sex");
		int sex1 = Integer.valueOf(sex);
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String mail = req.getParameter("mail");
		String nickname = req.getParameter("nickname");
//		String user_state = req.getParameter("user_state");
//		int user_state1 =Integer.valueOf(user_state);    //默认用户state为1
		String color = req.getParameter("color");
		String role_id =req.getParameter("role_id");
		int role_id1 = Integer.valueOf(role_id);
		
		User user = new User(); 
		user.setUser_id(user_id1);
		user.setAccount(account);
		user.setPassword(password);
		user.setSex(sex1);
		user.setMail(mail);
		user.setNickname(nickname);
		user.setRole_id(role_id1);
		user.setUser_state(1);
		user.setColor(color);
		
		boolean flag = instance.EditUser(user);
		PrintWriter out = res.getWriter();
		if(flag){
			int logflag = LogOperate.UserLog("编辑用户", user.getUser_id());
			System.out.println("记录编辑用户中");
			out.println("<script> alert(\"修改成功!\");</script>");
			if(logflag ==1){
				
			}else{				
				out.println("<script> alert(\"日志记录失败！\");</script>");
			}
		}else{
			out.println("<script> alert(\"修改失败!\");</script>");			
		}
		out.flush();
		out.close();
	}


}
