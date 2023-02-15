/**
 * @author Sira Nassoko
 * @date October 31, 2022
 * @name BuncoGame.java
 * @sources none
 * @help none
 * I confirm that the above list of sources is complete AND that 
 * I have not talked to anyone else (e.g., CSC 207 students) about 
 * the solution to this problem.
 * 
 * BuncoGame.java
 * This is the client program for the Bunco game.
 *
 * Note/Disclaimer: There were some discrepancies between the project description
 * and the actual rules of the game in the linked wikipedia article, I mostly followed
 * the rules of the game in the linked resource, but actually used the less specific 
 * criteria from the assignment description for tie breaking purposes.
 */

package bunco;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BuncoGame {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Bunco bunco = new Bunco(); // initialize global bunco game
		boolean playing = true;

		System.out.println("Welcome to Bunco! How many players will be playing?");
		System.out.println("(Enter 1 to play against the computer)");
		
		while (playing) {		
			try {	
				if (bunco.getIsFirst()) { // if this is the first game, 

					while (!sc.hasNextInt()) { // check if input is int
						throw new IllegalArgumentException("Please enter an integer!");
					}
					
					int playerNum = sc.nextInt();
					if (playerNum < 1) { // check if int is valid
						throw new InputMismatchException("Invalid input, please enter an int > 0!");
					} 
			
					bunco.addPlayers(sc, playerNum); // ask user to input player names
				}
		
				bunco.decideStartingPlayer(); // decide who starts

				boolean continueRound; // flag to end or continue one round
				for (int i = 0; i < 6; i ++) { // play 6 rounds
					continueRound = true; 
					while(continueRound) {
						for (Player p : bunco.getPlayers()) {
							bunco.playRound(p);
							if (bunco.getRoundOverStatus()) {
								continueRound = false;
								break;
							}
						}
					}
					
					bunco.roundWinner(); // print the winner of the round
					bunco.resetRound(); // reset the round and update round number
				}
				
				bunco.gameWinner(); // after all rounds are completed, decide winner of game
				
				System.out.println("Would you like to play again? Enter yes or no.");
				String response = sc.next();
				if (response.equalsIgnoreCase("no")) {
					playing = false; // exit loop
					break;
				} else if (response.equalsIgnoreCase("yes")) {
					System.out.println("Would you like to enter different/new players? Enter yes or no.");
					response = sc.next();
					
					if (response.equalsIgnoreCase("no")) { // create new game with same players
						Bunco temp = bunco;
						temp.endGame();
						bunco = new Bunco(temp);
						playing = true;
						System.out.println("Okay, creating game with same players.");
				
					} else if (response.equalsIgnoreCase("yes")) { // create completely new game
						bunco.endGame();
						bunco = new Bunco();
						System.out.println("Okay, creating new game.");
						System.out.println("Welcome to Bunco! How many players will be playing?");
						System.out.println("(Enter 1 to play against the computer)");

						playing = true;
						continue;
					} else {
						throw new IllegalArgumentException("Please enter yes or no!");
					}	
				} else {
					throw new IllegalArgumentException("Please enter yes or no!");
				}
			} catch (InputMismatchException e) {
				System.err.println(e.getMessage());
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
				sc.next();
			}
		}
		
		System.out.println("Thanks for playing!");
		sc.close();
	}

}
