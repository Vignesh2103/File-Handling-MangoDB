// package com.kgisl.bsonfileupload.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.kgisl.bsonfileupload.Model.ClaimForm;
// import com.kgisl.bsonfileupload.Service.ClaimService;

// import lombok.NoArgsConstructor;


// @RestController
// @NoArgsConstructor
// @RequestMapping("/api/bsonclaim")
// @CrossOrigin(origins = "*", maxAge = 3600)
// public class ClaimController {

//     @Autowired
//     private  ClaimForm claimForm;

//     @Autowired
//     private  ClaimService claimService;

    

//     @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//     public ResponseEntity<String> uploadFile(@RequestParam("files") MultipartFile[] files,
//             @RequestParam("id") String id, @RequestParam("emailId") String emailId) throws Exception {

//         claimForm.setId(id);
//         claimForm.setEmailId(emailId);

//         for (MultipartFile file : files) {

//             claimForm = claimService.fileUpload(file);
           
//         }
//         MongoTemplate mongoTemplate = new MongoTemplate();
//         mongoTemplate.insert(claimForm);
//         return new ResponseEntity<>("Success", HttpStatus.OK);

//     }

// }
