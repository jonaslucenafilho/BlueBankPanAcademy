package br.com.pan.bluebank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pan.bluebank.docs.EnderecoDocs;
import br.com.pan.bluebank.models.Endereco;
import br.com.pan.bluebank.services.EnderecoService;

@RestController
@RequestMapping(path = "v1/enderecos")
public class EnderecoController implements EnderecoDocs{
    
    @Autowired
    private EnderecoService service;
	
   
    @GetMapping() 
	public ResponseEntity<List<Endereco>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}

}
