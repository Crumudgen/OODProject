
public class Context {
	private Strategy strat;
	
	
	public Context(Strategy strat) {
		this.strat = strat;
	}
	//executes correct strategy
	public void execute(Card c) {
		strat.doOperation(c);
	}
	//executes correct strat
	public void execute() {
		strat.stayOperation();
	}
}
