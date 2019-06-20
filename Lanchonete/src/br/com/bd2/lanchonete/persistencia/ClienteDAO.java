package br.com.bd2.lanchonete.persistencia;

import java.util.List;

import br.com.bd2.lanchonete.negocio.Cliente;


public interface ClienteDAO {
	public String inserir(Cliente cli);
	public String alterar(Cliente cli);
	public String excluir(Cliente cli);
	public List<Cliente> listarTodos();
	public Cliente pesquisarPorCpf(String cpf);

}
