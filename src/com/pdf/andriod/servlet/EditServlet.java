package com.pdf.andriod.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pdf.entity.User;
import com.pdf.service.andriod.UserService_andriod;
import com.pdf.serviceImpl.andriod.UserServiceImpl_andriod;
import com.pdf.servlet.BaseServlet;

@SuppressWarnings("serial")
public class EditServlet extends BaseServlet{
	//////////////////////////////////////////////////////////≤ª π”√
	private static UserService_andriod userInstance = UserServiceImpl_andriod.getInstance();
	public void GetEditorList(HttpServletRequest req,HttpServletResponse res) throws IOException{
		//int user_id = Integer.parseInt(req.getParameter("user_id"));
		List<User> editors = userInstance.GetEditorList();
		JsonObject json = new JsonObject();
		if(editors!=null){
			json.add("result", new JsonParser().parse(new Gson().toJson(editors)).getAsJsonArray());
			json.addProperty("flag", true);
		}else{
			json.addProperty("flag", false);
		}
		
		PrintWriter out = res.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
}
