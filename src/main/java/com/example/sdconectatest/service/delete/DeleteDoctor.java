package com.example.sdconectatest.service.delete;

import com.example.sdconectatest.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteDoctor {
    private final DoctorRepository repository;

    public DeleteDoctor(DoctorRepository repository) {
        this.repository = repository;
    }

    public void execute(Integer id){
        this.repository.deleteById(id);
    }
}
