package com.openclassrooms.testing.calcul.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.testing.calcul.domain.Calculator;
import com.openclassrooms.testing.calcul.domain.model.CalculationModel;
import com.openclassrooms.testing.calcul.domain.model.CalculationType;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {

	@Mock
	Calculator calculator;

	CalculatorService classUnderTest;

	@BeforeEach
	public void init() {
		classUnderTest = new CalculatorServiceImpl(calculator);
	}

	@Test
	public void calculate_shouldUseCalculator_forAddition() {
		// GIVEN
		when(calculator.add(1, 2)).thenReturn(3);

		// WHEN
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, 1, 2)).getSolution();

		// THEN
		verify(calculator).add(1, 2);
		assertThat(result).isEqualTo(3);
	}

	// test avec des parametres d entrée aleatoire et calculator qui prend en
	// parametre n'importe quelle valeur mais du type demandé
	@Test
	public void calculate_shouldUseCalculator_forAnyAddition() {
		// GIVEN
		final Random r = new Random();
		Integer argLeft = r.nextInt();
		Integer argRight = r.nextInt();
		when(calculator.add(any(Integer.class), any(Integer.class))).thenReturn(argLeft + argRight);

		// WHEN
		final int result = classUnderTest.calculate(new CalculationModel(CalculationType.ADDITION, argLeft, argRight))
				.getSolution();

		// THEN
		verify(calculator).add(any(Integer.class), any(Integer.class));
		assertThat(result).isEqualTo(argLeft + argRight);
	}

	@Test
	public void calculate_shouldThrowIllegalArgumentException_forADivisionBy0() {
		// GIVEN
		when(calculator.divide(1, 0)).thenThrow(new ArithmeticException());

		// WHEN
		assertThrows(IllegalArgumentException.class, () -> classUnderTest.calculate(
				new CalculationModel(CalculationType.DIVISION, 1, 0)));

		// THEN
		verify(calculator, times(1)).divide(1, 0);
	}

	@Test
	public void calculate_shouldFormatSolution_forAnAddition() {
		// GIVEN
		when(calculator.add(10000, 3000)).thenReturn(13000);

		// WHEN
		final String formattedResult = classUnderTest
		    .calculate(new CalculationModel(CalculationType.ADDITION, 10000, 3000))
    		.getFormattedSolution();

		// THEN
		assertThat(formattedResult).isEqualTo("13 000");
	}

}