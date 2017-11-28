package br.edu.unoesc.edi.carcilosystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import org.dom4j.Document;

import br.edu.unoesc.carcilosystem.db.dao.impl.SiloDAOImpl;
import br.edu.unoesc.carcilosystem.db.dao.impl.UsuarioDAOImpl;
import br.edu.unoesc.carcilosystem.db.dao.impl.VeiculoDAOImpl;
import br.edu.unoesc.carcilosystem.db.model.Itinerario;
import br.edu.unoesc.carcilosystem.db.model.ItinerarioSilos;
import br.edu.unoesc.carcilosystem.db.model.Silo;
import br.edu.unoesc.carcilosystem.db.model.Usuario;
import br.edu.unoesc.carcilosystem.db.model.Veiculo;
import br.edu.unoesc.carcilosystem.maps.DirectionsXML;
import br.edu.unoesc.carcilosystem.maps.GoogleDirectionsFactory;
import br.edu.unoesc.carcilosystem.rgn.impl.ItinerarioRgnImpl;

public class FrmItinerario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5913684370356862801L;
	private JPanel contentPane;
	private JTextField txtSituacao;
	private JTextField txtDataSaida;
	private JTextField txtHoraSaida;
	private JTextField txtDataChegada;
	private JTextField txtHoraChegada;
	private JComboBox cbMotoristas;
	private JComboBox cbVeiculo;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtSiglaUF;
	private JTextField txtNumero;
	private JTextField txtNomesilo;
	private JTextField txtLongitude;
	private JTextField txtLatitude;
	private JTextField txtCidadeDest;
	private JTextField txtEstadoDest;
	private JTextField txtRuaDest;
	private JTextField txtBairroDest;
	private JTextField txtSiglaUFDest;
	private JTextField txtNumeroDest;
	private JTextField txtNomeSiloDest;
	private JTextField txtLongitudeDest;
	private JTextField txtLatitudeDest;
	private JComboBox cbSiloDestino;
	private JComboBox cbSiloOrigem;
	private JTextField txtTempoestimado;
	private JTextField txtKmpercurso; 
	
	private List<Veiculo> ListaVeiculos;
	private List<Silo> ListaSilos;
	private Silo SiloOrigemSelecionado = null;
	private Silo SiloDestinoSelecionado = null;
	
	
	
	/**
	 * Create the frame.
	 */
	public FrmItinerario() {
		ItinerarioRgnImpl itinerarioRgn = new ItinerarioRgnImpl();
		
		setFont(new Font("SansSerif", Font.PLAIN, 14));
		setTitle("Itiner\u00E1rio");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 556, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlSuperior = new JPanel();
		pnlSuperior.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSuperior.setBounds(10, 11, 520, 127);
		contentPane.add(pnlSuperior);
		pnlSuperior.setLayout(null);
				
		JLabel lblMotorista = new JLabel("Motorista:");
		lblMotorista.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblMotorista.setBounds(10, 11, 68, 21);
		pnlSuperior.add(lblMotorista);
		
		cbMotoristas = new JComboBox();
		cbMotoristas.setBounds(10, 32, 199, 20);
		pnlSuperior.add(cbMotoristas);
		
		cbVeiculo = new JComboBox();
		cbVeiculo.setBounds(10, 76, 199, 20);
		pnlSuperior.add(cbVeiculo);
		
		JLabel lblVeiculo = new JLabel("Veiculo:");
		lblVeiculo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblVeiculo.setBounds(10, 55, 68, 21);
		pnlSuperior.add(lblVeiculo);
		
		JLabel lblDataSaida = new JLabel("Data Sa\u00EDda:");
		lblDataSaida.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDataSaida.setBounds(249, 11, 84, 21);
		pnlSuperior.add(lblDataSaida);
		
		JLabel lblHoraSaida = new JLabel("Hora Sa\u00EDda:");
		lblHoraSaida.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblHoraSaida.setBounds(363, 11, 84, 21);
		pnlSuperior.add(lblHoraSaida);
		
		JLabel lblDataChegada = new JLabel("Data Chegada:");
		lblDataChegada.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDataChegada.setBounds(249, 55, 109, 21);
		pnlSuperior.add(lblDataChegada);
		
		JLabel lblHoraChegada = new JLabel("Hora Chegada:");
		lblHoraChegada.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblHoraChegada.setBounds(363, 55, 109, 21);
		pnlSuperior.add(lblHoraChegada);
		
		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituao.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSituao.setBounds(10, 100, 68, 21);
		pnlSuperior.add(lblSituao);
		
		txtSituacao = new JTextField();
		txtSituacao.setText("N\u00E3o Iniciado");
		txtSituacao.setEditable(false);
		txtSituacao.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtSituacao.setColumns(10);
		txtSituacao.setBounds(74, 100, 103, 20);
		pnlSuperior.add(txtSituacao);
		
		try {
			MaskFormatter qtdSilos = new MaskFormatter("#");
			
			MaskFormatter dataSaida = new MaskFormatter("##/##/####");
			txtDataSaida = new JFormattedTextField(dataSaida);
			txtDataSaida.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtDataSaida.setColumns(10);
			txtDataSaida.setBounds(249, 32, 76, 20);
			pnlSuperior.add(txtDataSaida);
			
			MaskFormatter horaSaida = new MaskFormatter("##:##:##");
			txtHoraSaida = new JFormattedTextField(horaSaida);
			txtHoraSaida.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtHoraSaida.setColumns(10);
			txtHoraSaida.setBounds(363, 32, 76, 20);
			pnlSuperior.add(txtHoraSaida);
			
			MaskFormatter dataChegada = new MaskFormatter("##/##/####");
			txtDataChegada = new JFormattedTextField(dataChegada);
			txtDataChegada.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtDataChegada.setColumns(10);
			txtDataChegada.setBounds(249, 76, 76, 20);
			pnlSuperior.add(txtDataChegada);
			
			MaskFormatter horaChegada = new MaskFormatter("##:##:##");
			txtHoraChegada = new JFormattedTextField(horaChegada);
			txtHoraChegada.setFont(new Font("SansSerif", Font.PLAIN, 14));
			txtHoraChegada.setColumns(10);
			txtHoraChegada.setBounds(363, 76, 76, 20);
			pnlSuperior.add(txtHoraChegada);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JPanel pnlInferior = new JPanel();
		pnlInferior.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlInferior.setBounds(10, 570, 520, 39);
		contentPane.add(pnlInferior);
		pnlInferior.setLayout(null);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(421, 8, 89, 23);
		pnlInferior.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(322, 8, 89, 23);
		pnlInferior.add(btnCancelar);
		
		JButton btnLimparCampos = new JButton("Limpar");
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimparCampos();
			}
		});
		btnLimparCampos.setBounds(223, 8, 89, 23);
		pnlInferior.add(btnLimparCampos);
		
		JButton btnCalcularTempo = new JButton("Calcular Tempo");
		btnCalcularTempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (SiloOrigemSelecionado == null) {
					JOptionPane.showMessageDialog(cbSiloOrigem, "Selecione uma origem!");
				} else if (SiloDestinoSelecionado == null) {
					JOptionPane.showMessageDialog(cbSiloDestino, "Selecione um destino!");
				} else {
					
					String Origem = GoogleDirectionsFactory.PreparaURL(
							SiloOrigemSelecionado.getNome(), 
							SiloOrigemSelecionado.getRua(), 
							SiloOrigemSelecionado.getBairro(), 
							SiloOrigemSelecionado.getCidade(), 
							SiloOrigemSelecionado.getSiglaUF());
					
					String Destino = GoogleDirectionsFactory.PreparaURL(
							SiloDestinoSelecionado.getNome(), 
							SiloDestinoSelecionado.getRua(), 
							SiloDestinoSelecionado.getBairro(), 
							SiloDestinoSelecionado.getCidade(), 
							SiloDestinoSelecionado.getSiglaUF());
					
					GoogleDirectionsFactory.BuscaRota(Origem, Destino);
					
					Document document = GoogleDirectionsFactory.getDocumentInstance();
					
					String Distancia = DirectionsXML.GetDistancia(document);
					String TempoPercurso = DirectionsXML.GetTempoPercurso(document);
					
					txtKmpercurso.setText(Distancia);
					txtTempoestimado.setText(TempoPercurso);
				}
				
			}
		});
		btnCalcularTempo.setBounds(10, 8, 150, 23);
		pnlInferior.add(btnCalcularTempo);
		
		JPanel pnlOrigemDestino = new JPanel();
		pnlOrigemDestino.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlOrigemDestino.setBounds(10, 145, 520, 389);
		contentPane.add(pnlOrigemDestino);
		pnlOrigemDestino.setLayout(null);
		
		JPanel pnlOrigem = new JPanel();
		pnlOrigem.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlOrigem.setBounds(10, 21, 500, 165);
		pnlOrigemDestino.add(pnlOrigem);
		pnlOrigem.setLayout(null);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblCidade.setBounds(10, 50, 46, 14);
		pnlOrigem.add(lblCidade);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblRua.setBounds(10, 79, 42, 14);
		pnlOrigem.add(lblRua);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblBairro.setBounds(10, 108, 46, 14);
		pnlOrigem.add(lblBairro);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		lblNmero.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNmero.setBounds(392, 79, 50, 14);
		pnlOrigem.add(lblNmero);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblEstado.setBounds(308, 50, 46, 14);
		pnlOrigem.add(lblEstado);
		
		JLabel lblSiglaUf = new JLabel("Sigla UF:");
		lblSiglaUf.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblSiglaUf.setBounds(253, 79, 50, 14);
		pnlOrigem.add(lblSiglaUf);
		
		JLabel lblNome = new JLabel("Nome Silo:");
		lblNome.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblNome.setBounds(253, 108, 68, 14);
		pnlOrigem.add(lblNome);
		
		txtCidade = new JTextField();
		txtCidade.setEditable(false);
		txtCidade.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCidade.setBounds(59, 47, 239, 20);
		pnlOrigem.add(txtCidade);
		txtCidade.setColumns(10);
		
		txtEstado = new JTextField();
		txtEstado.setEditable(false);
		txtEstado.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtEstado.setBounds(364, 48, 130, 20);
		pnlOrigem.add(txtEstado);
		txtEstado.setColumns(10);
		
		txtRua = new JTextField();
		txtRua.setEditable(false);
		txtRua.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtRua.setBounds(59, 76, 184, 20);
		pnlOrigem.add(txtRua);
		txtRua.setColumns(10);
		
		txtBairro = new JTextField();
		txtBairro.setEditable(false);
		txtBairro.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtBairro.setColumns(10);
		txtBairro.setBounds(59, 105, 183, 20);
		pnlOrigem.add(txtBairro);
		
		txtSiglaUF = new JTextField();
		txtSiglaUF.setEditable(false);
		txtSiglaUF.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSiglaUF.setBounds(307, 76, 46, 20);
		pnlOrigem.add(txtSiglaUF);
		txtSiglaUF.setColumns(10);
		
		txtNumero = new JTextField();
		txtNumero.setEditable(false);
		txtNumero.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNumero.setColumns(10);
		txtNumero.setBounds(448, 76, 46, 20);
		pnlOrigem.add(txtNumero);
		
		txtNomesilo = new JTextField();
		txtNomesilo.setEditable(false);
		txtNomesilo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNomesilo.setBounds(317, 105, 177, 20);
		pnlOrigem.add(txtNomesilo);
		txtNomesilo.setColumns(10);
		
		JLabel lblLongitude = new JLabel("Longitude:");
		lblLongitude.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLongitude.setBounds(10, 136, 68, 14);
		pnlOrigem.add(lblLongitude);
		
		JLabel lblLatitude = new JLabel("Latitude:");
		lblLatitude.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblLatitude.setBounds(253, 137, 57, 14);
		pnlOrigem.add(lblLatitude);
		
		txtLongitude = new JTextField();
		txtLongitude.setEditable(false);
		txtLongitude.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtLongitude.setColumns(10);
		txtLongitude.setBounds(69, 134, 174, 20);
		pnlOrigem.add(txtLongitude);
		
		txtLatitude = new JTextField();
		txtLatitude.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtLatitude.setEditable(false);
		txtLatitude.setColumns(10);
		txtLatitude.setBounds(308, 134, 186, 20);
		pnlOrigem.add(txtLatitude);
		
		cbSiloOrigem = new JComboBox();
		cbSiloOrigem.setBounds(10, 21, 293, 20);
		pnlOrigem.add(cbSiloOrigem);
		
		JLabel lblSelecionarSilo = new JLabel("Selecionar Silo:");
		lblSelecionarSilo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSelecionarSilo.setBounds(10, 0, 119, 21);
		pnlOrigem.add(lblSelecionarSilo);
		
		JLabel label = new JLabel("Origem:");
		label.setFont(new Font("SansSerif", Font.PLAIN, 14));
		label.setBounds(10, 0, 97, 19);
		pnlOrigemDestino.add(label);
		
		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDestino.setBounds(10, 197, 97, 19);
		pnlOrigemDestino.add(lblDestino);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 216, 500, 165);
		pnlOrigemDestino.add(panel);
		
		JLabel label_1 = new JLabel("Cidade:");
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_1.setBounds(10, 53, 46, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Rua:");
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_2.setBounds(10, 82, 42, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Bairro:");
		label_3.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_3.setBounds(10, 111, 46, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("N\u00FAmero:");
		label_4.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_4.setBounds(392, 82, 50, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Estado:");
		label_5.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_5.setBounds(308, 53, 46, 14);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Sigla UF:");
		label_6.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_6.setBounds(253, 82, 50, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("Nome Silo:");
		label_7.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_7.setBounds(253, 111, 68, 14);
		panel.add(label_7);
		
		txtCidadeDest = new JTextField();
		txtCidadeDest.setEditable(false);
		txtCidadeDest.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtCidadeDest.setColumns(10);
		txtCidadeDest.setBounds(59, 50, 239, 20);
		panel.add(txtCidadeDest);
		
		txtEstadoDest = new JTextField();
		txtEstadoDest.setEditable(false);
		txtEstadoDest.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtEstadoDest.setColumns(10);
		txtEstadoDest.setBounds(364, 51, 130, 20);
		panel.add(txtEstadoDest);
		
		txtRuaDest = new JTextField();
		txtRuaDest.setEditable(false);
		txtRuaDest.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtRuaDest.setColumns(10);
		txtRuaDest.setBounds(59, 79, 184, 20);
		panel.add(txtRuaDest);
		
		txtBairroDest = new JTextField();
		txtBairroDest.setEditable(false);
		txtBairroDest.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtBairroDest.setColumns(10);
		txtBairroDest.setBounds(59, 108, 183, 20);
		panel.add(txtBairroDest);
		
		txtSiglaUFDest = new JTextField();
		txtSiglaUFDest.setEditable(false);
		txtSiglaUFDest.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtSiglaUFDest.setColumns(10);
		txtSiglaUFDest.setBounds(307, 79, 46, 20);
		panel.add(txtSiglaUFDest);
		
		txtNumeroDest = new JTextField();
		txtNumeroDest.setEditable(false);
		txtNumeroDest.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNumeroDest.setColumns(10);
		txtNumeroDest.setBounds(448, 79, 46, 20);
		panel.add(txtNumeroDest);
		
		txtNomeSiloDest = new JTextField();
		txtNomeSiloDest.setEditable(false);
		txtNomeSiloDest.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtNomeSiloDest.setColumns(10);
		txtNomeSiloDest.setBounds(317, 108, 177, 20);
		panel.add(txtNomeSiloDest);
		
		JLabel label_8 = new JLabel("Longitude:");
		label_8.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_8.setBounds(10, 139, 68, 14);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("Latitude:");
		label_9.setFont(new Font("SansSerif", Font.PLAIN, 12));
		label_9.setBounds(253, 140, 57, 14);
		panel.add(label_9);
		
		txtLongitudeDest = new JTextField();
		txtLongitudeDest.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtLongitudeDest.setEditable(false);
		txtLongitudeDest.setColumns(10);
		txtLongitudeDest.setBounds(69, 137, 174, 20);
		panel.add(txtLongitudeDest);
		
		txtLatitudeDest = new JTextField();
		txtLatitudeDest.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtLatitudeDest.setEditable(false);
		txtLatitudeDest.setColumns(10);
		txtLatitudeDest.setBounds(308, 137, 186, 20);
		panel.add(txtLatitudeDest);
		
		cbSiloDestino = new JComboBox();
		cbSiloDestino.setBounds(10, 19, 293, 20);
		panel.add(cbSiloDestino);
		
		JLabel label_10 = new JLabel("Selecionar Silo:");
		label_10.setFont(new Font("SansSerif", Font.PLAIN, 14));
		label_10.setBounds(10, -2, 119, 21);
		panel.add(label_10);
		
		JButton btnVisualizarRota = new JButton("Rota");
		btnVisualizarRota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (SiloOrigemSelecionado == null) {
					JOptionPane.showMessageDialog(cbSiloOrigem, "Selecione uma origem!");
				} else if (SiloDestinoSelecionado == null) {
					JOptionPane.showMessageDialog(cbSiloDestino, "Selecione um destino!");
				} else {
					String Origem = GoogleDirectionsFactory.PreparaURL(
							SiloOrigemSelecionado.getNome(), 
							SiloOrigemSelecionado.getRua(), 
							SiloOrigemSelecionado.getBairro(), 
							SiloOrigemSelecionado.getCidade(), 
							SiloOrigemSelecionado.getSiglaUF());
					Origem = Origem + ",+Brasil";
					
					String Destino = GoogleDirectionsFactory.PreparaURL(
							SiloDestinoSelecionado.getNome(), 
							SiloDestinoSelecionado.getRua(), 
							SiloDestinoSelecionado.getBairro(), 
							SiloDestinoSelecionado.getCidade(), 
							SiloDestinoSelecionado.getSiglaUF());
					
					String URLMaps = GoogleDirectionsFactory.GenerateURLMapsDir(
							Origem, 
							Destino, 
							SiloOrigemSelecionado.getLatitude(), 
							SiloOrigemSelecionado.getLongitude(),
							SiloDestinoSelecionado.getLatitude(),
							SiloDestinoSelecionado.getLongitude());
					
					FrmMaps mapa = new FrmMaps(URLMaps, FrmMaps.class.getName());		
					mapa.frame.setTitle("CarCiloSystem - Percurso - Itinerário");
					mapa.frame.setLocationRelativeTo(null);
					mapa.frame.setVisible(true);			
				}	
			}
		});
		btnVisualizarRota.setBounds(427, 540, 103, 23);
		contentPane.add(btnVisualizarRota);
		
		JLabel lblTempoEstimado = new JLabel("Tempo Estimado:");
		lblTempoEstimado.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblTempoEstimado.setBounds(10, 540, 119, 23);
		contentPane.add(lblTempoEstimado);
		
		txtTempoestimado = new JTextField();
		txtTempoestimado.setEditable(false);
		txtTempoestimado.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtTempoestimado.setBounds(125, 540, 142, 23);
		contentPane.add(txtTempoestimado);
		txtTempoestimado.setColumns(10);
		
		JLabel lblKm = new JLabel("Dist\u00E2ncia:");
		lblKm.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblKm.setBounds(271, 540, 62, 23);
		contentPane.add(lblKm);
		
		txtKmpercurso = new JTextField();
		txtKmpercurso.setEditable(false);
		txtKmpercurso.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtKmpercurso.setBounds(335, 540, 86, 23);
		contentPane.add(txtKmpercurso);
		txtKmpercurso.setColumns(10);
		
		PopularComboMotoristas();
		PopularComboVeiculos();
		PopularComboSiloOrigemEDestino();
		
		cbSiloOrigem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String SiloSelecionado = cbSiloOrigem.getSelectedItem().toString();
				String[] CodigoSilo = SiloSelecionado.split("-");
								
				Integer Codigo = Integer.parseInt(CodigoSilo[0].trim());
								
				Iterator<Silo> iterator = ListaSilos.iterator();
						
				while(iterator.hasNext()){
					SiloOrigemSelecionado = iterator.next();
					
					if (SiloOrigemSelecionado.getCodigo() == Codigo) {
						txtCidade.setText(SiloOrigemSelecionado.getCidade());
						txtEstado.setText(SiloOrigemSelecionado.getEstado());
						txtRua.setText(SiloOrigemSelecionado.getRua());
						txtSiglaUF.setText(SiloOrigemSelecionado.getSiglaUF());
						txtNumero.setText(SiloOrigemSelecionado.getNumero());
						txtBairro.setText(SiloOrigemSelecionado.getBairro());
						txtNomesilo.setText(SiloOrigemSelecionado.getNome());
						txtLongitude.setText(SiloOrigemSelecionado.getLongitude());
						txtLatitude.setText(SiloOrigemSelecionado.getLatitude());
						break;
					}				
					
				}
			}
		});
		
		cbSiloDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String SiloSelecionado = cbSiloDestino.getSelectedItem().toString();
				String[] CodigoSilo = SiloSelecionado.split("-");
								
				Integer Codigo = Integer.parseInt(CodigoSilo[0].trim());
								
				Iterator<Silo> iterator = ListaSilos.iterator();
						
				while(iterator.hasNext()){
					SiloDestinoSelecionado = iterator.next();
					
					if (SiloDestinoSelecionado.getCodigo() == Codigo) {
						txtCidadeDest.setText(SiloDestinoSelecionado.getCidade());
						txtEstadoDest.setText(SiloDestinoSelecionado.getEstado());
						txtRuaDest.setText(SiloDestinoSelecionado.getRua());
						txtSiglaUFDest.setText(SiloDestinoSelecionado.getSiglaUF());
						txtNumeroDest.setText(SiloDestinoSelecionado.getNumero());
						txtBairroDest.setText(SiloDestinoSelecionado.getBairro());
						txtNomeSiloDest.setText(SiloDestinoSelecionado.getNome());
						txtLongitudeDest.setText(SiloDestinoSelecionado.getLongitude());
						txtLatitudeDest.setText(SiloDestinoSelecionado.getLatitude());
						break;
					}				
					
				}
			}
		});
		
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				
				if (txtDataSaida.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtDataSaida, "Campo Data Saída é obrigatório!");
				} else if (txtHoraSaida.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtHoraSaida, "Campo Hora Saída é obrigatório!");
				} else if (txtDataChegada.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtDataChegada, "Campo Data Chegada é obrigatório!");
				} else if (txtHoraChegada.getText().isEmpty()) {
					JOptionPane.showMessageDialog(txtHoraChegada, "Campos Hora Chegada é obrigatório!");
				} else if ((txtTempoestimado.getText().isEmpty()) && (txtKmpercurso.getText().isEmpty())) {
					JOptionPane.showMessageDialog(btnCalcularTempo, "Selecione os silos e calcule o tempo e distância!");
				} else {
					List<ItinerarioSilos> listaItinerarioSilos = new ArrayList<ItinerarioSilos>();					
					Itinerario itinerario = new Itinerario();					
					
					Timestamp DataHoraSaida = null;
					Timestamp DataHoraChegada = null;
					
					String Origem = GoogleDirectionsFactory.PreparaURL(
							SiloOrigemSelecionado.getNome(), 
							SiloOrigemSelecionado.getRua(), 
							SiloOrigemSelecionado.getBairro(), 
							SiloOrigemSelecionado.getCidade(), 
							SiloOrigemSelecionado.getSiglaUF());
					Origem = Origem + ",+Brasil";
					
					String Destino = GoogleDirectionsFactory.PreparaURL(
							SiloDestinoSelecionado.getNome(), 
							SiloDestinoSelecionado.getRua(), 
							SiloDestinoSelecionado.getBairro(), 
							SiloDestinoSelecionado.getCidade(), 
							SiloDestinoSelecionado.getSiglaUF());
					
					String URLMaps = GoogleDirectionsFactory.GenerateURLMapsDir(
							Origem, 
							Destino, 
							SiloOrigemSelecionado.getLatitude(), 
							SiloOrigemSelecionado.getLongitude(),
							SiloDestinoSelecionado.getLatitude(),
							SiloDestinoSelecionado.getLongitude());					
					
					
					Integer CodigoMotorista = getCodigoMotorista();
					Integer CodigoVeiculo = getCodigoVeiculo();
					
					String data = txtDataSaida.getText() + " " + txtHoraSaida.getText();						
					
					DataHoraSaida = itinerarioRgn.getTimestamp(data);
					data = txtDataChegada.getText() + " " + txtHoraChegada.getText();
					
					DataHoraChegada = itinerarioRgn.getTimestamp(data);
				
					//Itinerário
					itinerario.setCodigo(0);
					itinerario.setMotorista(CodigoMotorista);
					itinerario.setVeiculo(CodigoVeiculo);
					itinerario.setDataSaida(DataHoraSaida);
					itinerario.setDataChegada(DataHoraChegada);
					itinerario.setQuantidadeSilosVisitar(2);
					itinerario.setDistanciaCalculada(txtKmpercurso.getText());
					itinerario.setTempoPercursoCalculado(txtTempoestimado.getText());
					itinerario.setSituacao("Aguardando saída.");
					itinerario.setURLRotaMaps(URLMaps);
					
					//Silos Itinerario
					for (int i = 0; i < 2; i++) {
						ItinerarioSilos itinerarioSilo = new ItinerarioSilos();
						
						if (i == 0){
							itinerarioSilo.setCodigoSilo(SiloOrigemSelecionado.getCodigo());
							itinerarioSilo.setDataSaida(DataHoraSaida);
							itinerarioSilo.setTipoSilo("Origem");
						} else {
							itinerarioSilo.setCodigoSilo(SiloDestinoSelecionado.getCodigo());
							itinerarioSilo.setDataChegada(DataHoraChegada);
							itinerarioSilo.setTipoSilo("Destino");
						}
						
						itinerarioSilo.setCodigoItinerario(0);
						itinerarioSilo.setCodigo(0);	
						itinerarioSilo.setSituacaoCarga("S");
						itinerarioSilo.setCargaEntregue(0);
						itinerarioSilo.setCarregado(0);					
						
						listaItinerarioSilos.add(itinerarioSilo);
					}
					
					itinerario.setListaItinerarioSilos(listaItinerarioSilos);
										
					if (itinerarioRgn.salvar(itinerario)) {
						JOptionPane.showMessageDialog(null, "Itinerário Salvo!");
						LimparCampos();
					} else {
						JOptionPane.showMessageDialog(null, "Falha ao salvar itinerário!");
					}
					
				}
			}
		});
		
	}
	
	private void LimparCampos(){		
		cbMotoristas.setSelectedIndex(0);
		cbVeiculo.setSelectedIndex(0);
		cbSiloDestino.setSelectedIndex(0);
		cbSiloOrigem.setSelectedIndex(0);
		
		txtDataSaida.setText("");
		txtHoraSaida.setText("");
		txtDataChegada.setText("");
		txtHoraChegada.setText("");
		txtSituacao.setText("Não Iniciado");
		
		txtCidade.setText("");
		txtCidadeDest.setText("");
		txtEstado.setText("");
		txtEstadoDest.setText("");
		txtRua.setText("");
		txtRuaDest.setText("");
		txtSiglaUF.setText("");
		txtSiglaUFDest.setText("");
		txtNumero.setText("");
		txtNumeroDest.setText("");
		txtBairro.setText("");
		txtBairroDest.setText("");
		txtNomesilo.setText("");
		txtNomeSiloDest.setText("");
		txtLatitude.setText("");
		txtLatitudeDest.setText("");
		txtLongitude.setText("");
		txtLongitudeDest.setText("");
		
		txtTempoestimado.setText("");
		txtKmpercurso.setText("");		
		
		SiloOrigemSelecionado = null;
		SiloDestinoSelecionado = null;
	}
		
	/**
	 * Pega o código do motorista do combobox dos motoristas.
	 * 
	 * @return
	 */
	private Integer getCodigoMotorista(){
		String MotoristaSelecionado = cbMotoristas.getSelectedItem().toString();
		String[] CodigoMotorista = MotoristaSelecionado.split("-");
						
		return Integer.parseInt(CodigoMotorista[0].trim());
	}
	
	/**
	 * Pega o código do veículo do combobox dos veículos.
	 * 
	 * @return
	 */
	private Integer getCodigoVeiculo(){
		String VeiculoSelecionado = cbVeiculo.getSelectedItem().toString();
		String[] CodigoVeiculo = VeiculoSelecionado.split("-");
		
		return Integer.parseInt(CodigoVeiculo[0].trim());
	}
	
	/**
	 * Popula combobox dos motoristas buscando eles no banco.
	 * 
	 */
	private void PopularComboMotoristas(){
		List<Usuario> ListaMotoristas;
		
		UsuarioDAOImpl MotoristaDAO = new UsuarioDAOImpl();
		
		ListaMotoristas = MotoristaDAO.localizarTodos();
		
		Iterator<Usuario> iterator = ListaMotoristas.iterator();
		
		cbMotoristas.removeAll();
		
		while(iterator.hasNext()){
			Usuario motorista = iterator.next();
			
			cbMotoristas.addItem(motorista.getCodigo() + " - " + motorista.getNome());
		}
	}
	
	/**
	 * Popula o combobox dos veículos procurando os no banco.
	 * 
	 */
	private void PopularComboVeiculos(){
				
		VeiculoDAOImpl VeiculoDAO = new VeiculoDAOImpl();
		
		ListaVeiculos = VeiculoDAO.localizarTodos();
		
		Iterator<Veiculo> iterator = ListaVeiculos.iterator();
		
		cbVeiculo.removeAll();
		
		while(iterator.hasNext()){
			Veiculo veiculo = iterator.next();
			
			cbVeiculo.addItem(veiculo.getCodigo() + " - " + veiculo.getNome());
		}

	}
	
	/**
	 * Popula os combobox de silo origem e destino buscando os mesmos no banco.
	 * 
	 */
	private void PopularComboSiloOrigemEDestino(){
		
		SiloDAOImpl SiloDAO = new SiloDAOImpl();
		
		ListaSilos = SiloDAO.localizarTodos();
		
		Iterator<Silo> iterator = ListaSilos.iterator();
		
		cbSiloOrigem.removeAll();
		cbSiloDestino.removeAll();
		
		while(iterator.hasNext()) {
			Silo silo = iterator.next();
			
			String caption = "";
			caption = caption + silo.getCodigo();
			if (!silo.getNome().isEmpty()) {
				caption = caption + " - " + silo.getNome();
			}
			
			caption = caption + " - " + silo.getCidade();
			
			
			cbSiloOrigem.addItem(caption);
			cbSiloDestino.addItem(caption);
		}
	}
}
