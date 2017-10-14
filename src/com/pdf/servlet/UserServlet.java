package com.pdf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pdf.entity.User;
import com.pdf.service.UserService;
import com.pdf.serviceImpl.UserServiceImpl;

@SuppressWarnings("serial")
public class UserServlet extends BaseServlet{
	private static UserService userInstance = UserServiceImpl.getInstance();
	
	public void DeleteUser(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String user_id = req.getParameter("user_id");
		boolean result = userInstance.DeleteUser(user_id);
		PrintWriter out =res.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}
	
	public void DeleteUserList(HttpServletRequest req ,HttpServletResponse res) throws IOException{
		String user_list = req.getParameter("user_list");
		System.out.println("11111111111111111111111111--------"+user_list);
		boolean result = userInstance.DeleteUserList(user_list);
		PrintWriter out =res.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}
	
	public void GetEditorList(HttpServletRequest req,HttpServletResponse res) throws IOException{
		List<User> editors = userInstance.GetEditorList();
		JsonObject json = new JsonObject();
		json.add("result", new JsonParser().parse(new Gson().toJson(editors)).getAsJsonObject());
		PrintWriter out = res.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
	
	public void getUserList(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String page =req.getParameter("page");
		String user_name = req.getParameter("user_name");
		List<User> userList = userInstance.getUserList(page,user_name);
		System.out.println(userList);
		PrintWriter out = res.getWriter();
		JsonObject json = new JsonObject();
		json.add("result", new JsonParser().parse(new Gson().toJson(userList)).getAsJsonArray());
		out.println(json);
		out.flush();
		out.close();
		
		
	}
	public void getUserNumber(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		String user_name = req.getParameter("user_name");
		int number = userInstance.getUserNumber(user_name);
		PrintWriter out = resp.getWriter();
		try {
			out.println(number);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void SearchUserById(HttpServletRequest req,HttpServletResponse res) throws IOException {
		int user_id = Integer.valueOf( req.getParameter("user_id") );
		User uu = userInstance.SearchUserById(user_id);
		JsonObject json = new JsonObject();
		json.add("result", new JsonParser().parse(new Gson().toJson(uu)).getAsJsonArray());
		PrintWriter out = res.getWriter();
		out.println();
		out.flush();
		out.close();
	}
	//获取用户信息
	public void UserDetail(HttpServletRequest req,HttpServletResponse res) throws IOException{
		int user_id = Integer.valueOf(req.getParameter("user_id"));
		User uu = userInstance.UserDetail(user_id);
		JsonObject json = new JsonObject();
		json.add("result", new JsonParser().parse(new Gson().toJson(uu)).getAsJsonObject());
		PrintWriter out = res.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
}
