package br.com.marcelo.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.marcelo.financas.modelo.Conta;
import br.com.marcelo.financas.modelo.Movimentacao;
import br.com.marcelo.financas.modelo.TipoMovimentacao;
import br.com.marcelo.financas.util.JPAUtil;

public class TesteJPQL {

	public static void main(String[] args) {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEntityManagerMySQL();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		String jpql = "select m from Movimentacao m where m.conta = :pConta" +
			" and m.tipo = :pTipo" +
			" order by m.valor desc";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		List<Movimentacao> resultados = query.getResultList();
		
		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descri��o: " + movimentacao.getDescricao());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
		}		
		
		em.getTransaction().commit();
		em.close();
		jpa.closeFactories();
	}
	
}
