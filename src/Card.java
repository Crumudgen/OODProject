

public class Card {
	private Suit suit;
	private Rank rank;
	
	//creates the cards with a suit and rank  
	public Card(Suit suit,Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	//gets the suit of the card
	public Suit getSuit() {
		return suit;
	}
	//gets the rank of the card
	public Rank getRank() {
		return rank;
	}
	//to string to display the cards
	public String toString() {
		return rank + " of " + suit; 
	}
}
