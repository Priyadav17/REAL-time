package com.hackathon.developer.service;


import com.hackathon.developer.model.User;
import com.hackathon.developer.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveEntry(User users) {

        userRepository.save(users);
    }

    public void saveNewEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    public List<User> getAll() {
        return userRepository.findAll();

    }
    public void deleteById(ObjectId id) {                        //deletebyid
        userRepository.deleteById(id);
    }

    public Optional<User> getById(ObjectId id) {        //getbyid
        return userRepository.findById(id);
    }

    public User findByUsername(String username){           //put
        return userRepository.findByUsername(username);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);


    }

}











