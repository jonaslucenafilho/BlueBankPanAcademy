package br.com.pan.bluebank.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.pan.bluebank.dtos.AgenciaDTO;
import br.com.pan.bluebank.dtos.response.AgenciaResponseDTO;
import br.com.pan.bluebank.dtos.response.MessageResponseImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface AgenciaDocs {
	
	@ApiOperation(value = "Retorna uma agência a partir do id informado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a agência"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(path = "/{id}", produces="application/json")
	public ResponseEntity<AgenciaResponseDTO> findById(@PathVariable Long id);

	@ApiOperation(value = "Retorna uma lista de agências")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de agências"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(produces="application/json")
	public ResponseEntity<List<AgenciaResponseDTO>> findAll(); 
	
	@ApiOperation(value = "Salva uma agência")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Salva a agência"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<MessageResponseImpl> create(@RequestBody AgenciaDTO dto);

	@ApiOperation(value = "Atualiza uma agência")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Atualiza a agência"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
		public ResponseEntity<MessageResponseImpl> update(@PathVariable Long id,
		@RequestBody AgenciaDTO dto);

	@ApiOperation(value = "Apaga uma agência")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Apaga a agência"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@DeleteMapping(value = "/{id}", consumes="application/json")
	public ResponseEntity<Void> delete(@PathVariable Long id);
}
