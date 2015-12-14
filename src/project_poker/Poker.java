package project_poker;

public class Poker {

	private double pot, payout, deposit;
	private int round, matchCard, matchSuit, straight, royalFlush, cardsDrawn;
	private int[][] hand = new int[5][2];
	private int[][] deck = new int[52][2];
	private int[][] shuffledDeck = new int[52][2];
	private int[] rFlush = {1, 10, 11, 12, 13};
	private String suit[] = {"", "Spades", "Clubs", "Diamonds", "Hearts"};
	private String value[] = {"", "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	private String payoutText;
	private boolean twoPair = false;
	
	public Poker() {
		pot = 0;
		round = 1;
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
	
	public void resetRound() {
		pot = 0;
		straight = 0;
		royalFlush = 0;
		payout = 0;
		round++;
		matchCard = 0;
		matchSuit = 0;
		cardsDrawn = 0;
		payoutText = "";
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
    
    public void getHand() {
    	for (int i = 0; i < 5; i++) {
    		hand[i][0] = shuffledDeck[i][0];
    		hand[i][1] = shuffledDeck[i][1];
    		cardsDrawn++;
    	}
    }
    
    /**
     * This method will use a for loop that displays the user's hand..
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
    
	public void checkHand() {
		// Checking for flush
		for(int i = 1; i < 5; i++) {
			if(hand[0][0] == hand[i][0]) {
				matchSuit++;
			}
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
			if((hand[i-1][1] + 1) == hand[i][1]) {
				straight++;
			} else if(hand[0][1] == 1 && hand[1][1] == 10 && hand[2][1] == 11 && hand[3][1] == 12 && hand[4][1] == 13) {
				straight = 4;
			}
		}
		// Checking for royal flush
		for(int i = 0; i < 5; i ++) {
			if(hand[i][1] == rFlush[i]) {
				royalFlush++;
			}
		}
	}
	
	public void determinePayout() {
		if(twoPair && matchCard == 2) { // full house
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
			deposit -= pot;
		} else {
			System.out.println("You win $" + (pot+(pot*payout)) + "!");
			deposit += (pot+(pot*payout));
		}
	}
		
	public void bet(double amount) {
		pot = amount;
		deposit -= amount;
	}
	
	public void deposit(double amount) {
		deposit += amount;
	}
	
	public double displayDeposit(){
		return deposit;
	}
	
	
	public int round() {
		return round;
	}
}