package com.example.sdconectatest.repository;

import com.example.sdconectatest.domain.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Page<Doctor> findAllByNameContains(String name, Pageable pageable);
    Optional<Doctor> findByEmailContains(String email);
}
