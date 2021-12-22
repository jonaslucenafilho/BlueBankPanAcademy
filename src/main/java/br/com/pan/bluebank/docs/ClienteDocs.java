package br.com.pan.bluebank.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.pan.bluebank.dtos.ClienteDTO;
import br.com.pan.bluebank.dtos.response.MessageResponseImpl;
import br.com.pan.bluebank.models.Cliente;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


public interface ClienteDocs {
	
	@ApiOperation(value = "Retorna um cliente a partir do id informado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o cliente"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(path = "/{id}", produces="application/json")
	public ResponseEntity<Cliente> findById(@PathVariable Long id);
	
	@ApiOperation(value = "Retorna uma lista de clientes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de clientes"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Cliente>> findAll();
	
	@ApiOperation(value = "Salva um novo cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Salva o cliente"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PostMapping(consumes = "application/json")
	public ResponseEntity<MessageResponseImpl> create(@RequestBody ClienteDTO dto);

	@ApiOperation(value = "Atualiza um cliente a partir do id informado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Atualiza o cliente"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PutMapping(value = "/{id}",consumes="application/json", produces="application/json")
	public ResponseEntity<MessageResponseImpl> update(@PathVariable Long id, @RequestBody ClienteDTO dto);  
	
}
