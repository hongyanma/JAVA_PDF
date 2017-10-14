package com.pdf.serviceImpl.andriod;

import java.util.List;

import com.pdf.dao.andriod.OrderDao_andriod;
import com.pdf.entity.File;
import com.pdf.factory.DaoFactory;
import com.pdf.service.andriod.OrderService_andriod;

public class OrderServiceImpl_andriod implements OrderService_andriod{

	private static OrderServiceImpl_andriod instance_andriod = new OrderServiceImpl_andriod();
	private  OrderDao_andriod daoInstance_andriod = (OrderDao_andriod) DaoFactory.getInstance().getBean(OrderDao_andriod.class);
	
	public static OrderServiceImpl_andriod getInstance() {
		return instance_andriod;
	}

	@Override
	public File search( String order_name) {
		 
		return daoInstance_andriod.search(order_name);
	}

	@Override
	public File download(String order_id) {
		return daoInstance_andriod.download(order_id);
	}

	@Override
	public List<File> getOrderHis(String order_id) {
		// TODO Auto-generated method stub
		return daoInstance_andriod.getOrderHis(order_id);
	}


}
