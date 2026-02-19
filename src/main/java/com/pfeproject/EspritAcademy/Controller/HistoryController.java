package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Entity.History;
import com.pfeproject.EspritAcademy.Services.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @PostMapping("/addHistory")
    History addHistory(@RequestBody History history) {
        return historyService.addHistory(history);
    }

@PutMapping("/editHistory/{username}")
History editHistory(@RequestBody History historyDto, @PathVariable("username") String username) {
    return historyService.editHistory(historyDto, username);
}

    @GetMapping("/getHistories")
    List<History> getHistories() {
        return historyService.getHistories();
    }

    @GetMapping("/findHistoryBySore/{score}")
    History findHistoryBySore(@PathVariable("score") int score) {
        return historyService.findHistoryBySore(score);
    }

    @GetMapping("/findHistoryByUsername/{username}")
    History findHistoryByUsername(@PathVariable("username") String username) {
        return historyService.findHistoryByUsername(username);
    }

}
