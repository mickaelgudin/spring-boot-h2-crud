package com.spring.crud.demo.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
	private static ResourceBundle languageSelected;
	public static Locale languageCode = Locale.FRENCH;
	private static boolean languageHasChange = false;
	public static boolean testIsRunning = false;
	
	/**
	 * read bundle for selected language
	 * (clearing cache to have up to date text from properties file without recompilation)
	 * @return
	 */
	public static ResourceBundle get() {
		if(!testIsRunning) {
			ResourceBundle.clearCache();
			
			if(languageSelected == null || languageHasChange == true) {
				languageHasChange = false;
				return ResourceBundle.getBundle("language", languageCode);
			} 
		}
		
		return languageSelected;
	}
	
	/**
	 * useful for unit test
	 * @param rb
	 */
	public static void set(ResourceBundle rb) {
		LanguageManager.languageSelected = rb;
	}
	
	/**
	 * LanguageController change the selected language that way
	 * @param language
	 */
	public static void setLanguageCode(Locale language) {
		languageHasChange = true;
		languageCode = language;
	}
}
