package br.com.app.store;

import java.math.BigDecimal;

import br.com.app.store.orcamento.Orcamento;
import br.com.app.store.orcamento.situacao.Aprovado;

public class TesteDescontoExtra {

	public static void main(String[] args) {
		Orcamento orcamento = new Orcamento(new BigDecimal("100"),5);	
		
		
		orcamento.aplicarDescontoExtra();
		
		System.out.println(orcamento.getValor());		


		orcamento.setSituacao(new Aprovado());
		
		orcamento.aplicarDescontoExtra();
		
		System.out.println(orcamento.getValor());
	}

}
