package br.com.bd2.lanchonete.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bd2.lanchonete.negocio.Ingrediente;

public class IngredienteDAOImp implements IngredienteDAO{

	@Override
	public String inserir(Ingrediente ing) {
		String sql = "insert into ingrediente(codigo,nome,quantidade_estoque) values(?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1,0);
			pst.setString(2,ing.getNome());
			pst.setDouble(3,ing.getQuantidade());
	
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
	public String atualizaEstoque(Double qtd) {
		// TODO Fazer com procedure ou fun��o
		return null;
	}

	@Override
	public String excluir(Ingrediente ing) {
		String sql = "delete from ingrediente where codigo=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, ing.getCodIngrediente());
			int res = pst.executeUpdate();
			if (res > 0) {
				return "Exclu�do com sucesso.";
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
	public List<Ingrediente> listarTodos() {
		String sql = "select * from ingrediente";
		Connection con = ConnectionFactory.getConnection();
		List<Ingrediente> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Ingrediente ing = new Ingrediente();
					
					ing.setCodIngrediente(rs.getInt(1));
					ing.setNome(rs.getString(2));
					ing.setQuantidade(rs.getDouble(3));
					
					lista.add(ing);
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
