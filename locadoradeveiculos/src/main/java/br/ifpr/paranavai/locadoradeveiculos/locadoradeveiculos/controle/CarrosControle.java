package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.controle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.CarrosRepositorio;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.CarrosListaDTO;

@Controller
public class CarrosControle {
	
	private CarrosRepositorio carrosRepositorio;
	
	public CarrosControle(CarrosRepositorio carrosRepositorio) {
		this.carrosRepositorio = carrosRepositorio;
	}
	
	@GetMapping("/veiculos/carros")
	public String pessoas(Model model, @RequestParam("page") Optional<Integer> pagina, @RequestParam("size") Optional<Integer> tamanho) {
		int paginaAtual = pagina.orElse(1) - 1;
		int tamanhoPagina = tamanho.orElse(5);
		
		PageRequest requisicao = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("nome"));
		Page<CarrosListaDTO> listaPaginada = carrosRepositorio.findAllCarrosListaPaginado(requisicao);
		
		model.addAttribute("listaCarros", listaPaginada);

		int totalPaginas = listaPaginada.getTotalPages();
		if (totalPaginas > 0) {
			List<Integer> numerosPaginas = IntStream.rangeClosed(1, totalPaginas)
						.boxed()
						.collect(Collectors.toList());
			model.addAttribute("numerosPaginas", numerosPaginas);
		}
		
		return "veiculos/carros/index";
	}

}
