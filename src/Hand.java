import java.util.ArrayList;


public class Hand {
	
	private ArrayList<Card> hand;
	private int startHand = 2;
	
	
	public Hand() {
		//initialize hand
		hand = new ArrayList<Card>();
	}
	
	

	
	//gets the hand 
	public ArrayList<Card> getHand() {
		return hand;
	}
	//prints the hand
	public void printHand() {
		for(Card c : hand) {
			System.out.println(c.toString());
		}
	}
	

	//sets hand -- not used for blackjack
	public boolean setHand(ArrayList<Card> c) {
		if(c.size() == startHand){
			hand = c;
			return true;
		}
		
		return false;
	}
	//adds card to hand
	public void addCard(Card c) {
		
		hand.add(c);
	}
	//takes card from hand and removes it
	public Card giveCards() {
		return hand.remove(0);
	}
	

	//checks to see if hand has card, not used in this game
	public boolean hasCard(Suit suite, Rank rank) {
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).getRank().equals(rank) && hand.get(i).getSuit().equals(suite)) {
				return true;
			}
		}
		
		return false;
	}
	//gets the value of the hand using switch cases 
	public int getValues() {
		int total = 0;//creates a total sets to zero to get player and dealer values
		//goes through the cards in dealer or players and and gets the value of each card 
		for(Card cards : this.hand) {
			switch(cards.getRank()) {


			case Two: total += 2;
				break;
			case Three: total += 3;
				break;
			case Four: total += 4;
				break;
			case Five: total += 5;
				break;
			case Six: total += 6;
				break;
			case Seven: total += 7;
				break;
			case Eight: total += 8;
				break;
			case Nine: total += 9;
				break;
			case Ten: total += 10;
				break;
			case Jack: total += 10;
				break;
			case Queen: total += 10;
				break;
			case King: total += 10;
				break;
			case Ace: if(total >10) { //if player or dealer has ace, if total is above 10 ace is now only plus 1 
				total += 1;
			}
			else {
				total +=11;
			}
			break;
			}
		}
		
		return total; //returns the total
		
	}



	//adds a card to hand ( strat pattern takes care of this)
	public void hit(Card c) {
		// TODO Auto-generated method stub
		hand.add(c);
	}
	//gets the count of the hand size
	public int getCount() {
		return hand.size();
	}

	//shows the 2nd card dealt to the dealer, just like in black jack 
	public void showOne() {
		// TODO Auto-generated method stub
		System.out.println(hand.get(1));
	}

}
