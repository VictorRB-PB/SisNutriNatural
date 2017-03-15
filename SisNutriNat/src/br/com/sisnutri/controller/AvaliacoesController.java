/**
 * 
 */
package br.com.sisnutri.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.sisnutri.dao.AgendaDao;
import br.com.sisnutri.dao.AvaliacaoDao;
import br.com.sisnutri.dao.ConsultaDao;
import br.com.sisnutri.model.Agenda;
import br.com.sisnutri.model.Avaliacao;
import br.com.sisnutri.model.Consulta;
import br.com.sisnutri.model.Doenca;
import br.com.sisnutri.model.Exame;
import br.com.sisnutri.model.Farmaco;
import br.com.sisnutri.model.MedidasAntropometricas;
import br.com.sisnutri.model.Paciente;
import br.com.sisnutri.util.DateUtil;
import br.com.sisnutri.view.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;

/**
 * @author Victor
 *
 */
public class AvaliacoesController implements Initializable {

	MainApp mainApp;
	private Paciente pacienteSelecionado;
	private Agenda agendaPaciente;
	private Consulta consultaSelecionada;
	private Avaliacao avAtual;
	private MedidasAntropometricas medidasAtual;
	private Doenca doencaAtual;
	private Farmaco farmacoAtual;
	private Exame exameAtual;

	@FXML
	Text txNomePac;
	@FXML
	Text txSexo;
	@FXML
	Text txIdade;
	@FXML
	Text txAltura;
	@FXML
	Text txPeso;
	@FXML
	TabPane tabPAv;
	@FXML
	Tab tabAvClinica;
	@FXML
	Tab tabAvFisica;
	@FXML
	HTMLEditor htEditor;
	@FXML
	TableView<Doenca> tbDoencas;
	@FXML
	TableColumn<Doenca, String> clDescDoenca;
	@FXML
	TableColumn<Doenca, String> clSituDoenca;
	@FXML
	TableColumn<Doenca, String> clObsDoenca;
	@FXML
	TableColumn<Doenca, String> clTipoDoenca;
	@FXML
	TableView<Farmaco> tbFarmacos;
	@FXML
	TableColumn<Farmaco, String> clDescFarmaco;
	@FXML
	TableColumn<Farmaco, String> clSubstAtivaFarmaco;
	@FXML
	TableColumn<Farmaco, String> clObsFarmaco;
	@FXML
	TableView<Exame> tbExames;
	@FXML
	TableColumn<Exame, String> clDescExames;
	@FXML
	TableColumn<Exame, String> clVrReferidoExames;
	@FXML
	TableColumn<Exame, String> clInterpretacaoExames;
	@FXML
	TextField txPesoAtual;
	@FXML
	TextField txPesoDesejado;
	@FXML
	TextField txPesoUsual;
	@FXML
	TextField txTempoSobrepeso;
	@FXML
	TextField txAltura2;
	@FXML
	TextField txAltJoelho;
	@FXML
	TextField txTriceps;
	@FXML
	TextField txBiceps;
	@FXML
	TextField txSubescapular;
	@FXML
	TextField txAxilarMedial;
	@FXML
	TextField txToracica;
	@FXML
	TextField txSupraEspinal;
	@FXML
	TextField txSuprailiaca;
	@FXML
	TextField txAbdome;
	@FXML
	TextField txCoxa;
	@FXML
	TextField txPanturrilha;
	@FXML
	TextField txBraco;
	@FXML
	TextField txAntebraco;
	@FXML
	TextField txPunho;
	@FXML
	TextField txTorax;
	@FXML
	TextField txCintura;
	@FXML
	TextField txTornozelo;
	@FXML
	TextField txAbdominal;
	@FXML
	TextField txQuadril;
	@FXML
	TextField txGlutMax;
	@FXML
	TextField txCoxaMax;
	@FXML
	TextField txPanturrilha2;
	@FXML
	TextField txCefalico;
	@FXML
	TextField txBiestiloide;
	@FXML
	TextField txBumeral;
	@FXML
	TextField txBfemural;
	@FXML
	ImageView imgMedidas;
	@FXML
	Text txPt;
	@FXML
	Text txPpr;
	@FXML
	Text txPavsPt;
	@FXML
	Text txPavsPu;
	@FXML
	Text txPavsPd;
	@FXML
	Text txPesoCorrigido;
	@FXML
	Text txTempo;
	@FXML
	Text txClassifPavsPt;
	@FXML
	Text txClassifPavsPd;
	@FXML
	Text txImc;
	@FXML
	Text txCb;
	@FXML
	Text txPct;
	@FXML
	Text txAmb;
	@FXML
	Text txCmb;
	@FXML
	Text txDC;
	@FXML
	Text txPercGord;
	@FXML
	Text txPercGordIdeal;
	@FXML
	Text txMcm;
	@FXML
	Text txPg;
	@FXML
	Text txPgi;
	@FXML
	Text txPm;
	@FXML
	Text txPo;
	@FXML
	Text txPr;
	@FXML
	Text txGeb;
	@FXML
	Text txTmb;
	@FXML
	Button btFinalizarConsulta;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initFichas();
		initCols();
		initListenerTabs();

	}

	// Botão FINALIZAR CONSULTA
	@FXML
	private void finishConsulta() {
		try {
			JOptionPane.showMessageDialog(null, "Consulta finalizada");
			if (agendaPaciente != null) {
				agendaPaciente.setStatusConsulta("REALIZADA");
				AgendaDao a = new AgendaDao();
				a.update(agendaPaciente);
			}
			mainApp.finalizaConsulta(this.mainApp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Botão ADICIONAR DOENÇA.
	@FXML
	private void addDoenca() {
		try {
			newDoenca(doencaAtual);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Botão ADICIONAR FARMACO.
	@FXML
	private void addFarmaco() {
		try {
			newFarmaco(farmacoAtual);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Botão ADICIONAR EXAME.
	@FXML
	private void addExame() {
		try {
			newExame(exameAtual);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Botão DELETAR DOENÇA selecionada.
	@FXML
	private void deleteDoenca() {
		try {
			if (doencaAtual != null) {
				int resp = JOptionPane.showConfirmDialog(null,
						"Deseja realmente deletar doença/sintomas/sinal: " + doencaAtual.getDescricao() + "?",
						"Deletar Doença", JOptionPane.YES_NO_OPTION);
				if (resp == 0) {
					delDoenca(doencaAtual);
					atualizaTabelas();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecione uma doença/sintomas/sinal para poder deletar",
						"Deletar Doença/Sintomas/Sinal", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Botão DELETAR FARMACO selecionada.
	@FXML
	private void deleteFarmaco() {
		try {
			if (farmacoAtual != null) {
				int resp = JOptionPane.showConfirmDialog(null,
						"Deseja realmente deletar farmaco " + farmacoAtual.getDescricao() + "?", "Deletar Farmaco",
						JOptionPane.YES_NO_OPTION);
				if (resp == 0) {
					delFarmaco(farmacoAtual);
					atualizaTabelas();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um farmaco para poder deletar", "Deletar Farmaco",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Botão DELETAR EXAME selecionada.
	@FXML
	private void deleteExame() {
		try {
			if (exameAtual != null) {
				int resp = JOptionPane.showConfirmDialog(null,
						"Deseja realmente deletar exame: " + exameAtual.getDescricao() + "?", "Deletar Exame",
						JOptionPane.YES_NO_OPTION);
				if (resp == 0) {
					delExame(exameAtual);
					atualizaTabelas();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Selecione um exame para poder deletar", "Deletar Exame",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void setMainApp(MainApp mainApp, Paciente pacienteSelecionado, Agenda agendaPaciente, Consulta consulta,
			Avaliacao av) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
		this.pacienteSelecionado = pacienteSelecionado;
		this.agendaPaciente = agendaPaciente;
		if (consulta != null) {
			this.consultaSelecionada = consulta;
		}
		if (av != null) {
			this.avAtual = av;
		}

		if (consulta == null && av == null) {
			this.mainApp.initEvolucao(this.pacienteSelecionado, AvaliacoesController.this, false);
		} else {
			this.mainApp.initEvolucao(this.pacienteSelecionado, AvaliacoesController.this, true);
		}
	}

	// Metodo para inserir DOENÇA na avaliação clinica solicitada
	private void newDoenca(Doenca d) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		if (verifyIdAvClinica(avAtual.getIdAvClinica())) {
			Object[] resp = informacoesDoenca();
			if (resp != null) {
				d = new Doenca(0, avAtual.getIdAvClinica(), resp[0].toString(), resp[1].toString(), resp[2].toString(),
						resp[3].toString());
				avDao.insertDoenca(d);
			}
		} else {
			avAtual.setIdAvClinica(avDao.insertAvClinica(avAtual.getIdAvaliacao()));
			avDao.updateAvaliacao(avAtual);
			Object[] resp = informacoesDoenca();
			if (resp != null) {
				d = new Doenca(0, avAtual.getIdAvClinica(), resp[0].toString(), resp[1].toString(), resp[2].toString(),
						resp[3].toString());
				avDao.insertDoenca(d);
			}
		}

		atualizaTabelas();
	}

	// Metodo para inserir FARMACO na avaliação clinica solicitada
	private void newFarmaco(Farmaco f) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		if (verifyIdAvClinica(avAtual.getIdAvClinica())) {
			Object[] resp = informacoesFarmaco();
			if (resp != null) {
				f = new Farmaco(0, avAtual.getIdAvClinica(), resp[0].toString(), resp[1].toString(),
						resp[2].toString());
				avDao.insertFarmaco(f);
			}
		} else {
			avAtual.setIdAvClinica(avDao.insertAvClinica(avAtual.getIdAvaliacao()));
			avDao.updateAvaliacao(avAtual);
			Object[] resp = informacoesFarmaco();
			if (resp != null) {
				f = new Farmaco(0, avAtual.getIdAvClinica(), resp[0].toString(), resp[1].toString(),
						resp[2].toString());
				avDao.insertFarmaco(f);
			}
		}

		atualizaTabelas();
	}

	// Metodo para inserir EXAME na avaliação clinica solicitada
	private void newExame(Exame e) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		if (verifyIdAvClinica(avAtual.getIdAvClinica())) {
			Object[] resp = informacoesExame();
			if (resp != null) {
				e = new Exame(0, avAtual.getIdAvClinica(), resp[0].toString(), resp[1].toString(), resp[2].toString());
				avDao.insertExame(e);
			}
		} else {
			avAtual.setIdAvClinica(avDao.insertAvClinica(avAtual.getIdAvaliacao()));
			avDao.updateAvaliacao(avAtual);
			Object[] resp = informacoesExame();
			if (resp != null) {
				e = new Exame(0, avAtual.getIdAvClinica(), resp[0].toString(), resp[1].toString(), resp[2].toString());
				avDao.insertExame(e);
			}
		}

		atualizaTabelas();
	}

	// Metodo para DELETAR DOENÇA selecionada na tabela.
	private void delDoenca(Doenca d) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		avDao.deleteDoenca(d);
	}

	// Metodo para DELETAR FARMACO selecionada na tabela.
	private void delFarmaco(Farmaco f) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		avDao.deleteFarmaco(f);
	}

	// Metodo para DELETAR EXAME selecionada na tabela.
	private void delExame(Exame e) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		avDao.deleteExame(e);
	}

	// Metodo para atualiza dados do paciente no TitledPane - DADOS DO PACIENTE
	private void atualizaDadosPaciente() {
		if (pacienteSelecionado != null) {
			txNomePac.setText("Nome: " + pacienteSelecionado.getNome());
			txIdade.setText("Data de Nascimento: " + DateUtil.format(pacienteSelecionado.getDataNasc()));

			if (pacienteSelecionado.getSexo().equalsIgnoreCase("m")) {
				txSexo.setText("Sexo: Masculino");
			} else {
				txSexo.setText("Sexo: Feminino");
			}
			if (medidasAtual != null) {
				txAltura.setText(String.valueOf(medidasAtual.getAltura()));
				txPeso.setText(String.valueOf(medidasAtual.getPesoAtual()));
			}

		} else {
			txNomePac.setText(null);
			txSexo.setText(null);
			txIdade.setText(null);
			txAltura.setText(null);
			txPeso.setText(null);
		}
	}

	// Metodo para abrir Fichas de avaliação apenas se houver alguma selecionada
	private void initFichas() {
		if (avAtual != null) {
			tabPAv.setVisible(false);
		} else {
			tabPAv.setVisible(true);
		}
	}

	// Metodo para pegar DOENÇA selecionada na tabela.
	private void showDoencaDetail(Doenca doenca) {
		// TODO Auto-greturn null;
		if (doenca != null) {
			doencaAtual = doenca;
		} else {
			doencaAtual = null;
		}
	}

	// Metodo para pegar FARMACO selecionado na tabela.
	private void showFarmacoDetail(Farmaco farmaco) {
		// TODO Auto-greturn null;
		if (farmaco != null) {
			farmacoAtual = farmaco;
		} else {
			farmacoAtual = null;
		}
	}

	// Metodo para pegar EXAME selecionado na tabela
	private void showExameDetail(Exame exame) {
		// TODO Auto-greturn null;
		if (exame != null) {
			exameAtual = exame;
		} else {
			exameAtual = null;
		}
	}

	// Metodo para adicionar LISTENER nas tabelas DOENÇA, FARMACO e EXAME.
	private void initListenerTabs() {
		tbDoencas.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDoencaDetail(newValue));
		tbFarmacos.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showFarmacoDetail(newValue));
		tbExames.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showExameDetail(newValue));
	}

	// Metodo para iniciar as colunas das tabelas de DOENÇA, FARMACO e EXAME.
	private void initCols() {
		// COLUNAS DA TABELA DOENÇA
		clDescDoenca.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
		clSituDoenca.setCellValueFactory(cellData -> cellData.getValue().situacaoProperty());
		clObsDoenca.setCellValueFactory(cellData -> cellData.getValue().obsProperty());
		clTipoDoenca.setCellValueFactory(cellData -> cellData.getValue().tipoDoencaProperty());

		// COLUNAS DA TABELA FARMACO
		clDescFarmaco.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
		clSubstAtivaFarmaco.setCellValueFactory(cellData -> cellData.getValue().subsAtivaProperty());
		clObsFarmaco.setCellValueFactory(cellData -> cellData.getValue().obsProperty());

		// COLUNAS DA TABELA EXAME
		clDescExames.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
		clVrReferidoExames.setCellValueFactory(cellData -> cellData.getValue().valorRefProperty());
		clInterpretacaoExames.setCellValueFactory(cellData -> cellData.getValue().interpretacaoProperty());
	}

	// Verifica se já existe AVALIAÇÃO CLINICA na AVALIAÇÃO selecionada.
	private boolean verifyIdAvClinica(int idAvClinica) {
		boolean flag = false;
		if (idAvClinica > 0) {
			flag = true;
		}
		return flag;
	}

	// Metodo para pegar informações da DOENÇA adicionada.
	private Object[] informacoesDoenca() {
		Object[] obj = new Object[4];
		Object[] situacao = { "Boa", "Ruim" };
		Object[] tipo = { "Doença", "Sintoma/Sinal" };
		obj[0] = JOptionPane.showInputDialog("Digite a descrição: ");
		obj[1] = JOptionPane.showInputDialog(null, "Escolha a situação", "Situação", JOptionPane.PLAIN_MESSAGE, null,
				situacao, "");
		obj[2] = JOptionPane.showInputDialog("Observação (Caso não tenha, deixei o campo em branco e click em OK)");
		obj[3] = JOptionPane.showInputDialog(null, "Escolha o tipo", "Tipo", JOptionPane.PLAIN_MESSAGE, null, tipo, "");
		if (obj[0] != null) {
			return obj;
		} else {
			JOptionPane.showMessageDialog(null, "Descrição não pode ser vazio", "Campo invalido",
					JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
	}

	// Metodo para pegar informações do Farmaco adicionada.
	private Object[] informacoesFarmaco() {
		Object[] obj = new Object[3];
		obj[0] = JOptionPane.showInputDialog("Digite a descrição: ");
		obj[1] = JOptionPane.showInputDialog("Digite a substancia ativa: ");
		obj[2] = JOptionPane.showInputDialog("Observação (Caso não tenha, deixei o campo em branco e click em OK)");
		if (obj[0] != null) {
			return obj;
		} else {
			JOptionPane.showMessageDialog(null, "Descrição não pode ser vazio", "Campo invalido",
					JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
	}

	// Metodo para pegar informações do Exame adicionado.
	private Object[] informacoesExame() {
		// TODO Auto-generated method stub
		Object[] obj = new Object[3];
		obj[0] = JOptionPane.showInputDialog("Digite a descrição: ");
		obj[1] = JOptionPane.showInputDialog("Digite o valor referido: ");
		obj[2] = JOptionPane.showInputDialog("Interpretaçaõ: ");
		if (obj[0] != null) {
			return obj;
		} else {
			JOptionPane.showMessageDialog(null, "Descrição não pode ser vazio", "Campo invalido",
					JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
	}

	// ATUALIZA as tabelas de DOENÇA, FARMACO e EXAME da avaliação clinica
	// selecionada.
	private void atualizaTabelas() {
		try {
			if (avAtual.getIdAvClinica() > 0) {
				tbDoencas.setItems(listaDoencas(avAtual.getIdAvClinica()));
				tbFarmacos.setItems(listaFarmacos(avAtual.getIdAvClinica()));
				tbExames.setItems(listaExames(avAtual.getIdAvClinica()));
			} else {
				tbDoencas.setItems(null);
				tbFarmacos.setItems(null);
				tbExames.setItems(null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Retorna lista de DOENÇAS da avaliação CLINICA selecionada.
	private ObservableList<Doenca> listaDoencas(int idAvClinica) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		ObservableList<Doenca> doencas = FXCollections.observableArrayList();
		doencas.setAll(avDao.listaDoencaAvClinica(idAvClinica));
		return doencas;
	}

	// Retorna lista de FARMACOS da avaliação CLINICA selecionada.
	private ObservableList<Farmaco> listaFarmacos(int idAvClinica) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		ObservableList<Farmaco> farmacos = FXCollections.observableArrayList();
		farmacos.setAll(avDao.listaFarmacoAvClinica(idAvClinica));
		return farmacos;
	}

	// Retorna lista de EXAMES da avaliação CLINICA selecionada.
	private ObservableList<Exame> listaExames(int idAvClinica) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		ObservableList<Exame> exames = FXCollections.observableArrayList();
		exames.setAll(avDao.listaExameAvClinica(idAvClinica));
		return exames;
	}

	// Metodo para atualizar os campos de acordo com a avaliação selecionada na
	// borda da ESQUERDA (EVOLUÇÃO).
	public void atualizaAvaliacao(Avaliacao av) {
		// TODO Auto-generated method stub
		if (av != null) {
			this.avAtual = av;
			ConsultaDao cDao = new ConsultaDao();
			AgendaDao aDao = new AgendaDao();
			AvaliacaoDao avDao = new AvaliacaoDao();
			try {
				this.consultaSelecionada = cDao.findConsulta(avAtual.getIdConsulta());
				System.out.println(consultaSelecionada.getIdAgenda());
				this.agendaPaciente = aDao.findConsultaAgend(consultaSelecionada.getIdAgenda());
				this.medidasAtual = avDao.findMedidas(avAtual.getIdAvFisica());

				if (medidasAtual != null) {
					atualizaMedidas(medidasAtual);
				}

				atualizaTabelas();
				atualizaDadosPaciente();

				if (avAtual.getIdAnamnese() > 0) {
					// metdo para inserir anamnese no HTLM EDITOR
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Metodo para atualizar campos de medidas antropometricas na tela.
	private void atualizaMedidas(MedidasAntropometricas m) {
		if (m != null) {
			txPesoAtual.setText(String.valueOf(m.getPesoAtual()));
			txPesoDesejado.setText(String.valueOf(m.getPesoDesejado()));
			txPesoUsual.setText(String.valueOf(m.getPesoUsual()));
			txTempoSobrepeso.setText(String.valueOf(m.getTempoSobrepeso()));
			txAltura2.setText(String.valueOf(m.getAltura()));
			txAltJoelho.setText(String.valueOf(m.getAltJoelho()));
			txTriceps.setText(String.valueOf(m.getTriceps()));
			txBiceps.setText(String.valueOf(m.getBiceps()));
			txSubescapular.setText(String.valueOf(m.getSubescapular()));
			txAxilarMedial.setText(String.valueOf(m.getAxilarMedial()));
			txToracica.setText(String.valueOf(m.getToracica()));
			txSupraEspinal.setText(String.valueOf(m.getSupraEspinal()));
			txSuprailiaca.setText(String.valueOf(m.getSupraIliaca()));
			txAbdome.setText(String.valueOf(m.getAbdome()));
			txCoxa.setText(String.valueOf(m.getCoxa()));
			txPanturrilha.setText(String.valueOf(m.getPanturrilhaDobra()));
			txBraco.setText(String.valueOf(m.getBraco()));
			txAntebraco.setText(String.valueOf(m.getAntebraco()));
			txPunho.setText(String.valueOf(m.getPunho()));
			txTorax.setText(String.valueOf(m.getTorax()));
			txCintura.setText(String.valueOf(m.getCintura()));
			txTornozelo.setText(String.valueOf(m.getTornozelo()));
			txAbdominal.setText(String.valueOf(m.getAbdominal()));
			txQuadril.setText(String.valueOf(m.getQuadril()));
			txGlutMax.setText(String.valueOf(m.getGlutMax()));
			txCoxaMax.setText(String.valueOf(m.getCoxaMax()));
			txPanturrilha2.setText(String.valueOf(m.getPanturrilhaPerimetro()));
			txCefalico.setText(String.valueOf(m.getCefalico()));
			txBiestiloide.setText(String.valueOf(m.getBiestiloide()));
			txBumeral.setText(String.valueOf(m.getBumeral()));
			txBfemural.setText(String.valueOf(m.getBfemural()));
		} else {
			txPesoAtual.setText(null);
			txPesoDesejado.setText(null);
			txPesoUsual.setText(null);
			txTempoSobrepeso.setText(null);
			txAltura2.setText(null);
			txAltJoelho.setText(null);
			txTriceps.setText(null);
			txBiceps.setText(null);
			txSubescapular.setText(null);
			txAxilarMedial.setText(null);
			txToracica.setText(null);
			txSupraEspinal.setText(null);
			txSuprailiaca.setText(null);
			txAbdome.setText(null);
			txCoxa.setText(null);
			txPanturrilha.setText(null);
			txBraco.setText(null);
			txAntebraco.setText(null);
			txPunho.setText(null);
			txTorax.setText(null);
			txCintura.setText(null);
			txTornozelo.setText(null);
			txAbdominal.setText(null);
			txQuadril.setText(null);
			txGlutMax.setText(null);
			txCoxaMax.setText(null);
			txPanturrilha2.setText(null);
			txCefalico.setText(null);
			txBiestiloide.setText(null);
			txBumeral.setText(null);
			txBfemural.setText(null);
		}
	}

}
