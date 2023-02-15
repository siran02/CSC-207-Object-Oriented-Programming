package bunco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Bunco.java
 * @author Sira Nassoko
 * This class creates a Bunco object, where most of
 * the gameplay will take place.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Bunco {
	/* data fields */
	private int round;
	private DiceBox diceBox;
	private ArrayList<Player> players;
	private boolean roundOver;
	private boolean first;
	
	
	/* score constants */
	private static final int BUNCO = 21;  
	private static final int MINI_BUNCO = 5;  
	private static final int ROUND_WIN = 21;
	
	/**
	 * Constructor for the Bunco game, initialized
	 * a list of players and a Dice Box. Also flags
	 * that this is the first game.
	 */
	public Bunco () {
		round = 1;
		this.diceBox = new DiceBox();
		this.players = new ArrayList<>();
		this.first = true;
	}
	
	/**
	 * Constructor for the Bunco game, initializes 
	 * players field using data from old game.
	 * @param Bunco		an old Bunco game
	 */
	public Bunco (Bunco b) {
		round = 1;
		this.diceBox = new DiceBox();
		this.players = b.players;
		this.first = false;
	}
	
	
	/**
	 * Returns whether this is the first game or not
	 * @return boolean	
	 */
	public boolean getIsFirst() {
		return this.first;
	}

	
	/**
	 * Returns the current round.
	 * @return int	the current round
	 */
	public int getRound () {
		return this.round;
	}

	/**
	 * Set the round to a given int.
	 * @param num	the round to be set
	 */
	public void setRound (int num) {
		this.round = num;
	}
	
	/**
	 * Return the dice box.
	 * @return DiceBox	the dice box for the current game
	 */
	public DiceBox getDiceBox() {
		return this.diceBox;
	}
	
	/**
	 * Returns list of players. For debugging purposes
	 * @return ArrayList<Player> an array list of players
	 */
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	/**
	 * Prints player names. For debugging purposes
	 */
	public void printPlayers() {
		for (Player p : this.players) {
			System.out.println(p.getName());
		}
	}
	
	/**
	 * Increment the round number
	 */
	public void incrementRoundNum() {
		this.round++;
	}
	
	/**
	 * Check if at least one of the dices matches
	 * the current round number.
	 * @return boolean	whether one or more of the dice match the round
	 */
	public boolean canContinue () {
		return this.diceBox.getDice1().getFaceVal() == this.round || 
				this.diceBox.getDice2().getFaceVal() == this.round || 
				this.diceBox.getDice3().getFaceVal() == this.round; 
	}
	
	/**
	 * Check whether the roll is a Bunco.
	 * If all three dice match the current 
	 * round number, the player has won a Bunco.
	 * @return boolean	if the player has gotten a Bunco
	 */
	public boolean isABunco () {
		return this.round == 
				this.diceBox.getDice1().getFaceVal() && 
				this.diceBox.sameFaceVal();
	}
	
	/**
	 * Check whether the roll is a mini Bunco.
	 * If all three dice are of the same face value,
	 * but don't match the round number, it is a mini Bunco.
	 * @return boolean	if the player has gotten a mini Bunco
	 */
	public boolean isAMiniBunco () {
		return this.diceBox.sameFaceVal();
	}
	

	/**
	 * Adds players to a game of Bunco
	 * @param sc	scanner to read input
	 * @param playerNum		number of players
	 */
	public void addPlayers(Scanner sc, int playerNum) {
		// store names in local array to check for unique names
		ArrayList<String> player_names = new ArrayList<String>();
		System.out.println("There are " + playerNum + " players in the game.");

		if (playerNum == 1) {
			this.players.add(new Player());
			player_names.add(new Player().getName());
		}
		
		for (int i = 1; i <= playerNum; i++) { // loop to collect player names
			System.out.println("Player " + i + ", what is your name?");
			String curr_name = sc.next();
				
			if (player_names.contains(curr_name)) {
				System.err.println("Names must be unique! Please enter a different name.");
				i--; // decrease i so we don't skip the player
			} else { // otherwise add player to set
				this.players.add(new Player(curr_name));
				player_names.add(curr_name);
			}
		}
	}
		
	/**
	 * Decide who will go first by having each
	 * player roll the dice box, and finding
	 * the player with the biggest roll.
	 * @return String	starting player
	 */
	public void decideStartingPlayer() {
		/*
		 * Note: Wikipedia page and project description does not specify if rolling a
		 * Bunco during the 0th round (the round that decides who starts the game)
		 * constitutes as 21 points or just the sum of the three dice. I went with 
		 * the latter, as I could not find any consistent conventions online.
		 */
		int startingRoll = 0;
		
		System.out.println("Let's play Bunco! First, we'll decide who goes first.");
		System.out.println("Rolling dice...");
		
		for (Player p : this.players)  {
			// set the starting roll for each player to decide order
		    this.diceBox.rollDices();
		    System.out.println(p.getName() +  " rolled " + this.diceBox.getDice1().getFaceVal() + ", " + this.diceBox.getDice2().getFaceVal() + ", and " + this.diceBox.getDice3().getFaceVal() + "!");
		    startingRoll = this.diceBox.sumDices();
		    p.setScore(startingRoll); 
		}
		for (Player p : this.players) {
			startingRoll = Math.max(startingRoll, p.getScore()); // find max score
		}
		
		
		for (Player p : this.players) {
		    if (p.getScore() == startingRoll) {
				System.out.println("Starting player is: " + p.getName());
				System.out.println();

				// move them up in the order of turns
				if (this.players.indexOf(p) != 0) {
				    this.players.remove(p);
				    this.players.add(0,p);
			    }
				break; // break to avoid concurrent modification
		    }
		    
		    p.resetScore(); // reset scores for game play
		}
	}
	
	/**
	 * Plays a round for a given player, keeps going
	 * until none of the dice match the round number.
	 * @param p		a player to play a round for
	 */
	public void playRound (Player p) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("ooooo Round " + this.getRound() + " ooooo");
		System.out.println(p.getName() + "\'s turn!");
		System.out.println("Press return/enter to play your round " + p.getName() + ".");

		do {

			if (p.getScore() >= ROUND_WIN) { // check if round is over
				this.roundOver = true;
				return;
			}
			
			try {
			    br.readLine();
			    // executes after user presses enter	
			    this.diceBox.rollDices();
			    System.out.println(p.getName() +  " rolled " + this.diceBox.getDice1().getFaceVal() + ", " + this.diceBox.getDice2().getFaceVal() + ", and " + this.diceBox.getDice3().getFaceVal() + "!");
			
				if (this.isABunco()) { 
					// player wins 21 points for a Bunco
					System.out.println("You scored a Bunco!");
					p.setScore(p.getScore() + Bunco.BUNCO);
					this.roundOver = true; // round automatically ends	
					//return;
					
				} else if (this.isAMiniBunco ()) { 
					// player wins 5 points for a mini Bunco
					System.out.println("You scored a mini Bunco!");
					p.setScore(p.getScore() + Bunco.MINI_BUNCO);
					this.isRoundOver(); // check if round is over
	
				} else if (this.canContinue()) { 
					// check if some combination of dice match the round, award points accordingly
					if (this.diceBox.getDice1().getFaceVal() == this.round) {
						p.setScore(p.getScore() + 1);
					}
					if (this.diceBox.getDice2().getFaceVal() == this.round) {
						p.setScore(p.getScore() + 1);
					}
					if (this.diceBox.getDice3().getFaceVal() == this.round) {
						p.setScore(p.getScore() + 1);
					}
					
				} else {
					// Finish their turn and display their stats if they have an invalid roll
					System.out.println("None of your dice match the current round. Sorry!");
					System.out.println(p.getName() + ", your score is: " + p.getScore());
					p.setLifetimeScore(p.getScore());
					System.out.println();
					return;
			}	
			// Keep displaying their score after each roll
			System.out.println(p.getName() + ", your score is: " + p.getScore());
			p.setLifetimeScore(p.getScore());
			} catch (IOException e) {
			     System.err.println(e.getMessage());
			}
		} while ((this.isAMiniBunco() || this.canContinue()));
		
		
	}
	
	
	/**
	 * Checks if a round is over and sets
	 * current game flags to approproate bool.
	 */
	public void isRoundOver() {
		for (Player p : this.players) {
			if (p.getScore() >= ROUND_WIN) {
				this.roundOver = true;
				return;
			} 
		}
		this.roundOver = false;
		return;
	}
	
	/**
	 * Return round status
	 * @return boolean	state of round
	 */
	public boolean getRoundOverStatus() {
		return this.roundOver;
	}
	
	/**
	 * Reset round statistics
	 */
	public void resetRound() {
		this.roundOver = false;
		for (Player p : this.players) {
			p.setScore(0);
		}
		this.round = this.round + 1;
	}
	
	/**
	 * Calculate which player has the largest score
	 * in the current round, and adds a point to 
	 * their win count.
	 * @return String	name of winner
	 */
	public void roundWinner() {
		
		for (Player p : this.players) {
		    if (p.getScore() >= ROUND_WIN) {
		    	p.incrementRoundWinCount(); // find the player with the score largest score
		    	System.out.printf("Winner of round %d is %s\n", this.round, p.getName()); // return their name
		    	break;
		    } 
		}
		
		System.out.println("So far...");
		for (Player p : this.players) {
			System.out.println(p.getName() + " has won " + p.getWinCount() + " rounds.");
		}
		return;
	}


	/**
	 * Calculate which player won the whole game.
	 * @return String	name of winner
	 */
	public void gameWinner() {
		int winner = 0; 
		int winnerCount = 0; // check how many winners there are, or how many players won the same amount of rounds
		Player winningPlayer = null;
		
		/* for tie breaking */
		int superWinner = 0; 
		Player superWinningPlayer = null;
		
		for (Player p : this.players) {
			// find the largest score
			superWinner = Math.max(p.getLifetimeScore(), superWinner);
		    winner = Math.max(p.getWinCount(), winner);
		}
		
		for (Player p : this.players) {
		    if (p.getWinCount() == winner) {
		    	winningPlayer = p;
		    	winnerCount++;
		    } 
		    
		    if (p.getLifetimeScore() == superWinner) {
		    	superWinningPlayer = p;
		    }
		}
		
		if (winnerCount == 1) {
	    	System.out.println("Winner of the game is: " + winningPlayer.getName()); // return their name
		} else {
	    	System.out.println("There's been a tie, so we will decide the winner based of lifetime scores"); // return their name
	    	System.out.println("Winner of the game is: " + superWinningPlayer.getName()); // return their name
		}
		return;
	}
	
	/**
	 * Ends a game, for purposes of using same 
	 * players for new game.
	 */
	public void endGame() {
		this.round = 1;
		for (Player p : this.players) {
			p.resetScore();
			p.resetwinCount();
		}
		this.roundOver = false;
	}

}

