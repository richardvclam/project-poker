package project_poker;

public class Poker {
	private double pot, payout, deposit;
	private int matchCard, matchSuit, straight, royalFlush, cardsDrawn;
	private int[][] hand = new int[5][2];
	private int[][] deck = new int[52][2];
	private int[][] shuffledDeck = new int[52][2];
	private int[] rFlush = {1, 10, 11, 12, 13};
	private String suit[] = {"", "Spades", "Clubs", "Diamonds", "Hearts"};
	private String value[] = {"", "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	private String payoutText;
	private boolean twoPair = false, fullHouse = false;
	
	public Poker() {
		pot = 0;
		matchCard = 0;
		matchSuit = 0;
		payout = 0;
		cardsDrawn = 0;
		deposit = 0;
		
		// Creating the deck
		int count = 0;
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 13; j++) {
				deck[count][0] = i;
				deck[count][1] = j;
				count++;
			}
		}
	}
	
	/**
	 * This method shuffles the deck.
	 */
	public void shuffleDeck() {
		int currentSize = deck.length;

		for(int i = 0; i < deck.length; i++) {
			int pos = (int) (Math.random()*currentSize);
			shuffledDeck[i][0] = deck[pos][0];
			shuffledDeck[i][1] = deck[pos][1];
			deck[pos][0] = deck[currentSize - 1][0];
			deck[pos][1] = deck[currentSize - 1][1];
			currentSize--;
		}
	}
	
	/**
	 * This method is to be used at the end of a round to reset some variables back to 0.
	 */
	public void resetRound() {
		pot = 0;
		straight = 0;
		royalFlush = 0;
		payout = 0;
		matchCard = 0;
		matchSuit = 0;
		cardsDrawn = 0;
		payoutText = "";
		
		// Recreate the deck
		int count = 0;
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 13; j++) {
				deck[count][0] = i;
				deck[count][1] = j;
				count++;
			}
		}
	}

	/**
     * This will replace the player's card depending on which card number they want to replace
     * @parameter cardNumber is the place from 1-5 (left to right) of the user's cards
     * This takes the ordinal value of the card number in the array and does another randomCard().
     */
    public void replace(int cardNumber) {
    	hand[cardNumber][0] = shuffledDeck[cardsDrawn][0];
    	hand[cardNumber][1] = shuffledDeck[cardsDrawn][1];
    	cardsDrawn++;
    	displayHand();
    }
    
    /**
     * This method "draws" five cards from the shuffledDeck. This method is to be used after shuffleDeck().
     */
    public void getHand() {
    	for (int i = 0; i < 5; i++) {
    		hand[i][0] = shuffledDeck[i][0];
    		hand[i][1] = shuffledDeck[i][1];
    		cardsDrawn++;
    	}
    }
    
    /**
     * This method will use a for loop that displays the user's hand.
     */
    public void displayHand() {
    	sortHand();
        System.out.println("Your hand is: ");
    	for (int i = 0; i <= 4; i++) {
    		if(i != 0) {
    			System.out.print(", ");
    		}
        	System.out.print(value[hand[i][1]] + " of " + suit[hand[i][0]]);
        }
    	System.out.println("");
    }
    
    /**
     * This method sorts the hand's card values from smallest to largest.
     */
    public void sortHand() {
    	for(int i = 0; i < hand.length; i++) {
    		for(int j = i + 1; j < hand.length; j++) {
	    		int[] tempCard = new int[2];
	    		if(hand[i][1] > hand[j][1]) {
	    			tempCard[0] = hand[i][0];
	    			tempCard[1] = hand[i][1];
	    			
	    			hand[i][0] = hand[j][0];
	    			hand[i][1] = hand[j][1];
	    			
	    			hand[j][0] = tempCard[0];
	    			hand[j][1] = tempCard[1];
	    		}
    		}
    	}
    }
    
    /**
     * This method checks the hand for flush, pairs, straights, or a royal flush.
     */
	public void checkHand() {
		// Checking for flush
		for(int i = 1; i < 5; i++) {
			if(hand[0][0] == hand[i][0]) {
				matchSuit++;
			}
		}
		
		// Checking for full house
		if(hand[0][1] == hand[1][1] && hand[0][1] == hand[2][1] && hand[3][1] == hand[4][1]) {
			fullHouse = true;
		} else if(hand[0][1] == hand[1][1] && hand[2][1] == hand[3][1] && hand[2][1] == hand[4][1]) {
			fullHouse = true;
		}
		
		// Checking for pairs
		int tempCard = -1;
		for(int i = 0; i <= 3; i++) {
			for(int j = 1; (i + j) <= 4; j++) {
				if(hand[i][1] == hand[i+j][1]) {
					if(matchCard != 0 && hand[i][1] != tempCard) {
						twoPair = true;
					} else {
						matchCard++;
						tempCard = hand[i][1];
					}
					break;
				}
			}
		}
		
		// Checking for straight
		for(int i = 1; i < 5; i ++) {
			if(hand[0][1] == 1 && hand[1][1] == 10 && hand[2][1] == 11 && hand[3][1] == 12 && hand[4][1] == 13) {
				straight = 4;
			} else if((hand[i-1][1] + 1) == hand[i][1]) {
				straight++;
			}
		}
		// Checking for royal flush
		for(int i = 0; i < 5; i ++) {
			if(hand[i][1] == rFlush[i]) {
				royalFlush++;
			}
		}
	}
	
	/**
	 * This method determines the payout based on variables collected from checkHand().
	 */
	public void determinePayout() {
		if(fullHouse) { // full house
			payoutText = "a Full House";
			payout = 0.06;
		} else if(twoPair) {        // two pair
			payoutText = "Two Pairs";
			payout = 0.02;
		} else if(royalFlush == 5 && matchSuit == 4) {
			payoutText = "a Royal Flush";
			payout = 2.5;
		} else if(straight == 4 && matchSuit == 4) {
			payoutText = "a Straight Flush";
			payout = 0.5;
		} else if(straight == 4) {  // straight
			payoutText = "a Straight";
			payout = 0.04;
		} else if(matchSuit == 4) { // flush
			payoutText = "a Flush";
			payout = 0.05;
		} else if(matchCard == 1) { // one pair
			payoutText = "One Pair";
			payout = 0.01;
		} else if(matchCard == 2) { // three of a kind
			payoutText = "Three of a Kind";
            payout = 0.03;
        } else if(matchCard == 3) { // four of a kind 
        	payoutText = "Four of a Kind";
            payout = 0.25;
        } else if(matchCard == 0) { // no pair
        	payoutText = "no pairs";
            payout = 0;
        }
		
		System.out.println("You got " + payoutText);
		
		if(payout == 0) {
			System.out.println("You lost $" + pot + ".");
		} else {
			System.out.println("You win $" + (pot+(pot*payout)) + "!");
			deposit += (pot+(pot*payout));
		}
	}
	
	/**
	 * This method sets the pot to the amount and subtracts the amount from the deposit total.
	 * @param amount is a user input variable that determines how much they want to bet.
	 */
	public void bet(double amount) {
		pot = amount;
		deposit -= amount;
	}
	
	/** 
	 * This method adds the amount to the variable deposit
	 * @param amount is a user input variable that determines how much the user has to deposit.
	 */
	public void deposit(double amount) {
		deposit += amount;
	}
	
	/**
	 * This method returns the deposit variable.
	 * @return the deposit variable.
	 */
	public double displayDeposit(){
		return deposit;
	}
}