package com.hackathon.developer.repository;

import com.hackathon.developer.model.Documents;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<Documents, ObjectId> {
}
