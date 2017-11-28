package br.edu.unoesc.carcilosystem.rgn;

import java.sql.Timestamp;
import java.util.List;

import javax.swing.JPanel;

import br.edu.unoesc.carcilosystem.db.model.FilaItinerario;
import br.edu.unoesc.carcilosystem.db.model.Itinerario;

public interface ItinerarioRgn {
	
	/**
	 * Permite salvar e atualizar o itiner�rio.
	 * 
	 * @param AItinerario Objeto Itiner�rio
	 * @return Boolean
	 */
	public boolean salvar(Itinerario AItinerario);
	
	/**
	 * Permite excluir o itiner�rio caso n�o esteja vinculado.
	 * 
	 * @param ACodigo C�digo do Itinerario
	 * @return Boolean
	 */
	public boolean excluir(Integer ACodigo);
	
	/**
	 * Localiza itiner�rio pelo c�digo.
	 * 
	 * @param ACodigo C�digo do itiner�rio
	 * @return Itinerario 
	 */
	public Itinerario localizar(Integer ACodigo);
	
	/**
	 * Localiza itiner�rio pelo c�digo do itiner�rio e c�digo do silo.
	 * 
	 * @param AItinerario C�digo do Itinerario
	 * @param ASilo C�digo do Silo
	 * @return Itinerario
	 */
	public Itinerario localizar(Integer AItinerario, Integer ASilo);
	
	/**
	 * Localiza todos os itiner�rios.
	 * 
	 * @return Lista de Itiner�rios (List Tipo: Itinerario)
	 */
	public List<Itinerario> localizarTodos();
	
	/**
	 * Localiza todos os itiner�rios que n�o foram descarregados para empresa X.
	 * 
	 * @param AEmpresa C�digo da empresa
	 * 
	 * @return Lista com os itiner�rios e os silos origem/destino.
	 */
	public List<FilaItinerario> localizarTodosADescarregar(Integer AEmpresa);
	
	
	/**
	 * Recebe um lista de itiner�rios e faz o processo de ordenar a lista e setar em qual silo que ser� descarregado. 
	 * 
	 * @param AListaItinerarios Lista de itiner�rios
	 * @param AEmpresa C�digo da empresa
	 * 
	 * @return List Tipo: FilaItinerario Lista ordenada..
	 */
	public List<FilaItinerario> processarTodosADescarregar(List<FilaItinerario> AListaItinerarios, Integer AEmpresa);
	
	/** 
	 * Ir� receber uma cole��o ordenada e a partir dela montar panels para exibir na tela.
	 * 
	 * @param APanel Painel ao qual ser�o exibidas as informa��es detalhadas do itiner�rio.
	 * @param AListaFilaItinerario Lista ordenada dos itiner�rios
	 * 
	 * @return Lista de JPanels montados.
	 */
	public List<JPanel> montarJPanelItinerarioADescarregar(JPanel APanel, List<FilaItinerario> AListaFilaItinerario);
	
	/**
	 * Ir� setar os dados do itiner�rio no painel principal do programa.
	 * 
	 * @param APanel / Panel ao qual ser�o adicionados os valores.
	 * @param AFilaItinerario / Passa objeto do tipo FilaItiner�rio.
	 * @param AExibirCampos / Caso n�o queira exibir os campos.
	 */	
	public void exibeDadosPainelCentral(JPanel APanel, FilaItinerario AFilaItinerario, boolean AExibirCampos);
	
	/**
	 * � executada de tempo em tempo para descarregar os ve�culos.
	 * Ap�s recarrega o painel lateral.
	 * 
	 * @param AListaFilaItinerario Lista ordenada dos itiner�rios
	 */
	public void descarregarVeiculo(List<FilaItinerario> AListaFilaItinerario);
	
	/**
	 * Seta alguns valores para o itiner�rio e salva.
	 * 
	 * @param AFilaItinerario Objeto FilaItinerario
	 */
	public void descarregaESalva(FilaItinerario AFilaItinerario);
	
	/**
	 * Localiza todos os iten�rios vinculados a X silo.
	 * 
	 * @param ASilo C�digo do Silo
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
