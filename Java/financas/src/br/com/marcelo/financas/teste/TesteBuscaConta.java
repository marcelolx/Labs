package br.com.marcelo.financas.teste;

import javax.persistence.EntityManager;

import br.com.marcelo.financas.Conta;
import br.com.marcelo.financas.util.JPAUtil;

public class TesteBuscaConta {

	public static void main(String[] args) {
		JPAUtil jpa = new JPAUtil();
		
		EntityManager em = jpa.getEntityManagerMySQL();
		em.getTransaction().begin();
		
		Conta conta = em.find(Conta.class, 1);
		
		conta.setTitular("João");
		
		System.out.println(conta.getTitular());
				
		em.getTransaction().commit();		
		em.close();
		
		
		em = jpa.getEntityManagerMySQL();
		em.getTransaction().begin();
		
		conta.setTitular("Élvis");
		em.merge(conta);
		
		em.getTransaction().commit();
		em.close();		
		
		jpa.closeFactories();
	}
	
}
