package com.openclassrooms.testing.calcul.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

}