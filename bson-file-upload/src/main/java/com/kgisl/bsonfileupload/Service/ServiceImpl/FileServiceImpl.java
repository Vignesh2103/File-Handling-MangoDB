package com.kgisl.bsonfileupload.Service.ServiceImpl;

import java.io.IOException;
import java.util.List;

// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.kgisl.bsonfileupload.Service.FileService;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.kgisl.bsonfileupload.Model.File;
// import com.kgisl.bsonfileupload.Model.FileDownloadResult;
// import com.kgisl.bsonfileupload.Repository.FileRepository;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    // @Autowired
    // private FileRepository fileRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void uploadFileToUser(String userId, MultipartFile file) {

        try {
            File uploadfile = new File();
            uploadfile.setUserId(userId);
            uploadfile.setFileName(file.getOriginalFilename());
            uploadfile.setFileData(file.getBytes());
            uploadfile.setFileSize(file.getSize());
            mongoTemplate.save(uploadfile);
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // @Override
    // public FileDownloadResult downloadFileByUserId(String userId) {

    // Optional<File> Optionalfile = fileRepository.findByUserId(userId);
    // File file = Optionalfile.orElse(null);
    // return new FileDownloadResult(file);
    // }

    // @Override
    // public FileDownloadResult downloadFileByUserId(String userId) {
    // Optional<File> optionalFile = fileRepository.findByUserId(userId);
    // File file = optionalFile.orElse(null);
    // return new FileDownloadResult(file);
    // }

    @Override
    public List<File> find(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        List<File> files = mongoOperations.find(query, File.class);

        return files;
    }

}
