package br.com.bd2.lanchonete.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bd2.lanchonete.negocio.ClienteEstat;
import br.com.bd2.lanchonete.negocio.Cliente;
import br.com.bd2.lanchonete.negocio.PedidoEstat;


public class ClienteDAOImp implements ClienteDAO{

	@Override
	public String inserir(Cliente cli) {
		String sql = "insert into cliente(nome,cpf,endereco,fones) values(?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,cli.getNome());
			pst.setString(2,cli.getCpf());
			pst.setString(3,cli.getEndereco());
			pst.setString(4,cli.getContato());
			int res = pst.executeUpdate();
			if (res > 0) {
				commit();
				return "Inserido com sucesso.";
			} else {
				rollback();
				return "Erro ao inserir.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public String alterar(Cliente cli) {
		String sql = "update cliente set nome=?,endereco=?,fones=? where cpf=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cli.getNome());
			pst.setString(2,cli.getEndereco());
			pst.setString(3, cli.getContato());
			pst.setString(4, cli.getCpf());
			int res = pst.executeUpdate();
			if (res > 0) {
				commit();
				return "Alterado com sucesso.";
			} else {
				rollback();
				return "Erro ao alterar.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public String excluir(Cliente cli) {
		String sql = "delete from cliente where cpf=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cli.getCpf());
			int res = pst.executeUpdate();
			if (res > 0) {
				commit();
				return "Excluído com sucesso.";
			} else {
				rollback();
				return "Erro ao excluir.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public List<Cliente> listarTodos() {
		String sql = "select * from cliente";
		Connection con = ConnectionFactory.getConnection();
		List<Cliente> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Cliente cli = new Cliente();
					cli.setNome(rs.getString(1));
					cli.setCpf(rs.getString(2));
					cli.setEndereco(rs.getString(3));
					cli.setContato(rs.getString(4));
					
					lista.add(cli);
				}
				return lista;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public Cliente pesquisarPorCpf(String cpf) {
		String sql = "select * from cliente where cpf=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cpf);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Cliente cli = new Cliente();
				cli.setNome(rs.getString(1));
				cli.setCpf(rs.getString(2));
				cli.setEndereco(rs.getString(3));
				cli.setContato(rs.getString(4));;
				return cli;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionFactory.close(con);
		}
	}
	
	public List<ClienteEstat> pesquisarEstat() {
		String sql = "select * from Cliente_preferencial";
		Connection con = ConnectionFactory.getConnection();
		List<ClienteEstat> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ClienteEstat cliEst = new ClienteEstat();
					
					cliEst.nome = rs.getString(1);
					cliEst.cpf = rs.getString(2);
					cliEst.qtdPedidos = rs.getInt(3);
					cliEst.totalGasto = rs.getDouble(4);
					
					
					lista.add(cliEst);
				}
				return lista;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionFactory.close(con);
		}
	}
	
	public List<ClienteEstat> pesquisarEstatCpf(String cpf) {
		String sql = "select * from Cliente_preferencial where cpf_cliente = ?";
		Connection con = ConnectionFactory.getConnection();
		List<ClienteEstat> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cpf);
			
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ClienteEstat cliEst = new ClienteEstat();
					
					cliEst.nome = rs.getString(1);
					cliEst.cpf = rs.getString(2);
					cliEst.qtdPedidos = rs.getInt(3);
					cliEst.totalGasto = rs.getDouble(4);
					
					
					lista.add(cliEst);
				}
				return lista;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionFactory.close(con);
		}
	}
	
	public String rollback() {
		String sql = "call voltar()";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.executeUpdate();
			return "Alterações desfeitas";

		} catch (SQLException e) {
			return e.getMessage();
			
		} finally {
			ConnectionFactory.close(con);
		}
		
	}
	
	public void commit() {
		String sql = "call salvar()";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.executeQuery();
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionFactory.close(con);
		}
		
	}
	
	}


