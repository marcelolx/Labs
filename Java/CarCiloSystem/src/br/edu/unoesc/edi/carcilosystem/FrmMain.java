package br.edu.unoesc.edi.carcilosystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.WindowConstants;


public class FrmMain {
	
	public JFrame frame;
	public JInternalFrame Home;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrmMain(Integer IdUsuario) {
		initialize(IdUsuario);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Integer IdUsuario) {
		
		
		Home = new FrmPrincipal();
		Home.setVisible(true);
		
	    frame = new JFrame("CarCiloSystem");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		//frame.add(view, BorderLayout.CENTER);
		frame.add(Home, BorderLayout.CENTER);
		frame.setSize(1132, 700);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);		
		frame.setVisible(true);
		
		/*browser.loadURL("http://maps.google.com");
		
		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				int PosIni = view.getBrowser().getURL().indexOf("@") + 1;
				String[] url = view.getBrowser().getURL().substring(PosIni).replace("z", "").trim().split(",");
				String latitude = url[0];
				String longitude = url[1];
				
				FrmEvento frmevento = new FrmEvento(IdUsuario, latitude, longitude);
				frmevento.setLocationRelativeTo(null);
				frmevento.setVisible(true);			
			}
		});*/
	}

}