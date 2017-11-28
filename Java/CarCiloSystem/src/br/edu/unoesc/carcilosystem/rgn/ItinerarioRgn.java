package br.edu.unoesc.carcilosystem.rgn;

import java.sql.Timestamp;
import java.util.List;

import javax.swing.JPanel;

import br.edu.unoesc.carcilosystem.db.model.FilaItinerario;
import br.edu.unoesc.carcilosystem.db.model.Itinerario;

public interface ItinerarioRgn {
	
	/**
	 * Permite salvar e atualizar o itinerário.
	 * 
	 * @param AItinerario Objeto Itinerário
	 * @return Boolean
	 */
	public boolean salvar(Itinerario AItinerario);
	
	/**
	 * Permite excluir o itinerário caso não esteja vinculado.
	 * 
	 * @param ACodigo Código do Itinerario
	 * @return Boolean
	 */
	public boolean excluir(Integer ACodigo);
	
	/**
	 * Localiza itinerário pelo código.
	 * 
	 * @param ACodigo Código do itinerário
	 * @return Itinerario 
	 */
	public Itinerario localizar(Integer ACodigo);
	
	/**
	 * Localiza itinerário pelo código do itinerário e código do silo.
	 * 
	 * @param AItinerario Código do Itinerario
	 * @param ASilo Código do Silo
	 * @return Itinerario
	 */
	public Itinerario localizar(Integer AItinerario, Integer ASilo);
	
	/**
	 * Localiza todos os itinerários.
	 * 
	 * @return Lista de Itinerários (List Tipo: Itinerario)
	 */
	public List<Itinerario> localizarTodos();
	
	/**
	 * Localiza todos os itinerários que não foram descarregados para empresa X.
	 * 
	 * @param AEmpresa Código da empresa
	 * 
	 * @return Lista com os itinerários e os silos origem/destino.
	 */
	public List<FilaItinerario> localizarTodosADescarregar(Integer AEmpresa);
	
	
	/**
	 * Recebe um lista de itinerários e faz o processo de ordenar a lista e setar em qual silo que será descarregado. 
	 * 
	 * @param AListaItinerarios Lista de itinerários
	 * @param AEmpresa Código da empresa
	 * 
	 * @return List Tipo: FilaItinerario Lista ordenada..
	 */
	public List<FilaItinerario> processarTodosADescarregar(List<FilaItinerario> AListaItinerarios, Integer AEmpresa);
	
	/** 
	 * Irá receber uma coleção ordenada e a partir dela montar panels para exibir na tela.
	 * 
	 * @param APanel Painel ao qual serão exibidas as informações detalhadas do itinerário.
	 * @param AListaFilaItinerario Lista ordenada dos itinerários
	 * 
	 * @return Lista de JPanels montados.
	 */
	public List<JPanel> montarJPanelItinerarioADescarregar(JPanel APanel, List<FilaItinerario> AListaFilaItinerario);
	
	/**
	 * Irá setar os dados do itinerário no painel principal do programa.
	 * 
	 * @param APanel / Panel ao qual serão adicionados os valores.
	 * @param AFilaItinerario / Passa objeto do tipo FilaItinerário.
	 * @param AExibirCampos / Caso não queira exibir os campos.
	 */	
	public void exibeDadosPainelCentral(JPanel APanel, FilaItinerario AFilaItinerario, boolean AExibirCampos);
	
	/**
	 * É executada de tempo em tempo para descarregar os veículos.
	 * Após recarrega o painel lateral.
	 * 
	 * @param AListaFilaItinerario Lista ordenada dos itinerários
	 */
	public void descarregarVeiculo(List<FilaItinerario> AListaFilaItinerario);
	
	/**
	 * Seta alguns valores para o itinerário e salva.
	 * 
	 * @param AFilaItinerario Objeto FilaItinerario
	 */
	public void descarregaESalva(FilaItinerario AFilaItinerario);
	
	/**
	 * Localiza todos os itenários vinculados a X silo.
	 * 
	 * @param ASilo Código do Silo
	 * @return List Itinerario 
	 */
	public List<Itinerario> localizarPorSilo(Integer ASilo);
	
	/**
	 * Formata string para timestamp.
	 * 
	 * @param ADateTime Recebe uma data no formato dd/mm/yyyy hh:mm:ss 
	 * @return Retorna um timestamp no formato yyyy-mm-dd hh:mm:ss
	 */
	public Timestamp getTimestamp(String ADateTime);
}
