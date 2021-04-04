package com.spring.crud.demo.controller;

import java.util.Locale;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.crud.demo.config.LanguageManager;

@RestController
@RequestMapping("/language")
/**
 * rest controller to allow front end to change the selected language for the backend, so that
 * custom error throwned by the back is in the front end selected language
 * 
 * @author mickaelgudin
 * @see LanguageManager class that manages selected backend language
 */
public class LanguageController {
	
	@PutMapping("/fr")
    public void setFrench() {
		 LanguageManager.setLanguageCode(Locale.FRENCH);
    }
	 
	@PutMapping("/en")
    public void setEnglish() {
		LanguageManager.setLanguageCode(Locale.ENGLISH);
    }
}
