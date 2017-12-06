import java.util.Scanner;

public class BlackJack {
	
	
	static Deck d;
	static Hand hand;
	static Hand dHand;
	static Dealer dealer;		
	static Player p;
	static Scanner scan = new Scanner(System.in);
	
	
	//initializes the game after player answers whether they would like to play or not
	@SuppressWarnings("static-access")
	public static void startGame() {
		
		//creating all objects
		d.getInstance();
	    hand = new Hand();
		dHand = new Hand();		
		dealer = new Dealer("Dealer",d,dHand);		
		p = new Player("Player1",hand);
		System.out.println("Getting the deck together");
		dealer.getDeck().getInstance().fullDeck();
		System.out.println("\nDealer is shuffling the cards");
		//dealer gets deck and shuffles the deck 
		dealer.getDeck().getInstance().shuffleDeck();
		
		System.out.println("\nDealing Cards!.....");
		//begins dealing cards to player and dealer
		startDeal();	
	}
	//deal function that deals the correct amount of cards in correct order 
	@SuppressWarnings("static-access")
	public static void startDeal() {
		p.getHand().addCard(dealer.getDeck().getInstance().dealCard());
		dealer.getHand().addCard(dealer.getDeck().getInstance().dealCard());
		p.getHand().addCard(dealer.getDeck().getInstance().dealCard());
		dealer.getHand().addCard(dealer.getDeck().getInstance().dealCard());
	}
	//shows the players hand with the value of his/her hand
	public static void showPlayer() {
		System.out.println("\n" + p.getFirstName() + " has the cards");
		p.getHand().printHand();
		System.out.println("\nWith a value of: ");
		System.out.println(p.getHand().getValues());
		//if the player gets 21 after the cards are dealt dealer then shows cards and tries to tie for the win
		if (p.getHand().getValues() == 21) {
			System.out.println("\nYou have 21! We will see if the dealer can tie!");
				dealerFlip();
		}
	}
	//shows the second card dealt to dealer which is the only card that dealer shows
	public static void dealerOneCardShow() {
		System.out.println("\nThe dealer show card is a: ");
		dealer.getHand().showOne();
	}
	//checks to see if player wants to continue playing
	public static void checkToPlay() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("\nIf you would like to continue playing please type (yes) or (no) to exit the game!" );
		String reply = sc.nextLine();
		if(reply.equalsIgnoreCase("yes")) {
			newGame();
		}
		//if player replies no exits the game
		else if (reply.equalsIgnoreCase("no")){
			System.out.println("\nThanks for playing !");
			System.exit(0);
		}
	}
	//checks to see if the player or dealer has busted 
	public static void checkBust() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		//if player goes over 21 player busts || asks if player would like to continue playing
		if(p.getHand().getValues() > 21) {
			System.out.println("\nSorry that is a bust, if you would like to play again please type (play) or (no) to end game!");
			String wrd = scan.nextLine();
			if(wrd.equalsIgnoreCase("play")) { //if player types to play again new game is created
				newGame(); //calls to create new game 
			}
			else if (wrd.equalsIgnoreCase("no")){ //if player enters no
				System.out.println("\nThanks for playing !"); //tell 'em thanks
				System.exit(0); //exits the game
			}
		//if player has less than 21 player is asked to hit or stay 
		}else if(p.getHand().getValues() < 21) {
			toHitOrNot();
		//if player has 21 , we play to see if dealer can tie
		}else if (p.getHand().getValues() == 21) {
			System.out.println("You have 21! We will see if the dealer can tie!");
				dealerFlip(); //dealer cards are shown
		}
	}
	//asks the player whether they would like to hit or stay
	@SuppressWarnings("static-access")
	public static void toHitOrNot() {
		System.out.println("\nWould you like to hit or stay? (1)hit (2)stay");
		int num = scan.nextInt();
		if(num == 1) {
			//strategy pattern implemented here, if player hits 1 then hit is created
			Context hit = new Context(new Hit(p.getFirstName(),hand));
			hit.execute(dealer.getDeck().getInstance().dealCard()); //executes the hit function
			showPlayer(); //shows the players cards
			checkBust(); //then checks to see if the player busted
		}else if(num == 2) {
			//strategy pattern implemented here, if player would like to stay it essentially does nothing , but hey its the strategy pattern
			Context stay = new Context(new Stay(p.getFirstName(),hand));
			stay.execute(); //executes stay 
			System.out.println("Player decided to stay"); //message letting you know player stayed
			dealerFlip(); //begins to show the dealer cards and values so the dealer can try to beat you
		}
	}
	//makes comparisons to see who wins the hand
	public static void whoWins() {
		//if player hand is greater than the dealers hand 
		if(p.getHand().getValues() > dealer.getHand().getValues()) {
			System.out.println("\nPlayer has beat the dealer");
			checkToPlay(); //method call to ask player if he/she wants to play again
			//if dealer has a higher hand value player loses
		}if(p.getHand().getValues() < dealer.getHand().getValues()) {
			System.out.println("\nSorry but the dealer won"); //tells player sorry
			checkToPlay(); // asks player to play again
			//if player and dealer ties, house always wins
		}else if(p.getHand().getValues() == dealer.getHand().getValues()) { 
			System.out.println("\nIt is a tie! To bad dealer always wins a tie"); 
			checkToPlay(); //asks player to play again
		}
	}
	//makes dealer show both cards with values , only the second card is shown in the beginning like in regular blackjack
	public static void dealerFlip() {
		System.out.println("\nDealer cards are: ");
		dealer.getHand().printHand(); //prints the dealers hand
		System.out.println("\nDealer has a total of: ");
		System.out.println(dealer.getHand().getValues()); //prints the dealers hand value
		if(dealer.getHand().getValues()<17) { //if dealers hand is less than 17 dealer must hit
			dealerHit(); //calls dealer hit to deal card
		}
		else if(dealer.getHand().getValues()>21) { // if dealer goes over 21 when player stays under, player wins
			System.out.println("\nDealer has busted! You Win!!!");
			checkToPlay(); //calls method to ask player if he/she wants to play again
		}else if(dealer.getHand().getValues() > 16 && dealer.getHand().getValues() <= 21) { //if dealer is about 16 and under or equal to 21 dealer must stay
			whoWins(); //method call to see who wins if this is hit
		}
	}
	//method to deal a card to dealer if he must hit
	@SuppressWarnings("static-access")
	public static void dealerHit() {
		System.out.println("Dealer has hit: ");
		dealer.getHand().addCard(dealer.getDeck().getInstance().dealCard()); //deals card to dealer
		dealerFlip(); //calls dealer flip to check values again
		
	}
	//returns the dealer and player hand to the deck in order to start a new game
	@SuppressWarnings("static-access")
	public static void returnHands() {
		int size1 = p.getHand().getCount();     //without these variables adding hands will not work correctly 
		int size2 = dealer.getHand().getCount();
		//for every card in the players hand , give cards back to dealer to add to deck
		for(int i = 0; i<size1;i++) {
			dealer.getDeck().getInstance().addCard(p.getHand().giveCards());
		}
		//for every card in the dealers hand, add those cards back to deck 
		for(int i = 0; i<size2;i++) {
			dealer.getDeck().getInstance().addCard(dealer.getHand().giveCards());
		}
		
			
		
	}
	//starts a new game if the player wants to play again
	@SuppressWarnings("static-access")
	public static void newGame() {
		//lets player know the cards are being added to the deck
		System.out.println("\nReturning cards to the deck!");
		returnHands(); //calls to add cards from all hands || player and dealer || back to the deck 
		
		System.out.println("\nDealer is shuffling the deck");
		
		dealer.getDeck().getInstance().shuffleDeck(); //shuffles the deck again
		System.out.println("\nDealing cards.....");
		
		startDeal(); //deals cards again
		showPlayer();	//shows the players cards for new round	 
		dealerOneCardShow(); //shows the one face up card for the dealer
		toHitOrNot(); //calls to ask if player would like to hit or stay
		checkBust(); //checks to see if player busts or not, and call rest of methods from in that method
				
	}
	
	//main asks person if they would like to play black jack
	public static void main(String[] args) {
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				
				System.out.println("Would you like to play BlackJack? (Yes) or (No) ");
				String ans = sc.nextLine();
				if (ans.equalsIgnoreCase("yes")){
					//if answer is yes start game is called to initialize the game
						startGame();
				}else if(ans.equalsIgnoreCase("no")){ //if no prints message to come back and play later
						System.out.println("Thats fine come back and try again later");
						System.exit(0); //exits the program
				}
				//once cards are dealt show player is called
				showPlayer();
				
				dealerOneCardShow();
				toHitOrNot();
				checkBust();
				
				
				
		
	}
}