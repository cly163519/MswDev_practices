import ecs100.*;

public class Startup {

	public static void main(String[] args) {
		UI.initialise();
		FlagDrawer flagDrawer = new FlagDrawer();
		PatternDrawer patternDrawer = new PatternDrawer();
		UI.addButton("Clear", UI::clearPanes );
		UI.addButton("Flags - core", flagDrawer::testCore);
		UI.addButton("Flags - completion", flagDrawer::testCompletion);
        UI.addButton("Patterns - core: grid", patternDrawer::drawGridBoard);
        UI.addButton("Patterns - core: illusion", patternDrawer::drawIllusion);
        UI.addButton("Patterns - completion: checkers", patternDrawer::drawCheckersBoard);
        UI.addButton("Patterns - challenge: concentric", patternDrawer::drawConcentricBoard);
		UI.addButton("Quit", UI::quit );
	}
}
