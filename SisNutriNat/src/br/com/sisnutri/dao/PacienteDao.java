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

import br.com.sisnutri.model.Paciente;
import br.com.sisnutri.model.Prontuario;

/**
 * @author Victor
 *
 */
public class PacienteDao {

	private BancoDados bd;

	public PacienteDao() {
		this.bd = BancoDados.getInstancia();
	}

	// Procura paciente pelo ID, retorna null se ID for inexistente.
	public Paciente findPac(int idPac) throws SQLException {

		String select = "Select * FROM tb_paciente WHERE idPac =" + idPac;

		try {
			ResultSet rs = (ResultSet) bd.select(select);
			if (rs.next()) {
				Paciente pac = getPacienteFromResultSet(rs);
				return pac;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Erro procurando paciente com id: " + idPac + ", erro ocorrido: " + e);
		}

		return null;
	}

	// ResultSet para pegar informações da tabela Paciente.
	public Paciente getPacienteFromResultSet(ResultSet rs) throws SQLException {

		int idPac = rs.getInt("idPac");
		int idPront = rs.getInt("idPront");
		String nome = rs.getString("nome");
		String cpf = rs.getString("cpf");
		String sexo = rs.getString("sexo");
		boolean gestante = rs.getBoolean("gestante");
		LocalDate dataNasc = LocalDate.parse(rs.getDate("dataNasc").toString());
		int cep = rs.getInt("cep");
		String estado = rs.getString("estado");
		String cidade = rs.getString("cidade");
		String endereco = rs.getString("endereco");
		String bairro = rs.getString("bairro");
		String email = rs.getString("email");
		String tel = rs.getString("tel");
		String cel = rs.getString("cel");
		String obs = rs.getString("obs");
		boolean ativo = rs.getBoolean("ativo");

		Paciente pac = new Paciente(idPac, idPront, nome, cpf, sexo, gestante, dataNasc, cep, estado, cidade, endereco,
				bairro, email, tel, cel, obs, ativo);

		return pac;

	}

	// ResultSet para pegar informações da tabela Prontuario.
	public Prontuario getProntuarioFromResultRest(ResultSet rs) throws SQLException {

		int idPront = rs.getInt("idPront");
		int idPac = rs.getInt("idPac");
		String descricao = rs.getString("descricao");

		Prontuario pront = new Prontuario(idPront, idPac, descricao);
		return pront;

	}

	// Retorna uma lista de pacientes.
	public List<Paciente> listaPac() throws SQLException {
		String select = "select * FROM tb_paciente";
		List<Paciente> listPac = new ArrayList<>();

		ResultSet rs = (ResultSet) bd.select(select);
		while (rs.next()) {
			Paciente pac = getPacienteFromResultSet(rs);
			listPac.add(pac);
		}

		return listPac;
	}

	// Insere Paciente na tabela.
	public void insert(Paciente pac) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("INSERT INTO tb_paciente (" + "nome, " + "cpf, " + "sexo, " + "gestante, "
						+ "dataNasc, " + "cep, " + "estado, " + "cidade, " + "endereco, " + "bairro, " + "email, "
						+ "tel, " + "cel, " + "obs, " + "ativo) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		ps.setString(1, pac.getNome());
		ps.setString(2, pac.getCpf());
		ps.setString(3, pac.getSexo());
		ps.setBoolean(4, pac.isGestante());
		ps.setDate(5, Date.valueOf(pac.getDataNasc()));
		ps.setInt(6, pac.getCep());
		ps.setString(7, pac.getEstado());
		ps.setString(8, pac.getCidade());
		ps.setString(9, pac.getEndereco());
		ps.setString(10, pac.getBairro());
		ps.setString(11, pac.getEmail());
		ps.setString(12, pac.getTel());
		ps.setString(13, pac.getCel());
		ps.setString(14, pac.getObs());
		ps.setBoolean(15, pac.isAtivo());
		ps.executeUpdate();

		insertPront(pac);
	}

	// Insere prontuario na tabela para cada paciente adicionado.
	public void insertPront(Paciente pac) throws SQLException {

		int idPac = 0;
		String select = "Select idPac FROM tb_paciente WHERE cpf = " + pac.getCpf();

		ResultSet rs = (ResultSet) bd.select(select);
		if (rs.next()) {
			idPac = rs.getInt("idPac");

			PreparedStatement ps = (PreparedStatement) bd.getConnection()
					.prepareStatement("INSERT INTO tb_prontuario (idPac) VALUES(?)");

			ps.setInt(1, idPac);
			ps.executeUpdate();

			updateIdPront(idPac);

		}

	}

	// Atualiza o ID do prontuario do paciente criado.
	public void updateIdPront(int idPac) throws SQLException {

		int idPront = 0;
		String select = "Select * FROM tb_prontuario WHERE idPac = " + idPac;

		ResultSet rs = (ResultSet) bd.select(select);
		if (rs.next()) {
			idPront = rs.getInt("idPront");

			PreparedStatement ps = (PreparedStatement) bd.getConnection()
					.prepareStatement("Update tb_paciente SET " + "idPront = ? WHERE idPac = ?");

			ps.setInt(1, idPront);
			ps.setInt(2, idPac);
			ps.executeUpdate();
		}

	}

	// Atualiza paciente na tabela.
	public void update(Paciente pac) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("UPDATE tb_paciente SET " + "nome = ?" + ", sexo = ?" + ", gestante = ?"
						+ ", dataNasc = ?" + ", cep = ?" + ", estado = ?" + ", cidade = ?" + ", endereco = ?"
						+ ", bairro = ?" + ", email = ?" + ", tel = ?" + ", cel = ?" + ", ativo = ?" + ", obs = ?"
						+ " WHERE idPac = ?");

		ps.setString(1, pac.getNome());
		ps.setString(2, pac.getSexo());
		ps.setBoolean(3, pac.isGestante());
		ps.setDate(4, Date.valueOf(pac.getDataNasc()));
		ps.setInt(5, pac.getCep());
		ps.setString(6, pac.getEstado());
		ps.setString(7, pac.getCidade());
		ps.setString(8, pac.getEndereco());
		ps.setString(9, pac.getBairro());
		ps.setString(10, pac.getEmail());
		ps.setString(11, pac.getTel());
		ps.setString(12, pac.getCel());
		ps.setBoolean(13, pac.isAtivo());
		ps.setString(14, pac.getObs());
		ps.setInt(15, pac.getIdPac());

		ps.executeUpdate();

	}

	// Atualiza descricao do prontuario na tabela.
	public void updateProntuario(Paciente pac, String descr) throws SQLException {
		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("UPDATE tb_prontuario SET  descricao = ? WHERE idPac = ?");

		ps.setString(1, descr);
		ps.setInt(2, pac.getIdPac());
	}

	// Desabilita/Habilita paciente na tabela.
	public void disablePac(Paciente pac) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("UPDATE tb_paciente SET ativo = ? WHERE idPac = ?");

		ps.setBoolean(1, pac.isAtivo());
		ps.setInt(2, pac.getIdPac());
		ps.executeUpdate();
	}
	
	
	// Pesquisa paciente pelo CPF.
	public Paciente pesquisarPorCpf(String cpf) throws SQLException {

		String select = "select * from tb_paciente WHERE cpf = " + cpf;

		ResultSet rs = (ResultSet) bd.select(select);
		if (rs.next()) {
			Paciente pac = getPacienteFromResultSet(rs);
			return pac;
		}

		return null;
	}

}
