package com.example.sdconectatest.service.getAccessToken;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class GetAccessToken {

    public OAuth2Response execute(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = getCredentials();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<OAuth2Response> res = restTemplate.postForEntity(
                "https://beta.sdconecta.com/oauth2/token", request , OAuth2Response.class);

        return res.getBody();

    }

    private MultiValueMap<String, String> getCredentials() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", "655c5cb4-906b-4b90-a66b-cdbbcb246717");
        map.add("client_secret", "3OPxYLDsdBOwC/2xFQv2gg7kcg/1h1hEsJqhmKFgR9Q=");
        map.add("grant_type", "client_credentials");
        return map;
    }
}
