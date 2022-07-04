package com.example.sdconectatest.service.externalLogin;

import com.example.sdconectatest.domain.Doctor;
import com.example.sdconectatest.security.IntegrationInfo;
import com.example.sdconectatest.service.getAccessToken.GetAccessToken;
import com.example.sdconectatest.service.getAccessToken.OAuth2Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ExternalLogin {

    private final GetAccessToken getAccessToken;
    private final RestTemplate restTemplate;

    public ExternalLogin() {
        this.getAccessToken = new GetAccessToken();
        this.restTemplate = new RestTemplate();
    }

    public IntegrationInfo execute(Doctor doctor) {
        OAuth2Response tokenInfo = this.getAccessToken.execute();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + tokenInfo.getAccess_token());

        HttpEntity<Doctor> request = new HttpEntity<>(doctor, headers);

        ResponseEntity<IntegrationInfo> response = restTemplate.postForEntity("https://beta.sdconecta.com/api/v2/partners/generate-user-token", request, IntegrationInfo.class);

        return response.getBody();
    }
}
