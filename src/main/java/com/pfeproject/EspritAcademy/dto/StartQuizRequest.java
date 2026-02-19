package com.pfeproject.EspritAcademy.dto;

import lombok.Data;

@Data
public class StartQuizRequest {
    private String studentId;
    private Long quizId;
}

