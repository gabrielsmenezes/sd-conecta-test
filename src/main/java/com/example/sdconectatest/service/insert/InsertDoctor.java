package com.example.sdconectatest.service.insert;

import com.example.sdconectatest.domain.Doctor;
import com.example.sdconectatest.repository.DoctorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InsertDoctor {

    private final DoctorRepository repository;
    private final BCryptPasswordEncoder encoder;

    public InsertDoctor(DoctorRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public Doctor execute(Doctor doctor) {
        doctor.setPassword(encoder.encode(doctor.getPassword()));
        return this.repository.save(doctor);
    }
}
