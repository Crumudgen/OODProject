
public class Stay extends Player implements Strategy{

	public Stay(String firstName, Hand h) {
		super(firstName, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doOperation(Card c) {
		// TODO Auto-generated method stub
		System.out.println("Player decided to stay!");
	}

	@Override
	public String stayOperation() {
		// TODO Auto-generated method stub
		return null;
	}

}
