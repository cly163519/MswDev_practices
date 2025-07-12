import ecs100.*;

public class TemperatureCalculator {
	/** Constructor: Set up interface */
	public TemperatureCalculator() {
		UI.initialise();
		UI.addButton("F->C", this::fahrenheitToCelsius);
		UI.addButton("Formula", this::printFormula);
	}

	/** Convert from Fahrenheit to Celsius */
	public void fahrenheitToCelsius() {
		double fahren = UI.askDouble("Fahrenheit:");
		double celsius = (fahren - 32) * 5 / 9;
		UI.println(fahren + "F  is " + celsius + " C");
	}

	/** Print conversion formula */
	public void printFormula() {
		UI.println("Celsius = (Fahrenheit - 32) *5/9");
	}
	

	public static void main(String[] args) {
		new TemperatureCalculator();
	}
}
