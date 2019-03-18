package lanchonete.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * @author flavio.rocha
 */
public class AcessoBD {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Erro: " + e.getCause());
		}
	}

	/**
	 * @return Connection
	 * @throws SQLException
	 */
	public Connection obtemConexao() throws SQLException {
		return DriverManager
				.getConnection("jdbc:mysql://db4free.net:3306/base_dados?user=flavio_rocha&password=159Asd753&useSSL=false");
	}
}