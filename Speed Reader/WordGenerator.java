/**
 * @author Sira Nassoko
 * 
 * WordGenerator.java
 * The WordGenerator class that wraps around the Scanner
 * class and functionality.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordGenerator {

	private Scanner sc;
	private int wordCount;
	private int senCount;
	
	/**
	 * Constructs a new generator that processes 
	 * text from the given file
	 * @param filename
	 * @throws FileNotFoundException 
	 */
	public WordGenerator(String filename) throws FileNotFoundException {
		File f = new File(filename);
		sc = new Scanner(f);
		wordCount = 0;
		senCount = 0;
	}
	
	/**
	 * Returns true if the underlying Scanner 
	 * of this WordGenerator has text left to process
	 * @return boolean	true if there is text left, false otherwise
	 */
	public boolean hasNext() {
		return sc.hasNext();
	}
	
	/**
	 * Returns the next word of the underlying Scanner. 
	 * If the Scanner does not have words left, then 
	 * the behavior of next() is undefined (i.e, you 
	 * don’t have to check or handle this case).
	 * @return String	the next String to process
	 */
	public String next() {
		if (sc.hasNext()) {
			String temp = sc.next();
			
			if (temp.matches(".*[?!.]")) {
				senCount++;
			}
			wordCount++;
			return temp;
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the number of words produced by 
	 * the WordGenerator so far.
	 * @return int	the number of words in a displayed text
	 */
	public int getWordCount() {
		return wordCount;
	}
	
	/**
	 * Returns the number of sentences produced 
	 * by the WordGenerator so far. A sentence 
	 * is the number of occurrences a 
	 * sentence-ending punctuation mark appears at 
	 * the end of a word: ‘.’, ‘!’, or ‘?’.
	 * @return int	the number of sentences in a displayed text
	 */
	public int getSentenceCount() {
		return senCount;
	}
	
	/**
	 * Closes the scanner in the WordGenerator
	 */
	public void close() {
		sc.close();
	}

}
