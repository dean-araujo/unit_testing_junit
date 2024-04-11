package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Filme;

public class FilmeBuilder {

	private Filme filme;
	
	private FilmeBuilder() {
	}
	
	public static FilmeBuilder umFilme() {
		FilmeBuilder builder = new FilmeBuilder();
		builder.filme = new Filme("Filme 1", 2, 4.0);
		return builder;
	}
	
	public FilmeBuilder semEstoque() {
		filme.setEstoque(0);
		return this;
	}
	
	public FilmeBuilder comValor(Double valor) {
		filme.setPrecoLocacao(valor);
		return this;
	}
	
	public Filme agora() {
		return filme;
	}
}
