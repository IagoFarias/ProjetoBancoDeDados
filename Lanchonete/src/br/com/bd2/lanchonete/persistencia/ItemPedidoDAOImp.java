package br.com.bd2.lanchonete.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.bd2.lanchonete.negocio.ItemPedido;

public class ItemPedidoDAOImp implements ItemPedidoDAO{

	@Override
	public String inserir(ItemPedido item) {
		String sql = "insert into itens_pedido(numero_pedido,cod_item_cardapio,quantidade) values(?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,item.getCodPedido());
			pst.setInt(2, item.getCodItemCardapio());
			pst.setDouble(3,item.getQuantidade());
	
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Inserido com sucesso.";
			} else {
				return "Erro ao inserir.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public String excluir(ItemPedido item) {
		String sql = "delete from itens_pedido where numero_pedido=? and cod_item_cardapio=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, item.getCodPedido());
			pst.setInt(2, item.getCodItemCardapio());
			
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Excluído com sucesso.";
			} else {
				return "Erro ao excluir.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	
	}

	@Override
	public List<ItemPedido> listarItensPedido(Integer codPedido) {
		String sql = "select * from nome_itens_cardapio_pedido where numero_pedido = ?"; //<----VIEW
		Connection con = ConnectionFactory.getConnection();
		List<ItemPedido> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,codPedido);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ItemPedido item = new ItemPedido();
					
					item.setCodPedido(rs.getInt(1));
					item.setNomeItem(rs.getString(2));
					item.setQuantidade(rs.getInt(3));
					
					lista.add(item);
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

}
