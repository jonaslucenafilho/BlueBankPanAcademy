package br.com.pan.bluebank.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pan.bluebank.docs.MovimentacaoDocs;
import br.com.pan.bluebank.dtos.MovimentacaoDTO;
import br.com.pan.bluebank.dtos.response.MessageResponse;
import br.com.pan.bluebank.dtos.response.MessageResponseImpl;
import br.com.pan.bluebank.dtos.response.MovimentacaoResponseDTO;
import br.com.pan.bluebank.models.Movimentacao;
import br.com.pan.bluebank.services.MovimentacaoService;

@RestController
@RequestMapping(path = "v1/movimentacao")
public class MovimentacaoController implements MessageResponse, MovimentacaoDocs {
	
	@Autowired
	private MovimentacaoService service;

	@GetMapping(path = "/{origemId}")
	public ResponseEntity<MovimentacaoResponseDTO> findById(@PathVariable Long origemId){
		return ResponseEntity.ok(this.service.findByIdResponse(origemId));

	}

	@GetMapping
	public ResponseEntity<Page<MovimentacaoResponseDTO>> findAll(Pageable page) {
			return ResponseEntity.ok(this.service.findAllPageable(page));
	}		
	
	@PostMapping
	public ResponseEntity<MessageResponseImpl> create(		
			@RequestBody MovimentacaoDTO dto){		
		Movimentacao newMovimentacao = this.service.create(dto);		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newMovimentacao.getId()).toUri();
		return ResponseEntity
				.created(uri)
				.body(createMessageResponse("Movimentação realizada com sucesso!"));
	}
}
