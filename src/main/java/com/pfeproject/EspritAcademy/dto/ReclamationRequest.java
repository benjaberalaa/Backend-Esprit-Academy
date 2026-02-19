package com.pfeproject.EspritAcademy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReclamationRequest {
    private String titre;
    private String categorie;
    private String description;
    private String creerpar;
    private String priority;
}
