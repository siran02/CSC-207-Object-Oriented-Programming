/**
 * @author Sira Nassoko
 * @date November 14, 2022
 * @name SpeedReader.java
 * @sources none
 * @help none
 * I confirm that the above list of sources is complete AND that 
 * I have not talked to anyone else (e.g., CSC 207 students) about 
 * the solution to this problem.
 * 
 * SpeedReader.java
 * This is the client program for the Speed Reader.
 */
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class SpeedReader{
	static final int FACTOR = 2;
	static final int SECOND = 1000;
	static final int MINUTE = 60;

	public static void main(String[] args){
		
		try {

			
			System.out.println("Welcome to Speed Reader!");
			System.out.println("Please answer the filename, width, height, font size, and wpm: ");

			if (args.length != 5) { // if the user inputs more than 5 arguments, throw an exception
				throw new InputMismatchException();
			} 
			
			for (int i = 1; i <= 4; i++) {
				// parsing throws an error before assigning the args to a variable
				Integer.parseInt(args[i]);
			}
			
			// otherwise, continue assigning args to respective variables
			String filename = new String("textfiles/quiz_texts/" + args[0]); // text files within seperate folder
			int width = Integer.parseInt(args[1]);
			int height = Integer.parseInt(args[2]);
			int fontsize = Integer.parseInt(args[3]);
			int wpm = Integer.parseInt(args[4]);
			
			// drawing panel construction
			DrawingPanel panel = new DrawingPanel(width, height);
			Graphics g = panel.getGraphics();
			Font f = new Font("Courier", Font.BOLD, fontsize);
			g.setFont(f);
			
			WordGenerator wg = new WordGenerator(filename);
			
			// until the end of the file
			while(wg.hasNext()) {
				String curr = wg.next();
				g.drawString(curr, (width + curr.length() * fontsize/FACTOR) / FACTOR - (curr.length() * fontsize/FACTOR), height / FACTOR);	
				Thread.sleep((int) SECOND / (wpm / MINUTE)); // wpm conversion			
				panel.clear(); // clear the panel after every word
			}
			
			System.out.println("Sentence count: " + wg.getSentenceCount());
			System.out.println("Word count: " + wg.getWordCount());
			
			wg.close();
			
		} catch(NumberFormatException e) { // if the user supplies arguments other than strings
			System.err.println("Please enter valid positive integers for relevant fields.");
			System.exit(1);
		} catch (IllegalArgumentException e) { // if the user supplies negative integers
			System.err.println("Please enter positive integers only for the width, height, font size, and wpm fields");
			System.exit(1);
		} catch (InputMismatchException e) { // if the user does not supply exactly five arguments
			System.err.println("Usage: SpeedReader <filename> <width> <height> <font size> <wpm>");
			System.exit(1);
		} catch (FileNotFoundException e) { // if the file is not found
			System.err.println("File was not found. Please input valid file name");
		} catch (InterruptedException e) { // for the thread sleep function
			System.err.println("Thread interrupted.");
		}
	}

}

