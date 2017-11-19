package br.edu.unoesc.carcilosystem.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.edu.unoesc.carcilosystem.db.connection.ConnectionFactory;
import br.edu.unoesc.carcilosystem.db.dao.VeiculoDAO;
import br.edu.unoesc.carcilosystem.db.model.Veiculo;

public class VeiculoDAOImpl implements VeiculoDAO {

	private static Connection conexao;
	
	public VeiculoDAOImpl(){
		GetConexao();
	}
	
	private static void GetConexao(){
		conexao = new ConnectionFactory().getConnection();
	}
	
	private static class SQL {
		
		public static String GetInsert(){
			return "INSERT INTO veiculo (Nome, Marca, Ano, Modelo, TipoVeiculo, Placa) "
							  + "VALUES (?,?,?,?,?,?)"; 
		}
	}
	
	
	@Override
	public boolean Inserir(Veiculo AVeiculo) {
		
		boolean inseriu = false;
		try {
			GetConexao();
		  	
			PreparedStatement stmt = conexao.prepareStatement(SQL.GetInsert());
			
			stmt.setString(1, AVeiculo.getNome());
			stmt.setString(2, AVeiculo.getMarca());
			stmt.setInt(3, AVeiculo.getAno());
			stmt.setString(4, AVeiculo.getModelo());
			stmt.setString(5, AVeiculo.getTipoVeiculo());
			stmt.setString(6, AVeiculo.getPlaca());
			
			stmt.execute();
			
			stmt.close();
			conexao.close();
			
			inseriu = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inseriu;
	}

	@Override
	public boolean Atualizar(Veiculo AVeiculo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Excluir(Integer ACodigo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Veiculo Localizar(Integer ACodigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Veiculo> LocalizarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
