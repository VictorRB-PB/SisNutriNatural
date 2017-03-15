/**
 * 
 */
package br.com.sisnutri.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import br.com.sisnutri.dao.FuncionarioDao;
import br.com.sisnutri.model.Funcionario;
import br.com.sisnutri.util.DateUtil;
import br.com.sisnutri.view.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @author Victor
 *
 */
public class CadastroFuncionarioController implements Initializable {

	MainApp mainApp;
	Funcionario funcAtual;
	private Funcionario funcSelecionado;
	private FuncionarioDao funcDao;
	private ObservableList<Funcionario> listaFunc;

	@FXML
	private TextField txCrn;
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
	private TextField txDataAdmiss;
	@FXML
	private TextField txDataDemiss;
	@FXML
	private TextArea txObs;
	@FXML
	private TextField txPesquisar;
	@FXML
	private PasswordField pfSenha;
	// @FXML
	// private ComboBox<T> cbTipoFunc;
	@FXML
	private CheckBox cMostrar;
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
	private TableView<Funcionario> tbFunc;
	@FXML
	private TableColumn<Funcionario, String> clNome;

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
		tbFunc.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showFuncDetail(newValue));

	}

	// Botão ADICIONAR
	@FXML
	public void addFunc() {
		showFuncDetail(null);
		txNome.requestFocus();
	}

	// Botão GRAVAR.
	@FXML
	public void saveFunc() {
		if (verifyData()) {
			if (funcSelecionado != null) {
				try {
					int resp = JOptionPane.showConfirmDialog(null,
							"Deseja realmente fazer alterações no funcionario: " + funcSelecionado.getNome() + "?",
							"Alterar Funcionario", JOptionPane.YES_NO_OPTION);
					if (resp == 0) {
						updateFunc(funcSelecionado);
					} else {
						atualizaTabela();
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				newFunc(funcSelecionado);
			}
		}

	}

	// Botão DESABILITAR
	@FXML
	public void disableFunc() {
		if (funcSelecionado != null) {
			desativaFunc(funcSelecionado);
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um funcionario para poder desativar",
					"Desativar Funcionario", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// TextField para pesquisar funcionario.
	@FXML
	public void findFunc() {
		if (!txPesquisar.getText().equals("")) {
			try {
				tbFunc.setItems(pesquisarFunc());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			atualizaTabela();
		}
	}

	// Metodo para exibir funcionario selecionado na tabela nos TextFields,
	// campos em branco se funcionario não for selecionado.
	private void showFuncDetail(Funcionario func) {
		if (func != null) {
			// Prenche os textfields com informação do funcionario selecionado
			txNome.setText(func.getNome());
			txCpf.setText(func.getCpf());
			txDataNasc.setText(DateUtil.format(func.getDataNasc()));
			pfSenha.setText(func.getSenha());
			txCep.setText(String.valueOf(func.getCep()));
			txEstado.setText(func.getEstado());
			txCidade.setText(func.getCidade());
			txEndereco.setText(func.getEndereco());
			txBairro.setText(func.getBairro());
			txEmail.setText(func.getEmail());
			txTel.setText(func.getTel());
			txCel.setText(func.getCel());
			txDataAdmiss.setText(DateUtil.format(func.getDataAdmissao()));
			if (func.getDataDemissao() != null) {
				txDataDemiss.setText(DateUtil.format(func.getDataDemissao()));
			} else {
				txDataDemiss.setText(null);
			}
			txObs.setText(func.getObs());
			if (func.getSexo().equalsIgnoreCase("m")) {
				rbMasculino.setSelected(true);
			} else {
				rbFeminino.setSelected(true);
			}
			if (func.isAtivo()) {
				rbAtivado.setSelected(true);
				btDesativar.setText("Desativar");
			} else {
				rbDesativado.setSelected(true);
				btDesativar.setText("Ativar");
			}

			funcSelecionado = func;

		} else {
			txNome.setText(null);
			txCpf.setText(null);
			txDataNasc.setText(null);
			pfSenha.setText(null);
			txCep.setText(null);
			txEstado.setText(null);
			txCidade.setText(null);
			txEndereco.setText(null);
			txBairro.setText(null);
			txEmail.setText(null);
			txTel.setText(null);
			txCel.setText(null);
			txDataAdmiss.setText(null);
			txDataDemiss.setText(null);
			txObs.setText(null);
			rbMasculino.setSelected(true);
			rbAtivado.setSelected(true);
			tbFunc.getSelectionModel().clearSelection();
			btDesativar.setText("Desativar");
			funcSelecionado = null;
		}
	}

	// Metodo para pegar informações na tela e ATUALIZAR funcionario.
	private void updateFunc(Funcionario func) throws SQLException {
		Funcionario funcTemp = func;

		funcTemp.setNome(txNome.getText());
		funcTemp.setSenha(pfSenha.getText());
		if (rbMasculino.isSelected()) {
			funcTemp.setSexo("m");
		} else {
			funcTemp.setSexo("f");
		}
		funcTemp.setDataNasc(DateUtil.parse(txDataNasc.getText()));
		funcTemp.setCep(Integer.parseInt(txCep.getText()));
		funcTemp.setEstado(txEstado.getText());
		funcTemp.setCidade(txCidade.getText());
		funcTemp.setEndereco(txEndereco.getText());
		funcTemp.setBairro(txBairro.getText());
		funcTemp.setEmail(txEmail.getText());
		funcTemp.setTel(txTel.getText());
		funcTemp.setCel(txCel.getText());
		funcTemp.setDataAdmissao(DateUtil.parse(txDataAdmiss.getText()));
		if (rbAtivado.isSelected()) {
			funcTemp.setAtivo(true);
			funcTemp.setDataDemissao(null);
		} else {
			funcTemp.setAtivo(false);
			funcTemp.setDataDemissao(DateUtil.parse(txDataDemiss.getText()));
		}
		funcTemp.setObs(txObs.getText());

		funcDao = new FuncionarioDao();
		funcDao.update(funcTemp);
		atualizaTabela();

	}

	// Metodo para pegar informações na tela e ADICIONAR novo funcionario.
	private void newFunc(Funcionario funcSelecionado) {
		// TODO Auto-generated method stub

		String nome = txNome.getText();
		String sexo;
		if (rbMasculino.isSelected()) {
			sexo = "m";
		} else {
			sexo = "f";
		}
		String cpf = txCpf.getText();
		String senha = pfSenha.getText();
		LocalDate dataNasc = DateUtil.parse(txDataNasc.getText());
		int cep = Integer.valueOf(txCep.getText());
		String estado = txEstado.getText();
		String cidade = txCidade.getText();
		String endereco = txEndereco.getText();
		String bairro = txBairro.getText();
		String email = txEmail.getText();
		String tel = txTel.getText();
		String cel = txCel.getText();
		// funcSelecionado.setTipoFunc();
		LocalDate dataAdmiss = DateUtil.parse(txDataAdmiss.getText());
		String obs = txObs.getText();
		int crn;
		if (txCrn.getText().length() > 1) {
			crn = Integer.parseInt(txCrn.getText());
		} else {
			crn = 0;
		}

		funcSelecionado = new Funcionario(0, crn, nome, cpf, senha, sexo, dataNasc, cep, estado, cidade, endereco,
				bairro, email, tel, cel, "nutricionista", dataAdmiss, null, obs, true);
		try {
			funcDao = new FuncionarioDao();
			funcDao.insert(funcSelecionado);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		atualizaTabela();

	}

	// Metodo para DESATIVAR funcionario selecionado.
	private void desativaFunc(Funcionario func) {
		if (func != null) {
			if (rbAtivado.isSelected()) {
				int resp = JOptionPane.showConfirmDialog(null, "Deseja realmente desativar este funcionario?",
						"Desativar Funcionario", JOptionPane.YES_NO_OPTION);
				if (resp == 0) {
					func.setAtivo(false);
				}

			} else {
				func.setAtivo(true);
			}
		}
		try {
			funcDao = new FuncionarioDao();
			funcDao.disableFunc(func);
			atualizaTabela();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Metodo para PESQUISAR funcionarios na tabela por NOME ou CPF
	private ObservableList<Funcionario> pesquisarFunc() throws SQLException {
		// TODO Auto-generated method stub
		ObservableList<Funcionario> listaEncontrados = FXCollections.observableArrayList();
		ObservableList<Funcionario> listaFuncs = getListaFunc();
		for (Funcionario func : listaFuncs) {
			if (func.getNome().toLowerCase().contains(txPesquisar.getText().toLowerCase())
					|| func.getCpf().contains(txPesquisar.getText())) {
				listaEncontrados.add(func);
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
		if (pfSenha.getText() == null || pfSenha.getText().length() == 0) {
			errorMessage += "Senha inválida\n";
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
		if (txDataAdmiss.getText() == null || txDataAdmiss.getText().length() == 0) {
			errorMessage += "Data de Admissão inválida\n";
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
	// principal MainApp
	public void setMainApp(MainApp mainApp, Funcionario func) {
		// TODO Auto-generated method stub
		this.mainApp = mainApp;
		this.funcAtual = func;
		atualizaTabela();
		showFuncDetail(null);
	}

	// Atualiza a tabela de funcionarios.
	private void atualizaTabela() {
		try {
			int index = tbFunc.getSelectionModel().getSelectedIndex();
			tbFunc.setItems(getListaFunc());
			tbFunc.getSelectionModel().select(index);
			showFuncDetail(funcSelecionado);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Retorna lista de funcionarios
	private ObservableList<Funcionario> getListaFunc() throws SQLException {
		funcDao = new FuncionarioDao();
		listaFunc = FXCollections.observableArrayList();
		listaFunc.setAll(funcDao.listaFunc());
		return listaFunc;
	}

	// public void bindConfig() {
	//
	// // esse binding só e false quando os campos da tela estão preenchidos
	// BooleanBinding camposPreenchidos =
	// txNome.textProperty().isEmpty().or(txCpf.textProperty().isEmpty()
	// .or(pfSenha.textProperty().isEmpty().or(txDataNasc.textProperty().isEmpty()
	// .or(txCep.textProperty().isEmpty().or(txEstado.textProperty().isEmpty().or(txCidade
	// .textProperty().isEmpty().or(txEndereco.textProperty().isEmpty()
	// .or(txBairro.textProperty().isEmpty()))))))));
	// // indica se há algo selecionado na tabela
	// BooleanBinding algoSelecionado =
	// tbFunc.getSelectionModel().selectedItemProperty().isNull();
	// // alguns botões só são habilitados se algo foi selecionado na tabela
	// initBinds(algoSelecionado);
	// // o botão salvar só é habilitado se as informações foram preenchidas e
	// // não tem nada na tela
	// //
	// btnSalvar.disableProperty().bind(algoSelecionado.not().or(camposPreenchidos));
	//
	// }
	//
	// public void initBinds(BooleanBinding algoSelecionado) {
	//
	// txNome.disableProperty().bind(algoSelecionado.not());
	// rbMasculino.disableProperty().bind(algoSelecionado.not());
	// rbFeminino.disableProperty().bind(algoSelecionado.not());
	// txCpf.disableProperty().bind(algoSelecionado.not());
	// pfSenha.disableProperty().bind(algoSelecionado.not());
	// cMostrar.disableProperty().bind(algoSelecionado.not());
	// txDataNasc.disableProperty().bind(algoSelecionado.not());
	// txCep.disableProperty().bind(algoSelecionado.not());
	// txEstado.disableProperty().bind(algoSelecionado.not());
	// txCidade.disableProperty().bind(algoSelecionado.not());
	// txEndereco.disableProperty().bind(algoSelecionado.not());
	// txBairro.disableProperty().bind(algoSelecionado.not());
	// txEmail.disableProperty().bind(algoSelecionado.not());
	// txTel.disableProperty().bind(algoSelecionado.not());
	// txCel.disableProperty().bind(algoSelecionado.not());
	// txDataAdmiss.disableProperty().bind(algoSelecionado.not());
	// txDataDemiss.disableProperty().bind(algoSelecionado.not());
	// rbAtivado.disableProperty().bind(algoSelecionado.not());
	// rbDesativado.disableProperty().bind(algoSelecionado.not());
	// txObs.disableProperty().bind(algoSelecionado.not());
	// btDesativar.disableProperty().bind(algoSelecionado);
	// btGravar.disableProperty().bind(algoSelecionado.not());
	//
	// }

}
