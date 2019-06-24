package br.com.bd2.lanchonete.persistencia;

import java.util.List;

import br.com.bd2.lanchonete.negocio.ItemCardapio;

public interface ItensCardapioDAO {
	public List<ItemCardapio> listarTodos();
	public String inserir(ItemCardapio itemC);
	public String excluir(ItemCardapio itemC);
	public String alterar(ItemCardapio itemC);	
}
