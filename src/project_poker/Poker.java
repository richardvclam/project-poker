package project_poker;

public class Poker {
	private double pot;
	private int round;
	
	public Poker() {
		pot = 0;
		round = 1;
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
