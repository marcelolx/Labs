package br.edu.unoesc.carcilosystem.db.dao;

import java.util.List;

import br.edu.unoesc.carcilosystem.db.model.Usuario;

public interface UsuarioDAO {
	
	public boolean salvar(Usuario AUsuario);
	
	public boolean excluir(Integer ACodigo);
	
	public Usuario localizar(Integer ACodigo);
	
	public List<Usuario> localizarTodos();
	
	public boolean verificaLogin(String AUsuario, String ASenha); 
}
