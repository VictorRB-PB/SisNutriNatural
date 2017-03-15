package br.com.sisnutri.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Exame {

	IntegerProperty idExame = new SimpleIntegerProperty();
	IntegerProperty idAvCli = new SimpleIntegerProperty();
	StringProperty descricao = new SimpleStringProperty();
	StringProperty valorRef = new SimpleStringProperty();
	StringProperty interpretacao = new SimpleStringProperty();

	public Exame(int idExame, int idAvCli, String descricao, String valorRef, String interpretacao) {
		setIdExame(idExame);
		setIdAvCli(idAvCli);
		setDescricao(descricao);
		setValorRef(valorRef);
		setInterpretacao(interpretacao);

	}

	public final IntegerProperty idExameProperty() {
		return this.idExame;
	}

	public final int getIdExame() {
		return this.idExameProperty().get();
	}

	public final void setIdExame(final int idExame) {
		this.idExameProperty().set(idExame);
	}

	public final IntegerProperty idAvCliProperty() {
		return this.idAvCli;
	}

	public final int getIdAvCli() {
		return this.idAvCliProperty().get();
	}

	public final void setIdAvCli(final int idAvCli) {
		this.idAvCliProperty().set(idAvCli);
	}

	public final StringProperty descricaoProperty() {
		return this.descricao;
	}

	public final String getDescricao() {
		return this.descricaoProperty().get();
	}

	public final void setDescricao(final String descricao) {
		this.descricaoProperty().set(descricao);
	}

	public final StringProperty valorRefProperty() {
		return this.valorRef;
	}

	public final String getValorRef() {
		return this.valorRefProperty().get();
	}

	public final void setValorRef(final String valorRef) {
		this.valorRefProperty().set(valorRef);
	}

	public final StringProperty interpretacaoProperty() {
		return this.interpretacao;
	}

	public final String getInterpretacao() {
		return this.interpretacaoProperty().get();
	}

	public final void setInterpretacao(final String interpretacao) {
		this.interpretacaoProperty().set(interpretacao);
	}

}
