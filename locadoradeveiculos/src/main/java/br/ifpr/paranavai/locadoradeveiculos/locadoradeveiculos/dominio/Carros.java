package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "O campo ano não pode ser vazio")
	@Column(nullable = false)
	private Integer ano;
	
	@NotNull(message = "O campo quilometragem não pode ser vazio")
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
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Carros [nome=" + nome + "]";
	}
	
}
