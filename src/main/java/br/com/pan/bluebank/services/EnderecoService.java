package br.com.pan.bluebank.services;

import java.util.List;

import br.com.pan.bluebank.models.Endereco;
import io.swagger.annotations.Api;

@Api(value = "v1/endereco")
public interface EnderecoService {
	
	
	public List<Endereco> findAll();

	public Endereco create(Endereco endereco);
	
	public Endereco findById(Long id);
}
