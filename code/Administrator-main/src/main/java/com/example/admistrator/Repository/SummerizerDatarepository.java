package com.example.admistrator.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.admistrator.Entity.Summerizer;
import java.util.*;



@Repository
public interface SummerizerDatarepository extends MongoRepository<Summerizer, String> {
    List<Summerizer> findByEndDateBetween(Date start, Date end);


}


