package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.controle;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.Carros;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.CarrosRepositorio;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.CarrosListaDTO;

@Controller
public class CarrosControle {
	
	private CarrosRepositorio carrosRepositorio;
	
	public CarrosControle(CarrosRepositorio carrosRepositorio) {
		this.carrosRepositorio = carrosRepositorio;
	}
	
	@GetMapping("/inicio/home")
	public String login(Principal principal) {
        if (principal != null) {
            return "redirect:/home";
        }
        return "/inicio/home";
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
	
	 
	@GetMapping("/veiculos/carros/novo")
	public String novoCarro(Model model) {
		model.addAttribute("carros", new Carros(""));
		return "veiculos/carros/form";
	}
	
	@GetMapping("/veiculos/carros/{id}")
	public String alterarCarro(@PathVariable("id") long id, Model model) {
		Optional<Carros> carrosOpt = carrosRepositorio.findCompletoById(id);
		if (!carrosOpt.isPresent()) {
			throw new IllegalArgumentException("Carro inválido.");
		}
		
		model.addAttribute("carros", carrosOpt.get());
		return "veiculos/carros/form";
	}
	
	@PostMapping("/veiculos/carros/salvar")
	public String salvarCarro(@Valid @ModelAttribute("carros") Carros carros, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "veiculos/carros/form";
		}
		
		carrosRepositorio.save(carros);
		return "redirect:/veiculos/carros";
	}
	
	@GetMapping("/veiculos/carros/excluir/{id}")
	public String excluirCarro(@PathVariable("id") long id) {
		Optional<Carros> carroOpt = carrosRepositorio.findById(id);
		if (carroOpt.isPresent()) {
			carrosRepositorio.delete(carroOpt.get());
			return "redirect:/veiculos/carros";
		} else {
			throw new IllegalArgumentException("Carro inválido.");
		}
	}

}
