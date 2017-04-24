package br.com.sisnutri.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import br.com.sisnutri.controller.AgendaController;
import br.com.sisnutri.controller.AvaliacoesController;
import br.com.sisnutri.controller.CadastroFuncionarioController;
import br.com.sisnutri.controller.CadastroPacienteController;
import br.com.sisnutri.controller.ConsultaController;
import br.com.sisnutri.controller.EvolucaoController;
import br.com.sisnutri.controller.RootLayoutController;
import br.com.sisnutri.dao.FuncionarioDao;
import br.com.sisnutri.model.Agenda;
import br.com.sisnutri.model.Avaliacao;
import br.com.sisnutri.model.Consulta;
import br.com.sisnutri.model.Funcionario;
import br.com.sisnutri.model.Paciente;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Pair;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;

public class MainApp extends Application {

	private static Stage stage;
	private BorderPane mainPane;
	private Funcionario loginFuncionario;
	private static String iconLoginPath = "file:images/logoSoftware.png";
	private FXMLLoader rootLoader;
	private boolean evaluation = false;

	@Override
	public void start(Stage primaryStage) {
		try {
			rootLoader = new FXMLLoader(getClass().getResource("RootLayout.fxml"));
			mainPane = (BorderPane) rootLoader.load();
			Scene scene = new Scene(mainPane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stage = primaryStage;
			stage.getIcons().add(new Image(iconLoginPath));
			stage.setScene(scene);
			stage.setTitle("Sistema NutriNatural");
			stage.centerOnScreen();
			stage.setOnCloseRequest(confirmCloseEventHandler);
			criarLogin();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	// Metodo para iniciar tela de cadastro de funcionairo no centro do
	// BorderPane principal.
	public void initCadastroFunc(Funcionario func) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroFuncionario.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();

			mainPane.setCenter(pane);

			CadastroFuncionarioController cadastroFuncionarioController = loader.getController();
			cadastroFuncionarioController.setMainApp(MainApp.this, func);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Metodo para iniciar tela de cadastro de paciente no centro do BorderPane
	// principal.
	public void initCadastroPac(Funcionario funcionarioAtual) {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CadastroPaciente.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();

			mainPane.setCenter(pane);

			CadastroPacienteController cadastroPacienteController = loader.getController();
			cadastroPacienteController.setMainApp(MainApp.this, funcionarioAtual);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// Metodo para iniciar tela de agendar consultas em uma nova tela (new
	// stage).
	public void initAgenda(Funcionario funcAtual, Paciente pacSelecionado) {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Agenda.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Agenda");
			dialogStage.getIcons().setAll(stage.getIcons());
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stage);
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);

			AgendaController agendController = loader.getController();
			agendController.setMainApp(MainApp.this, funcAtual, pacSelecionado, dialogStage);

			dialogStage.show();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Metodo para iniciar tela de nova consulta em uma nova tela(new stage).
	public void initConsulta(Funcionario funcAtual) {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Consulta.fxml"));
			AnchorPane pane = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Consultas Agendadas do Dia");
			dialogStage.getIcons().setAll(stage.getIcons());
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(stage);
			Scene scene = new Scene(pane);
			dialogStage.setScene(scene);

			ConsultaController consultaController = loader.getController();
			consultaController.setMainApp(MainApp.this, funcAtual, dialogStage);

			dialogStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Metodo para iniciar uma nova avaliação no centro do RootLayout e
	// utilizando as bordas da esquerda (LEFT) para visualizar consultas já
	// realizadas do paciente, caso exista.
	public void initAvaliacaoNutricional(Paciente pacienteSelecionado, Agenda agendaPaciente, Consulta consulta,
			Avaliacao avNutricional, boolean isVisualizacao) {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Avaliacoes.fxml"));
			AnchorPane paneCenter = (AnchorPane) loader.load();

			mainPane.setCenter(paneCenter);
			// Seta em avaliação verdadeiro
			evaluation = true;

			AvaliacoesController avController = loader.getController();
			avController.setMainApp(MainApp.this, pacienteSelecionado, agendaPaciente, consulta, avNutricional, isVisualizacao);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void initEvolucao(Paciente pacienteSelecionado, AvaliacoesController avController,
			boolean visualizarEvolucao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Evolucao.fxml"));
			AnchorPane paneLeft = (AnchorPane) loader.load();

			mainPane.setLeft(paneLeft);

			EvolucaoController evController = loader.getController();
			evController.setMainApp(MainApp.this, pacienteSelecionado, avController, visualizarEvolucao);

			loader = new FXMLLoader(getClass().getResource("BordaDireita.fxml"));
			AnchorPane paneRight = (AnchorPane) loader.load();

			mainPane.setRight(paneRight);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Cria um Dialog para fazer login
	private void criarLogin() {
		// Cria o dialog
		Dialog<Pair<String, String>> loginDialog = new Dialog<>();
		loginDialog.setTitle("Login");
		loginDialog.setHeaderText("Digite o CPF e senha para fazer login no sistema");

		// Seta icone
		loginDialog.setGraphic(new ImageView("file:images/LoginSoftware.png"));

		// Butões LOGIN e CANCELAR
		ButtonType loginButtonType = new ButtonType("Login", ButtonData.YES);
		ButtonType cancelButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		loginDialog.getDialogPane().getButtonTypes().setAll(loginButtonType, cancelButtonType);

		// Cria as labels no gridpane para o dialog
		TextField login = new TextField();
		PasswordField password = new PasswordField();
		login.setPromptText("Login");
		password.setPromptText("Senha");
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 100, 10, 10));
		grid.add(login, 0, 0);
		grid.add(password, 1, 0);

		// Habilitar/Desabilita botão se o login for inserido
		Node loginButton = loginDialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Se não estiver vazio habilita o botão LOGIN
		login.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});

		// Adiciona o grid criado ao dialog
		loginDialog.getDialogPane().setContent(grid);

		// Focus no campo LOGIN
		login.requestFocus();

		// Converte o resultado em um par de String quando o botão login é
		// clicado
		loginDialog.setResultConverter(dialogbutton -> {
			if (dialogbutton == loginButtonType) {
				return new Pair<>(login.getText(), password.getText());
			}
			return null;
		});

		// Seta as configurações do stage principal no LoginDialog 
		loginDialog.initOwner(stage);
			
		// Variavel para receber o resultado do dialog
		Optional<Pair<String, String>> result = loginDialog.showAndWait();

		// Se o botão login for clicado, realiza metodo para fazer login
		result.ifPresent(usernamePassword -> {
			try {
				FuncionarioDao funcDao = new FuncionarioDao();
				loginFuncionario = funcDao.login("07874554400", "senha");
				// loginFuncionario = funcDao.login(usernamePassword.getKey(),
				// usernamePassword.getValue());
				if (loginFuncionario != null) {
					if (loginFuncionario.isAtivo()) {
						RootLayoutController rootController = rootLoader.getController();
						rootController.setMainApp(this, loginFuncionario);
						stage.setMaximized(true);
						stage.show();
					} else {
						Alert alert = createAlert(AlertType.ERROR, "Login", "Login com status inativo, login invalido",
								"Digite um Login valido");

						Optional<ButtonType> ok = alert.showAndWait();
						if (ok.isPresent()) {
							if (ok.get() == ButtonType.OK) {
								criarLogin();
							}
						}
					}
				} else {
					Alert alert = createAlert(AlertType.ERROR, "Login", "Login e/ou senha errada", "Tente novamente");

					Optional<ButtonType> ok = alert.showAndWait();
					if (ok.isPresent()) {
						if (ok.get() == ButtonType.OK) {
							criarLogin();
						}
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

	}

	// Metodo para retornar um ALERTA de acordo com o tipo de alerta desejado
	// (Confirmation, Error, Information, None, Warning)
	private Alert createAlert(AlertType alertType, String title, String headerText, String contentText) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(headerText);
		alert.setContentText(contentText);
		alert.initOwner(stage);
		return alert;
	}

	// Evento para exibir mensagem quando clicar no botão X (close) na barra de
	// tarefas
	private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {

		if (evaluation) {
			Alert alert = createAlert(AlertType.INFORMATION, "Sair", "Ação invalida",
					"Finalize a consulta para poder fechar o programa");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(stage);
			alert.showAndWait();
			event.consume();
		} else {
			Alert alert = createAlert(AlertType.CONFIRMATION, "Sair", "Finalizar sistema",
					"Deseja realmete fechar o programa?");

			Button exitButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);

			exitButton.setText("Sair");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(stage);

			Optional<ButtonType> closeResponse = alert.showAndWait();

			if (closeResponse.isPresent()) {
				if (!ButtonType.OK.equals(closeResponse.get())) {
					event.consume();
				}
			}
		}
	};

	// Fecha a tela de avaliações setando null no centro e na esquerda do
	// BorderPane.
	public void finalizaConsulta(MainApp mainApp) throws Throwable {
		this.finalize();
		mainPane.setCenter(null);
		mainPane.setLeft(null);
	}

	public static Stage getStage() {
		return stage;
	}

	public boolean isEvaluation() {
		return evaluation;
	}

	public void setIsevaluation(boolean isevaluation) {
		this.evaluation = isevaluation;
	}

}
