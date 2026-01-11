package com.client.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.client.client.Entity.SupportData;
import com.client.client.Repository.SupportDataRepository;
import org.json.JSONObject;

import java.util.Date;

@Service
public class SupportDataService {

    @Autowired
    private SupportDataRepository supportDataRepository;

    public void saveSupportData(String query, String extractedJson) {
        try {
            JSONObject jsonObject = new JSONObject(extractedJson);

            SupportData supportData = new SupportData();
            supportData.setQuery(query);
            supportData.setDepartment(jsonObject.getString("department"));
            supportData.setQueryResponse(jsonObject.getString("reply"));
            supportData.setQuerySolution(jsonObject.getString("backend_solution"));
            supportData.setPriority(jsonObject.getString("priority"));
            supportData.setQuerySentiment(jsonObject.getString("sentiment"));
            supportData.setQuerystatus(false);  
            supportData.setCreatedAt(new Date());
            supportData.setUpdatedAt(new Date());

            supportDataRepository.save(supportData);
            System.out.println("Query successfully stored in MongoDB");

        } catch (Exception e) {
            System.err.println("Error saving query to MongoDB: " + e.getMessage());
        }
    }
}
