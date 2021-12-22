package br.com.pan.bluebank.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pan.bluebank.docs.ContaDocs;
import br.com.pan.bluebank.dtos.ContaDTO;
import br.com.pan.bluebank.dtos.ExtratoDTO;
import br.com.pan.bluebank.dtos.filter.ExtratoFilter;
import br.com.pan.bluebank.dtos.response.ContaResponseDTO;
import br.com.pan.bluebank.dtos.response.MessageResponse;
import br.com.pan.bluebank.dtos.response.MessageResponseImpl;
import br.com.pan.bluebank.models.Conta;
import br.com.pan.bluebank.services.ContaService;

@RestController
@RequestMapping(path = "v1/contas")
public class ContaController implements MessageResponse, ContaDocs{
	
	@Autowired
	private ContaService contaService;
		
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContaResponseDTO> findByIdResponse(@PathVariable Long id){
		return ResponseEntity.ok(this.contaService.findByIdResponse(id));
	}

	@GetMapping
	public ResponseEntity<List<ContaResponseDTO>> findAll(){
		return ResponseEntity.ok(this.contaService.findAll());	
	}
	
	@GetMapping(value = "extrato")
	public ResponseEntity<ExtratoDTO> createExtrato(ExtratoFilter filter){
		return ResponseEntity.ok(this.contaService.extratoConta(filter));	
	}	

	@GetMapping(value = "/ativas")
	public ResponseEntity<List<ContaResponseDTO>> findAllAtivas(){
		return ResponseEntity.ok(this.contaService.findAllAtivas());	
	}

	@PostMapping
	public ResponseEntity<MessageResponseImpl> create(@RequestBody ContaDTO dto){
		Conta newConta = this.contaService.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newConta.getId()).toUri();
		return ResponseEntity.created(uri).body(createMessageResponse("Conta criada com sucesso!"));
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity<MessageResponseImpl> alterarStatus(@PathVariable Long id, 
			@RequestParam String status){
		this.contaService.alterarStatus(id, status);
		return ResponseEntity.ok(createMessageResponse("Status da conta alterado com sucesso!"));
	}	

}
