package com.pdf.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.pdf.service.OrderService;
import com.pdf.service.UserService;
import com.pdf.serviceImpl.OrderServiceImpl;
import com.pdf.serviceImpl.UserServiceImpl;


/**
 * @author:LinHaiZhen
 * @date:2016�?11�?25�? 下午2:55:31
 * @Description:工具类，提供静�?�的工具函数
 */
public class Utils {
	private static UserService userInstance = UserServiceImpl.getInstance();
	private static OrderService orderInstance = OrderServiceImpl.getInstance();
	public static String getNowTime() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date(System.currentTimeMillis()));// new Date()为获取当前系统时�?
	}
	
	public static String getNowTimeDate() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		return df.format(new Date(System.currentTimeMillis()));// new Date()为获取当前系统时�?
	}
	
    
    public static boolean writeFile(InputStream in,String filename) {
    	boolean result = false;
		try {
			int len = 0;
			byte buffer[] = new byte[1024];
//			File f = new File(filename);
//			if(!f.exists())
//			{
//				f.mkdirs();
//			}
			FileOutputStream out = new FileOutputStream(filename); // 向upload目录中写入文�?
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.close();
			result = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
    }
    
    public static int getEditorNum(){
    	return userInstance.getEditorNum();
    }
    
    public static int getNewFileId(){
    	return orderInstance.getNewFileId();
    }
    
    public static boolean IsLocked(String order_id) {
    	return orderInstance.IsLocked(order_id);
    }
}
