package com.example.admistrator.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;  


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "summaries")
public class Summerizer {
    @Id
    private String id;  
    private String Summary;
    private Date startDate;
    private Date endDate;
    private Date createdAt;
    private Date updatedAt;
    




    
}
