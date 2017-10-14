package com.pdf.junitTest;

import org.junit.Test;

import com.pdf.entity.File;
//import com.pdf.service.OrderService;
import com.pdf.service.andriod.OrderService_andriod;
//import com.pdf.serviceImpl.OrderServiceImpl;
import com.pdf.serviceImpl.andriod.OrderServiceImpl_andriod;


public class orderTest {
	private static OrderService_andriod orderInstance = OrderServiceImpl_andriod.getInstance();
//	private static OrderService  orderInstance1 = OrderServiceImpl.getInstance();

//	@Test
//	public void testnum(){
//		int id = orderInstance1.getNewFileId();
//		System.out.println(id);
//	}
//	
	@Test
	public void test() {
		String order_id = "4";
		File result = orderInstance.search(order_id);
		System.out.println(result);
	}
//	@Test
//	public void test1() {
//		int user_id = 3;
//		String order_id="33";
//		List<File> result = orderInstance.search(user_id,order_id);
//		System.out.println(result);
//	}
	
//	@Test
//	public void deleteorderList() {
//		String orderList="2,3";
//		boolean result = orderInstance1.DeleteOrderList(orderList);
//		System.out.println(result);
//	}
//	
//	@Test 
//	public void ReviewOrder() {
//		String order_id  = "2";
//		int schedule = Utils.getEditorNum()+2;
//		boolean result = orderInstance1.ReviewOrder(order_id, schedule);
//		System.out.println(result);
//	}
//	
//	@Test
//	public void OrderHis() {
//		String order_id  = "2";
//		System.out.println(orderInstance1.getOrderHis(order_id));
//	}
//	
//	@Test
//	public void updateOrder() {
//		String order_id = "2";
//		String schedule = "4";
//		System.out.println(orderInstance1.UpdateOrder(order_id, schedule));
//	}
	
//	@Test
//	public void getOrderListById() {
//		String role_id = "2";
//		String edit_id = "1";
//		System.out.println(orderInstance1.getOrderListById(edit_id, role_id));
//	}
	
}
