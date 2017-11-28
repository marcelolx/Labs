package br.edu.unoesc.carcilosystem.rgn.impl;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import br.edu.unoesc.carcilosystem.db.dao.impl.ItinerarioDAOImpl;
import br.edu.unoesc.carcilosystem.db.dao.impl.ItinerarioSilosDAOImpl;
import br.edu.unoesc.carcilosystem.db.dao.impl.SiloDAOImpl;
import br.edu.unoesc.carcilosystem.db.model.FilaItinerario;
import br.edu.unoesc.carcilosystem.db.model.Itinerario;
import br.edu.unoesc.carcilosystem.db.model.ItinerarioSilos;
import br.edu.unoesc.carcilosystem.db.model.Silo;
import br.edu.unoesc.carcilosystem.rgn.ItinerarioRgn;

public class ItinerarioRgnImpl implements ItinerarioRgn {
	
	private Integer qtdSilos;

	@Override
	public boolean salvar(Itinerario AItinerario) {
		boolean salvou = false;
		
		Integer codigoItinerario = 0;
		ItinerarioSilos silo = new ItinerarioSilos();
				
		ItinerarioDAOImpl ItinerarioDAO = new ItinerarioDAOImpl();
		
		codigoItinerario = ItinerarioDAO.salvar(AItinerario);
		
		salvou = (codigoItinerario > 0);
			
		if (salvou){
			ItinerarioSilosDAOImpl ItinerarioSilosDAO = new ItinerarioSilosDAOImpl();
			Iterator<ItinerarioSilos> iterator = AItinerario.getListaItinerarioSilos().iterator();
			
			while(iterator.hasNext()){
				silo = iterator.next();
				
				silo.setCodigoItinerario(codigoItinerario);
				
				ItinerarioSilosDAO.salvar(silo);
			}		
			
		}		
		
		return salvou;
	}

	@Override
	public boolean excluir(Integer ACodigo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Itinerario localizar(Integer ACodigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Itinerario localizar(Integer AItinerario, Integer ASilo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Itinerario> localizarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Itinerario> localizarPorSilo(Integer ASilo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FilaItinerario> localizarTodosADescarregar(Integer AEmpresa) {
		
		ItinerarioDAOImpl itinerarioDAO = new ItinerarioDAOImpl();
				
		return itinerarioDAO.localizarTodosADescarregar(AEmpresa);
	}

	@Override
	public List<FilaItinerario> processarTodosADescarregar(List<FilaItinerario> AListaItinerarios, Integer AEmpresa) {
		FilaItinerario filaItinerario = new FilaItinerario();
		List<FilaItinerario> listaFilaItinerario = new ArrayList<FilaItinerario>();
		List<Silo> listaSilos;
		SiloDAOImpl siloDAO = new SiloDAOImpl();
		qtdSilos = 0;
		Iterator<Silo> siloIterator;
		Silo silo;
		Iterator mapIterator;
		Set set;
		
		Map<Timestamp, FilaItinerario> mapFilaItinerarios = new TreeMap<Timestamp, FilaItinerario>();
		
		Iterator<FilaItinerario> iterator = AListaItinerarios.iterator();
		
		while(iterator.hasNext()){
			filaItinerario = iterator.next();
			
			mapFilaItinerarios.put(filaItinerario.getDataChegada(), filaItinerario);
		}
		
		listaSilos = siloDAO.localizarTodosEmpresa(AEmpresa);
				
		qtdSilos = listaSilos.size();		
		
		set = mapFilaItinerarios.entrySet();
		mapIterator = set.iterator();
		
		int posSilo = 0;
		while (mapIterator.hasNext()) {			
			Map.Entry mapEntry = (Map.Entry)mapIterator.next();
	 			
	        filaItinerario = (FilaItinerario) mapEntry.getValue();
	        
	        if (posSilo < qtdSilos) {
	        	
	        	siloIterator = listaSilos.iterator();
	    		
	    		while (siloIterator.hasNext()) {
	    			silo = siloIterator.next();
	    			
	    			if (!silo.getVinculado()) {
	    				filaItinerario.setNumeroSiloDescarregar(silo.getCodigo());
	    				silo.setVinculado(true);
	    				posSilo++;
	    				break;
	    			}
	    		} 
			}
	        
	        
	        if (posSilo == (qtdSilos)){
				posSilo = 0;
				for(Silo lSilo: listaSilos) {
					lSilo.setVinculado(false);
				}
			}
	        
	        
	        listaFilaItinerario.add(filaItinerario);
		}
		
		
		return listaFilaItinerario;
	}

	@Override
	public List<JPanel> montarJPanelItinerarioADescarregar(JPanel APanel, List<FilaItinerario> AFilaItinerario) {		
		List<JPanel> listPanels = new ArrayList<JPanel>();
		int posPanel = 5;
		int panelIndex = 0;
		int labelIndex = 0;
		
		for(FilaItinerario filaIti: AFilaItinerario) {				
			
			listPanels.add(panelIndex, new JPanel());
			listPanels.get(panelIndex).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					exibeDadosPainelCentral(APanel, filaIti, true);
				}
			});
			listPanels.get(panelIndex).setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			listPanels.get(panelIndex).setBounds(10, posPanel, 260, 55);
			                  
		    posPanel = posPanel + 59;	
			
		    listPanels.get(panelIndex).setLayout(null);
			
		    listPanels.get(panelIndex).add(new JLabel("Motorista:"), labelIndex);
		    listPanels.get(panelIndex).getComponent(labelIndex).setFont(new Font("SansSerif", Font.BOLD, 13));
		    listPanels.get(panelIndex).getComponent(labelIndex).setBounds(6, 6, 68, 18);			
		    labelIndex++;
		    
			listPanels.get(panelIndex).add(new JLabel("Hora chegada:"), labelIndex);
			listPanels.get(panelIndex).getComponent(labelIndex).setFont(new Font("SansSerif", Font.BOLD, 13));
			listPanels.get(panelIndex).getComponent(labelIndex).setBounds(6, 31, 100, 20);			
			labelIndex++;
			
			listPanels.get(panelIndex).add(new JLabel("Silo:"), labelIndex);
			listPanels.get(panelIndex).getComponent(labelIndex).setFont(new Font("SansSerif", Font.BOLD, 13));
			listPanels.get(panelIndex).getComponent(labelIndex).setBounds(201, 31, 30, 20);
			labelIndex++;
			
			listPanels.get(panelIndex).add(new JLabel(filaIti.getNomeMotorista()), labelIndex);			
			listPanels.get(panelIndex).getComponent(labelIndex).setFont(new Font("SansSerif", Font.PLAIN, 14));
			listPanels.get(panelIndex).getComponent(labelIndex).setBounds(72, 6, 178, 18);
			labelIndex++;			
			
			listPanels.get(panelIndex).add(new JLabel(new SimpleDateFormat("HH:mm:ss").format(filaIti.getDataChegada())), labelIndex);
			listPanels.get(panelIndex).getComponent(labelIndex).setFont(new Font("SansSerif", Font.PLAIN, 14));
			listPanels.get(panelIndex).getComponent(labelIndex).setBounds(101, 31, 105, 20);
			labelIndex++;
			
			listPanels.get(panelIndex).add(new JLabel(filaIti.getNumeroSiloDescarregar().toString()), labelIndex);
			listPanels.get(panelIndex).getComponent(labelIndex).setFont(new Font("SansSerif", Font.PLAIN, 14));
			listPanels.get(panelIndex).getComponent(labelIndex).setBounds(235, 30, 20, 20);
			labelIndex = 0;
			
			panelIndex++;			
		}
		
		
		return listPanels;
	}

	@Override
	public void exibeDadosPainelCentral(JPanel APanel, FilaItinerario AFilaItinerario, boolean AExibirCampos) {
				
		
		if (AFilaItinerario != null) {
			APanel.removeAll();
			
			JLabel lblMotorista = new JLabel("Motorista: " + AFilaItinerario.getNomeMotorista());
			lblMotorista.setFont(new Font("SansSerif", Font.PLAIN, 16));
			lblMotorista.setBounds(10, 10, 741, 36);
			APanel.add(lblMotorista);
			
			JLabel lblCnh = new JLabel("CNH: " + AFilaItinerario.getCnh());
			lblCnh.setFont(new Font("SansSerif", Font.PLAIN, 16));
			lblCnh.setBounds(10, 56, 741, 36);
			APanel.add(lblCnh);
			
			JLabel lblVeiculo = new JLabel("Veiculo: " + AFilaItinerario.getNomeVeiculo());
			lblVeiculo.setFont(new Font("SansSerif", Font.PLAIN, 16));
			lblVeiculo.setBounds(10, 102, 517, 36);
			APanel.add(lblVeiculo);
			
			JLabel lblPlaca = new JLabel("Placa: " + AFilaItinerario.getPlacaVeiculo());
			lblPlaca.setFont(new Font("SansSerif", Font.PLAIN, 16));
			lblPlaca.setBounds(532, 102, 214, 36);
			APanel.add(lblPlaca);
			
			JLabel lblDataSada = new JLabel("Data Sa\u00EDda: " + AFilaItinerario.getDataSaida());
			lblDataSada.setFont(new Font("SansSerif", Font.PLAIN, 16));
			lblDataSada.setBounds(10, 142, 741, 36);
			APanel.add(lblDataSada);
			
			JLabel lblDataChegada = new JLabel("Data Chegada: " + AFilaItinerario.getDataChegada());
			lblDataChegada.setFont(new Font("SansSerif", Font.PLAIN, 16));
			lblDataChegada.setBounds(10, 188, 741, 36);
			APanel.add(lblDataChegada);
						
			Browser browser = new Browser();
			BrowserView view = new BrowserView(browser);
			
			JInternalFrame internalFrame = new JInternalFrame("Rota");
			internalFrame.setBounds(10, 234, 800, 350);
			APanel.add(internalFrame);
			internalFrame.setVisible(true);
					
			internalFrame.add(view, BorderLayout.CENTER);			

			browser.loadURL(AFilaItinerario.getURLRotaMaps());
			
			APanel.updateUI();
			APanel.repaint();
			APanel.revalidate();	
		}
		
		
		APanel.setVisible(AExibirCampos);			
	}

	@Override
	public void descarregarVeiculo(List<FilaItinerario> AListaFilaItinerario) {

		int qtdSilosAuxiliar = 0;
		int[] silosDescarregados = new int[qtdSilos];
		
		for(FilaItinerario filaIti: AListaFilaItinerario) {
			for (int silo: silosDescarregados) {
				if ((silo != filaIti.getNumeroSiloDescarregar()) && (qtdSilosAuxiliar != qtdSilos)) {
					filaIti.setSituacao("Entregue");
					descarregaESalva(filaIti);
										
					silosDescarregados[qtdSilosAuxiliar] = filaIti.getNumeroSiloDescarregar();
					qtdSilosAuxiliar++;
					break;
				}
			}
			
		}
		
	}

	@Override
	public void descarregaESalva(FilaItinerario AFilaItinerario) {
		
		for(ItinerarioSilos itiSilo: AFilaItinerario.getListaItinerarioSilos()){
			if (itiSilo.getTipoSilo().equals("Destino")){
				itiSilo.setSituacaoCarga("Descarregado");
			}
		}		
		
		salvar(AFilaItinerario);
	}

	@Override
	public Timestamp getTimestamp(String ADateTime) {
		String[] dataHora = ADateTime.split(" ");
		String[] data = dataHora[0].trim().split("/");
		String[] horas = dataHora[1].trim().split(":");
		
		
		String dia = data[0];
		String mes = data[1];
		String ano = data[2];
		String hora = horas[0];
		String minuto = horas[1];
		String segundo = horas[2];
		
		String preparedDate = ano + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundo;	
		
		Timestamp formatedDate = Timestamp.valueOf(preparedDate);
	
		return formatedDate;
	}
	

}
