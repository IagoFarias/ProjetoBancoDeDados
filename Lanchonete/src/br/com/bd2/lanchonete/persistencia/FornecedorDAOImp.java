package br.com.bd2.lanchonete.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bd2.lanchonete.negocio.Fornecedor;

public class FornecedorDAOImp implements FornecedorDAO {

	@Override
	public String inserir(Fornecedor forn) {
		String sql = "insert into fornecedor(nome,cnpj,endereco,contato) values(?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, forn.getNome());
			pst.setString(2, forn.getCpnj());
			pst.setString(4, forn.getEndereco());
			pst.setString(5, forn.getContato());

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
	public String alterar(Fornecedor forn) {
		String sql = "update fornecedor set nome=?,endereco=?,contato=? where cnpj=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);

			pst.setString(1, forn.getNome());
			pst.setString(2, forn.getEndereco());
			pst.setString(3, forn.getContato());
			pst.setString(4, forn.getCpnj());

			int res = pst.executeUpdate();
			if (res > 0) {
				return "Alterado com sucesso.";
			} else {
				return "Erro ao alterar.";
			}
		} catch (SQLException e) {
			return e.getMessage();
		} finally {
			ConnectionFactory.close(con);
		}
	}

	@Override
	public String excluir(Fornecedor forn) {
		String sql = "delete from fornecedor where cnpj=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, forn.getCpnj());
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
	public List<Fornecedor> listarTodos() {
		String sql = "select * from fornecedor";
		Connection con = ConnectionFactory.getConnection();
		List<Fornecedor> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Fornecedor forn = new Fornecedor();

					forn.setNome(rs.getString(1));
					forn.setCpnj(rs.getString(2));
					forn.setEndereco(rs.getString(3));
					forn.setContato(rs.getString(4));

					lista.add(forn);
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
	public Fornecedor pesquisarPorCnpj(String cnpj) {
		String sql = "select * from fornecedor where cnpj=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cnpj);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Fornecedor forn = new Fornecedor();

				forn.setNome(rs.getString(1));
				forn.setCpnj(rs.getString(2));
				forn.setEndereco(rs.getString(3));
				forn.setContato(rs.getString(4));

				return forn;
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
