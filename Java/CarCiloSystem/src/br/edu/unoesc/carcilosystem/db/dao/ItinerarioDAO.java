package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Itinerario;

public interface ItinerarioDAO {
	
	public Integer salvar(Itinerario AItinerario);
	
	public boolean excluir(Integer ACodigo);
	
	public Itinerario localizar(Integer ACodigo);
	
	public List<Itinerario> localizarEspecificos(Integer[] ACodigos);
	
	public List<Itinerario> localizarTodos();
	
}
