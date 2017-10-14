package com.pdf.dao;

import com.pdf.entity.File;

public interface LogDao {
	/**
	 * 
	 * 函数功能:记录对文件操作
	 * @author Searlas
	 * 时间2017年7月10日下午10:36:32
	 * @param operate_desc
	 * @param nowdate 
	 * @param file_id
	 * @param user
	 * @return
	 */
	boolean FileLog(String operate_desc	,File file, String nowdate);
	/**
	 * 
	 * 函数功能:记录对用户操作
	 * @author Searlas
	 * 时间2017年7月10日下午10:38:27
	 * @param operate_desc
	 * @param user_id
	 * @param nowdate 
	 * @param user
	 * @return
	 */
	boolean UserLog(String operate_desc,int user_id, String nowdate);

}
