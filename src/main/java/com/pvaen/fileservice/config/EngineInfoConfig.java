package com.pvaen.fileservice.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "pvaen")
public class EngineInfoConfig {
	
	private List<EngineInfo> engineInfos;

}
