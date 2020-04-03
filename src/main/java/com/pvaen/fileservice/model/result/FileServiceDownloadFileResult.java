package com.pvaen.fileservice.model.result;

import java.util.HashMap;
import java.util.Map;

import com.pvaen.fileservice.model.po.TFileInfo;

public class FileServiceDownloadFileResult {

	private Map<TFileInfo, EngineDownloadResult> fileServiceDownloadFileResult = new HashMap<TFileInfo, EngineDownloadResult>();

	public Map<TFileInfo, EngineDownloadResult> getFileServiceDownloadFileResult() {
		return fileServiceDownloadFileResult;
	}

	public EngineDownloadResult putFileServiceDownloadFileResult(TFileInfo tFileInfo, EngineDownloadResult engineDownloadResult) {
		EngineDownloadResult put = fileServiceDownloadFileResult.put(tFileInfo, engineDownloadResult);
		return put;
	}
	
}

