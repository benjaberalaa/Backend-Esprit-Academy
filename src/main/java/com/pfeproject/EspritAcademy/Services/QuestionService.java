package com.pfeproject.EspritAcademy.Services;

import com.pfeproject.EspritAcademy.Entity.Level;
import com.pfeproject.EspritAcademy.Entity.Question;
import com.pfeproject.EspritAcademy.Repository.LevelRepository;
import com.pfeproject.EspritAcademy.Repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LevelRepository levelRepository;

    public Question addQuestion(Question question, Level level) {
        level.addQuestion(question);
        levelRepository.save(level);
        return  question;
    }
    public Question addQuestion(Question questionDto, Long idLevel) {
        Level level = levelRepository.getReferenceById(idLevel);
        return addQuestion(questionDto, level);
    }

    public List<Question> getQuestions(Long LevelId, String themename) {
       return questionRepository.getAll(themename,LevelId);
    }


}

