package com.spring.crud.demo.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.spring.crud.demo.controller.LanguageController;

/**
 * Class that manage selected language, useful for the front end 
 * when custom error are throwed by the back end(ex: if we try to update a non existing train station)
 * so that the website has full multilanguage support
 * 
 * @author mickaelgudin
 * @see LanguageController the rest controller unabling the front end to change back end language
 */
public class LanguageManager {
	private static ResourceBundle languageSelected;
	public static Locale languageCode = Locale.FRENCH;
	public static List<Locale> allowedLanguage = new ArrayList<>(Arrays.asList(Locale.FRENCH, Locale.ENGLISH));
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
	 * useful to change the properties file for unit test
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
		if(allowedLanguage.contains(language)) {
			languageHasChange = true;
			languageCode = language;
		}
	}
}
