package com.spring.crud.demo.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class that manage selected language, useful for the front end when custom
 * error are throwed by the back end(ex: if we try to update a non existing
 * train station) so that the website has full multilanguage support
 * 
 * @author mickaelgudin
 */
public class LanguageManager {
	private static ResourceBundle languageSelected;
	public static boolean testIsRunning = false;

	private LanguageManager() {

	}

	/**
	 * read bundle for selected language (clearing cache to have up to date text
	 * from properties file without recompilation)
	 * 
	 * @return
	 */
	public static ResourceBundle get(String languageCode) {
		languageCode = languageCode.toLowerCase();
		
		if (!testIsRunning) {
			ResourceBundle.clearCache();
			
			Locale langue = null;
			if(languageCode.equals("fr")) {
				langue = Locale.FRENCH;
			} else if(languageCode.equals("en")) {
				langue = Locale.ENGLISH;
			}
			
			return ResourceBundle.getBundle("language", langue);
		}

		return languageSelected;
	}

	/**
	 * useful to change the properties file for unit test
	 * 
	 * @param rb
	 */
	public static void set(ResourceBundle rb) {
		LanguageManager.languageSelected = rb;
	}
}
