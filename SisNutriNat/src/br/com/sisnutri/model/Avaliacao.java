package br.com.sisnutri.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Avaliacao {

	IntegerProperty idAvaliacao = new SimpleIntegerProperty();
	IntegerProperty idConsulta = new SimpleIntegerProperty();
	IntegerProperty idAnamnese = new SimpleIntegerProperty();
	IntegerProperty idAvClinica = new SimpleIntegerProperty();
	IntegerProperty idAvFisica = new SimpleIntegerProperty();
	StringProperty dataAvaliacao = new SimpleStringProperty();

	public Avaliacao(int idAvaliacao, int idConsulta, int idAnamnese, int idAvClinica, int idAvFisica,
			String dataAvaliacao) {

		setIdAvaliacao(idAvaliacao);
		setIdConsulta(idConsulta);
		setIdAnamnese(idAnamnese);
		setIdAvClinica(idAvClinica);
		setIdAvFisica(idAvFisica);
		setDataAvaliacao(dataAvaliacao);
	}
	// Getters and Setters

	public final IntegerProperty idAvaliacaoProperty() {
		return this.idAvaliacao;
	}

	public final int getIdAvaliacao() {
		return this.idAvaliacaoProperty().get();
	}

	public final void setIdAvaliacao(final int idAvaliacao) {
		this.idAvaliacaoProperty().set(idAvaliacao);
	}

	public final IntegerProperty idConsultaProperty() {
		return this.idConsulta;
	}

	public final int getIdConsulta() {
		return this.idConsultaProperty().get();
	}

	public final void setIdConsulta(final int idConsulta) {
		this.idConsultaProperty().set(idConsulta);
	}

	public final IntegerProperty idAnamneseProperty() {
		return this.idAnamnese;
	}

	public final int getIdAnamnese() {
		return this.idAnamneseProperty().get();
	}

	public final void setIdAnamnese(final int idAnamnese) {
		this.idAnamneseProperty().set(idAnamnese);
	}

	public final IntegerProperty idAvClinicaProperty() {
		return this.idAvClinica;
	}

	public final int getIdAvClinica() {
		return this.idAvClinicaProperty().get();
	}

	public final void setIdAvClinica(final int idAvClinica) {
		this.idAvClinicaProperty().set(idAvClinica);
	}

	public final IntegerProperty idAvFisicaProperty() {
		return this.idAvFisica;
	}

	public final int getIdAvFisica() {
		return this.idAvFisicaProperty().get();
	}

	public final void setIdAvFisica(final int idAvFisica) {
		this.idAvFisicaProperty().set(idAvFisica);
	}

	public final StringProperty dataAvaliacaoProperty() {
		return this.dataAvaliacao;
	}

	public final String getDataAvaliacao() {
		return this.dataAvaliacaoProperty().get();
	}

	public final void setDataAvaliacao(final String dataAvaliacao) {
		this.dataAvaliacaoProperty().set(dataAvaliacao);
	}

}
