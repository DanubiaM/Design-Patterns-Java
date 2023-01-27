package br.com.app.store;

import java.math.BigDecimal;

import br.com.app.store.imposto.CalculadoraDeImpostos;
import br.com.app.store.imposto.ISS;
import br.com.app.store.orcamento.Orcamento;

public class TesteImpostos {

	public static void main(String[] args) {
		Orcamento orcamento = new Orcamento(new BigDecimal("100"),0);
		
		CalculadoraDeImpostos calculadora = new CalculadoraDeImpostos();
		
		System.out.println(calculadora.calcular(orcamento, new ISS()));
		
	}

}
