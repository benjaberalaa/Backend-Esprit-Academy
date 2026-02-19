package com.pfeproject.EspritAcademy.Services;


import com.pfeproject.EspritAcademy.Entity.ForumAnswer;
import com.pfeproject.EspritAcademy.Entity.ForumQuestion;
import com.pfeproject.EspritAcademy.Repository.ForumAnswerRepository;
import com.pfeproject.EspritAcademy.Repository.ForumQuestionRepository;
import com.pfeproject.EspritAcademy.dto.CreateAnswerDTO;
import com.pfeproject.EspritAcademy.dto.CreateQuestionDTO;
import com.pfeproject.EspritAcademy.dto.VoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {

    private final ForumQuestionRepository questionRepository;
    private final ForumAnswerRepository answerRepository;

    // ========== QUESTIONS ==========

    public List<ForumQuestion> getAllQuestions() {
        return questionRepository.findAllByOrderByVotesDesc();
    }

    public List<ForumQuestion> getRecentQuestions() {
        return questionRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<ForumQuestion> searchQuestions(String keyword) {
        return questionRepository.searchByKeyword(keyword);
    }

    public List<ForumQuestion> getUnansweredQuestions() {
        return questionRepository.findUnansweredQuestions();
    }

    public List<ForumQuestion> getResolvedQuestions() {
        return questionRepository.findResolvedQuestions();
    }

    public List<ForumQuestion> getQuestionsByTag(String tag) {
        return questionRepository.findByTag(tag);
    }

    @Transactional
    public ForumQuestion getQuestionById(Long id) {
        ForumQuestion question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));

        // Incrémenter les vues
        question.setViews(question.getViews() + 1);
        return questionRepository.save(question);
    }

    @Transactional
    public ForumQuestion createQuestion(CreateQuestionDTO dto) {
        ForumQuestion question = new ForumQuestion();
        question.setTitle(dto.getTitle());
        question.setDescription(dto.getDescription());
        question.setTags(dto.getTags());
        question.setAuthor(dto.getAuthor());
        question.setVotes(0);
        question.setViews(0);

        return questionRepository.save(question);
    }

    @Transactional
    public ForumQuestion voteQuestion(Long questionId, VoteDTO voteDTO) {
        ForumQuestion question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        String userId = voteDTO.getUserId();

        if (voteDTO.getIsUpvote()) {
            // Upvote
            if (!question.getUpvotedBy().contains(userId)) {
                question.getUpvotedBy().add(userId);
                question.getDownvotedBy().remove(userId);
                question.setVotes(question.getVotes() + 1);
            }
        } else {
            // Downvote
            if (!question.getDownvotedBy().contains(userId)) {
                question.getDownvotedBy().add(userId);
                question.getUpvotedBy().remove(userId);
                question.setVotes(question.getVotes() - 1);
            }
        }

        return questionRepository.save(question);
    }

    // ========== ANSWERS ==========

    public List<ForumAnswer> getAnswersByQuestionId(Long questionId) {
        return answerRepository.findByQuestionIdOrderByVotesDesc(questionId);
    }

    @Transactional
    public ForumAnswer createAnswer(CreateAnswerDTO dto) {
        ForumQuestion question = questionRepository.findById(dto.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question not found"));

        ForumAnswer answer = new ForumAnswer();
        answer.setQuestion(question);
        answer.setContent(dto.getContent());
        answer.setAuthor(dto.getAuthor());
        answer.setVotes(0);
        answer.setIsAccepted(false);

        return answerRepository.save(answer);
    }

    @Transactional
    public ForumAnswer voteAnswer(Long answerId, VoteDTO voteDTO) {
        ForumAnswer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        String userId = voteDTO.getUserId();

        if (voteDTO.getIsUpvote()) {
            if (!answer.getUpvotedBy().contains(userId)) {
                answer.getUpvotedBy().add(userId);
                answer.getDownvotedBy().remove(userId);
                answer.setVotes(answer.getVotes() + 1);
            }
        } else {
            if (!answer.getDownvotedBy().contains(userId)) {
                answer.getDownvotedBy().add(userId);
                answer.getUpvotedBy().remove(userId);
                answer.setVotes(answer.getVotes() - 1);
            }
        }

        return answerRepository.save(answer);
    }

    @Transactional
    public ForumAnswer acceptAnswer(Long answerId) {
        ForumAnswer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));

        ForumQuestion question = answer.getQuestion();

        // Retirer l'acceptation des autres réponses
        question.getAnswers().forEach(a -> a.setIsAccepted(false));

        // Accepter cette réponse
        answer.setIsAccepted(true);

        return answerRepository.save(answer);
    }
}



