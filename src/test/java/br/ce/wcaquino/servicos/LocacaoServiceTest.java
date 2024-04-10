package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static br.ce.wcaquino.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	private LocacaoService service;
	private List<Filme> filmes;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before //Executa antes de cada teste
	public void setup() {
		service = new LocacaoService();
		filmes = new ArrayList<Filme>();
	}
	
	/*@After //Executa após cada teste
	public void tearDown() {
		System.out.println("After");
	}
	
	@BeforeClass //Executa antes da instanciação da classe
	public static void setupClass() {
		System.out.println("Before Class");
	}
	
	@AfterClass //Executa após a instanciação da classe
	public static void tearDownClass() {
		System.out.println("After Class");
	}*/
	
	@Test
	public void deveAlugarFilme() throws Exception {
		//cenario
		Usuario usuario = new Usuario("Usuario");
		filmes.add(new Filme("Filme 1", 1, 5.0));
		
		//acao
		Locacao locacao = service.alugarFilmes(usuario, filmes);
		
		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
	
	@Test(expected=FilmeSemEstoqueException.class) //Forma Elegante - simples, mas não consegue ver a msg da exception
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		//cenario
		Usuario usuario = new Usuario("Usuario");
		filmes.add(new Filme("Filme 1", 0, 4.0));
		
		//acao
		service.alugarFilmes(usuario, filmes);
	}
	
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
		//cenario
		Usuario usuario = null;
		filmes.add(new Filme("Filme 1", 1, 4.0));
		
		//acao
		try {
			service.alugarFilmes(usuario, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	
	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Usuario");

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//acao
		service.alugarFilmes(usuario, null);
	}
	
	@Test
	public void devePagar75PctNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Usuario");
		filmes.add(new Filme("Filme 1", 2, 4.0));
		filmes.add(new Filme("Filme 2", 2, 4.0));
		filmes.add(new Filme("Filme 3", 2, 4.0));
		
		//acao
		Locacao resultado = service.alugarFilmes(usuario, filmes);
		
		//verificacao
		assertThat(resultado.getValor(), is(11.0));
	}
	
	@Test
	public void devePagar50PctNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Usuario");
		filmes.add(new Filme("Filme 1", 2, 4.0));
		filmes.add(new Filme("Filme 2", 2, 4.0));
		filmes.add(new Filme("Filme 3", 2, 4.0));
		filmes.add(new Filme("Filme 4", 2, 4.0));
		
		//acao
		Locacao resultado = service.alugarFilmes(usuario, filmes);
		
		//verificacao
		assertThat(resultado.getValor(), is(13.0));
	}
	
	@Test
	public void devePagar25PctNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Usuario");
		filmes.add(new Filme("Filme 1", 2, 4.0));
		filmes.add(new Filme("Filme 2", 2, 4.0));
		filmes.add(new Filme("Filme 3", 2, 4.0));
		filmes.add(new Filme("Filme 4", 2, 4.0));
		filmes.add(new Filme("Filme 5", 2, 4.0));
		
		//acao
		Locacao resultado = service.alugarFilmes(usuario, filmes);
		
		//verificacao
		assertThat(resultado.getValor(), is(14.0));
	}
	
	@Test
	public void devePagar0PctNoFilme6() throws FilmeSemEstoqueException, LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Usuario");
		filmes.add(new Filme("Filme 1", 2, 4.0));
		filmes.add(new Filme("Filme 2", 2, 4.0));
		filmes.add(new Filme("Filme 3", 2, 4.0));
		filmes.add(new Filme("Filme 4", 2, 4.0));
		filmes.add(new Filme("Filme 5", 2, 4.0));
		filmes.add(new Filme("Filme 6", 2, 4.0));
		
		//acao
		Locacao resultado = service.alugarFilmes(usuario, filmes);
		
		//verificacao
		assertThat(resultado.getValor(), is(14.0));
	}
	
	@Test
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Usuario");
		filmes.add(new Filme("Filme 1", 2, 5.0));
		
		//acao
		Locacao retorno = service.alugarFilmes(usuario, filmes);
		
		//verificacao
		boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
		Assert.assertTrue(ehSegunda);
	}
}
