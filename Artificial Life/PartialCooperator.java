/**
 * PartialCooperator.java
 * @author Sira Nassoko
 * The Partial Cooperator is an organism that cooperates 
 * with probability 0.5.
 */

public class PartialCooperator extends Organism {

	/**
	 * Constructor for PartialCooperator
	 */
	public PartialCooperator() {
		super();
		type = "PartialCooperator";
		coopProb = 0.5;
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
		return new PartialCooperator();
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
     * @return boolean		true if it cooperates, false otherwise
     */
	public boolean cooperates() {
		return coopProb > Population.rand.nextDouble();
	}
}
