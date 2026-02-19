package com.pfeproject.EspritAcademy.Services;

import com.pfeproject.EspritAcademy.Entity.History;
import com.pfeproject.EspritAcademy.Repository.HistoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HistoryService {

    @Autowired
    private HistoryRepository historyDao;

    public History addHistory(History history) {
        return historyDao.save(history);
    }

    public History editHistory(History history, History history1) {
        history.setUsername(history1.getUsername());
        history.setLevelName(history1.getLevelName());
        history.setThemeName(history1.getThemeName());
        history.setScore(history1.getScore());
        history.setTotal(history1.getTotal());
        return historyDao.save(history);
    }

    public List<History> getHistories() {
        List<History> histories = historyDao.findAll();
        histories.sort((f1, f2) -> Long.compare(f2.getScore(), f1.getScore()));
        return histories;
    }


    public History findHistoryBySore(int score) {
        return historyDao.findHistoryByScore(score);
    }

    public History findHistoryByUsername(String username) {
        return historyDao.findHistoryByUsername(username);
    }

    public History editHistory(History historyDto, String username) {
        History history = new History();
        History history1 = historyDao.findHistoryByUsername(username);
        return editHistory(history, history1);
    }
}
