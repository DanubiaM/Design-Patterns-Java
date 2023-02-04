package br.com.app.store.pedido;

import java.time.LocalDateTime;

import br.com.app.store.orcamento.Orcamento;

public class GeraPedidoHandler {

	//Aqui iria: repository, service e outras dependencias
	
	public void executar(GerarPedido dados) {
		
		Orcamento orcamento = new Orcamento(dados.getValorOrcamento(), dados.getQuantidadeItens());	
		
		
		Pedido pedido = new Pedido(dados.getCliente(),orcamento, LocalDateTime.now());
	}
}
