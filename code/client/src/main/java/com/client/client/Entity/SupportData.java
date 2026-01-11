package com.client.client.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

import java.util.Date;  

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "queries")  
public class SupportData {
    @Id
    private String id;  
    private String department;
    private String query;
    private String queryResponse;
    private String querySolution;
    private boolean querystatus;
    private String priority;
    private String querySentiment;
    private Date createdAt;
    private Date updatedAt;
}


