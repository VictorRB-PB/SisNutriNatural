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
import br.com.sisnutri.model.Avaliacao;
import br.com.sisnutri.model.Doenca;
import br.com.sisnutri.model.Exame;
import br.com.sisnutri.model.Farmaco;
import br.com.sisnutri.model.MedidasAntropometricas;
import br.com.sisnutri.util.DateUtil;

/**
 * @author Victor
 *
 */
public class AvaliacaoDao {

	private BancoDados bd;

	public AvaliacaoDao() {
		this.bd = BancoDados.getInstancia();
	}

	// Retorna avaliação pelo ID;
	public Avaliacao findAvaliacao(int idAvaliacao) throws SQLException {
		// TODO Auto-generated method stub

		String select = "Select * from tb_avaliacao WHERE idAvaliacao =" + idAvaliacao;

		ResultSet rs = (ResultSet) bd.select(select);
		if (rs.next()) {
			Avaliacao av = getAvaliacaoFromResultSet(rs);
			return av;
		}
		return null;
	}

	// ResultSet para pegar informações de avaliação na tabela
	private Avaliacao getAvaliacaoFromResultSet(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub

		int idAvaliacao = rs.getInt("idAvaliacao");
		int idConsulta = rs.getInt("idConsulta");
		int idAnamnese = rs.getInt("idAnamnese");
		int idAvClinica = rs.getInt("idAvClinica");
		int idAvFisica = rs.getInt("idAvFisica");

		Avaliacao av = new Avaliacao(idAvaliacao, idConsulta, idAnamnese, idAvClinica, idAvFisica, null);

		return av;
	}

	// Insere uma avaliação na tabela e retorna a PRIMARY KEY da avaliação
	// inserida
	public int insertAvaliacao(Avaliacao av) throws SQLException {
		// TODO Auto-generated method stub
		int idAv = 0;

		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"INSERT INTO tb_avaliacao (idConsulta, idAnamnese, idAvClinica, idAvFisica) VALUES(?, ?, ?, ?)",
				PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setInt(1, av.getIdConsulta());
		ps.setInt(2, av.getIdAnamnese());
		ps.setInt(3, av.getIdAvClinica());
		ps.setInt(4, av.getIdAvFisica());
		ps.executeUpdate();

		// Recupera ID inserido na tabela
		ResultSet rs = (ResultSet) ps.getGeneratedKeys();
		if (rs.next()) {
			idAv = rs.getInt(1);
		}

		return idAv;
	}

	// ATUALIZA AVALIAÇÃO na tabela
	public void updateAvaliacao(Avaliacao av) throws SQLException {
		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"UPDATE tb_avaliacao SET idAnamnese = ?, idAvClinica =?, idAvFisica = ? WHERE idAvaliacao = ?");
		ps.setInt(1, av.getIdAnamnese());
		ps.setInt(2, av.getIdAvClinica());
		ps.setInt(3, av.getIdAvFisica());
		ps.setInt(4, av.getIdAvaliacao());
		ps.executeUpdate();
	}

	// DELETA AVALIAÇÃO da tabela.
	public void deleteAvaliacao(Avaliacao av) throws SQLException {
		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("DELETE from tb_avaliacao WHERE idAvaliacao = ?");
		ps.setInt(1, av.getIdAvaliacao());
		ps.executeUpdate();
	}

	// ResultSet para pegar informações da tabela DOENÇA da avaliação clinico.
	private Doenca getDoencaFromResultSet(ResultSet rs) throws SQLException {

		int idDoenca = rs.getInt("idDoenca");
		int idAvClinica = rs.getInt("idAvClinica");
		String descricao = rs.getString("descricao");
		String situacao = rs.getString("situacao");
		String obs = rs.getString("obs");
		String tipoDoenca = rs.getString("tipoDoenca");

		Doenca dc = new Doenca(idDoenca, idAvClinica, descricao, situacao, obs, tipoDoenca);

		return dc;
	}

	// ResultSet para pegar informações da tabela FARMACO da avaliação clinico.
	private Farmaco getFarmacoFromResultSet(ResultSet rs) throws SQLException {

		int idFarmaco = rs.getInt("idFarmaco");
		int idAvClinica = rs.getInt("idAvClinica");
		String descricao = rs.getString("descricao");
		String subsAtiva = rs.getString("subsAtiva");
		String obs = rs.getString("obs");

		Farmaco fm = new Farmaco(idFarmaco, idAvClinica, descricao, subsAtiva, obs);

		return fm;
	}

	// ResultSet para pegar informações da tabela EXAME da avaliação clinico.
	private Exame getExameFromResultSet(ResultSet rs) throws SQLException {

		int idExame = rs.getInt("idExame");
		int idAvCli = rs.getInt("idAvClinica");
		String descricao = rs.getString("descricao");
		String valorRef = rs.getString("valorRef");
		String interpretacao = rs.getString("interpretacao");

		Exame ex = new Exame(idExame, idAvCli, descricao, valorRef, interpretacao);

		return ex;
	}

	// ResultSet para retornar Medidas Antropometricas tiradas na avaliação
	// fisica.
	private MedidasAntropometricas getMedidasFromResultSet(ResultSet rs) throws SQLException {

		int idMedida = rs.getInt("idMedida");
		int idAvFisica = rs.getInt("idAvFisica");
		double pesoAtual = rs.getDouble("pesoAtual");
		double pesoDesejado = rs.getDouble("pesoDesejado");
		double pesoUsual = rs.getDouble("pesoUsual");
		int tempoSobrepeso = rs.getInt("tempoSobrepeso");
		double altura = rs.getDouble("altura");
		double altJoelho = rs.getDouble("altJoelho");
		double triceps = rs.getDouble("triceps");
		double biceps = rs.getDouble("biceps");
		double subescapular = rs.getDouble("subescapular");
		double axilarMedial = rs.getDouble("alixarMidal");
		double toracica = rs.getDouble("toracica");
		double supraEspinal = rs.getDouble("supraEspinal");
		double supraIliaca = rs.getDouble("supraIlaica");
		double abdome = rs.getDouble("abdome");
		double coxa = rs.getDouble("coxa");
		double panturrilhaDobra = rs.getDouble("panturrilhaDobra");
		double braco = rs.getDouble("braco");
		double antebraco = rs.getDouble("antebraco");
		double punho = rs.getDouble("punho");
		double torax = rs.getDouble("torax");
		double cintura = rs.getDouble("cintura");
		double tornozelo = rs.getDouble("tornozelo");
		double abdominal = rs.getDouble("abdominal");
		double quadril = rs.getDouble("quadril");
		double glutMax = rs.getDouble("glutMax");
		double coxaMax = rs.getDouble("coxaMax");
		double panturrilhaPerimetro = rs.getDouble("panturrilhaPerimetro");
		double cefalico = rs.getDouble("cefalico");
		double biestiloide = rs.getDouble("biestiloide");
		double bumeral = rs.getDouble("bumeral");
		double bfemural = rs.getDouble("bfumeral");

		MedidasAntropometricas mds = new MedidasAntropometricas(idMedida, idAvFisica, pesoAtual, pesoDesejado,
				pesoUsual, tempoSobrepeso, altura, altJoelho, triceps, biceps, subescapular, axilarMedial, toracica,
				supraEspinal, supraIliaca, abdome, coxa, panturrilhaDobra, braco, antebraco, punho, torax, cintura,
				tornozelo, abdominal, quadril, glutMax, coxaMax, panturrilhaPerimetro, cefalico, biestiloide, bumeral,
				bfemural);

		return mds;

	}

	// Insere AVALIAÇÃO CLINICA na tabela e retorna o ID gerado
	public int insertAvClinica(int idAvaliacao) throws SQLException {

		int idAvClinica = 0;
		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"INSERT INTO tb_avclinica (idAvaliacao) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setInt(1, idAvaliacao);
		ps.executeUpdate();

		// Recupera id inserido na table
		ResultSet rs = (ResultSet) ps.getGeneratedKeys();
		if (rs.next()) {
			idAvClinica = rs.getInt(1);
		}

		return idAvClinica;
	}

	// Metodo para inserir DOENCA na AVALIAÇÃO CLINICA do paciente
	public void insertDoenca(Doenca d) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"INSERT INTO tb_doenca (idAvClinica, descricao, situacao, obs, tipoDoenca) VALUES (?, ?, ?, ?, ?)");

		ps.setInt(1, d.getIdAvClinica());
		ps.setString(2, d.getDescricao());
		ps.setString(3, d.getSituacao());
		ps.setString(4, d.getObs());
		ps.setString(5, d.getTipoDoenca());
		ps.executeUpdate();

	}

	// Metodo para inserir FARMACO na AVALIAÇÃO CLINICA do paciente
	public void insertFarmaco(Farmaco f) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"INSERT INTO tb_farmaco (idAvClinica, descricao, subsAtiva, obs) VALUES (?, ?, ?, ?)");

		ps.setInt(1, f.getIdAvCli());
		ps.setString(2, f.getDescricao());
		ps.setString(3, f.getSubsAtiva());
		ps.setString(4, f.getObs());
		ps.executeUpdate();

	}

	// Metodo para inserir EXAME na AVALIAÇÃO CLINICA do paciente
	public void insertExame(Exame e) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"INSERT INTO tb_exame (idAvClinica, descricao, valorRef, interpretacao) VALUES (?, ?, ?, ?)");

		ps.setInt(1, e.getIdAvCli());
		ps.setString(2, e.getDescricao());
		ps.setString(3, e.getValorRef());
		ps.setString(4, e.getInterpretacao());
		ps.executeUpdate();

	}

	// ATUALIZA DOENÇA na avaliação CLINICA selecionada.
	public void updateDoenca(Doenca d) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"UPDATE tb_doenca SET descricao = ?, situacao = ?, obs = ?, tipoDoenca = ? WHERE idDoenca =?");

		ps.setString(1, d.getDescricao());
		ps.setString(2, d.getSituacao());
		ps.setString(3, d.getObs());
		ps.setString(4, d.getTipoDoenca());
		ps.setInt(5, d.getIdDoenca());
		ps.executeUpdate();
	}

	// ATUALIZA FARMACO na avaliação CLINICA selecionada.
	public void updateFarmaco(Farmaco f) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("UPDATE tb_farmaco SET descricao = ?, subsAtiva = ?, obs = ? WHERE idFarmaco =?");

		ps.setString(1, f.getDescricao());
		ps.setString(2, f.getSubsAtiva());
		ps.setString(3, f.getObs());
		ps.setInt(4, f.getIdFarcamo());
		ps.executeUpdate();
	}

	// ATUALIZA EXAME na avaliação CLINICA selecionada.
	public void updateExame(Exame e) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"UPDATE tb_exame SET descricao = ?, valorRef = ?, interpretacao = ? WHERE idExame =?");

		ps.setString(1, e.getDescricao());
		ps.setString(2, e.getValorRef());
		ps.setString(3, e.getInterpretacao());
		ps.setInt(4, e.getIdExame());
		ps.executeUpdate();
	}

	// DELETA DOENÇA da avaliação CLINICA selecionada
	public void deleteDoenca(Doenca d) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("DELETE from tb_doenca WHERE idDoenca = ?");

		ps.setInt(1, d.getIdDoenca());
		ps.executeUpdate();
	}

	// DELETA FARMACO da avaliação CLINICA selecionada
	public void deleteFarmaco(Farmaco f) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("DELETE from tb_farmaco WHERE idFarmaco = ?");

		ps.setInt(1, f.getIdFarcamo());
		ps.executeUpdate();
	}

	// DELETA EXAME da avaliação CLINICA selecionada
	public void deleteExame(Exame e) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("DELETE from tb_exame WHERE idExame = ?");

		ps.setInt(1, e.getIdExame());
		ps.executeUpdate();
	}

	// Retorna lista de DOENÇAS da avaliação CLINICA selecionada.
	public List<Doenca> listaDoencaAvClinica(int idAvClinica) throws SQLException {

		String select = "Select * from tb_doenca WHERE idAvClinica = " + idAvClinica;
		List<Doenca> doencas = new ArrayList<>();

		ResultSet rs = (ResultSet) bd.select(select);
		while (rs.next()) {
			Doenca d = getDoencaFromResultSet(rs);
			doencas.add(d);
		}

		return doencas;
	}

	// Retorna lista de FARMACO da avaliação CLINICA selecionada.
	public List<Farmaco> listaFarmacoAvClinica(int idAvClinica) throws SQLException {

		String select = "Select * from tb_farmaco WHERE idAvClinica = " + idAvClinica;
		List<Farmaco> farmacos = new ArrayList<>();

		ResultSet rs = (ResultSet) bd.select(select);
		while (rs.next()) {
			Farmaco f = getFarmacoFromResultSet(rs);
			farmacos.add(f);
		}

		return farmacos;
	}

	// Retorna lista de EXAMES da avaliação CLINICA selecionada.
	public List<Exame> listaExameAvClinica(int idAvClinica) throws SQLException {

		String select = "Select * from tb_exame WHERE idAvClinica = " + idAvClinica;
		List<Exame> exames = new ArrayList<>();

		ResultSet rs = (ResultSet) bd.select(select);
		while (rs.next()) {
			Exame e = getExameFromResultSet(rs);
			exames.add(e);
		}

		return exames;
	}

	// Insere AVALIAÇÃO FISICA na tabela e retorna o ID gerado
	public int insertAvFisica(int idAvaliacao) throws SQLException {

		int idAvFisica = 0;
		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"INSERT INTO tb_avfisica (idAvaliacao) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setInt(1, idAvaliacao);
		ps.executeUpdate();

		// Recupera id inserido na table
		ResultSet rs = (ResultSet) ps.getGeneratedKeys();
		if (rs.next()) {
			idAvFisica = rs.getInt(1);
		}

		return idAvFisica;
	}

	// Retorna MEDIDA ANTROPOMETRICA pelo ID da avaliação FISICA requerida.
	public MedidasAntropometricas findMedidas(int idAvFisica) throws SQLException {

		String select = "Select * from tb_medidas_antropometricas WHERE idAvFisica = " + idAvFisica;
		ResultSet rs = (ResultSet) bd.select(select);
		if (rs.next()) {
			MedidasAntropometricas m = getMedidasFromResultSet(rs);
			return m;
		}

		return null;
	}

	// Insere MEDIDA ANTROPOMETRICA na tabela.
	public void insertMedidas(MedidasAntropometricas m) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"INSERT INTO tb_medidas_antropometricas (idAvFisica, pesoAtual, pesoDesejado, pesoUsual, temposobrepeso, altura, altJoelho, triceps, biceps, subescapular, axilarMidal, toracica, supraEspinal, supraIliaca, abdome, coxa, panturrilhaDobra, braco, antebraco, punho, torax, cintura, tornozelo, abdominal, quadril, glutMax, coxaMax, panturrilhaPerimetro, cefalico, biestiloide, bumeral, bfemural) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		ps.setInt(1, m.getIdAvFisica());
		ps.setDouble(2, m.getPesoAtual());
		ps.setDouble(3, m.getPesoDesejado());
		ps.setDouble(4, m.getPesoUsual());
		ps.setInt(5, m.getTempoSobrepeso());
		ps.setDouble(6, m.getAltura());
		ps.setDouble(7, m.getAltJoelho());
		ps.setDouble(8, m.getTriceps());
		ps.setDouble(9, m.getBiceps());
		ps.setDouble(10, m.getSubescapular());
		ps.setDouble(11, m.getAxilarMedial());
		ps.setDouble(12, m.getToracica());
		ps.setDouble(13, m.getSupraEspinal());
		ps.setDouble(14, m.getSupraIliaca());
		ps.setDouble(15, m.getAbdome());
		ps.setDouble(16, m.getCoxa());
		ps.setDouble(17, m.getPanturrilhaDobra());
		ps.setDouble(18, m.getBraco());
		ps.setDouble(19, m.getAntebraco());
		ps.setDouble(20, m.getPunho());
		ps.setDouble(21, m.getTorax());
		ps.setDouble(22, m.getCintura());
		ps.setDouble(23, m.getTornozelo());
		ps.setDouble(24, m.getAbdominal());
		ps.setDouble(25, m.getQuadril());
		ps.setDouble(26, m.getGlutMax());
		ps.setDouble(27, m.getCoxaMax());
		ps.setDouble(28, m.getPanturrilhaPerimetro());
		ps.setDouble(29, m.getCefalico());
		ps.setDouble(30, m.getBiestiloide());
		ps.setDouble(31, m.getBumeral());
		ps.setDouble(32, m.getBfemural());
		ps.executeUpdate();
	}

	// Atualiza MEDIDAS ANTROPOMETRICAS na tabela.
	public void updateMedidas(MedidasAntropometricas m) throws SQLException {
		PreparedStatement ps = (PreparedStatement) bd.getConnection().prepareStatement(
				"UPDATE tb_medidas_antropometricas SET idAvFisica = ?, pesoAtual = ?, pesoDesejado = ?,"
						+ " pesoUsual = ?, temposobrepeso = ?, altura = ?, altJoelho = ?, triceps = ?, biceps = ?, "
						+ "subescapular = ?, axilarMidal = ?, toracica = ?, supraEspinal = ?, supraIliaca = ?, abdome = ?, "
						+ "coxa = ?, panturrilhaDobra = ?, braco = ?, antebraco = ?, punho = ?, torax = ?, cintura = ?, "
						+ "tornozelo = ?, abdominal = ?, quadril = ?, glutMax = ?, coxaMax = ?, panturrilhaPerimetro = ?, "
						+ "cefalico = ?, biestiloide = ?, bumeral = ?, bfemural = ? WHERE idMedida = ?");

		ps.setInt(1, m.getIdAvFisica());
		ps.setDouble(2, m.getPesoAtual());
		ps.setDouble(3, m.getPesoDesejado());
		ps.setDouble(4, m.getPesoUsual());
		ps.setInt(5, m.getTempoSobrepeso());
		ps.setDouble(6, m.getAltura());
		ps.setDouble(7, m.getAltJoelho());
		ps.setDouble(8, m.getTriceps());
		ps.setDouble(9, m.getBiceps());
		ps.setDouble(10, m.getSubescapular());
		ps.setDouble(11, m.getAxilarMedial());
		ps.setDouble(12, m.getToracica());
		ps.setDouble(13, m.getSupraEspinal());
		ps.setDouble(14, m.getSupraIliaca());
		ps.setDouble(15, m.getAbdome());
		ps.setDouble(16, m.getCoxa());
		ps.setDouble(17, m.getPanturrilhaDobra());
		ps.setDouble(18, m.getBraco());
		ps.setDouble(19, m.getAntebraco());
		ps.setDouble(20, m.getPunho());
		ps.setDouble(21, m.getTorax());
		ps.setDouble(22, m.getCintura());
		ps.setDouble(23, m.getTornozelo());
		ps.setDouble(24, m.getAbdominal());
		ps.setDouble(25, m.getQuadril());
		ps.setDouble(26, m.getGlutMax());
		ps.setDouble(27, m.getCoxaMax());
		ps.setDouble(28, m.getPanturrilhaPerimetro());
		ps.setDouble(29, m.getCefalico());
		ps.setDouble(30, m.getBiestiloide());
		ps.setDouble(31, m.getBumeral());
		ps.setDouble(32, m.getBfemural());
		ps.setInt(33, m.getIdMedida());
		ps.executeUpdate();
	}

	// DELETA MEDIDAS ANTROPOMETRICAS pelo ID solicitado.
	public void deleteMedidas(MedidasAntropometricas m) throws SQLException {

		PreparedStatement ps = (PreparedStatement) bd.getConnection()
				.prepareStatement("DELETE FROM tb_medidas_antropometricas WHERE idMedida = ?");

		ps.setInt(1, m.getIdMedida());
		ps.executeUpdate();
	}

	// Retorna lista com ID das avaliações CLINICAS e FISICAS REALIZADAS do
	// paciente requerido.
	public List<Avaliacao> listaClinicasFisicasPaciente(int idPaciente) throws SQLException {
		List<Avaliacao> avaliacoes = new ArrayList<>();
		String select = "Select av.idAvaliacao as IDAVALIACAO, av.idConsulta as IDCONSULTA, av.idAnamnese as IDANAMNESE, av.idAvClinica as IDAVCLINICA, av.idAvFisica as IDAVFISICA, a.dataConsulta as DATACONSULTA from tb_avaliacao as av join tb_consulta as c on av.idConsulta = c.idConsulta join tb_agenda as a on c.idAgenda = a.idConsultaAgendada join tb_paciente as p on a.idPaciente = p.idPac WHERE p.idPac = "
				+ idPaciente + " and a.statusConsulta = 'REALIZADA' ORDER BY a.dataConsulta DESC";

		ResultSet rs = (ResultSet) bd.select(select);
		while (rs.next()) {
			int idAvaliacao = rs.getInt("IDAVALIACAO");
			int idConsulta = rs.getInt("IDCONSULTA");
			int idAnamnese = rs.getInt("IDANAMNESE");
			int idAvClinica = rs.getInt("IDAVCLINICA");
			int idAvFisica = rs.getInt("IDAVFISICA");
			Date dataString = rs.getDate("DATACONSULTA");
			LocalDate dataFormatada = LocalDate.parse(dataString.toString());
			String dataStringFormatada = DateUtil.format(dataFormatada);

			Avaliacao av = new Avaliacao(idAvaliacao, idConsulta, idAnamnese, idAvClinica, idAvFisica,
					dataStringFormatada);
			avaliacoes.add(av);
		}
		return avaliacoes;
	}

}
