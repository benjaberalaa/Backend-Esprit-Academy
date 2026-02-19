package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Entity.ForumAnswer;
import com.pfeproject.EspritAcademy.Entity.ForumQuestion;
import com.pfeproject.EspritAcademy.Services.ForumService;
import com.pfeproject.EspritAcademy.dto.CreateAnswerDTO;
import com.pfeproject.EspritAcademy.dto.CreateQuestionDTO;
import com.pfeproject.EspritAcademy.dto.VoteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/forum")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class ForumController {

    private final ForumService forumService;

    // ========== QUESTIONS ENDPOINTS ==========

    @GetMapping("/questions")
    public ResponseEntity<List<ForumQuestion>> getAllQuestions(
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String tag) {

        List<ForumQuestion> questions;

        if (search != null && !search.isEmpty()) {
            questions = forumService.searchQuestions(search);
        } else if (tag != null && !tag.isEmpty()) {
            questions = forumService.getQuestionsByTag(tag);
        } else if ("unanswered".equals(filter)) {
            questions = forumService.getUnansweredQuestions();
        } else if ("resolved".equals(filter)) {
            questions = forumService.getResolvedQuestions();
        } else if ("recent".equals(filter)) {
            questions = forumService.getRecentQuestions();
        } else {
            questions = forumService.getAllQuestions();
        }

        return ResponseEntity.ok(questions);
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<ForumQuestion> getQuestionById(@PathVariable Long id) {
        try {
            ForumQuestion question = forumService.getQuestionById(id);
            return ResponseEntity.ok(question);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/questions")
    public ResponseEntity<ForumQuestion> createQuestion(@RequestBody CreateQuestionDTO dto) {
        try {
            log.info(" STARTED Creating question in the forum");
            ForumQuestion question = forumService.createQuestion(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(question);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/questions/{id}/vote")
    public ResponseEntity<ForumQuestion> voteQuestion(
            @PathVariable Long id,
            @RequestBody VoteDTO voteDTO) {
        try {
            ForumQuestion question = forumService.voteQuestion(id, voteDTO);
            return ResponseEntity.ok(question);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ========== ANSWERS ENDPOINTS ==========

    @GetMapping("/questions/{questionId}/answers")
    public ResponseEntity<List<ForumAnswer>> getAnswersByQuestionId(@PathVariable Long questionId) {
        List<ForumAnswer> answers = forumService.getAnswersByQuestionId(questionId);
        return ResponseEntity.ok(answers);
    }

    @PostMapping("/answers")
    public ResponseEntity<ForumAnswer> createAnswer(@RequestBody CreateAnswerDTO dto) {
        try {
            ForumAnswer answer = forumService.createAnswer(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(answer);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/answers/{id}/vote")
    public ResponseEntity<ForumAnswer> voteAnswer(
            @PathVariable Long id,
            @RequestBody VoteDTO voteDTO) {
        try {
            ForumAnswer answer = forumService.voteAnswer(id, voteDTO);
            return ResponseEntity.ok(answer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/answers/{id}/accept")
    public ResponseEntity<ForumAnswer> acceptAnswer(@PathVariable Long id) {
        try {
            ForumAnswer answer = forumService.acceptAnswer(id);
            return ResponseEntity.ok(answer);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}


