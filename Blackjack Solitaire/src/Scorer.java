import java.util.*;

/**
 * This class represents a 'Scorer' object that adds up the score at the end of
 * the game.
 * 
 * @author User
 *
 */
public class Scorer {

	/**
	 * 'score' stores the score for the game.
	 */
	private int totalScore;

	/**
	 * This method takes in the whole table and uses helper methods, first to split the
	 * table into different hands, then to sum each hand, to score each hand, and
	 * finally returns the sum of the scores of each hand for a total score.
	 * 
	 * @param myTable
	 * @return
	 */
	public int tableScorer(Table myTable) {
		
		// An ArrayList holds all the hand combinations contained in the Table.
		ArrayList<ArrayList<Card>> allHands = tableHandSplitter(myTable);
		
		// Passes each hand to a helper method to be scored and sums the 
		// results.
		for (ArrayList<Card> hand : allHands) {
			totalScore += calculateScore(hand);
		}

		return totalScore;
	}
	
	/**
	 * This is a helper method that splits the table into 9 hands and stores
	 * them in a 2D ArrayList, 'allHands', to be scored. It copies all rows and
	 * columns from the table as separate hands, using control statements to
	 * avoid copying null spaces.
	 * 
	 * @param myTable
	 * @return
	 */
	private ArrayList<ArrayList<Card>> tableHandSplitter(Table myTable) {
		
		// A 2D ArrayList holds all the hand combinations.
		ArrayList<ArrayList<Card>> allHands = new ArrayList<ArrayList<Card>>();
		
		// Adds the ROW hands to allHands.
		for (int i = 0; i < 4; i++) {
			ArrayList<Card> currentHand = new ArrayList<Card>();
			for (int j = 0; j < 5; j++) {
				// Adds each card in the row to the current hand.
				if (myTable.getGridCard(i,j) != null) {
					currentHand.add(myTable.getGridCard(i,j));
				}
			}
			// Adds the current hand to allHands.
			allHands.add(currentHand);
		}
		
		// Adds the COLUMN hands to allHands.
		for (int j = 0; j < 5; j++) {
			ArrayList<Card> currentHand = new ArrayList<Card>();
			for (int i = 0; i < 4; i++) {
				// Adds each card in the column to the current hand.
				if (myTable.getGridCard(i,j) != null) {
					currentHand.add(myTable.getGridCard(i,j));
				}
			}
			// Adds the current hand to allHands.
			allHands.add(currentHand);
		}
		return allHands;
	}
	
	/**
	 * This method takes a single hand of cards and sums them, handling Aces
	 * appropriately, then uses a helper method 'pointAssessor' to return a 
	 * number of points for the hand.
	 * 
	 * @param hand
	 * @return
	 */
	private int calculateScore(ArrayList<Card> hand) {

		// Summing variable 'rowSum' keeps track of the sum of a single hand.
		int rowSum = 0;
		// numAces counts how many Aces are in a hand, used to determine if
		// they should be counted as 1 or 11.
		int numAces = 0;

		// First, the values of the cards in the hand are summed. Aces are
		// counted, but not yet scored.
		for (int j = 0; j < hand.size(); j++) {
			// Checks if current card is a number card using regular 
			// expressions and adds it.
			if (hand.get(j).getCard().matches(".*\\d.*")) {
				rowSum += hand.get(j).getValue();
			} 
			
			// Face Cards are all counted as 10.
			else if ((hand.get(j).getCard().contains("J")) ||
			(hand.get(j).getCard().contains("Q"))||
			(hand.get(j).getCard().contains("K"))) {
				rowSum += 10;
			}
			
			//Counts the number of Aces in the hand for special scoring.
			if (hand.get(j).getCard().contains("A")) {
				numAces++;
			}
		}

		// If there are NO Aces in the hand, we already have the final sum,
		// which is then passed to the pointAssessor method to receive a score.
		if (numAces == 0) {
			return pointAssessor(rowSum, hand.size());
		}

		// If there ARE Aces in the hand, we determine if they should be 
		// counted as 1 or 11.
		else if (numAces == 1) {
			// Check if counting the Ace as 11 will cause the hand to go bust.
			if (rowSum + 11 > 21) {
				// Count the Ace as 1.
				rowSum += 1;
			}
			// The hand won't go bust, so count the Ace as 11.
			else {
				rowSum += 11;
			}
		}
		
		// With more than one Ace, only one Ace can be counted as 11 without
		// going bust, so we only need to test whether one Ace should be counted
		// as 1 or 11, and the rest of the Aces will count as 1.
		// (numAces - 1) is the value of all the Aces besides for the single Ace
		// which is being counted as 11.
		if (numAces > 1) {
			if (rowSum + 11 + (numAces - 1) > 21) {
				rowSum += numAces;
			} 
			else {
				rowSum += (11 + (numAces - 1));
			}
		}
		return pointAssessor(rowSum, hand.size());
	}

	/**
	 * Helper method that calculates how many points should be awarded based on
	 * the sum of the hand. Also includes a parameter for the number of cards 
	 * in the hand to check for blackjack: 2 cards that add up to 21.
	 * 
	 * @param sum
	 * @param numCardsInHand
	 * @return
	 */
	private int pointAssessor(int sum, int numCardsInHand) {
		if ((sum > 0) && (sum < 17)) {
			return 1;
		} 
		else if (sum == 17) {
			return 2;
		} 
		else if (sum == 18) {
			return 3;
		} 
		else if (sum == 19) {
			return 4;
		} 
		else if (sum == 20) {
			return 5;
		} 
		else if (sum == 21) {
			// Checks for Blackjack if 21 was made with only 2 cards.
			if (numCardsInHand == 2) {
				return 10;
			} else {
				return 7;
			}
		}
		// Any other score must be over 21 and is bust.
		else {
			return 0;
		}
	}

}
