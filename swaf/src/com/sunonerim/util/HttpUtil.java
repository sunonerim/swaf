package com.sunonerim.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import com.sunonerim.framework.core.Parameter;


public class HttpUtil {

    public	static void	fillParameter(HttpServletRequest req, Parameter param) {
    	
    
//	새로운방식
//    	String qry_str = req.getQueryString();
//    	
//    	String decoded = null;
//		try {
//			decoded = URLDecoder.decode(qry_str, "UTF-8");
//		} catch (UnsupportedEncodingException e) {		
//			e.printStackTrace();
//		}
//    	
////		decoded = "{'name':'MORGAN 시가렛 팬츠 3종','image':'http://image.gsshop.com/image/15/42/15429251_1424163596426.jpg','smallImage':'http://image.gsshop.com/image/15/42/15429251_H1.jpg'}"; 
////		decoded = "{'name':'MORGAN 시가렛 팬츠 3종'}";
//		System.out.println(decoded);
//    
//    	
//    	
//    	HashMap<String, Object> map = new HashMap<String, Object>();
//    	Gson gson=new Gson(); 
//    	map = gson.fromJson( decoded, map.getClass());
//    	param.setParameterMap(map);
    	
    	
    	
		// 이전의 방식.    	
    	Enumeration<String> pnames = req.getParameterNames();
    	while (pnames.hasMoreElements()) {    		
    	    String pname 	= (String)pnames.nextElement();    	    
    	    String[] p_value 	= null;
			
    	    System.out.printf("paramter  name:%s\n", pname );
    	    p_value 		= req.getParameterValues(pname);
    	    
    	    if( p_value.length == 1 ){
    	    	System.out.println("value[싱글] " + p_value[0].toString() );
    	    	param.put(  pname.replace("[", ".").replace("]", "") , p_value[0]);
    	    } else {    	    	
    	    	for ( String val : p_value )
    	    		System.out.println("value[배열] " + val );
    	    	param.put(pname, p_value);	
    	    }
    	}    	
    }

}

