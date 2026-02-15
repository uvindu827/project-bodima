package com.example.projecctbodima.service.impl;

import com.example.projecctbodima.service.ISmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServiceImpl implements ISmsService {

    @Value("${textlk.api.key}")
    private String apiKey;

    @Value("${textlk.sender.id}")
    private String senderId;

    @Value("${textlk.api.url}")
    private String apiUrl;

    private  final RestTemplate restTemplate = new RestTemplate();

    @Async
    @Override
    public void sendRegistrationSms(String phoneNumber, String name, int id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            Map<String, Object> body = new HashMap<>();
            body.put("recipient", "94" + phoneNumber);
            body.put("sender_id", senderId);
            body.put("type", "plain");
            body.put("message", "Hi " + name + " welcome to bodima!!!\n Your tenent ID is T" + id);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

            System.out.println("SMS Status: " + response.getStatusCode());

        } catch(Exception e){
            System.err.println("SMS Failed: " + e.getMessage());
        }
    }
}
