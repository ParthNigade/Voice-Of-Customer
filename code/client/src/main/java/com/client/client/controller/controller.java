package com.client.client.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.client.client.service.Genrativeaiservice;

@RestController
@CrossOrigin("http://localhost:3000/")
public class controller {
    @Autowired 
    private Genrativeaiservice genaiService; 

    @PostMapping("/query")
    public String userQuery(@RequestBody String message) throws IOException, InterruptedException {
        return genaiService.getResponse(message);
        }
    
    }

       
    

