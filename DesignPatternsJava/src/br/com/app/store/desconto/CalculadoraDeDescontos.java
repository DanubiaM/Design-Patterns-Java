package br.com.app.store.desconto;

import java.math.BigDecimal;

import br.com.app.store.orcamento.Orcamento;

public class CalculadoraDeDescontos {
	
	public BigDecimal calcular(Orcamento orcamento) {
		
		Desconto desconto  = 
				new DescontoParaOrcamentoComMaisDeCincoItens(
				new DescontoParaOrcamentoComValorMaiorQueQuinhentos( new SemDesconto()));
		
		return desconto.calcular(orcamento);
	}

}
