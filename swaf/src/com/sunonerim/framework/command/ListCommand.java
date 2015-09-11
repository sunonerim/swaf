package com.sunonerim.framework.command;

import com.sunonerim.framework.core.CommandExecutor;
import com.sunonerim.framework.core.ConfigIO;
import com.sunonerim.framework.core.Parameter;
import com.sunonerim.framework.db.CoreDao;


public class ListCommand extends ACommand {

	@Override
	public void execute(Parameter parameter) throws Exception {
		CoreDao dao = CoreDao.getInstance ( ConfigIO.getValue(ConfigIO.cfCoreDao) );
		if( dao != null ) addResult("commands",  dao.selectAllCommand() );
	}
}
