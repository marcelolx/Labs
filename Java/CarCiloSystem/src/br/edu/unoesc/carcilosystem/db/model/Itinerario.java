package br.edu.unoesc.carcilosystem.db.model;

import java.sql.Timestamp;
import java.util.List;

public class Itinerario {
	private Integer Codigo;
	private Integer Motorista;
	private Integer Veiculo;
	private Integer QuantidadeSilosVisitar;
	private Timestamp DataSaida;
	private Timestamp DataChegada;
	private String Situacao;
	private String DistanciaCalculada;
	private String TempoPercursoCalculado;
	private String URLRotaMaps;
	private List<ItinerarioSilos> ListaItinerarioSilos;
	
	public Integer getCodigo() {
		return Codigo;
	}
	public void setCodigo(Integer codigo) {
		Codigo = codigo;
	}
	public Integer getMotorista() {
		return Motorista;
	}
	public void setMotorista(Integer motorista) {
		Motorista = motorista;
	}
	public Integer getVeiculo() {
		return Veiculo;
	}
	public void setVeiculo(Integer veiculo) {
		Veiculo = veiculo;
	}
	public Integer getQuantidadeSilosVisitar() {
		return QuantidadeSilosVisitar;
	}
	public void setQuantidadeSilosVisitar(Integer quantidadeSilosVisitar) {
		QuantidadeSilosVisitar = quantidadeSilosVisitar;
	}
	public Timestamp getDataSaida() {
		return DataSaida;
	}
	public void setDataSaida(Timestamp dataSaida) {
		DataSaida = dataSaida;
	}
	public Timestamp getDataChegada() {
		return DataChegada;
	}
	public void setDataChegada(Timestamp dataChegada) {
		DataChegada = dataChegada;
	}
	public String getSituacao() {
		return Situacao;
	}
	public void setSituacao(String situacao) {
		Situacao = situacao;
	}
	public String getDistanciaCalculada() {
		return DistanciaCalculada;
	}
	public void setDistanciaCalculada(String distanciaCalculada) {
		DistanciaCalculada = distanciaCalculada;
	}
	public String getTempoPercursoCalculado() {
		return TempoPercursoCalculado;
	}
	public void setTempoPercursoCalculado(String tempoPercursoCalculado) {
		TempoPercursoCalculado = tempoPercursoCalculado;
	}
	public String getURLRotaMaps() {
		return URLRotaMaps;
	}
	public void setURLRotaMaps(String uRLRotaMaps) {
		URLRotaMaps = uRLRotaMaps;
	}
	public List<ItinerarioSilos> getListaItinerarioSilos() {
		return ListaItinerarioSilos;
	}
	public void setListaItinerarioSilos(List<ItinerarioSilos> listaItinerarioSilos) {
		ListaItinerarioSilos = listaItinerarioSilos;
	}
	public void addItemListaItinerarioSilos(ItinerarioSilos AItinerarioSilo){
		ListaItinerarioSilos.add(AItinerarioSilo);		
	}
	public void removeItemListaItinerarioSilos(ItinerarioSilos AItinerarioSilo){
		ListaItinerarioSilos.remove(AItinerarioSilo);
	}
}
