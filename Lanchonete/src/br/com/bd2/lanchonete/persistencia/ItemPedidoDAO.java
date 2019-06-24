package br.com.bd2.lanchonete.persistencia;

import java.util.List;

import br.com.bd2.lanchonete.negocio.ItemPedido;

public interface ItemPedidoDAO {
	public String inserir(ItemPedido item);
	public String excluir(ItemPedido item);
	public List<ItemPedido> listarItensPedido(Integer codPedido);

}
