import java.util.Scanner;

/**
 * This class manages the game by creating required objects and accepting and
 * processing user input.
 *
 * @author User
 *
 */
public class BlackjackSolitaire {

	/**
	 * This method runs the whole game, including creating a deck and shuffling,
	 * dealing, allowing a user to select where cards should be placed, displaying
	 * the playing table, scoring, and game over.
	 */
	public void play() {

		// Scanner allows user to input where cards should be played.
		Scanner in = new Scanner(System.in);
		
		// Holds the user's input for validation and processing.
		 String inputString;
		
		// Holds the user's selection of where to play a card. 
		int playSpot;
		
		// Keeps track of which card to deal next.
		int dealCounter = 0;

		//Stores the user's score.
		int myScore;

		// Initializes a Table object to hold and display the cards.
		Table myTable = new Table();
		
		// Displays the empty table;
		myTable.showTable();
		
		// Initializes a new card deck.
		Deck myDeck = new Deck();
		
		// Shuffles the deck.
		myDeck.shuffle();
		
		// This while loop controls the flow of the game: dealing and playing cards, 
		// showing the updated table, requesting input, as well as error checking.
		// It only runs while the table still has vacant spaces, verified using
		// the 'fullTable' method.
		while (myTable.fullTable() == false) {
			
			// Deals a card and assigns it to a variable.
			Card currentCard = myDeck.deal(dealCounter);

			// Prompts user to play the card.
			System.out.println("Enter the position (number 1-16) where you would like to play: "
			+ currentCard.getCard());

			// Offers user to discard only if available.
			if (myTable.fullDiscard() == false) {
				System.out.println("Or enter 0 to discard.");
			}

			inputString = in.nextLine();

			// Verifies that the input contains only digits using a
			// regular expression and converts it into an integer.
			if (inputString.matches("\\d+")) {
				playSpot = Integer.parseInt(inputString);
			}

			// If the input is not a digit, 
			// guides the user to select a valid input.
			else {
				System.out.println("Digits only, please.");
				continue;
			}
			
			//Next come control statements to process the user's selection.
			
			// If 0 was entered: Discard into the next open discard spot,
			// after checking for availability. If unavailable, the user is
			// notified.
			if (playSpot == 0) {
				if (myTable.fullDiscard() == false) {
					myTable.discard(currentCard);
				} 
				else {
					System.out.println("Bad news - no discards available.");
					continue;
				}
			}
			
			//Displays an error message if the selection is out of bounds.
			else if (playSpot > 16) {
				System.out.println("Please choose a spot between 1-16");
				continue;
			} 
			
			// Places the card in the spot that was selected, if it is open.
			// The playCard method returns 'false' if the spot is occupied.
			// Guides the user to select a valid spot if the spot selected
			// isn't valid.
			else if (myTable.playCard(playSpot, currentCard) == false) {
					System.out.println("That spot is full. Please choose another one.");
					continue;
				}
			
			//Displays the updated table.
			myTable.showTable();
			//Increments the counter variable that keeps track of where the 
			//player is up to in the deck.
			dealCounter++;
		}

		// Closing the Scanner object.
		in.close();
		// Game over and scoring
		System.out.println("It's time to see how well you did!");
		//Creates a Scorer object to calculate the player's score.
		Scorer gameScore = new Scorer();
		//Calls the tableScorer method to produce a score from the play table.
		myScore = gameScore.tableScorer(myTable);
		System.out.println("Here's your score: " + myScore);
		System.out.println("Thanks for Playing. Bye!");
	}
}
