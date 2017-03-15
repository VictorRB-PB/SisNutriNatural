/**
 * 
 */
package br.com.sisnutri.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author Victor
 *
 */
public class AvClinica {

	IntegerProperty idAvClinica = new SimpleIntegerProperty();
	IntegerProperty idAvaliacao = new SimpleIntegerProperty();

	public AvClinica(int idAvClinica, int idAvalicao) {
		setIdAvClinica(idAvClinica);
		setIdAvaliacao(idAvalicao);
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

	public final IntegerProperty idAvaliacaoProperty() {
		return this.idAvaliacao;
	}

	public final int getIdAvalicao() {
		return this.idAvaliacaoProperty().get();
	}

	public final void setIdAvaliacao(final int idAvaliacao) {
		this.idAvaliacaoProperty().set(idAvaliacao);
	}

}
