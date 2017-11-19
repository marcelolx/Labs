package br.edu.unoesc.carcilosystem.db.model;

public class Veiculo {
	private Integer Codigo;
	private String Nome;
	private String Marca;
	private Integer Ano;
	private String Modelo;
	private String TipoVeiculo;
	private String Placa;
	
	
	public Integer getCodigo() {
		return Codigo;
	}
	public void setCodigo(Integer codigo) {
		Codigo = codigo;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public Integer getAno() {
		return Ano;
	}
	public void setAno(Integer ano) {
		Ano = ano;
	}
	public String getModelo() {
		return Modelo;
	}
	public void setModelo(String modelo) {
		Modelo = modelo;
	}
	public String getTipoVeiculo() {
		return TipoVeiculo;
	}
	public void setTipoVeiculo(String tipoVeiculo) {
		TipoVeiculo = tipoVeiculo;
	}
	public String getPlaca() {
		return Placa;
	}
	public void setPlaca(String placa) {
		Placa = placa;
	}
}
