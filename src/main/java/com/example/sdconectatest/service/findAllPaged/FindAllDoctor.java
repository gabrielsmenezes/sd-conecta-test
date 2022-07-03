package com.example.sdconectatest.service.findAllPaged;

import com.example.sdconectatest.domain.Doctor;
import com.example.sdconectatest.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FindAllDoctor {

    private final DoctorRepository repository;

    public FindAllDoctor(DoctorRepository repository) {
        this.repository = repository;
    }

    public Page<Doctor> execute(Pageable pageable, String name, String specialty) {
        if (Objects.nonNull(name)) {
            return this.repository.findAllByNameContains(name, pageable);
        }
        return this.repository.findAll(pageable);
    }
}
