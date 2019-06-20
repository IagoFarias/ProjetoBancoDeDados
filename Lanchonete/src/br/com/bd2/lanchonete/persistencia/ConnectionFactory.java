package br.com.bd2.lanchonete.persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
	public static Connection getConnection() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String user = "root";/*Coloque o usuário criado para acesso ao banco*/
		String senha = "Bd741951";/*Coloque a senha para acesso ao banco*/
		String url = "jdbc:mysql://localhost:3306/lanchonete?useTimezone=true&serverTimezone=UTC";/*Coloque o servidor
		onde está instalado o banco*/
		Connection con = null;

		try {
		Class.forName(driver);
		con = (Connection) DriverManager.getConnection(url, user, senha);
		} catch (ClassNotFoundException ex) {
		System.err.print(ex.getMessage());
		} catch (SQLException e) {
		System.err.print(e.getMessage());
		}
		return con;
		}
		public static void close(Connection con){
		try{
		con.close();
		}catch(SQLException e){
			
		}
		
		}

}
