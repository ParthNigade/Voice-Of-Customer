package com.client.client.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.client.client.Entity.SupportData;





@Repository
public interface SupportDataRepository extends MongoRepository<SupportData, String> {
}



