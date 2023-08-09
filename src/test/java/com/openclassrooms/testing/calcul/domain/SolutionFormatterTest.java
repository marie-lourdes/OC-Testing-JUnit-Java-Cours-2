package com.openclassrooms.testing.calcul.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolutionFormatterTest {
	
	private SolutionFormatter solutionFormatter;
	
	@BeforeEach
	public void initFormatter() {
		solutionFormatter = new SolutionFormatter();
	}
	
	@Test
	public void format_shouldFormatAnyBigNumber() {
		// GIVEN
		int number = 1234567890;
		
		// WHEN
		String result = solutionFormatter.format(number);
		
		// THEN 
		// integrer les espace de la methode String.format appele depuis la method format de la class SolutionFormatter, dans le resultat attendu
		// sinon le result sera faussement echoué car  formt() n a pas d espace standard 
		//assertThat(result).isEqualTo("1 234 567 890");// test echoue
		assertThat(result).isEqualTo("1 234 567 890");// test reussi
	}
	
}
