package br.edu.unoesc.carcilosystem.db.model;

public class FilaItinerario extends Itinerario {
	private String nomeMotorista;
	private String cnh;
	private String placaVeiculo;
	private String NomeVeiculo;
	private Integer numeroSiloDescarregar;
	private Boolean Descarregou;
	
	public String getNomeMotorista() {
		return nomeMotorista;
	}
	public void setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}
	public String getCnh() {
		return cnh;
	}
	public void setCnh(String cnh) {
		this.cnh = cnh;
	}
	public String getPlacaVeiculo() {
		return placaVeiculo;
	}
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}
	public String getNomeVeiculo() {
		return NomeVeiculo;
	}
	public void setNomeVeiculo(String nomeVeiculo) {
		NomeVeiculo = nomeVeiculo;
	}
	public Integer getNumeroSiloDescarregar() {
		return numeroSiloDescarregar;
	}
	public void setNumeroSiloDescarregar(Integer numeroSiloDescarregar) {
		this.numeroSiloDescarregar = numeroSiloDescarregar;
	}
	public Boolean getDescarregou() {
		return Descarregou;
	}
	public void setDescarregou(Boolean descarregou) {
		Descarregou = descarregou;
	}
}
