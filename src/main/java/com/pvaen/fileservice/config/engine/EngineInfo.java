package com.pvaen.fileservice.config.engine;

import lombok.Getter;
import lombok.Setter;

/**
 * 引擎的基础属性类</br>
 * 可继承 扩充属性
 * @author pvaen
 *
 */
@Setter
@Getter
public class EngineInfo {
	
	private Integer id;
	
	private String portol;
	
	private String url;
	
	private String port;
	
	private String account;
	
	private String passWord;

	@Override
	public String toString() {
		return "EngineInfo [id=" + id + ", portol=" + portol + ", url=" + url + ", port=" + port + ", account="
				+ account + ", passWord=" + passWord + "]";
	}
	
}
