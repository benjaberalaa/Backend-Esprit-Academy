package com.pfeproject.EspritAcademy.Controller;

import com.pfeproject.EspritAcademy.Entity.Theme;
import com.pfeproject.EspritAcademy.Services.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @PostMapping("/addTheme")
    public Theme addTheme(@RequestBody Theme theme) {
        return themeService.addTheme(theme);
    }

    @PostMapping("/UpdTheme")
    public Theme UpdateTheme(@RequestBody Theme theme) {
        return themeService.updateTheme(theme);
    }

    @DeleteMapping("/DeleteTheme/{idtheme}")
    public void DeleteTheme(@PathVariable Long idtheme) {
        themeService.deleteTheme( idtheme);
    }

    @GetMapping("/getThemes")
    public List<Theme> getThemes() {
        return themeService.getThemes();
    }

    @GetMapping("/getTheme/{idTheme}")
    public Theme getTheme(@PathVariable("idTheme") Long idTheme) {
        return themeService.getTheme(idTheme);

    }
}
