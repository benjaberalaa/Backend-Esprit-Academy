package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    Theme findByName(String themename);
}
