package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.QuizAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuizAssignmentRepository extends JpaRepository<QuizAssignment, Long> {

    List<QuizAssignment> findByStudentId(Long studentId);

    Optional<QuizAssignment> findByStudentIdAndQuizId(Long studentId, Long quizId);
}
