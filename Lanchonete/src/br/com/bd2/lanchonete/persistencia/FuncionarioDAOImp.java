package br.com.bd2.lanchonete.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import br.com.bd2.lanchonete.negocio.Funcionario;

public class FuncionarioDAOImp implements FuncionarioDAO{

	@Override
	public String inserir(Funcionario func) {
		String sql = "insert into funcionario(codigo,nome,cpf,endereco,fones) values(?,?,?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, 0);
			pst.setString(2,func.getNome());
			pst.setString(3,func.getCpf());
			pst.setString(4,func.getEndereco());
			pst.setString(5,func.getContato());
			
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
	public String alterar(Funcionario func) {
		String sql = "update funcionario set nome=?,endereco=?,fones=? where cpf=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1,func.getNome());
			pst.setString(2,func.getEndereco());
			pst.setString(3,func.getContato());
			pst.setString(4,func.getCpf());
			
			
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
	public String excluir(Funcionario func) {
		String sql = "delete from funcionario where cpf=? and codigo=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, func.getCpf());
			pst.setInt(2, func.getCodFuncionario());
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
	public List<Funcionario> listarTodos() {
		String sql = "select * from funcionario";
		Connection con = ConnectionFactory.getConnection();
		List<Funcionario> lista = new ArrayList<>();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Funcionario func = new Funcionario();
					
					func.setCodFuncionario(rs.getInt(1));
					func.setNome(rs.getString(2));
					func.setCpf(rs.getString(3));
					func.setEndereco(rs.getString(4));
					func.setContato(rs.getString(5));
					
					lista.add(func);
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
	public Funcionario pesquisarPorCpf(String cpf) {
		String sql = "select * from funcionario where cpf=?";
		Connection con = ConnectionFactory.getConnection();
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, cpf);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				Funcionario func = new Funcionario();
				
				func.setCodFuncionario(rs.getInt(1));
				func.setNome(rs.getString(2));
				func.setCpf(rs.getString(3));
				func.setEndereco(rs.getString(4));
				func.setContato(rs.getString(5));
				
				return func;
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
