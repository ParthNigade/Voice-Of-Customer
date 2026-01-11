package com.example.admistrator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.admistrator.Entity.Summerizer;
import com.example.admistrator.Entity.SupportData;
import com.example.admistrator.Repository.SummerizerDatarepository;
import com.example.admistrator.Repository.SupportDatarepository;
import java.util.*;


@Service
public class getdata {

    @Autowired 
    private SupportDatarepository supportDatarepository;
    @Autowired
    private SummerizerDatarepository summerizerDatarepository;

    public List<SupportData> getEntitiesByDepartment(String department) {
        return supportDatarepository.findBydepartment(department);
    }











    //summerizer
    public List<Summerizer> getsummery(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.JANUARY, 1, 0, 0, 0);
        Date start = calendar.getTime();
        calendar.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        Date end = calendar.getTime();
        return summerizerDatarepository.findByEndDateBetween(start,end);
    }












    //Staticstics




    //Sentiment of the Customer 
    public int getPositiveSentimentCount() {
        return supportDatarepository.countByQuerySentiment("Positive");
    }
    public int getNegativeSentimentCount() {
        return supportDatarepository.countByQuerySentiment("Negative");
    }
    public  SentimentStatistics getSentimentStatistics() {
        SentimentStatistics stats = new SentimentStatistics();
        stats.setPositiveCount(getPositiveSentimentCount());
        stats.setNegativeCount(getNegativeSentimentCount());
        return stats;
    }


    //getter setter

    public static class SentimentStatistics {
        private int positiveCount;
        private int negativeCount;


        public int getPositiveCount() {
            return positiveCount;
        }

        public void setPositiveCount(int positiveCount) {
            this.positiveCount = positiveCount;
        }

        public int getNegativeCount() {
            return negativeCount;
        }

        public void setNegativeCount(int negativeCount) {
            this.negativeCount = negativeCount;
        }
    }


    //count the deaprtment 

    public int getTechnicaldepartmentCount(){
        return supportDatarepository.countByDepartment("Technical");
    }
    public int getHRdepartmentCount(){
        return supportDatarepository.countByDepartment("HR");
    }
    public int getCustomerServicedepartmentCount(){
        return supportDatarepository.countByDepartment("Customer Service");
    }
    public int getMisscellaneousdepartmentCount(){
        return supportDatarepository.countByDepartment("miscellaneous");
    }

    public departmentcountt departmentcount(){
        departmentcountt departmentcount=new departmentcountt();
        departmentcount.setTechnicalDepartmentCount(getTechnicaldepartmentCount());
        departmentcount.setHrDepartmentCount(getHRdepartmentCount());
        departmentcount.setMiscellaneousDepartmentCount(getMisscellaneousdepartmentCount());
        departmentcount.setCustomerServiceDepartmentCount(getCustomerServicedepartmentCount());
        return departmentcount;
    }





    //getter setter
    public static class departmentcountt {

    private int technicalDepartmentCount;
    private int hrDepartmentCount;
    private int customerServiceDepartmentCount;
    private int miscellaneousDepartmentCount;

    public int getTechnicalDepartmentCount() {
        return technicalDepartmentCount;
    }

    public void setTechnicalDepartmentCount(int technicalDepartmentCount) {
        this.technicalDepartmentCount = technicalDepartmentCount;
    }

    public int getHrDepartmentCount() {
        return hrDepartmentCount;
    }

    public void setHrDepartmentCount(int hrDepartmentCount) {
        this.hrDepartmentCount = hrDepartmentCount;
    }

    public int getCustomerServiceDepartmentCount() {
        return customerServiceDepartmentCount;
    }

    public void setCustomerServiceDepartmentCount(int customerServiceDepartmentCount) {
        this.customerServiceDepartmentCount = customerServiceDepartmentCount;
    }

    public long getMiscellaneousDepartmentCount() {
        return miscellaneousDepartmentCount;
    }

    public void setMiscellaneousDepartmentCount(int miscellaneousDepartmentCount) {
        this.miscellaneousDepartmentCount = miscellaneousDepartmentCount;
    }

    
    }

 }
    

    



    
















    




    

