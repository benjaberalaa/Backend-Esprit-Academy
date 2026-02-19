package com.pfeproject.EspritAcademy.Services.impl;

import com.pfeproject.EspritAcademy.Entity.*;
import com.pfeproject.EspritAcademy.Repository.*;
import com.pfeproject.EspritAcademy.Services.QuizService;
import com.pfeproject.EspritAcademy.dto.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

        private final QuizRepository quizRepository;
        private final QuizAssignmentRepository quizAssignmentRepository;
        private final QuizScoreRepository quizScoreRepository;
        private final UserRepository userRepository;
        private final QuestionRepository questionRepository;


        @Override
        public QuizDto createQuiz(QuizDto quizDto) {
                Quiz quiz = new Quiz();
                updateQuizFromDto(quiz, quizDto);
                Quiz savedQuiz = quizRepository.save(quiz);
                return convertQuizToDto(savedQuiz);
        }

        @Override
        public QuizDto updateQuiz(Long id, QuizDto quizDto) {
                Quiz quiz = quizRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Quiz not found"));
                updateQuizFromDto(quiz, quizDto);
                Quiz savedQuiz = quizRepository.save(quiz);
                return convertQuizToDto(savedQuiz);
        }

        @Override
        public void deleteQuiz(Long id) {
                quizRepository.deleteById(id);
        }

        @Override
        public List<QuizDto> getAllQuizzesForAdmin() {
                return quizRepository.findAll().stream()
                                .map(this::convertQuizToDto)
                                .collect(Collectors.toList());
        }

        @Override
        public com.pfeproject.EspritAcademy.dto.QuestionDto addQuestionToQuiz(Long quizId,
                                                                              com.pfeproject.EspritAcademy.dto.QuestionDto questionDto) {
                Quiz quiz = quizRepository.findById(quizId)
                                .orElseThrow(() -> new RuntimeException("Quiz not found"));

                Question question = new Question();
                question.setQuiz(quiz);
                updateQuestionFromDto(question, questionDto);

                // Ensure options have the question reference set if needed, though
                // updateQuestionFromDto handles it


                Question savedQuestion = questionRepository.save(question);
                return convertQuestionToDto(savedQuestion);
        }

        @Override
        public com.pfeproject.EspritAcademy.dto.QuestionDto updateQuestion(Long questionId,
                                                                           com.pfeproject.EspritAcademy.dto.QuestionDto questionDto) {
                Question question = questionRepository.findById(questionId)
                                .orElseThrow(() -> new RuntimeException("Question not found"));

                updateQuestionFromDto(question, questionDto);

                if (question.getOptions() != null) {
                        question.getOptions().forEach(opt -> opt.setQuestion(question));
                }

                Question savedQuestion = questionRepository.save(question);
                return convertQuestionToDto(savedQuestion);
        }

        @Override
        public void deleteQuestion(Long questionId) {
                questionRepository.deleteById(questionId);
        }

        private void updateQuizFromDto(Quiz quiz, QuizDto dto) {
                quiz.setTitle(dto.getTitle());
                quiz.setDescription(dto.getDescription());
                quiz.setLevel(dto.getLevel());
                quiz.setActive(dto.getActive());
                quiz.setDuration(dto.getDuration());
                quiz.setMinScore(dto.getMinScore());
                quiz.setCategory(dto.getCategory());
                quiz.setStartDate(dto.getStartDate());
                quiz.setEndDate(dto.getEndDate());

                // Handle Questions if needed, for now we assume questions are managed
                // separately
                // or simple list replacement could be implemented here if complex logic is
                // needed.
        }

        private void updateQuestionFromDto(Question question, com.pfeproject.EspritAcademy.dto.QuestionDto dto) {
                question.setLabel(dto.getLabel());
                question.setPoints(dto.getPoints());
                question.setTimer(dto.getTimer());
                question.setType(dto.getType());

                if (dto.getOptions() != null) {
                        List<Option> options = dto.getOptions().stream().map(optDto -> {
                                Option opt = new Option();
                                opt.setId(optDto.getId());
                                opt.setLabel(optDto.getLabel());
                                opt.setCorrect(optDto.getCorrect());
                                opt.setQuestion(question);
                                return opt;
                        }).collect(Collectors.toList());

                        // If updating existing question, we might need to handle orphan removal or
                        // clearing list
                        if (question.getOptions() != null) {
                                question.getOptions().clear();
                                question.getOptions().addAll(options);
                        } else {
                                question.setOptions(options);
                        }
                }
        }

        // =========================================================
        // QUIZ POUR ÉTUDIANT
        // =========================================================

        @Override
        public List<QuizDto> getStudentQuizzes(String studentName) {

                Long studentId = userRepository.findIdByFirstname(studentName);

                return quizAssignmentRepository.findByStudentId(studentId)
                                .stream()
                                .map(assignment -> {
                                        Quiz quiz = assignment.getQuiz();
                                        QuizDto dto = convertQuizToDto(quiz);

                                        dto.setCompleted(
                                                        assignment.getCompleted() != null && assignment.getCompleted());

                                        return dto;
                                })
                                .collect(Collectors.toList());
        }

        @Override
        public QuizDto getQuizDetails(Long quizId) {
                Quiz quiz = quizRepository.findById(quizId)
                                .orElseThrow(() -> new RuntimeException("Quiz non trouvé avec l'ID: " + quizId));

                return convertQuizToDto(quiz);
        }

        // =========================================================
        // DÉMARRER & SOUMETTRE QUIZ
        // =========================================================

        @Override
        public QuizScoreDto startQuiz(String studentName, Long quizId) {

                Long studentId = userRepository.findIdByFirstname(studentName);

                QuizAssignment assignment = quizAssignmentRepository
                                .findByStudentIdAndQuizId(studentId, quizId)
                                .orElseThrow(() -> new RuntimeException("Assignment non trouvé"));

                assignment.setStarted(true);
                quizAssignmentRepository.save(assignment);

                QuizScore score = quizScoreRepository
                                .findByStudentIdAndQuizId(studentId, quizId)
                                .orElseGet(() -> {
                                        QuizScore newScore = new QuizScore();
                                        newScore.setStudent(assignment.getStudent());
                                        newScore.setQuiz(assignment.getQuiz());
                                        newScore.setFinished(false);
                                        return quizScoreRepository.save(newScore);
                                });

                return convertQuizScoreToDto(score);
        }

        @Override
        public QuizScoreDto submitQuizScore(
                        String studentName,
                        Long quizId,
                        Integer scoreValue,
                        Integer total,
                        Long duration) {

                Long studentId = userRepository.findIdByFirstname(studentName);

                QuizScore score = quizScoreRepository
                                .findByStudentIdAndQuizId(studentId, quizId)
                                .orElseThrow(() -> new RuntimeException("Score non trouvé"));

                score.setScore(scoreValue);
                score.setTotal(total);
                score.setDurationSec(duration);
                score.setFinished(true);

                quizScoreRepository.save(score);

                QuizAssignment assignment = quizAssignmentRepository
                                .findByStudentIdAndQuizId(studentId, quizId)
                                .orElseThrow(() -> new RuntimeException("Assignment non trouvé"));

                assignment.setCompleted(true);
                quizAssignmentRepository.save(assignment);

                return convertQuizScoreToDto(score);
        }

        // =========================================================
        // RÉSULTATS & SCORES
        // =========================================================

        public QuizScoreDto getQuizResult(String studentName, Long quizId) {

                Long studentId = userRepository.findIdByFirstname(studentName);

                QuizScore result = quizScoreRepository
                                .findByStudentIdAndQuizId(studentId, quizId)
                                .orElseThrow(() -> new RuntimeException("Résultat du quiz non trouvé"));

                return convertQuizScoreToDto(result);
        }

        @Override
        public List<QuizScoreDto> getStudentScores(String studentName) {
                Long studentId = userRepository.findIdByFirstname(studentName);
                return quizScoreRepository.findByStudentId(studentId)
                                .stream()
                                .map(this::convertQuizScoreToDto)
                                .collect(Collectors.toList());
        }

        // =========================================================
        // CONVERSIONS ENTITY → DTO
        // =========================================================

        private QuizDto convertQuizToDto(Quiz quiz) {
                QuizDto dto = new QuizDto();
                dto.setId(quiz.getId());
                dto.setTitle(quiz.getTitle());
                dto.setDescription(quiz.getDescription());
                dto.setLevel(quiz.getLevel());
                dto.setActive(quiz.getActive());

                dto.setDuration(quiz.getDuration());
                dto.setMinScore(quiz.getMinScore());
                dto.setCategory(quiz.getCategory());
                dto.setStartDate(quiz.getStartDate());
                dto.setEndDate(quiz.getEndDate());

                dto.setQuestions(
                                quiz.getQuestions() != null
                                                ? quiz.getQuestions().stream()
                                                                .map(this::convertQuestionToDto)
                                                                .collect(Collectors.toList())
                                                : new ArrayList<>());

                return dto;
        }

        private QuestionDto convertQuestionToDto(Question question) {
                QuestionDto dto = new QuestionDto();
                dto.setId(question.getId());
                dto.setLabel(question.getLabel());
                dto.setPoints(question.getPoints());
                dto.setTimer(question.getTimer());
                dto.setType(question.getType());
                dto.setQuizId(question.getQuiz().getId());
                dto.setLevelId(
                                question.getLevel() != null ? question.getLevel().getId() : null);

                dto.setOptions(
                                question.getOptions() != null
                                                ? question.getOptions().stream()
                                                                .map(this::convertOptionToDto)
                                                                .collect(Collectors.toList())
                                                : new ArrayList<>());

                return dto;
        }

        private OptionDto convertOptionToDto(Option option) {
                OptionDto dto = new OptionDto();
                dto.setId(option.getId());
                dto.setLabel(option.getLabel());
                dto.setCorrect(option.getCorrect());
                dto.setQuestionId(option.getQuestion().getId());
                return dto;
        }

        private QuizScoreDto convertQuizScoreToDto(QuizScore score) {
                QuizScoreDto dto = new QuizScoreDto();
                dto.setId(score.getId());
                dto.setStudentId(score.getStudent().getId().longValue());
                dto.setQuizId(score.getQuiz().getId());
                dto.setScore(score.getScore());
                dto.setTotal(score.getTotal());
                dto.setDurationSec(score.getDurationSec());
                dto.setFinished(score.getFinished());
                return dto;
        }
}
