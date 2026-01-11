package com.client.client.service;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.client.client.config.AppConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Genrativeaiservice {
    @Autowired
    private AppConfig appConfig;

    @Autowired
    private SupportDataService supportDataService; // Injecting SupportDataService

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getResponse(String userInput) {
        String payload = createPayload(userInput);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<String> requestEntity = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                appConfig.getApiUrl(),
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        System.out.println("Full API Response Body: " + response.getBody());

        return extractRequiredJson(userInput, response.getBody());
    }

    private String createPayload(String userInput) {
        return "{\n" +
                "  \"contents\": [\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"parts\": [\n" +
                "        {\n" +
                "          \"text\": \"" + userInput + "\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"systemInstruction\": {\n" +
                "    \"role\": \"user\",\n" +
                "    \"parts\": [\n" +
                "      {\n" +
                "        \"text\": \"You are given a prompt of customer response. You have to decide which department to notify among 'Technical', 'HR', 'Customer Service', or 'Miscellaneous'. Give the name of department, reply , suggest solution for backend employees to make their work easy, determine priority, and sentiment of query according to the JSON format provided. Do not ask for additional details, tell them the said department will contact them at the earliest PLEASE DO NOT ASK FOR ADDITIONAL INFORMATION ABOUT CUTOMER QUERY. If the customers English is incorrect or incomplete, understand it without correcting.if customer using another language understand it and continue the conversation in that language.If Cutomer asking the information other query tell them you only here assist on  Queries \"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }

    private String extractRequiredJson(String userInput, String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode textNode = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text");

            if (textNode.isMissingNode()) {
                System.err.println("Error: Unable to find 'text' node in response.");
                return "{}";
            }

            String jsonString = textNode.asText();
            System.out.println("Extracted JSON String: " + jsonString);

            if (jsonString != null && !jsonString.trim().isEmpty()) {
                int start = jsonString.indexOf("{");
                int end = jsonString.lastIndexOf("}");

                if (start != -1 && end != -1 && start < end) {
                    jsonString = jsonString.substring(start, end + 1);

                    try {
                        JSONObject jsonObject = new JSONObject(jsonString);
                        String reply = jsonObject.getString("reply");

                        // Store extracted JSON in MongoDB
                        supportDataService.saveSupportData(userInput, jsonString);

                        return reply;
                    } catch (JSONException e) {
                        System.err.println("JSON parsing error: " + e.getMessage());
                    }
                } else {
                    System.err.println("Error: JSON format is invalid.");
                }
            } else {
                System.err.println("Error: JSON string is null or empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "{}";
    }
}
