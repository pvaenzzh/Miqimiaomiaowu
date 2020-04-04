package com.pvaen.fileservice.config.engine;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * 加载配置文件入口
 * @author pvaen
 *
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "pvaen")
public class EngineInfoConfig {

    private List<EngineInfo> engineInfos;

}
