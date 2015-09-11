package com.sunonerim.sample;

import com.sunonerim.framework.command.ACommand;
import com.sunonerim.framework.core.Parameter;

import com.sunonerim.framework.entity.User;

public class SampleCommand extends ACommand {

	@Override
	public void execute(Parameter parameter) throws Exception {
		addResult("Hello", "World");
		addResult("List", new String[] {"Font", "Line", "Shape"});
		addResult("User", new User("sunny", "임성원"));
	}

}
