package com.spring.crud.demo.controller;

import java.util.Locale;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.crud.demo.config.LanguageManager;

@RestController
@RequestMapping("/language")
public class LanguageController {
	
	@GetMapping("/fr")
    public void setFrench() {
		 LanguageManager.setLanguageCode(Locale.FRENCH);
    }
	 
	@GetMapping("/en")
    public void setEnglish() {
		LanguageManager.setLanguageCode(Locale.ENGLISH);
    }
}
