/**
 * 
 */
package br.com.sisnutri.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import br.com.sisnutri.model.Agenda;

/**
 * @author Victor
 *
 */
public class AgendaDao {

	private BancoDados bd;

	public AgendaDao() {

		this.bd = BancoDados.getInstancia();
	}

	// Procura consulta agendada pelo ID, retorna null se não existir consulta.
	public Agenda findConsultaAgend(int idConsultaAgend) throws SQLException {

		String select = "Select * FROM tb_agenda WHERE idConsultaAgendada =" + idConsultaAgend;

		try {
			ResultSet rs = (ResultSet) bd.select(select);
			if (rs.next()) {
				Agenda aged = getAgendaFromResultSet(rs);
				return aged;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out
					.println("Erro procurando consulta agendada com id: " + idConsultaAgend + ", erro ocorrido: " + e);
		}

		return null;
	}

	// ResultSet para pegar informações da tabela AGENDA.
	public Agenda getAgendaFromResultSet(ResultSet rs) throws SQLException {

		int idConsultaAgendada = rs.getInt("idConsultaAgendada");
		int idFuncionario = rs.getInt("idFuncionario");
		int idPaciente = rs.getInt("idPaciente");
		LocalDate dataConsulta = LocalDate.parse(rs.getDate("dataConsulta").toString());
		String tipoConsulta = rs.getString("tipoConsulta");
		String statusConsulta = rs.getString("statusConsulta");
		String obs = rs.getString("obs");

		Agenda aged = new Agenda(idConsultaAgendada, idFuncionario, idPaciente, dataConsulta, tipoConsulta,
				statusConsulta, obs);

		return aged;
	}

	// Retorna uma lista de consultas agendadas.
	public List<Agenda> listaAged() throws SQLException {
		String select = "select * FROM tb_agenda ORDER BY dataConsulta DESC";
		List<Agenda> listaAged = new ArrayList<>();

		ResultSet rs = (ResultSet) bd.select(select);
		while (rs.next()) {
			Agenda aged = getAgendaFromResultSet(rs);
			listaAged.add(aged);
		}

		return listaAged;
	}

	// Insere Consulta na tabela de Agenda.
	public void insert(Agenda aged) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("INSERT INTO tb_agenda (" + "idFuncionario, " + "idPaciente, " + "dataConsulta, "
						+ "tipoConsulta, " + "statusConsulta, " + "obs) VALUES(?, ?, ?, ?, ?, ?)");

		ps.setInt(1, aged.getIdFuncionario());
		ps.setInt(2, aged.getIdPaciente());
		ps.setDate(3, Date.valueOf(aged.getDataConsulta()));
		ps.setString(4, aged.getTipoConsulta());
		ps.setString(5, aged.getStatusConsulta());
		ps.setString(6, aged.getObs());
		ps.executeUpdate();

	}

	// Atualiza consulta agendada na tabela.
	public void update(Agenda aged) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("UPDATE tb_agenda SET " + "idFuncionario = ?" + ", idPaciente = ?"
						+ ", dataConsulta = ?" + ", tipoConsulta = ?" + ", statusConsulta = ?" + ", obs = ?"
						+ " WHERE idConsultaAgendada = ?");

		ps.setInt(1, aged.getIdFuncionario());
		ps.setInt(2, aged.getIdPaciente());
		ps.setDate(3, Date.valueOf(aged.getDataConsulta()));
		ps.setString(4, aged.getTipoConsulta());
		ps.setString(5, aged.getStatusConsulta());
		ps.setString(6, aged.getObs());
		ps.setInt(7, aged.getIdConsultaAgendada());

		ps.executeUpdate();

	}

	// Cancela/Remarca consulta na tabela.
	public void cancelCons(Agenda aged) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("UPDATE tb_agenda SET statusConsulta = ? WHERE idConsultaAgendada = ?");

		ps.setString(1, aged.getStatusConsulta());
		ps.setInt(2, aged.getIdConsultaAgendada());
		ps.executeUpdate();
	}

	// Retorna uma lista de consultas EM ABERTO agendadas NO DIA.
	public List<Agenda> listaConsultDia() throws SQLException {
		Date dataAtual = new Date(System.currentTimeMillis());
		String select = "select * FROM tb_agenda WHERE dataConsulta = '" + dataAtual
				+ "' and statusConsulta = 'EM ABERTO'";
		List<Agenda> listaAged = new ArrayList<>();

		ResultSet rs = (ResultSet) bd.select(select);
		AgendaDao agendDao = new AgendaDao();
		while (rs.next()) {
			Agenda aged = agendDao.getAgendaFromResultSet(rs);
			listaAged.add(aged);
		}

		return listaAged;
	}
}
