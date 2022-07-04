package com.example.sdconectatest.security;

import com.example.sdconectatest.domain.Doctor;
import com.example.sdconectatest.repository.DoctorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final DoctorRepository repository;

    public UserDetailsServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        Doctor doctor = repository.findByEmailContains(email).orElseThrow(() -> {
            throw new UsernameNotFoundException(email);
        });

        return new UserDetailsImpl(doctor);
    }
}