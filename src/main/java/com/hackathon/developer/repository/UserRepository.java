package com.hackathon.developer.repository;

import com.hackathon.developer.model.User;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,ObjectId>{
    User findByUsername(String username);
    void deleteById(ObjectId id);

    void deleteByUsername(String username);

}

