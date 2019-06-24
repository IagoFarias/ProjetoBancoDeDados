package br.com.bd2.lanchonete.negocio;

public class ItemPedido {
	private Integer codPedido;
	private Integer codItemCardapio;
	private String nomeItem;
	private Integer quantidade;
	
	public Integer getCodPedido() {
		return codPedido;
	}
	public void setCodPedido(Integer codPedido) {
		this.codPedido = codPedido;
	}
	public Integer getCodItemCardapio() {
		return codItemCardapio;
	}
	public void setCodItemCardapio(Integer codItemCardapio) {
		this.codItemCardapio = codItemCardapio;
	}
	public String getNomeItem() {
		return nomeItem;
	}
	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
