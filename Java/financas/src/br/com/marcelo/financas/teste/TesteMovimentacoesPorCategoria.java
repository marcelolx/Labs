package br.com.marcelo.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.marcelo.financas.modelo.Categoria;
import br.com.marcelo.financas.modelo.Movimentacao;
import br.com.marcelo.financas.util.JPAUtil;

public class TesteMovimentacoesPorCategoria {

	public static void main(String[] args) {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEntityManagerMySQL();
		em.getTransaction().begin();
		
		Categoria categoria = new Categoria();
		categoria.setId(3);
		
		String jpql = "select m from Movimentacao m join m.categoria cat where cat = :pCategoria";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pCategoria", categoria);		
		
		List<Movimentacao> resultados = query.getResultList();
		
		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descrição: " + movimentacao.getDescricao());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
		}
				
		em.getTransaction().commit();
		em.close();
		jpa.closeFactories();		
	}
	
}
