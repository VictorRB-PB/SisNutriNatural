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
public class Farmaco {

	IntegerProperty idFarcamo = new SimpleIntegerProperty();
	IntegerProperty idAvCli = new SimpleIntegerProperty();
	StringProperty descricao = new SimpleStringProperty();
	StringProperty subsAtiva = new SimpleStringProperty();
	StringProperty obs = new SimpleStringProperty();

	public Farmaco(int idFarcamo, int idAvCli, String descricao, String subsAtiva, String obs) {
		setIdFarcamo(idFarcamo);
		setIdAvCli(idAvCli);
		setDescricao(descricao);
		setSubsAtiva(subsAtiva);
		setObs(obs);
	}

	public final IntegerProperty idFarcamoProperty() {
		return this.idFarcamo;
	}

	public final int getIdFarcamo() {
		return this.idFarcamoProperty().get();
	}

	public final void setIdFarcamo(final int idFarcamo) {
		this.idFarcamoProperty().set(idFarcamo);
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

	public final StringProperty subsAtivaProperty() {
		return this.subsAtiva;
	}

	public final String getSubsAtiva() {
		return this.subsAtivaProperty().get();
	}

	public final void setSubsAtiva(final String subsAtiva) {
		this.subsAtivaProperty().set(subsAtiva);
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
