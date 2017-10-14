package com.pdf.daoImpl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.pdf.dao.LogDao;
import com.pdf.entity.File;
import com.pdf.utils.JdbcUtils_DBCP;

public class LogDaoImpl implements LogDao{

	private static QueryRunner runerQuery = new QueryRunner(JdbcUtils_DBCP.getDataSource());
	
	@Override
	public boolean FileLog(String operate_desc, File file,String time) {
		System.out.println("file--"+file);
		System.out.println(operate_desc);
		
		String sql = "insert into log (operate_time,log_state,operate_desc,user_id,file_id)"
						+      "values (?,         1,     ?,?      , ?)";
		Object[] params = {time,operate_desc,file.getUser_id(),file.getFile_id()};
		try {
			int flag = runerQuery.update(sql,params);
			if(flag != 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean UserLog(String operate_desc, int user_id,String time) {
		String sql = "insert into log (operate_time,log_state,operate_desc,user_id,file_id)"
				+      "values (?,1,?,?,?)";
		Object[] params = {time,operate_desc,user_id,null};
		try {
			int flag = runerQuery.update(sql,params);
			if(flag != 0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
