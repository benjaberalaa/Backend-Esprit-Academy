package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.ForumQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumQuestionRepository extends JpaRepository<ForumQuestion, Long> {

    // Rechercher par titre ou description
    @Query("SELECT q FROM ForumQuestion q WHERE " +
            "LOWER(q.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(q.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<ForumQuestion> searchByKeyword(@Param("keyword") String keyword);

    // Trouver par tag
    @Query("SELECT DISTINCT q FROM ForumQuestion q JOIN q.tags t WHERE t = :tag")
    List<ForumQuestion> findByTag(@Param("tag") String tag);

    // Questions sans réponse
    @Query("SELECT q FROM ForumQuestion q WHERE q.answers IS EMPTY")
    List<ForumQuestion> findUnansweredQuestions();

    // Questions résolues (avec réponse acceptée)
    @Query("SELECT q FROM ForumQuestion q JOIN q.answers a WHERE a.isAccepted = true")
    List<ForumQuestion> findResolvedQuestions();

    // Trier par votes
    List<ForumQuestion> findAllByOrderByVotesDesc();

    // Trier par date
    List<ForumQuestion> findAllByOrderByCreatedAtDesc();
}
