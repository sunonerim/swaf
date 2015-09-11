/**
 * 프레임워크의 주요 클래스 ( User, Group, Command) 를 Database, Persistence 에서 CRUD관련한 작업을 하는 
 * 객체를 로드한다. 
 */
package com.sunonerim.framework.db;

//import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sunonerim.framework.command.CommandItem;
import com.sunonerim.framework.core.CommandExecutor;
//import com.sunonerim.framework.entity.User;

public abstract class CoreDao {
	
	static	public	CoreDao	getInstance(String _core_dao_name){
		CoreDao	core_dao = null;
		
		try {
			Class	core_dao_cls = Class.forName( _core_dao_name );
			core_dao = (CoreDao)core_dao_cls.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return core_dao;
	}
	
	public	List<CommandItem> 	selectAllCommand() throws Exception{
		ArrayList< CommandItem> cmd_list = new ArrayList< CommandItem>();
		
		Map<String, CommandItem>	commands = CommandExecutor.getCoomand();
		
		 for (Map.Entry<String, CommandItem> entry : commands.entrySet())
			 cmd_list.add( entry.getValue());
		
		return cmd_list;
	}
	
	abstract	public	List<CommandItem> 	selectCommandItem(int _page, int _page_size) throws Exception;
	
	abstract	public	boolean 			createCommandItem(CommandItem _cmd_item) throws Exception;
	abstract	public	boolean 			updateCommandItem(CommandItem _cmd_item) throws Exception;
	abstract	public	boolean 			deleteCommandItem(CommandItem _cmd_item) throws Exception;
	
//	abstract	List<User> 						selectUser(int _page, int _page_size) throws Exception;
}
