package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Usuario;

public interface UsuarioDAO {
	
	public boolean Inserir(Usuario AUsuario);
	
	public boolean Atualizar(Usuario AUsuario);
	
	public boolean Excluir(Integer ACodigo);
	
	public Usuario Localizar(Integer ACodigo);
	
	public List<Usuario> LocalizarTodos();
	
	public boolean VerificaLogin(String AUsuario, String ASenha); 
}
