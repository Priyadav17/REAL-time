package com.hackathon.developer.model;



import lombok.Getter;
import lombok.NonNull;
import org.bson.types.ObjectId;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.index.Indexed;
@Document(collection = " Developers")
@Getter
@Setter
public class User {
    @Id
    public ObjectId id;
    @Indexed(unique = true)
    @NonNull
    public String username;
    @NonNull
    public String password;

    @DBRef
    public List<Documents> documents = new ArrayList<>();
    public List<String> roles;

}
