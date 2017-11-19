package br.edu.unoesc.edi.carcilosystem;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class FrmPrincipal extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPrincipal() {
		setTitle("Home");
		setBounds(100, 100, 1130, 672);
		getContentPane().setLayout(null);
		
		JPanel pnlLateral = new JPanel();
		pnlLateral.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLateral.setBounds(855, 11, 249, 599);
		getContentPane().add(pnlLateral);
		pnlLateral.setLayout(null);
		
		JPanel pnlDataSuperior = new JPanel();
		pnlDataSuperior.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDataSuperior.setBounds(10, 11, 229, 29);
		pnlLateral.add(pnlDataSuperior);
		pnlDataSuperior.setLayout(null);
		
		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblData.setBounds(10, 6, 46, 18);
		pnlDataSuperior.add(lblData);
		
		JLabel lblExibeData = new JLabel("##/##/####");
		lblExibeData.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblExibeData.setBounds(47, 6, 118, 18);
		pnlDataSuperior.add(lblExibeData);
		
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

	}
}
