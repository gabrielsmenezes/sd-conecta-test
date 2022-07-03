package com.example.sdconectatest.controller;

import com.example.sdconectatest.domain.Doctor;
import com.example.sdconectatest.service.delete.DeleteDoctor;
import com.example.sdconectatest.service.findAllPaged.FindAllDoctor;
import com.example.sdconectatest.service.insert.InsertDoctor;
import com.example.sdconectatest.service.update.UpdateDoctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final InsertDoctor insert;
    private final UpdateDoctor update;
    private final DeleteDoctor delete;
    private final FindAllDoctor findAll;

    public DoctorController(InsertDoctor insert, UpdateDoctor update, DeleteDoctor delete, FindAllDoctor findAll) {
        this.insert = insert;
        this.update = update;
        this.delete = delete;
        this.findAll = findAll;
    }

    @PostMapping
    public ResponseEntity<Doctor> insert(@RequestBody Doctor doctor) {
        Doctor saved = this.insert.execute(doctor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Integer id, @RequestBody Doctor doctor) {
        return ResponseEntity.ok(this.update.execute(id, doctor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Doctor> delete(@PathVariable Integer id) {
        this.delete.execute(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<Doctor>> findAll(
            @PageableDefault(sort = "id", page = 0, size = 10, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(name = "name", required = false) String name, @RequestParam(name = "specialty", required = false) String specialty
    ) {
        return ResponseEntity.ok(this.findAll.execute(pageable, name, specialty));
    }

}
