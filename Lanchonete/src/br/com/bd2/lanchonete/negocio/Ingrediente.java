package br.com.bd2.lanchonete.negocio;

public class Ingrediente {
	Integer codIngrediente;
	String descricao;
	String nome;
	Double quantidade;
	
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getCodIngrediente() {
		return codIngrediente;
	}
	public void setCodIngrediente(Integer codIngrediente) {
		this.codIngrediente = codIngrediente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
