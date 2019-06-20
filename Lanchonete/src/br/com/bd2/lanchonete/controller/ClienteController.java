package br.com.bd2.lanchonete.controller;

import java.util.List;

import br.com.bd2.lanchonete.negocio.Cliente;
import br.com.bd2.lanchonete.persistencia.ClienteDAOImp;


public class ClienteController {
	
	public String inserir(Cliente cli) {
		ClienteDAOImp dao = new ClienteDAOImp();
		return dao.inserir(cli);
	}

	public String alterar(Cliente cli) {
		ClienteDAOImp dao = new ClienteDAOImp();
		return dao.alterar(cli);
	}

	public String excluir(Cliente cli) {
		ClienteDAOImp dao = new ClienteDAOImp();
		return dao.excluir(cli);
	}

	public List<Cliente> listarTodos() {
		ClienteDAOImp dao = new ClienteDAOImp();
		return dao.listarTodos();
	}

	public Cliente pesquisarPorCpf(String cpf) {
		ClienteDAOImp dao = new ClienteDAOImp();
		return dao.pesquisarPorCpf(cpf);
	}

}
