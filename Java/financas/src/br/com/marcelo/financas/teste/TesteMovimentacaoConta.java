package br.com.marcelo.financas.teste;

import javax.persistence.EntityManager;

import br.com.marcelo.financas.modelo.Conta;
import br.com.marcelo.financas.modelo.Movimentacao;
import br.com.marcelo.financas.util.JPAUtil;

public class TesteMovimentacaoConta {

	public static void main(String[] args) {
		
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEntityManagerMySQL();
		em.getTransaction().begin();
		
		Movimentacao movimentacao = em.find(Movimentacao.class, 1);
		Conta conta = movimentacao.getConta();
		
		System.out.println(conta.getTitular());
		
		System.out.println(conta.getMovimentacoes().size());		
		
		em.getTransaction().commit();		
		jpa.closeFactories();
		
	}
	
}
