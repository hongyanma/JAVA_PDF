package com.pdf.service.andriod;

import java.util.List;

import com.pdf.entity.File;

public interface OrderService_andriod{

	File search(String order_name);

	File download(String order_id);

	List<File> getOrderHis(String order_id);

}
