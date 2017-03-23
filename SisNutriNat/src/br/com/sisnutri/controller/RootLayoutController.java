package br.com.sisnutri.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import br.com.sisnutri.model.Funcionario;
import br.com.sisnutri.view.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.text.Text;

public class RootLayoutController implements Initializable {

	MainApp mainApp;
	Funcionario funcAtual;

	@FXML
	private Text txLogado;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	// Menu item SAIR.
	@FXML
	private void handleExit() {
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

	// Menu item CADASTRO DE FUNCIONARIO
	@FXML
	private void cadastroFunc() {
		if (funcAtual.getTipoFunc().equalsIgnoreCase("Nutricionista")) {
			this.mainApp.initCadastroFunc(funcAtual);
		} else {
			Alert alert = createAlert(AlertType.ERROR, "Cadastro de Funcionario", "Ação invalida",
					"Apenas o Nutricionista e Administrador podem cadastrar funcionarios");
			alert.show();
		}

	}

	// Botão CADASTRO DE PACIENTE.
	@FXML
	private void cadastroPac() {
		this.mainApp.initCadastroPac(funcAtual);
	}

	// Botão/Menu Item AGENDA.
	@FXML
	private void agend() {
		this.mainApp.initAgenda(funcAtual, null);
	}

	@FXML
	private void consulta() {
		if (funcAtual.getTipoFunc().equalsIgnoreCase("Nutricionista")) {
			this.mainApp.initConsulta(funcAtual);
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
				"Você pode pesquisar, deletar, atualizar, inserir pacientes na agenda de consultas e tem disponibilidade de ferramentas necessarias para uma consulta clinica, direcionada a ATLETAS, OBESOS e EUTRÓFICOS");
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
