package br.com.sisnutri.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.sisnutri.model.Funcionario;
import br.com.sisnutri.view.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
		System.exit(0);
	}

	// Menu item CADASTRO DE FUNCIONARIO
	@FXML
	private void cadastroFunc() {
		this.mainApp.initCadastroFunc(funcAtual);
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
		this.mainApp.initConsulta(funcAtual);
	}

	// Menu item SOBRE.
	@FXML
	private void handleAbout() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Informação do Programa");
		alert.setHeaderText("Este é um prototipo em JAVAFX para uma aplicação de nutrição");
		alert.setContentText(
				"Você pode pesquisar, deletar, atualizar, inserir pacientes na agenda de consultas e tem disponibilidade de ferramentas necessarias para uma consulta clinica, direcionada a ATLETAS, OBESOS e EUTRÓFICOS");
		alert.show();
	}

	public void setMainApp(MainApp mainApp, Funcionario func) {
		this.mainApp = mainApp;
		this.funcAtual = func;
		setaFuncionario();
	}

	private void setaFuncionario() {

		if (funcAtual != null && funcAtual.getCrn() > 0) {
			txLogado.setText("Funcionario: " + funcAtual.getNome() + " - CRN: " + funcAtual.getCrn());
		} else {
			txLogado.setText("Funcionario: " + funcAtual.getNome());
		}
	}
}
