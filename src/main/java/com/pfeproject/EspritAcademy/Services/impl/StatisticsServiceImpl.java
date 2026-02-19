package com.pfeproject.EspritAcademy.Services.impl;

import com.pfeproject.EspritAcademy.Entity.Quiz;
import com.pfeproject.EspritAcademy.Entity.QuizScore;
import com.pfeproject.EspritAcademy.Repository.QuizRepository;
import com.pfeproject.EspritAcademy.Repository.QuizScoreRepository;
import com.pfeproject.EspritAcademy.Services.StatisticsService;
import com.pfeproject.EspritAcademy.dto.QuizStatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final QuizRepository quizRepository;
    private final QuizScoreRepository quizScoreRepository;

    @Override
    public List<QuizStatisticsDto> getQuizStatistics() {
        return quizRepository.findAll().stream()
                .map(this::calculateStatistics)
                .collect(Collectors.toList());
    }

    @Override
    public QuizStatisticsDto getQuizStatisticsById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        return calculateStatistics(quiz);
    }

    private QuizStatisticsDto calculateStatistics(Quiz quiz) {
        List<QuizScore> scores = quizScoreRepository.findByQuizId(quiz.getId());

        QuizStatisticsDto dto = new QuizStatisticsDto();
        dto.setQuizId(quiz.getId());
        dto.setQuizTitle(quiz.getTitle());

        if (scores.isEmpty()) {
            dto.setAttemptCount(0L);
            dto.setAverageScore(0.0);
            dto.setCompletionRate(0.0);
            dto.setActiveStudents(0L);
            return dto;
        }

        long attempts = scores.size();
        long completed = scores.stream().filter(s -> s.getFinished() != null && s.getFinished()).count();
        double totalScore = scores.stream()
                .filter(s -> s.getScore() != null)
                .mapToInt(QuizScore::getScore)
                .sum();

        long uniqueStudents = scores.stream()
                .map(s -> s.getStudent().getId())
                .distinct()
                .count();

        dto.setAttemptCount(attempts);
        dto.setAverageScore(attempts > 0 ? totalScore / attempts : 0.0);
        dto.setCompletionRate(attempts > 0 ? (double) completed / attempts * 100 : 0.0);
        dto.setActiveStudents(uniqueStudents);

        return dto;
    }
}
