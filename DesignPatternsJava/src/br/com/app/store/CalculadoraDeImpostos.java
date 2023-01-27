package br.com.app.store;

import java.math.BigDecimal;

import br.com.app.store.orcamento.Orcamento;

public class CalculadoraDeImpostos {

	public BigDecimal calcular(Orcamento orcamento, Imposto imposto) {
		return imposto.calcular(orcamento);
	}
}
