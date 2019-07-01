package br.com.bd2.lanchonete.controller;

import java.util.List;

import br.com.bd2.lanchonete.negocio.Fornecedor;
import br.com.bd2.lanchonete.negocio.FornecedorEstat;
import br.com.bd2.lanchonete.persistencia.FornecedorDAOImp;

public class FornecedorController {

	public String inserir(Fornecedor forn) {
		FornecedorDAOImp dao = new FornecedorDAOImp();
		return dao.inserir(forn);
	}

	public String alterar(Fornecedor forn) {
		FornecedorDAOImp dao = new FornecedorDAOImp();
		return dao.alterar(forn);
	}

	public String excluir(Fornecedor forn) {
		FornecedorDAOImp dao = new FornecedorDAOImp();
		return dao.excluir(forn);
	}

	public List<Fornecedor> listarTodos() {
		FornecedorDAOImp dao = new FornecedorDAOImp();
		return dao.listarTodos();
	}

	public Fornecedor pesquisarPorCnpj(String cnpj) {
		FornecedorDAOImp dao = new FornecedorDAOImp();
		return dao.pesquisarPorCnpj(cnpj);
	}
	
	public List<FornecedorEstat> pesquisarEstat() {
		FornecedorDAOImp dao = new FornecedorDAOImp();
		return dao.pesquisarEstat();
	}
	
	public List<FornecedorEstat> pesquisarEstatNomeIng(String nomeIng) {
		FornecedorDAOImp dao = new FornecedorDAOImp();
		return dao.pesquisarEstatNomeIng(nomeIng);	
	}
	
	public List<FornecedorEstat> pesquisarEstatNomeForn(String nomeForn) {
		FornecedorDAOImp dao = new FornecedorDAOImp();
		return dao.pesquisarEstatNomeForn(nomeForn);	
	}
	
}
