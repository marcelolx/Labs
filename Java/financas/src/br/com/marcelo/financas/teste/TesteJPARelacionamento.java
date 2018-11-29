package br.com.marcelo.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.marcelo.financas.modelo.Conta;
import br.com.marcelo.financas.modelo.Movimentacao;
import br.com.marcelo.financas.modelo.TipoMovimentacao;
import br.com.marcelo.financas.util.JPAUtil;

public class TesteJPARelacionamento {

	public static void main(String[] args) {
		JPAUtil jpa = new JPAUtil();
		
		Conta conta = new Conta();
		conta.setAgencia("0012");
		conta.setBanco("BB");
		conta.setNumero("4342");
		conta.setTitular("Marcelo");		
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Churrascaria");
		movimentacao.setTipo(TipoMovimentacao.SAIDA);
		movimentacao.setValor(new BigDecimal("200.00"));
		movimentacao.setConta(conta);
		
		EntityManager em = jpa.getEntityManagerMySQL();
		em.getTransaction().begin();
		
		em.persist(conta);		
		em.persist(movimentacao);
		
		em.getTransaction().commit();
		em.close();
		
		jpa.closeFactories();
		
	}

}
