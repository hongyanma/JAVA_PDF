package com.pdf.global;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateTools {

	/**
	 * 
	 *作者：ZhangHuanMing
	 *时间：2017年3月23日下午2:22:03
	 *函数名：getDateBetween
	 *功能：计算两个日期之间的天数
	 *参数：
	 *返回值：int
	 * @throws ParseException 
	 */
	public static int getDateBetween(String date1,String date2) throws ParseException{
		
		long to = 0;
		long from = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		if( date1.compareTo(date2) > 0 ){               //date1大于date2
			
			to = df.parse(date1).getTime();
		    from = df.parse(date2).getTime();
		}else if(date1.compareTo(date2) < 0){
			
			to = df.parse(date2).getTime();
		    from = df.parse(date1).getTime();
		}else{                                      //相等
			return 0;
		}
	    return (int) ((to - from) / (1000 * 60 * 60 * 24));
	}
	
	/**
	 *     
	 *作者：ZhangHuanMing
	 *时间：2017年3月23日下午3:13:14
	 *函数名：getDatesBetween2Date
	 *功能：获得两个日期之间所有的日期
	 *参数：
	 *返回值：List<String>
	 */
    public static List<String> getDatesBetween2Date(String start_date, String end_date) {
    
    	List<String> result = new ArrayList<String>();
    
    	try {
    
    		start_date = start_date.trim();
    		end_date = end_date.trim();
    
		    if ( start_date.isEmpty() || end_date.isEmpty() ) {
		    
		    	return result;
		    }
		    
		    String tempDate = null;
		    
		    if( start_date.compareTo(end_date)>=0){
		    	
		    	tempDate = end_date;
		    	result.add(end_date);
		    }else {
		    	
		    	tempDate = start_date;
		    	result.add(start_date);
		    }
		    
		    for (int i = 0; i < getDateBetween(start_date,end_date); i++) {
				
		    	result.add(turnDate(tempDate,1+i));
			}
		 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	  return result;
    
    }
	    
    /**
     * 
     *作者：ZhangHuanMing
     *时间：2017年3月23日下午2:49:44
     *函数名：turnDate
     *功能：日期字符串加上指定的天数
     *参数：
     *返回值：String
     */
    public static String turnDate(String showDate, int interDay) {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
    	Date date = null; 
    	Date newDate = null;
	    try {
	    	date = sdf.parse(showDate); 
	    	newDate = addDate(date,interDay);
	    }catch (ParseException e) { 
	    	e.printStackTrace(); 
	    }
	    
	    return sdf.format(newDate);
	    
    }
	    
    /**
     * 
     *作者：ZhangHuanMing
     *时间：2017年3月23日下午2:58:03
     *函数名：addDate
     *功能：将日期加上天数
     *参数：
     *返回值：Date
     */
	public static Date addDate(Date date,int day){
		
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		time+=day; // 相加得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}    
	

	/**
	 * 
	 *作者：ZhangHuanMing
	 *时间：2017年4月10日下午8:55:18
	 *函数名：dateFormat
	 *功能：日期格式转化
	 *参数：
	 *返回值：String
	 */
	public static String dateFormat(String oldDate){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try{
			
			date = sdf.parse(oldDate);
		}catch(ParseException e){
			
			e.printStackTrace();
		}
		
		return sdf.format(date);
	}
	
	
	
	public static void main(String[] args) throws ParseException {
		
		String date1 = "2017-12-28";
		String date2 = "2018-01-03";
		System.out.println(getDateBetween(date1,date2));
		for (int i = 0; i < getDatesBetween2Date(date2,date1).size(); i++) {
			
			System.out.println( getDatesBetween2Date(date2,date1).get(i));
		}
		
		System.out.println(dateFormat("2017-4-3"));
	}
}
