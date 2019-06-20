package br.com.bd2.lanchonete.negocio;

public class Fornecedor {
	String cpnj;
	String nome;
	String endereco;
	String contato;
	
	public String getCpnj() {
		return cpnj;
	}
	public void setCpnj(String cpnj) {
		this.cpnj = cpnj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String fones) {
		this.contato = fones;
	}

}
