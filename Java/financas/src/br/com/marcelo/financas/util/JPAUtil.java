package br.com.marcelo.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emfMySQL = Persistence.createEntityManagerFactory("financas-mysql");
	private static EntityManagerFactory emfPostgres = Persistence.createEntityManagerFactory("financas-postgres");
	
	public EntityManager getEntityManagerMySQL() {
		return emfMySQL.createEntityManager();
	}
	
	public EntityManager getEntityManagerPostgres() {
		return emfPostgres.createEntityManager();
	}
	
	public void closeFactories() {
		emfMySQL.close();
		emfPostgres.close();		
	}
	
}
