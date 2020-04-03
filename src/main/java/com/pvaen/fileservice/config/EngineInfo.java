package com.pvaen.fileservice.config;

import lombok.Getter;
import lombok.Setter;

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
