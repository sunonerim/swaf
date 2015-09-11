package com.sunonerim.framework.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunonerim.framework.core.CommandExecutor;
import com.sunonerim.framework.core.ConfigIO;
import com.sunonerim.framework.core.Parameter;
import com.sunonerim.framework.core.ResultMap;
import com.sunonerim.framework.io.IOutputWriter;
import com.sunonerim.framework.io.JsonWriter;
import com.sunonerim.util.HttpUtil;


public class SwafServlet extends HttpServlet {
	private static final 	long 				serialVersionUID = 1L;
	private					CommandExecutor 	TheCommandExecutor = null;
	

 	public void init(ServletConfig config) throws ServletException {    	
        super.init(config);
            	
    	String conf_path = config.getServletContext().getRealPath("/WEB-INF/swaf.conf.xml");
   		ConfigIO.getConfig( conf_path );
   		
   		System.out.println( "init(ServletConfig config) >> " + conf_path);
   		
   		// 기존의 CommandHandler 를 로드한다. 
   		TheCommandExecutor = new CommandExecutor();            
    }
    
    
   	public void service(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException   {
   		
    	System.out.println("#############  [[[[[ start MainServlet.java ]]]]]] ######################");
    	
    	
		response.setCharacterEncoding("utf8");
        response.setContentType("application/json");
        
		String		cmd_id = request.getPathInfo();				
		ResultMap	result_map = null;

		// building Parameter but user information is nor real just for test.
	    Parameter	parameter = new Parameter(); 
	    parameter.setLoginUserID("sunone");	    
		HttpUtil.fillParameter(request, parameter );
		
		System.out.println("PARAMETER  >>");
		System.out.println( parameter.toString());
		
		try {
			result_map =  TheCommandExecutor.execute(cmd_id, parameter);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		IOutputWriter	writer = null;
		System.out.println( "result.getCommandID()--->" + result_map.getCommandID() );
		
//		if( result_map.getCommandID().equals("UserList")) 
//			writer = new BootstrapTableWriter();
//		else 
			writer = new JsonWriter();

		
		try {			
			writer.write2Output(result_map, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}    	
    }
}
