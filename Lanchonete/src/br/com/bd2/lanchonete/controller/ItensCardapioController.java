package br.com.bd2.lanchonete.controller;

import java.util.List;

import br.com.bd2.lanchonete.negocio.ItemCardapio;
import br.com.bd2.lanchonete.persistencia.ItensCardapioDAOImp;

public class ItensCardapioController {
	public List<ItemCardapio> listarTodos() {
		ItensCardapioDAOImp dao = new ItensCardapioDAOImp();
		return dao.listarTodos();

	}

	public String inserir(ItemCardapio itemC) {
		ItensCardapioDAOImp dao = new ItensCardapioDAOImp();
		return dao.inserir(itemC);
	}

	public String excluir(ItemCardapio itemC) {
		ItensCardapioDAOImp dao = new ItensCardapioDAOImp();
		return dao.excluir(itemC);
	}

	public String alterar(ItemCardapio itemC) {
		ItensCardapioDAOImp dao = new ItensCardapioDAOImp();
		return dao.alterar(itemC);
	}

}
