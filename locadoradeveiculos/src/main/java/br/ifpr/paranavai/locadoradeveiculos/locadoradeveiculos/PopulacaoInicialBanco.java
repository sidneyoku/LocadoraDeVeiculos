package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.Carros;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.CarrosRepositorio;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.Cliente;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.ClienteRepositorio;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.Funcionario;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.FuncionarioRepositorio;

@Component
@Transactional
public class PopulacaoInicialBanco implements CommandLineRunner {
	
	@Autowired
	private CarrosRepositorio carrosRepositorio;
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Autowired
	private FuncionarioRepositorio funcionarioRepositorio;

	@Override
	public void run(String... args) throws Exception {
		Carros carros1 = new Carros("McLaren P1");
		carros1.setPlaca("BEER2021");
		carros1.setDataAquisicao(LocalDate.now());
		carros1.setCor("Prata");
		carros1.setAno(2021);
		carros1.setQuilometragem(10);
		
		Carros carros2 = new Carros("LaFerrari");
		carros2.setAno(2021);
		carros2.setCor("Vermelho");
		carros2.setDataAquisicao(LocalDate.now());
		carros2.setPlaca("BE2021ER");
		carros2.setQuilometragem(10);
		
		carrosRepositorio.save(carros1);
		carrosRepositorio.save(carros2);
		
		Cliente cliente1 = new Cliente("Sidney");
		cliente1.setDataNascimento(LocalDate.now());
		cliente1.setCpf("01234567890");
		cliente1.setEmail("sidney@locadoradeveiculosteste.com.br");
		cliente1.setTelefone("44123456789");
		
		Cliente cliente2 = new Cliente("Adriel");
		cliente2.setDataNascimento(LocalDate.now());
		cliente2.setCpf("01234567890");
		cliente2.setEmail("adriel@locadoradeveiculosteste.com.br");
		cliente2.setTelefone("41123456789");
		
		clienteRepositorio.save(cliente1);
		clienteRepositorio.save(cliente2);
		
		Funcionario funfionario = new Funcionario("Adriel");
		funfionario.setUsuario("adriel");
		funfionario.setSenha("123"); 
		funfionario.setEmail("adriel@locadoradeveiculosteste.com.br");
		funfionario.setTelefone("41123456789");
		
	
		funcionarioRepositorio.save(funfionario);
		
	}

}
