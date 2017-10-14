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
public class FileUpload extends HttpServlet{
		
		
	OrderService instance = OrderServiceImpl.getInstance();
	       
		/* (non-Javadoc)
		 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
		 */
		@SuppressWarnings("unchecked")
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			res.setCharacterEncoding("utf-8");
	    	req.setCharacterEncoding("utf-8");
	    	HttpSession session = req.getSession();
	    	User user =  (User) session.getAttribute("user");
	    	PrintWriter out = res.getWriter();
	    	System.out.println("file-upload servlet");
	    	
	    	
			File file = new File();
			int user_id = user.getUser_id();
			String file_name = null;
			String file_path = null;
			boolean flag = false;
			int LogFlag = 0;
			int newid = 0;
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
						byte[] buf = item.get();
						String value = new String(buf,"utf-8");
						file.totalSet(name,value);
					}else{
						System.out.println("file loading");
						//得到上传输入项
						file_name = item.getName();
						file_name = file_name.substring(file_name.lastIndexOf("\\")+1);
						file_name = file_name.substring(0,file_name.lastIndexOf("."));
						//创建新文件名称和文件写入
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
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String nowtime = sdf.format(date);
				// 1    根据上传的文件的id获取订单
				file = instance.GetOrderByFileId(Integer.valueOf(file_name));
				
				if(!Utils.IsLocked(file.getOrder_id())) {         //增加判断是否未锁定
					System.out.println("列表中未锁定");
					out.println("<script> alert(\"文件未锁定!\");</script>");
					out.flush();
					out.close();
					return ;
				}
				file.setFile_path(newid+".pdf");
				file.setFile_id(newid);
				file.setModify_time(nowtime);//增加修改时间记录
				file.setLocked(0);           //增加锁定默认为0
				file.setEdit_time(file.getEdit_time()+1);    //订单编辑次数+1
				file.setUser_id(user_id);  //记录操作的人
				file.setEdition(1);
				file.setOrder_state(1);
				file.setSchedule(1);
				// 2    先解锁已经锁定的上次下载的订单
				int lockflag = instance.UnLockOrder(file.getOrder_id());
				System.out.println("file:"+file.toString());
				System.out.println("upload new file");
				//  3    更新订单
				System.out.println("更新订单："+file);
				int file_id = instance.UploadFile(file);
				file.setFile_id(file_id);
				//  4    解锁订单
				if(lockflag == 0) {
					System.out.println("解锁订单失败");
					out.println("<script> alert(\"解锁订单失败!\");</script>");
					out.flush();
					out.close();
				}
				//  5  记录订单
				LogFlag = LogOperate.FileLog("上传订单",file);
			}
			if(LogFlag == 1){
				out.println("<script> alert(\"上传成功!\");</script>");
			}else{
				out.println("<script> alert(\"上传失败!\");</script>");
			}
			out.flush();
			out.close();
		    
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
