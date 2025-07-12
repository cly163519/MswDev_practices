import ecs100.*;

public class HigherLower {

    /**
     * Asks user for a guess.
     * Tells them if the target is higher or lower than the guess.
     * If they guessed the target correctly, they win.
     * Otherwise, asks for another guess until they get the right answer.
     */
    public void higherLowerCore(){
    	// Target is a random number from 1 to 100 for the user to guess.
    	int target = (int)Math.floor(Math.random() * 100) + 1;
        /*# YOUR CODE HERE */
    	while(true) {
    		int guess = UI.askInt("Guess: ");
    		if(guess == target) {
    			UI.println("You win!");
    			break;
    		}else if(guess < target) {
    			UI.println("Lower!");
    		}else {
    			UI.println("Higher!");
    		}
    	}
    	
    }

    /**
     * Asks user for the number of rounds to play, then lets them play the
     * guessing game that many times.
     * Each game has a new target and the player has 10 guesses.
     * At the end, reports to the user how many times they guessed correctly
     * and how many guesses they used in total.
     */
    public void higherLowerCompletion(){
        /*# YOUR CODE HERE (Learning while loop) */
    	int round = 0;
    	int rounds = UI.askInt("How many rounds: ");
    	int correctGuess = 0;
    	int totallyGuesses = 0;
    	
    	while(round < rounds) {
    		int target = (int)Math.floor(Math.random() * 100) + 1;
    		int guessWithinTen = 0;
    		
    		while(guessWithinTen < 10) {
    			int guess = UI.askInt("Guess: ");
    			if (guess == target) {
        			UI.println("You win!");
        			correctGuess++;
        			totallyGuesses++;
        			break;
        		}else if(guess < target) {
        			UI.println("Lower");
        		}else {
        			UI.println("Higher");
        		}
    			//correctGuess++;
    			totallyGuesses++;
    			guessWithinTen++;
    		}
    		round++;
    		if(round < rounds) {
    			UI.println("Next round!");
    		}
    		
    	}
    	UI.println("correctGuess: " + correctGuess);
    	UI.println("totallyGuesses " + totallyGuesses);    	
    }
     
    /**
     * Make the game more complicated and record more information about the game.
     *
     *Asks user for the number of rounds to play, then lets them play the
       guessing game that many times.
     * Each game has a new target and the player has 10 guesses.
     * At the end, reports to the user how many times they guessed correctly
     * and how many guesses they used in total.
     */
    public void higherLowerChallenge(){
        /*# YOUR CODE HERE (Learning for loop) */
    	
    	
    	
    }
    	
    

    public void createTimeTable() {
    	for(int i=1;i<=10;i++) {
    		for(int j=1;j<=10;j++) {
    			UI.println(i + "*" + j + "=" + (i * j));
    		}
    	}
    	
    }
    /** ---------- The code below is already written for you ---------- **/
    /** Constructor: set up user interface */
    public HigherLower(){
        UI.initialise();
        UI.addButton("Clear", UI::clearText );
        UI.addButton("Higher or Lower Core", this::higherLowerCore );
        UI.addButton("Higher or Lower Completion", this::higherLowerCompletion );
        UI.addButton("Higher lower challenge", this::higherLowerChallenge);
        UI.addButton("Times table", this::createTimeTable);
        UI.addButton("Quit", UI::quit );
        UI.setDivider(1);       // Expand the text area
    }

    public static void main(String[] args) {
    	new HigherLower();
    }


}

