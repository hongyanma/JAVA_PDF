package com.pdf.factory;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class DaoFactory {

	private static DaoFactory instance = new DaoFactory();
	private Document document = null;
	private Map<String, Object> beanMap = new HashMap<String, Object>();  
	
	private DaoFactory(){
		
		try{
			document = new SAXReader().read(Thread.currentThread()  
                      .getContextClassLoader().getResourceAsStream(  
                            "DaoInterfaceFactory.xml"));  
		}catch (DocumentException e){
			
	        e.printStackTrace();  
	    }  
	}
	
	public static DaoFactory getInstance() { 
		
        return instance;  
    }
	
	/**
	 * 
	 *作者：ZhangHuanMing
	 *时间：2017年3月8日下午6:59:28
	 *函数名：getBean
	 *功能：根据id获取class
	 *参数：
	 *返回值：Object
	 */
    @SuppressWarnings("rawtypes")
	public synchronized Object getBean(Class c) {  
    	
        Object bean = null;  
        bean = beanMap.get(c.getName());                      //如果map里有这个class则直接返回，不用再去从xml文件里获取 
        
        if (bean == null) {                               //在map中没有找到这个class,则去xml中搜索
            try {  
            	 Element rootElement = document.getRootElement();
            	 String className = "";
            	 Node node = null;
            	 
            	 for ( int i = 0, size = rootElement.nodeCount(); i < size; i++ ) {
                     node = rootElement.node(i);
                     if(node instanceof  Element){
                         Element e = (Element) node;
                         
                         if(e.attribute("id").getText().equals(c.getName())){
                        	 className = e.attributeValue("class");
                        	 break;
                         }
                     }
                 }
            	
                bean = Class.forName(className).newInstance();  
                beanMap.put(c.getName(), bean);  
            } catch (InstantiationException e1) {  
                e1.printStackTrace();  
            } catch (IllegalAccessException e2) {  
                e2.printStackTrace();  
            } catch (ClassNotFoundException e3) {  
                e3.printStackTrace();  
            }  
        }  
        return bean;  
    } 
}
