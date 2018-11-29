package br.com.marcelo.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.marcelo.financas.modelo.Conta;
import br.com.marcelo.financas.util.JPAUtil;

public class TesteFuncoesJPQL {

	public static void main(String[] args) {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEntityManagerMySQL();
		em.getTransaction().begin();
		
		String jpql = "select distinct conta from Conta conta left join fetch conta.movimentacoes";
		Query query = em.createQuery(jpql);
		
		List<Conta> contas = query.getResultList();
		
		for (Conta conta : contas) {
			System.out.println("Títular: " + conta.getTitular());
			System.out.println("Movimentações: ");
			System.out.println(conta.getMovimentacoes());
		}
		
		em.getTransaction().commit();		
		jpa.closeFactories();
	}
	
}
