package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    History findHistoryByScore(int score);


    History findHistoryByUsername(String username);
}
