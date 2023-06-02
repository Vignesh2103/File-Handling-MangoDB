package com.kgisl.bsonfileupload.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kgisl.bsonfileupload.Model.File;
// import com.kgisl.bsonfileupload.Model.FileDownloadResult;

public interface FileService {
    void uploadFileToUser(String userId, MultipartFile file);

    // FileDownloadResult downloadFileByUserId(String userId);
    List<File> find(String userId);

}
