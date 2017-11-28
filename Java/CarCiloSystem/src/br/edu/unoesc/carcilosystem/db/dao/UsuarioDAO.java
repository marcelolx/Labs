package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Usuario;

public interface UsuarioDAO {
	
	/**
	 * Permite salvar e atualizar os dados do usuário/motorista.
	 * 
	 * @param AUsuario
	 * @return Boolean
	 */
	public boolean salvar(Usuario AUsuario);
	
	/**
	 * Permite excluir o usuário caso não esteja vinculado a nenhum itinerário.
	 * 
	 * @param ACodigo
	 * @return Boolean
	 */
	public boolean excluir(Integer ACodigo);
	
	/**
	 * Permite localizar um usuário pelo seu código;
	 * 	
	 * @param ACodigo
	 * @return Boolean
	 */
	public Usuario localizar(Integer ACodigo);
	
	/**
	 * Permite localizar todos os usuários.
	 * 
	 * @return List<Usuario>
	 */
	public List<Usuario> localizarTodos();
	
	/**
	 * Válida login ao se conectar no sistema.
	 * 
	 * @param AUsuario
	 * @param ASenha
	 * @return True caso seja válido.
	 */
	public boolean verificaLogin(String AUsuario, String ASenha); 
}
