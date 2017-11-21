package br.edu.unoesc.carcilosystem.rgn.impl;

import java.util.Iterator;
import java.util.List;

import br.edu.unoesc.carcilosystem.db.dao.impl.ItinerarioDAOImpl;
import br.edu.unoesc.carcilosystem.db.dao.impl.ItinerarioSilosDAOImpl;
import br.edu.unoesc.carcilosystem.db.model.Itinerario;
import br.edu.unoesc.carcilosystem.db.model.ItinerarioSilos;
import br.edu.unoesc.carcilosystem.rgn.ItinerarioRgn;

public class ItinerarioRgnImpl implements ItinerarioRgn {

	@Override
	public boolean salvar(Itinerario AItinerario) {
		boolean salvou = false;
		
		Integer codigoItinerario = 0;
		ItinerarioSilos silo = new ItinerarioSilos();
				
		ItinerarioDAOImpl ItinerarioDAO = new ItinerarioDAOImpl();
		
		codigoItinerario = ItinerarioDAO.salvar(AItinerario);
		
		salvou = (codigoItinerario > 0);
			
		if (salvou){
			ItinerarioSilosDAOImpl ItinerarioSilosDAO = new ItinerarioSilosDAOImpl();
			Iterator<ItinerarioSilos> iterator = AItinerario.getListaItinerarioSilos().iterator();
			
			while(iterator.hasNext()){
				silo = iterator.next();
				
				silo.setCodigoItinerario(codigoItinerario);
				
				ItinerarioSilosDAO.salvar(silo);
			}		
			
		}		
		
		return salvou;
	}

	@Override
	public boolean excluir(Integer ACodigo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Itinerario localizar(Integer ACodigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Itinerario localizar(Integer AItinerario, Integer ASilo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Itinerario> localizarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Itinerario> localizarPorSilo(Integer ASilo) {
		// TODO Auto-generated method stub
		return null;
	}

}
