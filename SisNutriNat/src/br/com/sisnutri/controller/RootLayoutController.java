package br.com.sisnutri.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import br.com.sisnutri.model.Funcionario;
import br.com.sisnutri.reports.ImprimirRelatorioMensal;
import br.com.sisnutri.view.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import net.sf.jasperreports.engine.JRException;

public class RootLayoutController implements Initializable {

	MainApp mainApp;
	Funcionario funcAtual;

	@FXML
	private Text txLogado;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	// Menu item Relatorio Mensal do Mes Atual
	@FXML
	private void handlePrintRelatorioMesAtual(){
		try {
			new ImprimirRelatorioMensal().showReport();
		} catch (ClassNotFoundException | JRException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	// Menu item SAIR.
	@FXML
	private void handleExit() {
		if (this.mainApp.isEvaluation()) {
			Alert alert = createAlert(AlertType.INFORMATION, "Sair", "Ação invalida",
					"Finalize a consulta para poder fechar o programa");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(this.mainApp.getStage());
			alert.showAndWait();
		} else {
			Alert alert = createAlert(AlertType.CONFIRMATION, "Sair", "Finalizar sistema",
					"Deseja realmente sair do programa?");

			ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
			ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(yesButton, noButton);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent()) {
				if (result.get() == yesButton) {
					System.exit(0);
				}
			}
		}
	}

	// Menu item CADASTRO DE FUNCIONARIO
	@FXML
	private void cadastroFunc() {
		if (funcAtual.getTipoFunc().equalsIgnoreCase("Nutricionista")) {
			if (this.mainApp.isEvaluation()) {
				Alert alert = createAlert(AlertType.INFORMATION, "Cadastro de Funcionario", "Ação invalida",
						"Finalize a consulta para utilizar o cadastro de funcionario");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.initOwner(this.mainApp.getStage());
				alert.showAndWait();
			} else {
				this.mainApp.initCadastroFunc(funcAtual);
			}
		} else {
			Alert alert = createAlert(AlertType.ERROR, "Cadastro de Funcionario", "Ação invalida",
					"Apenas o Nutricionista e Administrador podem cadastrar funcionarios");
			alert.show();
		}

	}

	// Botão CADASTRO DE PACIENTE.
	@FXML
	private void cadastroPac() {
		if (this.mainApp.isEvaluation()) {
			Alert alert = createAlert(AlertType.INFORMATION, "Cadastro de Paciente", "Ação invalida",
					"Finalize a consulta para utilizar o cadastro de paciente");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(this.mainApp.getStage());
			alert.showAndWait();
		} else {
			this.mainApp.initCadastroPac(funcAtual);
		}
	}

	// Botão/Menu Item AGENDA.
	@FXML
	private void agend() {
		this.mainApp.initAgenda(funcAtual, null);
	}

	@FXML
	private void consulta() {
		if (funcAtual.getTipoFunc().equalsIgnoreCase("Nutricionista")) {
			if (this.mainApp.isEvaluation()) {
				Alert alert = createAlert(AlertType.INFORMATION, "Consultas", "Ação invalida",
						"Finalize a consulta para acessar as consultas agendadas");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.initOwner(this.mainApp.getStage());
				alert.showAndWait();
			} else {
				this.mainApp.initConsulta(funcAtual);
			}
		} else {
			Alert alert = createAlert(AlertType.ERROR, "Consulta", "Ação invalida",
					"Apenas o Nutricionista ou Administrador pode realizar consulta");
			alert.show();
		}
	}

	// Menu item SOBRE.
	@FXML
	private void handleAbout() {
		Alert alert = createAlert(AlertType.INFORMATION, "Sobre o Sistema",
				"Este é um prototipo em JAVAFX para uma aplicação de nutrição",
				"Você pode pesquisar, deletar, atualizar, inserir pacientes na agenda de consultas e tem disponibilidade de ferramentas necessarias para uma consulta clinica, direcionada a OBESOS e EUTRÓFICOS");
		alert.show();
	}

	public void setMainApp(MainApp mainApp, Funcionario func) {
		this.mainApp = mainApp;
		this.funcAtual = func;
		setaFuncionario();
	}

	// Seta nome e CRN (caso nutricionista) na base da tela principal
	private void setaFuncionario() {
		if (funcAtual != null && funcAtual.getTipoFunc().equalsIgnoreCase("Nutricionista")) {
			txLogado.setText("Funcionario: " + funcAtual.getNome() + " - CRN: " + funcAtual.getCrn());
		} else {
			txLogado.setText("Funcionario: " + funcAtual.getNome());
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

}
