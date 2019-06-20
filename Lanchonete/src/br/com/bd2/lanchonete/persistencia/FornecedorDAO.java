package br.com.bd2.lanchonete.persistencia;

import java.util.List;

import br.com.bd2.lanchonete.negocio.Fornecedor;


public interface FornecedorDAO {
	public String inserir(Fornecedor forn);
	public String alterar(Fornecedor forn);
	public String excluir(Fornecedor forn);
	public List<Fornecedor> listarTodos();
	public Fornecedor pesquisarPorCnpj(String cnpj);

}
