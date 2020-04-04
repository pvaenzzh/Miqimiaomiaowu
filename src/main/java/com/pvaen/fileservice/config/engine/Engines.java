package com.pvaen.fileservice.config.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.pvaen.fileservice.Enum.EngineEnum;
import com.pvaen.fileservice.engine.CommonEngine;
import com.pvaen.fileservice.engine.MinioEngine;

/**
 * 将配置文件的引擎配置类加载到map中保存, 可注入使用</br>
 * @author pvaen
 *
 */
@Configuration
public class Engines {
	
	@Autowired
	private EngineInfoConfig engineInfoConfig;
	
	private Map<EngineEnum, Class> engineList = new HashMap<EngineEnum, Class>();

	public Engines() {
		init();
	}
	
	public void init() {
		engineList.put(EngineEnum.s3, MinioEngine.class);
	}
	
	
	public CommonEngine getEngine(EngineEnum enu) {
		Class<?> class1 = engineList.get(enu);
		try {
			CommonEngine newInstance =(CommonEngine) class1.newInstance();
			List<EngineInfo> collect = engineInfoConfig.getEngineInfos().stream().filter(temp->temp.getId().equals(enu.getId())).collect(Collectors.toList());
			EngineInfo engineInfo = collect.get(0);
			newInstance.init(engineInfo);
			return newInstance;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
