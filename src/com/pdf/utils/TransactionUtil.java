package com.pdf.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * 事务控制类 
 * 
 * 作者：joker		时间：2016年10月17日上午10:55:42
 */
public class TransactionUtil {
	
	/**
	 * 
	 * 维护同一个线程中的Connection连接
	 */
	private static ThreadLocal<Connection> tl =  new ThreadLocal<Connection>();
	
	public static Connection getConnection() {
		Connection conn = (Connection)tl.get();
		if (conn==null) {
			conn = JdbcUtils_DBCP.getConnection();
			tl.set(conn);
		} 
		return conn;
	}
	
	/**
	 * 
	 * 函数名称：startTransaction
	 * 功能描述：开启事务
	 * 参数说明：
	 * 返回值：void
	 * 备注：
	 * 
	 * 作者：joker		时间：2016年10月17日上午10:55:42
	 */
	public static void startTransaction() {
		Connection conn = (Connection) tl.get();
		if(conn==null){
			conn = getConnection();
		}
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * 函数名称：roolback
	 * 功能描述：回滚
	 * 参数说明：
	 * 返回值：void
	 * 备注：
	 * 
	 * 作者：joker		时间：2016年10月17日上午10:55:35
	 */
	public static void roolback() {
		Connection conn = (Connection) tl.get();
		if(conn==null){
			conn = getConnection();
		}
		try {
			conn.rollback();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * 函数名称：commit
	 * 功能描述：事务结束提交
	 * 参数说明：
	 * 返回值：void
	 * 备注：
	 * 
	 * 作者：joker		时间：2016年10月17日上午10:56:30
	 */
	public static void commit() {
		Connection conn = (Connection) tl.get();
		if(conn==null){
			conn = getConnection();
		}
		try {
			conn.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * 函数名称：release
	 * 功能描述：释放连接
	 * 参数说明：
	 * 返回值：void
	 * 备注：
	 * 
	 * 作者：joker		时间：2016年10月17日上午10:56:20
	 */
	public static void release() {
		try {
			Connection conn = (Connection) tl.get();
			if(conn!=null){
				conn.close();
				tl.remove();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
