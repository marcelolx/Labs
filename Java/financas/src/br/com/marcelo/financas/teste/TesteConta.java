package br.com.marcelo.financas.teste;

import javax.persistence.EntityManager;

import br.com.marcelo.financas.modelo.Conta;
import br.com.marcelo.financas.util.JPAUtil;

public class TesteConta {

	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setTitular("Marcelo");
		conta.setBanco("Caixa Economica");
		conta.setAgencia("123");		
		conta.setNumero("456");
		
		JPAUtil jpaUtil = new JPAUtil();
				
		EntityManager em = jpaUtil.getEntityManagerMySQL();
		
		em.getTransaction().begin();
		em.persist(conta);
		
		conta.setBanco("Banco do Brasil");
		
		em.getTransaction().commit();
		
		em.close();
		
		Conta conta2 = new Conta();
		conta2.setTitular("Marcelo");
		conta2.setBanco("Caixa Economica");
		conta2.setAgencia("123");		
		conta2.setNumero("456");		
		
		em = jpaUtil.getEntityManagerPostgres();
		
		em.getTransaction().begin();
		em.persist(conta2);
		em.getTransaction().commit();
		
		em.close();
		jpaUtil.closeFactories();		
	}
	
	
}
