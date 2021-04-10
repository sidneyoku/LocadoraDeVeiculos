package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.Carros;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.CarrosRepositorio;

@Component
@Transactional
public class PopulacaoInicialBanco implements CommandLineRunner {
	
	@Autowired
	private CarrosRepositorio carrosRepositorio;

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
		
	}

}
