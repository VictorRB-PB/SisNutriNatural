/**
 * 
 */
package br.com.sisnutri.dao;

import java.sql.SQLException;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;

import br.com.sisnutri.model.Consulta;

/**
 * @author Victor
 *
 */
public class ConsultaDao {

	private BancoDados bd;

	public ConsultaDao() {
		this.bd = BancoDados.getInstancia();
	}

	// ResultSet para pegar informações na tabela de consulta.
	public Consulta getConsultaFromResultSet(ResultSet rs) throws SQLException {

		int idConsulta = rs.getInt("idConsulta");
		int crn = rs.getInt("crn");
		int idAgenda = rs.getInt("idAgenda");
		int idAvaliacao = rs.getInt("idAvaliacao");
		String tecnica = rs.getString("tecnica");

		Consulta consulta = new Consulta(idConsulta, crn, idAgenda, idAvaliacao, tecnica);
		return consulta;

	}

	// Retorna consulta pelo ID.
	public Consulta findConsulta(int idConsulta) throws SQLException {

		String select = "Select * from tb_consulta WHERE idConsulta =" + idConsulta;

		ResultSet rs = (ResultSet) bd.select(select);
		if (rs.next()) {
			Consulta consulta = getConsultaFromResultSet(rs);
			return consulta;
		}

		return null;
	}

	// Insere consulta na tabela.
	public int insert(Consulta consulta) throws SQLException {

		int idConsulta = 0;

		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"INSERT INTO tb_consulta (crn, idAgenda, idAvaliacao, tecnica) VALUES (?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setInt(1, consulta.getCrn());
		ps.setInt(2, consulta.getIdAgenda());
		ps.setInt(3, consulta.getIdAvaliacao());
		ps.setString(4, consulta.getTecnica());
		ps.executeUpdate();

		// Recupera ID inserido na tabela.
		ResultSet rs = (ResultSet) ps.getGeneratedKeys();
		if (rs.next()) {
			idConsulta = rs.getInt(1);
		}

		return idConsulta;
	}

	// Atualiza consulta na tabela.
	public void update(Consulta consulta) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"UPDATE tb_consulta SET crn = ?, idAgenda = ?, idAvaliacao = ?, tecnica = ? WHERE idConsulta = ?");

		ps.setInt(1, consulta.getCrn());
		ps.setInt(2, consulta.getIdAgenda());
		ps.setInt(3, consulta.getIdAvaliacao());
		ps.setString(4, consulta.getTecnica());
		ps.setInt(5, consulta.getIdConsulta());
		ps.executeUpdate();

	}

	// Retorna consulta do paciente realizada, pelo ID da data de ultimo dia
	// agendado com status da consulta REALIZADO ou EM ABERTO
	public Consulta retornaUltimaConsultaAgendada(int idAgenda) throws SQLException {

		String select = "SELECT * FROM tb_consulta WHERE idAgenda = " + idAgenda + "order by idConsulta desc limit 1";

		ResultSet rs = (ResultSet) bd.select(select);
		if(rs.next()){
			Consulta consulta = getConsultaFromResultSet(rs);
			return consulta;
		}
		return null;
	}
}
