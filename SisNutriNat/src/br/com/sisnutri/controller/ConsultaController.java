package br.com.sisnutri.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.sisnutri.dao.AgendaDao;
import br.com.sisnutri.dao.AvaliacaoDao;
import br.com.sisnutri.dao.ConsultaDao;
import br.com.sisnutri.dao.PacienteDao;
import br.com.sisnutri.model.Agenda;
import br.com.sisnutri.model.Avaliacao;
import br.com.sisnutri.model.Consulta;
import br.com.sisnutri.model.Funcionario;
import br.com.sisnutri.model.Paciente;
import br.com.sisnutri.view.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ConsultaController implements Initializable {

	MainApp mainApp;
	Paciente pacienteSelecionado;
	private Agenda agendaPaciente;
	private Funcionario funcionarioAtual;
	private Stage dialogStage;
	private ObservableList<Agenda> listaConsultasAgendada;

	@FXML
	private Text txNomePac;
	@FXML
	private Text txTipoConsulta;
	@FXML
	private TableView<Paciente> tbPac;
	@FXML
	private TableColumn<Paciente, String> clNome;
	@FXML
	private TableColumn<Paciente, String> clSexo;
	@FXML
	private TableColumn<Paciente, String> clCpf;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		initCols();

		tbPac.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> ShowPacienteDetail(newValue));

	}

	// Botão Iniciar Consulta.
	@FXML
	private void newConsulta() {
		if (pacienteSelecionado != null) {
			verifyConsulta();
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um paciente para ser assistido", "Iniciar consulta",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// Botão fechar
	@FXML
	private void close() {
		dialogStage.close();
	}

	// Metodo para visulizar dados da agenda do paciente selecionado.
	private void ShowPacienteDetail(Paciente pac) {
		// TODO Auto-generated method stub
		if (pac != null) {
			pacienteSelecionado = pac;
			txNomePac.setText(pac.getNome());
			getIdAgenda(pacienteSelecionado.getIdPac());
			txTipoConsulta.setText(agendaPaciente.getTipoConsulta());
		} else {
			pacienteSelecionado = null;
			txNomePac.setText(null);
			agendaPaciente = null;
			txTipoConsulta.setText(null);
		}
	}

	// Metodo para iniciar as colunas da tabela
	private void initCols() {
		clNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		clSexo.setCellValueFactory(cellData -> cellData.getValue().sexoProperty());
		clCpf.setCellValueFactory(cellData -> cellData.getValue().cpfProperty());
	}

	// Atualiza a tabela de pacientes agendados.
	private void atualizaTabela() {
		int index = tbPac.getSelectionModel().getSelectedIndex();
		try {
			tbPac.setItems(getListaConsultasAgendadas());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tbPac.getSelectionModel().select(index);
	}

	public void setMainApp(MainApp mainApp, Funcionario funcAtual, Stage dialogStage) {
		this.mainApp = mainApp;
		this.funcionarioAtual = funcAtual;
		this.dialogStage = dialogStage;
		atualizaTabela();
	}

	// Metodo para retornar Pacientes agendados para o dia corrente.
	private ObservableList<Paciente> getListaConsultasAgendadas() throws SQLException {
		AgendaDao agedDao = new AgendaDao();
		listaConsultasAgendada = FXCollections.observableArrayList();
		ObservableList<Paciente> listaPac = FXCollections.observableArrayList();
		listaConsultasAgendada.setAll(agedDao.listaConsultDia());

		if (listaConsultasAgendada.size() > 0) {
			PacienteDao pacDao = new PacienteDao();
			for (Agenda agend : listaConsultasAgendada) {
				Paciente p = pacDao.findPac(agend.getIdPaciente());
				listaPac.add(p);
			}
			return listaPac;
		}
		return null;
	}

	// Metodo para pegar o ID da consulta agendada do paciente selecionado.
	private void getIdAgenda(int idPac) {
		if (listaConsultasAgendada.size() > 0) {
			for (Agenda agend : listaConsultasAgendada) {
				if (agend.getIdPaciente() == idPac) {
					agendaPaciente = agend;
				}
			}
		}
	}

	// Metodo para verifica se é consulta ou retorno e executar o fluxo de
	// eventos correspondente.
	private void verifyConsulta() {
		// TODO Auto-generated method stub
		if (agendaPaciente.getTipoConsulta().equalsIgnoreCase("CONSULTA")) {
			String res = exibeMsgTecnica();
			try {
				if (res != null) {
					verifyTecnica(res.toString());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (agendaPaciente.getTipoConsulta().equalsIgnoreCase("RETORNO")) {
			String res = exibeMsgRetorno();
			if (res != null) {
				if (res.equalsIgnoreCase("Nova Consulta")) {
					String resp = exibeMsgTecnica();
					if (resp != null) {
						try {
							verifyTecnica(resp.toString());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					this.mainApp.initAvaliacaoNutricional(pacienteSelecionado, agendaPaciente, null, null);
					dialogStage.close();
				}
			}
		}

	}

	// Metodo que verifica a tecnica a ser aplicada para a avaliação
	// direcionada.
	private void verifyTecnica(String tecnica) throws SQLException {

		if (tecnica != null) {

			ConsultaDao consultaDao = new ConsultaDao();
			AvaliacaoDao avDao = new AvaliacaoDao();

			switch (tecnica) {
			case "Atleta": {
				Consulta consulta = new Consulta(0, funcionarioAtual.getCrn(), agendaPaciente.getIdConsultaAgendada(),
						0, tecnica);
				consulta.setIdConsulta(consultaDao.insert(consulta));

				Avaliacao av = new Avaliacao(0, consulta.getIdConsulta(), 0, 0, 0, null);
				av.setIdAvaliacao(avDao.insertAvaliacao(av));
				consulta.setIdAvaliacao(av.getIdAvaliacao());
				consultaDao.update(consulta);
				this.mainApp.initAvaliacaoNutricional(pacienteSelecionado, agendaPaciente, consulta, av);
				dialogStage.close();
				break;
			}
			case "Obese": {

				break;
			}
			case "Eutrófico": {

				break;
			}
			}
		}

	}

	// Metodo para exibir mensagem de tecnica de avaliação nutricional.
	private String exibeMsgTecnica() {
		Object[] opcoes = { "Atleta", "Obeso", "Eutrófico" };
		Object res = JOptionPane.showInputDialog(null, "Escolha a técnica", "Técnica", JOptionPane.PLAIN_MESSAGE, null,
				opcoes, "");
		if (res != null) {
			return res.toString();
		} else {
			return null;
		}
	}

	// Metodo para exibir mensagem para consultas do tipo "RETORNO".
	private String exibeMsgRetorno() {
		Object[] opcoes = { "Nova Consulta", "Visualizar Evolução" };
		Object res = JOptionPane.showInputDialog(null, "Escolha tipo de consulta", "Consulta",
				JOptionPane.PLAIN_MESSAGE, null, opcoes, "");

		if (res != null) {
			return res.toString();
		} else {
			return null;
		}
	}
}
