package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.ForumAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumAnswerRepository extends JpaRepository<ForumAnswer, Long> {

    // Trouver toutes les réponses d'une question
    List<ForumAnswer> findByQuestionIdOrderByVotesDesc(Long questionId);

    // Trouver la réponse acceptée d'une question
    @Query("SELECT a FROM ForumAnswer a WHERE a.question.id = :questionId AND a.isAccepted = true")
    ForumAnswer findAcceptedAnswerByQuestionId(@Param("questionId") Long questionId);
}
