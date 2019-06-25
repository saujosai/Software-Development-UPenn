/**
 * This class represents the table where cards are played or discarded. It
 * displays the table and keeps track of which spots are open.
 * 
 * @author User
 *
 */
public class Table {

	/**
	 * The 'grid' array of Cards holds all the Cards that have been played or
	 * discarded. This array needs to be accessed by the Scorer class for scoring
	 * when the game is complete, so the variable has been declared 'public'. A 2D
	 * array was selected for this purpose to simplify scoring, which must be
	 * calculated for both rows and columns. Use of a 2D array does complicate
	 * storing where each card has been placed.
	 */

	public Card[][] grid = new Card[5][5];

	public void showTable() {
		int positionNumber = 1;
		for (int i = 0; i < grid.length; i++) {
			// If condition ensures proper formatting.
			if (i == 4) {
				System.out.println("Discards:");
			}
			for (int j = 0; j < grid[i].length; j++) {
				// If condition skips blank spaces in on the table
				// for proper formating.
				if (((j == 0) && ((i == 2) || (i == 3))) || ((j == 4) && ((i == 2) || (i == 3) || (i == 4)))) {
					System.out.format("%5s", "");
					continue;
				}
				if (grid[i][j] == null) {
					System.out.format("%5s", positionNumber);
				} else {
					System.out.format("%5s", grid[i][j].getCard());
				}
				positionNumber++;
			}
			System.out.println();
		}
	}

	/**
	 * Allows the user to play the current card in a particular place on the table.
	 * 
	 * @param playSpot
	 * @param currentCard
	 */
	public boolean playCard(int playSpot, Card currentCard) {

		if (playSpot == 1) {
			if (grid[0][0] == null) {
				grid[0][0] = currentCard;

			} else {
				return false;
			}
		}

		if (playSpot == 2) {
			if (grid[0][1] == null) {
				grid[0][1] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 3) {
			if (grid[0][2] == null) {
				grid[0][2] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 4) {
			if (grid[0][3] == null) {
				grid[0][3] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 5) {
			if (grid[0][4] == null) {
				grid[0][4] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 6) {
			if (grid[1][0] == null) {
				grid[1][0] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 7) {
			if (grid[1][1] == null) {
				grid[1][1] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 8) {
			if (grid[1][2] == null) {
				grid[1][2] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 9) {
			if (grid[1][3] == null) {
				grid[1][3] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 10) {
			if (grid[1][4] == null) {
				grid[1][4] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 11) {
			if (grid[2][1] == null) {
				grid[2][1] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 12) {
			if (grid[2][2] == null) {
				grid[2][2] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 13) {
			if (grid[2][3] == null) {
				grid[2][3] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 14) {
			if (grid[3][1] == null) {
				grid[3][1] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 15) {
			if (grid[3][2] == null) {
				grid[3][2] = currentCard;

			} else {
				return false;
			}
		}
		if (playSpot == 16) {
			if (grid[3][3] == null) {
				grid[3][3] = currentCard;

			} else {
				return false;
			}
		}

		return true;
	}

	/**
	 * Allows the user to play the current card in a particular place on the 
	 * table. The integer between 1-16 is converted into [i][j] coordinates 
	 * for row and column to store the current card in the 2D array, 'grid'.
	 * Division, modulo, and hard-coded values allow for compact code to 
	 * convert the values into 2D coordinates. Although interpreting the code
	 * is more difficult, this was preferred over using 16 'if' statements to
	 * map each input to a spot in the array. Before a spot is filled, we 
	 * verify that it is vacant, and return 'false' if the space is occupied.
	 * 
	 * @param playSpot
	 * @param currentCard
	 */
	public boolean betterplayCard(int playSpot, Card currentCard) {
		int i;
		int j;
		if (playSpot < 11) {
			i = (playSpot - 1) / 5;
			j = ((playSpot - 1 + 5) % 5);

			if (grid[i][j] == null) {
				grid[i][j] = currentCard;
			} 
			else {
				return false;
			}
		}

		if ((playSpot > 10) && (playSpot < 14)) {
			i = 2;
			j = ((playSpot) % 5);

			if (grid[i][j] == null) {
				grid[i][j] = currentCard;

			} 
			else {
				return false;
			}
		}
		if (playSpot > 13) {
			i = 3;
			j = ((playSpot) % 13);

			if (grid[i][j] == null) {
				grid[i][j] = currentCard;

			} 
			else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Discards the current card in the next open space.
	 * 
	 * @param currentCard
	 */
	public void discard(Card currentCard) {
		// For loop finds the first open discard spot, if there is one.
		for (int j = 0; j < 4; j++) {
			if (grid[4][j] == null) {
				grid[4][j] = currentCard;
				System.out.println(currentCard.getCard() + " has been discarded.");
				break;
			}
		}

	}

	/**
	 * Checks whether all spots on the table have been played.
	 * 
	 * @return
	 */
	public boolean fullTable() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				// Control statement skips blank spaces on the table
				// when checking if the table is full.
				if (((j == 0) && ((i == 2) || (i == 3))) || ((j == 4) && ((i == 2) || (i == 3) || (i == 4)))) {
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
