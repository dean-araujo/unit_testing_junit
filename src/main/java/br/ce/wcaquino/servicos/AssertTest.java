package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {

	@Test
	public void teste() {
		//Assert True / False
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		
		//Assert Equals
		//Primeiro se coloca o valor esperado, depois o atual
		Assert.assertEquals(1, 1); // Também espera tipos iguais
		Assert.assertEquals(0.51234, 0.512, 0.01); // Se trabalhando c/ nº com casas decimal,
											 		// é necessário incluir um delta como margem de erro.
		int i = 5;
		Integer i2 = 5;
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals("bola", "bola"); //case sensitive
		Assert.assertNotEquals("bola", "casa");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bo"));
		
		//Using Equals
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");
		Usuario u3 = u2;
		Usuario u4 = null;
		
		/*assertEquals procura o método Equals implementado.
		Quando não está implementado, implementa o Object,
		cuja comparação é dada por ponteiros.
		Portanto, é necessário implementar o método Equals.*/
		Assert.assertEquals(u1, u2);
		
		//Caso queira ver se o obj é completamente igual,
		//utiliza-se o assertSame
		Assert.assertSame(u2, u3);
		Assert.assertNotSame(u1, u2);
		
		//Is Null?
		Assert.assertNull(u4);
		Assert.assertNotNull(u3);
		
		//No início, pode-se colocar uma String que será
		//apresentada caso ocorra algum erro de comparação
		Assert.assertEquals("Erro de comparação", 1, 1);
		
		
	}
}
