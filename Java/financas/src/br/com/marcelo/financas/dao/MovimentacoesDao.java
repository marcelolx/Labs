package br.com.marcelo.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.marcelo.financas.modelo.Conta;
import br.com.marcelo.financas.modelo.TipoMovimentacao;

public class MovimentacoesDao {
	
	EntityManager em;
	
	public MovimentacoesDao(EntityManager em) {
		this.em = em;
	}
	
	public List<Double> getMediasPorDiaETipo(TipoMovimentacao saida, Conta conta) {
		List<Double> retorno;
		em.getTransaction().begin();
		
		TypedQuery<Double> typedQuery = em.createNamedQuery("MediasPorDiaETipo", Double.class);
		typedQuery.setParameter("pConta", conta);
		typedQuery.setParameter("pTipo", TipoMovimentacao.SAIDA);
			
		retorno = typedQuery.getResultList();
			
		em.getTransaction().commit();		
		
		return retorno;
	}

	
	
}
