package br.com.marcelo.financas.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titular;
	private String numero;
	private String banco;
	private String agencia;
	
	@OneToMany(mappedBy = "conta")
	private List<Movimentacao> movimentacoes;
	
}
