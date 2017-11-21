package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Veiculo;

public interface VeiculoDAO {
	
	public boolean salvar(Veiculo AVeiculo);
	
	public boolean excluir(Integer ACodigo);
	
	public Veiculo localizar(Integer ACodigo);
	
	public List<Veiculo> localizarTodos();
}
