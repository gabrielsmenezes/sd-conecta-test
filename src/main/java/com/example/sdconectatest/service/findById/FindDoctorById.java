package com.example.sdconectatest.service.findById;

import com.example.sdconectatest.domain.Doctor;
import com.example.sdconectatest.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class FindDoctorById {
    private final DoctorRepository repository;

    public FindDoctorById(DoctorRepository repository) {
        this.repository = repository;
    }

    public Doctor execute(Integer id){
        return this.repository.findById(id).orElseThrow();
    }

}
