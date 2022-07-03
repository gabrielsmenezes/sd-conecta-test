package com.example.sdconectatest.service.insert;

import com.example.sdconectatest.domain.Doctor;
import com.example.sdconectatest.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class InsertDoctor {

    private final DoctorRepository repository;

    public InsertDoctor(DoctorRepository repository) {
        this.repository = repository;
    }

    public Doctor execute(Doctor doctor) {
        return this.repository.save(doctor);
    }

}
