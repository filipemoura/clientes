package br.projeto.clientes.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.projeto.clientes.model.entity.Cliente;
import br.projeto.clientes.model.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	private final ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteController(ClienteRepository clienteRepository) {
		this. clienteRepository = clienteRepository;
	}
	
	@GetMapping
	public List<Cliente> obterTodos() {
		return clienteRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@GetMapping("{id}")
	public Cliente acharPorId(@PathVariable Integer id) {
		return clienteRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		clienteRepository
		.findById(id)
		.map( cliente -> {
			clienteRepository.delete(cliente);
			return Void.TYPE;
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
		clienteRepository
		.findById(id)
		.map( cliente -> {
			clienteAtualizado.setId(cliente.getId());
			return clienteRepository.save(clienteAtualizado);
		})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
