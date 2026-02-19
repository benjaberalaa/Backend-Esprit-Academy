package com.pfeproject.EspritAcademy.dto;

import lombok.Data;

@Data
public class QuizStatisticsDto {
    private Long quizId;
    private String quizTitle;
    private Long attemptCount;
    private Double averageScore;
    private Double completionRate;
    private Long activeStudents;
}
