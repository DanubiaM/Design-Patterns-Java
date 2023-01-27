package br.com.app.store.desconto;

import java.math.BigDecimal;

import br.com.app.store.orcamento.Orcamento;

public class DescontoParaOrcamentoComMaisDeCincoItens  extends Desconto{

	public DescontoParaOrcamentoComMaisDeCincoItens(Desconto proximo) {
		super(proximo);
		// TODO Auto-generated constructor stub
	}

	public BigDecimal calcular(Orcamento orcamento) {
		
		if(orcamento.getQuantidadeItens() > 5 ) {
			return orcamento.getValor().multiply(new BigDecimal("0.1"));
		}
		
		return proximo.calcular(orcamento);
	}
}
