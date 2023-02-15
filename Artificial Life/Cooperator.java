/**
 * Cooperator.java
 * @author Sira Nassoko
 * The Cooperator is a type of organism that always 
 * cooperates with its peers.
 */

public class Cooperator extends Organism {


	/**
	 * Constructor for the Cooperator class
	 */
	public Cooperator() {
		super();
	    type = "Cooperator";
		coopProb = 1.0;
	}

	@Override
    /**
     * Returns the type of this Organism as a string.
     * @return String		type of Organism
     */
	public String getType() {
		return type;
	}

	@Override
    /**
     * Called by update when the organism can reproduce. 
     * Creates an offspring organism and returns it.
     * @return Organism		returns offspring
     */
	public Organism reproduce() {
		return new Cooperator();
	}

	@Override
    /**
     * Returns the cooperation probability of this organism.     
     * @return double		the cooperation probability
     */
	public double getCooperationProbability() {
		return coopProb;
	}

	@Override
    /**
     * Returns whether or not the organism cooperates.
     * @return boolean		true for Cooperator organism
     */
	public boolean cooperates() {
		return true;
	}
  
}