import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * GrammarApp contains a main program that prompts a user for the name of a
 * grammar file and then gives the user the opportunity to generate random
 * versions of various elements of the grammar.
 * 
 * @author: Robert Damore and Stuart Reges, modified by B. Goldner
 * @version: Winter 2016
 */

public class GrammarApp {
	
	private static Scanner console = new Scanner(System.in); //Class scanner to keep up with user input
	
	public static void main(String[] args) {
		boolean fileFound = false;
		System.out.println("Welcome to the random sentence generator.");
		System.out.println();

		while(!fileFound){
			// open grammar file and read it in
			System.out.print("What is the name of the grammar file? ");
			String fileName = console.nextLine();
			System.out.println();
			List<String> grammar = loadGrammar(fileName);

			// construct the grammar solver
			try{
				GrammarSolver solver = new GrammarSolver(Collections.unmodifiableList(grammar));
				runSolver(console, solver);
				fileFound = true;
			}catch(IllegalArgumentException ex){
				System.out.println(ex.getMessage());
			}
		}
	}

	/**
	 * Adds lines from file to List. 
	 * Recursively asks for another file if not found.
	 * @param fileName name of grammar file
	 * @return List of lines from file
	 */
	private static List<String> loadGrammar(String fileName) {
		try{
			File file = new File(fileName);
			Scanner input = new Scanner(file);
			ArrayList<String> list = new ArrayList<String>();
			while(input.hasNext()){
				list.add(input.nextLine());
			}
			input.close();
			return list;
		}catch(FileNotFoundException ex){
			System.out.println(ex.getMessage());
			System.out.print("What is the name of the grammer file? ");
			String nextFileName = console.nextLine();
			return loadGrammar(nextFileName);
		}
	}

	/** Interaction with end-user to generate elements of the grammar
	 * precondition : console open for console reading, solver initialized
	 * post: allows the user to repeatedly pick a grammar element to generate
	 */
	public static void runSolver(Scanner console, GrammarSolver solver) {
		System.out.println();
		System.out.println("Available symbols to generate are:");
		System.out.println(solver.getSymbols());
		System.out.print("What do you want generated (return to quit)? ");
		String target = console.nextLine();
		while (target.length() != 0) {
			if (!solver.grammarContains(target))
				System.out.println("Illegal symbol");
			else {
				System.out.print("How many do you want me to generate? ");
				if (!console.hasNextInt())
					System.out.println("that's not an integer");
				else {
					int number = console.nextInt();
					if (number < 0)
						System.out.println("no negatives allowed");
					else {
						try{
							String[] answers = solver.generate(target, number);
							System.out.println("****************************************");
							for (int i = 0; i < number; i++)
								System.out.println(answers[i]);
						}catch(IllegalArgumentException ex){
							System.out.println(ex.getMessage());
						}
					}
				}
				console.nextLine();  // consume the whitespace left over from the integer input
			}
			System.out.println();
			System.out.println("Available symbols to generate are:");
			System.out.println(solver.getSymbols());
			System.out.print("What do you want generated (return to quit)? ");
			target = console.nextLine();
		}
	}
}