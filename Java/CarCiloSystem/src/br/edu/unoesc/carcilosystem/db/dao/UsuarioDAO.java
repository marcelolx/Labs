package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Usuario;

public interface UsuarioDAO {
	
	/**
	 * Permite salvar e atualizar os dados do usu�rio/motorista.
	 * 
	 * @param AUsuario
	 * @return Boolean
	 */
	public boolean salvar(Usuario AUsuario);
	
	/**
	 * Permite excluir o usu�rio caso n�o esteja vinculado a nenhum itiner�rio.
	 * 
	 * @param ACodigo
	 * @return Boolean
	 */
	public boolean excluir(Integer ACodigo);
	
	/**
	 * Permite localizar um usu�rio pelo seu c�digo;
	 * 	
	 * @param ACodigo
	 * @return Boolean
	 */
	public Usuario localizar(Integer ACodigo);
	
	/**
	 * Permite localizar todos os usu�rios.
	 * 
	 * @return List<Usuario>
	 */
	public List<Usuario> localizarTodos();
	
	/**
	 * V�lida login ao se conectar no sistema.
	 * 
	 * @param AUsuario
	 * @param ASenha
	 * @return True caso seja v�lido.
	 */
	public boolean verificaLogin(String AUsuario, String ASenha); 
}
