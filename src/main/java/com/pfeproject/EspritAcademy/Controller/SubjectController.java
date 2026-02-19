package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Entity.Subject;
import com.pfeproject.EspritAcademy.Repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Subject")
@AllArgsConstructor
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ENSEI')")
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping("/Add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        Subject savedSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(savedSubject);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
        subjectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
