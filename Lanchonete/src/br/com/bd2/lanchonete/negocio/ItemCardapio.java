package br.com.bd2.lanchonete.negocio;

public class ItemCardapio {
	Integer codItem;
	String nome;
	Double valor;
	String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getCodItem() {
		return codItem;
	}
	public void setCodItem(Integer codItem) {
		this.codItem = codItem;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
