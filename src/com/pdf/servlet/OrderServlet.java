package com.pdf.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pdf.entity.File;
import com.pdf.entity.Log;
import com.pdf.service.OrderService;
import com.pdf.serviceImpl.OrderServiceImpl;
import com.pdf.utils.Constant;
import com.pdf.utils.Utils;



@SuppressWarnings("serial")
public class OrderServlet extends BaseServlet{
	private static OrderService instance = OrderServiceImpl.getInstance();
	//根据订单id获取订单 多用在servlet内部
	public void SearchOrder(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String order_id = req.getParameter("order_id");
		File result =  instance.SearchOrder(order_id);
		PrintWriter out = res.getWriter();
		boolean flag = false;
		JsonObject json = new JsonObject();
		if(result == null){
			out.print(flag);
			json.addProperty("flag", false);
		}else{
			json.add("result", new JsonParser().parse(new Gson().toJson(result)).getAsJsonObject());	
			json.addProperty("flag", true);
			out.println(json);
		}
		out.flush();
		out.close();
	}
	public void DeleteOrder(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String order_id = req.getParameter("orderid");
		
		if(Utils.IsLocked(order_id)) {				//增加判断是否锁定
			System.out.println("列表中有文件已锁定");
			PrintWriter out = res.getWriter();
			out.println("<script> alert(\"文件已锁定!\");</script>");
			out.flush();
			out.close();
			return ;
		}
		
		File file = new File();
		file = instance.SearchOrder(order_id);
		boolean result = instance.DeleteOrder(order_id);
		boolean result2 =false;
		if(result) {
			System.out.println(file);
			//记录
			if((LogOperate.FileLog("删除订单", file))==1){
				result2 =true;
			}else 
				result2 = false;
			
		}else {
			result2 =false;
		}
		PrintWriter out = res.getWriter();
		out.println(result2);
		out.flush();
		out.close();
	}
	
	public void ReviewOrder(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String user_id = req.getParameter("user_id");
		String order_id = req.getParameter("order_id");
		boolean result = false;
		boolean result2 = false;
		if(Utils.IsLocked(order_id)) {         //增加判断是否锁定
			System.out.println("列表中有文件已锁定");
			PrintWriter out = res.getWriter();
			out.println("<script> alert(\"文件已锁定!\");</script>");
			out.flush();
			out.close();
			return ;
		}
		
		File file = new File();
		file.setOrder_id(order_id);
		file.setUser_id(Integer.valueOf(user_id));
		result = instance.ReviewOrder(order_id,user_id);
		if(result) {
			if( (LogOperate.FileLog("审核订单", file))!= 0  ){
				result2 = true;
				System.out.println("记录成功");
			}else {
				result2 = false;
				System.out.println("记录失败");
			}
		}
		PrintWriter out = res.getWriter();
		out.println(result2);
		out.flush();
		out.close();
	}
	
	public void withdraw(HttpServletRequest req ,HttpServletResponse res) throws IOException {
		String order_id = req.getParameter("order_id");
		String user_id = req.getParameter("user_id");
		String edit_time = req.getParameter("edit_time");
		int flag = instance.Withdraw(order_id,edit_time,user_id);
		File file = instance.SearchOrder(order_id);
		if(Utils.IsLocked(order_id)) {				//增加判断是否锁定
			System.out.println("文件已锁定");
			PrintWriter out = res.getWriter();
			out.println("<script> alert(\"文件已锁定!\");</script>");
			out.flush();
			out.close();
			return ;
		}
		if(flag != 0) {
			flag = LogOperate.FileLog("撤回订单", file);
		}
		PrintWriter out = res.getWriter();
		out.println(flag);
		out.flush();
		out.close();
	}
	
	public void DeleteOrderList(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String order_list = req.getParameter("order_list");
		System.out.println("orderList"+order_list);
		boolean result = true;
		File file = new File();
		String[] list = order_list.split(",");
		for (String string : list) {                    //增加判断是否列表中有锁定的
			if(Utils.IsLocked(string)) {
				System.out.println("列表中有文件已锁定");
				PrintWriter out = res.getWriter();
				out.println("<script> alert(\"列表中有文件已锁定!\");</script>");
				out.flush();
				out.close();
				return ;
			}
		}
		for (String string : list) {
			file = instance.SearchOrder(string);
			result = instance.DeleteOrder(string);
			if(result) {
				//记录
				System.out.println("记录删除："+file);
				LogOperate.FileLog("删除订单", file);					
			}else {
				System.out.println("中途失败 file："+file);
				break;
			}
		}
		PrintWriter out = res.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}
	
	public void getOrderList(HttpServletRequest req,HttpServletResponse res) throws IOException{
		//String page = "1";
		String order_name = req.getParameter("order_name");
		String page =req.getParameter("page");
//		List<Order> orderList = instance.GetOrderList(page);
		System.out.println(page+"----page orderid-----"+order_name);
		List<File> OrderList = instance.GetOrderList(page,order_name);
		System.out.println("orderList:"+ OrderList);
		
		PrintWriter out = res.getWriter();
		JsonObject json = new JsonObject();
		json.add("result", new JsonParser().parse(new Gson().toJson(OrderList)).getAsJsonArray());
		
		int totalnum = Utils.getEditorNum();
		System.out.println(totalnum);
		json.addProperty("totalnum", totalnum);
		
		out.println(json);
		out.flush();
		out.close();
		
		
	}
	
	public void GetOrderDetail(HttpServletRequest req,HttpServletResponse res) throws IOException{
		System.out.println("getorderdetail");
		String order_id = req.getParameter("order_id");
//		Order result = instance.getOrderDetail(order_id);
		File result = instance.getOrderDetail(order_id);
		JsonObject json = new JsonObject();
		json.add("result", new JsonParser().parse(new Gson().toJson(result)).getAsJsonObject());
		PrintWriter out = res.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
	
	public void GetLogList(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String page = req.getParameter("page");
		//String nickname = req.getParameter("nickname");
		List<Log> loglist = instance.getLogList(page);
		JsonObject json = new JsonObject();
		json.add("result", new JsonParser().parse(new Gson().toJson(loglist)).getAsJsonArray());
		PrintWriter out = res.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
	
	public void DeleteLogList(HttpServletRequest  req, HttpServletResponse res) throws IOException{
		String loglist = req.getParameter("loglist");
		boolean result = instance.DeleteLogList(loglist);
		PrintWriter out = res.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}
	
	//获取审核订单列表 可能使用
	public void GetReviewEdOrderList(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String page = req.getParameter("page");
		List<File> reviewedOrlist = instance.getReviewEdOrderList(page);
		System.out.println(reviewedOrlist);
		JsonObject json = new JsonObject();
		json.add("result", new JsonParser().parse(new Gson().toJson(reviewedOrlist)).getAsJsonArray());
		PrintWriter out = res.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
	
	public void DeleteLog(HttpServletRequest req ,HttpServletResponse res) throws IOException{
		String log_id = req.getParameter("log_id");
		boolean result = instance.DeleteLog(log_id);
		PrintWriter out = res.getWriter();
		out.println(result);
		out.flush();
		out.close();
	}

	public void getLogNumber(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		//String nickname = req.getParameter("nickname");
		int number = instance.getLogNumber();
		PrintWriter out = resp.getWriter();
		out.println(number);
		out.flush();
		out.close();
	}

	public void getOrderNumber(HttpServletRequest req,HttpServletResponse res) throws Exception{
        String order_name = req.getParameter("order_name");
		int number = instance.getOrderNumber(order_name);
		PrintWriter out = res.getWriter();
		out.println(number);
		out.flush();
		out.close();
	}
	 public void getReviewNumber(HttpServletRequest req,HttpServletResponse resp) throws Exception{
 		int number = instance.getReviewNumber();
 		PrintWriter out = resp.getWriter();
 		try {
 			out.println(number);
 			out.flush();
 			out.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 }
	//文件不是锁定状态 文件不是已审核 文件                 ？？？？
	public void getOrderHis(HttpServletRequest req,HttpServletResponse res) throws IOException{
		System.out.println("getorderhis");
		String order_id = req.getParameter("order_id");
		List<File> HisList = instance.getOrderHis(order_id);
		System.out.println(HisList);
		JsonObject json = new JsonObject();
		json.add("result", new JsonParser().parse(new Gson().toJson(HisList)).getAsJsonArray());
		PrintWriter out = res.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
	
//	public void getOrderListById(HttpServletRequest req,HttpServletResponse res) throws IOException {
//		String role_id = req.getParameter("role_id");
//		List<File> result = instance.getOrderListById(role_id);
//		JsonObject json = new JsonObject();
//		json.add("result", new JsonParser().parse(new Gson().toJson(result)).getAsJsonArray());
//		PrintWriter out = res.getWriter();
//		out.println(json);
//		out.flush();
//		out.close();
//	}
	
	
	public String DownloadForModify(HttpServletRequest req,HttpServletResponse res)throws IOException{
		String file_id = req.getParameter("file_id");
		String order_id = req.getParameter("order_id");
		String fullFileName = Constant.PdfLocation+file_id+".pdf";
		File file = instance.SearchOrder(order_id);
		System.out.println("file---"+file);           //已判断是否锁定
		if(file.getLocked() == 1 ) {
//			out1.close();
			PrintWriter out1 = res.getWriter();
			out1.println("<script> alert(\"文件已锁定!\");</script>");
			out1.flush();
			out1.close();
			System.out.println("文件已锁定");
			return null;
		}
		
		//锁定订单
		int lockflag = instance.LockOrder(order_id);
		if(lockflag == 0) {
//			out1.println("<script> alert(\"文件锁定失败!\");</script>");
//			out1.flush();
//			out1.close();
			System.out.println("文件锁定失败");
			return null;
		}
		System.out.println("文件锁定成功");
		res.setHeader("Content-Disposition", "attachment;filename="+file_id+".pdf");  //
		
		//下载服务
		InputStream in = new FileInputStream(fullFileName);
		OutputStream out = res.getOutputStream();
		int len;
		byte[] buf = new byte[8 * 1024];
		while( (len = in.read(buf,0,buf.length)) != -1) {
			out.write(buf, 0, len);
		}
		out.flush();
		in.close();
		out.close();
		
		//////////////////////////记录修改订单
		@SuppressWarnings("unused")
		int logflag =0;
		if((logflag=LogOperate.FileLog("修改订单", file))==0){
			System.out.println("记录失败！");
		}
		return "rdata";
		
	}
	
	
	

	
}
