package com.pvaen.fileservice.model.result;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommonResultModel {
	
	private Integer code;
	
	private String msg;
	
	private Object data;
	
	public static final int SUCCESS = 1;
	
	public static final int ERROR = 0;

	private CommonResultModel(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public static CommonResultModel success(String msg, Object data) {
		return new CommonResultModel(SUCCESS, msg, data);
	}
	
	public static CommonResultModel success(Object data) {
		return new CommonResultModel(SUCCESS, "", data);
	}
	
	public static CommonResultModel error(String msg, Object data) {
		return new CommonResultModel(ERROR, msg, data);
	}
	
	public static CommonResultModel error(String msg) {
		return new CommonResultModel(ERROR, msg, null);
	}

}
