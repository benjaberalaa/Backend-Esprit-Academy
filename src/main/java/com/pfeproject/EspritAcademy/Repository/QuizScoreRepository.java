package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.QuizScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizScoreRepository extends JpaRepository<QuizScore, Long> {

    Optional<QuizScore> findByStudentIdAndQuizId(Long studentId, Long quizId);

    List<QuizScore> findByStudentId(Long studentId);

    List<QuizScore> findByQuizId(Long quizId);

}
