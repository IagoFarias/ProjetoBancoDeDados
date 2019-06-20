package br.com.bd2.lanchonete.controller;

import java.util.List;

import br.com.bd2.lanchonete.negocio.Funcionario;
import br.com.bd2.lanchonete.persistencia.FuncionarioDAOImp;

public class FuncionarioController {

	public String inserir(Funcionario func) {
		FuncionarioDAOImp dao = new FuncionarioDAOImp();
		return dao.inserir(func);
	}

	public String alterar(Funcionario func) {
		FuncionarioDAOImp dao = new FuncionarioDAOImp();
		return dao.alterar(func);
	}

	public String excluir(Funcionario func) {
		FuncionarioDAOImp dao = new FuncionarioDAOImp();
		return dao.excluir(func);
	}

	public List<Funcionario> listarTodos() {
		FuncionarioDAOImp dao = new FuncionarioDAOImp();
		return dao.listarTodos();
	}

	public Funcionario pesquisarPorCpf(String cpf) {
		FuncionarioDAOImp dao = new FuncionarioDAOImp();
		return dao.pesquisarPorCpf(cpf);
	}

}
