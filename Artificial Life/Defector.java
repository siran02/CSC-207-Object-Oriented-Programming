/**
 * Defector.java
 * @author Sira Nassoko
 * The Defector is a type of organism that never
 * cooperates with its peers.
 */
public class Defector extends Organism{
	
	public Defector () {
		super();
		type = "Defector";
		coopProb = 0;
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
		return new Defector();
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
     * @return boolean		false for Defector organism
     */
	public boolean cooperates() {
		return false;
	}
}
