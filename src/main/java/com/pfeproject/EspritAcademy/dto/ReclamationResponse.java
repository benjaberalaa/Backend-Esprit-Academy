package com.pfeproject.EspritAcademy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReclamationResponse {
    private Long id;
    private String titre;
    private String categorie;
    private String description;
    private String creerpar;
    private String status;
    private String priority;
    private String assignedTo;
    private Integer rating;
    private LocalDateTime createdAt;
    private List<CommentResponse> comments = new ArrayList<>();
}
