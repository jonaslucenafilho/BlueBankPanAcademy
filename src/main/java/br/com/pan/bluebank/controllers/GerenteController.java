package br.com.pan.bluebank.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pan.bluebank.docs.GerenteDocs;
import br.com.pan.bluebank.dtos.GerenteDTO;
import br.com.pan.bluebank.dtos.response.MessageResponse;
import br.com.pan.bluebank.dtos.response.MessageResponseImpl;
import br.com.pan.bluebank.models.Gerente;
import br.com.pan.bluebank.services.GerenteService;

@RestController
@RequestMapping(path = "v1/gerentes")
public class GerenteController implements MessageResponse, GerenteDocs {
	
	@Autowired
	private GerenteService gerenteService;
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Gerente> findById(@PathVariable Long id){
		return ResponseEntity.ok(this.gerenteService.findById(id));
	}

	@GetMapping
	public ResponseEntity<List<Gerente>> findAll() {
		return ResponseEntity.ok(this.gerenteService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<MessageResponseImpl> create(@RequestBody GerenteDTO gerenteDTO){
		Gerente newGerente = this.gerenteService.create(gerenteDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newGerente.getId()).toUri();
		return ResponseEntity.created(uri).body(createMessageResponse("Gerente criado com sucesso!"));
	}

	@PutMapping(value = "/{id}")
		public ResponseEntity<MessageResponseImpl> update(@PathVariable Long id,
		@RequestBody GerenteDTO gerenteDTO) {
		this.gerenteService.update(id, gerenteDTO);
		return ResponseEntity.ok(createMessageResponse("Gerente alterado com sucesso!"));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.gerenteService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
