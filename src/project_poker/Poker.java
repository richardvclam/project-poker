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

	
	public Poker() {
		pot = 10;
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
		if(matchSuit == 4) { //flush
			payout = 0.05;
		} else if(matchCard == 1) { // one pair
			payout = 0.01;
		} else if(matchCard == 2) { // three of a kind
            payout = 0.03;
        } else if(matchCard == 3) { // four of a kind 
            payout = 0.25;
        } else if(matchCard == 0) { // no pair
            payout = 0;
        }		
		
	}
	
	public void bet(double amount) {
		pot += amount;
	}
	
	public double displayPot(){
		return pot;
	}
	
	public int round() {
		return round;
	}
}
