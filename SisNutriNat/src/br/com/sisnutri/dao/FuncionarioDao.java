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
import br.com.sisnutri.model.Funcionario;

/**
 * @author Victor
 *
 */
public class FuncionarioDao {

	private BancoDados bd;

	public FuncionarioDao() {
		this.bd = BancoDados.getInstancia();
	}

	// Pesquisar Funcionario pelo ID.
	public Funcionario findFunc(int idFunc) throws SQLException {

		String select = "Select * from tb_funcionario WHERE idFunc =" + idFunc;

		ResultSet rs = (ResultSet) bd.select(select);
		if (rs.next()) {
			Funcionario func = getFuncionarioFromResultSet(rs);
			return func;
		}

		return null;
	}

	public Funcionario findFuncCRN(int crn) throws SQLException {
		String select = "Select * from tb_funcionario WHERE crn = " + crn;

		ResultSet rs = (ResultSet) bd.select(select);
		if (rs.next()) {
			Funcionario func = getFuncionarioFromResultSet(rs);
			return func;
		}
		return null;
	}

	// ResultRest para pegar informações da tabela.
	public Funcionario getFuncionarioFromResultSet(ResultSet rs) throws SQLException {

		int idFunc = rs.getInt("idFunc");
		int crn = rs.getInt("crn");
		String nome = rs.getString("nome");
		String cpf = rs.getString("cpf");
		String senha = rs.getString("senha");
		String sexo = rs.getString("sexo");
		LocalDate dataNasc = LocalDate.parse(rs.getDate("dataNasc").toString());
		int cep = rs.getInt("cep");
		String estado = rs.getString("estado");
		String cidade = rs.getString("cidade");
		String endereco = rs.getString("endereco");
		String bairro = rs.getString("bairro");
		String email = rs.getString("email");
		String tel = rs.getString("tel");
		String cel = rs.getString("cel");
		String tipoFunc = rs.getString("tipoFunc");
		LocalDate dataAdmissao = LocalDate.parse(rs.getDate("dataAdmissao").toString());
		LocalDate dataDemissao;
		if (rs.getDate("dataDemissao") != null) {
			dataDemissao = LocalDate.parse(rs.getDate("dataDemissao").toString());
		} else {
			dataDemissao = null;
		}

		String obs = rs.getString("obs");
		boolean ativo = rs.getBoolean("ativo");

		Funcionario func = new Funcionario(idFunc, crn, nome, cpf, senha, sexo, dataNasc, cep, estado, cidade, endereco,
				bairro, email, tel, cel, tipoFunc, dataAdmissao, dataDemissao, obs, ativo);

		return func;

	}

	// Metodo para verificar LOGIN e SENHA na tabela.
	public Funcionario login(String login, String senha) throws SQLException {

		String select = "select * from tb_funcionario WHERE cpf = " + login + " and senha = " + senha;

		try {
			ResultSet rs = (ResultSet) bd.select(select);
			if (rs.next()) {
				Funcionario func = getFuncionarioFromResultSet(rs);
				return func;
			}
		} catch (Exception e) {
			// TODO: handle exception

		}
		return null;
	}

	// Retorna Lista de funcionarios
	public List<Funcionario> listaFunc() throws SQLException {
		String select = "select * from tb_funcionario ";
		List<Funcionario> listFunc = new ArrayList<>();

		ResultSet rs = (ResultSet) bd.select(select);
		while (rs.next()) {
			Funcionario func = getFuncionarioFromResultSet(rs);
			listFunc.add(func);
		}

		return listFunc;
	}

	// Insere funcionario na tabela.
	public void insert(Funcionario func) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("INSERT INTO tb_funcionario (" + "crn, " + "nome, " + "cpf, " + "senha, " + "sexo, "
						+ "dataNasc, " + "cep, " + "estado, " + "cidade, " + "endereco, " + "bairro, " + "email, "
						+ "tel, " + "cel, " + "dataAdmissao, " + "tipoFunc, " + "obs, "
						+ "ativo) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		ps.setInt(1, func.getCrn());
		ps.setString(2, func.getNome());
		ps.setString(3, func.getCpf());
		ps.setString(4, func.getSenha());
		ps.setString(5, func.getSexo());
		ps.setDate(6, Date.valueOf(func.getDataNasc()));
		ps.setInt(7, func.getCep());
		ps.setString(8, func.getEstado());
		ps.setString(9, func.getCidade());
		ps.setString(10, func.getEndereco());
		ps.setString(11, func.getBairro());
		ps.setString(12, func.getEmail());
		ps.setString(13, func.getTel());
		ps.setString(14, func.getCel());
		ps.setDate(15, Date.valueOf(func.getDataAdmissao()));
		ps.setString(16, func.getTipoFunc());
		ps.setString(17, func.getObs());
		ps.setBoolean(18, func.isAtivo());

		ps.executeUpdate();
	}

	// Atualiza funcionario na tabela.
	public void update(Funcionario func) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("UPDATE tb_funcionario SET " + "nome = ?" + ", senha = ?" + ", sexo = ?"
						+ ", dataNasc = ?" + ", cep = ?" + ", estado = ?" + ", cidade = ?" + ", endereco = ?"
						+ ", bairro = ?" + ", email = ?" + ", tel = ?" + ", cel = ?" + ", dataAdmissao = ?"
						+ ", dataDemissao = ?" + ", ativo = ?" + ", obs = ?" + ", tipoFunc = ?" + " WHERE idFunc = ?");

		ps.setString(1, func.getNome());
		ps.setString(2, func.getSenha());
		ps.setString(3, func.getSexo());
		ps.setDate(4, Date.valueOf(func.getDataNasc()));
		ps.setInt(5, func.getCep());
		ps.setString(6, func.getEstado());
		ps.setString(7, func.getCidade());
		ps.setString(8, func.getEndereco());
		ps.setString(9, func.getBairro());
		ps.setString(10, func.getEmail());
		ps.setString(11, func.getTel());
		ps.setString(12, func.getCel());
		ps.setDate(13, Date.valueOf(func.getDataAdmissao()));
		if (!func.isAtivo()) {
			ps.setDate(14, Date.valueOf(func.getDataDemissao()));
		} else {
			ps.setDate(14, null);
		}
		ps.setBoolean(15, func.isAtivo());
		ps.setString(16, func.getObs());
		ps.setString(17, func.getTipoFunc());
		ps.setInt(18, func.getIdFunc());
		ps.executeUpdate();

	}

	// Desatibilita funcionario na tabela.
	public void disableFunc(Funcionario func) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("UPDATE tb_funcionario SET ativo = ? WHERE idFunc = ?");

		ps.setBoolean(1, func.isAtivo());
		ps.setInt(2, func.getIdFunc());
		ps.executeUpdate();
	}
}
