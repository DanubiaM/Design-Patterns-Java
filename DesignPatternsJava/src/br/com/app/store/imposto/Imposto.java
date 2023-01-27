package br.com.app.store.imposto;

import java.math.BigDecimal;

import br.com.app.store.orcamento.Orcamento;

public interface Imposto {

	BigDecimal calcular(Orcamento orcamento); 
}
