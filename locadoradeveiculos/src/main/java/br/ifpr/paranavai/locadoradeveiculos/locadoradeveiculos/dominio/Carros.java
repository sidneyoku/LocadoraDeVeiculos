package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Carros {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "O campo nome não pode ser vazio")
	@Column(nullable = false)
	private String nome;
	
	@NotBlank(message = "O campo placa não pode ser vazio")
	@Column(nullable = false)
	private String placa;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataAquisicao;
	
	@NotBlank(message = "O campo cor não pode ser vazio")
	@Column(nullable = false)
	private String cor;
	
	@NotBlank(message = "O campo ano não pode ser vazio")
	@Column(nullable = false)
	private Integer ano;
	
	@NotBlank(message = "O campo quilometragem não pode ser vazio")
	@Column(nullable = false)
	private long quilometragem;
	
	@Deprecated
	protected Carros() {}
	
	public Carros(String nome) {
		this.nome = nome;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carros other = (Carros) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Carros [nome=" + nome + "]";
	}
	
}
