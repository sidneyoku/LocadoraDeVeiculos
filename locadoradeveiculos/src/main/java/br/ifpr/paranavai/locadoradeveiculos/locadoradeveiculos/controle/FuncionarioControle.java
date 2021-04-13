package br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.controle;

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

import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.Funcionario;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.FuncionarioRepositorio;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.FuncionarioListaDTO;  

@Controller
public class FuncionarioControle {
	
	private FuncionarioRepositorio funcionarioRepositorio;
	
	public FuncionarioControle(FuncionarioRepositorio funcionarioRepositorio) {
		this.funcionarioRepositorio = funcionarioRepositorio;
	}
	
	@GetMapping("/funcionario")
	public String pessoas(Model model, @RequestParam("page") Optional<Integer> pagina, @RequestParam("size") Optional<Integer> tamanho) {
		int paginaAtual = pagina.orElse(1) - 1;
		int tamanhoPagina = tamanho.orElse(5);
		
		PageRequest requisicao = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("nome"));
		Page<FuncionarioListaDTO> listaPaginada = funcionarioRepositorio.findAllFuncionarioListaPaginado(requisicao);
		
		model.addAttribute("listaFuncionario", listaPaginada);

		int totalPaginas = listaPaginada.getTotalPages();
		if (totalPaginas > 0) {
			List<Integer> numerosPaginas = IntStream.rangeClosed(1, totalPaginas)
						.boxed()
						.collect(Collectors.toList());
			model.addAttribute("numerosPaginas", numerosPaginas);
		}
		
		return "funcionario/index";
	}
	
	@GetMapping("/funcionario/novo")
	public String novoCarro(Model model) {
		model.addAttribute("funcionario", new Funcionario(""));
		return "funcionario/form";
	}
	
	@GetMapping("/funcionario/{id}")
	public String alterarFuncionario(@PathVariable("id") long id, Model model) {
		Optional<Funcionario> funcionarioOpt = funcionarioRepositorio.findCompletoById(id);
		if (!funcionarioOpt.isPresent()) {
			throw new IllegalArgumentException("Funcionario inválido.");
		}
		
		model.addAttribute("funcionario", funcionarioOpt.get());
		return "funcionario/form";
	}
	
	@PostMapping("/funcionario/salvar")
	public String salvarFuncionario(@Valid @ModelAttribute("funcionario") Funcionario funcionario, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "funcionario/form";
		}
		
		funcionarioRepositorio.save(funcionario);
		return "redirect:/funcionario";
	}
	
	@GetMapping("/funcionario/excluir/{id}")
	public String excluirFuncionario(@PathVariable("id") long id) {
		Optional<Funcionario> funcionarioOpt = funcionarioRepositorio.findById(id);
		if (funcionarioOpt.isPresent()) {
			funcionarioRepositorio.delete(funcionarioOpt.get());
			return "redirect:/funcionario";
		} else {
			throw new IllegalArgumentException("Funcionario inválido.");
		}
	}

}
