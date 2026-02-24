package com.pfeproject.EspritAcademy.Services.impl;

import com.pfeproject.EspritAcademy.Entity.Classe;
import com.pfeproject.EspritAcademy.Repository.ClasseRepository;
import com.pfeproject.EspritAcademy.Services.ClasseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository classeRepository;

    @Override
    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    @Override
    public Classe addClasse(Classe classe) {
        return classeRepository.save(classe);
    }

    @Override
    public void deleteClasse(Long id) {
        classeRepository.deleteById(id);
    }

    @Override
    public Classe getClasseById(Long id) {
        return classeRepository.findById(id).orElse(null);
    }
}
