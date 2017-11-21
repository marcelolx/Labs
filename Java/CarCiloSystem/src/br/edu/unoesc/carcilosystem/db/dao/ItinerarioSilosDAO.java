package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.ItinerarioSilos;

public interface ItinerarioSilosDAO {
	
	public boolean salvar(ItinerarioSilos AItinerarioSilos);
	
	public boolean excluir(ItinerarioSilos AItinerarioSilos);
	
	public ItinerarioSilos localizar(Integer ACodigo);
	
	public List<ItinerarioSilos> localizarPorItinerario(Integer AItinerario);
	
	public List<ItinerarioSilos> localizarEspecificos(Integer[] ACodigos);
	
	public List<ItinerarioSilos> localizarTodos();
}
