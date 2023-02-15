package bunco;

/**
 * Dice.java
 * @author Sira Nassoko
 * This class creates a Dice object, used for
 * the Dice Box and general gameplay.
 */
import java.util.Random;

public class Dice {
	
	/* data fields */
	private int face;
	
	/** 
	 * Dice constructor
	 */
	public Dice () {
		
	}
	
	/**
	 * Returns a random dice roll, 
	 * @return int	a number between 1 and 6 (inclusive)
	 */
	public void roll() {
		Random rand = new Random();
		this.face = rand.nextInt(6) + 1;
	}
	
	/**
	 * Getter method for current face value
	 * @return int	current face value
	 */
	public int getFaceVal() {
		return this.face;
	}
	
	/**
	 * String conversion of current dice value
	 * @return String	string representation of face
	 */
	public String toString() {
		return String.format("Dice rolled: %d", this.face);
	}
	
}
