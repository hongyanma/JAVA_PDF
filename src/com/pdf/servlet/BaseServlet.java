package com.pdf.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public BaseServlet() {
        super();
    }

    /**
     * 通过反射来实现动态方法的调用
     */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		System.out.println(method);
		if(method == null || method.trim().isEmpty()) {
			
	        throw new RuntimeException("您没有传递method参数！无法确定您想要调用的方法！");
	    }

		try {
			Method func = this.getClass().getMethod(method,HttpServletRequest.class, HttpServletResponse.class);
			func.invoke(this, request, response);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}
