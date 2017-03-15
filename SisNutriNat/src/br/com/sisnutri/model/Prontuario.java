package br.com.sisnutri.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prontuario {

	private IntegerProperty idPront = new SimpleIntegerProperty();
	private IntegerProperty idPac = new SimpleIntegerProperty();
	private StringProperty descricao = new SimpleStringProperty();

	
	
	public Prontuario(int idPront, int idPac, String descricao) {
		setIdPront(idPront);
		setIdPac(idPac);
		setDescricao(descricao);
	}

	// Getters and Setters
	public final IntegerProperty idProntProperty() {
		return this.idPront;
	}

	public final int getIdPront() {
		return this.idProntProperty().get();
	}

	public final void setIdPront(final int idPront) {
		this.idProntProperty().set(idPront);
	}

	public final IntegerProperty idPacProperty() {
		return this.idPac;
	}

	public final int getIdPac() {
		return this.idPacProperty().get();
	}

	public final void setIdPac(final int idPac) {
		this.idPacProperty().set(idPac);
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

}
