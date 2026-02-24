package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Entity.Classe;
import com.pfeproject.EspritAcademy.Entity.Subject;
import com.pfeproject.EspritAcademy.Repository.ClasseRepository;
import com.pfeproject.EspritAcademy.Repository.SubjectRepository;
import com.pfeproject.EspritAcademy.dto.CourseResponse;
import com.pfeproject.EspritAcademy.dto.SubjectResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/Subject")
@AllArgsConstructor
public class SubjectController {

    private final SubjectRepository subjectRepository;
    private final ClasseRepository classeRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ENSEI')")
    public List<SubjectResponse> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @PostMapping("/Add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SubjectResponse> addSubject(@RequestParam String title, @RequestParam Long classeId) {
        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new RuntimeException("Classe not found"));

        Subject subject = Subject.builder()
                .title(title)
                .classe(classe)
                .build();

        Subject savedSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(mapToResponse(savedSubject));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
        subjectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private SubjectResponse mapToResponse(Subject subject) {
        SubjectResponse response = new SubjectResponse();
        response.setId(subject.getId());
        response.setTitle(subject.getTitle());
        response.setClasseId(subject.getClasse() != null ? subject.getClasse().getId() : null);

        if (subject.getCourses() != null) {
            response.setCourses(subject.getCourses().stream()
                    .map(c -> new CourseResponse(c.getId(), c.getFileName(),
                            c.getSubject() != null ? c.getSubject().getId() : null,
                            c.getClasse() != null ? c.getClasse().getId() : null))
                    .toList());
        }
        return response;
    }
}
