package com.spring.crud.demo.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
	private static ResourceBundle languageSelected;
	
	public static ResourceBundle get() {
		return (languageSelected == null) ? ResourceBundle.getBundle("com.spring.crud.demo.config.language", Locale.FRENCH) : languageSelected ;
	}
}
