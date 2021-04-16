package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos;

import java.time.LocalDate;


public class LocacaoListaDTO {
	private Long id;
	private String carro;
	private String cliente;
	private LocalDate inicioLocacao;	
	private LocalDate fimLocacao;
	
	public LocacaoListaDTO(Long id, LocalDate inicioLocacao, LocalDate fimLocacao, String carro, String cliente) {
		this.id = id;
		this.carro = carro;
		this.inicioLocacao = inicioLocacao;
		this.fimLocacao = fimLocacao;
		this.cliente = cliente;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}

	public LocalDate getInicioLocacao() {
		return inicioLocacao;
	}

	public void setInicioLocacao(LocalDate inicioLocacao) {
		this.inicioLocacao = inicioLocacao;
	}

	public LocalDate getFimLocacao() {
		return fimLocacao;
	}

	public void setFimLocacao(LocalDate fimLocacao) {
		this.fimLocacao = fimLocacao;
	}


	
}
