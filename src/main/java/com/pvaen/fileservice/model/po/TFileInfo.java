package com.pvaen.fileservice.model.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.pvaen.fileservice.utils.TimeUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TFileInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	private String fileCode;
	
	private String hash;
	
	private String hashM;
	
	private Long size;
	
	private String fileName;
	
	private Integer storeType;

	private String metaPath;
	
	private Date createTime;
	
	private String createUser;
	
	public void setCreateTime() {
		this.createTime = TimeUtils.getCurrentDate();
	}
	
}
