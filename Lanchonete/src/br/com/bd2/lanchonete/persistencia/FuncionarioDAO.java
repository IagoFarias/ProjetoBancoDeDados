package br.com.bd2.lanchonete.persistencia;

import java.util.List;


import br.com.bd2.lanchonete.negocio.Funcionario;

public interface FuncionarioDAO {
	
	public String inserir(Funcionario func);
	public String alterar(Funcionario func);
	public String excluir(Funcionario func);
	public List<Funcionario> listarTodos();
	public Funcionario pesquisarPorCpf(String cpf);

}
