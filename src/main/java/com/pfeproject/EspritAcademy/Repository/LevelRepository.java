package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {


}
