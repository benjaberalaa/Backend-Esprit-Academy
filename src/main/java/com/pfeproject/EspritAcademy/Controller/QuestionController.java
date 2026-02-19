package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Entity.Question;
import com.pfeproject.EspritAcademy.Services.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping("/addQuestion/{idLevel}")
    Question addQuestion(@RequestBody Question questionDto, @PathVariable("idLevel") Long idLevel) {
        System.out.println(" question vené du service heeeeeeeeeeee " + questionDto);
        return questionService.addQuestion(questionDto, idLevel);
    }

    @GetMapping("/getQuestions/{idLevel}/{themename}")
    List<Question> getQuestions(@PathVariable("idLevel") Long idLevel, @PathVariable("themename") String themename)  {
        return questionService.getQuestions(idLevel, themename);
    }
}
