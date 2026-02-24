package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Entity.Role;
import com.pfeproject.EspritAcademy.Services.AuthenticationService;
import com.pfeproject.EspritAcademy.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDto> getUsersByRole(@PathVariable Role role) {
        return authenticationService.getUsersByRole(role).stream()
                .map(u -> {
                    UserDto dto = new UserDto();
                    dto.setId(u.getId());
                    dto.setFullName(u.getFirstname() + " " + u.getLastname());
                    dto.setEmail(u.getEmail());
                    dto.setRoleName(u.getRole().name());
                    return dto;
                }).toList();
    }

    @PutMapping("/{userId}/assign-classe/{classeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignUserToClasse(@PathVariable Integer userId, @PathVariable Long classeId) {
        authenticationService.assignUserToClasse(userId, classeId);
        return ResponseEntity.ok().build();
    }
}
