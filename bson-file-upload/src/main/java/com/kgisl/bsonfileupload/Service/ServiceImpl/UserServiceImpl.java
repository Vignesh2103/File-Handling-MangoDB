package com.kgisl.bsonfileupload.Service.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.kgisl.bsonfileupload.Model.User;
import com.kgisl.bsonfileupload.Service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    
    @Autowired
    private MongoTemplate mongoTemplate;

   

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

   

}