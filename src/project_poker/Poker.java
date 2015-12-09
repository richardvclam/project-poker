package project_poker;

public class Poker {

	private double pot;
	private int round;
	private int matchCard;
	private int matchSuit;
	private int[][] hand = new int[5][2];
	private double payout;
	private String suit[] = {"", "Spades", "Clubs", "Diamonds", "Hearts"};
	private String value[] = {"", "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

	private String payoutText;
	private boolean twoPair = false;
	
	private int straight, royalFlush;
	
	private int[] rFlush = {10, 11, 12, 13, 1};
	
	
	public Poker() {
		pot = 0;
		round = 1;
		matchCard = 0;
		matchSuit = 0;
		payout = 0;
	}
	
	public void randomCard() {
		for (int i = 0; i <= 4; i++) {
			hand[i][0] = (int)(Math.random() * 4 + 1);
			hand[i][1] = (int)(Math.random() * 13 + 1);
			
		}
	}
	
	/**
     * This will replace the player's card depending on which card number they want to replace
     * @parameter cardNumber is the place from 1-5 (left to right) of the user's cards
     * This takes the ordinal value of the card number in the array and does another randomCard().
     */
    public void replace(int cardNumber) {
    	hand[cardNumber][0] = (int)(Math.random() * 4 + 1);
    	hand[cardNumber][1] = (int)(Math.random() * 13 + 1);
    	displayHand();
    }
    
    /**
     * This method will use a for loop that displays the user's hand..
     */
    public void displayHand() {
        System.out.println("Your hand is: ");
    	for (int i = 0; i <= 4; i++) {
        	System.out.print(value[hand[i][1]] + " of " + suit[hand[i][0]] + ", ");
        }
    	System.out.println("");
    }
	public void checkHand() {
		// Checking for suits
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
			} else if(hand[3][1] == 13 && hand[4][1] == 1) {
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
		System.out.println("Straights: " + royalFlush);

		
	}
		
	public void bet(double amount) {
		pot += amount;
	}
	
	public double displayPot(){
		return pot;
	}
	
	public double getPayout() {
		return payout;
	}
	
	public int round() {
		return round;
	}
}
