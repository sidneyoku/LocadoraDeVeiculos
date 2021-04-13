package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos;

import javax.validation.constraints.NotBlank; 
import javax.validation.constraints.NotNull;
 
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.Funcionario;

public class RoleFuncionarioForm {

	private String username;
	@NotNull
	private long id;
	@NotBlank
	private String role;
	 
	
	public RoleFuncionarioForm() {}

	public RoleFuncionarioForm(Funcionario funcionario) {
		this.username = funcionario.getUsername();
		this.id = funcionario.getId();
		this.role = funcionario.getRole();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
