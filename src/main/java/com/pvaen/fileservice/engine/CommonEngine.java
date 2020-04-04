package com.pvaen.fileservice.engine;

import com.pvaen.fileservice.config.engine.EngineInfo;
import com.pvaen.fileservice.excption.MinioException;
import com.pvaen.fileservice.model.po.TFileInfo;
import com.pvaen.fileservice.model.result.EngineDownloadResult;
import com.pvaen.fileservice.model.result.EngineUploadResult;
import com.pvaen.fileservice.model.vo.EngineUploadVO;

public abstract class CommonEngine {
	
	public abstract EngineUploadResult upload(EngineUploadVO vo) throws MinioException;
	
	public abstract EngineDownloadResult downloadFile(TFileInfo tFileInfo);
	
	public abstract void init(EngineInfo engineInfo);

}
