package com.example.admistrator.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.example.admistrator.Entity.Summerizer;
import com.example.admistrator.Entity.SupportData;
import com.example.admistrator.Service.getdata;


@RestController
@CrossOrigin(origins = "https://admin-echo.choreoapps.dev/")
public class controller {
    @Autowired
    public  getdata service;

  
    @GetMapping("/Technical")
    public ResponseEntity<List<SupportData>> getTechsupportqueries(
    @RequestParam(value = "Technical", required = false, defaultValue = "Technical") String Technical) {
    List<SupportData> entity = service.getEntitiesByDepartment(Technical);
    return ResponseEntity.ok(entity);
    }   



    @GetMapping("/Customerservice")
    public ResponseEntity<List<SupportData>> getCustomersupportqueries(
    @RequestParam(value = "Customer Service", required = false, defaultValue = "Customer Service") String CustomerService) {
    List<SupportData> entity = service.getEntitiesByDepartment(CustomerService);
    return ResponseEntity.ok(entity);
    } 

    
    @GetMapping("/Hr")
    public ResponseEntity<List<SupportData>> getDocumantationqueries(
        @RequestParam(value = "HR", required = false, defaultValue = "HR") String Hr) {
    List<SupportData> entity = service.getEntitiesByDepartment(Hr);
    return ResponseEntity.ok(entity);
    }

    @GetMapping("/miscellaneous")
        public ResponseEntity<List<SupportData>> getMiscellaneousqueries(
                @RequestParam(value = "miscellaneous", required = false, defaultValue = "miscellaneous") String miscellaneous) {
            List<SupportData> entity = service.getEntitiesByDepartment(miscellaneous);
            return ResponseEntity.ok(entity);
        }

    @GetMapping("/Summery/{year}")
    public ResponseEntity<List<Summerizer>> getsummery(@PathVariable Integer year){
        List<Summerizer> entity = service.getsummery(year);
        return ResponseEntity.ok(entity);

    
    }
    @GetMapping("/statistics/sentiment")
    public  getdata.SentimentStatistics getSentimentStatistics() {
        return service.getSentimentStatistics();
    }

    @GetMapping("/statistics/departmentcount")
        public getdata.departmentcountt getHrDepartmentCount(){
            return service.departmentcount();
        }
    }



        




    





