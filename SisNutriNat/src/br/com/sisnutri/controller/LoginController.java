/**
 * 
 */
package br.com.sisnutri.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.sisnutri.dao.FuncionarioDao;
import br.com.sisnutri.model.Funcionario;
import br.com.sisnutri.view.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
	public void entrar() {
		String login = txLogin.getText();
		String senha = pfSenha.getText();
		try {
			loginFuncionario = funcDao.login("07874554400", "senha");
			if (loginFuncionario != null) {
				if (loginFuncionario.isAtivo()) {
					this.mainApp.initRootLayout(loginFuncionario);
				} else {
					JOptionPane.showMessageDialog(null, "Login com status inativo, login invalido", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Login e/ou senha inválidos", "Erro", JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void exit() {
		System.exit(0);
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		funcDao = new FuncionarioDao();
	}

}
