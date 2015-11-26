package beans;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class RealLayerFeatures {

	protected SimpleIntegerProperty number;
	protected SimpleDoubleProperty sponginess;
	protected SimpleDoubleProperty amountOfClay;
	protected SimpleDoubleProperty amountOfCarbonate;
	protected SimpleDoubleProperty vPAmplitude;

	public RealLayerFeatures() {
		this(0, 0.0, 0.0, 0.0, 0.0);
	}

	public RealLayerFeatures(int number, double sponginess, double amountOfClay, double amountOfCarbonate,
			double vPAmplitude) {
		this.number = new SimpleIntegerProperty(number);
		this.sponginess = new SimpleDoubleProperty(sponginess);
		this.amountOfCarbonate = new SimpleDoubleProperty(amountOfCarbonate);
		this.amountOfClay = new SimpleDoubleProperty(amountOfClay);
		this.vPAmplitude = new SimpleDoubleProperty(vPAmplitude);
	}

	// Number's property accessory
	public int getNumber() {
		return number.get();
	}

	public void setNumber(int number) {
		this.number.set(number);
	}

	public SimpleIntegerProperty getNumberProperty() {
		return number;
	}

	// amountOfCarbonate's property accessory
	public double getAmountOfCarbonate() {
		return amountOfCarbonate.get();
	}

	public void setAmountOfCarbonate(double amountOfCarbonate) {
		this.amountOfCarbonate.set(amountOfCarbonate);
	}

	public SimpleDoubleProperty getAmountOfCarbonateProperty() {
		return amountOfCarbonate;
	}

	// sponginess's property accessory
	public double getSponginess() {
		return sponginess.get();
	}

	public void setSponginess(double sponginess) {
		this.sponginess.set(sponginess);
	}

	public SimpleDoubleProperty getSponginessProperty() {
		return sponginess;
	}

	// vPAmplitude's property accessory
	public double getVPAmplitude() {
		return amountOfClay.get();
	}

	public void setVPAmplitude(double vPAmplitude) {
		this.vPAmplitude.set(vPAmplitude);
	}

	public SimpleDoubleProperty getvPAmplitudeProperty() {
		return vPAmplitude;
	}

	// amountOfClay's property accessory
	public double getAmountOfClay() {
		return amountOfClay.get();
	}

	public void setAmountOfClay(double amountOfClay) {
		this.amountOfClay.set(amountOfClay);
	}

	public SimpleDoubleProperty getAmountOfClayProperty() {
		return amountOfClay;
	}

}
