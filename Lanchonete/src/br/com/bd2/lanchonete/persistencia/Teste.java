package br.com.bd2.lanchonete.persistencia;

import java.sql.*;

import br.com.bd2.lanchonete.controller.ClienteController;
import br.com.bd2.lanchonete.controller.FuncionarioController;
import br.com.bd2.lanchonete.negocio.Cliente;
import br.com.bd2.lanchonete.negocio.Funcionario;

public class Teste {

	public static void main(String[] args) {
		Cliente cli = new Cliente();
		ClienteController cc = new ClienteController();
		Funcionario func = new Funcionario();
		FuncionarioController funco = new FuncionarioController();

		cli.setContato("123");
		cli.setCpf("511616");
		cli.setEndereco("dafgafg");
		cli.setNome("aaaa");
		
		func.setContato("177");
		func.setCpf("0000444");
		func.setEndereco("mn");
		func.setNome("pp");

		System.out.println(funco.inserir(func));

	}

}
