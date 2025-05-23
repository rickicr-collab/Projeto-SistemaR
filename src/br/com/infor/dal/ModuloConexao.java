/*
 * 
 */
package br.com.infor.dal;

import java.sql.Connection;
import java.sql.DriverManager;

// TODO: Auto-generated Javadoc
/**
 * Conexao com o banco de dados.
 *
 * @author Ricardo Rosendo
 * @version 1.1 The Class ModuloConexao.
 */
public class ModuloConexao {

	/** The driver. */
	private String driver = "com.mysql.cj.jdbc.Driver";

	/** The url. */
	private String url = "jdbc:mysql://localhost:3306/dbinfor?characterEncoding=utf-8";

	/** The user. */
	private String user = "dba";

	/** The pass word. */
	private String passWord = "root@1804";

	/**
	 * Conectar. responsavel pela conexão com o banco
	 * 
	 * @return the connection
	 */
	public Connection conectar() {
		try {
			Class.forName(driver);
			Connection conexao = DriverManager.getConnection(url, user, passWord);
			return conexao;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
