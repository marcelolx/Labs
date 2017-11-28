package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Silo;

public interface SiloDAO {
	
	/**
	 * Permite salvar e atualizar um silo.
	 * 
	 * @param ASilo
	 * @return Boolean
	 */
	public boolean inserir(Silo ASilo);
	
	/**
	 * Permite excluir o silo caso não esteja vinculado.
	 * 
	 * @param ACodigo
	 * @return Boolean
	 */
	public boolean excluir(Integer ACodigo);
	
	/**
	 * Permite localizar um silo em específico.
	 * 
	 * @param ACodigo
	 * @return Silo
	 */
	public Silo localizar(Integer ACodigo);
	
	/**
	 * Localiza todos os silos.
	 * 
	 * @return List<Silo>
	 */
	public List<Silo> localizarTodos();
	
	
	/**
	 * Localiza todos os silos de empresa X.
	 * 
	 * @param AEmpresa
	 * @return List<Silo>
	 */
	public List<Silo> localizarTodosEmpresa(Integer AEmpresa);

}
