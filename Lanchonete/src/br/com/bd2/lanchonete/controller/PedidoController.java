package br.com.bd2.lanchonete.controller;

import java.util.List;

import br.com.bd2.lanchonete.negocio.Pedido;
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
	/*
	public String excluir(Pedido ped) {
		
	}
	public List<Pedido> listarTodos(){
		
	}
	public List<Pedido> pesquisarPorCodigoFuncionario(Integer cod,Integer flag){
		
	}
	public List<Pedido> pesquisarPorCpfCliente(String cpf,Integer flag){
		
	}
	public List<Pedido> listarUltimosPorDia(Integer dias){
		
	}
	public List<Pedido> listarPedidosPendentes(){
		
	}
	public List<Pedido> listarPedidosConcluídos(){
		
	}*/

}
