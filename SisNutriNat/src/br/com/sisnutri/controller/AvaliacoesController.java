/**
 * 
 */
package br.com.sisnutri.controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import br.com.sisnutri.dao.AgendaDao;
import br.com.sisnutri.dao.AvaliacaoDao;
import br.com.sisnutri.model.Agenda;
import br.com.sisnutri.model.Anamnese;
import br.com.sisnutri.model.Avaliacao;
import br.com.sisnutri.model.Consulta;
import br.com.sisnutri.model.Doenca;
import br.com.sisnutri.model.Exame;
import br.com.sisnutri.model.Farmaco;
import br.com.sisnutri.model.MedidasAntropometricas;
import br.com.sisnutri.model.Paciente;
import br.com.sisnutri.util.DateUtil;
import br.com.sisnutri.util.ModeloFormularios;
import br.com.sisnutri.view.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;

/**
 * @author Victor
 *
 */
public class AvaliacoesController implements Initializable {

	MainApp mainApp;
	private Paciente pacienteSelecionado;
	private Anamnese anamnese;
	private Agenda agendaPaciente;
	private Avaliacao avAtual;
	private MedidasAntropometricas medidasAtual;
	private Doenca doencaAtual;
	private Farmaco farmacoAtual;
	private Exame exameAtual;
	private boolean isVisualizacao;
	private double adequacaoPavsPT;
	private double vet;
	private String tecnicaAtual;

	@FXML
	Text txNomePac;
	@FXML
	Text txSexo;
	@FXML
	Text txIdade;
	@FXML
	Text txAltura;
	@FXML
	Text txPeso;
	@FXML
	TabPane tabPAv;
	@FXML
	TabPane tabPConsulta;
	@FXML
	TabPane tabPEntrevista;
	@FXML
	Tab tabAvClinica;
	@FXML
	Tab tabAvFisica;
	@FXML
	Tab tabAnamnese;
	@FXML
	HTMLEditor htEditor;
	@FXML
	TableView<Doenca> tbDoencas;
	@FXML
	TableColumn<Doenca, String> clDescDoenca;
	@FXML
	TableColumn<Doenca, String> clSituDoenca;
	@FXML
	TableColumn<Doenca, String> clObsDoenca;
	@FXML
	TableColumn<Doenca, String> clTipoDoenca;
	@FXML
	TableView<Farmaco> tbFarmacos;
	@FXML
	TableColumn<Farmaco, String> clDescFarmaco;
	@FXML
	TableColumn<Farmaco, String> clSubstAtivaFarmaco;
	@FXML
	TableColumn<Farmaco, String> clObsFarmaco;
	@FXML
	TableView<Exame> tbExames;
	@FXML
	TableColumn<Exame, String> clDescExames;
	@FXML
	TableColumn<Exame, String> clVrReferidoExames;
	@FXML
	TableColumn<Exame, String> clInterpretacaoExames;
	@FXML
	TextField txPesoAtual;
	@FXML
	TextField txPesoDesejado;
	@FXML
	TextField txPesoUsual;
	@FXML
	TextField txAltura2;
	@FXML
	TextField txAltJoelho;
	@FXML
	TextField txTriceps;
	@FXML
	TextField txBiceps;
	@FXML
	TextField txSubescapular;
	@FXML
	TextField txAxilarMedial;
	@FXML
	TextField txToracica;
	@FXML
	TextField txSupraEspinal;
	@FXML
	TextField txSuprailiaca;
	@FXML
	TextField txAbdome;
	@FXML
	TextField txCoxa;
	@FXML
	TextField txPanturrilha;
	@FXML
	TextField txBraco;
	@FXML
	TextField txAntebraco;
	@FXML
	TextField txPunho;
	@FXML
	TextField txTorax;
	@FXML
	TextField txCintura;
	@FXML
	TextField txTornozelo;
	@FXML
	TextField txAbdominal;
	@FXML
	TextField txQuadril;
	@FXML
	TextField txGlutMax;
	@FXML
	TextField txCoxaMax;
	@FXML
	TextField txPanturrilha2;
	@FXML
	TextField txCefalico;
	@FXML
	TextField txBiestiloide;
	@FXML
	TextField txBumeral;
	@FXML
	TextField txBfemural;
	@FXML
	ImageView imgMedidas;
	@FXML
	Text txPt;
	@FXML
	Text txPpr;
	@FXML
	Text txPprtext;
	@FXML
	Text txPavsPt;
	@FXML
	Text txPavsPu;
	@FXML
	Text txPavsPd;
	@FXML
	Text txPesoAjustado;
	@FXML
	Text txPAtext;
	@FXML
	Text txTempo;
	@FXML
	Text txTempotext;
	@FXML
	Text txClassifPavsPt;
	@FXML
	Text txClassifPavsPd;
	@FXML
	Text txClassifPavsPu;
	@FXML
	Text txImc;
	@FXML
	Text txCb;
	@FXML
	Text txPct;
	@FXML
	Text txAmb;
	@FXML
	Text txCmb;
	@FXML
	Text txDC;
	@FXML
	Text txImcClass;
	@FXML
	Text txCbClass;
	@FXML
	Text txDCTClass;
	@FXML
	Text txCMBClass;
	@FXML
	Text txAMBClass;
	@FXML
	Text txDCClass;
	@FXML
	Text txPercGord;
	@FXML
	Text txPercGordIdeal;
	@FXML
	Text txMcm;
	@FXML
	Text txPg;
	@FXML
	Text txGorduraClass;
	@FXML
	Text txPgi;
	@FXML
	Text txPm;
	@FXML
	Text txPo;
	@FXML
	Text txPr;
	@FXML
	Text txGeb;
	@FXML
	Text txTmb;
	@FXML
	Text txVET;
	@FXML
	Text txFA;
	@FXML
	Button btFinalizarConsulta;
	@FXML
	Tab tabMedidas;
	@FXML
	ComboBox<String> cbTempoPR;
	@FXML
	ComboBox<String> cbFatorAtividade;
	@FXML
	ComboBox<String> cbVetRp;
	@FXML
	ComboBox<String> cbVET;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initCols();
		initListenerTabs();
		initComboBoxe();
	}

	// Botão FINALIZAR CONSULTA
	@FXML
	private void finishConsulta() throws Throwable {
		try {
			Alert alert = createAlert(AlertType.CONFIRMATION, "Consulta", "Finalizar consulta",
					"Deseja realmente finalizar consulta?");

			ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
			ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(yesButton, noButton);
			Optional<ButtonType> result = alert.showAndWait();

			if (result.isPresent()) {
				if (result.get() == yesButton) {
					if (agendaPaciente != null) {
						AgendaDao a = new AgendaDao();
						AvaliacaoDao av = new AvaliacaoDao();

						MedidasAntropometricas medidasTemp = getMedidasTela(Integer.parseInt(txIdade.getText()));
						if (avAtual.getIdAvFisica() <= 0) {
							avAtual.setIdAvFisica(av.insertAvFisica(avAtual.getIdAvaliacao()));
							medidasTemp.setIdAvFisica(avAtual.getIdAvFisica());
							av.insertMedidas(medidasTemp);
						} else {
							medidasTemp.setIdAvFisica(avAtual.getIdAvFisica());
							av.updateMedidas(medidasTemp);
						}

						// Se o paciente já tiver anamnese, finalizar consulta
						// apenas atualiza descricao da anamnese, se não, cria
						// nova anamnese
						if (anamnese != null) {
							String attAnamnese = htEditor.getHtmlText();
							anamnese.setDescricao(attAnamnese);
							av.updateAnamnese(anamnese);
						} else {
							anamnese = new Anamnese(0, avAtual.getIdAvaliacao(), htEditor.getHtmlText());
							anamnese.setIdAnamnese(av.insertAnamnese(anamnese));
							avAtual.setIdAnamnese(anamnese.getIdAnamnese());
						}

						agendaPaciente.setStatusConsulta("REALIZADA");
						av.updateAvaliacao(avAtual);
						a.update(agendaPaciente);

						Alert alert2 = createAlert(AlertType.INFORMATION, "Consulta", "Fanalizando consulta",
								"Consulta finalizada com sucesso");
						Optional<ButtonType> result2 = alert2.showAndWait();
						if (result2.isPresent()) {
							if (result2.get() == ButtonType.OK) {
								mainApp.setIsevaluation(false);
								mainApp.finalizaConsulta(this.mainApp);
								// FALTA ADICIONAR METODO PARA REAGENDAR RETORNO
								// OU NOVA CONSULTA APOS FINALIZAÇÃO DE CONSULTA
							}
						}
					} else {
						// Falta inserir metodo para estudar paciente
						// (visualizar evolução, sem necessariamente agendar uma
						// nova consulta ou retorno)
						Alert alert2 = createAlert(AlertType.INFORMATION, "Consulta", "Fanalizando consulta",
								"Consulta finalizada com sucesso");
						Optional<ButtonType> result2 = alert2.showAndWait();
						if (result2.isPresent()) {
							if (result2.get() == ButtonType.OK) {
								mainApp.setIsevaluation(false);
								mainApp.finalizaConsulta(this.mainApp);
							}
						}
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Aba INDICADORES NUTRICIONAIS, verifica se existe avaliação fisica e se é
	// igual aos campos preenchidos, se
	// sim, calcula os indicadores nutricionais de acordo com as medidas
	// existentes, se não, pega os dados nos campos, realiza os calculos e
	// insere/atualiza uma avaliação fisica para a avaliação atual/selecionada.
	@FXML
	private void initCalcs() {
		int idade = Integer.valueOf(txIdade.getText());
		MedidasAntropometricas medidasTemp = getMedidasTela(idade);
		medidasTemp.setIdAvFisica(avAtual.getIdAvFisica());
		initCalculadora(medidasTemp);
	}

	// Comoboxe Fator atividade
	@FXML
	private void fatorAtividade() {
		DecimalFormat df = new DecimalFormat("#");
		double vetTemp;
		if (cbFatorAtividade.getSelectionModel().getSelectedItem().equalsIgnoreCase("Sedentario")) {
			txFA.setText("1,40");
			vetTemp = vet * 1.40;
			txVET.setText(df.format(vetTemp) + "Kcal (FAO, OMS, 1985)");
		} else if (cbFatorAtividade.getSelectionModel().getSelectedItem().equalsIgnoreCase("Leve")) {
			if (pacienteSelecionado.getSexo().equalsIgnoreCase("f")) {
				txFA.setText("1,55");
				vetTemp = vet * 1.55;
			} else {
				txFA.setText("1,56");
				vetTemp = vet * 1.56;
			}
			txVET.setText(df.format(vetTemp) + "Kcal (FAO, OMS, 1985)");
		} else if (cbFatorAtividade.getSelectionModel().getSelectedItem().equalsIgnoreCase("Moderada")) {
			if (pacienteSelecionado.getSexo().equalsIgnoreCase("f")) {
				txFA.setText("1,64");
				vetTemp = vet * 1.64;
			} else {
				txFA.setText("1,78");
				vetTemp = vet * 1.78;
			}
			txVET.setText(df.format(vetTemp) + "Kcal (FAO, OMS, 1985)");
		} else if (cbFatorAtividade.getSelectionModel().getSelectedItem().equalsIgnoreCase("Intensa")) {
			if (pacienteSelecionado.getSexo().equalsIgnoreCase("f")) {
				txFA.setText("1,82");
				vetTemp = vet * 1.82;
			} else {
				txFA.setText("2,10");
				vetTemp = vet * 2.10;
			}
			txVET.setText(df.format(vetTemp) + "Kcal (FAO, OMS, 1985)");
		}

	}

	// Botão Adicionar Medidas Antropometricas
	@FXML
	private void addMedidas() {
		tabPAv.getSelectionModel().select(tabAvFisica);
		tabPConsulta.getSelectionModel().select(tabMedidas);
	}

	// Botão ADICIONAR DOENÇA.
	@FXML
	private void addDoenca() {
		try {
			createDoencaDialog(doencaAtual);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Botão ADICIONAR FARMACO.
	@FXML
	private void addFarmaco() {
		try {
			createFarmacoDialog(farmacoAtual);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Botão ADICIONAR EXAME.
	@FXML
	private void addExame() {
		try {
			createExameDialog(exameAtual);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Botão DELETAR DOENÇA selecionada.
	@FXML
	private void deleteDoenca() {
		try {
			if (doencaAtual != null) {
				Alert alert = createAlert(AlertType.CONFIRMATION, "Doença - Sintomas/Sinal", "Deletar doença",
						"Deseja realmente deletar doença/sintomas/sinal: " + doencaAtual.getDescricao() + "?");

				ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
				ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(yesButton, noButton);
				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent()) {
					if (result.get() == yesButton) {
						delDoenca(doencaAtual);
						atualizaTabelas();
					}
				}
			} else {
				Alert alert = createAlert(AlertType.ERROR, "Doença - Sintomas/Sinal",
						"Doença - Sintomas/Sinal invalido", "Selecione uma doença/sintomas/sinal para poder deletar");
				alert.show();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Botão DELETAR FARMACO selecionada.
	@FXML
	private void deleteFarmaco() {
		try {
			if (farmacoAtual != null) {
				Alert alert = createAlert(AlertType.CONFIRMATION, "Farmaco", "Deletar farmaco",
						"Deseja realmente deletar farmaco: " + farmacoAtual.getDescricao() + "?");

				ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
				ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(yesButton, noButton);
				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent()) {
					if (result.get() == yesButton) {
						delFarmaco(farmacoAtual);
						atualizaTabelas();
					}
				}
			} else {
				Alert alert = createAlert(AlertType.ERROR, "Farmaco", "Farmaco invalido",
						"Selecione um farmaco para poder deletar");
				alert.show();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Botão DELETAR EXAME selecionada.
	@FXML
	private void deleteExame() {
		try {
			if (exameAtual != null) {
				Alert alert = createAlert(AlertType.CONFIRMATION, "Exames", "Deletar exame",
						"Deseja realmente deletar exame: " + exameAtual.getDescricao() + "?");

				ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
				ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(yesButton, noButton);
				Optional<ButtonType> result = alert.showAndWait();

				if (result.isPresent()) {
					if (result.get() == yesButton) {
						delExame(exameAtual);
						atualizaTabelas();
					}
				}
			} else {
				Alert alert = createAlert(AlertType.ERROR, "Exame", "Exame invalido",
						"Selecione um exame para poder deletar");
				alert.show();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void setMainApp(MainApp mainApp, Paciente pacienteSelecionado, Agenda agendaPaciente, Consulta consulta,
			Avaliacao av, boolean isVisualizacao) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
		this.pacienteSelecionado = pacienteSelecionado;
		this.agendaPaciente = agendaPaciente;
		this.avAtual = av;
		this.isVisualizacao = isVisualizacao;
		this.tecnicaAtual = consulta.getTecnica();

		// Retorna anamnese do paciente caso já tenha realizado, se não, o
		// formulario de anamnese é iniciado de acordo com a tecnica desejada
		getAnamnese(this.pacienteSelecionado.getIdPac(), tecnicaAtual);

		// Verifica se está visualizando evolução ou realizando uma nova
		// consulta.
		if ((avAtual.getIdAvFisica() > 0 || avAtual.getIdAvClinica() > 0 || avAtual.getIdAnamnese() > 0)
				&& this.isVisualizacao) {
			this.mainApp.initEvolucao(this.pacienteSelecionado, AvaliacoesController.this, this.isVisualizacao);
			atualizaDadosDasAvaliacoes(avAtual);
			// Esse if verifica se já existe avaliações inseridas no dia
			// corrente, se sim atualiza os campos, se não campos vazio
		} else if ((avAtual.getIdAvFisica() > 0 || avAtual.getIdAvClinica() > 0 || avAtual.getIdAnamnese() > 0)
				&& !this.isVisualizacao) {
			this.mainApp.initEvolucao(this.pacienteSelecionado, AvaliacoesController.this, false);
			atualizaDadosDasAvaliacoes(avAtual);
		} else {
			this.mainApp.initEvolucao(this.pacienteSelecionado, AvaliacoesController.this, false);
		}

		atualizaDadosPaciente();

	}

	// Verifica se o paciente já tem uma anamnese, se tiver, seta no HTML
	// Editor se não, atualiza tela para o nutricionista iniciar a entrevista.
	private void getAnamnese(int idPac, String tecnica) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		this.anamnese = avDao.getAnamnese(idPac);
		if (anamnese != null && anamnese.getIdAnamnese() > 0) {
			htEditor.setHtmlText(anamnese.getDescricao());
		} else if (tecnica.equalsIgnoreCase("Eutrófico")) {
			tabPAv.getSelectionModel().select(tabAvClinica);
			tabPEntrevista.getSelectionModel().select(tabAnamnese);
			htEditor.setHtmlText(ModeloFormularios.anmneseGeral);
		}
	}

	// Metodo para atualizar dados das avaliações se existir
	public void atualizaDadosDasAvaliacoes(Avaliacao av) throws SQLException, ParseException {
		// TODO Auto-generated method stub
		this.avAtual = av;
		AvaliacaoDao avDao = new AvaliacaoDao();

		if (avAtual.getIdAvFisica() > 0) {
			medidasAtual = avDao.findMedidas(avAtual.getIdAvFisica());
			atualizaMedidas(medidasAtual);
			atualizaDadosPaciente();
		} else {
			atualizaMedidas(null);
		}

		atualizaTabelas();
	}

	//

	/********************************************************
	 * * * * SEÇÃO DOS CALCULOS ANTROPOMETRICOS - CALCULADORA GERAL * * * * *
	 * 
	 * @throws ParseException
	 *             *
	 * 
	 *******************************************************/

	// Metodo para calcular idade
	private int calculaIdade() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String formataLocalDate = DateUtil.format(pacienteSelecionado.getDataNasc());
		Date dataNasc = sdf.parse(formataLocalDate);
		int idade = DateUtil.calculaIdade(dataNasc);
		return idade;
	}

	// Inicializa todos os metodos de calculo
	private void initCalculadora(MedidasAntropometricas m) {

		calculaIMC(m.getPesoAtual(), m.getAltura());
		calculaAdequacaoPesoAtualePesoUsual(m.getPesoAtual(), m.getPesoUsual());
		calculaAdequacaoPesoAtualePesoDesejado(m.getPesoAtual(), m.getPesoDesejado());
		calculaCMB(m.getTriceps(), m.getBraco(), m.getCintura());
		if (cbTempoPR.getSelectionModel().getSelectedIndex() > 0 && m.getPesoUsual() > 0 && m.getPesoAtual() > 0) {
			calculaMudancaPeso(m.getPesoUsual(), m.getPesoAtual(),
					cbTempoPR.getSelectionModel().getSelectedItem().toString());
		}
		calculaPercG(m);
		calculaEstimativas(m);
	}

	/********************************************************
	 * * * * SEÇÃO DOS CALCULOS ANTROPOMETRICOS - PESO * * * * * *
	 *******************************************************/

	// Calcula Indice de massa corporal (IMC)
	private void calculaIMC(double peso, double altura) {

		DecimalFormat df = new DecimalFormat("#.##");
		// Converta altura de Metros para centimetros
		altura = altura / 100;
		double imc = peso / Math.pow(altura, 2);
		if (imc < 16) {
			txImc.setText(df.format(imc) + "/m² (QUETELET, 1836)");
			txImcClass.setText("Magreza Grau III (WHO, 2004)");
		} else if (imc >= 16.1 && imc <= 16.99) {
			txImc.setText(df.format(imc) + "/m² (QUETELET, 1836)");
			txImcClass.setText("Magreza Grau II (WHO, 2004)");
		} else if (imc >= 17 && imc <= 18.49) {
			txImc.setText(df.format(imc) + "/m² (QUETELET, 1836)");
			txImcClass.setText("Magreza Gray I (WHO, 2004)");
		} else if (imc >= 18.5 && imc <= 24.99) {
			txImc.setText(df.format(imc) + "/m² (QUETELET, 1836)");
			txImcClass.setText("Normal (WHO, 2004)");
		} else if (imc == 25) {
			txImc.setText(df.format(imc) + "/m² (QUETELET, 1836)");
			txImcClass.setText("Excesso de Peso (WHO, 2004)");
		} else if (imc >= 26 && imc <= 29.99) {
			txImc.setText(df.format(imc) + "/m² (QUETELET, 1836)");
			txImcClass.setText("Pre-Obeso (WHO, 2004)");
		} else if (imc == 30) {
			txImc.setText(df.format(imc) + "/m² (QUETELET, 1836)");
			txImcClass.setText("Obeso (WHO, 2004)");
		} else if (imc >= 31 && imc <= 34.99) {
			txImc.setText(df.format(imc) + "/m² (QUETELET, 1836)");
			txImcClass.setText("Obeso Grau I (WHO, 2004)");
		} else if (imc >= 35 && imc <= 39.99) {
			txImc.setText(df.format(imc) + "/m² (QUETELET, 1836)");
			txImcClass.setText("Obeso Grau II (WHO, 2004)");
		} else if (imc >= 4) {
			txImc.setText(df.format(imc) + "/m² (QUETELET, 1836)");
			txImcClass.setText("Obeso III (WHO, 2004)");
		}
		calculaPesoTeorico(altura, peso, imc);

	}

	// Calcula Peso Teorico/Peso Ideal
	private void calculaPesoTeorico(double altura, double peso, double imc) {
		DecimalFormat df = new DecimalFormat("#.##");
		double pesoTeorico;
		// Eleva ao quadrado
		altura = Math.pow(altura, 2);
		if (pacienteSelecionado.getSexo().equalsIgnoreCase("m")) {
			pesoTeorico = altura * 22;
			txPt.setText(df.format(pesoTeorico) + "Kg (WEST, 1980)");
		} else {
			pesoTeorico = altura * 21;
			txPt.setText(df.format(pesoTeorico) + "Kg (WEST, 1980)");
		}

		calculaAdequacaoPesoAtualeTeorico(peso, pesoTeorico);
		calculaPesoAjustado(peso, pesoTeorico, imc);
	}

	// Calcula Adequação de peso atual sobre peso teorico
	private void calculaAdequacaoPesoAtualeTeorico(double pesoAtual, double pesoTeorico) {
		DecimalFormat df = new DecimalFormat("#.##");
		double adequacao = (pesoAtual / pesoTeorico) * 100;
		txPavsPt.setText(df.format(adequacao) + "%");

		if (adequacao <= 70) {
			txClassifPavsPt.setText("Desnutrição grave (BLACKBURN, 1977)");
		} else if (adequacao >= 70.1 && adequacao <= 80) {
			txClassifPavsPt.setText("Desnutrição moderada (BLACKBURN, 1977)");
		} else if (adequacao >= 80.1 && adequacao <= 90) {
			txClassifPavsPt.setText("Desnutrição leve (BLACKBURN, 1977)");
		} else if (adequacao >= 90.1 && adequacao <= 110) {
			txClassifPavsPt.setText("Eutrófia (BLACKBURN, 1977)");
		} else if (adequacao >= 110.1 && adequacao <= 120) {
			txClassifPavsPt.setText("Sobrepeso (BLACKBURN, 1977)");
		} else if (adequacao > 120) {
			txClassifPavsPt.setText("Obesidade (BLACKBURN, 1977)");
		}
		this.adequacaoPavsPT = adequacao;
	}

	// Calcula adequação de peso atual sobre peso usual
	private void calculaAdequacaoPesoAtualePesoUsual(double pesoAtual, double pesoUsual) {

		DecimalFormat df = new DecimalFormat("#.##");
		double adequacao = (pesoAtual / pesoUsual) * 100;
		txPavsPu.setText(df.format(adequacao) + "%");
		if (adequacao <= 74) {
			txClassifPavsPu.setText("Depleção grave (BLACKBURN, 1977)");
		} else if (adequacao >= 75 && adequacao <= 84) {
			txClassifPavsPu.setText("Depleção moderada (BLACKBURN, 1977)");
		} else if (adequacao >= 85 && adequacao <= 95) {
			txClassifPavsPu.setText("Depleção leve (BLACKBURN, 1977)");
		}

	}

	// Calcula adequação de peso atual sobre peso desejado
	private void calculaAdequacaoPesoAtualePesoDesejado(double pesoAtual, double pesoDesejado) {
		DecimalFormat df = new DecimalFormat("#.##");
		if (pesoAtual > 0 && pesoDesejado > 0) {
			double adequacao = (pesoAtual / pesoDesejado) * 100;
			txPavsPd.setText(" " + df.format(adequacao) + "%");
			if (adequacao >= adequacaoPavsPT && adequacao >= 96) {
				txClassifPavsPd.setText("Normal (BLACKBURN e THORNTON, 1979)");
			} else if (adequacao >= adequacaoPavsPT && adequacao >= 85) {
				if (adequacao <= adequacaoPavsPT && adequacao <= 95) {
					txClassifPavsPd.setText("Desnutrição leve (BLACKBURN e THORNTON, 1979)");
				}
			} else if (adequacao >= adequacaoPavsPT && adequacao >= 75) {
				if (adequacao <= adequacaoPavsPT && adequacao <= 84) {
					txClassifPavsPd.setText("Desnutrição moderada (BLACKBURN e THORNTON, 1979)");
				}
			} else if (adequacao >= adequacaoPavsPT && adequacao >= 84) {
				txClassifPavsPd.setText("Desnutrição grave (BLACKBURN e THORNTON, 1979)");
			}
		}
	}

	// Calcula Peso Ajustado
	private void calculaPesoAjustado(double pesoAtual, double pesoTeorico, double imc) {
		DecimalFormat df = new DecimalFormat("#.##");
		double pesoAjustado = 0;
		txPAtext.setVisible(true);
		txPesoAjustado.setVisible(true);
		pesoAjustado = (pesoAtual - pesoTeorico) * 0.25 + pesoTeorico;
		txPesoAjustado.setText(df.format(pesoAjustado) + "Kg (ASPEN, 1988)");

	}

	// Calcula Perda de Peso Recente
	private void calculaMudancaPeso(double pesoUsual, double pesoAtual, String tempo) {
		DecimalFormat df = new DecimalFormat("#.##");
		double perdaPeso = (pesoUsual - pesoAtual) * 100 / pesoUsual;
		if (tempo != null && tempo.length() > 0) {
			txPprtext.setVisible(true);
			txPpr.setVisible(true);
			txPpr.setText(df.format(perdaPeso) + "% (BLACKBURN et al., 1977)");
			txTempotext.setVisible(true);
			txTempo.setVisible(true);
			if (tempo.equalsIgnoreCase("1 semana")) {
				if (perdaPeso <= 2) {
					txTempo.setText("Moderada (LANG, 1987)");
				} else {
					txTempo.setText("Intensa (LANG, 1987)");
				}
			}
			if (tempo.equalsIgnoreCase("1 mês")) {
				if (perdaPeso <= 5) {
					txTempo.setText("Moderada (LANG, 1987)");
				} else {
					txTempo.setText("Intensa (LANG, 1987)");
				}
			}
			if (tempo.equalsIgnoreCase("3 meses")) {
				if (perdaPeso <= 7.5) {
					txTempo.setText("Moderada (LANG, 1987)");
				} else {
					txTempo.setText("Intensa (LANG, 1987)");
				}
			}
			if (tempo.equalsIgnoreCase("6 meses ou mais")) {
				if (perdaPeso <= 10) {
					txTempo.setText("Moderada (LANG, 1987)");
				} else {
					txTempo.setText("Intensa (LANG, 1987)");
				}
			}
		} else {
			txPprtext.setVisible(false);
			txPpr.setVisible(false);
			txTempotext.setVisible(false);
			txTempo.setVisible(false);
		}
	}

	/********************************************************
	 * * * * SEÇÃO DOS CALCULOS ANTROPOMETRICOS - CLASSIFICAÇÃO DO ESTADO
	 * NUTRICIONAL * * * * * *
	 *******************************************************/
	// Calcula e classifica CB, CMB, DCT, AMB e DC
	private void calculaCMB(double pct, double cb, double cintura) {

		DecimalFormat df = new DecimalFormat("##.##");
		// Fazer comparativos ADEQUAÇÃO do CB, PCT e CMB de acordo com a
		// referencia PERCENTIL DE REFERENCIA
		// (JELLIFE, 1973) e a classificação (BLACKBURN & THORNTON, 1979)
		// para
		// homens e mulheres 18 a 74 anos
		double calculoCmb = cb - (0.314 * pct) / 100;
		double adequacaoPct = 0;
		double adequacaoCb = 0;
		double adequacaoCmb = 0;
		double ambc = Math.pow(cb - 0.314 * pct, 2) / 4 * 0.314;

		if (pacienteSelecionado.getSexo().equalsIgnoreCase("m")) {
			adequacaoPct = (pct / 12.5) * 100;
			adequacaoCb = (cb / 29.3) * 100;
			adequacaoCmb = (calculoCmb / 25.3) * 100;
			ambc = ambc - 10;

			if (adequacaoPct <= 70) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Depleção grave (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoPct > 70 && adequacaoPct <= 80) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Depleção moderada (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoPct > 80 && adequacaoPct <= 90) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Depleção leve (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoPct > 90 & adequacaoPct <= 110) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Eutrofia (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoPct > 110 && adequacaoPct <= 120) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Sobrepeso (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoPct > 120) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Obesidade (BLACKBURN & THORNTON, 1979)");
			}

			if (adequacaoCb <= 70) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Depleção grave (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCb > 70 && adequacaoCb <= 80) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Depleção moderada (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCb > 80 && adequacaoCb <= 90) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Depleção leve (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCb > 90 & adequacaoCb <= 110) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Eutrofia (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCb > 110 && adequacaoCb <= 120) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Sobrepeso (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCb > 120) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Obesidade (BLACKBURN & THORNTON, 1979)");
			}

			if (adequacaoCmb <= 70) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Depleção grave (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCmb > 70 && adequacaoCb <= 80) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Depleção moderada (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCmb > 80 && adequacaoCb <= 90) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Depleção leve (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCmb > 90 & adequacaoCb <= 110) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Eutrofia (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCmb > 110 && adequacaoCb <= 120) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Sobrepeso (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCmb > 120) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Obesidade (BLACKBURN & THORNTON, 1979)");
			}

			if (ambc <= 35) {
				txAmb.setText(df.format(ambc) + "cm² (HEYMSFIELD, 1999)");
				txAMBClass.setText("Musculatura baixa - Depleção (ANN ARBOR, 1990)");
			} else if (ambc > 35 && ambc <= 45) {
				txAmb.setText(df.format(ambc) + "cm² (HEYMSFIELD, 1999)");
				txAMBClass.setText("Abaixo da média (ANN ARBOR, 1990)");
			} else if (ambc > 45 && ambc <= 60) {
				txAmb.setText(df.format(ambc) + "cm² (HEYMSFIELD, 1999)");
				txAMBClass.setText("Média (ANN ARBOR, 1990)");
			} else if (ambc > 60 && ambc <= 75) {
				txAmb.setText(df.format(ambc) + "cm² (HEYMSFIELD, 1999)");
				txAMBClass.setText("Acima da média (ANN ARBOR, 1990)");
			} else if (ambc > 75) {
				txAmb.setText(df.format(ambc) + "cm² ");
				txAMBClass.setText("Musculatura elevada – boa nutrição (ANN ARBOR, 1990)");
			}

			if (cintura < 94) {
				txDC.setText(df.format(cintura) + "cm");
				txDCClass.setText("Normal (OMS, 1998)");
			} else if (cintura >= 94 && cintura < 102) {
				txDC.setText(df.format(cintura) + "cm");
				txDCClass.setText("Aumentado (OMS, 1998)");
			} else if (cintura >= 102) {
				txDC.setText(df.format(cintura) + "cm");
				txDCClass.setText("Muito aumentado (OMS, 1998)");
			}
		} else {
			adequacaoPct = (pct / 16.5) * 100;
			adequacaoCb = (cb / 28.5) * 100;
			adequacaoCmb = (calculoCmb / 23.2) * 100;
			ambc = ambc - 6.5;

			if (adequacaoPct <= 70) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Depleção grave (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoPct > 70 && adequacaoPct <= 80) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Depleção moderada (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoPct > 80 && adequacaoPct <= 90) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Depleção leve (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoPct > 90 & adequacaoPct <= 110) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Eutrofia (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoPct > 110 && adequacaoPct <= 120) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Sobrepeso (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoPct > 120) {
				txPct.setText(df.format(adequacaoPct) + "% (JELLIFE, 1973)");
				txDCTClass.setText("Obesidade (BLACKBURN & THORNTON, 1979)");
			}

			if (adequacaoCb <= 70) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Depleção grave (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCb > 70 && adequacaoCb <= 80) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Depleção moderada (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCb > 80 && adequacaoCb <= 90) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Depleção leve (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCb > 90 & adequacaoCb <= 110) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Eutrofia (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCb > 110 && adequacaoCb <= 120) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Sobrepeso (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCb > 120) {
				txCb.setText(df.format(adequacaoCb) + "% (JELLIFE, 1973)");
				txCbClass.setText("Obesidade (BLACKBURN & THORNTON, 1979)");
			}

			if (adequacaoCmb <= 70) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Depleção grave (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCmb > 70 && adequacaoCb <= 80) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Depleção moderada (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCmb > 80 && adequacaoCb <= 90) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Depleção leve (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCmb > 90 & adequacaoCb <= 110) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Eutrofia (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCmb > 110 && adequacaoCb <= 120) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Sobrepeso (BLACKBURN & THORNTON, 1979)");
			} else if (adequacaoCmb > 120) {
				txCmb.setText(df.format(adequacaoCmb) + "% (JELLIFE, 1973)");
				txCMBClass.setText("Obesidade (BLACKBURN & THORNTON, 1979)");
			}

			if (ambc <= 20) {
				txAmb.setText(df.format(ambc) + "cm² (HEYMSFIELD, 1999)");
				txAMBClass.setText("Musculatura baixa - Depleção (ANN ARBOR, 1990)");
			} else if (ambc > 20 && ambc <= 25) {
				txAmb.setText(df.format(ambc) + "cm² (HEYMSFIELD, 1999)");
				txAMBClass.setText("Abaixo da média (ANN ARBOR, 1990)");
			} else if (ambc > 25 && ambc <= 35) {
				txAmb.setText(df.format(ambc) + "cm² (HEYMSFIELD, 1999)");
				txAMBClass.setText("Média (ANN ARBOR, 1990)");
			} else if (ambc > 35 && ambc <= 55) {
				txAmb.setText(df.format(ambc) + "cm² (HEYMSFIELD, 1999)");
				txAMBClass.setText("Acima da média (ANN ARBOR, 1990)");
			} else if (ambc > 55) {
				txAmb.setText(df.format(ambc) + "cm² (HEYMSFIELD, 1999)");
				txAMBClass.setText("Musculatura elevada – boa nutrição (ANN ARBOR, 1990)");
			}

			if (cintura < 80) {
				txDC.setText(df.format(cintura) + "cm");
				txDCClass.setText("Normal (OMS, 1998)");
			} else if (cintura >= 80 && cintura < 88) {
				txDC.setText(df.format(cintura) + "cm");
				txDCClass.setText("Aumentado (OMS, 1998)");
			} else if (cintura >= 88) {
				txDC.setText(df.format(cintura) + "cm");
				txDCClass.setText("Muito aumentado (OMS, 1998)");
			}
		}

	}

	/********************************************************
	 * * * * SEÇÃO DOS CALCULOS ANTROPOMETRICOS - COMPOSIÇÃO CORPORAL * * * * *
	 * *
	 *******************************************************/

	// Calcula percentual de gordura (PG)
	private void calculaPercG(MedidasAntropometricas m) {
		DecimalFormat df = new DecimalFormat("##.##");
		double altura = m.getAltura() / 100;

		if (pacienteSelecionado.getSexo().equalsIgnoreCase("m")) {
			// TORAXICA - Abdominal - Coxa
			// m.setToracica(25);
			// m.setAbdominal(31);
			// m.setCoxa(35);
			if (m.getToracica() > 0 && m.getAbdominal() > 0 && m.getCoxa() > 0) {
				double somatoria3 = m.getToracica() + m.getAbdominal() + m.getCoxa();
				double densidade = (1.10938 - (0.0008267 * somatoria3))
						+ (0.0000016 * (Math.pow(somatoria3, 2)) - (0.0002574 * m.getIdade()));
				double resultado = ((4.95 / densidade) - 4.5) * 100; // equação
																		// de
																		// SIRI
																		// (1961)

				double pesoOsseo = 0.302
						* (Math.pow((Math.pow(altura, 2) * m.getPunho() / 100 * m.getBfemural() / 100 * 400), 0.712)); // Von
				// Doblen
				double pesoGAtual = (resultado / 100) * m.getPesoAtual();
				double pesoResidual = m.getPesoAtual() * 0.241; // Wurch
				double mcm = m.getPesoAtual() - pesoGAtual;
				double pesoMuscular = (m.getPesoAtual() - (pesoGAtual + pesoOsseo + pesoResidual));
				String classificacaoGordura = classGordura(m.getIdade(), resultado);

				txPercGord.setText(df.format(resultado) + "% (SIRI, 1961)");

				txMcm.setText(df.format(mcm) + "Kg (SIRI, 1961)");
				txPg.setText(df.format(pesoGAtual) + "Kg (SIRI, 1961)");

				txPr.setText(df.format(pesoResidual) + "Kg (PIRES NETO, 1997)");
				txPo.setText(df.format(pesoOsseo) + "Kg (ROCHA, 1975)");
				txPm.setText(df.format(pesoMuscular) + "Kg (SIRI, 1961)");

				if (m.getIdade() >= 18 && m.getIdade() <= 29) {
					txPercGordIdeal.setText("14% (LEA e FEBIGER, 1986)");
					double pesoGordoIdeal = 14 * m.getPesoAtual() / 100;
					txPgi.setText(df.format(pesoGordoIdeal) + "Kg (FAULKNER, 1968)");
				} else if (m.getIdade() >= 30 && m.getIdade() <= 39) {
					txPercGordIdeal.setText("16% (LEA e FEBIGER, 1986)");
					double pesoGordoIdeal = 16 * m.getPesoAtual() / 100;
					txPgi.setText(df.format(pesoGordoIdeal) + "Kg (FAULKNER, 1968)");
				} else if (m.getIdade() >= 40 && m.getIdade() <= 49) {
					txPercGordIdeal.setText("17% (LEA e FEBIGER, 1986)");
					double pesoGordoIdeal = 17 * m.getPesoAtual() / 100;
					txPgi.setText(df.format(pesoGordoIdeal) + "Kg (FAULKNER, 1968)");
				} else if (m.getIdade() >= 50 && m.getIdade() <= 59) {
					txPercGordIdeal.setText("18% (LEA e FEBIGER, 1986)");
					double pesoGordoIdeal = 18 * m.getPesoAtual() / 100;
					txPgi.setText(df.format(pesoGordoIdeal) + "Kg (FAULKNER, 1968)");
				} else if (m.getIdade() >= 60) {
					txPercGordIdeal.setText("21% (LEA e FEBIGER, 1986)");
					double pesoGordoIdeal = 21 * m.getPesoAtual() / 100;
					txPgi.setText(df.format(pesoGordoIdeal) + "Kg (FAULKNER, 1968)");
				}

				txGorduraClass.setText(classificacaoGordura);
			}
		} else {
			// tríceps/supra-ilíaca/coxa
			if (m.getTriceps() > 0 && m.getSupraIliaca() > 0 && m.getCoxa() > 0) {
				double somatorio3 = m.getTriceps() + m.getSupraIliaca() + m.getCoxa();
				double densidade = 1.0994921 - 0.0009929 * somatorio3 + 0.0000023 * Math.pow(somatorio3, 2)
						- 0.0001392 * m.getIdade();// Equação de POLLOCK e
													// JACKSON
													// (1978)
				double resultado = ((4.95 / densidade) - 4.5) * 100; // Equação
																		// de
																		// SIRI
																		// (1961)

				double pesoOsseo = 0.302 * (Math
						.pow((Math.pow(altura, 2) * (m.getPunho() / 100) * (m.getBfemural() / 100) * 400), 0.712)); // Von
				// Doblen
				double pesoGAtual = (resultado / 100) * m.getPesoAtual();
				double pesoResidual = m.getPesoAtual() * 0.209; // Wurch
				double mcm = m.getPesoAtual() - pesoGAtual;
				double pesoMuscular = (m.getPesoAtual() - (pesoGAtual + pesoOsseo + pesoResidual));
				String classificacaoGordura = classGordura(m.getIdade(), resultado);

				txPercGord.setText(df.format(resultado) + "% (SIRI, 1961)");

				txMcm.setText(df.format(mcm) + "Kg (SIRI, 1961)");
				txPg.setText(df.format(pesoGAtual) + "Kg (SIRI, 1961)");

				txPr.setText(df.format(pesoResidual) + "Kg (PIRES NETO, 1997)");
				txPo.setText(df.format(pesoOsseo) + "Kg (ROCHA, 1975)");
				txPm.setText(df.format(pesoMuscular) + "Kg (SIRI, 1961)");

				if (m.getIdade() >= 18 && m.getIdade() <= 29) {
					txPercGordIdeal.setText("19% (LEA e FEBIGER, 1986)");
					double pesoGordoIdeal = 19 * m.getPesoAtual() / 100;
					txPgi.setText(df.format(pesoGordoIdeal) + "Kg (FAULKNER, 1968)");
				} else if (m.getIdade() >= 30 && m.getIdade() <= 39) {
					txPercGordIdeal.setText("21% (LEA e FEBIGER, 1986)");
					double pesoGordoIdeal = 21 * m.getPesoAtual() / 100;
					txPgi.setText(df.format(pesoGordoIdeal) + "Kg (FAULKNER, 1968)");
				} else if (m.getIdade() >= 40 && m.getIdade() <= 49) {
					txPercGordIdeal.setText("22% (LEA e FEBIGER, 1986)");
					double pesoGordoIdeal = 22 * m.getPesoAtual() / 100;
					txPgi.setText(df.format(pesoGordoIdeal) + "Kg (FAULKNER, 1968)");
				} else if (m.getIdade() >= 50 && m.getIdade() <= 59) {
					txPercGordIdeal.setText("23% (LEA e FEBIGER, 1986)");
					double pesoGordoIdeal = 23 * m.getPesoAtual() / 100;
					txPgi.setText(df.format(pesoGordoIdeal) + "Kg (FAULKNER, 1968)");
				} else if (m.getIdade() >= 60) {
					txPercGordIdeal.setText("26% (LEA e FEBIGER, 1986)");
					double pesoGordoIdeal = 26 * m.getPesoAtual() / 100;
					txPgi.setText(df.format(pesoGordoIdeal) + "Kg (FAULKNER, 1968)");
				}

				txGorduraClass.setText(classificacaoGordura);
			}
		}

	}

	/********************************************************
	 * * * * SEÇÃO DOS CALCULOS ANTROPOMETRICOS - ESTIMATIVAS CALORICAS * * * *
	 * * *
	 *******************************************************/
	// Calcula o GASTO ENERGETICO BASAL (GEB) e a TAXA DE METABOLISMO BASAL
	private void calculaEstimativas(MedidasAntropometricas m) {
		DecimalFormat df = new DecimalFormat("##.##");
		double geb = 0;
		double tmb = 0;

		if (pacienteSelecionado.getSexo().equalsIgnoreCase("m")) {
			geb = 66.4730 + (13.7517 * m.getPesoAtual()) + (5.003 * m.getAltura()) - (6.7550 * m.getIdade());
			tmb = 66 + (13.7 * m.getPesoAtual()) + (5 * m.getAltura()) - (6.8 * m.getIdade());
		} else {
			geb = 655.0955 + (9.5634 * m.getPesoAtual()) + (1.8496 * m.getAltura()) - (4.6756 * m.getIdade());
			tmb = 655 + (9.6 * m.getPesoAtual()) + (1.7 * m.getAltura()) - (4.7 * m.getIdade());
		}

		txGeb.setText(df.format(geb) + "kcal (HARRIS BENEDICT, 1919)");
		txTmb.setText(df.format(tmb) + "kcal (FAO/OMS, 1985)");
		vet = geb;
	}

	/********************************************************
	 * * * * SEÇÃO DOS CALCULOS ANTROPOMETRICOS * * * * * *
	 *******************************************************/

	// Metodo para DELETAR DOENÇA selecionada na tabela.
	private void delDoenca(Doenca d) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		avDao.deleteDoenca(d);
	}

	// Metodo para DELETAR FARMACO selecionada na tabela.
	private void delFarmaco(Farmaco f) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		avDao.deleteFarmaco(f);
	}

	// Metodo para DELETAR EXAME selecionada na tabela.
	private void delExame(Exame e) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		avDao.deleteExame(e);
	}

	// Metodo para atualiza dados do paciente no TitledPane - DADOS DO PACIENTE
	private void atualizaDadosPaciente() throws ParseException {
		DecimalFormat dfIdade = new DecimalFormat("#");
		DecimalFormat df = new DecimalFormat("##,##");
		if (pacienteSelecionado != null) {
			txNomePac.setText(pacienteSelecionado.getNome());

			if (pacienteSelecionado.getSexo().equalsIgnoreCase("m")) {
				txSexo.setText("Masculino");
			} else {
				txSexo.setText("Feminino");
			}
			if (medidasAtual != null) {
				txAltura.setText(String.valueOf("Altura: " + df.format(medidasAtual.getAltura()) + "m"));
				txPeso.setText(String.valueOf("Peso: " + df.format(medidasAtual.getPesoAtual()) + "Kg"));
				txIdade.setText(dfIdade.format(medidasAtual.getIdade()));
			} else {
				int idade = calculaIdade();
				txIdade.setText(String.valueOf(idade));
			}

		} else {
			txNomePac.setText(null);
			txSexo.setText(null);
			txAltura.setText(null);
			txPeso.setText(null);
			txIdade.setText(null);
		}
	}

	// Metodo para pegar DOENÇA selecionada na tabela.
	private void showDoencaDetail(Doenca doenca) {
		// TODO Auto-greturn null;
		if (doenca != null) {
			doencaAtual = doenca;
		} else {
			doencaAtual = null;
		}
	}

	// Metodo para pegar FARMACO selecionado na tabela.
	private void showFarmacoDetail(Farmaco farmaco) {
		// TODO Auto-greturn null;
		if (farmaco != null) {
			farmacoAtual = farmaco;
		} else {
			farmacoAtual = null;
		}
	}

	// Metodo para pegar EXAME selecionado na tabela
	private void showExameDetail(Exame exame) {
		// TODO Auto-greturn null;
		if (exame != null) {
			exameAtual = exame;
		} else {
			exameAtual = null;
		}
	}

	// Metodo para adicionar LISTENER nas tabelas DOENÇA, FARMACO e EXAME.
	private void initListenerTabs() {
		tbDoencas.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showDoencaDetail(newValue));
		tbFarmacos.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showFarmacoDetail(newValue));
		tbExames.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showExameDetail(newValue));
	}

	// Metodo para iniciar as colunas das tabelas de DOENÇA, FARMACO e EXAME.
	private void initCols() {
		// COLUNAS DA TABELA DOENÇA
		clDescDoenca.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
		clSituDoenca.setCellValueFactory(cellData -> cellData.getValue().situacaoProperty());
		clObsDoenca.setCellValueFactory(cellData -> cellData.getValue().obsProperty());
		clTipoDoenca.setCellValueFactory(cellData -> cellData.getValue().tipoDoencaProperty());

		// COLUNAS DA TABELA FARMACO
		clDescFarmaco.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
		clSubstAtivaFarmaco.setCellValueFactory(cellData -> cellData.getValue().subsAtivaProperty());
		clObsFarmaco.setCellValueFactory(cellData -> cellData.getValue().obsProperty());

		// COLUNAS DA TABELA EXAME
		clDescExames.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
		clVrReferidoExames.setCellValueFactory(cellData -> cellData.getValue().valorRefProperty());
		clInterpretacaoExames.setCellValueFactory(cellData -> cellData.getValue().interpretacaoProperty());
	}

	// Verifica se já existe AVALIAÇÃO CLINICA na AVALIAÇÃO selecionada.
	private boolean verifyIdAvClinica(int idAvClinica) {
		boolean flag = false;
		if (idAvClinica > 0) {
			flag = true;
		}
		return flag;
	}

	// ATUALIZA as tabelas de DOENÇA, FARMACO e EXAME da avaliação clinica
	// selecionada.
	private void atualizaTabelas() {
		try {
			if (avAtual.getIdAvClinica() > 0) {
				tbDoencas.setItems(listaDoencas(avAtual.getIdAvClinica()));
				tbFarmacos.setItems(listaFarmacos(avAtual.getIdAvClinica()));
				tbExames.setItems(listaExames(avAtual.getIdAvClinica()));
			} else {
				tbDoencas.setItems(null);
				tbFarmacos.setItems(null);
				tbExames.setItems(null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Retorna lista de DOENÇAS da avaliação CLINICA selecionada.
	private ObservableList<Doenca> listaDoencas(int idAvClinica) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		ObservableList<Doenca> doencas = FXCollections.observableArrayList();
		doencas.setAll(avDao.listaDoencaAvClinica(idAvClinica));
		return doencas;
	}

	// Retorna lista de FARMACOS da avaliação CLINICA selecionada.
	private ObservableList<Farmaco> listaFarmacos(int idAvClinica) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		ObservableList<Farmaco> farmacos = FXCollections.observableArrayList();
		farmacos.setAll(avDao.listaFarmacoAvClinica(idAvClinica));
		return farmacos;
	}

	// Retorna lista de EXAMES da avaliação CLINICA selecionada.
	private ObservableList<Exame> listaExames(int idAvClinica) throws SQLException {
		AvaliacaoDao avDao = new AvaliacaoDao();
		ObservableList<Exame> exames = FXCollections.observableArrayList();
		exames.setAll(avDao.listaExameAvClinica(idAvClinica));
		return exames;
	}

	// Metodo para atualizar campos de medidas antropometricas na tela.
	private void atualizaMedidas(MedidasAntropometricas m) {
		DecimalFormat df = new DecimalFormat("##,##");
		if (m != null) {
			txPesoAtual.setText(df.format(m.getPesoAtual()));
			txPesoDesejado.setText(df.format(m.getPesoDesejado()));
			txPesoUsual.setText(df.format(m.getPesoUsual()));
			cbTempoPR.getSelectionModel().select(m.getTempoSobrepeso());
			txAltura2.setText(df.format(m.getAltura()));
			txAltJoelho.setText(df.format(m.getAltJoelho()));
			txTriceps.setText(df.format(m.getTriceps()));
			txBiceps.setText(df.format(m.getBiceps()));
			txSubescapular.setText(df.format(m.getSubescapular()));
			txAxilarMedial.setText(df.format(m.getAxilarMedial()));
			txToracica.setText(df.format(m.getToracica()));
			txSupraEspinal.setText(df.format(m.getSupraEspinal()));
			txSuprailiaca.setText(df.format(m.getSupraIliaca()));
			txAbdome.setText(df.format(m.getAbdome()));
			txCoxa.setText(df.format(m.getCoxa()));
			txPanturrilha.setText(df.format(m.getPanturrilhaDobra()));
			txBraco.setText(df.format(m.getBraco()));
			txAntebraco.setText(df.format(m.getAntebraco()));
			txPunho.setText(df.format(m.getPunho()));
			txTorax.setText(df.format(m.getTorax()));
			txCintura.setText(df.format(m.getCintura()));
			txTornozelo.setText(df.format(m.getTornozelo()));
			txAbdominal.setText(df.format(m.getAbdominal()));
			txQuadril.setText(df.format(m.getQuadril()));
			txGlutMax.setText(df.format(m.getGlutMax()));
			txCoxaMax.setText(df.format(m.getCoxaMax()));
			txPanturrilha2.setText(df.format(m.getPanturrilhaPerimetro()));
			txCefalico.setText(df.format(m.getCefalico()));
			txBiestiloide.setText(df.format(m.getBiestiloide()));
			txBumeral.setText(df.format(m.getBumeral()));
			txBfemural.setText(df.format(m.getBfemural()));
		} else {
			txPesoAtual.setText(null);
			txPesoDesejado.setText(null);
			txPesoUsual.setText(null);
			cbTempoPR.getSelectionModel().clearSelection();
			txAltura2.setText(null);
			txAltJoelho.setText(null);
			txTriceps.setText(null);
			txBiceps.setText(null);
			txSubescapular.setText(null);
			txAxilarMedial.setText(null);
			txToracica.setText(null);
			txSupraEspinal.setText(null);
			txSuprailiaca.setText(null);
			txAbdome.setText(null);
			txCoxa.setText(null);
			txPanturrilha.setText(null);
			txBraco.setText(null);
			txAntebraco.setText(null);
			txPunho.setText(null);
			txTorax.setText(null);
			txCintura.setText(null);
			txTornozelo.setText(null);
			txAbdominal.setText(null);
			txQuadril.setText(null);
			txGlutMax.setText(null);
			txCoxaMax.setText(null);
			txPanturrilha2.setText(null);
			txCefalico.setText(null);
			txBiestiloide.setText(null);
			txBumeral.setText(null);
			txBfemural.setText(null);
		}
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

	// Metodo de criar um dialog para inserir DOENÇA - SINTOMAS/SINAL na
	// avaliação clinica
	// solicitada, cria uma nova
	// avaliação clinica se não existir aviliação clinica pra exame atual
	private void createDoencaDialog(Doenca d) throws SQLException {

		Dialog<HashMap<String, String>> dialogDoenca = new Dialog<>();
		dialogDoenca.setTitle("Doença - Sintomas/Sinal");
		dialogDoenca.setHeaderText("Preencha os campos abaixo sobre doença ou sintoma/sinal a ser adicionado");
		// Seta icone
		dialogDoenca.initOwner(MainApp.getStage());

		// Butões ADICIONAR e CANCELAR
		ButtonType addButtonType = new ButtonType("Adicionar", ButtonData.YES);
		ButtonType cancelButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		dialogDoenca.getDialogPane().getButtonTypes().setAll(addButtonType, cancelButtonType);

		// Cria labels e comboboxes para o grid de farmaco
		TextField descricao = new TextField();
		ComboBox<String> situacao = new ComboBox<>();
		TextField observacao = new TextField();
		ComboBox<String> tipo = new ComboBox<>();

		// Adiciona valores nos comboboxe situação e tipo
		situacao.getItems().setAll("Boa", "Ruim");
		tipo.getItems().setAll("Doença", "Sintoma/Sinal");

		descricao.setPromptText("Descrição");
		situacao.setPromptText("Situação");
		observacao.setPromptText("Observação");
		tipo.setPromptText("Tipo da doença ou sintoma/sinal");

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		grid.add(descricao, 0, 0);
		grid.add(situacao, 1, 0);
		grid.add(observacao, 0, 1);
		grid.add(tipo, 1, 1);

		// Habilita/Desabilita botão adicionar se descrição for preenchida
		Node addButton = dialogDoenca.getDialogPane().lookupButton(addButtonType);
		addButton.setDisable(true);

		// Se descrição não estiver vazio habilita o botão adicionar
		descricao.textProperty().addListener((observable, oldValue, newValue) -> {
			addButton.setDisable(newValue.trim().isEmpty());
		});

		// Adiciona grid criado ao dialog
		dialogDoenca.getDialogPane().setContent(grid);

		// Converte o resultado do dialogDoenca em HashMap quando o botão
		// adicionar é clicado
		dialogDoenca.setResultConverter(newButton -> {
			if (newButton == addButtonType) {
				HashMap<String, String> result = new HashMap<>();
				result.put("descricao", descricao.getText());
				result.put("situacao", situacao.getValue());
				result.put("obs", observacao.getText());
				result.put("tipo", tipo.getValue());
				return result;
			}
			return null;
		});

		// Seta configurações do stage principal no dialog doença
		dialogDoenca.initOwner(MainApp.getStage());

		// Variavel para receber os resultados do dialogDoenca
		Optional<HashMap<String, String>> result = dialogDoenca.showAndWait();

		if (result.isPresent()) {
			HashMap<String, String> resultado = result.get();
			AvaliacaoDao avDao = new AvaliacaoDao();
			if (verifyIdAvClinica(avAtual.getIdAvClinica())) {
				d = new Doenca(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("situacao"),
						resultado.get("obs"), resultado.get("tipo"));
				avDao.insertDoenca(d);
			} else {
				avAtual.setIdAvClinica(avDao.insertAvClinica(avAtual.getIdAvaliacao()));
				avDao.updateAvaliacao(avAtual);
				d = new Doenca(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("situacao"),
						resultado.get("obs"), resultado.get("tipo"));
				avDao.insertDoenca(d);
			}
			atualizaTabelas();
		}
	}

	// Metodo de criar um dialog para inserir FARMACO na avaliação clinica
	// solicitada, cria uma nova
	// avaliação clinica se não existir aviliação clinica pra exame atual
	private void createFarmacoDialog(Farmaco f) throws SQLException {

		Dialog<HashMap<String, String>> dialogFarmaco = new Dialog<>();
		dialogFarmaco.setTitle("Farmaco");
		dialogFarmaco.setHeaderText("Preencha os campos abaixo sobre o farmaco a ser adicionado");
		// Seta icone
		dialogFarmaco.initOwner(MainApp.getStage());

		// Butões ADICIONAR e CANCELAR
		ButtonType addButtonType = new ButtonType("Adicionar", ButtonData.YES);
		ButtonType cancelButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		dialogFarmaco.getDialogPane().getButtonTypes().setAll(addButtonType, cancelButtonType);

		// Cria labels e comboboxes para o grid de farmaco
		TextField descricao = new TextField();
		TextField subsAtiva = new TextField();
		TextField observacao = new TextField();

		descricao.setPromptText("Descrição do farmaco");
		subsAtiva.setPromptText("Substancia ativa");
		observacao.setPromptText("Observação");

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 80, 10, 10));
		grid.add(descricao, 0, 0);
		grid.add(subsAtiva, 1, 0);
		grid.add(observacao, 0, 1);

		// Habilita/Desabilita botão adicionar se descrição for preenchida
		Node addButton = dialogFarmaco.getDialogPane().lookupButton(addButtonType);
		addButton.setDisable(true);

		// Se descrição não estiver vazio habilita o botão adicionar
		descricao.textProperty().addListener((observable, oldValue, newValue) -> {
			addButton.setDisable(newValue.trim().isEmpty());
		});

		// Adiciona grid criado ao dialog
		dialogFarmaco.getDialogPane().setContent(grid);

		// Converte o resultado do dialogFarmaco em HashMap quando o botão
		// adicionar é clicado
		dialogFarmaco.setResultConverter(newButton -> {
			if (newButton == addButtonType) {
				HashMap<String, String> result = new HashMap<>();
				result.put("descricao", descricao.getText());
				result.put("subsAtiva", subsAtiva.getText());
				result.put("obs", observacao.getText());
				return result;
			}
			return null;
		});

		// Seta configurações do stage principal no dialog farmaco
		dialogFarmaco.initOwner(MainApp.getStage());

		// Variavel para receber os resultados do farmacoDialog
		Optional<HashMap<String, String>> result = dialogFarmaco.showAndWait();

		if (result.isPresent()) {
			HashMap<String, String> resultado = result.get();
			AvaliacaoDao avDao = new AvaliacaoDao();
			if (verifyIdAvClinica(avAtual.getIdAvClinica())) {
				f = new Farmaco(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("subsAtiva"),
						resultado.get("obs"));
				avDao.insertFarmaco(f);
			} else {
				avAtual.setIdAvClinica(avDao.insertAvClinica(avAtual.getIdAvaliacao()));
				avDao.updateAvaliacao(avAtual);
				f = new Farmaco(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("subsAtiva"),
						resultado.get("obs"));
				avDao.insertFarmaco(f);
			}
			atualizaTabelas();
		}
	}

	// Metodo de criar um dialog para inserir EXAME na avaliação clinica
	// solicitada, cria uma nova
	// avaliação clinica se não existir aviliação clinica pra exame atual
	private void createExameDialog(Exame e) throws SQLException {

		Dialog<HashMap<String, String>> dialogExame = new Dialog<>();
		dialogExame.setTitle("Exame");
		dialogExame.setHeaderText("Preencha os campos abaixo sobre o exame a ser adicionado");
		// Seta icone
		dialogExame.initOwner(MainApp.getStage());

		// Butões ADICIONAR e CANCELAR
		ButtonType addButtonType = new ButtonType("Adicionar", ButtonData.YES);
		ButtonType cancelButtonType = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);
		dialogExame.getDialogPane().getButtonTypes().setAll(addButtonType, cancelButtonType);

		// Cria labels e comboboxes para o grid de exames
		TextField descricao = new TextField();
		TextField valorRef = new TextField();
		TextField interpretacao = new TextField();

		descricao.setPromptText("Descrição do exame");
		valorRef.setPromptText("Valor referido do exame");
		interpretacao.setPromptText("Interpretação do exame");

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 80, 10, 10));
		grid.add(descricao, 0, 0);
		grid.add(valorRef, 1, 0);
		grid.add(interpretacao, 0, 1);

		// Habilita/Desabilita botão adicionar se descrição for preenchida
		Node addButton = dialogExame.getDialogPane().lookupButton(addButtonType);
		addButton.setDisable(true);

		// Se descrição não estiver vazio habilita o botão adicionar
		descricao.textProperty().addListener((observable, oldValue, newValue) -> {
			addButton.setDisable(newValue.trim().isEmpty());
		});

		// Adiciona grid criado ao dialog
		dialogExame.getDialogPane().setContent(grid);

		// Converte o resultado do dialogExame em HashMap quando o botão
		// adicionar é clicado
		dialogExame.setResultConverter(newButton -> {
			if (newButton == addButtonType) {
				HashMap<String, String> result = new HashMap<>();
				result.put("descricao", descricao.getText());
				result.put("valorReferido", valorRef.getText());
				result.put("interpretacao", interpretacao.getText());
				return result;
			}
			return null;
		});

		// Seta configurações do stage principal no dialog exames
		dialogExame.initOwner(MainApp.getStage());

		// Variavel para receber os resultados do exameDialog
		Optional<HashMap<String, String>> result = dialogExame.showAndWait();

		if (result.isPresent()) {
			HashMap<String, String> resultado = result.get();
			AvaliacaoDao avDao = new AvaliacaoDao();
			if (verifyIdAvClinica(avAtual.getIdAvClinica())) {
				e = new Exame(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("valorReferido"),
						resultado.get("interpretacao"));
				avDao.insertExame(e);
			} else {
				avAtual.setIdAvClinica(avDao.insertAvClinica(avAtual.getIdAvaliacao()));
				avDao.updateAvaliacao(avAtual);
				e = new Exame(0, avAtual.getIdAvClinica(), resultado.get("descricao"), resultado.get("valorReferido"),
						resultado.get("interpretacao"));
				avDao.insertExame(e);
			}
			atualizaTabelas();
		}
	}

	// Preenche as comboboxe Tempo perda de tempo recente e Categoria IMC
	private void initComboBoxe() {
		// TODO Auto-generated method stub
		ObservableList<String> tempoPerdaRecente = FXCollections.observableArrayList("1 semana", "1 mês", "3 meses",
				"6 meses ou mais");
		ObservableList<String> fatorAtividade = FXCollections.observableArrayList("Sedentario", "Leve", "Moderada",
				"Intensa");

		cbTempoPR.setItems(tempoPerdaRecente);
		cbFatorAtividade.setItems(fatorAtividade);

	}

	// Metodo para o ScrollPane não abaixar quando pressionar barra de espaço
	@FXML
	private void ignoreSpaceBar(KeyEvent event) {
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {
			// Consume Event before Bubbling Phase, -> otherwise Scrollpane
			// scrolls
			if (event.getCode() == KeyCode.SPACE) {
				event.consume();
			}
		}
	}

	private MedidasAntropometricas getMedidasTela(int idade) {
		// TODO Auto-generated method stub
		// DOBRAS
		double pesoAtual = 0;
		double pesoDesejado = 0;
		double pesoUsual = 0;
		String tempoPp = null;
		double altura = 0;
		double alturaJoelho = 0;
		double triceps = 0;
		double biceps = 0;
		double subescapular = 0;
		double axilarMedial = 0;
		double toracica = 0;
		double supraEspinal = 0;
		double supraIliaca = 0;
		double abdome = 0;
		double coxa = 0;
		double panturrilha = 0;

		// PERIMETROS
		double braco = 0;
		double antebraco = 0;
		double punho = 0;
		double torax = 0;
		double cintura = 0;
		double tornozelo = 0;
		double abdominal = 0;
		double quadril = 0;
		double glutMax = 0;
		double coxaMax = 0;
		double panturrilha2 = 0;
		double cefalico = 0;

		// Diametros
		double biestiloide = 0;
		double bUmeral = 0;
		double bFemural = 0;

		if (txPesoAtual.getText() != null && txPesoAtual.getText().length() > 0) {
			pesoAtual = Double.parseDouble(txPesoAtual.getText().replace(",", ""));
		}
		if (txPesoDesejado.getText() != null && txPesoDesejado.getText().length() > 0) {
			pesoDesejado = Double.parseDouble(txPesoDesejado.getText().replace(",", ""));
		}
		if (txPesoUsual.getText() != null && txPesoUsual.getText().length() > 0) {
			pesoUsual = Double.parseDouble(txPesoUsual.getText().replace(",", ""));
		}
		if (cbTempoPR.getSelectionModel().getSelectedIndex() > 0) {
			tempoPp = cbTempoPR.getSelectionModel().getSelectedItem();
		}
		if (txAltura2.getText() != null && txAltura2.getText().length() > 0) {
			altura = Double.parseDouble(txAltura2.getText().replace(".", ""));
		}
		if (txAltJoelho.getText() != null && txAltJoelho.getText().length() > 0) {
			alturaJoelho = Double.parseDouble(txAltJoelho.getText().replace(",", ""));
		}
		if (txTriceps.getText() != null && txTriceps.getText().length() > 0) {
			triceps = Double.parseDouble(txTriceps.getText().replace(",", ""));
		}
		if (txBiceps.getText() != null && txBiceps.getText().length() > 0) {
			biceps = Double.parseDouble(txBiceps.getText().replace(",", ""));
		}
		if (txSubescapular.getText() != null && txSubescapular.getText().length() > 0) {
			subescapular = Double.parseDouble(txSubescapular.getText().replace(",", ""));
		}
		if (txAxilarMedial.getText() != null && txAxilarMedial.getText().length() > 0) {
			axilarMedial = Double.parseDouble(txAxilarMedial.getText().replace(",", ""));
		}
		if (txToracica.getText() != null && txToracica.getText().length() > 0) {
			toracica = Double.parseDouble(txToracica.getText().replace(",", ""));
		}
		if (txSupraEspinal.getText() != null && txSupraEspinal.getText().length() > 0) {
			supraEspinal = Double.parseDouble(txSupraEspinal.getText().replace(",", ""));
		}
		if (txSuprailiaca.getText() != null && txSuprailiaca.getText().length() > 0) {
			supraIliaca = Double.parseDouble(txSuprailiaca.getText().replace(",", ""));
		}
		if (txAbdome.getText() != null && txAbdome.getText().length() > 0) {
			abdome = Double.parseDouble(txAbdome.getText().replace(",", ""));
		}
		if (txCoxa.getText() != null && txCoxa.getText().length() > 0) {
			coxa = Double.parseDouble(txCoxa.getText().replace(",", ""));
		}
		if (txPanturrilha.getText() != null && txPanturrilha.getText().length() > 0) {
			panturrilha = Double.parseDouble(txPanturrilha.getText().replace(",", ""));
		}
		if (txBraco.getText() != null && txBraco.getText().length() > 0) {
			braco = Double.parseDouble(txBraco.getText().replace(",", ""));
		}
		if (txAntebraco.getText() != null && txAntebraco.getText().length() > 0) {
			antebraco = Double.parseDouble(txAntebraco.getText().replace(",", ""));
		}
		if (txPunho.getText() != null && txPunho.getText().length() > 0) {
			punho = Double.parseDouble(txPunho.getText().replace(",", ""));
		}
		if (txTorax.getText() != null && txTorax.getText().length() > 0) {
			torax = Double.parseDouble(txTorax.getText().replace(",", ""));
		}
		if (txCintura.getText() != null && txCintura.getText().length() > 0) {
			cintura = Double.parseDouble(txCintura.getText().replace(",", ""));
		}
		if (txTornozelo.getText() != null && txTornozelo.getText().length() > 0) {
			tornozelo = Double.parseDouble(txTornozelo.getText().replace(",", ""));
		}
		if (txAbdominal.getText() != null && txAbdominal.getText().length() > 0) {
			abdominal = Double.parseDouble(txAbdominal.getText().replace(",", ""));
		}
		if (txQuadril.getText() != null && txQuadril.getText().length() > 0) {
			quadril = Double.parseDouble(txQuadril.getText().replace(",", ""));
		}
		if (txGlutMax.getText() != null && txGlutMax.getText().length() > 0) {
			glutMax = Double.parseDouble(txGlutMax.getText().replace(",", ""));
		}
		if (txCoxaMax.getText() != null && txCoxaMax.getText().length() > 0) {
			coxaMax = Double.parseDouble(txCoxaMax.getText().replace(",", ""));
		}
		if (txPanturrilha2.getText() != null && txPanturrilha2.getText().length() > 0) {
			panturrilha2 = Double.parseDouble(txPanturrilha2.getText().replace(",", ""));
		}
		if (txCefalico.getText() != null && txCefalico.getText().length() > 0) {
			cefalico = Double.parseDouble(txCefalico.getText().replace(",", ""));
		}

		if (txBiestiloide.getText() != null && txBiestiloide.getText().length() > 0) {
			biestiloide = Double.parseDouble(txBiestiloide.getText().replace(",", ""));
		}
		if (txBumeral.getText() != null && txBumeral.getText().length() > 0) {
			bUmeral = Double.parseDouble(txBumeral.getText().replace(",", ""));
		}
		if (txBfemural.getText() != null && txBfemural.getText().length() > 0) {
			bFemural = Double.parseDouble(txBfemural.getText().replace(",", ""));
		}
		// Metodo para inserir idade do dia da avaliação fisica realizada.
		MedidasAntropometricas medidasTemp = new MedidasAntropometricas(0, 0, pesoAtual, pesoDesejado, pesoUsual,
				tempoPp, altura, alturaJoelho, triceps, biceps, subescapular, axilarMedial, toracica, supraEspinal,
				supraIliaca, abdome, coxa, panturrilha, braco, antebraco, punho, torax, cintura, tornozelo, abdominal,
				quadril, glutMax, coxaMax, panturrilha2, cefalico, biestiloide, bUmeral, bFemural, idade);
		return medidasTemp;
	}

	private String classGordura(double idade, double porcentagem) {
		String classificacao = null;
		if (pacienteSelecionado.getSexo().contentEquals("m")) {
			if (idade >= 18 && idade <= 25) {
				if (porcentagem >= 4 && porcentagem <= 7) {
					classificacao = "Excelente (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 8 && porcentagem <= 11) {
					classificacao = "Bom (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 12 && porcentagem <= 13) {
					classificacao = "Acima da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 14 && porcentagem <= 16) {
					classificacao = "Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 17 && porcentagem <= 20) {
					classificacao = "Abaixo da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 21 && porcentagem <= 25) {
					classificacao = "Ruim (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 26) {
					classificacao = "Muito Ruim (POLLOCK & WILMORE, 1993)";
				}
			} else if (idade >= 26 && idade <= 35) {
				if (porcentagem >= 8 && porcentagem <= 11) {
					classificacao = "Excelente (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 12 && porcentagem <= 15) {
					classificacao = "Bom (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 16 && porcentagem <= 17) {
					classificacao = "Acima da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 18 && porcentagem <= 21) {
					classificacao = "Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 22 && porcentagem <= 24) {
					classificacao = "Abaixo da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 25 && porcentagem <= 27) {
					classificacao = "Ruim (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 28) {
					classificacao = "Muito Ruim (POLLOCK & WILMORE, 1993)";
				}
			} else if (idade >= 36 && idade <= 45) {
				if (porcentagem >= 10 && porcentagem <= 15) {
					classificacao = "Excelente (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 16 && porcentagem <= 18) {
					classificacao = "Bom (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 19 && porcentagem <= 20) {
					classificacao = "Acima da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 21 && porcentagem <= 23) {
					classificacao = "Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 24 && porcentagem <= 26) {
					classificacao = "Abaixo da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 27 && porcentagem <= 29) {
					classificacao = "Ruim (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 30) {
					classificacao = "Muito Ruim (POLLOCK & WILMORE, 1993)";
				}
			} else if (idade >= 46 && idade <= 55) {
				if (porcentagem >= 12 && porcentagem <= 17) {
					classificacao = "Excelente (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 18 && porcentagem <= 20) {
					classificacao = "Bom (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 21 && porcentagem <= 23) {
					classificacao = "Acima da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 24 && porcentagem <= 25) {
					classificacao = "Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 26 && porcentagem <= 27) {
					classificacao = "Abaixo da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 28 && porcentagem <= 31) {
					classificacao = "Ruim (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 32) {
					classificacao = "Muito Ruim (POLLOCK & WILMORE, 1993)";
				}
			} else if (idade >= 56 && idade <= 65) {
				if (porcentagem >= 13 && porcentagem <= 19) {
					classificacao = "Excelente (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 20 && porcentagem <= 21) {
					classificacao = "Bom (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 22 && porcentagem <= 23) {
					classificacao = "Acima da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 24 && porcentagem <= 25) {
					classificacao = "Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 26 && porcentagem <= 27) {
					classificacao = "Abaixo da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 28 && porcentagem <= 31) {
					classificacao = "Ruim (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 32) {
					classificacao = "Muito Ruim (POLLOCK & WILMORE, 1993)";
				}
			}
		} else if (pacienteSelecionado.getSexo().equalsIgnoreCase("f")) {// Classificação
																			// mulheres
			if (idade >= 18 && idade <= 25) {
				if (porcentagem >= 13 && porcentagem <= 16) {
					classificacao = "Excelente (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 17 && porcentagem <= 19) {
					classificacao = "Bom (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 20 && porcentagem <= 22) {
					classificacao = "Acima da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 23 && porcentagem <= 25) {
					classificacao = "Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 26 && porcentagem <= 28) {
					classificacao = "Abaixo da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 29 && porcentagem <= 31) {
					classificacao = "Ruim (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 33) {
					classificacao = "Muito Ruim (POLLOCK & WILMORE, 1993)";
				}
			} else if (idade >= 26 && idade <= 35) {
				if (porcentagem >= 14 && porcentagem <= 17) {
					classificacao = "Excelente (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 18 && porcentagem <= 20) {
					classificacao = "Bom (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 21 && porcentagem <= 23) {
					classificacao = "Acima da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 24 && porcentagem <= 26) {
					classificacao = "Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 27 && porcentagem <= 30) {
					classificacao = "Abaixo da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 31 && porcentagem <= 35) {
					classificacao = "Ruim (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 36) {
					classificacao = "Muito Ruim (POLLOCK & WILMORE, 1993)";
				}
			} else if (idade >= 36 && idade <= 45) {
				if (porcentagem >= 16 && porcentagem <= 19) {
					classificacao = "Excelente (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 20 && porcentagem <= 23) {
					classificacao = "Bom (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 24 && porcentagem <= 26) {
					classificacao = "Acima da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 27 && porcentagem <= 29) {
					classificacao = "Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 30 && porcentagem <= 32) {
					classificacao = "Abaixo da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 33 && porcentagem <= 37) {
					classificacao = "Ruim (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 38) {
					classificacao = "Muito Ruim (POLLOCK & WILMORE, 1993)";
				}
			} else if (idade >= 46 && idade <= 55) {
				if (porcentagem >= 17 && porcentagem <= 22) {
					classificacao = "Excelente (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 23 && porcentagem <= 25) {
					classificacao = "Bom (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 26 && porcentagem <= 28) {
					classificacao = "Acima da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 29 && porcentagem <= 31) {
					classificacao = "Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 32 && porcentagem <= 34) {
					classificacao = "Abaixo da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 35 && porcentagem <= 38) {
					classificacao = "Ruim (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 39) {
					classificacao = "Muito Ruim (POLLOCK & WILMORE, 1993)";
				}
			} else if (idade >= 56 && idade <= 65) {
				if (porcentagem >= 18 && porcentagem <= 23) {
					classificacao = "Excelente (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 24 && porcentagem <= 26) {
					classificacao = "Bom (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 27 && porcentagem <= 29) {
					classificacao = "Acima da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 30 && porcentagem <= 32) {
					classificacao = "Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 33 && porcentagem <= 35) {
					classificacao = "Abaixo da Média (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 36 && porcentagem <= 38) {
					classificacao = "Ruim (POLLOCK & WILMORE, 1993)";
				} else if (porcentagem >= 39) {
					classificacao = "Muito Ruim (POLLOCK & WILMORE, 1993)";
				}
			} else
				return classificacao;

		}
		return classificacao;
	}
}
