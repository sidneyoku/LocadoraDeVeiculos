package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
 
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.FuncionarioListaDTO;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long>{

	@Query(value = "select new br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.FuncionarioListaDTO(c.id, c.nome, c.usuario, c.senha, c.email, c.telefone)"
			+ "from Funcionario c ", countQuery = "select count(c) from Funcionario c")
	Page<FuncionarioListaDTO> findAllFuncionarioListaPaginado(Pageable pageable);
	
	@Query("select c from Funcionario c where c.id = :indice")
	Optional<Funcionario> findCompletoById(@Param("indice") Long id);
}
