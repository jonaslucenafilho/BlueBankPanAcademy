package br.com.pan.bluebank.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.pan.bluebank.dtos.GerenteDTO;
import br.com.pan.bluebank.dtos.response.MessageResponseImpl;
import br.com.pan.bluebank.models.Gerente;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface GerenteDocs {

	@ApiOperation(value = "Retorna um gerente a partir do id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o gerente"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Gerente> findById(@PathVariable Long id);

	@ApiOperation(value = "Retorna uma lista de gerentes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de gerentes"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Gerente>> findAll();
	
	@ApiOperation(value = "Salva um novo gerente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Salva o gerente"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<MessageResponseImpl> create(@RequestBody GerenteDTO gerenteDTO);

	@ApiOperation(value = "Atualiza um gerente a partir do id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Atualiza o gerente"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PutMapping(value = "/{id}", produces = "application/json")
		public ResponseEntity<MessageResponseImpl> update(@PathVariable Long id,
		@RequestBody GerenteDTO gerenteDTO);

	@ApiOperation(value = "Apaga um gerente a partir do id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Apaga o gerente"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id);
	
}
