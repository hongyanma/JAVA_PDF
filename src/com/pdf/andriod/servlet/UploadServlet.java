package com.pdf.andriod.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pdf.entity.File;
import com.pdf.service.OrderService;
import com.pdf.serviceImpl.OrderServiceImpl;
import com.pdf.servlet.LogOperate;
import com.pdf.utils.Constant;
import com.pdf.utils.Utils;
@SuppressWarnings("serial")
@WebServlet("/upload")
public class UploadServlet extends HttpServlet{		
	
	private static OrderService instance = OrderServiceImpl.getInstance();

       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("upload servlet");
		String file_name = "";
		String file_path = "";
		File file = new File();
		boolean flag = false;
		int LogFlag = 0;
		int user_id =0;
		String order_id = "";
		user_id = Integer.valueOf(req.getParameter("user_id"));
		order_id = req.getParameter("order_id");
		System.out.println("user_id----"+user_id+" "+order_id+" =--order_id");
		PrintWriter out = res.getWriter();
		int newid = Utils.getNewFileId();
		try {
			//1、得到解析器工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2、得到解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//3、为上传表单，则调用解析器解析上传数据
			List<FileItem> list = upload.parseRequest(req);//FileItem
			//遍历list，得到用于封装第一个上传输入项数据fileItem对象
			
			//获取最新文件id+1
			for(FileItem item :list){
				if(item.isFormField()){
					System.out.println(item.toString());
					//得到的是普通输入项
					String name = item.getFieldName();//得到输入项的名称
					System.out.println("name"+name);
					//
					byte[] buf = item.get();
					String value = new String(buf,"utf-8");
					System.out.println("value"+value);
					
					if(name.equals("user_id")){
						user_id =Integer.valueOf( value);
						System.out.println("user_id"+user_id);
					}else if(name.equals("order_id")){
						System.out.println("order_id"+order_id);
						order_id = value;
					}

				}else{
					System.out.println("file loading");
					//得到上传输入项
					file_name = item.getName();
					file_name = file_name.substring(file_name.lastIndexOf("\\")+1);
					System.out.println("file"+file_name);
					file_path = Constant.PdfLocation+newid+".pdf";
					file.setFile_path(newid+".pdf");
					System.out.println(file_path);
					System.out.println(file);
					flag = Utils.writeFile(item.getInputStream(),file_path );
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(flag){
			System.out.println("file upload success!");
			file = instance.SearchOrder(order_id);
			if(!Utils.IsLocked(order_id)) {         //增加判断是否未锁定
				System.out.println("列表中未锁定");
				out.flush();
				out.close();
				return ;
			}
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowtime = sdf.format(date);
			file.setFile_path(newid+".pdf");
			                                                  //删除了修改进度
			file.setEdition(1);
			int flag_unlock = instance.UnLockOrder(order_id);                   //增加先把之前锁定的文件解锁操作
			if(flag_unlock == 0) {
				System.out.println("旧文件解锁失败");
			}
			file.setEdit_time(file.getEdit_time()+1);         //增加一次编辑次数
			file.setModify_time(nowtime);                      //增加修改时间记录
			file.setLocked(0);
			//file.setFile_path(file_path);
			file.setUser_id(user_id);// 设置提交的人员
			//提交订单File更新
			System.out.println("提交订单");
			System.out.println(file);
			int file_id = instance.SubmitFile(file);
			file.setFile_id(file_id);
			//记录提交订单操作
			System.out.println("记录操作");
			LogFlag = LogOperate.FileLog("提交订单",file);
		}
		if(LogFlag == 1){
			System.out.println("记录成功");
			out.println(1);
		}else{
			out.println(0);
		}
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
