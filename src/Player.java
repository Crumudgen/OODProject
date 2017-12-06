
//player class
public class Player implements Person {
	private String firstName;
	
	private Hand h;
	
	
	//player constructor "generic Player" as name and hand for the player
	public Player(String firstName,Hand h) {
		this.firstName = firstName;
		this.h = h;
	}

	//gets hand for player to use
	public Hand getHand() {
		return h;
	}
	//sets hand
	public void setHand(Hand h) {
		this.h = h;
	}
	//adds a card to player hand
	public void addCard(Card c) {
		h.addCard(c);
	}
	
	@Override
	public String getFirstName() {
		
		return firstName;
	}	

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		this.firstName = firstName;
	}


	


}
