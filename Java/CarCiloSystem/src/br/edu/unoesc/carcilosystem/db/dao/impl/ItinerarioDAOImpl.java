package br.edu.unoesc.carcilosystem.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;

import br.edu.unoesc.carcilosystem.db.connection.ConnectionFactory;
import br.edu.unoesc.carcilosystem.db.dao.ItinerarioDAO;
import br.edu.unoesc.carcilosystem.db.model.FilaItinerario;
import br.edu.unoesc.carcilosystem.db.model.Itinerario;
import br.edu.unoesc.carcilosystem.db.model.ItinerarioSilos;

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
				stmt = conexao.prepareStatement(SQL.getInsert());
			} else {
				stmt = conexao.prepareStatement(SQL.getUpdate());
			}
				
			stmt.setInt(paramIndex++, AItinerario.getMotorista());
			stmt.setInt(paramIndex++, AItinerario.getVeiculo());
			stmt.setInt(paramIndex++, AItinerario.getQuantidadeSilosVisitar());
			stmt.setTimestamp(paramIndex++, AItinerario.getDataSaida());
			stmt.setTimestamp(paramIndex++, AItinerario.getDataChegada());
			stmt.setString(paramIndex++, AItinerario.getSituacao());
			stmt.setString(paramIndex++, AItinerario.getDistanciaCalculada());
			stmt.setString(paramIndex++, AItinerario.getTempoPercursoCalculado());
			stmt.setString(paramIndex++, AItinerario.getURLRotaMaps());
			
			if (AItinerario.getCodigo() > 0){
				stmt.setInt(paramIndex, AItinerario.getCodigo());
			}
			
			stmt.execute();			
			stmt.close();
			
			if (AItinerario.getCodigo() == 0){
				stmt = conexao.prepareStatement(SQL.getSelectCodigo());
				
				ResultSet retorno = stmt.executeQuery();
				
				while(retorno.next()){
					codigo = retorno.getInt("Codigo");			
				}
				
				retorno.close();
			} else {
				codigo = AItinerario.getCodigo();
			}
			
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
		return null;
	}

	@Override
	public List<FilaItinerario> localizarTodosADescarregar(Integer AEmpresa) {
		List<FilaItinerario> listaFilaItinerarios = new ArrayList<FilaItinerario>();
		List<ItinerarioSilos> siloOrigemDestino;
		Date date = new Date();
		String dataPreparada;
		
		GetConexao();
		
		try{
			ItinerarioSilosDAOImpl itinerarioSilosDAO = new ItinerarioSilosDAOImpl();
			
			PreparedStatement stmt = conexao.prepareStatement(SQL.selectAllToDump());
			
			stmt.setInt(1, AEmpresa);			
			dataPreparada = new SimpleDateFormat("yyyy-MM-dd").format(date.getTime()) + " 00:00:00";
			stmt.setTimestamp(2, Timestamp.valueOf(dataPreparada));
			dataPreparada = new SimpleDateFormat("yyyy-MM-dd").format(date.getTime()) + " 23:59:59";
			stmt.setTimestamp(3, Timestamp.valueOf(dataPreparada));
			
			ResultSet itinerarios = stmt.executeQuery();
			
			while(itinerarios.next()){
				FilaItinerario filaItinerario = new FilaItinerario();
				
				filaItinerario.setCodigo(itinerarios.getInt("iti.Codigo"));
				filaItinerario.setMotorista(itinerarios.getInt("iti.Motorista"));
				filaItinerario.setVeiculo(itinerarios.getInt("iti.Veiculo"));
				filaItinerario.setQuantidadeSilosVisitar(itinerarios.getInt("iti.QuantidadeSilosVisitar"));
				filaItinerario.setDataSaida(itinerarios.getTimestamp("iti.DataSaida"));
				filaItinerario.setDataChegada(itinerarios.getTimestamp("iti.DataChegada"));
				filaItinerario.setSituacao(itinerarios.getString("iti.Situacao"));
				filaItinerario.setDistanciaCalculada(itinerarios.getString("iti.DistanciaCalculada"));
				filaItinerario.setTempoPercursoCalculado(itinerarios.getString("iti.TempoPercurso"));
				filaItinerario.setURLRotaMaps(itinerarios.getString("iti.URLRotaMaps"));
				filaItinerario.setNomeMotorista(itinerarios.getString("usu.Nome"));
				filaItinerario.setCnh(itinerarios.getString("usu.NumeroRegCNH"));
				filaItinerario.setPlacaVeiculo(itinerarios.getString("vei.Placa"));		
				filaItinerario.setNomeVeiculo(itinerarios.getString("vei.Nome"));
				
				siloOrigemDestino = itinerarioSilosDAO.localizarPorItinerario(filaItinerario.getCodigo());
				
				filaItinerario.setListaItinerarioSilos(siloOrigemDestino);	
				
				listaFilaItinerarios.add(filaItinerario);
			}
			
			itinerarios.close();
			stmt.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaFilaItinerarios;
	}
	
	/**
	 * Classe contendo as instruções SQL para inserir, atualizar, buscar os itinerários.
	 * 
	 * @author Leandro
	 *
	 */
	private static class SQL{
		
		public static String getInsert(){
			return "INSERT INTO itinerario (Motorista, Veiculo, QuantidadeSilosVisitar, DataSaida, DataChegada, Situacao,"
					+ " DistanciaCalculada, TempoPercurso, URLRotaMaps) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		}
		
		public static String getUpdate(){
			return "UPDATE itinerario  set Motorista = ?, Veiculo = ?, QuantidadeSilosVisitar = ?, DataSaida = ?,"
					+ " DataChegada = ?, Situacao = ?, DistanciaCalculada = ?, TempoPercurso = ?, URLRotaMaps = ? WHERE Codigo = ?";
		}
		
		public static String getSelectCodigo(){
			return "SELECT max(Codigo) AS Codigo FROM itinerario"; 
		}
		
		public static String selectAll(){
			return "SELECT Codigo, Motorista, Veiculo, QuantidadeSilosVisitar, DataSaida, DataChegada, Situacao, DistanciaCalculada"
					+ " ,TempoPercurso, URLRotaMaps FROM itinerario";
		}
		
		public static String selectAllToDump(){
			return "SELECT iti.Codigo, iti.Motorista, iti.Veiculo, iti.QuantidadeSilosVisitar, iti.DataSaida, iti.DataChegada," + 
				   "       iti.Situacao, iti.DistanciaCalculada, iti.TempoPercurso, iti.URLRotaMaps, usu.Nome, usu.NumeroRegCNH," +
				   " 	   vei.Placa, vei.Nome " + 
				   " FROM itinerario AS iti" +
				   " INNER JOIN itinerariosilos AS itis ON itis.CodigoItinerario = iti.Codigo" +
				   " INNER JOIN silos AS s ON s.Codigo = itis.CodigoSilo" +
				   " INNER JOIN usuario AS usu ON usu.Codigo = iti.Motorista" +
				   " INNER JOIN veiculo AS vei ON vei.Codigo = iti.Veiculo" +
				   " WHERE s.Empresa = ?" +
				   " AND iti.Situacao <> 'Entregue'" + 
				   " AND iti.Situacao <> 'Cancelada'" +
				   " AND iti.DataChegada BETWEEN ? AND ?" +
				   " GROUP BY iti.Codigo";
		}
		
	}
	
	/**
	 * Verifica se existe uma conexão, caso contrário busca pela instância principal da conexão.
	 */
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
