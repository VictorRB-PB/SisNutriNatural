/**
 * 
 */
package br.com.sisnutri.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Victor
 *
 */
public class MedidasAntropometricas {

	IntegerProperty idMedida = new SimpleIntegerProperty();
	IntegerProperty idAvFisica = new SimpleIntegerProperty();
	DoubleProperty pesoAtual = new SimpleDoubleProperty();
	DoubleProperty pesoDesejado = new SimpleDoubleProperty();
	DoubleProperty pesoUsual = new SimpleDoubleProperty();
	StringProperty tempoSobrepeso = new SimpleStringProperty();
	DoubleProperty altura = new SimpleDoubleProperty();
	DoubleProperty altJoelho = new SimpleDoubleProperty();
	DoubleProperty triceps = new SimpleDoubleProperty();
	DoubleProperty biceps = new SimpleDoubleProperty();
	DoubleProperty subescapular = new SimpleDoubleProperty();
	DoubleProperty axilarMedial = new SimpleDoubleProperty();
	DoubleProperty toracica = new SimpleDoubleProperty();
	DoubleProperty supraEspinal = new SimpleDoubleProperty();
	DoubleProperty supraIliaca = new SimpleDoubleProperty();
	DoubleProperty abdome = new SimpleDoubleProperty();
	DoubleProperty coxa = new SimpleDoubleProperty();
	DoubleProperty panturrilhaDobra = new SimpleDoubleProperty();
	DoubleProperty braco = new SimpleDoubleProperty();
	DoubleProperty antebraco = new SimpleDoubleProperty();
	DoubleProperty punho = new SimpleDoubleProperty();
	DoubleProperty torax = new SimpleDoubleProperty();
	DoubleProperty cintura = new SimpleDoubleProperty();
	DoubleProperty tornozelo = new SimpleDoubleProperty();
	DoubleProperty abdominal = new SimpleDoubleProperty();
	DoubleProperty quadril = new SimpleDoubleProperty();
	DoubleProperty glutMax = new SimpleDoubleProperty();
	DoubleProperty coxaMax = new SimpleDoubleProperty();
	DoubleProperty panturrilhaPerimetro = new SimpleDoubleProperty();
	DoubleProperty cefalico = new SimpleDoubleProperty();
	DoubleProperty biestiloide = new SimpleDoubleProperty();
	DoubleProperty bumeral = new SimpleDoubleProperty();
	DoubleProperty bfemural = new SimpleDoubleProperty();
	DoubleProperty idade = new SimpleDoubleProperty();

	public MedidasAntropometricas(int idMedida, int idAvFisica, double pesoAtual, double pesoDesejado, double pesoUsual,
			String tempoSobrepeso, double altura, double altJoelho, double triceps, double biceps, double subescapular,
			double axilarMedial, double toracica, double supraEspinal, double supraIliaca, double abdome, double coxa,
			double panturrilhaDobra, double braco, double antebraco, double punho, double torax, double cintura,
			double tornozelo, double abdominal, double quadril, double glutMax, double coxaMax,
			double panturrilhaPerimetro, double cefalico, double biestiloide, double bumeral, double bfemural,
			double idade) {

		setIdMedida(idMedida);
		setIdAvFisica(idAvFisica);
		setPesoAtual(pesoAtual);
		setPesoDesejado(pesoDesejado);
		setPesoUsual(pesoUsual);
		setTempoSobrepeso(tempoSobrepeso);
		setAltura(altura);
		setAltJoelho(altJoelho);
		setTriceps(triceps);
		setBiceps(biceps);
		setSubescapular(subescapular);
		setAxilarMedial(axilarMedial);
		setToracica(toracica);
		setSupraEspinal(supraEspinal);
		setSupraIliaca(supraIliaca);
		setAbdome(abdome);
		setCoxa(coxa);
		setPanturrilhaDobra(panturrilhaDobra);
		setBraco(braco);
		setAntebraco(antebraco);
		setPunho(punho);
		setTorax(torax);
		setCintura(cintura);
		setTornozelo(tornozelo);
		setAbdominal(abdominal);
		setQuadril(quadril);
		setGlutMax(glutMax);
		setCoxaMax(coxaMax);
		setPanturrilhaPerimetro(panturrilhaPerimetro);
		setCefalico(cefalico);
		setBiestiloide(biestiloide);
		setBumeral(bumeral);
		setBfemural(bfemural);
		setIdade(idade);
	}

	public final IntegerProperty idMedidaProperty() {
		return this.idMedida;
	}

	public final int getIdMedida() {
		return this.idMedidaProperty().get();
	}

	public final void setIdMedida(final int idMedida) {
		this.idMedidaProperty().set(idMedida);
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

	public final DoubleProperty pesoAtualProperty() {
		return this.pesoAtual;
	}

	public final double getPesoAtual() {
		return this.pesoAtualProperty().get();
	}

	public final void setPesoAtual(final double pesoAtual) {
		this.pesoAtualProperty().set(pesoAtual);
	}

	public final DoubleProperty pesoDesejadoProperty() {
		return this.pesoDesejado;
	}

	public final double getPesoDesejado() {
		return this.pesoDesejadoProperty().get();
	}

	public final void setPesoDesejado(final double pesoDesejado) {
		this.pesoDesejadoProperty().set(pesoDesejado);
	}

	public final DoubleProperty pesoUsualProperty() {
		return this.pesoUsual;
	}

	public final double getPesoUsual() {
		return this.pesoUsualProperty().get();
	}

	public final void setPesoUsual(final double pesoUsual) {
		this.pesoUsualProperty().set(pesoUsual);
	}

	public final StringProperty tempoSobrepesoProperty() {
		return this.tempoSobrepeso;
	}

	public final String getTempoSobrepeso() {
		return this.tempoSobrepesoProperty().get();
	}

	public final void setTempoSobrepeso(final String tempoSobrepeso) {
		this.tempoSobrepesoProperty().set(tempoSobrepeso);
	}

	public final DoubleProperty alturaProperty() {
		return this.altura;
	}

	public final double getAltura() {
		return this.alturaProperty().get();
	}

	public final void setAltura(final double altura) {
		this.alturaProperty().set(altura);
	}

	public final DoubleProperty altJoelhoProperty() {
		return this.altJoelho;
	}

	public final double getAltJoelho() {
		return this.altJoelhoProperty().get();
	}

	public final void setAltJoelho(final double altJoelho) {
		this.altJoelhoProperty().set(altJoelho);
	}

	public final DoubleProperty tricepsProperty() {
		return this.triceps;
	}

	public final double getTriceps() {
		return this.tricepsProperty().get();
	}

	public final void setTriceps(final double triceps) {
		this.tricepsProperty().set(triceps);
	}

	public final DoubleProperty bicepsProperty() {
		return this.biceps;
	}

	public final double getBiceps() {
		return this.bicepsProperty().get();
	}

	public final void setBiceps(final double biceps) {
		this.bicepsProperty().set(biceps);
	}

	public final DoubleProperty subescapularProperty() {
		return this.subescapular;
	}

	public final double getSubescapular() {
		return this.subescapularProperty().get();
	}

	public final void setSubescapular(final double subescapular) {
		this.subescapularProperty().set(subescapular);
	}

	public final DoubleProperty axilarMedialProperty() {
		return this.axilarMedial;
	}

	public final double getAxilarMedial() {
		return this.axilarMedialProperty().get();
	}

	public final void setAxilarMedial(final double axilarMedial) {
		this.axilarMedialProperty().set(axilarMedial);
	}

	public final DoubleProperty toracicaProperty() {
		return this.toracica;
	}

	public final double getToracica() {
		return this.toracicaProperty().get();
	}

	public final void setToracica(final double toracica) {
		this.toracicaProperty().set(toracica);
	}

	public final DoubleProperty supraEspinalProperty() {
		return this.supraEspinal;
	}

	public final double getSupraEspinal() {
		return this.supraEspinalProperty().get();
	}

	public final void setSupraEspinal(final double supraEspinal) {
		this.supraEspinalProperty().set(supraEspinal);
	}

	public final DoubleProperty supraIliacaProperty() {
		return this.supraIliaca;
	}

	public final double getSupraIliaca() {
		return this.supraIliacaProperty().get();
	}

	public final void setSupraIliaca(final double supraIliaca) {
		this.supraIliacaProperty().set(supraIliaca);
	}

	public final DoubleProperty abdomeProperty() {
		return this.abdome;
	}

	public final double getAbdome() {
		return this.abdomeProperty().get();
	}

	public final void setAbdome(final double abdome) {
		this.abdomeProperty().set(abdome);
	}

	public final DoubleProperty coxaProperty() {
		return this.coxa;
	}

	public final double getCoxa() {
		return this.coxaProperty().get();
	}

	public final void setCoxa(final double coxa) {
		this.coxaProperty().set(coxa);
	}

	public final DoubleProperty panturrilhaDobraProperty() {
		return this.panturrilhaDobra;
	}

	public final double getPanturrilhaDobra() {
		return this.panturrilhaDobraProperty().get();
	}

	public final void setPanturrilhaDobra(final double panturrilhaDobra) {
		this.panturrilhaDobraProperty().set(panturrilhaDobra);
	}

	public final DoubleProperty bracoProperty() {
		return this.braco;
	}

	public final double getBraco() {
		return this.bracoProperty().get();
	}

	public final void setBraco(final double braco) {
		this.bracoProperty().set(braco);
	}

	public final DoubleProperty antebracoProperty() {
		return this.antebraco;
	}

	public final double getAntebraco() {
		return this.antebracoProperty().get();
	}

	public final void setAntebraco(final double antebraco) {
		this.antebracoProperty().set(antebraco);
	}

	public final DoubleProperty punhoProperty() {
		return this.punho;
	}

	public final double getPunho() {
		return this.punhoProperty().get();
	}

	public final void setPunho(final double punho) {
		this.punhoProperty().set(punho);
	}

	public final DoubleProperty toraxProperty() {
		return this.torax;
	}

	public final double getTorax() {
		return this.toraxProperty().get();
	}

	public final void setTorax(final double torax) {
		this.toraxProperty().set(torax);
	}

	public final DoubleProperty cinturaProperty() {
		return this.cintura;
	}

	public final double getCintura() {
		return this.cinturaProperty().get();
	}

	public final void setCintura(final double cintura) {
		this.cinturaProperty().set(cintura);
	}

	public final DoubleProperty tornozeloProperty() {
		return this.tornozelo;
	}

	public final double getTornozelo() {
		return this.tornozeloProperty().get();
	}

	public final void setTornozelo(final double tornozelo) {
		this.tornozeloProperty().set(tornozelo);
	}

	public final DoubleProperty abdominalProperty() {
		return this.abdominal;
	}

	public final double getAbdominal() {
		return this.abdominalProperty().get();
	}

	public final void setAbdominal(final double abdominal) {
		this.abdominalProperty().set(abdominal);
	}

	public final DoubleProperty quadrilProperty() {
		return this.quadril;
	}

	public final double getQuadril() {
		return this.quadrilProperty().get();
	}

	public final void setQuadril(final double quadrial) {
		this.quadrilProperty().set(quadrial);
	}

	public final DoubleProperty glutMaxProperty() {
		return this.glutMax;
	}

	public final double getGlutMax() {
		return this.glutMaxProperty().get();
	}

	public final void setGlutMax(final double glutMax) {
		this.glutMaxProperty().set(glutMax);
	}

	public final DoubleProperty coxaMaxProperty() {
		return this.coxaMax;
	}

	public final double getCoxaMax() {
		return this.coxaMaxProperty().get();
	}

	public final void setCoxaMax(final double coxaMax) {
		this.coxaMaxProperty().set(coxaMax);
	}

	public final DoubleProperty panturrilhaPerimetroProperty() {
		return this.panturrilhaPerimetro;
	}

	public final double getPanturrilhaPerimetro() {
		return this.panturrilhaPerimetroProperty().get();
	}

	public final void setPanturrilhaPerimetro(final double panturrilhaPerimetro) {
		this.panturrilhaPerimetroProperty().set(panturrilhaPerimetro);
	}

	public final DoubleProperty cefalicoProperty() {
		return this.cefalico;
	}

	public final double getCefalico() {
		return this.cefalicoProperty().get();
	}

	public final void setCefalico(final double cefalico) {
		this.cefalicoProperty().set(cefalico);
	}

	public final DoubleProperty biestiloideProperty() {
		return this.biestiloide;
	}

	public final double getBiestiloide() {
		return this.biestiloideProperty().get();
	}

	public final void setBiestiloide(final double biestiloide) {
		this.biestiloideProperty().set(biestiloide);
	}

	public final DoubleProperty bumeralProperty() {
		return this.bumeral;
	}

	public final double getBumeral() {
		return this.bumeralProperty().get();
	}

	public final void setBumeral(final double bumeral) {
		this.bumeralProperty().set(bumeral);
	}

	public final DoubleProperty bfemuralProperty() {
		return this.bfemural;
	}

	public final double getBfemural() {
		return this.bfemuralProperty().get();
	}

	public final void setBfemural(final double bfemural) {
		this.bfemuralProperty().set(bfemural);
	}

	public final DoubleProperty idadeProperty() {
		return this.idade;
	}

	public final double getIdade() {
		return this.idadeProperty().get();
	}

	public final void setIdade(final double idade) {
		this.idadeProperty().set(idade);
	}

}
