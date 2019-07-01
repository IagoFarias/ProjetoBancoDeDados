package br.com.bd2.lanchonete.controller;

import java.util.List;

import br.com.bd2.lanchonete.negocio.Pedido;
import br.com.bd2.lanchonete.negocio.PedidoEstat;
import br.com.bd2.lanchonete.persistencia.PedidoDAOImp;

public class PedidoController {

	public String inserir(Pedido ped) {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.inserir(ped);
	}

	public Pedido getUltimo() {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.getUltimo();
	}

	public String salvar(Pedido ped) {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.salvar(ped);
	}

	public String cancelar() {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.rollback();
	}

	public List<Pedido> listarPedidos(String status) {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.listarPedidos(status);
	}

	public List<Pedido> pesquisarPorCpfCliente(String cpf, String status) {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.pesquisarPorCpfCliente(cpf, status);
	}

	public String executarPedido(Pedido ped) {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.executarPedido(ped);
	}

	public String excluir(Pedido ped) {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.excluir(ped);
	}

	public String finalizarPedido(Pedido ped) {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.finalizarPedido(ped);
	}

	public List<Pedido> pesquisarPorCodigoFuncionario(Integer cod, String status) {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.pesquisarPorCodigoFuncionario(cod, status);
	}
	
	public String visualizaObservacao(Integer numeroPedido) {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.visualizaObservacao(numeroPedido);
	}
	
	public String visualizaItensPedido(Integer numeroPedido) {
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.visualizaItensPedido(numeroPedido);
	}
	
	public List<PedidoEstat> pesquisaEstat(){
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.pesquisaEstat();
	}
	
	public List<PedidoEstat> pesquisaEstatAnoMes(Integer ano,Integer mes){
		PedidoDAOImp dao = new PedidoDAOImp();
		return dao.pesquisaEstatAnoMes(ano, mes);
	}

}
