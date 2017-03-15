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
public class AvFisica {

	IntegerProperty idAvFisica = new SimpleIntegerProperty();
	IntegerProperty idAvaliacao = new SimpleIntegerProperty();

	public AvFisica(int idAvFisica, int idAvaliacao) {
		setIdAvFisica(idAvFisica);
		setIdAvaliacao(idAvaliacao);
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

	public final IntegerProperty idAvaliacaoProperty() {
		return this.idAvaliacao;
	}

	public final int getIdAvaliacao() {
		return this.idAvaliacaoProperty().get();
	}

	public final void setIdAvaliacao(final int idAvaliacao) {
		this.idAvaliacaoProperty().set(idAvaliacao);
	}

}
