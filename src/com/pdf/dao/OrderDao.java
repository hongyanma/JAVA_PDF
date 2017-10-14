package com.pdf.dao;

import java.util.List;

import com.pdf.entity.File;
import com.pdf.entity.Log;

public interface OrderDao {

	/**
	 * 
	 * 函数功能:搜索订单
	 * @author Searlas
	 * 时间2017年7月9日下午1:31:36
	 * @param order_id
	 * @return
	 */
	
	File SearchOrder(String order_id);
	/**
	 * 
	 * 函数功能:删除订单
	 * @author Searlas
	 * 时间2017年7月9日下午2:41:31
	 * @param orderId
	 * @return
	 */
	boolean DeleteOrder(String orderId);
	/**
	 * 
	 * 函数功能:审核订单
	 * @author Searlas
	 * 时间2017年7月9日下午5:27:00
	 * @param order_id
	 * @param judge
	 * @param user_id 
	 * @return
	 */
//	boolean ReviewOrder(String order_id, int judge, String user_id);
	boolean ReviewOrder(String order_id, String user_id);
	/**
	 * 
	 * 函数功能:批量删除订单
	 * @author Searlas
	 * 时间2017年7月10日下午1:22:25
	 * @param orderList
	 * @return
	 */
	boolean DeleteOrderList(String orderList);
	/**
	 * 
	 * 函数功能:获取订单列表
	 * @author Searlas
	 * 时间2017年7月10日下午3:04:03
	 * @param page
	 * @param order_name
	 * @return
	 */
	List<File> GetOrderList(String page, String order_name);
	/**
	 * 
	 * 函数功能:获取订单详情
	 * @author Searlas
	 * 时间2017年7月10日下午3:33:28
	 * @param order_id
	 * @return
	 */
	File GetOrderDetail(String order_id);
	/**
	 * 
	 * 函数功能:获取日志列表
	 * @author Searlas
	 * 时间2017年7月10日下午8:31:51
	 * @param page
	 * @return
	 */
	List<Log> getLogList(String page);
	 /**
	  * 
	  * 函数功能:添加订单文件
	  * @author Searlas
	  * 时间2017年7月11日下午6:15:55
	  * @param file
	  * @return
	  */
	int AddFile(File file);
	/**
	 * 
	 * 函数功能:批量删除日志
	 * @author Searlas
	 * 时间2017年7月12日上午9:28:11
	 * @param loglist
	 * @return
	 */
	boolean DeleteLogList(String loglist);
	/**
	 * 
	 * 函数功能:删除日志
	 * @author Searlas
	 * 时间2017年7月12日上午10:13:22
	 * @param log_id
	 * @return
	 */
	boolean DeleteLog(String log_id);
	/**
	 * 
	 * 函数功能:获取已经审核订单列表
	 * @author Searlas
	 * 时间2017年7月12日上午10:22:49
	 * @param page
	 * @return
	 */
	List<File> getReviewEdOrderList(String page);
	int getOrderNumber(String order_name);
	/**
	 * 
	 * 函数功能:获取新订单的文件的id以存放文件
	 * @author Searlas
	 * 时间2017年7月14日下午7:37:41
	 * @return
	 */
	int getNewFileId();
	int getLogNumber();

	/**
	 * 
	 * 函数功能:获取订单所有历史文件
	 * @author Searlas
	 * 时间2017年7月15日下午5:21:00
	 * @param order_id
	 * @return
	 */
	List<File> getOrderHis(String order_id);
	/**
	 * 
	 * 函数功能:提交新订单文件
	 * @author Searlas
	 * 时间2017年7月15日下午7:06:42
	 * @param file
	 * @return
	 */
	int SubmitFile(File file);
	/**
	 * 
	 * 函数功能:撤回到订单某个状态
	 * @author Searlas
	 * 时间2017年7月16日下午4:05:23
	 * @param order_id
	 * @param edit_time
	 * @return
	 */
	int WithDraw(String order_id, String edit_time,String user_id);
	/**
	 * 
	 * 函数功能:根据编辑id获取订单列表
	 * @author Searlas
	 * 时间2017年7月19日上午10:53:59
	 * @param edit_id
	 * @return
	 */
	List<File> getOrderListById(String edit_id);
	List<File> getSchedulList(String page);
	/**
	 * 
	 * 函数功能:查找订单进度文件是否存在
	 * @author Searlas
	 * 时间2017年7月22日下午3:50:53
	 * @param order_id
	 * @param schedule1
	 * @return
	 */
	File SearchOrderBySchedule(String order_id, int schedule1);
	/**
	 * 
	 * 函数功能:锁定订单
	 * @author Searlas
	 * 时间2017年9月15日下午10:27:27
	 * @param order_id
	 * @return
	 */
	int LockOrder(String order_id);
	/**
	 * 
	 * 函数功能:解锁订单
	 * @author Searlas
	 * 时间2017年9月15日下午10:31:43
	 * @param order_id
	 * @return
	 */
	int UnLockOrder(String order_id);
	/**
	 * 
	 * 函数功能:根据文件id获取订单
	 * @author Searlas
	 * 时间2017年9月15日下午10:45:07
	 * @param file_id
	 * @return
	 */
	File GetOrderByFileId(int file_id);
	/**
	 * 
	 * 函数功能:上传修改的文件
	 * @author Searlas
	 * 时间2017年9月16日上午10:09:57
	 * @param file
	 * @return
	 */
	int UplaodFile(File file);
	/**
	 * 
	 * 函数功能:判断一个订单是否锁定
	 * @author Searlas
	 * 时间2017年9月20日下午6:53:50
	 * @param order_id
	 * @return
	 */
	boolean IsLocked(String order_id);
	int getReviewNumber();


	
	
	

}
