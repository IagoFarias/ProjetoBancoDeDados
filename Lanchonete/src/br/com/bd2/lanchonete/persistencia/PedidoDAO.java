package br.com.bd2.lanchonete.persistencia;

import java.util.List;

import br.com.bd2.lanchonete.negocio.Pedido;


public interface PedidoDAO {
	public String inserir(Pedido ped);
	public String salvar(Pedido ped);
	public String excluir(Pedido ped);
	public String executarPedido(Pedido ped);
	public String finalizarPedido(Pedido ped);
	public Pedido getUltimo();
	public List<Pedido> listarPedidos(String status);
	public List<Pedido> pesquisarPorCodigoFuncionario(Integer cod,String status);
	public List<Pedido> pesquisarPorCpfCliente(String cpf,String status);
	public List<Pedido> listarUltimosPorDia(Integer dias);
}
