package br.com.marcelo.financas.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@NamedQuery(name = "MediasPorDiaETipo", query = "select avg(m.valor) from Movimentacao m where m.conta = :pConta" +
		" and m.tipo = :pTipo group by day(m.data), month(m.data), year(m.data)")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	private String descricao;
	
	@ManyToOne
	private Conta conta;
	
	@ManyToMany
	private List<Categoria> categoria;
	
}
