

import java.util.ArrayList;

public class Deck {
	
	private static Deck d;
	private static ArrayList<Card> deck;
	
	
	//creates SINGLETON private deck 
	private Deck() {
		deck = new ArrayList<Card>();
	}
	
	public static Deck getInstance() {
		if(d == null) {
			d = new Deck();
		}
		return d;
	}
	
	//creates a full deck unshuffled
	public void fullDeck() {
		for(Suit suit : Suit.values()) { //goes through suit enum
			for(Rank rank : Rank.values()) {//goes through rank enum
			
				deck.add(new Card(suit, rank));//adds each card to deck 
			}
		}
	}

	//prints the deck 
	public String printDeck() {
		String output = "";
		
		for(int i=0; i<deck.size();i++) { //for everything in the deck, convert to a string for output
		output += deck.get(i).toString() + "\n";
		}
		
		return output;
	}
	//shuffles the deck 
	public void shuffleDeck() {
		for(int i = 0; i < deck.size(); i++) { 
			
			int val = (int) (52 * Math.random());
			Card temp = deck.get(val);
			deck.set(val, deck.get(i));
			deck.set(i, temp);
			
			
		}
	}
	//deals card from the deck 
	public Card dealCard() {
		return deck.remove(0);
	}
	//adds a hand back to the deck (not used)
	public void addHand(ArrayList<Card> h) {
		for(Card c : h ) { //runs through cards in hand, and adds each card to deck 
			addCard(c);
		}
	}
	
	//adds cards to the deck 
	public void addCard(Card c) { //adds card to deck 
		deck.add(c);
	}
	
        
}
