package com.pfeproject.EspritAcademy.Services;

import com.pfeproject.EspritAcademy.dto.QuizDto;
import com.pfeproject.EspritAcademy.dto.QuizScoreDto;

import java.util.List;

public interface QuizService {

        List<QuizDto> getStudentQuizzes(String studentId);

        QuizDto getQuizDetails(Long quizId);

        QuizScoreDto startQuiz(String studentId, Long quizId);

        QuizScoreDto submitQuizScore(String studentId, Long quizId, Integer score, Integer total, Long duration);

        List<QuizScoreDto> getStudentScores(String studentName);

        // Admin methods
        QuizDto createQuiz(QuizDto quizDto);

        QuizDto updateQuiz(Long id, QuizDto quizDto);

        void deleteQuiz(Long id);

        List<QuizDto> getAllQuizzesForAdmin();

        // Admin Question methods
        com.pfeproject.EspritAcademy.dto.QuestionDto addQuestionToQuiz(Long quizId,
                        com.pfeproject.EspritAcademy.dto.QuestionDto questionDto);

        com.pfeproject.EspritAcademy.dto.QuestionDto updateQuestion(Long questionId,
                        com.pfeproject.EspritAcademy.dto.QuestionDto questionDto);

        void deleteQuestion(Long questionId);

        QuizScoreDto getQuizResult(String student, Long quizId);

        void assignQuizToStudent(Long quizId, Long studentId);

        List<QuizScoreDto> getQuizScoresByQuizId(Long quizId);

}
