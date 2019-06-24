package br.com.bd2.lanchonete.controller;

import java.util.List;

import br.com.bd2.lanchonete.negocio.ItemPedido;
import br.com.bd2.lanchonete.persistencia.ItemPedidoDAOImp;

public class ItemPedidoController {
	public String inserir(ItemPedido item) {
		ItemPedidoDAOImp dao = new ItemPedidoDAOImp();
		return dao.inserir(item);
	}
	
	public String excluir(ItemPedido item) {
		ItemPedidoDAOImp dao = new ItemPedidoDAOImp();
		return dao.excluir(item);
	}
	
	public List<ItemPedido> listarItensPedido(Integer codPedido){
		ItemPedidoDAOImp dao = new ItemPedidoDAOImp();
		return dao.listarItensPedido(codPedido);
	}

}
