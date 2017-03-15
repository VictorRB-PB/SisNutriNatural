package br.com.sisnutri.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.sisnutri.dao.AvaliacaoDao;
import br.com.sisnutri.model.Avaliacao;
import br.com.sisnutri.model.Paciente;
import br.com.sisnutri.view.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EvolucaoController implements Initializable {

	MainApp mainApp;
	Paciente pacienteSelecionado;
	AvaliacoesController avController;

	@FXML
	TableView<Avaliacao> tbAvaliacao;
	@FXML
	TableColumn<Avaliacao, String> clDatas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		clDatas.setCellValueFactory(cellData -> cellData.getValue().dataAvaliacaoProperty());
		tbAvaliacao.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showAvaliacaoDetail(newValue));

	}

	public void setMainApp(MainApp mainApp, Paciente pacienteSelecionado, AvaliacoesController avController,
			boolean visualizarEvolucao) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
		this.pacienteSelecionado = pacienteSelecionado;
		this.avController = avController;
		initEvolucao(visualizarEvolucao);
	}

	// Inicia a BORDA da ESQUERDA do ROOTLAYOUT com uma tabela listando a data
	// das consultas realizadas pelo paciente assistido.
	private void initEvolucao(boolean visualizarEvolucao) {
		AvaliacaoDao avDao = new AvaliacaoDao();
		ObservableList<Avaliacao> listAvaliacoes = FXCollections.observableArrayList();
		try {
			listAvaliacoes.setAll(avDao.listaClinicasFisicasPaciente(pacienteSelecionado.getIdPac()));
			if (listAvaliacoes.size() > 0 && !visualizarEvolucao) {
				tbAvaliacao.setItems(listAvaliacoes);
				tbAvaliacao.getSelectionModel().selectFirst();
			} else if (listAvaliacoes.size() > 0 && visualizarEvolucao) {
				tbAvaliacao.setItems(listAvaliacoes);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Metodo para atualizar as FICHAS de avaliação preenchidas de acordo com a
	// avaliação selecionada.
	private void showAvaliacaoDetail(Avaliacao av) {
		// TODO Auto-generated method stub
		this.avController.atualizaAvaliacao(av);
	}

}
