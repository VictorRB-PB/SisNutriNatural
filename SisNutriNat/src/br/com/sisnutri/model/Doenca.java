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
public class Doenca {

	IntegerProperty idDoenca = new SimpleIntegerProperty();
	IntegerProperty idAvClinica = new SimpleIntegerProperty();
	StringProperty descricao = new SimpleStringProperty();
	StringProperty situacao = new SimpleStringProperty();
	StringProperty obs = new SimpleStringProperty();
	StringProperty tipoDoenca = new SimpleStringProperty();

	public Doenca(int idDoenca, int idAvClinica, String descricao, String situacao, String obs, String tipoDoenca) {
		setIdDoenca(idDoenca);
		setIdAvClinica(idAvClinica);
		setDescricao(descricao);
		setSituacao(situacao);
		setObs(obs);
		setTipoDoenca(tipoDoenca);
	}

	public final IntegerProperty idDoencaProperty() {
		return this.idDoenca;
	}

	public final int getIdDoenca() {
		return this.idDoencaProperty().get();
	}

	public final void setIdDoenca(final int idDoenca) {
		this.idDoencaProperty().set(idDoenca);
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

	public final StringProperty descricaoProperty() {
		return this.descricao;
	}

	public final String getDescricao() {
		return this.descricaoProperty().get();
	}

	public final void setDescricao(final String descricao) {
		this.descricaoProperty().set(descricao);
	}

	public final StringProperty situacaoProperty() {
		return this.situacao;
	}

	public final String getSituacao() {
		return this.situacaoProperty().get();
	}

	public final void setSituacao(final String situacao) {
		this.situacaoProperty().set(situacao);
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

	public final StringProperty tipoDoencaProperty() {
		return this.tipoDoenca;
	}

	public final String getTipoDoenca() {
		return this.tipoDoencaProperty().get();
	}

	public final void setTipoDoenca(final String tipoDoenca) {
		this.tipoDoencaProperty().set(tipoDoenca);
	}

}
