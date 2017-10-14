package com.pdf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pdf.entity.User;
import com.pdf.service.UserService;
import com.pdf.serviceImpl.UserServiceImpl;

@SuppressWarnings("serial")
public class UserAdd extends HttpServlet{		
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

	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("getUserInfo");
		Map map = req.getParameterMap();
		for (Object key : map.keySet()) {
		    System.out.println(key+":"+req.getParameter(key.toString()));
		}
		String sex = req.getParameter("sex");
		int sex1 = Integer.parseInt(sex);
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String mail = req.getParameter("mail");
		String nickname = req.getParameter("nickname");
		int user_state1 = 1;
		String color = req.getParameter("color");
		String role_id =req.getParameter("role_id");
		int role_id1 = Integer.valueOf(role_id);
		System.out.println("color:"+color);
		User user = new User(); 
		user.setAccount(account);
		user.setPassword(password);
		user.setSex(sex1);
		user.setMail(mail);
		user.setNickname(nickname);
		user.setRole_id(role_id1);
		user.setUser_state(user_state1);
		user.setColor(color);
		int user_id1 = instance.AddUser(user);
		user.setUser_id(user_id1);
		PrintWriter out = res.getWriter();
		
		//返回的是user_id
		if(user_id1 == -1){
			System.out.println("user---------"+user.toString());
			/*if(user.getRole_id() == 2) {
				int edit_id = instance.UpdateUserEdit( String.valueOf( user.getUser_id() ) );
				out.println("<script> alert(\"添加成功!编辑id:"+edit_id+"\");  </script>");
			}else*/                                                                     //取消判定为编辑员后传输编辑id
			out.println("<script> alert(\"添加失败!\");  </script>");
		}else if(user_id1 == -2){
			out.println("<script> alert(\"账户重复!\");  </script>");
		}else {
			out.println("<script> alert(\"添加成功!\");  </script>");
			
		}
		out.flush();
		out.close();
	}


}
