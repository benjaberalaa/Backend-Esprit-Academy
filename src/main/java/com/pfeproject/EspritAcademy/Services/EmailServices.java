package com.pfeproject.EspritAcademy.Services;

import org.springframework.web.multipart.MultipartFile;


public interface EmailServices  {
    String sendMail(MultipartFile[] file, String to,String[] cc, String subject, String body);
}