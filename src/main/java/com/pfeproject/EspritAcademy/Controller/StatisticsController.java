package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Services.StatisticsService;
import com.pfeproject.EspritAcademy.dto.QuizStatisticsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/statistics")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/quiz")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<QuizStatisticsDto>> getAllQuizStatistics() {
        return ResponseEntity.ok(statisticsService.getQuizStatistics());
    }

    @GetMapping("/quiz/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<QuizStatisticsDto> getQuizStatistics(@PathVariable Long id) {
        return ResponseEntity.ok(statisticsService.getQuizStatisticsById(id));
    }
}
