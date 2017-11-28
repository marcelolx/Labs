package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Veiculo;

public interface VeiculoDAO {
	
	/**
	 * Permite salvar e atualizar o veículo.
	 * 
	 * @param AVeiculo
	 * @return Boolean
	 */
	public boolean salvar(Veiculo AVeiculo);
	
	/**
	 * Permite excluir o veículo caso não esteja vinculado a nenhum itinerário.
	 * 
	 * @param ACodigo
	 * @return Boolean
	 */
	public boolean excluir(Integer ACodigo);
	
	/**
	 * Permite localizar o veículo a partir de seu código.
	 * 
	 * @param ACodigo
	 * @return Veiculo
	 */
	public Veiculo localizar(Integer ACodigo);
	
	/**
	 * Localiza todos os veículos
	 * 
	 * @return List<Itinerario>
	 */
	public List<Veiculo> localizarTodos();
}
