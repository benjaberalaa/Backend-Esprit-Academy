package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Entity.CoursEntity;
import com.pfeproject.EspritAcademy.Entity.Subject;
import com.pfeproject.EspritAcademy.Repository.ClasseRepository;
import com.pfeproject.EspritAcademy.Repository.CoursRepository;
import com.pfeproject.EspritAcademy.Repository.SubjectRepository;
import com.pfeproject.EspritAcademy.dto.CourseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/File")
@AllArgsConstructor
@Slf4j
public class FileController {

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private com.pfeproject.EspritAcademy.Repository.UserRepository userRepository;

    // Upload a new PDF file
    @PostMapping("/Add")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ENSEI')")
    public ResponseEntity<CourseResponse> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "subjectId", required = false) Long subjectId,
            @RequestParam(value = "classeId", required = false) Long classeId) {
        log.info("Attempting upload. Auth: "
                + org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication());
        try {
            log.info("Uploading file: " + file.getOriginalFilename()
                    + (subjectId != null ? " to subject: " + subjectId : ""));

            // Create a new entity to save
            CoursEntity entity = new CoursEntity();
            entity.setFileName(file.getOriginalFilename());
            entity.setFileContent(file.getBytes());

            // Link to subject if provided
            if (subjectId != null) {
                subjectRepository.findById(subjectId).ifPresent(entity::setSubject);
            }

            // Link to classe if provided
            if (classeId != null) {
                classeRepository.findById(classeId).ifPresent(entity::setClasse);
            }

            // Save to database
            CoursEntity savedEntity = coursRepository.save(entity);

            // Return only the metadata, not the file bytes
            CourseResponse response = new CourseResponse(
                    savedEntity.getId(),
                    savedEntity.getFileName(),
                    savedEntity.getSubject() != null ? savedEntity.getSubject().getId() : null,
                    savedEntity.getClasse() != null ? savedEntity.getClasse().getId() : null);

            return ResponseEntity.ok(response);
        } catch (IOException e) {
            log.error("Error uploading file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get all courses WITHOUT file content (just metadata)
    @GetMapping("/GETAll")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ENSEI')")
    public List<CourseResponse> getAllCourses() {
        String email = org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication()
                .getName();
        com.pfeproject.EspritAcademy.Entity.User currentUser = userRepository.findByEmail(email);

        List<CoursEntity> entities;
        if (currentUser != null && currentUser.getRole() == com.pfeproject.EspritAcademy.Entity.Role.USER) {
            // If student, filter by their class + universal courses (classe == null)
            Long userClasseId = (currentUser.getClasse() != null) ? currentUser.getClasse().getId() : null;

            entities = coursRepository.findAll().stream()
                    .filter(c -> c.getClasse() == null
                            || (userClasseId != null && c.getClasse().getId().equals(userClasseId)))
                    .toList();
        } else {
            // Admin or ENSEI see all
            entities = coursRepository.findAll();
        }

        List<CourseResponse> responses = new ArrayList<>();

        for (CoursEntity entity : entities) {
            CourseResponse response = new CourseResponse(
                    entity.getId(),
                    entity.getFileName(),
                    entity.getSubject() != null ? entity.getSubject().getId() : null,
                    entity.getClasse() != null ? entity.getClasse().getId() : null);
            responses.add(response);
        }

        return responses;
    }

    // Download a PDF file by ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ENSEI')")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Optional<CoursEntity> optionalEntity = coursRepository.findById(id);
        if (optionalEntity.isPresent()) {
            CoursEntity entity = optionalEntity.get();
            String encodedFileName = UriUtils.encode(entity.getFileName(), StandardCharsets.UTF_8);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("inline").filename(encodedFileName).build());

            return ResponseEntity.ok().headers(headers).body(entity.getFileContent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Download a PDF file for saving to device (forces download)
    @GetMapping("/download/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ENSEI')")
    public ResponseEntity<byte[]> downloadFileForSave(@PathVariable Long id) {
        Optional<CoursEntity> optionalEntity = coursRepository.findById(id);
        if (optionalEntity.isPresent()) {
            CoursEntity entity = optionalEntity.get();
            String encodedFileName = UriUtils.encode(entity.getFileName(), StandardCharsets.UTF_8);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(encodedFileName).build());

            return ResponseEntity.ok().headers(headers).body(entity.getFileContent());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a course/file by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ENSEI')")
    public ResponseEntity<?> deleteFile(@PathVariable Long id) {
        try {
            coursRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Assign a course to a subject
    @PutMapping("/{id}/subject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignCourseToSubject(
            @PathVariable Long id,
            @RequestBody java.util.Map<String, String> body) {

        Optional<CoursEntity> optionalCourse = coursRepository.findById(id);
        if (optionalCourse.isPresent()) {
            CoursEntity course = optionalCourse.get();
            String subjectIdStr = body.get("subjectId");

            if (subjectIdStr == null || subjectIdStr.isEmpty() || subjectIdStr.equals("None")) {
                course.setSubject(null);
            } else {
                try {
                    Long subjectId = Long.parseLong(subjectIdStr);
                    Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
                    if (optionalSubject.isPresent()) {
                        course.setSubject(optionalSubject.get());
                    } else {
                        return ResponseEntity.badRequest().body("Subject not found");
                    }
                } catch (NumberFormatException e) {
                    return ResponseEntity.badRequest().body("Invalid subject ID format");
                }
            }

            coursRepository.save(course);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Assign a course to a classe
    @PutMapping("/{id}/classe")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignCourseToClasse(
            @PathVariable Long id,
            @RequestBody java.util.Map<String, String> body) {

        Optional<CoursEntity> optionalCourse = coursRepository.findById(id);
        if (optionalCourse.isPresent()) {
            CoursEntity course = optionalCourse.get();
            String classeIdStr = body.get("classeId");

            if (classeIdStr == null || classeIdStr.isEmpty() || classeIdStr.equals("None")) {
                course.setClasse(null);
            } else {
                try {
                    Long classeId = Long.parseLong(classeIdStr);
                    Optional<com.pfeproject.EspritAcademy.Entity.Classe> optionalClasse = classeRepository
                            .findById(classeId);
                    if (optionalClasse.isPresent()) {
                        course.setClasse(optionalClasse.get());
                    } else {
                        return ResponseEntity.badRequest().body("Classe not found");
                    }
                } catch (NumberFormatException e) {
                    return ResponseEntity.badRequest().body("Invalid classe ID format");
                }
            }

            coursRepository.save(course);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}