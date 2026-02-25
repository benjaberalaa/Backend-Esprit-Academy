package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Services.QuizService;
import com.pfeproject.EspritAcademy.dto.QuizDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/quiz")
@RequiredArgsConstructor
public class AdminQuizController {

    private final QuizService quizService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuizDto> createQuiz(@RequestBody QuizDto quizDto) {
        return ResponseEntity.ok(quizService.createQuiz(quizDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuizDto> updateQuiz(@PathVariable Long id, @RequestBody QuizDto quizDto) {
        return ResponseEntity.ok(quizService.updateQuiz(id, quizDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<QuizDto>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzesForAdmin());
    }

    @PostMapping("/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> assignQuiz(@RequestParam Long quizId, @RequestParam Long studentId) {
        quizService.assignQuizToStudent(quizId, studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/scores")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<com.pfeproject.EspritAcademy.dto.QuizScoreDto>> getQuizScores(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizScoresByQuizId(id));
    }
}
