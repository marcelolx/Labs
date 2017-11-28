package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.FilaItinerario;
import br.edu.unoesc.carcilosystem.db.model.Itinerario;

public interface ItinerarioDAO {
	
	/**
	 * Permite salvar/atualizar um itiner�rio.
	 * 
	 * @param AItinerario  
	 * @return Retorna c�digo salvo.
	 */
	public Integer salvar(Itinerario AItinerario);
	
	/**
	 * Permite um itiner�rio.
	 * 	
	 * @param ACodigo
	 * @return True/False
	 */
	public boolean excluir(Integer ACodigo);
	
	/**
	 * Permite localizar um itiner�rio pelo seu c�digo.
	 * 
	 * @param ACodigo
	 * @return Itinerario
	 */
	public Itinerario localizar(Integer ACodigo);
	
	/**
	 * Permite localizar um ou mais itiner�rios espec�ficos.
	 * 
	 * @param ACodigos
	 * @return List<Itinerario>
	 */
	public List<Itinerario> localizarEspecificos(Integer[] ACodigos);
	
	/**
	 * Locaiza todos os itiner�rios que est�o para ser descarregados no dia de hoje.
	 * 
	 * @param AEmpresa
	 * @return List<FilaItinerario>
	 */
	public List<FilaItinerario> localizarTodosADescarregar(Integer AEmpresa);
	
}
