package com.pdf.daoImpl.andriod;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.pdf.dao.andriod.UserDao_andriod;
import com.pdf.entity.User;
import com.pdf.utils.JdbcUtils_DBCP;

public class UserDaoImpl_andriod implements UserDao_andriod{

	private static QueryRunner runerQuery = new QueryRunner(JdbcUtils_DBCP.getDataSource());
	@Override
	public User Login(User user) {
		String account = user.getAccount();
		String password = user.getPassword();
		Object[] params = {account,password};
		User result = new User();

		String sql = "select r.role_name,u.user_id,u.role_id,u.sex, u.mail,u.nickname,u.color from user as u,role as r where u.role_id=r.role_id  and u.account = ? and u.password =?  and user_state=1";
		try {
			System.out.println(sql);
			result = runerQuery.query(sql, new BeanHandler<>(User.class),params);
			if(result != null && result.getAccount() != null ){
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List<User> GetEditorList() {
		List<User> u =null;
		String sql = "select role.role_name,user.* from user,role where user.role_id=role.role_id and role.role_id = 2 and user.user_state = 1";
		try {
			System.out.println(sql);
			u = runerQuery.query(sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

}
