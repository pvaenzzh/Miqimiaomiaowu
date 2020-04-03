package com.pvaen.fileservice.rest;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.LogFactoryService;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pvaen.fileservice.Enum.EngineEnum;
import com.pvaen.fileservice.model.po.TFileInfo;
import com.pvaen.fileservice.model.result.CommonResultModel;
import com.pvaen.fileservice.model.result.EngineDownloadResult;
import com.pvaen.fileservice.model.result.FileServiceDownloadFileResult;
import com.pvaen.fileservice.service.FileService;

@RestController("/v1/file")
public class FileUploadController {

    @Autowired
    private FileService service;

    @PostMapping(value = "/single-upload/{engine}", consumes = "multipart/form-data")
    public CommonResultModel singleUpload(@PathVariable("engine") EngineEnum engineEnum, @RequestBody MultipartFile file) {
        CommonResultModel res = null;
        try {
            out(file);
            res = service.singleUpload(engineEnum, file);
        } catch (Exception e) {
            res = CommonResultModel.error(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    private void out(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
    }

    @GetMapping("/download-file/{filecode}")
    public void downloadFile(@PathVariable("filecode") String fileCode, HttpServletResponse response) {
        FileServiceDownloadFileResult downloadFile = service.downloadFile(fileCode, response);
        Map<TFileInfo, EngineDownloadResult> fileServiceDownloadFileResult = downloadFile.getFileServiceDownloadFileResult();
        byte[] buffer = new byte[1024 * 64];
        for (TFileInfo temp : fileServiceDownloadFileResult.keySet()) {
            EngineDownloadResult engineDownloadResult = fileServiceDownloadFileResult.get(temp);
            try (InputStream downloadFile2 = engineDownloadResult.getDownloadFile(); OutputStream toClient = new BufferedOutputStream(response.getOutputStream());) {

                String filename = temp.getFileName();
                String encode = URLEncoder.encode(filename, "utf-8");
                encode = encode.replaceAll("\\+", "%20");
                response.setCharacterEncoding("utf-8");
                response.addHeader("Content-Disposition", "attachment;filename=" + filename);
                response.addHeader("Content-Length", "" + temp.getSize());

                response.setContentType(MediaType.MULTIPART_FORM_DATA.toString());
                int read = 0;
                while ((read = downloadFile2.read(buffer)) != -1) {
                    toClient.write(buffer, 0, read);
                    toClient.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
