package br.edu.unoesc.carcilosystem.db.model;

public class Silo {
	private Integer Codigo;
	private Integer Empresa;
	private String Cidade;
	private String Rua;
	private String Bairro;
	private String Numero;
	private String Estado;
	private String SiglaUF;
	private String Latitude;
	private String Longitude;
	private String Nome;
	private Boolean Vinculado = false;
	
	
	public Integer getCodigo() {
		return Codigo;
	}
	public void setCodigo(Integer codigo) {
		Codigo = codigo;
	}
	public Integer getEmpresa() {
		return Empresa;
	}
	public void setEmpresa(Integer empresa) {
		Empresa = empresa;
	}
	public String getCidade() {
		return Cidade;
	}
	public void setCidade(String cidade) {
		Cidade = cidade;
	}
	public String getRua() {
		return Rua;
	}
	public void setRua(String rua) {
		Rua = rua;
	}
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	public String getNumero() {
		return Numero;
	}
	public void setNumero(String numero) {
		Numero = numero;
	}
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getSiglaUF() {
		return SiglaUF;
	}
	public void setSiglaUF(String siglaUF) {
		SiglaUF = siglaUF;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public Boolean getVinculado() {
		return Vinculado;
	}
	public void setVinculado(Boolean vinculado) {
		Vinculado = vinculado;
	}
	
}
