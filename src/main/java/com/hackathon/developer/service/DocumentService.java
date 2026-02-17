package com.hackathon.developer.service;


import com.hackathon.developer.model.Documents;
import com.hackathon.developer.model.User;
import com.hackathon.developer.repository.DocumentRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private UserService userService;


    public void saveEntry(Documents documents){        //post
        documentRepository.save(documents);
    }
    @Transactional
    public void saveEntry(Documents documents, String userName){
        User user = userService.findByUsername(userName);
        documents.setDate(LocalDateTime.now());
        Documents saved = documentRepository.save(documents);
        user.getDocuments().add(saved);
        userService.saveEntry(user);
    }
    public List<Documents> getAll(){
        return documentRepository.findAll();

    }

    public void deleteById(ObjectId id){                        //deletebyid
        documentRepository.deleteById(id);
    }

    public void deleteById(String userName, ObjectId id){
        User user = userService.findByUsername(userName);
        user.getDocuments().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        documentRepository.deleteById(id);
    }

    public User findByUsername(String username){
        return userService.findByUsername(username);
    }
    public Optional<Documents> getById(ObjectId id){        //getbyid
        return documentRepository.findById(id);
    }



}
