package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Services.QuizService;
import com.pfeproject.EspritAcademy.dto.QuizDto;
import com.pfeproject.EspritAcademy.dto.QuizScoreDto;
import com.pfeproject.EspritAcademy.dto.StartQuizRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    // Liste des quiz de l’étudiant
    @GetMapping(path = "/{studentName}", produces = "application/json;charset=UTF-8")
    public List<QuizDto> getStudentQuizzes(@PathVariable String studentName) {

        System.out.println(" studentId === " + studentName);

        return quizService.getStudentQuizzes(studentName);

    }

    // Détails d’un quiz
    @GetMapping(path = "/details/{quizId}", produces = "application/json;charset=UTF-8")
    public QuizDto getQuizDetails(@PathVariable Long quizId) {
        System.out.println(" quizId === " + quizId);
        return quizService.getQuizDetails(quizId);
    }

    // Commencer un quiz
    @PostMapping("/start")
    public QuizScoreDto startQuiz(@RequestBody StartQuizRequest request) {

        System.out.println("  START studentId === " + request.getStudentId());

        System.out.println(" quizId === " + request.getQuizId());

        return quizService.startQuiz(request.getStudentId(), request.getQuizId());
    }

    @GetMapping("/result/{student}/{quizId}")
    public ResponseEntity<QuizScoreDto> getQuizResult(
            @PathVariable String student,
            @PathVariable Long quizId) {
        try {
            QuizScoreDto result = quizService.getQuizResult(student, quizId);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Soumettre le score
    @PostMapping("/submit")
    public QuizScoreDto submitScore(
            @RequestParam String studentId,
            @RequestParam Long quizId,
            @RequestParam Integer score,
            @RequestParam Integer total,
            @RequestParam Long duration) {

        System.out.println("  Submit studentId === " + studentId);
        System.out.println("  Submit quizId === " + quizId);
        System.out.println("  Submit score === " + score);
        System.out.println("  Submit total === " + total);
        System.out.println("  Submit duration === " + duration);

        return quizService.submitQuizScore(studentId, quizId, score, total, duration);
    }

    // Scores de l’étudiant
    @GetMapping(path = "/scores/{studentName}", produces = "application/json;charset=UTF-8")
    public List<QuizScoreDto> getStudentScores(@PathVariable String studentName) {
        return quizService.getStudentScores(studentName);
    }

}
