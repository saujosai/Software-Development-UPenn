/**
 * This class represents the table where cards are played or discarded. It
 * displays the table, keeps track of which spots are open, and plays or
 * discards cards.
 * 
 * @author User
 *
 */
public class Table {

	/**
	 * The 'grid' array of Cards holds all the Cards that have been played or
	 * discarded. A 2D array was selected for this purpose to simplify scoring,
	 * which must be calculated for both rows and columns. 
	 */
	private Card[][] grid = new Card[5][5];
	
	/**
	 * A getter method provides access to cards in the grid to be used by the 
	 * Scorer class for scoring.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public Card getGridCard(int i, int j) {
		return grid[i][j];
	}
	
	/**
	 * This method displays the table's current state, either where cards
	 * have been played or placeholders consisting of integers 1-20.
	 */
	public void showTable() {
		
		//positionNumber keeps track of what number to display in empty spots.
		int positionNumber = 1;
		
		for (int i = 0; i < grid.length; i++) {
			// This statement ensures proper formatting by separating the
			// discard pile from the play area.
			if (i == 4) {
				System.out.println("Discards:");
			}
			for (int j = 0; j < grid[i].length; j++) {
				// Creates blank spaces on the table
				// for proper formating.
				if (((j == 0) && ((i == 2) || (i == 3))) || 
				((j == 4) && ((i == 2) || (i == 3) || (i == 4)))) {
					System.out.format("%5s", "");
					continue;
				}
				// Displays the number of a spot (1-20) if no card has been 
				// played there.
				if (grid[i][j] == null) {
					System.out.format("%5s", positionNumber);
				} 
				// Displays the card that has been played in a particular spot.
				else {
					System.out.format("%5s", grid[i][j].getCard());
				}
				// Increments the positionNumber variable so that the correct
				// placeholder number will be shown in the next open spot.
				positionNumber++;
			}
			// Skips to the next row of the play table.
			System.out.println();
		}
	}
	
	/**
	 * Allows the user to play the current card in a particular place on the 
	 * table. The integer between 1-16 is translated into [i][j] coordinates 
	 * for row and column to store the current card in the 2D array, 'grid'.
	 * Division, modulo, and hard-coded values allow for compact code to 
	 * convert the values into 2D coordinates. Although interpreting the code
	 * is more difficult, this was preferred over using 16 'if' statements to
	 * map each input to a spot in the array. Before a spot is filled, the 
	 * method verifies that it is vacant, and returns 'false' if the space is
	 * occupied.
	 * 
	 * @param playSpot
	 * @param currentCard
	 * @return
	 */
	public boolean playCard(int playSpot, Card currentCard) {
		int i = 0; // Row index
		int j = 0; // Column index
		if (playSpot < 11) {
			// 1 is subtracted from playSpot because the array is 0 indexed, 
			// but play spaces start with 1. Therefore, every user input must
			// be moved over by 1 to match the correct spot in the array. 
			i = (playSpot - 1) / 5;
			j = ((playSpot - 1) % 5);
		}
		
		// In spots 11-13, the 0 spot remains null for formatting purposes
		// and 11 needs to go in the 1 index for this row, so nothing is 
		// subtracted from playSpot before calculating the modulo. All these
		// spots are in row 3, so i is hard-coded as 2.
		if ((playSpot > 10) && (playSpot < 14)) {
			i = 2;
			j = ((playSpot) % 5);
		}
		
		//All these spots are in row 4, so i is hard-coded as 3.
		if (playSpot > 13) {
			i = 3;
			j = ((playSpot) % 13);
		}
		
		// Plays the card if the spot is open, otherwise returns 'false'.
		if (grid[i][j] == null) {
			grid[i][j] = currentCard;
			return true;
			} 
		else {
			return false;
		}
		
	}

	/**
	 * Discards the current card in the next open space.
	 * 
	 * @param currentCard
	 */
	public void discard(Card currentCard) {
		
		// Loops through spots allocated for discards and discards in the first
		// available spot.
		for (int j = 0; j < 4; j++) {
			if (grid[4][j] == null) {
				grid[4][j] = currentCard;
				System.out.println(currentCard.getCard() + " has been discarded.");
				break;
			}
		}

	}

	/**
	 * Checks whether all spots on the table have been played in order to end 
	 * play and begin scoring.
	 * 
	 * @return
	 */
	public boolean fullTable() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				// Control statement skips blank spaces on the table that are
				// unavailable for play when checking if the table is full.
				if (((j == 0) && ((i == 2) || (i == 3))) ||
				((j == 4) && ((i == 2) ||(i == 3)))) {
					continue;
				}
				if (grid[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks whether all discard spots have been used.
	 * 
	 * @return
	 */
	public boolean fullDiscard() {
		for (int j = 0; j < 4; j++) {
			if (grid[4][j] == null) {
				return false;
			}
		}
		return true;
	}
}
