package com.pdf.dao.andriod;

import java.util.List;

import com.pdf.entity.File;

public interface OrderDao_andriod {

	File search( String order_name);

	File download(String order_id);

	List<File> getOrderHis(String order_id);

}
