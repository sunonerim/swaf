package com.sunonerim.framework.command;


public class CommandItem {

	/**
	 * Command
	 * 
	 * @param parameter_map
	 * @return XML
	 * @throws CommandException
	 */
	private String CommandCD;
	private String ClassName;	
	private	String WriterClass;
	private	String Kind;			// 시스템 기본 커맨드인지, 유저 생성 맨드 인지를 확인 한다. 

	final static public String	ciSystem	= "system";
	final static public String	ciUDF 		= "udc";		// user defined command
	
	public CommandItem(String cmd_cd, String class_name) {
		super();
		CommandCD 	= cmd_cd;
		ClassName 	= class_name;
		WriterClass	= null;
		Kind		= ciUDF;
	}
			
	public CommandItem(String cmd_cd, String class_name, String writer_class, String _kind) {
		super();
		CommandCD 	= cmd_cd;
		ClassName 	= class_name;
		WriterClass	= writer_class;
		Kind		= _kind;
	}

	public String getCommandCD() {
		return CommandCD;
	}

	public void setCommandCD(String commandCD) {
		CommandCD = commandCD;
	}

	public String getKind() {
		return Kind;
	}

	public void setKind(String kind) {
		Kind = kind;
	}

	public ACommand getCommandInstance() throws Exception {
		Class cmd_cls = null;
		try {
			cmd_cls = Class.forName(ClassName);			
			ACommand cmd = (ACommand) cmd_cls.newInstance();
			return cmd;
		} catch (Exception e) {
			System.out.println("NO command class exist --" + ClassName);
			throw new Exception(ClassName + " Not Exist Coammnd ?");
		}
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return ClassName;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		ClassName = className;
	}

	public String getWriterClass() {
		return WriterClass;
	}



	public void setWriterClass(String writerClass) {
		WriterClass = writerClass;
	}

}
