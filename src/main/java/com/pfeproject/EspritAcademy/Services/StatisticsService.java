package com.pfeproject.EspritAcademy.Services;

import com.pfeproject.EspritAcademy.dto.QuizStatisticsDto;
import java.util.List;

public interface StatisticsService {
    List<QuizStatisticsDto> getQuizStatistics();

    QuizStatisticsDto getQuizStatisticsById(Long quizId);
}
