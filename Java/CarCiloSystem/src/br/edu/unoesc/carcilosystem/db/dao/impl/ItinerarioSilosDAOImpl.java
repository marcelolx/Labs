package br.edu.unoesc.carcilosystem.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.edu.unoesc.carcilosystem.db.connection.ConnectionFactory;
import br.edu.unoesc.carcilosystem.db.dao.ItinerarioSilosDAO;
import br.edu.unoesc.carcilosystem.db.model.ItinerarioSilos;

public class ItinerarioSilosDAOImpl implements ItinerarioSilosDAO {

	private static Connection conexao;
	
	public ItinerarioSilosDAOImpl(){
		GetConexao();
	}
	
	@Override
	public boolean salvar(ItinerarioSilos AItinerarioSilos) {
		
		boolean salvou = false;
		int paramIndex = 1;
		
		try{
			GetConexao();
			
			PreparedStatement stmt;
			if(AItinerarioSilos.getCodigo() == 0){
				stmt = conexao.prepareStatement(SQL.GetInsert());
			} else{
				stmt = conexao.prepareStatement(SQL.GetUpdate());
			}
			
			stmt.setInt(paramIndex++, AItinerarioSilos.getCodigoSilo());
			stmt.setInt(paramIndex++, AItinerarioSilos.getCodigoItinerario());
			stmt.setString(paramIndex++, AItinerarioSilos.getTipoSilo());
			stmt.setTimestamp(paramIndex++, AItinerarioSilos.getDataSaida());
			stmt.setTimestamp(paramIndex++, AItinerarioSilos.getDataChegada());
			stmt.setInt(paramIndex++, AItinerarioSilos.getCarregado());
			stmt.setInt(paramIndex++, AItinerarioSilos.getCargaEntregue());
			stmt.setString(paramIndex++, AItinerarioSilos.getSituacaoCarga());
			
			if (AItinerarioSilos.getCodigo() > 0) {
				stmt.setInt(paramIndex++, AItinerarioSilos.getCodigo());
			}
			
			stmt.execute();
			stmt.close();
			
			salvou = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return salvou;
	}
	
	@Override
	public boolean excluir(ItinerarioSilos AItinerarioSilos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItinerarioSilos localizar(Integer ACodigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItinerarioSilos> localizarPorItinerario(Integer AItinerario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItinerarioSilos> localizarEspecificos(Integer[] ACodigos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItinerarioSilos> localizarTodos() {
		// TODO Auto-generated method stub
		return null;
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
			return "INSERT INTO itinerariosilos (CodigoSilo, CodigoItinerario, TipoSilo, DataSaida, DataChegada, Carregado,"
					+ " CargaEntregue, SituacaoCarga) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";			
		}		

		public static String GetUpdate() {			
			return "UPDATE itinerariosilos CodigoSilo = ?, CodigoItinerario = ?, TipoSilo = ?, DataSaida = ?, DataChegada = ?,"
					+ " Carregado = ?, CargaEntregue = ?, SituacaoCarga = ? WHERE Codigo = ?";
		}
		
		public static String GetDelete() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public static String GetSelectAll() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public static String GetSelectCodigo() {
			// TODO Auto-generated method stub
			return null;
		}

		
		
	}
	
}
