package com.pdf.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pdf.dao.LogDao;
import com.pdf.entity.File;
import com.pdf.factory.DaoFactory;

public class LogOperate {
	
	private static  LogDao logInstance = (LogDao) DaoFactory.getInstance().getBean(LogDao.class);
	
	public static int FileLog(String desc,File file){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String nowdate = sdf.format(date);
		if(logInstance.FileLog(desc,file,nowdate)){
			return 1;
		}
		return 0;
	}
	public static int UserLog(String desc,int user_id){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		String nowdate = sdf.format(date);
		
		boolean result = logInstance.UserLog(desc,user_id,nowdate);
		if(result){
			return 1;
		}
		return 0;
	}
}
