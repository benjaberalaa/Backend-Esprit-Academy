package com.pfeproject.EspritAcademy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity

public class EspritAcademyApplication {




	public static void main(String[] args) {
		SpringApplication.run(EspritAcademyApplication.class, args);
		System.out.println("App Started...");


	}



}
