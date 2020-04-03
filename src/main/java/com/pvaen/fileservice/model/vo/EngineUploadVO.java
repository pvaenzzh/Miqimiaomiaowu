package com.pvaen.fileservice.model.vo;

import java.io.InputStream;

import org.springframework.core.io.Resource;

import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
public class EngineUploadVO {
	
	private String localPath;
	
	private Resource file;
	
	private InputStream in;
	
	private String remotePath;
	
	private String userAccount;
	
}
