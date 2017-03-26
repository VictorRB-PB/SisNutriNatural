/**
 * 
 */
package br.com.sisnutri.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author Victor
 *
 */
public class BancoDados {

	private Connection connection;
	private static String url;
	private static String user;
	private static String password;
	private Statement statement;
	private static BancoDados _instancia = null;

	public static enum URL {

		PRINCIPAL, DESENVOLVIMENTO;
	}

	public static URL servidor;

	public BancoDados() {
		servidor = URL.PRINCIPAL;
		switch (servidor) {
		case DESENVOLVIMENTO:
			url = "jdbc:mysql://localhost:3306/sisnutri_dev?useSSL=false";
			break;
		case PRINCIPAL:
			url = "jdbc:mysql://localhost:3306/sisnutri?useSSL=false";
			break;
		}

		user = "root";
		password = "135201zx";
		registraDriver();
		try {
			connection = (Connection) DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {
			Logger.getLogger(BancoDados.class.getName()).log(Level.SEVERE, null, ex);
			System.exit(1);
		}
	}

	public BancoDados(String url, String user, String pass) {
		BancoDados.url = url;
		BancoDados.user = user;
		BancoDados.password = pass;
	}

	public Connection getConnection() {
		return connection;
	}

	public void fazConexao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = (Connection) DriverManager.getConnection(url, user, password);
	}

	private void registraDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static BancoDados getInstancia() {
		synchronized (BancoDados.class) {
			return _instancia == null ? _instancia = new BancoDados() : _instancia;
		}
	}

	/**
	 * Executa o comando fornecido de acesso ao banco de dados.
	 *
	 * @param comando
	 *            String de acesso ao banco de dados em SQL.
	 * @return a quantidade de linhas da tabela após a execução do comando > 0
	 *         se o comando executou com sucesso, 0 caso contrário
	 * @throws java.sql.SQLException
	 */
	public int executaComando(String comando) throws SQLException {
		statement = (Statement) this.connection.createStatement();
		return this.statement.executeUpdate(comando);
	}

	/**
	 * Executa o comando fornecido de acesso ao banco de dados.
	 *
	 * @param comando
	 *            String de acesso ao banco de dados, contendo a clausula
	 *            'select'.
	 * @return Objeto ResultSet que contem todos os campos.
	 * @throws SQLException
	 */
	public java.sql.ResultSet select(String comando) throws SQLException {
		this.statement = (Statement) this.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		return this.statement.executeQuery(comando);
	}

	public int getLastID() throws SQLException {
		ResultSet r = (ResultSet) this.select("SELECT LAST_INSERT_ID() AS id");
		return r.next() ? r.getInt("id") : -1;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public void setUrl(String url) {
		BancoDados.url = url;
	}

	public void setUser(String user) {
		BancoDados.user = user;
	}

	public void setPassword(String password) {
		BancoDados.password = password;
	}
}
