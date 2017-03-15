/**
 * 
 */
package br.com.sisnutri.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Victor
 *
 */
public class Consulta {

	IntegerProperty idConsulta = new SimpleIntegerProperty();
	IntegerProperty crn = new SimpleIntegerProperty();
	IntegerProperty idAgenda = new SimpleIntegerProperty();
	IntegerProperty idAvaliacao = new SimpleIntegerProperty();
	StringProperty tecnica = new SimpleStringProperty();

	public Consulta(int idConsulta, int crn, int idAgenda, int idAvaliacao, String tecnica) {
		setIdConsulta(idConsulta);
		setCrn(crn);
		setIdAgenda(idAgenda);
		setIdAvaliacao(idAvaliacao);
		setTecnica(tecnica);
	}

	// Getters and Setters.
	public final IntegerProperty idConsultaProperty() {
		return this.idConsulta;
	}

	public final int getIdConsulta() {
		return this.idConsultaProperty().get();
	}

	public final void setIdConsulta(final int idConsulta) {
		this.idConsultaProperty().set(idConsulta);
	}

	public final IntegerProperty crnProperty() {
		return this.crn;
	}

	public final int getCrn() {
		return this.crnProperty().get();
	}

	public final void setCrn(final int crn) {
		this.crnProperty().set(crn);
	}

	public final IntegerProperty idAgendaProperty() {
		return this.idAgenda;
	}

	public final int getIdAgenda() {
		return this.idAgendaProperty().get();
	}

	public final void setIdAgenda(final int idAgenda) {
		this.idAgendaProperty().set(idAgenda);
	}

	public final IntegerProperty idAvaliacaoProperty() {
		return this.idAvaliacao;
	}

	public final int getIdAvaliacao() {
		return this.idAvaliacaoProperty().get();
	}

	public final void setIdAvaliacao(final int idAvaliacao) {
		this.idAvaliacaoProperty().set(idAvaliacao);
	}

	public final StringProperty tecnicaProperty() {
		return this.tecnica;
	}

	public final String getTecnica() {
		return this.tecnicaProperty().get();
	}

	public final void setTecnica(final String tecnica) {
		this.tecnicaProperty().set(tecnica);
	}

}
