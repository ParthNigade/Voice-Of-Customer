package com.example.admistrator.Repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.admistrator.Entity.SupportData;



@Repository
public interface SupportDatarepository extends MongoRepository<SupportData,String> {
    List<SupportData> findBydepartment(String department);
    int countByQuerySentiment(String sentiment);
    int countByDepartment(String department);




}




