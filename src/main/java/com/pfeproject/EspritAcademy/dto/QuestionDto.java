package com.pfeproject.EspritAcademy.dto;

import com.pfeproject.EspritAcademy.Entity.Enum.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long id;
    private String label;
    private Integer points;
    private Integer timer;
    private QuestionType type;
    private Long quizId; // Id du quiz associé
    private Long levelId; // Id du niveau associé
    private List<OptionDto> options; // Liste des options
}
