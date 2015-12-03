package project_poker;

import java.util.*;

public class MainPoker {

	public static void main(String[] args) {
		Poker poker = new Poker();
		Scanner in = new Scanner(System.in);
		double cash, totalCash;
		boolean run = true;
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
				totalCash = cash;
				if(cash < 1) {
					do {
						System.out.println("Error: Please input a number more than or equal to $1.00.");
						System.out.print("Enter an amount to bet: $");
						in.nextDouble();
					} while(cash < 1);
				}
				String playAgain;
				System.out.print("Do you want to play again? (Y or N) ");
				if (in.hasNext()) {
					playAgain = in.next();
					if (playAgain.equals("Y") || playAgain.equals("y")) {
						run = true;
					}
					else if (playAgain.equals("N") || playAgain.equals("n")) {
						run = false;
						System.out.println("Your total winnings: " + totalCash);
					}
					else { // the program ends if the input is invalid.
						System.out.println("Error: The input is invalid.");
						System.out.println("The game will now end.");
						System.out.println("Your total winnings: " + totalCash);
					}
				}   
			} while(cash < 1);
		
		poker.bet(cash);
		poker.displayPot();
		}
	}
}
