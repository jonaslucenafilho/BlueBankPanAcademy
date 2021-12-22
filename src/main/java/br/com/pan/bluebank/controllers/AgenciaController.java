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

import br.com.pan.bluebank.docs.AgenciaDocs;
import br.com.pan.bluebank.dtos.AgenciaDTO;
import br.com.pan.bluebank.dtos.response.AgenciaResponseDTO;
import br.com.pan.bluebank.dtos.response.MessageResponse;
import br.com.pan.bluebank.dtos.response.MessageResponseImpl;
import br.com.pan.bluebank.models.Agencia;
import br.com.pan.bluebank.services.AgenciaService;

@RestController
@RequestMapping(path = "v1/agencias")
public class AgenciaController implements MessageResponse, AgenciaDocs {
	
	@Autowired
	private AgenciaService agenciaService;

	@GetMapping(path = "/{id}", produces="application/json")
	public ResponseEntity<AgenciaResponseDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(this.agenciaService.findByIdResponse(id));
	}

	@GetMapping(produces="application/json")
	public ResponseEntity<List<AgenciaResponseDTO>> findAll() {	
		return ResponseEntity.ok(this.agenciaService.findAll());
	}
	
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<MessageResponseImpl> create(@RequestBody AgenciaDTO dto){
		Agencia newAgencia = this.agenciaService.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newAgencia.getId()).toUri();
		return ResponseEntity.created(uri).body(createMessageResponse("Agência criada com sucesso!"));
	}

	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
		public ResponseEntity<MessageResponseImpl> update(@PathVariable Long id,
		@RequestBody AgenciaDTO dto) {
		this.agenciaService.update(id, dto);
		return ResponseEntity.ok(createMessageResponse("Agência atualizada com sucesso!"));
	}

	@DeleteMapping(value = "/{id}", consumes="application/json")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		this.agenciaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
