package br.edu.unoesc.edi.carcilosystem;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.unoesc.carcilosystem.db.dao.impl.VeiculoDAOImpl;
import br.edu.unoesc.carcilosystem.db.model.Veiculo;

public class FrmCadastrarVeiculo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7700854751612896795L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JFormattedTextField txtAno;
	private JComboBox<?> cbTipoVeiculo;
	private JFormattedTextField txtPlaca;
	
	/**
	 * Create the frame.
	 */
	public FrmCadastrarVeiculo() {
		setTitle("Cadastro de Veículo");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 302, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtNome.setBounds(10, 28, 266, 21);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMarca.setBounds(10, 60, 46, 14);
		contentPane.add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtMarca.setColumns(10);
		txtMarca.setBounds(10, 77, 266, 21);
		contentPane.add(txtMarca);
		
		txtModelo = new JTextField();
		txtModelo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtModelo.setColumns(10);
		txtModelo.setBounds(10, 126, 147, 21);
		contentPane.add(txtModelo);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblModelo.setBounds(10, 109, 68, 14);
		contentPane.add(lblModelo);
		
		JLabel lblTipoVeiculo = new JLabel("Tipo Veiculo:");
		lblTipoVeiculo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTipoVeiculo.setBounds(10, 158, 97, 14);
		contentPane.add(lblTipoVeiculo);
		
		cbTipoVeiculo = new JComboBox();
		cbTipoVeiculo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		cbTipoVeiculo.setModel(new DefaultComboBoxModel(new String[] {"Carro", "Caminhão", "Carreta"}));
		cbTipoVeiculo.setBounds(10, 175, 97, 20);
		contentPane.add(cbTipoVeiculo);
		
		try{
			MaskFormatter ano = new MaskFormatter("####");
			txtAno = new JFormattedTextField(ano);
			txtAno.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtAno.setColumns(10);
			txtAno.setBounds(230, 126, 46, 21);
			contentPane.add(txtAno);
			
			JLabel lblAno = new JLabel("Ano:");
			lblAno.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblAno.setBounds(230, 109, 38, 14);
			contentPane.add(lblAno);
			
			MaskFormatter placa = new MaskFormatter("UUU-####");
			txtPlaca = new JFormattedTextField(placa);
			txtPlaca.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtPlaca.setColumns(10);
			txtPlaca.setBounds(198, 174, 78, 21);
			contentPane.add(txtPlaca);
			
			JLabel lblPlaca = new JLabel("Placa:");
			lblPlaca.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblPlaca.setBounds(200, 158, 46, 14);
			contentPane.add(lblPlaca);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira seu nome do veículo!");
					txtNome.requestFocus();
				}else if (txtMarca.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira a marca do veículo!");
					txtMarca.requestFocus();
				}else if (txtAno.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira o ano do veículo!");
					txtAno.requestFocus();
				}else if (txtModelo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira o modelo do veículo!");
					txtModelo.requestFocus();
				}else if (txtPlaca.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira a placa do veículo!");
					txtPlaca.requestFocus();
				}else {
					
					Veiculo veiculo = new Veiculo();
					
					veiculo.setNome(txtNome.getText());
					veiculo.setMarca(txtMarca.getText());
					veiculo.setAno(Integer.parseInt(txtAno.getText()));
					veiculo.setMarca(txtModelo.getText());
					veiculo.setTipoVeiculo(cbTipoVeiculo.getSelectedItem().toString());
					veiculo.setPlaca(txtPlaca.getText());
					
					VeiculoDAOImpl cadastrarVeiculo = new VeiculoDAOImpl();
					
					if (cadastrarVeiculo.salvar(veiculo)) {
						JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso!");
						LimparCampos();
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao cadastrar veículo!");
					}					
				}
			}
		});
		btnSalvar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnSalvar.setBounds(187, 224, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimparCampos();
			}
		});
		btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnCancelar.setBounds(10, 224, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel label = new JLabel("-----------------------------------------------------------------------");
		label.setBounds(0, 206, 286, 14);
		contentPane.add(label);
	}
	
	private void LimparCampos(){
		txtNome.setText("");
		txtMarca.setText("");
		txtAno.setText("");
		txtModelo.setText("");
		cbTipoVeiculo.setSelectedIndex(0);
		txtPlaca.setText("");
	}
}
