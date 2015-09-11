<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import ="java.util.Map" %>
<%@ page import ="java.util.Set" %>
<%
	Map<String, String[]>		param_map = request.getParameterMap();

	Set<String>	keys = param_map.keySet();
	
	for ( String key : keys){
		response.getWriter().write(key + "\n");
		String[]	vals = param_map.get(key) ; //response.getWriter().write( param_map.get(key).toString() + "\n" );
		
		for ( String val : vals){
			response.getWriter().write("-----val:" + val + "\n");
		}
	}
%>