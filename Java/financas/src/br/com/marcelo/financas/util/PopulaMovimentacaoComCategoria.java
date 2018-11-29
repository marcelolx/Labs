package br.com.marcelo.financas.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.marcelo.financas.modelo.Categoria;
import br.com.marcelo.financas.modelo.Conta;
import br.com.marcelo.financas.modelo.Movimentacao;
import br.com.marcelo.financas.modelo.TipoMovimentacao;
import br.com.marcelo.financas.util.JPAUtil;

public class PopulaMovimentacaoComCategoria {

	public static void main(String[] args) {
		Categoria viagem = new Categoria("Viagem");		
		Categoria negocios = new Categoria("Negócios");
		
		Conta conta = new Conta();
		conta.setId(2);		
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setDescricao("Viagem à SP");
		movimentacao.setTipo(TipoMovimentacao.SAIDA);
		movimentacao.setValor(new BigDecimal("100.00"));
		movimentacao.setCategoria(Arrays.asList(viagem, negocios));
		
		movimentacao.setConta(conta);		
		
		Movimentacao mov2 = new Movimentacao();
		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		mov2.setData(amanha);
		mov2.setDescricao("Viagem à RJ");
		mov2.setTipo(TipoMovimentacao.SAIDA);
		mov2.setValor(new BigDecimal("300.00"));
		mov2.setCategoria(Arrays.asList(viagem, negocios));		
		
		mov2.setConta(conta);
		
		
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEntityManagerMySQL();
		em.getTransaction().begin();
		
		em.persist(viagem);
		em.persist(negocios); 
		em.persist(movimentacao);
		em.persist(mov2);
		
//		Movimentacao movi = em.find(Movimentacao.class, 7);
	//	movi.setValor(new BigDecimal("300.0"));
		
		em.getTransaction().commit();
		em.close();
		jpa.closeFactories();
		
		
	}
	
}
