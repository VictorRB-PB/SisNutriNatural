/**
 * 
 */
package br.com.sisnutri.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import br.com.sisnutri.dao.AgendaDao;
import br.com.sisnutri.dao.AvaliacaoDao;
import br.com.sisnutri.dao.ConsultaDao;
import br.com.sisnutri.model.Agenda;
import br.com.sisnutri.model.Anamnese;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
	private boolean flagVisualizarEvolucao;

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
	TabPane tabPConsulta;
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
	@FXML
	Tab tabMedidas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initCols();
		initListenerTabs();

	}

	// Botão FINALIZAR CONSULTA
	@FXML
	private void finishConsulta() {
		try {
			Alert alert = createAlert(AlertType.CONFIRMATION, "Consulta", "Finalizar consulta",
					"Deseja realmente finalizar consulta?");

			ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
			ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(yesButton, noButton);
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent()) {
				if (result.get() == yesButton) {
					if (agendaPaciente != null) {
						agendaPaciente.setStatusConsulta("REALIZADA");
						AgendaDao a = new AgendaDao();
						a.update(agendaPaciente);

						Alert alert2 = createAlert(AlertType.INFORMATION, "Consulta", "Fanalizando consulta",
								"Consulta finalizada com sucesso");
						Optional<ButtonType> result2 = alert2.showAndWait();
						if (result2.isPresent()) {
							if (result2.get() == ButtonType.OK) {
								mainApp.setIsevaluation(false);
								mainApp.finalizaConsulta(this.mainApp);
							}
						}

					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Botão Adicionar Medidas Antropometricas
	@FXML
	private void addMedidas() {
		tabPAv.getSelectionModel().select(tabAvFisica);
		tabPConsulta.getSelectionModel().select(tabMedidas);
	}

	// Botão ADICIONAR DOENÇA.
	@FXML
	private void addDoenca() {
		try {
			createDoencaDialog(doencaAtual);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Botão ADICIONAR FARMACO.
	@FXML
	private void addFarmaco() {
		try {
			createFarmacoDialog(farmacoAtual);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Botão ADICIONAR EXAME.
	@FXML
	private void addExame() {
		try {
			createExameDialog(exameAtual);
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
				Alert alert = createAlert(AlertType.CONFIRMATION, "Doença - Sintomas/Sinal", "Deletar doença",
						"Deseja realmente deletar doença/sintomas/sinal: " + doencaAtual.getDescricao() + "?");

				ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
				ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(yesButton, noButton);
				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent()) {
					if (result.get() == yesButton) {
						delDoenca(doencaAtual);
						atualizaTabelas();
					}
				}
			} else {
				Alert alert = createAlert(AlertType.ERROR, "Doença - Sintomas/Sinal",
						"Doença - Sintomas/Sinal invalido", "Selecione uma doença/sintomas/sinal para poder deletar");
				alert.show();
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
				Alert alert = createAlert(AlertType.CONFIRMATION, "Farmaco", "Deletar farmaco",
						"Deseja realmente deletar farmaco: " + farmacoAtual.getDescricao() + "?");

				ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
				ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(yesButton, noButton);
				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent()) {
					if (result.get() == yesButton) {
						delFarmaco(farmacoAtual);
						atualizaTabelas();
					}
				}
			} else {
				Alert alert = createAlert(AlertType.ERROR, "Farmaco", "Farmaco invalido",
						"Selecione um farmaco para poder deletar");
				alert.show();
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
				Alert alert = createAlert(AlertType.CONFIRMATION, "Exames", "Deletar exame",
						"Deseja realmente deletar exame: " + exameAtual.getDescricao() + "?");

				ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
				ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(yesButton, noButton);
				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent()) {
					if (result.get() == yesButton) {
						delExame(exameAtual);
						atualizaTabelas();
					}
				}
			} else {
				Alert alert = createAlert(AlertType.ERROR, "Exame", "Exame invalido",
						"Selecione um exame para poder deletar");
				alert.show();
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
		this.consultaSelecionada = consulta;
		this.avAtual = av;

		this.flagVisualizarEvolucao = false;
		if (avAtual.getIdAvFisica() > 0 || avAtual.getIdAvClinica() > 0) {
			try {
				this.mainApp.initEvolucao(this.pacienteSelecionado, AvaliacoesController.this, true);
				atualizaDadosDasAvaliacoes(avAtual);
				flagVisualizarEvolucao = true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.mainApp.initEvolucao(this.pacienteSelecionado, AvaliacoesController.this, false);
		}

		atualizaDadosPaciente();
	}

	// Metodo para atualizar dados das avaliações se existir
	public void atualizaDadosDasAvaliacoes(Avaliacao av) throws SQLException {
		// TODO Auto-generated method stub
		this.avAtual = av;
		AvaliacaoDao avDao = new AvaliacaoDao();
		ConsultaDao cDao = new ConsultaDao();
		AgendaDao agDao = new AgendaDao();

		this.consultaSelecionada = cDao.findConsulta(avAtual.getIdConsulta());
		this.agendaPaciente = agDao.findConsultaAgend(this.consultaSelecionada.getIdAgenda());

		if (avAtual.getIdAvFisica() > 0) {
			MedidasAntropometricas m = avDao.findMedidas(avAtual.getIdAvFisica());
			atualizaMedidas(m);
		}
		if (avAtual.getIdAnamnese() > 0) {
			Anamnese a = avDao.findAnamnese(avAtual.getIdAnamnese());
			htEditor.setHtmlText(a.getDescricao());
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

	// Metodo para retornar um ALERTA de acordo com o tipo de alerta desejado
	// (Confirmation, Error, Information, None, Warning)
	private Alert createAlert(AlertType alertType, String title, String headerText, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.initOwner(this.mainApp.getStage());
		return alert;
	}

	// Metodo de criar um dialog para inserir DOENÇA - SINTOMAS/SINAL na
	// avaliação clinica
	// solicitada, cria uma nova
	// avaliação clinica se não existir aviliação clinica pra exame atual
	private void createDoencaDialog(Doenca d) throws SQLException {

		Dialog<HashMap<String, String>> dialogDoenca = new Dialog<>();
		dialogDoenca.setTitle("Doença - Sintomas/Sinal");
		dialogDoenca.setHeaderText("Preencha os campos abaixo sobre doença ou sintoma/sinal a ser adicionado");
		// Seta icone
		dialogDoenca.initOwner(this.mainApp.getStage());

		// Butões ADICIONAR e CANCELAR
		ButtonType addButtonType = new ButtonType("Adicionar", ButtonData.YES);
		ButtonType cancelButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		dialogDoenca.getDialogPane().getButtonTypes().setAll(addButtonType, cancelButtonType);

		// Cria labels e comboboxes para o grid de farmaco
		TextField descricao = new TextField();
		ComboBox<String> situacao = new ComboBox<>();
		TextField observacao = new TextField();
		ComboBox<String> tipo = new ComboBox<>();

		// Adiciona valores nos comboboxe situação e tipo
		situacao.getItems().setAll("Boa", "Ruim");
		tipo.getItems().setAll("Doença", "Sintoma/Sinal");

		descricao.setPromptText("Descrição");
		situacao.setPromptText("Situação");
		observacao.setPromptText("Observação");
		tipo.setPromptText("Tipo da doença ou sintoma/sinal");

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(descricao, 0, 0);
		grid.add(situacao, 1, 0);
		grid.add(observacao, 0, 1);
		grid.add(tipo, 1, 1);

		// Habilita/Desabilita botão adicionar se descrição for preenchida
		Node addButton = dialogDoenca.getDialogPane().lookupButton(addButtonType);
		addButton.setDisable(true);

		// Se descrição não estiver vazio habilita o botão adicionar
		descricao.textProperty().addListener((observable, oldValue, newValue) -> {
			addButton.setDisable(newValue.trim().isEmpty());
		});

		// Adiciona grid criado ao dialog
		dialogDoenca.getDialogPane().setContent(grid);

		// Converte o resultado do dialogDoenca em HashMap quando o botão
		// adicionar é clicado
		dialogDoenca.setResultConverter(newButton -> {
			if (newButton == addButtonType) {
				HashMap<String, String> result = new HashMap<>();
				result.put("descricao", descricao.getText());
				result.put("situacao", situacao.getValue());
				result.put("obs", observacao.getText());
				result.put("tipo", tipo.getValue());
				return result;
			}
			return null;
		});

		// Seta configurações do stage principal no dialog doença
		dialogDoenca.initOwner(this.mainApp.getStage());

		// Variavel para receber os resultados do dialogDoenca
		Optional<HashMap<String, String>> result = dialogDoenca.showAndWait();

		if (result.isPresent()) {
			HashMap<String, String> resultado = result.get();
			AvaliacaoDao avDao = new AvaliacaoDao();
			if (verifyIdAvClinica(avAtual.getIdAvClinica())) {
				d = new Doenca(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("situacao"),
						resultado.get("obs"), resultado.get("tipo"));
				avDao.insertDoenca(d);
			} else {
				avAtual.setIdAvClinica(avDao.insertAvClinica(avAtual.getIdAvaliacao()));
				avDao.updateAvaliacao(avAtual);
				d = new Doenca(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("situacao"),
						resultado.get("obs"), resultado.get("tipo"));
				avDao.insertDoenca(d);
			}
			atualizaTabelas();
		}
	}

	// Metodo de criar um dialog para inserir FARMACO na avaliação clinica
	// solicitada, cria uma nova
	// avaliação clinica se não existir aviliação clinica pra exame atual
	private void createFarmacoDialog(Farmaco f) throws SQLException {

		Dialog<HashMap<String, String>> dialogFarmaco = new Dialog<>();
		dialogFarmaco.setTitle("Farmaco");
		dialogFarmaco.setHeaderText("Preencha os campos abaixo sobre o farmaco a ser adicionado");
		// Seta icone
		dialogFarmaco.initOwner(this.mainApp.getStage());

		// Butões ADICIONAR e CANCELAR
		ButtonType addButtonType = new ButtonType("Adicionar", ButtonData.YES);
		ButtonType cancelButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		dialogFarmaco.getDialogPane().getButtonTypes().setAll(addButtonType, cancelButtonType);

		// Cria labels e comboboxes para o grid de farmaco
		TextField descricao = new TextField();
		TextField subsAtiva = new TextField();
		TextField observacao = new TextField();

		descricao.setPromptText("Descrição do farmaco");
		subsAtiva.setPromptText("Substancia ativa");
		observacao.setPromptText("Observação");

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 80, 10, 10));
		grid.add(descricao, 0, 0);
		grid.add(subsAtiva, 1, 0);
		grid.add(observacao, 0, 1);

		// Habilita/Desabilita botão adicionar se descrição for preenchida
		Node addButton = dialogFarmaco.getDialogPane().lookupButton(addButtonType);
		addButton.setDisable(true);

		// Se descrição não estiver vazio habilita o botão adicionar
		descricao.textProperty().addListener((observable, oldValue, newValue) -> {
			addButton.setDisable(newValue.trim().isEmpty());
		});

		// Adiciona grid criado ao dialog
		dialogFarmaco.getDialogPane().setContent(grid);

		// Converte o resultado do dialogFarmaco em HashMap quando o botão
		// adicionar é clicado
		dialogFarmaco.setResultConverter(newButton -> {
			if (newButton == addButtonType) {
				HashMap<String, String> result = new HashMap<>();
				result.put("descricao", descricao.getText());
				result.put("subsAtiva", subsAtiva.getText());
				result.put("obs", observacao.getText());
				return result;
			}
			return null;
		});

		// Seta configurações do stage principal no dialog farmaco
		dialogFarmaco.initOwner(this.mainApp.getStage());

		// Variavel para receber os resultados do farmacoDialog
		Optional<HashMap<String, String>> result = dialogFarmaco.showAndWait();

		if (result.isPresent()) {
			HashMap<String, String> resultado = result.get();
			AvaliacaoDao avDao = new AvaliacaoDao();
			if (verifyIdAvClinica(avAtual.getIdAvClinica())) {
				f = new Farmaco(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("subsAtiva"),
						resultado.get("obs"));
				avDao.insertFarmaco(f);
			} else {
				avAtual.setIdAvClinica(avDao.insertAvClinica(avAtual.getIdAvaliacao()));
				avDao.updateAvaliacao(avAtual);
				f = new Farmaco(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("subsAtiva"),
						resultado.get("obs"));
				avDao.insertFarmaco(f);
			}
			atualizaTabelas();
		}
	}

	// Metodo de criar um dialog para inserir EXAME na avaliação clinica
	// solicitada, cria uma nova
	// avaliação clinica se não existir aviliação clinica pra exame atual
	private void createExameDialog(Exame e) throws SQLException {

		Dialog<HashMap<String, String>> dialogExame = new Dialog<>();
		dialogExame.setTitle("Exame");
		dialogExame.setHeaderText("Preencha os campos abaixo sobre o exame a ser adicionado");
		// Seta icone
		dialogExame.initOwner(this.mainApp.getStage());

		// Butões ADICIONAR e CANCELAR
		ButtonType addButtonType = new ButtonType("Adicionar", ButtonData.YES);
		ButtonType cancelButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		dialogExame.getDialogPane().getButtonTypes().setAll(addButtonType, cancelButtonType);

		// Cria labels e comboboxes para o grid de exames
		TextField descricao = new TextField();
		TextField valorRef = new TextField();
		TextField interpretacao = new TextField();

		descricao.setPromptText("Descrição do exame");
		valorRef.setPromptText("Valor referido do exame");
		interpretacao.setPromptText("Interpretação do exame");

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 80, 10, 10));
		grid.add(descricao, 0, 0);
		grid.add(valorRef, 1, 0);
		grid.add(interpretacao, 0, 1);

		// Habilita/Desabilita botão adicionar se descrição for preenchida
		Node addButton = dialogExame.getDialogPane().lookupButton(addButtonType);
		addButton.setDisable(true);

		// Se descrição não estiver vazio habilita o botão adicionar
		descricao.textProperty().addListener((observable, oldValue, newValue) -> {
			addButton.setDisable(newValue.trim().isEmpty());
		});

		// Adiciona grid criado ao dialog
		dialogExame.getDialogPane().setContent(grid);

		// Converte o resultado do dialogExame em HashMap quando o botão
		// adicionar é clicado
		dialogExame.setResultConverter(newButton -> {
			if (newButton == addButtonType) {
				HashMap<String, String> result = new HashMap<>();
				result.put("descricao", descricao.getText());
				result.put("valorReferido", valorRef.getText());
				result.put("interpretacao", interpretacao.getText());
				return result;
			}
			return null;
		});

		// Seta configurações do stage principal no dialog exames
		dialogExame.initOwner(this.mainApp.getStage());

		// Variavel para receber os resultados do exameDialog
		Optional<HashMap<String, String>> result = dialogExame.showAndWait();

		if (result.isPresent()) {
			HashMap<String, String> resultado = result.get();
			AvaliacaoDao avDao = new AvaliacaoDao();
			if (verifyIdAvClinica(avAtual.getIdAvClinica())) {
				e = new Exame(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("valorReferido"),
						resultado.get("interpretacao"));
				avDao.insertExame(e);
			} else {
				avAtual.setIdAvClinica(avDao.insertAvClinica(avAtual.getIdAvaliacao()));
				avDao.updateAvaliacao(avAtual);
				e = new Exame(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("valorReferido"),
						resultado.get("interpretacao"));
				avDao.insertExame(e);
			}
			atualizaTabelas();
		}
	}

}
