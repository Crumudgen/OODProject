

public class Dealer implements Person {
	private String firstName;

	private Deck d;
	private Hand hand;

	//makes dealer with a name , deck , and hand
	public Dealer(String firstName, Deck d, Hand hand) {
		this.firstName = firstName;
		this.d = d;
		this.hand = hand;
	}
	
	//dealer can get the deck 
	
	public Deck getDeck() {
		return d;
	}
	
	//dealer can deal a card
	public Card dealCard() {
		return d.dealCard();
	}
	

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		this.firstName = firstName;
		
	}

	public Hand getHand() {
		return hand;
	}
	
	public void setHand(Hand h) {
		this.hand = h;
	}

	public void addCard(Card c) {
		hand.addCard(c);
	}
	public void hit(Card c) {
		hand.addCard(c);
	}
	
	
}
