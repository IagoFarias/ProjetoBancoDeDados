package br.com.bd2.lanchonete.controller;

import java.util.List;

import br.com.bd2.lanchonete.negocio.Ingrediente;
import br.com.bd2.lanchonete.persistencia.IngredienteDAOImp;

public class IngredienteController {
	public String inserir(Ingrediente ing) {
		IngredienteDAOImp dao = new IngredienteDAOImp();
		return dao.inserir(ing);
	}
	
	public String atualizaEstoque(Double qtd,Integer cod) {
		IngredienteDAOImp dao = new IngredienteDAOImp();
		return dao.atualizaEstoque(qtd,cod);
	}
	public String excluir(Ingrediente ing) {
		IngredienteDAOImp dao = new IngredienteDAOImp();
		return dao.excluir(ing);
	}
	
	public List<Ingrediente> listarTodos(){
		IngredienteDAOImp dao = new IngredienteDAOImp();
		return dao.listarTodos();
	}

}
