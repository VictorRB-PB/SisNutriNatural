package br.com.sisnutri.view;

import java.io.IOException;
import br.com.sisnutri.controller.AgendaController;
import br.com.sisnutri.controller.AvaliacoesController;
import br.com.sisnutri.controller.CadastroFuncionarioController;
import br.com.sisnutri.controller.CadastroPacienteController;
import br.com.sisnutri.controller.ConsultaController;
import br.com.sisnutri.controller.EvolucaoController;
import br.com.sisnutri.controller.LoginController;
import br.com.sisnutri.controller.RootLayoutController;
import br.com.sisnutri.model.Agenda;
import br.com.sisnutri.model.Avaliacao;
import br.com.sisnutri.model.Consulta;
import br.com.sisnutri.model.Funcionario;
import br.com.sisnutri.model.Paciente;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class MainApp extends Application {

	private static Stage stage;
	private BorderPane mainPane;

	@Override
	public void start(Stage primaryStage) {
		initComponnents(primaryStage);

	}

	public static void main(String[] args) {
		launch(args);
	}

	// Metodo para iniciar a tela de LOGIN.
	private void initComponnents(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
			AnchorPane loginLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(loginLayout);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage = primaryStage;
			stage.setScene(scene);
			// Dá um título para a tela
			stage.setTitle("Login - Sistema NutriNatural");
			stage.show();

			LoginController loginController = loader.getController();
			loginController.setMainApp(this);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Metodo para iniciar Tela Principal do programa.
	public void initRootLayout(Funcionario func) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RootLayout.fxml"));
			mainPane = (BorderPane) loader.load();
			// mainPane.setPrefSize(800, 600);
			Scene scene = new Scene(mainPane);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			stage.setScene(scene);
			stage.setTitle("Sistema NutriNatural");
			stage.show();

			RootLayoutController rootController = loader.getController();
			rootController.setMainApp(this, func);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			dialogStage.setTitle("Consultar Paciente");
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
			Avaliacao avNutricional) {
		// TODO Auto-generated method stub
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Avaliacoes.fxml"));
			AnchorPane paneCenter = (AnchorPane) loader.load();

			mainPane.setCenter(paneCenter);

			AvaliacoesController avController = loader.getController();
			avController.setMainApp(MainApp.this, pacienteSelecionado, agendaPaciente, consulta, avNutricional);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void initEvolucao(Paciente pacienteSelecionado, AvaliacoesController avController, boolean visualizarEvolucao) {
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

	// Fecha a tela de avaliações setando null no centro e na esquerda do
	// BorderPane.
	public void finalizaConsulta(MainApp mainApp) {
		mainPane.setCenter(null);
		mainPane.setLeft(null);
	}

	public static Stage getStage() {
		return stage;
	}

}
