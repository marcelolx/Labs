package br.com.marcelo.financas.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	private String nome;
	
	@Deprecated
	public Categoria() {		
	}
	
	public Categoria(String nome) {
		super();
		this.nome = nome;
	}
}
