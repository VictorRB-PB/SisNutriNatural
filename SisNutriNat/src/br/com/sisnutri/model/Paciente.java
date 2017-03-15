/**
 * 
 */
package br.com.sisnutri.model;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Victor
 *
 */
public class Paciente {

	private IntegerProperty idPac = new SimpleIntegerProperty();
	private IntegerProperty idPront = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty cpf = new SimpleStringProperty();
	private StringProperty sexo = new SimpleStringProperty();
	private BooleanProperty gestante = new SimpleBooleanProperty();
	private ObjectProperty<LocalDate> dataNasc = new SimpleObjectProperty<>();
	private IntegerProperty cep = new SimpleIntegerProperty();
	private StringProperty estado = new SimpleStringProperty();
	private StringProperty cidade = new SimpleStringProperty();
	private StringProperty endereco = new SimpleStringProperty();
	private StringProperty bairro = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	private StringProperty tel = new SimpleStringProperty();
	private StringProperty cel = new SimpleStringProperty();
	private StringProperty obs = new SimpleStringProperty();
	private BooleanProperty ativo = new SimpleBooleanProperty();

	public Paciente(int idPac, int idPront, String nome, String cpf, String sexo, boolean gestante, LocalDate dataNasc,
			int cep, String estado, String cidade, String endereco, String bairro, String email, String tel, String cel,
			String obs, boolean ativo) {

		setIdPac(idPac);
		setIdPront(idPront);
		setNome(nome);
		setCpf(cpf);
		setSexo(sexo);
		setGestante(gestante);
		setDataNasc(dataNasc);
		setCep(cep);
		setEstado(estado);
		setCidade(cidade);
		setEndereco(endereco);
		setBairro(bairro);
		setEmail(email);
		setTel(tel);
		setCel(cel);
		setObs(obs);
		setAtivo(ativo);
	}

	// Getters and Setters
	public final IntegerProperty idPacProperty() {
		return this.idPac;
	}

	public final int getIdPac() {
		return this.idPacProperty().get();
	}

	public final void setIdPac(final int idPac) {
		this.idPacProperty().set(idPac);
	}

	public final IntegerProperty idProntProperty() {
		return this.idPront;
	}

	public final int getIdPront() {
		return this.idProntProperty().get();
	}

	public final void setIdPront(final int idPront) {
		this.idProntProperty().set(idPront);
	}

	public final StringProperty nomeProperty() {
		return this.nome;
	}

	public final String getNome() {
		return this.nomeProperty().get();
	}

	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}

	public final StringProperty cpfProperty() {
		return this.cpf;
	}

	public final String getCpf() {
		return this.cpfProperty().get();
	}

	public final void setCpf(final String cpf) {
		this.cpfProperty().set(cpf);
	}

	public final StringProperty sexoProperty() {
		return this.sexo;
	}

	public final String getSexo() {
		return this.sexoProperty().get();
	}

	public final void setSexo(final String sexo) {
		this.sexoProperty().set(sexo);
	}

	public final BooleanProperty gestanteProperty() {
		return this.gestante;
	}

	public final boolean isGestante() {
		return this.gestanteProperty().get();
	}

	public final void setGestante(final boolean gestante) {
		this.gestanteProperty().set(gestante);
	}

	public final ObjectProperty<LocalDate> dataNascProperty() {
		return this.dataNasc;
	}

	public final LocalDate getDataNasc() {
		return this.dataNascProperty().get();
	}

	public final void setDataNasc(final LocalDate dataNasc) {
		this.dataNascProperty().set(dataNasc);
	}

	public final IntegerProperty cepProperty() {
		return this.cep;
	}

	public final int getCep() {
		return this.cepProperty().get();
	}

	public final void setCep(final int cep) {
		this.cepProperty().set(cep);
	}

	public final StringProperty estadoProperty() {
		return this.estado;
	}

	public final String getEstado() {
		return this.estadoProperty().get();
	}

	public final void setEstado(final String estado) {
		this.estadoProperty().set(estado);
	}

	public final StringProperty cidadeProperty() {
		return this.cidade;
	}

	public final String getCidade() {
		return this.cidadeProperty().get();
	}

	public final void setCidade(final String cidade) {
		this.cidadeProperty().set(cidade);
	}

	public final StringProperty enderecoProperty() {
		return this.endereco;
	}

	public final String getEndereco() {
		return this.enderecoProperty().get();
	}

	public final void setEndereco(final String endereco) {
		this.enderecoProperty().set(endereco);
	}

	public final StringProperty bairroProperty() {
		return this.bairro;
	}

	public final String getBairro() {
		return this.bairroProperty().get();
	}

	public final void setBairro(final String bairro) {
		this.bairroProperty().set(bairro);
	}

	public final StringProperty emailProperty() {
		return this.email;
	}

	public final String getEmail() {
		return this.emailProperty().get();
	}

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}

	public final StringProperty telProperty() {
		return this.tel;
	}

	public final String getTel() {
		return this.telProperty().get();
	}

	public final void setTel(final String tel) {
		this.telProperty().set(tel);
	}

	public final StringProperty celProperty() {
		return this.cel;
	}

	public final String getCel() {
		return this.celProperty().get();
	}

	public final void setCel(final String cel) {
		this.celProperty().set(cel);
	}

	public final StringProperty obsProperty() {
		return this.obs;
	}

	public final String getObs() {
		return this.obsProperty().get();
	}

	public final void setObs(final String obs) {
		this.obsProperty().set(obs);
	}

	public final BooleanProperty ativoProperty() {
		return this.ativo;
	}

	public final boolean isAtivo() {
		return this.ativoProperty().get();
	}

	public final void setAtivo(final boolean ativo) {
		this.ativoProperty().set(ativo);
	}

}
