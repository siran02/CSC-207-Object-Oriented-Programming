import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * @author Sira Nassoko
 * @date December 5, 2022
 * @name ALifeSim.java
 * @sources none
 * @help none
 * I confirm that the above list of sources is complete AND that 
 * I have not talked to anyone else (e.g., CSC 207 students) about 
 * the solution to this problem.
 * 
 * ALifeSim.java
 * This is the client program for the Artificial Life Program.
 */

public class ALifeSim {
	public static void main(String[] args) {
		try {
			if (args.length != 4) { // if wrong number of args in command line
				throw new InputMismatchException();
			}
			
			Map<String, Integer> organisms = new HashMap<String, Integer>();
			// store args from command line into map
			organisms.put("Cooperator", Integer.parseInt(args[1]));
			organisms.put("Defector", Integer.parseInt(args[2]));
			organisms.put("Partial Cooperator", Integer.parseInt(args[3]));
			
			// create population
			Population population = new Population(organisms);
			
			int iterations = Integer.parseInt(args[0]);
			for (int i = 0; i < iterations; i++) { // iterate on population
				population.update();
			}
			
			System.out.println("After " + iterations + " ticks:");
			System.out.println("Cooperators \t = " + population.getPopulationCounts().get("Cooperator"));
			System.out.println("Defectors \t = " + population.getPopulationCounts().get("Defector"));
			System.out.println("Partial \t = " + population.getPopulationCounts().get("Partial Cooperator"));
			System.out.println("Mean Cooperation Probability: " + population.calculateCooperateMean());
			
		} catch (InputMismatchException e){
			System.err.println("Wrong number of arguments");
			System.err.println("Usage: java ALifeSim <#/iterations> <#/cooperators> <#/defectors> <#/partial cooperators> \r\n");
			System.exit(1);
		} catch (IllegalArgumentException e) {
			System.err.println("Mutated organism found.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
