import ecs100.UI;

public class LengthCalculator {
	/** Constructor: Set up interface */
	public LengthCalculator() {
		UI.initialise();
		UI.addButton("I->cm", this::inchesToCm);
		UI.addButton("Formula", this::printFormula);
	}

	/** Convert from Inches to centimetres */
	public void inchesToCm() {
		double inches = UI.askDouble("Inches:");
		double cm = inches * 2.54;
		UI.println(inches+ "I  is " + cm+ "cm");
	}

	/** Print conversion formula */
	public void printFormula() {
		UI.println("cm = inches * 2.54");
	}
	

	public static void main(String[] args) {
		new LengthCalculator();
	}
}
