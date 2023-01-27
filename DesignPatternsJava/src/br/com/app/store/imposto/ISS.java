package br.com.app.store.imposto;

import java.math.BigDecimal;

import br.com.app.store.orcamento.Orcamento;

public class ISS implements Imposto{

	public BigDecimal calcular(Orcamento orcamento) {
		return orcamento.getValor().multiply(new BigDecimal("0.06"));
	}
}
