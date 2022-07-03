package com.example.sdconectatest.service.update;

import com.example.sdconectatest.domain.Doctor;
import com.example.sdconectatest.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateDoctor {

    private final DoctorRepository repository;

    public UpdateDoctor(DoctorRepository repository) {
        this.repository = repository;
    }

    public Doctor execute(Integer id, Doctor doctor){
        Doctor saved = this.repository.findById(id).orElseThrow();
        saved.update(doctor);
        return this.repository.save(saved);
    }
}
