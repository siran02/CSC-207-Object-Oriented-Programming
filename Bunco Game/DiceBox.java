package bunco;

/**
 *  DiceBox.java
 *  @author Sira Nassoko
 *  This class creates a DiceBox object
 *  that holds three dice for gameplay.
 */
public class DiceBox {
	/* data fields */
	private Dice dice1;
	private Dice dice2;
	private Dice dice3;
	
	/**
	 * Constructor for the DiceBox object, creates
	 * a DiceBox with three dice.
	 */
	public DiceBox () {
		this.dice1 = new Dice();
		this.dice2 = new Dice();
		this.dice3 = new Dice();
	}
	
	/**
	 * Rolls all three dice in the DiceBox
	 */
	public void rollDices () {
		this.dice1.roll();
		this.dice2.roll();
		this.dice3.roll();
	}
	
	/**
	 * Sums all the dices, mostly for deciding who
	 * goes first in a given game.
	 * @return int	sum of all dice in Dice box
	 */
	public int sumDices() {
		return this.dice1.getFaceVal() + this.dice2.getFaceVal() + this.dice3.getFaceVal();
	}
	
	/**
	 * Return the first dice
	 * @return Dice  return first dice
	 */
	public Dice getDice1() {
		return this.dice1;
	}
	
	/**
	 * Return the second dice
	 * @return Dice  return second dice
	 */
	public Dice getDice2() {
		return this.dice2;
	}
	
	/**
	 * Return the third dice
	 * @return Dice  return third dice
	 */
	public Dice getDice3() {
		return this.dice3;
	}
	
	/**
	 * Check if all dices have the same face value
	 * @return boolean	whether all dice faces are the same or not
	 */
	public boolean sameFaceVal() {
		return (this.dice1.getFaceVal() == this.dice2.getFaceVal()) && 
				(this.dice1.getFaceVal() == this.dice3.getFaceVal());
	}
	

}