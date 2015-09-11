package com.sunonerim.sample;

import java.io.IOException;

import com.sunonerim.framework.core.ACommandHandler;
import com.sunonerim.framework.core.CommandExecutor;
import com.sunonerim.framework.core.ConfigIO;
import com.sunonerim.framework.core.Parameter;
import com.sunonerim.framework.core.ResultMap;
import com.sunonerim.framework.io.JsonWriter;


public class CommandHandlerSample extends ACommandHandler {
	@Override
	public void doBeforeExecute(String command_id, Parameter param)	throws Exception {
		System.out.println("<<<<<<<<<<<<<<<<< doBeforeExecute");

	}

	@Override
	public void doAfterExecute(String command_id, Parameter param,	ResultMap result_map) throws Exception {
		System.out.println("doAfterExecute>>>>>>>>>>>>>>>>>>>>");
	}
	
}
