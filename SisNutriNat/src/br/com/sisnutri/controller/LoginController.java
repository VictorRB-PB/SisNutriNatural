/**
 * 
 */
package br.com.sisnutri.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import br.com.sisnutri.dao.FuncionarioDao;
import br.com.sisnutri.model.Funcionario;
import br.com.sisnutri.view.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 * @author Victor
 *
 */
public class LoginController implements Initializable {

	FuncionarioDao funcDao;
	Funcionario loginFuncionario;

	@FXML
	private TextField txLogin;
	@FXML
	private PasswordField pfSenha;

	MainApp mainApp;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@FXML
	private void entrar() {
		String login = txLogin.getText();
		String senha = pfSenha.getText();
		try {
			loginFuncionario = funcDao.login("07874554400", "senha");
			if (loginFuncionario != null) {
				if (loginFuncionario.isAtivo()) {
					this.mainApp.initRootLayout(loginFuncionario);
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Login");
					alert.setHeaderText("Login com status inativo, login invalido");
					alert.setContentText("Digite um Login valido");
					alert.show();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login");
				alert.setHeaderText("Login e/ou senha errada");
				alert.setContentText("Tente novamente");
				alert.show();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void exit() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Sair");
		alert.setHeaderText("Encerrar programa");
		alert.setContentText("Deseja realmente sair do programa?");

		ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
		ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(yesButton, noButton);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == yesButton) {
			System.exit(0);
		}
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		funcDao = new FuncionarioDao();
	}

}
