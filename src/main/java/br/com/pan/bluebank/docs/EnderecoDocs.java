package br.com.pan.bluebank.docs;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.pan.bluebank.models.Endereco;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface EnderecoDocs {
	
	@ApiOperation(value = "Retorna uma lista de endereços")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de endereços"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
    @GetMapping(produces = "application/json")
	public  ResponseEntity<List<Endereco>> findAll();


}
