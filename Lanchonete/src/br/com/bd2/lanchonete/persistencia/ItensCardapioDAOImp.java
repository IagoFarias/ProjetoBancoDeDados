package br.com.bd2.lanchonete.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bd2.lanchonete.negocio.ItemCardapio;

public class ItensCardapioDAOImp implements ItensCardapioDAO{

	@Override
	public List<ItemCardapio> listarTodos() {
		String sql = "select * from itens_cardapio";
		Connection con = ConnectionFactory.getConnection();
		List<ItemCardapio> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					ItemCardapio item = new ItemCardapio();
					
					item.setCodItem(rs.getInt(1));
					item.setNome(rs.getString(2));
					item.setValor(rs.getDouble(3));
					item.setDescricao(rs.getString(4));
					
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

	@Override
	public String inserir(ItemCardapio itemC) {
		String sql = "insert into itens_cardapio(codigo,nome,preco,descricao) values(?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,itemC.getCodItem());
			pst.setString(2,itemC.getNome() );
			pst.setDouble(3, itemC.getValor());
			pst.setString(4, itemC.getDescricao());
			
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
	public String excluir(ItemCardapio itemC) {
		String sql = "delete from itens_cardapio where codigo=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,itemC.getCodItem());
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
	public String alterar(ItemCardapio itemC) {
		String sql = "update itens_cardapio set nome=?,preco=?,descricao=? where codigo=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, itemC.getNome());
			pst.setDouble(2,itemC.getValor());
			pst.setString(3, itemC.getDescricao());
			pst.setInt(4, itemC.getCodItem());
			
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
