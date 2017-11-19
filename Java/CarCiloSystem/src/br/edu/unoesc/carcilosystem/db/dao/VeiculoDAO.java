package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Veiculo;

public interface VeiculoDAO {
	
	public boolean Inserir(Veiculo AVeiculo);
	
	public boolean Atualizar(Veiculo AVeiculo);
	
	public boolean Excluir(Integer ACodigo);
	
	public Veiculo Localizar(Integer ACodigo);
	
	public List<Veiculo> LocalizarTodos();
}
