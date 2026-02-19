package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q JOIN q.level l JOIN l.theme t WHERE t.name = :themeName AND l.id = :levelId")
    List getAll(String themeName,Long levelId);
}
