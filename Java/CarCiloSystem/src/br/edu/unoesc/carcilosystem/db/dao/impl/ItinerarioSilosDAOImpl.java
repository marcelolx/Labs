package br.edu.unoesc.carcilosystem.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.carcilosystem.db.connection.ConnectionFactory;
import br.edu.unoesc.carcilosystem.db.dao.ItinerarioSilosDAO;
import br.edu.unoesc.carcilosystem.db.model.ItinerarioSilos;

public class ItinerarioSilosDAOImpl implements ItinerarioSilosDAO {

	private static Connection conexao;
	
	public ItinerarioSilosDAOImpl(){
		getConexao();
	}
	
	@Override
	public boolean salvar(ItinerarioSilos AItinerarioSilos) {
		
		boolean salvou = false;
		int paramIndex = 1;
		
		try{
			getConexao();
			
			PreparedStatement stmt;
			if(AItinerarioSilos.getCodigo() == 0){
				stmt = conexao.prepareStatement(SQL.getInsert());
			} else{
				stmt = conexao.prepareStatement(SQL.getUpdate());
			}
			
			stmt.setInt(paramIndex++, AItinerarioSilos.getCodigoSilo());
			stmt.setInt(paramIndex++, AItinerarioSilos.getCodigoItinerario());
			stmt.setString(paramIndex++, AItinerarioSilos.getTipoSilo());
			stmt.setTimestamp(paramIndex++, AItinerarioSilos.getDataSaida());
			stmt.setTimestamp(paramIndex++, AItinerarioSilos.getDataChegada());
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
		List<ItinerarioSilos> listaOrigemDestino = new ArrayList<ItinerarioSilos>();
		
		getConexao();
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(SQL.getSelectItinerario());
			
			stmt.setInt(1, AItinerario);
			
			ResultSet silos = stmt.executeQuery();
			
			while(silos.next()){
				ItinerarioSilos itiSilo = new ItinerarioSilos();
								
				itiSilo.setCodigo(silos.getInt("Codigo"));
				itiSilo.setCodigoItinerario(AItinerario);
				itiSilo.setCodigoSilo(silos.getInt("CodigoSilo"));
				itiSilo.setTipoSilo(silos.getString("TipoSilo"));
				itiSilo.setDataSaida(silos.getTimestamp("DataSaida"));
				itiSilo.setDataChegada(silos.getTimestamp("DataChegada"));
				itiSilo.setSituacaoCarga(silos.getString("SituacaoCarga"));
				
				listaOrigemDestino.add(itiSilo);
			}
			
			silos.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listaOrigemDestino;
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

	private static void getConexao(){
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

		public static String getInsert(){
			return "INSERT INTO itinerariosilos (CodigoSilo, CodigoItinerario, TipoSilo, DataSaida, DataChegada,"
					+ " SituacaoCarga) VALUES (?, ?, ?, ?, ?, ?)";			
		}		

		public static String getUpdate() {			
			return "UPDATE itinerariosilos set CodigoSilo = ?, CodigoItinerario = ?, TipoSilo = ?, DataSaida = ?, DataChegada = ?, SituacaoCarga = ? WHERE Codigo = ?";
		}
		
		public static String getDelete() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public static String getSelectAll() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public static String getSelectCodigo() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public static String getSelectItinerario(){
			return "SELECT Codigo, CodigoSilo, CodigoItinerario, TipoSilo, DataSaida, DataChegada,"
					+ " SituacaoCarga FROM itinerariosilos WHERE CodigoItinerario = ?";
		}

		
		
	}
	
}
