package com.kgisl.bsonfileupload.Controller;

import java.io.IOException;
// import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.kgisl.bsonfileupload.Model.*;
import com.kgisl.bsonfileupload.Service.FileService;
import com.kgisl.bsonfileupload.Service.UserService;
import lombok.NoArgsConstructor;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@NoArgsConstructor
@RequestMapping("/api/bsonclaim")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {
    @Autowired
    private FileService fileService;

    // @Autowired
    // private FileDownloadResult downloadResult;

    // @Autowired
    // private File file;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestParam("userId") String userId,
            @RequestParam("username") String username,
            @RequestParam("files") MultipartFile[] files) throws IOException {
        try {
            User user = new User();
            user.setId(userId);
            user.setUsername(username);
            userService.saveUser(user);
            for (MultipartFile multipartFile : files) {
                fileService.uploadFileToUser(userId, multipartFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload files.");
        }

        return ResponseEntity.ok("Files uploaded successfully.");

    }

    // @GetMapping(value = "/download/{userId}")
    // public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("userId")
    // String userId) {
    // FileDownloadResult downloadResult = fileService.downloadFileByUserId(userId);
    // System.out.println(downloadResult);

    // if (downloadResult != null && downloadResult.getFile() != null) {
    // File file = downloadResult.getFile();
    // ByteArrayResource resource = new ByteArrayResource(file.getFileData());
    // return ResponseEntity.ok()
    // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" +
    // file.getFileName())
    // .contentType(MediaType.APPLICATION_OCTET_STREAM)
    // .contentLength(file.getFileData().length)
    // .body(resource);

    // } else {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    // }
    // }

    @GetMapping(value = "/download/{userId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("userId") String userId) {
        List<File> files = fileService.find(userId);

        if (!files.isEmpty()) {
            byte[] zipData = createZipFile(files);
            ByteArrayResource resource = new ByteArrayResource(zipData);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=files.zip");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(zipData.length)
                    .body(resource);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

   
    private byte[] createZipFile(List<File> files) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ZipOutputStream zipOutputStream = new ZipOutputStream(baos)) {

            for (File file : files) {
                byte[] fileData = file.getFileData();
                String fileName = file.getFileName();

                ZipEntry zipEntry = new ZipEntry(fileName);
                zipEntry.setSize(fileData.length);

                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(fileData);
                zipOutputStream.closeEntry();
            }

            zipOutputStream.finish();

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

}
