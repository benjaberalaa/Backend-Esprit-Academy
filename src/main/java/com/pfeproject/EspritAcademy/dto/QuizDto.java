package com.pfeproject.EspritAcademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDto {
    private Long id;
    private String title;
    private String description;
    private Integer level;
    private Boolean active;
    private Boolean completed;

    // New Fields
    private Integer duration;
    private Integer minScore;
    private String category;
    private java.time.LocalDateTime startDate;
    private java.time.LocalDateTime endDate;
    private List<QuestionDto> questions; // Liste des questions du quiz
}
