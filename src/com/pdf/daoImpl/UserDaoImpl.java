package com.pdf.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pdf.dao.UserDao;
import com.pdf.entity.User;
import com.pdf.utils.JdbcUtils_DBCP;

public class UserDaoImpl implements UserDao{
	private static QueryRunner runerQuery = new QueryRunner(JdbcUtils_DBCP.getDataSource());
	@Override
	public User Login(User user) {
		String account = user.getAccount();
		String password = user.getPassword();
		Object[] params = {account,password};
		User result = new User();
		String sql = "select * from user where user.account = ? and user.password = ? and user.user_state = 1";
		try {
			System.out.println(sql);
			result = runerQuery.query(sql, new BeanHandler<>(User.class),params);
			if(result != null && result.getAccount() != null){
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public int AddUser(User user) {
		String account = user.getAccount();
		String password = user.getPassword();
		String nickname = user.getNickname();
		int sex = user.getSex();
		String mail = user.getMail();
		int role_id = user.getRole_id();
		String color = user.getColor();
		String sql = "insert into user (account,password,sex,mail,nickname,user_state,role_id,color) values (?,?,?,?,?,?,?,?)";
		Object[] params =              {account,password,sex,mail,nickname,1         ,role_id,color};
		String sql2 = "select user_id from user where user.account = "+account ;
		String sql3 = "select * from user where user.account = ?";
		///判断是否能添加这个账户
		User user1 = null;
		try {
			user1 = runerQuery.query(sql3, new BeanHandler<>(User.class),user.getAccount());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//用户账户重复
		if(user1 != null) {
			return -2;    
		}

		int user_id = -1;
		try {
			System.out.println(sql);
			runerQuery.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(sql2);
			user_id = runerQuery.query(sql2, new ScalarHandler<Integer>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user_id;
	}
	@Override
	public boolean DeleteUser(String user_id) {
		String sql = "update user set user.user_state = 0 where user.user_id = "+user_id;
		int result = 0;
		try {
			System.out.println(sql);
			result =runerQuery.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result == 1)
			return true;
		else
			return false;
	}
	@Override
	public boolean DeleteUserList(String user_list) {
		String sql = "update user set user.user_state = 0 where user.user_id in ("+user_list+")";
		int result = 0 ;
		try {
			System.out.println(sql);
			result = runerQuery.update(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result != 0 )
			return true;
		else
			return false;
	}
	@Override
	public int getEditorNum() {
		String sql = "select * from user where user.role_id = 2 and user.user_state = 1";
		try {
			System.out.println(sql);
			List<User> userlist = runerQuery.query(sql, new BeanListHandler<>(User.class));
			return userlist.size();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	@Override
	public List<User> GetEditorList() {
		String sql = "select * from user where user.role_id = 2 and user.user_state = 1";
		try {
			System.out.println(sql);
			return runerQuery.query(sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean EditUser(User user) {
		String sql = "update user set user.account = ?,user.password = ?,user.sex = ?,user.mail = ?,"+
	"user.nickname = ?,user.user_state = ?,user.role_id = ?,user.color = ? where user.user_id = ?";
		Object[] params = {
			user.getAccount(),user.getPassword(),user.getSex(),user.getMail(),
			user.getNickname(),user.getUser_state(),user.getRole_id(),user.getColor(),user.getUser_id()
		};
		try {
			System.out.println(sql);
			int flag = runerQuery.update(sql,params);
			if(flag != 0){
				System.out.println("edit success!");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<User> getUserList(String page,String user_name) {
		List<User> result=null;
		int page1 = (Integer.valueOf(page)-1)*10;
		String sql= "";
		if(user_name.equals("")){
			sql = "select * from user as u where u.user_state=1 order by user_id limit ?,10 ";
		}else{
			sql = "select * from user as u where u.user_state=1 and nickname like '%"+user_name+"%' order by user_id limit ?,10 ";
		}

		try {
			result= runerQuery.query(sql,new BeanListHandler<>(User.class),page1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int getUserNumber(String user_name) {
		String sql = "";
		if(user_name.equals("")){
			sql = "select count(*) from user where user_state = 1";
		}else{
			sql = "select count(*) from user where user_state = 1 and nickname like '%"+user_name+"%'";
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
	public User SearchUserById(int user_id) {
		String sql = "select * from user where user.user_state = 1 and user.user_id = "+user_id;
		try {
			return runerQuery.query(sql, new BeanHandler<>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public User UserDetail(int user_id) {
		String sql = "select * from user where user_state=1 and user_id="+user_id;
		try {
			return runerQuery.query(sql, new BeanHandler<>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
