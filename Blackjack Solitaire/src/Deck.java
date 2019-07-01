import java.util.*;

/**
 * This class represents the deck which holds the cards for the game, 
 * allowing for shuffling and dealing the next card in the deck.
 * 
 * @author User
 *
 */
public class Deck {

	/**
	 * An ArrayList that represents a deck holding Card objects.
	 */
	private ArrayList<Card> allCards = new ArrayList<Card>();

	/**
	 * Constructor creates a deck using 'for' loops.
	 */
	public Deck() {
		String[] suits = { "H", "D", "C", "S" };
		String[] specialCards = { "J", "Q", "K", "A" };

		// Loop controlling the suit of cards being generated.
		for (int i = 0; i < 4; i++) {
			// Loop controlling the creation of number cards.
			for (int j = 2; j < 11; j++) {
				allCards.add(new Card(String.valueOf(j), suits[i]));
			}
			// Loop controlling the creation of special cards - face cards
			// and aces.
			for (int k = 0; k < 4; k++)
				allCards.add(new Card(specialCards[k], suits[i]));
		}

	}

	/**
	 * Shuffles the deck.
	 * 
	 * @param deckToShuffle
	 * @return
	 */
	public Deck shuffle() {
		Collections.shuffle(this.allCards);
		return this;
	}

	/**
	 * Deals the next card in the deck, keeping track of which card is next 
	 * using a counter variable, dealCounter, which is incremented after
	 * a card is played.
	 * 
	 * @param dealCounter
	 * @return
	 */
	public Card deal(int dealCounter) {
		return allCards.get(dealCounter);
	}

}