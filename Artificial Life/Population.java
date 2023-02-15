/**
 * Population.java
 * @author Sira Nassoko
 * This is the Population class, which represents
 * a population of organisms.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class Population {
	
	/* data fields */
	private Map<String, Integer> counts;
	private ArrayList<Organism> organisms;
	protected static Random rand;

	/**
	 * Constructor for the Population class
	 * @param counts	Map that contains all organisms and their associated count
	 * @throws IllegalArgumentException
	 */
	public Population(Map<String, Integer> counts) throws IllegalArgumentException {
		rand = new Random();
		this.counts = counts;
		this.organisms = new ArrayList<Organism>();
		
		Defector d = new Defector();
		Cooperator c = new Cooperator();
		PartialCooperator pc = new PartialCooperator();
		
		for (String o : counts.keySet()) {
			if (o.equals("Cooperator")) {
				for (int i = 0; i < counts.get("Cooperator"); i++)
					organisms.add(c);
				
			} else if (o.equals("Partial Cooperator")) {
				for (int i = 0; i < counts.get("Partial Cooperator"); i++)
					organisms.add(pc);
				
			} else if (o.equals("Defector")) {
				for (int i = 0; i < counts.get("Defector"); i++)
					organisms.add(d);
			}
		}		
	}
	
	/**
	 * Loops through all the organisms in the population and 
	 * (1) updates them (by calling their update method), 
	 * (2) checks to see if they cooperate as described above
	 * (3) checks to see if they reproduce. 
	 * 
	 * @throws Exception
	 */
	public void update() throws Exception {
		for (int i = 0; i < organisms.size(); i++) {
			// update all organisms
			organisms.get(i).update();
			
			// disperse energy from cooperators to other organisms
			if (organisms.get(i).cooperates()) {
				organisms.get(i).decrementEnergy();
				for (int j = 0; j < 8; j++) {
					organisms.get(rand.nextInt(organisms.size()-1)).incrementEnergy();
				}
			}	
	   
			// replace random organism in population if there is reproduction
			if (organisms.get(i).getEnergy() > 9) {
				organisms.get(i).resetEnergy();
				Organism temp = organisms.get(i).reproduce();
				organisms.remove(rand.nextInt(organisms.size()-1));
				organisms.add(temp);
			}
		}
		
		int def_count = 0, pc_count = 0, coop_count = 0;
		
		for (int i = 0; i < organisms.size(); i++) {
			if (organisms.get(i).getType().equals("Defector"))
				def_count++;
			else if (organisms.get(i).getType().equals("Partial Cooperator"))
				pc_count++;
			else if (organisms.get(i).getType().equals("Cooperator"))
				coop_count++;
		}
		
		counts.clear();
		counts.put("Defector", def_count);
		counts.put("Cooperator", coop_count);
		counts.put("Partial Cooperator", pc_count);
	  }

	/**
	 * Calculates the mean cooperation probability of all the organisms 
	 * in the population—the average of the cooperation probabilities 
	 * of all the organisms in the population.
	 * 
	 * @return double	the mean cooperation probability
	 */
	public double calculateCooperateMean() {
	    double mean = 0;

	    for (int i = 0; i < organisms.size(); i++) {
	      mean += organisms.get(i).getCooperationProbability();
	    }
	
	    return mean / organisms.size();
	  }
	  
	/**
	 * Returns the counts of all the organisms in the population.
	 * @return Map<String, Integer>	all of the population counts
	 */
	public Map<String, Integer> getPopulationCounts(){
		 return counts;
	}
	
}