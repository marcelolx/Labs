package br.edu.unoesc.carcilosystem.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			return "INSERT INTO veiculo (Nome, Marca, Ano, Modelo, TipoVeiculo, Placa) "
							  + "VALUES (?,?,?,?,?,?)"; 
		}
		
		public static String GetSelectAll(){
			return "SELECT Codigo, Nome, Marca, Ano, Modelo, TipoVeiculo, Placa FROM Veiculo";
		}
	}
	
	
	@Override
	public boolean salvar(Veiculo AVeiculo) {
		
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
	public Veiculo localizar(Integer ACodigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Veiculo> localizarTodos() {
		List<Veiculo> ListaVeiculos = new ArrayList<Veiculo>();
		
		GetConexao();
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(SQL.GetSelectAll());
			
			ResultSet veiculos = stmt.executeQuery();		
			
			while(veiculos.next()){
				Veiculo veiculo = new Veiculo();
				
				veiculo.setCodigo(veiculos.getInt("Codigo"));
				veiculo.setNome(veiculos.getString("Nome"));
				veiculo.setMarca(veiculos.getString("Marca"));
				veiculo.setAno(veiculos.getInt("Ano"));
				veiculo.setModelo(veiculos.getString("Modelo"));
				veiculo.setTipoVeiculo(veiculos.getString("TipoVeiculo"));
				veiculo.setPlaca(veiculos.getString("Placa"));
				
				ListaVeiculos.add(veiculo);
			}
			
			veiculos.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ListaVeiculos;
	}

}
