package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.CarrosListaDTO;

@Repository
public interface CarrosRepositorio extends JpaRepository<Carros, Long>{

//	@Query(value = "select new br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.CarrosListaDTO(c.id, c.nome, c.placa, c.dataAquisicao, c.cor, c.ano, c.quilometragem)"
//			+ "from Carros p left join p.departamento d ", countQuery = "select count(p) from Pessoa p")
	@Query(value = "select new br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.CarrosListaDTO(c.id, c.nome, c.placa, c.dataAquisicao, c.cor, c.ano, c.quilometragem)"
			+ "from Carros c ", countQuery = "select count(c) from Carros c")
	Page<CarrosListaDTO> findAllCarrosListaPaginado(Pageable pageable);
	
}
