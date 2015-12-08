package project_poker;

public class Poker {
	private double pot;
	private int round;
	private int matchCard;
	private int matchSuit;
	private int[][] hand = {{0,0},{1,0},{2,0},{3,0},{0,4}};
	private double payout;
	
	public Poker() {
		pot = 10;
		round = 1;
		matchCard = 0;
		matchSuit = 0;
		payout = 0;
	}
	
	public void randomCard() {
		
	}
	
	/**
     * This will replace the player's card depending on which card number they want to replace
     * @parameter cardNumber is the place from 1-5 (left to right) of the user's cards
     * This takes the ordinal value of the card number in the array and does another randomCard().
     */
    public void replace(int cardNumber) {
    }
    
    /**
     * This method will use a for loop that displays the user's hand..
     */
    public void displayHand() {
        
        System.out.println("Your hand is: " + hand);
    }
	
	public void checkHand() {
		for(int i = 1; i < 5; i++) {
			if(hand[0][0] == hand[i][0]) {
				matchSuit++;
			}
		}
		
		for(int i = 0; i <= 3; i++) {
			for(int j = 1; (i + j) <= 4; j++) {
				if(hand[i][1] == hand[i+j][1]) {
					matchCard++;
					break;
				}
			}
		}
	}
	
	public void determinePayout() {
		if(matchSuit == 4) {
			payout = 0.05;
		} else if(matchCard == 1) {
			payout = 0.01;
		} else if(matchCard == 2) { // three of a kind
            payout = 0.03;
        } else if(matchCard == 3) { // four of a kind 
            payout = 0.25;
        } else if(matchCard == 0) { // no pair
            payout = 0;
        }
		System.out.println("Match card: " + matchCard);
		System.out.println("Match suit: " + matchSuit);

		
		System.out.println("Payout is $" + pot*payout);
	}
	
	public void bet(double amount) {
		pot += amount;
	}
	
	public void displayPot(){
		System.out.println(pot);
	}
	
	public int round() {
		return round;
	}
}
