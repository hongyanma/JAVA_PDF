package com.pdf.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pdf.dao.OrderDao;
import com.pdf.entity.File;
import com.pdf.entity.Log;
import com.pdf.utils.JdbcUtils_DBCP;
import com.pdf.utils.TransactionUtil;

public class OrderDaoImpl implements OrderDao{
	private static QueryRunner runerQuery = new QueryRunner(JdbcUtils_DBCP.getDataSource());
	@Override
	public File SearchOrder(String order_id) {
		File file = null;
		String sql = "select file.* , u.nickname from file,`user` as u where file.user_id = u.user_id and file.order_state = 1 and "
				+ "file.order_id = "+order_id+" and file.edition = 1";
		try {
			System.out.println(sql);
			file= runerQuery.query(sql, new BeanHandler<>(File.class));
	
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		return file;
	}
	@Override
	public boolean DeleteOrder(String order_id) {
		String sql = "update file set file.order_state = 0 where file.order_id = "+order_id;
		try {
			int result = runerQuery.update(sql);
			if(result != 0){
				return true;
			}
//			int flag = runerUpdate.update(sql);
//			if(flag==1){
//				return true;
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	                  //修改 进度直接设为2 
	public boolean ReviewOrder(String order_id,String user_id) {
		String sql ="update file set file.schedule = 2 ,file.user_id = "+user_id+" where file.order_id = "+order_id+" and file.order_state = 1 and file.edition = 1";//审核通过
		System.out.println(sql);
		boolean result = false;
		try {
			int a =  runerQuery.update(sql);
			if(a != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public boolean DeleteOrderList(String orderList) {
		String sql = "update file set file.order_state = 0 where file.order_id in ("+orderList+")";
		try {
			System.out.println(sql);
			int flag = runerQuery.update(sql);
			if(flag==0){
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public List<File> GetOrderList(String page,String order_name) {
		//file.edition=1 and file.order_state=1 新版本且未被删除
		int page1 = (Integer.valueOf(page)-1)*10;
		String sql = "";
		if(order_name.equals("")){
			sql = "select `user`.nickname,file.* from file,user where file.user_id = user.user_id and file.edition=1 and file.schedule between 0 and 1 and file.order_state=1 order by order_id limit ?,10 ";
		}else{
			sql = "select `user`.nickname,file.* from file,user where file.user_id = user.user_id and file.edition=1 and file.order_state=1 and file.schedule between 0 and 1 and order_name like '%"+order_name+"%' order by order_id limit ?,10 ";
		}
		System.out.println(sql);
		//String sql = "select user.nickname,user.role_id,file.* from file,user where file.user_id=user.user_id and file.edition=1 and file.order_state=1 limit ?,10";
		try {
			return runerQuery.query(sql,new BeanListHandler<>(File.class),page1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public File GetOrderDetail(String order_id) {
		//file.edition=1 and file.order_state=1 新版本且未被删除
		String sql = "select `user`.nickname,file.* from file,`user` where `user`.user_id = file.user_id and file.edition=1 and file.order_state=1 and file.order_id = ?";
		try {
			return runerQuery.query(sql, new BeanHandler<>(File.class),order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Log> getLogList(String page) {
		//log需要log.log_state=1
		int page1 = (Integer.valueOf(page)-1)*10;
		String sql ="";
		//if(nickname.equals("")){
			//sql = "select u.nickname,l.* from user as u,log as l where l.log_state = 1 and u.user_id =l.user_id order by log_id  limit ?,10";
		//}else{
			sql = "select u.nickname,l.* from user as u,log as l where l.log_state = 1 and u.user_id =l.user_id  order by l.log_id limit ?,10";
		//}
		 
		try {
			return runerQuery.query(sql, new BeanListHandler<>(Log.class),page1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int AddFile(File file) {
		String sql = "insert into file (order_id,order_name,file_path,client_name,end_time,order_time,modify_time,edit_time,locked"
				+ ",delivery_time,order_state,edition,`schedule`,user_id,`explain`,remark) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Object[] params = {file.getOrder_id(),file.getOrder_name(),file.getFile_path(),file.getClient_name(),
				file.getEnd_time(),file.getOrder_time(),file.getModify_time(),file.getEdit_time(),file.getLocked(),
				file.getDelivery_time(),file.getOrder_state(),file.getEdition(),
				file.getSchedule(),file.getUser_id(),file.getExplain(),file.getRemark()
		}; 
		try {
			System.out.println(sql);
			return runerQuery.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	@Override
	public boolean DeleteLogList(String loglist) {
		String sql = "update log set log.log_state = 0 where log.log_id in ("+loglist+")";
		System.out.println(sql);
		try {
			int result = runerQuery.update(sql);
			if(result!=0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean DeleteLog(String log_id) {
		String sql = "update log set log.log_state = 0 where log.log_id = "+log_id;
		System.out.println(sql);
		try {
			int result = runerQuery.update(sql);
			if(result != 0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<File> getReviewEdOrderList(String page) {
		String sql =  "select file.*,u.user_id from file,`user` as u where file.user_id = u.user_id "
				+ "and file.order_state = 1 and file.schedule = 2 and file.edition = 1 limit ?,10";
		int page1 = (Integer.valueOf(page)-1)*10;
		Object[] params = {page1};
		try {
			System.out.println(sql);
			return runerQuery.query(sql,new BeanListHandler<>(File.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int getOrderNumber(String order_name) {
		String sql = "";
		if(order_name.equals("")){
			sql = "select count(*) from file where order_state = 1 and edition=1 and file.schedule between 0 and 1";
		}else{
			sql = "select count(*) from file where order_state = 1 and edition=1 and file.schedule between 0 and 1 and order_name like '%"+order_name+"%'";
		}
		Object object = null;
		try {
			object = runerQuery.query(sql, new ScalarHandler<Object>(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int number = ((Long)object).intValue(); 
		return number;
	}
	@Override
	public int getNewFileId() {
		// 返回一个可以生成新文件的id
		int id = 0 ;
		String sql = "select IFNULL( Max(file_id) , 1 ) as file_id from file";
		try {
			Long id3 = runerQuery.query(sql, new ScalarHandler<Long>());
			id = id3.intValue();
			return id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int getLogNumber() {
		//log需要log.log_state=1
		String sql = "";
		//if(nickname.equals("")){
		//	sql = "select count(*) from user as u,log as l where l.log_state = 1 and u.user_id =l.user_id  ";
		//}else{
			sql = "select count(*) from user as u,log as l where l.log_state = 1 and u.user_id =l.user_id";
		//}
		
		Object object = null;
		try {
			object = runerQuery.query(sql, new ScalarHandler<Object>(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int number = ((Long)object).intValue(); 
		return number;
	}


	@Override
	public List<File> getOrderHis(String order_id) {
		String sql = "select file.*,u.nickname from file,`user` as u where file.user_id = u.user_id and file.order_state = 1 and file.order_id = "+order_id;
		try {
			return runerQuery.query(sql, new BeanListHandler<>(File.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int SubmitFile(File file) {
		//设置旧版本
		String sql = "update file set file.edition = 0 where file.order_state = 1 and file.order_id = "+file.getOrder_id();
		//插入新版本
		String sql2 = "insert into file (order_id,order_name,file_path,client_name,end_time,order_time,modify_time,edit_time,locked"
				+ ",delivery_time,order_state,edition,`schedule`,user_id,`explain`,remark) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		//寻找新版本的id
		String sql3 = "select file_id from file where file.order_state = 1 and file.order_id = ? and file.edition = 1";
		Object[] params = {file.getOrder_id(),file.getOrder_name(),file.getFile_path(),file.getClient_name(),
				file.getEnd_time(),file.getOrder_time(),file.getModify_time(),file.getEdit_time(),file.getLocked(),
				file.getDelivery_time(),file.getOrder_state(),file.getEdition(),
				file.getSchedule(),file.getUser_id(),file.getExplain(),file.getRemark()
		};
		int result = 0;
		TransactionUtil.startTransaction();
		try {
			if( ( runerQuery.update(TransactionUtil.getConnection(),sql)) != 0){
				
				if( runerQuery.update(TransactionUtil.getConnection(),sql2, params) != 0){
					result = runerQuery.query(sql3,new ScalarHandler<Integer>(), file.getOrder_id());
				}else{
					System.out.println("添加提交新订单失败!");
				}
				
			}else{
				System.out.println("设置订单不是最新失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			TransactionUtil.roolback();
			System.out.println("roolback-------");
		}finally{
			TransactionUtil.commit();
			TransactionUtil.release();
		}
		
		return result;
	}
	@Override
	public int WithDraw(String order_id, String edit_time,String user_id) {
		System.out.println(order_id+" orderid---edittime  "+edit_time+"   user_id"+user_id);
		TransactionUtil.startTransaction();
		String sql = "update file set file.edition = 0 where file.order_state = 1 and file.order_id = "+order_id;
		String sql1 = "update file set file.order_state = 0 where file.order_id = ? and file.order_state = 1 and file.edit_time > ?";
		String sql2 = "update file set file.edition = 1 where file.order_state = 1 and file.edit_time = ? and file.order_id = ?";
		Object[] params = { order_id,edit_time};
		Object[] params1 = { edit_time,order_id };
		int result = 0;
		int result1 = 0;
		int result2 = 0;
		try {
			result = runerQuery.update(TransactionUtil.getConnection(),sql);
			System.out.println(sql);
			if(result == 0) {
				System.out.println("改版本为0失败");
			}else {
				System.out.println("撤回操作-删除后续文件");
				result1 = runerQuery.update(TransactionUtil.getConnection(),sql1,params);
				if(result1 != 0 ) {
					System.out.println("撤回操作-设置最新文件");
					result2 = runerQuery.update(TransactionUtil.getConnection(),sql2, params1);
				}
				return result2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("撤回失败");
			TransactionUtil.roolback();
		}finally {
			TransactionUtil.commit();
			TransactionUtil.release();
		}
		return 0;
	}
	
	@Override
	//不使用
	public List<File> getOrderListById(String role_id) {
		String sql = "select * from file where file.order_state = 1 and file.edition = 1 and file.schedule in (0,1)";
		if(role_id.equals("2") || role_id.equals("3")) {			
			try {
				System.out.println("编辑员/管理员获取订单以供编辑");
				return runerQuery.query(sql, new BeanListHandler<>(File.class));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("角色不符合");
			return null;
		}
		System.out.println("error");
		return null;
	}

	//不使用
	@Override
	public List<File> getSchedulList(String page) {
		int page1 = (Integer.valueOf(page)-1)*10;
		String sql = "select distinct * from file where file.edition=1 and file.order_state=1  limit ?,10 ";
		try {
			return runerQuery.query(sql,new BeanListHandler<>(File.class),page1);
		} catch (SQLException e) {
					e.printStackTrace();
		}
		return null;
	}
	@Override
	//不使用
	public File SearchOrderBySchedule(String order_id, int schedule1) {
		String sql = "select * from file where file.order_id = ? and file.schedule = ? and file.order_state = 1";
		try {
			return runerQuery.query(sql,new BeanHandler<>(File.class), order_id,schedule1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int LockOrder(String order_id) {
		String sql = "update file set file.locked = 1 where file.order_id = ? and file.order_state = 1 and file.edition = 1";
		try {
			return runerQuery.update(sql,order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int UnLockOrder(String order_id) {
		String sql = "update file set file.locked = 0 where file.order_id = ? and file.order_state = 1 and file.edition = 1";
		try {
			return runerQuery.update(sql,order_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public File GetOrderByFileId(int file_id) {
		String sql = "select file.*,u.user_id from file,`user` as u where file.user_id = u.user_id and file.file_id = ? and file.order_state = 1 and file.edition = 1";
		try {
			return runerQuery.query(sql,new BeanHandler<File>(File.class), file_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int UplaodFile(File file) { //先将旧版本edition设为0 再插入新版本file
		String sql = "update file set file.edition = 0 where file.order_id = ?";
		String sql2 = "insert into file (order_id,order_name,file_path,client_name,end_time,order_time,modify_time,edit_time,locked"
				+ ",delivery_time,order_state,edition,`schedule`,user_id,`explain`,remark) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {file.getOrder_id(),file.getOrder_name(),file.getFile_path(),file.getClient_name(),file.getEnd_time(),file.getOrder_time()
				,file.getModify_time(),file.getEdit_time(),file.getLocked(),
				file.getDelivery_time(),file.getOrder_state(),file.getEdition(),file.getSchedule(),file.getUser_id(),file.getExplain(),
				file.getRemark()};
		int flag = 0;
		try {
			flag = runerQuery.update(sql,file.getOrder_id());
			if(flag != 0) {
				return runerQuery.update(sql2,params);
			} }catch (SQLException e) {
				e.printStackTrace();
		}
		
		return 0;
	}
	@Override
	public boolean IsLocked(String order_id) {
		String sql = "select file.locked from file where file.edition = 1 and  file.order_id = "+order_id;
		int locked = -1;
		try {
			System.out.println(sql);
			locked = runerQuery.query(sql, new ScalarHandler<Long>()).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(locked == -1) {
			System.out.println("查询失败!");
		}
		return (locked==1);
	}
	@Override
	public int getReviewNumber() {
		String sql = "select count(*) from file where file.order_state = 1 and file.schedule = 2 and file.edition = 1";		
		Object object = null;
		try {
			object = runerQuery.query(sql, new ScalarHandler<Object>(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int number = ((Long)object).intValue(); 
		return number;
	}
}