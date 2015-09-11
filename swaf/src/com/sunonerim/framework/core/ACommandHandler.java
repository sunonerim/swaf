package com.sunonerim.framework.core;

abstract	public class ACommandHandler {
	abstract	public void doBeforeExecute( String command_id,  Parameter	param ) throws Exception;
	abstract	public void doAfterExecute(  String command_id,  Parameter	param,  ResultMap	result_map) throws Exception;

}
