package br.edu.unoesc.carcilosystem.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.unoesc.carcilosystem.db.connection.ConnectionFactory;
import br.edu.unoesc.carcilosystem.db.dao.ItinerarioDAO;
import br.edu.unoesc.carcilosystem.db.model.Itinerario;

public class ItinerarioDAOImpl implements ItinerarioDAO {

	private static Connection conexao;
	
	public ItinerarioDAOImpl(){
		GetConexao();
	}
	
	@Override
	public Integer salvar(Itinerario AItinerario) {
		
		Integer codigo = 0;
		int paramIndex = 1;
		try{
			GetConexao();
			
			PreparedStatement stmt;
			if (AItinerario.getCodigo() == 0){
				stmt = conexao.prepareStatement(SQL.GetInsert());
			} else {
				stmt = conexao.prepareStatement(SQL.GetUpdate());
			}
				
			stmt.setInt(paramIndex++, AItinerario.getMotorista());
			stmt.setInt(paramIndex++, AItinerario.getVeiculo());
			stmt.setInt(paramIndex++, AItinerario.getQuantidadeSilosVisitar());
			stmt.setTimestamp(paramIndex++, AItinerario.getDataSaida());
			stmt.setTimestamp(paramIndex++, AItinerario.getDataChegada());
			stmt.setString(paramIndex++, AItinerario.getSituacao());
			stmt.setString(paramIndex++, AItinerario.getDistanciaCalculada());
			stmt.setString(paramIndex++, AItinerario.getTempoPercursoCalculado());
			
			if (AItinerario.getCodigo() > 0){
				stmt.setInt(paramIndex, AItinerario.getMotorista());
			}
			
			stmt.execute();			
			stmt.close();
			
			stmt = conexao.prepareStatement(SQL.GetSelectCodigo());
			
			ResultSet retorno = stmt.executeQuery();
			
			while(retorno.next()){
				codigo = retorno.getInt("Codigo");			
			}
			
			retorno.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return codigo;
	}

	@Override
	public boolean excluir(Integer ACodigo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Itinerario localizar(Integer ACodigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Itinerario> localizarEspecificos(Integer[] ACodigos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Itinerario> localizarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static class SQL{
		
		public static String GetInsert(){
			return "INSERT INTO itinerario (Motorista, Veiculo, QuantidadeSilosVisitar, DataSaida, DataChegada, Situacao,"
					+ " DistanciaCalculada, TempoPercurso) VALUES (?, ?, ?, ? , ?, ?, ?, ?)";
		}
		
		public static String GetUpdate(){
			return "UPDATE itinerario  set Motorista = ?, Veiculo = ?, QuantidadeSilosVisitar = ?, DataSaida = ?,"
					+ " DataChegada = ?, Situacao = ?, DistanciaCalculada = ?, TempoPercurso = ? WHERE Codigo = ?";
		}
		
		public static String GetSelectCodigo(){
			return "SELECT max(Codigo) AS Codigo FROM itinerario"; 
		}
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

}
