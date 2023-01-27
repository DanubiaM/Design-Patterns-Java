package br.com.app.store;

import java.math.BigDecimal;

import br.com.app.store.desconto.CalculadoraDeDescontos;
import br.com.app.store.imposto.ISS;
import br.com.app.store.orcamento.Orcamento;

public class TesteDescontos {

	public static void main(String[] args) {
		Orcamento orcamento = new Orcamento(new BigDecimal("100"),5);
		Orcamento orcamento2 = new Orcamento(new BigDecimal("1000"),1);

		CalculadoraDeDescontos calculadora = new CalculadoraDeDescontos();
		
		System.out.println(calculadora.calcular(orcamento));
		System.out.println(calculadora.calcular(orcamento2));

	}

}
