package br.com.bd2.lanchonete.persistencia;

import java.util.List;

import br.com.bd2.lanchonete.negocio.Pedido;


public interface PedidoDAO {
	public String inserir(Pedido ped);
	public String salvar(Pedido ped);
	public String excluir(Pedido ped);
	public Pedido getUltimo();
	public List<Pedido> listarTodos();
	public List<Pedido> pesquisarPorCodigoFuncionario(Integer cod,Integer flag);
	public List<Pedido> pesquisarPorCpfCliente(String cpf,Integer flag);
	public List<Pedido> listarUltimosPorDia(Integer dias);
	public List<Pedido> listarPedidosPendentes();
	public List<Pedido> listarPedidosConcluídos();
}
