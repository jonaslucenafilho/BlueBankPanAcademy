package br.com.pan.bluebank.model;

import java.util.Date;
import java.util.Objects;

public class Cliente {

	private Long id;
	private String nome;
	private Date dataDeNascimento;
	
	public Cliente(Long id, String nome, Date dataDeNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
}
