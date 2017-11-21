package br.edu.unoesc.carcilosystem.db.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ItinerarioSilos {
	private Integer Codigo;
	private Integer CodigoSilo;
	private Integer CodigoItinerario;
	private String TipoSilo;
	private Timestamp DataSaida;
	private Timestamp DataChegada;
	private Integer Carregado;
	private Integer CargaEntregue;
	private String SituacaoCarga;
	
	public Integer getCodigo() {
		return Codigo;
	}
	public void setCodigo(Integer codigo) {
		Codigo = codigo;
	}
	public Integer getCodigoSilo() {
		return CodigoSilo;
	}
	public void setCodigoSilo(Integer codigoSilo) {
		CodigoSilo = codigoSilo;
	}
	public Integer getCodigoItinerario() {
		return CodigoItinerario;
	}
	public void setCodigoItinerario(Integer codigoItinerario) {
		CodigoItinerario = codigoItinerario;
	}
	public String getTipoSilo() {
		return TipoSilo;
	}
	public void setTipoSilo(String tipoSilo) {
		TipoSilo = tipoSilo;
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
	public Integer getCarregado() {
		return Carregado;
	}
	public void setCarregado(Integer carregado) {
		Carregado = carregado;
	}
	public Integer getCargaEntregue() {
		return CargaEntregue;
	}
	public void setCargaEntregue(Integer cargaEntregue) {
		CargaEntregue = cargaEntregue;
	}
	public String getSituacaoCarga() {
		return SituacaoCarga;
	}
	public void setSituacaoCarga(String situacaoCarga) {
		SituacaoCarga = situacaoCarga;
	}
	
}
