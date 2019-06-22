package br.com.bd2.lanchonete.persistencia;

import java.util.List;

import br.com.bd2.lanchonete.negocio.Ingrediente;

public interface IngredienteDAO {
	public String inserir(Ingrediente ing);
	public String atualizaEstoque(Double qtd,Integer cod);
	public String excluir(Ingrediente ing);
	public List<Ingrediente> listarTodos();
}
