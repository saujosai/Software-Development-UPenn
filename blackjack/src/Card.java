/**
 * This class represents individual cards.
 * @author User
 *
 */
public class Card {

	/**
	 * A String storing the value of the card: 2-10 or Jack, Queen, King, Ace.
	 */
	private String value;

	/**
	 * A String storing the suit of the card: Hearts, Clubs, Diamonds, Spades.
	 */
	private String suit;

	/**
	 * Constructs a Card object with value and suit String parameters.
	 * @param value
	 * @param suit
	 */
	public Card(String value, String suit) {
		this.value = value;
		this.suit = suit;
	}

	/**
	 * This method returns the value and suit of a particular card as a 
	 * concatenated String.
	 * @return
	 */
	public String getCard() {
		return value + suit;
	}

	/**
	 * This method returns the value of a card as an integer,
	 *  which will be used for scoring.	
	 * @return
	 */
	public int getValue() {
		return Integer.parseInt(value);
	}

}
