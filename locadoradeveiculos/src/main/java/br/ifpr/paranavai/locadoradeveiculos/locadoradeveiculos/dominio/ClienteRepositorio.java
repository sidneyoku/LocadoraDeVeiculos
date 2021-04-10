package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.ClienteListaDTO;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{

	@Query(value = "select new br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.ClienteListaDTO(c.id, c.nome, c.dataNascimento, c.cpf, c.email, c.telefone)"
			+ "from Cliente c ", countQuery = "select count(c) from Cliente c")
	Page<ClienteListaDTO> findAllClienteListaPaginado(Pageable pageable);
	
	@Query("select c from Cliente c where c.id = :indice")
	Optional<Cliente> findCompletoById(@Param("indice") Long id);
}
