package br.edu.unoesc.carcilosystem.rgn;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Itinerario;

public interface ItinerarioRgn {
	
	public boolean salvar(Itinerario AItinerario);
	
	public boolean excluir(Integer ACodigo);
	
	public Itinerario localizar(Integer ACodigo);
	
	public Itinerario localizar(Integer AItinerario, Integer ASilo);
	
	public List<Itinerario> localizarTodos();
	
	public List<Itinerario> localizarPorSilo(Integer ASilo);
	
}
