/**
 * Organism.java
 * @author Sira Nassoko
 * This is the Organism class, which represents individual 
 * bacterium in the population.
 */

public abstract class Organism {

	/* data fields */
    protected double coopProb;
    protected int energy;
    protected String type;

    /**
     * Constructor for Organism object
     */
    public Organism() {
    	energy = 0;
    }

    /**
     * Updates the given organism. By default, an organism 
     * gains one new energy point.
     */
    public void update() {
        incrementEnergy();
    }


    /**
     * Returns current energy of organism
     * @return int		current energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * Increments energy by 1
     */
    public void incrementEnergy() {
        energy++;
    }

    /**
     * Decrements energy by 1
     */
    public void decrementEnergy() {
    	if (energy > 0) {
    		energy--;
    	}
    }
    
    
    /*
     * Reset energy to 0
     */
    public void resetEnergy() {
        energy = 0;
    }

    /**
     * Returns the type of this Organism as a string.
     * @return String		type of Organism
     */
    public abstract String getType();

    /**
     * Called by update when the organism can reproduce. 
     * Creates an offspring organism and returns it.
     * @return Organism		returns offspring
     */
    public abstract Organism reproduce();

    /**
     * Returns the cooperation probability of this organism.     
     * @return double		the cooperation probability
     */
    public abstract double getCooperationProbability();

    /**
     * Returns whether or not the organism cooperates.
     * @return boolean		true if it cooperates, false otherwise
     */
    public abstract boolean cooperates();
}