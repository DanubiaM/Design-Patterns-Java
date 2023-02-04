package br.com.app.store.pedido;

import java.time.LocalDateTime;

import br.com.app.store.orcamento.Orcamento;

public class Pedido {
	private String cliente;
	private Orcamento orcamento;
	private LocalDateTime data;
	
	
	public Pedido(String cliente, Orcamento orcamento, LocalDateTime data) {
		this.cliente = cliente;
		this.orcamento = orcamento;
		this.data = data;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Orcamento getOrcamento() {
		return orcamento;
	}
	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}


}
