package br.com.app.store.desconto;

import java.math.BigDecimal;

import br.com.app.store.orcamento.Orcamento;

public class DescontoParaOrcamentoComMaisDeCincoItens  extends Desconto{

	public DescontoParaOrcamentoComMaisDeCincoItens(Desconto proximo) {
		super(proximo);
		// TODO Auto-generated constructor stub
	}

	public BigDecimal efetuarCalculo(Orcamento orcamento) {
			
		return orcamento.getValor().multiply(new BigDecimal("0.1"));		
		
	}

	@Override
	public boolean deveAplicar(Orcamento orcamento) {
		// TODO Auto-generated method stub
		return orcamento.getQuantidadeItens() > 5;
	}
}
