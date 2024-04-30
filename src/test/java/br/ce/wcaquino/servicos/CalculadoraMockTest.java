package br.ce.wcaquino.servicos;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

public class CalculadoraMockTest {

	@Test
	public void teste() {
		Calculadora calc = Mockito.mock(Calculadora.class);
		when(calc.somar(eq(1), anyInt())).thenReturn(5);
		
		System.out.println(calc.somar(1, 8));
	}
	
}
