package br.edu.unoesc.edi.carcilosystem;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import br.edu.unoesc.carcilosystem.db.model.FilaItinerario;
import br.edu.unoesc.carcilosystem.rgn.impl.ItinerarioRgnImpl;

public class FrmPrincipal extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2537636671992172863L;
	private ItinerarioRgnImpl iti = new ItinerarioRgnImpl();
	private List<FilaItinerario> listIti;
	private JPanel pnlCentral;
	private JPanel pnlLateral;
	
	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setTitle("Home");
		setBounds(100, 100, 1130, 672);
		getContentPane().setLayout(null);

		pnlLateral = new JPanel();
		pnlLateral.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLateral.setBounds(824, 48, 280, 562);
		getContentPane().add(pnlLateral);
		pnlLateral.setLayout(null);
		
		pnlCentral = new JPanel();
		pnlCentral.setBounds(10, 11, 807, 599);
		pnlCentral.setLayout(null);
		getContentPane().add(pnlCentral);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(824, 11, 280, 29);
		getContentPane().add(panel);
		
		JLabel label = new JLabel("Data:");
		label.setFont(new Font("SansSerif", Font.PLAIN, 14));
		label.setBounds(10, 6, 46, 18);
		panel.add(label);
		
		Date date = new Date();
		JLabel lblDataAtual = new JLabel(new SimpleDateFormat("dd/MM/yyyy").format(date.getTime()));
		lblDataAtual.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblDataAtual.setBounds(47, 6, 118, 18);
		panel.add(lblDataAtual);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);
		
		JMenuItem mntmMotorista = new JMenuItem("Motorista");
		mntmMotorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmCadastro cadastro = new FrmCadastro(false, "Cadastro de Motorista");
				cadastro.setLocationRelativeTo(null);
				cadastro.setVisible(true);
				dispose();
				setVisible(true);
			}
		});
		mnCadastro.add(mntmMotorista);
		
		JMenuItem mntmVeculo = new JMenuItem("Veículo");
		mntmVeculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmCadastrarVeiculo cadVeiculo = new FrmCadastrarVeiculo();
				cadVeiculo.setLocationRelativeTo(null);
				cadVeiculo.setVisible(true);
				dispose();
				setVisible(true);
			}
		});
		mnCadastro.add(mntmVeculo);
		
		JMenuItem mntmItinerrio = new JMenuItem("Itiner\u00E1rio");
		mntmItinerrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmItinerario Itinerario = new FrmItinerario();
				Itinerario.setLocationRelativeTo(null);
				Itinerario.setVisible(true);
				dispose();
				setVisible(true);
			}
		});
		mnCadastro.add(mntmItinerrio);
		
		JMenuItem mntmSilo = new JMenuItem("Silo");
		mntmSilo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmCadastroSilo cadSilo = new FrmCadastroSilo();
				cadSilo.setLocationRelativeTo(null);
				cadSilo.setVisible(true);
				dispose();
				setVisible(true);
			}
		});
		mnCadastro.add(mntmSilo);

		carregaItinerariosDescarregar();		
		/**
		 * Cria timer que fica executando a cada 5 minutos para descarregar um veículo
		 */
		TimerTask repeatedTask = new TimerTask() {
	        public void run() {
	        	iti.descarregarVeiculo(listIti);	        	
	            carregaItinerariosDescarregar();
	            
	            if (listIti.size() == 0){
	    			pnlCentral.removeAll();	
	    			pnlCentral.updateUI();
	    			pnlCentral.repaint();
	    			pnlCentral.revalidate();	    			
	    		}
	        }
	    };   
	
	    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	    long delay  = 12000L; //Delay de dois minuto para executar pela primeira vez
	    long period = 300000L; //executa num intervalo de a cada 5 minutos
	    executor.scheduleAtFixedRate(repeatedTask, delay, period, TimeUnit.MILLISECONDS);	
	}
	
	/**
	 * Localiza os itinerários a serem descarregados no dia corrente.
	 * 
	 * Ordena os mesmos e adiciona no panel lateral.
	 */
	public void carregaItinerariosDescarregar(){		
		pnlLateral.removeAll();
		iti = new ItinerarioRgnImpl();		
		List<JPanel> pnlItinerarioDescarregar;
						
		listIti = iti.processarTodosADescarregar(iti.localizarTodosADescarregar(0), 0);
		
		pnlItinerarioDescarregar = iti.montarJPanelItinerarioADescarregar(pnlCentral, listIti);
		
		
		for(JPanel panel: pnlItinerarioDescarregar) {
			pnlLateral.add(panel);				
		}			
		
	
		pnlLateral.updateUI();
		pnlLateral.repaint();
		pnlLateral.revalidate();
		
	}
}
