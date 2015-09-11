package com.sunonerim.framework.core;

import java.io.IOException;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sunonerim.framework.command.CommandItem;
import com.sunonerim.framework.command.ACommand;
//import com.sunonerim.framework.command.io.CommandItemLoader;
import com.sunonerim.framework.db.CoreDao;



public class CommandExecutor {
	
//	public void doBeforeExecute( String command_id,  Parameter	param ) throws Exception;
//	public void doAfterExecute(  String command_id,  Parameter	param,  ResultMap	result_map) throws Exception;

	ACommandHandler	CommandHandler		= null;
	
	// 사전에 정의된 Command
	final static public	String	DummyComm = "/dummy";	
	final static public	String	ReloadComm = "/reload";
	
	// Command related Command
	final static public	String	ListCommandComm 	= "/listCommand";
	final static public	String	CreateCommandComm 	= "/createCommand";
	final static public	String	UpdateCommandComm 	= "/updateCommand";
	final static public	String	DeleteCommandComm 	= "/deleteCommand";
	
	
	static 				HashMap<String, CommandItem> Commands  = null ;	// CommandItem을 저장하는 맵 데이터구조
	
	public CommandExecutor( ){
		
		createCommandHandler();
		loadCommand();
	}
	
	static public Map<String, CommandItem>	getCoomand(){
		return Commands;
	}
	
	static public void addCommand(String command, String command_class) {
		addCommand( command,  command_class, null, CommandItem.ciUDF);
	}

	static public void addCommand(String command, String command_class, String writer_class, String kind) {
		if( Commands == null ) return ;
		
		CommandItem cmd_item = new CommandItem(command, command_class, writer_class, kind);		
		Commands.put(command, cmd_item);
	}

	public	void	createCommandHandler(){
		String comm_handler_name = ConfigIO.getValue(  ConfigIO.cfCommandHanlder );
		if ( comm_handler_name == null ) return;
		
		try {
			Class	comm_handler_cls = Class.forName( comm_handler_name );
			CommandHandler = (ACommandHandler)comm_handler_cls.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void loadCommand() {
		Commands =  new HashMap<String, CommandItem>();				
		try {		
			setupSystemCommand();			 		
		} catch (Exception e) {
			e.printStackTrace();
		}
		loadCommandFromDB();
	}

	public	void	setupSystemCommand() {
		addCommand( DummyComm,  "com.sunonerim.framework.command.DummyCommand"	);	 
        addCommand( ReloadComm, "com.sunonerim.framework.command.ReloadCommand" );	
        
        addCommand( ListCommandComm, "com.sunonerim.framework.command.ListCommand" );
        addCommand( CreateCommandComm, "com.sunonerim.framework.command.CreateCommand" );
        addCommand( UpdateCommandComm, "com.sunonerim.framework.command.UpdateCommand" );
        addCommand( DeleteCommandComm, "com.sunonerim.framework.command.DeleteCommand" );
                
	}
	
	/**
	 * 신규로 Command를 로드한다. CoreDao 를 이용한다. 
	 */
	public	void	loadCommandFromDB() {		
		CoreDao	core_dao = CoreDao.getInstance( ConfigIO.getValue(ConfigIO.cfCoreDao) );
		
		try {
			List<CommandItem>cmd_list = core_dao.selectAllCommand();
			for ( CommandItem cmd_item : cmd_list){
				Commands.put(cmd_item.getCommandCD(), cmd_item );
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param cmd_id
	 * @return
	 * @throws CommandException
	 */
	public ACommand lookupCommand(String cmd_id) throws Exception {
		 System.out.println("in lookupCommand>" + cmd_id);
		if (cmd_id == null) {
			System.out.println("cmd_id NULL");
			throw new Exception("command Identifier must be requierd!");
		}

		if (Commands == null) {
			System.out.println("commands NULL");
			throw new Exception("commands is not initiated!");
		}

		if (!Commands.containsKey(cmd_id)) {
			throw new Exception("The given command ["+ cmd_id +"] doesn't exist in the command pool.");
		}

		// System.out.println("before Command find OK.");
		CommandItem cmd_item = (CommandItem) Commands.get(cmd_id);
		ACommand command = cmd_item.getCommandInstance();
		return command;
	}

	
	public	String	getWriterClassOfCommand( String cmd_id) {
		CommandItem cmd_item = (CommandItem) Commands.get(cmd_id);
		if( cmd_item == null ) return null;
		return cmd_item.getWriterClass();
	}
	
	
	/*
	public	static Connection	createConnection() {
		Connection connection = null;
		try {
			Class.forName( CommandExecutor.Driver );
			try {
				connection = DriverManager.getConnection(CommandExecutor.Url, CommandExecutor.ID, CommandExecutor.Password);
				System.out.println("JDBC URL : " + CommandExecutor.Url);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return connection;
	}	
	*/

	private ResultMap executeReal(String cmd_id, Parameter parameter) throws Exception {
		
		System.out.println("before  execute " + cmd_id);

			ACommand cmd = lookupCommand(cmd_id);
			if (cmd == null) throw new Exception("No Command exist");
			
			cmd.getResultMap().setParameter(parameter);		
			cmd.execute(parameter);
			return cmd.getResultMap();
		
	}

    private	ResultMap	checkAndDoReload(String cmd_id ){
	    if( cmd_id.equals(ReloadComm )) {
			ResultMap	result_map = new ResultMap(ReloadComm);
			result_map.addResult( "status", "All Commands have been reloaded");
			return result_map;
		} else return null;
    }
    
	/**
	 * 
	 * @param cmd_id
	 * @param parameter
	 * @param out
	 *           
	 * @return
	 * @throws Exception
	 * @throws CommandException
	 */
	public ResultMap execute(String cmd_id, Parameter parameter) throws Exception {
		
			if( CommandHandler != null )  CommandHandler.doBeforeExecute( cmd_id, parameter );
			ResultMap	result_map = checkAndDoReload(cmd_id);
			if( result_map == null ) result_map = executeReal    ( cmd_id, parameter );
			if( CommandHandler != null )  CommandHandler.doAfterExecute( cmd_id, parameter, result_map);
    		    		
			return result_map;
		
	}
}	
