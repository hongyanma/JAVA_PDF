package com.pdf.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	List<String> unUrlList;
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		
		arg1.setContentType("text/html;charset=utf-8");
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		String path = req.getServletPath();
//		System.out.println(unUrlList.size());
		if(unUrlList.contains(path)) {
			chain.doFilter(arg0,arg1);
		}else {
	//		System.out.println(unUrlList);
	//		System.out.println(111);
//			System.out.print(path+" ");
	//		System.out.println();
			HttpSession session = req.getSession();
//			System.out.println(session.getAttribute("user") );
			if (session == null || session.getAttribute("user") == null) {
				PrintWriter out = resp.getWriter();
//				out.write("<script charset=\"utf-8\">alert(\"please login\");</script>");
				out.print("<script>alert('µÇÂ½³¬Ê±£¬ÇëÖØÐÂµÇÂ¼')</script>");
				out.print("<script> window.history.go(-1);window.loaction.reload();</script>");
				out.flush();
				out.close();
			} else {
				chain.doFilter(arg0, arg1);
			}
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		String unUrl = fConfig.getInitParameter("unUrl");
		if (unUrl != null && !"".equals(unUrl)) {
			unUrlList = Arrays.asList(unUrl.split(","));
		}
		
	}
	

}
