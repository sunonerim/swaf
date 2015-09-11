package com.sunonerim.framework.db;

import java.util.List;
import com.sunonerim.framework.command.CommandItem;
import com.sunonerim.framework.db.CoreDao;

public class MongodbCoreDao extends CoreDao{

	@Override
	public	boolean createCommandItem(CommandItem _cmd_item) throws Exception {

		return false;
	}

	@Override
	public	boolean updateCommandItem(CommandItem _cmd_item) throws Exception {

		return false;
	}

	@Override
	public	boolean deleteCommandItem(CommandItem _cmd_item) throws Exception {

		return false;
	}

	@Override
	public List< CommandItem> selectCommandItem(int _page, int _page_size)
			throws Exception {
		return null;
	}

}
