package com.hackathon.developer.controller;

import com.hackathon.developer.model.Documents;
import com.hackathon.developer.model.User;
import com.hackathon.developer.service.DocumentService;
import com.hackathon.developer.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getEntryFromUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<Documents> all = user.getDocuments();
        if (all != null) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<?> postEntryOfUser(@RequestBody Documents documents) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        documentService.saveEntry(documents, username);
        if (documents != null) {
            return new ResponseEntity<>(documents, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getEntryOfUserById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = documentService.findByUsername(username);
        List<Documents> collect = user.getDocuments().stream().filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<Documents> documents = documentService.getById(myId);
            if (documents.isPresent()) {
                return new ResponseEntity<>(documents.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> DeleteById(@PathVariable ObjectId myId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            documentService.deleteById(username, myId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> put(
            @PathVariable ObjectId myId,
            @RequestBody Documents newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = documentService.findByUsername(username);
        List<Documents> collect = user.getDocuments().stream().filter(x -> x.getId().equals(myId))
                .collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<Documents> documents = documentService.getById(myId);
            if (documents.isPresent()) {
                Documents old = documentService.getById(myId).orElse(null);
                if (old != null) {
                    old.setHeading(newEntry.getHeading() != null && !newEntry.getHeading().equals(" ") ?
                            newEntry.getHeading() : old.getHeading());
                    old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals(" ") ?
                            newEntry.getContent() : old.getContent());


                }
                documentService.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

