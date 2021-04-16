package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.controle;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.Carros;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.CarrosRepositorio;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.ClienteRepositorio;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.Locacao;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.LocacaoRepositorio;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.LocacaoListaDTO;

@Controller
public class LocacaoControle {

	private LocacaoRepositorio locacaoRepositorio;
	private CarrosRepositorio carrosRepositorio;
	private ClienteRepositorio clienteRepositorio;

	public LocacaoControle(LocacaoRepositorio locacaoRepositorio, CarrosRepositorio carrosRepositorio, ClienteRepositorio clienteRepositorio) {
		this.locacaoRepositorio = locacaoRepositorio;
		this.carrosRepositorio = carrosRepositorio;
		this.clienteRepositorio = clienteRepositorio;
	}


	@GetMapping("/locacoes")
	public String locacoes(Model model, @RequestParam("page") Optional<Integer> pagina, @RequestParam("size") Optional<Integer> tamanho) {
		int paginaAtual = pagina.orElse(1) - 1;
		int tamanhoPagina = tamanho.orElse(5);
		
		PageRequest requisicao = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("id"));
		Page<LocacaoListaDTO> listaPaginada = locacaoRepositorio.findAllLocacaoListaPaginado(requisicao);
		
		model.addAttribute("listaLocacao", listaPaginada);

		int totalPaginas = listaPaginada.getTotalPages();
		if (totalPaginas > 0) {
			List<Integer> numerosPaginas = IntStream.rangeClosed(1, totalPaginas)
						.boxed()
						.collect(Collectors.toList());
			model.addAttribute("numerosPaginas", numerosPaginas);
		}
		
		return "locacoes/index";
	}

	
	@GetMapping("/locacoes/nova")
	public String novaLocacao(Model model) {
		model.addAttribute("locacao", new Locacao());
		model.addAttribute("carros", carrosRepositorio.findAll());
		model.addAttribute("clientes", clienteRepositorio.findAll());
		return "locacoes/form";
	}

	@GetMapping("/locacoes/{id}")
	public String alterarLocacao(@PathVariable("id") long id, Model model) {
		Optional<Locacao> locacaoOpt = locacaoRepositorio.findById(id);
		if (locacaoOpt.isEmpty()) {
			throw new IllegalArgumentException("Locação inválida.");
		}

		model.addAttribute("locacao", locacaoOpt.get());
		model.addAttribute("clientes", clienteRepositorio.findAll());
		model.addAttribute("carros", carrosRepositorio.findAll());
		return "locacoes/form";
	}

	@PostMapping("/locacoes/salvar")
	public String salvarLocacao(@Valid @ModelAttribute("locacao") Locacao locacao, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("clientes", clienteRepositorio.findAll());
			model.addAttribute("carros", carrosRepositorio.findAll());
			return "locacoes/form";
		}

		locacaoRepositorio.save(locacao);
		return "redirect:/locacoes";
	}
	
	@GetMapping("/locacoes/excluir/{id}")
	public String excluirLocacao(@PathVariable("id") long id) {
		Optional<Locacao> locacaoOpt = locacaoRepositorio.findById(id);
		if (locacaoOpt.isPresent()) {
			locacaoRepositorio.delete(locacaoOpt.get());
			return "redirect:/locacoes";
		} else {
			throw new IllegalArgumentException("Locação inválida.");
		}
	}

}
