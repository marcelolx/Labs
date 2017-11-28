package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.FilaItinerario;
import br.edu.unoesc.carcilosystem.db.model.Itinerario;

public interface ItinerarioDAO {
	
	/**
	 * Permite salvar/atualizar um itinerário.
	 * 
	 * @param AItinerario  
	 * @return Retorna código salvo.
	 */
	public Integer salvar(Itinerario AItinerario);
	
	/**
	 * Permite um itinerário.
	 * 	
	 * @param ACodigo
	 * @return True/False
	 */
	public boolean excluir(Integer ACodigo);
	
	/**
	 * Permite localizar um itinerário pelo seu código.
	 * 
	 * @param ACodigo
	 * @return Itinerario
	 */
	public Itinerario localizar(Integer ACodigo);
	
	/**
	 * Permite localizar um ou mais itinerários específicos.
	 * 
	 * @param ACodigos
	 * @return List<Itinerario>
	 */
	public List<Itinerario> localizarEspecificos(Integer[] ACodigos);
	
	/**
	 * Locaiza todos os itinerários que estão para ser descarregados no dia de hoje.
	 * 
	 * @param AEmpresa
	 * @return List<FilaItinerario>
	 */
	public List<FilaItinerario> localizarTodosADescarregar(Integer AEmpresa);
	
}
