package br.com.pan.bluebank.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pan.bluebank.docs.ClienteDocs;
import br.com.pan.bluebank.dtos.ClienteDTO;
import br.com.pan.bluebank.dtos.response.MessageResponse;
import br.com.pan.bluebank.dtos.response.MessageResponseImpl;
import br.com.pan.bluebank.models.Cliente;
import br.com.pan.bluebank.services.ClienteService;

@RestController
@RequestMapping(path = "v1/clientes")
public class ClienteController implements MessageResponse, ClienteDocs {
	
	@Autowired
	private ClienteService service;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id){
		return ResponseEntity.ok(this.service.findById(id));
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}
	
	@PostMapping
	public ResponseEntity<MessageResponseImpl> create(@RequestBody ClienteDTO dto){
		Cliente newCliente = this.service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newCliente.getId()).toUri();
		return ResponseEntity.created(uri).body(createMessageResponse("Cliente criado com sucesso!"));
	}
	
	@PutMapping(value = "/{id}")
		public ResponseEntity<MessageResponseImpl> update(@PathVariable Long id,
		@RequestBody ClienteDTO dto) {
		this.service.update(id, dto);
		return ResponseEntity.ok(createMessageResponse("Cliente atualizado com sucesso!"));
	}  
}
