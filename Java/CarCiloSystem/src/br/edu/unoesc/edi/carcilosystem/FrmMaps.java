package br.edu.unoesc.edi.carcilosystem;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

public class FrmMaps {

	public JFrame frame;
	private String[] LatitudeLongitude = null;	
	
	
	public FrmMaps(String AURLPreparada, String AFormPai) {
		initialize(AURLPreparada, AFormPai);		
	}
	
	
	private void initialize(String AURLPreparada, String AFormPai) {		
		Browser browser = new Browser();
		BrowserView view = new BrowserView(browser);
		frame = new JFrame("CarCiloSystem");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(view, BorderLayout.CENTER);
		frame.setSize(1024, 728);
		frame.setVisible(true);
		browser.loadURL(AURLPreparada);
		
		view.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				if (AFormPai == "br.edu.unoesc.edi.carcilosystem.FrmCadastroSilo") {
					
					int PosIni = view.getBrowser().getURL().indexOf("@") + 1;
					
					String[] url = view.getBrowser().getURL().substring(PosIni).replace("z", "").trim().split(",");
					String latitude = url[0];
					String longitude = url[1];
					
					int dialogResult = JOptionPane.showConfirmDialog(null, "Deseja utilizar esta geolocalização para o silo?", "Confirmar geolocalização!", JOptionPane.YES_NO_OPTION);				
					if (dialogResult == JOptionPane.YES_OPTION){
						LatitudeLongitude = new String[2];
						
						LatitudeLongitude[0] = latitude;
						LatitudeLongitude[1] = longitude;
						
					
						FrmCadastroSilo.SetLatitudeLongitude(LatitudeLongitude);
						frame.dispose();				
					} else {
						LatitudeLongitude = null;
					}
				}
			}
		});
	}

}
