package com.pvaen.fileservice.Enum;

import lombok.Getter;

@Getter
public enum EngineEnum {
	
	s3(1, "s3");
	
	private int id;
	
	private String name;

	private EngineEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static EngineEnum getEngineEnum(Integer id) {
		EngineEnum[] values = EngineEnum.values();
		for (EngineEnum temp : values) {
			if(temp.getId() == id) {
				return temp;
			}
		}
		return null;
	}
	
}
