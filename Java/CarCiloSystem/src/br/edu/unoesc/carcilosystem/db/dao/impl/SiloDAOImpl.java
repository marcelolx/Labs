package br.edu.unoesc.carcilosystem.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.unoesc.carcilosystem.db.connection.ConnectionFactory;
import br.edu.unoesc.carcilosystem.db.dao.SiloDAO;
import br.edu.unoesc.carcilosystem.db.model.Silo;

public class SiloDAOImpl implements SiloDAO{

	private static Connection conexao;
		
	public SiloDAOImpl(){
		GetConexao();
	}
	
	@Override
	public boolean inserir(Silo ASilo) {

		boolean inseriu = false;
		try {
			GetConexao();
		  	
			PreparedStatement stmt = conexao.prepareStatement(SQL.GetInsert());
			
			stmt.setString(1, ASilo.getCidade());
			stmt.setString(2, ASilo.getRua());
			stmt.setString(3, ASilo.getBairro());
			stmt.setString(4, ASilo.getNumero());
			stmt.setString(5, ASilo.getEstado());
			stmt.setString(6, ASilo.getSiglaUF());
			stmt.setString(7, ASilo.getLatitude());
			stmt.setString(8, ASilo.getLongitude());
			stmt.setString(9, ASilo.getNome());
			
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
		boolean excluiu = false;
		
		GetConexao();
				
		try {
			PreparedStatement stmt = conexao.prepareStatement(SQL.GetSiloVinculado());
			
			stmt.setInt(1, ACodigo);
			
			ResultSet retorno = stmt.executeQuery();
			
			if (!retorno.next()) {
				stmt.close();
				
				stmt = conexao.prepareStatement(SQL.GetDelete());
				
				stmt.execute();
				
				stmt.close();
				excluiu = true;
				
			} else {
				JOptionPane.showMessageDialog(null, "O silo já está vinculado, não será possível excluir!");
				excluiu = false;
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return excluiu;
	}

	@Override
	public Silo localizar(Integer ACodigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Silo> localizarTodos() {
		List<Silo> ListaSilos = new ArrayList<Silo>();
		
		GetConexao();
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(SQL.GetSelectAll());
			
			ResultSet silos = stmt.executeQuery();		
			
			while(silos.next()){
				Silo silo = new Silo();
				
				silo.setCodigo(silos.getInt("Codigo"));
				silo.setCidade(silos.getString("Cidade"));
				silo.setRua(silos.getString("Rua"));
				silo.setBairro(silos.getString("Bairro"));
				silo.setNumero(silos.getString("Numero"));
				silo.setEstado(silos.getString("Estado"));
				silo.setSiglaUF(silos.getString("SiglaUF"));
				silo.setLatitude(silos.getString("Latitude"));
				silo.setLongitude(silos.getString("Longitude"));
				silo.setNome(silos.getString("Nome"));
				
				ListaSilos.add(silo);
			}
			
			silos.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ListaSilos;
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
			return "INSERT INTO silos (Cidade, Rua, Bairro, Numero, Estado, SiglaUF, Latitude, Longitude, Nome)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		}
		
		public static String GetSelectAll(){
			return "SELECT Codigo, Cidade, Rua, Bairro, Numero, Estado, SiglaUF, Latitude, Longitude, Nome "
					+ "FROM silos";
		}
		
		public static String GetSiloVinculado(){
			return "SELECT COUNT(*) FROM itinerariosilos WHERE CodigoSilo = ?";
		}
		
		public static String GetDelete(){
			return "DELETE FROM silos WHERE Codigo = ?";
		}
		
	}
	
}
