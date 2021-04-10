package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos;

import java.time.LocalDate;

public class CarrosListaDTO {
	
	private Long id;
	private String nome;
	private String placa;
	private LocalDate dataAquisicao;
	private String cor;
	private Integer ano;
	private long quilometragem;
	
	public CarrosListaDTO(
			Long id, 
			String nome, 
			String placa, 
			LocalDate dataAquisicao, 
			String cor, 
			Integer ano,
			long quilometragem) {
		this.id = id;
		this.nome = nome;
		this.placa = placa;
		this.dataAquisicao = dataAquisicao;
		this.cor = cor;
		this.ano = ano;
		this.quilometragem = quilometragem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public LocalDate getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(LocalDate dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public long getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(long quilometragem) {
		this.quilometragem = quilometragem;
	}
	
}
