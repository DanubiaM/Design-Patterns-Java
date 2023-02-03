package br.com.app.store.orcamento.situacao;

import java.math.BigDecimal;

import br.com.app.store.DomainException;
import br.com.app.store.orcamento.Orcamento;

public class Reprovado extends SituacaoOrcamento {

	
	public void finalizar(Orcamento orcamento) {
		orcamento.setSituacao(new Finalizado());
	}
	
}
