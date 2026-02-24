package com.pfeproject.EspritAcademy.Repository;

import com.pfeproject.EspritAcademy.Entity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long> {
}
