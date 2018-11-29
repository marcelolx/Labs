package br.com.marcelo.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.marcelo.financas.dao.MovimentacoesDao;
import br.com.marcelo.financas.modelo.Conta;
import br.com.marcelo.financas.modelo.TipoMovimentacao;
import br.com.marcelo.financas.util.JPAUtil;

public class TesteTodasMovimentacoesDasContas {

	public static void main(String[] args) {
		JPAUtil jpa = new JPAUtil();
		EntityManager em = jpa.getEntityManagerMySQL();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		MovimentacoesDao dao = new MovimentacoesDao(em);		
		List<Double> medias = dao.getMediasPorDiaETipo(TipoMovimentacao.SAIDA, conta);
		
		for (Double media : medias) {
			System.out.println("A média é: " + media);
		}
		
		em.close();		
		jpa.closeFactories();
	}
	
}
