package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Silo;

public interface SiloDAO {
	
	public boolean inserir(Silo ASilo);
	
	public boolean excluir(Integer ACodigo);
	
	public Silo localizar(Integer ACodigo);
	
	public List<Silo> localizarTodos();

}
