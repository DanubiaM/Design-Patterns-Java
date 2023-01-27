package br.com.app.store;

import java.math.BigDecimal;

import br.com.app.store.orcamento.Orcamento;

public class TesteImpostos {

	public static void main(String[] args) {
		Orcamento orcamento = new Orcamento(new BigDecimal("100"));
		
		CalculadoraDeImpostos calculadora = new CalculadoraDeImpostos();
		
		System.out.println(calculadora.calcular(orcamento, new ISS()));
		
	}

}
