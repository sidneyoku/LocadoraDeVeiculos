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

import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.Cliente;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.ClienteRepositorio;
import br.ifpr.paranavai.locadoradeveiculos.locadoradeveiculos.dominio.dtos.ClienteListaDTO;

@Controller
public class ClienteControle {
	
	private ClienteRepositorio clienteRepositorio;
	
	public ClienteControle(ClienteRepositorio clienteRepositorio) {
		this.clienteRepositorio = clienteRepositorio;
	}
	
	@GetMapping("/cliente")
	public String pessoas(Model model, @RequestParam("page") Optional<Integer> pagina, @RequestParam("size") Optional<Integer> tamanho) {
		int paginaAtual = pagina.orElse(1) - 1;
		int tamanhoPagina = tamanho.orElse(5);
		
		PageRequest requisicao = PageRequest.of(paginaAtual, tamanhoPagina, Sort.by("nome"));
		Page<ClienteListaDTO> listaPaginada = clienteRepositorio.findAllClienteListaPaginado(requisicao);
		
		model.addAttribute("listaCliente", listaPaginada);

		int totalPaginas = listaPaginada.getTotalPages();
		if (totalPaginas > 0) {
			List<Integer> numerosPaginas = IntStream.rangeClosed(1, totalPaginas)
						.boxed()
						.collect(Collectors.toList());
			model.addAttribute("numerosPaginas", numerosPaginas);
		}
		
		return "cliente/index";
	}
	
	@GetMapping("/cliente/novo")
	public String novoCarro(Model model) {
		model.addAttribute("cliente", new Cliente(""));
		return "cliente/form";
	}
	
	@GetMapping("/cliente/{id}")
	public String alterarCliente(@PathVariable("id") long id, Model model) {
		Optional<Cliente> clienteOpt = clienteRepositorio.findCompletoById(id);
		if (!clienteOpt.isPresent()) {
			throw new IllegalArgumentException("Cliente inválido.");
		}
		
		model.addAttribute("cliente", clienteOpt.get());
		return "cliente/form";
	}
	
	@PostMapping("/cliente/salvar")
	public String salvarCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "cliente/form";
		}
		
		clienteRepositorio.save(cliente);
		return "redirect:/cliente";
	}
	
	@GetMapping("/cliente/excluir/{id}")
	public String excluirCliente(@PathVariable("id") long id) {
		Optional<Cliente> clienteOpt = clienteRepositorio.findById(id);
		if (clienteOpt.isPresent()) {
			clienteRepositorio.delete(clienteOpt.get());
			return "redirect:/cliente";
		} else {
			throw new IllegalArgumentException("Cliente inválido.");
		}
	}

}
