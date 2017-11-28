package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Veiculo;

public interface VeiculoDAO {
	
	/**
	 * Permite salvar e atualizar o ve�culo.
	 * 
	 * @param AVeiculo
	 * @return Boolean
	 */
	public boolean salvar(Veiculo AVeiculo);
	
	/**
	 * Permite excluir o ve�culo caso n�o esteja vinculado a nenhum itiner�rio.
	 * 
	 * @param ACodigo
	 * @return Boolean
	 */
	public boolean excluir(Integer ACodigo);
	
	/**
	 * Permite localizar o ve�culo a partir de seu c�digo.
	 * 
	 * @param ACodigo
	 * @return Veiculo
	 */
	public Veiculo localizar(Integer ACodigo);
	
	/**
	 * Localiza todos os ve�culos
	 * 
	 * @return List<Itinerario>
	 */
	public List<Veiculo> localizarTodos();
}
