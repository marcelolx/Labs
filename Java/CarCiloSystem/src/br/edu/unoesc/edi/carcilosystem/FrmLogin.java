package br.edu.unoesc.edi.carcilosystem;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import br.edu.unoesc.carcilosystem.db.dao.impl.UsuarioDAOImpl;

import javax.swing.UIManager;
import java.awt.Color;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField edtUsuario;
	private JPasswordField edtSenha;

     /**
	 * Create the frame.
	 */
	
	public FrmLogin() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 478, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblUsurio.setBounds(180, 254, 84, 21);
		contentPane.add(lblUsurio);
		
		edtUsuario = new JTextField();
		edtUsuario.setBounds(21, 255, 148, 20);
		contentPane.add(edtUsuario);
		edtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSenha.setBounds(180, 286, 84, 21);
		contentPane.add(lblSenha);
		
		edtSenha = new JPasswordField();
		edtSenha.setBounds(21, 287, 148, 20);
		contentPane.add(edtSenha);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//aqui verificar o login do usu�rio e depois abrir form principal
				
				String usuario = edtUsuario.getText();
				String senha = new String(edtSenha.getPassword()).trim();
			
				UsuarioDAOImpl UsuarioLogin = new UsuarioDAOImpl();
				
				if (UsuarioLogin.VerificaLogin(usuario, senha)) {
					FrmMain main = new FrmMain(1);
					main.frame.setTitle("CarCiloSystem | Usuário: " + usuario + " |");
					main.frame.setLocationRelativeTo(null);
					main.frame.setVisible(true);
					dispose();	
				} else {
					JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!.");
				}	
			}
		});
		btnLogar.setBounds(21, 316, 148, 23);
		contentPane.add(btnLogar);
		
		JButton btnCadastrarse = new JButton("Cadastrar-se");
		btnCadastrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//para cadastrar novo usu�rio
				FrmCadastro cadastro = new FrmCadastro(true, "Cadastro de Usuário");
				cadastro.setLocationRelativeTo(null);
				cadastro.setVisible(true);
				dispose();
			}
		});
		btnCadastrarse.setBounds(180, 316, 148, 23);
		contentPane.add(btnCadastrarse);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 52, 409, 58);
		contentPane.add(panel);
		
		JLabel label = new JLabel("CarSiloSystem");
		label.setFont(new Font("SansSerif", Font.PLAIN, 35));
		panel.add(label);
	}
}