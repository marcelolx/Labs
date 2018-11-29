package br.com.marcelo.financas.teste;

import javax.persistence.EntityManager;

import br.com.marcelo.financas.modelo.Cliente;
import br.com.marcelo.financas.modelo.Conta;
import br.com.marcelo.financas.util.JPAUtil;

public class TestaContaCliente {

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.setNome("Marcelo");
		cliente.setEndereco("Rua tal, 124");
		cliente.setProfissao("Aluno");
		
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Douglas");
		cliente2.setEndereco("Rua tal, 124");
		cliente2.setProfissao("Aluno");
		
		
		Conta conta = new Conta();
		conta.setId(2);
		cliente.setConta(conta);
		//cliente2.setConta(conta);
		
		
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEntityManagerMySQL();
		em.getTransaction().begin();
		
		em.persist(cliente);
		//em.persist(cliente2);
		
		em.getTransaction().commit();
		em.close();
		jpa.closeFactories();
	}
	
}
