package br.com.app.store;

import java.math.BigDecimal;

import br.com.app.store.orcamento.Orcamento;

public class ICMS implements Imposto{

	public BigDecimal calcular(Orcamento orcamento) {
		return orcamento.getValor().multiply(new BigDecimal("0.1"));
	}
}
