package com.sunonerim.framework.command;

import com.sunonerim.framework.core.Parameter;


public class DummyCommand extends ACommand {

	@Override
	public void execute(Parameter parameter) throws Exception {
		// TODO Auto-generated method stub	
		addResult("Message", "Hello, this is from the Sunonerim Web Application Framework");				
	}
}
