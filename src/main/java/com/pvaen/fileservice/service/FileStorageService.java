package com.pvaen.fileservice.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pvaen.fileservice.Enum.EngineEnum;
import com.pvaen.fileservice.config.engine.Engines;
import com.pvaen.fileservice.engine.CommonEngine;
import com.pvaen.fileservice.model.po.TFileInfo;
import com.pvaen.fileservice.model.result.CommonResultModel;
import com.pvaen.fileservice.model.result.EngineDownloadResult;
import com.pvaen.fileservice.model.result.EngineUploadResult;
import com.pvaen.fileservice.model.vo.EngineUploadVO;
import com.pvaen.fileservice.utils.PathUtils;

@Service
public class FileStorageService {

	@Autowired
	private Engines engines;

	public EngineUploadResult singleUpload(EngineEnum engineEnum, MultipartFile file) {
		EngineUploadVO engineUploadVO = new EngineUploadVO();
		engineUploadVO.setFile(file.getResource());

		String nextPath = PathUtils.nextPath();
		engineUploadVO.setRemotePath(nextPath);
		try {
			EngineUploadResult upload = engines.getEngine(engineEnum).upload(engineUploadVO);
			return upload;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public EngineDownloadResult downloadFile(TFileInfo tFileInfo, HttpServletResponse response) {
		Integer storeType = tFileInfo.getStoreType();
		CommonEngine engine = engines.getEngine(EngineEnum.getEngineEnum(storeType));
		EngineDownloadResult downloadFile = engine.downloadFile(tFileInfo);
		return downloadFile;
	}

}
