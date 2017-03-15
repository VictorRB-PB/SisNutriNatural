package br.com.sisnutri.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Agenda {

	IntegerProperty idConsultaAgendada = new SimpleIntegerProperty();
	IntegerProperty idFuncionario = new SimpleIntegerProperty();
	IntegerProperty idPaciente = new SimpleIntegerProperty();
	ObjectProperty<LocalDate> dataConsulta = new SimpleObjectProperty<>();
	StringProperty tipoConsulta = new SimpleStringProperty();
	StringProperty statusConsulta = new SimpleStringProperty();
	StringProperty obs = new SimpleStringProperty();

	public Agenda(int idConsultaAgendada, int idFuncionario, int idPaciente, LocalDate dataConsulta,
			String tipoConsulta, String statusConsulta, String obs) {

		setIdConsultaAgendada(idConsultaAgendada);
		setIdFuncionario(idFuncionario);
		setIdPaciente(idPaciente);
		setDataConsulta(dataConsulta);
		setTipoConsulta(tipoConsulta);
		setStatusConsulta(statusConsulta);
		setObs(obs);

	}

	public final IntegerProperty idConsultaAgendadaProperty() {
		return this.idConsultaAgendada;
	}

	public final int getIdConsultaAgendada() {
		return this.idConsultaAgendadaProperty().get();
	}

	public final void setIdConsultaAgendada(final int idConsultaAgendada) {
		this.idConsultaAgendadaProperty().set(idConsultaAgendada);
	}

	public final IntegerProperty idFuncionarioProperty() {
		return this.idFuncionario;
	}

	public final int getIdFuncionario() {
		return this.idFuncionarioProperty().get();
	}

	public final void setIdFuncionario(final int idFuncionario) {
		this.idFuncionarioProperty().set(idFuncionario);
	}

	public final IntegerProperty idPacienteProperty() {
		return this.idPaciente;
	}

	public final int getIdPaciente() {
		return this.idPacienteProperty().get();
	}

	public final void setIdPaciente(final int idPaciente) {
		this.idPacienteProperty().set(idPaciente);
	}

	public final ObjectProperty<LocalDate> dataConsultaProperty() {
		return this.dataConsulta;
	}

	public final LocalDate getDataConsulta() {
		return this.dataConsultaProperty().get();
	}

	public final void setDataConsulta(final LocalDate dataConsulta) {
		this.dataConsultaProperty().set(dataConsulta);
	}

	public final StringProperty tipoConsultaProperty() {
		return this.tipoConsulta;
	}

	public final String getTipoConsulta() {
		return this.tipoConsultaProperty().get();
	}

	public final void setTipoConsulta(final String tipoConsulta) {
		this.tipoConsultaProperty().set(tipoConsulta);
	}

	public final StringProperty statusConsultaProperty() {
		return this.statusConsulta;
	}

	public final String getStatusConsulta() {
		return this.statusConsultaProperty().get();
	}

	public final void setStatusConsulta(final String statusConsulta) {
		this.statusConsultaProperty().set(statusConsulta);
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

}
