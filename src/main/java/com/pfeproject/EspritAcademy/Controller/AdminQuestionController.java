package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Services.QuizService;
import com.pfeproject.EspritAcademy.dto.QuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/question")
@RequiredArgsConstructor
public class AdminQuestionController {

    private final QuizService quizService;

    @PostMapping("/{quizId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuestionDto> addQuestionToQuiz(@PathVariable Long quizId,
            @RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok(quizService.addQuestionToQuiz(quizId, questionDto));
    }

    @PutMapping("/{questionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long questionId,
            @RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok(quizService.updateQuestion(questionId, questionDto));
    }

    @DeleteMapping("/{questionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        quizService.deleteQuestion(questionId);
        return ResponseEntity.noContent().build();
    }
}
