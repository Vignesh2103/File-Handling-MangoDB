package com.kgisl.bsonfileupload.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "files")
@Component
public class File {
    @Id
    private String id;
    private String userId;
    private String fileName;
    private long fileSize;
    private byte[] fileData;

    // Getters and setters
}
