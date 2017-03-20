/**
 * 
 */
package br.com.sisnutri.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import br.com.sisnutri.dao.PacienteDao;
import br.com.sisnutri.model.Funcionario;
import br.com.sisnutri.model.Paciente;
import br.com.sisnutri.util.DateUtil;
import br.com.sisnutri.view.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

/**
 * @author Victor
 *
 */
public class CadastroPacienteController implements Initializable {

	MainApp mainApp;
	Funcionario funcAtual;
	private Paciente pacSelecionado;
	private PacienteDao pacDao;
	private ObservableList<Paciente> listaPaciente;

	@FXML
	private TextField txNome;
	@FXML
	private TextField txCpf;
	@FXML
	private TextField txDataNasc;
	@FXML
	private TextField txCep;
	@FXML
	private TextField txEstado;
	@FXML
	private TextField txCidade;
	@FXML
	private TextField txEndereco;
	@FXML
	private TextField txBairro;
	@FXML
	private TextField txEmail;
	@FXML
	private TextField txTel;
	@FXML
	private TextField txCel;
	@FXML
	private TextArea txObs;
	@FXML
	private TextField txPesquisar;
	@FXML
	private CheckBox cGestante;
	@FXML
	private RadioButton rbMasculino;
	@FXML
	private RadioButton rbFeminino;
	@FXML
	private RadioButton rbAtivado;
	@FXML
	private RadioButton rbDesativado;
	@FXML
	private Button btDesativar;
	@FXML
	private TableView<Paciente> tbPac;
	@FXML
	private TableColumn<Paciente, String> clNome;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		clNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		// bindConfig();
		tbPac.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPacDetail(newValue));
	}

	// Botão AGENDAR CONSULTA
	@FXML
	private void scheduleConsulta() {
		if (pacSelecionado != null) {
			this.mainApp.initAgenda(funcAtual, pacSelecionado);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Agenda");
			alert.setHeaderText("Agendamento de consulta");
			alert.setContentText("Selecione um paciente para agendar consulta");
			alert.show();
		}
	}

	// Botão ADICIONAR
	@FXML
	private void addPac() {
		showPacDetail(null);
		txNome.requestFocus();
	}

	// Botão GRAVAR.
	@FXML
	private void savePac() {
		if (verifyData()) {
			if (pacSelecionado != null) {
				try {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Alterar");
					alert.setHeaderText("Alterar Paciente: " + pacSelecionado.getNome());
					alert.setContentText("Deseja realmente alterar este Paciente?");

					ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
					ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
					alert.getButtonTypes().setAll(yesButton, noButton);
					Optional<ButtonType> result = alert.showAndWait();

					if (result.get() == yesButton) {
						updateFunc(pacSelecionado);
						Alert alert2 = new Alert(AlertType.INFORMATION);
						alert2.setTitle("Alterar");
						alert2.setHeaderText("Alterar Paciente: " + pacSelecionado.getNome());
						alert2.setContentText("Alterado com sucesso");
						alert2.show();
					} else {
						atualizaTabela();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				newPac(pacSelecionado);
			}
		}

	}

	// Botão DESABILITAR
	@FXML
	private void disablePac() {
		if (pacSelecionado != null) {
			desativaPac(pacSelecionado);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Desativar");
			alert.setHeaderText("Paciente invalido");
			alert.setContentText("Selecione um paciente para desativar");
			alert.show();
		}
	}

	// TextField para pesquisar paciente.
	@FXML
	private void findPac() {
		if (!txPesquisar.getText().equals("")) {
			try {
				tbPac.setItems(pesquisarPac());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			atualizaTabela();
		}
	}

	// Metodo para agendar consulta ao paciente selecionado.

	// Metodo para exibir paciente que foi selecionado na tabela para os
	// TextFields, campos
	// em branco se não houver selecionado.
	private void showPacDetail(Paciente pac) {
		if (pac != null) {
			// Prenche os textfields com informação do funcionario selecionado
			txNome.setText(pac.getNome());
			txCpf.setText(pac.getCpf());
			txDataNasc.setText(DateUtil.format(pac.getDataNasc()));
			txCep.setText(String.valueOf(pac.getCep()));
			txEstado.setText(pac.getEstado());
			txCidade.setText(pac.getCidade());
			txEndereco.setText(pac.getEndereco());
			txBairro.setText(pac.getBairro());
			txEmail.setText(pac.getEmail());
			txTel.setText(pac.getTel());
			txCel.setText(pac.getCel());
			txObs.setText(pac.getObs());
			if (pac.getSexo().equalsIgnoreCase("m")) {
				rbMasculino.setSelected(true);
			} else {
				rbFeminino.setSelected(true);
			}
			if (pac.isGestante()) {
				cGestante.setSelected(true);
			} else {
				cGestante.setSelected(false);
			}
			if (pac.isAtivo()) {
				rbAtivado.setSelected(true);
				btDesativar.setText("Desativar");
			} else {
				rbDesativado.setSelected(true);
				btDesativar.setText("Ativar");
			}

			pacSelecionado = pac;

		} else {
			txNome.setText(null);
			txCpf.setText(null);
			txDataNasc.setText(null);
			txCep.setText(null);
			txEstado.setText(null);
			txCidade.setText(null);
			txEndereco.setText(null);
			txBairro.setText(null);
			txEmail.setText(null);
			txTel.setText(null);
			txCel.setText(null);
			txObs.setText(null);
			rbMasculino.setSelected(true);
			rbAtivado.setSelected(true);
			tbPac.getSelectionModel().clearSelection();
			btDesativar.setText("Desativar");
			pacSelecionado = null;
		}
	}

	// Metodo para pegar informações na tela e ATUALIZAR paciente.
	private void updateFunc(Paciente pac) throws SQLException {
		Paciente pacTemp = pac;

		pacTemp.setNome(txNome.getText());
		if (rbMasculino.isSelected()) {
			pacTemp.setSexo("m");
		} else {
			pacTemp.setSexo("f");
		}
		pacTemp.setDataNasc(DateUtil.parse(txDataNasc.getText()));
		pacTemp.setCep(Integer.parseInt(txCep.getText()));
		pacTemp.setEstado(txEstado.getText());
		pacTemp.setCidade(txCidade.getText());
		pacTemp.setEndereco(txEndereco.getText());
		pacTemp.setBairro(txBairro.getText());
		pacTemp.setEmail(txEmail.getText());
		pacTemp.setTel(txTel.getText());
		pacTemp.setCel(txCel.getText());
		if (rbAtivado.isSelected()) {
			pacTemp.setAtivo(true);
		} else {
			pacTemp.setAtivo(false);
		}
		pacTemp.setObs(txObs.getText());

		pacDao = new PacienteDao();
		pacDao.update(pacTemp);
		atualizaTabela();

	}

	// Metodo para pegar informações na tela e ADICIONAR novo paciente.
	private void newPac(Paciente newPac) {
		// TODO Auto-generated method stub

		int idPront = 0;
		String nome = txNome.getText();
		String sexo;
		if (rbMasculino.isSelected()) {
			sexo = "m";
		} else {
			sexo = "f";
		}
		boolean gestante = false;
		if (cGestante.isSelected()) {
			gestante = true;
		}
		String cpf = txCpf.getText();
		LocalDate dataNasc = DateUtil.parse(txDataNasc.getText());
		int cep = Integer.valueOf(txCep.getText());
		String estado = txEstado.getText();
		String cidade = txCidade.getText();
		String endereco = txEndereco.getText();
		String bairro = txBairro.getText();
		String email = txEmail.getText();
		String tel = txTel.getText();
		String cel = txCel.getText();
		String obs = txObs.getText();

		newPac = new Paciente(0, idPront, nome, cpf, sexo, gestante, dataNasc, cep, estado, cidade, endereco, bairro,
				email, tel, cel, obs, true);
		try {
			pacDao = new PacienteDao();
			pacDao.insert(newPac);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		atualizaTabela();
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Adicionar");
		alert.setHeaderText("Paciente: " + newPac.getNome());
		alert.setContentText("Adicionado com sucesso");
		alert.show();

	}

	// Metodo para DESATIVAR paciente selecionado.
	private void desativaPac(Paciente pac) {
		if (pac != null) {
			if (rbAtivado.isSelected()) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Desativar");
				alert.setHeaderText("Desativar Paciente: " + pacSelecionado.getNome());
				alert.setContentText("Deseja realmente desativar este Paciente?");
				ButtonType yesButton = new ButtonType("Sim", ButtonData.YES);
				ButtonType noButton = new ButtonType("Não", ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(yesButton, noButton);
				Optional<ButtonType> result = alert.showAndWait();

				if (result.get() == yesButton) {
					pac.setAtivo(false);
				}
			} else {
				pac.setAtivo(true);
			}
		}
		try {
			pacDao = new PacienteDao();
			pacDao.disablePac(pac);
			atualizaTabela();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Metodo para PESQUISAR paciente na tabela por NOME ou CPF
	private ObservableList<Paciente> pesquisarPac() throws SQLException {
		// TODO Auto-generated method stub
		ObservableList<Paciente> listaEncontrados = FXCollections.observableArrayList();
		ObservableList<Paciente> listaPacs = getListaPacientes();
		for (Paciente pac : listaPacs) {
			if (pac.getNome().toLowerCase().contains(txPesquisar.getText().toLowerCase())
					|| pac.getCpf().contains(txPesquisar.getText())) {
				listaEncontrados.add(pac);
			}
		}

		return listaEncontrados;
	}

	// Metodo para verificar se os campos estão preenchidos.
	private boolean verifyData() {
		String errorMessage = "";

		if (txNome.getText() == null || txNome.getText().length() == 0) {
			errorMessage += "Nome inválido\n";
		}
		if (txCpf.getText() == null || txCpf.getText().length() == 0) {
			errorMessage += "CPF invalido";
		}
		if (txDataNasc.getText() == null || txDataNasc.getText().length() == 0) {
			errorMessage += "Data de nascimento inválido\n";
		}
		// } else {
		// // tenta converter o código postal em um int.
		// try {
		// Integer.parseInt(postalCodeField.getText());
		// } catch (NumberFormatException e) {
		// errorMessage += "Código Postal inválido (deve ser um inteiro)!\n";
		// }
		// }

		if (txCep.getText() == null || txCep.getText().length() == 0) {
			errorMessage += "CEP inválida\n";
		}
		if (txEstado.getText() == null || txEstado.getText().length() == 0) {
			errorMessage += "Estado inválido\n";
		} // else {
			// if (!DateUtil.validDate(birthdayField.getText())) {
			// errorMessage += "Aniversário inválido. Use o formato
			// dd/mm/yyyy\n";
			// }
			// }

		if (txCidade.getText() == null || txCidade.getText().length() == 0) {
			errorMessage += "Cidade inválida\n";
		}
		if (txEndereco.getText() == null || txEndereco.getText().length() == 0) {
			errorMessage += "Endereço inválida\n";
		}
		if (txBairro.getText() == null || txBairro.getText().length() == 0) {
			errorMessage += "Bairro inválida\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Mostra a mensagem de erro.
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campos Inválidos");
			alert.setHeaderText("Por favor, corrija os campos inválidos");
			alert.setContentText(errorMessage);
			alert.showAndWait();

			return false;
		}

	}

	// Metodo setMainApp para receber o controller da classe view controladora
	// principal MainApp.
	public void setMainApp(MainApp mainApp, Funcionario func) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
		this.funcAtual = func;
		atualizaTabela();
		showPacDetail(null);
	}

	// Atualiza a tabela de paciente.
	private void atualizaTabela() {
		int index = tbPac.getSelectionModel().getSelectedIndex();
		try {
			tbPac.setItems(getListaPacientes());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tbPac.getSelectionModel().select(index);
		showPacDetail(pacSelecionado);

	}

	// Metodo para retorna uma lista de paciente.
	private ObservableList<Paciente> getListaPacientes() throws SQLException {
		PacienteDao pacDao = new PacienteDao();
		listaPaciente = FXCollections.observableArrayList();
		listaPaciente.setAll(pacDao.listaPac());
		return listaPaciente;
	}

}
