package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.ItinerarioSilos;

public interface ItinerarioSilosDAO {
	
	/**
	 * Permite salvar ou atualizar os dados dos silos de um itiner�rio.
	 * 
	 * @param AItinerarioSilos
	 * @return Boolean
	 */
	public boolean salvar(ItinerarioSilos AItinerarioSilos);
	
	/**
	 * Permite excluir os silos relacionados a um itiner�rio.
	 * 
	 * @param AItinerarioSilos
	 * @return Boolean
	 */
	public boolean excluir(ItinerarioSilos AItinerarioSilos);
	
	/**
	 * Permite localizar a origem ou destino de um itiner�rio a partir de seu c�digo.
	 * 
	 * @param ACodigo
	 * @return ItinerarioSilos
	 */
	public ItinerarioSilos localizar(Integer ACodigo);
	
	/**
	 * Permite localizar os silos por itiner�rio.
	 * 
	 * @param AItinerario
	 * @return List<ItinerarioSilos>
	 */
	public List<ItinerarioSilos> localizarPorItinerario(Integer AItinerario);
	
	/**
	 * Permite localizar uma lista de silos espec�ficos.
	 * 
	 * @param ACodigos
	 * @return List<ItinerarioSilos>
	 */
	public List<ItinerarioSilos> localizarEspecificos(Integer[] ACodigos);
	
	/**
	 * Permite localizar todos os silos.
	 * 	
	 * @return List<ItinerarioSilos>
	 */
	public List<ItinerarioSilos> localizarTodos();
}
