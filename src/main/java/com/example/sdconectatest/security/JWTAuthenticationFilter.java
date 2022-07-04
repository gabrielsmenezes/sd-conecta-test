package com.example.sdconectatest.security;

import com.example.sdconectatest.domain.Doctor;
import com.example.sdconectatest.repository.DoctorRepository;
import com.example.sdconectatest.service.externalLogin.ExternalLogin;
import com.example.sdconectatest.service.getAccessToken.GetAccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtils jwtUtil;
    private final DoctorRepository repository;
    private final ExternalLogin externalLogin;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtils jwtUtil, DoctorRepository repository) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.repository = repository;
        this.externalLogin = new ExternalLogin();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        LoginCredentials credentials = null;

        try {
            credentials = new ObjectMapper().readValue(req.getInputStream(), LoginCredentials.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(credentials.username(), credentials.password(), new ArrayList<>());

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) {
        String username = ((UserDetailsImpl) auth.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        res.addHeader("Authorization", "Bearer " + token);
        res.addHeader("access-control-expose-headers", "Authorization");

        Doctor doctor = this.repository.findByEmailContains(username).orElseThrow();

        IntegrationInfo info = this.externalLogin.execute(doctor);

        doctor.setAuthorizationStatus(info.getAuthorization_status());

        this.repository.save(doctor);
    }
}