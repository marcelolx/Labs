package br.edu.unoesc.carcilosystem.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.carcilosystem.db.connection.ConnectionFactory;
import br.edu.unoesc.carcilosystem.db.dao.UsuarioDAO;
import br.edu.unoesc.carcilosystem.db.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {
	
	private static Connection conexao;		
	
	public UsuarioDAOImpl(){
		GetConexao();
	} //metodo construtor
	
	
	
	private static void GetConexao(){
		
		try {
			if ((conexao == null) || (conexao.isClosed())) {
				conexao = new ConnectionFactory().UseMainConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	private static class SQL {
		
		public static String GetInsert(){
			return "INSERT INTO usuario (Login, Senha, Nome, DataNascimento, Rg, Cpf, NumeroRegCNH, CategoriaHabilitacao)"
							  + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		}
		
		public static String GetLogin(){
			return "SELECT Codigo FROM usuario WHERE Login = ? AND Senha = ?"; 
		}
		
		public static String GetSelectAll(){
			return "SELECT Codigo, Login, Senha, Nome, DataNascimento, Rg, Cpf, NumeroRegCNH, CategoriaHabilitacao "
					+ "FROM Usuario";
		}
		
	}
	
	
	
	@Override
	public boolean salvar(Usuario AUsuario) {
		
		boolean inseriu = false;
		try {
			GetConexao();
		  	
			PreparedStatement stmt = conexao.prepareStatement(SQL.GetInsert());
			
			stmt.setString(1, AUsuario.getLogin());
			stmt.setString(2, AUsuario.getSenha());
			stmt.setString(3, AUsuario.getNome());
			stmt.setDate(4, AUsuario.getDataNascimento());
			stmt.setString(5, AUsuario.getRg());
			stmt.setString(6, AUsuario.getCpf());
			stmt.setLong(7, AUsuario.getNumeroRegCNH());
			stmt.setString(8, AUsuario.getCategoriaHabilitacao());
			
			stmt.execute();
			
			stmt.close();
			
			inseriu = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inseriu;
	}

	@Override
	public boolean excluir(Integer ACodigo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario localizar(Integer ACodigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> localizarTodos() {
		List<Usuario> ListaUsuarios = new ArrayList<Usuario>();
		
		GetConexao();
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(SQL.GetSelectAll());
			
			ResultSet usuarios = stmt.executeQuery();
			
			while(usuarios.next()){
				Usuario usuario = new Usuario();
				
				usuario.setCodigo(usuarios.getInt("Codigo"));
				usuario.setLogin(usuarios.getString("Login"));
				usuario.setSenha(usuarios.getString("Senha"));
				usuario.setNome(usuarios.getString("Nome"));
				usuario.setDataNascimento(usuarios.getDate("DataNascimento"));
				usuario.setRg(usuarios.getString("Rg"));
				usuario.setCpf(usuarios.getString("Cpf"));
				usuario.setNumeroRegCNH(usuarios.getLong("NumeroRegCNH"));
				usuario.setCategoriaHabilitacao(usuarios.getString("CategoriaHabilitacao"));
				
				ListaUsuarios.add(usuario);				
			}
			
			usuarios.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return ListaUsuarios;
	}



	@Override
	public boolean verificaLogin(String AUsuario, String ASenha) {
		
		boolean loginValido = false;
		try {
			GetConexao();
			
			
			PreparedStatement stmt = conexao.prepareStatement(SQL.GetLogin());
			
			stmt.setString(1, AUsuario);
			stmt.setString(2, ASenha);
			
			ResultSet login = stmt.executeQuery();
			
			loginValido = login.next();
			
			login.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loginValido;
	}

}
