package com.pvaen.fileservice.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.pvaen.fileservice.Enum.EngineEnum;
import com.pvaen.fileservice.mapper.TFileInfoRepository;
import com.pvaen.fileservice.model.po.TFileInfo;
import com.pvaen.fileservice.model.result.CommonResultModel;
import com.pvaen.fileservice.model.result.EngineDownloadResult;
import com.pvaen.fileservice.model.result.EngineUploadResult;
import com.pvaen.fileservice.model.result.FileServiceDownloadFileResult;
import com.pvaen.fileservice.utils.HashUtils;

@Service
public class FileService {
	
	@Autowired
	private FileStorageService storeService;
	
	@Autowired
	private TFileInfoRepository tFileInfoRepository;
	
	public CommonResultModel singleUpload(EngineEnum engineEnum, MultipartFile file) {
		EngineUploadResult singleUpload = storeService.singleUpload(engineEnum, file);
		TFileInfo tFileInfo = getTFileInfo(engineEnum, file, singleUpload);
		tFileInfoRepository.save(tFileInfo);
		return CommonResultModel.success(tFileInfo);
	}
	private TFileInfo getTFileInfo(EngineEnum engineEnum, MultipartFile file, EngineUploadResult singleUpload) {
		String fileCode = UUID.randomUUID().toString();
		String id = UUID.randomUUID().toString();
		TFileInfo tFileInfo = new TFileInfo();

		try (InputStream inputStream = file.getInputStream();){
			tFileInfo.setFileCode(fileCode);
			tFileInfo.setFileName(file.getOriginalFilename());
			tFileInfo.setHash(HashUtils.getSHA256(inputStream));
			tFileInfo.setHashM("sha256");
			tFileInfo.setId(id);
			tFileInfo.setMetaPath(singleUpload.getRemotePath());
			tFileInfo.setSize(file.getSize());
			tFileInfo.setStoreType(engineEnum.getId());
			tFileInfo.setCreateTime();
			tFileInfo.setCreateUser("pvaen");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tFileInfo;
	}
	
	public FileServiceDownloadFileResult downloadFile(String fileCode, HttpServletResponse response) {
		FileServiceDownloadFileResult fileServiceDownloadFileResult = new FileServiceDownloadFileResult();
//		List<TFileInfo> findAll = tFileInfoRepository.findAll();
		TFileInfo tFileInfo = new TFileInfo();
		tFileInfo.setFileCode(fileCode);
		Example<TFileInfo> of = Example.of(tFileInfo);
		Optional<TFileInfo> findOne = tFileInfoRepository.findOne(of);
		TFileInfo tFileInfo2 = findOne.get();
		EngineDownloadResult downloadFile = storeService.downloadFile(tFileInfo2, response);
		fileServiceDownloadFileResult.putFileServiceDownloadFileResult(tFileInfo2, downloadFile);
		return fileServiceDownloadFileResult;
	}

}
