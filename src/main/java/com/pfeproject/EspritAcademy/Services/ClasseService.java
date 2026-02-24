package com.pfeproject.EspritAcademy.Services;

import com.pfeproject.EspritAcademy.Entity.Classe;
import java.util.List;

public interface ClasseService {
    List<Classe> getAllClasses();

    Classe addClasse(Classe classe);

    void deleteClasse(Long id);

    Classe getClasseById(Long id);
}
