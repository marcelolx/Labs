package br.edu.unoesc.carcilosystem.db.model;

import java.sql.Date;

public class Usuario {
	private Integer Codigo;
	private Integer Empresa;
	private String Login;
	private String Senha;
	private String Nome;
	private Date DataNascimento;
	private String Rg;
	private String Cpf;
	private Long NumeroRegCNH;
	private String CategoriaHabilitacao;
	
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
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getSenha() {
		return Senha;
	}
	public void setSenha(String senha) {
		Senha = senha;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public Date getDataNascimento() {
		return DataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		DataNascimento = dataNascimento;
	}
	public String getRg() {
		return Rg;
	}
	public void setRg(String rg) {
		Rg = rg;
	}
	public String getCpf() {
		return Cpf;
	}
	public void setCpf(String cpf) {
		Cpf = cpf;
	}
	public Long getNumeroRegCNH() {
		return NumeroRegCNH;
	}
	public void setNumeroRegCNH(Long numeroRegCNH) {
		NumeroRegCNH = numeroRegCNH;
	}
	public String getCategoriaHabilitacao() {
		return CategoriaHabilitacao;
	}
	public void setCategoriaHabilitacao(String categoriaHabilitacao) {
		CategoriaHabilitacao = categoriaHabilitacao;
	}
		
}
