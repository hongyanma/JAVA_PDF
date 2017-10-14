package com.pdf.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

@SuppressWarnings("static-access")
public class JdbcUtils_DBCP {

	private static DataSource ds = null;

	/**
	 * 静态代码块读取配置文件，初始化DBCP连接池
	 */
	static {
		try {
			InputStream in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties prop = new Properties();
			prop.load(in);

			BasicDataSourceFactory factory = new BasicDataSourceFactory();

			ds = factory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * 函数名称：getDataSource
	 * 功能描述：
	 * 参数说明：
	 * 返回值：DataSource
	 * 备注：
	 * 
	 * 作者：ariclee		时间：2016年6月12日下午1:23:39
	 */
	public static DataSource getDataSource() {
		return ds;
	}

	/**
	 * 函数名称：getConnection
	 * 功能描述：
	 * 参数说明：
	 * 返回值：Connection
	 * 备注：
	 * 
	 * 作者：ariclee		时间：2016年5月26日下午5:41:49
	 */
	public static Connection getConnection() {

		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("JdbcUtils_DBCP--getConnection ERROR!!");
		}
	}
	
	/**
	 * 函数名称：release
	 * 功能描述：
	 * 参数说明：
	 * 返回值：void
	 * 备注：
	 * 
	 * 作者：ariclee		时间：2016年5月26日下午5:41:55
	 */
	public static void release(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 *作者：ZhangHuanMing
	 *时间：2017年1月1日下午5:53:25
	 *函数名：releaseConn
	 *功能：将连接返回给连接池
	 *参数：
	 *返回值：void
	 */
	public static void releaseConn( Connection conn){
		try {
			
			if (conn != null && !conn.isClosed()) {
//				conn.setAutoCommit(true);
				conn.close();
				System.out.println("返回连接给连接池");
			}
		} catch (SQLException e) {
			System.out.println("关闭数据库连接失败：" + e);
		}
	}
}

