package br.com.app.store.orcamento;

import java.math.BigDecimal;

public class Orcamento {
	
	private BigDecimal valor;
	private int quantidadeItens;

	
	public Orcamento(BigDecimal valor, int quantidadeItens) {
		this.valor = valor;
		this.quantidadeItens = quantidadeItens;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public int getQuantidadeItens() {
		return this.quantidadeItens;
	}

}
