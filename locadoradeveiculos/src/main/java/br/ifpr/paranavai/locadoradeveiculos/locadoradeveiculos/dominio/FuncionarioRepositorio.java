package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio;
 
 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;
  
@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Long>{
  
	Funcionario findByUsername(String username);
}
