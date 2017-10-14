package com.pdf.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pdf.entity.File;
import com.pdf.entity.Log;


public interface OrderService {
	
	
	/**
	 * 
	 * 函数功能:搜索订单
	 * @author Searlas
	 * 时间2017年7月9日下午2:43:28
	 * @param order_id
	 * @return
	 */
	File SearchOrder(String order_id);
	/**
	 * 
	 * 函数功能:删除订单
	 * @author Searlas
	 * 时间2017年7月9日下午2:43:38
	 * @param order_id
	 * @return
	 */
	boolean DeleteOrder(String order_id);
	/**
	 * 
	 * 函数功能:添加订单文件
	 * @author Searlas
	 * 时间2017年7月11日下午6:16:25
	 * @param file
	 * @return
	 */
	int AddFile(File file);
	/**
	 * 
	 * 函数功能:审核订单
	 * @author Searlas
	 * 时间2017年7月9日下午5:25:00
	 * @param order_id
	 * @param judge
	 * @param user_id 
	 * @return
	 */
	boolean ReviewOrder(String order_id, String user_id);
	/**
	 * 
	 * 函数功能:批量删除订单
	 * @author Searlas
	 * 时间2017年7月10日下午1:20:58
	 * @param orderList
	 * @return
	 */
	boolean DeleteOrderList(String orderList);
	/**
	 * 
	 * 函数功能:获取订单列表
	 * @author Searlas
	 * 时间2017年7月10日下午3:03:24
	 * @param page
	 * @param order_name
	 * @return
	 */
	List<File> GetOrderList(String page, String order_name);
	/**
	 * 
	 * 函数功能:获取订单详情
	 * @author Searlas
	 * 时间2017年7月10日下午3:31:39
	 * @param order_id
	 * @return
	 */
	File getOrderDetail(String order_id);
	/**
	 * 
	 * 函数功能:获取日志列表
	 * @author Searlas
	 * 时间2017年7月10日下午8:30:41
	 * @param page
	 * @return
	 */
	List<Log> getLogList(String page);
	/**
	 * 
	 * 函数功能:获取待审核的订单列表
	 * @author Searlas
	 * 时间2017年7月10日下午8:43:09
	 * @param page
	 * @return
	 */
	String AddOrder(HttpServletRequest req);
	/**
	 * 
	 * 函数功能:批量删除日志
	 * @author Searlas
	 * 时间2017年7月12日上午9:26:56
	 * @param loglist
	 * @return
	 */
	boolean DeleteLogList(String loglist);
	/**
	 * 
	 * 函数功能:删除日志
	 * @author Searlas
	 * 时间2017年7月12日上午10:12:37
	 * @param log_id
	 * @return
	 */
	boolean DeleteLog(String log_id);
	/**
	 * 
	 * 函数功能:获取已经审核的订单
	 * @author Searlas
	 * 时间2017年7月12日上午10:22:05
	 * @param page
	 * @return
	 */
	List<File> getReviewEdOrderList(String page);
	int getOrderNumber(String order_name);
	/**
	 * 
	 * 函数功能:获取新订单的文件的id以存放文件
	 * @author Searlas
	 * 时间2017年7月14日下午7:35:23
	 * @return
	 */
	int getNewFileId();

	int getLogNumber();

	/**
	 * 
	 * 函数功能:获取订单所有历史文件
	 * @author Searlas
	 * 时间2017年7月15日下午5:20:08
	 * @param order_id
	 * @return
	 */
	List<File> getOrderHis(String order_id);
	/**
	 * 
	 * 函数功能:修改订单进度
	 * @author Searlas
	 * 时间2017年7月15日下午5:25:05
	 * @param order_id
	 * @param schedule
	 * @return
	 */
//	int UpdateOrder(String order_id, String schedule,String user_id);
	/**
	 * 
	 * 函数功能:提交新订单文件
	 * @author Searlas
	 * 时间2017年7月15日下午7:05:59
	 * @param file
	 * @return
	 */
	int SubmitFile(File file);
	/**
	 * 
	 * 函数功能:根据角色和编辑id获取订单列表
	 * @author Searlas
	 * 时间2017年7月19日上午10:52:10
	 * @param edit_id
	 * @param user_id
	 * @return
	 */
	List<File> getOrderListById(String edit_id);
	List<File> getSchedulList(String page);
	/**
	 * 
	 * 函数功能:锁定订单
	 * @author Searlas
	 * 时间2017年9月15日下午10:27:39
	 * @param order_id
	 * @return
	 */
	int LockOrder(String order_id);
	/**
	 * 
	 * 函数功能:解锁订单
	 * @author Searlas
	 * 时间2017年9月15日下午10:31:02
	 * @param order_id
	 * @return
	 */
	int UnLockOrder(String order_id);
	/**
	 * 
	 * 函数功能:根据文件id获取订单
	 * @author Searlas
	 * 时间2017年9月15日下午10:43:09
	 * @param file_id
	 * @return
	 */
	File GetOrderByFileId(int file_id);
	/**
	 * 
	 * 函数功能:上传修改的文件
	 * @author Searlas
	 * 时间2017年9月16日上午10:09:14
	 * @param file
	 * @return
	 */
	int UploadFile(File file);
	/**
	 * 
	 * 函数功能:撤回订单状态
	 * @author Searlas
	 * 时间2017年9月18日下午2:50:05
	 * @param order_id
	 * @param edit_time
	 * @param user_id
	 * @return
	 */
	int Withdraw(String order_id, String edit_time, String user_id);
	/**
	 * 
	 * 函数功能:判断一个订单是否已经锁定
	 * @author Searlas
	 * 时间2017年9月20日下午6:51:54
	 * @param order
	 * @return
	 */
	boolean IsLocked(String order_id);
	int getReviewNumber();

	
	
}
