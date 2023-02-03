package br.com.app.store.orcamento.situacao;

import java.math.BigDecimal;

import br.com.app.store.DomainException;
import br.com.app.store.orcamento.Orcamento;

public class EmAnalise extends SituacaoOrcamento {

	
	public BigDecimal calcularValorDescontoExtra(Orcamento orcamento) {
		return orcamento.getValor().multiply(new BigDecimal("0.05"));
	}
	

	public void reprovar(Orcamento orcamento) {
		super.finalizar(orcamento);
	}
	
	public void aprovar(Orcamento orcamento) {
		super.aprovar(orcamento);
	}
	
}
