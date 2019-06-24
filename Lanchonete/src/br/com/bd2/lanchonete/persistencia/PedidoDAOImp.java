package br.com.bd2.lanchonete.persistencia;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import br.com.bd2.lanchonete.negocio.Cliente;
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
				return "Pedido salvo com sucesso!";
			} else {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> pesquisarPorCodigoFuncionario(Integer cod, Integer flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> pesquisarPorCpfCliente(String cpf, Integer flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> listarUltimosPorDia(Integer dias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> listarPedidosPendentes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> listarPedidosConcluídos() {
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

}
