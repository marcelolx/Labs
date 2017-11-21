package br.edu.unoesc.carcilosystem.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	
	private static Connection conexao;
	
	public Connection getConnection() {
        try {
        	String urlConexao = "jdbc:mysql://localhost:3306/carcilo";
        	String usuario = "root";
        	String senha = "";
        	
            return DriverManager.getConnection(urlConexao, usuario, senha);
            
        } catch (SQLException e) {
        	JOptionPane.showMessageDialog(null, "Falha ao conectar com a base de dados!");
            throw new RuntimeException(e);
        }
    }
	
	public Connection UseMainConnection(){
		try {
			if ((conexao == null) || (conexao.isClosed())) {
				conexao = getConnection();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conexao;
	}
}
