package com.spring.crud.demo.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
	private static ResourceBundle languageSelected;
	
	public static ResourceBundle get() {
		return (languageSelected == null) ? ResourceBundle.getBundle("language", Locale.FRENCH) : languageSelected ;
	}
	
	/**
	 * useful for unit tests
	 * @param rb
	 */
	public static void set(ResourceBundle rb) {
		LanguageManager.languageSelected = rb;
	}
}
