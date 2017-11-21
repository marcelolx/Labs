package br.edu.unoesc.edi.carcilosystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.edu.unoesc.carcilosystem.db.dao.impl.SiloDAOImpl;
import br.edu.unoesc.carcilosystem.db.model.Silo;

public class FrmCadastroSilo extends JFrame {

	private JPanel contentPane;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtSiglaUF;
	private JTextField txtNumero;
	private JTextField txtNomeSilo;
	private static JTextField txtLongitude;
	private static JTextField txtLatitude;
	private Silo silo;
	
	private static String[] LatitudeLongitude = null;

	/**
	 * Create the frame.
	 */
	public FrmCadastroSilo() {
		setTitle("Cadastrar Silo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 587, 165);
		contentPane.add(panel);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtCidade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira o nome da Cidade!");
					txtCidade.requestFocus();
				}else if (txtEstado.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira o nome do Estado!");
					txtEstado.requestFocus();
				}else if (txtRua.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira o nome da Rua!");
					txtRua.requestFocus();
				}else if (txtBairro.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira o nome do Bairro!");
					txtBairro.requestFocus();
				}else if (txtNumero.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira o número do silo!");
					txtNumero.requestFocus();
				}else if (txtSiglaUF.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira a sigla do Estado!");
					txtSiglaUF.requestFocus();
				}else if (txtLatitude.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Latitude é um campo obrigatório!");
					txtLatitude.requestFocus();
				}else if (txtLongitude.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Longitude é um campo obrigatório!");
					txtLongitude.requestFocus();
				}else {
					
					silo = new Silo();
					
					silo.setCidade(txtCidade.getText());
					silo.setEstado(txtEstado.getText());
					silo.setRua(txtRua.getText());
					silo.setBairro(txtBairro.getText());
					silo.setNumero(txtNumero.getText());
					silo.setSiglaUF(txtSiglaUF.getText());
					silo.setLatitude(txtLatitude.getText());
					silo.setLongitude(txtLongitude.getText());
					silo.setNome(txtNomeSilo.getText());
					
					SiloDAOImpl SiloDAO = new SiloDAOImpl();
					
					if (SiloDAO.inserir(silo)) {
						JOptionPane.showMessageDialog(null, "Silo salvo com sucesso!");
						LimparCampos();
					}else {
						JOptionPane.showMessageDialog(null, "Falha ao salvar silo");
					}
				}
			}
		});
		btnSalvar.setBounds(486, 131, 89, 23);
		panel.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (silo != null) {
					if (silo.getCodigo() > 0) {
						SiloDAOImpl SiloDAO = new SiloDAOImpl();
						
						if  (SiloDAO.excluir(silo.getCodigo())) {
							JOptionPane.showMessageDialog(null, "Silo excluído com sucesso!");
						}
					}
				}
			}
		});
		btnExcluir.setBounds(377, 131, 107, 23);
		panel.add(btnExcluir);
		
		JLabel label = new JLabel("Cidade:");
		label.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label.setBounds(6, 14, 46, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Rua:");
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_1.setBounds(6, 43, 42, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Bairro:");
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_2.setBounds(6, 72, 46, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("N\u00FAmero:");
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_3.setBounds(473, 40, 50, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Estado:");
		label_4.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_4.setBounds(334, 14, 46, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Sigla UF:");
		label_5.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_5.setBounds(334, 40, 50, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Nome Silo:");
		label_6.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_6.setBounds(334, 69, 68, 14);
		panel.add(label_6);
		
		txtCidade = new JTextField();
		txtCidade.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCidade.setColumns(10);
		txtCidade.setBounds(55, 11, 239, 20);
		panel.add(txtCidade);
		
		txtEstado = new JTextField();
		txtEstado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtEstado.setColumns(10);
		txtEstado.setBounds(390, 11, 185, 20);
		panel.add(txtEstado);
		
		txtRua = new JTextField();
		txtRua.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtRua.setColumns(10);
		txtRua.setBounds(55, 40, 184, 20);
		panel.add(txtRua);
		
		txtBairro = new JTextField();
		txtBairro.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtBairro.setColumns(10);
		txtBairro.setBounds(55, 69, 183, 20);
		panel.add(txtBairro);
		
		txtSiglaUF = new JTextField();
		txtSiglaUF.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSiglaUF.setColumns(10);
		txtSiglaUF.setBounds(390, 37, 46, 20);
		panel.add(txtSiglaUF);
		
		txtNumero = new JTextField();
		txtNumero.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNumero.setColumns(10);
		txtNumero.setBounds(529, 37, 46, 20);
		panel.add(txtNumero);
		
		txtNomeSilo = new JTextField();
		txtNomeSilo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNomeSilo.setColumns(10);
		txtNomeSilo.setBounds(398, 66, 177, 20);
		panel.add(txtNomeSilo);
		
		JLabel label_7 = new JLabel("Longitude:");
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_7.setBounds(6, 100, 68, 14);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Latitude:");
		label_8.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_8.setBounds(334, 97, 57, 14);
		panel.add(label_8);
		
		txtLongitude = new JTextField();
		txtLongitude.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtLongitude.setEditable(false);
		txtLongitude.setColumns(10);
		txtLongitude.setBounds(65, 98, 174, 20);
		panel.add(txtLongitude);
		
		txtLatitude = new JTextField();
		txtLatitude.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtLatitude.setEditable(false);
		txtLatitude.setColumns(10);
		txtLatitude.setBounds(389, 94, 186, 20);
		panel.add(txtLatitude);
		
		JButton btnLimpar = new JButton("Cancelar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimparCampos();
			}
		});
		btnLimpar.setBounds(267, 131, 107, 23);
		panel.add(btnLimpar);
		
		JButton btnMaps = new JButton("Maps");
		btnMaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				FrmMaps mapa = new FrmMaps("http://maps.google.com", FrmCadastroSilo.class.getName());		
				mapa.frame.setTitle("CarCiloSystem");
				mapa.frame.setLocationRelativeTo(null);
				mapa.frame.setVisible(true);
			}
		});
		btnMaps.setBounds(6, 131, 89, 23);
		panel.add(btnMaps);
	}

	
	private void LimparCampos(){
		txtCidade.setText("");
		txtEstado.setText("");
		txtBairro.setText("");
		txtLatitude.setText("");
		txtLongitude.setText("");
		txtNomeSilo.setText("");
		txtNumero.setText("");
		txtRua.setText("");
		txtSiglaUF.setText("");
	}
	
	
	public static void SetLatitudeLongitude(String[] ALatitudeLongitude){

		LatitudeLongitude = ALatitudeLongitude;
		
		if (LatitudeLongitude != null){
			
			txtLatitude.setText(LatitudeLongitude[0]);
			txtLongitude.setText(LatitudeLongitude[1]);
		}
	}
	
}
