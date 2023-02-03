package br.com.app.store.orcamento.situacao;

import java.math.BigDecimal;

import br.com.app.store.DomainException;
import br.com.app.store.orcamento.Orcamento;

public class Aprovado extends SituacaoOrcamento {

	
	public BigDecimal calcularValorDescontoExtra(Orcamento orcamento) {
		return orcamento.getValor().multiply(new BigDecimal("0.02"));
	}
	

	public void finalizar(Orcamento orcamento) {
		orcamento.setSituacao(new Finalizado());
	}
	
}
