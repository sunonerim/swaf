package com.sunonerim.framework.io;


import java.io.IOException;
import java.io.OutputStream;

import com.sunonerim.framework.core.ResultMap;


public interface IOutputWriter {
	public void write2Output(ResultMap result_map,  OutputStream out) throws IOException;
}
