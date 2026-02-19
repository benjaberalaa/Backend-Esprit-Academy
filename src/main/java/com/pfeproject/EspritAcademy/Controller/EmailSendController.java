package com.pfeproject.EspritAcademy.Controller;


import com.pfeproject.EspritAcademy.Services.EmailServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/v1/auth")
public class EmailSendController {
    private final EmailServices emailServices;

    public EmailSendController(EmailServices emailServices) {
        this.emailServices = emailServices;
    }

    @PostMapping("/send")
    public String sendMail(@RequestParam(value = "file", required = false) MultipartFile[] file, String to, String[] cc, String subject, String body) {
        return emailServices.sendMail(file, to, cc, subject, body);
    }

}