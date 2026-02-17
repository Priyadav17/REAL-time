package com.hackathon.developer.model;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "documents_entries")
public class Documents {


    @Id
    public ObjectId id;
    private LocalDateTime date;
    public String heading;
    public String content;

}
