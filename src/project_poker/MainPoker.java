package project_poker;

import java.util.*;

public class MainPoker {

	public static void main(String[] args) {
		Poker poker = new Poker();
		Scanner in = new Scanner(System.in);
		double cash;
		boolean run = true;
		double deposit = 0;
		System.out.print("Enter the amount of money that you want to deposit: $");
		if (in.hasNextDouble()) {
			deposit = in.nextDouble();
		}
		else {
			while (deposit <= 0) {
				System.out.println("Error: invalid deposit, try again");
				System.out.print("Enter the amount of money that you want to deposit: $");
				deposit = in.nextDouble();
			}
		}
		while (run == true) {
			do {
				System.out.print("Enter an amount to bet: $");
				if(!in.hasNextDouble()) {
					do {
						System.out.println("Error: Please input valid integer.");
						System.out.print("Enter an amount to bet: $");
						in.nextDouble();
					} while(!in.hasNextDouble());
				}
				cash = in.nextDouble();
				deposit -= cash;
				if(cash < 1) {
					do {
						System.out.println("Error: Please input a number more than or equal to $1.00.");
						System.out.print("Enter an amount to bet: $");
						in.nextDouble();
					} while(cash < 1);
				}
				poker.bet(cash);
				System.out.println("The pot is: " + poker.displayPot());
				// poker.randomCard();
				poker.shuffleDeck();
				poker.getHand();
                poker.displayHand();
                System.out.print("Which cards do you want to replace (1-5 for cards respectively, 0 to quit): ");
                
                while (in.hasNextInt()) {
                    int replaceCard = in.nextInt();
                    if (replaceCard == 0) 
                        break;
                    else if (replaceCard >= 1 && replaceCard <= 5) 
                        poker.replace(replaceCard - 1);
                    else {
                        while (replaceCard < 0 || replaceCard > 5) {
                            System.out.println("Error: Invalid input please try again.");
                            System.out.print("Which cards do you want to replace (1-5 for cards respectively, 0 to quit): ");
                            replaceCard = in.nextInt();
                        }
                    }
                    System.out.print("Which cards do you want to replace (1-5 for cards respectively, 0 to quit): ");
                }
                
                poker.checkHand();
                poker.determinePayout();
                System.out.println("You got: " + poker.getHandText());
                cash = (cash + (cash * poker.getPayout()));
        		System.out.printf("The payout: %.2f" , cash);
        		
				String playAgain;				
				System.out.print("\nDo you want to play again? (Y or N) ");
				deposit += cash;
				if (in.hasNext()) {
					playAgain = in.next();
					if (playAgain.equals("Y") || playAgain.equals("y")) {
						run = true;
						poker.resetRound();
					}
					else if (playAgain.equals("N") || playAgain.equals("n")) {
						run = false;
						System.out.printf("Your total winnings: %.2f" , deposit);
					}
					else { // the program ends if the input is invalid.
						run = false;
						System.out.println("Error: The input is invalid.");
						System.out.println("The game will now end.");
						System.out.printf("Your total winnings: %.2f" , deposit);
					}
				}   
			} while(cash < 1);
		
		}
		
	}
}
