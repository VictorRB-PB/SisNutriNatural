/**
 * 
 */
package br.com.sisnutri.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import br.com.sisnutri.dao.AgendaDao;
import br.com.sisnutri.dao.PacienteDao;
import br.com.sisnutri.model.Agenda;
import br.com.sisnutri.model.Funcionario;
import br.com.sisnutri.model.Paciente;
import br.com.sisnutri.util.DateUtil;
import br.com.sisnutri.view.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

/**
 * @author Victor
 *
 */
public class AgendaController implements Initializable {

	MainApp mainApp;
	Funcionario funcionarioAtual;
	public Stage dialogStage;
	private Paciente pacSelecionado;
	private ObservableList<Agenda> listaAgenda;
	private Agenda consultaAgendSelecionada;

	private ObservableList<String> tipoConsultas = FXCollections.observableArrayList("CONSULTA", "RETORNO");
	private ObservableList<String> statusConsultas = FXCollections.observableArrayList("EM ABERTO", "CANCELADA",
			"REALIZADA");

	@FXML
	private TableColumn<Agenda, Number> clResponsavel;
	@FXML
	private TableColumn<Agenda, Number> clPaciente;
	@FXML
	private TableColumn<Agenda, LocalDate> clDataConsulta;
	@FXML
	private TableColumn<Agenda, String> clTipoConsulta;
	@FXML
	private TableColumn<Agenda, String> clStatus;
	@FXML
	private TableView<Agenda> tbAgenda;
	@FXML
	private TextField txDataConsulta;
	@FXML
	private CheckBox cDiaCorrente;
	@FXML
	private ComboBox<String> cbTipoConsulta;
	@FXML
	private ComboBox<String> cbStatusConsulta;
	@FXML
	private TextArea txObs;
	@FXML
	private TextField txPesquisar;
	@FXML
	private Text txPacienteSelecionado;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		initCols();
		initCboxe();
		tbAgenda.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showConsultaAgendDetail(newValue));
	}

	// Metodo para fechar janela
	@FXML
	private void close() {
		this.dialogStage.close();
	}

	// Botão cancelar consulta
	@FXML
	private void cancelConsulta() {
		cancelaConsultaAgend(consultaAgendSelecionada);
	}

	// Botão ALTERAR.
	@FXML
	private void saveAgenda() throws SQLException {
		if (consultaAgendSelecionada != null) {
			if (verifyData()) {
				try {

					Alert alert = createAlert(AlertType.CONFIRMATION, "Alterar",
							"Alterar agendamento do dia: "
									+ DateUtil.format(consultaAgendSelecionada.getDataConsulta()),
							"Deseja realmente alterar este agendamento para o dia: " + txDataConsulta.getText() + "?");

					ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
					ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
					alert.getButtonTypes().setAll(yesButton, noButton);
					Optional<ButtonType> result = alert.showAndWait();

					if (result.isPresent()) {
						if (result.get() == yesButton) {
							String oldDate = DateUtil.format(consultaAgendSelecionada.getDataConsulta());
							updateAgenda(consultaAgendSelecionada);
							Alert alert2 = createAlert(AlertType.INFORMATION, "Agenda",
									"Agendamento do dia: " +oldDate,
									"Atualizado com sucesso");
							alert2.show();
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			if (pacSelecionado != null) {
				if (verifyData()) {
					AgendaDao agDao = new AgendaDao();
					Agenda agendaVerificada = agDao.verificaAgenda(pacSelecionado.getIdPac(),
							verifyDateAgendamento(txDataConsulta.getText()));
					cbStatusConsulta.getSelectionModel().select(0);
					if (agendaVerificada != null) {
						updateAgenda(agendaVerificada);
					} else {
						newAgend(consultaAgendSelecionada);
					}
				}
			} else {
				Alert alert = createAlert(AlertType.ERROR, "Agenda", "Agendamento invalido",
						"Selecione um paciente para agendar consulta");
				alert.show();
			}
		}

	}

	// CheckBox DIA CORRENTE
	@FXML
	private void ckDiaCorrente() {
		if (pacSelecionado != null) {
			if (cDiaCorrente.isSelected()) {
				Date diaCorrente = new Date(System.currentTimeMillis());
				LocalDate selected = LocalDate.parse(diaCorrente.toString());
				txDataConsulta.setText(DateUtil.format(selected));
			}
		} else {
			cDiaCorrente.setSelected(false);
			Alert alert = createAlert(AlertType.INFORMATION, "Agenda", "Agendamento",
					"Selecione um paciente para adicionar dia correnta no agendamento");
			alert.show();

		}
	}

	// TextField para pesquisar consultas AGENDADAS.
	@FXML
	private void findPac() {
		if (!txPesquisar.getText().equals("")) {
			try {
				tbAgenda.setItems(pesquisarAgend());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			atualizaTabela();
		}
	}

	// Metodo para visualizar agendamento selecionado, campos vazios se nao
	// tiver agendamento selecionado.
	private void showConsultaAgendDetail(Agenda aged) {
		// TODO Auto-generated method stub
		if (aged != null) {
			// Preenche os campos com os valores do agendamento selecionado
			txDataConsulta.setText(DateUtil.format(aged.getDataConsulta()));
			txObs.setText(aged.getObs());
			consultaAgendSelecionada = aged;
			PacienteDao pacDao = new PacienteDao();
			try {
				pacSelecionado = pacDao.findPac(aged.getIdPaciente());
				txPacienteSelecionado.setText(pacSelecionado.getNome() + " - CPF: " + pacSelecionado.getCpf());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			caseTipoStatusConsulta(aged);
			cDiaCorrente.setSelected(false);

		} else {
			txDataConsulta.setText(null);
			txObs.setText(null);
			cbTipoConsulta.getSelectionModel().clearSelection();
			cbStatusConsulta.getSelectionModel().clearSelection();
			consultaAgendSelecionada = null;
			pacSelecionado = null;
			txPacienteSelecionado.setText(null);
			cDiaCorrente.setText(null);
		}
	}

	// Metodo para pegar informações na tela e ADICIONAR novo AGENDAMENTO.
	private void newAgend(Agenda newAgend) {
		// TODO Auto-generated method stub

		int idFunc = funcionarioAtual.getIdFunc();
		int idPac = pacSelecionado.getIdPac();
		LocalDate dataNasc = DateUtil.parse(txDataConsulta.getText());
		String tipoConsulta = cbTipoConsulta.getSelectionModel().getSelectedItem().toString();
		String statusConsulta = cbStatusConsulta.getSelectionModel().getSelectedItem().toString();
		String obs = txObs.getText();

		newAgend = new Agenda(0, idFunc, idPac, dataNasc, tipoConsulta, statusConsulta, obs);
		try {
			AgendaDao agendDao = new AgendaDao();
			agendDao.insert(newAgend);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		atualizaTabela();

		Alert alert = createAlert(AlertType.INFORMATION, "Agenda",
				"Agendamento para o dia: " + DateUtil.format(newAgend.getDataConsulta()), "Adicionado com sucesso");
		alert.show();

	}

	// Metodo para pegar informações na tela e ATUALIZAR agendamento.
	private void updateAgenda(Agenda agend) throws SQLException {
		Agenda agendTemp = agend;

		agendTemp.setDataConsulta(DateUtil.parse(txDataConsulta.getText()));
		agendTemp.setIdFuncionario(funcionarioAtual.getIdFunc());
		agendTemp.setTipoConsulta(cbTipoConsulta.getSelectionModel().getSelectedItem().toString());
		agendTemp.setStatusConsulta(cbStatusConsulta.getSelectionModel().getSelectedItem().toString());
		agendTemp.setObs(txObs.getText());

		AgendaDao agendDao = new AgendaDao();
		agendDao.update(agendTemp);

		atualizaTabela();

	}

	// Metodo para CANCELAR consulta agendada.
	private void cancelaConsultaAgend(Agenda aged) {
		if (aged != null) {
			Alert alert = createAlert(AlertType.CONFIRMATION, "Cancelar",
					"Cancelar consulta: " + DateUtil.format(consultaAgendSelecionada.getDataConsulta()),
					"Deseja realmente cancelar consulta do paciente " + pacSelecionado.getNome() + ", CPF: "
							+ pacSelecionado.getCpf() + "?");

			ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
			ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(yesButton, noButton);

			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent()) {
				if (result.get() == yesButton) {
					try {
						aged.setStatusConsulta("CANCELADA");
						AgendaDao agendDao = new AgendaDao();
						agendDao.cancelCons(aged);
						atualizaTabela();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			Alert alert = createAlert(AlertType.INFORMATION, "Agenda", "Cancelamento invalido",
					"Selecione um agendamento para cancelar");
			alert.show();
		}

	}

	// Metodo para PESQUISAR agendamento na tabela por CPF do paciente
	private ObservableList<Agenda> pesquisarAgend() throws SQLException {
		// TODO Auto-generated method stub
		ObservableList<Agenda> listaEncontrados = FXCollections.observableArrayList();
		ObservableList<Agenda> listaAgenda = getListaConsultasAgendadas();
		PacienteDao pacDao = new PacienteDao();
		Paciente pac = pacDao.pesquisarPorCpf(txPesquisar.getText());
		if (pac != null) {
			for (Agenda agend : listaAgenda) {
				if (pac.getIdPac() == agend.getIdPaciente()) {
					listaEncontrados.add(agend);
				}
			}

		}

		return listaEncontrados;
	}

	// Metodo para verificar se os campos estão preenchidos.
	private boolean verifyData() {
		String errorMessage = "";

		if (txDataConsulta.getText() == null || txDataConsulta.getText().length() == 0) {
			errorMessage += "Data inválida, preencha corretamente no formato: dd/MM/AAAA\n";
		}

		if (cbTipoConsulta.getSelectionModel().getSelectedItem() == null
				|| cbTipoConsulta.getSelectionModel().getSelectedItem().length() == 0) {
			errorMessage += "Informe o tipo de consulta\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Mostra a mensagem de erro.
			Alert alert = createAlert(AlertType.ERROR, "Campos Inválidos", "Por favor, corrija os campos inválidos",
					errorMessage);
			alert.showAndWait();
			return false;
		}

	}

	// Metodo para pegar o controller do MAINAPP.
	public void setMainApp(MainApp mainApp, Funcionario funcAtual, Paciente pacSelecionado, Stage dialogStage) {
		this.mainApp = mainApp;
		this.funcionarioAtual = funcAtual;
		this.dialogStage = dialogStage;
		if (pacSelecionado != null) {
			this.pacSelecionado = pacSelecionado;
			txPacienteSelecionado.setText(pacSelecionado.getNome());
		}
		try {
			tbAgenda.setItems(getListaConsultasAgendadas());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Atualiza a tabela de agenda.
	private void atualizaTabela() {
		int index = tbAgenda.getSelectionModel().getSelectedIndex();
		try {
			tbAgenda.setItems(getListaConsultasAgendadas());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tbAgenda.getSelectionModel().select(index);
		cDiaCorrente.setSelected(false);

	}

	// Metodo para retorna uma lista de consultas agendadas.
	private ObservableList<Agenda> getListaConsultasAgendadas() throws SQLException {
		AgendaDao agedDao = new AgendaDao();
		listaAgenda = FXCollections.observableArrayList();
		listaAgenda.setAll(agedDao.listaAged());
		return listaAgenda;
	}

	// Metodo para atualizar informações de TIPO DE CONSULTA e STATUS CONSULTA
	// nas comboboxes.
	private void caseTipoStatusConsulta(Agenda aged) {
		String tipoConsulta = aged.getTipoConsulta();
		String statusConsulta = aged.getStatusConsulta();

		switch (tipoConsulta) {
		case "CONSULTA": {
			cbTipoConsulta.setValue("CONSULTA");
			break;
		}
		case "RETORNO": {
			cbTipoConsulta.setValue("RETORNO");
			break;
		}
		}

		switch (statusConsulta) {
		case "EM ABERTO": {
			cbStatusConsulta.setValue("EM ABERTO");
			break;
		}
		case "CANCELADA": {
			cbStatusConsulta.setValue("CANCELADA");
			break;
		}
		case "REALIZADA": {
			cbStatusConsulta.setValue("REALIZADA");
			break;
		}
		}
	}

	// Metodo para iniciar as colunas da TableView
	private void initCols() {
		clResponsavel.setCellValueFactory(cellData -> cellData.getValue().idFuncionarioProperty());
		clPaciente.setCellValueFactory(cellData -> cellData.getValue().idPacienteProperty());
		clDataConsulta.setCellValueFactory(cellData -> cellData.getValue().dataConsultaProperty());
		clTipoConsulta.setCellValueFactory(cellData -> cellData.getValue().tipoConsultaProperty());
		clStatus.setCellValueFactory(cellData -> cellData.getValue().statusConsultaProperty());
	}

	// Metodo que inicia e preenche as comboboxes TIPO CONSULTA e STATUS
	// CONSULTA.
	private void initCboxe() {
		cbTipoConsulta.setItems(tipoConsultas);
		cbStatusConsulta.setItems(statusConsultas);
	}

	// Metodo para retornar um ALERTA de acordo com o tipo de alerta desejado
	// (Confirmation, Error, Information, None, Warning)
	private Alert createAlert(AlertType alertType, String title, String headerText, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.initOwner(MainApp.getStage());
		return alert;
	}

	// Metodo para transformar o dia do agendamento em DateSQL
	private Date verifyDateAgendamento(String dataAgendamento) {
		LocalDate dataLocal = DateUtil.parse(dataAgendamento);
		Date dataSQL = Date.valueOf(dataLocal);
		return dataSQL;
	}

}
