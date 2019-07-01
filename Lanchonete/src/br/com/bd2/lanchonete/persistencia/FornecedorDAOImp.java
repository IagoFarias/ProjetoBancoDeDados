package br.com.bd2.lanchonete.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bd2.lanchonete.negocio.Fornecedor;
import br.com.bd2.lanchonete.negocio.FornecedorEstat;

public class FornecedorDAOImp implements FornecedorDAO {

	@Override
	public String inserir(Fornecedor forn) {
		String sql = "insert into fornecedor(nome,cnpj,endereco,contato) values(?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, forn.getNome());
			pst.setString(2, forn.getCpnj());
			pst.setString(3, forn.getEndereco());
			pst.setString(4, forn.getContato());

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
	public String excluir(Fornecedor forn) {
		String sql = "delete from fornecedor where cnpj=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, forn.getCpnj());
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
	
	public List<FornecedorEstat> pesquisarEstat() {
		String sql = "select * from Controle_de_Estoque";
		Connection con = ConnectionFactory.getConnection();
		List<FornecedorEstat> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					FornecedorEstat fornEst = new FornecedorEstat();
					
					fornEst.nomeIngrediente=rs.getString(1);
					fornEst.qtdEstoque=rs.getDouble(2);
					fornEst.nomeFornecedor=rs.getString(3);
					fornEst.contato=rs.getString(4);
					fornEst.cNPJ=rs.getString(5);

					

					lista.add(fornEst);
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
	
	public List<FornecedorEstat> pesquisarEstatNomeIng(String nomeIng) {
		String sql = "select * from Controle_de_Estoque where Nome_Ingrediente = ?";
		Connection con = ConnectionFactory.getConnection();
		List<FornecedorEstat> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nomeIng);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					FornecedorEstat fornEst = new FornecedorEstat();
					
					fornEst.nomeIngrediente=rs.getString(1);
					fornEst.qtdEstoque=rs.getDouble(2);
					fornEst.nomeFornecedor=rs.getString(3);
					fornEst.contato=rs.getString(4);
					fornEst.cNPJ=rs.getString(5);

					

					lista.add(fornEst);
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
	
	public List<FornecedorEstat> pesquisarEstatNomeForn(String nomeForn) {
		String sql = "select * from Controle_de_Estoque where Nome_Fornecedor = ?";
		Connection con = ConnectionFactory.getConnection();
		List<FornecedorEstat> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nomeForn);
			
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					FornecedorEstat fornEst = new FornecedorEstat();
					
					fornEst.nomeIngrediente=rs.getString(1);
					fornEst.qtdEstoque=rs.getDouble(2);
					fornEst.nomeFornecedor=rs.getString(3);
					fornEst.contato=rs.getString(4);
					fornEst.cNPJ=rs.getString(5);

					

					lista.add(fornEst);
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
