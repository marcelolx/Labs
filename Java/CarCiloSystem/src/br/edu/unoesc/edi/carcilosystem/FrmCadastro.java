package br.edu.unoesc.edi.carcilosystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
/**
 * @author
 *
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.unoesc.carcilosystem.db.dao.impl.UsuarioDAOImpl;
import br.edu.unoesc.carcilosystem.db.model.Usuario;

public class FrmCadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 798691094720255014L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtNomeUsuario;
	private JTextField txtRG;
	private JPasswordField txtSenha;

	/**
	 * Create the frame.
	 */
	public FrmCadastro(boolean AAbirTelaLogin, String ATitulo) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				
				if (AAbirTelaLogin) {
					FrmLogin frmLogin = new FrmLogin();
					frmLogin.setLocationRelativeTo(null);
					frmLogin.setVisible(true);
				}
			}
		});
		setTitle(ATitulo);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 687, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNome.setBounds(53, 42, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtNome.setBounds(53, 60, 300, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNomeDeUsurio = new JLabel("Nome de usu\u00E1rio:");
		lblNomeDeUsurio.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNomeDeUsurio.setBounds(449, 42, 160, 18);
		contentPane.add(lblNomeDeUsurio);
		
		txtNomeUsuario = new JTextField();
		txtNomeUsuario.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtNomeUsuario.setBounds(449, 63, 180, 20);
		contentPane.add(txtNomeUsuario);
		txtNomeUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSenha.setBounds(449, 87, 60, 18);
		contentPane.add(lblSenha);
		
		JLabel label2 = new JLabel("Informa\u00E7\u00F5es de Usu\u00E1rio");
		label2.setFont(new Font("SansSerif", Font.BOLD, 15));
		label2.setBounds(449, 25, 200, 18);
		contentPane.add(label2);
		
		JLabel label = new JLabel("Informa\u00E7\u00F5es Pessoais");
		label.setFont(new Font("SansSerif", Font.BOLD, 15));
		label.setBounds(53, 25, 200, 18);
		contentPane.add(label);
			
		
		JLabel lblNewLabel_1 = new JLabel("Data Nascimento:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(38, 87, 126, 18);
		contentPane.add(lblNewLabel_1);
		
		try {
			JLabel lblRG = new JLabel("RG:");
			lblRG.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblRG.setBounds(263, 87, 90, 18);
			contentPane.add(lblRG);
			
			MaskFormatter rg = new MaskFormatter("#.###.###");
			JFormattedTextField txtRG = new JFormattedTextField(rg);
			txtRG.setHorizontalAlignment(SwingConstants.RIGHT);
			txtRG.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtRG.setBounds(263, 106, 90, 21);	
			contentPane.add(txtRG);
			
			
			MaskFormatter data = new MaskFormatter("##/##/####");
			JFormattedTextField txtDataNascimento = new JFormattedTextField(data);
			txtDataNascimento.setHorizontalAlignment(SwingConstants.RIGHT);
			txtDataNascimento.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtDataNascimento.setBounds(53, 107, 101, 20);			
			contentPane.add(txtDataNascimento);
			
						
			txtSenha = new JPasswordField();
			txtSenha.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtSenha.setBounds(449, 109, 180, 20);
			contentPane.add(txtSenha);
			
			JLabel lblCpf = new JLabel("CPF:");
			lblCpf.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblCpf.setBounds(53, 138, 46, 14);
			contentPane.add(lblCpf);
			
			MaskFormatter cpf = new MaskFormatter("###.###.###-##");
			JFormattedTextField txtCPF = new JFormattedTextField(cpf);
			txtCPF.setHorizontalAlignment(SwingConstants.CENTER);
			txtCPF.setToolTipText("");
			txtCPF.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtCPF.setBounds(53, 155, 116, 21);
			contentPane.add(txtCPF);
						
			JLabel lblNSerieCnh = new JLabel("Nº Serie CNH:");
			lblNSerieCnh.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblNSerieCnh.setBounds(237, 138, 100, 14);
			contentPane.add(lblNSerieCnh);
			
			MaskFormatter cnh = new MaskFormatter("###########");
			JFormattedTextField txtNSerieCNH = new JFormattedTextField(cnh);
			txtNSerieCNH.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtNSerieCNH.setBounds(237, 155, 116, 21);
			contentPane.add(txtNSerieCNH);
			
			JLabel lblCategoriaHabilitao = new JLabel("Categoria Habilitação:");
			lblCategoriaHabilitao.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblCategoriaHabilitao.setBounds(53, 187, 160, 20);
			contentPane.add(lblCategoriaHabilitao);
			
			MaskFormatter categoriaHabilitacao = new MaskFormatter("UU");
			JFormattedTextField txtCategoriaHabilitacao = new JFormattedTextField(categoriaHabilitacao);
			txtCategoriaHabilitacao.setBounds(53, 209, 60, 21);
			contentPane.add(txtCategoriaHabilitacao);
			
			
			
			JButton btnLimpar = new JButton("Limpar");
			btnLimpar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtNome.setText("");
					txtRG.setText("");
					txtDataNascimento.setText("");
					txtNomeUsuario.setText("");
					txtSenha.setText("");					
				}
			});
			btnLimpar.setFont(new Font("SansSerif", Font.PLAIN, 14));
			btnLimpar.setBounds(449, 206, 80, 23);
			contentPane.add(btnLimpar);
			
			JButton btnCadastrar = new JButton("Cadastrar");
			btnCadastrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String senha = new String(txtSenha.getPassword());
					if (txtNome.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Insira seu nome completo!");
						txtNome.requestFocus();
					}else if (txtRG.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Insira a sua idade!");
						txtRG.requestFocus();
					}else if (txtDataNascimento.getText().equalsIgnoreCase("  /  /    ")) {
						JOptionPane.showMessageDialog(null, "Insira a sua data de nascimento!");
						txtDataNascimento.requestFocus();
					}else if (txtNomeUsuario.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Insira um nome de usuario!");
						txtNomeUsuario.requestFocus();
					}else if (senha.length() == 0) {
						JOptionPane.showMessageDialog(null, "Insira uma senha!");
						txtSenha.requestFocus();
					}else{
						
					    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
					    
					    
					    java.sql.Date DataNascimento = null;
						try {
							DataNascimento = new java.sql.Date(formatter.parse(txtDataNascimento.getText()).getTime());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					    
						
						Usuario user = new Usuario();
						user.setNome(txtNome.getText());
						user.setLogin(txtNomeUsuario.getText());
						user.setSenha(senha);
						user.setDataNascimento(DataNascimento);
						user.setRg(txtRG.getText());
						user.setCpf(txtCPF.getText());			
						user.setNumeroRegCNH(Long.parseLong(txtNSerieCNH.getText()));
						user.setCategoriaHabilitacao(txtCategoriaHabilitacao.getText());
						
						
						UsuarioDAOImpl CadastraUsuario = new UsuarioDAOImpl();
						
						
						if (CadastraUsuario.salvar(user)) {
							JOptionPane.showMessageDialog(null, "Usuario cadastrado com sucesso!");
							btnLimpar.doClick();
						} else {
							JOptionPane.showMessageDialog(null, "Falha ao Cadastrar o usuario!");
						}								
					}
				}
			});
			btnCadastrar.setFont(new Font("SansSerif", Font.PLAIN, 14));
			btnCadastrar.setBounds(534, 206, 95, 23);
			contentPane.add(btnCadastrar);		
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
