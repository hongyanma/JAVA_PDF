package com.pdf.daoImpl.andriod;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.pdf.dao.andriod.OrderDao_andriod;
import com.pdf.entity.File;
import com.pdf.utils.JdbcUtils_DBCP;

public class OrderDaoImpl_andriod implements OrderDao_andriod{
	private static QueryRunner runerQuery = new QueryRunner(JdbcUtils_DBCP.getDataSource());
	
	@Override
	public File search(String order_name) {
		String sql = null;
		File result = new File();
		if(order_name==null){
			sql = "select file.*,`user`.nickname from file,`user` where `user`.user_id = file.user_id and edition = 1 and order_state =1";
		}else {
			sql = "select file.*,`user`.nickname from file,`user` where `user`.user_id = file.user_id and file.order_state = 1 and"
					+ " file.edition = 1 and file.order_name = '"+order_name+"'";
		}
		//Object[] param = {order_id};
		 try {
				result =  runerQuery.query(sql, new BeanHandler<>(File.class));
			 } catch (SQLException e) {
				e.printStackTrace();
		     }
		return result;
	}
	@Override
	public File download(String order_id) {
		String sql=null;
		File file = null;
		sql = "select file_id,order_id,file_path from file where order_id = "+order_id;
		try{
			 file = runerQuery.query(sql,new BeanHandler<File>(File.class));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return file;
	}
	
	@Override
	public List<File> getOrderHis(String order_id) {
		String sql = "select file.* ,u.nickname from file,`user` as u where file.order_state = 1 and file.user_id = u.user_id and"
				+ " file.order_id = '"+order_id+"' order by modify_time desc";
		try {
			List<File> list =runerQuery.query(sql, new BeanListHandler<>(File.class));
			System.out.println(list);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
