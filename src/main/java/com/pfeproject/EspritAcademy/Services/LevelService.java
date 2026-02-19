package com.pfeproject.EspritAcademy.Services;

import com.pfeproject.EspritAcademy.Entity.Level;
import com.pfeproject.EspritAcademy.Entity.Theme;
import com.pfeproject.EspritAcademy.Repository.LevelRepository;
import com.pfeproject.EspritAcademy.Repository.ThemeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LevelService {
    @Autowired
    private LevelRepository levelDao;

    @Autowired
    private ThemeRepository themeDao;


    public Level addLevel(Level level, Theme theme) {
        theme.addLevel(level);
        return levelDao.save(level);
    }

    public Level addLevel(Level level, Long idTheme) {
        Theme theme = themeDao.getReferenceById(idTheme);
        return addLevel(level ,theme);
    }

    public List<Level> getLevels(Long idTheme) {
        Theme theme = themeDao.getReferenceById(idTheme);
         List<Level> levels = theme.getLevels();
        return levels;
    }

    public Level getLevel(Long idTheme) {
        return levelDao.getReferenceById(idTheme);

    }
}
