package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private Role role;
    private String Nom;
    private String prenom;
    private String image;
    private String niveau;
    private Long classeId;
}
