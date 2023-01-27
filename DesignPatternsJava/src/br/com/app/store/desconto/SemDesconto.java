package br.com.app.store.desconto;

import java.math.BigDecimal;

import br.com.app.store.orcamento.Orcamento;

public class SemDesconto extends Desconto{

	public SemDesconto() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	public BigDecimal calcular(Orcamento orcamento) {
		
		return BigDecimal.ZERO;
	}
}
