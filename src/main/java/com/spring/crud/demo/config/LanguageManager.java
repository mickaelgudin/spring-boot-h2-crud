package com.spring.crud.demo.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
	public static ResourceBundle languageSelected = 
			   ResourceBundle.getBundle(LanguageManager.class.getPackageName()+".language", Locale.FRENCH) ;
}
