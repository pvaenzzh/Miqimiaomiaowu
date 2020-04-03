package com.pvaen.fileservice.engine;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.xmlpull.v1.XmlPullParserException;

import com.pvaen.fileservice.config.EngineInfo;
import com.pvaen.fileservice.excption.MinioException;
import com.pvaen.fileservice.model.po.TFileInfo;
import com.pvaen.fileservice.model.result.EngineDownloadResult;
import com.pvaen.fileservice.model.result.EngineUploadResult;
import com.pvaen.fileservice.model.vo.EngineUploadVO;

import io.minio.MinioClient;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidArgumentException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.NoResponseException;
import io.minio.errors.RegionConflictException;

public class MinioEngine extends CommonEngine{

	private static final String BUCKETNAME = "pvaen01";
	
	private EngineInfo config;
	
	private MinioClient client;
	
	public MinioEngine() {
	}
	
	@Override
	public void init(EngineInfo engineInfo) {
		this.config = engineInfo;
		try {
			this.client = getMinioClient();
		} catch (InvalidEndpointException | InvalidPortException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "deprecation"})
	@Override
	public EngineUploadResult upload(EngineUploadVO vo) throws MinioException{
		EngineUploadResult res = new EngineUploadResult();
		Resource file = vo.getFile();
		try (InputStream inputStream = file.getInputStream();){
			String bucketName = BUCKETNAME;
			String objectName = vo.getRemotePath();
			createBucket(BUCKETNAME);
			client.putObject(bucketName, objectName, inputStream, MediaType.APPLICATION_OCTET_STREAM_VALUE);
			res.setSuccess(true);
			res.setRemotePath(objectName);
		} catch (InvalidKeyException | InvalidBucketNameException | RegionConflictException | NoSuchAlgorithmException
				| InsufficientDataException | NoResponseException | ErrorResponseException | InternalException
				| InvalidResponseException | IOException | XmlPullParserException | InvalidArgumentException e) {
			e.printStackTrace();
			throw new MinioException("minio upload error");
		}
		return res;
	}
	
	private void createBucket(String bucketName) throws InvalidKeyException, InvalidBucketNameException, RegionConflictException, NoSuchAlgorithmException, InsufficientDataException, NoResponseException, ErrorResponseException, InternalException, InvalidResponseException, IOException, XmlPullParserException{
		if(!client.bucketExists(bucketName)) {
			client.makeBucket(bucketName);
		}
	}
	
	private MinioClient getMinioClient() throws InvalidEndpointException, InvalidPortException {
		String url = config.getPortol() + "://" + config.getUrl() + ":" + config.getPort();
		MinioClient minioClient = new MinioClient(url, config.getAccount(), config.getPassWord());
		return minioClient;
	}

	@Override
	public EngineDownloadResult downloadFile(TFileInfo tFileInfo) {
		EngineDownloadResult engineDownloadResult = new EngineDownloadResult();
		InputStream object= null;
		try {
			object = client.getObject(BUCKETNAME, tFileInfo.getMetaPath());
			engineDownloadResult.setDownloadFile(object);
		} catch (InvalidKeyException | InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException
				| NoResponseException | ErrorResponseException | InternalException | InvalidArgumentException
				| InvalidResponseException | IOException | XmlPullParserException e) {
			e.printStackTrace();
		}
		return engineDownloadResult;
	}

}
