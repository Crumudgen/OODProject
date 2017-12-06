
public class Hit extends Player implements Strategy{
	
	public Hit(String firstName, Hand h) {
		super(firstName, h);
	}

	@Override
	public void doOperation(Card c) {
		super.getHand().addCard(c);
	}

	@Override
	public String stayOperation() {
		return null;		
	}

}
