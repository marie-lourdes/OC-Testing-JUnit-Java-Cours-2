package com.openclassrooms.testing.calcul.service;

import java.util.Locale;

public class SolutionFormatterImpl implements SolutionFormatter {

	@Override
	public String format(int solution) {
		// la methode String.format() utilise la classe formatter avec les placeholder %
		// suivi d'un converter %d = formatter des donnée par ex du type double
		return String.format(Locale.FRENCH, "%,d", solution);
		// "%,d " le pourecntage recupere solution et separe solution par des decimals
		// avec local.French avec des espaces(ex; 1 300 000) et non des virgules comme
		// le format americain(1,300,000)

		/*
		 * Exemple executé avec jshell terminal, le placeholder % recupere le nombre et
		 * est separe par des decimals d= double, mais locale US il y a les virgule
		 * String.format(new Locale("en","US"), "%,d",1300000) $1 ==> "1,300,000"
		 */
		// lors d un calcul le result par defaut de 1000000+ 300000 = 1300000 sans
		// espace ni viragule d'ou l importance de formater soit avec des virgule(format
		// US) ou espace (format français)
	}
}
