package br.com.fujioka.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Utilitarios {

	public static String formateDateTime(Long date) {
		Locale locale = new Locale("pt","br");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYY - HH:mm:ss", locale);
		return dateFormat.format(date*1000);
	}
}
