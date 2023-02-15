package bunco;

/**
 * Player.java
 * @author Sira Nassoko
 * This class creates a Player object for the Bunco game.
 * 
 */

public class Player {
	
	private String name;
	private int score;
	private int roundWinCount;
	private int lifeTimeScore;
	
	/** 
	 * Constructor for the Player object, takes a name
	 * and sets the score and wincount to 0.
	 * @param name	the players name
	 */
	public Player (String name) {
		this.name = name;
		this.score = 0;
		this.lifeTimeScore = 0;
		this.roundWinCount = 0;
	}
	
	/**
	 * Constructor for the Player object, 
	 * default constructor that sets player 
	 * name to "computer."
	 */
	public Player () {
		this.name = "computer";
		this.score = 0;
		this.lifeTimeScore = 0;
		this.roundWinCount = 0;
	}
	
	/**
	 * Get the player's name
	 * @return the player's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Return the players score
	 * @return int	players current score
	 */
	public int getScore() {
		return this.score;
	}
	
	
	/**
	 * Return the players lifetime Score
	 * (used in case of a tie)
	 * @return int	players current score
	 */
	public int getLifetimeScore() {
		return this.lifeTimeScore;
	}
	
	/**
	 * Return the players lifetime Score
	 * (used in case of a tie)
	 * @return int	players current score
	 */
	public void setLifetimeScore(int num) {
		this.lifeTimeScore += num;
	}
	
	/**
	 * Set the players score to a given value
	 * @param int	the new score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Reset the players score to 0
	 */
	public void resetScore() {
		this.score = 0;
	}
	
	/**
	 * Get the number of rounds a player
	 * has won.
	 * @return int	the amount of rounds the player has won
	 */
	public int getWinCount() {
		return this.roundWinCount;
	}
	
	/**
	 * Reset the win Count
	 */
	public void resetwinCount() {
		this.roundWinCount = 0;
	}
	
	/**
	 * Increments amount of total
	 * rounds won for a player.
	 */
	public void incrementRoundWinCount() {
		this.roundWinCount++;
	}
		
}