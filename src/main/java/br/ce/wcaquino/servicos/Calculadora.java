package br.ce.wcaquino.servicos;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;

public class Calculadora {

	public int somar(int a, int b) {
		return a + b;
	}

	public int subtrair(Integer a, Integer b) {
		return a - b;
	}

	public double dividir(double x, double y) throws NaoPodeDividirPorZeroException {
		if (y == 0) {
			throw new NaoPodeDividirPorZeroException();
		}
		return x / y;
	}
	
	public double dividir(String a, String b) {
		return Integer.valueOf(a) / Integer.valueOf(b);
	}

}
