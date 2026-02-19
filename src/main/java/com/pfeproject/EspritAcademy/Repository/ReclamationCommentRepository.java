package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.ReclamationComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamationCommentRepository extends JpaRepository<ReclamationComment, Long> {
}
