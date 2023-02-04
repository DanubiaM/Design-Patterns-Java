package br.com.app.store.pedido;

import java.math.BigDecimal;

public class TestePedido {

	public static void main(String[] args) {

		String cliente = "Maria";
		BigDecimal valorOrcamento = BigDecimal.TEN;
		int quantidadeItens = 10;
		
		GerarPedido gerador = new GerarPedido(cliente, valorOrcamento, quantidadeItens);
		GeraPedidoHandler handler = new GeraPedidoHandler(/*dependenciar*/);
		
		handler.executar(gerador);
	}

	

}
