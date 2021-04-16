package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.LocacaoListaDTO;

@Repository
public interface LocacaoRepositorio extends JpaRepository<Locacao, Long> {

	
	@Query("select new br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.LocacaoListaDTO(p.id, p.inicioLocacao, p.fimLocacao, d.nome, ec.nome, p.status)"
			+ "from Locacao p left join p.carro d left join p.cliente ec")
	List<LocacaoListaDTO> findAllLocacaoLista();
	
	@Query(value = "select new br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.LocacaoListaDTO(p.id, p.inicioLocacao, p.fimLocacao, d.nome, ec.nome, p.status)"
			+ "from Locacao p left join p.carro d left join p.cliente ec ", countQuery = "select count(p) from Locacao p")
	Page<LocacaoListaDTO> findAllLocacaoListaPaginado(Pageable pageable);
	
	/*
	 * @Query("select p from Locacao p left join fetch p.carro c where p.id = :indice"
	 * ) Optional<Locacao> findCompletoById(@Param("indice") Long id);
	 */
	
	@Query("select p from Locacao p left join fetch p.carro c left join fetch p.cliente ec where p.id = :indice")
	Optional<Locacao> findCompletoById(@Param("indice") Long id);
}
