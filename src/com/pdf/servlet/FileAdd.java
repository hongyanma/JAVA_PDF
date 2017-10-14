package com.pdf.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pdf.entity.File;
import com.pdf.entity.User;
import com.pdf.service.OrderService;
import com.pdf.serviceImpl.OrderServiceImpl;
import com.pdf.utils.Constant;
import com.pdf.utils.Utils;

@SuppressWarnings("serial")
public class FileAdd extends HttpServlet{		
	
	private static OrderService instance = OrderServiceImpl.getInstance();
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		System.out.println("file-add servlet");
		File file = new File();
		HttpSession session = req.getSession();
		User user =  (User) session.getAttribute("user");
		int user_id = user.getUser_id();
		String file_name = null;
		String file_path = null;
		boolean flag = false;
		int newid = 0 ;
		try {
			//1、得到解析器工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2、得到解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//3、为上传表单，则调用解析器解析上传数据
			List<FileItem> list = upload.parseRequest(req);//FileItem
			//遍历list，得到用于封装第一个上传输入项数据fileItem对象
			newid = Utils.getNewFileId()+1;
			//获取最新文件id+1
			for(FileItem item :list){
				if(item.isFormField()){
					//得到的是普通输入项
					String name = item.getFieldName();//得到输入项的名称
					//
					byte[] buf = item.get();
					String value = new String(buf,"utf-8");
					file.totalSet(name,value);
				}else{
					System.out.println("file loading");
					//得到上传输入项
					file_name = item.getName();
					file_name = file_name.substring(file_name.lastIndexOf("\\")+1);
					//写入图片到
//					String basicpath = req.getSession().getServletContext().getRealPath("/") ;
//					file_path = basicpath+"/Test/"+file_name;
					file_path = Constant.PdfLocation+newid+".pdf";
					file.setFile_path(newid+".pdf");
					System.out.println("filepath---------"+file_path);
					flag = Utils.writeFile(item.getInputStream(),file_path );
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(flag){
			System.out.println("file upload success!");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String nowtime = sdf.format(date);  //订单id生成
			
			Date date2 = new Date();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String nowtime2 = sdf2.format(date2); //修改时间的添加
			
			file.setOrder_id(nowtime);
			file.setUser_id(user_id);
			file.setEdit_time(0);           //增加 添加完之后编辑次数默认为0
			file.setModify_time(nowtime2);   //增加修改时间记录
			file.setLocked(0);              //增加 添加完之后锁定默认为0  
			file.setEdition(1);
			file.setOrder_state(1);
			file.setSchedule(0);     //添加文件后文件进度为0 已录入
			System.out.println("file:"+file.toString());
			System.out.println("insert a new file");
			
			flag = (instance.AddFile(file)!=0);
			if(!flag) {
				System.out.println("添加文件失败");
			}
			file.setFile_id(newid);         //修改直接把新的id放进file类
			file.setSchedule(0);			//修改已录入的schedule为0
			flag = (LogOperate.FileLog("添加订单",file)!=0);
		}
		PrintWriter out = res.getWriter();
		if(flag){
			out.println("<script> alert(\"添加成功!\");</script>");
		}else{
			out.println("<script> alert(\"添加失败!\");</script>");
		}
		out.flush();
		out.close();
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
