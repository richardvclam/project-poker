package project_poker;

import java.util.*;

public class MainPoker {

	public static void main(String[] args) {
		Poker poker = new Poker();
		Scanner in = new Scanner(System.in);
		double cash;
		
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
			if(cash < 1) {
				do {
					System.out.println("Error: Please input a number more than or equal to $1.00.");
					System.out.print("Enter an amount to bet: $");
					in.nextDouble();
				} while(cash < 1);
			}
		} while(cash < 1);
		
		poker.bet(cash);
		poker.displayPot();
	}
}
