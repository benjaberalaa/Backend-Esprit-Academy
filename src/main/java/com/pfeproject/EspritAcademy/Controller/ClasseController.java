package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Entity.Classe;
import com.pfeproject.EspritAcademy.Services.ClasseService;
import com.pfeproject.EspritAcademy.dto.ClasseResponse;
import com.pfeproject.EspritAcademy.dto.RoleDto;
import com.pfeproject.EspritAcademy.dto.UserDto;
import com.pfeproject.EspritAcademy.Entity.Subject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Classe")
@AllArgsConstructor
public class ClasseController {

    private final ClasseService classeService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'ENSEI')")
    public List<ClasseResponse> getAllClasses() {
        return classeService.getAllClasses().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @PostMapping("/Add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClasseResponse> addClasse(@RequestBody Classe classe) {
        Classe savedClasse = classeService.addClasse(classe);
        return ResponseEntity.ok(mapToResponse(savedClasse));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteClasse(@PathVariable Long id) {
        classeService.deleteClasse(id);
        return ResponseEntity.ok().build();
    }

    private ClasseResponse mapToResponse(Classe classe) {
        ClasseResponse response = new ClasseResponse();
        response.setId(classe.getId());
        response.setName(classe.getName());

        List<UserDto> allUsers = (classe.getUsers() != null)
                ? classe.getUsers().stream().map(u -> {
                    UserDto dto = new UserDto();
                    dto.setFullName(u.getFirstname() + " " + u.getLastname());
                    dto.setEmail(u.getEmail());
                    dto.setRoleName(u.getRole().name());
                    return dto;
                }).toList()
                : List.of();

        response.setStudents(allUsers.stream()
                .filter(u -> "USER".equals(u.getRoleName()))
                .toList());

        response.setTeachers(allUsers.stream()
                .filter(u -> "ENSEI".equals(u.getRoleName()))
                .toList());

        response.setStudentCount(response.getStudents().size());
        response.setTeacherCount(response.getTeachers().size());

        response.setSubjects((classe.getSubjects() != null)
                ? classe.getSubjects().stream().map(Subject::getTitle).toList()
                : List.of());

        return response;
    }
}
