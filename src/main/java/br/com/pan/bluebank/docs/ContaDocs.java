package br.com.pan.bluebank.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.pan.bluebank.dtos.ContaDTO;
import br.com.pan.bluebank.dtos.ExtratoDTO;
import br.com.pan.bluebank.dtos.filter.ExtratoFilter;
import br.com.pan.bluebank.dtos.response.ContaResponseDTO;
import br.com.pan.bluebank.dtos.response.MessageResponseImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ContaDocs {

	@ApiOperation(value = "Retorna uma conta a partir do id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a conta"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})	
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<ContaResponseDTO> findByIdResponse(@PathVariable Long id);

	@ApiOperation(value = "Retorna uma lista de contas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de contas"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})	
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<ContaResponseDTO>> findAll();
	
	@ApiOperation(value = "Retorna um extrato de uma conta")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna o extrato da conta"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(value = "extrato", produces = "application/json")
	public ResponseEntity<ExtratoDTO> createExtrato(ExtratoFilter filter);

	@ApiOperation(value = "Retorna uma lista de contas con status ativo")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de contas"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(value = "/ativas", produces = "application/json")
	public ResponseEntity<List<ContaResponseDTO>> findAllAtivas();

	@ApiOperation(value = "Salva uma nova conta")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Salva a conta"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<MessageResponseImpl> create(@RequestBody ContaDTO dto);

	@ApiOperation(value = "Altera o status de uma conta a partir do id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Altera o status da conta"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PatchMapping(value = "/{id}",produces = "application/json")
	public ResponseEntity<MessageResponseImpl> alterarStatus(@PathVariable Long id,	@RequestParam String status);	
}
