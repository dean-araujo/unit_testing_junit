package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

public class CalculadoraTest {

	private Calculadora calc;
	private int a;
	private int b;
	
	@Before
	public void setup() {
		calc = new Calculadora();
		a = 5;
		b = 3;
	}
	
	
	@Test
	public void deveSomarDoisValores() {
		//cenario
		
		//acao
		int resultado = calc.somar(a, b);
		
		//verificacao
		Assert.assertEquals(8, resultado);
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		//cenario
		
		//acao
		int resultado = calc.subtrair(a, b);
		
		//verificacao
		Assert.assertEquals(2, resultado);
	}
	
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		//cenario
		double x = 5.0;
		double y = 2.0;
		
		//acao
		double resultado = calc.dividir(x, y);
		
		//verificacao
		Assert.assertEquals(resultado, resultado, 0.01);
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		int x = 10;
		int y = 0;
		
		calc.dividir(x, y);
	}
	
	@Test
	public void deveDividir() {
		String a = "6";
		String b = "3";
		
		double resultado = calc.dividir(a, b);
		
		Assert.assertEquals(2, resultado, 0.01);
	}
}
