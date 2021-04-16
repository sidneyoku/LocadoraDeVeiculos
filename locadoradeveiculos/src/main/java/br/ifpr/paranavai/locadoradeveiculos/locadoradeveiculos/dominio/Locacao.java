package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity

public class Locacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Carros carro;
	
	@ManyToOne
	private Cliente cliente;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate inicioLocacao;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fimLocacao;
	
	private String status;
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Carros getCarro() {
		return carro;
	}

	public void setCarro(Carros carro) {
		this.carro = carro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
	
	/*
	 * public Locacao(LocalDate inicioLocacao) { this.inicioLocacao = inicioLocacao;
	 * }
	 */
	
	
	
	
}
