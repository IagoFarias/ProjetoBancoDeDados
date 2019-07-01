package br.com.bd2.lanchonete.persistencia;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import br.com.bd2.lanchonete.negocio.PedidoEstat;
import br.com.bd2.lanchonete.negocio.Pedido;


public class PedidoDAOImp implements PedidoDAO{
	
	@Override
	public String inserir(Pedido ped) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date date = new Date(); 
		String dataAtual = dateFormat.format(date);
		
		String sql = "insert into pedido(numero,cpf_cliente,cod_funcionario,pedido_status,valor,observacao,data_hora) values(?,?,?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,0);
			pst.setString(2,ped.getCpfCliente());
			pst.setInt(3, ped.getCodFuncionario());
			pst.setString(4,"Pendente");
			pst.setDouble(5, 0.0);
			pst.setString(6, "");
			pst.setObject(7,dataAtual);
			
			
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Pedido gerado com sucesso!";
			} else {
				rollback();
				return "Erro ao gerar pedido!";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public String salvar(Pedido ped) {
		String sql = "update pedido set valor=?,observacao=? where numero=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setDouble(1, ped.getValor());
			pst.setString(2, ped.getObservacao());
			pst.setInt(3,ped.getNumeroPedido());
			
			
			
			int res = pst.executeUpdate();
			if (res > 0) {
				commit();
				return "Pedido salvo com sucesso!";
			} else {
				rollback();
				return "Erro ao gerar pedido!";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public String excluir(Pedido ped) {
		String sql = "delete from pedido where numero = ?";
		Connection con = ConnectionFactory.getConnection();
		try {
			excluirItensPedido(ped.getNumeroPedido());
			
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, ped.getNumeroPedido());
			
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
	public List<Pedido> listarPedidos(String status) {
		String sql = "select * from pedido where pedido_status = ?";
		Connection con = ConnectionFactory.getConnection();
		List<Pedido> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, status);
			
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Pedido ped = new Pedido();
					
					ped.setNumeroPedido(rs.getInt(1));
					ped.setCpfCliente(rs.getString(2));
					ped.setCodFuncionario(rs.getInt(3));
					ped.setStatus(rs.getString(4));
					ped.setValor(rs.getDouble(5));
					ped.setObservacao(rs.getString(6));
					ped.setDataHora(rs.getDate(7).toString());
					
					
					lista.add(ped);
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
	public List<Pedido> pesquisarPorCodigoFuncionario(Integer cod, String status) {
		String sql = "select * from pedido where pedido_status = ? and cod_funcionario = ?";
		Connection con = ConnectionFactory.getConnection();
		List<Pedido> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, status);
			pst.setInt(2, cod);
			
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Pedido ped = new Pedido();
					
					ped.setNumeroPedido(rs.getInt(1));
					ped.setCpfCliente(rs.getString(2));
					ped.setCodFuncionario(rs.getInt(3));
					ped.setStatus(rs.getString(4));
					ped.setValor(rs.getDouble(5));
					ped.setObservacao(rs.getString(6));
					ped.setDataHora(rs.getDate(7).toString());
					
					
					lista.add(ped);
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
	public List<Pedido> pesquisarPorCpfCliente(String cpf, String status) {
		String sql = "select * from pedido where pedido_status = ? and cpf_cliente = ?";
		Connection con = ConnectionFactory.getConnection();
		List<Pedido> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, status);
			pst.setString(2, cpf);
			
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Pedido ped = new Pedido();
					
					ped.setNumeroPedido(rs.getInt(1));
					ped.setCpfCliente(rs.getString(2));
					ped.setCodFuncionario(rs.getInt(3));
					ped.setStatus(rs.getString(4));
					ped.setValor(rs.getDouble(5));
					ped.setObservacao(rs.getString(6));
					ped.setDataHora(rs.getDate(7).toString());
					
					
					lista.add(ped);
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
	public List<Pedido> listarUltimosPorDia(Integer dias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido getUltimo() {		
		String sql = "select max(numero),max(data_hora) from pedido";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Pedido ped = new Pedido();
				ped.setNumeroPedido(rs.getInt(1));
				return ped;
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

	@Override
	public String executarPedido(Pedido ped) {
		String sql = "update pedido set pedido_status=? where numero=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
		    pst.setString(1,ped.getStatus());
		    pst.setInt(2, ped.getNumeroPedido());
			
			
			int res = pst.executeUpdate();
			if (res > 0) {
				commit();
				return "Operação Realizada";
			} else {
				rollback();
				return "Erro!";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	
	}

	@Override
	public String finalizarPedido(Pedido ped) {
		String sql = "update pedido set pedido_status=? where numero=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
		    pst.setString(1,ped.getStatus());
		    pst.setInt(2, ped.getNumeroPedido());
			
			
			int res = pst.executeUpdate();
			if (res > 0) {
				commit();
				return "Operação Realizada";
			} else {
				rollback();
				return "Erro!";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}
	
	public String visualizaObservacao(Integer numeroPedido) {
		String sql = "select observacao from pedido where numero = ?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, numeroPedido);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				
				return rs.getString(1);
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionFactory.close(con);
		}
		
	}
	
	public String visualizaItensPedido(Integer numeroPedido) {
		String sql = "select numero_pedido,cod_item_cardapio,nome,quantidade from itens_pedido,itens_cardapio where numero_pedido=? and cod_item_cardapio = codigo";
		Connection con = ConnectionFactory.getConnection();
		String itensPedido=" ";
		
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, numeroPedido);
			ResultSet rs = pst.executeQuery();

			if(rs!=null) {
			while (rs.next()) {
			itensPedido	= itensPedido+"Código: "+rs.getInt(2)+" Item: "+rs.getString(3)+" Qtd: "+rs.getInt(4) +"\n";
			} 
			return itensPedido;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionFactory.close(con);
		}
		
	}
	
	public List<PedidoEstat> pesquisaEstat() {
		String sql = "select * from Lucro_por_mes";
		Connection con = ConnectionFactory.getConnection();
		List<PedidoEstat> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
		
			
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					PedidoEstat pedEst = new PedidoEstat();
					
					pedEst.totalMes = rs.getDouble(1);
					pedEst.qtdPedidos = rs.getInt(2);	
					pedEst.mes = rs.getInt(3);
					pedEst.ano = rs.getInt(4);
					
					
					lista.add(pedEst);
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
	
	public List<PedidoEstat> pesquisaEstatAnoMes(Integer ano,Integer mes) {
		String sql = "select * from Lucro_por_mes where mes = ? and ano = ?";
		Connection con = ConnectionFactory.getConnection();
		List<PedidoEstat> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, mes);
			pst.setInt(2, ano);
			
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					PedidoEstat pedEst = new PedidoEstat();
					
					pedEst.totalMes = rs.getDouble(1);
					pedEst.qtdPedidos = rs.getInt(2);	
					pedEst.mes = rs.getInt(3);
					pedEst.ano = rs.getInt(4);
					
					
					lista.add(pedEst);
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
	
	public String excluirItensPedido(Integer codPedido) {
		String sql = "delete from itens_pedido where numero_pedido = ?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, codPedido);
			
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

}
