package com.wsx.friends.es.search;

import java.util.List;
import java.util.Map;

import com.wsx.friends.core.model.OutputData;

/**
 * 
 * @author wangshuaixin
 *
 */
public class OutputDatas extends OutputData {

	private static final long serialVersionUID = -8990759960052491611L;
	
	private List<Map<String, Object>> datas;
	
	public OutputDatas() {
		super();
	}
	
	public OutputDatas(int code, int status) {
		super(code, status);
	}

	public List<Map<String, Object>> getDatas() {
		return datas;
	}

	public void setDatas(List<Map<String, Object>> datas) {
		this.datas = datas;
	}

}
